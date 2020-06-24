/**
 * @author: Anitha.M.
 * @Created Date :10/12/2015
 * @Updated Date :
 * @Comments:This automation class will refund membership amount
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.adminPages.Helpers.RefundMembershipPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class RefundMembershipTest 
{
	private static Logger log = Logger.getLogger(RefundMembershipTest.class);
	public WebDriver driver;
	 
	@Test 
	public void changeUserTypeTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		RefundMembershipPage refund = new RefundMembershipPage();
		refund.AdminLogin(driver);
		refund.refundMembershipInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.REFUND_MEMBERSHIP)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
