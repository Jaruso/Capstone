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
    Object numPC;
    Object numMAC;
    String Image;
    Object TV;
    Object projector;
    Object printer;
    Object whiteboard;
    Object soundeq;
    Object dualMon;
    Object studyRoom;
    Object scanner;
    Object photoeq;
    Object description;


    public Room(Document newD) {

        name = newD.getString("Name");
        room = newD.getString("Room");
        num = newD.get("Number", Object.class);
        building = newD.getString("Building");
        numPC = newD.get("NumPCs");
        numMAC = newD.get("NumMacs");
        Image = newD.getString("Image");
        TV = newD.get("TVs", Object.class);
        projector = newD.get("Projector", Object.class);
        printer = newD.get("Printer", Object.class);
        whiteboard = newD.get("Whiteboard", Object.class);
        soundeq = newD.get("Sound_equipment", Object.class);
        dualMon = newD.get("Dual_monitors", Object.class);
        studyRoom = newD.get("Study_rooms", Object.class);
        scanner = newD.get("Scanner", Object.class);
        photoeq = newD.get("Photo_equipment", Object.class);
        description = newD.get("Description", Object.class);

    }


    public String toString() // toHtml, for freeMarker
    {

        String str = "<h3> ";
        str += name;
        str += " <div class = \"icons\">";
        if(numPC.equals("1")) {

        }
        else if(!numPC.equals("0")){
            str += " <img src=\"http://www.iconarchive.com/download/i45768/tatice/operating-systems/Windows.ico\" width=\"25\" height=\"25\">";
        }
        if(!numMAC.equals("0")){
            str += " <img src=\"http://icons.iconarchive.com/icons/kyo-tux/phuzion/256/System-Mac-icon.png\" width=\"25\" height=\"25\">";
        }
        if(whiteboard!=null){
        str += " <img src=\"http://icons.iconseeker.com/png/fullsize/battlestar-galactica-vol-3/lauras-whiteboard.png\" width=\"25\" height=\"25\">";
        }
        if(studyRoom!=null){
            str += " <img src=\"http://library.tulane.edu/sites/library.tulane.edu/files/styles/thumbnail/public/sites/default/files/img/icons/icon_28130.png?itok=If_YflCC\" width=\"25\" height=\"25\">";
        }
        str += "</div>";
        str += "</h3>";
        str += toFullString();

        return str;
    }

    public String toFullString() {
        String s;

        //TODo get specific images

       // if (Image != null) {
       //     s = " <img src= \"" + Image + " width=\"25\" height=\"25\">";
       // } else {
            s = "<div> <img src= \"" + "http://www.champlain.edu/Images/Academic-Affairs/CampusMap2015.png" + "\" width=\"200px\" height=\"150px\">";

        // String str = name + ": " + numPC + " PC(s), " + numMAC + " Mac(s)";

        // String str = "<label for=\"" + name + "\">" + name + "</label>" ;


        //ToDO make description
        s += "<p>";
        s += description;
        s += "</p>";

        s += "</div>";
     //   }
        return s;
    }


}