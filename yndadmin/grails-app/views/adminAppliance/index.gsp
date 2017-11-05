<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'adminAppliance.label', default: 'Appliance')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="container">
            <g:link controller="adminAppliance" action="create" class="btn btn-primary">Add Appliance</g:link>

            <div class="row">
            <div class="col-md-12">
                <h4>Appliance List</h4>
                <div class="table-responsive">
                    <table id="mytable" class="table table-bordred table-striped">
                        <thead>
                        <tr>
                            <th>Appliance Id</th>
                            <th>Web Status</th>
                            <th>Actual Device Status</th>
                            <th>Category</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:if test="${applianceList}">
                            <g:each in="${applianceList}" var="appliance" status="i">
                                <tr>
                                    <td>${appliance.applianceId}</td>
                                    <td>${appliance.webStatus}</td>
                                    <td>${appliance.actualDeviceStatus}</td>
                                    <td>${appliance.category}</td>
                                    <td>
                                        <g:link controller="adminAppliance" action="edit" id="${appliance.id}"><span class="glyphicon glyphicon-pencil"></span>
                                        </g:link>
                                        <g:link controller="adminAppliance" action="delete" id="${appliance.id}"> <span class="glyphicon glyphicon-trash"></span>
                                        </g:link>
                                    </td>
                                </tr>
                            </g:each>
                        </g:if>
                        <g:else>
                            <tr>
                                <td colspan="5">
                                    No Record exist
                                </td>
                            </tr>
                        </g:else>
                        </tbody>
                    </table>

                    <div class="clearfix"></div>
                </div>

            </div>
        </div>
        </div>
    </body>
</html>