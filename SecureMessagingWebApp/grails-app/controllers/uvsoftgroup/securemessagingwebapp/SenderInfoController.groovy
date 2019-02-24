package uvsoftgroup.securemessagingwebapp

import grails.validation.ValidationException
import uvsoftgroup.securemessagingwebapp.SenderInfo

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

import static org.springframework.http.HttpStatus.*

class SenderInfoController {

    SenderInfoService senderInfoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond senderInfoService.list(params), model:[senderInfoCount: senderInfoService.count()]
    }

    def show(Long id) {
        respond senderInfoService.get(id)
    }

    def create() {
        respond new SenderInfo(params)
    }

    def save(SenderInfo senderInfo) {
        if (senderInfo == null) {
            notFound()
            return
        }

        try {
            addSenderInfoGeneration(senderInfo)
            senderInfoService.save(senderInfo)
        } catch (ValidationException e) {
            respond senderInfo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'senderInfo.label', default: 'SenderInfo'), senderInfo.id])
                redirect senderInfo
            }
            '*' { respond senderInfo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond senderInfoService.get(id)
    }

    def update(SenderInfo senderInfo) {
        if (senderInfo == null) {
            notFound()
            return
        }

        try {

            senderInfoService.save(senderInfo)
        } catch (ValidationException e) {
            respond senderInfo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'senderInfo.label', default: 'SenderInfo'), senderInfo.id])
                redirect senderInfo
            }
            '*'{ respond senderInfo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        senderInfoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'senderInfo.label', default: 'SenderInfo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'senderInfo.label', default: 'SenderInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }



    def addSenderInfoGeneration(SenderInfo senderInfo) {

        try {
            Cipher cipher = Cipher.getInstance(senderInfo.getAesEnSelectionMode()!=null? senderInfo.getAesEnSelectionMode():"AES/CBC/PKCS5PADDING")
            IvParameterSpec iv = new IvParameterSpec(senderInfo.getAesEnIvInitVector()!=null? senderInfo.getAesEnIvInitVector().getBytes("UTF-8"):"7a11111111111118".getBytes("UTF-8"))
            SecretKeySpec secretKeySpec = new SecretKeySpec(senderInfo.getAesEnSecretKey().getBytes("UTF-8"), "AES")

            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec, iv)

            byte[] encrypted = cipher.doFinal(senderInfo.getAesEnSourceString().getBytes())
            String   encryptedStringValue = new String(Base64.getEncoder().encode(encrypted))
            println(encryptedStringValue)

            senderInfo.setAesEnSelectionMode(senderInfo.getAesEnSelectionMode()!=null? senderInfo.getAesEnSelectionMode():"AES/CBC/PKCS5PADDING")
            senderInfo.setAesEnIvInitVector(senderInfo.getAesEnIvInitVector()!=null? senderInfo.getAesEnIvInitVector():"7a11111111111118")
            senderInfo.setAesEnSecretKey(senderInfo.getAesEnSecretKey())
            senderInfo.setAesEnSourceString(senderInfo.getAesEnSourceString())
            senderInfo.setAesEnBase64String(encryptedStringValue)


        } catch (Exception ex) {
            ex.printStackTrace()
        }
        return senderInfo
    }

}
