package labfinder;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.bson.conversions.Bson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        return new BasicDBObject("$nor", or);
    }



    public static Bson combineFilter(List<Bson> list)
    {
        BasicDBList and = new BasicDBList();
        for(Bson b : list){ and.add(b); }
        return new BasicDBObject("$and", and);
    }


    public static BasicDBObject generateImgFilter(List<String> list) {
        BasicDBList or = new BasicDBList();
        for(String str : list){ or.add(new BasicDBObject("Image", str )); }
        return new BasicDBObject("$or", or);
    }

    //TODO  fix hardware filter
    public static Bson generateHardfilter(ArrayList<String> list){

        BasicDBList and = new BasicDBList();

        for(String str : list){

            if(str.equals("Printer")){
                and.add(new BasicDBObject("Printer", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Projector")){
                and.add(new BasicDBObject("Projector", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("TVs")){ //TODO This might not work
                and.add(new BasicDBObject("TVs", new BasicDBObject("$gt", 0) ));
            }
            else if(str.equals("Scanner")){
                and.add(new BasicDBObject("Scanner", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Dual Monitors")){
                and.add(new BasicDBObject("Dual_monitors", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Sound Equipment")){
                and.add(new BasicDBObject("Sound_equipment", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Photo Equipment")){
                and.add( new BasicDBObject("Photo_equipment", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Windows")){
                and.add(new BasicDBObject("Num_PC", new BasicDBObject("$gt", 0) ));
            }
            else if(str.equals("Apple")){
                and.add(new BasicDBObject("Num_mac", new BasicDBObject("$gt", 0) ));
            }

        }
        return new BasicDBObject("$and", and);
    }


    // Returns a list of the software contained on the image used in the room
    public static List<String> getSoftware(Room room){

        List<String> software = new ArrayList<String>();

        return software;
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
    public static String[] getDays(){

        String[] Options = new String[7];

        Options[0] = "S";
        Options[1] = "M";
        Options[2] = "T";
        Options[3] = "W";
        Options[4] = "TH";
        Options[5] = "F";
        Options[6] = "St";


        return Options;
    }
    public static String[] getTimeOptions(){

        String[] Options = new String[10];

        Options[0] = "pre";
        Options[1] = "8:00 AM";
        Options[2] = "9:30 AM";
        Options[3] = "11:00 AM";
        Options[4] = "12:30 PM";
        Options[5] = "2:00 PM";
        Options[6] = "3:30 PM";
        Options[7] = "5:00 PM";
        Options[8] = "5:30 PM";
        Options[9] = "post";

        return Options;
    }

    public static String convertDay(String date){

        if(date.equals(""))
            return "";

        Date d = null;
        String day;
        try {
            d = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        String[] Options = getDays();
        day = Options[c.get(Calendar.DAY_OF_WEEK)-1];// minus 1 since DAY_OF_WEEK starts at 1 not 0

        return day;
    }

    public static String convertTime(String timestr){
        int index = getTimes().indexOf(timestr);
        if(index==-1)
            return "";
        else
            return getTimeOptions()[index];
    }

    public static List<String> getTimes(){

        List<String> Options = new ArrayList<String>();

        Options.add("Before 8:00AM");
        Options.add("8:00AM - 9:15AM");
        Options.add("9:30AM - 10:45AM");
        Options.add("11:00AM - 12:15PM");
        Options.add("12:30PM - 1:45PM");
        Options.add("2:00PM - 3:15PM");
        Options.add("3:30PM - 4:45PM");
        Options.add("5:00PM - 6:15PM");
        Options.add("5:30PM - 8:15PM");
        Options.add("After 8:15PM");

        return Options;
    }

}
