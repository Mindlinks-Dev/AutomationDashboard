package com.practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.dinnerLab.util.DriverManager;
import com.dinnerLab.util.ExcelLib;

public class testLogin {
	public WebDriver driver;
	public static String Username;
	public static String Password;
	public static String LoginURL;
	@Test
	
public void testLoginpage() throws Exception
{
		ExcelLib getExcelData = new ExcelLib();
		int RowCount = getExcelData.getRowCount("RedcrossLogin");
		
		for (int i = 1; i <= RowCount; i++) 
		{
	 		 LoginURL = getExcelData.getExcelData("RedcrossLogin", i, 0);
	 		 Username = getExcelData.getExcelData("RedcrossLogin", i, 1);
			 Password = getExcelData.getExcelData("RedcrossLogin", i, 2);
			 System.setProperty("webdriver.chrome.driver", "D:/DinnerLabWorkspace/DinnerLabTesting/src/chromedriver.exe");	
				//System.setProperty("webdriver.ie.driver", "D:/DinnerLabWorkspace/DinnerLabTesting/src/IEDriverServer_32.exe");
				
				//WebDriver driver=new InternetExplorerDriver();
				WebDriver driver=new ChromeDriver();
				//WebDriver driver=new FirefoxDriver(DriverManager.FirefoxDriverProfile());
				
				driver.get(LoginURL);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//driver.findElement(By.id("use_new_identity")).click();
				driver.findElement(By.id("username")).sendKeys(Username);
				driver.findElement(By.id("password")).sendKeys(Password);
				driver.findElement(By.id("Login")).click();
}
}}
