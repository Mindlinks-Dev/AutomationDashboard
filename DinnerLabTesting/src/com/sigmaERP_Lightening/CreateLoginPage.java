package com.sigmaERP_Lightening;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;

public class CreateLoginPage
{
	public String URL;
	public String UserName;
	public String Password;
	public static boolean loginstatus;
	
	
	public void logincreation(WebDriver driver) throws InvalidElementStateException
	{
		try
		{
			ExcelReading xlib = new ExcelReading();
			int readsheetdata = xlib.getRowCount("loginTestData");
			for(int i=1;i<=readsheetdata;i++)
			{
				String URL = xlib.getExcelData("loginTestData",i, 0);
				String UserName= xlib.getExcelData("loginTestData",i, 1);
				String Password= xlib.getExcelData("loginTestData",i, 2);
				
				

                 System.out.println("URL name:"+URL);
                 System.out.println("URL name:"+UserName);
                 System.out.println("URL name:"+Password);
                 
				loginstatus = loginpage(driver,URL,UserName, Password);
			}
			
			
			
			
		   }
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

	private boolean loginpage(WebDriver driver, String URL, String UserName, String Password)
	{
		try
		{
	    Thread.sleep(2000);
		System.out.println("hi");
		driver.get(URL);
		

		driver.findElement(By.name("username")).sendKeys(UserName);
		driver.findElement(By.name("pw")).sendKeys(Password);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id=\"Login\"]")).submit();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		// TODO Auto-generated method stub
		return false;
	}

	

}
