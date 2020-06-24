/**
 * @author: Basappa Hunsikatti
 * @Created Date :17/12/2015
 * @Updated Date :
 * @Comments:This automation class will change the Home City of a user by a Super Admin.
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.ChangeHomeCityPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ChangeHomeCityTest 
{
	private static Logger log = Logger.getLogger(ChangeHomeCityTest.class);
	public WebDriver driver;
	 
	@Test 
	public void changeHomeCityTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		ChangeHomeCityPage changeUser = new ChangeHomeCityPage();
		changeUser.AdminLogin(driver);
		changeUser.changeHomeCityInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.CHANGE_HOME_CITY)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
