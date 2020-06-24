package genericcode;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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




public class cohbaines extends Testbase 

{

	
	//----------------------------------------------CREATE ACCOUNT------------------------------------------------------
	
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp",priority=1)
	public static void account(String TYPE, String ACCOUNTNAME,String REGION,String COUNTRY,String BILLINGADDRESS) throws InterruptedException, AWTException {

		Thread.sleep(20000);		
		driver.manage().window().maximize();
		// clicks on account tab
		//driver.findElement(By.xpath("//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Accounts']")).click();
		driver.findElement(By.xpath("//a[@title='Accounts']/span")).click();		

		Thread.sleep(5000l);
		// New button
		driver.findElement(By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li")).click();

		
		//clicks on the types (customer/vendor/shipping addresss)
		String account = TYPE;

		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.tagName("span"));
		System.out.println(list.size());
		// System.out.println(list);
		for (WebElement we : list) {

			if (we.getText().equalsIgnoreCase(account)) {

				Thread.sleep(2000);
				Actions act1 = new Actions(driver);
				act1.click(we);
				//act1.sendKeys("PL");
				act1.build().perform();

			}

		}

		//next button		
		if(account.equals("Vendor")||account.equals("Customer")) {
			
			Thread.sleep(4000);
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			
			
		//enters account name
		driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"))
				.sendKeys(ACCOUNTNAME);

		//enters currency field
		driver.findElement(By.xpath("(//label/span[text()=\"Currency code\"]/following::input)[1]")).sendKeys("GBP");
		
		//enters region
		String Region=REGION;
		
		driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
		 List<WebElement> lst=	driver.findElements(By.xpath("//div[@role=\"menu\"]/descendant::a"));
		    for(WebElement we:lst) {
			if(we.getText().equalsIgnoreCase(Region)) {
				Thread.sleep(3000);
				we.click();
			}	}
		
		
		    //enters  Billing address
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//textarea[@placeholder=\"Billing Street\"]")).sendKeys(BILLINGADDRESS);
		
		    
		    String Country =COUNTRY;
		    
		  //clicks on country
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//a[@class='select'])[4]")).click();
			
		   List<WebElement> lst1=	driver.findElements(By.xpath("//div[@role=\"menu\"]/descendant::a"));
		    for(WebElement we:lst1) {
			if(we.getText().equalsIgnoreCase(Country)) {
				Thread.sleep(3000);
				we.click();
			}	
			}
		
		}
		else if(account.equals("Location")||account.equals("Packaged Location")||account.equals("Receiving Location")||account.equals("Logistics")||account.equals(""))
		{
			
			Thread.sleep(5000);
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			
			driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"))
			.sendKeys(ACCOUNTNAME);
			
				
		}
		
		driver.findElement(By.xpath("(//span[text()=\"Save\"])[2]")).click();
		Thread.sleep(12000l);
		
		System.out.println("Account created");

	} 


	
	
	//-----------------------------------------CREATE  PRODUCT--------------------------------------------------------

	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
	public static void product(String PRODUCTNAME, String TYPE, String ATTRIBUTYPE, String PRODUCTWEIGHT, String PUOM,
			String PMT, String SOURCE,String PRODUCTCODE,String PRODUCTDESCRIPTION) throws MalformedURLException, InterruptedException {
//driver.manage().window().maximize();
		Thread.sleep(18000);
		//WebDriverWait wait = new WebDriverWait(driver, 120);
		//click on products tab
		//driver.findElement(By.xpath("//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Products']")).click();
		
		driver.findElement(By.xpath("//a[@title='Products']/span")).click();
		Thread.sleep(5000l);
		// new button
		driver.findElement(By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li")).click();

		// Product text field
		//driver.findElement(By.xpath("(//div[@class='slds-form slds-is-editing']/div[1]/descendant::input)[1]")).sendKeys(PRODUCTNAME);
		
		driver.findElement(By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input']/input)[1]")).sendKeys(PRODUCTNAME);
		Thread.sleep(8000l);
		// clicks on dimension text field and selects yes
		//driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
		driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();//---coh
		driver.findElement(By.xpath("//a[@title=\"Yes\"]")).click();
		Thread.sleep(5000l);
		
		//product code
		//driver.findElement(By.xpath("(//label/descendant::span[text()='Product Code']/following::input)[1]")).sendKeys(PRODUCTCODE);
		driver.findElement(By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input']/input)[2]")).sendKeys(PRODUCTCODE);
		
		//product description
		//driver.findElement(By.xpath("(//textarea)[1]")).sendKeys(PRODUCTDESCRIPTION);
		driver.findElement(By.xpath("(//div[@class='uiInput uiInputTextArea uiInput--default uiInput--textarea']/textarea)[1]")).sendKeys(PRODUCTDESCRIPTION);
		
		Thread.sleep(5000l);
		// Sendkeys("//input[@id=\"1862:0\"]","spn-001");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(5000l);
		// clicks on active checkbox
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		
		//clicks on stock item
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();

		// clicks on save
		driver.findElement(By.xpath("//button[@title='Save']")).click();

		Thread.sleep(8000l);
		
		List<WebElement> multi = driver.findElements(By.xpath("//a[@class=\"tabHeader\"][@tabindex=\"-1\"]"));
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

		
		log.debug("product creation sucessfull");

	}

	public static void pricebook(String pricebook) throws InterruptedException {

		select("(//select)[1]", pricebook);

		Thread.sleep(5000);
		select("(//select)[2]", "GBP");
		Click("//span[text()=\"Next\"]");
		Thread.sleep(5000);

	}

	public static void Amount(String price) throws InterruptedException {

		Click("(//input[@aria-required=\"true\"])[3]");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@aria-required=\"true\"])[3]")).clear();
		Thread.sleep(2000);
		sendkeys("(//input[@aria-required=\"true\"])[3]", price);

		Click("(//span[text()=\"Save\"])[2]");
	}

	
	
	public static void ADDtoPriceBook() throws InterruptedException {

		List<WebElement> multi1 = driver.findElements(By.xpath("//div[text()=\"Add to Price Book\"]"));
		int size2 = multi1.size();
		for (int x = 1; x <= size2; x++) {

			if (driver.findElement(By.xpath("(//div[text()=\"Add to Price Book\"])[" + x + "]")).isDisplayed()) {
				Thread.sleep(6000);
				driver.findElement(By.xpath("(//div[text()=\"Add to Price Book\"])" + "[" + x + "]")).click();
				// driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])[x]")).click();
			}
		}
		
		System.out.println("Product created");
	}
	
	

	//---------------------------------------------Create_transaction.java class---------------------------------------------
	
	//	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority =3)
		public static void transactionSR(String SLNO, String PURCHASEORDER, String ACCOUNT, String CONTACT,
				String PRICEBOOK, String TAXTREATMENT) throws InterruptedException {

			Thread.sleep(22000);
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
			System.out.println("Transaction created");
			//driver.switchTo().defaultContent();
		}

		
		//------------------------------------Create TRANSACTION LINE ITEM-------------------------------------------------
		
	//  @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)

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

		    Thread.sleep(3000);
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

	
	    //---------------------------------Create_stockreceiving.java class-------------------------------------------------
		
	//	@Test(dataProviderClass=Testbase.class,dataProvider="dp",priority = 5)
		public static void stockreceiving(String VENDOR,String TRANSACTIONNO,String QTY,String LOCATION) throws InterruptedException {
			//driver.manage().window().maximize();
			Thread.sleep(12000);
			//WebDriverWait wait = new WebDriverWait(driver, 60l);
			// Clicks on stock receiving tab
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Stock Receiving']"))).click();
			driver.findElement(By.xpath("//a[@title='Stock Receiving']/span")).click();
			Thread.sleep(8000l);
			
			driver.findElement(By.xpath("//div[text()=\"New\"]")).click();

			
			System.out.println("Checking frames inside SR");
			
			
		
			
			List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
			System.out.println("frames.size::"+frames.size());
			for (WebElement WE1 : frames)
			{
			
				System.out.println("FRAME LIST ::"+WE1);
				
			}
			
			System.out.println("switching to frame");
			Thread.sleep(8000);
		    WebElement frame = driver.findElement(By.xpath("(//iframe[@allowfullscreen='true'])[2]"));
		    driver.switchTo().frame(frame);
		    
  
			
			Thread.sleep(10000);
	        System.out.println("inspecting vendor xpath::: "+driver.findElement(By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[1]")).getAttribute("data-aura-class"));
			
			driver.findElement(By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[1]")).click();
			driver.findElement(By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[1]")).sendKeys(VENDOR);
			
			
			Thread.sleep(5000);
			
			
			WebElement vendorclick=driver.findElement(By.xpath("//ul[@class=\"slds-lookup__list\"]/li[1]"));
			if(vendorclick.isDisplayed()) {
				Thread.sleep(5000);
				
			driver.findElement(By.xpath("//ul[@class=\"slds-lookup__list\"]/li[1]")).click();
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

			Thread.sleep(5000l);
			
			//entering qty
			driver.findElement(
					By.xpath("//input[@class=\"slds-input input uiInput uiInputNumber uiInput--default uiInput--input\"]"))
					.sendKeys(QTY);
			
			//enter location
			 WebElement we2 = driver.findElement(
					By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[7]"));
			 
			 Actions actions1 = new Actions(driver);
			actions1.moveToElement(we2);
			actions1.click();
			actions1.sendKeys(LOCATION);
			actions1.build().perform();	
			
			Thread.sleep(9000l);
			
		    List<WebElement> list1 =  driver.findElements(By.xpath("(//ul[@class='slds-lookup__list'])/li"));
			for (WebElement we : list1) {
				if (we.getText().contentEquals(LOCATION)) {
					Thread.sleep(5000l);
					we.click();
				}
				
			}
			


	        Thread.sleep(5000l);
			driver.findElement(By
					.xpath("//div[@class=\"form-element uiInput uiInputDate uiInput--default uiInput--input uiInput--datetime\"]/a"))
					.click();

			driver.findElement(By.xpath("//span[text()=\"10\"]")).click();
			
			
			driver.findElement(By.xpath("//button[text()=\"Submit\"]")).click();
			
			
			
			driver.switchTo().defaultContent();
			System.out.println("StockReceiving creation sucessfull");
				Thread.sleep(10000l);
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
	
	

	//-----------------------------------------Create_stapporder.java class-------------------------------------------------

//		@Test(dataProviderClass=Testbase.class,dataProvider="dp",priority = 6)
		
		
		public static void stapporder1(String CUSTOMER, String SOURCE, String CONTACT, String STATUS)
				throws InterruptedException, AWTException {

			//driver.manage().window().maximize();
			Thread.sleep(15000);
	        
			
			//click stapporder tab
			//driver.findElement(By.xpath("//ul[@class=\"slds-grid slds-has-flexi-truncate navUL\"]/descendant::span[text()='Stapp Order']")).click();
			driver.findElement(By.xpath("//span[text()='Stapp Order']")).click();
			
			
			
			// New button
			Thread.sleep(5000);		
			driver.findElement(By.xpath("(//button[text()='New'])[1]")).click();
			
			//customer order number
			//driver.findElement(By.xpath("(//input)[4]")).sendKeys("1");
			driver.findElement(By.xpath("(//div[@class='slds-size--1-of-4 slds-p-around--medium']/input)[1]")).sendKeys("1");
			
			// customer
			Thread.sleep(10000);
			
			System.out.println("enter customer");
			driver.findElement(By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[1]")).click();
			driver.findElement(By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[1]")).sendKeys(CUSTOMER);
			System.out.println("Selects 123 customer");
			//click(CUSTOMER);
			
            Thread.sleep(10000);
			
			
			WebElement customerclick=driver.findElement(By.xpath("//ul[@class=\"slds-lookup__list\"]/li[1]"));
			if(customerclick.isDisplayed()) 
			{
				Thread.sleep(5000);
				
			driver.findElement(By.xpath("//ul[@class=\"slds-lookup__list\"]/li[1]")).click();
			}
			 
			 
			 /*Actions actions1 = new Actions(driver);
			 actions1.moveToElement(loc);
			 actions1.click();
			 actions1.sendKeys(CUSTOMER);
			 actions1.build().perform();	
			 
			 Thread.sleep(5000);
			 
			 List<WebElement> loclist = driver.findElements(By.xpath("//ul[@class='slds-lookup__list']/li"));
			 for( WebElement we : loclist)
			 {
				 if(we.getText().contentEquals(CUSTOMER)) {
					Thread.sleep(5000l);
					we.click();
				}
				
			 }*/
			
			System.out.println("clicks customer");
			
		/*
			WebElement we=driver.findElement(By.xpath("(//input)[5]"));
			Actions act1 = new Actions(driver);
			
			//act1.click(we);
			act1.sendKeys(CUSTOMER);
			System.out.println("customer name search");
			act1.build().perform();
			Thread.sleep(12000l);
			click(CUSTOMER);*/

			
			// order source
			Thread.sleep(5000);
			WebElement we3 = driver.findElement(By.xpath(
					"(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[2]"));
			Select option = new Select(we3);
			option.selectByVisibleText(SOURCE);
			
			
			
			//status
			Thread.sleep(5000);
			WebElement we4 = driver.findElement(By.xpath(
					"(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[4]"));
			Select option1 = new Select(we4);
			option1.selectByVisibleText("Confirmed");
			
			
			//contact
			/*Thread.sleep(6000);
					//WebElement we1=driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]"));
					WebElement we5 =driver.findElement(By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[2]"));
					Actions act = new Actions(driver);
					act.click(we5);
					act.sendKeys(CONTACT);
					act.build().perform();
					Thread.sleep(12000l);
					click(CONTACT);
			*/
			Thread.sleep(8000);
			driver.findElement(By.xpath("(//input[@data-aura-class='uiInput uiInputText uiInput--default uiInput--input'])[3]")).sendKeys(CONTACT);
			Thread.sleep(10000);
			
		    System.out.println("entering contact");
		
			WebElement contactclick=driver.findElement(By.xpath("(//ul[@class='slds-lookup__list'])[2]"));
			if(contactclick.isDisplayed()) 
			{
				System.out.println("Taking from list");
				Thread.sleep(5000);
				
			driver.findElement(By.xpath("(//ul[@class='slds-lookup__list'])[2]")).click();
			}
			
			
		}
		
		
		
		//------------------------------Stapp Order Add ORDER LINE ITEM-----------------------------------------------------
			
	//	@Test(dataProviderClass=Testbase.class,dataProvider="dp",priority = 7)
			public static void stapporderline( String PRODUCT1, String QTY )
					throws InterruptedException, AWTException {
				
				
				//click on add more products
				Thread.sleep(5000);
				driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral slds-m-around--small']")).click();	
				
				//enter the order line products
				Thread.sleep(5000l);
				//WebElement we1=driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]/descendant::input)[10]"));
				WebElement we11 = driver.findElement(By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon--right'])[9]"));
				
				Actions act = new Actions(driver);
				act.click(we11);
				act.sendKeys(PRODUCT1);
				act.build().perform();				
				Thread.sleep(12000l);
				click(PRODUCT1);
				
				
				//enter the qty
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@class='input uiInput uiInputNumber uiInput--default uiInput--input']")).sendKeys(QTY);
							
				// save
				Thread.sleep(5000);
				driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
				Thread.sleep(8000);
				Robot rb = new Robot();

				rb.keyPress(KeyEvent.VK_ENTER);

				rb.keyRelease(KeyEvent.VK_ENTER);

				Thread.sleep(8000);
				// driver.close();
				System.out.println(driver.getWindowHandles().size());
				//String SONumber = driver.findElement(By.xpath("(//h1[@class=\"slds-page-header__title slds-m-right--small slds-truncate slds-align-middle\"])[2]/span")).getText();
				//System.out.println("entered the value");
				//System.out.println(SONumber);
			    log.debug("Stapporder creation sucessfull");
		}

			
		


		@DataProvider
		public static Object[][] data1() {
			String sheetname = "orderline";
			
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

		 //---------------------------------------Call Create_package.java class-------------------------------------------

		//@Test(dataProviderClass=Testbase.class,dataProvider="dp", priority = 8)
		public static void Package(String CUSTOMER,String SONO,String PACKAGEDQTY,String STATUS) throws InterruptedException, AWTException {
			Thread.sleep(16000);

           //click on tab

			driver.findElement(By.xpath("//a[@title='Stapp Order Package']/span")).click();
			Thread.sleep(4000);
			
			driver.navigate().refresh();
			
			// New button
			Thread.sleep(16000);
			System.out.println("New button::"+driver.findElement(By.xpath("(//button[text()='New'])[1]")).getAttribute("class"));
			WebElement click= 	driver.findElement(By.xpath("(//button[text()='New'])[1]"));
			click.click();
			//click.submit();
			
			//driver.findElement(By.xpath("(//button[@class='slds-button slds-button_neutral'])[1]")).click();

			Thread.sleep(9000l);
			
			//Enter customer
			WebElement we = driver.findElement(By.xpath(
					"(//section[@class=\"slds-modal slds-modal_large slds-slide-up-saving\"]/descendant::div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[1]"));
			Actions act1 = new Actions(driver);
			act1.click(we);
			act1.sendKeys(CUSTOMER);
			act1.build().perform();
			Thread.sleep(12000);
			click(CUSTOMER);

			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			WebElement we2 = driver.findElement(By.xpath(
					"(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[4]"));
			act1.click(we2);
			Thread.sleep(12000);
			act1.sendKeys(SONO);
			act1.build().perform();

			Thread.sleep(5000l);
			click(SONO);
			
			Thread.sleep(8000);
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[4]")).clear();
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[4]")).sendKeys(PACKAGEDQTY);

			Thread.sleep(8000);
			WebElement we3 = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));

			act1.click(we3);
			act1.build().perform();
			Thread.sleep(8000);
			driver.findElement(By.xpath("//a[text()=\"Autopick\"]")).click();
			Thread.sleep(12000);
			Robot rt = new Robot();
			rt.keyPress(KeyEvent.VK_ENTER);
			rt.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(12000);
			rt.keyPress(KeyEvent.VK_ENTER);
			rt.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(8000);

			driver.findElement(By.xpath("(//footer/descendant::button)[4]")).click();
			Thread.sleep(8000);
			rt.keyPress(KeyEvent.VK_ENTER);
			rt.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(8000);
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
		public static Object[][] data2() {
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
	//java.util.Iterator<String> itr = handles.iterator();
}
