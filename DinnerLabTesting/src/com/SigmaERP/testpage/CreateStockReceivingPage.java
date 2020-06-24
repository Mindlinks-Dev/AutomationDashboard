package com.SigmaERP.testpage;

import java.beans.Statement;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.dinnerLab.util.ExcelLib;



public class CreateStockReceivingPage 
{
WebDriver driver;
	
	public String Vendor;
	public String ReceivingLocation;
	public String TruckNumber;
	public String DeliveryPerson;
	public String ReceivedDateTime;
	public String PurchaseOrder;
	public String PurchaseOrderProduct;
	public String receivedQuantity;
	public String enterSerialNo;
	public String lot;
	public String actualLocation;
	public String stockReceivingProductStatus;
	public String returnQuantityComment;
	public String newStockReceivingName;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean newStockReceivingStatus;
    int i =0;
	
	
    
    private static Logger log = Logger.getLogger (CreateStockReceivingPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean StockReceivingInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createStockReceivingRowCount = xllib.getRowCount("StockReceivingclassic");
			System.out.println("createStockReceivingRowCount:"+createStockReceivingRowCount);
			log.info("CreateStockReceiving log initialized ");
			for (i = 1; i <= createStockReceivingRowCount; i++) 
			{
				Vendor = xllib.getExcelData("StockReceivingclassic", i, 0);
				ReceivingLocation = xllib.getExcelData("StockReceivingclassic", i, 1);
				TruckNumber = xllib.getExcelData("StockReceivingclassic", i, 2);
				DeliveryPerson = xllib.getExcelData("StockReceivingclassic", i, 3);
				ReceivedDateTime = xllib.getExcelData("StockReceivingclassic", i, 4);
				PurchaseOrder = xllib.getExcelData("StockReceivingclassic", i, 5);
				PurchaseOrderProduct = xllib.getExcelData("StockReceivingclassic", i, 6);
				receivedQuantity = xllib.getExcelData("StockReceivingclassic", i, 7);
				enterSerialNo = xllib.getExcelData("StockReceivingclassic", i, 8);
				lot = xllib.getExcelData("StockReceivingclassic", i, 9);
				actualLocation = xllib.getExcelData("StockReceivingclassic", i, 10);
				stockReceivingProductStatus = xllib.getExcelData("StockReceivingclassic", i, 11);
				returnQuantityComment = xllib.getExcelData("StockReceivingclassic", i, 12);
				
									
				System.out.println("PurchaseOrder"+PurchaseOrder);

				System.out.println("ReceivingLocation"+ReceivingLocation);
				
				
				
				newStockReceivingStatus = CreateStockReceiving(driver,Vendor,ReceivingLocation,TruckNumber,DeliveryPerson,ReceivedDateTime,PurchaseOrder,PurchaseOrderProduct,receivedQuantity,enterSerialNo,lot,actualLocation,stockReceivingProductStatus,returnQuantityComment);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("StockReceiving Unsucessful");
			e.printStackTrace();
		}
		return newStockReceivingStatus;
	}

		
		private boolean CreateStockReceiving(WebDriver driver, String vendor,
			String receivingLocation, String truckNumber,
			String deliveryPerson, String receivedDateTime,
			String purchaseOrder, String purchaseOrderProduct,
			String receivedQuantity, String enterSerialNo, String lot,
			String actualLocation, String stockReceivingProductStatus,
			String returnQuantityComment)
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				Thread.sleep(8000);
				//Inspect for stock receiving tab
				driver.findElement(By.cssSelector("a[title='Stock Receiving Tab']")).click();
				log.info("stock receiving Tab");
				Thread.sleep(4000);
				
				
				
				//Inspecting for New button
				driver.findElement(By.xpath("//input[@value=' New ']")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				System.out.println("New button ");
				
				
				//Inspect for vendor lookup field
				Thread.sleep(4000);
				driver.findElement(By.xpath("//img[@title='Vendor Lookup (New Window)']")).click(); // vendor lookup icon
				String parentWindow = driver.getWindowHandle();
				Set<String> handles =  driver.getWindowHandles();
				for(String windowHandle  : handles)
			       {
			       if(!windowHandle.equals(parentWindow))
			          {
			          driver.switchTo().window(windowHandle);
			          driver.switchTo().frame(0);//frame1
			          driver.findElement(By.id("lksrch")).clear();
			          driver.findElement(By.id("lksrch")).sendKeys(Vendor);//Search input
			          Thread.sleep(2000);
			          driver.findElement(By.xpath("//input[@title='Go!']")).click();//Go button
			          driver.switchTo().defaultContent();
			          driver.switchTo().frame(1);//frame2
			          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
			          Thread.sleep(2000);
			          //driver.close(); //closing child window
			          driver.switchTo().window(parentWindow); //cntrl to parent wind
			          }//if loop
			       }//for loop
			   	Thread.sleep(2000);
				log.info("Select vendor lookup field");
				
				
				//Inspect for receivingLocation lookup field
				Thread.sleep(7000);
				driver.findElement(By.xpath("//img[@title='Location Lookup (New Window)']")).click();
				String parentWindow1 = driver.getWindowHandle();
				Set<String> handles1 =  driver.getWindowHandles();
				for(String windowHandle  : handles1)
			       {
			       if(!windowHandle.equals(parentWindow1))
			          {
			          driver.switchTo().window(windowHandle);
			          driver.switchTo().frame(0);//frame1
			          driver.findElement(By.id("lksrch")).clear();
			          driver.findElement(By.id("lksrch")).sendKeys(ReceivingLocation);//Search input
			          driver.findElement(By.xpath("//input[@value=' Go! ']")).click();//Go button
			          driver.switchTo().defaultContent();
			          driver.switchTo().frame(1);//frame2
			          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
			          Thread.sleep(2000);
			          driver.switchTo().window(parentWindow1); //cntrl to parent wind
			          }//if loop
			       }//for loop
			   	Thread.sleep(1000);
				log.info("Select receivingLocation lookup field");
				
				
								
				//Inspect for truckNumber text field
				driver.findElement(By.xpath("//input[@id='mainPage:myform:pageBlock:pageSection:trnumbitem:trnum']")).sendKeys(TruckNumber);
				log.info("truckNumber text field");
				Thread.sleep(4000);
				
				
				
				//Inspect for deliveryPerson lookup field
				Thread.sleep(6000);
				driver.findElement(By.xpath("//img[@title='Contact Lookup (New Window)']")).click();
				String parentWindow2 = driver.getWindowHandle();
				Set<String> handles2 =  driver.getWindowHandles();
				for(String windowHandle  : handles2)
			       {
			       if(!windowHandle.equals(parentWindow2))
			          {
			          driver.switchTo().window(windowHandle);
			          driver.switchTo().frame(0);//frame1
			          driver.findElement(By.id("lksrch")).clear();
			          driver.findElement(By.id("lksrch")).sendKeys(deliveryPerson);//Search input
			          driver.findElement(By.cssSelector("input[title='Go!']")).click();//Go button
			          driver.switchTo().defaultContent();
			          driver.switchTo().frame(1);//frame2
			          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
			          Thread.sleep(2000);
			          driver.switchTo().window(parentWindow2); //cntrl to parent wind
			          }//if loop
			       }//for loop
			   	Thread.sleep(2000);
				log.info("Select deliveryPerson lookup field");
				
				
				
				//Inspect for receivedDateTime text field
				driver.findElement(By.xpath(".//*[@id='mainPage:myform:pageBlock:pageSection']/div[2]/table/tbody/tr[3]/td[2]/span/span/a")).click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				log.info("receivedDateTime text field");
				
								
				
				//Inspect for purchase order lookup field
				driver.findElement(By.xpath("//td[@id='mainPage:myform:newBlock:newSection:newTable:0:j_id71']/img")).click();
				Thread.sleep(3000);
				String parentWindow3 = driver.getWindowHandle();
				Set<String> handles3 =  driver.getWindowHandles();
				for(String windowHandle  : handles3)
			    {
			       if(!windowHandle.equals(parentWindow3))
			          {
			    	   	driver.switchTo().window(windowHandle);
			    	   	//driver.switchTo().frame(0);//frame1
			    	   	driver.findElement(By.xpath("//input[@type='text']")).clear();
			    	   	driver.findElement(By.xpath("//input[@type='text']")).sendKeys(PurchaseOrder);//Search input
			            driver.findElement(By.xpath("//input[@value='Go']")).click();//Go button
			          
			            //driver.switchTo().defaultContent();
			            // driver.switchTo().frame(1);
			            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			            driver.findElement(By.xpath("//a[contains(text(),'"+PurchaseOrder+"')]")).click(); // 
			            Thread.sleep(2000);
				        driver.switchTo().window(parentWindow3); //cntrl to parent wind
				      }//if loop
				 }//for loop
				log.info("Select purchaseorder lookup field");
				
				
				//Inspect for PurchaseOrderProduct lookup field
				driver.findElement(By.xpath("//td[@id='mainPage:myform:newBlock:newSection:newTable:0:j_id74']/img")).click();
				Thread.sleep(3000);
				String parentWindow4 = driver.getWindowHandle();
				Set<String> handles4 =  driver.getWindowHandles();
				for(String windowHandle  : handles4)
			    {
			       if(!windowHandle.equals(parentWindow4))
			          {
			    	   	driver.switchTo().window(windowHandle);
			    	   	//driver.switchTo().frame(0);//frame1
			    	   	driver.findElement(By.xpath("//input[@type='text']")).clear();
			    	   	driver.findElement(By.xpath("//input[@type='text']")).sendKeys(PurchaseOrderProduct);//Search input
			            driver.findElement(By.xpath("//input[@value='Go']")).click();//Go button
			          
			            //driver.switchTo().defaultContent();
			            // driver.switchTo().frame(1);
			            driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			            driver.findElement(By.xpath("//a[contains(text(),'"+PurchaseOrderProduct+"')]")).click(); // 
			            Thread.sleep(2000);
				        driver.switchTo().window(parentWindow4); //cntrl to parent wind
				      }//if loop
				 }//for loop
				log.info("Select purchaseorder lookup field");
				
				//Inspect for receivedQuantity text field
				driver.findElement(By.xpath("//input[@id='mainPage:myform:newBlock:newSection:newTable:0:qty']")).sendKeys(receivedQuantity);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				log.info("Select receivedQuantity text field");
				
				
				
	/*			//Inspect for enterSerialNo lookup field
				driver.findElement(By.xpath("//input[@id='mainPage:myform:newBlock:newSection:newTable:0:j_id80']")).click();
				Thread.sleep(8000);
				driver.findElement(By.xpath("//input[@id='mainPage:myform:j_id95:j_id123:j_id124:0:j_id125:sval']")).sendKeys(enterSerialNo);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//input[@id='mainPage:myform:j_id131']")).click();
				log.info("Select enterSerialNo lookup field");
				
				*/
				
			/*	//Inspect for lot lookup field
				Thread.sleep(3000);
				driver.findElement(By.xpath("//td[@id='mainPage:myform:newBlock:newSection:newTable:0:j_id83']/img")).click();
				Thread.sleep(3000);
				String parentWindow5 = driver.getWindowHandle();
				Set<String> handles5 =  driver.getWindowHandles();
				for(String windowHandle  : handles5)
			    {
			       if(!windowHandle.equals(parentWindow5))
			          {
			    	   	driver.switchTo().window(windowHandle);
			    	    //driver.switchTo().defaultContent();
			            driver.switchTo().frame(0);
			            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			            driver.findElement(By.xpath("//td[@id='j_id0:j_id2:j_id3:tbl:0:j_id5']/a")).click(); // 
			            Thread.sleep(2000);
				        driver.switchTo().window(parentWindow5); //cntrl to parent wind
				      }//if loop
				 }//for loop*/
				
				
				//Inspect for actualLocation lookup field
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[@id='mainPage:myform:newBlock:newSection:newTable:0:actloc_lkwgt']/img")).click();
				Thread.sleep(3000);
				String parentWindow6 = driver.getWindowHandle();
				Set<String> handles6 =  driver.getWindowHandles();
				for(String windowHandle  : handles6)
			       {
			       if(!windowHandle.equals(parentWindow6))
			          {
			          driver.switchTo().window(windowHandle);
			          driver.switchTo().frame(0);//frame1
			          driver.findElement(By.id("lksrch")).clear();
			          driver.findElement(By.id("lksrch")).sendKeys(actualLocation);//Search input
			          driver.findElement(By.xpath("//input[@value=' Go! ']")).click();//Go button
			          driver.switchTo().defaultContent();
			          driver.switchTo().frame(1);//frame2
			          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
			          Thread.sleep(2000);
			          driver.switchTo().window(parentWindow6); //cntrl to parent wind
			          }//if loop
			       }//for loop
			           
				
				//Inspecting for stockReceivingProduct status drop down
				WebElement clusterText =  driver.findElement(By.id("mainPage:myform:newBlock:newSection:newTable:0:status"));
				Select st2 = new Select(clusterText);
				Thread.sleep(6000);
				st2.selectByVisibleText(stockReceivingProductStatus);
				System.out.println("stockReceivingProductStatus");
				
				
				//Inspect for returnQuantityComment text field
				driver.findElement(By.xpath("//textarea[@id='mainPage:myform:newBlock:newSection:newTable:0:comment']")).sendKeys(returnQuantityComment);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				log.info("Select returnQuantityComment text field");
				
				
				//Inspect for submit button
				Thread.sleep(4000);
				driver.findElement(By.xpath("(//input[@class=\"btn slds-button slds-button--brand\"])[1]")).click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				log.info("Select submit button");
				
				
				return true;
				
				
				
			}
			catch(Exception e)
			{
				System.out.println("createStockReceiving Unsuccessful");
				return false;
				
			}
		}

}
		