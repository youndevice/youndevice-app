<!doctype html>
<html>
<head>
    <meta name="layout" content="user"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

</head>

<body>
<asset:image src="main-content.png"/>
%{--<g:link controller="userDevice" action="addDevice">Add Device</g:link>--}%
%{--<br/>--}%

%{--<div>--}%
    %{--<g:link controller="userDevice" action="listDevices">Device List</g:link>--}%
%{--</div>--}%
%{--<g:each in="${deviceList}" var="device">--}%
%{--<div class="row">--}%
    %{--<div class="col-md-12">--}%
        %{--<g:link controller="userDevice" action="showDevice" id="${device.id}">${device.deviceId}</g:link>--}%
    %{--</div>--}%
%{--</div>--}%
%{--</g:each>--}%

%{--<div>--}%
    %{--<g:link controller="userAppliance" action="listAppliances">Appliance List</g:link>--}%
%{--</div>--}%
%{--<g:each in="${applianceList}" var="appliance">--}%
    %{--<div class="row">--}%
        %{--<div class="col-md-12">--}%
            %{--<g:link controller="userAppliance" action="showAppliance" id="${appliance.id}">${appliance.applianceId}</g:link>--}%
        %{--</div>--}%
    %{--</div>--}%
%{--</g:each>--}%
</body>
</html>
