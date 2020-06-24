/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/09/2015
 * @Updated Date :
 * @Comments:This automation class will serve the Subscriber Sign Up  using a friend's referral link. 
 * Once you upgrade to a member account, you'll both get $20 credit in your account..
 */
package com.dinnerLab.userTests;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.FreeMemberSignUpPage;
import com.dinnerLab.userPages.Helpers.ReferralThroughInviteLinkPage;
import com.dinnerLab.util.DriverManager;
import com.dinnerLab.util.Manager;
import com.pack.ScriptHandler;

public class ReferralThroughInviteLinkTest
{
	private static Logger log = Logger.getLogger(ReferralThroughInviteLinkTest.class);
	public WebDriver driver;
	//OutputReport report = new OutputReport();
	
	/**
	 * Test Case for signing up as a member depending on the test data passed
	 * Registering as a member in to the application
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 * 
	 */
     @Test
	 public void  referralThroughInviteLinkSignUpTest() throws Exception
	 {
	   	log.info("-------Opening the browser--------");
	   	DriverManager loadBrowser = new DriverManager();
	   	//Calling WebDriver to launch Browser.
	   	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		//Calling Free Member Sign Up Page Method.
		ReferralThroughInviteLinkPage signUp = new ReferralThroughInviteLinkPage();
		signUp.subscriberSignUpInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.REFERRAL_THROUGH_INVITE_LINK)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}

