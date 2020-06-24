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

public class CreateShipmentsPage 
{
WebDriver driver;
	
	public String Logistics;
	public String DeliveryPerson;
	public String ShipmentDate;
	public String ShipmentStatus;
	public String PackageNumber;
	public String ShipmentDeliveryDate;
	public String ShipmentProductStatus;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean Shipmentsstatus;
    int i =0;

    
private static Logger log = Logger.getLogger (CreateShipmentsPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean ShipmentsInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createShipmentsRowCount = xllib.getRowCount("Shipments");
			System.out.println("createShipmentsRowCount:"+createShipmentsRowCount);
			log.info("createShipmentsRowCount log initialized ");
			for (i = 1; i <= createShipmentsRowCount; i++) 
			{
				Logistics = xllib.getExcelData("Shipments", i, 0);
				DeliveryPerson = xllib.getExcelData("Shipments", i, 1);
				ShipmentStatus = xllib.getExcelData("Shipments", i, 2);
				PackageNumber = xllib.getExcelData("Shipments", i, 3);
				ShipmentProductStatus = xllib.getExcelData("Shipments", i, 4);
			
				Shipmentsstatus = CreateShipments(driver,Logistics,DeliveryPerson,ShipmentDate,ShipmentStatus,PackageNumber,ShipmentDeliveryDate,ShipmentProductStatus);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("Shipments Unsucessful");
			e.printStackTrace();
		}
		return Shipmentsstatus;
	}

	private boolean CreateShipments(WebDriver driver, String logistics,String deliveryPerson, String shipmentDate,
			String shipmentStatus, String packageNumber,String shipmentDeliveryDate, String shipmentProductStatus) 
	{
		

		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//Inspect for Shipments tab
			driver.findElement(By.xpath("//li[@id='01r28000000tS3C_Tab']/a")).click();
			log.info("Shipments Tab");
			Thread.sleep(4000);
			
			
			
			//Inspecting for New button
			driver.findElement(By.xpath("//input[@value=' New ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("New button ");
			
		
			
			//Inspect for Logistics lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Account Lookup (New Window)']")).click();
			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(Logistics);//Search input
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
			log.info("Select Logistics lookup field");
			
			
			
			//Inspect for DeliveryPerson lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Delivery Person Lookup (New Window)']")).click();
			String parentWindow1 = driver.getWindowHandle();
			Set<String> handles1 =  driver.getWindowHandles();
			for(String windowHandle  : handles1)
		       {
		       if(!windowHandle.equals(parentWindow1))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(DeliveryPerson);//Search input
		          driver.findElement(By.xpath("//input[@value=' Go! ']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);
		          driver.switchTo().window(parentWindow1); //cntrl to parent wind
		          }//if loop
		       }//for loop
		   	Thread.sleep(1000);
			log.info("Select DeliveryPerson lookup field");
			
			
			
			//Inspect for ShipmentDate text field
			driver.findElement(By.xpath(".//*[@id='shipPage:frmNew:newBlock:j_id33']/div[2]/table/tbody/tr[2]/td[2]/span/span/a")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("ShipmentDate text field");
			
			
			
			//Inspecting for ShipmentStatus drop down
			WebElement clusterText =  driver.findElement(By.id("shipPage:frmNew:newBlock:j_id33:statusid"));
			Select st1 = new Select(clusterText);
			Thread.sleep(6000);
			st1.selectByVisibleText(ShipmentStatus);
			System.out.println("ShipmentStatus");
			
			
			
			//Inspect for PackageNumber lookup field
			Thread.sleep(4000);
			driver.findElement(By.xpath("//img[@title='Package ID Lookup (New Window)']")).click();
			String parentWindow2 = driver.getWindowHandle();
			Set<String> handles2 =  driver.getWindowHandles();
			for(String windowHandle  : handles2)
		       {
		       if(!windowHandle.equals(parentWindow2))
		          {
		          driver.switchTo().window(windowHandle);
		          driver.switchTo().frame(0);//frame1
		          driver.findElement(By.id("lksrch")).clear();
		          driver.findElement(By.id("lksrch")).sendKeys(PackageNumber);//Search input
		          driver.findElement(By.cssSelector("input[value=' Go! ']")).click();//Go button
		          driver.switchTo().defaultContent();
		          driver.switchTo().frame(1);//frame2
		          driver.findElement(By.xpath("html/body/form/div/div[3]/div/div[2]/table/tbody/tr[2]/th/a")).click(); 
		          Thread.sleep(2000);
		          driver.switchTo().window(parentWindow2); //cntrl to parent wind
		          }//if loop
		       }//for loop
		   	Thread.sleep(2000);
			log.info("Select PackageNumber lookup field");
			
			
			//Inspect for ShipmentDeliveryDate text field
			driver.findElement(By.xpath(".//*[@id='shipPage:frmNew:newBlock:newSection:newTable:0:j_id75']/div/span/span/a")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("ShipmentDeliveryDate text field");
			
			
			
			//Inspecting for ShipmentProductStatus drop down
			WebElement cluster =  driver.findElement(By.id("shipPage:frmNew:newBlock:newSection:newTable:0:j_id78"));
			Select st = new Select(cluster);
			Thread.sleep(6000);
			st.selectByVisibleText(ShipmentProductStatus);
			System.out.println("ShipmentProductStatus");
								
				
			
			//Inspect for save button
			driver.findElement(By.xpath("//input[@id='shipPage:frmNew:newBlock:j_id81:Save']")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select save button");
			
			
			return true;					
		}
		
		catch(Exception e)
		{
			System.out.println("CreateShipment Unsuccessful");
			return false;
			
		}
	}

}