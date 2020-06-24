package com.stapp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Inbound extends Testbase {

	// -----------------------------------------CREATE PRODUCT--------------------------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 1)
	public static void product(String RecordType, String ProductName, String AttributeType, String ProductCode,
			String UOM, String Price) throws InterruptedException {
		driver.manage().window().maximize();
		Thread.sleep(8000);

		// Products tab
		// driver.findElement(By.xpath("//a[@title='Products']/descendant::span")).click();
		driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[3]")).click();
		Thread.sleep(5000l);

		// New button
		driver.findElement(By.xpath("(//div[@class=\"slds-grid\"])[3]//div[@title=\"New\"]")).click();
		Thread.sleep(5000l);

		// Record type
		if (RecordType.equals("BOM")) {
			driver.findElement(By.xpath("(//span[@class='slds-radio--faux'])[1]")).click();
			Thread.sleep(3000);
		} else {
			driver.findElement(By.xpath("(//span[@class='slds-radio--faux'])[2]")).click();
			Thread.sleep(3000);
		}

		// Next button
		driver.findElement(By.xpath("//div[@class=\"forceChangeRecordTypeFooter\"]/button[2]")).click();
		Thread.sleep(3000);

		// Product name field
		driver.findElement(By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input'])[1]//input"))
				.sendKeys(ProductName);
		Thread.sleep(3000);

		// Attribute type
		driver.findElement(By.xpath("(//span[text()='Attribute Type'])[2]/following::div[1]")).click();
		if (AttributeType.equals("BULK")) {
			driver.findElement(By.xpath("//a[@title='BULK']")).click();
		} else {
			driver.findElement(By.xpath("//a[@title='SERIALIZED']")).click();
		}

		// Product code
		// driver.findElement(By.xpath("(//div[@class='uiInput uiInputText
		// uiInput--default
		// uiInput--input'])[2]//input")).sendKeys(ProductCode);
		driver.findElement(By.xpath("(//span[text()='Product Code'])[2]/following::input[1]")).sendKeys(ProductCode);
		Thread.sleep(3000);

		// Active button click
		driver.findElement(By.xpath("//span[text()='Active']//following::input[1]")).click();
		// driver.findElement(By.xpath("(//div[@class='uiInput uiInputCheckbox
		// uiInput--default uiInput--checkbox'])[1]//input")).click();
		Thread.sleep(3000);

		// Stock item click
		// driver.findElement(By.xpath("(//div[@class='uiInput uiInputCheckbox
		// uiInput--default uiInput--checkbox'])[3]//input")).click();
		driver.findElement(By.xpath("//span[text()='Stock Item']//following::input[1]")).click();
		Thread.sleep(3000);

		// clicking on dimension tag
		driver.findElement(By.xpath("(//a[@class='select'])[4]")).click();
		driver.findElement(By.xpath("//a[@title='Yes']")).click();
		Thread.sleep(3000);

		// UOM
		driver.findElement(By.xpath("(//span[text()=\"UOM\"]/following::a)[1]")).click();
		Thread.sleep(5000);
		List<WebElement> lst1 = driver.findElements(By.xpath("(//div[@class=\"select-options\"])[7]/descendant::li"));
		System.out.println(lst1);
		for (WebElement we : lst1) {
			if (we.getText().contains(UOM)) {
				Thread.sleep(5000);
				we.click();

			}
		}

		// Clicks on save button
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(6000);

		// Goes to related list
		driver.findElement(By.xpath("//span[text()='Related']")).click();
		Thread.sleep(5000);

		// clicks on add standard price book button
		driver.findElement(By.xpath("//div[text()='Add Standard Price']")).click();
		Thread.sleep(5000);

		// entering value of list price
		driver.findElement(By.xpath("//span[text()='List Price']/following::input[1]")).sendKeys(Price);
		Thread.sleep(5000);

		// clicking on save button
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(5000);

		System.out.println("product creation sucessfull");
	}

	// ----------------------------------------PURCHASE ORDER------------------------------------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority =2)
	public static void transactions(String SLNO, String PURCHASEORDER, String ACCOUNT, String CONTACT, String PRICEBOOK,
			String TAXTREATMENT, String PRODUCT, String QTY) throws InterruptedException {
		Thread.sleep(18000);

		System.out.println("entered transaction tab");

		// clicks on Transaction tab
		driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[4]")).click();
		Thread.sleep(5000);

		// driver.findElement(By.xpath("//div[@class=\"bBottom\"]/descendant::span[text()='Transactions']")).click();
		/*
		 * driver.findElement(By.xpath("//a//span[text()='Transactions']")).
		 * click(); Thread.sleep(5000);
		 */

		// clicks on New
		driver.findElement(By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li")).click();
		Thread.sleep(3000);

		// select purchase order type
		driver.findElement(By.xpath("//label[text()='Transaction Type']/following::input[1]")).sendKeys(PURCHASEORDER);
		
		Thread.sleep(5000);

		List<WebElement> list2 = driver.findElements(By.xpath("//div[@class=\"slds-show slds-lookup__menu\"]/ul/li"));
		for (WebElement we : list2) {
			if (we.getText().contains(PURCHASEORDER)) {
				Thread.sleep(3000);
				we.click();
			}
		}
		Thread.sleep(5000);
        // clicks on accept button
		driver.findElement(By.xpath("//button[text()='Accept']")).click();
		Thread.sleep(10000);

		// select account
		driver.findElement(By.xpath("//label[text()='Account']/following::input[1]")).sendKeys(ACCOUNT);
		Thread.sleep(5000);

		
		List<WebElement> list3 = driver
				.findElements(By.xpath("(//div[@class=\"scrollable uiScrollerWrapper\"])[2]//ul/li"));
		for (WebElement we : list3) {
			if (we.getText().contains(ACCOUNT)) {
				Thread.sleep(5000);
				we.click();
			}
		}

		// select contact
		driver.findElement(By.xpath("//label[text()='Contact']/following::input[1]")).sendKeys(CONTACT);
		Thread.sleep(5000);

		List<WebElement> list4 = driver
				.findElements(By.xpath("(//div[@class=\"scrollable uiScrollerWrapper\"])[3]//ul/li"));
		for (WebElement we : list4) {
			if (we.getText().contains(CONTACT)) {
				Thread.sleep(5000);
				we.click();
			}
		}

		// clicks on Save button
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(18000);

		// scroll down
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(8000);

		// adding product
		driver.findElement(By
				.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon_right\"])[8]//input"))
				.sendKeys(PRODUCT);
		Thread.sleep(5000);

		List<WebElement> list5 = driver.findElements(By.xpath(
				"(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon_right\"])[6]//following::ul/li"));
		for (WebElement we : list5) {
			if (we.getText().contains(PRODUCT)) {
				Thread.sleep(7000);
				we.click();
			}
		}

		
		
		Thread.sleep(10000);
	
		//try{
		// enter quantity
		
				
		driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-grow\"])[6]/input")).clear();;
		driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-grow\"])[6]/input")).sendKeys(QTY);
	
		
		System.out.println("Clicks on Qty field");
		
		
		
		//clicks on save
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(10000);

		//clicks on Transaction tab to get TD number
		driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[4]")).click();
		Thread.sleep(5000);

		List<WebElement> myElements = driver.findElements(By.xpath("//table[@class=\"slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable\"]/descendant::tbody/tr"));
		   int count=0;
		   
		   System.out.println("index 1:"+myElements.get(1).getText());
		   String tempTD = myElements.get(0).getText();
		   System.out.println("tempTD::"+tempTD);
		   String lines[] = tempTD.split("\\r?\\n");
		  
		   excel.setCellData("stockreceiving", "TDNO", 2, lines[1]);
	}
	// ----------------------------------STOCK RECEIVING--------------------------------------------------------------------

		@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 3)
		public static void stockreceiving(String Vendor, String Company, String Warehouse, String ReceiveDate, String TDNO,
				String Quantity, String Bin) throws InterruptedException {

			Thread.sleep(8000);

			// clicks on SR tab
			// driver.findElement(By.xpath("//a[@title=\"Stock
			// Receiving\"]")).click();
			driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[5]")).click();
			Thread.sleep(5000);

			// clicks on new
			driver.findElement(By.xpath("//div[text()='New']")).click();
			Thread.sleep(5000);

			// clicks on vendor
			driver.findElement(By.xpath("//label[text()=\"Vendor\"]/following::input[1]")).click();
			driver.findElement(By.xpath("//label[text()=\"Vendor\"]/following::input[1]")).sendKeys(Vendor);
			Thread.sleep(5000);
			WebElement vendorclick = driver.findElement(By.xpath(
					"(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"])[1]/li[1]"));

			if (vendorclick.isDisplayed())
			{
				Thread.sleep(6000);

				driver.findElement(By
						.xpath("(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"])[1]/li[1]"))
						.click();
			}
			Thread.sleep(6000);
			

			// clicks on warehouse
			driver.findElement(By.xpath("(//button[@class=\"slds-button slds-pill__remove slds-button_icon-bare\"])[4]"))
					.click();
			driver.findElement(By.xpath("//label[text()=\"Warehouse Location\"]/following::input[1]")).clear();
			driver.findElement(By.xpath("//label[text()=\"Warehouse Location\"]/following::input[1]")).click();
			driver.findElement(By.xpath("//label[text()=\"Warehouse Location\"]/following::input[1]")).sendKeys(Warehouse);
			WebElement warehouse = driver.findElement(By.xpath(
					"(//ul[@class='slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds'])[4]/li"));

			if (warehouse.isDisplayed()) 
			{
				Thread.sleep(6000);

				driver.findElement(By
						.xpath("(//ul[@class='slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds'])[4]/li"))
						.click();
			}
			Thread.sleep(5000);

			// enter receive date
			driver.findElement(By.xpath("//span[text()='Received DateTime']//following::input[1]")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//span[text()='Today']")).click();
			Thread.sleep(6000);

			// click on add more products
			driver.findElement(By.xpath("//button[text()='Add More Products']")).click();
			Thread.sleep(5000);

			// select transaction
			driver.findElement(By.xpath("//label[text()='Select Transaction']/following::input[1]")).click();
			driver.findElement(By.xpath("//label[text()='Select Transaction']/following::input[1]")).sendKeys(TDNO);
			WebElement td = driver.findElement(By.xpath("(//li[@class='slds-listbox__item'][1]"));
			
			//WebElement td = driver.findElement(By.xpath("(//ul[@role=\"listbox\"])[7]/li[1]"));

			if (td.isDisplayed()) 
			{
				Thread.sleep(6000);

				td.click();
			}
			Thread.sleep(6000);

			// quantity
			driver.findElement(By.xpath("(//div[@class='slds-form-element__control slds-grow'])[6]/input"))
					.sendKeys(Quantity);
			Thread.sleep(6000);

			// selecting bin
			driver.findElement(By.xpath("(//div[@class='slds-show'])[4]//input")).sendKeys(Bin);
			WebElement bin = driver.findElement(By.xpath(
					"(//ul[@class='slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds'])[3]"));

			if (bin.isDisplayed()) 
			{
				Thread.sleep(6000);

				driver.findElement(By
						.xpath("(//ul[@class='slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds'])[3]"))
						.click();
			}
			Thread.sleep(6000);

			// save
			driver.findElement(By.xpath("//button[text()='Save']")).click();
			Thread.sleep(3000);

		}
}