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
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.Buffer;
import com.src.test.java.com.Base.Testbase;

public class Create_Stapporder extends Testbase
{
	public  static int i=10,k=1;
	
	
	     @Test(dataProviderClass=Testbase.class,dataProvider="dp",priority = 1)
	
	
			public static void stapporder1(String CUSTOMER, String SOURCE, String CONTACT, String STATUS)
					throws InterruptedException, AWTException {

				driver.manage().window().maximize();
				Thread.sleep(15000);
		        
				
				//click stapporder tab
				//driver.findElement(By.xpath("//ul[@class=\"slds-grid slds-has-flexi-truncate navUL\"]/descendant::span[text()='Stapp Order']")).click();
				driver.findElement(By.xpath("//span[text()='Stapp Order']")).click();
				
				
				
				// New button
				Thread.sleep(5000);		
				driver.findElement(By.xpath("(//button[text()='New'])[1]")).click();
				
				
				driver.findElement(By.xpath("(//input)[4]")).sendKeys("1");
				
				// customer
				Thread.sleep(10000);
				
				System.out.println("enter customer");
				driver.findElement(By.xpath("(//input)[5]")).sendKeys(CUSTOMER);
				System.out.println("Selects 123 customer");
				Thread.sleep(8000);
				driver.findElement(By.xpath("//ul[@class=\"slds-lookup__list\"]/descendant::a")).click();
				//click(CUSTOMER);
				
				
				System.out.println("clicks customer");
				
			
				/*WebElement we=driver.findElement(By.xpath("(//input)[5]"));
				Actions act1 = new Actions(driver);
				
				//act1.click(we);
				act1.sendKeys(CUSTOMER);
				System.out.println("customer name search");
				act1.build().perform();
				Thread.sleep(12000l);
				click(CUSTOMER);*/

				
				// order source
				Thread.sleep(5000);
				WebElement we2 = driver.findElement(By.xpath(
						"(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[2]"));
				Select option = new Select(we2);
				option.selectByVisibleText(SOURCE);
				
				
				
				//status
				Thread.sleep(5000);
				WebElement we3 = driver.findElement(By.xpath(
						"(//select[@class=\"slds-input select uiInput uiInputSelect uiInput--default uiInput--select\"])[4]"));
				Select option1 = new Select(we3);
				option1.selectByVisibleText("Confirmed");
				
				
				//contact
				Thread.sleep(6000);
						//WebElement we1=driver.findElement(By.xpath("(//input[@class=\"slds-input input uiInput uiInputText uiInput--default uiInput--input\"])[2]"));
						WebElement we1=driver.findElement(By.xpath("(//input)[6]"));
						Actions act = new Actions(driver);
						act.click(we1);
						act.sendKeys(CONTACT);
						act.build().perform();
						Thread.sleep(12000l);
						click(CONTACT);
				
			}
			
			
			
			//------------------------------Stapp Order Add ORDER LINE ITEM-----------------------------------------------------
				
			@Test(dataProviderClass=Testbase.class,dataProvider="dp",priority = 2)
				public static void stapporderline( String PRODUCT1, String QTY )
						throws InterruptedException, AWTException {
					
					
					//click on add more products
					Thread.sleep(5000);
					driver.findElement(By.xpath("//button[contains(text(),\"Add Order Lines\")]")).click();	
					
					//enter the order line products
					Thread.sleep(5000l);
					WebElement we1=driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]/descendant::input)[9]"));
					Actions act = new Actions(driver);
					act.click(we1);
					act.sendKeys(PRODUCT1);
					act.build().perform();				
					Thread.sleep(12000l);
					click(PRODUCT1);
					
					
					//enter the qty
					Thread.sleep(8000);
					driver.findElement(By.xpath("(//input[@class=\"input uiInput uiInputNumber uiInput--default uiInput--input\"])")).sendKeys(QTY);
								
					// save
					Thread.sleep(5000);
					driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
					Thread.sleep(5000l);
					Robot rb = new Robot();

					rb.keyPress(KeyEvent.VK_ENTER);

					rb.keyRelease(KeyEvent.VK_ENTER);

					Thread.sleep(4000);
					// driver.close();
					System.out.println(driver.getWindowHandles().size());
					//String SONumber = driver.findElement(By.xpath("(//h1[@class=\"slds-page-header__title slds-m-right--small slds-truncate slds-align-middle\"])[2]/span")).getText();
					//System.out.println("entered the value");
					//System.out.println(SONumber);
				    log.debug("Stapporder creation sucessfull");
			}

				
			


			@DataProvider
			public static Object[][] data1() {
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
	
}
