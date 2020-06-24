package com.autoparts.userclass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.autoparts.userpage.CreateEditQuotationpage;
import com.autoparts.userpage.CreateOrderPage;
import com.autoparts.userpage.CreateOrderPartsPage;
import com.autoparts.userpage.CreateQuotationPartsPage;
import com.autoparts.userpage.UserLoginLogoutPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;


public class CreateOrderPartsTest
{
	private static Logger log = Logger.getLogger(CreateEditQuotationTest.class);
    public WebDriver driver;

 @Test 
  public void CreateOrderpartsTest() throws Exception
 {
	log.info("-------Opening the browser--------");
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
	/*//Create the quotation
	CreateEditQuotationpage account = new CreateEditQuotationpage();
	account.QuotationInitialPage(driver);	
	//Create the quotationparts 
	CreateQuotationPartsPage account1 = new CreateQuotationPartsPage();
	account1.QuotationPartsInitialPage(driver);
	 //Create the Order 
	CreateOrderPage account2 = new CreateOrderPage();
	account2.OrderInitialPage(driver);*/
	//Create the OrderParts
	CreateOrderPartsPage account3 = new CreateOrderPartsPage();
	account3.OrderPartsInitialPage(driver);
	
}
}
