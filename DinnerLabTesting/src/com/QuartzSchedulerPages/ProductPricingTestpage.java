package com.QuartzSchedulerPages;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.util.ExcelLib;

public class ProductPricingTestpage {
	WebDriver driver;

	private String ProductName;
	private String Q1STARTRANGE;
	private String Q1ENDRANGE;
	private String Q1PRICE;
	private String PriceBook;
	private String PassorFail;
	public static boolean newProductstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(ProductPricingTestpage.class);

	public boolean ProductPricingInitialPage(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("ProductPricingPage");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				ProductName = xllib.getExcelData("ProductPricingPage", i, 0);
				Q1STARTRANGE = xllib.getExcelData("ProductPricingPage", i, 1);
				Q1ENDRANGE = xllib.getExcelData("ProductPricingPage", i, 2);
				Q1PRICE = xllib.getExcelData("ProductPricingPage", i, 3);
				PriceBook = xllib.getExcelData("ProductPricingPage", i, 4);
				PassorFail =xllib.writeToExcel("ProductPricingPage", i,5,"Pass");
				newProductstatus = createProduct(driver, ProductName,Q1STARTRANGE,
						Q1ENDRANGE, Q1PRICE, PriceBook);

				System.out.println("Login Count:" + i);
			}

		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

	private boolean createProduct(WebDriver driver,String ProductName, String Q1STARTRANGE,
			String Q1ENDRANGE, String Q1PRICE, String PriceBook) {
		try {
			Thread.sleep(5000);

			// Click on the Product Tab
			driver.findElement(By.xpath("//a[@title='Products']")).click();
			Thread.sleep(5000);
			
			//click on search icon
		System.out.println("product Pricing page");
		WebElement Wb=driver.findElement(By.xpath("//div[@class=\"inputWrapper slds-grid slds-grid--align-center slds-grid--vertical-align-center slds-size--1-of-1\"]/descendant::input"));
		Wb.click();
		System.out.println("product Pricing page1");
		Wb.clear();
		Thread.sleep(3000);
		Wb.sendKeys(ProductName);
		
		Thread.sleep(6000);
		WebElement  wb2=driver.findElement(By.xpath("(//img)[1]"));
		wb2.click();
	
		System.out.println("Product Related tab1");
			// Related of the Product
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.findElement(
					By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::ul[@class='tabs__nav']/descendant::li[1]"))
					.click();
			System.out.println("Product Related tab2");
		
			  Thread.sleep(5000);

			  JavascriptExecutor javascript = (JavascriptExecutor) driver;
			  javascript.executeScript("alert('Defining Product Pricing Ranges');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
			
			
			
			
			Thread.sleep(5000);
			// Productpricing new Button
			driver.findElement(
					By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::div[@class='container forceRelatedListSingleContainer'][1]/descendant::div[@class='slds-card__header slds-grid']/descendant::div[@class='slds-no-flex']/descendant::div[@title='New']"))
					.click();
			Thread.sleep(5000);
			//Q1STARTRANGE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[1]"))).sendKeys(Q1STARTRANGE);
			
			Thread.sleep(5000);

			//Q1ENDRANGE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[2]"))).sendKeys(Q1ENDRANGE);
			
			
			Thread.sleep(5000);
			//Q1PRICE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[3]"))).sendKeys(Q1PRICE);
			
			
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			 js1.executeScript("window.scrollBy(0,1000)");
			
			 
			//PriceBook for textfield
			Thread.sleep(5000);
			driver.findElement(By.xpath(("(//div[@class=\"autocompleteWrapper slds-grow\"])[3]/input"))).sendKeys(PriceBook);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class=\"undefined lookup__menu uiAbstractList uiAutocompleteList uiInput uiAutocomplete uiInput--default uiInput--lookup\"]/descendant::ul/li[1]")).click();
			
			Thread.sleep(5000);
			
		    //Save Button
			driver.findElement(By.xpath("(//span[text()=\"Save\"])[2]")).click();
      
		   //Alert Prroduct price message
			Thread.sleep(10000);
			
			  JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
			  javascript1.executeScript("alert('Product Pricing Range Created Sucessfully');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
			
			
			
 
		} catch (Exception e) {
			System.out.println("Product Pricing");

		}
		return false;

	}

}



