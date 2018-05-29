package edu.handong.csee.java.countingChat;

import java.util.ArrayList;

/**
 *this class is superclass of Macparser and Windowsparser
 */
public class MessageParser {
	
	protected String pName="";
	protected String wMessage="";
	protected String fullDate="";
	protected String fullTime="";
	protected String fullString="";
	
	/**
	 * messageM arraylist to store csv data
	 */
	static ArrayList<String> messageM = new ArrayList<String>();
	
	/**
	 * messageW arraylist to store txt data
	 */
	static ArrayList<String> messageW = new ArrayList<String>();
	
}