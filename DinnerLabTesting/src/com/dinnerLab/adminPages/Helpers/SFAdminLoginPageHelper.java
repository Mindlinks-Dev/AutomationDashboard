/**
 * @author: Anitha M
 * @Created Date :16/06/2015
 * @Updated Date :7/7/2015
 * @Comments:This automation class will server SFDC Admin login page test case 
 */
package com.dinnerLab.adminPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;


/**
 * Test Case for submitting the Admin Login page depending on the test data passed
 * valid email and password should allow the user to
 * login in to the application.
 * Input: No parameter
 * Output: Void
 * @throws InvalidFormatException 
 */	 

public class SFAdminLoginPageHelper 
{
	private String uname;
	private String pwd;
	private String loginUrl=TestConstants.SFLOGIN_URL;
	Boolean status;
	public static boolean flag;
   
    private static Logger log = Logger.getLogger(SFAdminLoginPageHelper.class);
    
    /**
	 * Test Case for Reading excel data for SFDC Admin login page
	 * Input: WebDriver driver
	 * Output: void
	 * 
	 */
    
    public void sfAdminLoginInitialPage(WebDriver driver)
    {
    	try
    	{
    		ExcelLib xlib=new ExcelLib();
    		int rowCount=xlib.getRowCount("SFlogin");
    		System.out.println("rowCount:"+rowCount);
    		for(int i=1;i<=rowCount;i++)
    		{
    			uname = xlib.getExcelData("SFlogin", i, 0);
    			pwd = xlib.getExcelData("SFlogin", i, 1);
    			
    			System.out.println("uname:"+uname);
    			System.out.println("pwd:"+pwd);
    			
    			status=sfadminloginActions(driver, uname, pwd);
    			System.out.println("status:"+status);
    			if(status)
    			{
    				log.info(status);
    				log.info("Logged in Successful");
    				
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    /**
	 * Test Case for login performing for the data 
	 * that is passed from excel
	 * Input: WebDriver driver,String Username,String Password
	 * Output: Boolean
	 * 
	 */
    
    public Boolean sfadminloginActions(WebDriver driver,String Username,String Password)
	 {
	 try
		{
			driver.manage().window().maximize();
			driver.get(loginUrl);
			log.info("********************************************");
			System.out.println("Before Login");
			log.info("navigating to "+loginUrl+"");
			System.out.println("After Login");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Username);
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Password);
			driver.findElement(By.xpath("//input[@id='Login']")).click();
			
			log.info("Waiting for signin process to complete...");
			
			flag = driver.findElement(By.xpath(" //div[@id='getting-started-block']/div/span/div/h2")).isDisplayed();
			System.out.println("Home page:"+flag);
			if(flag)
			{
				log.info("Successfully logged in");
				//Thread.sleep(2000);
				
				return true;
			}
			
	     }
		catch(Exception e)
		{
			log.info("Unsuccessful Logged into an application");
			return false;
			
		}
	return null;
}
    
    
}
