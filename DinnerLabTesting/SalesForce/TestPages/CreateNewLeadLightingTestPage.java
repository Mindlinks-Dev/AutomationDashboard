package com.SalesForce.TestPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.src.test.java.com.Base.Testbase;



public class CreateNewLeadLightingTestPage extends Testbase
{
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
	
	
	public static void CreateLead(String Salutation,String FirstName,String LastName,String Company,String Title,String Phone,String Mobile,String Email,
			String Website,String Street,String State,String City,String PostalCode,String Country) throws InterruptedException, InvalidFormatException{

	
	        Thread.sleep(20000);
	        System.out.println("Enter Inside");
	        //Lead Tab
	        driver.findElement(By.xpath("//ul[@class=\"slds-grid slds-has-flexi-truncate navUL\"]/li[3]")).click();
	        Thread.sleep(5000);
	        
	        //Enter the New Button
	        System.out.println("Click on the New Button");
	        driver.findElement(By.xpath("//div[@title=\"New\"]")).click();
	        Thread.sleep(5000);
	        
	        //Alert Message
		      JavascriptExecutor javascript3 = (JavascriptExecutor) driver;
			  javascript3.executeScript("alert('Creating the Lead');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
	        
	   
	         //Enter the Salutation
	        System.out.println("Enter the Quantity");
	        driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
	 
			Thread.sleep(5000);
			List<WebElement> lst1=driver.findElements(By.xpath("(//div[@class=\"select-options\"])[7]//li"));
					
			Thread.sleep(5000);
			System.out.println(lst1);
			for (WebElement we : lst1) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Salutation))
				{
					we.click();
				}
			}
	        
	        Thread.sleep(5000);
	       
	       
	         System.out.println("Enter the First Name");
	         //Enter the FirstName
	         driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[1]/input")).sendKeys(FirstName);

	         Thread.sleep(5000);
	         //Enter the LastName
	       	 driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[2]/input")).sendKeys(LastName);
	        

	       //Enter the Company();
	  	   Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[3]/input")).sendKeys(Company);
	       
	       //Enter the Title
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[4]/input")).sendKeys(Title);
	       
	      //Enter the Phone
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPhone uiInput--default uiInput--input\"])[1]/input")).sendKeys(Phone);
	       
	      //Enter the Mobile
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPhone uiInput--default uiInput--input\"])[2]/input")).sendKeys(Mobile);
	       
	     //Enter the Email
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("//div[@class=\"uiInput uiInputEmail uiInput--default uiInput--input\"]/input")).sendKeys(Email);   
	       
	      
		 //Enter the Website
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//div[@class=\"uiInput uiInputURL uiInput--default uiInput--input\"]/input")).sendKeys(Website);   
	      
		  
	      //Enter the Street-Address
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//textarea)[1]")).sendKeys(Street);   
	      
	     //Enter the City
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[1]//input")).sendKeys(City);   
	       
	      //Enter the State
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[6]/input")).sendKeys(State);   
	       
	      //Enter the PostalCode
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[2]//input")).sendKeys(PostalCode);   
	       
	      //Enter the Country
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[8]/input")).sendKeys(Country);   
	       
	      //Enter the Save
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("//div[@class=\"forceModalActionContainer--footerAction forceModalActionContainer\"]/button[3]")).click();   
	       
	      //Click on the Convert Button
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiMenu\"])[4]")).click(); 
	      Thread.sleep(3000);
	      driver.findElement(By.xpath("(//ul[@class=\"scrollable\"])[8]/li[6]")).click();
	      
	      Thread.sleep(5000);
	      //Enter the Save
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("//div[@class=\"forceModalActionContainer--footerAction forceModalActionContainer\"]/button[3]")).click();   
	      

	      //Enter the "CONVERT BUTTON"
	      Thread.sleep(10000);
	      driver.findElement(By.xpath("//div[@class=\"modal-container slds-modal__container\"]/descendant::button[6]")).click();   
	      
	      //Scroll Bar 
	      Thread.sleep(8000);
	      System.out.println("Scrolling Down Till the Page");
	      JavascriptExecutor js = (JavascriptExecutor) driver;
	      js.executeScript("window.scrollBy(0,1000)");
	      
	      //Go to Lead Button
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("//div[@class=\"modal-container slds-modal__container\"]/descendant::button[3]")).click();   
	      
	      
	      //Alert Message
	      JavascriptExecutor javascript4 = (JavascriptExecutor) driver;
		  javascript4.executeScript("alert('Lead Created Sucessfully');");
		  Thread.sleep(8000);
		  driver.switchTo().alert().accept();
	      
	      
	
	
	}
	
	
	
@Test(priority=1)
  
	public static void AppLaucher() throws InterruptedException
	{
	
		try{
			Thread.sleep(20000);
		
		     
			driver.findElement(By.xpath("//nav/button/div")).click();

			Thread.sleep(10000);
			 
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)")).sendKeys("Sales");
			Thread.sleep(10000);
			Actions action=new Actions(driver);
			WebElement ele1=driver.findElement(By.xpath("//div[@title=\"Sales\"]"));
			action.moveToElement(ele1);
			action.click();
			action.build().perform();
			Thread.sleep(5000);

			
			
		}
	 
		catch(Exception exp){
		
		
		}
		
		
		
	    }






	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 3)
	
	public static void CreateAccount(String AccountName,String Type,String AccountNumber,String AccountSite,String Employees,String AnnualRevenue,String Description,
			String Street,String State,String City,String PostalCode,String Country,String Website,String Industry) throws InterruptedException, InvalidFormatException
	{

	        Thread.sleep(5000);
	        System.out.println("Enter Inside");
	        
	        //Lead Tab
	        driver.findElement(By.xpath("//ul[@class=\"slds-grid slds-has-flexi-truncate navUL\"]/li[6]")).click();
	        Thread.sleep(5000);
	        
	        //Enter the New Button
	        System.out.println("Click on the New Button");
	        driver.findElement(By.xpath("//div[@title=\"New\"][1]")).click();
	      
	        //Alert Message
		      JavascriptExecutor javascript3 = (JavascriptExecutor) driver;
			  javascript3.executeScript("alert('Creating Account');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
	        
	        Thread.sleep(5000);
	         //Enter the AccountName
	        System.out.println("Enter the AccountName");
	        driver.findElement(By.xpath("//div[@class=\"uiInput witsSuggestedNameField uiInput--default\"]/input")).sendKeys(AccountName);
	 
	        //Click on the Type
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
	       
			Thread.sleep(5000);
			List<WebElement> lst1=driver.findElements(By.xpath("(//div[@class=\"select-options\"])[8]//li"));
					
			Thread.sleep(5000);
			System.out.println(lst1);
			for (WebElement we : lst1) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Type))
				{
					we.click();
				}
			}
	        
	        Thread.sleep(5000);
	       
	       
	         System.out.println("Enter the AccountNumber");
	         //Enter the AccountNumber
	         driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[7]/descendant::input")).sendKeys(AccountNumber);

	         Thread.sleep(5000);
	         //Enter the Account Site
	       	 driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[9]/descendant::input")).sendKeys(AccountSite);
	        

	       //Enter the Employees;
	  	   Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[14]/descendant::input")).sendKeys(Employees);
	       
	       //Enter the AnnualRevenue
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[15]/descendant::input")).sendKeys(AnnualRevenue);
	       
	      //Enter the Description
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//textarea)[3]")).sendKeys(Description);
	   

	      //Enter the Street-Address
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//textarea)[1]")).sendKeys(Street);   
	      
	     //Enter the City
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[1]//input")).sendKeys(City);   
	       
	      //Enter the State
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[6]/input")).sendKeys(State);   
	       
	      //Enter the PostalCode
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[2]//input")).sendKeys(PostalCode);   
	       
	      //Enter the Country
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[8]/input")).sendKeys(Country);   
	       
	      //Enter the Save
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("//div[@class=\"forceModalActionContainer--footerAction forceModalActionContainer\"]/button[3]")).click();   
	     
	      
	      
	      //Alert Message
	      JavascriptExecutor javascript4 = (JavascriptExecutor) driver;
		  javascript4.executeScript("alert('Editing the Account');");
		  Thread.sleep(8000);
		  driver.switchTo().alert().accept();
		  
	      //Enter the "Edit Flow"
	      driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS) ;
	      driver.findElement(By.xpath("(//div[@class=\"slds-col slds-no-flex slds-grid slds-align-middle actionsContainer\"]/descendant::a)[6]")).click();   
	       
	      //Click on edit 
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"branding-actions actionMenu\"])[2]/descendant::li[2]")).click();  
	      
	      //Enter the Website
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[9]/descendant::input")).sendKeys(Website); 
	      
	      //Click on the Industry
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("(//a[@class=\"select\"])[4]")).click();
	       
			Thread.sleep(5000);
			List<WebElement> lst2=driver.findElements(By.xpath("(//div[@class=\"select-options\"])[8]//li"));
					
			Thread.sleep(5000);
			System.out.println(lst2);
			for (WebElement we : lst2) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Industry))
				{
					we.click();
				}
			}
	        
		    //Click on the Save
		      Thread.sleep(5000);
		      driver.findElement(By.xpath("//div[@class=\"forceModalActionContainer--footerAction forceModalActionContainer\"]/button[3]")).click();   
		      
		
	
	
	}
	
@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)
	
	public static void CreateContact(String Salutation,String FirstName,String LastName,String AccountName,String Mobile,String Email,String Description,
			String Street,String State,String City,String PostalCode,String Country,String Department) throws InterruptedException, InvalidFormatException
	{

	        Thread.sleep(5000);
	        System.out.println("Enter Inside");
	        
	        //Contact Tab
	        driver.findElement(By.xpath("//ul[@class=\"slds-grid slds-has-flexi-truncate navUL\"]/li[7]")).click();
	        Thread.sleep(5000);
	        
	        //Enter the New Button
	        System.out.println("Click on the New Button");
	        driver.findElement(By.xpath("//div[@title=\"New\"][1]")).click();
	        
	        
	        Thread.sleep(10000);
			
			  JavascriptExecutor javascript = (JavascriptExecutor) driver;
			  javascript.executeScript("alert('Creating the Contact');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
	        
	  

	        //Enter the Salutation
	        System.out.println("Enter the Salutation");
	        driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
	 
			Thread.sleep(5000);
			List<WebElement> lst1=driver.findElements(By.xpath("(//div[@class=\"select-options\"])[3]/descendant::li"));
					
			Thread.sleep(5000);
			System.out.println(lst1);
			for (WebElement we : lst1) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Salutation))
				{
					we.click();
				}
			}
	        
	        Thread.sleep(5000);
	       
	       
	         System.out.println("Enter the First Name");
	         //Enter the FirstName
	         driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[1]/input")).sendKeys(FirstName);

	         Thread.sleep(5000);
	         //Enter the LastName
	       	 driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[2]/input")).sendKeys(LastName);
	 
	
	       System.out.println("Enter the AccountName");
	         //Enter the AccountName
	         driver.findElement(By.xpath("(//div[@class=\"autocompleteWrapper slds-grow\"])[1]/input")).sendKeys(AccountName);
	    	 driver.findElement(By.xpath("//div[@class=\"listContent\"]/descendant::li[2]")).click();
	         

	         Thread.sleep(5000);
	         //Enter the Mobile
	       	 driver.findElement(By.xpath("(//div[@ class=\"slds-form-element__control\"])[8]/descendant::input")).sendKeys(Mobile);
	        

	       //Enter the Email;
	  	   Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[12]/descendant::input")).sendKeys(Email);
	       
	   
	      //Enter the Description
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//textarea)[3]")).sendKeys(Description);
	   

	      //Enter the Street-Address
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//textarea)[1]")).sendKeys(Street);   
	      
	     //Enter the City
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[1]//input")).sendKeys(City);   
	       
	      //Enter the State
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[7]/input")).sendKeys(State);   
	       
	      //Enter the PostalCode
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[2]/descendant::input")).sendKeys(PostalCode);   
	       
	      //Enter the Country
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[9]/input")).sendKeys(Country);   
	       
	      //Enter the Save
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("//div[@class=\"forceModalActionContainer--footerAction forceModalActionContainer\"]/button[3]")).click();  
	     
	      JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
		  javascript1.executeScript("alert('Editing the Contact');");
		  Thread.sleep(8000);
		  driver.switchTo().alert().accept();
	     
	      //Enter the "Edit Flow"
	      Thread.sleep(10000);
	      driver.findElement(By.xpath("(//a[@class=\"slds-grid slds-grid--vertical-align-center slds-grid--align-center sldsButtonHeightFix\"])[2]")).click();   
	       
	      //Click on edit 
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"branding-actions actionMenu\"])[2]/descendant::li[2]")).click();  
	      
	      
	      
	      //Enter the Department
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[10]/descendant::input")).sendKeys(Department); 
	      
	      /*Click on the Industry
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("(//a[@class=\"select\"])[4]")).click();
	       
			Thread.sleep(5000);
			List<WebElement> lst2=driver.findElements(By.xpath("(//div[@class=\"select-options\"])[8]//li"));
					
			Thread.sleep(5000);
			System.out.println(lst2);
			for (WebElement we : lst2) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Industry))
				{
					we.click();
				}
			}*/
	        
		    //Click on the Save
		      Thread.sleep(5000);
		      driver.findElement(By.xpath("//div[@class=\"forceModalActionContainer--footerAction forceModalActionContainer\"]/button[3]")).click();   
		      
		      
		      //Alert Message
		      JavascriptExecutor javascript3 = (JavascriptExecutor) driver;
			  javascript3.executeScript("alert('Contact Saved Sucessfully');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
		
	
	
	}
	
	
	
	






	
	

}
