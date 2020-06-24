package com.Sigma;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;



public class GenericCode extends Testbase 
{
	//----------------------------------------------CREATE ACCOUNT------------------------------------------------------
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp",priority=1)
	public static void account(String AccountType, String AccountName, String Active, String CurrencyCode, String Email, String ShippingStreet,
			String ShippingCity, String ShippingState, String ShippingZip, String ShippingCountry, String AccAddress, String AccCity,
			String AccState, String AccCountry, String AccZip, String AccPhone) throws InterruptedException
	{
		Thread.sleep(20000);
		driver.manage().window().maximize();
		
		//app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);
		
		//click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);
		
		//click on account tab
		driver.findElement(By.xpath(OR.getProperty("Acctab"))).click();
		Thread.sleep(5000);
		
		//click on new button
		driver.findElement(By.xpath(OR.getProperty("AccNew"))).click();
		Thread.sleep(4000);
		
		if(AccountType.equals("Customer"))
		{
			//selects customer type
			driver.findElement(By.xpath(OR.getProperty("AccCustomer"))).click();
			Thread.sleep(4000);
		}
			
			//next
			driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
			Thread.sleep(4000);
			
			//account name
			WebElement an = driver.findElement(By.xpath(OR.getProperty("AccName")));
			an.sendKeys(AccountName);
			
			
			/*an.sendKeys(AccountName, Keys.TAB, Keys.CONTROL, Keys.SHIFT, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
			Thread.sleep(4000);
			
			an.sendKeys(AccountName, Keys.TAB, Keys.TAB,
					Keys.ENTER, Keys.CONTROL, Keys.SHIFT, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER );*/
			
			
			//active
			WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccActiveClick")));
			Actions a = new Actions(driver);
			a.moveToElement(we1);
			a.click();
			
			List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
			for (WebElement we : list)
			{
				if (we.getText().contains(Active)){
					Thread.sleep(5000);
					we.click();
				}
			}
			Thread.sleep(5000);
			
			//email, currencycode, Address
			WebElement email = driver.findElement(By.xpath(OR.getProperty("Email")));
			email.sendKeys(Email, Keys.TAB, CurrencyCode, Keys.TAB, ShippingStreet, Keys.TAB, ShippingCity, Keys.TAB, ShippingState,
					Keys.TAB, ShippingZip, Keys.TAB, ShippingCountry, Keys.TAB, Keys.TAB, AccAddress, Keys.TAB, AccPhone, Keys.TAB,
					AccCity, Keys.TAB, Keys.TAB, AccState, Keys.TAB, AccCountry, Keys.TAB, AccZip);
		
			//save
			driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
			Thread.sleep(4000);
		
	}

}
