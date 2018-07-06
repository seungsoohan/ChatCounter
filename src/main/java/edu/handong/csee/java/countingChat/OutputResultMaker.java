package edu.handong.csee.java.countingChat;

import java.io.PrintWriter;          
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This class make file that given from user path store result of data
 * 
 *
 */
public class OutputResultMaker {
	/**
	 * get store path and store data to the file
	 * data get from NameAdder class
	 * if you typing wrong it print out Error message
	 * but, you good path print out Those lines were written to your path
	 * @param args1 
	 */
	public void showResult(String args1, String args2) {
		
		MessagesData MD = new MessagesData();
		HashMap<String,Integer> map = MD.count();
		
		String countingArray[] = MD.countName();
		
		System.out.println("Complete Making File :"+args1);
		Scanner keyboard = new Scanner (System.in);
		
		PrintWriter output = null;
		
		try {
			output = new PrintWriter (args1);
		
		} catch (FileNotFoundException e) {
			System.out.println ("Error: Failed to open " + args1);
			System.exit(0);
		}
		
		output.println("ID, Count");
		
		for (int i = 0; i < countingArray.length; i++) {
			output.println (countingArray[i] + "," + map.get(countingArray[i]));
		}
		
		output.close();
		keyboard.close();
		
		System.out.println ("The result file is stored at " + args2);

	}
}


