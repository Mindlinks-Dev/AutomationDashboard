/**
 * @author: Basappa Hunsikatti
 * @Created Date :15/12/2015
 * @Updated Date :
 * @Comments:This automation class add tickets and modify reservation
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.ViewEditTicketsPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ViewEditTicketsTest 
{
	private static Logger log = Logger.getLogger(ViewEditTicketsTest.class);
	public WebDriver driver;
	 
	@Test 
	public void viewEditTicketsTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		ViewEditTicketsPage ticket = new ViewEditTicketsPage();
		ticket.AdminLogin(driver);
		ticket.viewEditTicketsInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.VIEW_EDIT_TICKETS)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
