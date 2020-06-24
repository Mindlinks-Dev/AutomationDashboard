/**
 * @author: Basappa Hunsikatti
 * @Created Date : 07/06/2016
 * @Updated Date :
 * @Comments This automation class will create New Entity
 */

package com.tronixss.userTests;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.tronixss.userPages.Helpers.CreateNewEntityPage;
import com.tronixss.userPages.Helpers.UserLoginLogoutPage;


public class CreateNewEntityTest 
{
	private static Logger log = Logger.getLogger(UserLoginLogoutTest.class);
	public WebDriver driver;
	 
	@Test 
	public void createNewEntityTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
		driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		driver.manage().window().maximize();
		log.info("-------Loaded the Driver--------");
		
		UserLoginLogoutPage login = new UserLoginLogoutPage();
		login.loginInitialPage(driver);
		
		CreateNewEntityPage entity = new CreateNewEntityPage();
		entity.createNewEntityInitialPage(driver);
		
	}
}
