package labfinder;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 3/17/2016.
 */
public class Functions {

    public static BasicDBObject generateRoomFilter(List<String> list) {

        BasicDBList or = new BasicDBList();
        BasicDBObject tmpObj;

        for(String str : list){
            tmpObj = new BasicDBObject("Room", str );
            or.add(tmpObj);
        }

        BasicDBObject filter = new BasicDBObject("$nor", or);

        return filter;
    }



    public static Bson combineFilter(List<Bson> list)
    {
        BasicDBList and = new BasicDBList();

        for(Bson b : list){
            and.add(b);
        }

        Bson filter = new BasicDBObject("$and", and);

        return filter;
    }


    public static BasicDBObject generateImgFilter(List<String> list) {

        BasicDBList or = new BasicDBList();

        BasicDBObject tmpObj;


        for(String str : list){
            tmpObj = new BasicDBObject("Image", str );
            or.add(tmpObj);
        }

        BasicDBObject filter = new BasicDBObject("$or", or);

        return filter;
    }

    //TODO  fix hardware filter

    public static Bson generateHardfilter(ArrayList<String> list){

        BasicDBList and = new BasicDBList();

        BasicDBObject tmpObj;

        for(String str : list){

            if(str.equals("Printer")){
                tmpObj = new BasicDBObject("Printer", true );
                and.add(tmpObj);
            }
            else if(str.equals("Projector")){
                tmpObj = new BasicDBObject("Projector", new BasicDBObject("$exists", true) );
                and.add(tmpObj);
            }
            else if(str.equals("TVs")){ //TODO This will not work
                tmpObj = new BasicDBObject("TVs", true );
                and.add(tmpObj);
            }
            else if(str.equals("Scanner")){
                tmpObj = new BasicDBObject("Scanner", new BasicDBObject("$exists", true) );
                and.add(tmpObj);
            }
            else if(str.equals("Dual Monitors")){
                tmpObj = new BasicDBObject("Dual_monitors", new BasicDBObject("$exists", true) );
                and.add(tmpObj);
            }
            else if(str.equals("Sound Equipment")){
                tmpObj = new BasicDBObject("Sound_equipment", new BasicDBObject("$exists", true) );
                and.add(tmpObj);
            }
            else if(str.equals("Photo Equipment")){
                tmpObj = new BasicDBObject("Photo_equipment", new BasicDBObject("$exists", true) );
                and.add(tmpObj);
            }
            else if(str.equals("Windows")){
                //TODO  ????
            }
            else if(str.equals("Apple")){
                //TODO  ????
            }
            else if(str.equals("Chromebooks")){
                //TODO  ????
            }

        }

        BasicDBObject filter = new BasicDBObject("$and", and);
        return filter;
    }


    public static String[] getSoftwareOptions(){

        String[] Options = new String[32];

        Options[0] = "Adobe Creative Cloud";
        Options[1] = "Adobe Web Premium";
        Options[2] = "MATLAB";
        Options[3] = "Quicken";
        Options[4] = "Steam";
        Options[5] = "IBM SPSS Statistics";
        Options[6] = "Microsoft SQL Server 2008";
        Options[7] = "Microsoft SQL Server 2012";
        Options[8] = "Microsoft Office";
        Options[9] = "Autodesk Infrastructure Design Suite";
        Options[10] = "Microsoft Visual Studio 2010";
        Options[11] = "Microsoft Visual Studio 2013";
        Options[12] = "Microsoft Visual Studio 2015";
        Options[13] = "Microsoft Visio";
        Options[14] = "Unity 3d";
        Options[15] = "GIMP";
        Options[16] = "Maxon CINEMA 4D Broadcast";
        Options[17] = "CrazyBump";
        Options[18] = "FRAPS";
        Options[19] = "InfraRecorder";
        Options[20] = "Garageband";
        Options[21] = "Microsoft Project";
        Options[22] = "Numbers";
        Options[23] = "Pages";
        Options[24] = "Fontographer";
        Options[25] = "Keynote";
        Options[26] = "Wacom Tablet";
        Options[27] = "Arduino";
        Options[28] = "iBooks";
        Options[29] = "iMovie";
        Options[30] = "Logic Pro X";
        Options[31] = "iTunes";

        return Options;
    }
    public static String[] getHardwareOptions(){

        String[] Options = new String[10];

        Options[0] = "Printer";
        Options[1] = "Projector";
        Options[2] = "TVs";
        Options[3] = "Scanner";
        Options[4] = "Dual Monitors";
        Options[5] = "Sound Equipment";
        Options[6] = "Photo Equipment";
        Options[7] = "Windows";
        Options[8] = "Apple";
        Options[9] = "Chromebooks";

        return Options;
    }
    public static String[] getExtraOptions(){

        String[] Options = new String[4];

        Options[0] = "Whiteboard";
        Options[1] = "Study Rooms";
        Options[2] = "Couches";
        Options[3] = "Building";

        return Options;
    }
    public static String[] getDayOptions(){

        String[] Options = new String[5];

        Options[0] = "M";
        Options[1] = "T";
        Options[2] = "W";
        Options[3] = "TH";
        Options[4] = "F";

        return Options;
    }
    public static String[] getTimeOptions(){

        String[] Options = new String[8];

        Options[0] = "8:00 AM";
        Options[1] = "9:30 AM";
        Options[2] = "11:00 AM";
        Options[3] = "12:30 PM";
        Options[4] = "2:00 PM";
        Options[5] = "3:30 PM";
        Options[6] = "5:00 PM";
        Options[7] = "5:30 PM";

        return Options;
    }

}
