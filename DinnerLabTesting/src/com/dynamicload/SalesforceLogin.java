package com.dynamicload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SalesforceLogin {
	
	@Test
	public  void test()
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://login.salesforce.com/");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ranjithas@mindlinkssolution.com");
		
		driver.findElement(By.xpath("//input[@ type='password']")).sendKeys("Password-01");
		
		driver.findElement(By.xpath("//input[@ type='submit']")).click();
	}
	
	

}
