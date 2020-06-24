package com.cohbainies.testclasses;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cohbaines.testpages.ProductPricingTestpage;
import com.dinnerLab.util.ExcelLib;

public class backupclass {

	WebDriver driver;

		private String ProductName;
		private String Q1STARTRANGE;
		private String Q1ENDRANGE;
		private String Q1PRICE;
		private String PriceBook;
		private static boolean Productlist;
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
			driver.findElement(By.xpath("//div[@class='listContent']/descendant::img[@class='icon ']")).click();
		
				/*for niacin Product
				driver.findElement(By.xpath("//a[@title='niacin']")).click();
				Thread.sleep(5000);*/
				// Related of the Product
				driver.findElement(
						By.xpath("//ul[@class='tabs__nav']/descendant::a[1]"))
						.click();
				Thread.sleep(5000);
				// Productpricing new Button
				driver.findElement(
						By.xpath("(//div[@class='actionsContainer']/descendant::div[@title='New'])[1]"))
						.click();
				
				//Q1STARTRANGE for textfield
				driver.findElement(By.xpath(("(//input[@step='1'])[1]"))).sendKeys(Q1STARTRANGE);
				
				

				//Q1ENDRANGE for textfield
				driver.findElement(By.xpath(("(//input[@step='1'])[2]"))).sendKeys(Q1ENDRANGE);
				
				

				//Q1PRICE for textfield
				driver.findElement(By.xpath(("(//input[@step='1'])[3]"))).sendKeys(Q1PRICE);
				System.out.println("Unit Price");
	            Thread.sleep(3000);
				//PriceBook for textfield
				//*WebElement wb=driver.findElement(By.xpath(("(//div[@class='contentWrapper slds-box--border'])[3]")));
				/*wb.click();
				System.out.println("Click on the PriceBook");
				List<WebElement> lst = driver.findElements(By
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
	           
				WebElement wb2=driver.findElement(By.xpath("(//div[@class='contentWrapper slds-box--border'])[3]"));
				wb2.click();
				Thread.sleep(1000);
				List<WebElement> lst=driver.findElements(By.xpath("//div[@role='listbox']/div"));
				
				
				for (WebElement we : lst)
				
				{	
					String str = we.getText();
					if (str.endsWith(PriceBook))
					{
						Thread.sleep(4000);
						we.click();
					}
					
					/*else{
						
						System.out.println("Not found");
					}*/
				}
				driver.findElement(By.xpath("//div[@class='forceModalActionContainer--footerAction forceModalActionContainer']/descendant::button[@title='Save']")).click();
				
			} catch (Exception e) {
				System.out.println("createaccount Unsuccessful");

			}
			return false;

		}

	}



