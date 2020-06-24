package com.autoparts.userclass;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import com.autoparts.userpage.CreateEditQuotationpage;
import com.autoparts.userpage.CreateOrderPage;
import com.autoparts.userpage.CreateOrderPartsPage;
import com.autoparts.userpage.CreateQuotationPartsPage;
import com.autoparts.userpage.UserLoginLogoutPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;

public class UserLoginLogoutClass

{

	private static Logger log = Logger.getLogger(UserLoginLogoutClass.class);
	public WebDriver driver;
	
	/**
	 * Test Case for submitting the Login page depending on the test data passed
	 * valid email and password should allow the user to
	 * login in to the application.
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 * @throws InvalidFormatException 
	 */	 

	 @Test
	 public void loginPageTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		DriverManager Launch = new DriverManager();
		//DriverManager loadBrowser = new DriverManager();
		//Launch.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		//driver = Launch.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		driver = new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver" , "E://spoo project//Testing Tool Softwares//New Chrome driver//chromedriver.exe");
		System.out.println("Chrome browser has been successfully loaded");
		driver.manage().window().maximize();
		System.out.println("-------Loaded the Driver--------");
		//driver.get("http:google.com");
		UserLoginLogoutPage login = new UserLoginLogoutPage();
		login.loginInitialPage(driver);
		//Create the quotation 
		CreateEditQuotationpage account = new CreateEditQuotationpage();
		account.QuotationInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Create the quotationparts 
		CreateQuotationPartsPage account1 = new CreateQuotationPartsPage();
		account1.QuotationPartsInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		//Create the Order 
		CreateOrderPage account2 = new CreateOrderPage();
		account2.OrderInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		//Create the OrderParts
		CreateOrderPartsPage account3 = new CreateOrderPartsPage();
		account3.OrderPartsInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			 }	
        }
