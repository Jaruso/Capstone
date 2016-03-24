package labfinder;

/**
 * Created by Joe on 1/3/2016.
 */

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.conversions.Bson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

public class Main {

    public static void main(String[] args){

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading( Main.class, "/");

        Spark.get("/", new Route() {

            public Object handle(final Request request, final Response response) {

                StringWriter writer = new StringWriter();
                try {


                    Template thisTemplate = configuration.getTemplate("RoomSearch.ftl");
                    Map<String, Object> thisMap = new HashMap<String, Object>();

                    //generate page content
                    List rooms = new ArrayList<String>();

                    thisMap.put("error", "");
                    //thisMap.put("dayoptions", Functions.getDayOptions());
                   // thisMap.put("timeoptions", Functions.getTimeOptions());
                    thisMap.put("softwareoptions", Functions.getSoftwareOptions());
                    thisMap.put("hardwareoptions", Functions.getHardwareOptions());
                    thisMap.put("extraoptions", Functions.getExtraOptions());
                    thisMap.put("rooms", rooms);

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


        Spark.post("/", new Route() {

            public Object handle(final Request request, final Response response) {

                // search bar input
                final String errstring = "Invalid input.";
                final String searchstring = request.queryParams("search");


                // Database manipulation functions

              //  MongoAccess.setMongoRooms();
              //  MongoAccess.setMongoDays();




                // Request input to various filter items

                String dayString = "";
                if (request.queryParams("dayFilter") != null) {
                    dayString = Functions.convertDay(request.queryParams("dayFilter"));
                }
                System.out.println(dayString);
                String timeString = "";
                if (request.queryParamsValues("timeFilter") != null) {
                    timeString = Functions.convertTime(request.queryParams("timeFilter"));
                }
                System.out.println(timeString);
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


                // Will combine all input
                final ArrayList<Bson> filterlist = new ArrayList<Bson>();
                boolean search = false;


                if((timeString != "")&&(dayString != "")){
                    search = true;
                    filterlist.add(Functions.generateRoomFilter(MongoAccess.getRoomList(timeString, dayString)));
                }
                if(!softstrings.isEmpty()) {
                    search = true;
                    filterlist.add(Functions.generateImgFilter(MongoAccess.getImgList(softstrings)));
                }
                if(!hardstrings.isEmpty()) {
                    search = true;
                    filterlist.add(Functions.generateHardfilter(hardstrings));
                }


                // prepare to write
                StringWriter writer = new StringWriter();
                if(search) {
                    try {

                        //  specify the html and set up map
                        Template thisTemplate = configuration.getTemplate("RoomSearch.ftl");
                        Map<String, Object> thisMap = new HashMap<String, Object>();

                        // Query MongoDB for rooms
                        List<Room> rooms = MongoAccess.getSomeRooms(Functions.combineFilter(filterlist));


                        //generate page content

                        thisMap.put("error", "");
                       // thisMap.put("dayoptions", Functions.getDayOptions());
                       // thisMap.put("timeoptions", Functions.getTimeOptions());
                        thisMap.put("softwareoptions", Functions.getSoftwareOptions());
                        thisMap.put("hardwareoptions", Functions.getHardwareOptions());
                        thisMap.put("extraoptions", Functions.getExtraOptions());
                        thisMap.put("rooms", rooms);


                        try {
                            thisTemplate.process(thisMap, writer);
                        } catch (TemplateException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
                        // halt(500);
                        e.printStackTrace();
                    }
                }
                else //invalid search criteria
                {
                    try {

                        //  specify the html and set up map
                        Template thisTemplate = configuration.getTemplate("RoomSearch.ftl");
                        Map<String, Object> thisMap = new HashMap<String, Object>();

                        //generate page content
                        List rooms = new ArrayList<String>();

                        thisMap.put("error", errstring);
                       // thisMap.put("dayoptions", Functions.getDayOptions());
                      //  thisMap.put("timeoptions", Functions.getTimeOptions());
                        thisMap.put("softwareoptions", Functions.getSoftwareOptions());
                        thisMap.put("hardwareoptions", Functions.getHardwareOptions());
                        thisMap.put("extraoptions", Functions.getExtraOptions());
                        thisMap.put("rooms", rooms);

                        try {
                            thisTemplate.process(thisMap, writer);
                        } catch (TemplateException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
                        // halt(500);
                        e.printStackTrace();
                    }
                }
                return writer;
            }

        });

    }



}
