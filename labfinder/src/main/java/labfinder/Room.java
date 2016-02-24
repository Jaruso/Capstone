package labfinder;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;


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

    }


    public String toString()
    {
        String s =   name + ", " + num + ", " + building + ", " + numPC + ", " + numMAC + ", " + Image + ", " +
                TV + ", " + projector + ", " + printer + ", " + whiteboard + ", " + soundeq + ", " +
                dualMon + ", " + studyRoom + ", " + scanner + ", " + photoeq + ".\n";

        // String str = name + ": " + numPC + " PC(s), " + numMAC + " Mac(s)";

        String str = "<label for=\"" + name + "\">" + name + "</label>" ;

        if(numPC.equals("1")) {

        }
        else if(!numPC.equals("0")){
            str += " <img src=\"http://www.iconarchive.com/download/i45768/tatice/operating-systems/Windows.ico\" width=\"25\" height=\"25\">";
        }
        if(!numMAC.equals("0")){
            str += " <img src=\"http://icons.iconarchive.com/icons/kyo-tux/phuzion/256/System-Mac-icon.png\" width=\"25\" height=\"25\">";
        }

        return str;
    }
}