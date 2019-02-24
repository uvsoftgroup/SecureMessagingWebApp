package uvsoftgroup.securemessagingwebapp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AddressLocationInfoController {

    AddressLocationInfoService addressLocationInfoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond addressLocationInfoService.list(params), model:[addressLocationInfoCount: addressLocationInfoService.count()]
    }

    def show(Long id) {
        respond addressLocationInfoService.get(id)
    }

    def create() {
        respond new AddressLocationInfo(params)
    }

    def save(AddressLocationInfo addressLocationInfo) {
        if (addressLocationInfo == null) {
            notFound()
            return
        }

        try {
            addressLocationInfoService.save(addressLocationInfo)
        } catch (ValidationException e) {
            respond addressLocationInfo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'addressLocationInfo.label', default: 'AddressLocationInfo'), addressLocationInfo.id])
                redirect addressLocationInfo
            }
            '*' { respond addressLocationInfo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond addressLocationInfoService.get(id)
    }

    def update(AddressLocationInfo addressLocationInfo) {
        if (addressLocationInfo == null) {
            notFound()
            return
        }

        try {
            addressLocationInfoService.save(addressLocationInfo)
        } catch (ValidationException e) {
            respond addressLocationInfo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'addressLocationInfo.label', default: 'AddressLocationInfo'), addressLocationInfo.id])
                redirect addressLocationInfo
            }
            '*'{ respond addressLocationInfo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        addressLocationInfoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'addressLocationInfo.label', default: 'AddressLocationInfo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'addressLocationInfo.label', default: 'AddressLocationInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
