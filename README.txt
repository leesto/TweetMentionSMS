
*************************************************************************************************
*************************                                               *************************
*************************                TweetMentionSMS                *************************
*************************                                               *************************
*************************************************************************************************


Author:
    Lee Stone
Date Created:
    23rd December 2009
Last Updated:
    30th January 2010

README Info:
    Thank you for downloading Twitter Mention SMS.
    This project is designed to be deployed to Google App Engine, where it will run via a Cron Job.

    Configuration
    To configure this for your specific twitter user, your email account, your Betavine API details and mobile phone number, you need to fill in the following in the Variables.java file:
    (If you import this package into Eclipse, you will see the TODO entries at the right location)

       1. TWITTER_NAME - set to your Twitter Name.
       2. TWITTER_CONSUMER_KEY - The consumer key for your Twitter Application. See instructions later in README
       3. TWITTER_CONSUMER_SECRET - The consumer secret for your Twitter Application. See instructions later in README
       4. TWITTER_TOKEN - The oAuth Token for your twitter user. See instructions later in README
       5. TWITTER_TOKEN_SECRET - The oAuth Token Secret for your twitter user. See instructions later in README
       6. RECIPIENT_NUM - Your mobile phone number.
       7. BETAVINE_KEY - Your Betavine API key from http://www.betavine.net.
       8. BETAVINE_DEV_KEY - Your Betavine Developer API key from http://www.betavine.net.
       9. EMAIL_TO - The Email address at which you want to receive the mentions
      10. EMAIL_TO _NAME - Your name.
      11. EMAIL_FROM - Google AppEngine Email Address. The address must be linked to the Application account for the email to send.


    If you decide to change the package name, you will also need to make changes to the following:

       1. In the Debug class, change the logger name
       2. In war/WEB-INF/web.xml change the class for TwitterMentionServlet
       3. In war/WEB-INF/web.xml change the class for Cron


    Scheduling Updates
    If you wish to change how frequently TweetMentionSMS checks for new mentions, you will need to change how frequently the cron job runs.
    This can be done by going to war/WEB-INF/cron.xml and changing the value of the schedule.
    Note: Google App Engine often fails to run some of the scheduled crons, so check the logs to see if the cron was attempted to be run.

    Operation Advice
    When deploying and running this, we recommend the following:

        * When first deploying and testing, use the email option rather than SMS. This will save your SMS credits. The setting to do this can be found in Variables.java
        * The first time the cron runs, you may have problems with it creating the database entry. You may be able to manually correct the problem from the datastore viewer. Please email me with details about this bug and I will try to develop a fix.
        * Running every 5 minutes, this application easily fits in the App Engine quotas (as of 10th January 2010)
        * There is a problem with cron and java on Google App Engine, so expect some of your scheduled runs not to happen. The only problems this causes is a possible delay to receiving some of your mentions.


    A Note on SMS
    We use the Betavine API, details of which can be found at http://www.betavine.net, for sending SMS.
    There are alternative services. If you do program the code to use one of these, we recommend you set it up as a separate option, chosen by changing the relevant value in Variables.java.
    Please think about pushing this code back to the project so these options become easily available to other people.

    Twitter Usage
    This application use oAuth for verifying the user. You will need to do the following:

       1. Generate a Consumer Key and Secret
          Go to http://twitter.com/oauth_clients and create a new client.
          This will give you your consumer Key and Secret.
       2. Generate you user's Twitter token and token secret This is a fairly manual process. In the included twitter4j-2.1.0 jar go to twitter4j.examples.
          Run OAuthUpdate with the following arguments:
          -Dtwitter4j.oauth.consumerKey=xxxxx -Dtwitter4j.oauth.consumerSecret=xxxxx "Hello"
          Replacing the consumerKey and consumerSecret to your values.
          You will be prompted to go to a twitter page, authorise the application and be given a pin.
          Enter the pin at the command line and you will get your Twitter Token and Token Secret.
          The actual update will probably fail, but this is not a problem.


    With Thanks To

        * Betavine - http://www.betavine.net
        * Twitter4j - http://twitter4j.org/en/index.html or http://groups.google.com/group/twitter4j
