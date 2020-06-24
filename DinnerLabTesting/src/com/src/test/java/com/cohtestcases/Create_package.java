 /**
 * @author: Meghana .J
 * @Created Date :02/03/2018
 * @Updated Date :
 * @Comments:This automation class will create package in stapp.
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


public class Create_package extends Testbase {

	
	Create_Stapporder SoNo=new Create_Stapporder();
	
	@Test(dataProviderClass=Testbase.class,dataProvider="dp", priority = 8)
	
			public static void Package(String CUSTOMER,String SONO,String PACKAGEDQTY,String STATUS) throws InterruptedException, AWTException {
				Thread.sleep(12000l);
				driver.manage().window().maximize();

	           //click on tab
				Thread.sleep(12000);
				driver.findElement(By.xpath(
						"//ul[@class=\"slds-grid slds-has-flexi-truncate navUL\"]/descendant::span[text()='Stapp Order Package']"))
						.click();
				
				
				// New button
				Thread.sleep(10000);
				driver.findElement(By.xpath("//button[text()=\"New\"]")).click();

				Thread.sleep(9000l);

				WebElement we = driver.findElement(By.xpath(
						"(//section[@class=\"slds-modal slds-modal_large slds-slide-up-saving\"]/descendant::div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[1]"));
				Actions act1 = new Actions(driver);
				act1.click(we);
				act1.sendKeys(CUSTOMER);
				act1.build().perform();
				Thread.sleep(12000l);
				click(CUSTOMER);

				Thread.sleep(5000l);

				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,250)", "");
				WebElement we2 = driver.findElement(By.xpath(
						"(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"])[4]"));
				act1.click(we2);

				act1.sendKeys(SONO);
				act1.build().perform();

				Thread.sleep(5000l);
				click(SONO);
				
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[4]")).clear();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[4]")).sendKeys(PACKAGEDQTY);

				Thread.sleep(5000l);
				WebElement we3 = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));

				act1.click(we3);
				act1.build().perform();
				Thread.sleep(5000l);
				driver.findElement(By.xpath("//a[text()=\"Autopick\"]")).click();
				Thread.sleep(12000l);
				Robot rt = new Robot();
				rt.keyPress(KeyEvent.VK_ENTER);
				rt.keyRelease(KeyEvent.VK_ENTER);

				Thread.sleep(12000l);
				rt.keyPress(KeyEvent.VK_ENTER);
				rt.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(8000l);

				driver.findElement(By.xpath("(//footer/descendant::button)[4]")).click();
				Thread.sleep(8000l);
				rt.keyPress(KeyEvent.VK_ENTER);
				rt.keyRelease(KeyEvent.VK_ENTER);

				Thread.sleep(5000l);
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
			
}
