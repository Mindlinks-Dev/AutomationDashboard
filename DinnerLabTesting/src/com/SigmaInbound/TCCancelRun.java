package com.SigmaInbound;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.SigmaInbound.Testbase;

public class TCCancelRun extends Testbase {

	public static WebDriver driver;

@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=1)
	
	public static void CancelRun(String ManufacturingRunName,String Recipe,String Quantity,
			String Startdate,String Enddate, String QCpassed, String BatchNo,String ExpiryDate,String MovedQuantity,String Storagelocation,String Bin) throws Throwable  
	{
	
	System.out.println("CancelRun");
		Thread.sleep(20000);
		System.out.println("CancelRun");
		
		//----------------------Cancel Run ------------------------------------------
		
		  //driver.findElement(By.xpath("//one-app-launcher-header/descendant::div[@class='slds-icon-waffle']")).click();	
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		

	       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys("Manufacturing Modules");

	       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys(Keys.ENTER);

	      Thread.sleep(6000);
 		
		//Manufacturing Run Modules
		driver.findElement(By.xpath("(//one-app-nav-bar[@class='slds-grid slds-has-flexi-truncate'])//one-app-nav-bar-item-root[9]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
        //Manufacturing Run
		driver.findElement(By.xpath("//img[@name=\"Manufacturing Run\"]")).click();
		Thread.sleep(5000);
				
		//click on new
		driver.findElement(By.xpath("//div[@class=\"slds-no-flex\"]/descendant::button[text()=\"New\"]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//manufacturing run name
		driver.findElement(By.xpath("//label[text()='Manufacturing Run Name']/following::input[1]")).sendKeys(ManufacturingRunName);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		//Manufacturing Recipe Name
		WebElement r = driver.findElement(By.xpath("(//span[text()=\"Recipe\"]/following::input)[1]"));
		
        //Thread.sleep(5000);
		
		Actions act3 = new Actions(driver);
		act3.click(r);
		System.out.println("Clicked");
		act3.sendKeys(Recipe);
		Thread.sleep(2000);
		act3.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']")).click();
		Thread.sleep(6000);	
		
					

		//Quantity
	    driver.findElement(By.xpath("//label[text()='Quantity']/following::input[1]")).sendKeys(Quantity);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		//start date
		driver.findElement(By.xpath("//label[text()='Start Date']/following::input[1]")).sendKeys(Startdate);
		Thread.sleep(3000);
		
		//end date
		driver.findElement(By.xpath("//label[text()='End Date']/following::input[1]")).sendKeys(Enddate);
		Thread.sleep(3000);
				
		//calculate
		driver.findElement(By.xpath("//button[text()='Calculate']")).click();
		Thread.sleep(3000);
		
		//save
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(3000);
		
		//accept alert
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		Thread.sleep(5000);

		//enable auto stock reservation
		driver.findElement(By.xpath("//span[@class='slds-checkbox_faux']")).click();
		Thread.sleep(3000);
		
		//update
		driver.findElement(By.xpath("//button[text()='Update']")).click();
		Thread.sleep(3000);
		
		//accept alert
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		Thread.sleep(5000);
		
		//Status changing to produce
		driver.findElement(By.xpath("//select[@class=\"slds-select select uiInput uiInputSelect uiInput--default uiInput--select\"]")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//option[@value=\"Cancelled\"]")).click();
		
		//Select status = new Select(driver.findElement(By.xpath("//span[text()=\"Status\"]/following::select")));
		//status.deselectByVisibleText("Cancelled");
		
		//accept alert
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		Thread.sleep(5000);
		
		

	}

}
