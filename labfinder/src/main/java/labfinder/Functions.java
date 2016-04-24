package labfinder;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import org.bson.conversions.Bson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Joe on 3/17/2016.
 *
 * Class for general logic for producing filters to query the mongoDB instance.
 *
 */
public class Functions {


    /**
     * Create a DBfilter object for querying the Rooms Collection based on a list unwanted room names.
     *
     * @return A DB object which is $nor query on a list of room names.
     * @param list A list of Room names to NOT be included.
     */
    public static BasicDBObject generateRoomFilter(List<String> list) {

        BasicDBList or = new BasicDBList();
        BasicDBObject tmpObj;


        for(String str : list){
            tmpObj = new BasicDBObject("Room", str );
            or.add(tmpObj);
        }

        if(or.toString().equals("[ ]")) //get all rooms
            return new BasicDBObject("$exists", "Room");

        //else
        return new BasicDBObject("$nor", or);
    }


    /**
     * Combine a list of Bson objects with an $and clause.
     *
     * @param list A list of Bson objects representing independent querys
     * @return A DBObject for filtering the Rooms collection.
     */
    public static Bson combineFilter(List<Bson> list)
    {
        BasicDBList and = new BasicDBList();
            // If the Bson object is empty, skip.
        for(Bson b : list){ if(!b.toString().equals("{ \"\" : \"\"}")){and.add(b);} }
        return new BasicDBObject("$and", and);
    }

    /**
     * With a given list of Image names, create a DBObject for filtering the Rooms Collection.
     * @param list List of Image names.
     * @return A DBObject for filtering the Rooms Collection.
     */
    public static BasicDBObject generateImgFilter(List<String> list) {
        BasicDBList or = new BasicDBList();
        if(list.isEmpty())
            return new BasicDBObject("", "");
        for(String str : list){ or.add(new BasicDBObject("Image", str)); }
        return new BasicDBObject("$or", or);
    }

    /**
     * Create a DbOBject for filtering Rooms based on a list of hardware.
     * @param list A list of hardware
     * @return A DBObject the combines an $and clause with a list of hardware.
     */
    public static Bson generateHardfilter(ArrayList<String> list){

        BasicDBList and = new BasicDBList();

        for(String str : list){

            if(str.equals("Printer")){
                and.add(new BasicDBObject("Printer", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Projector")){
                and.add(new BasicDBObject("Projector", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("TVs")){
                and.add(new BasicDBObject("TVs", new BasicDBObject("$gt", 0) ));
            }
            else if(str.equals("Scanner")){
                and.add(new BasicDBObject("Scanner", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Dual Monitors")){
                and.add(new BasicDBObject("Dual Monitors", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Sound Equipment")){
                and.add(new BasicDBObject("Sound Equipment", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Photo Equipment")){
                and.add( new BasicDBObject("Photo Equipment", new BasicDBObject("$exists", true) ));
            }
            else if(str.equals("Windows lab")){
                and.add(new BasicDBObject("NumPCs", new BasicDBObject("$gt", 1) ));
            }
            else if(str.equals("Mac lab")){
                and.add(new BasicDBObject("NumMacs", new BasicDBObject("$gt", 0) ));
            }
            else if(str.equals("Study Rooms")){
                and.add(new BasicDBObject("StudyRooms",  new BasicDBObject("$gt", 0) ));
            }

        }
        return new BasicDBObject("$and", and);
    }

    /**
     * Returns list of strings containing the names of all possible software options.
     * @return A list of software names.
     */
    public static List<String> getSoftwareOptions(){

        List<String> Options = new ArrayList<String>();

        Options.add("Adobe Creative Cloud");
        Options.add("Adobe Web Premium");
        Options.add("MATLAB");
        Options.add("Quicken");
        Options.add("Steam");
        Options.add("IBM SPSS Statistics");
        Options.add("Microsoft SQL Server 2008");
        Options.add("Microsoft SQL Server 2012");
        Options.add("Microsoft Office");
        Options.add("Autodesk Infrastructure Design Suite");
        Options.add("Microsoft Visual Studio 2010");
        Options.add("Microsoft Visual Studio 2013");
        Options.add("Microsoft Visual Studio 2015");
        Options.add("Microsoft Visio");
        Options.add("Unity 3d");
        Options.add("GIMP");
        Options.add("Maxon CINEMA 4D Broadcast");
        Options.add("CrazyBump");
        Options.add("FRAPS");
        Options.add("InfraRecorder");
        Options.add("Garageband");
        Options.add("Microsoft Project");
        Options.add("Numbers");
        Options.add("Pages");
        Options.add("Fontographer");
        Options.add("Keynote");
        Options.add("Wacom Tablet");
        Options.add("Arduino");
        Options.add("iBooks");
        Options.add("iMovie");
        Options.add("Logic Pro X");
        Options.add("iTunes");

        Collections.sort(Options);

        return Options;
    }

    /**
     * Create and return a list of all possible hardware options.
     * @return List of hardware.
     */
    public static List<String> getHardwareOptions(){

        List<String> Options = new ArrayList<String>();

        Options.add("Printer");
        Options.add("Projector");
        Options.add("TVs");
        Options.add("Scanner");
        Options.add("Dual Monitors");
        Options.add("Sound Equipment");
        Options.add("Photo Equipment");
        Options.add("Windows lab");
        Options.add("Mac lab");
        Options.add("Study Rooms");

        return Options;
    }


    /**
     * Reurn an array of strings representing the days of the week.
     * @return an Array of day abbreviations.
     */
    public static String[] getDays(){

        String[] Options = new String[7];

        Options[0] = "S";
        Options[1] = "M";
        Options[2] = "T";
        Options[3] = "W";
        Options[4] = "TH";
        Options[5] = "F";
        Options[6] = "ST";


        return Options;
    }

    /**
     * Return an array of Strings representing the searchable time slots.
     * @return an Array of time slot stings.
     */
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

    /**
     * Convert calendar date to a day of the week.
     *  i.e. 4/15/2016 -> F.
     * @param date A date in the format "mm/dd/yyyy".
     * @return An abbreviation for a the day of the week (Friday = F).
     */
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

    /**
     * Covnert a search time into a value used to search the database.
     * @param timestr  A time string taken from the search filter.
     * @return A time string to be used to filter the database.
     */
    public static String convertTime(String timestr){
        int index = getTimes().indexOf(timestr);
        if(index==-1)
            return "";
        else
            return getTimeOptions()[index];
    }


    /**
     * Return a list of times for the interface.
     * @return A list of time options.
     */
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
