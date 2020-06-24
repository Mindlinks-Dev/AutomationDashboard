package com.cohbaines.testpages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.dinnerLab.util.ExcelLib;

public class LoginLogoutTestpage {
	
	private String Username;
	private String Password;
	private String URL;
	public static boolean loginStatus;
	int i=0;
	
	private static Logger log = Logger.getLogger(LoginLogoutTestpage.class);
	public void loginInitialPage(WebDriver driver) throws InvalidFormatException
	{
	try
	{
	ExcelLib xllib = new ExcelLib();

	int loginRowCount = xllib.getRowCount("live3LoginPage");
	System.out.println("loginRowCount:"+loginRowCount);
	for(i=1;i<= loginRowCount;i++)
	{
	URL = xllib.getExcelData("live3LoginPage", i, 0);
	Username = xllib.getExcelData("live3LoginPage", i, 1);
	Password = xllib.getExcelData("live3LoginPage", i, 2);
	    
	  //Calling Login URL method
	System.out.println("loginURL:"+URL);
	System.out.println("loginUsername:"+Username);
	System.out.println("loginPassword:"+Password);

    loginStatus = loginPageActions(driver,URL,Username,Password);
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
	public Boolean loginPageActions(WebDriver driver,String URL,String Username,String Password) throws Exception
	{
	try
	{
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get(URL);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys(Username);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys(Password);
	Thread.sleep(3000);

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


