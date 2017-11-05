<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'adminDevice.label', default: 'Device')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="edit-device" class="content scaffold-create" role="main">

        <g:form controller="adminDevice" action="save" class="form-horizontal">
            <fieldset>
                <!-- Form Name -->
                <legend>Edit Device</legend>
                <g:hiddenField name="id" value="${device?.id}"/>
                <g:render template="form" model="[device:device]"/>

                <!-- Button (Double) -->
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-success" value="Update"/>
                        <input type="button" class="btn btn-danger" value="Cancel"/>
                    </div>
                </div>
            </fieldset>
        </g:form>
    </body>
    </body>
</html>
