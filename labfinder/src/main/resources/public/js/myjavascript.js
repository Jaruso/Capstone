/**
 * Created by Joe on 3/31/2016.
 */

    $(function() {
        $( "#datepicker" ).datepicker();
    });

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
