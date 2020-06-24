package com.QuartzSchedulerPages;





import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;




import com.dinnerLab.util.ExcelLib;

public class ProductTestPage {
	
	WebDriver driver;
	private String ProductName;
	private String ProductWeight;
	private String UOM;
	private String Productcode;
	private String PRODUCTDESCRIPTION;
	private String ProductFamily;
	private String Freight;
	private String Insurance;
	private String Duty;
	private String Others;
	private String 	PassorFail;

	
	
	public static boolean newProductstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(ProductTestPage.class);

	public boolean ProductInitialPage1(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("ProductPage");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				ProductName = xllib.getExcelData("ProductPage", i, 0);
				
				ProductWeight = xllib.getExcelData("ProductPage", i, 1);
				
				UOM = xllib.getExcelData("ProductPage", i, 2);
				
				Productcode = xllib.getExcelData("ProductPage", i, 3);
				
				PRODUCTDESCRIPTION =   xllib.getExcelData("ProductPage", i, 4);
				
				ProductFamily =xllib.getExcelData("ProductPage", i, 5);
			
				Freight  =xllib.getExcelData("ProductPage", i, 6);
				
				Insurance =xllib.getExcelData("ProductPage", i, 7);
				
				Duty =xllib.getExcelData("ProductPage", i, 8);
				
				Others =xllib.getExcelData("ProductPage", i, 9);
				
				PassorFail =xllib.writeToExcel("ProductPage", i, 11, "Passed");
				
				

				newProductstatus = createproduct(driver, ProductName,
						ProductWeight,UOM,Productcode,PRODUCTDESCRIPTION,ProductFamily,Freight,Insurance,Duty,Others);
								
						System.out.println("Login Count:" + i);
			}
		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

	public boolean createproduct(WebDriver driver, String ProductName,
			String ProductWeight,String UOM,String Productcode,String PRODUCTDESCRIPTION,String ProductFamily,String Freight,String Insurance,String Duty,String Others) throws InterruptedException {
		
			
			//Click on Product Tab
			Thread.sleep(15000);
			
			driver.findElement(By.xpath("//button[@class=\"slds-button\"]")).click();

			Thread.sleep(5001);
			 
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).sendKeys("products");
			Thread.sleep(5001);
			Actions action=new Actions(driver);
			WebElement el=driver.findElement(By.xpath("(//a[@title=\"Products\"])[2]"));
			action.moveToElement(el);
			action.click();
			action.build().perform();
			Thread.sleep(5000);
		
			//Click on 'New' Button
			driver.findElement(By.xpath("(//a[@class='forceActionLink']/descendant::div)[1]")).click();
			Thread.sleep(5000);
			

             //Click on Product Name
			driver.findElement(
				By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input']/descendant::input)[1]")).sendKeys(ProductName);
					
            //Click on Product Weight
			driver.findElement(
					By.xpath("(//div[@class=\"slds-form-element__control\"])[3]//input")).sendKeys(ProductWeight);
			
			//Click on UOM
			driver.findElement(By.xpath("(//div[@class=\"uiPopupTrigger\"])[1]//a")).click();
			Thread.sleep(5000);
		   
			List<WebElement> lst=driver.findElements(By.xpath("(//div[@class=\"select-options\"])[7]//li"));
			Thread.sleep(5000);
			System.out.println(lst);
			for (WebElement we : lst) {
				String str = we.getText();
				if (str.equalsIgnoreCase(UOM))
				{
					we.click();				}
			}
			
		
			//Click on the Product Code
			driver.findElement(By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input']/descendant::input)[2]")).sendKeys(Productcode);
			Thread.sleep(5000);
			
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			 js1.executeScript("window.scrollBy(0,350)");

			//Click on the Product Description
			driver.findElement(By.xpath("(//textarea)[2]")).sendKeys(PRODUCTDESCRIPTION);
			Thread.sleep(5000);
	
			//Click on the Stock Item Checkbox
			WebElement ele=driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"])[18]/descendant::input"));
			ele.click();
			Thread.sleep(5000);

			
			
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			 js2.executeScript("window.scrollBy(0,350)");
			
			//Click on Freight
			Thread.sleep(5000);
			WebElement ele1=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[1]"));
			Thread.sleep(5000);
			ele1.click();
			ele1.sendKeys(Freight);
			
	
			//Click on Insurance
			WebElement ele2=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[2]"));
			Thread.sleep(5000);
			ele2.click();
			ele2.sendKeys(Insurance);
			
			
			//Click on Duty
			WebElement ele3=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[3]"));
			Thread.sleep(5000);
			ele3.click();
			ele3.sendKeys(Duty);
			
			
			//Click on Other
			WebElement ele4=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[4]"));
			Thread.sleep(5000);
			ele4.click();
			ele4.sendKeys(Others);

			
			//ProductFamily Lookup 
			driver.findElement(By.xpath("(//div[@class='slds-has-flexi-truncate slds-p-horizontal_medium full forcePageBlockItem forcePageBlockItemEdit'])[13]/descendant::a[@class='select']")).click();
			Thread.sleep(5000);
			List<WebElement> lst1=driver.findElements(By.xpath("(//div[@class=\"select-options\"])[7]//li"));
					
			Thread.sleep(5000);
			System.out.println(lst1);
			for (WebElement we : lst1) {
				String str = we.getText();
				if (str.equalsIgnoreCase(ProductFamily))
				{
					we.click();
				}
			}
			
			
			
			  //Click on 'Save' Button
			  driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
			
			  Thread.sleep(10000);
			
			  JavascriptExecutor javascript = (JavascriptExecutor) driver;
			  javascript.executeScript("alert('Product Created Sucessfully');");
			  Thread.sleep(8000);
			  driver.switchTo().alert().accept();
			  
			
		return true;
		

	}
	
	  

}
