<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Coming Soon Page</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <!--     Fonts     -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Grand+Hotel' rel='stylesheet' type='text/css'>
    <asset:stylesheet src="coming-soon.css"/>

</head>

<body>
<div class="main" style='background-image: url("${assetPath(src: 'restaurant.jpg')}")'>

    <!--    Change the image source '/images/default.jpg' with your favourite image.     -->

    <div class="cover black" data-color="black"></div>

    <!--   You can change the black color for the filter with those colors: blue, green, red, orange       -->

    <div class="container">
        <h1 class="logo cursive">
            Coming Soon
        </h1>
        <!--  H1 can have 2 designs: "logo" and "logo cursive"           -->

        <div class="content">
            <h4 class="motto">Find the best greetings and card on the web.</h4>
            <div class="subscribe">
                <h5 class="info-text">
                    Join the waiting list for the beta. We keep you posted.
                </h5>
                <div class="row">
                    <div class="col-md-4 col-md-offset-4 col-sm6-6 col-sm-offset-3 ">
                        <form class="form-inline" role="form">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="form-group">
                                        <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                        <input type="email" class="form-control transparent" placeholder="Your email here...">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-danger btn-fill">Notify Me</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>