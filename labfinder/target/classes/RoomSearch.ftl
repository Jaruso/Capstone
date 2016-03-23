<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Search for Classes</title>
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" href="https://www.champlain.edu/assets/prebuilt/css/normalize.css?v=3" />

    <!-- <link rel="stylesheet" href="/assets/prebuilt/css/champ_common.css?v=3" /> -->

    <!-- <link rel="stylesheet" href="/assets/prebuilt/css/champ_print.css?v=3" media="print" /> -->
    <link rel="stylesheet" href="https://www.champlain.edu/assets/prebuilt/css/champ_print.css?v=3" media="print" />


    <link rel="stylesheet" href="https://www.champlain.edu/assets/prebuilt/css/champ_level.css?v=3" />

    <link rel="stylesheet" href="http://www.monkeyphysics.com/moo/datepicker/datepicker_vista/datepicker_vista.css?1242375714" />

    <style rel="stylesheet">
        #body {
        margin: 0;
        padding: 0;
        background: #f6ebc2;
        font-size: 100%;
        font-family: Arial, Helvetica, Verdana, sans-serif;
        }

        #main {
        font-size: 0.8125em; /* 13 */
        }
        .icons{
        float: right;
        }

        #homepage {

        }

        #header {
        height: 101px;
        }

        #header h1 {
        display: inline;
        float: left;
        margin-top: 15px;
        margin-right: 0;
        margin-bottom: 0;
        margin-left: 19px;
        }
        #content_area{
        background-color:#FFF;
        margin: 0 auto;
        }
        #content {
        width:1050px;
        }

        #footer {
        background: #F6EBC2 url(http://classlist.champlain.edu//common/imgs/main-lower.gif) top center no-repeat;
        padding-top: 12px;
        clear: both;
        color: #347513;
        text-align: center;
        font-size: 0.8462em; /* 11 */
        }

        #footer a {
        color: #347513;
        }
        #homepage{
        }
        .wrapper {
        width: auto;
        overflow: hidden;
        font-style: normal;
        text-align: left;
        }
        #left-container{
        float:left;
        width: 350px;
        }
        #school-logo{

        }
        #logo{
        width: 345px;
        }
        #filter {
        font-family: Gotham, "Helvetica Neue", Helvetica, Arial, sans-serif;
        background-color:#FFFFFF;
        font-style: normal;
        margin: 8px;
        border-radius: 5px 5px 5px 5px;
        -moz-border-radius: 5px 5px 5px 5px;
        -webkit-border-radius: 5px 5px 5px 5px;
        -webkit-box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        -moz-box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        }

        #results {
        font-family: Gotham, "Helvetica Neue", Helvetica, Arial, sans-serif;
        width:500px;
        background-color: #FFFFFF;
        overflow: hidden;
        padding: 10px;
        padding-top: 0px;
        margin: 8px;
        float: left;
        border-radius: 5px 5px 5px 5px;
        -moz-border-radius: 5px 5px 5px 5px;
        -webkit-border-radius: 5px 5px 5px 5px;
        -webkit-box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        -moz-box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        }
        .room{
        float:left;
        width:220px;
        margin-left:10px;
        margin-right:10px;
        }
        #key {
        font-family: Gotham, "Helvetica Neue", Helvetica, Arial, sans-serif;
        overflow: hidden;
        background-color: #FFFFFF;
        padding: 10px;
        padding-top: 0px;
        margin: 8px;
        float: left;
        border-radius: 5px 5px 5px 5px;
        -moz-border-radius: 5px 5px 5px 5px;
        -webkit-border-radius: 5px 5px 5px 5px;
        -webkit-box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        -moz-box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        box-shadow: 2px 2px 15px -5px rgba(0,0,0,0.5);
        }
        #title {

        }
        #time{
        width: 150px;
        float:left;
        margin-bottom: 10px;
        }
        #day{
        margin-left: 150 px;
        float:right;
        width: 100px;
        }
        #test1{
        width: 125px;
        float: left;
        }
        #test2{
        float: right;
        }
        #header {
        background-color: #231f20;
        color: #fff;
        height: 101px;
        -moz-box-shadow: 0 0 10px #000;
        -webkit-box-shadow: 0 0 10px #000;
        box-shadow: 0 0 10px #000;
        width: 100%;
        z-index: 4000;
        position: relative;
        }
        #inner-container.level, #inner-container.landing {
        background-color: #ECECEC;
        }
        #inner-container {
        height: 100%;
        width: 100%;
        margin: auto;
        max-width: 1600px;
        min-height: 500px;
        overflow: hidden;
        position: relative;
        }
        #content {
        overflow: auto;
        width: 1190px;
        margin: 50px auto;
        color: #57585a;
        position: relative;
        z-index: 2;
        min-height: 550px;
        }
        #error-message p {
        margin-left: 36%;
        color: #FF0000;
        font-size: large;
        }
        .top {

        }
        .tftextinput {

        }
        .tfbutton {

        }

    </style>


    <script type="text/javascript">
    $(function() {
        $( "#datepicker" ).datepicker();
    });
    </script>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

</head>


<body>

<div id="main">
    <div id="homepage">
        <div id="header" class="top">



        </div>
        <div id="inner-container" class="level">
            <div id="content">

                <div id="left-container">

                    <div id="school-logo">
                        <h1>
                            <a href="/"><img id="logo" src="https://vtcolleges.org/images/color_logos/champlain_logo_color.png" title="LabFinder"></a>
                        </h1>
                    </div>


                    <div id="filter" class="ui-content" data-role="main">

                        <form action="/" method="POST">

                            <input type="text" name="search" class="tftextinput" size="21" maxlength="120" >
                            <input type="submit" value="Search" class="tfbutton">

                            <p>Date: <input type="text" id="datepicker" class="hasDatepicker"></p>

                            <div id="error-message">
                                <p>${error}</p>
                            </div>


                            <div data-role="content">

                                <div data-role="collapsible">
                                    <h4>Time and Day</h4>
                                    <div id="timeday" class="wrapper">
                                        <div id="time">

                                            <p >
                                                <#list timeoptions as time>
                                                    <label><input type="checkbox" name="timeFilter[]" value="${time}" autocomplete="off" >${time}</label>
                                                </#list>
                                            </p>

                                        </div>


                                        <div id="day">
                                            <p >
                                                <#list dayoptions as day>
                                                    <label><input type="checkbox" name="dayFilter[]" value="${day}" autocomplete="off">${day}</label>
                                                </#list>
                                            </p>
                                        </div>
                                    </div>


                                </div>

                                <div data-role="collapsible">
                                    <h4>Software</h4>

                                    <#list softwareoptions as software>
                                        <label><input type="checkbox" name="softwareFilter[]" value="${software}" autocomplete="off" >${software}</label>
                                    </#list>


                                </div>



                                <div data-role="collapsible">
                                    <h4>Hardware</h4>

                                    <#list hardwareoptions as hardware>
                                        <label><input type="checkbox" name="hardwareFilter[]" value="${hardware}" autocomplete="off" >${hardware}</label>
                                    </#list>


                                </div>

                                <div data-role="collapsible">
                                    <h4>Extras</h4>

                                    <#list extraoptions as extra>
                                        <label><input type="checkbox" name="extraFilter[]" value="${extra}" autocomplete="off" >${extra}</label>
                                    </#list>

                                </div>

                            </div>

                        </form>

                    </div>
                </div>

                <div  id="results" class="result">
                        <h1> Results </h1>
                            <#list rooms as room>
                            <div data-role="collapsible" class="room">

                            ${room}

                            </div>
                            </#list>


                </div>

                <div class="keybox" id="key">
                    <h2> Key </h2>
                    <img src="http://www.iconarchive.com/download/i45768/tatice/operating-systems/Windows.ico" width="25" height="25"> Windows <br>
                    <img src="http://icons.iconarchive.com/icons/kyo-tux/phuzion/256/System-Mac-icon.png" width="25" height="25">  Macs <br>
                    <img src="http://icons.iconseeker.com/png/fullsize/battlestar-galactica-vol-3/lauras-whiteboard.png" width="25" height="25"> Whiteboard <br>
                    <img src="http://library.tulane.edu/sites/library.tulane.edu/files/styles/thumbnail/public/sites/default/files/img/icons/icon_28130.png?itok=If_YflCC" width="25" height="25"> Study Rooms <br>

                </div>


            </div>




        </div>



        <div id="footer">
             LabFinder produced for the benefit of Champlain College students and faculty. Created by Joe Caruso.
        </div>

    </div>

</div>
</body>
</html>