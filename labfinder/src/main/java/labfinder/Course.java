package labfinder;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 1/10/2016.
 */
public class Course {

    public String Division;
    public String Section;
    public String Title;
    public List<String> Building;
    public List<String> RoomNum;
    public List<String> Room;
    public List<String> Days;
    public List<String> StartTime;
    public List<String> EndTime;


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
