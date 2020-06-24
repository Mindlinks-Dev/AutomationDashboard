/**
 * @author: Basappa Hunsikatti
 * @Created Date :16/10/2015
 * @Updated Date :
 * @Comments This automation class will serve the Membership Extend.
 */
package com.dinnerLab.userTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.userPages.Helpers.MembershipExtendPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class MembershipExtendTest 
{
	private static Logger log = Logger.getLogger(MembershipExtendTest.class);
	public WebDriver driver;
	 
	@Test 
	public void membershipExtend() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		MembershipExtendPage memberExtend = new MembershipExtendPage();
		//Calling Membership Extend URL method
		memberExtend.membershipExtendURLActions(driver);
		//Calling Ferrari DB Connection method
		//memberExtend.getFerrariDbConnection();
		//Calling Membership Extend Actions method
		memberExtend.membershipExtendActions(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.MEMBERSHIP_EXTEND)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
