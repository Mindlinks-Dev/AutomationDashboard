 /**
 * @author: Spoorthi .k
 * @Created Date :02/09/2017
 * @Updated Date :
 * @Comments:This automation class will login to appex org.
*/
package com.SigmaERP.testclass;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.SigmaERP.testpage.Userloginlogoutpage;
import com.dinnerLab.util.DriverManager;

public class Userloginlogoutclass
{
	private static Logger log = Logger.getLogger(Userloginlogoutclass.class);
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
		
		//DriverManager Launch = new DriverManager();
		String exePath = "D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		//driver.get("https://login.salesforce.com");
		
		//driver = new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver" , "E://spoo project//Testing Tool Softwares//New Chrome driver//chromedriver.exe");
		System.out.println("Chrome browser has been successfully loaded");
		driver.manage().window().maximize();
		System.out.println("-------Loaded the Driver--------");
		//driver.get("http:google.com");
		
		
		Userloginlogoutpage login = new Userloginlogoutpage();
		login.loginInitialPage(driver);
	 }
}
