package com.stappmvp.userclass;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.dinnerLab.util.DriverManager;
import com.stappmvp.userpage.CreateVendorPage;
import com.stappmvp.userpage.Userloginlogoutpage;

public class CreateVendorClass 
{
	private static Logger log = Logger.getLogger(CreateVendorClass.class);
	public WebDriver driver;
	
	
	 //Test Case for submitting the Login page depending on the test data passed
	

	 @Test
	 public void CreateVendorTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		
		DriverManager Launch = new DriverManager();
		driver = new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver" , "E://spoo project//Testing Tool Softwares//New Chrome driver//chromedriver.exe");
		System.out.println("Chrome browser has been successfully loaded");
		driver.manage().window().maximize();
		System.out.println("-------Loaded the Driver--------");
		//driver.get("http:google.com");
		
		
		Userloginlogoutpage login = new Userloginlogoutpage();
		login.loginInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		CreateVendorPage  B = new CreateVendorPage();
		B.VendorInitialPage(driver);
		
		
}
}
