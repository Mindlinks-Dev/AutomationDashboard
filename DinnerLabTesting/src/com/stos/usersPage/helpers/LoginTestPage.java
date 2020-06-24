package com.stos.usersPage.helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;



public class LoginTestPage 
{
	private String LoginURL;
	private String UserName;
	private String Password;
	public String text="Sudhir DakshinaM...";
	
	public static boolean loginStatus; 
	public int rowCount;
	int i=0;
	ExcelLib xllib = new ExcelLib();
	
	private static Logger log = Logger.getLogger(LoginTestPage.class);
	public WebDriver driver;
	
	public void loginTestInitialPage(WebDriver driver)
	{
		try
		{
			rowCount= xllib.getRowCount("STOSlogin");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		LoginURL = xllib.getExcelData("STOSlogin", i, 0);
		 		UserName = xllib.getExcelData("STOSlogin", i, 1);
		 		Password = xllib.getExcelData("STOSlogin", i, 2);
		 		
		 		
		 		loginStatus = loginTestActionPage(driver,LoginURL,UserName,Password);
		 		if(loginStatus)
		 		{
		 			log.info("Successfully logged in");
		 		}
			}
		 	
			
		}
		catch(Exception e)
		{
			log.info("UnSuccessfully logged in");
			e.printStackTrace();
		}
		
		
	}

	private boolean loginTestActionPage(WebDriver driver, String loginURL,
			String userName, String password)
	{
		try
		{
			log.info("logging in as a User");
			Thread.sleep(2000);
			driver.manage().window().maximize();
			driver.get(loginURL);
			
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(userName); //userName
			
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password); //password
			
			driver.findElement(By.id("Login")).click(); //login button
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void logoutTestActionPage(WebDriver driver)
	{
		try
		{
			log.info("Logout Action method");
			Thread.sleep(6000);
			if(driver.findElement(By.xpath("//span[@id='userNavLabel']")).getText().contains(text))
			{
				Thread.sleep(6000);
				driver.findElement(By.id("userNavLabel")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//a[text()='Logout']")).click(); // logout option
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				log.info("Logged out successfully");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
