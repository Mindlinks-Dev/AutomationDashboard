package com.Sigma;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

//import com.Base.Testbase;

public class TCCancelRun extends Testbase {



@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=1)
	
	public static void CancelRun(String ManufacturingRunName,String Recipe,String Quantity,
			String Startdate,String Enddate, String QCpassed, String BatchNo,String ExpiryDate,String MovedQuantity,String Storagelocation,String Bin,String Product) throws InterruptedException
	{
		Thread.sleep(20000);
		
		//----------------------Cancel Run ------------------------------------------
		
				
		
		//Click on App Laucher
		driver.findElement(By.xpath("//one-app-launcher-header/descendant::div[@class='slds-icon-waffle']")).click();
		

       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys("SigmaERP 365");
       
       Thread.sleep(6000);

       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys(Keys.ENTER);

        Thread.sleep(6000);
        
        
      //Click on App Laucher
		driver.findElement(By.xpath("//one-app-launcher-header/descendant::div[@class='slds-icon-waffle']")).click();
		

       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys("Manufacturing Modules");
       
       Thread.sleep(6000);

       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys(Keys.ENTER);

        Thread.sleep(6000);
        
	  		
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@name=\"Manufacturing Run\"]")));
        //Manufacturing Run
		
      WebElement ele = 	driver.findElement(By.xpath("//img[@name=\"Manufacturing Run\"]"));
  	  boolean man = ele.isDisplayed();
       System.out.println("man"+ man);
	  Thread.sleep(5000);
	 ele.click();
				
	 
	   Thread.sleep(5000);
		//click on new
		driver.findElement(By.xpath("//div[@class=\"sigmaerpdevManufacturingRun\"]/descendant::button[@type=\"button\"]")).click();
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
		
		
		Thread.sleep(6000);	
		 JavascriptExecutor javascript = (JavascriptExecutor) driver;
		 javascript.executeScript("alert('Inventories are reverted back');");
		 Thread.sleep(8000);
		 driver.switchTo().alert().accept();

		//product tab
	    //driver.findElement(By.xpath("//one-app-nav-bar-item-root/descendant::a[@title=\"Products\"]")).click();
		 
		 //Global Search
		 
		   WebElement w3 = driver.findElement(By.xpath("//div[@class=\"autocompleteWrapper slds-grow slds-form-element__control\"]/descendant::input"));
		 
		   Actions act6= new Actions(driver);
			act6.click(w3);
			act6.sendKeys(Product);
			Thread.sleep(2000);
			act6.build().perform();				
			Thread.sleep(8000);
			driver.findElement(By.xpath("//ul[@class='lookup__list  visible']/descendant::li[2]")).click();
			Thread.sleep(10000);	
			
			
			//related list
			driver.findElement(By.xpath("//a[@class=\"tabHeader\"]/descendant::span[text()=\"Related\"]")).click();
			
			
			//Product PI
			
			WebElement element = driver.findElement(By.xpath("(//a//descendant::span[text()=\"Inventory Location Product Line Items\"])[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(5000); 
			
			
			driver.findElement(By.xpath("//span[text()=\"Inventory Transactions\"]/following::span[@class=\"view-all-label\"]")).click();
			
			
			Thread.sleep(5000);
			//Javascrpit to scroll till buttom of the page
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
			Thread.sleep(5000);
			
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			
			
			Thread.sleep(6000);	
			 JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
			 javascript1.executeScript("alert('Manufacturing Run is cancelled and Inventories are reverted back');");
			 Thread.sleep(8000);
			 driver.switchTo().alert().accept();
				
		
		

	}

}
