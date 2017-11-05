<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'adminAppliance.label', default: 'Appliance')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="create-appliance" class="content scaffold-create" role="main">
            <g:form controller="adminAppliance" action="save" class="form-horizontal">
                <fieldset>
                    <!-- Form Name -->
                    <legend>Create Appliance</legend>

                    <g:render template="form"/>

                    <!-- Button (Double) -->
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-success" value="Save"/>
                            <input type="button" class="btn btn-danger" value="Cancel"/>
                        </div>
                    </div>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
