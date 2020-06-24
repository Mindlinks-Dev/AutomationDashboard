package com.cohbaines.testpages;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.dinnerLab.util.ExcelLib;

public class RecipeManagement {
	
    WebDriver driver;
    private  String Quantity;
    private String Product;
    private String RecipeName;
    private String PassorFail;
	public static boolean newProductstatus;
	int i = 0;
	private static Logger log = Logger.getLogger(RecipeManagement.class);
	public boolean RecipeInitialPage(WebDriver driver)
			throws InvalidFormatException {

		try {
			ExcelLib xllib = new ExcelLib();

			int rowCount = xllib.getRowCount("Recipe Management");
			System.out.println("rowCount:" + rowCount);
			// For loop:Fetch data from excel input configuration file(excel
			// sheet)
			for (i = 1; i <= rowCount; i++) {
				Quantity =xllib.getExcelData("Recipe Management", i, 0);
				Product =xllib.getExcelData("Recipe Management", i,1);
				RecipeName =xllib.getExcelData("Recipe Management", i,2);
				
				PassorFail =xllib.writeToExcel("Recipe Management", i,4,"Pass");
				newProductstatus = createRecipe(driver, Quantity,Product,RecipeName);
						

				System.out.println("Login Count:" + i);
			}

		} catch (Exception e) {
			System.out.println("Product Unsucessful");
			e.printStackTrace();
		}
		return newProductstatus;
	}
	private boolean createRecipe(WebDriver driver,String Quantity,String Product,String RecipeName) throws InterruptedException{
		Thread.sleep(50000);
		//More Tab
		driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_reset slds-context-bar__label-action\"]")).click();
		
		Thread.sleep(5000);
		//Recipe Management
		driver.findElement(By.xpath("//one-tmp-menu-item[27]")).click();
		
		Thread.sleep(5000);
		//New Buttton
		driver.findElement(By.xpath("(//header[@class='slds-card__header slds-grid']//button)[5]")).click();
		
		Thread.sleep(8000);
		//Enter the Quantity
		Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='uiInput uiInputNumber uiInput--default uiInput--input']/input")));
        actions.click();
        actions.sendKeys(Quantity);
        actions.build().perform();
		
        Thread.sleep(8000);
        //Enter the Recipe Name
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//div[@class='slds-form-element__control slds-grow']")));
        action.click();
        action.sendKeys(RecipeName);
        action.build().perform();
        
        Thread.sleep(8000);
        //Enter the Product Lookup
        
        driver.findElement(By.xpath("//div[@class=\"slds-form-element__control\"]")).click();
		   Reporter.log("clicks on account icon");
		// going into the look up method of account
		//lookupDataSelect(driver, Product);

        
        
		
       
		 System.out.println("createrecipe Unsuccessful");

			
			return false;
	}
	
	
	

}
