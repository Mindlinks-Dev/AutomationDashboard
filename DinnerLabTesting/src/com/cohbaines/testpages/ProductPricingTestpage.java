package com.cohbaines.testpages;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.util.ExcelLib;

public class ProductPricingTestpage {
	WebDriver driver;

	private String ProductName;
	private String Q1STARTRANGE;
	private String Q1ENDRANGE;
	private String Q1PRICE;
	private String PriceBook;
	private String PassorFail;
	public static boolean newProductstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(ProductPricingTestpage.class);

	public boolean ProductPricingInitialPage(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("ProductPricingPage");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				ProductName = xllib.getExcelData("ProductPricingPage", i, 0);
				Q1STARTRANGE = xllib.getExcelData("ProductPricingPage", i, 1);
				Q1ENDRANGE = xllib.getExcelData("ProductPricingPage", i, 2);
				Q1PRICE = xllib.getExcelData("ProductPricingPage", i, 3);
				PriceBook = xllib.getExcelData("ProductPricingPage", i, 4);
				PassorFail =xllib.writeToExcel("ProductPricingPage", i,5,"Pass");
				newProductstatus = createProduct(driver, ProductName,Q1STARTRANGE,
						Q1ENDRANGE, Q1PRICE, PriceBook);

				System.out.println("Login Count:" + i);
			}

		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}

	private boolean createProduct(WebDriver driver,String ProductName, String Q1STARTRANGE,
			String Q1ENDRANGE, String Q1PRICE, String PriceBook) {
		try {
			Thread.sleep(5000);

			// Click on the Product Tab
			driver.findElement(By.xpath("//a[@title='Products']")).click();
			Thread.sleep(5000);
			
			//click on search icon
			
		WebElement Wb=driver.findElement(By.xpath("html/body/div[5]/div[1]/header/div[2]/div[2]/div/div/div[2]/div[2]/div/input"));
		Wb.click();
		Wb.clear();
		Thread.sleep(3000);
		Wb.sendKeys(ProductName);
		
		Thread.sleep(6000);
		WebElement  wb2=driver.findElement(By.xpath("//div[@class='listContent']/descendant::li/descendant::img[@src='https://mindlinks3-dev-ed.my.salesforce.com/img/icon/t4v35/standard/product_120.png']"));
		wb2.click();
	   /*if(wb2.equals(ProductName)){
		  WebElement  wb2=driver.findElement(By.xpath("//div[@class='listContent']/descendant::li/descendant::img[@src='https://mindlinks3-dev-ed.my.salesforce.com/img/icon/t4v35/standard/product_120.png']"));
		wb2.click();
	     }
	else{
		System.out.println("Product not found");
	}*/
			/*for niacin Product
			driver.findElement(By.xpath("//a[@title='niacin']")).click();
			Thread.sleep(5000);*/
		System.out.println("Product Related tab1");
			// Related of the Product
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.findElement(
					By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::ul[@class='tabs__nav']/descendant::li[1]"))
					.click();
			System.out.println("Product Related tab2");
			Thread.sleep(5000);
			// Productpricing new Button
			driver.findElement(
					By.xpath("//div[@class='windowViewMode-normal oneContent active forcePageHost']/descendant::div[@class='container forceRelatedListSingleContainer'][1]/descendant::div[@class='slds-card__header slds-grid']/descendant::div[@class='slds-no-flex']/descendant::div[@title='New']"))
					.click();
			
			//Q1STARTRANGE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[1]"))).sendKeys(Q1STARTRANGE);
			
			

			//Q1ENDRANGE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[2]"))).sendKeys(Q1ENDRANGE);
			
			

			//Q1PRICE for textfield
			driver.findElement(By.xpath(("(//input[@step='1'])[3]"))).sendKeys(Q1PRICE);
			
      
			//PriceBook for textfield

			WebElement Ele=driver.findElement(By.xpath("//div[@class='modal-body scrollable slds-modal__content slds-p-around--medium']//div[@class='slds-grid full forcePageBlockSectionRow'][7]//div[@class='contentWrapper slds-box--border']//div[@class='autocompleteWrapper slds-grow']"));


			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", Ele);
			
			/*
		Ele.submit();
		//Ele.sendKeys("Sales");
		//Ele.sendKeys("Sales unit price");*/
			

			
			System.out.println("Click on the PriceBook");
			/*List<WebElement> lst = driver.findElements(By
					.xpath("//div[@class='undefined lookup__menu uiAbstractList uiAutocompleteList uiInput uiAutocomplete uiInput--default uiInput--lookup']"));
			System.out.println("lookup entry");
			for (WebElement we : lst) {
				String str = we.getText();
				if (str.equalsIgnoreCase(PriceBook))
				{
					we.click();
				}
			}*/

          


 

 


 

 

 /*
// It will return the parent window name as a String
String parent=driver.getWindowHandle();
 
// This will return the number of windows opened by Webdriver and will return Set of St//rings
Set<String>s1=driver.getWindowHandles();
 
// Now we will iterate using Iterator
Iterator<String> I1= s1.iterator();
 
while(I1.hasNext())
{
 
   String child_window=I1.next();
 
// Here we will compare if parent window is not equal to child window then we            will close
 
if(!parent.equals(child_window))
{
driver.switchTo().window(child_window);
 
System.out.println(driver.switchTo().window(child_window).getTitle());
JavascriptExecutor jse = (JavascriptExecutor)driver;
jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
 
driver.close();
}
 
}
// once all pop up closed now switch to parent window
driver.switchTo().window(parent);*/
 


//driver.switchTo().window(child_window);
        	//Actions action = new Actions(driver); 
          //  WebElement ele= driver.findElement(By.xpath("(//div[@class='contentWrapper slds-box--border'])[3]"));
            
            //action.sendKeys(ele, "Sales Price Book");
           // ele.sendKeys("354566");
	
          // ele.click();
		/* To click on the element
		action.
		//action.sendKeys(ele, PriceBook);
		Thread.sleep(100001);
		
	
	

	//ele.sendKeys(PriceBook);
	//System.out.println(PriceBook);
	//Thread.sleep(500001);
	
	//JavascriptExecutor jse = (JavascriptExecutor) driver;
	//jse.executeScript("document.getElementById('elementid').focus();");
		
			//List<WebElement> lst=driver.findElements(By.xpath("html/body/div[5]/div[2]/div[3]/div[2]/div/div[2]/div/div[4]/div/div/div/div/div[7]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li"));
			//System.out.println(lst);
			
		//((WebElement) lst).click();
		
			/*for (WebElement we : lst)
			
			{	
				String str = we.getText();
				System.out.println(str);
				if (str.equalsIgnoreCase(PriceBook))	
				{
					
					Thread.sleep(4000);
					we.click();
				}
			
				
				else{
					
					System.out.println("Not found");
				}
			}*/
			
		/*	--------------Working Code-------------------------------------------
           WebElement Wb4=driver.findElement(By.xpath("(//div[@class='contentWrapper slds-box--border'])[3]"));
    		Wb4.click();
    		Wb4.clear();
    		Thread.sleep(3000);
    		Wb4.sendKeys(PriceBook);
    		
            
            save Button
            System.out.println("Save Button Press");
			driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
			//driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
			
			  // System.out.println("Save Button Not Press");*/
			
		} catch (Exception e) {
			System.out.println("createaccount Unsuccessful");

		}
		return false;

	}

}



