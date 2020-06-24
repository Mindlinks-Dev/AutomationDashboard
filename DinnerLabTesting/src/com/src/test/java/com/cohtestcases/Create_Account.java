package com.src.test.java.com.cohtestcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.src.test.java.com.Base.NewTestBase;
import com.src.test.java.com.Base.Testbase;

public class Create_Account extends NewTestBase {
	
	// Call Create_product.java class

   @Test(dataProviderClass = NewTestBase.class, dataProvider = "dp", priority = 1)
	public static void product(String PRODUCTNAME, String TYPE, String ATTRIBUTYPE, String PRODUCTWEIGHT, String PUOM,
			String PMT, String SOURCE,String PRODUCTCODE,String PRODUCTDESCRIPTION) throws MalformedURLException, InterruptedException {
		
		Thread.sleep(15000);
		//driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Products']")).click();

		Thread.sleep(5000l);
		// new button
		driver.findElement(By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li")).click();

		//CLICK ON NEXT
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton']")).click();
		
		
		// Product text field
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='slds-form slds-is-editing']/div[1]/descendant::input)[1]"))
				.sendKeys(PRODUCTNAME);

		Thread.sleep(8000l);
		
		
	/*	// clicks on dimension text field and selects yes
		//driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
		driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();//---coh
		driver.findElement(By.xpath("//a[@title=\"Yes\"]")).click();
		Thread.sleep(5000l);*/
		
/*		
		// enters product weight
		WebElement we = driver.findElement(By.xpath(
				"(//div[@class=\"uiInput uiInputNumber uiInput--default uiInput--input\"]/descendant::input)[1]"));
		Actions act1 = new Actions(driver);
		act1.click(we);
		act1.sendKeys(PRODUCTWEIGHT);
		act1.build().perform();
		Thread.sleep(5000l);

						
		//(//a[@class=\"select\"])[1]---coh
		//(//a[@class=\"select\"])[2]---coh
		// selects value from product UOM
		driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
		List<WebElement> list = driver.findElements(By.xpath("//ul[@data-aura-rendered-by=\"1790:0\"]/descendant::a"));
		for (WebElement we2 : list) {
			if (we.getAttribute("title").equals(PUOM)) {
				Thread.sleep(4000l);

				we2.click();

			}
		}

		*/
		
		driver.findElement(By.xpath("(//label/descendant::span[text()='Product Code']/following::input)[1]")).sendKeys(PRODUCTCODE);
		
		
		driver.findElement(By.xpath("(//textarea)[1]")).sendKeys(PRODUCTDESCRIPTION);
		
		
		
		
		
		Thread.sleep(5000l);
		// Sendkeys("//input[@id=\"1862:0\"]","spn-001");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(5000l);
		
		
		// clicks on stock item
		driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[1]")).click();
		
		//clicks on ACTIVE
		driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[2]")).click();

		// clicks on save
		Click("(//span[text()=\"Save\"])[2]");

		Thread.sleep(8000l);
		
		
		
		
		
/*		//ranjitha code
		
		
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
			driver.findElement(By.xpath(("(//input[@step='1'])[1]"))).sendKeys("10");
			
			Thread.sleep(5000);

			//Q1ENDRANGE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[2]"))).sendKeys("15");
			
			
			Thread.sleep(5000);
			//Q1PRICE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[3]"))).sendKeys("100");
			
			
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			 js1.executeScript("window.scrollBy(0,1000)");
			
			 
			//PriceBook for textfield
			Thread.sleep(5000);
			driver.findElement(By.xpath(("(//div[@class=\"autocompleteWrapper slds-grow\"])[3]/input"))).sendKeys("Standard Price Book");
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
			
			
			*/
 	
		
		
		
/*		List<WebElement> multi = driver.findElements(By.xpath("//a[@class=\"tabHeader\"][@tabindex=\"-1\"]"));
		int size = multi.size();
		for (int x = 1; x <= size; x++) {

			if (driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]"))
					.isDisplayed()) {
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]")).click();
				// driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])[x]")).click();
			}
		}

		// goes to related

		Thread.sleep(7000);
		JavascriptExecutor jsw = (JavascriptExecutor) driver;
		jsw.executeScript("window.scrollBy(0,450)", "");
		Thread.sleep(2000);
		// clicks on add standard price book button
		driver.findElement(By.xpath("//div[text()=\"Add Standard Price\"]")).click();
		Thread.sleep(5000);
		// enters amount by calling amount method
		Amount("1.5");

		// clicks on add to price book
		Thread.sleep(7000);
*/
		/*ADDtoPriceBook();

		
		
		Thread.sleep(5000);
		// enters pricebook and clicks save button
		pricebook("Purchase Order Price Book");
		Amount("2.5");

		Thread.sleep(7000);
		//click on add to price book button  calls ADDto pricebook method
		ADDtoPriceBook();
		
		Thread.sleep(5000);

		// enters pricebook and clicks save button
		pricebook("Sales Order Price Book");
		Amount("3.5");
		
		
		Thread.sleep(5000l);

		*/

		
		log.debug("product creation sucessfull");

	}

/*	public static void pricebook(String pricebook) throws InterruptedException {

		select("(//select)[1]", pricebook);

		Thread.sleep(5000);
		select("(//select)[2]", "GBP");
		Click("//span[text()=\"Next\"]");
		Thread.sleep(5000);

	}
*/
/*	public static void Amount(String price) throws InterruptedException {

		Click("(//input[@aria-required=\"true\"])[3]");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@aria-required=\"true\"])[3]")).clear();
		Thread.sleep(2000);
		sendkeys("(//input[@aria-required=\"true\"])[3]", price);

		Click("(//span[text()=\"Save\"])[2]");
	}
*/
	
	
/*	public static void ADDtoPriceBook() throws InterruptedException {

		List<WebElement> multi1 = driver.findElements(By.xpath("//div[text()=\"Add to Price Book\"]"));
		int size2 = multi1.size();
		for (int x = 1; x <= size2; x++) {

			if (driver.findElement(By.xpath("(//div[text()=\"Add to Price Book\"])[" + x + "]")).isDisplayed()) {
				Thread.sleep(6000);
				driver.findElement(By.xpath("(//div[text()=\"Add to Price Book\"])" + "[" + x + "]")).click();
				// driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])[x]")).click();
			}
		}
	}*/
	
	// Call login.java class
	
	//	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority =2)
		public static void Login(String Testurl, String UN, String PWD) throws InterruptedException {
			
			driver.manage().window().maximize();
			Thread.sleep(5000);
			
			driver.navigate().to(Testurl);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(UN);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys(PWD);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@id='Login']")).submit();
			 log.debug("login successfull");
			
		}
			
			
			
			

	// Call Create_transaction.java class
	
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority =3)
	public static void transactionSR(String SLNO, String PURCHASEORDER, String ACCOUNT, String CONTACT,
			String PRICEBOOK, String TAXTREATMENT) throws InterruptedException {

		Thread.sleep(18000);
		// clicks on Transactiontab
		Click("//div[@class=\"bBottom\"]/descendant::span[text()='Transactions']");
		Thread.sleep(5000);
		// click on new button
		Click("//a[@class='forceActionLink'][@title='New']/ancestor::li");
		// identifying frame and switch to frame
		WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		Thread.sleep(12000);
		// entering purchase order(1)

		driver.findElement(By.xpath("(//span[@class=\"lookupInput\"]/input)[1]")).sendKeys(PURCHASEORDER);
		Reporter.log("enters order");
		// clicks on account look up(img)
		Click("//img[@title='Account Lookup (New Window)']");
		Reporter.log("clicks on account icon");
		
		Thread.sleep(8000);
		// going into the look up method of account
		lookupDataSelect(driver, ACCOUNT);
		// switching back to main frame from lookup window
		Thread.sleep(4000l);
		driver.switchTo().frame(frame);
		
		 /*System.out.println(" switching to frame after account look up");
		 Thread.sleep(1000);
		 
		 String CONTACT1 = CONTACT; // clicks on contact and calls lookup
		 Click("//img[@title='Contact Lookup (New Window)']");
		 lookupDataSelect(driver, CONTACT1); Reporter.log("enters contact");
		 driver.switchTo().frame(frame);
		  System.out.println(" switching  to frame afterccontact lookup"); String
		 PRICEBOOK1 = PRICEBOOK; // clicks on pricebook icon Thread.sleep(3000l);
		 Click("//img[@title='Price Book Lookup (New Window)']");
		 lookupDataSelect(driver, PRICEBOOK1); Reporter.log("enters pricebook");
		 
		 
		 
		 // clicks on tax treatment look up 
		 driver.switchTo().frame(frame);
		 System.out.println(" switching  to frame after price book look up");
		 Thread.sleep(3000); String TAXTREATMENT1 = TAXTREATMENT;
		 Click("//img[@title='Tax Treatment Lookup (New Window)']");
		 lookupDataSelect(driver, TAXTREATMENT1); Reporter.log("enters taxtreatment");
		 // clicks on payment terms
		 
		 Thread.sleep(3000);
		 
		
		 driver.switchTo().frame(frame);
		 
*/
		// clicks on save button
		Click("(//input[@value=\"Save\"])[1]");

		driver.switchTo().defaultContent();
		Thread.sleep(3000);

		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
		System.out.println(frames);
		for (WebElement WE1 : frames) {
			System.out.println(WE1);
		}
		Thread.sleep(3000);
		driver.switchTo().frame(1);
		//System.out.println(TDNumber);

		// excel.setCellData("stockreceiving", "TRANSACTIONNO", 2, TDNumber);
		Thread.sleep(4000);

	}

	public static void lookupDataSelect(WebDriver driver, String Account) throws InterruptedException {

		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);

		Iterator<String> itr = handles.iterator();
		String PW = itr.next();
		String SW = itr.next();
		// String CW = itr.next();
		// new child window opens to enter account
		driver.switchTo().window(SW);
		Thread.sleep(2000);
		WebElement frame1 = driver.findElement(By.xpath("//frame[@id=\"searchFrame\"]"));
		driver.switchTo().frame(frame1);

		// Search Text field to enter account
		Actions actions = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//input[@name=\"lksrch\"]"));
		// Thread.sleep(3000l);
		actions.moveToElement(ele);
		actions.click();// clicks on search option
		driver.findElement(By.xpath("//input[@name=\"lksrch\"]")).clear();

		actions.sendKeys(Account);// enters value
		actions.build().perform();
		Thread.sleep(2000);
		// clicks on Go button
		driver.findElement(By.xpath("//input[@title='Go!']")).click();// Go
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		// switch to child window
		System.out.println(" shifting to default ");

		Thread.sleep(2000);

		// enters into searching tabel
		System.out.println("switching to 2nd  frame");
		WebElement frame2 = driver.findElement(By.xpath("//frame[@id=\"resultsFrame\"]"));
		driver.switchTo().frame(frame2);
		System.out.println("switched to the element");

		Thread.sleep(2000);
		System.out.println("searching account");
		try {
			// clicks on the account in the tabel
			// if
			// (driver.findElement(By.xpath("((//table)[2]/descendant::a)[1]")).isDisplayed())//
			// ---live
			if (driver.findElement(By.xpath("((//table)[5]/descendant::a)[4]")).isDisplayed())// ---coh
			{
				System.out.println("clicked the element");
				Thread.sleep(2000);
				// driver.findElement(By.xpath("((//table)[2]/descendant::a)[1]")).click();--live
				driver.findElement(By.xpath("((//table)[5]/descendant::a)[4]")).click();// --coh

				System.out.println("clicked the element");
			}

		} catch (Exception e) {
			System.out.println("account is not present");
		}
		
		//Click
		try {
			// if (driver.findElement(By.xpath("//table/descendant::a")).isDisplayed()) {

			if (driver.findElement(By.xpath("(//table/descendant::a)[8]")).isDisplayed()) {
				System.out.println("enters contact  s element");
				Thread.sleep(800);

				// driver.findElement(By.xpath("//table/descendant::a")).click();//--live3

				driver.findElement(By.xpath("(//table/descendant::a)[8]")).click();// --coh

			}
		}

		catch (Exception e) {
			
			
			System.out.println("contact is not present");
		}

		// clicks on purchase order price book , tax treatment
		try {
			// clicks on the account in the tabel
			// if
			// (driver.findElement(By.xpath("((//table)[2]/descendant::a)[1]")).isDisplayed())//
			// ---live
			if (driver.findElement(By.xpath("//table/descendant::a")).isDisplayed()) {
				
				
				driver.findElement(By.xpath("//table/descendant::a")).click();// --live3

				System.out.println("clicked the element");
			}

		} catch (Exception e) {
			
			System.out.println(" tax treatment is not present");
		}

		driver.switchTo().window(PW);
		Thread.sleep(2000l);
	}

	
	
	
  // @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)
	public static void transactionorderline(String SLNO, String PRODUCT2, String QTY, String taxcode)
			throws InterruptedException {
		Thread.sleep(20000);
		// clicks on nextitem button
		Click("(//div[@class=\"pbHeader\"])[2]/descendant::input");
		Thread.sleep(3000l);
		Thread.setDefaultUncaughtExceptionHandler(null);

		// click on product text field and sends the product.
		Actions actions2 = new Actions(driver);
		WebElement ele3 = driver.findElement(By.xpath("//div[@class=\"requiredInput\"]/descendant::span"));
		actions2.moveToElement(ele3);
		actions2.click();
		actions2.sendKeys(PRODUCT2);
		actions2.build().perform();
		Thread.sleep(1000);

	/*	WebElement ele5 = driver.findElement(By.xpath("(//input[@type=\"text\"])[5]"));
		Thread.sleep(2000);
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(ele5);
		Thread.sleep(1000);
		actions1.click();
		ele5.clear();
		actions1.sendKeys(QTY);
		actions1.build().perform();*/

	/*	Thread.sleep(3000);
		WebElement ele4 = driver.findElement(By.xpath("(//input[@type=\"text\"])[3]"));
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele4);
		Thread.sleep(2000);
		actions.click();
		ele4.clear();
		actions.sendKeys(QTY);
		actions.build().perform();
*/
		Thread.sleep(3000);
/*
		// clicks on tax code
		WebElement taxcode4 = driver.findElement(
				By.xpath("//select[@id='j_id0:TheForm:DocumentItem:j_id276:currentDocumentItemTaxCodeField_Edit']"));
		Thread.sleep(2000);
		taxcode4.click();

		Thread.sleep(3000);

		String code1 = taxcode;
		taxcode4.sendKeys(code1);
*/
		/*System.out.println("//option[text()=" + code1 + "]");
		Thread.sleep(5000l);

		driver.findElement(By.xpath("//option[text()=\"" + code1 + "\"]")).click();*/

		// option[text()="Exempt UK"]
		driver.switchTo().defaultContent();

		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
		System.out.println(frames);
		for (WebElement WE1 : frames) {
			System.out.println(WE1);
		}

		driver.switchTo().frame(1);

		Thread.sleep(4000);
		// clicks on save button
		driver.findElement(By.xpath("(//input[@value=\"Save\"])[1]")).click();
		Thread.sleep(8000);
		
		driver.switchTo().defaultContent();
		
		Thread.sleep(1000);

	}

	// Call Create_stockreceiving.java class
	
	//@Test(dataProviderClass=Testbase.class,dataProvider="dp",priority = 5)
	public static void stockreceiving(String VENDOR,String TRANSACTIONNO,String QTY,String LOCATION) throws InterruptedException {
		//driver.manage().window().maximize();
		Thread.sleep(12000);
		//Thread.sleep(10000);
		driver.findElement(By.xpath("//a[@title=\"Stock Receiving\"]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@title='New']")).click();

		 Thread.sleep(10000);
		
		 WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
			driver.switchTo().frame(frame);
			System.out.println(" switching  to frame");
			try {
			Thread.sleep(20001);// entering purchase order
			System.out.println("r768");
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[1]/input")).click();
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[1]/input")).sendKeys(VENDOR);
			Thread.sleep(20001);
			Actions action=new Actions(driver);
			List<WebElement> lst=driver.findElements(By.xpath("(//ul[@class=\"slds-lookup__list\"])[1]/li"));

			for (WebElement we : lst) {
			String str = we.getText();
			System.out.println(str);
			if (str.equalsIgnoreCase(VENDOR))
			{
			Thread.sleep(20001);
			we.click();
			action.click();
			action.build().perform();
			}
			}
			System.out.println("Vendor");
			} catch (Exception e) {

			}
		
		
		
			System.out.println("switched to frame");
			WebElement we1 = driver.findElement(By.xpath(
					"(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[4]"));

			Actions actions = new Actions(driver);
			actions.moveToElement(we1);
			actions.click();
			actions.sendKeys(TRANSACTIONNO);
			actions.build().perform();

			Thread.sleep(9000l);
			List<WebElement> list = driver.findElements(By.xpath("//ul[@class=\"slds-lookup__list\"]/li"));
			for (WebElement we : list) {
				if (we.getText().contentEquals(TRANSACTIONNO)) {
					Thread.sleep(5000l);
					we.click();

				}
			}
		
		Thread.sleep(8000l);
		driver.findElement(
				By.xpath("//input[@class=\"slds-input input uiInput uiInputNumber uiInput--default uiInput--input\"]"))
				.sendKeys(QTY);
	/*	Thread.sleep(5000l);
		driver.findElement(
				By.xpath("//input[@class=\"slds-input input uiInput uiInputNumber uiInput--default uiInput--input\"]"))
				.sendKeys(QTY);
		Thread.sleep(20001);*/

      
		
		
		//save button
		driver.findElement(By.xpath("//button[text()=\"Submit\"]")).click();
		
		
		
		
		 log.debug("StockReceiving creation sucessfull");
			Thread.sleep(8000l);
	}

	public static void click(String TD) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::a"));
		for (WebElement we : list) {
			if (we.getText().contentEquals(TD)) {
				Thread.sleep(8000l);

				we.click();

			}
		}
	}

	
	@DataProvider
	public static Object[][] data() {
		String sheetname = "STOCKRECEIVING";
		int rowno = excel.getRowCount(sheetname);
		int colno = excel.getColumnCount(sheetname);
		Object[][] data = new Object[rowno - 1][colno];
		for (int row = 2; row <= rowno; row++) {
			for (int col = 0; col < colno; col++) {
				System.out.println(rowno + "," + colno);
				data[row - 2][col] = excel.getCellData(sheetname, col, row);
				System.out.println(data[row - 2][col]);

			}

		}
		return data;

	}
	
	

	// Call Create_stapporder.java class

		//@Test(dataProvider = "data2", priority = 6)
		public static void stapporder1(String CUSTOMER, String SOURCE,
				String PRODUCT, String QTY, String STATUS)
				throws InterruptedException, AWTException {

			//driver.manage().window().maximize();
			Thread.sleep(16000l);

			// click on stapporder tab
			driver.findElement(
					By.xpath("//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Stapp Orders']"))
					.click();
			System.out.println("Clicks on Stapp order Tab");
			Thread.sleep(5000l);

			// click on New button
			driver.findElement(
					By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li"))
					.click();

			// customer order number
			driver.findElement(By.xpath("(//input)[6]")).sendKeys("1");
			Thread.sleep(9000l);
			
			// customer
			driver.findElement(By.xpath("(//input)[7]")).sendKeys(CUSTOMER);
			Thread.sleep(20000l);
			click(CUSTOMER);

			// source
			WebElement we2 = driver
					.findElement(By
							.xpath("(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[2]"));
			Select option = new Select(we2);
			option.selectByVisibleText(SOURCE);

			// add orderline button click
			driver.findElement(
					By.xpath("//button[contains(text(),\"Add Order Lines\")]"))
					.click();
			Thread.sleep(8000l);

			// enter product
			WebElement we4 = driver
					.findElement(By
							.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]/descendant::input)[9]"));
			Actions act = new Actions(driver);
			act.click(we4);
			act.sendKeys(PRODUCT);
			act.build().perform();
			// wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]/descendant::input)[9]"))).click();
			// wait1.wait(timeout);
			// driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]/descendant::input)[9]")).click();
			Thread.sleep(15000l);

			// scrolls scrollbar
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			// wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::li)[1]"))).click();

			// select product from the list
			click1(PRODUCT);
			// javascriptExecuter jp= new JavaScript

			// tax code
			
			/* * 
			 * driver.findElement(By .xpath(
			 * "(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[20]"
			 * )) .sendKeys("Taxable Goods"); Thread.sleep(5000l);
			 * click("Taxable Goods");*/
			 

			// Enter the line item QTY
			driver.findElement(
					By.xpath("//input[@class=\"input uiInput uiInputNumber uiInput--default uiInput--input\"]"))
					.sendKeys(QTY);
			
			/* * WebElement we4 = driver.findElement(By. xpath(
			 * "(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[8]"
			 * )); Select option2= new Select(we4);
			 * option2.selectByVisibleText("Confirmed");*/
			 

			// Select the line item status
			WebElement we3 = driver
					.findElement(By
							.xpath("(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[4]"));
			Select option1 = new Select(we3);
			option1.selectByVisibleText(STATUS);

			// click on save button
			driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
			Thread.sleep(16000l);

			// click ok in the alert
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(8000);
			// driver.close();
			System.out.println(driver.getWindowHandles().size());
		}

		// selects product from the list
		public static void click1(String TD) throws InterruptedException {
			List<WebElement> list = driver.findElements(By
					.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::a"));
			for (WebElement we : list) {
				if (we.getText().contentEquals(TD)) {
					Thread.sleep(8000);
					((JavascriptExecutor) driver).executeScript(
							"arguments[0].click();", we);
					// we.click();

				}
			}
		}

		@DataProvider
		public static Object[][] data2() {
			String sheetname = "stapporder1";

			int rowno = excel.getRowCount(sheetname);
			int colno = excel.getColumnCount(sheetname);
			Object[][] data = new Object[rowno - 1][colno];
			for (int row = 2; row <= rowno; row++) {
				for (int col = 0; col < colno; col++) {
					System.out.println(rowno + "," + colno);
					data[row - 2][col] = excel.getCellData(sheetname, col, row);
					System.out.println(data[row - 2][col]);

				}

			}
			return data;

		}

		 //Call Create_package.java class

		//@Test(dataProviderClass=Testbase.class,dataProvider="dp", priority = 7)
		public static void Package(String CUSTOMER,String SONO,String STATUS) throws InterruptedException, AWTException {

			//driver.manage().window().maximize();
			Thread.sleep(16000l);
			WebDriverWait wait = new WebDriverWait(driver, 60l);
			// Account
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"More\"]"))).click();

			// driver.findElement(By.xpath("//span[text()=\"More\"]")).click();
			List<WebElement> lst = driver.findElements(By.xpath(
					"//div[@class=\"slds-dropdown slds-dropdown--length-with-icon-10 slds-dropdown--right\"]/descendant::a"));
			Thread.sleep(5000l);
			for (WebElement we : lst) {
				if (we.getText().equalsIgnoreCase("Stapp Order Package")) {
					Thread.sleep(5000l);
					we.click();
				}
			}

			Thread.sleep(16000l);
			// New button
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()=\"New\"])[2]"))).click();

			driver.findElement(By.xpath("//button[text()=\"New\"]")).click();

			// driver.findElement(By.xpath("(//button[text()=\"New\"])[2]")).click();

			// driver.switchTo().
			Thread.sleep(16000l);

			WebElement we = driver.findElement(By.xpath(
					"(//section[@class=\"slds-modal slds-modal_large slds-slide-up-saving\"]/descendant::div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[1]"));
			Actions act1 = new Actions(driver);
			act1.click(we);
			act1.sendKeys(CUSTOMER);
			act1.build().perform();
			Thread.sleep(21000l);
			click2(CUSTOMER);

			Thread.sleep(5000l);

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			WebElement we2 = driver.findElement(By.xpath(
					"(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[4]"));
			act1.click(we2);

			act1.sendKeys(SONO);
			act1.build().perform();

			Thread.sleep(5000l);
			click2(SONO);

			Thread.sleep(5000l);
			WebElement we3 = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));

			act1.click(we3);
			act1.build().perform();
			Thread.sleep(5000l);
			driver.findElement(By.xpath("//a[text()=\"Autopick\"]")).click();
			Thread.sleep(8000l);
			Robot rt = new Robot();
			rt.keyPress(KeyEvent.VK_ENTER);
			rt.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(5000l);
			rt.keyPress(KeyEvent.VK_ENTER);
			rt.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000l);

			driver.findElement(By.xpath("(//footer/descendant::button)[4]")).click();
			Thread.sleep(5000l);
			rt.keyPress(KeyEvent.VK_ENTER);
			rt.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(5000l);
			WebElement we4 = driver.findElement(By.xpath("(//select[@class=\"slds-input select\"])[2]"));
			Select sel = new Select(we4);
			sel.selectByVisibleText(STATUS);

			Thread.sleep(8000l);
			driver.findElement(By.xpath("(//button[text()=\"Save\"])[2]")).click();
			
			 log.debug("package creation sucessfull");

		}

		public static void click2(String TD) throws InterruptedException {
			List<WebElement> list = driver.findElements(By.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::a"));
			for (WebElement we : list) {
				if (we.getText().contentEquals(TD)) {
					Thread.sleep(8000l);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
					// we.click();

				}
			}
		}

		
		
		
		@DataProvider
		public static Object[][] data3() {
			String sheetname = "PACKAGE";
			int rowno = excel.getRowCount(sheetname);
			int colno = excel.getColumnCount(sheetname);
			Object[][] data = new Object[rowno - 1][colno];
			for (int row = 2; row <= rowno; row++) {
				for (int col = 0; col < colno; col++) {
					System.out.println(rowno + "," + colno);
					data[row - 2][col] = excel.getCellData(sheetname, col, row);
					System.out.println(data[row - 2][col]);

				}

			}
			return data;

		}
	
}
