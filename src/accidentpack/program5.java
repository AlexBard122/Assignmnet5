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
	 * @author abard
	 * main method for executing program
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//readFile = input file
		String filePath = args[0]; // 	EX: accidents_small_sample.csv
		
		//arguments for user input
		String state = args[1]; // 		EX: IL
		String date = args[2]; //  		EX: 2022-09-08
		
		//create binary search trees
		ArrayList<binarySearchTree> report = task1(filePath, state, date);
		
		//find report number with child count fields
		task2(state, date, report);
		
		//find report number with recursive method
		task3(state, date, report);
	}

	/**
	 * @author abard
	 * performs everything needed for task3
	 * @param state
	 * @param date
	 * @param report
	 */
	private static void task3(String state, String date, ArrayList<binarySearchTree> report) {
		long time1;
		long time2;
		String processTime;
		int reportCount;
		//find report number with recursive method
		time1 = System.nanoTime();
		reportCount = reportHelper.recursiveCalculateReports(report, state, date.concat(" 00:00:00"));
		time2 = System.nanoTime();
		processTime = reportHelper.convertTime(time1, time2);
		System.out.println(reportCount + " reports are available for " + state + " on and after the date " + date);
		System.out.println(processTime + " seconds to calculate this using recursive method");
	}

	/**
	 * @author abard
	 * performs everything needed for task2
	 * @param state
	 * @param date
	 * @param report
	 */
	private static void task2(String state, String date, ArrayList<binarySearchTree> report) {
		long time1;
		long time2;
		String processTime;
		//find report number with child count fields
		time1 = System.nanoTime();
		int reportCount = reportHelper.calculateReports(report, state, date.concat(" 00:00:00"));
		time2 = System.nanoTime();
		processTime = reportHelper.convertTime(time1, time2);
		System.out.println(reportCount + " reports are available for " + state + " on and after the date " + date);
		System.out.println(processTime + " seconds to calculate this using children count fields");
	}

	/**
	 * @author abard
	 * performs everything needed for task1
	 * @param filePath
	 * @param state
	 * @param date
	 * @return ArrayList<binarySearchTree>
	 * @throws IOException
	 */
	private static ArrayList<binarySearchTree> task1(String filePath, String state, String date)
			throws IOException {
		//create binary search trees
		long time1 = System.nanoTime();
		ArrayList<binarySearchTree> report = reportHelper.ReadCSVFile(filePath);
		long time2 = System.nanoTime();
		String processTime = reportHelper.convertTime(time1, time2);
		
		System.out.println(processTime + " Seconds to build the binary search trees.");
		System.out.println("Entered state: " + state);
		System.out.println("Entered date: " + date);
		return report;
	}

}
