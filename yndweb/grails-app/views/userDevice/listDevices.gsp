<!doctype html>
<html>
<head>
    <meta name="layout" content="user"/>
    <title>Welcome to YouNDevice</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

</head>

<body>
<div class="pull-right">
    <g:link class="btn btn-danger" controller="userDevice" action="addDevice"> Add Device</g:link>
</div>
<legend>Device List</legend>
<g:if test="${deviceList}">
    <g:each in="${deviceList}" var="device" status="i">
        <div class="well">
            <div class="media">
                <a class="pull-left" href="#">
                    <asset:image class="media-object" src="device.png" width="128"/>
                </a>
                <div class="media-body">
                    <h4 class="media-heading">
                        <g:link controller="userDevice" action="showDevice" id="${device.id}">
                            ${device.deviceId}
                        </g:link>
                    </h4>
                    <p>${device.userFriendlyName}</p>
                    <p>Up Time : 2h 30m</p>
                </div>
            </div>
        </div>
    </g:each>
</g:if>
<g:else>
    <div class="row">
        <div class="col-md-12 text-center">
            No Device Found
        </div>
    </div>
</g:else>
</body>
</html>
