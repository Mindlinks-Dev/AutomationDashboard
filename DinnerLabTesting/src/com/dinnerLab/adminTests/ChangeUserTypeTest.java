/**
 * @author: Basappa Hunsikatti
 * @Created Date :02/12/2015
 * @Updated Date :
 * @Comments:This automation class will change the User Type of a user by a Superdmin.
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.ChangeUserTypePage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class ChangeUserTypeTest 
{
	private static Logger log = Logger.getLogger(ChangeUserTypeTest.class);
	public WebDriver driver;
	 
	@Test 
	public void changeUserTypeTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		ChangeUserTypePage changeUser = new ChangeUserTypePage();
		changeUser.AdminLogin(driver);
		changeUser.changeUserTypeInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.CHANGE_USER_TYPE)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
