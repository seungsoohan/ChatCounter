package edu.handong.csee.java.countingChat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Database material I created
 * Various information is managed by array list and hash map.
 *
 */
public class MessagesData {
	/**
	 * rawMessage saves all message
	 */
	ArrayList<String> rawMessage =new ArrayList<String>();
	
	/**
	 *  kakao_id Arraylist save kakao_id
	 */
	ArrayList<String> id = new ArrayList<String>();
	
	/**
	 * whole_id Arraylist save kakao_id + rawMessage
	 */
	ArrayList<String> whole_id = new ArrayList<String>();
	
	/**
	 * HaspMap
	 */
	HashMap<String, Integer> counter = new HashMap<String, Integer>();
	
	
	/**
	 * sorting result method
	 */
	public String[] countName() {
		saveMessageToOnePoint();
		int count=0;
		
		String[] name = new String[id.size()];
		id.toArray(name);
		
		for(int i = 0; i < name.length; i++) {
			count = 0;
			for(String k:whole_id) {
				if(k.equals(name[i]))
					count++;
			}
			
			counter.put(name[i],count);
		}
		
		for(int j = 0; j < name.length; j++) {
			String temp = "";
			for(int i=j ; i < name.length-1; i++) {
				if(counter.get(name[j]).compareTo(counter.get(name[i+1]))<=0) {
					temp = name[j];
					name[j]=name[i+1];
					name[i+1]=temp;
				}
			}	
		}
		return name;
	}
	
	private void saveMessageToOnePoint() {
	
		String changeException="";
		String changePhoto="";
		
		for(String w:MessageParser.messageW) {
			changePhoto=w.replace("사진", "Photo");
			
			if(!rawMessage.contains(changePhoto))
			rawMessage.add(changePhoto);
			
		}
		
		for(String m:MessageParser.messageM) {
			changePhoto=m.replace("사진", "Photo");
			
			if(changePhoto.contains("\"\"")) {
				changeException=m.replace("\"\"", "\"");
				if(!rawMessage.contains(changeException))
					rawMessage.add(changeException);
			}
			else {
				if(!rawMessage.contains(m));
					rawMessage.add(m);
			}
			
		}

		for(String outx:rawMessage) {
			naming(outx);
			totalName(outx);
		}
	}
	
	private void naming(String line) {
		Pattern nameP = Pattern.compile("(\")(.*)(\")(,\\s)(\")([0-2][0-9]:[0-5][0-9])(\",\\s)(\")(.*)(\")");
		Matcher nameM = nameP.matcher(line);
		String justName="";
		
		if(nameM.find()) {
			String patternName = nameM.group();
			int first = nameM.start(2);
			int last = nameM.end(2);
			justName = patternName.substring(first, last);
			
			if(!id.contains(justName))
				id.add(justName);
		}
	}
	
	private void totalName(String line) {
		Pattern nameP = Pattern.compile("(\")(.*)(\")(,\\s)(\")([0-2][0-9]:[0-5][0-9])(\",\\s)(\")(.*)(\")");
		Matcher nameM = nameP.matcher(line);
		String justName="";
		
		if(nameM.find()) {
			String patternName = nameM.group();
			int first = nameM.start(2);
			int last = nameM.end(2);
			justName = patternName.substring(first, last);
			
			whole_id.add(justName);
		}
	}
	
	/**
	 * return counter
	 * @return
	 */
	public HashMap<String,Integer> count(){
		return counter;
	}
}



