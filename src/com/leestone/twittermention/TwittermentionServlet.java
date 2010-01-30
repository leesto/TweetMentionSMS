package com.leestone.twittermention;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.*;

import twitter4j.*;
import twitter4j.http.AccessToken;


/**
 * This is the main class from which the program is run.
 * 
 * Accessing through the mapped URL, won't achieve anything apart from printing 
 * a default method.<br/>
 * checkForUpdates() is called by the Cron and is what runs the program.<br/>
 * <b>Important:</b> You must set all the relevant variables in the Variables Class.
 * @author Lee Stone
 * @created 23rd December 2009
 * @updated 24th December 2009
 * @version 0.1
 * 
 * @mapped_name Twittermention
 * @mapped_url /twittermention
 *
 */
@SuppressWarnings("serial")
public class TwittermentionServlet extends HttpServlet {
	
	/**
	 * This is not actually required, but writes a response incase someone tries to get unauthorised access.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("I don't know how you got here, but this doesn't do anything.");
		
	}
	
	/**
	 * This is called by the cron function.<br/>
	 * All the processing of the tweets takes place from this function.
	 * 
	 * @exception TwitterException any exceptions related to the twitter method (usually service unavailable)
	 * @exception Exception All the other exceptions
	 */
	public static void checkForUpdates(){
		//Logging for development purposes
		if(Debug.CRON){
			Debug.getLog().info("Cron has been called and run checkForUpdates");
		}
		
		//Get Twitter Mentions
		try {
			Twitter twitter = new Twitter();
			//Set up all the OAuth related items
		    twitter.setOAuthConsumer(Variables.TWITTER_CONSUMER_KEY, Variables.TWITTER_CONSUMER_SECRET);
		    AccessToken accessToken = TwitterAssist.createAccessToken();
		    twitter.setOAuthAccessToken(accessToken);
		    
		    //Get the mentions
		    Paging paging = TwitterAssist.createPaging();
		    List<Status> mentions = twitter.getMentions(paging);
		    if(mentions !=null && mentions.size()!=0){
		    	Status lastStatusUpdate = mentions.get(0);	//Get the newest status to use later
		    	
			    Collections.reverse(mentions);	//We want to handle the oldest first
			    
			    //Take each mention in turn
			    for (Status status : mentions){
			    	//Find out relationship info to find out if notifications enabled.
			    	Relationship relationship = twitter.showFriendship(
			    			Variables.TWITTER_NAME, status.getUser().getScreenName());
			    	
			    	if(!relationship.isSourceNotificationsEnabled()){
			    		SendMention.sendMention(status.getText(), status.getUser().getScreenName());
			    	}
			    }
			    
			    //Now need to update the last ID we received
			    DatabaseAssist.storeLastMentionId(lastStatusUpdate.getId());
			    
		    }
		    
		    //If the result is null, we don't want it to do anything

		}catch (TwitterException te){
			if(Debug.LOGGING){
				Debug.getLog().severe("checkForUpdates() TwitterException: " + te.getMessage());
			}
		}catch (Exception e){
			if(Debug.LOGGING){
				Debug.getLog().severe("checkForUpdates() Exception: " + e.getMessage());
			}
		}
	}
}
