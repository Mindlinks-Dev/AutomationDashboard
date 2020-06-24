package com.SigmaERP.testpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.util.ExcelLib;

public class CreateStockmovementPage
{
WebDriver driver;
	
	public String Tolocation;
	public String Fromlocation;
	public String Product;
	public String prodQty;
	public String Quantity;	
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean newstockmovementStatus;
    int i =0;
    
private static Logger log = Logger.getLogger (CreateStockmovementPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean StockmovementPageInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createStockMovementRowCount = xllib.getRowCount("StockMovement");
			System.out.println("createStockMovementRowCount:"+createStockMovementRowCount);
			log.info("createStockMovementRowCount log initialized ");
			for (i = 1; i <= createStockMovementRowCount; i++) 
			{
				Tolocation = xllib.getExcelData("StockMovement", i, 0);
				Fromlocation = xllib.getExcelData("StockMovement", i, 1);
				Product = xllib.getExcelData("StockMovement", i, 2);
				Quantity = xllib.getExcelData("StockMovement", i, 3);
				
				
				newstockmovementStatus = CreateStockReceiving(driver,Tolocation,Fromlocation,Product,Quantity);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("StockMovement Unsucessful");
			e.printStackTrace();
		}
		return newstockmovementStatus;
	}

	private boolean CreateStockReceiving(WebDriver driver, String tolocation,
			String fromlocation, String product, String quantity)
	{
		
		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//Inspect for stockmovement tab
			driver.findElement(By.xpath("//li[@id='01r28000000tS3E_Tab']/a")).click();
			log.info("stockmovement Tab");
			Thread.sleep(4000);
			
			
			
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("New button ");
			
		
			//Inspect for Tolocation lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Inventory Location Lookup (New Window)']")).click(); 
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Tolocation);//Search input
		          Thread.sleep(2000);
		          driver.findElement(By.xpath("//input[@value=' Go! ']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);
		          //driver.close(); //closing child window
		          driver.switchTo().window(parentWindow); //cntrl to parent wind
		          }//if loop
		       }//for loop
		   	Thread.sleep(2000);
			log.info("Select Tolocation lookup field");
			
		
		
			//Inspect for Fromlocation lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//a[@id='mainPage:myform:pageBlockprd:pageSectionprd:pageTable:0:loc_lkwgt']/img")).click(); 
			String parentWindow1 = driver.getWindowHandle();
			Set<String> handles1 =  driver.getWindowHandles();
			for(String windowHandle  : handles1)
		       {
		       if(!windowHandle.equals(parentWindow1))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Fromlocation);//Search input
		          Thread.sleep(2000);
		          driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);
		          //driver.close(); //closing child window
		          driver.switchTo().window(parentWindow1); //cntrl to parent wind
		          }//if loop
		       }//for loop
		   	Thread.sleep(2000);
			log.info("Select Fromlocation lookup field");
						
			
			
			//Inspect for Product lookup field
			Thread.sleep(3000);
			driver.findElement(By.xpath("//td[@id='mainPage:myform:pageBlockprd:pageSectionprd:pageTable:0:j_id51']/img")).click();
			Thread.sleep(3000);
			String parentWindow3 = driver.getWindowHandle();
			Set<String> handles3 =  driver.getWindowHandles();
			for(String windowHandle  : handles3)
		    {
		       if(!windowHandle.equals(parentWindow3))
		          {
		    	   	driver.switchTo().window(windowHandle);
		    	 	prodQty = driver.findElement(By.xpath("//tr[td[a[contains(text(),'ILP-00462')]]]/td[3]/span")).getText();
		    	    //driver.switchTo().defaultContent();
		            // driver.switchTo().frame(1);
		            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		        	System.out.println("prodQty:"+prodQty);
		    	    driver.findElement(By.xpath(".//*[@id='j_id0:j_id2:j_id3:tbl:0:j_id5']/a")).click(); // 
		            Thread.sleep(2000);
			        driver.switchTo().window(parentWindow3); //cntrl to parent wind
			      }//if loop
			 }//for loop
			log.info("Select Product lookup field");
			
			
			//Inspecting for Quantity button
			driver.findElement(By.xpath("//input[@id='mainPage:myform:pageBlockprd:pageSectionprd:pageTable:0:j_id56']")).sendKeys(Quantity);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Quantity button ");		
			
			
			//Inspect for submit button
			driver.findElement(By.xpath("//input[@id='mainPage:myform:pageBlockprd:j_id60']")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select submit button");
			
			
			return true;		
					
		}
		catch(Exception e)
		{
			System.out.println("createstockmovement Unsuccessful");
			return false;
			
		}
	}

}
		
			
			
			
			
			
			
			
			
			
			
			