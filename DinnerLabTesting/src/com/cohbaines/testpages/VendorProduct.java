package com.cohbaines.testpages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
			
			//Click on search icon
		   WebElement Wb=driver.findElement(By.xpath("html/body/div[5]/div[1]/header/div[2]/div[2]/div/div/div[2]/div[2]/div/input"));
		   Wb.click();
		   Wb.clear();
		   Thread.sleep(3000);
		
		//To search for the product
		   Wb.sendKeys(ProductName);
		 
		 Thread.sleep(6000);
		 WebElement wb2=driver.findElement(By.xpath("//div[@class='listContent']/descendant::li/descendant::img[@src='https://mindlinks3-dev-ed.my.salesforce.com/img/icon/t4v35/standard/product_120.png']"));
		 wb2.click();
		 System.out.println("Product Related tab1");
		
			// Related of the Product
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		 driver.findElement(
					By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::ul[@class='tabs__nav']/descendant::li[1]"))
					.click();
			System.out.println("Product Related tab2");
			Thread.sleep(5000);
			
			//Product Pricing new Button
			driver.findElement(
					By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::div[@class='container forceRelatedListSingleContainer'][4]/descendant::div[@class='slds-no-flex']"))
					.click();
			
			Thread.sleep(5000);
			driver.findElement(By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div/div[2]/div/div[4]/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[5]/a/div[2]/div[1]")).click();
			/*Thread.sleep(5000);
			ele1.clear();
			Thread.sleep(5000);
			ele1.sendKeys("fhgvj");
			Thread.sleep(5000);*/
			
			
		    //click on Duty
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[3]/div/input")).sendKeys(Duty);
			Thread.sleep(5000);
			//click on Freight
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[4]/div/input")).sendKeys(Freight);
			Thread.sleep(5000);
			//click on Insurance
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[5]/div/input")).sendKeys(Insurance);
			Thread.sleep(5000);
			//click on Others
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[6]/div/input")).sendKeys(Others);

			//click on Save Button
			driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
			
			JavascriptExecutor javascript = (JavascriptExecutor) driver;
			  javascript.executeScript("alert('Test Case Execution Is started Now..with amount 1200.30');");
			  Thread.sleep(5000);
			  driver.switchTo().alert().accept();
			  
			  
		} catch (Exception e) {
			System.out.println("createaccount Unsuccessful");

		}
		return false;
	}

}
