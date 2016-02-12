package labfinder;

/**
 * Created by Joe on 1/3/2016.
 */

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import spark.Request;
import spark.Request.*;
import spark.*;
import spark.Response;
import spark.Route;
import spark.Spark;

import javax.management.Query;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Filter;

public class _main {

    public static void main(String[] args){

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading( _main.class, "/");

        Spark.get("/", new Route() {

            public Object handle(final Request request, final Response response) {

                StringWriter writer = new StringWriter();
                try {


                    Template thisTemplate =  configuration.getTemplate("RoomSearch.html");
                    Map<String, Object> thisMap = new HashMap<String, Object>();


                    List<Room> rooms = MongoAccess.getAllRooms();

                    String[] dayoptions = getDayOptions();
                    String[] timeoptions = getTimeOptions();
                    String[] softwareoptions = getSoftwareOptions();
                    String[] hardwareoptions = getHardwareOptions();
                    String[] extraoptions = getExtraOptions();

                    thisMap.put("dayoptions", dayoptions);
                    thisMap.put("timeoptions", timeoptions);
                    thisMap.put("softwareoptions", softwareoptions);
                    thisMap.put("hardwareoptions", hardwareoptions);
                    thisMap.put("extraoptions", extraoptions);
                    thisMap.put("body", rooms);

                    try
                    {
                        thisTemplate.process(thisMap, writer);
                    } catch (TemplateException e)
                    {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    // halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });


        Spark.post("/", new Route() {

            public Object handle(final Request request, final Response response) {
                final String searchstring = request.queryParams("search");

                //MongoAccess.setMongoRooms();
                //MongoAccess.setMongoDays();

                final ArrayList<String> daystrings = new ArrayList<String>();
                if (request.queryParamsValues("dayFilter[]") != null) {
                    for (String str : request.queryParamsValues("dayFilter[]")) {
                        daystrings.add(str);
                    }
                }
                final ArrayList<String> timestrings = new ArrayList<String>();
                if (request.queryParamsValues("timeFilter[]") != null) {
                    for (String str : request.queryParamsValues("timeFilter[]")) {
                        timestrings.add(str);
                    }
                }
                final Collection<String> softstrings = new ArrayList<String>();
                if (request.queryParamsValues("softwareFilter[]")!= null) {
                    for (String str : request.queryParamsValues("softwareFilter[]")) {
                        softstrings.add(str);
                    }
                }
                final ArrayList<String> hardstrings = new ArrayList<String>();
                if (request.queryParamsValues("hardwareFilter[]") != null) {
                    for (String str : request.queryParamsValues("hardwareFilter[]")) {
                        hardstrings.add(str);
                    }
                }
                final ArrayList<String> extrastrings = new ArrayList<String>();
                if (request.queryParamsValues("extraFilter[]") != null) {
                    for (String str : request.queryParamsValues("extraFilter[]")) {
                        extrastrings.add(str);
                    }
                }

                //final List<String> timestrings = new ArrayList<String>();
                //timestrings.add("9:30 AM");

                //final List<String> daystrings = new ArrayList<String>();
                //daystrings.add("T");


               // Bson classfilter = generateClassFilter(

                final ArrayList<Bson> filterlist = new ArrayList<Bson>();
                //Bson roomfilter = null;

                if((!timestrings.isEmpty())&&(!daystrings.isEmpty())){
                    filterlist.add(generateRoomFilter(MongoAccess.getRoomList(timestrings, daystrings)));
                }
                if(!softstrings.isEmpty()) {
                    filterlist.add(generateImgFilter(MongoAccess.getImgList(softstrings)));
                }
                //if(!hardstrings.isEmpty()) {
                //    filterlist.add(generateHardfilter(hardstrings, extrastrings));
                //}

                StringWriter writer = new StringWriter();
                try {


                    Template thisTemplate = configuration.getTemplate("RoomSearch.html");
                    Map<String, Object> thisMap = new HashMap<String, Object>();



                    List<Room> rooms = MongoAccess.getSomeRooms(combineFilter(filterlist));

                    //List<Room> rooms = MongoAccess.getSomeRooms(generateRoomFilter(MongoAccess.getRoomList(timestrings, daystrings)));

                    //List<Room> rooms = MongoAccess.getSomeRooms(generateImgFilter(MongoAccess.getImgList(softstrings)));

                    String[] dayoptions = getDayOptions();
                    String[] timeoptions = getTimeOptions();
                    String[] softwareoptions = getSoftwareOptions();
                    String[] hardwareoptions = getHardwareOptions();
                    String[] extraoptions = getExtraOptions();

                    thisMap.put("dayoptions", dayoptions);
                    thisMap.put("timeoptions", timeoptions);
                    thisMap.put("softwareoptions", softwareoptions);
                    thisMap.put("hardwareoptions", hardwareoptions);
                    thisMap.put("extraoptions", extraoptions);
                    thisMap.put("body", rooms);
                    thisMap.put("str", searchstring);

                    try {
                        thisTemplate.process(thisMap, writer);
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    // halt(500);
                    e.printStackTrace();
                }

                return writer;
            }

        });

        Spark.get("/test", new Route() {

            public Object handle(final Request request, final Response response) {

                StringWriter writer = new StringWriter();
                try {

                    Template thisTemplate = configuration.getTemplate("test.html");
                    Map<String, Object> thisMap = new HashMap<String, Object>();

                    String[] dayoptions = getDayOptions();
                    String[] timeoptions = getTimeOptions();
                    String[] softwareoptions = getSoftwareOptions();
                    String[] hardwareoptions = getHardwareOptions();
                    String[] extraoptions = getExtraOptions();

                    thisMap.put("dayoptions", dayoptions);
                    thisMap.put("timeoptions", timeoptions);
                    thisMap.put("softwareoptions", softwareoptions);
                    thisMap.put("hardwareoptions", hardwareoptions);
                    thisMap.put("extraoptions", extraoptions);

                    try
                    {
                        thisTemplate.process(thisMap, writer);
                    } catch (TemplateException e)
                    {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    // halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });


        Spark.post("/test", new Route() {

            public Object handle(final Request request, final Response response) {
                final String[] softstring = request.queryParamsValues("softwareFilter[]");
                final String[] hardstring = request.queryParamsValues("hardwareFilter[]");
                final String[] estring = request.queryParamsValues("extraFilter[]");
                for(String str : softstring)
                {  System.out.println(str);  }
                for(String str : hardstring)
                {  System.out.println(str);  }
                for(String str : estring)
                {  System.out.println(str);  }


                StringWriter writer = new StringWriter();
                try {


                    Template thisTemplate = configuration.getTemplate("test.html");
                    Map<String, Object> thisMap = new HashMap<String, Object>();

                    String[] softwareoptions = getSoftwareOptions();
                    String[] hardwareoptions = getHardwareOptions();
                    String[] extraoptions = getExtraOptions();

                    thisMap.put("softwareoptions", softwareoptions);
                    thisMap.put("hardwareoptions", hardwareoptions);
                    thisMap.put("extraoptions", extraoptions);

                    try {
                        thisTemplate.process(thisMap, writer);
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    // halt(500);
                    e.printStackTrace();
                }

                return writer;
            }

        });

    }



    public static BasicDBObject generateRoomFilter(List<String> list) {

        BasicDBList or = new BasicDBList();

        BasicDBObject tmpObj;


        for(String str : list){
            System.out.println(str);
            tmpObj = new BasicDBObject("Room", str );
            or.add(tmpObj);
        }


        //System.out.println(or);
       // System.out.println("  -- { $and : { Room: { $eq : [ that ] } } } ---");

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




    public static Bson generateImgFilter(List<String> list)
    {
        int size = list.size();

        Bson filter;


        switch(size){
            case 0:
                filter = null;
                break;
            case 1:
                filter = eq("Image", list.get(0));
                break;
            case 2:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1)));
                break;
            case 3:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)));
                break;
            case 4:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3)));
                break;
            case 5:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)));
                break;
            case 6:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5)));
                break;
            case 7:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)));
                break;
            case 8:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7)));
                break;
            case 9:
                filter = or(eq("Image", list.get(0)), eq("Image", list.get(1))
                        ,eq("Image", list.get(2)), eq("Image", list.get(3)),
                        eq("Image", list.get(4)), eq("Image", list.get(5)),
                        eq("Image", list.get(6)), eq("Image", list.get(7)),
                        eq("Image", list.get(8)));
                break;
            case 10:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(8)),eq("Image", list.get(9)));
                break;
            case 11:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(8)),eq("Image", list.get(9))
                        ,eq("Image", list.get(10)));
                break;
            case 12:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(8)),eq("Image", list.get(9))
                        ,eq("Image", list.get(10)),eq("Image", list.get(11)));
                break;
            case 13:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(8)),eq("Image", list.get(9))
                        ,eq("Image", list.get(10)),eq("Image", list.get(11))
                        ,eq("Image", list.get(12)));
                break;
            case 14:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(8)),eq("Image", list.get(9))
                        ,eq("Image", list.get(10)),eq("Image", list.get(11))
                        ,eq("Image", list.get(12)),eq("Image", list.get(13)));
                break;
            case 15:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(8)),eq("Image", list.get(9))
                        ,eq("Image", list.get(10)),eq("Image", list.get(11))
                        ,eq("Image", list.get(12)),eq("Image", list.get(13))
                        ,eq("Image", list.get(14)));
                break;
            case 16:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(8)),eq("Image", list.get(9))
                        ,eq("Image", list.get(10)),eq("Image", list.get(11))
                        ,eq("Image", list.get(12)),eq("Image", list.get(13))
                        ,eq("Image", list.get(14)),eq("Image", list.get(15)));
                break;
            case 17:
                filter = or(eq("Image", list.get(0)),eq("Image", list.get(1))
                        ,eq("Image", list.get(2)),eq("Image", list.get(3))
                        ,eq("Image", list.get(4)),eq("Image", list.get(5))
                        ,eq("Image", list.get(6)),eq("Image", list.get(7))
                        ,eq("Image", list.get(10)),eq("Image", list.get(11))
                        ,eq("Image", list.get(12)),eq("Image", list.get(13))
                        ,eq("Image", list.get(14)),eq("Image", list.get(15))
                        ,eq("Image", list.get(16)));
                break;
            default:
                filter =  eq("Image", new BasicDBObject("$exists", true));
                break;
        }


        return filter;
    }

    public static Bson generateHardfilter(ArrayList<String> hardstrings, ArrayList<String> extrastrings){

        hardstrings.addAll(extrastrings);

        int size = hardstrings.size();
        Bson filter;

        switch(size){
            case 0:
                filter = null;
                break;
            case 1:
                filter = eq(hardstrings.get(0), 1);
                break;
            case 2:
                filter = and(eq(hardstrings.get(0), 1), eq(hardstrings.get(1), 1));
                break;
            case 3:
                filter = and(eq(hardstrings.get(0), 1), eq(hardstrings.get(1), 1)
                        , eq(hardstrings.get(2), 1));
                break;
            case 4:
                filter = and(eq(hardstrings.get(0), 1), eq(hardstrings.get(1), 1)
                        , eq(hardstrings.get(2), 1), eq(hardstrings.get(3), 1));
                break;
            case 5:
                filter = and(eq(hardstrings.get(0), 1), eq(hardstrings.get(1), 1)
                        , eq(hardstrings.get(2), 1), eq(hardstrings.get(3), 1)
                        , eq(hardstrings.get(4), 1));
                break;
            case 6:
                filter = and(eq(hardstrings.get(0), 1), eq(hardstrings.get(1), 1)
                        , eq(hardstrings.get(2), 1), eq(hardstrings.get(3), 1)
                        , eq(hardstrings.get(4), 1), eq(hardstrings.get(5), 1));
                break;
            case 7:
                filter = and(eq(hardstrings.get(0), 1), eq(hardstrings.get(1), 1)
                        , eq(hardstrings.get(2), 1), eq(hardstrings.get(3), 1)
                        ,eq(hardstrings.get(4), 1), eq(hardstrings.get(5), 1)
                        , eq(hardstrings.get(6), 1));
                break;
            default:
                filter = null;
                break;
        }

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
        Options[3] = "Th";
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
