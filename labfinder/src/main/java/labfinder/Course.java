package labfinder;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 1/10/2016.
 */
public class Course {

    String Division;
    String Section;
    String Title;
    List<String> Building;
    List<String> RoomNum;
    List<String> Room;
    List<String> Days;
    List<String> StartTime;
    List<String> EndTime;


    public Course(){

        Division = "";
        Section = "";
        Title = "";
        Building = new ArrayList<String>();
        Room = new ArrayList<String>();
        RoomNum = new ArrayList<String>();
        Days = new ArrayList<String>();
        StartTime = new ArrayList<String>();
        EndTime = new ArrayList<String>();
    }


    public Course(Document newD)
    {

        Division = newD.getString("Division");
        Section = newD.getString("Section Name");
        Title = newD.getString("Title");

        try{
            Building  = newD.get("Building", ArrayList.class);
            Room      = newD.get("Room", ArrayList.class);
            Days      = newD.get("Days", ArrayList.class);
            StartTime = newD.get("Start Time", ArrayList.class);
            EndTime   = newD.get("End Time", ArrayList.class);

            List<String> temp = new ArrayList<String>();

            for(int i = 0; i < Building.size(); i++){
                temp.add(Building.get(i) + " " + RoomNum.get(i));
            }

        }catch (Exception e){

        }

    }


    public void setValues(Document newD)
    {

        Division = newD.getString("Division");
        Section = newD.getString("Section Name");
        Title = newD.getString("Title");


        Building  = newD.get("Building", ArrayList.class);
        Room      = newD.get("Room", ArrayList.class);
        Days      = newD.get("Days", ArrayList.class);
        StartTime = newD.get("Start Time", ArrayList.class);
        EndTime   = newD.get("End Time", ArrayList.class);

        List<String> temp = new ArrayList<String>();
        try{
            for(int i = 0; i < Building.size(); i++){
                temp.add(Building.get(i) + " " + RoomNum.get(i));
            }
        }catch (Exception e){
            //Out of bounds
        }


    }

    public String toString()
    {
        String s = Division + ", " + Section + ", " + Title + ", " + Room + ", " + Days + ", " + StartTime + ", " + EndTime + ", "
                + ".\n";

        return s;
    }


}
