package SalesForce.TestPages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;




public class CreatestandardobjectsTestPage extends Testbase
{
	
	
	 @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 7)


	  public static void oppurtunity(String Subject,String DueDate,String Name,String Status) throws InterruptedException, InvalidFormatException
	  {


		    //Opportunity  Tab
			Thread.sleep(10000);
		    System.out.println("Enter Inside opportunity");
		    driver.findElement(By.xpath(OR.getProperty("OpportunityTab"))).click();
		    
		    //click on the oppurtunity record
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("Taskrecord"))).click();
		  
		    //refresh the page
	  	     Thread.sleep(3000);
	  	   System.out.println("refresh event");
			 driver.navigate().refresh();
			 driver.navigate().refresh();
			 Thread.sleep(16000);
			 
			 
	  	    //click on the oppurtunity new task add button
	  	   
	       WebElement Element = driver.findElement(By.xpath(OR.getProperty("TaskAddbutton")));
	       Thread.sleep(5000);
           Element.click();
	  	    
	  	    //click on subject dropdown and select the subject	
	  	    Thread.sleep(10000);	
	  	    System.out.println(driver.findElement(By.xpath(OR.getProperty("Tasklabel"))).getAttribute("data-aura-class"));
	  	    driver.findElement(By.xpath(OR.getProperty("Tasklabel"))).click(); //click on label
	  	    Thread.sleep(8000);
	  	    
	  	    //Scroll Bar till the Oppoetunity
		     Thread.sleep(8000);
		     System.out.println("Scrolling up Till the Page");
		     JavascriptExecutor jse = (JavascriptExecutor) driver;
			 jse.executeScript("window.scrollBy(0,-450)", "");
			 Thread.sleep(10000);
		     
	  	    driver.findElement(By.xpath(OR.getProperty("TaskSubject"))).click();
	  	    Thread.sleep(5000);
	  	    List<WebElement> we = driver.findElements(By.xpath(OR.getProperty("TaskSelectSubject")));
	          for(WebElement ws : we)
	          	if(ws.getText().contains(Subject))
	          	{
	          		ws.getText();
	          		System.out.println("list all subject from dropdown::"+ws);
	          		ws.click();
	          	}
	          	
	          
	          
	          //enter the duedate
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("TaskDuedate"))).sendKeys(DueDate);
	          
	          
	  /*	    //enter the contact
	  	    Thread.sleep(16000);
	  	    driver.findElement(By.xpath("(//div[@class=\"autocompleteWrapper slds-grow\"])[1]")).click();
	  	    //(//div[@class="autocompleteWrapper slds-grow"])[1]/input xpath
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath("//div[@title='"+Name+"']")).click();*/
	  	  
	          
	          //select the status from status dropdown	  	  
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("Taskstatus"))).click();
	  	    Thread.sleep(6000);
	  	    driver.findElement(By.xpath(OR.getProperty("Taskselectstatus"))).click(); 
	          
	  	    
	  		//click on save button
	  	    Thread.sleep(5000);
	  	    driver.findElement(By.xpath(OR.getProperty("TaskSaveButton"))).click();

	  	    //open the created task
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("TaskSelectRecord"))).click();
	  } 		   		    




	         /*
	          * log a call for oppurtunity
	          * code written by spoorthi
	          */

	   @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 8)


	  		public static void oppurtunitycall(String Comments) throws InterruptedException, InvalidFormatException
	  		{


	  	    //click on oppurtunity tab
	          Thread.sleep(16000);
	  	    driver.findElement(By.xpath(OR.getProperty("oppurtunitytab"))).click();
	  	    
	  	    //click on the oppurtunity record
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("Callrecord"))).click();
	  	      
	  	    //refresh the page
			 driver.navigate().refresh();
			 Thread.sleep(15000);
	  	    
	  	    //click on the oppurtunity log on call link	
			 Thread.sleep(5000);
	  	    driver.findElement(By.xpath(OR.getProperty("CallLink"))).click();
	          
	  	    
	  	    //enter the comments text area field
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("CallComments"))).sendKeys(Comments);

/*	  	    
	  	    //Enter the name text field
	  	    Thread.sleep(8000);
	  	   // driver.findElement(By.xpath("(//input[@title=\"Search Contacts\"])[1]")).click();	  	  
	  	    driver.findElement(By.xpath("(//div[@class=\"inputWrapper slds-grid slds-grid_vertical-align-center slds-p-right_x-small\"])[2]")).click();
	  	    Thread.sleep(6000);
	  	    driver.findElement(By.xpath("(//div[@title=\"Tom James\"])[1]")).click();

*/
	  	    //click on save button
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("CallSaveButton"))).click();


	  	    //open the created task
	  	    Thread.sleep(7000);
	  	    System.out.println("123");
	  	    driver.findElement(By.xpath(OR.getProperty("CallSelectRecord"))).click();


	  }

	  	    
	  	    /*
	  	     * New Event for oppurtunity
	  	     * code written by spoorthi
	  	     */
	  		
	 	 @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 9)


	  		public static void oppurtunityevent(String Subject) throws InterruptedException, InvalidFormatException
	  		{


	  	    //click on oppurtunity tab
	          Thread.sleep(16000);
	  	    driver.findElement(By.xpath(OR.getProperty("oppurtunitytab"))).click();
	  	    
	  	    //click on the oppurtunity record
	  	    Thread.sleep(5000);
	  	    driver.findElement(By.xpath(OR.getProperty("Eventrecord"))).click();
	  	      
	  	     
	  	    //refresh the page
			 driver.navigate().refresh();
			 Thread.sleep(15000);
	  	    
	  	    //click on the oppurtunity new event link
	  	    Thread.sleep(9000);
	  	    driver.findElement(By.xpath(OR.getProperty("Eventlink"))).click();

	  	    
	  		 //click on subject dropdown 
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("EventSubject"))).click();	

	  	    
	  	    //click on subject dropdown and select the subject		   
	  	    List<WebElement> we = driver.findElements(By.xpath(OR.getProperty("EventSelectSubject")));
	          for(WebElement ws : we)
	          	if(ws.getText().contains(Subject))
	          	{
	          		ws.getText();
	          		System.out.println("list all subject from dropdown::"+ws);
	          		ws.click();
	          	}
	          	
	          
	          //click on all day event checkbox
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("AlldayEvent"))).click();	
	  		
/*	  		
	  	    //Enter the name text field
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath("(//div[@class=\"inputWrapper slds-grid slds-grid_vertical-align-center slds-p-right_x-small\"])[3]")).click();
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath("//div[@title=\"Albert James\"]")).click();
	  		
	  */		
	  	    //click on save button
	  	    Thread.sleep(8000);
	  	    driver.findElement(By.xpath(OR.getProperty("EventSaveButton"))).click();


	  	    //open the created event
	  	    Thread.sleep(7000);
	  	    driver.findElement(By.xpath(OR.getProperty("EventSelectRecord"))).click();
	  		
	  	
	  		}
	
	
	
   @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 2)
	
	
	public static void CreateLead(String Salutation,String FirstName,String LastName,String Company,String Title,String Phone,String Mobile,String Email,
			String Website,String Street,String State,String City,String PostalCode,String Country) throws InterruptedException, InvalidFormatException{

	
	        Thread.sleep(10000);
	        System.out.println("Enter Inside");
	        //Lead Tab
	        driver.findElement(By.xpath(OR.getProperty("LeadTab"))).click();
	        Thread.sleep(8000);
	        
	        
	        //Enter the New Button
	        System.out.println("Click on the New Button");
	        driver.findElement(By.xpath(OR.getProperty("NewButton"))).click();
	        //Thread.sleep(5000);
	        
	        
	        Thread.sleep(8000);
	         //Enter the Salutation
	        System.out.println("Enter the Quantity");
	        driver.findElement(By.xpath(OR.getProperty("ClickSalutation"))).click();
	 
			Thread.sleep(8000);
			List<WebElement> lst1=driver.findElements(By.xpath(OR.getProperty("SelectSalutation")));
					
			Thread.sleep(5000);
			System.out.println(lst1);
			for (WebElement we : lst1) {
				String str = we.getText();
				if (str.equalsIgnoreCase(Salutation))
				{
					we.click();
				}
			}
	        
	        Thread.sleep(8000);
	       
	       
	         System.out.println("Enter the First Name");
	         //Enter the FirstName
	         driver.findElement(By.xpath(OR.getProperty("FirstName"))).sendKeys(FirstName);

	         Thread.sleep(8000);
	         //Enter the LastName
	       	 driver.findElement(By.xpath(OR.getProperty("LastName"))).sendKeys(LastName);
	        

	       //Enter the Company();
	  	   Thread.sleep(8000);
	       driver.findElement(By.xpath(OR.getProperty("Company"))).sendKeys(Company);
	       
	       //Enter the Title
	      /* Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[4]/input")).sendKeys(Title);
	       
	      //Enter the Phone
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPhone uiInput--default uiInput--input\"])[1]/input")).sendKeys(Phone);
	       
	      //Enter the Mobile
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputPhone uiInput--default uiInput--input\"])[2]/input")).sendKeys(Mobile);
	       
	     //Enter the Email
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("//div[@class=\"uiInput uiInputEmail uiInput--default uiInput--input\"]/input")).sendKeys(Email);   
	       
	      
		 //Enter the Website
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//div[@class=\"uiInput uiInputURL uiInput--default uiInput--input\"]/input")).sendKeys(Website);   
	      
		  
	      //Enter the Street-Address
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//textarea)[1]")).sendKeys(Street);   
	      
	     //Enter the City
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[1]//input")).sendKeys(City);   
	       
	      //Enter the State
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"uiInput uiInputText uiInput--default uiInput--input\"])[6]/input")).sendKeys(State);   
	       
	      //Enter the PostalCode
	      Thread.sleep(5000);
	      driver.findElement(By.xpath("(//div[@class=\"inlineField form-element__control slds-size--4-of-6\"])[2]//input")).sendKeys(PostalCode);   
	      */
	      //Enter the Country
	      Thread.sleep(8000);
	        driver.findElement(By.xpath(OR.getProperty("Country"))).sendKeys(Country);   
	       
	       
	     
	         //Enter the Save
		      Thread.sleep(8000);
		      driver.findElement(By.xpath(OR.getProperty("SaveButton"))).click();   
		       
		      //Click on the Convert Button
		      Thread.sleep(8000);
		      driver.findElement(By.xpath(OR.getProperty("ConvertButton"))).click(); 
		      
		      //ConfirmConvert
		      Thread.sleep(8000);
		      driver.findElement(By.xpath(OR.getProperty("ConfirmConvert"))).click();
	
		      //Enter the "CONVERT"
		      Thread.sleep(8000);		     
		      driver.findElement(By.xpath(OR.getProperty("Convert"))).click();  

		     
		     //Scroll Bar till the Oppoetunity
		     Thread.sleep(8000);
		     System.out.println("Scrolling Down Till the Page");
		     JavascriptExecutor js = (JavascriptExecutor) driver;
		     
		     
		     WebElement Element = driver.findElement(By.xpath("//div[text()=\"Opportunity\"]"));

		       //This will scroll the page till the element is found	
		     js.executeScript("arguments[0].scrollIntoView();", Element);
		      
		      
		
		    
		      //Go to Lead Button
		      Thread.sleep(5000);
		      driver.findElement(By.xpath(OR.getProperty("LeadButton"))).click();   
		      

	
	}
	
	
	
  @Test(priority=1)
  
	public static void AppLaucher() throws InterruptedException
	{
	
   
		try{
			Thread.sleep(25000);
		
			 //click on app launcher image
			System.out.println("Clicked on the AppLaucher");
			driver.findElement(By.xpath(OR.getProperty("app"))).click();
			Thread.sleep(10000);
			 
			
			//click on search button
			driver.findElement(By.xpath(OR.getProperty("search"))).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath(OR.getProperty("search"))).sendKeys("Sales");
			Thread.sleep(5000);
			
			//Select the application
			Actions action=new Actions(driver);
			WebElement ele1=driver.findElement(By.xpath(OR.getProperty("salesapp")));
			action.moveToElement(ele1);
			Thread.sleep(5000);
			action.click();
			action.build().perform();
			Thread.sleep(5000);

			
			
		}
	 
		catch(Exception exp){
		
		
		}
		
		
		
	    }

	
	
	

    @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 3)

  public static void CreateEditAccount(String Description
		) throws InterruptedException, InvalidFormatException
{

    //Account Tab
	Thread.sleep(10000);
    System.out.println("Enter Inside Mindlinks Account");
    driver.findElement(By.xpath(OR.getProperty("AccountTab"))).click();
    
    //Click on Mindlinks
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("SelectRecord"))).click();
    
    try
    {
    		
    System.out.println(driver.findElement(By.xpath(OR.getProperty("Arrowbutton"))).getAttribute("data-aura-class"));
   
    //click on arrow image
    Thread.sleep(8000);
    WebElement element=driver.findElement(By.xpath(OR.getProperty("Arrowbutton")));
    Thread.sleep(5000);
    element.click();
    }
    
    catch(Exception e)
    {
    	//Thread.sleep(8000);
    	System.out.println("Entered the Edit flow");
    	e.printStackTrace();
    	
    
    }
 
    
    //click on arrow mark edit button inside
     try
     {
    Thread.sleep(8000);
	List<WebElement>  lst = driver.findElements(By.xpath(OR.getProperty("EditButton")));	    	
    int Size=	lst.size();

         for(WebElement editfl:lst   )
	
            {
	
	           System.out.println("editfl"+editfl.getText());
	                    if(editfl.getText().equals("Edit"))
	                        {
		
		                       Thread.sleep(3000);
		                       editfl.click();
	                         }		
             }		
      }
     
     catch(Exception e)
     {
 
    	 e.printStackTrace();
     }
     
     
     
    //Enter the Description
   Thread.sleep(8000);
   driver.findElement(By.xpath(OR.getProperty("Description"))).clear();
   driver.findElement(By.xpath(OR.getProperty("Description"))).sendKeys(Description);
  
    
    //Click on Save Button 
    Thread.sleep(5000);
    driver.findElement(By.xpath(OR.getProperty("AccountSaveButton"))).click();
    

}



  @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 4)

public static void CreateEditContact(String Description) throws InterruptedException, InvalidFormatException
{

    //Contact Tab
	Thread.sleep(10000);
    System.out.println("Click on Contact Tab");
    driver.findElement(By.xpath(OR.getProperty("ContactTab"))).click();
    
    //Click on Mindlinks
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("ContactSelect"))).click(); 
    Thread.sleep(6000);
   
    //click on arrow button
    System.out.println(driver.findElement(By.xpath(OR.getProperty("ContactArrowbutton"))).getAttribute("data-aura-class"));
    WebElement element=driver.findElement(By.xpath(OR.getProperty("ContactArrowbutton")));
    Thread.sleep(4000);
    element.click();
    Thread.sleep(8000);
   
    
    //Click on edit 
	List<WebElement>  lst = driver.findElements(By.xpath(OR.getProperty("ContactEditButton")));	    	
    int Size=	lst.size();

        for(WebElement editfl:lst   )
	
              {
	
	             System.out.println("editfl"+editfl.getText());
	                      if(editfl.getText().equals("Edit"))
	                          {
		
		                       Thread.sleep(3000);
		                       editfl.click();
		
	                           }		
               }		
   
        
    //Enter the Description
    Thread.sleep(5000);
    driver.findElement(By.xpath(OR.getProperty("ContactDescription"))).clear();
    driver.findElement(By.xpath(OR.getProperty("ContactDescription"))).sendKeys(Description);
   
     
     //Click on Save Button 
     Thread.sleep(5000);
     driver.findElement(By.xpath(OR.getProperty("ContactSaveButton"))).click();
    

}
  

   @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 5)

public static void CreateEditOpportunity(String Amount) throws InterruptedException, InvalidFormatException
{

    //Opportunity Tab
	Thread.sleep(10000);
    System.out.println("Enter Inside Mindlinks Contact");
    driver.findElement(By.xpath(OR.getProperty("OpportunityTab"))).click();
    
    //Click on Mindlinks
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("OpportunitySelect"))).click();
    Thread.sleep(6000);
   
    
    //click on arrow button
    System.out.println(driver.findElement(By.xpath(OR.getProperty("OpportunityArrowbutton"))).getAttribute("data-aura-class"));
   // WebElement element=driver.findElement(By.xpath("//span[text()='Ms. Tom James']/ancestor::div[@class=\"slds-grid primaryFieldRow\"]/descendant::li[4]"));
    WebElement element=driver.findElement(By.xpath(OR.getProperty("OpportunityArrowbutton")));
    Thread.sleep(4000);
    element.click();
    Thread.sleep(8000);
   
    
  
    //Click on edit 
	List<WebElement>  lst = driver.findElements(By.xpath(OR.getProperty("OpportunityEditButton")));	    	
    int Size=	lst.size();

    for(WebElement editfl:lst)
	
    {
	
	      System.out.println("editfl"+editfl.getText());
	       if(editfl.getText().equals("Edit"))
	             {
		
		            Thread.sleep(3000);
		            editfl.click();

	     }		
       }		
    
   
    //Enter the Amount
    Thread.sleep(5000);
    driver.findElement(By.xpath(OR.getProperty("OpportunityAmount"))).clear();
    driver.findElement(By.xpath(OR.getProperty("OpportunityAmount"))).sendKeys(Amount);
   
     
     //Click on Save Button 
     Thread.sleep(5000);
     driver.findElement(By.xpath(OR.getProperty("OpportunitySaveButton"))).click();
    
}
   
   
		
     /*
      * case creation and edit
      * written by Pooja 
      */
	    
      @Test(dataProviderClass = Testbase.class, dataProvider = "dp", priority = 10)


	public static void CreateCase(String ContactName,String Status,String CaseOrigin,String Type,String CaseReason,String Subject,String Priority) throws InterruptedException, InvalidFormatException
	{

 	
    Thread.sleep(8000);
    System.out.println("Enter Inside");
    
    //case tab
    driver.findElement(By.xpath(OR.getProperty("CaseTab"))).click();
    Thread.sleep(8000);
   
    //refresh the page
     Thread.sleep(3000);
	 driver.navigate().refresh();
	 Thread.sleep(12000);
	 
	 
     //click new button
     driver.findElement(By.xpath(OR.getProperty("CaseNewButton"))).click();
     Thread.sleep(8000);
	 System.out.println("click on case origin");
		    
    //click the case origin
    driver.findElement(By.xpath(OR.getProperty("CaseOrigin"))).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("CaseSelectOrigin"))).click();
    Thread.sleep(8000);
    System.out.println("click on Type");
    
    
    //click the Type
    driver.findElement(By.xpath(OR.getProperty("CaseType"))).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("CaseSelectType"))).click();
    Thread.sleep(8000);
    System.out.println("click case reason");
   
    
    //click the case reason
    driver.findElement(By.xpath(OR.getProperty("CaseReason"))).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("CaseselectReason"))).click();
    
    
    //click save button
    driver.findElement(By.xpath(OR.getProperty("CaseSaveButton"))).click();
  
    Thread.sleep(8000);
    //click on Edit button
    driver.findElement(By.xpath(OR.getProperty("CaseEdit"))).click();
    
    Thread.sleep(8000);
    System.out.println("click on Priority");
     //click the Priority
    driver.findElement(By.xpath(OR.getProperty("CasePriority"))).click();
    Thread.sleep(8000);
    driver.findElement(By.xpath(OR.getProperty("CaseSelectPriority"))).click();
    
    
    //click save button
    driver.findElement(By.xpath(OR.getProperty("CaseEditSave"))).click();

	
	      }
	
      }
