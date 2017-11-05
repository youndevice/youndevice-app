<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="YouNDevice"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>
<g:if test="${flash.success}">
    <div class="alert alert-success" style="display: block"  role="alert">${flash.success}</div>
</g:if>
<g:if test="${flash.error}">
    <div class="alert alert-danger" style="display: block" role="alert">${flash.error}</div>
</g:if>
 <g:layoutBody/>
    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>
    <asset:javascript src="application.js"/>
</body>
</html>
