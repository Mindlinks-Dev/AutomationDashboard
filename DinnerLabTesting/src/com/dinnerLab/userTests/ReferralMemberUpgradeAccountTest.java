/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/09/2015
 * @Updated Date :
 * @Comments:This automation class will serve the upgrade account for Referral Member. 
 * When you used code on PAYMENT PAGE when upgrading your account, you have $20 in credit added on your account automatically.
*/
package com.dinnerLab.userTests;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.FreeMemberSignUpPage;
import com.dinnerLab.userPages.Helpers.ReferralMemberUpgradeAccountPage;
import com.dinnerLab.userPages.Helpers.ReferralThroughInviteLinkPage;
import com.dinnerLab.util.DriverManager;
import com.dinnerLab.util.Manager;
import com.pack.ScriptHandler;

public class ReferralMemberUpgradeAccountTest
{
	private static Logger log = Logger.getLogger(ReferralMemberUpgradeAccountTest.class);
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
	 public void  ReferralMemberUpgradeAccountTest() throws Exception
	 {
	   	log.info("-------Opening the browser--------");
	   	DriverManager loadBrowser = new DriverManager();
	   	//Calling WebDriver to launch Browser.
	   	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		//Calling Free Member Sign Up Page Method.
		ReferralMemberUpgradeAccountPage upgrade = new ReferralMemberUpgradeAccountPage();
		upgrade.referralMemberUpgradeAccountInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.REFERRAL_THROUGH_UPGRADE_ACCOUNT)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}

