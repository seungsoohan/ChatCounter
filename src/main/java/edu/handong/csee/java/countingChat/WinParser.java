package edu.handong.csee.java.countingChat;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * WinParser class is used to parsing *.txt file which is made in window environment
 * and inherited by MassageParser class
 * @author kdi96
 *
 */
public class WinParser extends MessageParser{
	/**
	 * WinParse parse txt file and save data to messageW array list
	 * 
	 */


	public void structurist(String line) {
		
		Pattern nameP = Pattern.compile("(\\[)((?:\\D|\\d)+)(\\])(\\s\\[(.*)\\])((\\s)(.*))");
		Matcher nameM = nameP.matcher(line);
		
		
		if(nameM.find()) {
			String patternName = nameM.group();
			int first = nameM.start(2);
			int last = nameM.end(2);
			pName = patternName.substring(first, last);
			
		
			String mss = nameM.group();
			int firstM = nameM.start(8);
			int lastM = nameM.end(8);
			wMessage = mss.substring(firstM, lastM);
			wMessage.replace("\n", " ");
			
			String day = nameM.group();
			int firstD = nameM.start(5);
			int lastD = nameM.end(5);
			fullDate=day.substring(firstD, lastD);
			
		}
		
		
		String fullMessage = "\""+pName+"\""+", "+"\""+cutTime(fullDate)+"\""+", "+"\""+wMessage+"\"";
		if(!messageW.contains(fullMessage))	
			messageW.add(fullMessage);
	}
	/**
	 * If the date format is correctly aligned in the sentence, add it.
	 */
	public String sortingWithTime(String line) {
		Pattern nameP = Pattern.compile("(\\[)((?:\\D|\\d)+)(\\])(\\s\\[(.*(?:\\d{1}|\\d{2}):\\d{2}).*\\])((\\s)(.*))");
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
	
	private String cutTime(String r) {
		Pattern forTimeK = Pattern.compile("(\\D+)(\\s)(\\d+)(:)(\\d+)");
		Pattern forTimeE = Pattern.compile("(\\d+)(:)(\\d+)(\\s)(\\D+)");
		Matcher dateM1 = forTimeK.matcher(r);
		Matcher dateM2 = forTimeE.matcher(r);
		int currentHourK = 0;
		int currentHourE = 0;
		String fullTime2=null;
		String fullTime1=null;
		String fullTime3=null;
		String fullTime4=null;
		
		if(dateM1.find()) {
			String ap = dateM1.group(1);
			if(ap.equals("오전")) {
				currentHourK = Integer.parseInt(dateM1.group(3));
				if(Integer.parseInt(dateM1.group(3))<10) {
					fullTime1 = "0"+currentHourK+":"+dateM1.group(5);
					return fullTime1;
				}
				else return currentHourK+":"+dateM1.group(5);
				
			}
			else if(ap.equals("오후")) {
				currentHourK = Integer.parseInt(dateM1.group(3))+12;
				if(currentHourK == 24) return "12"+":"+dateM1.group(5);
				else {
					fullTime2 = currentHourK+":"+dateM1.group(5);
					return fullTime2;
				}
			}
		}

		else if(dateM2.find()) {	
			String ap = dateM2.group(5);
			if(ap.equals("AM")) {
				currentHourE = Integer.parseInt(dateM2.group(1));
				if(Integer.parseInt(dateM2.group(1))<10) {
					fullTime3 = "0"+currentHourE+":"+dateM2.group(3);
					return fullTime3;
				}
				else if(Integer.parseInt(dateM2.group(1))>=10&&Integer.parseInt(dateM2.group(1))<12) 
					return currentHourE+":"+dateM2.group(3);
				else if(Integer.parseInt(dateM2.group(1))==12)
					return "00"+":"+dateM2.group(3);
			}
			else if(ap.equals("PM")){
				currentHourE = 12+Integer.parseInt(dateM2.group(1));
				if(currentHourE == 24) return "12"+":"+dateM2.group(3);
				else {
					fullTime4 = currentHourE+":"+dateM2.group(3);
					return fullTime4;
				}
			}
				
		}
		return null;
	}
}