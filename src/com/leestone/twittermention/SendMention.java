/**
 * 
 */
package com.leestone.twittermention;

/**
 * Class file handles via what method any mentions should be sent.
 * @author Lee Stone
 * @created 23rd December 2009
 *
 */
public class SendMention {
	
	/**
	 * Handles sorting out how to send the tweet. This is based upon the value of
	 * SEND_MODE found in the Variables class.
	 * @param tweet The tweet that was sent
	 * @param from The author of the tweet
	 */
	public static void sendMention(String tweet, String from){
		//Create the message for either SMS or Email
		String message = from + ": " + tweet;
		//Prefix for email
		String emailPrefix = "You were mentioned in this Twitter message: \n\n";
		
		if (Variables.SEND_MODE == 1){
			SendSms.sendSMS(message);
			//Send as SMS Only
		}else if (Variables.SEND_MODE == 2){
			//Send as Email Only
			SendEmail.sendMentionEmail(emailPrefix + message);
		}else if (Variables.SEND_MODE == 3){
			//Send as SMS & Email
			SendSms.sendSMS(message);
			SendEmail.sendMentionEmail(emailPrefix + message);
		}else if (Variables.SEND_MODE == 4){
			//Do Nothing
		}
	}

}
