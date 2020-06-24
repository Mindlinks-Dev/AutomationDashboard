package com.sigmaERP_Lightening;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateStockReceivingPage 
{
	
	public static boolean loginstatus;
	public String Vendor;
	public String ReceivingLocation;
	public String DeliveryPerson;
	public String PurchaseOrder;
	public String POProduct;
	public String ReceivedQuantity;
	public String ActualLocation;
	public String po;
	public String ReceivedDate;
	public static void stockreceiving(WebDriver driver)
	{
		try 
		{
				ExcelReading xlib = new ExcelReading();
				int readsheetdata = xlib.getRowCount("sr");
				for (int i = 1; i <= readsheetdata; i++) 
				{
					
					String Vendor = xlib.getExcelData("sr", i, 0);
					String DeliveryPerson = xlib.getExcelData("sr",i,1);
					String ReceivingLocation = xlib.getExcelData("sr", i, 2);
					String ReceivedDate = xlib.getExcelData("sr", i, 3);
					String PurchaseOrder = xlib.getExcelData("sr", i, 4);
					String POProduct = xlib.getExcelData("sr", i, 5);
					String ReceivedQuantity = xlib.getExcelData("sr", i, 6);
					String ActualLocation = xlib.getExcelData("sr", i, 7);
					//String po = xlib.getExcelData("sr", i, 8);

					
					/*System.out.println("PurchaseOrder::"+ PurchaseOrder);
					System.out.println("po::"+ po);*/
					
					loginstatus = stockreceivingpage(driver, Vendor,ReceivingLocation,DeliveryPerson,ReceivedDate,PurchaseOrder,POProduct,ReceivedQuantity,ActualLocation); 
			   } 
		}
				catch (Exception e)
				{
				e.printStackTrace();
			    }
	}
	
	
	private static boolean stockreceivingpage(WebDriver driver,String Vendor,String ReceivingLocation,String DeliveryPerson,String ReceivedDate,String PurchaseOrder,String POProduct,String ReceivedQuantity,String ActualLocation) throws InterruptedException
	{
		//driver.get("https://sigmaerpdev1-dev-ed.lightning.force.com/one/one.app#/home");
		Thread.sleep(11000);
		//app launcher
        driver.findElement(By.xpath("//div[@class=\"slds-icon-waffle\"]")).click();
        Thread.sleep(9000);
        //driver.findElement(By.xpath("(//div[@ class=\"appTileTitle\"])[1]")).click();
		
        //app launcher look up field
		driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).sendKeys("Stock Receiving");
		//stock receiving link
        driver.findElement(By.xpath("(//span[@class=\"label slds-truncate slds-text-link\"]/mark)[1]")).click();
        //stock receiving new tab
        driver.findElement(By.xpath("//div[@title=\"New\"]")).click();
        
        
        /*//more button
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_reset slds-context-bar__label-action\"]")).click();
		Thread.sleep(5000);
		//stock receiving tab
		driver.findElement(By.xpath("//one-tmp-menu-item[3]")).click();
		Thread.sleep(3000);
		*/
		//Stock receiving tab;
		//driver.get("https://sigmaerpdev1-dev-ed.lightning.force.com/one/one.app?source=alohaHeader#/sObject/Stock_In__c/list?filterName=Recent");
		
		Thread.sleep(9000);
		//new button;
		//driver.findElement(By.xpath("(//li[@class=\"slds-button slds-button--neutral slds-truncate\"])[1]"));
		//driver.get("https://sigmaerpdev1-dev-ed.lightning.force.com/one/one.app#eyJjb21wb25lbnREZWYiOiJvbmU6YWxvaGFQYWdlIiwiYXR0cmlidXRlcyI6eyJhZGRyZXNzIjoiaHR0cHM6Ly9zaWdtYWVycGRldjEtZGV2LWVkLS1jLmFwNS52aXN1YWwuZm9yY2UuY29tL2FwZXgvTGlnaHRpbmdBcHBJblZGUGFnZT9fQ09ORklSTUFUSU9OVE9LRU49Vm1wRlBTeE5ha0Y0VDBNd2QwNVRNSGRQUmxGM1RtcHZlazFVYnpGT2VUUTBUV3BXWVN4WmVHTlNNRUUzYVdWMFJIaExSVWs1ZGtOM1kyOXFMRmxYV20xTlZGa3kmY29tbW9uLnVkZC5hY3Rpb25zLkFjdGlvbnNVdGlsT1JJR19VUkk9JTJGYTBnJTJGZSZzT2JqZWN0TmFtZT1hMGcmbmF2aWdhdGlvbkxvY2F0aW9uPURFVEFJTCZzYXZlX25ld191cmw9JTJGYTBnJTJGZSUzRl9DT05GSVJNQVRJT05UT0tFTiUzRFZtcEZQU3hOYWtGNFQwTXdkMDVUTUhkUFJsRjNUbXB2ZWsxVWJ6Rk9lVFEwVFdwV1lTeFplR05TTUVFM2FXVjBSSGhMUlVrNWRrTjNZMjlxTEZsWFdtMU5WRmt5JTI2Y29tbW9uLnVkZC5hY3Rpb25zLkFjdGlvbnNVdGlsT1JJR19VUkklM0QlMjUyRmEwZyUyNTJGZSZzZmRjLm92ZXJyaWRlPTEmdmZSZXRVUkxJblNGWD0lMkZhMGclMkZvIn19");
	 	
			
		//Vendor lookup 
		WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		try {
		Thread.sleep(5000);// entering purchase order
		System.out.println("Entered");
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(Vendor);
		Thread.sleep(5000);
		Actions action=new Actions(driver);
		System.out.println("action"+action);
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[1]/li"));
		List<WebElement> lst=driver.findElements(By.xpath("//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"]"));
		for (WebElement we : lst) {
		String str = we.getText();
		System.out.println(str);
		if (str.equalsIgnoreCase("Z_Bengaluru_Vendor"))
		{
			System.out.println(str);
			
		System.out.println("Entered if Statement");
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
		
		//Receiving Location lookup
		try{
		Thread.sleep(5000);// entering purchase order
		System.out.println("Entered");
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).click();
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).sendKeys(ReceivingLocation);
		Thread.sleep(5000);
		Actions action=new Actions(driver);
		System.out.println("action"+action);
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[3]/li"));
		List<WebElement> lst=driver.findElements(By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[3]"));
		for (WebElement we : lst) {
		String str = we.getText();
		System.out.println(str);
		if (str.equalsIgnoreCase(ReceivingLocation))
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
		
		
		
		//delivery person lookup
		try{
			Thread.sleep(5000);// entering purchase order
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]"));
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(DeliveryPerson);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[2]/li"));
			List<WebElement> lst=driver.findElements(By.xpath("(//div[@class=\"slds-media slds-listbox__option slds-listbox__option_entity slds-listbox__option_has-meta\"])[2]"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(DeliveryPerson))
			{
				System.out.println(str);
				
			System.out.println("Entered if Statement2");
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
		
		
	/*	
		
		// date picker=>  for Order date field
				//driver.findElement(By.xpath("(//input[@class=\"slds-input input\"])[2]")).click();
				//todays xapth in calender
				List<WebElement> allDates=driver.findElements(By.xpath("//span[@class=\"today slds-show--inline-block slds-text-link slds-p-bottom--x-small\"]"));
				Thread.sleep(3000);
				for(WebElement ele:allDates)
				{
					
					String date=ele.getText();
					
					if(date.equalsIgnoreCase("Today"))
					{
						ele.click();
						break;
					}
				}
		
		*/
		//received date
         driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).sendKeys(ReceivedDate);
         
		//add Products button
	    driver.findElement(By.xpath("//button[@id=\"addProducts\"]")).click();
	
	
	
	//PO Products lookup
	try{
		Thread.sleep(5000);// entering purchase order
		System.out.println("Entered");
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]"));
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).sendKeys(PurchaseOrder);
		Thread.sleep(5000);
		Actions action=new Actions(driver);
		System.out.println("action"+action);
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[4]/li[1]"));
		List<WebElement> lst=driver.findElements(By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[10]"));
		for (WebElement we : lst) {
		String str = we.getText();
		System.out.println(str);
		if (str.contains("PO-00079"))
		{
			System.out.println(str);
			
		System.out.println("Entered inside ifStatement");
		Thread.sleep(5000);
		we.click();
		action.click();
		action.build().perform();
		Thread.sleep(9000);
		}
		else
		{
			System.out.println("come to else statement");
		}
		}
		
		
		} catch (Exception e) {

		}
	
	//POP
	try{
		Thread.sleep(5000);
		System.out.println("Entered");
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]"));
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).sendKeys("po-");
		
		Thread.sleep(8000);
		//driver.findElement(By.xpath("(//ul[@class=\"slds-lookup__list\"])[5]/li")).click();
		
		Actions action=new Actions(driver);
		System.out.println("action"+action);
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@class=\"slds-lookup__list\"])[5]"));
		List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[5]"));
		for (WebElement we : lst) {
		String str = we.getText();
		System.out.println(str);
		if (str.contains(POProduct))
		{
			System.out.println(str);
			
		System.out.println("Entered inside pop ifStatement");
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
	
	//Received Qty 
	driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).sendKeys(ReceivedQuantity);
	//Actual Location
	try{
		Thread.sleep(5000);
		System.out.println("Entered");
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[3]"));
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[3]")).sendKeys(ActualLocation);
		Thread.sleep(5000);
		Actions action=new Actions(driver);
		System.out.println("action"+action);
		List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[6]"));
		for (WebElement we : lst) {
		String str = we.getText();
		System.out.println(str);
		if (str.contains(ActualLocation))
		{
			System.out.println(str);
			
		System.out.println("Entered if Statement2");
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
	Thread.sleep(3000);
	Select sel = new Select(driver.findElement(By.xpath("//select[@class=\"slds-select select uiInput uiInputSelect uiInput--default uiInput--select\"]")));
	sel.selectByVisibleText("Verified");
	Thread.sleep(9000);
	
	//submit button
	driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--brand\"])[1]")).click();
	//save button
	driver.findElement(By.xpath("//button[@class=\"slds-button slds-button--brand\"]")).click();
	Thread.sleep(5000);
		//cross symbol
	//driver.findElement(By.xpath("(//div[@role=\"alert\"])[2]")).click();
		return false;

		
	
	
	}
	
}
