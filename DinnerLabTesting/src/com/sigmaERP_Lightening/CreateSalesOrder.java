package com.sigmaERP_Lightening;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateSalesOrder {
	public static boolean loginstatus;
	public String CustomerName;
	public String Billingperson;
	public String Product;
	public String Quantity;
	
	
	public static void salesorder(WebDriver driver)
	{
		try 
		{
				ExcelReading xlib = new ExcelReading();
				int readsheetdata = xlib.getRowCount("so");
				for (int i = 1; i <= readsheetdata; i++) 
				{
					
					String CustomerName = xlib.getExcelData("so", i, 0);
					String Billingperson = xlib.getExcelData("so", i, 1);
					String Product = xlib.getExcelData("so",i,2);
					String Quantity = xlib.getExcelData("so", i, 3);
					
					
					
					loginstatus = SalesOrderPage(driver, CustomerName,Billingperson,Product,Quantity); 
			   } 
		}
				catch (Exception e)
				{
				e.printStackTrace();
			    }
	}
	
	private static boolean SalesOrderPage(WebDriver driver,String CustomerName,String Billingperson,String Product,String Quantity) throws InterruptedException
	{
		//driver.get("https://sigmaerpdev1-dev-ed.lightning.force.com/one/one.app#/n/SigmaMVP__Sales_order");
		//app launcher
        driver.findElement(By.xpath("//div[@class=\"slds-icon-waffle\"]")).click();
        Thread.sleep(9000);
      //app launcher look up field
      	driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).sendKeys("Sales Order");
      	//sales order link
      	driver.findElement(By.xpath("(//span[@class=\"label slds-truncate slds-text-link\"]/mark)[3]")).click();
      	
      	
		//customer name
		try{
			Thread.sleep(5000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(CustomerName);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("(//div[@role=\"option\"])[2]"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(CustomerName))
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
		Thread.sleep(3000);
	/*	
		//Billing person
		try{
			Thread.sleep(5000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]")).sendKeys(Billingperson);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("//ul[@role=\"presentation\"]/li"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.equalsIgnoreCase(Billingperson))
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
		*/
		//add product button
		driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral\"])[2]")).click();
		//product lookup
		try{
			Thread.sleep(8000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(Product);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("//ul[@role=\"presentation\"]/li"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(Product))
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
		
		//Qty field
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputNumber uiInput--default uiInput--input\"])[1]")).sendKeys(Quantity);
		Thread.sleep(3000);
		//add to Orders button
		driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral slds-button slds-button--neutral uiButton\"])[2]")).click();
		Thread.sleep(3000);
		//next button
		driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral\"])[3]")).click();
		Thread.sleep(3000);
		//cross symbol
		driver.findElement(By.xpath("(//button[@class=\"slds-button\"])[2]")).click();
		//skip payment
		driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral slds-button slds-button--neutral uiButton\"])[5]")).click();
		Thread.sleep(2000);
		
	/*	//proced to payment
		driver.findElement(By.xpath("title=\"Proceed to Payment\"]")).click();
		//cash payment
		driver.findElement(By.xpath("//img[@src=\"/resource/Cash\"]")).click();
		*/
		return false;
		
	}


}
