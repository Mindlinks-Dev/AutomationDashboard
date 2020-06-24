/**
 * @author: Basappa Hunsikatti
 * @Created Date :14/12/2015
 * @Updated Date :
 * @Comments This automation class will server For each friend that purchases a 
 * Select Membership off of your link, you’ll both receive $20 in credit.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.ReferFriendsPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ReferFriendsTest
{
	private static Logger log = Logger.getLogger(ReferFriendsTest.class);
	public WebDriver driver;
	/**
	 * Test Case for submitting the Login page depending on the test data passed
	 * valid email and password should allow the user to
	 * login in to the application.
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 */	 
	@Test
	 public void referFriendsTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		ReferFriendsPage refer = new ReferFriendsPage();
		refer.referFriendsInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.REFER_FRIENDS)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
