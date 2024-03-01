/**
 * 
 */
package accidentpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import accidentpack.binarySearchTree.Node;

/**
 * @author abard & Devin C
 *
 */
public class reportHelper {
	
	/**
	 * @author abard
	 * returns the number of reports found in a binary tree after a given date
	 * @param report
	 * @param state
	 * @param date
	 * @return int
	 */
	public static int calculateReports(ArrayList<binarySearchTree> report, String state, String date) {
		
		Node root = report.get(getStateIndex(state, report)).root;
		LocalDate givenDate = dateConvert(date);
		
		while(root != null) {
			//if node date is less than user date, move to right child
			if(root.data.getStartTime().isBefore(givenDate))
				root = root.right;
			//if node date is greater than or equal to the user date, return number of children
			else
				return root.rightChildren + 1;
		}
		//no reports found
		return 0;
	}
	/**
	 * @author abard
	 * returns the number of reports on or after the given date in the given state (recursive version)
	 * @param report
	 * @param state
	 * @param date
	 * @return int
	 */
	public static int recursiveCalculateReports(ArrayList<binarySearchTree> report, String state, String date) {
		LocalDate givenDate = dateConvert(date);
		return report.get(getStateIndex(state,report)).numOnOrAfter(givenDate);
	}
	/**
	 * @author abard
	 * calculates elapsed time and converts it to seconds
	 * @param time1
	 * @param time2
	 * @return String
	 */
	public static String convertTime(long time1, long time2) {
		long elapsedTime = time2 - time1;
		double elapsedTimeSeconds;
		elapsedTimeSeconds = elapsedTime / 1000000000;
		return String.valueOf(elapsedTimeSeconds);
	}
    /**
     * Reads lines from a CSV file and creates separate binary search trees for each state,
     * adding reports to their respective trees based on the state attribute.
     * 
     * @param filePath the path to the CSV file
     * @return an array list of binary search trees indexed by state
     * @throws IOException if an I/O error occurs
     */
    public static ArrayList<binarySearchTree> ReadCSVFile(String filePath) throws IOException {
        ArrayList<binarySearchTree> stateBSTList = new ArrayList<>(); // List to hold binary search trees for each state
        
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = null;
        
        reader.readLine(); // Skip the header line
        while ((line = reader.readLine()) != null) {
            report r = readfile(line); // Convert CSV line to report object
            
            // Check if the binary search tree for the state exists in the list
            int stateIndex = getStateIndex(r.getState(), stateBSTList);
            if (stateIndex == -1) {
                // If not, create a new binary search tree for the state and add it to the list
                binarySearchTree newBST = new binarySearchTree();
                newBST.add(r);
                stateBSTList.add(newBST);
            } else {
                // If yes, add the report to the existing binary search tree for the state
                stateBSTList.get(stateIndex).add(r);
            }
        }
        
        reader.close();
        
        return stateBSTList;
    }
    
    /**
     * Gets the index of the binary search tree for the given state in the list,
     * or -1 if not found.
     * 
     * @param state the state code
     * @param stateBSTList the list of binary search trees
     * @return the index of the binary search tree for the given state, or -1 if not found
     */
    private static int getStateIndex(String state, ArrayList<binarySearchTree> stateBSTList) {
        for (int i = 0; i < stateBSTList.size(); i++) {
            if (stateBSTList.get(i).getRoot().data.getState().equals(state)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * @author abard & Devin C
     * Reads lines from a csv file and converts them to report objects
     * @param line the line being read into a report object
     */
    //change arraylist input
    private static report readfile(String line) {
        String[] items = line.split(",");
        String id = items[0];
        int severity = Integer.parseInt(items[1]);
        LocalDate startTime = dateConvert(items[2]);
        LocalDate endTime = dateConvert(items[3]);
        String street = items[4];
        String city = items[5];
        String county = items[6];
        String state = items[7];
        int temperature = Integer.parseInt(items[8].split("\\.")[0]);
        int humidity = Integer.parseInt(items[9].split("\\.")[0]);
        int visibility = Integer.parseInt(items[10].split("\\.")[0]);
        String weatherCondition = items[11];
        boolean crossing = Boolean.parseBoolean(items[12]);
        boolean sunrise = items[13].equals("Night")?true:false;
        report r = new report(id, severity, startTime, endTime, street, city, county, state,
                temperature, humidity, visibility, weatherCondition, crossing, sunrise);
        return r;
    }
    
    //  Taken from Dr. Behrooz Mansouri
    
    //  Create a formatter with the specific date-time format
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * @author Dr. Behrooz Mansouri
     * This method takes in the string representation of dateTime and return LocalDate object
     * @param dateTimeString
     * @return
     */
    public static LocalDate dateConvert(String dateTimeString)
    {
        // for some of the instances the after seconds there are 0s; this line will remove them
        dateTimeString = dateTimeString.split("\\.")[0];

        // Parse the string using the formatter
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateTimeString, formatter);
          } catch (Exception e) {
            // Handle parsing exception, e.g., invalid format, invalid date
            System.err.println("Error parsing date-time string: " + e.getMessage());
            localDate = null;
          }
        return localDate;
    }
}
