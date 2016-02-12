package labfinder;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BSON;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;

import java.lang.reflect.Array;
import java.text.CollationElementIterator;
import java.util.*;

/**
 * Created by Joe on 1/4/2016.
 */
public class MongoAccess {
    public static List<Course> getAllClasses( )
    {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("test").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> classCol = db.getCollection("classes");

        //Find one test//
       // Document first = classCol.find().first();
       // String str = first.getString("name");
       // System.out.println(str);

        List<Document> all = classCol.find().into(new ArrayList<Document>());

        List<Course> courses = new ArrayList<Course>();

        for(Document doc : all)
        {
            courses.add(new Course(doc));
        }

        return courses;
    }

    public static List<Room> getAllRooms( )
    {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> classCol = db.getCollection("Rooms");

        //Find one test//
        // Document first = classCol.find().first();
        // String str = first.getString("name");
        // System.out.println(str);

        List<Document> all = classCol.find().into(new ArrayList<Document>());

        List<Room> rooms = new ArrayList<Room>();

        for(Document doc : all)
        {
            rooms.add(new Room(doc));
        }

        return rooms;
    }

    public static List<Room> getSomeRooms(Bson filter)
    {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> roomCol = db.getCollection("Rooms");

        List<Document> all = roomCol.find(filter).into(new ArrayList<Document>());

        List<Room> rooms = new ArrayList<Room>();

        for(Document doc : all)
        {
            rooms.add(new Room(doc));
        }

        return rooms;
    }




    public static List<Room> getSomeRoomwHardnImage(  Bson hardfilter, Bson imgfilter) {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> roomCol = db.getCollection("Rooms");

        List<Room> rooms = new ArrayList<Room>();


        if (hardfilter != null) {
            if (imgfilter != null) {
                List<Document> all = roomCol.find(and(hardfilter, imgfilter)).into(new ArrayList<Document>());
                for (Document doc : all) {
                    rooms.add(new Room(doc));
                }
            }
            else
            {
                List<Document> all = roomCol.find(hardfilter).into(new ArrayList<Document>());
                for (Document doc : all) {
                    rooms.add(new Room(doc));
                }
            }
        }
        else if (imgfilter != null){
            List<Document> all = roomCol.find(imgfilter).into(new ArrayList<Document>());
            for (Document doc : all) {
                rooms.add(new Room(doc));
            }
        }

        return rooms;

    }


    public static List<String> getImgList(Collection<String> myCol)
    {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> imgCol = db.getCollection("Images");

        List<Document> all = imgCol.find().into(new ArrayList<Document>());

        Image tmp = new Image();

        List<String> list = new ArrayList<String>();


        for(Document doc : all)
        {
            tmp.copyFrom(doc);

            if(tmp.software.containsAll(myCol)){
                list.add(tmp.name);
            }
        }

        return list;
    }

    public static List<String> getRoomList(List<String> timeCol, List<String> dayCol){

        if ((timeCol.isEmpty())||(dayCol.isEmpty()))
        {
            return null;
        }

        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> courseCol = db.getCollection("Classes");

        BasicDBList timelist = new BasicDBList();
        BasicDBObject tmplistObj;

        for(String str : timeCol){
            //System.out.println(str);
            tmplistObj = new BasicDBObject("Start Time", str );
            timelist.add(tmplistObj);
        }

        Bson timefilter = new BasicDBObject("$and", timelist );



        BasicDBList daylist = new BasicDBList();

        for(String str : dayCol){
            //System.out.println(str);
            tmplistObj = new BasicDBObject("Day", str );
            daylist.add(tmplistObj);
        }

        Bson dayfilter = new BasicDBObject("$and", daylist );



        System.out.println(dayfilter);
        System.out.println("dayfilter ");

        System.out.println(timefilter);
        System.out.println("timefilter");

        //List<Document> all = courseCol.find(and(new Document("Days", "T"), new Document("Start Time", "9:30 AM"))).into(new ArrayList<Document>());
        List<Document> all = courseCol.find(or(dayfilter, timefilter)).into(new ArrayList<Document>());



        List<String> list = new ArrayList<String>();

        for(Document doc : all)
        {
            Course tmp = new Course(doc);


                if(tmp.StartTime.size()>1){
                    for(int i = 0; i < tmp.StartTime.size(); i++){
                        if((timeCol.contains(tmp.StartTime.get(i)))&&(dayCol.contains(tmp.Days.get(i)))){
                            if(!list.contains(tmp.Room.get(i)))
                            {

                                list.add(tmp.Room.get(i));
                            }
                        }
                    }
                }
                else {

                    System.out.println(doc.toString());
                    System.out.println("in if");

                    for (String str : tmp.Days) {
                        if ((timeCol.contains(tmp.StartTime.get(0))) && (dayCol.contains(str))) {
                            if (!list.contains(tmp.Room.get(0))) {

                                System.out.println(doc.toString());
                                System.out.println("About to add");
                                list.add(tmp.Room.get(0));
                            }
                        }
                    }
                }

        }

        return list;

    }

    public static void setMongoDays(){

        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> collection = db.getCollection("Classes");

        List<Document> classlist = collection.find().into(new ArrayList<Document>());
        List<Document> newlist = new ArrayList<Document>();


        List<String> tmpBuilding, tmpRoom, tmpDays, tmpStart, tmpEnd;


        String tmp, btmp;
        Object otmp;


        for(Document doc  : classlist){

            List<String> tmpRoomname = new ArrayList<String>();

            tmp = doc.get("Room", Object.class).toString();
            btmp = doc.get("Building", Object.class).toString();
            otmp = doc.get("End time", Object.class);


            if((tmp.length() > 1)&&(otmp != null)&&(btmp.length() > 1 )) {

                tmpRoom = Arrays.asList(doc.get("Room", Object.class).toString().split(","));
                tmpDays = Arrays.asList(doc.get("Days", Object.class).toString().split(","));

                for (int i = 0; i < tmpDays.size(); i++)
                {
                    tmpDays.set(i, tmpDays.get(i).trim());
                }

                tmpStart = Arrays.asList(doc.get("Start Time", Object.class).toString().split(","));
                tmpEnd = Arrays.asList(doc.get("End time", Object.class).toString().split(","));
                tmpBuilding = Arrays.asList(doc.get("Building", Object.class).toString().split(","));


                int i=0;
                while(i < tmpRoom.size()){

                    tmpBuilding.set(i, tmpBuilding.get(i).trim());
                    tmpRoom.set(i, tmpRoom.get(i).trim());
                    tmpStart.set(i, tmpStart.get(i).trim());
                    tmpEnd.set(i, tmpEnd.get(i).trim());

                    if(tmpRoom.get(i).equals("1")) {
                        tmpRoom.set(i, "001");
                    }
                    else if(tmpRoom.get(i).equals("2")) {
                        tmpRoom.set(i, "002");
                    }
                    else if (tmpRoom.get(i).equals("12")){
                        tmpRoom.set(i,"012");
                    }
                    else if (tmpRoom.get(i).equals("14")){
                        tmpRoom.set(i,"014");
                    }
                    else if (tmpRoom.get(i).equals("17")){
                        tmpRoom.set(i,"017");
                    }

                    tmpRoomname.add(i, tmpBuilding.get(i).trim() + " " + tmpRoom.get(i).trim());
                    i++;
                }

                System.out.println("Roomname = " + tmpRoomname);
                System.out.println("Building = " + tmpBuilding);
                System.out.println("RoomNum = " + tmpRoom);
                System.out.println("Start Time = " + tmpStart);
                System.out.println("End Time = " + tmpEnd);

                Document temp = new Document("Section Name", doc.get("Section Name"))
                        .append("Term", doc.get("Term"))
                        .append("Building", tmpBuilding)
                        .append("RoomNum", tmpRoom)
                        .append("Days", tmpDays)
                        .append("Room", tmpRoomname)
                        .append("Start Time", tmpStart)
                        .append("End Time", tmpEnd)
                        .append("Start Date", doc.get("Start Date"))
                        .append("End Date", doc.get("End Date"));

                newlist.add(temp);

            }
        }

        collection.drop();

        //try {
            collection.insertMany(newlist);

        //}catch (Exception e){

        //}

    }

    public static void setMongoRooms(){

        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> collection = db.getCollection("Rooms");

        List<Document> roomlist = collection.find().into(new ArrayList<Document>());
        List<Document> newlist = new ArrayList<Document>();


        String tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7, tmp8, tmp9, tmp10, tmp11, tmp12, tmp13;



        for(Document doc  : roomlist){

            Document tmpDoc = new Document("Name", doc.get("Name"));

            tmp1 = doc.get("Number", Object.class).toString();
            tmp2 = doc.get("Building", Object.class).toString();
            tmp3 = doc.get("Projector", Object.class).toString();
            tmp4 = doc.get("Printer", Object.class).toString();
            tmp5 = doc.get("Whiteboard", Object.class).toString();
            tmp6 = doc.get("Sound_equipment", Object.class).toString();
            tmp7 = doc.get("Dual_monitors", Object.class).toString();
            tmp8 = doc.get("Study_rooms", Object.class).toString();
            tmp9 = doc.get("Scanner", Object.class).toString();
            tmp10 = doc.get("Photo_equipment", Object.class).toString();
            tmp11 = doc.get("TVs", Object.class).toString();
            tmp12 = doc.get("Num_PC", Object.class).toString();
            tmp13 = doc.get("Num_mac", Object.class).toString();


            tmpDoc.append("Room", (tmp2 + " " + tmp1))
                .append("Number", tmp1)
                .append("Building",tmp2)
                .append("Image", doc.get("Image", Object.class).toString())
                .append("TVs", tmp11);


                tmpDoc.append("NumPCs", tmp12);

                tmpDoc.append("NumMacs", tmp13);

            if (tmp3.equals("1"))
            {    tmpDoc.append("Projector", true);   }
            if (tmp4.equals("1"))
            {    tmpDoc.append("Printer", true);   }
            if (tmp5.equals("1"))
            {    tmpDoc.append("Whiteboard", true);   }
            if (tmp6.equals("1"))
            {    tmpDoc.append("Sound Equipment", true);   }
            if (tmp7.equals("1"))
            {    tmpDoc.append("Dual Monitors", true);   }
            if (!tmp8.equals(""))
            {    tmpDoc.append("Study Rooms", tmp8);   }
            if (tmp9.equals("1"))
            {    tmpDoc.append("Scanner", true);   }
            if (tmp10.equals("1"))
            {    tmpDoc.append("Photo Equipment", true);   }

            newlist.add(tmpDoc);
        }

        collection.drop();

        collection.insertMany(newlist);

    }




}
