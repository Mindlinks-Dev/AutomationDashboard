package com.align.userclass;

import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.alignsandbox.java.com.Base.AlignTestbase;
import com.alignsandbox.java.com.Base.IntegrationTestbase;


public class UserLoginClass extends IntegrationTestbase
{

	@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp")
	public static void Alignlogin(String URL,
			String Username, String Password) throws MalformedURLException,
			InterruptedException
	{
			try
				{
					//Redirect to login page
					driver.manage().window().maximize();
					driver.navigate().to(URL);
			         Thread.sleep(12000);
					
					//Enter the user name text field
					driver.findElement(By.xpath("//input[@name='username']")).click();
					driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Username);
					Thread.sleep(3000);
					 //Enter password
				     driver.findElement(By.xpath("(//input[@name='password'])[1]")).click();
				     driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys(Password);
				     Thread.sleep(3000);
				     
				     //Click login button
				     driver.findElement(By.xpath("//button[@class='button green']")).click();
					 log.debug("login successfull");
					 Thread.sleep(8000);
					 
					 try
				     {
					 //driver.findElement(By.xpath("//div[@class='k-widget k-window radoloWindow']/div[2]/div/div[2]/descendant::button[text()='OK']")).click();
				    	 driver.findElement(By.xpath("//button[text()='OK']")).click();
				     }
				     catch(Exception e)
				     {  
				    	 e.printStackTrace();
				     }				           					
					}
						catch(Exception e)
						{
							System.out.println("Login Unsuccessfull");
						}	
			}
}