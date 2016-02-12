package labfinder;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * Created by Joe on 1/13/2016.
 */
public class Testing {
    public static void dosomething()
    {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("students").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> classCol = db.getCollection("grades");
        List<Document> these = classCol.find().into(new ArrayList<Document>());

        final List<Integer> names = new ArrayList<Integer>();
        int temp;
        String grade;
        Bson filter;
        Bson sort;
       // Document toRemove;

        for(Document doc : these)
        {
            temp = doc.getInteger("student_id");
            grade = doc.getString("score");
            if(names.contains(temp)){

            }
            else {
                filter = new Document("student_id", temp).append("type", "homework" );
                sort = new Document("score", 1);

                System.out.print(temp + ",  " + grade);

                classCol.deleteOne(classCol.find(filter).sort(sort).into(new ArrayList<Document>()).get(1));

                names.add(temp);
            }
        }

    }

    public static boolean setMongoDays(){

        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("spring2016").withReadPreference(ReadPreference.secondary());

        MongoCollection<Document> collection = db.getCollection("Classes");

        List<Document> classlist = collection.find().into(new ArrayList<Document>());
        List<Document> newlist = new ArrayList<Document>();

        List<String> tmpBuilding, tmpRoom, tmpDays, tmpStart, tmpEnd;

        for(Document doc  : classlist){

            tmpRoom = Arrays.asList(doc.get("Room", Object.class).toString().split(","));
            tmpDays = Arrays.asList(doc.get("Days", Object.class).toString().split(","));
            tmpStart = Arrays.asList(doc.get("Start Time", Object.class).toString().split(","));
            tmpEnd = Arrays.asList(doc.get("End time", Object.class).toString().split(","));
            tmpBuilding = Arrays.asList(doc.get("Building", Object.class).toString().split(","));

            Document temp = new Document("Section Name", doc.get("Section Name"))
                    .append("Term", doc.get("Term")).append("Building", tmpBuilding)
                    .append("Room", tmpRoom).append("Days", tmpDays)
                    .append("Start Time", tmpStart).append("End Time", tmpEnd)
                    .append("Start Date", doc.get("End Date")).append("End Date", doc.get("End Date"));

            newlist.add(temp);
        }

        collection.drop();

        try {
            collection.insertMany(newlist);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}













