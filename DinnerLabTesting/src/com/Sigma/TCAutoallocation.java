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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Base.Testbase;

public class TCAutoallocation extends Testbase {

	

@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=1)
	
	public static void BulkprodAutoallocation(String ManufacturingRunName,String Recipe,String Quantity,
			String Startdate,String Enddate, String QCpassed, String BatchNo,String ExpiryDate,String MovedQuantity,String Storagelocation,String Bin,String Product) throws InterruptedException
	{
		Thread.sleep(20000);
		
	
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
		driver.findElement(By.xpath("//option[@value=\"Produced\"]")).click();

		//Post Production
				Thread.sleep(5000);
				driver.findElement(By.xpath("//ul[@role=\"tablist\"]/descendant::a[text()=\"Post Production\"]")).sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);


				//Enter the QC passed checkbox
				driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
				Thread.sleep(3000);

				//Enter the QC passed quantity
				driver.findElement(By.xpath("//span[text()=\"Passed Qty\"]/following::input[1]")).sendKeys(QCpassed);
				Thread.sleep(3000);
				
								
				//Enter the BATCH NUMBER
				driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[6]")).sendKeys(BatchNo);
				Thread.sleep(3000);
				
				//Enter the ExpiryDate
				driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[7]")).sendKeys(ExpiryDate);
				Thread.sleep(3000);
		
			
		//Enter the QUANTITY MOVED
		driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[10]")).sendKeys(MovedQuantity);
		Thread.sleep(3000);
		
		
		//Enter the ACTUAL STORAGE LOCATION
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement r1 = driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[11]"));

		//Thread.sleep(5000);
		Actions act4 = new Actions(driver);
		act3.click(r1);
		System.out.println("Clicked");
		act3.sendKeys(Storagelocation);
		Thread.sleep(2000);
		act3.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']")).click();
		Thread.sleep(6000);	
		
		//Bin inside location
		
		WebElement w2 = driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[13]"));
		Thread.sleep(5000);
		
		Actions act5 = new Actions(driver);
		act5.click(w2);
		act5.sendKeys(Bin);
		Thread.sleep(2000);
		act5.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//ul[@role='listbox']/child::li[1]")).click();
		Thread.sleep(6000);	
		
		//Clicking on button Save and Move
		driver.findElement(By.xpath("//fieldset//button[text()=\"Save_and_Move_Stock\"]")).click();
		
		Thread.sleep(6000);	
		 JavascriptExecutor javascript = (JavascriptExecutor) driver;
		 javascript.executeScript("alert('SS60 GS BUlK will update with produced quantity');");
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
			Thread.sleep(500); 
			
			
			driver.findElement(By.xpath("//span[text()=\"Inventory Transactions\"]/following::span[@class=\"view-all-label\"]")).click();
			
			Thread.sleep(6000);	
			 JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
			 javascript1.executeScript("alert('SS60 GS BUlK will update with produced quantity through Manufacturing Run');");
			 Thread.sleep(8000);
			 driver.switchTo().alert().accept();
				
	}


	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=2)
	
	public static void BulkprodMultiLoc(String ManufacturingRunName,String Recipe,String Quantity,
			String Startdate,String Enddate, String QCpassed, String BatchNo,String ExpiryDate,String MovedQuantity1,String Storagelocation1,String Bin1,String MovedQuantity2,String Storagelocation2,String Bin2,String Product) throws InterruptedException
	{
		Thread.sleep(20000);
		

		//----------------------Manufacturing Run BULK product allocation flow for Multiple Locations------------------------------------------
	
		//Click on App Laucher
		driver.findElement(By.xpath("//one-app-launcher-header/descendant::div[@class='slds-icon-waffle']")).click();
		

       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys("Manufacturing Modules");

       driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys(Keys.ENTER);

         Thread.sleep(6000);


		//Manufacturing Run Modules
		driver.findElement(By.xpath("(//one-app-nav-bar[@class='slds-grid slds-has-flexi-truncate'])//one-app-nav-bar-item-root[9]")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
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
		driver.findElement(By.xpath("//option[@value=\"Produced\"]")).click();

		//Post Production
				Thread.sleep(5000);
				driver.findElement(By.xpath("//ul[@role=\"tablist\"]/descendant::a[text()=\"Post Production\"]")).sendKeys(Keys.ENTER);
				
				Thread.sleep(3000);


				//Enter the QC passed checkbox
				driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
				Thread.sleep(3000);

				//Enter the QC passed quantity
				driver.findElement(By.xpath("//span[text()=\"Passed Qty\"]/following::input[1]")).sendKeys(QCpassed);
				Thread.sleep(3000);
				
								
				//Enter the BATCH NUMBER
				driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[6]")).sendKeys(BatchNo);
				Thread.sleep(3000);
				
				//Enter the ExpiryDate
				driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[7]")).sendKeys(ExpiryDate);
				Thread.sleep(3000);
		
			
		//Enter the QUANTITY MOVED1
		driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[10]")).sendKeys(MovedQuantity1);
		Thread.sleep(3000);
		
		
		//Enter the ACTUAL STORAGE LOCATION1
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement r1 = driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[11]"));

		//Thread.sleep(5000);
		Actions act4 = new Actions(driver);
		act3.click(r1);
		System.out.println("Clicked");
		act3.sendKeys(Storagelocation1);
		Thread.sleep(2000);
		act3.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']")).click();
		Thread.sleep(6000);	
		
		//Bin inside location1
		
		WebElement w2 = driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[13]"));
		Thread.sleep(5000);
		
		Actions act5 = new Actions(driver);
		act5.click(w2);
		act5.sendKeys(Bin1);
		Thread.sleep(2000);
		act5.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//ul[@role='listbox']/child::li[1]")).click();
		Thread.sleep(6000);	
		
		
		//Move the Multiple Locations stock
		driver.findElement(By.xpath("//button[@class=\"slds-button slds-button--neutral slds-m-around--small\"]")).click();
		
		
		//Enter the QUANTITY MOVED2
		driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[14]")).sendKeys(MovedQuantity2);
		Thread.sleep(3000);
		
		
		//Enter the ACTUAL STORAGE LOCATION2
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement r2 = driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[15]"));

		//Thread.sleep(5000);
		Actions act6 = new Actions(driver);
		act6.click(r2);
		System.out.println("Clicked");
		act6.sendKeys(Storagelocation2);
		Thread.sleep(2000);
		act6.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']")).click();
		Thread.sleep(6000);	
		
		//Bin inside location2
		
		WebElement w3 = driver.findElement(By.xpath("(//fieldset[@class=\"slds-form-element\"]/descendant::div[@class=\"slds-form-element__row\"]/descendant::input)[17]"));
		Thread.sleep(5000);
		
		Actions act7 = new Actions(driver);
		act7.click(w3);
		act7.sendKeys(Bin2);
		Thread.sleep(2000);
		act7.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//ul[@role='listbox']/child::li[1]")).click();
		Thread.sleep(6000);	
		
		
		//Clicking on button Save and Move
		driver.findElement(By.xpath("//fieldset//button[text()=\"Save_and_Move_Stock\"]")).click();
		
		Thread.sleep(6000);	
		 JavascriptExecutor javascript = (JavascriptExecutor) driver;
		 javascript.executeScript("alert('SS60 GS BUlK will update with produced quantity');");
		 Thread.sleep(8000);
		 driver.switchTo().alert().accept();
		
		 
		//product tab
	    //driver.findElement(By.xpath("//one-app-nav-bar-item-root/descendant::a[@title=\"Products\"]")).click();
		 
		 //Global Search
		 
		   WebElement w8 = driver.findElement(By.xpath("//div[@class=\"autocompleteWrapper slds-grow slds-form-element__control\"]/descendant::input"));
		   w8.clear();
		   
		   
		   Actions act8= new Actions(driver);
			act8.click(w8);
			act8.sendKeys(Product);
			Thread.sleep(2000);
			act8.build().perform();				
			Thread.sleep(8000);
			driver.findElement(By.xpath("//ul[@class='lookup__list  visible']/descendant::li[2]")).click();
			Thread.sleep(10000);	
			
			
			//related list
			driver.findElement(By.xpath("//a[@class=\"tabHeader\"]/descendant::span[text()=\"Related\"]")).click();
			
			
			//Product PI
			
			WebElement element = driver.findElement(By.xpath("(//a//descendant::span[text()=\"Inventory Location Product Line Items\"])[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
			
			
			driver.findElement(By.xpath("//span[text()=\"Inventory Transactions\"]/following::span[@class=\"view-all-label\"]")).click();
			
			Thread.sleep(6000);	
			 JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
			 javascript1.executeScript("alert('SS60 GS BUlK will update with produced quantity through Manufacturing Run');");
			 Thread.sleep(8000);
			 driver.switchTo().alert().accept();
				
	}
	
	
	
	
}
	
	
	

