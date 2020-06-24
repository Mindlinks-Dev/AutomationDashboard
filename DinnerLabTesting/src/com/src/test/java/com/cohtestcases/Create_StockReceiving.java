 /**
 * @author: Meghana .J
 * @Created Date :02/03/2018
 * @Updated Date :
 * @Comments:This automation class will create stapp order in stapp.
*/
package com.src.test.java.com.cohtestcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.src.test.java.com.Base.Testbase;


public class Create_StockReceiving extends Testbase {
	//public static String TD = "TD-022209";

	@Test(dataProviderClass=Testbase.class,dataProvider="dp",priority = 1)
	public static void stockreceiving(String VENDOR,String TRANSACTIONNO,String QTY,String LOCATION) throws InterruptedException {
		driver.manage().window().maximize();
		Thread.sleep(8000);
		WebDriverWait wait = new WebDriverWait(driver, 60l);
		// SR
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Stock Receiving']")))
				.click();
		Thread.sleep(8000l);
		driver.findElement(By.xpath("//div[text()=\"New\"]")).click();

		
		System.out.println("Checking frames inside SR");
		
		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
		System.out.println(frames);
		for (WebElement WE1 : frames) {
			System.out.println(WE1);
		}
		
		
		Thread.sleep(10000);
	//	WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		
		try
		{
			driver.switchTo().frame(0);
			System.out.println("frame 1");
		}
		
		catch(Exception e)
		{
			driver.switchTo().frame(1);
			System.out.println("frame 2");
		}
		
	//	System.out.println("switching to frame");
		// driver.switchTo().defaultContent();
		
		Thread.sleep(12000);
		driver.findElement(By.xpath("(//div[@class=\"slds-form-element\"]/descendant::input)[1]")).sendKeys(VENDOR);
		
		Thread.sleep(10000);
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
		driver.findElement(
				By.xpath("//input[@class=\"slds-input input uiInput uiInputNumber uiInput--default uiInput--input\"]"))
				.sendKeys(QTY);


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


}
