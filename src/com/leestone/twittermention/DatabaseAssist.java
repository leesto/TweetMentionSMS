/**
 * 
 */
package com.leestone.twittermention;

import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

/**
 * The DatabaseAssist Class contains all the functions used to store items in the datastore, 
 * then read from it.
 * @author Lee Stone
 * @created 24th December 2009
 * @updated 24th December 2009
 *
 */
public class DatabaseAssist {
	
	/**
	 * Looks in the datastore for the ID of the last received mention.<br/>
	 * If no ID is found, it is the first time the program has been run, so it creates
	 * a brand new entry using a manually entered Status ID and continues from there.
	 * @return Long the ID of the last mention that was received and processed
	 */
	@SuppressWarnings("unchecked")
	public static Long getLastMentionId(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Query query = pm.newQuery(LastMention.class, "myId == " + Variables.STORAGE_ID);
			List<LastMention> lastMention = (List<LastMention>) query.execute();
			if(Debug.LOGGING){
				Debug.getLog().info("The size of the results list is: " + lastMention.size());
			}
			
			if (lastMention.size() == 0){
				//This is the first time the program has run, need to create initial entry
				createStoredItem();
				return Long.valueOf("-1");	//recursive. Should now have an entry.
			}else{
				return lastMention.get(0).getStatusId();
			}
			
		}catch (Exception e){
			if(Debug.LOGGING){
				Debug.getLog().severe("getLastMentionId() Exception: " + e.getMessage());
			}
			return null;
		}finally{
			pm.close();
		}
	}
	
	/**
	 * Stores the ID of the last mention received. <br/>
	 * This actually performs an update rather than a new insert.
	 * @param statusId
	 */
	@SuppressWarnings("unchecked")
	public static void storeLastMentionId(Long statusId){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.currentTransaction().begin();
			Query query = pm.newQuery(LastMention.class, "myId == " + Variables.STORAGE_ID);
			List<LastMention> lastMention = (List<LastMention>) query.execute();
			
			//Done like this even though there should only be one entry
			for(LastMention lm : lastMention){
				lm.setStatusId(statusId);
				lm.setDateProcessed(new Date());
			}
			pm.currentTransaction().commit();	//Update the database record
			
		}catch (Exception e){
			if(Debug.LOGGING){
				Debug.getLog().severe("storeLastMentionId() Exception: " + e.getMessage());
			}
			
		}finally{
			pm.close();
		}
	}
	
	/**
	 * Creates the initial stored item using all the defaults.
	 */
	public static void createStoredItem(){
		LastMention lastMention = new LastMention(
				Variables.STORAGE_ID, TwitterAssist.getDefaultStatusId(), new Date());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.makePersistent(lastMention);
			
		}catch (Exception e){
			if(Debug.LOGGING){
				Debug.getLog().severe("createStoredItem() Exception: " + e.getMessage());
			}
		} finally {
			pm.close();
		}
	}

}
