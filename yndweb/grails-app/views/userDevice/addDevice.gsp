<!doctype html>
<html>
<head>
    <meta name="layout" content="user"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

</head>

<body>
<g:form controller="userDevice" action="saveDevice">
    <fieldset>
        <!-- Form Name -->
        <legend>Add Device</legend>

        <!-- Select Basic -->
        <div class="row form-group">
            <label class="col-md-4 control-label" for="deviceId">Device Id</label>

            <div class="col-md-4">
                <g:textField class="form-control" name="deviceId" value=""/>
            </div>
        </div>

        <div class="row form-group">
            <label class="col-md-4 control-label" for="userFriendlyName">User Friendly Name</label>

            <div class="col-md-4">
                <g:textField class="form-control" name="userFriendlyName" value=""/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <g:submitButton name="Add Device" class="btn btn-danger"/>
            </div>
        </div>
    </fieldset>
</g:form>
</body>
</html>
