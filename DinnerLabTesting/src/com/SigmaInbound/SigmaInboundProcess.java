//Author = Shridhar 

package com.SigmaInbound;



import java.awt.AWTException;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SigmaInboundProcess extends Testbase 
{
	
	//-------------------------------------Account creation--------------------------------------------------------------
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=1)
	public static void account(String AccountType, String AccountName, String Active, String CurrencyCode, String Email, String ShippingStreet,
			String ShippingCity, String ShippingState, String ShippingZip, String ShippingCountry, String AccAddress, String AccCity,
			String AccState, String AccCountry, String AccZip, String AccPhone) throws InterruptedException, AWTException
	{
		
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		
		
		//app launcher
		driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
	

	
		
		//click on SigmaERP
		
		driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys("SigmaErp");
		
		driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);

	

		//driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
			

		
		
		//click on account tab
		driver.findElement(By.xpath(OR.getProperty("Acctab"))).click();
		Thread.sleep(5000);
		
		
		//click on new button
		driver.findElement(By.xpath(OR.getProperty("AccNew"))).click();
		Thread.sleep(4000);
		
		//account type
		if(AccountType.equals("Vendor"))
		{
			//selects vendor type
			driver.findElement(By.xpath(OR.getProperty("AccVendor"))).click();
			Thread.sleep(4000);
		}
		
		
		//next
		driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
		Thread.sleep(4000);
		
		
		//account name
		driver.findElement(By.xpath(OR.getProperty("AccName"))).sendKeys(AccountName);
		Thread.sleep(4000);
		
		
		//active
		WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccActiveClick")));
		Actions a = new Actions(driver);
		a.moveToElement(we1);
		a.click();
		
		driver.findElement(By.xpath(OR.getProperty("AccActiveClick"))).click();
		
		
		List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
		for (WebElement we : list)
		{
			if (we.getText().contains(Active)){
				Thread.sleep(5000);
				we.click();
			}
		}
		Thread.sleep(5000);
		
		
		//Email 
		driver.findElement(By.xpath("//input[@class=' input'][@type='email']")).sendKeys(Email);
		
		
		//driver.findElement(By.xpath(OR.getProperty("AccEmail"))).click();
		
		
		
		//save
		//driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
		driver.findElement(By.xpath("//button[3]//span[.='Save']")).click();
		Thread.sleep(4000);
	
	
	
	}
		
	
	
		
	//---- Customer Creation 
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
	public static void Cashcustomer(String AccountType, String AccountName, String CustomerType, String CurrencyCode, String Email,
			String Address,String City,String State,String Country,String Zip,String Phone,String Fax,
			String ShippingStreet, String ShippingCity, String ShippingState, String ShippingZip,
			String ShippingCountry,String CreditLimit

			) throws InterruptedException
			{
			
			
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		 
		driver.findElement(By.xpath("//nav[@class='appLauncher slds-context-bar__icon-action']//button[1]")).click();
		Thread.sleep(6000);
	
		driver.findElement(By.xpath("//div[@class='app-grid']//ul//li[1]")).click();
		Thread.sleep(6000);

		
		driver.findElement(By.xpath(OR.getProperty("Acctab"))).click();
		Thread.sleep(5000);

		
		driver.findElement(By.xpath(OR.getProperty("AccNew"))).click();
		Thread.sleep(4000);


		if (AccountType.equals("Customer")) {
			
			driver.findElement(By.xpath(OR.getProperty("AccCustomer"))).click();
			Thread.sleep(4000);
			
			driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
			Thread.sleep(4000);

			driver.findElement(By.xpath(OR.getProperty("AccName"))).sendKeys(AccountName);
			Thread.sleep(4000);
			
			Thread.sleep(4000);
			driver.findElement(By.xpath("(//a[text()=\"--None--\"])[2]")).click();
			System.out.println("Clicking Active picklist");
			
			Thread.sleep(4000);
				List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
				for (WebElement we: list) {
					
					System.out.println("we>>>"+we.getText());
					
					if (we.getText().contains("Yes")) {
						System.out.println("Active selecting");
						Thread.sleep(5000);
						we.click();
						
					
		}
				
				}
				
				
		
		
		
				
				WebElement we1=	driver.findElement(By.xpath("(//a[@class='select'])[1]"));
				we1.sendKeys(CustomerType);
				Thread.sleep(3000);
				
				Thread.sleep(4000);
				driver.findElement(By.xpath(OR.getProperty("CurrencyCode"))).sendKeys(CurrencyCode);
				Thread.sleep(4000);
				
				driver.findElement(By.xpath(OR.getProperty("Email"))).sendKeys(Email);
				Thread.sleep(4000);
				
				driver.findElement(By.xpath(OR.getProperty("AccAddress"))).click();
				driver.findElement(By.xpath(OR.getProperty("AccAddress"))).sendKeys(Address);
				Thread.sleep(4000);
				
				
				driver.findElement(By.xpath(OR.getProperty("AccCity"))).sendKeys(City);
				Thread.sleep(4000);

				
				driver.findElement(By.xpath(OR.getProperty("AccState"))).sendKeys(State);
				Thread.sleep(4000);

		
				driver.findElement(By.xpath(OR.getProperty("AccCountry"))).sendKeys(Country);
				Thread.sleep(4000);
				
				driver.findElement(By.xpath(OR.getProperty("AccZip"))).sendKeys(Zip);
				Thread.sleep(4000);
				
				driver.findElement(By.xpath(OR.getProperty("AccPhone"))).sendKeys(Phone);
				Thread.sleep(4000);

				driver.findElement(By.xpath(OR.getProperty("AccFax"))).sendKeys(Fax);
				Thread.sleep(4000);									
				
				driver.findElement(By.xpath(OR.getProperty("ShippingStreet"))).sendKeys(ShippingStreet);
				Thread.sleep(4000);
				
				driver.findElement(By.xpath(OR.getProperty("ShippingCity"))).sendKeys(City);
				Thread.sleep(4000);
		
				driver.findElement(By.xpath(OR.getProperty("ShippingState"))).sendKeys(ShippingState);
				Thread.sleep(4000);
				
				driver.findElement(By.xpath(OR.getProperty("ShippingZip"))).sendKeys(ShippingZip);
				Thread.sleep(4000);

				driver.findElement(By.xpath(OR.getProperty("ShippingCountry"))).sendKeys(ShippingCountry);
				Thread.sleep(4000);				
			
				driver.findElement(By.xpath("//button[@title='Save']")).click(); 
				Thread.sleep(4000);
			
			}

	
				if(CustomerType.equals("Credit Customer")) {
					
					System.out.println("Credit Customer");
					js.executeScript("window.scrollBy(0,1000)");
					Thread.sleep(4000);
					
					driver.findElement(By.xpath(OR.getProperty("crClick"))).click();
					Thread.sleep(4000);
					
					driver.findElement(By.xpath(OR.getProperty("crEdit"))).click();
					Thread.sleep(4000);
					
					driver.findElement(By.xpath(OR.getProperty("crLimit"))).sendKeys(CreditLimit);
					Thread.sleep(4000);
					
					//driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
					Thread.sleep(4000);
					
					}
				
				
}
	
	

	//---------------------------------------Create Product---------------------------------------------------
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=2)
	public static void product(String PRODUCTTYPE, String ProductName, String AttributeType, String ReorderQuantity, String ThresholdQuantity, String MinimumQuantity,
			String SellingPrice, String ListPrice, String BuyingPrice, String Discount, String VENDOR) throws InterruptedException
	{
	

		Thread.sleep(20000);
		//driver.manage().window().maximize();
		
		//app launcher
		//driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		//Thread.sleep(8000);
		
		//click on SigmaERP
		
		
		//driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).sendKeys("ABC");
		//Thread.sleep(8000);
		
		//product tab
		
		//Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("Prodtab")))));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(OR.getProperty("Prodtab")))));
		//driver.findElement(By.xpath(OR.getProperty("Prodtab"))).click();
		driver.findElement(By.xpath("(//one-app-nav-bar[@class='slds-grid slds-has-flexi-truncate'])//one-app-nav-bar-item-root[4]")).click();
		Thread.sleep(5000);
		
	
		
		
		//new
		driver.findElement(By.xpath(OR.getProperty("Newbutton"))).click();
		//Thread.sleep(4000);
		System.out.println("printing"+PRODUCTTYPE );
		
		
		//product type
		if (PRODUCTTYPE.equals("BOM"))
		{
			System.out.println("printing"+PRODUCTTYPE );
			driver.findElement(By.xpath(OR.getProperty("Bom"))).click();
		} 
		else
		{
			driver.findElement(By.xpath(OR.getProperty("Individual"))).click();
		}
		//Thread.sleep(5000);
		
		
		//next button
		driver.findElement(By.xpath(OR.getProperty("Next"))).click();
		//Thread.sleep(5000);
		
		//product name
		driver.findElement(By.xpath(OR.getProperty("Prodname"))).sendKeys(ProductName);
		//Thread.sleep(4000);
		
		
		//Stock Item
		driver.findElement(By.xpath("//span[text()='Stock Item']/following::input[1]")).click();
		 
		
		//attribute type
		WebElement we1 = driver.findElement(By.xpath(OR.getProperty("Attributetype")));
		Actions a = new Actions(driver);
		a.moveToElement(we1);
		a.click();
		
		
		
		List<WebElement> lst = driver.findElements(By.xpath(OR.getProperty("AttributetypeList")));
		for (WebElement we : lst)
		{
			if (we.getText().contains(AttributeType))
			{
				Thread.sleep(5000);
				we.click();
			}
		}
		Thread.sleep(8000);
		
		
		
	
		
		//reorder qty
		driver.findElement(By.xpath(OR.getProperty("ReorderQty"))).clear();
		driver.findElement(By.xpath(OR.getProperty("ReorderQty"))).sendKeys(ReorderQuantity);
		
		
		//threshold qty
		driver.findElement(By.xpath(OR.getProperty("ThresholdQty"))).clear();
		driver.findElement(By.xpath(OR.getProperty("ThresholdQty"))).sendKeys(ThresholdQuantity);
		
		
		//minimum qty
		driver.findElement(By.xpath(OR.getProperty("MinQty"))).sendKeys(MinimumQuantity);
		
		
		//selling price
		driver.findElement(By.xpath(OR.getProperty("SellingPrice"))).sendKeys(SellingPrice);
		
		
		//average buying price
		driver.findElement(By.xpath(OR.getProperty("AvgBuyingPrice"))).sendKeys(BuyingPrice);
		
		
		//list price
		driver.findElement(By.xpath(OR.getProperty("ListPrice"))).sendKeys(ListPrice);
		
		
		//discount
		driver.findElement(By.xpath(OR.getProperty("Discount"))).sendKeys(Discount);
		
		
		
		//save
		driver.findElement(By.xpath(OR.getProperty("Save"))).click();
		
		
	
		
		//related link
		List<WebElement> multi = driver.findElements(By.xpath(OR.getProperty("RelatedLink")));
		int size = multi.size();
		for (int x = 1; x <= size; x++) 
		{

			if (driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]")).isDisplayed()) 
			{
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]")).click();
				
			}
		}
		
		
		//scroll down
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)", "");
		
		
		
		
		//vendor product
		//driver.findElement(By.xpath(OR.getProperty("VendorProd"))).click();
		
		driver.findElement(By.xpath("//span[text()='Product Prices']//following::span[text()='Vendor Products']")).click();
		
		driver.findElement(By.xpath("//*[@class='slds-page-header__title listViewTitle slds-truncate']//following::a[@title='New']")).click();
		
		Thread.sleep(4000);
		
		//vendor name
		WebElement vn = driver.findElement(By.xpath(OR.getProperty("VendorName")));
		Thread.sleep(5000);
		
		Actions act = new Actions(driver);
		act.click(vn);
		act.sendKeys(VENDOR);
		Thread.sleep(2000);
		act.build().perform();				
		Thread.sleep(8000l);
		driver.findElement(By.xpath("//div[@class=\"listContent\"]")).click();
		
		
	
		//Is Preferred Vendor
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='uiInput uiInputCheckbox uiInput--default uiInput--checkbox']//following::input[@type='checkbox']")).click();
		
		
		
		//buying price
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("BuyingPrice"))).sendKeys(BuyingPrice);
		Thread.sleep(4000);
		
		//save
		//driver.findElement(By.xpath(OR.getProperty("SaveVendor"))).click();
		driver.findElement(By.xpath("//button[3]//span[.='Save']")).click();
		Thread.sleep(8000l);
		
		System.out.println("Product Created");
		
		
}
		
	

	//-----------------------------Purchase Order----------------------------------------------------------
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=3)
	
	public static void purchaseorder(String vendor, String OrderDate, String ExpectedDate, String product, String Quantity,
			String ExpectedDeliveryDate , String Test) throws InterruptedException
	{
	
	
		//driver.manage().window().maximize();
		
				
		//app launcher
		//driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		//Thread.sleep(8000);
				
		//click on SigmaERP
		//driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		//Thread.sleep(9000);
		
		//purchase order tab
		driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab"))).click();
		Thread.sleep(5000);
		
		//verification of purchase order tab
		
		String Verify_Purchase_Order_Module = driver.getPageSource();
		
		String Page_Contains = "Stock Receiving";
		
		Assert.assertTrue(Verify_Purchase_Order_Module.contains(Page_Contains),"Purchase Order Module is not diplayed");
		
		//Purchase order option
		
		driver.findElement(By.xpath("//img[@title='Purchase Orders'][@name='Purchase']")).click();
		Thread.sleep(5000);
		
		//new purchase order
		
		driver.findElement(By.xpath("//button[text()='New']")).click();
		
		//driver.findElement(By.xpath(OR.getProperty("PONewButton"))).click();
		Thread.sleep(5000);
		
	
		
		//frame
		/*
		WebElement frame = driver.findElement(By.xpath(OR.getProperty("iframe")));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		Thread.sleep(6000);
		//select[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input select uiInput uiInputSelect uiInput--default uiInput--select']//preceding::input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'][1]
		 * 
		*/
		
		
		
		//vendor
		
		
		//WebElement v = driver.findElement(By.xpath(OR.getProperty("POVendor")));
		//WebElement v = driver.findElement(By.xpath(OR.getProperty("select[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input select uiInput uiInputSelect uiInput--default uiInput--select']"
				//+ "//preceding::input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'][1]")));
		
		//driver.findElement(By.xpath("//select[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input select uiInput uiInputSelect uiInput--default uiInput--select']//preceding::input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'][1]")).click();
		
		
		WebElement v= driver.findElement(By.xpath("//select[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input select uiInput uiInputSelect uiInput--default uiInput--select']//preceding::input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'][1]"));
	
		Actions act = new Actions(driver);
		act.click(v);
		act.sendKeys(vendor);
		Thread.sleep(5000);
		act.build().perform();				
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@class='slds-media__body']")).click();
		
		//order date
		//driver.findElement(By.xpath(OR.getProperty("OrderDate"))).sendKeys(OrderDate);
		driver.findElement(By.xpath(OR.getProperty("ExpectedDate"))).sendKeys(OrderDate);
		
		
		//Expected date
		//driver.findElement(By.xpath(OR.getProperty("ExpectedDate"))).sendKeys(ExpectedDate);
		driver.findElement(By.xpath(OR.getProperty("OrderDate"))).sendKeys(ExpectedDate);
		
		
		
		//add po
		driver.findElement(By.xpath(OR.getProperty("AddPO"))).click();
		
		
		//po product
		WebElement p = driver.findElement(By.xpath(OR.getProperty("POProduct")));
		
		
		Actions act1 = new Actions(driver);
		act1.click(p);
		act1.sendKeys(product);
		Thread.sleep(2000);
		act1.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[2]")).click();
		
		
		
		//quantity
		driver.findElement(By.xpath(OR.getProperty("POQuantity"))).sendKeys(Quantity);
		Thread.sleep(5000);
		
		//expexted delivery date
		driver.findElement(By.xpath(OR.getProperty("ExpectedDelivery"))).sendKeys(ExpectedDeliveryDate);
		Thread.sleep(5000);

		//submit
		driver.findElement(By.xpath(OR.getProperty("Submit"))).click();
		Thread.sleep(5000);
		
		//save
		driver.findElement(By.xpath(OR.getProperty("FinalSave"))).click();
		Thread.sleep(5000);
		
		

	}
		
		/*
		// close alert
		
		//HARSHU
		/*
		driver.findElement(By.xpath("(//button[@title=\"Close\"])[1]")).click();
		Thread.sleep(5000);
		
		
		}
		
/* 
 * Harshu - Approval is not required
 * 
 */
		
		/*
		 
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
		driver.findElement(By.xpath("//ul/li[@title=\"Related\"]")).click();
		Thread.sleep(5000);
		driver.navigate().refresh();
		driver.findElement(By.xpath("//ul/li[@title=\"Related\"]")).click();
		//SCROLL BUTTON
		
		
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
		Thread.sleep(5000);
		WebElement web = driver.findElement(By.xpath("(//div[@class='actionsContainer']/following::ul)[1]/li[1]/descendant::div[text()='Approve']"));
		Thread.sleep(5000);
		String but = web.getText();
		Actions acti = new Actions(driver);
		acti.moveToElement(web).click().perform();
		System.out.println("but>>>>"+but);
		//web.click();
		//driver.findElement(By.xpath("//a/div[text()='Approve']")).click();
		
		//driver.findElement(By.xpath(OR.getProperty("ApproveButton"))).click();

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
				
		WebElement status = driver.findElement(By.xpath(OR.getProperty("Status")));
		status.click();
		
		Select options = new Select(status);
		options.selectByVisibleText("Submitted");
		Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty("POStatus"))));
		dropdown.selectByVisibleText("Submitted");
		Thread.sleep(8000);

		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(frame);
		
		System.out.println("Soundarya frame ");
		
		//driver.findElement(By.xpath(OR.getProperty("UpdateButton")));
		
		WebElement updateButton = driver.findElement(By.xpath("//button[text()='Update']"));
		String ub = updateButton.getText();
		System.out.println("updateButton>>>"+ub);
		Actions acti1 = new Actions(driver);
		acti1.moveToElement(updateButton).click().perform();
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
*/
	
		
	
	
	//--------------------------------------Stock Receiving-----------------------------------------------
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=5)
	
	public static void stockreceive(String Vendor, String DeliveryPerson, String ReceivingLocation, String ReceivedDate,
			String PurchaseOrder, String POProduct, String ReceivedQuantity, String ActualLocation, String Status) throws InterruptedException, Exception
	{
		
		
		
		//InterruptedException
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Thread.sleep(2000);
		//driver.manage().window().maximize();
		
		Thread.sleep(8000);
		
		//app launcher
		//driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		//Thread.sleep(8000);
						
		//click on SigmaERP
		//driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		//Thread.sleep(8000);
		
		// Click on Purchase Order Module
		
		
		driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab"))).click();
		
		
		
		driver.findElement(By.xpath("//img[@title='Purchase Orders'][@name='Purchase']")).click();
		
		
		// NEW VALUE = //div[@class='slds-truncate' and text()='1']//following::a[1]
		
		//WebElement Last_Updated_PO =driver.findElement(By.xpath("//div[@class='slds-truncate' and text()='1']//following::a[1]"));
		
		//WebElement new_po =driver.findElement(By.xpath("//div[@class='slds-truncate' and text()='1']//following::a[1]"));
		
		
		String New_PO_TEXT=driver.findElement(By.xpath("//tr[1]//th[1]//span[1]//div[1]//a[1]")).getText();
		
		//String output=driver.findElement(By.xpath("//div[@class='slds-truncate' and @data-aura-rendered-by=\"5:1738;a\"]")).getText();
		
		//System.out.println("NEW PO "+output);
		/*
		List<WebElement> myElements = driver.findElements(By.xpath("//article[contains(@class,'slds-card sigmaerpdevPurachaseOrderModules')]//div[contains(@class,'slds-card__body')]"));
		
		int count = 0;

		System.out.println("index 1:" + myElements.get(1).getText());
		
		String tempTD = myElements.get(0).getText();
		System.out.println("tempTD::" + tempTD);
		String lines[] = tempTD.split("\\r?\\n");

		excel.setCellData("stockreceive", "PurchaseOrder", 2, lines[1]);
		*/
		
		System.out.println("value is "+New_PO_TEXT);
		excel.setCellData("stockreceive", "PurchaseOrder", 2, New_PO_TEXT);
		
	
	
		//stock receive tab
		//driver.findElement(By.xpath(OR.getProperty("StockReceivetab"))).click();
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//img[@title='Stock Receiving']")).click();
		Thread.sleep(5000);
		
				
		//New stock receive
		
		//driver.findElement(By.xpath(OR.getProperty("SRNewButton"))).click();
		//Thread.sleep(8000);
		
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebElement New_Button = driver.findElement(By.xpath("(//button[text()='New'])[1]"));
		
		wait.until(ExpectedConditions.elementToBeClickable(New_Button));
		
		New_Button.click();
		
		
		
		//switching to frame
		//WebElement f = driver.findElement(By.xpath(OR.getProperty("SRFrame")));
		//driver.switchTo().frame(f);
		
		
		
		System.out.println(" switching  to frame");
		Thread.sleep(6000);
		
		
		//vendor
		//WebElement v = driver.findElement(By.xpath(OR.getProperty("SRVendor")));
		WebElement v =driver.findElement(By.xpath("//label[contains(text(),'Contact')]//preceding::input[@class='slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input']"));
		Thread.sleep(5000);
		
		Actions act1 = new Actions(driver);
		act1.click(v);
		act1.sendKeys(Vendor);
		Thread.sleep(5000);
		act1.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-media__body']")).click();
		Thread.sleep(2000);
		
		
		if(!DeliveryPerson.isEmpty()) {
			
			System.out.println("Inside If");
			//delivery person
			WebElement d = driver.findElement(By.xpath(OR.getProperty("SRDeliveryPerson")));
			Thread.sleep(5000);
			
			Actions act2 = new Actions(driver);
			act2.click(d);
			act2.sendKeys(DeliveryPerson);
			Thread.sleep(2000);
			act2.build().perform();				
			Thread.sleep(8000);
			driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[2]")).click();
			}
			
		
		
		//receiving location
		//WebElement r = driver.findElement(By.xpath(OR.getProperty("SRReceivingLocation")));
		//WebElement r = driver.findElement(By.xpath("//span[text()='Location']/following::input[1]"));
		WebElement r = driver.findElement(By.xpath("//label[contains(text(),'Location')]//following::input[@class='slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input']"));
		
		
		
		//Thread.sleep(5000);
		
		Actions act3 = new Actions(driver);
		act3.click(r);
		System.out.println("Clicked");
		act3.sendKeys(ReceivingLocation);
		Thread.sleep(2000);
		act3.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[@class='slds-listbox__option-text slds-listbox__option-text_entity']")).click();
		Thread.sleep(6000);	
		
		
		
		//Receive date
		//driver.findElement(By.xpath(OR.getProperty("SRReceivedDate"))).sendKeys(ReceivedDate);
		driver.findElement(By.xpath("//label[text()='Date']/following::input[1]")).sendKeys(ReceivedDate);
		
		
		//add products
		//driver.findElement(By.xpath(OR.getProperty("SRAddProd"))).click();
		driver.findElement(By.xpath("//button[text()='Add PO']")).click();	
		
		//Scroll down
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("window.scrollBy(0,1000)");
		
		
		
		
		
		
		//purchase order
		//WebElement po = driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder")));
	
		//String SRPO = "//button[@class='slds-button slds-button_neutral slds-truncate ButtonTextPadding']//following::input[@class='slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input']";
		
		WebElement po = driver.findElement(By.xpath("//button[@class='slds-button slds-button_neutral slds-truncate ButtonTextPadding']//following::input[@class='slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input']"));
		
		Actions action = new Actions(driver);
		//
		//System.out.println("PO Clicked");
		//actions.moveToElement(po);
		action.click(po);
		System.out.println("PO Clicked");
		action.sendKeys(PurchaseOrder);
		Thread.sleep(9000);
		action.build().perform();
		Thread.sleep(9000);
		driver.findElement(By.xpath("//span[@class='slds-media slds-listbox__option slds-listbox__option_entity slds-listbox__option_has-meta']")).click();
		
		
		/*
		//List<WebElement> list = driver.findElements(By.xpath("(//ul[@class='slds-lookup__list'])[4]/li"));
		//System.out.println("list :: "+list);
		//for (WebElement w : list) {
			//if (w.getText().contains(PO_Number)) 
			//{
				//Thread.sleep(5000);
				//w.click();
			//}
		//}
		
		
		//driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).click();
		//Thread.sleep(5000);
		//driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).sendKeys(PurchaseOrder);
		//Thread.sleep(5000);
		//driver.findElement(By.xpath(OR.getProperty("SRPurchaseOrder"))).click();
		
			
		/*
		
		//PO product
		WebElement w1 = driver.findElement(By.xpath(OR.getProperty("SRPOProduct")));
		Thread.sleep(5000);
		
		Actions act4 = new Actions(driver);
		act4.click(w1);
		act4.sendKeys(POProduct);
		Thread.sleep(2000);
		act4.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//div[@class='slds-lookup__menu slds-p-around_x-small slds-show']//ul[@class='slds-lookup__list']/li[1]")).click();
		Thread.sleep(6000);	
		*/
		
		
		//receive qty
		Thread.sleep(8000);
		//driver.findElement(By.xpath(OR.getProperty("SRReceivedQty"))).sendKeys(ReceivedQuantity);
		driver.findElement(By.xpath("//input[@name=\"number\"]")).clear();
		driver.findElement(By.xpath("//input[@name=\"number\"]")).sendKeys(ReceivedQuantity);		
		Thread.sleep(5000);
		
		//status
		//Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty("SRSelect"))));
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='slds-select']")));
		dropdown.selectByVisibleText("Verified");
		Thread.sleep(15000);
		//driver.findElement(By.xpath("//div[@class='uiPopupTrigger']")).click();
		//List<WebElement> list1=driver.findElements(By.xpath("//ul/li/a"));
		//for (WebElement w3 : list1) {
			//if (w3.getText().contains("Verified")) 
			//{
				//Thread.sleep(5000l);
				//w3.click();
			//}
		//}
		
		/*
		
		WebElement alert = driver.findElement(By.xpath("//h2[contains(text(),'Sorry to interrupt')]"));
		
		if(alert.isDisplayed())
		{
			driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--neutral uiButton forceActionButton']")).click();
		}
		*/
		
		//bin location
		//WebElement w2 = driver.findElement(By.xpath(OR.getProperty("SRActualLocation")));
		//WebElement w2 = driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"]"));
		WebElement w2 = driver.findElement(By.xpath("(//select[@class='slds-select']//following::input[@class='slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input'])[1]"));
		Thread.sleep(5000);
		
		Actions act5 = new Actions(driver);
		act5.click(w2);
		act5.sendKeys(ActualLocation);
		Thread.sleep(2000);
		act5.build().perform();				
		Thread.sleep(8000);
		driver.findElement(By.xpath("//ul[@role='listbox']/child::li[1]")).click();
		Thread.sleep(6000);	
		
	
		//submit
		//driver.findElement(By.xpath(OR.getProperty("SRSubmit"))).click();
		
		
		//save
		//driver.findElement(By.xpath(OR.getProperty("SRSave"))).click();		
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(5000);
		
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		
		System.out.println("Stock Receiving successful");
	

		

	}
	
	
	
	//--------sigma order--------- 
	
	
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=5)
	public static void sigmaorder(String CustomerName, String Billingperson,String Currency) throws InterruptedException
	{
		
		
		
		//app launcher
		//driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
		//Thread.sleep(8000);
						
		//click on SigmaERP
		
		//driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
		
		//driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).sendKeys("SigmaERP");
		//driver.findElement(By.xpath("//input[@class='slds-input' and @placeholder=\"Search apps and items...\"]")).click();
		//Thread.sleep(8000);
		
	
		
		//a[@title='Purchase Order Modules']
		//sigma order
		//driver.findElement(By.xpath(OR.getProperty("SigmaOrdertab"))).click();
		
		
		
		// Sales order Module 
		 
		
		WebElement element = driver.findElement(By.xpath("//a[@title='Sales Order Modules']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
		
		
		/*
		//switching to frame
		WebElement fr = driver.findElement(By.xpath(OR.getProperty("SOframe")));
		driver.switchTo().frame(fr);
		*/
		
		
		System.out.println(" switching  to frame");
		
		
		//sales order
		//driver.findElement(By.xpath(OR.getProperty("SalesOrder"))).click();
		//Thread.sleep(5000);
	
		// Sales Order
		driver.findElement(By.xpath("//img[@name='SigmaOrder']")).click();
		Thread.sleep(5000);
		
		// New Button
		driver.findElement(By.xpath("//button[@name='Sales Order']")).click();
		
		
		 
		//customer name
		WebElement c = driver.findElement(By.xpath(OR.getProperty("Customername")));
		Thread.sleep(5000);
		Actions a1 = new Actions(driver);
		a1.click(c);
		a1.sendKeys(CustomerName);
		Thread.sleep(5000);
		a1.build().perform();				
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[1]")).click();
		
		Thread.sleep(5000);
		
		
	
		//billing person
		if(!Billingperson.isEmpty()) {
			WebElement b = driver.findElement(By.xpath(OR.getProperty("BillingPerson")));
			Thread.sleep(5000);
			
			Actions a2 = new Actions(driver);
			a2.click(b);
			
			a2.sendKeys(Billingperson);
		
			a2.build().perform();				
			
			driver.findElement(By.xpath("(//span[@class='slds-media__body'])[2]")).click();
			
		}
		
		
		//currency
		WebElement c1 = driver.findElement(By.xpath(OR.getProperty("Currency")));
		//c1.click();
		//c1.clear();
		Thread.sleep(5000);
		
		Actions a4 = new Actions(driver);
		a4.click(c1);
		Thread.sleep(6000);
		a4.sendKeys(Currency);
		Thread.sleep(8000);
		a4.build().perform();				
		Thread.sleep(5000);
		//driver.findElement(By.xpath("(//span[@class='slds-media__body'])[3]")).click();
		
		
	}	

	
	public static int firstIndex = 4;
	public static int secondIndex = 1;
	
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=6)
	public static void sigmaorderline(String Product, String Quantity,String Discount) throws InterruptedException
	{
		int Rowcount = Testbase.row;
		
		System.out.println("Rowcount=="+Rowcount);
		int NumberofOrderLines= Rowcount-1;
		System.out.println("NumberofProducts=="+NumberofOrderLines);
		
		//add product
		driver.findElement(By.xpath(OR.getProperty("SOAddproduct"))).click();
		Thread.sleep(5000);
		
		
		
		//product
		WebElement pr = driver.findElement(By.xpath(("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon--right'])["+firstIndex+"]//input")));
		Thread.sleep(5000);
		
		Actions a3 = new Actions(driver);
		a3.click(pr);
		a3.sendKeys(Product);
		Thread.sleep(4000);
		a3.build().perform();				
		Thread.sleep(8000);
		//driver.findElement(By.xpath("(//span[@class='slds-media__body'])["+firstIndex+"]")).click();
		driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[2]")).click();
		
		
		//quantity
		//driver.findElement(By.xpath("(//td[@data-label='Quantity']/input)["+secondIndex+"]")).clear();
		//driver.findElement(By.xpath("(//td[@data-label='Quantity']/input)["+secondIndex+"]")).sendKeys(Quantity);
		
		driver.findElement(By.xpath("(//td[@data-label='Quantity']/input)[1]")).clear();
		driver.findElement(By.xpath("(//td[@data-label='Quantity']/input)[1]")).sendKeys(Quantity);
		
		Thread.sleep(5000);
	
		
		//discount		
		System.out.println("Discount=="+Discount);
		if(Discount.toString().isEmpty()) 
		{
			
		}
		
		
		if(!Discount.toString().isEmpty()) 
		{
			if(Discount.toString().equals("0"))
			{
				System.out.println("Discountis zero=="+Discount);
			}
			else 
			{
				System.out.println("Discountis not zero=="+Discount);
			}
			
		}
		else 
		{
			driver.findElement(By.xpath("(//td[@data-label='DISCOUNT']//div[@class='slds-form-element__control slds-grow']/input)["+secondIndex+"]")).sendKeys(Discount);
			//Discount = "0";
		}
		System.out.println("Final Discountis=="+Discount);
				
		
		//add to orders
		//driver.findElement(By.xpath(OR.getProperty("SOAddtoorders"))).click();
		
		
		//next
		//driver.findElement(By.xpath(OR.getProperty("SONext"))).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		/*
		
		Alert aa1 = driver.switchTo().alert();
		aa1.accept();
		
		//driver.findElement(By.xpath("//button[@class='slds-button']"));
		Thread.sleep(8000);
		
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		
		*/
		
		
		
		
		driver.findElement(By.xpath("//div[@class='slds-truncate' and @title='Sales Order Name']")).click();
				
		firstIndex = firstIndex + 1;
		secondIndex = secondIndex + 1;
		
		/*
	}
	
	@Test(priority=7)
	public static void sigmaordersave() throws InterruptedException
	
	{
		
	
	//save 
		//driver.findElement(By.xpath(OR.getProperty("SOSave"))).click();
		//Thread.sleep(5000);
		
		
		//Alert aa = driver.switchTo().alert();
		//aa.accept();
		
		Thread.sleep(10000);
		driver.switchTo().defaultContent();
		//Thread.sleep(5000);
				
		System.out.println("sales order successful");
	
		
	
		//String output=driver.findElement(By.xpath("//div[@class='slds-truncate' and text()='1']//following::div[1]")).getText();
		String output=driver.findElement(By.xpath("//div[@class='slds-truncate' and @data-aura-rendered-by=\"5:1738;a\"]")).getText();
			
		System.out.println("new updated is available" + output);
		
		//driver.findElement(By.xpath("//*[contains(text(),'SO-0103')]")).click();
		
		String value = "//*[contains(text(),'";
		String value1 = output;
		String Value2 = "')]";
		
		String total = value+value1+Value2;
		
		driver.findElement(By.xpath(total)).click();
		
		
		//proceed payment
		WebElement Proceed_to_pay= driver.findElement(By.xpath("//button[@class='slds-button slds-button_neutral' and text()='Proceed to Payment']"));
				
		WebDriverWait paywait= new WebDriverWait(driver, 10);
		paywait.until(ExpectedConditions.elementToBeClickable(Proceed_to_pay)).click();
		
		
		
		//cash
		//driver.findElement(By.xpath("(//label[@class='slds-size--1-of-2'])[1]/img")).click();
		driver.findElement(By.xpath("//p[contains(text(),'Cash')]")).click();
		
		
		 /*
		WebElement wb =  driver.findElement(By.xpath("(//input[@class='slds-input input uiInput uiInputText uiInput--default uiInput--input'])[1]"));
		String st = wb.getText();
		System.out.println("Total Amount "+st);
		Thread.sleep(5000);
		WebElement wb1 =  driver.findElement(By.xpath("(//label[@class=\"slds-size--2-of-5\"])[4]"));
		wb1.click();
		Thread.sleep(5000);
		wb1.sendKeys(st);
		//skip payment
		//driver.findElement(By.xpath(OR.getProperty("SkipPayment"))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()=\"Save Payment\"]")).click();
		
		Alert aa2 = driver.switchTo().alert();
		aa2.accept();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Cancel']")).click();
		
		
	


	/*
	public static String Scroll() throws InterruptedException
	{
		// Scroll upto the Element Found
		WebElement ele = driver.findElement(By.xpath("//button[text()='Add Order Lines']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(500);
        return "Success";
	}
	
	
	//---------------------------Pick---------------------------------------
	
		//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 8)
		public void picking(String SigmaOrder,String User, String PickedQty) throws InterruptedException 
		{

			driver.manage().window().maximize();
			Thread.sleep(4000);
	 		
			driver.findElement(By.xpath("//nav[@class='appLauncher slds-context-bar__icon-action']//button[1]")).click();
			Thread.sleep(6000);
		
			
			driver.findElement(By.xpath("//div[@class='modal-header slds-modal__header']/descendant::div[@class='uiInput uiInputText uiInput--default uiInput--input']/input" )).sendKeys("Pick"); 
			Thread.sleep(3000);
			
			Click("(//span/mark[.='Pick'])[1]" ); 
			Thread.sleep(10000);

			//enter so no
			WebElement c = driver.findElement(By.xpath(OR.getProperty("SigmaOrder")));
			Thread.sleep(5000);

			Actions a1 = new Actions(driver);
			a1.click(c);
			a1.sendKeys(SigmaOrder);
			Thread.sleep(2000);
			a1.build().perform();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//span[@class='slds-media__body']")).click();
			Thread.sleep(3000);
			
			//select so
			driver.findElement(By.xpath(OR.getProperty("SelectSO"))).click();
			Thread.sleep(3000);
			
			//allocate user
			Select dd1 = new Select(driver.findElement(By.xpath(OR.getProperty("AllocateUser"))));
			dd1.selectByVisibleText(User);
			Thread.sleep(8000);
		
			//allocate
			driver.findElement(By.xpath(OR.getProperty("Allocate"))).click();
			Thread.sleep(3000);
			
			//submit alert
			Alert a = driver.switchTo().alert();
			a.accept();
			
			//enter so no
			WebElement c1 = driver.findElement(By.xpath(OR.getProperty("SigmaOrder")));
			Thread.sleep(5000);

			Actions a2 = new Actions(driver);
			a2.click(c1);
			a2.sendKeys(SigmaOrder);
			Thread.sleep(2000);
			a2.build().perform();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//span[@class='slds-media__body']")).click();
			Thread.sleep(3000);
			
			//select so
			driver.findElement(By.xpath(OR.getProperty("SelectSO"))).click();
			Thread.sleep(3000);
			
			//click on pick
			driver.findElement(By.xpath(OR.getProperty("Pick"))).click();
			Thread.sleep(3000);
			
			//start picking
			driver.findElement(By.xpath(OR.getProperty("StartPicking"))).click();
			Thread.sleep(3000);

			//input picked qty
			driver.findElement(By.xpath(OR.getProperty("PickedQty"))).sendKeys(PickedQty);
			Thread.sleep(3000);
			
			//submit
			driver.findElement(By.xpath(OR.getProperty("PickSubmit"))).click();
			Thread.sleep(6000);
			
		}
	
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 9)
	@Test
	public void Package() throws InterruptedException 
	{
	
		//app launcher
		driver.findElement(By.xpath("//nav[@class='appLauncher slds-context-bar__icon-action']//button[1]")).click();
		Thread.sleep(6000);
		
		//select packages tab
		driver.findElement(By.xpath("//div[@class='modal-header slds-modal__header']/descendant::div[@class='uiInput uiInputText uiInput--default uiInput--input']/input" )).sendKeys("Packages"); 
		Thread.sleep(3000);
		
		Click("//span/mark[.='Packages']" ); 
		Thread.sleep(10000);
	
		//click on new
		driver.findElement(By.xpath(OR.getProperty("PackageNew"))).click();
		Thread.sleep(6000);
		
		//select customer
		WebElement c = driver.findElement(By.xpath(OR.getProperty("PackageCustomer")));
		Thread.sleep(5000);

		Actions act = new Actions(driver);
		act.click(c);
		act.sendKeys("Goldfinch Paving");

		Thread.sleep(2000);
		act.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[1]")).click();
		Thread.sleep(8000);
		//select so
		WebElement s = driver.findElement(By.xpath("//div[@class='slds-show']//input"));
		Thread.sleep(5000);

		Actions so = new Actions(driver);
		so.click(s);
		so.sendKeys("SO-3305");
		
		Thread.sleep(2000);
		so.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//li[@class='slds-listbox__item'])[1]")).click();
		driver.findElement(By.xpath("//div[@class='slds-show']//input")).sendKeys("SO-3305");
		Thread.sleep(5000);
		
			driver.findElement(By.xpath("//input[@id='input-230']")).clear();
		driver.findElement(By.xpath("//input[@id='input-230']")).sendKeys("2");
		Thread.sleep(10000);
		
		//save
		//driver.findElement(By.xpath(OR.getProperty("PackageSave"))).click();
		Thread.sleep(10000);
	
	}
	
	//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 10)
	public void shipment(String LogisticsName,String DeliveryNote,String TruckNo,String Waybill,String DeliveryPerson, String ShipmentDate,String Status1,
			String ConsignmentNo,String TrackingUrl, String PackageNumber,String ShipmentDeliveryDate,String Status2) throws InterruptedException
			{
			
			//app launcher
			driver.findElement(By.xpath("//nav[@class='appLauncher slds-context-bar__icon-action']//button[1]")).click();
			Thread.sleep(6000);

			//select packages tab
			driver.findElement(By.xpath("//div[@class='modal-header slds-modal__header']/descendant::div[@class='uiInput uiInputText uiInput--default uiInput--input']/input" )).sendKeys("Shipments");
			Thread.sleep(3000);

			Click("//span/mark[.='Shipments']" );
			Thread.sleep(10000);

			//new
			driver.findElement(By.xpath(OR.getProperty("ShipmentNew"))).click();
			Thread.sleep(6000);

			//switch to frame
			WebElement sf = driver.findElement(By.xpath(OR.getProperty("ShipmentFrame")));
			driver.switchTo().frame(sf);
			System.out.println("switching  to frame");
			Thread.sleep(6000);

			//select logistics
			Select dd2 = new Select(driver.findElement(By.xpath(OR.getProperty("Logisticsname"))));
			dd2.selectByVisibleText(LogisticsName);
			Thread.sleep(4000);

			//delivery note
			driver.findElement(By.xpath(OR.getProperty("DeliveryNote"))).sendKeys(DeliveryNote);
			Thread.sleep(3000);

			//truck number
			driver.findElement(By.xpath(OR.getProperty("Truck"))).sendKeys(TruckNo);
			Thread.sleep(3000);

			//waybill
			driver.findElement(By.xpath(OR.getProperty("Waybill"))).sendKeys(Waybill);
			Thread.sleep(3000);

			//delivery person
			driver.findElement(By.xpath(OR.getProperty("DeliveryPerson"))).sendKeys(DeliveryPerson);
			Thread.sleep(3000);

			//shipment date
			driver.findElement(By.xpath(OR.getProperty("ShipmentDate"))).sendKeys(ShipmentDate);
			Thread.sleep(5000);

			//header status
			Select dd3 = new Select(driver.findElement(By.xpath(OR.getProperty("Status1"))));
			dd3.selectByVisibleText(Status1);
			Thread.sleep(4000);

			//consignment number
			driver.findElement(By.xpath(OR.getProperty("ConsignmentNo"))).sendKeys(ConsignmentNo);
			Thread.sleep(3000);
			//tracking url
			driver.findElement(By.xpath(OR.getProperty("TrackingUrl"))).sendKeys(TrackingUrl);
			Thread.sleep(5000);

			//add shipment products
			driver.findElement(By.xpath(OR.getProperty("AddShipmentProd"))).click();
			Thread.sleep(8000);

			//package number
			WebElement pn = driver.findElement(By.xpath(OR.getProperty("PackageNumber")));
			Thread.sleep(5000);

			Actions action2 = new Actions(driver);
			action2.moveToElement(pn);
			action2.click();
			action2.sendKeys(PackageNumber);
			action2.build().perform();

			Thread.sleep(9000);
			List<WebElement> list = driver.findElements(By.xpath("//ul[@class='slds-lookup__list']/li"));
			//System.out.println("list :: "+list);
			for (WebElement w : list) {
			if (w.getText().contains(PackageNumber))
			{
			Thread.sleep(5000l);
			w.click();
			}
			}

			//shipment delivery date
			driver.findElement(By.xpath(OR.getProperty("ShipmentDeliveryDate"))).sendKeys(ShipmentDeliveryDate);
			Thread.sleep(5000);

			//shipment status
			Select dd4 = new Select(driver.findElement(By.xpath(OR.getProperty("Status2"))));
			dd4.selectByVisibleText(Status2);
			Thread.sleep(8000);

			//click on submit
			driver.findElement(By.xpath(OR.getProperty("ShipmentSubmit"))).click();
			Thread.sleep(8000);

			//final submit
			driver.findElement(By.xpath(OR.getProperty("LastSubmit"))).click();
			Thread.sleep(8000);

			//switch back
			driver.switchTo().defaultContent();
			Thread.sleep(8000);

			System.out.println("Shipment succesful");
			
			*/
			 
	}

	
}
