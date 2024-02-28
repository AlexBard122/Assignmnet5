/**
 * 
 */
package accidentpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author abard
 *
 */
public class reportHelper {
	/**
     * @author abard
     * reads a CSV file and populates an ArrayList of report objects
     * @param filePath
     * @return Array List of report objects
     * @throws IOException
     */
    public static ArrayList<report> ReadCSVFile(String filePath) throws IOException{
    	//change arraylist to binary tree
        ArrayList<report> report = new ArrayList<report>();
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = null;
        
        reader.readLine();
        while ((line = reader.readLine()) != null) {
         
        readfile(report, line);
        }
        reader.close();
        Collections.sort(report, (r1, r2) ->  r1.getStartTime().compareTo(r2.getStartTime()));
        return report;
    }
    /**
     * @author abard
     * sorts the contents of a csv file into and ArrayList<report>
     * @param report
     * @param line
     */
    //change arraylist input
    private static void readfile(ArrayList<report> report, String line) {
        String[] items = line.split(",");
        String ID = items[0];
        int Severity = Integer.parseInt(items[1]);
        String StartTime = (items[2]);
        String EndTime = (items[3]);
        String Street = items[4];
        String City = items[5];
        String County = items[6];
        String State = items[7];
        String Temp = (items[8]);
        String Humidity = (items[9]);
        double Visibility = Double.parseDouble(items[10]);
        String Weather = items[11];
        boolean Crossing = Boolean.parseBoolean(items[12]);
        String Daynight = items[13];
        report r = new report(ID, Severity, StartTime, EndTime, Street, City, County, State, Temp,
                Humidity, Visibility, Weather, Crossing, Daynight);
        report.add(r);
    }
}
