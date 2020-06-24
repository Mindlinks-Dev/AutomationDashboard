package SalesForce.TestPages;

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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;




public class CreateCase extends Testbase
{
	
	

	
	
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
			System.out.println("Clicked on the Search");
			Thread.sleep(10000);
			driver.findElement(By.xpath(OR.getProperty("search"))).sendKeys("Sales");
			System.out.println("Enter value for Search tab");
			Thread.sleep(5000);
			
			//Select the application
			Actions action=new Actions(driver);
			WebElement ele1=driver.findElement(By.xpath(OR.getProperty("salesapp")));
			System.out.println("Select sales app");
			action.moveToElement(ele1);
			Thread.sleep(5000);
			action.click();
			action.build().perform();
			Thread.sleep(5000);
			System.out.println("Clicked on sales app");
			
			
		}
	 
		catch(Exception exp){
		
		
		}
		
		
		
	    }


     /*
      * case creation and edit
      * written by Pooja 
      */
	    
     @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)


	public static void CreateCase(String ContactName,String Status,String CaseOrigin,String Type,String CaseReason,String Subject,String Priority) throws InterruptedException, InvalidFormatException
	{

 	
    //Thread.sleep(8000);
    System.out.println("Enter Inside");
    
    //case tab
    driver.findElement(By.xpath(OR.getProperty("CaseTab"))).click();
    Thread.sleep(8000);
   
    //refresh the page
     //Thread.sleep(3000);
	 driver.navigate().refresh();
	 Thread.sleep(12000);
	 
	 
     //click new button
     driver.findElement(By.xpath(OR.getProperty("CaseNewButton"))).click();
     Thread.sleep(8000);
	 System.out.println("click on case origin");
		    
    //click the case origin
    driver.findElement(By.xpath(OR.getProperty("CaseOrigin"))).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("CaseSelectOrigin"))).click();
    Thread.sleep(8000);
    System.out.println("click on Type");
    
    
    //click the Type
    driver.findElement(By.xpath(OR.getProperty("CaseType"))).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath(OR.getProperty("CaseSelectType"))).click();
    Thread.sleep(8000);
    System.out.println("click case reason");
   
    
    //click the case reason
    driver.findElement(By.xpath(OR.getProperty("CaseReason"))).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath(OR.getProperty("CaseselectReason"))).click();
    Thread.sleep(5000);
    
    //scroll down
    JavascriptExecutor j = (JavascriptExecutor) driver;
	j.executeScript("window.scrollBy(0,450)", "");
	Thread.sleep(5000);
    
    //subject
	driver.findElement(By.xpath(OR.getProperty("CaseSubject"))).sendKeys(Subject);
	Thread.sleep(5000);
	
    //click save button
    driver.findElement(By.xpath(OR.getProperty("CaseSaveButton"))).click();
  
    Thread.sleep(8000);
    
    //create case edit flow ->click on Edit button
    driver.findElement(By.xpath(OR.getProperty("CaseEdit"))).click();
    
    Thread.sleep(8000);
    System.out.println("click on Priority");
     //click the Priority
    driver.findElement(By.xpath(OR.getProperty("CasePriority"))).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("CaseSelectPriority"))).click();
    //click save button
    driver.findElement(By.xpath(OR.getProperty("CaseEditSave"))).click();

	
	      }
	
      }
