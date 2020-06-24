package com.stapp;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class outbound extends Testbase
{


//----------------------------------STAPP ORDER--------------------------------------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)
	public void stappOrder(String CustomerOrderNumber, String Customer, String OrderSource, String Status,
			String Currency, String Contact) throws InterruptedException {

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
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 5)

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

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 6)
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

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 7)
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
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 8)

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
