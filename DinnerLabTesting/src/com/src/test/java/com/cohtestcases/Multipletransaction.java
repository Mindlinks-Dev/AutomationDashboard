package com.src.test.java.com.cohtestcases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import com.src.test.java.com.Base.Testbase;

public class Multipletransaction extends Testbase {
	public static String TDNumber = "TD-033858";
	public static int Length;
	public static WebElement frame2;

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 1)
	public static void transactionSR(String SLNO, String PURCHASEORDER, String ACCOUNT, String CONTACT,
			String PRICEBOOK, String TAXTREATMENT) throws InterruptedException {

		driver.manage().window().maximize();
		// clicks on Transactiontab
		Click("//div[@class=\"bBottom\"]/descendant::span[text()='Transactions']");
		Thread.sleep(3000l);
		// click on new button
		Click("//a[@class='forceActionLink'][@title='New']/ancestor::li");
		// identifying frame and switch to frame
		WebElement frame = driver.findElement(By.xpath("//iframe[@allowfullscreen=\"true\"]"));
		driver.switchTo().frame(frame);
		System.out.println(" switching  to frame");
		Thread.sleep(8000l);
		// entering purchase order(1)

		driver.findElement(By.xpath("(//span[@class=\"lookupInput\"]/input)[1]")).sendKeys(PURCHASEORDER);
		Reporter.log("enters order");
		// clicks on account look up(img)
		Click("//img[@title='Account Lookup (New Window)']");
		Reporter.log("clicks on account icon");
		// going into the look up method of account
		lookupDataSelect(driver, ACCOUNT);
		// switching back to main frame from lookup window
		Thread.sleep(4000l);
		driver.switchTo().frame(frame);
		
		 System.out.println(" switching to frame after account look up");
		 Thread.sleep(3000l);
		 
		 String CONTACT1 = CONTACT; // clicks on contact and calls lookup
		 Click("//img[@title='Contact Lookup (New Window)']");
		 lookupDataSelect(driver, CONTACT1); Reporter.log("enters contact");
		 driver.switchTo().frame(frame);
		  System.out.println(" switching  to frame afterccontact lookup"); String
		 PRICEBOOK1 = PRICEBOOK; // clicks on pricebook icon Thread.sleep(3000l);
		 Click("//img[@title='Price Book Lookup (New Window)']");
		 lookupDataSelect(driver, PRICEBOOK1); Reporter.log("enters pricebook");
		 
		 
		 
		 // clicks on tax treatment look up 
		 driver.switchTo().frame(frame);
		 System.out.println(" switching  to frame after price book look up");
		 Thread.sleep(3000l); String TAXTREATMENT1 = TAXTREATMENT;
		 Click("//img[@title='Tax Treatment Lookup (New Window)']");
		 lookupDataSelect(driver, TAXTREATMENT1); Reporter.log("enters taxtreatment");
		 // clicks on payment terms
		 
		 Thread.sleep(3000l);
		 
		
		 driver.switchTo().frame(frame);
		 

		// clicks on save button
		Click("(//input[@value=\"Save\"])[1]");

		driver.switchTo().defaultContent();
		Thread.sleep(3000l);

		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
		System.out.println(frames);
		for (WebElement WE1 : frames) {
			System.out.println(WE1);
		}

		driver.switchTo().frame(1);
		System.out.println(TDNumber);

		// excel.setCellData("stockreceiving", "TRANSACTIONNO", 2, TDNumber);
		Thread.sleep(6000l);

	}

	public static void lookupDataSelect(WebDriver driver, String Account) throws InterruptedException {

		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);

		Iterator<String> itr = handles.iterator();
		String PW = itr.next();
		String SW = itr.next();
		// String CW = itr.next();
		// new child window opens to enter account
		driver.switchTo().window(SW);
		Thread.sleep(5000);
		WebElement frame1 = driver.findElement(By.xpath("//frame[@id=\"searchFrame\"]"));
		driver.switchTo().frame(frame1);

		// Search Text field to enter account
		Actions actions = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//input[@name=\"lksrch\"]"));
		// Thread.sleep(3000l);
		actions.moveToElement(ele);
		actions.click();// clicks on search option
		driver.findElement(By.xpath("//input[@name=\"lksrch\"]")).clear();

		actions.sendKeys(Account);// enters value
		actions.build().perform();
		Thread.sleep(2000);
		// clicks on Go button
		driver.findElement(By.xpath("//input[@title='Go!']")).click();// Go
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		// switch to child window
		System.out.println(" shifting to default ");

		Thread.sleep(5000);

		// enters into searching tabel
		System.out.println("switching to 2nd  frame");
		WebElement frame2 = driver.findElement(By.xpath("//frame[@id=\"resultsFrame\"]"));
		driver.switchTo().frame(frame2);
		System.out.println("switched to the element");

		Thread.sleep(5000);
		System.out.println("searching account");
		try {
			// clicks on the account in the tabel
			// if
			// (driver.findElement(By.xpath("((//table)[2]/descendant::a)[1]")).isDisplayed())//
			// ---live
			if (driver.findElement(By.xpath("((//table)[5]/descendant::a)[4]")).isDisplayed())// ---coh
			{
				System.out.println("clicked the element");
				Thread.sleep(3000);
				// driver.findElement(By.xpath("((//table)[2]/descendant::a)[1]")).click();--live
				driver.findElement(By.xpath("((//table)[5]/descendant::a)[4]")).click();// --coh

				System.out.println("clicked the element");
			}

		} catch (Exception e) {
			System.out.println("account is not present");
		}
		
		//Click
		try {
			// if (driver.findElement(By.xpath("//table/descendant::a")).isDisplayed()) {

			if (driver.findElement(By.xpath("(//table/descendant::a)[8]")).isDisplayed()) {
				System.out.println("enters contact  s element");
				Thread.sleep(1000l);

				// driver.findElement(By.xpath("//table/descendant::a")).click();//--live3

				driver.findElement(By.xpath("(//table/descendant::a)[8]")).click();// --coh

			}
		}

		catch (Exception e) {
			
			
			System.out.println("contact is not present");
		}

		// clicks on purchase order price book , tax treatment
		try {
			// clicks on the account in the tabel
			// if
			// (driver.findElement(By.xpath("((//table)[2]/descendant::a)[1]")).isDisplayed())//
			// ---live
			if (driver.findElement(By.xpath("//table/descendant::a")).isDisplayed()) {
				
				
				driver.findElement(By.xpath("//table/descendant::a")).click();// --live3

				System.out.println("clicked the element");
			}

		} catch (Exception e) {
			
			System.out.println(" tax treatment is not present");
		}

		driver.switchTo().window(PW);
		Thread.sleep(2000l);
	}

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)

	public static void transactionorderline(String SLNO, String PRODUCT2, String QTY, String taxcode)
			throws InterruptedException {
		Thread.sleep(2000l);
		// clicks on nextitem button
		Click("(//div[@class=\"pbHeader\"])[2]/descendant::input");
		Thread.sleep(3000l);
		Thread.setDefaultUncaughtExceptionHandler(null);

		// click on product text field and sends the product.
		Actions actions2 = new Actions(driver);
		WebElement ele3 = driver.findElement(By.xpath("//div[@class=\"requiredInput\"]/descendant::span"));
		actions2.moveToElement(ele3);
		actions2.click();
		actions2.sendKeys(PRODUCT2);
		actions2.build().perform();
		Thread.sleep(3000l);

		WebElement ele5 = driver.findElement(By.xpath("(//input[@type=\"text\"])[5]"));
		Thread.sleep(3000l);
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(ele5);
		Thread.sleep(2000l);
		actions1.click();
		ele5.clear();
		actions1.sendKeys(QTY);
		actions1.build().perform();

		Thread.sleep(6000l);
		WebElement ele4 = driver.findElement(By.xpath("(//input[@type=\"text\"])[3]"));
		Thread.sleep(3000l);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele4);
		Thread.sleep(2000l);
		actions.click();
		ele4.clear();
		actions.sendKeys(QTY);
		actions.build().perform();

		Thread.sleep(5000l);

		// clicks on tax code
		WebElement taxcode4 = driver.findElement(
				By.xpath("//select[@id='j_id0:TheForm:DocumentItem:j_id276:currentDocumentItemTaxCodeField_Edit']"));
		Thread.sleep(2000l);
		taxcode4.click();

		Thread.sleep(4000l);

		String code1 = taxcode;
		taxcode4.sendKeys(code1);

		/*System.out.println("//option[text()=" + code1 + "]");
		Thread.sleep(5000l);

		driver.findElement(By.xpath("//option[text()=\"" + code1 + "\"]")).click();*/

		// option[text()="Exempt UK"]
		driver.switchTo().defaultContent();

		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
		System.out.println(frames);
		for (WebElement WE1 : frames) {
			System.out.println(WE1);
		}

		driver.switchTo().frame(1);

		Thread.sleep(6000l);
		// clicks on save button
		driver.findElement(By.xpath("(//input[@value=\"Save\"])[1]")).click();

		Thread.sleep(8000l);

	}

}
