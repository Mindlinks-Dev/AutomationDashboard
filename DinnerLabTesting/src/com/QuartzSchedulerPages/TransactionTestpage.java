package com.QuartzSchedulerPages;



import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;


import com.dinnerLab.util.ExcelLib;

@Test
public class TransactionTestpage {
	static String TDNumber;
	WebDriver driver;
	private String TransactionType;
	private String Account;
	private String PurchaseOrder;
	private String Product;
	private String Quantity;
	private String PassorFail;
	public static boolean newProductstatus;
	int i = 0;
	
	private static Logger log = Logger.getLogger(TransactionTestpage.class);

	public boolean TransactionInitialPage(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("Transactions(PO)");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				TransactionType = xllib.getExcelData("Transactions(PO)", i, 0);
				Account = xllib.getExcelData("Transactions(PO)", i, 1);
				PurchaseOrder =xllib.getExcelData("Transactions(PO)", i, 2);
				Product =xllib.getExcelData("Transactions(PO)", i, 3);
				Quantity =xllib.getExcelData("Transactions(PO)", i, 4);
				PassorFail =xllib.writeToExcel("Transactions(PO)", i,6,"Pass");
				newProductstatus = createTransaction(driver, TransactionType,Account,PurchaseOrder,Product,Quantity);
						

				System.out.println("Login Count:" + i);
			}

		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

	private boolean createTransaction(WebDriver driver,String TransactionType, String Account, String PurchaseOrder,String Product,String Quantity) throws InterruptedException{
		
			Thread.sleep(20000);
			driver.findElement(By.xpath("//button[@class=\"slds-button\"]")).click();

			Thread.sleep(5001);
			 
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).sendKeys("Transactions");
			Thread.sleep(5001);
			Actions action=new Actions(driver);
			WebElement el=driver.findElement(By.xpath("(//a[@title=\"Transactions\"])[2]"));
			action.moveToElement(el);
			action.click();
			action.build().perform();
			Thread.sleep(5000);
			

			  JavascriptExecutor javascript = (JavascriptExecutor) driver;
			  javascript.executeScript("alert('Creating Purchase Order');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
			
			
			  Thread.sleep(5000);
			
		   //Click on the 'New' Button
			driver.findElement(By.xpath("//a[@class='forceActionLink']/div[@title='New']")).click();
		
			Thread.sleep(5000);
			 
			WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
			driver.switchTo().frame(frame);
			System.out.println(" switching  to frame");
			try{
			Thread.sleep(20001);// entering purchase order
			System.out.println("enter");
		   driver.findElement(By.xpath("(//div[@class=\"requiredInput\"]/span)[1]/input")).sendKeys("purchase order");
			}
			catch(Exception exp){
				
			}
		
		    Thread.sleep(5000);
		   driver.findElement(By.xpath("(//div[@class=\"requiredInput\"]/span)[2]/input")).click();
		   
		
		// clicks on account tye img
			driver.findElement(By.xpath("//img[@title='Account Lookup (New Window)']")).click();
			Thread.sleep(5000l);
			
			// going into the look up of account
			lookupDataSelect(driver,Account);

			Thread.sleep(4000l);
			System.out.println("frame Switching");
			driver.switchTo().frame(frame);
			
			// clicks on save button
			System.out.println("save button");
			driver.findElement(By.xpath("(//input[@value=\"Save\"])[1]")).click();

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

			
		
			// clicks on new item button to add product
			driver.findElement(By.xpath("(//div[@class=\"pbHeader\"])[2]/descendant::input")).click();

			Thread.sleep(8000l);
			// Thread.setDefaultUncaughtExceptionHandler(null);
			// click on product text field and sends the product.

			Actions actions2 = new Actions(driver);
			WebElement ele3 = driver.findElement(By.xpath("//div[@class=\"requiredInput\"]/descendant::span"));
			actions2.moveToElement(ele3);
			actions2.click();
			actions2.sendKeys(Product);
			actions2.build().perform();
			Thread.sleep(5000);

			// enters qty
			WebElement ele2 = driver.findElement(By.xpath("(//input[@type=\"text\"])[3]"));
			Actions actions = new Actions(driver);
			actions.moveToElement(ele2);
			actions.click();
			ele2.clear();
			actions.sendKeys(Quantity);
			actions.build().perform();
        
		
			// clicks on save button
			driver.findElement(By.xpath("(//input[@value=\"Save\"])[1]")).click();

			
			Thread.sleep(5000);
			 JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
			  javascript1.executeScript("alert('Purchase Order Created Sucessfully');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
			  
			  
			  driver.switchTo().parentFrame();
			/*  driver.switchTo().defaultContent();
				Thread.sleep(3000);

				List<WebElement> frames1 = driver.findElements(By.xpath("//iframe"));
				System.out.println(frames1);
				for (WebElement WE1 : frames1) {
					System.out.println(WE1);
				}
				Thread.sleep(3000);
				driver.switchTo().frame(0);*/
		
			  Thread.sleep(5000l);
				// clicks on Transaction tab
				driver.findElement(By.xpath("//div[@class=\"bBottom\"]/descendant::span[text()='Transactions']")).click();

			  
			  
		return false;

	
}

	
	public static void lookupDataSelect(WebDriver driver, String Account) throws InterruptedException {
		
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);

		java.util.Iterator<String> itr = handles.iterator();
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

		driver.switchTo().window(PW);
		Thread.sleep(2000l); 
	}
	
}


