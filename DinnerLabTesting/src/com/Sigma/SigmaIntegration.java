package com.Sigma;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SigmaIntegration extends Testbase {

	// -------------------------------------Account creation--------------------------------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=1)
	public static void account(String AccountType, String AccountName, String Active, String CurrencyCode, String Email,
			String ShippingStreet, String ShippingCity, String ShippingState, String ShippingZip,
			String ShippingCountry, String AccAddress, String AccCity, String AccState, String AccCountry,
			String AccZip, String AccPhone) throws InterruptedException, AWTException {

		Thread.sleep(20000);
		driver.manage().window().maximize();

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		// click on account tab
		driver.findElement(By.xpath(OR.getProperty("Acctab"))).click();
		Thread.sleep(5000);

		// click on new button
		driver.findElement(By.xpath(OR.getProperty("AccNew"))).click();
		Thread.sleep(4000);

		// account type
		if (AccountType.equals("Vendor")) {
			// selects vendor type
			driver.findElement(By.xpath(OR.getProperty("AccVendor"))).click();
			Thread.sleep(4000);

			// next
			driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
			Thread.sleep(4000);

			// account name
			driver.findElement(By.xpath(OR.getProperty("AccName"))).sendKeys(AccountName);
			Thread.sleep(4000);

			// active
			WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccActiveClick")));
			Actions a = new Actions(driver);
			a.moveToElement(we1);
			a.click();

			List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
			for (WebElement we : list) {
				if (we.getText().contains(Active)) {
					Thread.sleep(5000);
					we.click();
				}
			}
			Thread.sleep(5000);

			// save
			driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
			Thread.sleep(4000);

		} else if (AccountType.equals("Agency")) {
			// selects agency type
			driver.findElement(By.xpath(OR.getProperty("AccAgency"))).click();
			Thread.sleep(4000);

			// next
			driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
			Thread.sleep(4000);

			// account name
			driver.findElement(By.xpath(OR.getProperty("AccName"))).sendKeys(AccountName);
			Thread.sleep(4000);

			// active
			WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccActiveClick")));
			Actions a = new Actions(driver);
			a.moveToElement(we1);
			a.click();

			List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
			for (WebElement we : list) {
				if (we.getText().contains(Active)) {
					Thread.sleep(5000);
					we.click();
				}
			}
			Thread.sleep(5000);

			// save
			driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
			Thread.sleep(4000);

		} else if (AccountType.equals("Customer")) {
			// selects agency type
			driver.findElement(By.xpath(OR.getProperty("AccCustomer"))).click();
			Thread.sleep(4000);

			// next
			driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
			Thread.sleep(4000);

			// account name
			driver.findElement(By.xpath(OR.getProperty("AccName"))).sendKeys(AccountName);
			Thread.sleep(4000);

			// active
			WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccActiveClick")));
			Actions a = new Actions(driver);
			a.moveToElement(we1);
			a.click();

			List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
			for (WebElement we : list) {
				if (we.getText().contains(Active)) {
					Thread.sleep(5000);
					we.click();
				}
			}
			Thread.sleep(5000);

			// currency code
			WebElement cc = driver.findElement(By.xpath(OR.getProperty("CurrencyCode")));
			driver.findElement(By.xpath(OR.getProperty("CurrencyCode"))).sendKeys(CurrencyCode);
			Thread.sleep(4000);

			// email
			driver.findElement(By.xpath(OR.getProperty("Email"))).sendKeys(Email);
			Thread.sleep(4000);

			// shipping street
			driver.findElement(By.xpath(OR.getProperty("ShippingStreet"))).sendKeys(ShippingStreet);
			Thread.sleep(4000);

			// shipping city
			driver.findElement(By.xpath(OR.getProperty("ShippingCity"))).sendKeys(ShippingCity);
			Thread.sleep(4000);

			// shipping state
			driver.findElement(By.xpath(OR.getProperty("ShippingState"))).sendKeys(ShippingState);
			Thread.sleep(4000);

			// scroll down
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(8000);

			// Postal code
			driver.findElement(By.xpath(OR.getProperty("ShippingZip"))).sendKeys(ShippingZip);
			Thread.sleep(4000);

			// shipping country
			driver.findElement(By.xpath(OR.getProperty("ShippingCountry"))).sendKeys(ShippingCountry);
			Thread.sleep(4000);

			// autopay
			driver.findElement(By.xpath(OR.getProperty("AutoPay"))).click();
			Thread.sleep(4000);

			// address
			driver.findElement(By.xpath(OR.getProperty("AccAddress"))).sendKeys(AccAddress);
			Thread.sleep(4000);

			// city
			driver.findElement(By.xpath(OR.getProperty("AccCity"))).sendKeys(AccCity);
			Thread.sleep(4000);

			// state
			driver.findElement(By.xpath(OR.getProperty("AccState"))).sendKeys(AccState);
			Thread.sleep(4000);

			// country
			driver.findElement(By.xpath(OR.getProperty("AccCountry"))).sendKeys(AccCountry);
			Thread.sleep(4000);

			// zip code
			driver.findElement(By.xpath(OR.getProperty("AccZip"))).sendKeys(AccZip);
			Thread.sleep(4000);

			// phone
			driver.findElement(By.xpath(OR.getProperty("AccPhone"))).sendKeys(AccPhone);
			Thread.sleep(4000);

			// save
			driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
			Thread.sleep(4000);

		}

	}

	// ---------------------------------------Create Product---------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
	public static void product(String PRODUCTTYPE, String ProductName, String AttributeType, String ReorderQuantity,
			String ThresholdQuantity, String MinimumQuantity, String SellingPrice, String ListPrice, String BuyingPrice,
			String Discount, String VENDOR) throws InterruptedException {
		Thread.sleep(20000);
		driver.manage().window().maximize();

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		// product tab
		driver.findElement(By.xpath(OR.getProperty("Prodtab"))).click();
		Thread.sleep(5000);

		// new
		driver.findElement(By.xpath(OR.getProperty("Newbutton"))).click();
		Thread.sleep(4000);
		System.out.println("printing" + PRODUCTTYPE);

		// product type
		if (PRODUCTTYPE.equals("BOM")) {
			System.out.println("printing" + PRODUCTTYPE);
			driver.findElement(By.xpath(OR.getProperty("Bom"))).click();

		} else {

			driver.findElement(By.xpath(OR.getProperty("Individual"))).click();
		}
		Thread.sleep(5000);

		// next button
		driver.findElement(By.xpath(OR.getProperty("Next"))).click();
		Thread.sleep(5000);

		// product name
		driver.findElement(By.xpath(OR.getProperty("Prodname"))).sendKeys(ProductName);
		Thread.sleep(4000);

		// attribute type
		WebElement we1 = driver.findElement(By.xpath(OR.getProperty("Attributetype")));
		Actions a = new Actions(driver);
		a.moveToElement(we1);
		a.click();

		List<WebElement> lst = driver.findElements(By.xpath(OR.getProperty("AttributetypeList")));
		for (WebElement we : lst) {
			if (we.getText().contains(AttributeType)) {
				Thread.sleep(5000);
				we.click();
			}
		}
		Thread.sleep(8000);

		// reorder qty
		driver.findElement(By.xpath(OR.getProperty("ReorderQty"))).sendKeys(ReorderQuantity);
		Thread.sleep(4000);

		// threshold qty
		driver.findElement(By.xpath(OR.getProperty("ThresholdQty"))).sendKeys(ThresholdQuantity);
		Thread.sleep(4000);

		// minimum qty
		driver.findElement(By.xpath(OR.getProperty("MinQty"))).sendKeys(MinimumQuantity);
		Thread.sleep(4000);

		// selling price
		driver.findElement(By.xpath(OR.getProperty("SellingPrice"))).sendKeys(SellingPrice);
		Thread.sleep(4000);

		// average buying price
		driver.findElement(By.xpath(OR.getProperty("AvgBuyingPrice"))).sendKeys(BuyingPrice);
		Thread.sleep(4000);

		// list price
		driver.findElement(By.xpath(OR.getProperty("ListPrice"))).sendKeys(ListPrice);
		Thread.sleep(4000);

		// discount
		driver.findElement(By.xpath(OR.getProperty("Discount"))).sendKeys(Discount);
		Thread.sleep(4000);

		// save
		driver.findElement(By.xpath(OR.getProperty("Save"))).click();
		Thread.sleep(4000);

		// related link
		List<WebElement> multi = driver.findElements(By.xpath(OR.getProperty("RelatedLink")));
		int size = multi.size();
		for (int x = 1; x <= size; x++) {

			if (driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]"))
					.isDisplayed()) {
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]")).click();

			}
		}

		// scroll down
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(8000);

		// vendor product
		driver.findElement(By.xpath(OR.getProperty("VendorProd"))).click();
		Thread.sleep(4000);

		// vendor name
		WebElement vn = driver.findElement(By.xpath(OR.getProperty("VendorName")));
		Thread.sleep(5000);

		Actions act = new Actions(driver);
		act.click(vn);
		act.sendKeys(VENDOR);
		Thread.sleep(2000);
		act.build().perform();
		Thread.sleep(8000l);
		driver.findElement(By.xpath("//div[@class=\"listContent\"]")).click();

		// buying price
		driver.findElement(By.xpath(OR.getProperty("BuyingPrice"))).sendKeys(BuyingPrice);
		Thread.sleep(4000);

		// save
		driver.findElement(By.xpath(OR.getProperty("SaveVendor"))).click();
		Thread.sleep(8000l);

		System.out.println("Product Created");

	}

	// -----------------------------Purchase Order----------------------------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 3)

	public static void purchaseorder(String vendor, String OrderDate, String ExpectedDate, String product,
			String Quantity, String ExpectedDeliveryDate) throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().window().maximize();

		Thread.sleep(8000);

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		// purchase order tab
		driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab"))).click();
		Thread.sleep(5000);

		// new purchase order
		driver.findElement(By.xpath(OR.getProperty("PONewButton"))).click();
		Thread.sleep(5000);

		// frame
		WebElement frame = driver.findElement(By.xpath(OR.getProperty("iframe")));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		Thread.sleep(6000);

		// vendor
		WebElement v = driver.findElement(By.xpath(OR.getProperty("POVendor")));
		Thread.sleep(5000);

		Actions act = new Actions(driver);
		act.click(v);
		act.sendKeys(vendor);
		Thread.sleep(2000);
		act.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-media__body']")).click();

		// order date
		driver.findElement(By.xpath(OR.getProperty("OrderDate"))).sendKeys(OrderDate);
		Thread.sleep(5000);

		// Expected date
		driver.findElement(By.xpath(OR.getProperty("ExpectedDate"))).sendKeys(ExpectedDate);
		Thread.sleep(5000);

		// add po
		driver.findElement(By.xpath(OR.getProperty("AddPO"))).click();
		Thread.sleep(5000);

		// po product
		WebElement p = driver.findElement(By.xpath(OR.getProperty("POProduct")));
		Thread.sleep(5000);

		Actions act1 = new Actions(driver);
		act1.click(p);
		act1.sendKeys(product);
		Thread.sleep(2000);
		act1.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[2]")).click();

		// quantity
		driver.findElement(By.xpath(OR.getProperty("POQuantity"))).sendKeys(Quantity);
		Thread.sleep(5000);

		// expected delivery date
		driver.findElement(By.xpath(OR.getProperty("ExpectedDelivery"))).sendKeys(ExpectedDeliveryDate);
		Thread.sleep(5000);

		// submit
		driver.findElement(By.xpath(OR.getProperty("Submit"))).click();
		Thread.sleep(5000);

		// save
		driver.findElement(By.xpath(OR.getProperty("FinalSave"))).click();
		Thread.sleep(5000);

		// close alert
		driver.findElement(By.xpath("(//button[@class='slds-button'])[1]")).click();
		Thread.sleep(5000);

		driver.switchTo().defaultContent();
		Thread.sleep(5000);

		// clicks on submit for approval
		driver.findElement(By.xpath(OR.getProperty("Approval"))).click();
		Thread.sleep(5000);

		// comments 0
		driver.findElement(By.xpath(OR.getProperty("Comments"))).sendKeys("Approve the Orders");
		Thread.sleep(5000);

		// ApproveSubmit 0
		driver.findElement(By.xpath(OR.getProperty("ApproveSubmit"))).click();
		Thread.sleep(5000);

		// related link
		List<WebElement> multi = driver.findElements(By.xpath(OR.getProperty("RelatedLink")));
		int size = multi.size();
		for (int x = 1; x <= size; x++) {

			if (driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]"))
					.isDisplayed()) {
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]")).click();

			}
		}

		// Click on Approvebutton

		driver.findElement(By.xpath(OR.getProperty("ApproveButton"))).click();

		

		// comments 1
		driver.findElement(By.xpath(OR.getProperty("Comments"))).sendKeys("Approve the Orders");
		Thread.sleep(5000);

		// ApproveSubmit 1
		driver.findElement(By.xpath(OR.getProperty("Approve2"))).click();
		Thread.sleep(5000);

		// edit
		driver.findElement(By.xpath(OR.getProperty("Edit"))).click();

		
		Thread.sleep(5000);
		// Switching to frame1
		WebElement frame1 = driver.findElement(By.xpath(OR.getProperty("Editframe")));
		driver.switchTo().frame(frame1);
		System.out.println(" switching  to frame1");
		Thread.sleep(6000);
		System.out.println("Meghanaa");
		
		
		/*WebElement status = driver.findElement(By.xpath(OR.getProperty("Status")));
		status.click();
		
		Select options = new Select(status);
		options.selectByVisibleText("Submitted");*/
		Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty("POStatus"))));
		dropdown.selectByVisibleText("Submitted");
		Thread.sleep(8000);

		
		driver.switchTo().defaultContent();
		
		
		
		
		driver.switchTo().frame(frame);
		
		
		System.out.println("Soundarya frame ");
		
		driver.findElement(By.xpath(OR.getProperty("UpdateButton")));
		
		
		Thread.sleep(6000);
		driver.switchTo().defaultContent();
		
		System.out.println("Purchase order successful");

		// again clicks on PO tab

		// purchase order tab
		driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab"))).click();
		Thread.sleep(5000);

		List<WebElement> myElements = driver.findElements(By.xpath(
				"//table[@class=\"slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable\"]/descendant::tbody/tr"));
		int count = 0;

		System.out.println("index 1:" + myElements.get(1).getText());
		String tempTD = myElements.get(0).getText();
		System.out.println("tempTD::" + tempTD);
		String lines[] = tempTD.split("\\r?\\n");

		excel.setCellData("stockreceive", "PurchaseOrder", 2, lines[1]);

	}

	// --------------------------------------Stock
	// Receiving-----------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)

	public static void stockreceive(String Vendor, String DeliveryPerson, String ReceivingLocation, String ReceivedDate,
			String PurchaseOrder, String POProduct, String ReceivedQuantity, String ActualLocation, String Status)
			throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().window().maximize();

		Thread.sleep(8000);

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		// stock receive tab
		driver.findElement(By.xpath(OR.getProperty("StockReceivetab"))).click();
		Thread.sleep(5000);

		// New stock receive
		driver.findElement(By.xpath(OR.getProperty("SRNewButton"))).click();
		Thread.sleep(5000);

		// switching to frame
		WebElement f = driver.findElement(By.xpath(OR.getProperty("SRFrame")));
		driver.switchTo().frame(f);
		System.out.println(" switching  to frame");
		Thread.sleep(6000);

		// vendor
		WebElement v = driver.findElement(By.xpath(OR.getProperty("SRVendor")));
		Thread.sleep(5000);

		Actions act1 = new Actions(driver);
		act1.click(v);
		act1.sendKeys(Vendor);
		Thread.sleep(2000);
		act1.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-media__body']")).click();

		// delivery person
		WebElement d = driver.findElement(By.xpath(OR.getProperty("SRDeliveryPerson")));
		Thread.sleep(5000);

		Actions act2 = new Actions(driver);
		act2.click(d);
		act2.sendKeys(DeliveryPerson);
		Thread.sleep(2000);
		act2.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[2]"))
				.click();

		// receiving location
		WebElement r = driver.findElement(By.xpath(OR.getProperty("SRReceivingLocation")));
		Thread.sleep(5000);

		Actions act3 = new Actions(driver);
		act3.click(r);
		act3.sendKeys(ReceivingLocation);
		Thread.sleep(2000);
		act3.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[3]"))
				.click();
		Thread.sleep(6000);

		// Receive date
		driver.findElement(By.xpath(OR.getProperty("SRReceivedDate"))).sendKeys(ReceivedDate);
		Thread.sleep(5000);

		// add products
		driver.findElement(By.xpath(OR.getProperty("SRAddProd"))).click();
		Thread.sleep(5000);

		// purchase order
		WebElement po = driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder")));
		Thread.sleep(5000);

		Actions actions = new Actions(driver);
		actions.moveToElement(po);
		actions.click();
		actions.sendKeys(PurchaseOrder);
		actions.build().perform();

		Thread.sleep(9000);
		List<WebElement> list = driver.findElements(By.xpath("(//ul[@class='slds-lookup__list'])[4]/li"));
		// System.out.println("list :: "+list);
		for (WebElement w : list) {
			if (w.getText().contains(PurchaseOrder)) {
				Thread.sleep(5000l);
				w.click();
			}
		}

		/*
		 * driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).click
		 * (); Thread.sleep(5000);
		 * driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).
		 * sendKeys(PurchaseOrder); Thread.sleep(5000);
		 * driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).
		 * sendKeys(Keys.TAB); Thread.sleep(2000);
		 * driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).
		 * sendKeys(Keys.TAB); Thread.sleep(2000);
		 * driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).
		 * sendKeys(Keys.TAB).click();
		 */

		// PO product
		WebElement w1 = driver.findElement(By.xpath(OR.getProperty("SRPOProduct")));
		Thread.sleep(5000);

		Actions act4 = new Actions(driver);
		act4.click(w1);
		act4.sendKeys(POProduct);
		Thread.sleep(2000);
		act4.build().perform();
		Thread.sleep(8000);
		driver.findElement(By
				.xpath("//div[@class='slds-lookup__menu slds-p-around_x-small slds-show']//ul[@class='slds-lookup__list']/li[1]"))
				.click();
		Thread.sleep(6000);

		// receive qty
		driver.findElement(By.xpath(OR.getProperty("SRReceivedQty"))).sendKeys(ReceivedQuantity);
		Thread.sleep(5000);

		// actual location
		WebElement w2 = driver.findElement(By.xpath(OR.getProperty("SRActualLocation")));
		Thread.sleep(5000);

		Actions act5 = new Actions(driver);
		act5.click(w2);
		act5.sendKeys(ActualLocation);
		Thread.sleep(2000);
		act5.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[8]"))
				.click();
		Thread.sleep(6000);

		// status
		Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty("SRSelect"))));
		dropdown.selectByVisibleText("Verified");
		Thread.sleep(8000);

		// submit
		driver.findElement(By.xpath(OR.getProperty("SRSubmit"))).click();
		Thread.sleep(8000);

		// save
		driver.findElement(By.xpath(OR.getProperty("SRSave"))).click();
		Thread.sleep(5000);

		driver.switchTo().defaultContent();
		Thread.sleep(5000);

		System.out.println("Stock Receiving successful");

	}

	// ----------------------------------------------Rental
	// Order---------------------------------------------------------------
	// @Test(dataProviderClass = Testbase.class, dataProvider = "dp",
	// priority=5)
	public static void rentalorder(String RentalCustomer, String RentalBillingPerson, String RentalProduct,
			String RentalQty, String RentalSD, String RentalDD, String RentalRD) throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().window().maximize();

		Thread.sleep(8000);

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		// sales order
		driver.findElement(By
				.xpath("(//one-app-nav-bar[@class='slds-grid slds-has-flexi-truncate'])//one-app-nav-bar-item-root[8]"))
				.click();
		Thread.sleep(5000);

		// switch to frame
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@scrolling=\"yes\"]"));
		driver.switchTo().frame(frame1);

		// rental order
		driver.findElement(By.xpath("//div[@class='slds-button-group']/button[2]")).click();
		Thread.sleep(5000);

		// clicks on customer
		driver.findElement(
				By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[1]"))
				.sendKeys(RentalCustomer);
		WebElement cust = driver.findElement(By.xpath("(//ul[@class='slds-lookup__list'])[1]/li[1]"));

		if (cust.isDisplayed()) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//ul[@class='slds-lookup__list'])[1]/li[1]")).click();
		}
		Thread.sleep(4000);

		// clicks on add product
		driver.findElement(By.xpath("//button[text()='Add Products']")).click();
		Thread.sleep(4000);

		// selecting product
		driver.findElement(By.xpath("//label/span[text()='Product:']//following::input[1]")).click();
		driver.findElement(By.xpath("//label/span[text()='Product:']//following::input[1]")).sendKeys(RentalProduct);
		WebElement prod = driver.findElement(By.xpath("(//ul[@class='slds-lookup__list']/li[1])[2]"));

		if (prod.isDisplayed()) {
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//ul[@class='slds-lookup__list']/li[1])[2]")).click();
		}
		Thread.sleep(4000);

		// enters quantity
		driver.findElement(By.xpath("//label[text()='Quantity']//following::input[1]")).sendKeys(RentalQty);
		Thread.sleep(4000);

		// selects start date
		driver.findElement(By.xpath("//label[text()='Start Date']//following::input[1]")).sendKeys(RentalSD);
		Thread.sleep(4000);

		// selects due date
		driver.findElement(By.xpath("//label[text()='Due Date']//following::input[1]")).sendKeys(RentalDD);
		Thread.sleep(4000);

		// selects return date
		driver.findElement(By.xpath("//label[text()='Return Date']//following::input[1]")).sendKeys(RentalRD);
		Thread.sleep(4000);

		// clicks on add to order
		driver.findElement(By.xpath("//button[text()='Add to Orders']")).click();
		Thread.sleep(4000);

		// clicks on save order
		driver.findElement(By.xpath("//button[text()='Save Order']")).click();
		Thread.sleep(4000);

		// switching from frame
		driver.switchTo().defaultContent();

	}

	// -----------------------------------Sigma
	// Order--------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 6)
	public static void sigmaorder(String CustomerName, String Billingperson, String Product, String Quantity)
			throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().window().maximize();

		Thread.sleep(8000);

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		// sigma order
		driver.findElement(By.xpath(OR.getProperty("SigmaOrdertab"))).click();
		Thread.sleep(5000);

		// switching to frame
		WebElement fr = driver.findElement(By.xpath(OR.getProperty("SOframe")));
		driver.switchTo().frame(fr);
		System.out.println(" switching  to frame");
		Thread.sleep(6000);

		// sales order
		driver.findElement(By.xpath(OR.getProperty("SalesOrder"))).click();
		Thread.sleep(5000);

		// customer name
		WebElement c = driver.findElement(By.xpath(OR.getProperty("Customername")));
		Thread.sleep(5000);

		Actions a1 = new Actions(driver);
		a1.click(c);
		a1.sendKeys(CustomerName);
		Thread.sleep(2000);
		a1.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-media__body']")).click();

		// billing person
		WebElement b = driver.findElement(By.xpath(OR.getProperty("BillingPerson")));
		Thread.sleep(10000);

		Actions a2 = new Actions(driver);
		a2.click(b);
		Thread.sleep(6000);
		a2.sendKeys(Billingperson);
		Thread.sleep(8000);
		a2.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[2]")).click();

		// add product
		driver.findElement(By.xpath(OR.getProperty("SOAddproduct"))).click();
		Thread.sleep(5000);

		// product
		WebElement pr = driver.findElement(By.xpath(OR.getProperty("SOProduct")));
		Thread.sleep(5000);

		Actions a3 = new Actions(driver);
		a3.click(pr);
		a3.sendKeys(Product);
		Thread.sleep(2000);
		a3.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[3]")).click();

		// quantity
		driver.findElement(By.xpath(OR.getProperty("SOQty"))).sendKeys(Quantity);
		Thread.sleep(5000);

		// add to orders
		driver.findElement(By.xpath(OR.getProperty("SOAddtoorders"))).click();
		Thread.sleep(5000);

		// next
		driver.findElement(By.xpath(OR.getProperty("SONext"))).click();
		Thread.sleep(10000);

		// close popup
		Alert aa = driver.switchTo().alert();
		aa.accept();

		Alert aa1 = driver.switchTo().alert();
		aa1.accept();

		driver.findElement(By.xpath("//button[@class='slds-button']"));
		Thread.sleep(8000);
		/*
		 * //proceed payment
		 * driver.findElement(By.xpath(OR.getProperty("ProccedPayment"))).click(
		 * ); Thread.sleep(8000);
		 * 
		 * //cash driver.findElement(By.xpath(OR.getProperty("Cash"))).click();
		 * Thread.sleep(8000);
		 */

		// skip payment
		driver.findElement(By.xpath(OR.getProperty("SkipPayment"))).click();
		Thread.sleep(5000);

		driver.switchTo().defaultContent();
		Thread.sleep(5000);

		System.out.println("sales order successful");

	}

	// -----------------------------------------Sigma
	// Package-----------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 7)
	public static void packages(String PCustomer, String PLocation, String PackagedBy, String STATUS, String SONO,
			String PackegedQty, String EnterQuantity) throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().window().maximize();

		Thread.sleep(8000);

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		/*
		 * //package tab
		 * driver.findElement(By.xpath(OR.getProperty("Packagetab"))).click();
		 * Thread.sleep(8000);
		 */

		// clicks on App
		Click("//button[@class=\"slds-button\"]");
		Thread.sleep(5000);
		driver.findElement(By
				.xpath("//div[@class=\"modal-header slds-modal__header\"]/descendant::div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"]/input"))
				.sendKeys("Packages");
		Thread.sleep(3000);
		Click("(//ul[@class='slds-grid slds-grid--pull-padded slds-wrap list']/descendant::mark[text()='Packages'])[1]");
		Thread.sleep(4000);

		// new package
		driver.findElement(By.xpath(OR.getProperty("PackageNew"))).click();
		Thread.sleep(8000);

		// package frame
		WebElement pf = driver.findElement(By.xpath(OR.getProperty("Packageframe")));
		driver.switchTo().frame(pf);
		System.out.println(" switching  to frame");
		Thread.sleep(6000);

		// customer
		WebElement c = driver.findElement(By.xpath(OR.getProperty("PackageCustomer")));
		Thread.sleep(5000);

		Actions act = new Actions(driver);
		act.click(c);
		act.sendKeys(PCustomer);
		Thread.sleep(2000);
		act.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[1]")).click();

		// location
		WebElement pl = driver.findElement(By.xpath(OR.getProperty("PackageLocation")));
		Thread.sleep(5000);

		Actions act1 = new Actions(driver);
		act1.click(pl);
		act1.sendKeys(PLocation);
		Thread.sleep(2000);
		act1.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[2]"))
				.click();

		// packaged by
		WebElement pp = driver.findElement(By.xpath(OR.getProperty("PackagedBy")));
		Thread.sleep(5000);

		Actions act2 = new Actions(driver);
		act2.click(pp);
		act2.sendKeys(PackagedBy);
		Thread.sleep(2000);
		act2.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[3]"))
				.click();

		// status
		Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty("PackageStatus"))));
		dropdown.selectByVisibleText(STATUS);
		Thread.sleep(8000);

		// SONO
		WebElement so = driver.findElement(By.xpath(OR.getProperty("PackageSONO")));
		Thread.sleep(5000);

		Actions act3 = new Actions(driver);
		act3.moveToElement(so);
		act3.click();
		act3.sendKeys(SONO);
		act3.build().perform();

		Thread.sleep(9000);
		List<WebElement> list = driver.findElements(By.xpath("(//ul[@class='slds-lookup__list'])[4]/li"));
		// System.out.println("list :: "+list);
		for (WebElement w : list) {
			if (w.getText().contains(SONO)) {
				Thread.sleep(5000l);
				w.click();
			}
		}

		// product checkbox
		driver.findElement(By.xpath(OR.getProperty("ProdCheckbox"))).click();
		Thread.sleep(5000);

		// package qty
		driver.findElement(By.xpath(OR.getProperty("PackageQty"))).clear();
		driver.findElement(By.xpath(OR.getProperty("PackageQty"))).sendKeys(PackegedQty);
		Thread.sleep(5000);

		// autopick
		driver.findElement(By.xpath(OR.getProperty("Autopick"))).click();
		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);
		alert.accept();
		Thread.sleep(6000);

		/*
		 * //accepting alert Alert alert = driver.switchTo().alert();
		 * alert.accept();
		 */

		// autopick confirm
		/*
		 * Alert a = driver.switchTo().alert(); a.accept(); Thread.sleep(4000);
		 */
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.alertIsPresent());
		Alert alert1 = driver.switchTo().alert();
		Thread.sleep(3000);
		alert1.accept();
		Thread.sleep(6000);

		// submit
		driver.findElement(By.xpath(OR.getProperty("PackageSubmit"))).click();
		Thread.sleep(5000);

		// submit alert
		Alert a1 = driver.switchTo().alert();
		a1.accept();

		// switching from frame
		driver.switchTo().defaultContent();
		Thread.sleep(5000);

		System.out.println("Package successful");

	}

	// -----------------------------------------Shipment-------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 8)
	public static void shipment(String LogisticsName, String TruckNumber, String DeliveryPerson, String ShipmentDate,
			String Status1, String PackageNumber, String ShipmentDeliveryDate, String Status2)
			throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().window().maximize();

		Thread.sleep(8000);

		// app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		Thread.sleep(8000);

		// click on SigmaERP
		driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		Thread.sleep(8000);

		/*
		 * //shipment tab
		 * driver.findElement(By.xpath(OR.getProperty("Shipmenttab"))).click();
		 */

		// clicks on App
		Click("//button[@class=\"slds-button\"]");
		Thread.sleep(5000);
		driver.findElement(By
				.xpath("//div[@class=\"modal-header slds-modal__header\"]/descendant::div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"]/input"))
				.sendKeys("Shipments");
		Thread.sleep(3000);
		Click("(//ul[@class='slds-grid slds-grid--pull-padded slds-wrap list']/descendant::mark[text()='Shipments'])[1]");

		Thread.sleep(5000);

		// new shipment
		driver.findElement(By.xpath(OR.getProperty("ShipmentNew"))).click();
		Thread.sleep(8000);

		// switching to frame
		WebElement sf = driver.findElement(By.xpath(OR.getProperty("ShipmentFrame")));
		driver.switchTo().frame(sf);
		System.out.println(" switching  to frame");
		Thread.sleep(6000);

		// logistics name
		WebElement sl = driver.findElement(By.xpath(OR.getProperty("Logisticsname")));
		Thread.sleep(5000);

		Actions action = new Actions(driver);
		action.click(sl);
		action.sendKeys(LogisticsName);
		Thread.sleep(2000);
		action.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[1]")).click();

		// truck number
		driver.findElement(By.xpath(OR.getProperty("Truck"))).sendKeys(TruckNumber);
		Thread.sleep(5000);

		// delivery person
		WebElement sd = driver.findElement(By.xpath(OR.getProperty("DeliveryPerson")));
		Thread.sleep(5000);

		Actions action1 = new Actions(driver);
		action1.click(sd);
		action1.sendKeys(DeliveryPerson);
		Thread.sleep(2000);
		action1.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[2]")).click();

		// shipment date
		driver.findElement(By.xpath(OR.getProperty("ShipmentDate"))).sendKeys(ShipmentDate);
		Thread.sleep(5000);

		// select status
		Select drpdown1 = new Select(driver.findElement(By.xpath(OR.getProperty("Status1"))));
		drpdown1.selectByVisibleText(Status1);
		Thread.sleep(8000);

		// add shipment products
		driver.findElement(By.xpath(OR.getProperty("AddShipmentProd"))).click();
		Thread.sleep(8000);

		// package number
		WebElement pn = driver.findElement(By.xpath(OR.getProperty("PackageNumber")));
		Thread.sleep(5000);

		Actions action2 = new Actions(driver);
		action2.moveToElement(pn);
		action2.click();
		action2.sendKeys(PackageNumber);
		action2.build().perform();

		Thread.sleep(9000);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='slds-lookup__list']/li"));
		// System.out.println("list :: "+list);
		for (WebElement w : list) {
			if (w.getText().contains(PackageNumber)) {
				Thread.sleep(5000l);
				w.click();
			}
		}

		// shipment delivery date
		driver.findElement(By.xpath(OR.getProperty("ShipmentDeliveryDate"))).sendKeys(ShipmentDeliveryDate);
		Thread.sleep(5000);

		// shipment status
		Select drpdown2 = new Select(driver.findElement(By.xpath(OR.getProperty("Status2"))));
		drpdown2.selectByVisibleText(Status2);
		Thread.sleep(8000);

		// click on submit
		driver.findElement(By.xpath(OR.getProperty("ShipmentSubmit"))).click();
		Thread.sleep(8000);

		// final submit
		driver.findElement(By.xpath(OR.getProperty("LastSubmit"))).click();
		Thread.sleep(8000);

		// switch back
		driver.switchTo().defaultContent();
		Thread.sleep(8000);

		System.out.println("Shipment succesful");

	}

}