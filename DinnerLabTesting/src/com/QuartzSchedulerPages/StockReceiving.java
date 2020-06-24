package com.QuartzSchedulerPages;



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
	double[] myList = {1.1, 1.2, 1.3, 1.4, 1.5};
	String[] myalphalist = {"2.a1", "2.b1", "2.c1", "2.d1", "2.e1"};

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
	
	Thread.sleep(10000);
	driver.findElement(By.xpath("//a[@title=\"Stock Receiving\"]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//a[@title='New']")).click();

	 Thread.sleep(10000);
		
	  JavascriptExecutor javascript = (JavascriptExecutor) driver;
	  javascript.executeScript("alert('Product Stock Receiving');");
	  Thread.sleep(8000);
	  driver.switchTo().alert().accept();
	  
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
	System.out.println("Vendor");
	} catch (Exception e) {

	}

	
	System.out.println("switched to frame");
	WebElement we1 = driver.findElement(By.xpath(
			"(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[4]"));

	Actions actions = new Actions(driver);
	actions.moveToElement(we1);
	actions.click();
	actions.sendKeys(Transaction);
	actions.build().perform();

	Thread.sleep(9000l);
	List<WebElement> list = driver.findElements(By.xpath("//ul[@class=\"slds-lookup__list\"]/li"));
	for (WebElement we : list) {
		if (we.getText().contentEquals(Transaction)) {
			Thread.sleep(5000l);
			we.click();

		}
	}
	JavascriptExecutor js1 = (JavascriptExecutor) driver;
	 js1.executeScript("window.scrollBy(0,250)");
	
	
	Thread.sleep(5000l);
	driver.findElement(
			By.xpath("//input[@class=\"slds-input input uiInput uiInputNumber uiInput--default uiInput--input\"]"))
			.sendKeys(ReceivingQuantity);
	Thread.sleep(20001);
	
	driver.findElement(By.xpath("//button[text()=\"Add Serial\"]")).click();
	Thread.sleep(20001);

	driver.findElement(By.xpath("(//img)[2]")).click();
	Thread.sleep(20001);
	
	JavascriptExecutor js2 = (JavascriptExecutor) driver;
	 js2.executeScript("window.scrollBy(0,500)");
	
 //Listout all accordion elements Serial Number
List<WebElement> serialnumberbutton = driver.findElements(By.xpath("//div[@title=\"Stage\"]/following::input"));
 System.out.println(serialnumberbutton.size());
 
 Iterator<WebElement> itr = serialnumberbutton.iterator();

 for(int k=1;k<=myalphalist.length;k++)
 {
	
	 driver.findElement(By.xpath("(//div[@title=\"Stage\"]/following::input)["+k+"]")).sendKeys(" "+myalphalist[k-1]);
	    System.out.println(k);
	    Thread.sleep(5000);
	    System.out.println(itr.next());

	  
 }
 

driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--brand\"])[1]")).click();
Thread.sleep(5000);

JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
javascript1.executeScript("alert('Test Case Execution-with Stock Linkage amount-$500');");
Thread.sleep(20000);
driver.switchTo().alert().accept();
Thread.sleep(20000);
WebElement Wb=driver.findElement(By.xpath("//div[@class=\"autocompleteWrapper slds-grow slds-form-element__control\"]/descendant::input"));
Wb.click();
Wb.clear();
Thread.sleep(3000);
Wb.sendKeys("Goods Receipt");
//img icon
Thread.sleep(6000);
WebElement  wb2=driver.findElement(By.xpath("(//img)[1]"));
wb2.click();
 

//Related of journal(goods Recipe)
Thread.sleep(5000);
driver.findElement(By.xpath("//span[text()=\"Related\"]")).click();

//scroll bar
Thread.sleep(5000);
JavascriptExecutor jse1 = (JavascriptExecutor) driver;
jse1.executeScript("window.scrollBy(0,250)", "");

//particular Stock Linkage
Thread.sleep(5000);
driver.findElement(By.xpath("(//tbody)[2]/tr[1]/th/div/a")).click();


Thread.sleep(20000);
//alerts
JavascriptExecutor javascript2 = (JavascriptExecutor) driver;
javascript2.executeScript("alert('verified with Stock Linkage journal amount-$500');");
Thread.sleep(20000);
driver.switchTo().alert().accept();


	
	return false;

}

}
