package com.src.test.java.com.rough;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.src.test.java.com.Base.Testbase;








public class TestUtil extends Testbase {

	@Test
	public static void account(String TYPE) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60l);
		// Account
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Accounts']")))
				.click();

		Thread.sleep(5000l);
		// New button
		driver.findElement(By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li")).click();

		

		String account = "Vendor";

		
		
		Thread.sleep(2000l);
		List<WebElement> list = driver.findElements(By.tagName("span"));
		System.out.println(list.size());
		System.out.println(list);
		for (WebElement we : list) {

			if (we.getText().equalsIgnoreCase(account)) {

				Thread.sleep(5000l);
				Actions act1 = new Actions(driver);
				act1.click(we);
				
				act1.build().perform();

			}

		}

		Thread.sleep(5000l);

		driver.findElement(By.xpath("//span[text()='Next']")).click();
		//for vendor
		if(account.equalsIgnoreCase(TYPE))
		{
		driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"))
				.sendKeys("IL");

		driver.findElement(By.xpath("//input[@ type=\"checkbox\"]")).click();
		//clicks on country
		Thread.sleep(9000l);
		driver.findElement(By.xpath("(//a[@class=\"select\"])[6]")).click();
	   List<WebElement> lst=	driver.findElements(By.xpath("//div[@role=\"menu\"]/descendant::a"));
	    for(WebElement we:lst) {
		if(we.getText().equalsIgnoreCase("Afghanistan")) {
			Thread.sleep(5000l);
			we.click();
		}	}
	
	    driver.findElement(By.xpath("//textarea[@placeholder=\"Billing Street\"]")).sendKeys("#1244, Chrurch street");

	
	Thread.sleep(5000l);
		driver.findElement(By.xpath("(//span[text()=\"Save\"])[2]")).click();
		
		}
		
		
		//for customer
		else	if(account.equalsIgnoreCase(TYPE))
		{
		driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"))
				.sendKeys("IL");

		driver.findElement(By.xpath("//input[@ type=\"checkbox\"]")).click();
		//clicks on country
		Thread.sleep(9000l);
		driver.findElement(By.xpath("(//a[@class=\"select\"])[6]")).click();
		
	   List<WebElement> lst=	driver.findElements(By.xpath("//div[@role=\"menu\"]/descendant::a"));
	    for(WebElement we:lst) {
		if(we.getText().equalsIgnoreCase("Afghanistan")) {
			Thread.sleep(5000l);
			we.click();
		}	}
	
	    driver.findElement(By.xpath("(//input/ancestor::div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"]/input)[1]")).sendKeys("GBP");
	    
	    driver.findElement(By.xpath("//textarea[@placeholder=\"Billing Street\"]")).sendKeys("#1244, Chrurch street");

	
	Thread.sleep(5000l);
		driver.findElement(By.xpath("(//span[text()=\"Save\"])[2]")).click();
		
		}
		
		
		
		

	}

}
