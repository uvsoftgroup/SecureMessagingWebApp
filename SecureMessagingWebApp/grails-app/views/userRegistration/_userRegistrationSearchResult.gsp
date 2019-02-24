<h1> Search Result</h1>
<table border="1">
<thead>
<tr>
 <td><g:message code="userName.label"/></td>
 <td><g:message code="userFirstName.label"/></td>       
 <td><g:message code="userMiddleName.label"/></td>    
 <td><g:message code="userLastName.label"/></td>       
 <td><g:message code="userFullName.label"/></td>   
 <td><g:message code="userCreateDate.label"/></td>      
 <td><g:message code="userLastModifiedDate.label"/></td>                                                      
</tr>
 </thead>
 
<g:each in="${listTable}" var="nlistTable">
<tr>
<td>${nlistTable.userName}</td>
<td>${nlistTable.userFirstName}</td>
<td> ${nlistTable.userMiddleName} </td> 
<td>${nlistTable.userLastName}</td>
<td> ${nlistTable.userFullName} </td> 
<td> ${nlistTable.userCreateDate} </td> 
<td> ${nlistTable.userLastModifiedDate} </td> 

  </tr>
</g:each>

</table>




