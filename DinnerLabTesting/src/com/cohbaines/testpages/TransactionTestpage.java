package com.cohbaines.testpages;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.dinnerLab.util.ExcelLib;

public class TransactionTestpage {
	
	WebDriver driver;
	private String TransactionType;
	private String Account;
	private String PurchaseOrder;
	private String Product;
	private String Quantity;
	private String PassorFail;
	public static boolean newProductstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(TransactionTestpage.class);
	
	public boolean TransactionInitialPage(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("Transactions(PO)");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				TransactionType = xllib.getExcelData("Transactions(PO)", i, 0);
				Account = xllib.getExcelData("Transactions(PO)", i, 1);
				PurchaseOrder =xllib.getExcelData("Transactions(PO)", i, 2);
				Product =xllib.getExcelData("Transactions(PO)", i, 3);
				Quantity =xllib.getExcelData("Transactions(PO)", i, 4);
				PassorFail =xllib.writeToExcel("Transactions(PO)", i,6,"Pass");
				newProductstatus = createTransaction(driver, TransactionType,Account,PurchaseOrder,Product,Quantity);
						

				System.out.println("Login Count:" + i);
			}

		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

	private boolean createTransaction(WebDriver driver,String TransactionType, String Account, String PurchaseOrder,String Product,String Quantity) throws InterruptedException{
		
			Thread.sleep(20000);

			//Click on the Transaction Tab
			driver.findElement(By.xpath("//a[@title='Transactions']")).click();
			Thread.sleep(5000);
			
		   //Click on the 'New' Button
			driver.findElement(By.xpath("//a[@class='forceActionLink']/div[@title='New']")).click();
		
			Thread.sleep(5000);
			 
			WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
			driver.switchTo().frame(frame);
			System.out.println(" switching  to frame");
			try{
			Thread.sleep(20001);// entering purchase order
			System.out.println("enter");
		   driver.findElement(By.xpath("(//div[@class=\"requiredInput\"]/span)[1]/input")).sendKeys(PurchaseOrder);
			}
			catch(Exception exp){
				
			}
		
		    Thread.sleep(5000);
		   driver.findElement(By.xpath("(//div[@class=\"requiredInput\"]/span)[2]/input")).click();
		   
		
		   driver.findElement(By.xpath("//img[@title='Account Lookup (New Window)']")).click();
		   Reporter.log("clicks on account icon");
		    // going into the look up method of account
		    lookupDataSelect(driver, Account);

		System.out.println("enter the Save");
		Thread.sleep(6000l);
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		driver.switchTo().frame(frame1);
		System.out.println(" switching  to frame");
		// clicks on save button
		
		driver.findElement(By.xpath("(//input[@type=\"submit\"])[1]")).click();
		
		System.out.println("enter the Save");
		// driver.switchTo().defaultContent();

		Thread.sleep(6000l);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		// switching to item frame
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@scrolling=\"yes\"]"));
		driver.switchTo().frame(frame2);
		System.out.println("switching to frame");

		// clicks on new item button to add product
		Thread.sleep(5000l);
		driver.findElement(By.xpath("(//div[@class=\"pbHeader\"])[2]/descendant::input")).click();
		// driver.findElement(By.xpath("(//div[@class=\"pbHeader\"])[2]/descendant::input")).click();
		
		Thread.sleep(8000l);
		Thread.setDefaultUncaughtExceptionHandler(null);

		// click on product text field and sends the product.
		Actions actions = new Actions(driver);
		WebElement ele1 = driver.findElement(By.xpath("//div[@class=\"requiredInput\"]/descendant::span"));
		actions.moveToElement(ele1);
		actions.click();
		actions.sendKeys(Product);
		actions.build().perform();
		Thread.sleep(8000l);
		
		
		// enters qty
		WebElement ele2 = driver.findElement(By.xpath("(//input[@type=\"text\"])[3]"));
		actions.moveToElement(ele2);
		actions.click();
		System.out.println("Entered into Qty1");
		ele2.clear();
		System.out.println("Entered into Qty2");
		actions.sendKeys(Quantity);
		actions.build().perform();
		
		// clicks on save button
		driver.findElement(By.xpath("(//input[@value=\"Save\"])[1]")).click();
		
		   
		   
		   
			  
			  
             //lookup Data Select
				//lookupDataSelect(driver, TransactionType);
	
			     System.out.println("createaccount Unsuccessful");

		
		return false;
}
	

	public void lookupDataSelect(WebDriver driver,String Transaction) throws InterruptedException
	{
		Thread.sleep(3000);
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		for(String windowHandle  : handles)
	    {
	       if(!windowHandle.equals(parentWindow))
	          {
	    	   	driver.switchTo().window(windowHandle);
	    	   	driver.switchTo().frame(0);//frame1
	    	   	driver.findElement(By.id("lksrch")).clear();
	    	   	driver.findElement(By.id("lksrch")).sendKeys(Account);//Search input
	    	   	Thread.sleep(5000);
	            driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
	          
	            driver.switchTo().defaultContent();
	            driver.switchTo().frame(1);
	            driver.findElement(By.xpath("//a[contains(text(),'"+Account+"')]")).click(); // 
	            Thread.sleep(2000);
		        driver.switchTo().window(parentWindow); //cntrl to parent wind
		      }//if loop
		 }//for loop
	}	

	
}