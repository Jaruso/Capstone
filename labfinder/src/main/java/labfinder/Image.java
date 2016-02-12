package labfinder;

import org.bson.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 2/4/2016.
 */
public class Image {

    String name;
    List<String> software;
    Object lastupdated;


    public Image() {
        name = "";
        software = null;
        lastupdated = "";
    }

    public Image(Document newD) {

        String tmp = newD.getString("Software");

        name = newD.getString("Image");
        software = Arrays.asList(tmp.split(","));
        for(int i = 1; i < software.size(); i++) //To remove whitespace in front of all values past the first
        {
            software.set(i, software.get(i).substring(1));
        }
        lastupdated = newD.getString("Last updated");

    }

    public void copyFrom(Document newD){

        String tmp = newD.getString("Software");

        name = newD.getString("Image");
        software = Arrays.asList(tmp.split(","));
        for(int i = 1; i < software.size(); i++) //To remove whitespace in front of all values past the first
        {
            software.set(i, software.get(i).substring(1));
        }
        lastupdated = newD.getString("Last updated");

    }

    public String toString()
    {
        String s =   name + ", " + software + ", " + lastupdated + ".\n";

        return s;
    }
}
