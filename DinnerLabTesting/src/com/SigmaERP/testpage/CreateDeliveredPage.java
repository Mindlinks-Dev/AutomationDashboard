package com.SigmaERP.testpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.util.ExcelLib;

public class CreateDeliveredPage 
{
WebDriver driver;
	
	public String ShipmentStatus;	
	public String ShipmentProductStatus;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean Deliveredstatus;
    int i =0;

    
private static Logger log = Logger.getLogger (CreateDeliveredPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean DeliveredInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createDeliveredRowCount = xllib.getRowCount("Delivered");
			System.out.println("createDeliveredRowCount:"+createDeliveredRowCount);
			log.info("createDeliveredRowCount log initialized ");
			for (i = 1; i <= createDeliveredRowCount; i++) 
			{
				
				ShipmentStatus = xllib.getExcelData("Delivered", i, 0);				
				ShipmentProductStatus = xllib.getExcelData("Delivered", i, 1);
			
				Deliveredstatus = CreateDelivered(driver,ShipmentStatus,ShipmentProductStatus);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("Delivered Unsucessful");
			e.printStackTrace();
		}
		return Deliveredstatus;
	}
			
	
		private boolean CreateDelivered(WebDriver driver, String shipmentStatus,String shipmentProductStatus) 
		{		
		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//Inspect for Shipments tab
			driver.findElement(By.xpath("//li[@id='01r28000000tS3C_Tab']/a")).click();
			log.info("Shipments Tab");
			Thread.sleep(4000);
			
						
			//Inspecting for Shipment number
			driver.findElement(By.xpath("//td[@id='bodyCell']/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@value=' Edit ']")).click();
			System.out.println("Shipment number");
			Thread.sleep(9000);
														
			
			//Inspecting for ShipmentStatus drop down
			Thread.sleep(10000);
			WebElement clusterText =  driver.findElement(By.id("shipPage:frmEdit:editBlock:j_id99:j_id105"));
			Select st1 = new Select(clusterText);
			Thread.sleep(6000);
			st1.selectByVisibleText(ShipmentStatus);
			System.out.println("ShipmentStatus");
						
															
		/*	//Inspecting for ShipmentProductStatus drop down
			WebElement cluster =  driver.findElement(By.id("shipPage:frmEdit:editBlock:editSection:editTable:0:j_id147"));
			Select st = new Select(cluster);
			Thread.sleep(6000);
			st.selectByVisibleText(ShipmentProductStatus);
			System.out.println("ShipmentProductStatus");*/
								
				
			
			//Inspect for Update button
			driver.findElement(By.xpath("//input[@id='shipPage:frmEdit:editBlock:j_id150:Save']")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			log.info("Select Update button");
			
			
			return true;					
		}
		
		catch(Exception e)
		{
			System.out.println("CreateShipment Unsuccessful");
			return false;
			
		}
	}

}

