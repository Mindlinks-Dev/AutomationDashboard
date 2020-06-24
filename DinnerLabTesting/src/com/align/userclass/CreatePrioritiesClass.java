package com.align.userclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.alignsandbox.java.com.Base.AlignTestbase;


public class CreatePrioritiesClass extends AlignTestbase
{
	@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp", priority=1)
	
	 public static void CreatePriority(String PriorityName,String PriorityOwner, String ParentPriority, String Target,String currentvalue, String KPIStart, String KPI) throws InterruptedException, AWTException
	{
		Thread.sleep(5000);
		
		
			 //click on priorities dropdown
		    driver.findElement(By.xpath("//nav[@class='nav navHeader']/div[1]/div[2]/ul/li[2]")).click();
		    Thread.sleep(8000);
		    	
			 
			 //alert after created
			 JavascriptExecutor javascriptb = (JavascriptExecutor) driver;
			 javascriptb.executeScript("alert('Create the priorities');");
			 Thread.sleep(3000);
			 driver.switchTo().alert().accept();
			 Thread.sleep(6000);
			 
		    //click on Add priorities button
		    driver.findElement(By.xpath("//button[@class='button green']")).click();
		    Thread.sleep(5000);

		  //Enter the priority name text field
		    driver.findElement(By.xpath("(//input[@class='input'])[2]")).sendKeys(PriorityName);
		    Thread.sleep(5000);
		    
		  //Enter the priority owner field
		    driver.findElement(By.xpath(".//*[@id='editPriorityDialogContent']/div[1]/ul[2]/li[1]/label/span/input")).sendKeys(PriorityOwner);
		    Thread.sleep(5000);
		    
		  //Enter the parent priority field
		    driver.findElement(By.xpath(".//*[@id='editPriorityDialogContent']/div[1]/ul[2]/li[2]/label/span/input")).sendKeys(ParentPriority);
		    Thread.sleep(5000);
		    
		  //select the KPI dropdown field
		   
		    Actions actions = new Actions(driver);
		    actions.moveToElement(driver.findElement(By.xpath("//div[@class='k-content k-state-active']/ul[1]/li/label/span/span/span[1]")));
		    actions.click();
		    Thread.sleep(12000);
		    actions.sendKeys(KPI);
		    actions.build().perform();
		    Thread.sleep(5000);
		    
		  //select the target field
		    driver.findElement(By.xpath("(//span[@class='k-numeric-wrap k-state-default']/input)[1]")).sendKeys(Target);
		    Thread.sleep(5000);
		    
		  //select the current value field
		    driver.findElement(By.xpath("(//input[@class='k-formatted-value input k-input'])[2]")).sendKeys(currentvalue);
		    Thread.sleep(5000);
		    
		    
		  //select the security priority checkbox
		    driver.findElement(By.xpath("(//div[@class='checker']/span)[3]")).click();
		    Thread.sleep(5000);
		 
		    
		  //select the decreasing checkbox
		    driver.findElement(By.xpath("(//div[@class='checker']/span)[4]")).click();
		    Thread.sleep(5000);
		    
		  //select the KPI start field
		    driver.findElement(By.xpath("((//span[@class='k-numeric-wrap k-state-default'])[3]/input)[1]")).sendKeys(KPIStart);
		    Thread.sleep(5000); 
		    
		    
		    //click on save button
		      driver.findElement(By.xpath("(//button[@class='button green'])[3]/descendant::span[2]")).click();
		      Thread.sleep(5000);
		      
		      
		      //refresh the page
				 driver.navigate().refresh();
				 Thread.sleep(8000);

	}
		      //-----------------------Edit Flow-----------------------------------

	  @Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp", priority = 10)

	public static void EditPriority(String PriorityName,String EditPriorityName,String EditKPI) throws InterruptedException, AWTException

			{
		      //alert before edit
		         Thread.sleep(5000);
				 JavascriptExecutor javascripta = (JavascriptExecutor) driver;
				 javascripta.executeScript("alert('Edit the Priorities');");
				 Thread.sleep(3000);
				 driver.switchTo().alert().accept();
				 Thread.sleep(6000);
			
				 //click on arrow button
			  		    driver.findElement(By.xpath("(//div[@class='k-top']/span)[1]")).click();
			  		    Thread.sleep(6000);
			  		      		  		 
						
			  		    //click on edit button	  		    
			  	 		String name =PriorityName;   
			  	 		Actions actionsedit = new Actions(driver);
				  		WebElement Menu = driver.findElement(By.xpath("(//div[@class='tree k-widget k-treeview']/ul/descendant::h3[starts-with(normalize-space(text( )) ,'"+name+"')]/ancestor::span/descendant::button)[5]/span"));
				  		actionsedit.moveToElement(Menu);
				  		System.out.println("subMenu");
				  		actionsedit.click().build().perform();
				  		Thread.sleep(8000);		 		
				  		
			 	        //Enter the priority name text field
				  		driver.findElement(By.xpath("(//input[@class='input'])[2]")).click();	
		  		    driver.findElement(By.xpath("(//input[@class='input'])[2]")).clear();
		  		    driver.findElement(By.xpath("(//input[@class='input'])[2]")).sendKeys(EditPriorityName);
		  		    System.out.print("priority name");
		  		    Thread.sleep(5000);
		  		    
		  		    
		  	       //select the KPI dropdown field		  		    
		  		   Actions actions1 = new Actions(driver);
		  		    actions1.moveToElement(driver.findElement(By.xpath("//div[@class='k-content k-state-active']/ul[1]/li/label/span/span/span[1]")));
		  		    actions1.click();
		  		    Thread.sleep(8000);
		  		    actions1.sendKeys(EditKPI);
		  		    actions1.build().perform();
		  		    System.out.print("select kpi");
		  		    Thread.sleep(5000);
		  		    
		  		  //select the target field	  
		  		   driver.findElement(By.xpath("(//span[@class='k-icon k-i-arrow-60-up'])[1]")).click();	   
		  		    System.out.println("select target");
		  		    Thread.sleep(5000);
		  		    
		  		  //select the current value field			  		    
		  		   driver.findElement(By.xpath("(//span[@class='k-icon k-i-arrow-60-up'])[2]")).click();;
		  		    System.out.println("select current value");
		  		    Thread.sleep(5000);
		  		    		 
		  		    
		  		   //select the KPI start field		
		  		    driver.findElement(By.xpath("(//span[@class='k-icon k-i-arrow-60-up'])[3]")).click();
		  		    Thread.sleep(5000); 
		  		   
		  		    
		  		    //click on save button
		  		      driver.findElement(By.xpath("(//button[@class='button green'])[3]/descendant::span[2]")).click();

			} 
		  		      
		  	
		  			
		  			
		  		//-----------------------delete Flow-----------------------------------
		  		
	  @Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp", priority = 11)
		
		public static void deletePriority(String DeletePriority) throws InterruptedException, AWTException

				{
		           Thread.sleep(5000);

			        // refresh the page
			        driver.navigate().refresh();
			        Thread.sleep(10000);
			 
		  			//alert before delete
					 JavascriptExecutor javascriptc = (JavascriptExecutor) driver;
					 javascriptc.executeScript("alert('Delete the Priorities');");
					 Thread.sleep(3000);
					 driver.switchTo().alert().accept();
					 Thread.sleep(8000);
					 
		  				  						  			
		  		//click on arrow button
		  		driver.findElement(By.xpath("(//div[@class='k-top']/span)[1]")).click();
		  		Thread.sleep(10000);
		  		      		  		 
					
		  		//click on delete button	  		    
		  		Actions actionsdelete = new Actions(driver);
		  		WebElement mainMenu = driver.findElement(By.xpath("(//div[@class='tree k-widget k-treeview']/ul/descendant::h3[starts-with(normalize-space(text( )) ,'"+DeletePriority+"')]/ancestor::span/descendant::button)[5]/span"));
		  		actionsdelete.moveToElement(mainMenu).perform();
		  		System.out.println("mainMenu");
		  		Thread.sleep(8000);
		  		
		  		
		  		Actions actionsclick = new Actions(driver);
		  		WebElement subMenu = driver.findElement(By.xpath("(//div[@class='tree k-widget k-treeview']/ul/descendant::h3[starts-with(normalize-space(text( )) ,'"+DeletePriority+"')]/ancestor::span/descendant::button)[1]/span"));
		  		actionsclick.moveToElement(subMenu);
		  		System.out.println("subMenu");
		  		actionsclick.click().build().perform();
		  		Thread.sleep(5000);
		  		
		  	    //click on yes in "confirm delete" alert
		  		driver.findElement(By.xpath("//div[@class=\"k-content kendo-confirm\"]/descendant::button[text()=\"Yes\"]")).click();
		  		    		      
		  		
	    }
}
