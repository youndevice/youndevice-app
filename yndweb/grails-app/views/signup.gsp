<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to YouNDevice</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>

<div id="login-overlay" class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">Register to youndevice.com</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-xs-12">
                    <div class="well">
                        <g:form class="form-horizontal" controller="user" action="registerUser">
                            <fieldset>

                                <!-- Form Name -->
                                <legend>Register</legend>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="lastName">Last Name</label>
                                    <div class="col-md-6">
                                        <input id="lastName" name="lastName" type="text" placeholder="enter last name" class="form-control input-md">

                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="firstName">First Name</label>
                                    <div class="col-md-6">
                                        <input id="firstName" name="firstName" type="text" placeholder="enter first name" class="form-control input-md">

                                    </div>
                                </div>

                                <!-- Prepended text-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="mobileNumber">Mobile Number</label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                            <span class="input-group-addon">+91</span>
                                            <input id="mobileNumber" name="mobileNumber" class="form-control" placeholder="enter mobile number" type="text">
                                        </div>

                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="username">Email Id</label>
                                    <div class="col-md-6">
                                        <input id="username" name="username" type="text" placeholder="enter email Id" class="form-control input-md" required="">

                                    </div>
                                </div>

                                <!-- Password input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="password">Password</label>
                                    <div class="col-md-6">
                                        <input id="password" name="password" type="password" placeholder="enter password" class="form-control input-md" required="">

                                    </div>
                                </div>

                                <!-- Password input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="confirmPassword">Confirm Password</label>
                                    <div class="col-md-6">
                                        <input id="confirmPassword" name="confirmPassword" type="password" placeholder="enter password again" class="form-control input-md">

                                    </div>
                                </div>

                                <!-- Button (Double) -->
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <button id="registerUser" name="registerUser" class="btn btn-success">Register</button>
                                        <button id="cancel" name="cancel" class="btn btn-danger">Cancel</button>
                                    </div>
                                </div>

                            </fieldset>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
