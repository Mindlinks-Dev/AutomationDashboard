package com.sigmaERP_Lightening;

import java.util.Iterator;
import java.util.List;

import org.apache.xerces.util.SynchronizedSymbolTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreatePurchaseOrderPage
{   public String vendor;
	public String Product;
	public String Quantity;
	public String OrderDate;
	public String ExpectedDate;
	public String ExpectedDeliveryDate;
	
	public static boolean loginstatus;
	
	public static void PurchaseOrder(WebDriver driver ) throws IllegalStateException 
	{
		try 
		{
				ExcelReading xlib = new ExcelReading();
				int readsheetdata = xlib.getRowCount("po");
				for (int i = 1; i <= readsheetdata; i++) 
				{
					String vendor = xlib.getExcelData("po",i, 0);
					String Product = xlib.getExcelData("po", i, 1);
					String OrderDate=xlib.getExcelData("po", i, 2);
					String ExpectedDate=xlib.getExcelData("po", i, 3);
					String Quantity = xlib.getExcelData("po", i, 4);
					String ExpectedDeliveryDate=xlib.getExcelData("po", i, 5);
					
					loginstatus = purchaseorderpage(driver, vendor,Product,OrderDate,ExpectedDate, Quantity,ExpectedDeliveryDate); 
			   } 
		}
				catch (Exception e)
				{
				e.printStackTrace();
			    }
		
	}
	

	



private static boolean purchaseorderpage(WebDriver driver,String vendor, String Product,String OrderDate,String ExpectedDate, String Quantity,String ExpectedDeliveryDate) throws InterruptedException
{
	
		//driver.get("https://sigmaerpdev1-dev-ed.lightning.force.com/one/one.app#/home");
	
        	//app launcher
	        driver.findElement(By.xpath("//div[@class=\"slds-icon-waffle\"]")).click();
	        Thread.sleep(9000);
	        //driver.findElement(By.xpath("(//div[@ class=\"appTileTitle\"])[11]")).click();
	        
	        //app launcher look up field
			driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).sendKeys("Purchase Orders");
			//stock receiving link
	        driver.findElement(By.xpath("(//span[@class=\"label slds-truncate slds-text-link\"]/mark)[1]")).click();
		//purchase Order tab
		//driver.findElement(By.xpath("(//span[@class=\"slds-truncate\"])[9]")).click();
		
		Thread.sleep(6000);
		//new button
		driver.findElement(By.xpath("(//li[@class=\"slds-button slds-button--neutral slds-truncate\"])[1]")).click();
	
	/*
		// vendor look up-- starts here---
		List var = driver.findElements(By.xpath("//iframe"));
		
	   System.out.println(var);
		int size =var.size();
		System.out.println(size);
		
	WebElement frame=	driver.findElement(By.xpath("//iframe[@frameborder=\"0\"]"));
		driver.switchTo().frame(frame);
		Thread.sleep(3000);
		System.out.println("shifted to frame");
		Thread.sleep(3000);
		
	WebElement vendor1 =	driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[2]"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(vendor1);
		actions.click();
		actions.sendKeys("mindtest vendor");
		actions.build().perform();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//li[@class=\"slds-lookup__item\"])[1]")).click();
	
		// vendor look up --- ends here---	
		*/
		//Vendor lookup 
		Thread.sleep(5000);
		WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		try {
		Thread.sleep(5000);// entering purchase order
		System.out.println("Entered");
		driver.findElement(By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon--right'])[2]/input")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon--right'])[2]/input")).sendKeys(vendor);
		Thread.sleep(5000);
		Actions action=new Actions(driver);
		System.out.println("action"+action);
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[2]"));
		List<WebElement> lst=driver.findElements(By.xpath("//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"]"));
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[1]/li"));
		for (WebElement we : lst) {
		String str = we.getText();
		System.out.println(str);
		if (str.equalsIgnoreCase("Z_Bengaluru_Vendor"))
		{
			System.out.println(str);
			
		System.out.println("Entered if Statement");
		Thread.sleep(8000);
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
		Select sel = new Select(driver.findElement(By.xpath("//select[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input select uiInput uiInputSelect uiInput--default uiInput--select\"]")));
		sel.selectByVisibleText("Submitted");
		Thread.sleep(8000);
		
		
		//order date
		driver.findElement(By.xpath("(//input[@class=\"slds-input input\"])[2]")).sendKeys(OrderDate);
		Thread.sleep(5000);
		//expected date
		driver.findElement(By.xpath("(//input[@class=\"slds-input input\"])[1]")).sendKeys(ExpectedDate);
		
		/*
		// date picker=>  for Order date field
		driver.findElement(By.xpath("(//input[@class=\"slds-input input\"])[2]")).click();
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
	/*	
		//date picker=> for Expected date field 
		driver.findElement(By.xpath("(//input[@class=\"slds-input input\"])[1]")).click();
		//todays xapth in calender
		List<WebElement> allDates1=driver.findElements(By.xpath("(//span[@class=\"slds-day weekday nextMonth DESKTOP uiDayInMonthCell--default\"])[8]"));
		Thread.sleep(3000);
		for(WebElement ele:allDates1)
		{
			
			String date=ele.getText();
			
			if(date.equalsIgnoreCase("10"))
			{
				ele.click();
				break;
			}
		}
		*/
		Thread.sleep(5000);
		  //add product button
		driver.findElement(By.xpath("//button[@class=\"slds-button slds-button--neutral\"]")).click();
		Thread.sleep(2000);
		
		/*
		//add product name
		WebElement app = driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[3]"));
		Actions act2 = new Actions(driver);
		//act2.moveToElement(app);
		Thread.sleep(9000);
		act2.click(app);
		act2.sendKeys("MindlinksProduct");
		act2.build().perform();
		Thread.sleep(9000);
		*/
		
		
		try {
		Thread.sleep(5000);// entering purchase order
		System.out.println("Entered");
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[3]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[3]")).sendKeys(Product);
		Thread.sleep(5000);
		Actions action=new Actions(driver);
		System.out.println("action"+action);
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[3]"));
		List<WebElement> lst=driver.findElements(By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[2]"));
		//List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])[1]/li"));
		for (WebElement we : lst) {
		String str = we.getText();
		System.out.println(str);
		if (str.equalsIgnoreCase("Mindlinks1Product"))
		{
			System.out.println(str);
			
		System.out.println("Entered if Statement");
		Thread.sleep(8000);
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
		
		/*//date picker=> for Expected delivery date field 
		Thread.sleep(5000);
				//driver.findElement(By.xpath("(//div[@class='form-element uiInput uiInputDate uiInput--default uiInput--input uiInput--datetime'])[3]/input")).click();
				//todays xapth in calender
				//Thread.sleep(3000);
				List<WebElement> allDates11=driver.findElements(By.xpath("(//span[@class=\"slds-day weekday DESKTOP uiDayInMonthCell--default\"])[5]"));
				Thread.sleep(3000);
				System.out.println("123");
				for(WebElement ele:allDates11)
				{
					
					String date=ele.getText();
					
					if(date.equalsIgnoreCase("9"))
					{
						Thread.sleep(3000);
						ele.click();
						break;
					}
				}		
*/
		
			
				//Qty field
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//div[@class=\"slds-form-element slds-size_1-of-2\"])[2]/input")).sendKeys(Quantity);

				
			//expected delivery date
				driver.findElement(By.xpath("(//input[@class=\"slds-input input\"])[3]")).sendKeys(ExpectedDeliveryDate);
				
		//submit button
				Thread.sleep(5000);
				driver.findElement(By.xpath("//button[@class=\"slds-button slds-button--brand\"]")).click();
				
				
				//save button
				driver.findElement(By.xpath("//button[@class=\"slds-button slds-button--brand\"]")).click();
		
		
		return false;
	}
		
}

		
		//Thread.sleep(9000);
		//vendor name search box;
		/*WebElement app = driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[2]"));
		Actions act2 = new Actions(driver);
		//act2.moveToElement(app);
		Thread.sleep(9000);
		act2.click(app);
		act2.sendKeys("mindtest vendor");
		act2.build().perform();
		Thread.sleep(9000);
		//vendor name list
		driver.findElement(By.xpath("(//li[@class=\"slds-lookup__item\"])[1]")).click();
*/
		
				//search box
		
		       /* driver.findElement(By.xpath("//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[2]")).sendKeys("mindtest vendor");
				//Iterator<String> itr=	driver.getWindowHandles().iterator();
				//String parentwindow=itr.next();
				//String childwindow=itr.next();
				driver.findElements(By.xpath("//iframes"));
				
		        driver.switchTo().frame(1);
				//list of vendors name 
				driver.findElement(By.xpath("(//li[@class=\"slds-lookup__item\"])[1]")).click();
				System.out.println("go clicked");
				Thread.sleep(5000l);
				//driver.switchTo().window(parentwindow);
		
		
		
		
		
		//dropdown list
		List<WebElement> lst=driver.findElements(By.xpath("//div[@class=\"slds-picklist slds-dropdown-trigger slds-dropdown-trigger--click slds-size--1-of-1\"]"));
		Thread.sleep(5000);
		System.out.println(lst);
		for (WebElement we : lst) 
		{
		String str = we.getText();
		if (str.equalsIgnoreCase("Submitted"))
		{
		we.click();
		}
	}
		
    }
	catch (Exception e) {
		e.printStackTrace();

	}

	return false;*/

	
