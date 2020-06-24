package com.SigmaERP.testclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.alignsandbox.java.com.Base.AlignTestbase;

public class SigmaERPtestbase

{
	
	public static int row;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static WebDriver driver;
	public static String Browser = "chrome";
	public static Logger log = Logger.getLogger(SigmaERPtestbase.class);
	
	@BeforeSuite
	public static void setup() throws IOException, InterruptedException 
	{
		System.out.println(System.getProperty("user.dir"));
		
		 if (driver == null) {
		  
		  fis = new FileInputStream(
		  "D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\SigmaERP\\testclass\\Config");
		  
		  config.load(fis); log.debug("loading config file");
		  
		  
		  
		  if (Browser.equals("chrome")) {
		String exePath = "D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		Thread.sleep(3000);
		driver.manage().window().maximize();
		  }
		  
		  
		//Redirect to login page
			driver.navigate().to(config.getProperty("Testurl"));
	         Thread.sleep(12000);
			
			//Enter the user name text field
			driver.findElement(By.id("username")).click();
			driver.findElement(By.id("username")).sendKeys(config.getProperty("UN"));
			
			 //Enter password
		     driver.findElement(By.id("password")).click();
		     driver.findElement(By.id("password")).sendKeys(config.getProperty("PWD"));

		     
		     //Click login button
		     driver.findElement(By.id("Login")).click();
			 log.debug("login successfull");
			 Thread.sleep(8000);
		 }
		}

	
	
	@AfterSuite
	public void close() {
		
	}
	

}
