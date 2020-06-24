package com.stapp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

	
public class LocationCreation extends Testbase
{
		@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 1)
		public static void Location (String LocationName,String Companyname,String Zonename, String BinName, String LocationBarcode,String LocationsTab, String Basket,String BasketBarcode) throws InterruptedException 
		{
	        driver.manage().window().maximize();
			Thread.sleep(8000);
			
			
			try
			{
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@class='slds-button']")).isDisplayed(); //lightning element Home Button
				{ 
					Thread.sleep(8000);
					System.out.println("Switched to Lightntning Page Button");
					//driver.findElement(By.xpath("//a[@title='Payment Run']")).click(); 
					System.out.println("clicked in else if");
				}	
			}	
			catch(Exception e)
			{
			   e.printStackTrace();
			   driver.findElement(By.xpath("(//a[text()='Switch to Lightning Experience'])[1]")).isDisplayed(); //classic element
			  {    
				  Thread.sleep(3000);
				 System.out.println("before click");
				  driver.findElement(By.xpath("(//a[text()='Switch to Lightning Experience'])[1]")).click();   
				  System.out.println("clicked"); 
				  Thread.sleep(8000);
			//driver.findElement(By.xpath("//a[@title='Payment Run']")).click(); 
			  }	
			}
			
			System.out.println("clicks on locations tab");
			//click on locations tab
			//driver.findElement(By.xpath("(//div[@class=\"slds-grid slds-has-flexi-truncate navUL\"]//span[@class=\"slds-truncate\"])[9]")).click();
			driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[10]")).click();
			Thread.sleep(4000);
			System.out.println("locaton saved");
			
/*//			System.out.println(" Clicking on new Button");
//			//click on new button
//			driver.findElement(By.xpath("(//ul[@class='branding-actions slds-button-group slds-m-left--xx-small oneActionsRibbon forceActionsContainer']//li)[1]")).click();
//			Thread.sleep(5000);
//			System.out.println("Done with clicking new");
//			
//			System.out.println("Clicking on new location");
//			//clicking on Location Button
//			driver.findElement(By.xpath("((((//div[@class='changeRecordTypeRow'])[1])//label//div[@class='changeRecordTypeOptionLeftColumn'])//span[@class='slds-radio--faux'])[1]")).click();
//			Thread.sleep(5000);
//			System.out.println("Done with clicking location");
//			
//			System.out.println("clicking on location tab");
//			//clicking on location tab
//			driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton']")).click();
//			Thread.sleep(5000);
//			System.out.println("Done with clicking on location tab");
//			
//			System.out.println("Providing location name");
//			//Providing location name
//			driver.findElement(By.xpath("//div[@class='slds-form-element slds-hint-parent']//input")).sendKeys(LocationName);
//			Thread.sleep(5000);
//			System.out.println("Done with Providing location name");
//			
//			System.out.println("clicking on save Button");
//			//clicking on save Button
//			driver.findElement(By.xpath("(//div[@class='modal-footer slds-modal__footer']//span[@class=' label bBody'])[3]")).click();
//			Thread.sleep(5000);
//			System.out.println("done with clicking on save Button");
//			
//			
//			System.out.println("click on related");
//			//click on related
//			driver.findElement(By.xpath("//div[@class='uiTabBar']//ul//li//span[text()='Related']")).click();
//			Thread.sleep(5000);
//			System.out.println("Done with click on related ");
//			
//			System.out.println("click on Inventory location new");
//			//click on Inventory location new
//			driver.findElement(By.xpath("//div[@class='slds-card__header slds-grid']//ul//li//a[@title='New']")).click();
//			Thread.sleep(3000);
//			System.out.println("Done with click on Inventory location new");
//			
//			
//			System.out.println("click on company creation");
//			//click on company creation
//			WebElement w1=driver.findElement(By.xpath("//span[text()=\"Company\"]/following::input"));
//			System.out.println("Exit Company");
//			
//			w1.click();
//		    w1.sendKeys(Companyname);
//			Thread.sleep(3000);
//			
//			driver.findElement(By.xpath("//div[@class=\"listContent\"]//ul[1]")).click();
//			Thread.sleep(3000);
//			
//			//click on save button 
//			driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton']//span[text()='Save']")).click();
//			Thread.sleep(3000);
//			
//			System.out.println("clicking on Related IL for Bin and Zone ");
//			//clicking on Related IL for Bin and Zone
//			driver.findElement(By.xpath("(//div[@class='outputLookupContainer forceOutputLookupWithPreview']/descendant::a)[7]")).click();
//			Thread.sleep(4000);
//			System.out.println("Done with clicking on Related IL for Bin and Zone ");
//			
//			
//			System.out.println("clicking on new button under location sub levels");
//			//clicking on related button under location sub levels
//			driver.findElement(By.xpath("(//div[@role='tablist']//ul//li)[6]")).click();
//			Thread.sleep(3000);
//			System.out.println("Done with clicking on related button under location sub levels");
//			
//			
//			System.out.println("//clicking on new button in location Sublevels");
//			driver.findElement(By.xpath("(//div[@class='slds-card__header slds-grid']//div[@class='slds-no-flex']//ul//li)[9]")).click();
//			Thread.sleep(3000);
//			System.out.println("Done with clicking on new button in location Sublevels");
//			
//			 System.out.println("clicking on Zone  button");
//			 driver.findElement(By.xpath("(//div[@class='changeRecordTypeRow']//div[@class='changeRecordTypeOptionLeftColumn']//span[@class='slds-radio--faux'])[2]")).click();
//			 Thread.sleep(3000);
//			 System.out.println(" done with clicking on Zone button");
//			 
//			 System.out.println("clicking on next button");
//			 driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button//span[text()='Next']")).click();
//			 Thread.sleep(3000);
//			 System.out.println("Done with clicking on next button");
//
//			 
//			 System.out.println("clicking on Name ");
//			 WebElement Wb3=driver.findElement(By.xpath("(//div[@class='slds-form-element slds-hint-parent'])[1]//descendant::input"));
//			 Wb3.sendKeys(Zonename);
//			 System.out.println("Done clicking on Name ");
//			 
//			 
//			System.out.println("clicking on type");
//			//clicking on type
//			driver.findElement(By.xpath("(//div[@class='uiMenu'])[8]")).click();
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("(//div[@class='select-options']//ul//li)[3]//a[.='ZONE']")).click();
//			//driver.findElement(By.xpath("//div[@class='select-options popupTargetContainer uiPopupTarget uiMenuList uiMenuList--default uiMenuList--left uiMenuList--short']//ul//li[2]//a[@title='BIN']")).click();
//			System.out.println("Done with clicking on type");
//			
//			
//			 System.out.println("clicking on save");
//			 //clicking on save	
//			 driver.findElement(By.xpath("(//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']//button)[3]")).click();
//			 Thread.sleep(5000);
//
//			  
//			System.out.println("Clicking on new after zone creation");
////			selecting new
//			driver.findElement(By.xpath("(//div[@class='slds-card__header slds-grid']/descendant::ul/li)[9]")).click();
//			Thread.sleep(3000);
//			System.out.println("Done with Clicking on new");
//			
//			
//			System.out.println("Clicking on Bin");
//			//selecting bin icon
//			driver.findElement(By.xpath("(//label[@class='slds-radio']/descendant::span)[1]")).click();
//			Thread.sleep(3000);
//			System.out.println("Done with selecting Bin icon");
//			
//			
//			System.out.println("clicking on next button");
//			//clicking on next button
//			driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button//span[text()='Next']")).click();
//			Thread.sleep(3000);
//			System.out.println("Done with clicking on next button");
//			 
//			
//			System.out.println("clicking on name in Bin");
//			//clicking on name in Bin
//			 WebElement wb1 = driver.findElement(By.xpath("(//div[@class='slds-form-element__control']//input)[4]"));
//			Thread.sleep(3000);
//			wb1.sendKeys(BinName);
//			System.out.println("Done With clicking on name in Bin");
//			
//			
//			System.out.println("clicking on type");
//			//clicking on type
//			driver.findElement(By.xpath("(//div[@class='uiMenu'])[8]")).click();
//			Thread.sleep(3000);
//			//driver.findElement(By.xpath("(//div[@class='select-options']//ul//li)[2]//a[.='BIN']")).click();
//			driver.findElement(By.xpath("//div[@class='select-options']//ul//li//descendant::a[@title='BIN']")).click();
//			Thread.sleep(3000);
//			//driver.findElement(By.xpath("//div[@class='select-options popupTargetContainer uiPopupTarget uiMenuList uiMenuList--default uiMenuList--left uiMenuList--short']//ul//li[2]//a[@title='BIN']")).click();
//			System.out.println("Done with clicking on type");
//			Thread.sleep(3000);
//		
//			
//			
//			System.out.println("entering bar code");
//		     WebElement wb2 = driver.findElement(By.xpath("(//div[@class='slds-form-element slds-hint-parent'])[5]/descendant::input"));
//		     wb2.sendKeys(LocationBarcode);
//		     Thread.sleep(3000);
//			 System.out.println("Done with entering Bar code");
//			 
//			 
//			 System.out.println("Selecting Zone for Bin");
//			//selecting zone location
//			 driver.findElement(By.xpath("(//div[@class='autocompleteWrapper slds-grow'])[3]")).click();
//			 Thread.sleep(3000);
//			 driver.findElement(By.xpath("(//div[@class='listContent']//ul)[1]")).click();
//			 Thread.sleep(3000);
//			 
//			 
//			 //clicking on usage
//			 System.out.println("clicking on usage");
//			 driver.findElement(By.xpath("(//div[@class='slds-form-element__control'])[10]//descendant::a[@class='select']")).click();
//			 Thread.sleep(3000);
//			 System.out.println("Done clicking on usage");
//			 
//			 
//			 //selecting the location
//			 System.out.println("selecting the usage");
//			 driver.findElement(By.xpath("((//div[@class='select-options'])[2]//ul//li)[2]")).click();
//			 Thread.sleep(3000);
//			 System.out.println("Done with selecting the usage");
//			 
//			 System.out.println("clicking on save");
//			 //clicking on save	
//			 driver.findElement(By.xpath("(//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']//button)[3]")).click();
//			 Thread.sleep(5000);
//			 
//			 //opening app launcher for Locations Click
//			 
//			 Click("//button[@class='slds-button']"); 
//			 Thread.sleep(5000);
//			 driver.findElement(By.xpath("//div[@class=\"modal-header slds-modal__header\"]/descendant::div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"]/input" )).sendKeys(LocationsTab); 
//			 Thread.sleep(3000);
//			 Click("(//ul[@class='slds-grid slds-grid--pull-padded slds-wrap list']/descendant::mark[text()='Locations'])[2]" );
//			 Thread.sleep(10000);
//			 System.out.println(" Clicking on new Button");
//				
//			
//			   //click on new button
//				driver.findElement(By.xpath("(//ul[@class='branding-actions slds-button-group slds-m-left--xx-small oneActionsRibbon forceActionsContainer']//li)[1]")).click();
//				Thread.sleep(5000);
//				System.out.println("Done with clicking new");
//				
//				System.out.println("Clicking on basket option");
//				
//				
//				//clicking on Location Button
//				driver.findElement(By.xpath("((((//div[@class='changeRecordTypeRow'])[1])//label//div[@class='changeRecordTypeOptionLeftColumn'])//span[@class='slds-radio--faux'])[2]")).click();
//				Thread.sleep(5000);
//				System.out.println("Done with clicking location");
//				
//				System.out.println("clicking on next button");
//				//clicking on next button
//				driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button//span[text()='Next']")).click();
//				Thread.sleep(3000);
//				System.out.println("Done with clicking on next button");
//				
//				
//				System.out.println("Providing Basket name");
//				//Providing Basket name
//				WebElement wb4 = driver.findElement(By.xpath("(//label[@class='label inputLabel uiLabel-left form-element__label uiLabel'])[1]/following::input[1]"));
//				wb4.click();
//				wb4.sendKeys(Basket);
//				System.out.println("Done with Providing Basket name");
//				
//				
//				System.out.println("Providing Basket Barcode");
//				//Providing Basket bar code no
//				WebElement wb5 =driver.findElement(By.xpath("(//label[@class='label inputLabel uiLabel-left form-element__label uiLabel'])[1]/following::input[2]"));
//				wb5.click();
//				wb5.sendKeys(BasketBarcode);
//				System.out.println("Done Providing Basket Barcode");
//				
//				System.out.println("clicking on save Button");
//				//clicking on save Button
//				driver.findElement(By.xpath("(//div[@class='modal-footer slds-modal__footer']//span[@class=' label bBody'])[3]")).click();
//				Thread.sleep(5000);
//				System.out.println("done with clicking on save Button");
//			 
*/			 
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		 
			 
		/*	  driver.findElement(By.xpath("//div[@class='more-item slds-context-bar__item slds-context-bar__dropdown-trigger slds-shrink-none']//span[.='More']")).click();
			 Thread.sleep(5000);
		 * System.out.println("clicks on locations tab");
				//click on locations tab
				//driver.findElement(By.xpath("(//div[@class=\"slds-grid slds-has-flexi-truncate navUL\"]//span[@class=\"slds-truncate\"])[9]")).click();
				driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[10]")).click();
				Thread.sleep(4000);
				System.out.println("locaton saved");
				
			
				System.out.println(" Clicking on new Button");
				//click on new button
				driver.findElement(By.xpath("(//ul[@class='branding-actions slds-button-group slds-m-left--xx-small oneActionsRibbon forceActionsContainer']//li)[1]")).click();
				Thread.sleep(5000);
				System.out.println("Done with clicking new");
				
				System.out.println("Clicking on basket option");
				//clicking on Location Button
				driver.findElement(By.xpath("((((//div[@class='changeRecordTypeRow'])[1])//label//div[@class='changeRecordTypeOptionLeftColumn'])//span[@class='slds-radio--faux'])[2]")).click();
				Thread.sleep(5000);
				System.out.println("Done with clicking location");
				
				System.out.println("clicking on next button");
				//clicking on next button
				driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button//span[text()='Next']")).click();
				Thread.sleep(3000);
				System.out.println("Done with clicking on next button");o
				
				
				System.out.println("Providing Basket name");
				//Providing Basket name
				WebElement wb4 = driver.findElement(By.xpath("(//label[@class='label inputLabel uiLabel-left form-element__label uiLabel'])[1]/following::input[1]"));
				wb4.click();
				wb4.sendKeys(Basket);
				System.out.println("Done with Providing Basket name");
				
				
				System.out.println("Providing Basket Barcode");
				//Providing Basket bar code no
				WebElement wb5 =driver.findElement(By.xpath("(//label[@class='label inputLabel uiLabel-left form-element__label uiLabel'])[1]/following::input[2]"));
				wb5.click();
				wb5.sendKeys(BasketBarcode);
				System.out.println("Done Providing Basket Barcode");
				
				System.out.println("clicking on save Button");
				//clicking on save Button
				driver.findElement(By.xpath("(//div[@class='modal-footer slds-modal__footer']//span[@class=' label bBody'])[3]")).click();
				Thread.sleep(5000);
				System.out.println("done with clicking on save Button");*/
		}
		
	}

