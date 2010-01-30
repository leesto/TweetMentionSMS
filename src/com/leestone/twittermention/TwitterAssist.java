/**
 * 
 */
package com.leestone.twittermention;

import java.util.Collections;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.http.AccessToken;

/**
 * This class contains all the functions related with getting and handling
 * the Twitter information
 * @author Lee Stone
 * @created 23rd December 2009
 * @updated 24th December 2009
 *
 */
public class TwitterAssist {
	
	/**
	 * Creates an AccessToken for the application to be used
	 * @return AccessToken from the OAuth of the Applications
	 */
	public static AccessToken createAccessToken(){
		return new AccessToken(Variables.TWITTER_TOKEN, Variables.TWITTER_TOKEN_SECRET);
	}
	
	/**
	 * createPaging creates the paging Item as determined by TWITTER_PAGING_MODE
	 * @return Paging with the defined limits
	 */
	public static Paging createPaging(){
		Paging paging = new Paging();
		if(Variables.TWITTER_PAGING_MODE == 1){
			paging.setCount(Variables.TWITTER_PAGING_COUNT);
		}else if(Variables.TWITTER_PAGING_MODE == 2){
			Long id = DatabaseAssist.getLastMentionId();
				if(id == Long.valueOf("-1")){
					//This will end up sending you the last 5 mentions.
					//Recommended to use email at this stage
					paging.setCount(Variables.TWITTER_PAGING_COUNT);
				}else{
					paging.setSinceId(id);
				}
		}
		
		return paging;
	}
	
	/**
	 * getDefaultStatusId is used to get a status ID to act as a starting point. <br/>
	 * It is only called when there isn't an entry already in the datastore. <br/><br/>
	 * Based upon the TWITTER_PAGING_COUNT value, it goes to the oldest update
	 * then returns that statusID to be the first one to be used.
	 * @return Long The status ID
	 */
	public static Long getDefaultStatusId(){
		Paging paging = new Paging();
		paging.setCount(Variables.TWITTER_PAGING_COUNT);
		
		Twitter twitter = new Twitter();
		//Set up all the OAuth related items
	    twitter.setOAuthConsumer(Variables.TWITTER_CONSUMER_KEY, Variables.TWITTER_CONSUMER_SECRET);
	    AccessToken accessToken = TwitterAssist.createAccessToken();
	    twitter.setOAuthAccessToken(accessToken);
	    
	    //Get the mentions
	    try{
	    	List<Status> mentions = twitter.getMentions(paging);
	    	
	    	//Get the oldest and start from there
	    	Collections.reverse(mentions);
	    	return mentions.get(0).getId();
	    	
	    }catch (TwitterException te){
	    	if(Debug.LOGGING){
				Debug.getLog().severe("getDefaultStatusId() TwitterException: " + te.getMessage());
			}
	    	return null;
	    }
	}

}
