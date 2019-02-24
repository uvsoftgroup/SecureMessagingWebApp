package uvsoftgroup.securemessagingwebapp

import java.util.Date

class UserRegistration {
 Long userRegistrationId,userAddressId
 String userRegistrationFName,userRegistrationMName,userRegistrationLName,userRegistrationName,userRegistrationPassword
 String userRegistrationfullName
 Date userRegistrationCrDate,userRegistrationLDate
 
 String toString() { "${id}"}
 
 static hasMany =[roles:UserRole,addresses:AddressInfo,senders:SenderInfo,receivers:ReceiverInfo]

 static constraints = {
  userRegistrationId()
  userAddressId()
  userRegistrationFName()
  userRegistrationMName()
  userRegistrationLName()
  userRegistrationfullName()
  userRegistrationCrDate()
  userRegistrationLDate()
  userRegistrationName(unique: true)
  userRegistrationPassword(password: true)
  roles()
  addresses()
  senders()
  receivers()
 }
 static mapping = {
  columns {
   id generator: 'uvsoftgroup.securemessagingwebapp.utility.UserRegistrationIdGenerator'
  }
 }

}
