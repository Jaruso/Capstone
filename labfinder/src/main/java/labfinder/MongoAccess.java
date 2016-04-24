package labfinder;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.or;

/**
 * Created by Joe on 1/4/2016.
 *
 * A class containing functionality for Database access.
 */
public class MongoAccess {

    static MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
    static MongoClient client = new MongoClient(new ServerAddress(), options);

    static MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

    /**
     * Get all classes in the Class collection.
     * @return A List of of all Courses.
     */
    public static List<Course> getAllClasses( )
    {
        MongoCollection<Document> classCol = db.getCollection("classes");

        List<Document> all = classCol.find().into(new ArrayList<Document>());

        List<Course> courses = new ArrayList<Course>();

        for(Document doc : all)
        {
            courses.add(new Course(doc));
        }

        return courses;
    }

    /**
     * Return all the rooms in the Rooms collection.
     * @return A list of all the Rooms.
     */
    public static List<Room> getAllRooms( )
    {
        MongoCollection<Document> classCol = db.getCollection("Rooms");

        List<Document> all = classCol.find().into(new ArrayList<Document>());

        List<Room> rooms = new ArrayList<Room>();

        for(Document doc : all)
        {
            rooms.add(new Room(doc));
        }

        return rooms;
    }


    /**
     * Get all rooms with satisfy the filter criteria.
     *
     * @param filter A Bson filter stuctureto perform a query on the Rooms collection.
     * @return A list of Room Objects.
     */
    public static List<Room> getSomeRooms(Bson filter)
    {
        MongoCollection<Document> roomCol = db.getCollection("Rooms");

        List<Document> all;

        try {
            all = roomCol.find(filter).into(new ArrayList<Document>());
        }catch (Exception e)
        {
            all = roomCol.find().into(new ArrayList<Document>());
        }

        List<Room> rooms = new ArrayList<Room>();

        for(Document doc : all)
        {
            rooms.add(new Room(doc));
        }

        return rooms;
    }

    /**
     * Get the software contian in anm Image document.
     *
     * @param image The name of the image to be queryed for.
     * @return A list of software names.
     */
    public static List<String> getSoftList(String image)
    {
        MongoCollection<Document> imgCol = db.getCollection("Images");

        Bson imgObj = new BasicDBObject("Image", image );

        List<Document> img = imgCol.find(imgObj).into(new ArrayList<Document>());

        Image tmp = new Image(img.get(0));

        return tmp.software;
    }

    /**
     *  Return a list of Image names which contain all teh the software in a given collection.
     * @param myCol A list of Software names.
     * @return A list of Image names.
     */
    public static List<String> getImgList(Collection<String> myCol)
    {
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

    /**
     * Return a list of Rooms which are NOT available on a given day at a given time.
     * @param time Desired time slot.
     * @param day Desired day.
     * @return A list of names of unavailable rooms.
     */
    public static List<String> getRoomList(String time, String day){

        if ((time.equals(""))||(day.equals("")))
        {
            System.out.println("How did i get in here?");
            return null;
        }

        MongoCollection<Document> courseCol = db.getCollection("Classes");

        Bson timeObj = new BasicDBObject("Start Time", time );
        Bson dayObj = new BasicDBObject("Day", day );


        List<Document> all = courseCol.find(or(dayObj, timeObj)).into(new ArrayList<Document>());
        List<String> list = new ArrayList<String>();

        for(Document doc : all)
        {
            Course tmp = new Course(doc);

               // System.out.println(tmp.Room.get(0));

                if(tmp.StartTime.size()>1){ //multiple class times
                    for(int i = 0; i < tmp.StartTime.size(); i++){
                        if((time.equals(tmp.StartTime.get(i)))&&(day.equals(tmp.Days.get(i)))){
                            if(!list.contains(tmp.Room.get(i)))
                            {
                                list.add(tmp.Room.get(i));
                            }
                        }
                    }
                }
                else { //One start time
                    for (String str : tmp.Days) {
                        if ((time.equals(tmp.StartTime.get(0))) && (day.equals(str))) {
                            if (!list.contains(tmp.Room.get(0))) {
                                list.add(tmp.Room.get(0));
                            }
                        }
                    }
                }
        }
        return list;
    }

    /**
     * Used for manipulating Class data after a data import.
     */
    public static void setMongoDays(){
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

    /**
     * Used to manipulate Room data after an import.
     */
    public static void setMongoRooms(){
        MongoCollection<Document> collection = db.getCollection("Rooms");

        List<Document> roomlist = collection.find().into(new ArrayList<Document>());
        List<Document> newlist = new ArrayList<Document>();


        String tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7, tmp9, tmp10, tmp14, tmp15;
        Integer tmp12, tmp13, tmp11, tmp8;

        for(Document doc  : roomlist){

            Document tmpDoc = new Document("Name", doc.get("Name"));

            tmp1 = doc.get("Number", Object.class).toString();
            tmp2 = doc.get("Building", Object.class).toString();
            tmp3 = doc.get("Projector", Object.class).toString();
            tmp4 = doc.get("Printer", Object.class).toString();
            tmp5 = doc.get("Whiteboard", Object.class).toString();
            tmp6 = doc.get("Sound_equipment", Object.class).toString();
            tmp7 = doc.get("Dual_monitors", Object.class).toString();
            tmp8 = doc.getInteger("Study_rooms");
            tmp9 = doc.get("Scanner", Object.class).toString();
            tmp10 = doc.get("Photo_equipment", Object.class).toString();
            tmp11 = doc.getInteger("TVs");
            tmp12 = doc.getInteger("Num_PC");
            tmp13 = doc.getInteger("Num_mac");
            tmp14 = doc.get("Description", Object.class).toString();
            tmp15 = doc.get("Png", Object.class).toString();


            tmpDoc.append("Room", (tmp2 + " " + tmp1))
                .append("Number", tmp1)
                .append("Building",tmp2)
                .append("Image", doc.get("Image", Object.class).toString())
                .append("TVs", tmp11)
                .append("Description", tmp14)
                .append("Png", tmp15)
                .append("NumPCs", tmp12)
                .append("NumMacs", tmp13)
                .append("StudyRooms", tmp8);

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
