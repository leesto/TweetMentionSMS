/**
 * 
 */
package com.leestone.twittermention;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class file is called by the scheduled cron.
 * @author Lee Stone
 * @created 23rd December 2009
 * 
 * @mapped_name Cron
 * @mapped_url /runcron
 *
 */
@SuppressWarnings("serial")
public class CronServlet extends HttpServlet {


	/**
	 * The Cron calls this which then starts off the process of any new @ mentions on twitter.<br/>
	 * The cron itself has security enabled so only the application or any administrators
	 * of the AppEnginge application can access the url.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		if(Debug.CRON){
			Debug.getLog().info("Cron has been called");
		}
		
		//When called it calls the checkForUpdates function which then handles everything else.
		//This is done if process == true (i.e Process is the master switch)
		if(Variables.PROCESS){
			TwittermentionServlet.checkForUpdates();
		}
	}

}
