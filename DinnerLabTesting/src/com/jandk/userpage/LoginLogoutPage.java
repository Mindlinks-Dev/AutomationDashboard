package com.jandk.userpage;

import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.util.ExcelLib;


public class LoginLogoutPage 
{
	private String loginUsername;
	private String loginPassword;
	private String loginURL;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	 public static String result;
	 public static String comment;
	 public static boolean loggedInStatus;
	 public static boolean loginStatus;
	 public static boolean logoutStatus;
	 int i = 0;

	 private static Logger log = Logger.getLogger(LoginLogoutPage.class);

	// Test Case for Reading the excel data and login into the Login Page
	 public void loginInitialPage(WebDriver driver) throws InvalidFormatException
	 {
		 try
		 {
			 ExcelLib xllib = new ExcelLib();
			 
			 int loginRowCount = xllib.getRowCount("J&KSFDCLogin");
			 System.out.println("loginRowCount:"+loginRowCount);
			 for(i=0;i<= loginRowCount;i++)
			 {
				 loginURL = xllib.getExcelData("J&KSFDCLogin", i, 0);
			     loginUsername = xllib.getExcelData("J&KSFDCLogin", i, 1);
			     loginPassword = xllib.getExcelData("J&KSFDCLogin", i, 2);
			     
			   //Calling Login URL method
					System.out.println("loginURL:"+loginURL);
					System.out.println("loginUsername:"+loginUsername);
					System.out.println("loginPassword:"+loginPassword);
					
					loginStatus = loginPageActions(driver,loginURL,loginUsername,loginPassword);
					System.out.println("Login Count:"+i);
			 }
		 }
		 
		 catch(Exception e)
			{
				System.out.println("Login Unsucessful");
				e.printStackTrace();
				
			}
		 }
	 
	 //Test Case for Reading the excel data and login into the Login Page
	 public Boolean loginPageActions(WebDriver driver,String loginURL,String loginUsername,String loginPassword) throws Exception
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			 driver.get(loginURL);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 driver.findElement(By.id("username")).clear();
				driver.findElement(By.id("username")).sendKeys(loginUsername);
				driver.findElement(By.id("password")).clear();
				driver.findElement(By.id("password")).sendKeys(loginPassword);
				driver.findElement(By.id("Login")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				return true;
		 }
		 catch(Exception e)
		 {
			 log.info("Unsuccessful Logged into an application");
				return false;
		 }
	 
	 }
			
}


