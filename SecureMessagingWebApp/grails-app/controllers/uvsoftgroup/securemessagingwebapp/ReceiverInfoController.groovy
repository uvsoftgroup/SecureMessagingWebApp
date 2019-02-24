package uvsoftgroup.securemessagingwebapp

import grails.validation.ValidationException
import uvsoftgroup.securemessagingwebapp.ReceiverInfo
import uvsoftgroup.securemessagingwebapp.SenderInfo
import uvsoftgroup.securemessagingwebapp.SenderInfoService

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

import static org.springframework.http.HttpStatus.*

class ReceiverInfoController {

    SenderInfoService senderInfoService
    ReceiverInfoService receiverInfoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond receiverInfoService.list(params), model:[receiverInfoCount: receiverInfoService.count()]
    }

    def show(Long id) {
        respond receiverInfoService.get(id)
    }

    def create() {
        respond new ReceiverInfo(params)
    }

    def save(ReceiverInfo receiverInfo) {
        if (receiverInfo == null) {
            notFound()
            return
        }

        try {
            // call SenderInfo with sender message id, SecretKey and encrypted message
            addReceiverInfoGeneration(receiverInfo)
            // save ReceiverInfo with received sender message id, SecretKey and encrypted message
            receiverInfoService.save(receiverInfo)
        } catch (ValidationException e) {
            respond receiverInfo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'receiverInfo.label', default: 'ReceiverInfo'), receiverInfo.id])
                redirect receiverInfo
            }
            '*' { respond receiverInfo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond receiverInfoService.get(id)
    }

    def update(ReceiverInfo receiverInfo) {
        if (receiverInfo == null) {
            notFound()
            return
        }

        try {
            receiverInfoService.save(receiverInfo)
        } catch (ValidationException e) {
            respond receiverInfo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'receiverInfo.label', default: 'ReceiverInfo'), receiverInfo.id])
                redirect receiverInfo
            }
            '*'{ respond receiverInfo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        receiverInfoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'receiverInfo.label', default: 'ReceiverInfo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'receiverInfo.label', default: 'ReceiverInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    /**
     * to get SenderInfo by calling ReceiverInfoService's get methods
     * @param id
     * @return
     */
    def getSenderInfo(Long id) {
        SenderInfo senderInfo=senderInfoService.get(id)
        senderInfo
    }

    /**
     * call SenderInfo with sender message id, SecretKey and encrypted message
     * @param receiverInfo
     * @return
     */
    def addReceiverInfoGeneration(ReceiverInfo receiverInfo) {
        // call senderInfo with sender message id
        SenderInfo receiverInfoFromSender=getSenderInfo(receiverInfo.getAesEnId())
        try {
            /* Checks with sender message id, SecretKey and encrypted message
               If all secrete information are ok from the input of receiver side
             */
            if(receiverInfo.getAesEnId().equals(receiverInfoFromSender.id)
                    && receiverInfo.getAesDeSoBase64String().equalsIgnoreCase(receiverInfoFromSender.aesEnBase64String)
                    && receiverInfo.getAesDeSecretKey().equalsIgnoreCase(receiverInfoFromSender.aesEnSecretKey)) {

                Cipher cipher = Cipher.getInstance(receiverInfo.getAesDeSelectionMode()!=null? receiverInfo.getAesDeSelectionMode():"AES/CBC/PKCS5PADDING")
                IvParameterSpec iv = new IvParameterSpec(receiverInfo.getAesDeIvInitVector()!=null? receiverInfo.getAesDeIvInitVector().getBytes("UTF-8"):"7a11111111111118".getBytes("UTF-8"))
                SecretKeySpec skeySpec= new SecretKeySpec(receiverInfo.getAesDeSecretKey().getBytes("UTF-8"), "AES")

                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
                // decode the encrypted message from the sender
                byte[] original = cipher.doFinal(Base64.getDecoder().decode(receiverInfo.getAesDeSoBase64String()))
                String decryptedStringValue=new String(original)

                /* set into the ReceiverInfo into the receiverinfo database table
                 and get the sender plain text message and then read the message(s) from the ReceiverInfo list
                 */
                receiverInfo.setAesDeDeBase64String(decryptedStringValue)
                receiverInfo.setAesDeSelectionMode(receiverInfoFromSender.aesEnSelectionMode)
                receiverInfo.setAesDeIvInitVector(receiverInfoFromSender.aesEnIvInitVector)
                receiverInfo.setAesDeSecretKey(receiverInfo.getAesDeSecretKey())
            }

            else {
                // set into the ReceiverInfo into the receiverinfo database table
                receiverInfo.setAesDeSelectionMode(receiverInfo.getAesDeSelectionMode()!=null?receiverInfo.getAesDeSelectionMode():"AES/CBC/PKCS5PADDING");
                if(receiverInfo.getAesDeIvInitVector()!=null && receiverInfo.getAesDeIvInitVector().length()==16){
                    receiverInfo.setAesDeIvInitVector(receiverInfo.getAesDeIvInitVector())
                }
                else{
                    receiverInfo.setAesDeIvInitVector("7a11111111111118")
                }
                if(receiverInfo.getAesDeSecretKey()!=null && receiverInfo.getAesDeSecretKey().length()==16){
                    receiverInfo.setAesDeSecretKey(receiverInfo.getAesDeSecretKey())
                }
                else{
                    receiverInfo.setAesDeSecretKey(receiverInfo.getAesDeSecretKey())
                }
                // get the sender text message and set into the receiver
                receiverInfo.setAesDeDeBase64String(receiverInfo.getAesDeSoBase64String())
            }

        } catch (Exception ex) {
            ex.printStackTrace()
        }
        return receiverInfo
    }
}
