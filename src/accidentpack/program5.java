/**
 * 
 */
package accidentpack;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author abard
 *
 */
public class program5 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//readFile = input file
		String filePath = args[0]; //accidents_small_sample.csv
		
		//temporary strings used for input until user input is properly implemented
		String state = args[1]; // IL
		String date = args[2]; // 2022-09-08
		
		
		//create binary search trees
		long time1 = System.nanoTime();
		ArrayList<binarySearchTree> report = reportHelper.ReadCSVFile(filePath);
		long time2 = System.nanoTime();
		String processTime = reportHelper.convertTime(time1, time2);
		
		System.out.println(processTime + " Seconds to build the binary search trees.");
		System.out.println("Entered state: " + state);
		System.out.println("Entered date: " + date);
		
		//find report number with child count fields
		time1 = System.nanoTime();
		int reportCount = reportHelper.calculateReports(report, state, date.concat(" 00:00:00"));
		time2 = System.nanoTime();
		processTime = reportHelper.convertTime(time1, time2);
		System.out.println(reportCount + " reports are available for " + state + " on and after the date " + date);
		System.out.println(processTime + " seconds to calculate this using children count fields");
		
		//find report number with recursive method
		
	}

}
