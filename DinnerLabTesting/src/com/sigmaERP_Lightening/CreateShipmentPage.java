package com.sigmaERP_Lightening;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateShipmentPage {
	public static boolean loginstatus;
	public String LogisticsName;
	public String TruckNumber;
	public String DeliveryPerson;
	public String ShipmentDate;
	public String Status;
	public String PackageNumber;
	public String ShipmentDeliveryDate;
	//public String Status ;
	
	
	public static void shipment(WebDriver driver)
	{
		try 
		{
				ExcelReading xlib = new ExcelReading();
				int readsheetdata = xlib.getRowCount("sh");
				for (int i = 1; i <= readsheetdata; i++) 
				{
					
					String LogisticsName = xlib.getExcelData("sh", i, 0);
					String TruckNumber = xlib.getExcelData("sh", i, 1);
					String DeliveryPerson = xlib.getExcelData("sh",i,2);
					String ShipmentDate = xlib.getExcelData("sh", i, 3);
					String Status = xlib.getExcelData("sh", i, 4);
					String PackageNumber = xlib.getExcelData("sh", i, 5);
					String ShipmentDeliveryDate = xlib.getExcelData("sh", i, 6);
					
					
					loginstatus = shipmentpage(driver, LogisticsName,TruckNumber,DeliveryPerson,ShipmentDate,Status,PackageNumber,ShipmentDeliveryDate); 
			   } 
		}
				catch (Exception e)
				{
				e.printStackTrace();
			    }
	}
	

	
	private static boolean shipmentpage(WebDriver driver,String LogisticsName,String TruckNumber,String DeliveryPerson,String ShipmentDate, String Status,String PackageNumber, String ShipmentDeliveryDate) throws InterruptedException
	{
		//app launcher
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class=\"slds-icon-waffle\"]")).click();
        Thread.sleep(9000);
        //app launcher look up field
      	driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).sendKeys("shipments");
      	//shipment link
      	driver.findElement(By.xpath("//span[@class=\"label slds-truncate slds-text-link\"]/mark")).click();
	
      	//new button
      	driver.findElement(By.xpath("//div[@title=\"New\"]")).click();
		
      	//Logistics Name
      	WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		try{
			Thread.sleep(5000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(LogisticsName);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[1]"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(LogisticsName))
			{
				System.out.println(str);
				
			System.out.println("Entered if Statement1");
			Thread.sleep(5000);
			we.click();
			action.click();
			action.build().perform();
			}
			else
			{
				System.out.println("come to else statement");
			}
			}
			
			
			} catch (Exception e) {

			}
		
		//truck number
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).sendKeys(TruckNumber);

		//Delivery Person
		try{
			Thread.sleep(5000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[4]]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[4]")).sendKeys(DeliveryPerson);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[2]"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(DeliveryPerson))
			{
				System.out.println(str);
				
			System.out.println("Entered if Statement1");
			Thread.sleep(5000);
			we.click();
			action.click();
			action.build().perform();
			}
			else
			{
				System.out.println("come to else statement");
			}
			}
			
			
			} catch (Exception e) {

			}
	
		//shipment date
		driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).sendKeys(ShipmentDate);
		
		//shipment status
		//drop down list
				Thread.sleep(3000);
				Select sel = new Select(driver.findElement(By.xpath("//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"]")));
				sel.selectByVisibleText("Shipped");
				Thread.sleep(3000);
				
				
				//add  shipment product
				driver.findElement(By.xpath("//button[@class=\"slds-button slds-button--neutral\"]")).click();
				
				//package number
				try{
					Thread.sleep(9000);
					System.out.println("Entered");
					driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[7]")).click();
					driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[7]")).sendKeys(PackageNumber);
					Thread.sleep(5000);
					Actions action=new Actions(driver);
					System.out.println("action"+action);
					List<WebElement> lst=driver.findElements(By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[8]"));
					for (WebElement we : lst) {
					String str = we.getText();
					System.out.println(str);
					if (str.contains(PackageNumber))
					{
						System.out.println(str);
						
					System.out.println("Entered if Statement1");
					Thread.sleep(5000);
					we.click();
					action.click();
					action.build().perform();
					}
					else
					{
						System.out.println("come to else statement");
					}
					}
					
					
					} catch (Exception e) {

					}
				
				//drop down list
				Thread.sleep(9000);
				driver.findElement(By.xpath("(//select)[2]")).click();
				Select sel1 = new Select(driver.findElement(By.xpath("(//select)[2]")));
				sel1.selectByVisibleText("Shipped");
				Thread.sleep(3000);
		
			    Thread.sleep(5000);
				//shipment delivery date
				driver.findElement(By.xpath("(//input[@class=\"slds-input input\"])[2]")).sendKeys(ShipmentDeliveryDate);
				//driver.close();
				//shipment status
				
			
		
				
				//submit button
				driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral\"])[2]")).click();
				
				//save button
				driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--brand\"])[1]")).click();
		return false;
	
	}
	
	
}
