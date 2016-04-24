package labfinder;

import org.bson.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Joe on 2/4/2016.
 *
 * Wrapper object for an Image Document.
 */
public class Image {

    public String name;
    public List<String> software;
    public Object lastupdated;

    /**
     * Default Constructor.
     */
    public Image() {
        name = "";
        software = null;
        lastupdated = "";
    }

    /**
     * Constructor for an existing image Document.
     * @param newD Existing Image document.
     */
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


    /**
     * Assign Image class the values form an existing image Document.
     * @param newD Existing Image document.
     */
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

    /**
     * Create a Sting to display information about an Image. For debuggigng purposes.
     * @return Image data as String.
     */
    public String toString()
    {
        String s =   name + ", " + software + ", " + lastupdated + ".\n";

        return s;
    }
}
