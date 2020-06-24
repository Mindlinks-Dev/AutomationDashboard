package com.cohbaines.testpages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.dinnerLab.util.ExcelLib;

public class ProductTestPage {
	
	WebDriver driver;
	private String ProductName;
	private String ProductWeight;
	private String CreateDimensionTag;
	private String Productcode;
	private String SKU;
	private String ProductFamily;
	private String Freight;
	private String Insurance;
	private String Duty;
	private String Others;
	private String PassorFail;
	
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
				
				CreateDimensionTag = xllib.getExcelData("ProductPage", i, 2);
				
				Productcode = xllib.getExcelData("ProductPage", i, 3);
				
				SKU =xllib.getExcelData("ProductPage", i, 4);
				
				ProductFamily =xllib.getExcelData("ProductPage", i, 5);
				
				Freight  =xllib.getExcelData("ProductPage", i, 6);
				
				Insurance =xllib.getExcelData("ProductPage", i, 7);
				
				Duty =xllib.getExcelData("ProductPage", i, 8);
				
				Others =xllib.getExcelData("ProductPage", i, 9);
				
				PassorFail =xllib.writeToExcel("ProductPage", i, 10, "Passed");

				
				newProductstatus = createproduct(driver, ProductName,
						ProductWeight, CreateDimensionTag,Productcode,SKU,ProductFamily,Freight,Insurance,Duty,Others);
								
						System.out.println("Login Count:" + i);
			}
		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

	private boolean createproduct(WebDriver driver, String ProductName,
			String ProductWeight, String CreateDimensionTag,String Productcode,String SKU,String ProductFamily,String Freight,String Insurance,String Duty,String Others) throws InterruptedException {
		
			
			//Click on Product Tab
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@title='Products']")).click();
			Thread.sleep(5000);
			//Click on 'New' Button
			driver.findElement(By.xpath("(//a[@class='forceActionLink']/descendant::div)[1]")).click();
			Thread.sleep(000);
			
			
			
             //Click on Product Name
			driver.findElement(
					By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input']/descendant::input)[1]")).sendKeys(ProductName);
				
			
			/*
            //Click on Product Weight
			driver.findElement(
					By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div/div[2]/div/div[4]/div/div[1]/div/div/div[1]/div[2]/div/div/div/input")).sendKeys(ProductWeight);
			
			//Click on the Create dimension tag---coded 
			driver.findElement(By.xpath("//div[@class='modal-body scrollable slds-modal__content slds-p-around--medium']/descendant::div[@class='slds-form-element__control'][3]")).click();
			Thread.sleep(3000);
			//Click on YES in dimension
			driver.findElement(By.xpath("html/body/div[9]/div/ul/li[2]/a")).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click on the Product Code
			driver.findElement(By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input']/descendant::input)[2]")).sendKeys(Productcode);
			Thread.sleep(5000);
			//Click on SKU
			driver.findElement(By.xpath("(//div[@class='uiInput uiInputText uiInput--default uiInput--input']/descendant::input)[3]")).sendKeys(SKU);
			Thread.sleep(5000);
			Click on the Stock Item Checkbox
			WebElement ele=driver.findElement(By.xpath("(//div[@class='uiInput uiInputCheckbox uiInput--default uiInput--checkbox'])[2]"));
			ele.click();
			
			//WebElement ele1=driver.findElement(By.xpath("//div[@class='DESKTOP uiContainerManager']//div[@class='full forcePageBlock forceRecordLayout']/div[2]/div/descendant::div[@class='slds-grid full forcePageBlockSectionRow'][1]/div[1]"));
			
			
			//CheckBox 
			WebElement ele=driver.findElement(By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div/div[2]/div/div[4]/div/div[1]/div/div/div[9]/div[1]/div/div/div/input"));
			//WebElement ele=driver.findElement(By.xpath("div[@class='slds-form-element__control']//input[@type='checkbox'][@data-aura-rendered-by='517:1783;a']"));
			ele.click();
			
			
			Thread.sleep(5000);
			
			WebElement ele1=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[1]"));
			Thread.sleep(5000);
			ele1.click();
			ele1.sendKeys(Freight);
			
			
			WebElement ele2=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[2]"));
			Thread.sleep(5000);
			ele2.click();
			ele2.sendKeys(Insurance);
			
			WebElement ele3=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[3]"));
			Thread.sleep(5000);
			ele3.click();
			ele3.sendKeys(Duty);
			
			WebElement ele4=driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPercent uiInput--default uiInput--input\"]/input)[4]"));
			Thread.sleep(5000);
			ele4.click();
			ele4.sendKeys(Others);
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class='slds-has-flexi-truncate slds-p-horizontal_medium full forcePageBlockItem forcePageBlockItemEdit'])[13]/descendant::a[@class='select']")).click();
			Thread.sleep(5000);
		    //driver.findElement(By.xpath("//div[@data-aura-class=\"uiPopupTarget uiMenuList uiMenuList--default uiMenuList--left uiMenuList--short\"]/div/ul/li")).click();
			List<WebElement> lst=driver.findElements(By.xpath("//div[@data-aura-class=\"uiPopupTarget uiMenuList uiMenuList--default uiMenuList--left uiMenuList--short\"]/div/ul/li"));
			Thread.sleep(5000);
			System.out.println(lst);
			for (WebElement we : lst) {
				String str = we.getText();
				if (str.equalsIgnoreCase(ProductFamily))
				{
					we.click();
				}
			}
			
			
			//driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
			
			
			
			
		//	Click on Freight
			WebElement ele1=driver.findElement(By.xpath("//div[@class='DESKTOP uiContainerManager']//div[@class='full forcePageBlock forceRecordLayout']/div[2]/div/descendant::div[@class='slds-grid full forcePageBlockSectionRow'][1]/div[1]"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			//jse.executeScript("arguments[0].value='(222)222-2222';", ele1);
			jse.executeScript("document.getElementById('672:4517;a').value='555-55-5555';");
			
			
			
			
			
			
			
			
		*/
			
			driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
		return true;
		

	}

}
