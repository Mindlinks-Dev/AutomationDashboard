package com.autoparts.userclass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.autoparts.userpage.CreateEditQuotationpage;
import com.autoparts.userpage.CreateOrderPage;
import com.autoparts.userpage.CreateQuotationPartsPage;
import com.autoparts.userpage.UserLoginLogoutPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;

public class CreateEditQuotationTest

{
	
	private static Logger log = Logger.getLogger(CreateEditQuotationTest.class);
    public WebDriver driver;

 @Test 
  public void createEditQuotationTest() throws Exception
 {
	log.info("-------Opening the browser--------");
	/*Drivermanager loadBrowser = new Drivermanager();
	loadBrowser.getDriverForBrowser(Testconstants.BROWSER_CHROME);
	//driver.manage().window().maximize();
	
	
	log.info("-------Loaded the Driver--------");
	
	UserLoginLogoutPage login = new UserLoginLogoutPage();
	login.loginInitialPage(driver);*/
	
	DriverManager Launch = new DriverManager();
	//Launch.getDriverForBrowser(TestConstants.BROWSER_CHROME);
	driver = new ChromeDriver();
	//System.setProperty("webdriver.chrome.driver" , "E://spoo project//Testing Tool Softwares//New Chrome driver//chromedriver.exe");
	log.info("Chrome browser has been successfully loaded");
	driver.manage().window().maximize();
	log.info("-------Loaded the Driver--------");
	
	//driver.get("http:google.com");
	UserLoginLogoutPage login = new UserLoginLogoutPage();
	login.loginInitialPage(driver);
	//Calling method to create/edit Account
	CreateEditQuotationpage account = new CreateEditQuotationpage();
	account.QuotationInitialPage(driver);	
 }
	
}

