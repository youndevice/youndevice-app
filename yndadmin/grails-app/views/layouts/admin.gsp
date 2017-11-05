<!doctype html>
<html lang="en" class="no-js">
<head>
    <title>
        You N Device
    </title>
    <g:render template="/layouts/header"/>
    <asset:stylesheet src="application-bundle.css"/>
    <asset:javascript src="application-bundle.js"/>
    <g:layoutHead/>
</head>

<body>
%{--Main Header Starts--}%
<div class="container">
    <sec:ifLoggedIn>
    %{--Any user is logged in--}%
    %{--For all roles Granted--}%
        <nav class="navbar navbar-fixed-top">
            <g:link class="navbar-brand" controller="admin" action="dashboard">
                <asset:image src="ynd_logo.png" class="header-logo"/>
            </g:link>

            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <div id="navbar" class="navbar-collapse collapse">
                    <ul style="padding-left: 10px" class="nav navbar-nav navbar-left padding-reduce">
                        <li><g:link controller="adminDevice" action="index">Device</g:link></li>
                        <li><g:link controller="adminAppliance" action="index">Appliance</g:link></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="active welcome-user logout"><g:link controller="myAccount"
                                                                       action="userAccount">
                            Welcome <u><b><ynd:loggedInUserFirstName/></b></u> |</g:link></li>
                        <li class="active logout"><g:link controller="logout" action="index"><u>Logout</u></g:link></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
    %{--</sec:ifAllGranted>--}%
        <div class="messageContainer">
            <g:if test="${message}">
                <div style="display:none" class="alert alert-danger alert-dismissible error-msg" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                            aria-hidden="true">×</span></button>

                    <div class="message-text-info">${message}</div>
                </div>
            </g:if>

            <g:if test="${flash.error}">
                <div style="display:none" class="alert alert-danger alert-dismissible error-msg" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                            aria-hidden="true">×</span></button>

                    <div class="message  message-text-error">${flash.error}
                        <% flash.error = null %></div>
                </div>
            </g:if>
            <g:if test="${flash.success}">
                <div style="display:none" class="message alert alert-success alert-dismissible success-msg" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                    <div class="message message-text-success">${flash.success}
                    <% flash.success = null %>
                    </div>
                </div>
            </g:if>
        </div>
    </sec:ifLoggedIn>
    <g:layoutBody/>
</div>
%{--Main Header Ends--}%

<div id="spinner" class="image spinner" style="display:none">
    <asset:image src="loading.gif"/>
</div>

<div id="commentPopupHtml" style="display:none">
    <form>

        <div class="row form-group">
            <b><p id="commentWarningMessage" style="margin-left: 3%;"></p></b>
            <label for="comment" class="col-sm-12">Comment<span class="required">*</span> :
            </label>

        </div>

        <div class="row form-group">
            <div class="col-sm-12">
                <textarea tabindex="1" class="form-control" id="comment" maxlength="500" required="true"
                          onKeyDown="limitText(this.form.comment, this.form.countdown, 500);"
                          oninput='jQuery(".modal.in").find(".btn-success:eq(0)").prop("disabled", jQuery.trim((jQuery(this).val())) == "");
                          limitText(this.form.comment, this.form.countdown, 500);'></textarea>
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-12 text-right">
                <input type="text" readonly class="countdown" name="countdown" size="3"
                       value="500"> <g:message code="chars.left"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>