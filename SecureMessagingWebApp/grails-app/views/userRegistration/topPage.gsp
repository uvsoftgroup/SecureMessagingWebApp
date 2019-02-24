<%@ page import="uvsoftgroup.securemessagingwebapp.UserRegistration" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="urbanviewwebapp Login" />
        <title><g:message code="urbanviewwebapp.login.label" args="[entityName]" /></title>
    </head>
    
    <body>
	
	<style>
	
	.container1{
	background: #C1C1C1;
	width: auto;
	height: 270px; 
	 display: flex;
    display: -webkit-
	background-repeat:no-repeat;
}
	.row{
    width: 100%;
    height: 270px;
    display: flex;
    display: -webkit-

}
.col-md-3{
    width: 49.5%;
    margin:1px;
    padding: 1px;
}

.col-md-4{
    width: 49.5%;
    margin:1px;
    padding: 1px;
}

.col-md-5{
width: 1%;
border: 5px solid #999999;
}



#cf {
  position:relative;
  height:400px;
  width:960px;
  margin:0 auto;
}

#cf img {
  position:absolute;
  left:0;
  -webkit-transition: opacity 1s ease-in-out;
  -moz-transition: opacity 1s ease-in-out;
  -o-transition: opacity 1s ease-in-out;
  transition: opacity 1s ease-in-out;
}


 @keyframes cf3FadeInOut {
  0% {
  opacity:1;
}
45% {
opacity:1;
}
55% {
opacity:0;
}
100% {
opacity:0;
}
}

#cf3 img.top {
animation-name: cf3FadeInOut;
animation-timing-function: ease-in-out;
animation-iteration-count: infinite;
animation-duration: 10s;
animation-direction: alternate;
}
.slider{
background: #C1C1C1;
 width:auto;
 height:420px; 
}
</style>
<div class="container1">
 <div class="row">
   <div div class="col-md-4">
   <fieldset>
  <legend> <h2><b> Web Adminitrator Login for New Web User Registartion </b></h2></legend><br>
   <g:link controller="userRegistration" action="loginAdmin"><img src="${resource(dir:"images", file: "web_adminitrator_button.png") }"/></g:link>
</fieldset>
</div>
</div>
</div>
 </body>
</html>