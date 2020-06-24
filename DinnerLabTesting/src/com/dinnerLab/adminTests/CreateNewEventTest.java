/**
 * @author: Basappa Hunsikatti
 * @Created Date :09/11/2015
 * @Updated Date :
 * @Comments:This automation class create new event.
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.adminPages.Helpers.CreateNewEventPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class CreateNewEventTest 
{
	private static Logger log = Logger.getLogger(CreateNewEventTest.class);
	public WebDriver driver;
	 
	@Test 
	public void CreateNewEventTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		CreateNewEventPage newEvent = new CreateNewEventPage();
		newEvent.AdminLogin(driver);
		newEvent.createNewEventInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.CREATE_NEW_EVENT)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
