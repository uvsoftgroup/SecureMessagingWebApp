<%@ page import="uvsoftgroup.securemessagingwebapp.UserRegistration" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="urbanviewwebapp Login" />
        <title><g:message code="urbanviewwebapp.login.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-endUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        
        <div id="create-endUser" class="content scaffold-create" role="main">
            <h1><g:message code="urbanviewwebapp.login.label" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userRegistrationInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${userRegistrationInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="authenticateWebApplication" >
                <fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: userRegistrationInstance, field: 'userRegistrationName', 'error')} ">
                        <label for="userRegistrationName">
                            <g:message code="userRegistrationInstance.userRegistrationName.label"/>
                           
                        </label>
                        <g:textField name="userRegistrationName" value="${userRegistrationInstance?.userRegistrationName}"/>
                    </div>
                   
                    <div class="fieldcontain ${hasErrors(bean: userRegistrationInstance, field: 'userRegistrationPassword', 'error')} ">
                        <label for="userRegistrationPassword">
                            <g:message code="userRegistrationInstance.userRegistrationPassword.label" />
                        </label>
                        <g:field type="password" name="userRegistrationPassword" value="${userRegistrationInstance?.userRegistrationPassword}"/>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="login" class="save" value="Login" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>