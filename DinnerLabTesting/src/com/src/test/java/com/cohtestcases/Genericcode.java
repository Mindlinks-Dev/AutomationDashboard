package com.src.test.java.com.cohtestcases;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.src.test.java.com.Base.Testbase;



public class Genericcode extends Testbase 

{

	
	//----------------------------------------------CREATE ACCOUNT------------------------------------------------------
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp",priority=1)
	public static void account(String TYPE, String ACCOUNTNAME,String REGION,String COUNTRY,String Currencycode, String PhoneNo, String Fax,String SHIPPINGCOUNTRY,String BILLINGCOUNTRY,String ShippingStreet,String ShippingCity,String ShippingPostalCode,String	ShippingCountry,String	BillingStreet, String BillingCity, String BillingPostalCode,String BillingCountry) throws InterruptedException, AWTException {

		Thread.sleep(16000);		
		//driver.manage().window().maximize();
		
		// clicks on account tab
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
		
		
			
		   //enters account name,CurrencyCode, PhoneNo, FAX, 
			WebElement ws= driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"));
		    ws.sendKeys(ACCOUNTNAME, Keys.TAB , Keys.TAB, Currencycode);	
			
		    //select the credit customer check box
			WebElement ws1= driver.findElement(By.xpath("//span[text()='Is Credit Customer']/ancestor::div[@class=\"slds-form-element__control\"]/descendant::input"));
			ws1.click();
			ws1.sendKeys(Keys.TAB, Keys.TAB, PhoneNo , Keys.TAB, Fax );
			Thread.sleep(5000);

			//Enter the ShippingAddress and BillingAddress
 			WebElement ws2= driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral lookupButton uiButton\"])[1]"));			
 			ws2.sendKeys(Keys.TAB,ShippingStreet,Keys.TAB,ShippingCity,Keys.TAB,ShippingPostalCode,Keys.TAB,ShippingCountry,Keys.TAB,SHIPPINGCOUNTRY,Keys.TAB,Keys.TAB,BillingStreet,Keys.TAB,BillingCity,Keys.TAB,BillingPostalCode,Keys.TAB,BillingCountry,Keys.TAB,BILLINGCOUNTRY,Keys.TAB,SHIPPINGCOUNTRY,Keys.ENTER,Keys.TAB,BILLINGCOUNTRY,Keys.ENTER,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER); 
			
  
 			System.out.println("Account created");

	} 

	}
	
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp",priority=2)
	public static void accountlive3(String TYPE, String ACCOUNTNAME,String REGION,String COUNTRY,String Currencycode, String PhoneNo, String Fax,String SHIPPINGCOUNTRY,String BILLINGCOUNTRY,String ShippingStreet,String ShippingCity,String ShippingPostalCode,String	ShippingCountry,String	BillingStreet, String BillingCity, String BillingPostalCode,String BillingCountry) throws InterruptedException, AWTException {

		Thread.sleep(12000);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver",
		"D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\src\\test\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.navigate().to(config.getProperty("URL"));
		
		//Enter username text field
		driver.findElement(By.id("username")).sendKeys(config.getProperty("UserName"));

		WebElement ws= driver.findElement(By.id("username"));
		//Enter password text field and click login button 
	    ws.sendKeys(Keys.TAB , config.getProperty("Password"),Keys.TAB.ENTER );    
		Thread.sleep(8000);
		
		
		 log.debug("login successfull");
		
		// clicks on account tab
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
		
		
		   Thread.sleep(6000);
		   //enters account name,CurrencyCode, PhoneNo, FAX, 
			WebElement ws3= driver.findElement(By.xpath("//input[@class=\"input uiInput uiInputText uiInput--default uiInput--input\"]"));
		    ws3.sendKeys(ACCOUNTNAME, Keys.TAB , Keys.TAB, Currencycode);	
			
		    //select the credit customer check box
			WebElement ws1= driver.findElement(By.xpath("//span[text()='Is Credit Customer']/ancestor::div[@class=\"slds-form-element__control\"]/descendant::input"));
			ws1.click();
			ws1.sendKeys(Keys.TAB, PhoneNo , Keys.TAB, Fax, Keys.TAB,ShippingStreet,Keys.TAB,ShippingCity,Keys.TAB,ShippingPostalCode,Keys.TAB,ShippingCountry,Keys.TAB,SHIPPINGCOUNTRY,Keys.TAB,BillingStreet,Keys.TAB,BillingCity,Keys.TAB,BillingPostalCode,Keys.TAB,BillingCountry,Keys.TAB,BILLINGCOUNTRY,Keys.TAB,SHIPPINGCOUNTRY,Keys.ENTER,Keys.TAB,BILLINGCOUNTRY,Keys.ENTER,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER );
			Thread.sleep(5000);

			//Enter the ShippingAddress and BillingAddress
 		//	WebElement ws2= driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral lookupButton uiButton\"])[1]"));			
 			//ws2.sendKeys(Keys.TAB,ShippingStreet,Keys.TAB,ShippingCity,Keys.TAB,ShippingPostalCode,Keys.TAB,ShippingCountry,Keys.TAB,SHIPPINGCOUNTRY,Keys.TAB,Keys.TAB,BillingStreet,Keys.TAB,BillingCity,Keys.TAB,BillingPostalCode,Keys.TAB,BillingCountry,Keys.TAB,BILLINGCOUNTRY,Keys.TAB,SHIPPINGCOUNTRY,Keys.ENTER,Keys.TAB,BILLINGCOUNTRY,Keys.ENTER,Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER); 
			
  
 			System.out.println("Account created");

	} 

	}
	

	
	//java.util.Iterator<String> itr = handles.iterator();
}
