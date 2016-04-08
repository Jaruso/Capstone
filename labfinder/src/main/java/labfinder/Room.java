package labfinder;

import org.bson.Document;


/**
 * Created by Joe on 1/10/2016.
 */
public class Room {

    String name;
    Object num;
    String building;
    String room;
    Integer numPC;
    Integer numMAC;
    String Image;
    Integer TVs;
    Object projector;
    Object printer;
    Object whiteboard;
    Object soundeq;
    Object dualMon;
    Object studyRoom;
    Object scanner;
    Object photoeq;
    Object description;
    Object png;


    public Room(Document newD) {

        name = newD.getString("Name");
        room = newD.getString("Room");
        num = newD.get("Number", Object.class);
        building = newD.getString("Building");
        numPC = newD.getInteger("NumPCs");
        if(numPC==null)
            numPC=0;
        numMAC = newD.getInteger("NumMacs");
        if(numMAC==null)
            numMAC=0;
        Image = newD.getString("Image");
        TVs = newD.getInteger("TVs");
        projector = newD.get("Projector", Object.class);
        printer = newD.get("Printer", Object.class);
        whiteboard = newD.get("Whiteboard", Object.class);
        soundeq = newD.get("Sound_equipment", Object.class);
        dualMon = newD.get("Dual_monitors", Object.class);
        studyRoom = newD.get("Study_rooms", Object.class);
        scanner = newD.get("Scanner", Object.class);
        photoeq = newD.get("Photo_equipment", Object.class);
        description = newD.get("Description", Object.class);
        png = newD.get("Png", Object.class);

    }


    public String toString() // toHtml, for freeMarker
    {
//        <div class="panel panel-default">
//          <div class="panel-heading">
//          <h4 class="panel-title">
//          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Collapsible Group 1</a>
//          </h4>
//        </div>
//        <div id="collapse1" class="panel-collapse collapse in">
//          <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
//              sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
//              quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>
//          </div>
//        </div>

        String str;

        str = "   <div class=\"panel-heading\">\n";
        str += "   <h4 class=\"panel-title\">\n";
        str += "<a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#" + building + num + "\" style=\"font-size: x-large;\">" + name;
        str += " <div class=\"icons\">";
        if(numPC > 1){
            str += " <img src=\"http://www.iconarchive.com/download/i45768/tatice/operating-systems/Windows.ico\" width=\"25\" height=\"25\">";
        }
        if(numMAC > 0){
            str += " <img src=\"http://icons.iconarchive.com/icons/kyo-tux/phuzion/256/System-Mac-icon.png\" width=\"25\" height=\"25\">";
        }
        if(whiteboard!=null){
            str += " <img src=\"http://icons.iconseeker.com/png/fullsize/battlestar-galactica-vol-3/lauras-whiteboard.png\" width=\"25\" height=\"25\">";
        }
        if(studyRoom!=null){
            str += " <img src=\"http://library.tulane.edu/sites/library.tulane.edu/files/styles/thumbnail/public/sites/default/files/img/icons/icon_28130.png?itok=If_YflCC\" width=\"25\" height=\"25\">";
        }
        str += "</div>";
        str += "</a>\n";
        str += "</h4>\n";
        str += "</div>\n";
        str += "<div id=\"" + building + num + "\" class=\"panel-collapse collapse\">\n";
        str += "<div class=\"panel-body\">";
        str += toFullString();
        str += "</div>\n   </div>\n";

        return str;

    }

    public String toFullString() {
        String s;


       // if (Image != null) {
       //     s = " <img src= \"" + Image + " width=\"25\" height=\"25\">";
       // } else {
            s = "<div> <img class=\"map\" src=\"img/" + png + ".png\">";
      //  s += "<button id=\"opener\">View Building</button>";

              //      "<img src=\"img/" + png + ".png\" width=\"191\" height=\"191\">";

        // <a class="fancybox" rel="group" href="big_image_1.jpg"><img src="small_image_1.jpg" alt="" /></a>

        // String str = name + ": " + numPC + " PC(s), " + numMAC + " Mac(s)";

        // String str = "<label for=\"" + name + "\">" + name + "</label>" ;

        s += "<p>";
        s += "&nbsp &nbsp &nbsp";
        s += description;
        s += "</p>";

        s += "</div>";
     //   }
        return s;
    }


}
