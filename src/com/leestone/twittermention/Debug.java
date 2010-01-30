/**
 * 
 */
package com.leestone.twittermention;

import java.util.logging.Logger;

/**
 * Anything associated with debugging is handled in here, such as logs.
 * @author Lee Stone
 * @created 23rd December 2009
 * @updated 23rd December 2009
 *
 */
public class Debug {

	/** Turn logging on or off **/
	public static final boolean LOGGING = true;
	
	/** Turn logging for Cron on or off **/
	public static final boolean CRON = true;
	
	/** If wanting successful SMS Info **/
	public static final boolean SMS_INFO = false;
	
	/** This is the log used within AppEngine **/
	private static final Logger log = Logger.getLogger("leestone.twittermention");

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}
}
