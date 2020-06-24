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


public class multiplestapporder extends Testbase {

	public  static int i=10,k=1;//these are used for xpath  for orderlines purpose dont delet them .
	@Test(dataProviderClass = Testbase.class, dataProvider = "dp",priority=1)
	public static void stapporder(String CUSTOMER, String SOURCE, String CONTACT,String STATUS)
			throws InterruptedException, AWTException {

		driver.manage().window().maximize();
		
		Thread.sleep(5000l);
		//WebDriverWait wait = new WebDriverWait(driver, 60l);
		// Account
		/*wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Stapp Orders']")))
				.click();*/
		Click("//span[text()=\"Stapp Order\"]");

		Thread.sleep(5000l);
		// New button
		driver.findElement(By.xpath("//div[@class=\"slds-button-group\"]/button[1]")).click();

		// customer order number
		//driver.findElement(By.xpath("(//input)[3]")).sendKeys("1");

		// customer
		driver.findElement(By.xpath("(//input)[5]")).sendKeys(CUSTOMER);
		Thread.sleep(8000l);
		click1(CUSTOMER);

		// order source
		WebElement we2 = driver.findElement(By.xpath(
				"(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[2]"));
		Select option = new Select(we2);
		option.selectByVisibleText(SOURCE);
		
		//contact
		sendkeys("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]/descendant::input)[9]",CONTACT);
		Thread.sleep(3000l);
		WebElement contact = driver.findElement(By.xpath("(//input)[6]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(contact);
		Thread.sleep(5000l);
		actions.click();
		//actions.sendKeys(CONTACT);
		actions.build().perform();
		Thread.sleep(8000l);
		click1(CONTACT);
		
		//status
		WebElement we3 = driver.findElement(By.xpath(
				"(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[4]"));
		Select option1 = new Select(we3);
		option1.selectByVisibleText("Confirmed");
		
	}
	
	
	
	//add order line
		@Test(priority=2, dataProvider="data")
		public static void orderline( String PRODUCT1, String QTY )
				throws InterruptedException, AWTException {
		int j=i;
		int l=k;
		
			newproduct(PRODUCT1, QTY, j,l);
			System.out.println(i);
			
			Thread.sleep(3000);
			
			
		// button click
	
		/*
		 * WebElement we4 = driver.findElement(By.
		 * xpath("(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[8]"
		 * )); Select option2= new Select(we4);
		 * option2.selectByVisibleText("Confirmed");
		 */

		// status
		
	}

		
		//save stapporder
		@Test(priority=3)
		public static void save() throws InterruptedException, AWTException
		{
			
			
			

			// save
			driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
			Thread.sleep(5000l);
			Robot rb = new Robot();

			rb.keyPress(KeyEvent.VK_ENTER);

			rb.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(2000);
			// driver.close();
			System.out.println(driver.getWindowHandles().size());
			String SONumber = driver
					.findElement(By
							.xpath("(//h1[@class=\"slds-page-header__title slds-m-right--small slds-truncate slds-align-middle\"])[2]/span"))
					.getText();

			
			
			/*System.out.println(excel.isSheetExist("Package"));
			excel.setCellData("Package", "CUSTOMER", row,CUSTOMER );
			excel.setCellData("Package", "SONO", row, SONumber);
			excel.setCellData("Package", "QTY", row, "3");
			excel.setCellData("Package", "LOCATION", row, "paris");
	*/
			System.out.println("entered the value");

			System.out.println(SONumber);
			
			 log.debug("Stapporder creation sucessfull");
			
			

		}
		
		
	public static void click1(String TD) throws InterruptedException {
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::a"));
		for (WebElement we : list) {
			if (we.getText().contentEquals(TD)) {
				
				Thread.sleep(8000);
				Actions act = new Actions(driver);
				//Thread.sleep(8000l);
				act.click(we);
				act.sendKeys(TD);
				act.build().perform();

				
				
				//((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
				// we.click();

			}
		}
	}

	@DataProvider
	public static Object[][] data() {
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
	
	
	
	
	public static void newproduct(String PRODUCT, String QTY  ,int r ,int m) throws InterruptedException{
		
		Thread.sleep(3000l);
		driver.findElement(By.xpath("//button[contains(text(),\"Add Order Lines\")]")).click();

		Thread.sleep(6000l);
		// product

		WebDriverWait wait1 = new WebDriverWait(driver, 120);
		// product
		wait1.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]/descendant::input)"+"["+r+"]")))
				.sendKeys(PRODUCT);

		Thread.sleep(10000l);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		// wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::li)[1]"))).click();
		click1(PRODUCT);

		

		// QTY
		driver.findElement(By.xpath("(//input[@class=\"input uiInput uiInputNumber uiInput--default uiInput--input\"])"+"["+m+"]"))
		
				.sendKeys(QTY);
		i=i+2;//needed for 
		k=k+1;

	}
	
}
