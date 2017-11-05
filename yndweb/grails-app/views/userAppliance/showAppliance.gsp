<!doctype html>
<html>
<head>
    <meta name="layout" content="user"/>
    <title>Show Appliance</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

</head>

<body>
Appliance Id : ${appliance.applianceId}
<br/>
User Friendly Name : ${appliance.userFriendlyName}
<br/>
Category : ${appliance.category}
<br/>
<g:link controller="userAppliance" action="editAppliance" id="${appliance.id}">Edit Appliance</g:link>
<br/>
<g:link controller="userAppliance" action="deleteAppliance" id="${appliance.id}">Delete Appliance</g:link>
</body>
</html>
