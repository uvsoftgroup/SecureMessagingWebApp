<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
  <link rel="apple-touch-icon" href="${assetPath(src: 'diulogo.png')}">
  <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'diulogo.png')}">
    <asset:stylesheet src="application.css"/>
  <asset:javascript src="application.js"/>
  <g:layoutHead/>

 </head>
 <body>

 <div class="container">

 <div class="titlebis" align="center">
    <h2 style="
   left: 80;
   width: 100%;
   height: 100px;
   color: white;
   font: bold 24px/45px Helvetica, Sans-Serif;
   letter-spacing: -1px;
   background: rgb(0, 0, 0); /* fallback color */
   background: rgba(0, 0, 0, 0.6);
    " >Secure Message Sending and Receiving Web Application </h2>
   </div>


  <div class="nav">
                    <ul>
                        <b><li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li></b>

                    <b> <li><g:link controller="userRegistration" action="service"><g:message code="service.level"/></g:link></b></li>

                   <b><li><g:link controller="userRegistration" action="about"><g:message code="about.us.level"/></g:link></b></li>

                   <b><li><g:link controller="userRegistration" action="logout"><g:message code="logout.home.label"/></g:link></b></li>

                  </ul>
              </div>

  <g:layoutBody/>
  <div class="footer" role="contentinfo"> <hr> <h1 align="center" >&copy; UVSoftGroup$ 2018</h1></div>
  <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
 </div>

 </body>
</html>