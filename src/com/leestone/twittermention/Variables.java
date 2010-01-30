package com.leestone.twittermention;

/**
 * This class holds all the variables used throughout the program
 * in one easy to update location.<br/>
* <b>Important:</b> You must set all the relevant variables in here first.
 * @author Lee Stone
 * @created 23rd December 2009
 * @updated 23rd December 2009
 *
 */
public class Variables {
	
	/** The whole system only runs if process is set to true **/
	public static final boolean PROCESS = true;
	
	/** Twitter name of the person this program is for 
	 *  @required Fill in your Twitter name **/
	//TODO Fill in your Twitter name
	public static final String TWITTER_NAME = "";
	
	/** The ID used to load the Status ID from the datastore **/
	public static final int STORAGE_ID = 1234;
	
	/** Twitter OAuth Consumer Key  
	 *  @required Fill in your Twitter Application Consumer Key **/
	//TODO Fill in your Twitter Application Consumer Key
	public static final String TWITTER_CONSUMER_KEY = "";
	/** Twitter OAuth Consumer Secret  
	 *  @required Fill in your Twitter Application Consumer Secret **/
	//TODO Fill in your Twitter Application Consumer Secret
	public static final String TWITTER_CONSUMER_SECRET = "";
	/** Twitter OAuth Token  
	 *  @required Fill in your Twitter Token. See the readme instructions for generation **/
	//TODO Fill in your Twitter Token
	public static final String TWITTER_TOKEN = "";
	/** Twitter OAuth Token Secret  
	 *  @required Fill in your Twitter Token Secret. See the readme instructions for generation **/
	//TODO Fill in your Twitter Token Secret
	public static final String TWITTER_TOKEN_SECRET = "";
	
	/**
	 * Twitter Paging Mode:<br/>
	 * 1: Count - Gets the number of mentions defined by TWITTER_PAGING_COUNT <br/>
	 * 2: Since Last Id - Gets the mentions since the last recorded ID <br/>
	 */
	public static final int TWITTER_PAGING_MODE = 2;
	/** The number of mention updates to get when using Count for Paging **/
	public static final int TWITTER_PAGING_COUNT = 5;
	
	/**
	 * Send Mode:<br/>
	 * 1: SMS - This is the normal operation mode <br/>
	 * 2: Email - Used for testing to save SMS Credit <br/>
	 * 3: Both - Send Email & SMS <br/>
	 * 4: Do Nothing - This will still process Tweets
	 */
	public static final int SEND_MODE = 1;
	
	/**
	 * SMS Mode:<br/>
	 * 1: Betavine - Sends the SMS via the Betavine API <br/>
	 */
	public static final int SMS_MODE = 1;
	
	/** Phone number the SMS should be sent to  
	 *  @required Fill in your Mobile Phone number where you want to receive the SMS messages **/
	//TODO Fill in your Mobile Phone number where you want to receive the SMS messages
	public static final String RECIPIENT_NUM = "";
	
	/** The Betavine API Key. Required to send using the Betavine API  
	 *  @required Fill in your Betavine API Key **/
	//TODO Fill in your Betavine API Key
	public static final String BETAVINE_KEY = "";
	/** Betavine Developer Key. Allows you to get the same response as sending an SMS but without using credit  
	 *  @required Fill in your Betavine Developer API Key **/
	//TODO Fill in your Betavine Developer API Key
	public static final String BETAVINE_DEV_KEY = "";
	/** Betavine Developer Mode. If true, uses the developer Key **/
	public static final boolean BETAVINE_DEV_MODE = false;
	
	/*
	 * *******************************************************************************
	 * Emails
	 * *******************************************************************************
	 */
	/** Email address to send tweets to for testing  
	 *  @required Fill in your email address you want to receive the mentions **/
	//TODO Fill in your email address you want to receive the mention
	public static final String EMAIL_TO = "";
	/** The name of the person the email should be sent to  
	 *  @required Fill in your name **/
	//TODO Fill in your name
	public static final String EMAIL_TO_NAME = "";
	/** Email address for the email to be from.<br/> This must be registered to the appengine application.  
	 *  @required Fill in your Google AppEngine Email Address **/
	//TODO Fill in your Google AppEngine Email Address
	public static final String EMAIL_FROM = "";
	/** The name the email should appear from **/
	public static final String EMAIL_FROM_NAME = "Twitter Mention";
	/** The subject of the Email **/
	public static final String EMAIL_SUBJECT = "Testing Twitter Mention";

}
