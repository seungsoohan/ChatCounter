package edu.handong.csee.java.countingChat;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * MacParser class is used to parsing *.csv file
 * and inherited by MassageParser class
 * @author kdi96
 *
 */
public class MacParser extends MessageParser{
	/**
	 * This method parse csv file and store parsing data to messageW array list
	 * so it can make integrate all message
	 */
	public void structurist(String line) {
		

		Pattern nameP = Pattern.compile("(20[0-1][0-9]-[0-1][0-9]-[0-3][0-9]\\s)([0-2][0-9]:[0-5][0-9])(:[0-5][0-9])(\\,\")(.*)(\"\\,)(\")(.*)");
		
		Matcher nameM = nameP.matcher(line);
		
	
		if(nameM.find()) {
			String patternName = nameM.group();
			int first = nameM.start(5);
			int last = nameM.end(5);
			pName = patternName.substring(first, last);

			int firstM = nameM.start(8);
			int lastM = nameM.end(8);
			wMessage = patternName.substring(firstM, lastM);

			int firstD = nameM.start(2);
			int lastD = nameM.end(2);
			fullTime = patternName.substring(firstD,lastD);
			fullString="\""+pName+"\""+", "+"\""+fullTime+"\""+", "+"\""+wMessage+"\"";
			
			if(!messageM.contains(fullString))
			messageM.add(fullString);
		}


	}
	/**
	 * this method used by FileLoader class to check line useful
	 */
	public String sortingWithTime(String line) {
		Pattern nameP = Pattern.compile("(20[0-1][0-9]-[0-1][0-9]-[0-3][0-9]\\s)([0-2][0-9]:[0-5][0-9])(:[0-5][0-9])(\\,\")(.*)(\"\\,)(\")(.*)");
		Matcher nameM = nameP.matcher(line);
		String pName="";

		if(nameM.find()) {
			String patternName = nameM.group();
			int first = nameM.start(2);
			int last = nameM.end(2);
			pName = patternName.substring(first, last);
			return pName;
		}
		
		return pName;
	}
}


