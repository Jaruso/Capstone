<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Search for Classes</title>
   <!-- <link rel="stylesheet" href="https://www.champlain.edu/assets/prebuilt/css/normalize.css?v=3" /> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link rel="stylesheet" href="/assets/prebuilt/css/champ_common.css?v=3" /> -->

    <!-- <link rel="stylesheet" href="/assets/prebuilt/css/champ_print.css?v=3" media="print" />
    <link rel="stylesheet" href="https://www.champlain.edu/assets/prebuilt/css/champ_print.css?v=3" media="print" />


    <link rel="stylesheet" href="https://www.champlain.edu/assets/prebuilt/css/champ_level.css?v=3" />
-->
  <!--

    <!--[if lt IE 9]>
 <!--   <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <![endif]-->



    <script type="text/javascript" src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>


    <link rel="stylesheet" href="css/lab.css"/>
    <link rel="stylesheet" href="css/lightbox.css"/>
    <link rel="stylesheet" href="css/jquery-ui.css"/>
    <link rel="stylesheet" href="css/jquery-ui.structure.css"/>
    <link rel="stylesheet" href="css/jquery-ui.theme.css"/>
    <link rel="stylesheet" href="css/screen.css"/>
 <!--   <script type="text/javascript" src="../js/myjavascript.js"></script> -->

<script type="text/javascript">

    $(function() {
        $( "#datepicker" ).datepicker();
    });
</script>

    <script type="text/javascript">
  $(function() {
    $( "#dialog" ).dialog({
      autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      }
    });

    $( "#opener" ).click(function() {
      $( "#dialog" ).dialog( "open" );
    });
  });
  </script>

    <script type="text/javascript">
    $(function() {
        var select = $( "#timeslider" );
        var slider = $( "<div id='slider'></div>" ).insertAfter( select ).slider({
            min: 1,
            max: 10,
            range: "min",
            value: select[ 0 ].selectedIndex + 1,
            slide: function( event, ui ) {
                select[ 0 ].selectedIndex = ui.value - 1;
            }
        });
        $( "#timeslider" ).change(function() {
            slider.slider( "value", this.selectedIndex + 1 );
        });
    });
</script>

</head>


<body>

<div id="main">
    <div id="homepage" >
        <div id="header" class="top">
            <div id="school-logo">
                <h1>
                    <a href="/"><img id="logo" src="https://vtcolleges.org/images/color_logos/champlain_logo_color.png" title="LabFinder"></a>
                </h1>
            </div>

        </div>
        <div id="inner-container" class="level">
            <div id="content" data-role="page"  >

                <div id="left-container">


                    <div id="filter" class="ui-content" data-role="main" data-theme="c" data-content-theme="c">

                        <form action="/" method="POST">

                            <div id="error-message">
                                <p>${error}</p>
                            </div>

                            <input type="submit" value="Search" class="tfbutton" >

                            <p><input name="dayFilter" type="text" id="datepicker" placeholder="xx-xx-xxxx"></p>

                            <select name="timeFilter" id="timeslider" >
                                <option>Before 8:00AM</option>
                                <option>8:00AM - 9:15AM</option>
                                <option>9:30AM - 10:45AM</option>
                                <option>11:00AM - 12:15PM</option>
                                <option>12:30PM - 1:45PM</option>
                                <option>2:00PM - 3:15PM</option>
                                <option>3:30PM - 4:45PM</option>
                                <option>5:00PM - 6:15PM</option>
                                <option>5:30PM - 8:15PM</option>
                                <option>After 8:15PM</option>
                            </select>

                            <div data-role="content" data-theme="c" data-content-theme="c">

                                <div data-role="collapsible" data-theme="c" data-content-theme="c">
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