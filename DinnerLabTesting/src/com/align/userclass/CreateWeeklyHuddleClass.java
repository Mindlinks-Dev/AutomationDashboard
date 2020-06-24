package com.align.userclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.alignsandbox.java.com.Base.AlignTestbase;


public class CreateWeeklyHuddleClass extends AlignTestbase
{
	@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp",priority=1)
	private static void CreateWeeklyHuddleGroup(String HuddleGroupName,String GeneralInformation, String Weekday) throws InterruptedException 
	{
		driver.manage().window().maximize();
		Thread.sleep(5000);
		try
		{
			// click on administrator dropdown
			driver.findElement(By.xpath("//nav[@class='nav navHeader']/div[1]/div[2]/ul[1]/li[5]")).click();
			Thread.sleep(5000);

			// click on manage huddle group
			driver.findElement(By.xpath("//nav[@class='nav navHeader']/div[1]/div[2]/ul[1]/li[5]/div[1]/ul[1]/li[3]")).click();
			Thread.sleep(6000);
		 
			 // Scroll down to bottom
			driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]")).click();
			Thread.sleep(4000);
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_SPACE);
			rb.keyRelease(KeyEvent.VK_SPACE);
			System.out.println("scroll");
			
			 //alert after created
			 JavascriptExecutor javascriptb = (JavascriptExecutor) driver;
			 javascriptb.executeScript("alert('Create the Weekly Huddle');");
			 Thread.sleep(3000);
			 driver.switchTo().alert().accept();
			 Thread.sleep(6000);
			 
			  // click on create new weekly huddle group button
			  driver.findElement
			  (By.xpath("//button[@title='Create New Weekly Huddle Group']")).click();
			  Thread.sleep(5000);
			  
			  //enter the huddle group name
			  driver.findElement(By.xpath("//input[@placeholder='Name...']")).sendKeys(HuddleGroupName);
			  Thread.sleep(5000);
			  
			  //enter the general information 
			  driver.findElement(By.xpath("(//ul[@class='form full no-style']/li)[2]/descendant::div[text()='Click or Tap to enter something...']")).sendKeys(GeneralInformation);
			  Thread.sleep(5000);
			  
			    
			  //select the weekend dropdown 
			  Actions actions = new
			  Actions(driver);
			  actions.moveToElement(driver.findElement(By.xpath("//div[@data-bind='display: showWeekdayDropdown']")));
			  actions.click(); Thread.sleep(2000); 
			  actions.sendKeys(Weekday);
			  Thread.sleep(2000); 
			  actions.click().perform();
			  Thread.sleep(5000);
			  
			/*  driver.findElement(By.xpath("(//span[@class='k-dropdown-wrap k-state-default'])[2]")).click();
			  Thread.sleep(5000);
			  */
	          System.out.println("click on save");
			  //click on save button
			  driver.findElement(By.xpath("(//button[@class='button green']/span)[5]")).click();
			  Thread.sleep(5000);
			 
				// scroll up alert
				JavascriptExecutor javascriptd = (JavascriptExecutor) driver;
				javascriptd.executeScript("alert('Scrolling Up');");
				Thread.sleep(3000);
				driver.switchTo().alert().accept();
				Thread.sleep(5000);
				
				
				// Scroll up to bottom
				driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]")).click();
				Thread.sleep(5000);
				Robot rb1 = new Robot();
				rb1.keyPress(KeyEvent.VK_PAGE_UP);
				rb1.keyRelease(KeyEvent.VK_PAGE_UP);
				System.out.println("scroll"); 
				
			// scroll down alert
			JavascriptExecutor javascript = (JavascriptExecutor) driver;
			javascript.executeScript("alert('Scrolling Down');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(4000);

			// Scroll down to bottom
			driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]"))
					.click();
			Thread.sleep(4000);
			Robot rb2 = new Robot();
			rb2.keyPress(KeyEvent.VK_SPACE);
			rb2.keyPress(KeyEvent.VK_SPACE);
			rb2.keyRelease(KeyEvent.VK_SPACE);
			System.out.println("scroll");

		
		}
			catch (Exception e) {
				System.out.println("weekly Unsucessful");
			}
		}  


			// -----------------------EditFlow-----------------------------------
			
			
			@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp",priority = 2)
			private static  void EditWeeklyHuddleGroup(String HuddleGroupName,String EditHuddleGroupName, String EditGeneralInformation) throws InterruptedException 
					
					{
			  Thread.sleep(5000);
			//alert before edit
				 JavascriptExecutor javascripta = (JavascriptExecutor) driver;
				 javascripta.executeScript("alert('Edit the Weekly Huddle');");
				 Thread.sleep(3000);
				 driver.switchTo().alert().accept();
				 Thread.sleep(6000);
				 
				 
			  //click on edit button	  		    
	  	 		 String name =HuddleGroupName;   
		  		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[2]/descendant::td[text()='"+name+"']/following-sibling::td)[2]/descendant::span"));		 		 	
		  		Thread.sleep(2000);
		  		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[2]/descendant::td[text()='"+name+"']/following-sibling::td)[2]/descendant::span")).click();	
			    Thread.sleep(5000);
			  
			  //enter the huddle group name
			  driver.findElement(By.xpath("//input[@placeholder='Name...']")).clear(); Thread.sleep(2000);
			  driver.findElement(By.xpath("//input[@placeholder='Name...']")).sendKeys(EditHuddleGroupName); Thread.sleep(5000);
			  Thread.sleep(5000);
			  
			  
			  //enter the general information 
			  driver.findElement(By.xpath("//div[@class='textarea k-widget k-editor k-editor-inline']")).clear();
			  Thread.sleep(2000);
			  driver.findElement(By.xpath("//div[@data-role='radoloeditor']")).sendKeys(EditGeneralInformation); Thread.sleep(5000);
			  Thread.sleep(5000);
			  
			  //click on save button 
			  driver.findElement(By.xpath("(//button[@class='button green']/span)[5]")).click();		 
					}
			  
			  
			  
			  
			  //-----------------------delete Flow-----------------------------------
			@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp",priority = 3)
			private static void DeleteWeeklyHuddleGroup(String DeleteweeklyHuddle) throws InterruptedException, AWTException 
					
					{
				  Thread.sleep(5000);
				// Scroll down to bottom
					driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]")).click();
					Thread.sleep(4000);
					Robot rb = new Robot();
					rb.keyPress(KeyEvent.VK_SPACE);
					rb.keyRelease(KeyEvent.VK_SPACE);
					System.out.println("scroll");
				  
				  
			  //alert before delete
				 JavascriptExecutor javascriptc = (JavascriptExecutor) driver;
				 javascriptc.executeScript("alert('Delete the Weekly Huddle');");
				 Thread.sleep(3000);
				 driver.switchTo().alert().accept();
			     Thread.sleep(6000);
			     
			     
		      //click on delete button	  		    
	  	 		 String delete =DeleteweeklyHuddle;   
		  		 driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[2]/descendant::td[text()='"+delete+"']/following-sibling::td)[3]/descendant::span"));		 		 	
		  		Thread.sleep(2000);
		  		 driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[2]/descendant::td[text()='"+delete+"']/following-sibling::td)[3]/descendant::span")).click();	
			     Thread.sleep(5000); 
			     
			  //click on yes in "confirm delete" alert
			     driver.findElement(By.xpath("//div[@class=\"k-content kendo-confirm\"]/descendant::button[text()=\"Yes\"]")).click();
		}

	
	}

	
