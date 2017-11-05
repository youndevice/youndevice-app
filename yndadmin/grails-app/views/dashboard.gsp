<!DOCTYPE html>
<html lang="en">
<head>
    <title><g:message
            code="ynd.title.text"/></title>
    <meta name="layout" content="admin"/>
</head>

<body>
<div class="col-md-12">
    <div class="container-fluid parent-container">
        <div class="row">
            <h3 class="text-center dtmc-header">Dashboard</h3>

            <form class="admin-home-page">

                <div class="admin-details">
                    <div class="row">
                        <div class="col-sm-7 col-md-7">
                            <h3>Welcome ${user.username}</h3>

                            <div></div>
                            <ul>
                                <li>
                                    <strong>Your Role:</strong>  ${user?.authorities*.authority?.join(",")}
                                </li>
                                <li>
                                    <strong>Last Login:</strong> new Date()
                                </li>
                            </ul>
                        </div>

                        <div class="col-sm-5 col-md-5">
                            <div class="pull-right">
                                <img src="${assetPath(src: 'no-profile.gif')}"
                                     height="200"
                                     width="200">
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
