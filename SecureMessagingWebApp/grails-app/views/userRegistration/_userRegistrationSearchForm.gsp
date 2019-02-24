<table>
  <tr>
  <td><label for="userRegistrationId"><g:message code="userRegistrationId.label"/></label>
  <g:textField name="userRegistrationId" value="${userRegistrationInstance?.userRegistrationId}"/>
  </td> 
  
  <td><label for="userRegistrationFName"><g:message code="adFirstName.label"/></label>
   <g:textField name="userRegistrationFName" value="${userRegistrationInstance?.userRegistrationFName}"/>
</td>

  <td> <label for="userRegistrationMName"><g:message code="addressInfo.adMiddleName.label"/></label>
   <g:textField name="userRegistrationMName" value="${userRegistrationInstance?.userRegistrationMName}"/>
</td>
</tr>
<tr>
  <td> 
  <label for="userRegistrationLName"><g:message code="addressInfo.adLastName.label"/></label>
   <g:textField name="userRegistrationLName" value="${userRegistrationInstance?.userRegistrationLName}"/>
</td>
<td> 
  <label for="userRegistrationName"><g:message code="user.name.label"/></label>
   <g:textField name="userRegistrationName" value="${userRegistrationInstance?.userRegistrationName}"/>
</td>


  </tr>
</table>