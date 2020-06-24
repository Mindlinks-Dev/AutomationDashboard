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

public class StappCompleteFlow extends Testbase {

	// -----------------------------------------CREATE PRODUCT--------------------------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 1)
	public static void product(String RecordType, String ProductName, String AttributeType, String ProductCode,String UOM, String Price) throws InterruptedException
	{
		driver.manage().window().maximize();
		Thread.sleep(8000);

		// Products tab
		driver.findElement(By.xpath("//a[@title='Products']/descendant::span")).click();
		driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[3]")).click();
		Thread.sleep(5000);

		/*// New button
		driver.findElement(By.xpath("(//div[text()='New']")).click();
		//driver.findElement(By.xpath("(//div[@class=\"slds-grid\"])[3]//div[@title=\"New\"]")).click();
		Thread.sleep(5000l);

		// Record type
		if (RecordType.equals("BOM"))
		{
			driver.findElement(By.xpath("(//span[@class='slds-radio--faux'])[1]")).click();
			Thread.sleep(3000);
		} 
		else 
		{
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

		System.out.println("product creation sucessfull");*/
	}

	
	// ----------------------------------------PURCHASE ORDER------------------------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority =2)
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
		for (WebElement we : list2)
		{
			if (we.getText().contains(PURCHASEORDER)) 
			{
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

		
		List<WebElement> list3 = driver.findElements(By.xpath("(//div[@class=\"scrollable uiScrollerWrapper\"])[2]//ul/li"));
		for (WebElement we : list3) {
			if (we.getText().contains(ACCOUNT)) 
			{
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
		Thread.sleep(6000);

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

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 3)
	public static void stockreceiving(String Vendor, String Company, String Warehouse, String ReceiveDate, String TDNO,
			String Quantity, String Bin) throws InterruptedException 
	{

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

			driver.findElement(By.xpath("(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"])[1]/li[1]")).click();
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

	// ----------------------------------STAPP ORDER--------------------------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)
	public void stappOrder(String CustomerOrderNumber, String Customer, String OrderSource, String Status,
			String Currency, String Contact) throws InterruptedException
	{

		Thread.sleep(20000);

		// New Stapp Order
		// driver.findElement(By.xpath("//one-app-nav-bar/descendant::li/descendant::span[text()=\"Stapp
		// Orders\"]")).click();
		driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[6]")).click();
		System.out.println("Click on Stapp Order Tab");

		// New Button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@role=\"banner\"]/div[1]/descendant::li[2]")).click();
		System.out.println("Click on New Button");

		// Customer Order Number
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class=\"slds-card__body\"]/descendant::input[1]"))
				.sendKeys(CustomerOrderNumber);
		System.out.println("Customer Order Number");

		// Customer Name
		Thread.sleep(5000);
		
		WebElement wd1 = driver.findElement(By.xpath("(//div[@class='slds-show'])[3]"));
		Thread.sleep(3000);
		wd1.click();
		Thread.sleep(5000);
		wd1.sendKeys(Customer);
		
		
		/*driver.findElement(By.xpath("//label[text()=\"Customer\"]/following::input[1]")).click();
		driver.findElement(By.xpath("//label[text()=\"Customer\"]/following::input[1]")).sendKeys(Customer);*/
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@title=\"Automation Testing-Customer\"]")).click();
		System.out.println("Customer Name");

		
		// Status
		/*
		 * Thread.sleep(5000); driver.findElement(By.
		 * xpath("//div[@class=\"slds-grid slds-wrap paddingAround\"]/div[6]")).
		 * click(); List<WebElement> status = driver.findElements(By.xpath(
		 * "(//div[@class=\"select-options\"])[4]/descendant::li"));
		 * System.out.println("ListofFrames::+"+status); for (WebElement we :
		 * status) {
		 * 
		 * if (we.getText().equalsIgnoreCase(Status)) {
		 * 
		 * Thread.sleep(5000l); Actions act1 = new Actions(driver);
		 * act1.click(we); act1.build().perform();
		 * 
		 * } }
		 */

		// status
		WebElement we4 = driver.findElement(By.xpath("//span[text()='Status']//following::select[3]"));
		Select option1 = new Select(we4);
		option1.selectByVisibleText(Status);

		// Contact LookUp
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[text()=\"Contact\"]/following::input[1]")).click();
		driver.findElement(By.xpath("//label[text()=\"Contact\"]/following::input[1]")).sendKeys(Contact);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@title=\"Test S\"]")).click();
		System.out.println("Contact Lookup");

		// Clicks on Ready for picking
		driver.findElement(By.xpath("(//span[@class=\"slds-checkbox_faux\"])[9]")).click();
		Thread.sleep(3000);

	}

	// --------------------------------------StappOrderLine-----------------------------------------------------
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 5)

	public void stapporderline(String PRODUCT, String QTY) throws InterruptedException {
		Thread.sleep(10000);

		// Scroll upto the Element Found
		WebElement ele = driver.findElement(By.xpath("//button[text()=\"Add Order Lines\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(500);

		// Add Stapp OrderLines
		driver.findElement(By.xpath("//button[text()=\"Add Order Lines\"]")).click();
		System.out.println("Click on Add Order Lines");
		Thread.sleep(5000);

		// Adding Product
		/*
		 * driver.findElement(By.xpath(
		 * "//tr[@class=\"stappStappOrderLines\"]/descendant::input[2]")).
		 * sendKeys(PRODUCT); Thread.sleep(3000); driver.findElement(By.xpath(
		 * "//span[@id=\"listbox-option-unique-id-01\"]/descendant::span[text()=\"TestBook-1\"]"
		 * )).click(); System.out.println("Click on PRODUCT");
		 * Thread.sleep(5000);
		 */

		// adding product
		driver.findElement(By.xpath("//tr[@class=\"stappStappOrderLines\"]/descendant::input[2]")).sendKeys(PRODUCT);
		Thread.sleep(3000);
		WebElement prodclick = driver.findElement(By.xpath(
				"(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"])[5]/li[1]"));
		System.out.println("Click on PRODUCT");
		Thread.sleep(5000);

		if (prodclick.isDisplayed()) {
			Thread.sleep(5000);

			driver.findElement(By
					.xpath("(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"])[5]/li[1]"))
					.click();
		}

		// Quantity
		driver.findElement(By.xpath("//td[@data-label=\"QTY\"]/input")).sendKeys(QTY);
		System.out.println("Click on Quantity");
		Thread.sleep(5000);

		// AutoAllocation Button
		driver.findElement(By.xpath("//button[text()=\"A\"]")).click();
		System.out.println("Click on Allocation Button");
		Thread.sleep(5000);

		// AutoAllocation Button
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// Add order lines
		// driver.findElement(By.xpath("//button[text()=\"Add Order
		// Lines\"]")).click();

		// Save Button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
		System.out.println("Clicked Save");

		
	
		
		Thread.sleep(10000);

		// clicks on SO tab
		driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[6]")).click();
		System.out.println("Stapp Order Tab-2 time clicked");
		Thread.sleep(10000);

		/*
		 * //clicks on recently viewed driver.findElement(By.xpath(
		 * ("//div[@class=\"slds-page-header--object-home slds-page-header_joined slds-page-header_bleed slds-page-header slds-shrink-none test-headerRegion forceListViewManagerHeader\"]/descendant::lightning-icon"
		 * ))).click(); System.out.println("Stapp order Recently Viewed");
		 * Thread.sleep(8000);
		 * 
		 * //clicked on all items driver.findElement(By.
		 * xpath("//div[@class=\"list uiAbstractList forceVirtualAutocompleteMenuList\"]/descendant::span[text()=\"All\"]"
		 * )).click(); System.out.println("Clicked on All Items");
		 */

		Thread.sleep(2000);
		List<WebElement> myElements = driver.findElements(By.xpath(
				"//table[@class=\"slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable\"]/descendant::tbody/tr"));
		System.out.println("Myelements::" + myElements);
		int count = 0;
		// for(WebElement e : myElements) {
		// System.out.print(count);
		// System.out.println(e.getText());
		// count++;
		// }

		Thread.sleep(5000);
		// System.out.println("index 1:"+myElements.get().getText());
		/*
		 * System.out.println("index 1:"+myElements.get(0).getText()); String
		 * tempSO = myElements.get(0).getText();
		 * System.out.println("tempSO::"+tempSO); String lines[] =
		 * tempSO.split("\\r?\\n");
		 */

		System.out.println("index 1:" + myElements.get(1).getText());
		String tempSR = myElements.get(0).getText();
		System.out.println("tempSR::" + tempSR);
		String lines[] = tempSR.split("\\r?\\n");

		// writing SO number to Picking and package sheet in excel
		Thread.sleep(5000);
		excel.setCellData("picking", "StappOrder", 2, lines[1]);
		Thread.sleep(5000);
		excel.setCellData("packages", "SelectSO", 2, lines[1]);

	}

	// ----------------------------------------Create Picking-----------------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 6)
	public void picking(String StappOrder, String BasketPickingNo, String PickedQty) throws InterruptedException {

		Thread.sleep(1000);

		// clicks on Transaction tab
		Click("//button[@class=\"slds-button\"]");
		Thread.sleep(5000);
		driver.findElement(By
				.xpath("//div[@class=\"modal-header slds-modal__header\"]/descendant::div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"]/input"))
				.sendKeys("Picking");
		Thread.sleep(3000);
		Click("(//ul[@class=\"slds-grid slds-grid--pull-padded slds-wrap list\"]/descendant::mark[text()=\"Picking\"])[1]");

		Thread.sleep(10000);
		// Stapp Order
		driver.findElement(By.xpath("(//label[text()=\"Stapp Order\"]/following::input)[1] ")).sendKeys(StappOrder);
		// Lookup Items
		Thread.sleep(5000);
		driver.findElement(By
				.xpath("(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"]/descendant::li)[1]"))
				.click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()=\"Start Picking\"]")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[text()=\"Basket Picking No\"]/following::input)[1]"))
				.sendKeys(BasketPickingNo);

		Thread.sleep(5000);
		driver.findElement(By.xpath("//tr[@id=\"rowData_0\"]/descendant::input")).sendKeys(PickedQty);

		Thread.sleep(5000);
		// driver.findElement(By.xpath("//button[text()=\"Alt
		// Pick\"]")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()=\"Submit\"]")).click();

	}

	// ----------------------------------------Create Package-----------------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 7)
	public void packages(String Customer, String SelectSO) throws InterruptedException {

		Thread.sleep(10000);

		// clicks on App launcher
		/*
		 * Click("//button[@class=\"slds-button\"]"); Thread.sleep(5000);
		 * driver.findElement(By.
		 * xpath("//div[@class=\"modal-header slds-modal__header\"]/descendant::div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"]/input"
		 * )).sendKeys("Package"); Thread.sleep(3000);
		 * Click("//ul[@class=\"slds-grid slds-grid--pull-padded slds-wrap list\"]/descendant::mark[text()=\"Package\"]"
		 * ); Thread.sleep(10000);
		 */

		// clicks on package tab
		driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[7]")).click();
		Thread.sleep(8000);

		// New Button
		driver.findElement(By.xpath("//div[@title=\"New\"]")).click();
		Thread.sleep(8000);

		// Customer Lookup Items and sends customer name
		driver.findElement(By
				.xpath("(//article[@class=\"slds-card stappPackage\"]/descendant::label[text()=\"Customer\"]/following::input)[1]"))
				.sendKeys(Customer);
		Thread.sleep(5000);
		// select's customer from list
		driver.findElement(By
				.xpath("(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"]/descendant::li)[1]"))
				.click();
		Thread.sleep(5000);

		// selects SO
		driver.findElement(By.xpath("//label[text()=\"Select SO\"]/following::input")).sendKeys(SelectSO);
		Thread.sleep(5000);
		// select SO from list
		driver.findElement(By
				.xpath("(//ul[@class=\"slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-lookup__menu slds\"]/descendant::span)[1]"))
				.click();
		Thread.sleep(10000);
		/*
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * driver.findElement(By.xpath("//button[text()='Save']")).click();
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 */

		
		//xpath for save button --- //button[@class=\"slds-button slds-button_brand buttonOverlay\"]
		//xpath for proceed to shipment button --- driver.findElement(By.xpath("//button[text()=\"Proceed To Shipment\"]"));
		
		// clicks on Proceed to Shipment button
		WebElement element = driver
				.findElement(By.xpath("//button[text()=\"Proceed To Shipment\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Thread.sleep(8000);

		// Again clicks on package tab
		// driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[7]")).click();
		// Thread.sleep(5000);

		// to get package number
		/*List<WebElement> myElements = driver.findElements(By.xpath(
				"//table[@class=\"slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable\"]/descendant::tbody/tr"));
		System.out.println("pacakge NO::" + myElements);
		int count = 0;
		// for(WebElement e : myElements) {
		// System.out.print(count);
		// System.out.println(e.getText());
		// count++;
		// }
		System.out.println("index 1:" + myElements.get(1).getText());
		String tempPK = myElements.get(0).getText();
		System.out.println("tempPK::" + tempPK);
		String lines[] = tempPK.split("\\r?\\n");
		System.out.println(lines[1]);

		// writing package number to shipment excel sheet
		excel.setCellData("shipments", "Package", 2, lines[1]);*/

	}

	// ---------------------------------------SHIPMENTS-----------------------------------------------
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 8)

	public static void shipments(String Package, String Carrier, String WayBill, String Status, String Notes)
			throws InterruptedException {

		// Shipment Tab
		Thread.sleep(8000);
		// driver.findElement(By.xpath("//ul[@class=\"slds-grid
		// slds-has-flexi-truncate
		// navUL\"]/descendant::span[text()=\"Shipments\"]")).click();
		//tab
		//driver.findElement(By.xpath("(//one-app-nav-bar/descendant::one-app-nav-bar-item-root)[8]")).click();

		// New Button
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//div[@class=\"slds-grid\"]/descendant::a[@title=\"New\"]")).click();

		// Package
		/*Thread.sleep(5000);
		driver.findElement(By.xpath("//td[@data-label=\"Package\"]/descendant::input")).sendKeys(Package);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[@id=\"listbox-option-unique-id-01\"]/span[2]/span)[1]")).click();*/

		// Carrier
		
		driver.findElement(By.xpath("//label[text()=\"Carrier\"]/following::input[1]")).sendKeys(Carrier);

		// Way Bill
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[text()=\"WayBill\"]/following::input)[1] ")).sendKeys(WayBill);

		// Status PickList
		Thread.sleep(5000);
		driver.findElement(By.xpath("((//label[text()=\"Status\"])[2]/following::div)[3]")).click();
		List<WebElement> status = driver.findElements(By.xpath("//ul[@class=\"scrollable\"]/li"));
		System.out.println("ListofFrames::+" + status);
		for (WebElement we : status) {

			if (we.getText().equalsIgnoreCase(Status)) {

				Thread.sleep(5000l);
				Actions act1 = new Actions(driver);
				act1.click(we);
				act1.build().perform();

			}
		}

		// Notes TextField
		Thread.sleep(5000);
		driver.findElement(By.xpath("//textarea")).sendKeys(Notes);

		// Save Button
		WebElement element = driver.findElement(By.xpath("(//button[text()='Save'])[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Thread.sleep(8000);
		
		System.out.println("Shipment created ");
		
	}

}
