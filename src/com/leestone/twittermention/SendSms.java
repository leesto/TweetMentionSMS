/**
 * 
 */
package com.leestone.twittermention;

import java.util.Vector;
import com.bv.api.*;
import com.bv.api.send.*;
import com.bv.api.response.*;

/**
 * SendSMS handles any mentions which need to be sent via an SMS Message
 * @author Lee Stone
 * @created 23rd December 2009
 *
 */
public class SendSms {
	
	/**
	 * Decides which method should be used to send the SMS.<br/>
	 * For now, only Betavine is implemented.<br/>
	 * The recipient number comes from the static variable RECIPIENT_NUM in the Variables class.
	 * @param message
	 */
	public static void sendSMS(String message){
		//Sends the message as a betavine SMS
		if (Variables.SMS_MODE == 1){
			sendBetavineSMS(message, Variables.RECIPIENT_NUM);
		}
	}
	
	/**
	 * Uses the Betavine API to send the SMS
	 * @param message The message to send
	 * @param recipient The phone number to send the SMS to
	 * 
	 * @exception ParamNotPresentException Specific to Betavine API
	 * @exception InvalidParameterException Specific to Betavine API
	 * @exception Exception All other exceptions
	 */
	@SuppressWarnings("unchecked")
	public static void sendBetavineSMS(String message, String recipient){
		
		/** The SMS Key to use for sending the message. Either real, or developer **/
		String smsKey = null;
		if(Variables.BETAVINE_DEV_MODE){
			smsKey = Variables.BETAVINE_DEV_KEY;
		}else{
			smsKey = Variables.BETAVINE_KEY;
		}
		
		/** The server URL on which the SMS is sent **/
		final String serverUrl = "http://www.betavine.net";
		
		Transport transport = Transport.getTransport(serverUrl, smsKey);
		
		SendSMS sms = new com.bv.api.send.SendSMS(message, recipient);

		try {
			SendSMSResponse response = (SendSMSResponse) transport.execute(sms);
			if(Debug.SMS_INFO){
				Debug.getLog().info("API Version: " + response.getVersion());
				Debug.getLog().info("Success? " + response.isSuccess());
			}
			if(response.isSuccess()) {
				if(Debug.SMS_INFO){
					Debug.getLog().info("Transaction ID:" + response.getTransactionId());
					Vector ids =  response.getMessageIds();
					for(int i = 0; i < ids.size(); i++) {
						Debug.getLog().info("Message ID: " +  ids.get(i));
					}
				}
			} else {
				if(Debug.LOGGING){
					Debug.getLog().severe("Error: " + response.getError().getCode() + " " + response.getError().getMessage());
				}
			}
			
		} catch (ParamNotPresentException pnpe) {
			if(Debug.LOGGING){
				Vector<String> v = pnpe.getParams();
				for(String param : v) {
					Debug.getLog().severe("SendSms.SendBetavineSMS(..) param not present exception. Parameter: " + param);
				}
			}
			
		} catch (InvalidParameterException ipe) {
			if(Debug.LOGGING){
				Debug.getLog().severe("SendSms.SendBetavineSMS(..) invalid paramter exception " + ipe.getParameter());
			}
			
		} catch (Exception e) {
			if(Debug.LOGGING){
				Debug.getLog().severe("SendSms.SendBetavineSMS(..) Exception: " + e.getMessage());
			}
		}
	}

}
