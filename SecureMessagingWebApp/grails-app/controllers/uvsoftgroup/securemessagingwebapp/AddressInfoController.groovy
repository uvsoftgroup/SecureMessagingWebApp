package uvsoftgroup.securemessagingwebapp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AddressInfoController {

    AddressInfoService addressInfoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond addressInfoService.list(params), model:[addressInfoCount: addressInfoService.count()]
    }

    def show(Long id) {
        respond addressInfoService.get(id)
    }

    def create() {
        respond new AddressInfo(params)
    }

    def save(AddressInfo addressInfo) {
        if (addressInfo == null) {
            notFound()
            return
        }

        try {
            addressInfoService.save(addressInfo)
        } catch (ValidationException e) {
            respond addressInfo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'addressInfo.label', default: 'AddressInfo'), addressInfo.id])
                redirect addressInfo
            }
            '*' { respond addressInfo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond addressInfoService.get(id)
    }

    def update(AddressInfo addressInfo) {
        if (addressInfo == null) {
            notFound()
            return
        }

        try {
            addressInfoService.save(addressInfo)
        } catch (ValidationException e) {
            respond addressInfo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'addressInfo.label', default: 'AddressInfo'), addressInfo.id])
                redirect addressInfo
            }
            '*'{ respond addressInfo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        addressInfoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'addressInfo.label', default: 'AddressInfo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'addressInfo.label', default: 'AddressInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
