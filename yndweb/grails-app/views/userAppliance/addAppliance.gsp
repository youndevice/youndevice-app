<!doctype html>
<html>
<head>
    <meta name="layout" content="user"/>
    <title>Add Appliance</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

</head>

<body>
<g:form controller="userAppliance" action="saveAppliance">
    <g:hiddenField name="pinNumber" value="${appliance.pinNumber}"/>
    <g:hiddenField name="deviceId" value="${deviceId}"/>
    <g:hiddenField name="applianceInstanceId" value="${appliance.id}"/>
    <fieldset>

        <!-- Form Name -->
        <legend>Add Appliance</legend>

        <!-- Select Basic -->
        <div class="row form-group">
            <label class="col-md-4 control-label" for="category">Appliance Category</label>

            <div class="col-md-4">
                <g:select class="form-control" from="${com.youndevice.core.enums.ApplianceCategory.values()}"
                          name="category" value="${appliance.category}"/>
            </div>
        </div>

        <div class="row form-group">
            <label class="col-md-4 control-label" for="category">User Friendly Name</label>

            <div class="col-md-4">
                <g:textField class="form-control" name="userFriendlyName" value="${appliance.userFriendlyName}"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-4 col-md-offset-4">
                <g:submitButton name="Add Appliance" class="btn btn-danger"/>
            </div>
        </div>
    </fieldset>
</g:form>
</body>
</html>
