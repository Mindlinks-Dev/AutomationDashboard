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



public class UserAlignIntegrationclass extends IntegrationTestbase
{
	
	@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 1)
	public static void AlignSignup(String SignupURL,String CompanyName, String Email, String FirstName,String LastName, String PhoneNumber,String CreatePassword,String PromoCode, String CoachsName,String PhoneArea,String Ext)throws InterruptedException
	{
		driver.manage().window().maximize();
		Thread.sleep(5000l);
	  	{

	     			//Redirect to signup page
	     			driver.get(SignupURL);
	     			Thread.sleep(8000);
	     			Thread.sleep(8000);
	     			
	     			//alert after created
	   			 JavascriptExecutor javascriptb = (JavascriptExecutor) driver;
	   			 javascriptb.executeScript("alert('Signup for Trial Account');");
	   			 Thread.sleep(3000);
	   			 driver.switchTo().alert().accept();
	   			 Thread.sleep(6000);
	     			
	     			//Enter the company name text field
	     			//driver.findElement(By.xpath("//input[@data-bind='value: newAccount.Company.Name']")).sendKeys(CompanyName);
	     			driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[1]/label[1]/input[1]")).click();
	     			driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[1]/label[1]/input[1]")).sendKeys(CompanyName);
	     			Thread.sleep(5000);
	     			
	     			//Enter the Email id text field
	     			//driver.findElement(By.xpath("//input[@data-bind='value: newAccount.Email, events: { change: validateEmail }'']")).click();
	     			driver.findElement(By.xpath("//input[@type='email']")).click();
	     			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Email);
	     			Thread.sleep(5000);
	     			
	     			 //Enter the first name
	     		     driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[2]/label[1]/input[1]")).click();
	     		     driver.findElement(By.xpath("//ul[@class='form full no-style form-columns']/li[2]/label[1]/input[1]")).sendKeys(FirstName);
	     		     Thread.sleep(5000);
	     		
	     		    //Enter the last name
	     		     driver.findElement(By.xpath("//input[@data-bind='value: newAccount.LastName']")).click();
	     		     driver.findElement(By.xpath("//input[@data-bind='value: newAccount.LastName']")).sendKeys(LastName);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the phone area
	     		     driver.findElement(By.xpath("//input[@placeholder='(Area)']")).click();
	     		     driver.findElement(By.xpath("//input[@placeholder='(Area)']")).sendKeys(PhoneArea);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the phone number
	     		     driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).click();
	     		     driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys(PhoneNumber);
	     		     Thread.sleep(5000);
	     		     
	     		  //Enter the EXT.
	     		     driver.findElement(By.xpath("//input[@placeholder='Ext.']")).click();
	     		     driver.findElement(By.xpath("//input[@placeholder='Ext.']")).sendKeys(Ext);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the password
	     		     driver.findElement(By.xpath("(//input[@placeholder='Password'])[1]")).click();
	     		     driver.findElement(By.xpath("(//input[@placeholder='Password'])[1]")).sendKeys(CreatePassword);
	     		     Thread.sleep(5000);
	     		
	     		    //enable the show password check box
	                  WebElement oCheckBox = driver.findElement(By.xpath("//div[@class='checker']/span")); 
	                  oCheckBox.click();
	     		     //driver.findElement(By.xpath("(//div[@class='checker']/span/input")).click();
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the promo code
	     		     driver.findElement(By.xpath("//input[@data-bind='value: promoCode']")).click();
	     		     driver.findElement(By.xpath("//input[@data-bind='value: promoCode']")).sendKeys(PromoCode);
	     		     Thread.sleep(5000);
	     		     
	     		     //Enter the coach name
	     		     driver.findElement(By.xpath("//input[@data-bind='value: coachNameAtSignUp']")).click();
	     		     driver.findElement(By.xpath("//input[@data-bind='value: coachNameAtSignUp']")).sendKeys(CoachsName);
	     		     Thread.sleep(5000);
	     		     
	     		     // click on recaptcha checkbox
	     		     WebElement frame = driver.findElement(By.xpath("(//iframe[contains(@src, 'recaptcha')])[1]")); 
	     		     driver.switchTo().frame(frame);
	     		     WebElement checkbox = driver.findElement(By.xpath(".//*[@id='recaptcha-anchor']/div[5]"));
	     		     checkbox.click();     		     
	     		    Thread.sleep(8000);
	     		}
	     		
	     	}

	// Call Create_login.java class

	@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp",priority = 2)
	public static void Alignlogin(String URL,
			String Username, String Password) throws MalformedURLException,
			InterruptedException
	{
			try
				{
					//Redirect to login page
					driver.manage().window().maximize();
					driver.navigate().to(URL);
			         Thread.sleep(12000);
					
					//Enter the user name text field
					driver.findElement(By.xpath("//input[@name='username']")).click();
					driver.findElement(By.xpath("//input[@name='username']")).sendKeys(Username);
					Thread.sleep(3000);
					 //Enter password
				     driver.findElement(By.xpath("(//input[@name='password'])[1]")).click();
				     driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys(Password);
				     Thread.sleep(3000);
				     
				     //Click login button
				     driver.findElement(By.xpath("//button[@class='button green']")).click();
					 log.debug("login successfull");
					 Thread.sleep(3000);
					 
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
				           
					// alert after created
						JavascriptExecutor javascriptb = (JavascriptExecutor) driver;
						javascriptb.executeScript("alert('Open Manage Huddle Group Page');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						Thread.sleep(4000);
					}
						
			
						
						catch(Exception e)
						{
							System.out.println("Login Unsuccessfull");
						}	
			}

				
		

	// create daily huddle
	@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 3)
	public static void CreateDailyHuddleGroup(String HuddleGroupName,
			String Note) throws MalformedURLException, InterruptedException {
		try {
			// click on administrator dropdown
			driver.findElement(
					By.xpath("//nav[@class='nav navHeader']/div[1]/div[2]/ul[1]/li[5]"))
					.click();
			Thread.sleep(7000);

			// click on manage huddle group
			driver.findElement(
					By.xpath("//nav[@class='nav navHeader']/div[1]/div[2]/ul[1]/li[5]/div[1]/ul[1]/li[3]"))
					.click();
			Thread.sleep(5000);

			// alert after created
			JavascriptExecutor javascriptb = (JavascriptExecutor) driver;
			javascriptb.executeScript("alert('Create the Daily Huddle');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(6000);

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

			// scroll down alert
			JavascriptExecutor javascript = (JavascriptExecutor) driver;
			javascript.executeScript("alert('Scrolling Down');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(6000);

			// Scroll down to bottom
			driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]"))
					.click();
			Thread.sleep(4000);
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_SPACE);
			rb.keyRelease(KeyEvent.VK_SPACE);
			System.out.println("scroll");
			Thread.sleep(5000);

			// scroll up alert
			JavascriptExecutor javascriptd = (JavascriptExecutor) driver;
			javascriptd.executeScript("alert('Scrolling Up');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(5000);

			// Scroll up to bottom
			driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]"))
					.click();
			Thread.sleep(4000);
			Robot rb1 = new Robot();
			rb1.keyPress(KeyEvent.VK_PAGE_UP);
			rb1.keyRelease(KeyEvent.VK_PAGE_UP);
			rb1.keyRelease(KeyEvent.VK_PAGE_UP);
			System.out.println("scroll");

		}

		catch (Exception e) {
			System.out.println("CreateDailyHuddle unsuccessfull");
		}
	}

	// -----------------------Edit Flow-----------------------------------

	@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 4)
	public static final void EditDailyHuddleGroup(String HuddleGroupName,
			String EditHuddleGroupName, String EditNote)
			throws MalformedURLException, InterruptedException {
		Thread.sleep(5000);
		
		// alert before edit
		JavascriptExecutor javascripta = (JavascriptExecutor) driver;
		javascripta.executeScript("alert('Edit the Daily Huddle');");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(6000);

		// click on edit button
		String name = HuddleGroupName;		
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+ name+ "')]/following-sibling::td)[1]/descendant::span"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+ name+ "')]/following-sibling::td)[1]/descendant::span")).click();
		Thread.sleep(5000);

		// enter the huddle group name
		driver.findElement(By.xpath("//input[@placeholder='Name...']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Name...']")).sendKeys(EditHuddleGroupName);
		Thread.sleep(5000);

		// enter the note
		driver.findElement(By.xpath("//div[@class='textarea k-widget k-editor k-editor-inline']")).clear();
		Thread.sleep(8000);
		driver.findElement(By.cssSelector("div[data-role='radoloeditor']")).sendKeys(EditNote);
		Thread.sleep(5000);

		// click on save button
		driver.findElement(By.xpath("//button[@class='button green']/descendant::span[2]")).click();
	}

	// -----------------------delete Flow-----------------------------------
	
	@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 5)
	
	public static final void DeleteDailyHuddleGroup(String DeleteHuddleGroup)
			throws MalformedURLException, InterruptedException {
		Thread.sleep(5000);

		// alert before delete
		JavascriptExecutor javascriptc = (JavascriptExecutor) driver;
		javascriptc.executeScript("alert('Delete the Daily Huddle');");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(5000);

		// click on delete button
		String delete = DeleteHuddleGroup;
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+ delete+ "')]/following-sibling::td)[2]/descendant::span"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+ delete+ "')]/following-sibling::td)[2]/descendant::span")).click();
		Thread.sleep(5000);

		// click on yes in "confirm delete" alert
		driver.findElement(
				By.xpath("//div[@class=\"k-content kendo-confirm\"]/descendant::button[text()=\"Yes\"]"))
				.click();
	}



//Create weekly huddle group

@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 6)
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
		Robot rb2 = new Robot();
		rb2.keyPress(KeyEvent.VK_SPACE);
		rb2.keyRelease(KeyEvent.VK_SPACE);
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
		driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]")).click();
		Thread.sleep(4000);
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_SPACE);
		rb.keyPress(KeyEvent.VK_SPACE);
		rb.keyRelease(KeyEvent.VK_SPACE);
		System.out.println("scroll");


		
	}
		catch (Exception e) {
			System.out.println("weekly Unsucessful");
		}
	}  


		// -----------------------EditFlow-----------------------------------
		
		
		@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp",priority = 7)
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
		@Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp",priority = 8)
		private static  void DeleteWeeklyHuddleGroup(String DeleteweeklyHuddle) throws InterruptedException, AWTException 
				
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

		
							
             //--------------------------create priority-----------------------------------
		
             @Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 9)
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

  @Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 10)

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
	  		
  @Test(dataProviderClass = IntegrationTestbase.class, dataProvider = "dp", priority = 11)
	
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


