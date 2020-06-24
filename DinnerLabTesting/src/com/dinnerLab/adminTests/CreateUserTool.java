package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.adminPages.Helpers.CreateUserToolPage;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class CreateUserTool 
{
	private static Logger log = Logger.getLogger(CreateUserTool.class);
	public WebDriver driver;
	 
	@Test 
	public void CreateUserToolPage() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		CreateUserToolPage newUser = new CreateUserToolPage();
		newUser.AdminLogin(driver);
		newUser.getFerrariDbConnection();
		newUser.loadUser(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.CREATE_USER_TOOL)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
