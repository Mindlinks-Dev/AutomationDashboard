package com.ManufacturingRun.SanityCheck;

import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dinnerLab.util.ExcelLib;
import com.src.test.java.com.Base.Testbase;

public class StappManufacturingRun extends Testbase
{


	//@Test(priority=1)
	

	  
	public static void AppLaucher() throws InterruptedException
	{
	
   
		try{
			
				Thread.sleep(8000);
			
			     
				driver.findElement(By.xpath("//one-app-launcher-header[@class=\"slds-icon-waffle_container\"]")).click();

				Thread.sleep(5001);
				 
				driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).sendKeys("recipe management");
				Thread.sleep(5001);
				Actions action=new Actions(driver);
				WebElement ele1=driver.findElement(By.xpath("(//a[@title=\"Recipe Management\"])[2]"));
				action.moveToElement(ele1);
				action.click();
				action.build().perform();
				Thread.sleep(5000);
				//New Buttton
		//driver.findElement(By.xpath("(//header[@class='slds-card__header slds-grid']//button)[5]")).click();
				
			
		}
	 
		catch(Exception exp){
		
		
		}
		
		
		
	    }
	
	


//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
	


	
	public static void RecipeMainPage(String Quantity,String Product,String RecipeName,String Company,String Status) throws InterruptedException, InvalidFormatException{

	
	Thread.sleep(5001);
    //Enter the RecipeName
    System.out.println("New Button");
    driver.findElement(By.xpath("//button[text()=\"New\"]")).click();
    
	Thread.sleep(5001);
    //Enter the RecipeName
    System.out.println("RecipeName");
    driver.findElement(By.xpath("//article[@class=\"slds-card\"]/descendant::input[1]")).sendKeys(RecipeName);
    
    Thread.sleep(5000);
    //Enter the Quantity
    System.out.println("Quantity");
    driver.findElement(By.xpath("//article[@class=\"slds-card\"]/descendant::input[4]")).sendKeys(Quantity);
    
    //Company Field
    driver.findElement(By.xpath("//article[@class=\"slds-card\"]/descendant::input[2]")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//article[@class=\"slds-card\"]/descendant::input[2]")).sendKeys(Company);
    Thread.sleep(3000);
    driver.findElement(By.xpath("(//ul[@class=\"slds-lookup__list\"])[1]/li[1]")).click();
    
    Thread.sleep(5000);
    //Enter the Product Lookup
    System.out.println("Product");
    driver.findElement(By.xpath("//article[@class=\"slds-card\"]/descendant::input[3]")).click();
    driver.findElement(By.xpath("//article[@class=\"slds-card\"]/descendant::input[3]")).sendKeys(Product);
    Thread.sleep(5000);
    driver.findElement(By.xpath("(//ul[@class=\"slds-lookup__list\"])[2]/li[1]")).click();
 	
 

     //Status field
  	driver.findElement(By.xpath("//select[@class=\"slds-select\"]")).click();
  	Thread.sleep(5000);
  	try{
	      Actions action4=new Actions(driver);
	      List<WebElement> lst1=driver.findElements(By.xpath("//select[@name=\"select1\"]/option"));
	      for (WebElement we : lst1) {
	      String str = we.getText();
	       	System.out.println(str);
	    	if (str.equalsIgnoreCase(Status))
		   	{
		        Thread.sleep(5000);
		   	we.click();
		   	action4.click();
		   	action4.build().perform();
		   	}
		   	}
	       }
	       catch(Exception exp)
	       {
	    	   
	       }
  	
  	
    Thread.sleep(5000);
 //Save Button
 driver.findElement(By.xpath("//button[text()=\"Save Recipe\"]")).click();
 Thread.sleep(5000);

 
 WebElement ele = driver.findElement(By.xpath("//article[@class=\"slds-card\"]/descendant::input[1]"));
 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", ele);
 

 //error message 

 //To Open the Created Recipe
 Thread.sleep(5000);
 driver.findElement(By.xpath("((//table[@class=\"slds-table slds-table_bordered slds-table_cell-buffer\"])[5]/descendant::a)[1]")).click(); 
 System.out.println("Test Passed");
 
}

	
	

//@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 3)

 public static void RecipeProducts(String Quantity,String Product) throws InterruptedException, InvalidFormatException{
	Thread.sleep(5001);
	 
	
	
	//RecipeManagementTestpage recipe= new  RecipeManagementTestpage();
	RecipeButton(driver);
	    
    Thread.sleep(5000);
	
       //Enter the Quantity
       System.out.println("Enter the Quantity");
       driver.findElement(By.xpath("(//div[@class=\"slds-card__body\"])[6]/descendant::input[@class=\"input uiInput uiInputNumber uiInput--default uiInput--input\"]")).sendKeys(Quantity);
       Thread.sleep(5000);
       
       
       System.out.println("Enter the Product");
       //Enter the Product Lookup
         driver.findElement(By.xpath("//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]//input")).click();
         driver.findElement(By.xpath("//div[@class=\"slds-form-element__control slds-input-has-icon slds-input-has-icon--right\"]//input")).sendKeys(Product);
         Thread.sleep(5000);
         //Click on the product in lookup 
    	 Actions action2=new Actions(driver);
       	 driver.findElement(By.xpath("(//div[@role=\"listbox\"])[2]/descendant::li[1]")).click();
        
         // SaveButton();
  	   Thread.sleep(5000);
      driver.findElement(By.xpath("(//div[@class=\"modal-body scrollable slds-modal__content slds-p-around--medium\"]/descendant::button)[3]")).click();
      System.out.println("Clicked on Save Button");   
       
      
      
	
}
static int i = 0;
private static boolean flag =true;
private static boolean flag1 = false;
public void SaveButton() throws InterruptedException{
	//Save Button
	Thread.sleep(5000);
    driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
    
}

public static void RecipeButton(WebDriver driver) throws InterruptedException
{
	
	System.out.println("entered inside button"+flag);
	 if(flag1==true)
	    {
		  System.out.println("enter the flag-clicked on the Additional Product");
	    	Thread.sleep(5000);
	    	JavascriptExecutor jse = (JavascriptExecutor)driver;
	    	jse.executeScript("window.scrollBy(0,250)", "");
	    	driver.findElement(By.xpath("//button[text()=\"Add Additional Product\"]")).click();
	    	System.out.println("enter the flag-clicked on the Additional Product1 ");
	    }
	  if(flag!=true && flag1==false)
	    {
    	  System.out.println("enter the flag-clicked on the Packaging material");
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//button[text()=\"Add Packaging Material\"]")).click();
	    flag1=true;
	    System.out.println("enter the flag-clicked on the Packaging material=1");
	    }
	if(flag==true)
    {
		System.out.println("enter the flag-clicked on the Product component");
		flag=false;
		System.out.println("enter the flag-clicked on the Product component"+flag);
    Thread.sleep(5000);
    driver.findElement(By.xpath("//button[text()=\"Create Product Component\"]")).click();
    }}


@Test(priority=1)

public static void ManufacturingRun() throws InterruptedException
{


	try{
		
			Thread.sleep(8000);
		
		     
			driver.findElement(By.xpath("//one-app-launcher-header[@class=\"slds-icon-waffle_container\"]")).click();

			Thread.sleep(5001);
			 
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control\"]//input)[2]")).sendKeys("Manufacturing Run");
			Thread.sleep(5001);
			Actions action=new Actions(driver);
			WebElement ele1=driver.findElement(By.xpath("(//a[@title=\"Manufacturing Run\"])[2]"));
			action.moveToElement(ele1);
			action.click();
			action.build().perform();
			Thread.sleep(5000);
			
			System.out.println("Test Manufacturing Run");
					
		
	}
 
	catch(Exception exp){
	
	
	}
}




@Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)

public static void ManufacturingRun(String ManufacturingRunName,String Company,String Product,String RunDate,String Status,String Recipe,String RequiredQuantity,String EditStatus,String EditStatus1,String EditStatus2,String	BatchNumber,String ExpirydateofBatch,String	StorageLocation,String Bin,String QuantityMoved) throws InterruptedException, InvalidFormatException
{
	
	System.out.println("Entered1");
	//New Button Field
	Thread.sleep(10000);
	driver.findElement(By.xpath("//header[@class=\"slds-card__header slds-grid\"]/descendant::div[4]/button")).click();
	
	//Manufacturing Run Field
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-grow\"])[1]/input")).sendKeys(ManufacturingRunName);
	
	//Company Field 
	
	Thread.sleep(5000);
	driver.findElement(By.xpath("//button[@title=\"Clear Selection\"]")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//div[@role=\"none\"]/input)[2]")).sendKeys(Company);
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@id=\"input-7-base-combobox\"]/lightning-base-combobox-item [2]")).click();
	
	

	//Product Field
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[@role=\"none\"]/input)[3]")).click();
	driver.findElement(By.xpath("(//div[@role=\"none\"]/input)[3]")).sendKeys(Product);
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div[@id='input-14-base-combobox']/descendant::lightning-base-combobox-item")).click();
   
  
	//Date Field
	Thread.sleep(5000);
	//Date Picker ----Non dynamic paths----------
	driver.findElement(By.xpath("//input[@name=\"stapplink__Run_Date__c\"]")).click();
	
	
	
	//status
    Thread.sleep(5000);
	Select select = new Select(driver.findElement(By.xpath("//select[@class=\" select\"]")));
	select.selectByVisibleText(Status);
    System.out.println("Select class");
	
	//Recipe Lookup
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[@aria-haspopup=\"listbox\"])[3]//input")).click();
	driver.findElement(By.xpath("(//div[@aria-haspopup=\"listbox\"])[3]//input")).sendKeys(Recipe);
    Thread.sleep(5000);
    driver.findElement(By.xpath("(//div[@id=\"input-9-base-combobox\"]//lightning-base-combobox-item)[2]")).click();
   /* Actions action= new Actions(driver);
    List<WebElement> lst1= driver.findElements(By.xpath("(//div[@id=\"input-9-base-combobox\"]//lightning-base-combobox-item)[1]"));
    Thread.sleep(5000);
    System.out.println(lst1);
	for (WebElement we : lst1) {
		String str = we.getText();
		if (str.equalsIgnoreCase(Recipe))
		{
			we.click();	
			action.click();
    	   	action.build().perform();
			}
	}*/
	
	
	//Required Quantity field
	Thread.sleep(5000);
	System.out.println("Entered Required Quantity");
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-grow\"])[3]/input")).click();
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element__control slds-grow\"])[3]/input")).sendKeys(RequiredQuantity);
	
	Proceedrun(EditStatus,EditStatus1,EditStatus2,BatchNumber,ExpirydateofBatch,StorageLocation,Bin,QuantityMoved);
	
}
	
	public static void Proceedrun(String EditStatus,String EditStatus1,String EditStatus2,String BatchNumber,String ExpirydateofBatch,String StorageLocation,String Bin,String QuantityMoved) throws InterruptedException, InvalidFormatException
	{
	//Click on calculate Button
	System.out.println("Clicked on calculate");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//button[text()=\"Calculate\"]")).click();
	Thread.sleep(3000);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,500)");
	Thread.sleep(3000);
	js.executeScript("window.scrollBy(0,-500)");

	
	//Click on Save Button
	System.out.println("Clicked on SAVE");
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[@class=\"slds-grid\"]//button)[2]")).click();
	
	//Click on Yes Button
	System.out.println("Clicked on YES");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//div[@class=\"jconfirm-buttons\"]/button[1]")).click();
	
	//Click on AutoReserve Button
	System.out.println("Clicked on AutoReserve Button");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//div[@class=\"jconfirm-buttons\"]/button[2]")).click();
	
	
	//Edit Status 
	System.out.println("Clicked on Status Edited");
	Thread.sleep(5000);
	EditStatus(EditStatus);
	
	//Click on ReserveStock
	System.out.println("Clicked on ReserveStock");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//ul[@role=\"tablist\"]/li[2]/a")).click();
	
	//Click on AutoreserveButton
	System.out.println("Clicked on AutoReserve Button");
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//header[@class=\"slds-card__header slds-grid\"]/div[2])[3]")).click();
	
	//Click on AutoReserve Button
	Thread.sleep(6000);
	System.out.println("Clicked on AutoResrve");
	driver.findElement(By.xpath("//button[text()=\"Auto Reserve-Stock\"]")).click();
	
	//Click on Yes Button
	System.out.println("Clicked on Yes Button");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//div[@role=\"dialog\"]/div[4]/button[1]")).click();
	
	SwicthingtoWindow(driver);

	//Edit the Status to Produce
	EditStatus2(EditStatus2);
	
	Thread.sleep(5000);
	driver.findElement(By.xpath("//ul[@class=\"slds-tabs--default__nav\"]/li[3]/a")).click();

	
	

}
	
	
	
public static void EditStatus(String EditStatus) throws InvalidFormatException, InterruptedException
{
	System.out.println("Edit Status-Entered");
	ExcelLib xllib = new ExcelLib();

	int rowCount = xllib.getRowCount("Manufacturing Run");
	System.out.println("rowCount:" + rowCount);
	
	EditStatus =xllib.getExcelData("Manufacturing Run", i, 6);
	 Thread.sleep(5000);
	 System.out.println("Edit Status");
		Select select = new Select(driver.findElement(By.xpath("//select[@class=\" select\"]")));
		select.selectByVisibleText(EditStatus);
	
}


public static void EditStatus1(String EditStatus1) throws InvalidFormatException, InterruptedException
{
	System.out.println("Edit Status-Entered");
	ExcelLib xllib = new ExcelLib();

	int rowCount = xllib.getRowCount("Manufacturing Run");
	System.out.println("rowCount:" + rowCount);
	
	EditStatus1 =xllib.getExcelData("Manufacturing Run", i, 7);
	 Thread.sleep(5000);
	 System.out.println("Edit Status");
	 Select select = new Select(driver.findElement(By.xpath("//select[@class=\" select\"]")));
	 select.selectByVisibleText(EditStatus1);
	
}

public static void EditStatus2(String EditStatus2) throws InvalidFormatException, InterruptedException
{
	System.out.println("Edit Status-Entered");
	ExcelLib xllib = new ExcelLib();

	int rowCount = xllib.getRowCount("Manufacturing Run");
	System.out.println("rowCount:" + rowCount);
	
	EditStatus2 =xllib.getExcelData("Manufacturing Run", i, 8);
	 Thread.sleep(5000);
	 System.out.println("Edit Status");
	 Select select = new Select(driver.findElement(By.xpath("//select[@class=\" select\"]")));
	 select.selectByVisibleText(EditStatus2);
	
}

public static boolean StockMovement(String BatchNumber,String ExpirydateofBatch,String StorageLocation,String Bin,String QuantityMoved) throws InterruptedException
{
	Thread.sleep(5000);
	System.out.println("Entered Batch Number");
	driver.findElement(By.xpath("((//fieldset[@class=\"slds-form-element\"])[2]//div)[1]/div/div[1]/input")).click();
	driver.findElement(By.xpath("((//fieldset[@class=\"slds-form-element\"])[2]//div)[1]/div/div[1]/input")).sendKeys(BatchNumber);
	
	Thread.sleep(5000);
	System.out.println("Expiry Date");
	driver.findElement(By.xpath("//input[@class=\"slds-input input\"]")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//table/tbody/tr[5]/td[5]/span")).click();
	
	Thread.sleep(5000);
	System.out.println("StorageLocation");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,500)");
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element\"])[3]/descendant::input")).click();
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element\"])[3]/descendant::input")).sendKeys(StorageLocation);
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[@class=\"slds-lookup__menu slds-show\"])[1]/descendant::li[1]")).click();
	
	
	Thread.sleep(10000);
	System.out.println("Bin");
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element\"])[4]/descendant::input")).click();
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element\"])[4]/descendant::input")).sendKeys(Bin);
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//ul[@class=\"slds-lookup__list\"])[2]/li[1]")).click();
	
	
	Thread.sleep(5000);
	System.out.println("QuantityMoved");
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element slds-size_1-of-3\"])[8]/input")).click();
	driver.findElement(By.xpath("(//div[@class=\"slds-form-element slds-size_1-of-3\"])[8]/input")).sendKeys(QuantityMoved);
	
	Thread.sleep(5000);
	System.out.println("Save Button");
	driver.findElement(By.xpath("(//header[@class=\"slds-card__header slds-grid\"])[5]/descendant::button")).click();
	
	
	
	return false;
}



public static void SwicthingtoWindow(WebDriver driver) throws InterruptedException
{
	// Store the current window handle
    String winHandleBefore = driver.getWindowHandle();
    
   // Perform the click operation that opens new window
    driver.findElement(By.xpath("//button[text()=\"View/Download PDF\"]")).click();
    Thread.sleep(8000);
    
	    for(String winHandle : driver.getWindowHandles()){
	    	
	    	System.out.println(winHandle);
	     
    	driver.switchTo().window(winHandle);
	
}
	//Perform the actions on new window
Thread.sleep(10000);

  JavascriptExecutor javascript = (JavascriptExecutor) driver;
  javascript.executeScript("alert('Download/view Button');");
  Thread.sleep(8000);
  driver.switchTo().alert().accept();
  
 JavascriptExecutor js1 = (JavascriptExecutor) driver;
 js1.executeScript("window.scrollBy(0,1000)");
 Thread.sleep(5000);
driver.close();

// Switch back to original browser (first window)
driver.switchTo().window(winHandleBefore);
}

}





	
	
	










