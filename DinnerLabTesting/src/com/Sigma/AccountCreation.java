package com.Sigma;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class AccountCreation extends Testbase{
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=1)
	public static  void account(String AccountType, String AccountName, String Active, String CurrencyCode, String Email,
			String ShippingStreet, String ShippingCity, String ShippingState, String ShippingZip,
			String ShippingCountry, String AccAddress, String AccCity, String AccState, String AccCountry,
			String AccZip, String AccPhone) throws InterruptedException
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationDashboard\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://login.salesforce.com/");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("sigmaerpdev@mindlinks.com");
		
		driver.findElement(By.xpath("//input[@ type='password']")).sendKeys("sigmadev-13");
		
		driver.findElement(By.xpath("//input[@ type='submit']")).click();
		Thread.sleep(10000);
		
		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		// click on account tab
		driver.findElement(By.xpath(OR.getProperty("Acctab"))).click();
		Thread.sleep(5000);

		// click on new button
		driver.findElement(By.xpath(OR.getProperty("AccNew"))).click();
		Thread.sleep(4000);
		
		if (AccountType.equals("Customer")) {
			// selects agency type
			driver.findElement(By.xpath(OR.getProperty("AccCustomer"))).click();
			Thread.sleep(4000);

			// next
			driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
			Thread.sleep(4000);

			// account name
			driver.findElement(By.xpath(OR.getProperty("AccName"))).sendKeys(AccountName);
			Thread.sleep(4000);

			// active
			WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccActiveClick")));
			Actions a = new Actions(driver);
			a.moveToElement(we1);
			a.click();

			List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
			for (WebElement we : list) {
				if (we.getText().contains(Active)) {
					Thread.sleep(5000);
					we.click();
				}
			}
			Thread.sleep(5000);

			// currency code
			WebElement cc = driver.findElement(By.xpath(OR.getProperty("CurrencyCode")));
			driver.findElement(By.xpath(OR.getProperty("CurrencyCode"))).sendKeys(CurrencyCode);
			Thread.sleep(4000);

			// email
			driver.findElement(By.xpath(OR.getProperty("Email"))).sendKeys(Email);
			Thread.sleep(4000);

			// shipping street
			driver.findElement(By.xpath(OR.getProperty("ShippingStreet"))).sendKeys(ShippingStreet);
			Thread.sleep(4000);

			// shipping city
			driver.findElement(By.xpath(OR.getProperty("ShippingCity"))).sendKeys(ShippingCity);
			Thread.sleep(4000);

			// shipping state
			driver.findElement(By.xpath(OR.getProperty("ShippingState"))).sendKeys(ShippingState);
			Thread.sleep(4000);

			// scroll down
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(8000);

			// Postal code
			driver.findElement(By.xpath(OR.getProperty("ShippingZip"))).sendKeys(ShippingZip);
			Thread.sleep(4000);

			// shipping country
			driver.findElement(By.xpath(OR.getProperty("ShippingCountry"))).sendKeys(ShippingCountry);
			Thread.sleep(4000);

			// autopay
			driver.findElement(By.xpath(OR.getProperty("AutoPay"))).click();
			Thread.sleep(4000);

			// address
			driver.findElement(By.xpath(OR.getProperty("AccAddress"))).sendKeys(AccAddress);
			Thread.sleep(4000);

			// city
			driver.findElement(By.xpath(OR.getProperty("AccCity"))).sendKeys(AccCity);
			Thread.sleep(4000);

			// state
			driver.findElement(By.xpath(OR.getProperty("AccState"))).sendKeys(AccState);
			Thread.sleep(4000);

			// country
			driver.findElement(By.xpath(OR.getProperty("AccCountry"))).sendKeys(AccCountry);
			Thread.sleep(4000);

			// zip code
			driver.findElement(By.xpath(OR.getProperty("AccZip"))).sendKeys(AccZip);
			Thread.sleep(4000);

			// phone
			driver.findElement(By.xpath(OR.getProperty("AccPhone"))).sendKeys(AccPhone);
			Thread.sleep(4000);

			// save
			driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
			Thread.sleep(4000);
		
		}
}
}
