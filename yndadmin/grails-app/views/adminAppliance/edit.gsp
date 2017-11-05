<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'adminAppliance.label', default: 'Appliance')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="edit-appliance" class="content scaffold-edit" role="main">
            <g:form controller="adminAppliance" action="update" class="form-horizontal">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Edit Appliance</legend>
                    <g:hiddenField name="id" value="${appliance?.id}"/>
                    <g:render template="form" model="[appliance:appliance]"/>

                    <!-- Button (Double) -->
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-success" value="Update"/>
                            <input type="button" class="btn btn-danger" value="Cancel"/>
                        </div>
                    </div>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
