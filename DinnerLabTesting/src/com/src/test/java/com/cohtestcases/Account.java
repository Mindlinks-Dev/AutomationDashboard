package com.src.test.java.com.cohtestcases;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.src.test.java.com.Base.Testbase;

public class Account extends Testbase {

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp",priority=1)
	public static void account(String TYPE, String ACCOUNTNAME,String REGION,String COUNTRY,String BILLINGADDRESS) throws InterruptedException, AWTException {

		Thread.sleep(8000l);

		
		driver.manage().window().maximize();
		// clicks on account tab
		driver.findElement(By.xpath(
				"//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Accounts']"))
				.click();

		Thread.sleep(5000l);
		// New button
		driver.findElement(By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li")).click();

		
		//clicks on the types (customer/vendor/shipping addresss)
		String account = TYPE;

		Thread.sleep(2000l);
		List<WebElement> list = driver.findElements(By.tagName("span"));
		System.out.println(list.size());
		// System.out.println(list);
		for (WebElement we : list) {

			if (we.getText().equalsIgnoreCase(account)) {

				Thread.sleep(5000l);
				Actions act1 = new Actions(driver);
				act1.click(we);
				//act1.sendKeys("PL");
				act1.build().perform();

			}

		}

		//next button
		

		if(account.equals("Vendor")||account.equals("Customer")) {
			
			Thread.sleep(5000l);
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			
			
		//enters account name
		driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"))
				.sendKeys(ACCOUNTNAME);

		//enters currency field
		driver.findElement(By.xpath("(//label/span[text()=\"Currency code\"]/following::input)[1]")).sendKeys("GBP");
		
		//enters region
		String Region=REGION;
		
		driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
		 List<WebElement> lst=	driver.findElements(By.xpath("//div[@role=\"menu\"]/descendant::a"));
		    for(WebElement we:lst) {
			if(we.getText().equalsIgnoreCase(Region)) {
				Thread.sleep(5000l);
				we.click();
			}	}
		
		
		    //enters  Billing address
		    driver.findElement(By.xpath("//textarea[@placeholder=\"Billing Street\"]")).sendKeys(BILLINGADDRESS);
		
		    
		    String Country =COUNTRY;
		    
		  //clicks on country
			Thread.sleep(9000l);
			driver.findElement(By.xpath("(//a[@class=\"select\"])[6]")).click();
			
		   List<WebElement> lst1=	driver.findElements(By.xpath("//div[@role=\"menu\"]/descendant::a"));
		    for(WebElement we:lst1) {
			if(we.getText().equalsIgnoreCase(Country)) {
				Thread.sleep(5000l);
				we.click();
			}	}
		
		}
		else if(account.equals("Location")||account.equals("Packaged Location")||account.equals("Receiving Location")||account.equals("Logistics")||account.equals(""))
		{
			
			Thread.sleep(5000l);
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			
			driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"))
			.sendKeys(ACCOUNTNAME);
			
				
		}
		
		// driver.findElement(By.xpath("//input[@ type=\"checkbox\"]")).click();
		
		
				
		
		driver.findElement(By.xpath("(//span[text()=\"Save\"])[2]")).click();
		Thread.sleep(8000l);

	} 

}
