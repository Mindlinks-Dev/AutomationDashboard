package com.src.test.java.com.cohtestcases;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.src.test.java.com.Base.Testbase;



public class Create_product extends Testbase {

	@Test(dataProviderClass = Testbase.class, dataProvider = "dp")
	public static void product(String PRODUCTNAME, String TYPE, String ATTRIBUTYPE, String PRODUCTWEIGHT, String PUOM,
			String PMT, String SOURCE,String PRODUCTCODE,String PRODUCTDESCRIPTION) throws MalformedURLException, InterruptedException {
        driver.manage().window().maximize();
		Thread.sleep(15000);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		// products
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='slds-context-bar']/descendant::a[@class='slds-context-bar__label-action'][@title='Products']")))
				.click();

		Thread.sleep(5000l);
		// new button
		driver.findElement(By.xpath("//a[@class='forceActionLink'][@title='New']/ancestor::li")).click();

		// Product text field
		driver.findElement(By.xpath("(//div[@class='slds-form slds-is-editing']/div[1]/descendant::input)[1]"))
				.sendKeys(PRODUCTNAME);

		Thread.sleep(8000l);
		// clicks on dimension text field and selects yes
		//driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
		driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();//---coh
		driver.findElement(By.xpath("//a[@title=\"Yes\"]")).click();
		Thread.sleep(5000l);
	
		driver.findElement(By.xpath("(//label/descendant::span[text()='Product Code']/following::input)[1]")).sendKeys(PRODUCTCODE);
		
		
		driver.findElement(By.xpath("(//textarea)[1]")).sendKeys(PRODUCTDESCRIPTION);
		
		
		Thread.sleep(5000l);
		// Sendkeys("//input[@id=\"1862:0\"]","spn-001");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(5000l);
		// clicks on stock item
		driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[2]")).click();

		// clicks on save
		Click("(//span[text()=\"Save\"])[2]");

		Thread.sleep(8000l);
		
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

		// goes to related

		Thread.sleep(7000);
		JavascriptExecutor jsw = (JavascriptExecutor) driver;
		jsw.executeScript("window.scrollBy(0,450)", "");
		Thread.sleep(2000);
		// clicks on add standard price book button
		driver.findElement(By.xpath("//div[text()=\"Add Standard Price\"]")).click();
		Thread.sleep(5000);
		// enters amount by calling amount method
		Amount("1.5");

		// clicks on add to price book
		Thread.sleep(7000);

		
		log.debug("product creation sucessfull");

	}

	public static void pricebook(String pricebook) throws InterruptedException {

		select("(//select)[1]", pricebook);

		Thread.sleep(5000);
		select("(//select)[2]", "GBP");
		Click("//span[text()=\"Next\"]");
		Thread.sleep(5000);

	}

	public static void Amount(String price) throws InterruptedException {

		Click("(//input[@aria-required=\"true\"])[3]");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@aria-required=\"true\"])[3]")).clear();
		Thread.sleep(2000);
		sendkeys("(//input[@aria-required=\"true\"])[3]", price);

		Click("(//span[text()=\"Save\"])[2]");
	}

	
	
	public static void ADDtoPriceBook() throws InterruptedException {

		List<WebElement> multi1 = driver.findElements(By.xpath("//div[text()=\"Add to Price Book\"]"));
		int size2 = multi1.size();
		for (int x = 1; x <= size2; x++) {

			if (driver.findElement(By.xpath("(//div[text()=\"Add to Price Book\"])[" + x + "]")).isDisplayed()) {
				Thread.sleep(6000);
				driver.findElement(By.xpath("(//div[text()=\"Add to Price Book\"])" + "[" + x + "]")).click();
				// driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])[x]")).click();
			}
		}
	}
}
