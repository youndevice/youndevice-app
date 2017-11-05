<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>
<div id="login-overlay" class="modal-dialog">
    <g:if test="${isAuthenticated}">
        You are successfully registered with YouNDevice. Click below icon to go on home page.
        <div class="text-center">
            <g:link class="btn btn-danger" controller="user" action="home">Home</g:link>
        </div>
    </g:if>
    <g:else>
        ${message}
    </g:else>
</div>

</body>
</html>
