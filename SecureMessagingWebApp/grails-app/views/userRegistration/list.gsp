
<%@ page import="uvsoftgroup.securemessagingwebapp.UserRegistration" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'UserRegistration.label', default: 'UserRegistration')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userRegistration" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				
				<li><g:link class="create" action="create"><g:message code="user.create.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		
		<g:if test="${flash.message}">
<div class="message" role="status">${flash.message}</div>
</g:if>

<fieldset class="form">
<g:form action="list" method="GET">
<g:render template="userRegistrationSearchForm"></g:render> 
<g:submitButton name="list" value="Search"/>
</g:form>
</fieldset>
    <div id="list-userRegistration" class="content scaffold-list" role="main">
   <h1><g:message code="userRegistration.list.label"/>: ${userRegistrationInstanceCount}</h1>
   <g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
   </g:if>
	
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="userRegistrationId" title="${message(code: 'userRegistrationId.label')}" />
						<g:sortableColumn property="userRegistrationFName" title="${message(code: 'addressInfo.adFirstName.label')}" />
						<g:sortableColumn property="userRegistrationMName" title="${message(code: 'addressInfo.adMiddleName.label')}" />
						<g:sortableColumn property="userRegistrationLName" title="${message(code: 'addressInfo.adLastName.label')}" />
						
						<g:sortableColumn property="userRegistrationName" title="${message(code: 'user.name.label')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userRegistrations}" status="i" var="userRegistrationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userRegistrationInstance.id}">${fieldValue(bean: userRegistrationInstance, field: "userRegistrationId")}</g:link></td>
						<td>${fieldValue(bean: userRegistrationInstance, field: "userRegistrationFName")}</td>
						<td>${fieldValue(bean: userRegistrationInstance, field: "userRegistrationMName")}</td>
						<td>${fieldValue(bean: userRegistrationInstance, field: "userRegistrationLName")}</td>
						<td>${fieldValue(bean: userRegistrationInstance, field: "userRegistrationName")}</td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userRegistrationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
