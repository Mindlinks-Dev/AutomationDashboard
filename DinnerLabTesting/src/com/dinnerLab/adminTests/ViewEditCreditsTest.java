/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/12/2015
 * @Updated Date :
 * @Comments:This automation class Add/Substract credits.
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.ViewEditCreditsPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ViewEditCreditsTest 
{
	private static Logger log = Logger.getLogger(ViewEditCreditsTest.class);
	public WebDriver driver;
	 
	@Test 
	public void viewEditCreditsTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		ViewEditCreditsPage credits = new ViewEditCreditsPage();
		credits.AdminLogin(driver);
		credits.viewEditCreditsInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.VIEW_EDIT_CREDITS)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
