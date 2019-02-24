package uvsoftgroup.securemessagingwebapp

import java.util.Date

class UserRole {
 Long userRoleId
 String userRoleName,userRoleType
 Date userRoleDate
 
 String toString () {"${id}" }
 static constraints = {
  userRoleId()
  userRoleName(inList: ["Admin Tasks", "Officer Tasks", "Web General User"])
  userRoleType(inList: ["Admin", "Officer", "General User"])
  userRoleDate()
    }
 static belongsTo =[userRegistrations:UserRegistration]

 static mapping = {

  columns {
   id generator: 'uvsoftgroup.securemessagingwebapp.utility.UserRoleIdGenerator'
  }
 }

}

