<!DOCTYPE html>
<html lang="en">
<head>
    <title><g:message
            code="dtmc.title.text"/></title>
    <meta name="layout" content="main"/>
</head>

<body>

<div class="container-fluid parent-container">
    <div class="row">
        <h3 class="text-center dtmc-header"><g:message code="dashboard.heading.text"/></h3>

        <form class="admin-home-page">
            <ul class="application-services">
                <dtmc:ifAnyGranted permissionGroup="${MenuGroup.HOTEL_MANAGER}">
                    <li class="image-block">
                        <g:link controller="dthiDashboard" action="index">
                            <asset:image src="hotel.png" class="header-logo"/>
                            <p><g:message code="dtmc.hotel.manager"/></p>
                        </g:link>
                    </li>
                </dtmc:ifAnyGranted>
                <dtmc:ifAnyGranted permissionGroup="${MenuGroup.EXPERIENCE_MANAGER}">
                    <li class="image-block">
                        <g:link controller="dteiDashboard" action="index">
                            <asset:image src="experience.png" class="header-logo"/>
                            <p><g:message code="dtmc.experience.manager"/></p>
                        </g:link>
                    </li>
                </dtmc:ifAnyGranted>
                <dtmc:ifAnyGranted permissionGroup="${MenuGroup.COMMON_MANAGER}">
                    <li class="image-block">
                        <g:link controller="dtciDashboard" action="index">
                            <asset:image src="common-manager-copy.png" class="header-logo"/>
                            <p><g:message code="dtmc.common.manager"/></p>
                        </g:link>
                    </li>
                </dtmc:ifAnyGranted>
                <dtmc:ifAnyGranted permissionGroup="${MenuGroup.INFORMATION_SYSTEM}">
                    <li class="image-block">
                        <a>
                            <asset:image src="info.png" class="header-logo"/>
                            <p><g:message code="dtmc.information.system"/></p>
                        </a>
                    </li>
                </dtmc:ifAnyGranted>
            </ul>

            <div class="admin-details">
                <div class="row">
                    <div class="col-sm-7 col-md-7">
                        <h3>Welcome ${user.firstName}</h3>

                        <div></div>
                        <ul>
                            <li>
                                <strong>Your Role:</strong>  ${user?.actualAuthorities*.displayName?.join(",")}
                            </li>
                            <li>
                                <strong>Last Login:</strong> ${user?.lastLoginTime ?: ''}
                            </li>
                            <li>
                                <strong>Last Password Reset Date:</strong> ${user?.passwordChangedDate ?: ''}
                            </li>
                        </ul>
                    </div>

                    <div class="col-sm-5 col-md-5">
                        <div class="pull-right">
                            <g:if test="${user?.image}">
                                <dtmc:cloudinaryImageTag public_id="${user?.image}" height="200"
                                                         width="200"/>
                            </g:if>
                            <g:else>
                                <img src="${assetPath(src: 'no-profile.gif')}"
                                     height="200"
                                     width="200">
                            </g:else>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <h3>Internal Messages</h3>
                        <g:if test="${messages}">
                            <marquee direction="up" onmouseover="this.stop();" onmouseout="this.start();"
                                     scrolldelay="300" height="98">
                                <g:each in="${messages}" var="message">
                                    <div class="text-muted">
                                        <p>${message[0]}</p>

                                        <p>${message[1]}, ${message[2]?.format("dd MMM yyyy")}</p>
                                    </div>
                                </g:each>
                            </marquee>
                        </g:if>
                        <g:else>
                            <p class="text-muted">No message to display</p>
                        </g:else>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    //Login screen : Disable browser back button once logged out.
    $(document).ready(function () {
        history.pushState(null, null, "/");
        window.addEventListener('popstate', function () {
            history.pushState(null, null, "/");
        });
    });
</script>
</body>
</html>
