/**
 * @author: Basappa Hunsikatti
 * @Created Date :31/12/2015
 * @Updated Date :
 * @Comments:This automation class will Refund Gift Credits and Membership Amount.
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.GiftOverviewPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class GiftOverviewTest 
{
	private static Logger log = Logger.getLogger(GiftOverviewTest.class);
	public WebDriver driver;
	 
	@Test 
	public void giftOverviewTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		GiftOverviewPage gift = new GiftOverviewPage();
		gift.AdminLogin(driver);
		gift.giftOverviewInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.GIFT_OVERVIEW)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			//driver.close();
			//Quit all the opened browsers
			//driver.quit();
		}
	}
}
