/* 
Author = Shridhar 
Flow = Sigma Outbound
*/
package com.SigmaInbound;

import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sigmaoutbound extends Testbase {
	
	
	
	//-------------------------------------Vendor Account creation--------------------------------------------------------------
	
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
	//WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccActiveClick")));
	//WebElement we1 = driver.findElement(By.xpath(OR.getProperty("AccVendorActive")));
	//WebElement we1 = driver.findElement(By.xpath("AccVendorActive"));
	WebElement we1 = driver.findElement(By.xpath("(//a[@class='select'])[1]"));
	Actions a = new Actions(driver);
	a.moveToElement(we1);
	a.click();
			
			
			//driver.findElement(By.xpath(OR.getProperty("//div[@class='uiMenu']//following::div[@class='uiPopupTrigger'][2]"))).click();
			//driver.findElement(By.xpath(OR.getProperty("AccVendorActive"))).click();
			//driver.findElement(By.xpath("AccVendorActive")).click();
	driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
	List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
	for (WebElement we : list)
	{
		if (we.getText().contains(Active)){
			Thread.sleep(5000);
			we.click();
			}
	}
	Thread.sleep(5000);
			
			

	
	//Address
	
	//driver.findElement(By.xpath("AccVendorStreet")).sendKeys(ShippingStreet);
	//driver.findElement(By.xpath(OR.getProperty("//span[contains(text(),'Address')]//following::input[@class=' input'][1]"))).click();
	//driver.findElement(By.xpath(OR.getProperty("AccVendorStreet"))).click();
	driver.findElement(By.xpath("//span[contains(text(),'Address')]//following::input[@class=' input'][1]")).sendKeys(ShippingStreet);
	Thread.sleep(4000);
	//City
	
	driver.findElement(By.xpath("//span[contains(text(),'City')]//following::input[@class=' input'][1]")).sendKeys(ShippingCity);
	Thread.sleep(4000);
	//State
	
	driver.findElement(By.xpath("//span[contains(text(),'State')]//following::input[@class=' input'][1]")).sendKeys(ShippingState);
	Thread.sleep(4000);
	//Country
	
	driver.findElement(By.xpath("//span[contains(text(),'Country')]//following::input[@class=' input'][1]")).sendKeys(ShippingCountry);
	Thread.sleep(4000);
	//Zip
	
	driver.findElement(By.xpath("//span[contains(text(),'Zip')]//following::input[@class=' input'][1]")).sendKeys(ShippingZip);
	Thread.sleep(4000);	
	//Phone
	
	driver.findElement(By.xpath("(//span[contains(text(),'Phone')]//following::input[@class=' input'][1])[2]")).sendKeys(AccPhone);
	Thread.sleep(4000);		
	
	
	//Email 
	driver.findElement(By.xpath("//input[@class=' input'][@type='email']")).sendKeys(Email);
	Thread.sleep(4000);
	
	//driver.findElement(By.xpath(OR.getProperty("AccEmail"))).click();
	
	
	
	//save
	//driver.findElement(By.xpath(OR.getProperty("AccSave"))).click();
	driver.findElement(By.xpath("//button[3]//span[.='Save']")).click();
	Thread.sleep(4000);


	
}

		//---------------------------------------Create Vendor Contact---------------------------------------------------
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
			public static void VendorContact(String Contact, String Salutation, String FirstName, String LastName, String Email, String MailingStreet, String MailingCity, String State,String PostalCode,String Country) throws InterruptedException
			
			{
			
					
				//New Contact creation	
				Thread.sleep(4000);
				WebElement element = driver.findElement((By.xpath("(//div[@title='New' and text()='New'])[3]")));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				Thread.sleep(4000);
		
				//Salution
				driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
				Thread.sleep(4000);
				
				List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
				for (WebElement we : list)
				{
					if (we.getText().contains(Salutation)){
						Thread.sleep(5000);
						we.click();
						}
				}
				Thread.sleep(5000);
				
				//First Name
				driver.findElement(By.xpath("//input[@class='firstName compoundBorderBottom form-element__row input']")).sendKeys(FirstName);
				Thread.sleep(5000);
				
				//Last Name
				driver.findElement(By.xpath("//input[@class='lastName compoundBLRadius compoundBRRadius form-element__row input']")).sendKeys(LastName);
				Thread.sleep(5000);
				
				//Email
				driver.findElement(By.xpath("//input[@class=' input' and @type='email']")).sendKeys(Email);
				Thread.sleep(5000);
				
				//Mailing Street	
				driver.findElement(By.xpath("(//textarea[@class='street compoundTLRadius compoundTRRadius compoundBorderBottom textarea'])[1]")).sendKeys(MailingStreet);
				Thread.sleep(5000);
				
				//MailingCity
				driver.findElement(By.xpath("(//input[@class='city compoundBorderBottom compoundBorderRight input'])[1]")).sendKeys(MailingCity);
				Thread.sleep(5000);
				
				//State
				driver.findElement(By.xpath("(//input[@class='state compoundBorderBottom input'])[1]")).sendKeys(State);
				Thread.sleep(5000);
				
				//PostalCode
				driver.findElement(By.xpath("(//input[@class='postalCode compoundBLRadius compoundBorderRight input'])[1]")).sendKeys(PostalCode);
				Thread.sleep(5000);
				
				
				// Save
				driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--brand uiButton forceActionButton']")).click();
				Thread.sleep(5000);
			
		}
	
	

	


	
//---- Customer Creation -------------
@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 3)
public static void Cashcustomer(String AccountType, String AccountName, String CustomerType, String CurrencyCode, String Email,
		String Address,String City,String State,String Country,String Zip,String Phone,String Fax,
		String ShippingStreet, String ShippingCity, String ShippingState, String ShippingZip,
		String ShippingCountry,String CreditLimit

		) throws InterruptedException
		{
		
	Thread.sleep(6000);
	JavascriptExecutor js = (JavascriptExecutor) driver;


	//click on account tab
	driver.findElement(By.xpath(OR.getProperty("Acctab"))).click();
	Thread.sleep(5000);
			
			
	//click on new button
	driver.findElement(By.xpath(OR.getProperty("AccNew"))).click();
	Thread.sleep(4000);
	
	
	if (AccountType.equals("Customer")) {
		
		driver.findElement(By.xpath(OR.getProperty("AccCustomer"))).click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath(OR.getProperty("AccNext"))).click();
		Thread.sleep(4000);

		driver.findElement(By.xpath(OR.getProperty("AccName"))).sendKeys(AccountName);
		Thread.sleep(4000);
	
		//Cash customer
		driver.findElement(By.xpath("(//span[@class='label inputLabel uiPicklistLabel-left form-element__label uiPicklistLabel']//following::a[contains(text(),'--None--')])[1]")).click();
		Thread.sleep(4000);
					
		List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
		for (WebElement we: list) {
			
			System.out.println("we>>>"+we.getText());
			Thread.sleep(5000);
			
			if (we.getText().contains(CustomerType)) {
				
				driver.findElement(By.xpath("//a[contains(text(),'Cash Customer')]")).click();
				

				}
			
	}
		
			//Currency
			driver.findElement(By.xpath(OR.getProperty("CurrencyCode"))).clear();
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
			
			Thread.sleep(4000);
		
	}
	}
	

	
/*			if(CustomerType.equals("Credit Customer")) {
				
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
				
				}*/
		
			
	
	//---------------------------------------Create Customer Contact---------------------------------------------------
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)
		public static void CustomerContact(String Contact, String Salutation, String FirstName, String LastName, String Email, String MailingStreet, String MailingCity, String State,String PostalCode) throws InterruptedException
		
		{
		
				
			//New Contact creation	
			Thread.sleep(6000);
			WebElement element = driver.findElement((By.xpath("(//div[@title='New' and text()='New'])[4]")));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(4000);
	
			//Salution
			driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
			Thread.sleep(4000);
			
			List<WebElement> list = driver.findElements(By.xpath(OR.getProperty("AccActiveList")));
			for (WebElement we : list)
			{
				if (we.getText().contains(Salutation)){
					Thread.sleep(5000);
					we.click();
					}
			}
			Thread.sleep(5000);
			
			//First Name
			driver.findElement(By.xpath("//input[@class='firstName compoundBorderBottom form-element__row input']")).sendKeys(FirstName);
			Thread.sleep(5000);
			
			//Last Name
			driver.findElement(By.xpath("//input[@class='lastName compoundBLRadius compoundBRRadius form-element__row input']")).sendKeys(LastName);
			Thread.sleep(5000);
			
			//Email
			driver.findElement(By.xpath("//input[@class=' input' and @type='email']")).sendKeys(Email);
			Thread.sleep(5000);
			
			//Mailing Street	
			driver.findElement(By.xpath("(//textarea[@class='street compoundTLRadius compoundTRRadius compoundBorderBottom textarea'])[1]")).sendKeys(MailingStreet);
			Thread.sleep(5000);
			
			//MailingCity
			driver.findElement(By.xpath("(//input[@class='city compoundBorderBottom compoundBorderRight input'])[1]")).sendKeys(MailingCity);
			Thread.sleep(5000);
			
			//State
			driver.findElement(By.xpath("(//input[@class='state compoundBorderBottom input'])[1]")).sendKeys(State);
			Thread.sleep(5000);
			
			//PostalCode
			driver.findElement(By.xpath("(//input[@class='postalCode compoundBLRadius compoundBorderRight input'])[1]")).sendKeys(PostalCode);
			Thread.sleep(5000);
			
			
			// Save
			driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--brand uiButton forceActionButton']")).click();
			Thread.sleep(5000);
		
	}




//---------------------------------------Create Product---------------------------------------------------
@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=5)
public static void product(String PRODUCTTYPE, String ProductName, String AttributeType, String ReorderQuantity, String ThresholdQuantity, String MinimumQuantity,
		String SellingPrice, String ListPrice, String BuyingPrice, String Discount, String VENDOR) throws InterruptedException
{
	
	//product tab
	
	Thread.sleep(5000);
	
	/*
	WebElement element = driver.findElement(By.xpath(OR.getProperty("Prodtab")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);
	
	*/
	
	driver.findElement(By.xpath(OR.getProperty("Prodtab"))).click();
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

@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=6)

public static void purchaseorder(String vendor, String OrderDate, String ExpectedDate, String product, String Quantity,
		String ExpectedDeliveryDate , String Test) throws InterruptedException
{

	
	//purchase order tab
	
	/*
	WebElement element = driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);
	*/
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
	
	driver.findElement(By.xpath("//div[@title='Serial Number']//preceding::button[@class='slds-button slds-button_neutral']")).click();
	Thread.sleep(5000);
	//driver.findElement(By.xpath(OR.getProperty("PONewButton"))).click();
	Thread.sleep(5000);
	
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
	Thread.sleep(5000);
	
	//Expected date
	//driver.findElement(By.xpath(OR.getProperty("ExpectedDate"))).sendKeys(ExpectedDate);
	driver.findElement(By.xpath(OR.getProperty("OrderDate"))).sendKeys(ExpectedDate);
	Thread.sleep(5000);
	
	
	//add po
	driver.findElement(By.xpath(OR.getProperty("AddPO"))).click();
	Thread.sleep(5000);
	
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
	Thread.sleep(10000);
	

	
//	
//	// close alert
//	
//	driver.findElement(By.xpath("(//button[@title=\"Close\"])[1]")).click();
//	Thread.sleep(5000);
//	

	
}


	


//--------------------------------------Stock Receiving-----------------------------------------------

@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority=7)

public static void stockreceive(String Vendor, String DeliveryPerson, String ReceivingLocation, String ReceivedDate,
		String PurchaseOrder, String POProduct, String ReceivedQuantity, String ActualLocation, String Status,String BIN) throws InterruptedException, Exception
{

	//InterruptedException	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	Thread.sleep(8000);
	
	// Click on Purchase Order Module
	Thread.sleep(8000);
	/*
	WebElement element = driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab")));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);	
	*/
	driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab"))).click();
	Thread.sleep(8000);
	
	driver.findElement(By.xpath("//img[@title='Purchase Orders'][@name='Purchase']")).click();
		
	
	String New_PO_TEXT=driver.findElement(By.xpath("//tr[1]//th[1]//span[1]//div[1]//a[1]")).getText();
	
	List<WebElement> myElements = driver.findElements(By.xpath("//article[contains(@class,'slds-card sigmaerpdevPurachaseOrderModules')]//div[contains(@class,'slds-card__body')]"));
	
	int count = 0;

	System.out.println("index 1:" + myElements.get(1).getText());
	
	String tempTD = myElements.get(0).getText();
	System.out.println("tempTD::" + tempTD);
	String lines[] = tempTD.split("\\r?\\n");

	excel.setCellData("stockreceive", "PurchaseOrder", 2, lines[1]);
	
	
	System.out.println("value is "+New_PO_TEXT);
	excel.setCellData("stockreceive", "PurchaseOrder", 2, New_PO_TEXT);
	


	//stock receive tab
	Thread.sleep(5000);
	driver.findElement(By.xpath("//img[@title='Stock Receiving']")).click();
		
	//New stock receive	
	WebElement New_Button = driver.findElement(By.xpath("//div[@class='slds-media slds-no-space slds-grow  slds-m-vertical_xx-small']//following::button[@class='slds-button slds-button_neutral']"));
	
	
	//WebElement element = driver.findElement(By.xpath(OR.getProperty("PurchaseOrdertab")));
	JavascriptExecutor executor1 = (JavascriptExecutor)driver;
	executor1.executeScript("arguments[0].click();", New_Button);	
	Thread.sleep(6000);
	
	
	//vendor
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
	driver.findElement(By.xpath("//label[text()='Date']/following::input[1]")).sendKeys(ReceivedDate);
	
	
	//add products
	driver.findElement(By.xpath("//button[text()='Add PO']")).click();	
	
	//Scroll down
	JavascriptExecutor exe = (JavascriptExecutor)driver;
	exe.executeScript("window.scrollBy(0,1000)");
	
	
	
	//purchase order
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
	
	
	
	
	//receive qty
	Thread.sleep(8000);
	

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,1000)");
	
	
	//driver.findElement(By.xpath(OR.getProperty("SRReceivedQty"))).sendKeys(ReceivedQuantity);
	driver.findElement(By.xpath("//input[@name='number']")).clear();
	driver.findElement(By.xpath("//input[@name='number']")).sendKeys(ReceivedQuantity);		
	Thread.sleep(5000);
	
	//status
	//Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty("SRSelect"))));
	Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='slds-select']")));
	dropdown.selectByVisibleText("Verified");
	Thread.sleep(8000);
	
	
	//bin location
	//WebElement w2 = driver.findElement(By.xpath(OR.getProperty("SRActualLocation")));
	//WebElement w2 = driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"]"));
	WebElement w2 = driver.findElement(By.xpath("//select[@class='slds-select']//following::input[@class='slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input']"));
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
	Thread.sleep(8000);
	
	//save
	//driver.findElement(By.xpath(OR.getProperty("SRSave"))).click();		
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	Thread.sleep(5000);
	
	driver.switchTo().defaultContent();
	Thread.sleep(5000);
	
	System.out.println("Stock Receiving successful");


	

}

//--------sigma order---------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 8)
	public static void sigmaorder(String CustomerName, String Billingperson, String Currency, String OrderDate)
			throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// sigma order
		driver.findElement(By.xpath(OR.getProperty("SigmaOrdertab"))).click();
		Thread.sleep(8000);

		// sales order
		WebElement element = driver.findElement((By.xpath("//img[@name='SigmaOrder']")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		//driver.findElement(By.xpath("//div[text()='Sales Order']")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//button[@name='Sales Order']")).click();
		Thread.sleep(5000);

		// customer name
		WebElement c = driver.findElement(By.xpath(OR.getProperty("Customername")));
		Thread.sleep(5000);

		Actions a1 = new Actions(driver);
		a1.click(c);
		a1.sendKeys(CustomerName);
		Thread.sleep(4000);
		a1.build().perform();
		Thread.sleep(4000);
		driver.findElement(
				By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[1]")).click();

		// billing person

		if (!Billingperson.isEmpty()) {
			WebElement b = driver.findElement(By.xpath(OR.getProperty("BillingPerson")));
			Thread.sleep(5000);
			Actions a2 = new Actions(driver);
			a2.click(b);
			Thread.sleep(6000);
			a2.sendKeys(Billingperson);
			Thread.sleep(8000);
			a2.build().perform();
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//span[@class='slds-media__body'])[2]")).click();
		}

		// Order Date
		if (!OrderDate.isEmpty()) {
			WebElement c1 = driver.findElement(By.xpath(OR.getProperty("OrdrerDate")));
			c1.click();
			c1.clear();
			c1.sendKeys(OrderDate);
			Thread.sleep(3000);
		}
		js.executeScript("window.scrollBy(0,1000)");

		// currency
		Thread.sleep(3000);
		if (!Currency.isEmpty()) {
			WebElement c1 = driver.findElement(By.xpath(OR.getProperty("Currency")));
			c1.click();
			c1.clear();
			Thread.sleep(5000);
			Actions a4 = new Actions(driver);
			a4.click(c1);
			Thread.sleep(6000);
			a4.sendKeys(Currency);
			Thread.sleep(8000);
			a4.build().perform();
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//span[@class='slds-media__body'])[3]")).click();
		}
		Thread.sleep(5000);
	}

	public static int firstIndex = 5;
	public static int secondIndex = 1;
	public static int Entity = 3;
	public static boolean firstclick = true;

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 9)
	public static void sigmaorderline(String Product, String Quantity, String Discount) throws InterruptedException {
		int Rowcount = Testbase.row;

		System.out.println("Rowcount==" + Rowcount);
		int NumberofOrderLines = Rowcount - 2;
		System.out.println("NumberofProducts==" + NumberofOrderLines);

		if (firstclick == true) {
			// add product
			driver.findElement(By.xpath(OR.getProperty("SOAddproduct"))).click();
			Thread.sleep(5000);
			firstclick = false;
		}

		// product
		Thread.sleep(5000);
		WebElement pr = driver.findElement(
				By.xpath(("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon--right'])["
						+ firstIndex + "]//input")));
		Thread.sleep(5000);

		Actions a3 = new Actions(driver);
		a3.click(pr);
		a3.sendKeys(Product);
		Thread.sleep(4000);
		a3.build().perform();
		Thread.sleep(8000);
		// driver.findElement(By.xpath("(//span[@class='slds-media__body'])["+firstIndex+"]")).click();
		driver.findElement(By
				.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[" + Entity + "]"))
				.click();

		// quantity
		driver.findElement(By.xpath("(//td[@data-label='Quantity']/input)[" + secondIndex + "]")).clear();
		driver.findElement(By.xpath("(//td[@data-label='Quantity']/input)[" + secondIndex + "]")).sendKeys(Quantity);
		Thread.sleep(5000);

		// discount
		System.out.println("Discount==" + Discount);
		if (!Discount.isEmpty()) {
			WebElement dis = driver.findElement(
					By.xpath("(//td[@data-label='DISCOUNT']//div[@class='slds-form-element__control slds-grow']/input)["
							+ secondIndex + "]"));
			dis.click();
			dis.clear();
			dis.sendKeys(Discount);

		} else {

			System.out.println("Discountis zero==" + Discount);
		}
		
		Thread.sleep(8000);
		if (secondIndex < NumberofOrderLines) {
			AddButton();
		}

	}

	public static void AddButton() throws InterruptedException {

		System.out.println("Add Order Lines");
		driver.findElement(By.xpath("//button[text()='Add Order Lines']")).click();
		firstIndex = firstIndex + 1;
		secondIndex = secondIndex + 1;
		Entity = Entity + 1;
	}

	@Test(dataProviderClass = Testbase.class,priority = 10)
	public static void sigmaordersave() throws InterruptedException {

		// Save
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//button[text()='Save'])[2]")).click();
		Thread.sleep(5000);
		 
		 //excel.setCellData("Payment", "Amount", 2, total);

	}
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 11)
	public static void Payment(String paymentvia, String CardHolderName, String CardNumber, String CVV, String ExpiryDate, String ChequeNumber, String BankName, String Date) throws InterruptedException {
		Thread.sleep(5000);
		// sigma order
		//driver.findElement(By.xpath(OR.getProperty("SigmaOrdertab"))).click();
		Thread.sleep(5000);

		//driver.findElement(By.xpath("(//table/tr/td[@class='zoom'])[1]")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("(//div[@title=\"SigmaOrder\"])[1]/a")).click();
		//driver.findElement(By.xpath("//tbody/tr[1]/th/span/div/a")).click();
		

		System.out.println("Clicking on SO");
		Thread.sleep(10000);
		
		WebElement so =
		driver.findElement(By.xpath("//span[text()='Sigma Order Name']/following::span[1]/slot[@name=\"outputField\"]/slot/lightning-formatted-text"));
		 String sonumber = so.getText();
		 System.out.println("sonumber " + sonumber);
		 
		 Thread.sleep(5000);
		 excel.setCellData("picking", "SigmaOrder", 2, sonumber);
		 excel.setCellData("picking", "SigmaOrder", 2, sonumber);
		 
		 Thread.sleep(5000);
		 excel.setCellData("Package", "SelectSO", 2, sonumber);
		 excel.setCellData("Package", "SelectSO", 2, sonumber);
		 
		 
		 
		 
		 WebElement wb =
		driver.findElement(By.xpath("(//span[text()='Total Amount'])[1]/following::span[1]/slot[@name=\"outputField\"]/slot/lightning-formatted-text"));
		 String st1 = wb.getText();
		 System.out.println("Total Amount " + st1);
		 String total = st1.substring(1);
		 System.out.println("total" + total); 

		// proceed payment
		driver.findElement(By.xpath("//button[text()='Proceed to Payment']")).click();
		Thread.sleep(5000);

		if(paymentvia.contains("Cash")) {
			System.out.println("Inside cash payment");
			// cash
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class=\"payment-img Cash\"]")).click();
			Thread.sleep(8000);
			
			WebElement wb2 = driver.findElement(By.xpath("(//label[@class=\"slds-size--2-of-5\"])[4]"));
			wb2.click();
			Thread.sleep(5000);
			wb2.sendKeys(total);
			// skip payment
			// driver.findElement(By.xpath(OR.getProperty("SkipPayment"))).click();
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//button[text()=\"Save Payment\"]")).click();
			
		}else if (paymentvia.contains("Card")) {
			
			System.out.println("Inside Card payment");
			// Card
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class=\"payment-img Card\"]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[contains(@class,'DESKTOP uiContainerManager')]//div[contains(@class,'slds-grid')]//div[1]//div[1]//div[2]//div[1]//input[1]")).sendKeys(total);			
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@placeholder=\"Name\"]")).sendKeys(CardHolderName);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@placeholder=\"Enter Credit Card Number\"]")).sendKeys(CardNumber);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@placeholder=\"Enter CVV Number\"]")).sendKeys(CVV);
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//div[contains(@class,'form-element uiInput uiInputDate uiInput--default uiInput--input uiInput--datetime')]//input[contains(@class,'slds-input slds-size--3-of-4 input')]")).sendKeys(Date);
			Thread.sleep(5000);
			
		}else {
			
			System.out.println("Inside Cheque payment");
			// Cheque
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class=\"payment-img Cheque\"]")).click();
			Thread.sleep(8000);
			driver.findElement(By.xpath("//fieldset[1]//div[1]//div[1]//label[2]//input[1]")).sendKeys(total);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//fieldset[2]//div[1]//div[1]//label[2]//input[1]")).sendKeys(ChequeNumber);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//fieldset[3]//div[1]//div[1]//label[2]//input[1]")).sendKeys(BankName);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[contains(@class,'slds-input slds-truncate input')]")).sendKeys(Date);
			Thread.sleep(5000);
			
		}		

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(text(),'Save Payment')]")).click();
		
		Thread.sleep(5000);
		Alert aa2 = driver.switchTo().alert();
		aa2.accept();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Cancel']")).click();
		
	}
	
	@Test(dataProviderClass = Testbase.class,dataProvider = "dp", priority = 12)
	public void Aallocation(String AallocationType) throws InterruptedException 
	{
		Thread.sleep(8000);
		System.out.println("Aallocation");
		
		// proceed to Allocate
		driver.findElement(By.xpath("//button[text()='Edit']")).click();
		Thread.sleep(5000);
		
		if(AallocationType.contains("Automatic")) {
			
			driver.findElement(By.xpath("//button[@class='slds-button slds-button_neutral btnpadding'][contains(text(),'A')]")).click();
			Thread.sleep(5000);
			
		}else {
			
			System.out.println("Manual Allocation");
		}
		
		
		Alert aa2 = driver.switchTo().alert();
		aa2.accept();
		Thread.sleep(5000);
		
		Alert aa3 = driver.switchTo().alert();
		aa3.accept();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand buttonOverlay']")).click();
		Thread.sleep(5000);	
		
	}

	// ---------------------------Pick---------------------------------------

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 13)
	public void picking(String SigmaOrder, String User, String PickedQty) throws InterruptedException {
		 Thread.sleep(4000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 
		driver.findElement(By.xpath(OR.getProperty("SigmaOrdertab"))).click();
		Thread.sleep(5000);

		WebElement element = driver.findElement((By.xpath("//img[@name='Picking']")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);

		driver.findElement(By.xpath("//a[@id='NotPicked__item']")).click();
		Thread.sleep(4000);
		
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder='search..']")).click();
		Thread.sleep(4000);
		
		System.out.println("SigmaOrder"+SigmaOrder);
		
		driver.findElement(By.xpath("//input[@placeholder='search..']")).sendKeys(SigmaOrder);
		Thread.sleep(5000);
		
		driver.findElement(
				By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[1]")).click();
		Thread.sleep(4000);

		// select so
		driver.findElement(By.xpath(OR.getProperty("SelectSO"))).click();
		Thread.sleep(3000);

		// allocate user
		Select dd1 = new Select(driver.findElement(By.xpath(OR.getProperty("AllocateUser"))));
		dd1.selectByVisibleText(User);
		Thread.sleep(8000);

		// allocate
		driver.findElement(By.xpath(OR.getProperty("Allocate"))).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[@id='NotPicked__item']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='search..']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@placeholder='search..']")).sendKeys(SigmaOrder);
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\"])[1]")).click();
		Thread.sleep(4000);

		// click on pick
		driver.findElement(By.xpath(OR.getProperty("Pick"))).click();
		Thread.sleep(3000);

		// start picking
		driver.findElement(By.xpath(OR.getProperty("StartPicking"))).click();
		Thread.sleep(3000);

		// input picked qty
		driver.findElement(By.xpath(OR.getProperty("PickedQty"))).sendKeys(PickedQty);
		Thread.sleep(3000);

		// submit
		driver.findElement(By.xpath(OR.getProperty("PickSubmit"))).click();
		Thread.sleep(6000);

	}

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 14)
	public void Package(String Customer, String SelectSO, String PackegedQty) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		

		driver.findElement(By.xpath(OR.getProperty("SigmaOrdertab"))).click();
		Thread.sleep(5000);

		WebElement element = driver.findElement((By.xpath("//img[@name='Package']")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);

		// click on new
		 driver.findElement(By.xpath(OR.getProperty("PackageNew"))).click();
		Thread.sleep(6000);
		
		js.executeScript("window.scrollBy(0,1000)");
		
		// select customer
		WebElement c = driver.findElement(By.xpath(OR.getProperty("PackageCustomer")));
		Thread.sleep(5000);

		Actions act = new Actions(driver);
		act.click(c);
		act.sendKeys(Customer);
		Thread.sleep(2000);
		act.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//span[@class='slds-media__body'])[1]")).click();
		Thread.sleep(8000);
		// select so
		WebElement s = driver.findElement(By.xpath("//input[@placeholder='search..']"));
		Thread.sleep(5000);
		
		System.out.println("SigmaOrder"+SelectSO);
		
		Actions so = new Actions(driver);
		so.click(s);
		so.sendKeys(SelectSO);
		Thread.sleep(2000);
		so.build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("(//li[@class='slds-listbox__item'])[1]")).click();
		Thread.sleep(5000);

		// save
		driver.findElement(By.xpath(OR.getProperty("PackageSave"))).click();
		Thread.sleep(8000);
		
		WebElement pknum = driver.findElement(By.xpath("//tbody/tr[1]/th/span/div/a"));
		String packnum = pknum.getText();
		System.out.println("packnum"+packnum);
		
		
		List<WebElement> myElements = driver.findElements(By.xpath("//table[@class='slds-table slds-table_bordered']/descendant::tbody/tr"));
		//int count = 0;

		System.out.println("index 1:" + myElements.get(1).getText());
		String tempTD = myElements.get(0).getText();
		System.out.println("tempTD::" + tempTD);
		String lines[] = tempTD.split("\\r?\\n");
		Thread.sleep(3000);
		excel.setCellData("shipment", "PackageNumber", 2, lines[1]);
		Thread.sleep(5000);
	}

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 15)
	public void shipment(String LogisticsName, String DeliveryNote, String TruckNo, String Waybill,
			String DeliveryPerson, String ShipmentDate, String Status1, String ConsignmentNo, String TrackingUrl,
			String PackageNumber, String ShipmentDeliveryDate, String Status2) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);

		driver.findElement(By.xpath(OR.getProperty("SigmaOrdertab"))).click();
		Thread.sleep(5000);

		WebElement element = driver.findElement((By.xpath("//img[@name='Shipment']")));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);

		// new
		// driver.findElement(By.xpath(OR.getProperty("ShipmentNew"))).click();

		driver.findElement(By.xpath("//button[@name='Package']")).click();
		Thread.sleep(3000);
		
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(5000);
		
		// select logistics
		Select dd2 = new Select(driver.findElement(By.xpath(OR.getProperty("Logisticsname"))));
		dd2.selectByVisibleText(LogisticsName);
		Thread.sleep(4000);

		// delivery note
		driver.findElement(By.xpath(OR.getProperty("DeliveryNote"))).sendKeys(DeliveryNote);
		Thread.sleep(3000);

		// truck number
		driver.findElement(By.xpath(OR.getProperty("Truck"))).sendKeys(TruckNo);
		Thread.sleep(3000);

		// waybill
		driver.findElement(By.xpath(OR.getProperty("Waybill"))).sendKeys(Waybill);
		Thread.sleep(3000);

		// delivery person
		driver.findElement(By.xpath(OR.getProperty("DeliveryPerson"))).sendKeys(DeliveryPerson);
		Thread.sleep(5000);

		// shipment date
		driver.findElement(By.xpath(OR.getProperty("ShipmentDate"))).sendKeys(ShipmentDate);
		Thread.sleep(5000);

		// header status
		Select dd3 = new Select(driver.findElement(By.xpath(OR.getProperty("Status1"))));
		dd3.selectByVisibleText(Status1);
		Thread.sleep(4000);

		// consignment number
		driver.findElement(By.xpath(OR.getProperty("ConsignmentNo"))).sendKeys(ConsignmentNo);
		Thread.sleep(3000);
		
		// tracking url
		driver.findElement(By.xpath(OR.getProperty("TrackingUrl"))).sendKeys(TrackingUrl);
		Thread.sleep(5000);

		// package number
		WebElement pn = driver.findElement(By.xpath("//input[@placeholder='search..']"));
		pn.click();
		pn.sendKeys(PackageNumber);
		Thread.sleep(5000);

		 driver.findElement(By.xpath("(//span[@class='slds-listbox__option-text slds-listbox__option-text_entity'])[1]")).click();
		Thread.sleep(9000);

		// click on submit
		driver.findElement(By.xpath(OR.getProperty("ShipmentSubmit"))).click();
		Thread.sleep(5000);

		System.out.println("Shipment succesful");

	} 
}
