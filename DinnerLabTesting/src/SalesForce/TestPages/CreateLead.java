package SalesForce.TestPages;

import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateLead extends Testbase{

	 @Test(priority=1)
	  
		public static void AppLaucher() throws InterruptedException
		{
		
	   
			try{
				Thread.sleep(25000);
			
				 //click on app launcher image
				System.out.println("Clicked on the AppLaucher");
				driver.findElement(By.xpath(OR.getProperty("app"))).click();
				Thread.sleep(10000);
				 
				
				//click on search button
				driver.findElement(By.xpath(OR.getProperty("search"))).click();
				Thread.sleep(10000);
				driver.findElement(By.xpath(OR.getProperty("search"))).sendKeys("Sales");
				Thread.sleep(5000);
				
				//Select the application
				Actions action=new Actions(driver);
				WebElement ele1=driver.findElement(By.xpath(OR.getProperty("salesapp")));
				action.moveToElement(ele1);
				Thread.sleep(5000);
				action.click();
				action.build().perform();
				Thread.sleep(5000);

				
				
			}
		 
			catch(Exception exp){
			     				
								}
		}
			
		
		
     @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
	
	
	public static void CreateLead(String Salutation,String FirstName,String LastName,String Company,String Title,String Phone,String Mobile,String Email,
			String Website,String Street,String State,String City,String PostalCode,String Country) throws InterruptedException, InvalidFormatException{

	
	        Thread.sleep(10000);
	        System.out.println("Enter Inside");
	        //Lead Tab
	        driver.findElement(By.xpath(OR.getProperty("LeadTab"))).click();
	        Thread.sleep(8000);
	        
	        
	        //Enter the New Button
	        System.out.println("Click on the New Button");
	        driver.findElement(By.xpath(OR.getProperty("NewButton"))).click();
	        //Thread.sleep(5000);
	        
	        
	        Thread.sleep(8000);
	         //Enter the Salutation
	        System.out.println("Enter the Quantity");
	        driver.findElement(By.xpath(OR.getProperty("ClickSalutation"))).click();
	 
			Thread.sleep(8000);
			List<WebElement> lst1=driver.findElements(By.xpath(OR.getProperty("SelectSalutation")));
					
			Thread.sleep(5000);
			System.out.println(lst1);
			for (WebElement we : lst1) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Salutation))
				{
					we.click();
				}
			}
	        
	        Thread.sleep(8000);
	       
	       
	         System.out.println("Enter the First Name");
	         //Enter the FirstName
	         driver.findElement(By.xpath(OR.getProperty("FirstName"))).sendKeys(FirstName);

	         Thread.sleep(8000);
	         //Enter the LastName
	       	 driver.findElement(By.xpath(OR.getProperty("LastName"))).sendKeys(LastName);
	        

	       //Enter the Company();
	  	   Thread.sleep(8000);
	       driver.findElement(By.xpath(OR.getProperty("Company"))).sendKeys(Company);
	       
	       //Enter the Title
	      /* Thread.sleep(5000);
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
	      */
	      //Enter the Country
	      Thread.sleep(8000);
	        driver.findElement(By.xpath(OR.getProperty("Country"))).sendKeys(Country);   
	       
	       
	     
	         //Enter the Save
		      Thread.sleep(8000);
		      driver.findElement(By.xpath(OR.getProperty("SaveButton"))).click();   
		       
		      //Click on the Convert Button
		      Thread.sleep(8000);
		      driver.findElement(By.xpath(OR.getProperty("ConvertButton"))).click(); 
		      
		      //ConfirmConvert
		      Thread.sleep(8000);
		      driver.findElement(By.xpath(OR.getProperty("ConfirmConvert"))).click();
	
		      //Enter the "CONVERT"
		      Thread.sleep(8000);		     
		      driver.findElement(By.xpath(OR.getProperty("Convert"))).click();  

		     
		     //Scroll Bar till the Oppoetunity
		     Thread.sleep(8000);
		     System.out.println("Scrolling Down Till the Page");
		     JavascriptExecutor js = (JavascriptExecutor) driver;
		     
		     
		     WebElement Element = driver.findElement(By.xpath("//div[text()=\"Opportunity\"]"));

		       //This will scroll the page till the element is found	
		     js.executeScript("arguments[0].scrollIntoView();", Element);
		      
		      
		
		    
		      //Go to Lead Button
		      Thread.sleep(5000);
		      driver.findElement(By.xpath(OR.getProperty("LeadButton"))).click();   
		      

	
	}
	
		   

	
}
