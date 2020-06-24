/**
 * @author: Basappa Hunsikatti
 * @Created Date :09/12/2015
 * @Updated Date :
 * @Comments:This automation class add user to waitlist
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.adminPages.Helpers.AddUserToWaitlistPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class AddUserToWaitlistTest 
{
	private static Logger log = Logger.getLogger(AddUserToWaitlistTest.class);
	public WebDriver driver;
	 
	@Test 
	public void addUserToWaitlistTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		AddUserToWaitlistPage waitList = new AddUserToWaitlistPage();
		waitList.AdminLogin(driver);
		waitList.addUserToWaitlistInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.ADD_USER_TO_WAITLIST)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
