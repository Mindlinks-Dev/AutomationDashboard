package com.sigmaERP_Lightening;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateProductPage {

	
	public String ProductName;
	public String ReorderQuantity;
	public String ThresholdQuantity;
	public String MinimumQuantity;
	public String SellingPrice;
	public String ProductPrice;
	public String BuyingPrice;
	public String Discount;

	public static boolean loginstatus;


	public static void productcreation(WebDriver driver) throws IllegalStateException {
		
		
		
		//for (int j = 1; j <= 2; j++) {	
		try {
			ExcelReading xlib = new ExcelReading();
			int readsheetdata = xlib.getRowCount("product");
			for (int i = 1; i <= readsheetdata; i++) {
				
				String ProductName = xlib.getExcelData("product", i, 0);
				String ReorderQuantity = xlib.getExcelData("product", i, 1);
				String ThresholdQuantity = xlib.getExcelData("product", i, 2);
				String MinimumQuantity = xlib.getExcelData("product", i, 3);
				String SellingPrice = xlib.getExcelData("product", i, 4);
				String ProductPrice = xlib.getExcelData("product", i, 5);
				String BuyingPrice = xlib.getExcelData("product", i, 6);
				String Discount = xlib.getExcelData("product", i, 7);
				loginstatus = ProductPage(driver, ProductName, ReorderQuantity, ThresholdQuantity,MinimumQuantity, SellingPrice, ProductPrice, BuyingPrice, Discount);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	private static boolean ProductPage(WebDriver driver, String ProductName, String ReorderQuantity,
			String ThresholdQuantity, String MinimumQuantity, String SellingPrice, String ProductPrice,
			String BuyingPrice, String Discount) {
		try {
			Thread.sleep(2000);
			System.out.println("hi");
			Thread.sleep(8000);
			//app launcher
			driver.findElement(By.xpath("//div[@class=\"slds-icon-waffle\"]")).click();
			Thread.sleep(9000);
			driver.findElement(By.xpath("(//div[@ class=\"appTileTitle\"])[1]")).click();
			Thread.sleep(3000);
			Thread.sleep(6000);
			Thread.sleep(3000);

			// product tab
			driver.findElement(By.xpath(".//*[@id='oneHeader']/div[3]/one-appnav/div/one-app-nav-bar/nav/ul/li[4]/a/span")).click();
			//driver.findElement(By.xpath(("//span[text()='Products'])[1]"))).click();
			// new button
			driver.findElement(By.xpath("//li[@class='slds-button slds-button--neutral slds-truncate']")).click();

			/*// to select the Ptoduct type
			List<WebElement> list = driver.findElements(By.tagName("span"));
			System.out.println(list.size());
			// System.out.println(list);
			for (WebElement we : list) {
				if (we.getText().equalsIgnoreCase(RecordType)) {
					Thread.sleep(8000l);
					Actions act1 = new Actions(driver);
					act1.click(we);
					// act1.sendKeys("PL");
					act1.build().perform();
				}

			}*/
			//next button
			driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton']")).click();
			
			Thread.sleep(8000l);
			// Product name:
			
			/*WebElement app = driver.findElement(By.xpath("(//div[@class=\"slds-grid full forcePageBlockSectionRow\"]/descendant::input)[1]"));
			Actions act2 = new Actions(driver);
			//act2.moveToElement(app);
			act2.click(app);
			act2.sendKeys(ProductName);
			act2.build().perform()*/;
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[@class=\"slds-grid full forcePageBlockSectionRow\"]/descendant::input)[1]")).sendKeys(ProductName);
		

			Thread.sleep(500); 
			// reorder Qty
			Thread.sleep(5000);
			//driver.findElement(By.xpath(".//*[@id='318:1328;a']")).sendKeys(ReorderQuantity);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[12]/descendant::input")).sendKeys(ReorderQuantity);
			
			// ThresholdQuantity
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[14]/descendant::input")).sendKeys(ThresholdQuantity);
			// MinimumQuantity
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[16]/descendant::input")).sendKeys(MinimumQuantity);
			// SellingPrice
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[17]/descendant::input")).sendKeys(SellingPrice);
			// ProductPrice
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[19]/descendant::input")).sendKeys(ProductPrice);
			// BuyingPrice
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[18]/descendant::input")).sendKeys(BuyingPrice);
			// Discount
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[20]/descendant::input")).sendKeys(Discount);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton']")).click();

			Thread.sleep(8000);
			
		
			//related link
			
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
		
			
			//scroll down
			
			WebElement element = driver.findElement(By.xpath("(//div[@class=\"slds-media__body\"])[3]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
			
			
			//vendor product new button
			
			Thread.sleep(8000);
			try
			{
			//driver.findElement(By.xpath("(//div[@class=\"slds-card__header slds-grid\"])[5]/div/descendant::li]")).click();
			driver.findElement(By.xpath("(//div[@class=\"slds-card__header slds-grid\"])[5]/div/descendant::div[@class=\"slds-truncate\"]")).click();
			}
			catch(Exception e)
			{
				driver.findElement(By.xpath("(//div[@class=\"slds-card__header slds-grid\"])[5]/div/descendant::a[@class=\"forceActionLink\"]")).click();
			}
			Thread.sleep(3000);
			
			
			
			//vendor name search field
			WebElement app = driver.findElement(By.xpath("(//div[@class=\"autocompleteWrapper slds-grow\"])[1]"));
			Actions act2 = new Actions(driver);
			//act2.moveToElement(app);
			act2.click(app);
			act2.sendKeys("Z_Bengaluru_Vendor");
			act2.build().perform();
			Thread.sleep(5000);
			//vendor names list (mindtest vendor)
			driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\"]")).click();
			
			//save button
			driver.findElement(By.xpath("//button[@class=\"slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton\"]")).click();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return false;
	}

}
