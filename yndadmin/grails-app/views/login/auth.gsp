<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <g:render template="/layouts/header"/>
    <asset:stylesheet src="login-bundle.css"/>
    <asset:javascript src="login-bundle.js"/>
</head>

<body>
<div class="login">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <div class="login-panel">
                    <div class="text-center logo">
                        <asset:image src="do-trips-logo.png"/>
                    </div>

                    <div id="status"></div>

                    <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="cssform"
                          autocomplete="off">
                        <g:if test='${flash.message}'>
                            <div class='login-error-message'>${flash.message}</div>
                        </g:if>
                        <div class="message"></div>
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Username"
                                       name="${usernameParameter ?: 'username'}" required="required"
                                       id="username" type="email" autofocus="">
                            </div>

                            <div class="form-group">
                                <input type="password" class="form-control" placeholder="Password" required="required"
                                       name="${passwordParameter ?: 'password'}" id="password">
                            </div>

                            <div class="form-group">

                                <input type="submit" id="submit" value="Log In"
                                       class="btn btn-lg btn-block login-button"/>
                            </div>

                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        if (!window.navigator.onLine) {
            $("#status").addClass('login-error-message').text("Oops, some error encountered. Please try after some time or contact administrator.");
        }
    });
    $(document).ready(function () {
        disablePageBackButton();
        loginValidation();
    });
    function disablePageBackButton() {
        history.pushState(null, null, "auth");
        window.addEventListener('popstate', function () {
            history.pushState(null, null, "auth");
        });
    }
    function loginValidation() {
        jQuery('.cssform').validate({
            rules: {
                username: {
                    required: true,
                    email: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: "<div class='login-error-message'>Username cannot be left blank. Please enter a valid username.</div>",
                    email: "<div class='login-error-message'>Please enter a valid username. Please note that username is the email ID" +
                    " used to create account.</div>"
                },
                password: {
                    required: "<div class='login-error-message'>Password cannot be left blank. Please enter a valid password</div>"
                }
            }
        });
    }
</script>
</body>
</html>
