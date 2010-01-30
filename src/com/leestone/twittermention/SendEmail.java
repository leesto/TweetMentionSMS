/**
 * 
 */
package com.leestone.twittermention;

/** These imports are required for handling sending the email **/
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * This class contains the functions for sending any email messages
 * @author Lee Stone
 * @created 23rd December 2009
 * @updated 23rd December 2009
 */
public class SendEmail {
	
	/**
	 * SendMessage will send an email message based upon the value of the given parameters
	 * 
	 * @param to Email Address to send the email to
	 * @param toName The name of the person the email is being sent to
	 * @param from Email Address the email is sent from (Has to be registered for the AppEengine application)
	 * @param fromName The name the email appears from
	 * @param subject The subject of the email
	 * @param message The content of the actual email message
	 * 
	 * @exception AddressException
	 */
	public static void sendMessage(String to, String toName, String from, String fromName, 
			String subject, String message){
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from, fromName));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, toName));
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		
			
		} catch (AddressException e){
			if(Debug.LOGGING){
				Debug.getLog().severe("SendEmail.SendMessage(..) AddressException: " + e.getMessage());
			}
			
		} catch (MessagingException e){
			if(Debug.LOGGING){
				Debug.getLog().severe("SendEmail.SendMessage(..) MessagingException: " + e.getMessage());
			}
			
		} catch (UnsupportedEncodingException e) {
			if(Debug.LOGGING){
				Debug.getLog().severe("SendEmail.SendMessage(..) UnsupportedEncodingException: " + e.getMessage());
			}
		}
	}
	
	/**
	 * This will send a test email message based upon the values in the Variables class.<br/>
	 * This is only used when testing the application is working before implementing tweets.
	 */
	public static void sendTestMessage(){
		String message = "This is a test email which has been sent from the Twitter Mention Application";
		sendMessage(
				Variables.EMAIL_TO,
				Variables.EMAIL_TO_NAME,
				Variables.EMAIL_FROM,
				Variables.EMAIL_FROM_NAME,
				Variables.EMAIL_SUBJECT,
				message);
	}
	
	/**
	 * This will send the mention as an email message.<br/>
	 * The email to and subject is based upon the values set in the Variables class.
	 * @param message Formed of the tweet author and the tweet.
	 */
	public static void sendMentionEmail(String message){
		//Sends the mention as an email message
		sendMessage(
				Variables.EMAIL_TO,
				Variables.EMAIL_TO_NAME,
				Variables.EMAIL_FROM,
				Variables.EMAIL_FROM_NAME,
				Variables.EMAIL_SUBJECT,
				message);
	}

}
