package com.cohbaines.testpages;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.util.ExcelLib;

public class StockReceiving {
	WebDriver driver;
	private String Vendor;
	private String Transaction;
	private String ReceivingQuantity;
	private String Location;
	private String PassorFail;
	public static boolean newProductstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(TransactionTestpage.class);
	
	public boolean StockReceivingInitialPage(WebDriver driver)
			throws InvalidFormatException {
		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("Stock Receiving");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				Vendor = xllib.getExcelData("Stock Receiving", i, 0);
				Transaction = xllib.getExcelData("Stock Receiving", i, 1);
				ReceivingQuantity = xllib.getExcelData("Stock Receiving", i, 2);
				Location = xllib.getExcelData("Stock Receiving", i, 3);
				PassorFail = xllib.writeToExcel("Stock Receiving", i,6,"Pass");
				newProductstatus = createStockReceiving(driver, Vendor,Transaction,ReceivingQuantity,Location);
						

				System.out.println("Login Count:" + i);
			}

		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

private boolean createStockReceiving(WebDriver driver,String Vendor,String Transaction, String ReceivingQuantity,String Location) throws InterruptedException{
	
	Thread.sleep(5000);
	driver.findElement(By.xpath("//a[@title=\"Stock Receiving\"]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//a[@title='New']")).click();
	Thread.sleep(5000);

	WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
	driver.switchTo().frame(frame);
	System.out.println(" switching  to frame");
	try {
	Thread.sleep(20001);// entering purchase order
	System.out.println("r768");
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[1]/input")).click();
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[1]/input")).sendKeys(Vendor);
	Thread.sleep(20001);
	Actions action=new Actions(driver);
	List<WebElement> lst=driver.findElements(By.xpath("(//ul[@class=\"slds-lookup__list\"])[1]/li"));

	for (WebElement we : lst) {
	String str = we.getText();
	System.out.println(str);
	if (str.equalsIgnoreCase(Vendor))
	{
	Thread.sleep(20001);
	we.click();
	action.click();
	action.build().perform();
	}
	}
	System.out.println("r768");
	} catch (Exception e) {

	}

	driver.findElement(By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon--right'])[4]/input")).click();
	driver.findElement(By.xpath("(//div[@class='slds-form-element__control slds-input-has-icon slds-input-has-icon--right'])[4]/input")).sendKeys(Transaction);
	Thread.sleep(20001);
	try {
	Actions action=new Actions(driver);
	List<WebElement> lst=driver.findElements(By.xpath("(//ul[@class=\"slds-lookup__list\"])[4]/li"));

	for (WebElement we1 : lst) {
	String str = we1.getText();
	System.out.println(str);
	if (str.equalsIgnoreCase(Transaction))
	{
	Thread.sleep(20001);
	we1.click();
	action.click();
	action.build().perform();
	}
	}
	System.out.println("r768");
	}
   catch (Exception e) {

	}
	

	
	driver.findElement(By.xpath("//td[@data-label=\"Received Quantity\"]//input")).click();
	driver.findElement(By.xpath("//td[@data-label=\"Received Quantity\"]//input")).sendKeys(ReceivingQuantity);
	Thread.sleep(5000);
	
	/*driver.findElement(By.xpath("//div[@title=\"Add Serial Number\"]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//div[@title=\"CNK\"]/img")).click();
	Thread.sleep(5000);
	
 //Listout all accordion elements
 List<WebElement> allLinks = driver.findElements(By.xpath("//div[@title=\"Stage\"]/following::input"));
 System.out.println(allLinks.size());
 
 Iterator<WebElement> itr = allLinks.iterator();
 while(itr.hasNext()){
 int j= 1;
 driver.findElement(By.xpath("(//div[@title=\"Stage\"]/following::input)["+j+"]")).sendKeys(" "+j);
    System.out.println(itr.next());
    itr.next();
    j++;
    
 }*/
	
//(//button[@class=\"slds-button slds-button--brand\"])[1]
driver.findElement(By.xpath("//button[text()=\"Submit\"]")).click();
Thread.sleep(5000);


JavascriptExecutor javascript = (JavascriptExecutor) driver;
javascript.executeScript("alert('Test Case Execution-with amount Stock Linkage amount-$500');");
Thread.sleep(5000);
driver.switchTo().alert().accept();
Thread.sleep(5000);

driver.findElement(By.xpath("//one-app-launcher-header[@class=\"slds-icon-waffle_container\"]")).click();

Thread.sleep(20001);
 
driver.findElement(By.xpath("//div[@class=\"slds-form-element__control\"]//input")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@class=\"slds-form-element__control\"]//input")).sendKeys("type");
Thread.sleep(20001);
Actions action=new Actions(driver);
WebElement ele1=driver.findElement(By.xpath("(//li[@class=\"slds-col--padded slds-grow-none oneAppLauncherItem\"])[1]/descendant::a"));
action.moveToElement(ele1);
action.click();
action.build().perform();

Thread.sleep(10000);
driver.findElement(By.xpath("//a[@title=\"Goods Receipt\"]")).click();
Thread.sleep(10000);
driver.findElement(By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::ul[@class='tabs__nav']/descendant::li[1]")).click();



	
	return false;

}

}
