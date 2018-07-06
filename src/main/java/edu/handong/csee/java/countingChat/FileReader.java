package edu.handong.csee.java.countingChat;

import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Scanner; 
/**
 * This class consists of the code necessary to read the file's directory.
 * @author kdi96
 *
 */
public class FileReader {
	/**
	 * Reads a directory and allocates an array list for the number of files in that directory.
	 * At this time, the directory is directly input by the user.
	 * Also, if an invalid directory or a missing directory is entered, an exception is executed.
	 * @param pathname
	 */
public void readAbsolutePath(String pathname) {
		
		Scanner input= null;	
		ArrayList<File> fnames = getName(pathname);		
		
		MacParser macParser = new MacParser(); //
		WinParser winParser = new WinParser(); //
				
		for (File temp:fnames) {
			System.out.println("File lists: " + temp);
			String name = temp.toString();

			try {
				input=new Scanner(new File(name));
				
				if(name.contains(".csv")) {
					while(input.hasNextLine()) {
						String line = input.nextLine();
						winParser.parsingAndStore(line); 
					}
				}
					
				else if(name.contains(".txt")){
					while(input.hasNextLine()) {
						String line = input.nextLine();
						if(!macParser.selectData(line).equals("")) 
							macParser.parsingAndStore(line); 
					}
				}
				
				
			} catch (FileNotFoundException e) {
				System.out.println ("Error opening the file " + name);
	            System.exit (0);
			}
		}
	}

	private ArrayList<File> getName (String pathname) {
		ArrayList<File> fnames = new ArrayList<File>();
		File Path = new File(pathname);
		
		for(File temp:Path.listFiles())
			fnames.add(temp);
	
		return fnames;
}
}