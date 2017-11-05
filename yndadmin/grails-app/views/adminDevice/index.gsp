<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'adminDevice.label', default: 'Device')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    <g:link controller="adminDevice" action="create" class="btn btn-primary">Add Device</g:link>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h4>Device List</h4>
                <div class="table-responsive">
                    <table id="mytable" class="table table-bordred table-striped">
                        <thead>
                        <tr>
                            <th>Device Id</th>
                            <th>Enabled</th>
                            <th>Appliance Type</th>
                            <th>Status</th>
                            <th>Device Type</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                            <g:if test="${deviceList}">
                            <g:each in="${deviceList}" var="device" status="i">
                                <tr>
                                    <td>${device.deviceId}</td>
                                    <td>${device.enabled}</td>
                                    <td>${device.applianceType}</td>
                                    <td>${device.status}</td>
                                    <td>${device.deviceType}</td>
                                    <td>
                                        <g:link controller="adminDevice" action="edit" id="${device.id}"><span class="glyphicon glyphicon-pencil"></span>
                                        </g:link>
                                        <g:link controller="adminDevice" action="delete" id="${device.id}"> <span class="glyphicon glyphicon-trash"></span>
                                        </g:link>
                                    </td>
                                </tr>
                            </g:each>
                        </g:if>
                            <g:else>
                            No Record exist
                        </g:else>
                        </tbody>
                    </table>

                    <div class="clearfix"></div>
                    %{--<ul class="pagination pull-right">--}%
                        %{--<li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>--}%
                        %{--<li class="active"><a href="#">1</a></li>--}%
                        %{--<li><a href="#">2</a></li>--}%
                        %{--<li><a href="#">3</a></li>--}%
                        %{--<li><a href="#">4</a></li>--}%
                        %{--<li><a href="#">5</a></li>--}%
                        %{--<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>--}%
                    %{--</ul>--}%

                </div>

            </div>
        </div>
    </div>
    </body>
</html>