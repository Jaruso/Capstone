package labfinder;

import org.bson.Document;

import java.util.List;


/**
 * Created by Joe on 1/10/2016.
 *
 * This Class is designed to hold data about a room from the collection "Rooms" in the MongoDB instance.
 *
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

    /**
     * Create a Room object from an already existing Document.
     *
     * @param newD A document from the "Rooms" collection.
     */
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

    /** Return the Room as a chunk of HTML code to be contained inside of a list item (li) inside of an unordered list (ul).
     *
     *  ie.
     *  ul
     *     li
     *         someRoom.toString();
     *     /li
     *     li
     *         anotherRoom.toString();
     *     /li
     *  /ul
     *
     * @return HTML code that represents the Room in the list item (li).
     */
    public String toString()
    {
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
        str += getBody();
        str += "</div>\n   </div>\n";

        return str;

    }

    /** Returns a list of the software contained on the image used in the room.
     *
     * @return List of the software.
     */
    public List<String> getSoftware(){
        return MongoAccess.getSoftList(this.Image);
    }

    /** Return HTML code representing the contents of the body for the list item (li).
     *
     * @return HTML code as a string.
     */
    public String getBody() {

        String s;


            s = "<div> <h3>"+ name.split(" ")[0] +" Location</h3><img class=\"map\" src=\"img/" + png + ".png\">";
      //  s += "<button id=\"opener\">View Building</button>";

              //      "<img src=\"img/" + png + ".png\" width=\"191\" height=\"191\">";

        // <a class="fancybox" rel="group" href="big_image_1.jpg"><img src="small_image_1.jpg" alt="" /></a>

        // String str = name + ": " + numPC + " PC(s), " + numMAC + " Mac(s)";

        // String str = "<label for=\"" + name + "\">" + name + "</label>" ;

        s += "<p>";
        s += "&nbsp &nbsp &nbsp";
        s += description;
        s += "</p>";

        /**
         * Will create a drop down list of the software in the room if the room is assigned an Image.
         */
        if(this.Image.length()>1){
            s += "<div class=\"softlist\" >";
            s += "<a href=\"#\" class=\"show\"><b>Software</b> &#x25BC</a>";
            s += "<a href=\"#\" class=\"hide\">&#x25B2</a>";
            s += "<ol id=\"list\">";
            for(String str: this.getSoftware())
            {
                s+="<li>"+ str +"</li>";
            }
            s += "</ol>";
            s += "</div>";
        }

        s += "</div>";
     //   }
        return s;
    }


}
