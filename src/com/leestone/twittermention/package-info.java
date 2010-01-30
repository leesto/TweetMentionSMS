/**
 * @author Lee Stone
 * @email code@leestone.co.uk
 * @created 23rd December 2009
 * @updated 30th January 2010
 * <br/><br/>
 * @readme
 * 
 * Thank you for downloading Twitter Mention SMS.
 * <br/>
 * This project is designed to be deployed to Google App Engine, where it will run via a Cron Job.
 * 
 * <br/><br/>
 * <b>Configuration</b><br/>
 * To configure this for your specific twitter user, your email account, your Betavine
 * API details and mobile phone number, you need to fill in the following in the Variables.java file:<br/>
 * (If you import this package into Eclipse, you will see the TODO entries at the right location)
 * <ol>
 * <li><b>TWITTER_NAME</b> - set to your Twitter Name.</li>
 * <li><b>TWITTER_CONSUMER_KEY</b> - The consumer key for your Twitter Application. See instructions later in README</li>
 * <li><b>TWITTER_CONSUMER_SECRET</b> - The consumer secret for your Twitter Application. See instructions later in README</li>
 * <li><b>TWITTER_TOKEN</b> - The oAuth Token for your twitter user. See instructions later in README</li>
 * <li><b>TWITTER_TOKEN_SECRET</b> - The oAuth Token Secret for your twitter user. See instructions later in README</li>
 * <li><b>RECIPIENT_NUM</b> - Your mobile phone number.</li>
 * <li><b>BETAVINE_KEY</b> - Your Betavine API key from http://www.betavine.net.</li>
 * <li><b>BETAVINE_DEV_KEY</b> - Your Betavine Developer API key from http://www.betavine.net.</li>
 * <li><b>EMAIL_TO </b> - The Email address at which you want to receive the mentions</li>
 * <li><b>EMAIL_TO _NAME</b> - Your name.</li>
 * <li><b>EMAIL_FROM</b> - Google AppEngine Email Address. The address must be linked to the Application account for the email to send.</li>
 * </ol>
 * <br/>
 * If you decide to change the package name, you will also need to make changes to the following:
 * <ol>
 * <li>In the Debug class, change the logger name</li>
 * <li>In war/WEB-INF/web.xml change the class for TwitterMentionServlet</li>
 * <li>In war/WEB-INF/web.xml change the class for Cron</li>
 * </ol>
 * 
 * <br/>
 * <b>Scheduling Updates</b><br/>
 * If you wish to change how frequently TweetMentionSMS checks for new mentions, you will need to change how
 * frequently the cron job runs.<br/>
 * This can be done by going to war/WEB-INF/cron.xml and changing the value of the schedule.<br/>
 * <i>Note: Google App Engine often fails to run some of the scheduled crons, so check the logs to
 * see if the cron was attempted to be run.</i>
 * 
 * <br/><br/>
 * <b>Operation Advice</b><br/>
 * When deploying and running this, we recommend the following:
 * <ul>
 * <li>When first deploying and testing, use the email option rather than SMS. This will save your SMS credits.
 * The setting to do this can be found in Variables.java</li>
 * <li>The first time the cron runs, you may have problems with it creating the database entry. You may be 
 * able to manually correct the problem from the datastore viewer. Please email me with details about this bug
 * and I will try to develop a fix.</li>
 * <li>Running every 5 minutes, this application easily fits in the App Engine quotas 
 * (as of 10th January 2010)</li>
 * <li>There is a problem with cron and java on Google App Engine, so expect some of your scheduled runs 
 * not to happen. The only problems this causes is a possible delay to receiving some of your mentions.</li>
 * </ul>
 * 
 * <br/>
 * <b>A Note on SMS</b><br/>
 * We use the Betavine API, details of which can be found at http://www.betavine.net, for sending SMS.<br/>
 * There are alternative services. If you do program the code to use one of these, we recommend you
 * set it up as a separate option, chosen by changing the relevant value in Variables.java.<br/>
 * Please think about pushing this code back to the project so these options become easily available 
 * to other people.
 * 
 * <br/><br/>
 * <b>Twitter Usage</b><br/>
 * This application use oAuth for verifying the user. You will need to do the following:
 * <ol>
 * <li>
 * <i>Generate a Consumer Key and Secret</i><br>
 * Go to http://twitter.com/oauth_clients and create a new client.<br/>
 * This will give you your consumer Key and Secret.
 * </li>
 * <li>
 * <i>Generate you user's Twitter token and token secret</i>
 * This is a fairly manual process. In the included twitter4j-2.1.0 jar go to twitter4j.examples.<br/>
 * Run OAuthUpdate with the following arguments:<br/>
 * -Dtwitter4j.oauth.consumerKey=xxxxx -Dtwitter4j.oauth.consumerSecret=xxxxx "Hello"
 * <br/>
 * Replacing the consumerKey and consumerSecret to your values.<br/>
 * You will be prompted to go to a twitter page, authorise the application and be given a pin.<br/>
 * Enter the pin at the command line and you will get your Twitter Token and Token Secret.<br/>
 * The actual update will probably fail, but this is not a problem.
 * </li>
 * </ol>
 * 
 * <br/>
 * <b>With Thanks To</b><br/>
 * <ul>
 * <li>Betavine - http://www.betavine.net</li>
 * <li>Twitter4j - http://twitter4j.org/en/index.html or http://groups.google.com/group/twitter4j</li>
 * </ul>
 */
package com.leestone.twittermention;

