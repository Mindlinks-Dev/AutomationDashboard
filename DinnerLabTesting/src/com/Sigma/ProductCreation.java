package com.Sigma;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ProductCreation extends Testbase {
	
	// ---------------------------------------Create Product---------------------------------------------------

		@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 1)
		public static void product(String PRODUCTTYPE, String ProductName, String AttributeType, String ReorderQuantity,
				String ThresholdQuantity, String MinimumQuantity, String SellingPrice, String ListPrice, String BuyingPrice,
				String Discount, String VENDOR) throws InterruptedException {
			Thread.sleep(20000);
			driver.manage().window().maximize();

			// app launcher
			driver.findElement(By.xpath(OR.getProperty("AppLauncher"))).click();
			Thread.sleep(8000);

			// click on SigmaERP
			driver.findElement(By.xpath(OR.getProperty("SigmaErp"))).click();
			Thread.sleep(8000);

			// product tab
			driver.findElement(By.xpath(OR.getProperty("Prodtab"))).click();
			Thread.sleep(5000);

			// new
			driver.findElement(By.xpath(OR.getProperty("Newbutton"))).click();
			Thread.sleep(4000);
			System.out.println("printing" + PRODUCTTYPE);

			// product type
			if (PRODUCTTYPE.equals("BOM")) {
				System.out.println("printing" + PRODUCTTYPE);
				driver.findElement(By.xpath(OR.getProperty("Bom"))).click();

			} else {

				driver.findElement(By.xpath(OR.getProperty("Individual"))).click();
			}
			Thread.sleep(5000);

			// next button
			driver.findElement(By.xpath(OR.getProperty("Next"))).click();
			Thread.sleep(5000);

			// product name
			driver.findElement(By.xpath(OR.getProperty("Prodname"))).sendKeys(ProductName);
			Thread.sleep(4000);

			// attribute type
			WebElement we1 = driver.findElement(By.xpath(OR.getProperty("Attributetype")));
			Actions a = new Actions(driver);
			a.moveToElement(we1);
			a.click();

			List<WebElement> lst = driver.findElements(By.xpath(OR.getProperty("AttributetypeList")));
			for (WebElement we : lst) {
				if (we.getText().contains(AttributeType)) {
					Thread.sleep(5000);
					we.click();
				}
			}
			Thread.sleep(8000);

			// reorder qty
			driver.findElement(By.xpath(OR.getProperty("ReorderQty"))).sendKeys(ReorderQuantity);
			Thread.sleep(4000);

			// threshold qty
			driver.findElement(By.xpath(OR.getProperty("ThresholdQty"))).sendKeys(ThresholdQuantity);
			Thread.sleep(4000);

			// minimum qty
			driver.findElement(By.xpath(OR.getProperty("MinQty"))).sendKeys(MinimumQuantity);
			Thread.sleep(4000);

			// selling price
			driver.findElement(By.xpath(OR.getProperty("SellingPrice"))).sendKeys(SellingPrice);
			Thread.sleep(4000);

			// average buying price
			driver.findElement(By.xpath(OR.getProperty("AvgBuyingPrice"))).sendKeys(BuyingPrice);
			Thread.sleep(4000);

			// list price
			driver.findElement(By.xpath(OR.getProperty("ListPrice"))).sendKeys(ListPrice);
			Thread.sleep(4000);

			// discount
			driver.findElement(By.xpath(OR.getProperty("Discount"))).clear();
			driver.findElement(By.xpath(OR.getProperty("Discount"))).sendKeys(Discount);
			Thread.sleep(4000);

			// save
			driver.findElement(By.xpath(OR.getProperty("Save"))).click();
			Thread.sleep(4000);

			// related link
			List<WebElement> multi = driver.findElements(By.xpath(OR.getProperty("RelatedLink")));
			int size = multi.size();
			for (int x = 1; x <= size; x++) {

				if (driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]"))
						.isDisplayed()) {
					Thread.sleep(5000);
					driver.findElement(By.xpath("(//a[@class=\"tabHeader\"][@tabindex=\"-1\"])" + "[" + x + "]")).click();

				}
			}

			// scroll down
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			Thread.sleep(8000);

			// vendor product
			driver.findElement(By.xpath(OR.getProperty("VendorProd"))).click();
			Thread.sleep(4000);

			// vendor name
			WebElement vn = driver.findElement(By.xpath(OR.getProperty("VendorName")));
			Thread.sleep(5000);

			Actions act = new Actions(driver);
			act.click(vn);
			act.sendKeys(VENDOR);
			Thread.sleep(2000);
			act.build().perform();
			Thread.sleep(8000l);
			driver.findElement(By.xpath("//div[@class=\"listContent\"]")).click();

			// buying price
			driver.findElement(By.xpath(OR.getProperty("BuyingPrice"))).sendKeys(BuyingPrice);
			Thread.sleep(4000);

			// save
			driver.findElement(By.xpath(OR.getProperty("SaveVendor"))).click();
			Thread.sleep(8000l);

			System.out.println("Product Created");

		}

}
