package edu.handong.csee.java.countingChat;


import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * A class for sorting and outputting csv, txt files 
 * in a certain format that is the goal of this program
 * @author kdi96
 *
 */
public class Runner {
	/**
	 * A class that contains all the steps of loading a file, splitting a message, 
	 * and counting the number of messages.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		OutputResultMaker fWriter = new OutputResultMaker();
		
		String AbsolutePathname = "";
		String OutputName = "";
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Input Absolute Pathname(Directory): ");
		FileReader loader = new FileReader();
		AbsolutePathname = keyboard.nextLine();
		
		System.out.println("Desired Ouput filename: ");
		OutputName = keyboard.nextLine();
		
		keyboard.close();
		
		try {
			loader.readAbsolutePath(AbsolutePathname);
			fWriter.showResult(OutputName);
		}
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Error: Missing Directory or Inputting output filename");
		}
		
	}

}
