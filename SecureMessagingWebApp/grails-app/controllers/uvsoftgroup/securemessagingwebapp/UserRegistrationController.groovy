package uvsoftgroup.securemessagingwebapp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserRegistrationController {

    UserRegistrationService userRegistrationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        redirect(controller: "userRegistration", action: "list")
    }

    def list() {

        // params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def userRegistrations =UserRegistration.createCriteria().list (params) {

            if(params.userRegistrationId!=null && params.userRegistrationId?.toString().isLong() ) {
                Long value=params.userRegistrationId as Long
                if (value!=0) {
                    eq('userRegistrationId',value)
                }
            }
            if(params.userAddressId!=null && params.userAddressId?.toString().isLong() ) {
                Long value=params.userAddressId as Long
                if (value!=0) {
                    eq('userAddressId',value)
                }
            }
            if (params.userRegistrationFName!=null) {
                ilike("userRegistrationFName", "%${params.userRegistrationFName}%")
            }

            if (params.userRegistrationMName!=null) {
                ilike("userRegistrationMName", "%${params.userRegistrationMName}%")
            }
            if (params.userRegistrationLName!=null) {
                ilike("userRegistrationLName", "%${params.userRegistrationLName}%")
            }
            if (params.userRegistrationName!=null) {
                ilike("userRegistrationName", "%${params.userRegistrationName}%")
            }
        }
        [userRegistrations:userRegistrations,counter:userRegistrations.size()]
    }

    def topPage = {}
    def loginAdmin = {}
    def login = {}
    def about = {}
    def service = {}

    def authenticateWebPortal = {
        render(view:'/publicApplicationList')
    }

    def authenticateWebApplication = {
        def user = UserRegistration.findByUserRegistrationNameAndUserRegistrationPassword(params.userRegistrationName, params.userRegistrationPassword)

        if(user){
            session.user = user
            flash.message = "Hello ${user.userRegistrationfullName}!"
            // redirect(action:"login")
            render(view:'/index2')

        }else{
            flash.message = "Your user name or password is wrong!"
            redirect(action:"login")
            //redirect(action:"create")
        }
    }


    def authenticateWebAdministrator = {
        def user = UserRegistration.findByUserRegistrationNameAndUserRegistrationPassword(params.userRegistrationName, params.userRegistrationPassword)

        if(user){
            session.user = user
            flash.message = "Hello ${user.userRegistrationfullName}!"
            // redirect(action:"login")
            render(view:'/index2')

        }else{
            flash.message = "Your user name or password is wrong!"
            redirect(action:"loginAdmin")
            //redirect(action:"create")
        }
    }

    def logout = {
        flash.message = "Please Login"
        redirect(action:"login")
    }


    def show(Long id) {
        respond userRegistrationService.get(id)
    }

    def create() {
        respond new UserRegistration(params)
    }

    def save(UserRegistration userRegistration) {
        if (userRegistration == null) {
            notFound()
            return
        }

        try {
            userRegistrationService.save(userRegistration)
        } catch (ValidationException e) {
            respond userRegistration.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userRegistration.label', default: 'UserRegistration'), userRegistration.id])
                redirect userRegistration
            }
            '*' { respond userRegistration, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userRegistrationService.get(id)
    }

    def update(UserRegistration userRegistration) {
        if (userRegistration == null) {
            notFound()
            return
        }

        try {
            userRegistrationService.save(userRegistration)
        } catch (ValidationException e) {
            respond userRegistration.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userRegistration.label', default: 'UserRegistration'), userRegistration.id])
                redirect userRegistration
            }
            '*'{ respond userRegistration, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userRegistrationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userRegistration.label', default: 'UserRegistration'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userRegistration.label', default: 'UserRegistration'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
