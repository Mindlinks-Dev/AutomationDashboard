package com.sigmaERP_Lightening;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePackagesPage {

	public static boolean loginstatus;
	public String Customer;
	public String Location;
	public String PackagedBy;
	public String Status;
	public String SONO;
	public String PACKAGEDQTY;
	public String EnterQuantity;
	
	public static void packages(WebDriver driver)
	{
		try 
		{
				ExcelReading xlib = new ExcelReading();
				int readsheetdata = xlib.getRowCount("pk");
				for (int i = 1; i <= readsheetdata; i++) 
				{
					
					String Customer = xlib.getExcelData("pk", i, 0);
					String Location = xlib.getExcelData("pk", i, 1);
					String PackagedBy = xlib.getExcelData("pk",i,2);
					String Status = xlib.getExcelData("pk", i, 3);
					String SONO = xlib.getExcelData("pk", i, 4);
					String PACKAGEDQTY = xlib.getExcelData("pk", i, 5);
					String EnterQuantity = xlib.getExcelData("pk", i, 6);
					
					
					
					loginstatus = packagespage(driver, Customer,Location,PackagedBy,Status,SONO,PACKAGEDQTY,EnterQuantity); 
			   } 
		}
				catch (Exception e)
				{
				e.printStackTrace();
			    }
	}
	
	
	private static boolean packagespage(WebDriver driver,String Customer,String Location,String PackagedBy,String Status, String SONO,String PACKAGEDQTY,String EnterQuantity ) throws InterruptedException
	{
		//driver.get("https://sigmaerpdev1-dev-ed.lightning.force.com/one/one.app#/home");
		/*//more tab
		driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_reset slds-context-bar__label-action\"]")).click();
		Thread.sleep(5000);
		//packages tab
		driver.findElement(By.xpath("//one-tmp-menu-item[5]")).click();*/
		
		
		//app launcher
        driver.findElement(By.xpath("//div[@class=\"slds-icon-waffle\"]")).click();
        Thread.sleep(9000);
      //app launcher look up field
      	driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).sendKeys("packages");
      	//packages link
      	driver.findElement(By.xpath("(//span[@class=\"label slds-truncate slds-text-link\"]/mark)")).click();
		//new button
		driver.findElement(By.xpath("(//li[@class=\"slds-button slds-button--neutral slds-truncate\"])[1]")).click();
		
		//Cuctomer lookup
		WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		try{
			Thread.sleep(5000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(Customer);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"]"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(Customer))
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
		
		//location lookup
		
		try{
			Thread.sleep(8000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(Location);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[2]"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(Location))
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
		
		//Packaged By 
	    
		try{
			Thread.sleep(3000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(PackagedBy);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])/li"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(PackagedBy))
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
		Thread.sleep(3000);
		Select sel = new Select(driver.findElement(By.xpath("(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[1]")));
		sel.selectByVisibleText("Ready");
		Thread.sleep(3000);
		
		
		//so no
		try{
			Thread.sleep(5000);
			System.out.println("Entered");
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).click();
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(SONO);
			Thread.sleep(5000);
			Actions action=new Actions(driver);
			System.out.println("action"+action);
			List<WebElement> lst=driver.findElements(By.xpath("(//ul[@role=\"presentation\"])/li"));
			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.contains(SONO))
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
		
		//check box
		driver.findElement(By.xpath("//input[@ class=\"uiInput uiInputCheckbox uiInput--default uiInput--checkbox\"]")).click();
		Thread.sleep(2000);
		//packaged qty
		driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[1]")).sendKeys(PACKAGEDQTY);
		//select manually
		Thread.sleep(8000);
		//driver.findElement(By.xpath("//a[@title=\"Select Stock Manually\"]")).click();
		 driver.findElement(By.xpath("//a[@title=\"Select Stock Manually\"]")).click();
		//alert
		 Thread.sleep(9000);
		 //WAIT UNTIL ALERT OPENS AND THEN CLICK ON OK/ACCEPT
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Thread.sleep(9000);
        alert.accept(); //ACCEPT ACTION
        
        //click on Plus symbol
        Thread.sleep(5000);
        //driver.findElement(By.xpath("//img[@title=\"Show Stock\"]")).click();
        driver.findElement(By.xpath("//td/a")).click();
        //enter Qty field
        Thread.sleep(9000);
        driver.findElement(By.xpath("(//td[@data-label=\"Enter Quantity\"])[1]/input")).sendKeys(EnterQuantity);
        Thread.sleep(2000);
        
        //save button
        driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--brand\"])[1]")).click();
        
        Thread.sleep(2000);
        //submit button
        driver.findElement(By.xpath(".//*[@id='showNormalFlow']/div/div[2]/button[1]")).click();
        
		return false;
		
	}
	
}
