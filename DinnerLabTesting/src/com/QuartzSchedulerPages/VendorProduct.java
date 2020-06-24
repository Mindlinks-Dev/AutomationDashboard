package com.QuartzSchedulerPages;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.util.ExcelLib;

public class VendorProduct {
	private String ProductName;
	private String AccountName;
	private String Duty;
	private String Freight;
	private String Insurance;
	private String Others;
	private String PassorFail;
	public static boolean newProductstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(VendorProduct.class);

	public boolean VendorProductInitialPage(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("VendorProduct");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				ProductName = xllib.getExcelData("VendorProduct", i, 0);
				AccountName = xllib.getExcelData("VendorProduct", i, 1);
				Duty = xllib.getExcelData("VendorProduct", i, 2);
				Freight = xllib.getExcelData("VendorProduct", i, 3);
				Insurance = xllib.getExcelData("VendorProduct", i, 4);
				Others =xllib.getExcelData("VendorProduct", i, 5);
				PassorFail =xllib.writeToExcel("VendorProduct", i,6,"Pass");
				newProductstatus = createProduct(driver, ProductName,AccountName,
						Duty, Freight, Insurance,Others);

				System.out.println("Login Count:" + i);
			}

		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

	private boolean createProduct(WebDriver driver,String ProductName, String AccountName,
			String Duty, String Freight, String Insurance,String Others) {
		try {
			Thread.sleep(5000);

			// Click on the Product Tab
			driver.findElement(By.xpath("//a[@title='Products']")).click();
			Thread.sleep(5000);
			
			//click on search icon
		System.out.println("product Pricing page");
		WebElement Wb=driver.findElement(By.xpath("//div[@class=\"inputWrapper slds-grid slds-grid--align-center slds-grid--vertical-align-center slds-size--1-of-1\"]/descendant::input"));
		Wb.click();
		System.out.println("product Pricing page1");
		Wb.clear();
		Thread.sleep(3000);
		Wb.sendKeys(ProductName);
		
		Thread.sleep(6000);
		WebElement  wb2=driver.findElement(By.xpath("(//img)[1]"));
		wb2.click();
	   
		System.out.println("Product Related tab1");
			// Related of the Product
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.findElement(
					By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::ul[@class='tabs__nav']/descendant::li[1]"))
					.click();
			System.out.println("Product Related tab2");
			Thread.sleep(5000);
			
			

			
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			 js1.executeScript("window.scrollBy(0,600)");
			
			 

			  JavascriptExecutor javascript = (JavascriptExecutor) driver;
			  javascript.executeScript("alert('Defining VendorProduct');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
			  
			 
			 
			 
			
			
			//Product Pricing new Button
			 System.out.println("Product Pricing new Button");
			driver.findElement(
					By.xpath("(//div[@title=\"New\"])[5]"))
					.click();
			
			Thread.sleep(5000);
			
		/*vendor lookup
			driver.findElement(By.xpath("(//div[@class=\"autocompleteWrapper slds-grow\"])[1]/descendant::input")).sendKeys(AccountName);
			Thread.sleep(5000);
		
				//Actions action=new Actions(driver);
				driver.findElement(By.xpath("((//div[@class=\"contentWrapper slds-box--border\"])[1]/descendant::div)[3]//ul/li[1]"));
				/*for (WebElement we1 : lst) {
				String str = we1.getText();
				System.out.println(str);
				if (str.equalsIgnoreCase(AccountName))
				{
				Thread.sleep(5000);
				we1.click();
				action.click();
				action.build().perform();
				}
				}
				
				}
			   catch (Exception e) {

				}*/
				
			driver.findElement(By.xpath("//div[text()='"+AccountName+"']")).click();

			
			
		    //click on Duty
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[7]/descendant::input")).clear();
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[7]/descendant::input")).sendKeys(Duty);
			Thread.sleep(5000);
			
			//click on Freight
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[8]/descendant::input")).clear();
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[8]/descendant::input")).sendKeys(Freight);
			System.out.println(Freight);
			Thread.sleep(5000);
			
			//click on Insurance
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[9]/descendant::input")).clear();
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[9]/descendant::input")).sendKeys(Insurance);
			Thread.sleep(5000);
			
			
			
			//click on Others
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[10]/descendant::input")).clear();
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[10]/descendant::input")).sendKeys(Others);
			Thread.sleep(5000);

			//click on Save Button
			driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
			
			
			Thread.sleep(8000);
		
			
			JavascriptExecutor javascript1 = (JavascriptExecutor) driver;
			  javascript1.executeScript("alert('Vendor Product created with Landed Cost');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
			  
			  
		} catch (Exception e) {
			System.out.println("createaccount Unsuccessful");

		}
		return false;
	}

}
