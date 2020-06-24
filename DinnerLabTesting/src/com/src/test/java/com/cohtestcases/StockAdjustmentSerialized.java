package com.src.test.java.com.cohtestcases;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.src.test.java.com.Base.Testbase;

public class StockAdjustmentSerialized extends Testbase {

	@Test(dataProviderClass=Testbase.class,dataProvider="dp")
	public static void SAfinancial(String Product,String Reasoncode,String Adjusttype,String Adjustedqty,String Unitprice) throws InterruptedException {

		driver.manage().window().maximize();
		try {

			//if the page is in  classic mode it clicks on  on the button
			WebElement Lightening = driver.findElement(By.xpath("//input[@value=\"Try Lightning Experience Now\"]"));

			if (Lightening.isDisplayed()) {

				Lightening.click();

			}
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
			System.out.println("Page is in Lightening only");
		}

		
		//Clicks on applauncher button
		//Thread.sleep(5000);
		//Click("//one-app-launcher-header[@class=\"slds-icon-waffle_container\"]");

		//
		Thread.sleep(6000);
		//Click("//div[@title=\"Stapp\"]");

		Thread.sleep(5000);

		
		
		
		
		Click("//div[@class=\"autocompleteWrapper slds-grow slds-form-element__control\"]/descendant::input");

		Thread.sleep(3000);
		sendkeys("//div[@class=\"autocompleteWrapper slds-grow slds-form-element__control\"]/descendant::input",
				Product);

		// click("Bom Product-001");Samsung 3A-01;Minky741;DEV 0002
		
		
		Thread.sleep(3000l);

		Headersearch(Product);

		//System.out.println("No Produt found of such name");

		System.out.println("enters related");
		// goes to related
		List<WebElement> multi = driver.findElements(By.xpath("//a[@class=\"tabHeader\"][@tabindex=\"-1\"]"));
		int size = multi.size();
		for (int x = 1; x <= size; x++) {

			if (driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]"))
					.isDisplayed()) {
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]")).click();
				// driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])[x]")).click();
			}
		}

		
		
		
		// Scroll's upto the element Inventory location Products

		//WebElement ILPelement = driver.findElement(By.xpath("//span[@title=\"Inventory Location Products\"]"));

		// here it checks whether number of ILP present in the list and
		// fetch the value
		String ILPCOUNT = driver
				.findElement(By.xpath("(//span[@title=\"Inventory Location Products\"]/following::span)[1]"))
				.getAttribute("title");

		System.out.println(ILPCOUNT);

		Thread.sleep(5000);
		JavascriptExecutor jsw1 = (JavascriptExecutor) driver;
		// jsw1.executeScript("arguments[0].scrollIntoView(true);", ILPelement);
		jsw1.executeScript("window.scrollBy(0,350)", "");
		// if ILP's count is 0 returns a msg as No ILP's found1
		if (!ILPCOUNT.equals("(0)")) {

			WebElement ILP = driver.findElement(
					By.xpath("(//span[text()=\"Inventory Location Products\"]/ancestor::article/descendant::a)[2]"));
			ILP.getText();
			System.out.println(ILP.getText());
			Thread.sleep(5000);

			
			
			
			Click("(//span[text()=\"Inventory Location Products\"]/ancestor::article/descendant::a)[2]");
			Thread.sleep(10000);
			JavascriptExecutor execute = (JavascriptExecutor) driver;
			execute.executeScript("alert(' ILP for this product is " + ILP.getText() + "');");
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			Thread.sleep(5000);

			
			
			// JavascriptExecutor jsw = (JavascriptExecutor) driver;
			// jsw.executeScript("arguments[0].scrollIntoView(true);",
			// ILPCOUNT);

			
			//clicks on  Stock Adjustment Button
			Thread.sleep(3000);
			Click("//div[text()=\"Stock Adjustment\"]");

			Thread.sleep(3000);

			
			
			//Stock Adjustment type increase or decrease
			select("(//select)[1]", Adjusttype);

			//Enters the adjusted qty
			Thread.sleep(3000);
			sendkeys("(//label[text()=\"Adjusted Quantity\"]/following::input)[1]", Adjustedqty);

			
			//Enters the reason code
			Thread.sleep(3000);
			select("(//select)[2]", Reasoncode);

			Thread.sleep(3000);

			//Selects the adjusted date
			Click("//label[text()=\"Adjusted Date\"]/following::input[1]");

			
			Thread.sleep(4000);
			Click("(//span[text()=\"27\"])[2]");

			
			
			System.out.println("Clicking Save button");

			Thread.sleep(5000);

			
			if(Adjusttype.equals("Increase Stock"))
			{
			
				//enters unit price
				Thread.sleep(3000);
				sendkeys("(//label[text()=\"Product Unit Price\"]/following::input)[1]",Unitprice);

				
				
				
			//Listout all accordion elements
			List<WebElement> serialnumberbutton = driver.findElements(By.xpath("//tr[@class=\"stapplinkStock_AdjustmentsChild2\"]"));
			System.out.println(serialnumberbutton.size());

			Iterator<WebElement> itr = serialnumberbutton.iterator();
			// Numeric value of Serial number
			int j= 1;
			char alpha=65;

			System.out.println(alpha);
			while(itr.hasNext()){

			driver.findElement(By.xpath("//tr[@class=\"stapplinkStock_AdjustmentsChild2\"]["+j+"]/descendant::input[3]")).sendKeys(""+alpha+j);
			   System.out.println(itr.next());
			  
			   j++;
			   alpha++;
			   
			}                        
			
			}
			else if (Adjusttype.equals("Reduce Stock")) {
				
				
				int reduceqty=Integer.parseInt(Adjustedqty);
				
				//driver.findElement(By.xpath("//table/descendant::tbody/descendant::input"));
				
				
				for (int i=1;i<=reduceqty;i++) {
					
					
					
					driver.findElement(By.xpath("(//table/descendant::tbody/descendant::input)["+i+"]")).sendKeys("1");
					
					
					
				}
				
				
				
				
			}
			/*//int w=0;
			for(int k=1;k<=myalphalist.length;k++)
			{

			driver.findElement(By.xpath("(//div[@title=\"Stage\"]/following::input)["+k+"]")).sendKeys(" "+myalphalist[k-1]);
			   System.out.println(k);
			   System.out.println(itr.next());

			  
			   //j++;
			//  w++;
			}*/
			
			
			WebElement SaveButton = driver.findElement(By.xpath("//button[text()=\"Save\"]"));

			jsw1.executeScript("arguments[0].scrollIntoView(true);", SaveButton);

			
			
			//clicks on save Button
			Actions act = new Actions(driver);
			act.moveToElement(SaveButton);
			act.click();
			act.build().perform();
			
			
			Thread.sleep(3000);
			try {
			WebElement ERROR =driver.findElement(By.xpath("//h4[text()=\"Error\"]"));
			if(ERROR.isDisplayed())
			{
						System.out.println("Duplicate serial number exists");
			}
			
			
			}
			catch(Exception E) {
				
			}
			
			
			// Click("//button[text()=\"Save\"]");

			System.out.println("Clicked Save button");
			
			
			
		}

		
		 else {
			 
			 System.out.println("No ILP's found1"); Thread.sleep(5000);
		 JavascriptExecutor execute = (JavascriptExecutor) driver;
		 execute.executeScript("alert('No ILP for this product');");
		 Thread.sleep(5000); 
		 
		 driver.switchTo().alert().accept();
		 
		 }
		 
		

		Thread.sleep(3000l);

	}

}