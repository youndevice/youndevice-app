<!doctype html>
<html>
<head>
    <meta name="layout" content="user"/>
    <title>List Appliance</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

</head>

<body>
<legend>Appliance List</legend>
<g:if test="${applianceList}">
    <g:each in="${applianceList}" var="appliance" status="i">
        <div class="well">
            <div class="media">
                <a class="pull-left" href="#">
                    <asset:image class="media-object" src="appliances/${appliance?.category?.value}.png"/>
                </a>

                <div class="media-body">
                    <h4 class="media-heading">
                        ${appliance?.applianceId}
                    </h4>

                    <p>${appliance?.userFriendlyName}</p>

                    <p>${appliance?.webStatus}</p>

                    <p>Up Time : 2h 30m</p>

                    <p>Device : ${appliance?.device?.deviceId}</p>

                    <p><g:link controller="userAppliance" action="toggleAppliance"
                               id="${appliance.id}">Switch ${appliance.webStatus.inverseValue}</g:link></p>

                    <div>
                        <g:link controller="userAppliance" action="editAppliance" class="btn btn-danger" id="${appliance.id}">Edit Appliance</g:link>

                        <g:link controller="userAppliance" action="deleteAppliance" class="btn btn-danger" id="${appliance.id}">Delete Appliance</g:link>

                    </div>
                </div>
            </div>
        </div>

        %{--<div class="row">--}%
            %{--<div class="col-md-4">${appliance.applianceId}</div>--}%

            %{--<div class="col-md-4">${appliance.userFriendlyName}</div>--}%

            %{--<div class="col-md-2">${appliance.webStatus}</div>--}%

            %{--<div class="col-md-2">--}%
                %{--<g:link controller="userAppliance" action="toggleAppliance"--}%
                        %{--id="${appliance.id}">Switch ${appliance.webStatus.inverseValue}</g:link>--}%
            %{--</div>--}%
        %{--</div>--}%
    </g:each>
</g:if>
<g:else>
    <div class="row">
        <div class="col-md-12 text-center">
            No Appliance Found
        </div>
    </div>
</g:else>
</body>
</html>
