package labfinder;

/**
 * Created by Joe on 1/3/2016.
 */

import com.mongodb.BasicDBObject;
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

        /**
         * Declare location of static files.
         * Html, CSS, JS, Images, etc.
         */
        Spark.staticFileLocation("/public");


        /**
         * Open Homepage.
         */
        Spark.get("/", new Route() {

            public Object handle(final Request request, final Response response) {

                StringWriter writer = new StringWriter();
                try {
                    Template thisTemplate = configuration.getTemplate("home.html");
                    Map<String, Object> thisMap = new HashMap<String, Object>();

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

        /**
         * Open the About page.
         */
        Spark.get("/about", new Route() {

            public Object handle(final Request request, final Response response) {

                StringWriter writer = new StringWriter();
                try {

                    Template thisTemplate = configuration.getTemplate("abouts.html");
                    Map<String, Object> thisMap = new HashMap<String, Object>();


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

        /**
         * Open the Search page.
         */
        Spark.get("/search", new Route() {

            public Object handle(final Request request, final Response response) {

                StringWriter writer = new StringWriter();
                try {


                    Template thisTemplate = configuration.getTemplate("search.ftl");
                    Map<String, Object> thisMap = new HashMap<String, Object>();

                    //generate page content
                    List rooms = new ArrayList<String>();

                    thisMap.put("error", "");
                    thisMap.put("timeoptions", Functions.getTimes());
                    thisMap.put("softwareoptions", Functions.getSoftwareOptions());
                    thisMap.put("hardwareoptions", Functions.getHardwareOptions());
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


        /**
         * Post to the Search page.
         *  i.e. Conduct search.
         */
        Spark.post("/search", new Route() {

            public Object handle(final Request request, final Response response) {

                // Request input to various filter items
                final String dayString = Functions.convertDay(request.queryParams("dayFilter"));

                final String timeString = Functions.convertTime(request.queryParams("timeFilter"));


                final Collection<String> softstrings = new ArrayList<String>();
                if (request.queryParamsValues("softwareFilter[]")!= null) {
                    softstrings.addAll(Arrays.asList(request.queryParamsValues("softwareFilter[]")));
                }
                final ArrayList<String> hardstrings = new ArrayList<String>();
                if (request.queryParamsValues("hardwareFilter[]") != null) {
                    hardstrings.addAll(Arrays.asList(request.queryParamsValues("hardwareFilter[]")));
                }


                // Will combine all input
                final ArrayList<Bson> filterlist = new ArrayList<Bson>();
                boolean search = false;


                if((!timeString.equals(""))&&(!dayString.equals(""))){
                    search = true;
                    BasicDBObject tmp = Functions.generateRoomFilter(MongoAccess.getRoomList(timeString, dayString));
                    if(tmp.toString().equals("{ \"$exists\" : \"Room\"}"))
                    {/* do nothing */ }
                    else
                    { filterlist.add(tmp); }
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
                        Template thisTemplate = configuration.getTemplate("search.ftl");
                        Map<String, Object> thisMap = new HashMap<String, Object>();

                        // Query MongoDB for rooms
                        List<Room> rooms;
                        if(filterlist.toString().equals("[]"))
                        {
                            rooms = MongoAccess.getAllRooms();
                        }
                        else {
                            rooms = MongoAccess.getSomeRooms(Functions.combineFilter(filterlist));
                        }
                        //generate page content

                        thisMap.put("error", "");
                        thisMap.put("timeoptions", Functions.getTimes());
                        thisMap.put("softwareoptions", Functions.getSoftwareOptions());
                        thisMap.put("hardwareoptions", Functions.getHardwareOptions());
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
                        Template thisTemplate = configuration.getTemplate("search.ftl");
                        Map<String, Object> thisMap = new HashMap<String, Object>();

                        //generate page content
                        List rooms = new ArrayList<String>();

                        thisMap.put("error", "Invalid input.");
                        thisMap.put("timeoptions", Functions.getTimes());
                        thisMap.put("softwareoptions", Functions.getSoftwareOptions());
                        thisMap.put("hardwareoptions", Functions.getHardwareOptions());
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
