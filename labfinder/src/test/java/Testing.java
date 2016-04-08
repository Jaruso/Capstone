import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import labfinder.Functions;
import labfinder.MongoAccess;
import labfinder.Room;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Joe on 1/13/2016.
 */
public class Testing {

    @Test
    public void testConvertDay()
    {
        String day = Functions.convertDay("3/25/2016"); //  3/25/2016 is a Friday
        assertEquals("F", day);                         // day should be equal to "F"
    }

    @Test
    public void testConvertTime()
    {
        String time = Functions.convertTime("12:30PM - 1:45PM"); //
        assertEquals("12:30 PM", time);                         // time should be equal to "12:30 PM"
    }




    /*
    @Test
    public void testSoftwareSearch()
    {
        ArrayList<String> softstrings = new ArrayList<String>();
        softstrings.add("MATLAB");
        softstrings.add("Quicken");

        final ArrayList<Bson> filterlist = new ArrayList<Bson>();
        filterlist.add(Functions.generateImgFilter(MongoAccess.getImgList(softstrings)));
        List<Room> rooms = MongoAccess.getSomeRooms(Functions.combineFilter(filterlist));

        for(Room r: rooms){
            if(Functions.getSoftware(r).contains(softstrings))
                assertTrue(true);
            else
                assertTrue(false);
        }

    }
    */

}













