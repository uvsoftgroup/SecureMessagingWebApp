<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Register User Info</title>
</head>
<body>

<g:link controller="databaseInfo" action=""><g:message code="address.search.label"/></g:link>

<g:form action="loadAllRegisterUser">
<g:render template="userRegistrationForm"></g:render>
<g:submitButton name="loadAllRegisterUser" value="Search"/>

<g:render template="userRegistrationSearchResult"></g:render>
</g:form>

</body>
</html>