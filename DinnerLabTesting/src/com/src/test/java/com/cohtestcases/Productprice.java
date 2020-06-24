 /**
 * @author: Meghana .J
 * @Created Date :02/03/2018
 * @Updated Date :
 * @Comments:This automation class will add product price for product  in stapp.
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.src.test.java.com.Base.Testbase;


public class Productprice extends Testbase {
	// (dataProvider = "data") String Q1STARTRANGE, String Q1ENDRANGE, String
	// Q1PRICE, String PRODUCTID
	@Test
	public static void productprice() throws InterruptedException, AWTException {

		// https://cohbaines--sagedev.lightning.force.com/one/one.app#/sObject/01t25000002uSCOAA2/rlName/stapplink__Product_Prices__r/view

		driver.manage().window().maximize();
		// https://cohbaines--sagedev.lightning.force.com/one/one.app?source=aloha#/sObject/01t25000002uS1bAAE/rlName/stapplink__Product_Prices__r/view
		// product id
		driver.get(
				"https://cohbaines--sagedev.lightning.force.com/one/one.app#/sObject/01t25000002uSJGAA2/rlName/stapplink__Product_Prices__r/view");

		// New button

		Thread.sleep(8000l);
		// This will not return the button until it is enabled.
		// button =
		// driver.find_element("(//a[@class='forceActionLink'][@title='New']/ancestor::li)[3]",
		// "//button[@id='myButtonId' and not(@disabled)]")
		// button.click

		WebElement un = driver.findElement(By.xpath("(//a[@class='forceActionLink'][@title='New']/ancestor::li)[3]"));
		int xaxis = un.getLocation().x;

		int yaxis = un.getLocation().y;

		int width = un.getSize().width;
		int height = un.getSize().height;

		Robot r = new Robot();
		r.mouseMove(xaxis + width / 2, yaxis + height / 2 + 70);
		r.mousePress(KeyEvent.BUTTON1_MASK);// click function
		r.mouseRelease(KeyEvent.BUTTON1_MASK);

		/*
		 * WebElement element = driver.findElement(By.xpath(
		 * "//a[@class='forceActionLink'][@title='New']/ancestor::li"));
		 * 
		 * JavascriptExecutor executor = (JavascriptExecutor)driver;
		 * executor.executeScript("arguments[0].click();", element);
		 * 
		 * 
		 * Thread.sleep(5000l);
		 */

		/*
		 * System.out.println("enters start range"); driver.findElement( By.
		 * xpath("(//div[@class=\"uiInput uiInputNumber uiInput--default uiInput--input\"]/input)[1]"
		 * )) .sendKeys("500");
		 * 
		 * Thread.sleep(5000l);
		 * 
		 * System.out.println("enters end range"); driver.findElement( By.
		 * xpath("(//div[@class=\"uiInput uiInputNumber uiInput--default uiInput--input\"]/input)[2]"
		 * )) .sendKeys("2000");
		 * 
		 * System.out.println("enters price"); driver.findElement(By.
		 * xpath("//div[@class=\"uiInput uiInput--default uiInput--input\"]/input"
		 * )) .sendKeys("2");
		 * 
		 * Thread.sleep(5000l); Robot robot = new Robot();
		 * robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		 * robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		 * 
		 * Actions actions = new Actions(driver); WebElement ele =
		 * driver.findElement(By.
		 * xpath("(//div[@class=\"autocompleteWrapper slds-grow\"])[3]"));
		 * Thread.sleep(5000l); actions.moveToElement(ele); actions.click();
		 * actions.sendKeys("Sales Price Book"); actions.build().perform();
		 * 
		 * // driver.findElement(By.xpath("(//div[@class=\"autocompleteWrapper
		 * // slds-grow\"])[3]")) // .sendKeys("Sales Price Book");
		 * 
		 * Thread.sleep(5000l); Robot robot1 = new Robot();
		 * robot1.keyPress(KeyEvent.VK_PAGE_DOWN);
		 * robot1.keyRelease(KeyEvent.VK_PAGE_DOWN);
		 * 
		 * WebElement lst = driver.findElement(By.xpath(
		 * "(//div[@role=\"listbox\"]/descendant::div[3])[2]"));
		 * 
		 * // Thread.sleep(5000l);
		 * 
		 * actions.moveToElement(lst); // Thread.sleep(5000l); actions.click();
		 * actions.build().perform();
		 * 
		 * Thread.sleep(5000l);
		 * 
		 * driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
		 */
	}

	/*
	 * @DataProvider public static Object[][] data() { String sheetname =
	 * "packageproduct"; int rowno = excel.getRowCount(sheetname);
	 * System.out.println(rowno);
	 * 
	 * int colno = excel.getColumnCount(sheetname); System.out.println(colno);
	 * Object[][] data = new Object[rowno - 1][colno]; for (int row = 4; row <=
	 * 10; row++) { for (int col=0; col < colno; col++) {
	 * System.out.println(rowno + "," + colno); data[row-3][col] =
	 * excel.getCellData(sheetname, col, row); System.out.println(data[row -
	 * 3][col]);
	 * 
	 * }
	 * 
	 * } return data;
	 * 
	 * }
	 */
}
