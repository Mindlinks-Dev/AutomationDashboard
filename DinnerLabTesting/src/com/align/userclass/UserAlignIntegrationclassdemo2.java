package com.align.userclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.alignsandbox.java.com.Base.AlignTestbase;
import com.alignsandbox.java.com.Base.IntegrationTestbase;



public class UserAlignIntegrationclassdemo2 extends IntegrationTestbase
{
	
	

	// Call Create_login.java class

	@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp",priority = 1)
	public static void Alignlogin(String URL,
			String Username, String Password, String UserName) throws MalformedURLException,
			InterruptedException
	{
			try
				{
					//Redirect to login page
					//driver.manage().window().maximize();
					driver.navigate().to(URL);
			         Thread.sleep(12000);
					
					//Enter the user name text field
					driver.findElement(By.xpath("//input[@name='username']")).click();
					driver.findElement(By.xpath("//input[@name='username']")).sendKeys(UserName);
					Thread.sleep(3000);
					 //Enter password
				     driver.findElement(By.xpath("(//input[@name='password'])[1]")).click();
				     driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys(Password);
				     Thread.sleep(3000);
				     
				     //Click login button
				     driver.findElement(By.xpath("//button[@class='button green']")).click();
					 log.debug("login successfull");
					 Thread.sleep(8000);
		
					
			
			 try
		     {
			 //driver.findElement(By.xpath("//div[@class='k-widget k-window radoloWindow']/div[2]/div/div[2]/descendant::button[text()='OK']")).click();
		    	 driver.findElement(By.xpath("//button[text()='OK']")).click();
		    	 Thread.sleep(3000);
		     }
		     catch(Exception e)
		     {  
		    	 e.printStackTrace();
		     }
						
	}
						
						catch(Exception e)
						{
							System.out.println("Login Unsuccessfull");
						}	
			System.out.println("Invalid username and password");
			
			//Enter the user name text field
			driver.findElement(By.xpath("//input[@name='username']")).clear();
			driver.findElement(By.xpath("//input[@name='username']")).click();
			driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Username);
			Thread.sleep(3000);
			
			//Enter password
			driver.findElement(By.xpath("(//input[@name='password'])[1]")).clear();
		     driver.findElement(By.xpath("(//input[@name='password'])[1]")).click();
		     driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys(Password);
		     Thread.sleep(3000);
		     
		   //Click login button
		     driver.findElement(By.xpath("//button[@class='button green']")).click();
			 log.debug("login successfull");
			 Thread.sleep(8000);
			
			}

				
		

	// create daily huddle
	@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 2)
	public static void CreateDailyHuddleGroup(String HuddleGroupName,String Note) throws MalformedURLException, InterruptedException {
		
		Thread.sleep(8000);
		
		try {
			// click on administrator dropdown
			driver.findElement(
					By.xpath("//nav[@class='nav navHeader']/div[1]/div[3]/ul[1]/li[5]"))
					.click();
			Thread.sleep(8000);

			// click on manage huddle group
			driver.findElement(
					By.xpath("//nav[@class='nav navHeader']/div[1]/div[3]/ul[1]/li[5]/div[1]/ul[1]/li[3]"))
					.click();
			Thread.sleep(12000);


			// click on create new daily huddle group button
			driver.findElement(
					By.xpath("//button[@title='Create New Huddle Group']"))
					.click();
			Thread.sleep(5000);

			// enter the huddle group name
			driver.findElement(By.xpath("//input[@placeholder='Name...']"))
					.sendKeys(HuddleGroupName);
			Thread.sleep(5000);

			// enter the note
			driver.findElement(
					By.xpath("(//ul[@class='form full no-style']/li)[2]/descendant::div[text()='Click or Tap to enter something...']"))
					.sendKeys(Note);
			Thread.sleep(5000);

			// enable the occur on weekend checkbox
			driver.findElement(By.xpath("//div[@class='checker']/span"))
					.click();
			Thread.sleep(5000);

			// click on save button
			driver.findElement(
					By.xpath("//button[@class='button green']/descendant::span[2]"))
					.click();
			Thread.sleep(5000);
		}

		catch (Exception e) {
			System.out.println("CreateDailyHuddle unsuccessfull");
		}
	}



//=================================================Create weekly huddle group=====================================================

    @Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 3)
      
    private static void CreateWeeklyHuddleGroup(String HuddleGroupName,String GeneralInformation, String Weekday) throws InterruptedException 
           {
    	
	          driver.manage().window().maximize();
	         Thread.sleep(5000);
	              
	         try
	          {
	            	   
		// click on administrator dropdown
		driver.findElement(By.xpath("//nav[@class='nav navHeader']/div[1]/div[3]/ul[1]/li[5]")).click();
		Thread.sleep(5000);

		// click on manage huddle group
		driver.findElement(By.xpath("//nav[@class='nav navHeader']/div[1]/div[3]/ul[1]/li[5]/div[1]/ul[1]/li[3]")).click();
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
		  Actions actions = new Actions(driver);
		  actions.moveToElement(driver.findElement(By.xpath("(//span[@class=\"k-input\"])[2]")));
		  actions.click();
		  Thread.sleep(6000); 
		  actions.sendKeys(Weekday);
		  Thread.sleep(6000); 
		  actions.click().perform();
		  Thread.sleep(5000);
		  
		/*  driver.findElement(By.xpath("(//span[@class='k-dropdown-wrap k-state-default'])[2]")).click();
		  Thread.sleep(5000);
		  */
          System.out.println("click on save");
		  //click on save button
		  driver.findElement(By.xpath("(//button[@class='button green']/span)[5]")).click();
		  Thread.sleep(5000);
		 
	
	}
		catch (Exception e) {
			System.out.println("weekly Unsucessful");
		}
	}  




		
							
//==================================================create priority==========================================================
		
       @Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 4)
           public static void CreatePriority(String PriorityName,String PriorityOwner, String ParentPriority, String Target,String currentvalue, String KPIStart, String KPI) throws InterruptedException, AWTException
{
	Thread.sleep(5000);
	
	try {
		 //click on priorities dropdown
	    driver.findElement(By.xpath("//nav[@class='nav navHeader']/div[1]/div[3]/ul/li[2]")).click();
	    Thread.sleep(8000);
	    	
		 
	    //click on Add priorities button
	    driver.findElement(By.xpath("//button[@class='button green']")).click();
	    Thread.sleep(5000);

	  //Enter the priority name text field
	    driver.findElement(By.xpath("(//input[@class='input'])[2]")).sendKeys(PriorityName);
	    Thread.sleep(5000);
	    
	  //Enter the priority owner field
	   // driver.findElement(By.xpath(".//*[@id='editPriorityDialogContent']/div[1]/ul[2]/li[1]/label/span/input")).sendKeys(PriorityOwner);
	 //   Thread.sleep(5000);
	    
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
	    
	    
	    //click on save buttons
	      driver.findElement(By.xpath("(//button[@class='button green'])[3]/descendant::span[2]")).click();
	      Thread.sleep(5000);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	      
	System.out.println("Enter  priority owner field");
	
	//click ok
	driver.findElement(By.xpath("//button[text()='OK']")).click();
	
	  //Enter the priority owner field
	  driver.findElement(By.xpath(".//*[@id='editPriorityDialogContent']/div[1]/ul[2]/li[1]/label/span/input")).sendKeys(PriorityOwner);
	  Thread.sleep(5000);
	  
	  //click on save button
      driver.findElement(By.xpath("(//button[@class='button green'])[3]/descendant::span[2]")).click();
      Thread.sleep(5000);
	

}
	  

}


