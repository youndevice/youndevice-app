<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="YouNDevice"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    %{--<asset:stylesheet src="application.css"/>--}%
    <style>
    .my-appliance-container {
        height: 500px;
    }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".dropdown").hover(
                function () {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true, true).slideDown("400");
                    $(this).toggleClass('open');
                },
                function () {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true, true).slideUp("400");
                    $(this).toggleClass('open');
                }
            );
        });
    </script>
    <g:layoutHead/>
</head>

<body>
<div class="container">
    <g:if test="${flash.success}">
        <div class="alert alert-success" style="display: block">${flash.success}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="alert alert-danger" style="display: block">${flash.error}</div>
    </g:if>
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <g:link class="navbar-brand" controller="user" action="home">
                            <asset:image src="ynd_logo_black.png" width="30"/>
                        </g:link>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <g:link controller="userDevice" action="listDevices">
                                    Device<span class="sr-only">(current)</span>
                                </g:link>
                            </li>
                            <li>
                                <g:link controller="userAppliance" action="listAppliances">Appliance</g:link>
                            </li>
                            %{--<li class="dropdown">--}%
                                %{--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"--}%
                                   %{--aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>--}%
                                %{--<ul class="dropdown-menu">--}%
                                    %{--<li><a href="#">Action</a></li>--}%
                                    %{--<li><a href="#">Another action</a></li>--}%
                                    %{--<li><a href="#">Something else here</a></li>--}%
                                    %{--<li role="separator" class="divider"></li>--}%
                                    %{--<li><a href="#">Separated link</a></li>--}%
                                    %{--<li role="separator" class="divider"></li>--}%
                                    %{--<li><a href="#">One more separated link</a></li>--}%
                                %{--</ul>--}%
                            %{--</li>--}%
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="false">
                                    <ynd:loggedInUser/>
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <g:link controller='logout'>Logout</g:link>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3 my-appliance-container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">My Appliances</h3>
                </div>

                <div class="panel-body">
                    <g:if test="${applianceList}">
                        <g:each in="${applianceList}" var="appliance">
                            <g:link controller="userAppliance" action="showAppliance"
                                    id="${appliance.id}">
                                <asset:image src="appliances/${appliance?.category?.img48}.png"/>
                            </g:link>
                        </g:each>
                    </g:if>
                    <g:else>
                        No Appliance
                    </g:else>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <g:layoutBody/>
        </div>

    </div>
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>
<asset:javascript src="application.js"/>
</body>
</html>
