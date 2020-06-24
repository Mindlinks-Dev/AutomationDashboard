package com.alignsandbox.userclass;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alignsandbox.java.com.Base.AlignTestbase;



public class CreateDailyHuddleClass extends AlignTestbase
{

	
	
	@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp",priority=1)
	public static void CreateDailyHuddleGroup(String HuddleGroupName,
			String Note) throws MalformedURLException, InterruptedException {
		try {
			// click on administrator dropdown
			driver.findElement(
					By.xpath("//nav[@class='nav navHeader']/div[1]/div[2]/ul[1]/li[5]"))
					.click();
			Thread.sleep(7000);

			// click on manage huddle group
			driver.findElement(
					By.xpath("//nav[@class='nav navHeader']/div[1]/div[2]/ul[1]/li[5]/div[1]/ul[1]/li[3]"))
					.click();
			Thread.sleep(5000);

			// alert after created
			JavascriptExecutor javascriptb = (JavascriptExecutor) driver;
			javascriptb.executeScript("alert('Create the Daily Huddle');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(6000);

			// click on create new daily huddle group button
			driver.findElement(
					By.xpath("//button[@title='Create New Huddle Group']"))
					.click();
			Thread.sleep(5000);

			// enter the huddle group name
			driver.findElement(By.xpath("//input[@placeholder='Name...']"))
					.sendKeys(HuddleGroupName);
			Thread.sleep(5000);

			// enter the note
			driver.findElement(
					By.xpath("(//ul[@class='form full no-style']/li)[2]/descendant::div[text()='Click or Tap to enter something...']"))
					.sendKeys(Note);
			Thread.sleep(5000);

			// enable the occur on weekend checkbox
			driver.findElement(By.xpath("//div[@class='checker']/span"))
					.click();
			Thread.sleep(5000);

			// click on save button
			driver.findElement(
					By.xpath("//button[@class='button green']/descendant::span[2]"))
					.click();
			Thread.sleep(5000);

			// scroll down alert
			JavascriptExecutor javascript = (JavascriptExecutor) driver;
			javascript.executeScript("alert('Scrolling Down');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(6000);

			// Scroll down to bottom
			driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]"))
					.click();
			Thread.sleep(4000);
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_SPACE);
			rb.keyRelease(KeyEvent.VK_SPACE);
			System.out.println("scroll");
			Thread.sleep(5000);

			// scroll up alert
			JavascriptExecutor javascriptd = (JavascriptExecutor) driver;
			javascriptd.executeScript("alert('Scrolling Up');");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(5000);

			// Scroll up to bottom
			driver.findElement(By.xpath("(//th[@class=\"k-header\"])[5]"))
					.click();
			Thread.sleep(4000);
			Robot rb1 = new Robot();
			rb1.keyPress(KeyEvent.VK_PAGE_UP);
			rb1.keyRelease(KeyEvent.VK_PAGE_UP);
			rb1.keyRelease(KeyEvent.VK_PAGE_UP);
			System.out.println("scroll");

		}

		catch (Exception e) {
			System.out.println("CreateDailyHuddle unsuccessfull");
		}
	}

	// -----------------------Edit Flow-----------------------------------

	@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp", priority = 2)
	public static final void EditDailyHuddleGroup(String HuddleGroupName,
			String EditHuddleGroupName, String EditNote)
			throws MalformedURLException, InterruptedException {
		Thread.sleep(5000);
		
		// alert before edit
		JavascriptExecutor javascripta = (JavascriptExecutor) driver;
		javascripta.executeScript("alert('Edit the Daily Huddle');");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(6000);

		// click on edit button
		String name = HuddleGroupName;		
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+ name+ "')]/following-sibling::td)[1]/descendant::span"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+ name+ "')]/following-sibling::td)[1]/descendant::span")).click();
		Thread.sleep(5000);

		// enter the huddle group name
		driver.findElement(By.xpath("//input[@placeholder='Name...']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Name...']")).sendKeys(EditHuddleGroupName);
		Thread.sleep(5000);

		// enter the note
		driver.findElement(By.xpath("//div[@class='textarea k-widget k-editor k-editor-inline']")).clear();
		Thread.sleep(8000);
		driver.findElement(By.cssSelector("div[data-role='radoloeditor']")).sendKeys(EditNote);
		Thread.sleep(5000);

		// click on save button
		driver.findElement(By.xpath("//button[@class='button green']/descendant::span[2]")).click();
	}

	// -----------------------delete Flow-----------------------------------
	
	@Test(dataProviderClass = AlignTestbase.class, dataProvider = "dp", priority = 3)
	
	public static final void DeleteDailyHuddleGroup(String DeleteHuddleGroup)
			throws MalformedURLException, InterruptedException {
		Thread.sleep(5000);

		// alert before delete
		JavascriptExecutor javascriptc = (JavascriptExecutor) driver;
		javascriptc.executeScript("alert('Delete the Daily Huddle');");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(7000);

		// click on delete button
		String delete = DeleteHuddleGroup;
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+delete+ "')]/following-sibling::td)[2]/descendant::span"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("((//tbody[@role='rowgroup'])[1]/descendant::td[(text()='"+delete+ "')]/following-sibling::td)[2]/descendant::span")).click();
		Thread.sleep(5000);

		// click on yes in "confirm delete" alert
		driver.findElement(
				By.xpath("//div[@class=\"k-content kendo-confirm\"]/descendant::button[text()=\"Yes\"]"))
				.click();
	}

}
	


	

