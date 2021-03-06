<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Search for classes">
    <meta name="author" content="Joe Caruso">
    <link rel="icon" href="https://www.champlain.edu/assets/prebuilt/img/favicon.png">

    <title>Search for Space</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- Custom styles for this template  -->
    <link href="css/navbar-fixed-top.css" rel="stylesheet">

    <link href="css/soft.css" rel="stylesheet">

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>

</head>

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><img id="logo" src="img/logo.png" title="LabFinder" style="width:130px;margin-top: -10%;"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="http://www.champlain.edu/"><img id="schoollogo" src="https://vtcolleges.org/images/color_logos/champlain_logo_color.png" title="Champlain" style="float: right;width:130px;overflow: visible;"></a></li>
                <li class="active"><a href="/search">Search</a></li>
                <li><a href="/about">About</a></li>
                <!-- <li><a href="#contact">Contact</a></li> -->
            </ul>
            <form action="/search" method="POST">
            <ul class="nav navbar-nav navbar-right">
                    <li>
                        <p><input name="dayFilter" type="text" id="datepicker" placeholder="xx/xx/xxxx" style="margin: 11px; width: 90px;"></p>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Times <span class="caret"></span></a>
                        <ul class="dropdown-menu" style="width: 173px;">
                            <#list timeoptions as time>
                                <li><label><input type="radio" name="timeFilter" value="${time}" autocomplete="off" />${time}</label></li>
                            </#list>
                        </ul>

                    </li>
                    <li class="dropdown" style="max-width: 280px;">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Software <span class="caret"></span></a>
                        <ul id="soft" class="dropdown-menu" style="width: 357px;">
                            <#list softwareoptions as software>
                                <li >
                                    <input  type="checkbox" id="${software}" name="softwareFilter[]" value="${software}" autocomplete="off"  title="${software}">
                                    <label for="${software}">${software}
                                    </label>
                                </li>
                            </#list>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hardware <span class="caret"></span></a>
                        <ul id="hard" class="dropdown-menu" style="width: 170px;">
                            <#list hardwareoptions as hardware>
                                <li>
                                    <input  type="checkbox" id="${hardware}" name="hardwareFilter[]" value="${hardware}" autocomplete="off" title="${hardware}">
                                    <label for="${hardware}">${hardware}
                                    </label>
                                </li>
                            </#list>
                        </ul>
                    </li>
                    <li>
                        <input type="submit" value="Search" class="button" >
                    </li>
            </ul>
            </form>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container marketing">

    <!-- Main component for displaying rooms -->
    <div class="main" >
        <h1>
            Results
        </h1>
        <h2>${error}</h2>
        <div class="panel-group">
            <div class="panel panel-default">
                <#list rooms as room>
                    ${room}
                </#list>
            </div>
        </div>
    </div>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script> -->
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug  -->
<script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
