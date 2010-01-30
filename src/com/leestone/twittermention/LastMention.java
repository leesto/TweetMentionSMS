/**
 * 
 */
package com.leestone.twittermention;

import java.util.Date;

import javax.jdo.annotations.*;

/**
 * Last Mention creates the object that is actually stored  in the database to record the
 * last ID of the mentions received
 * @author Lee Stone
 * @created 24th December 2009
 * @updated 24th December 2009
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class LastMention {
	/** The ID to locate the stored Status ID **/
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	/** My ID to locate **/
	private int myId;
	
	/** The statusId of the last mention processed **/
	@Persistent
	private Long statusId;
	
	/** The Date this status was stored **/
	@Persistent
	private Date dateProcessed;

	/**
	 * @param myId
	 * @param statusId
	 * @param dateProcessed
	 */
	public LastMention(int myId, Long statusId, Date dateProcessed) {
		this.myId = myId;
		this.statusId = statusId;
		this.dateProcessed = dateProcessed;
	}

	/**
	 * @param myId
	 * @param statusId
	 */
	public LastMention(int myId, Long statusId) {
		this.myId = myId;
		this.statusId = statusId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the myId
	 */
	public int getMyId() {
		return myId;
	}

	/**
	 * @param myId the myId to set
	 */
	public void setMyId(int myId) {
		this.myId = myId;
	}

	/**
	 * @return the statusId
	 */
	public Long getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the dateProcessed
	 */
	public Date getDateProcessed() {
		return dateProcessed;
	}

	/**
	 * @param dateProcessed the dateProcessed to set
	 */
	public void setDateProcessed(Date dateProcessed) {
		this.dateProcessed = dateProcessed;
	}
	
	
	

}
