package com.SigmaERP.testpage;

import java.beans.Statement;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.util.ExcelLib;

public class CreateOrderPage
{
WebDriver driver;
	
	public String CustomerName;
	public String CreatedBy;
	public String Product;
	public String Quantity;
	public String CashReceived;
	public int rowCount;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean Orderstatus;
    int i =0;

    
private static Logger log = Logger.getLogger (CreateOrderPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean OrderInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			int createOrderRowCount = xllib.getRowCount("Order");
			System.out.println("createOrderRowCount:"+createOrderRowCount);
			log.info("createOrderRowCount log initialized ");
			for (i = 1; i <= createOrderRowCount; i++) 
			{
				CustomerName = xllib.getExcelData("Order", i, 0);
				CreatedBy = xllib.getExcelData("Order", i, 1);
				Product = xllib.getExcelData("Order", i, 2);
				Quantity = xllib.getExcelData("Order", i, 3);
				CashReceived = xllib.getExcelData("Order", i, 4);
			
				Orderstatus = CreateOrder(driver,CustomerName,CreatedBy,Product,Quantity,CashReceived);
				System.out.println("Login Count:"+i);
			}
		}
		catch(Exception e)
		{
			System.out.println("Shipments Unsucessful");
			e.printStackTrace();
		}
		return Orderstatus;
	}

	private boolean CreateOrder(WebDriver driver, String customerName,
			String createdBy, String product, String quantity,
			String cashReceived) 
	{
		

		try
		{
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(8000);
			//Inspect for Order tab
			driver.findElement(By.xpath("//li[@id='Order_Tab']/a")).click();
			log.info("Shipments Tab");
			Thread.sleep(4000);			
			
			
			//Inspecting for Go button
			driver.findElement(By.xpath("//input[@value=' Go! ']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Go button ");
			
		
			//Inspecting for CreateOrderViaPos button
			driver.findElement(By.xpath("//input[@title='CreateOrder_Via POS']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("CreateOrderViaPos button ");
			Thread.sleep(9000);
			Thread.sleep(9000);
		
		
		
			//Inspecting for CustomerName button
			System.out.println("CustomerName button ");
			Thread.sleep(9000);
			driver.findElement(By.xpath(".//*[@id='51:0']")).sendKeys(CustomerName);
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			System.out.println("CustomerName button ");
		
		
			driver.findElement(By.xpath("//a[@id='28:0_id_0012800001NMeCYAA1']")).click();
		
		return true;					
	}
	
	catch(Exception e)
	{
		System.out.println("CreateOrder Unsuccessful");
		return false;
		
	}
}

}