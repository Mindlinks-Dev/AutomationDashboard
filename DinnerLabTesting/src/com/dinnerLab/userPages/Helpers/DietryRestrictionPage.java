/**
 * @author: Basappa Hunsikatti
 * @Created Date :24/11/2015
 * @Updated Date :
 * @Comments This automation class will manage dietary restrictions or allergies.
 */
package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;
import com.pack.ScriptHandler;

public class DietryRestrictionPage
{
	private String loginURL;	 
	private String loginEmail;
	private String loginPassword;
	public static String allergies;
	public static String dietaryRestrictions;
	private String otherDietaryRestrictOrAllergies;
	private String OtherAllergiesTextArea;
	boolean present;
	public int rowCount;
	String isTestPassed="FAIL";
	public static String result;
	public static String referLink;
	public static String memberCity;
	private String peanuts;
	private String shellfish;
	private String treeNuts;
	private String lactose;
	private String gluten;
	private String noneOfThese;
	public static boolean dietaryStatus;
	public static boolean allergiesStatus;
	public static boolean DietaryRestrictionsUpdateStatus;
	private static Logger log = Logger.getLogger(DietryRestrictionPage.class);
	//MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();	 
	
	/**
	 * Test Case for restricting Member/Chef dietary or allergies.
	 * Input: WebDriver
	 * Output: Void
	*/
	public Boolean dietaryRestrictionInitialPage(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			rowCount= xllib.getRowCount("DietaryRestriction");
			for (int i = 1; i <= rowCount; i++) 
			{
				loginURL = xllib.getExcelData("DietaryRestriction", i, 0);
				loginEmail = xllib.getExcelData("DietaryRestriction", i, 1);
				loginPassword = xllib.getExcelData("DietaryRestriction", i, 2);
				dietaryRestrictions = xllib.getExcelData("DietaryRestriction", i,3);
				peanuts = xllib.getExcelData("DietaryRestriction", i,4);
				shellfish = xllib.getExcelData("DietaryRestriction", i, 5);
				treeNuts = xllib.getExcelData("DietaryRestriction", i, 6);
				lactose = xllib.getExcelData("DietaryRestriction", i, 7);
				gluten = xllib.getExcelData("DietaryRestriction", i,8);
				noneOfThese = xllib.getExcelData("DietaryRestriction", i,9);
			 	otherDietaryRestrictOrAllergies = xllib.getExcelData("DietaryRestriction", i,10);
			 	OtherAllergiesTextArea = xllib.getExcelData("DietaryRestriction", i,11);
			 		
			 	System.out.println("dietaryRestrictions:"+dietaryRestrictions);
			 	System.out.println("peanuts:"+peanuts);
				System.out.println("shellfish:"+shellfish);
			 	System.out.println("treeNuts:"+treeNuts);
				System.out.println("lactose:"+lactose);
			 	System.out.println("gluten:"+gluten);
				System.out.println("noneOfThese:"+noneOfThese);
			 	System.out.println("otherDietaryRestrictOrAllergies:"+otherDietaryRestrictOrAllergies);
			 	System.out.println("OtherAllergiesTextArea:"+OtherAllergiesTextArea);
			 	
			 	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
			 	
			 	//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("DietaryRestriction", i, 12, isTestPassed);
					
					//Calling Dietary Restriction Link method
					clickingOnDietaryRestrictionsLink(driver);
					
					//Calling Dietary Restrict method
					dietaryStatus = dietaryRestrictionsActions(driver,dietaryRestrictions);
			 		
					//System.out.println("dietary flag ::"+dietaryStatus);
					if(dietaryStatus)
					{
						//Calling Allergies method
						allergiesStatus = allergiesActions(driver,peanuts,shellfish,treeNuts,lactose,gluten);
						
						//Selecting login page allergy none of these
						selectingLoginPageAllergyNoneOfThese(driver);
					
						//Calling other Dietary Restrict Or Allergies method
						otherDietaryRestrictions(driver,otherDietaryRestrictOrAllergies,OtherAllergiesTextArea);
							
						//Calling Dietary Restrictions Update Status method
						verifyDietaryRestrictionsUpdateStatus(driver);
							
						if(rowCount !=1)
						{
							//Calling Logout method
							loginLogout.logoutActions(driver);
						}
					}
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("DietaryRestriction", i, 12, isTestPassed);
				}	
				
				if(DietaryRestrictionsUpdateStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("DietaryRestriction", i, 13, isTestPassed);
				}else if(!DietaryRestrictionsUpdateStatus)
				{
					isTestPassed = "FAIL";
					xllib.writeToExcel("DietaryRestriction", i, 13, isTestPassed);
				}
				
				/*driver.findElement(By.xpath("//input[@value='Save All']")).click();
				boolean dietaryRestrictionsUpdateSuccess = driver.findElement(By.xpath("//span[contains(text(),'Your dietary restrictions have been updated.')]")).isDisplayed();
				//Verify whether dietary restrictions have been updated or not.
				if(dietaryRestrictionsUpdateSuccess)
				{
					log.info("Your dietary restrictions have been updated.");
				}*/
			}//End of For Loop
			
			/*driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Clicking on Next             
            driver.findElement(By.xpath("//span[@class='next ctrl']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//span[@class='next ctrl']")).click();
            
            //Link to Refer friend.
            Thread.sleep(5000);
             referLink = driver.findElement(By.xpath("//textarea[@class='code']")).getText();
             System.out.println("Your Referral Link is 1 :"+referLink);
             //log.info("Your Referral Link is : "+referLink);
             //Clicking on view Events.
             driver.findElement(By.xpath("//a[@href='/events/']")).click();  
             //driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);*/
             return true;
            }
			catch(Exception e)
			{
				e.printStackTrace();
				log.info("Your dietary restrictions have not been updated");
				 return false;
			}		
		}//End of dietaryRestrictionsOrAllergiesActions method
	
	/**
	 * Test Case for selecting Dietary
	 * Input: WebDriver
	 * Output: Boolean
	 * @return 
	 * @throws InvalidFormatException 
	*/
		public Boolean dietaryRestrictionsActions(WebDriver driver,String dietaryRestrictions) 
		{
			
			//Condition to restrict Dietaries.
			if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Pescetarian))
			{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='pescetarian']")).click();
				//driver.findElement(By.xpath("//label[@class='icon-pescetarian']")).click();
				log.info("Pescetarian has been selected");	
				return true;
			}
			else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Vegetarian))
			{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='vegetarian']")).click();
				//driver.findElement(By.xpath("//label[@class='icon-vegetarian']")).click();
				log.info("Vegetarian has been selected");	
				return true;
			}
			else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Vegan))
			{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='vegan']")).click();
				//driver.findElement(By.xpath("//label[@class='icon-vegan']")).click();
				log.info("Vegan has been selected");	
				return true;
			}
			else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_NoneOfThese))
			{
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='restrictionsNone']")).click();
				//driver.findElement(By.xpath("//label[@class='icon-none']")).click();
				log.info("None of these Dietary has been selected");	
				return true;
			}
			else
			{
				return false;
			}
			
		}
		/**
		 * Test Case for selecting Allergies
		 * Input: WebDriver
		 * Output: Boolean
		 * @return 
		 * @throws InvalidFormatException 
		*/	
		public Boolean allergiesActions(WebDriver driver,String peanuts,String shellfish,String treeNuts,String lactose,String gluten)
		{
			try
			{
				//Condition to restricting allergies.
				if(peanuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//label[@for='peanuts']")).click();
					log.info("Peanuts has been selected");
				}
				if(shellfish.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//label[@for='shellfish']")).click();
					log.info("Shellfish has been selected");
				}
				if(treeNuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//label[@for='treenuts']")).click();
					log.info("Tree Nuts has been selected");
				}
				if(lactose.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//label[@for='lactose']")).click();
					log.info("Lactose has been selected");
				}
				if(gluten.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//label[@for='gluten']")).click();
					log.info("Gluten has been selected");
				}
				return true;
			}catch(Exception e)
			{
				//System.out.println("Allergies in catch block");
				e.printStackTrace();
				return false;
			}
		}
		
		public void otherDietaryRestrictOrAllergiesActions(WebDriver driver,String otherDietaryRestrictOrAllergies,String OtherAllergiesTextArea) {
			
			//Condition for other dietary restrictions or allergies. 
			if(otherDietaryRestrictOrAllergies.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.findElement(By.id("other")).click();
				driver.findElement(By.id("other-allergies")).sendKeys(OtherAllergiesTextArea);
				log.info("Entering other Dietary Restrictions and/or Allergies");
				//driver.findElement(By.xpath("//input[@ value='save']")).click();
				log.info("Other Dietary Restrictions and/or Allergies are saved.");
			}//End of if Condition
		}
		/**
		 * Test Case for clicking on Dietary Restrictions link. 
		 * Input: WebDriver
		 * Output: Void
		 * @return 
		 * @throws InvalidFormatException 
		 */
		public void clickingOnDietaryRestrictionsLink(WebDriver driver)
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Actions actions = new Actions(driver);
				
				WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
				actions.moveToElement(parent).build().perform();

				driver.findElement(By.xpath("//a[contains(text(),'Dietary Restrictions')]")).click();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS); 
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		/**
		 * Test Case for verifying Dietary Restrictions Update Status
		 * Input: WebDriver
		 * Output: Boolean
		 * @return 
		 * @throws InvalidFormatException 
		*/
		 public  boolean verifyDietaryRestrictionsUpdateStatus(WebDriver driver)
		 {
			 try
			 {
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 DietaryRestrictionsUpdateStatus = driver.findElement(By.xpath("//span[contains(text(),'Your dietary restrictions have been updated.')]")).isDisplayed();
				 System.out.println("DietaryRestrictionsUpdateStatus:"+DietaryRestrictionsUpdateStatus);
				 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		         return true;
			 }catch(Exception e)
			 {
				e.printStackTrace();
				return false;
			 }
		}
		 /**
			 * Test Case for entering other  Dietary Restrictions 
			 * Input: WebDriver
			 * Output: Boolean
			 * @return 
			 * @throws InvalidFormatException 
		*/
		 public void otherDietaryRestrictions(WebDriver driver,String otherDietaryRestrictOrAllergies,String OtherAllergiesTextArea) 
		 {
			 try
			 {
				//Condition for other dietary restrictions or allergies. 
				if(otherDietaryRestrictOrAllergies.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					driver.findElement(By.id("other")).click();
					driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
					driver.findElement(By.id("other-allergies")).sendKeys(OtherAllergiesTextArea);
					driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
					log.info("Entering other Dietary Restrictions and/or Allergies");
					driver.findElement(By.xpath("//input[@value='Save All']")).click();
					driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
					log.info("Other Dietary Restrictions and/or Allergies are saved.");
				}//End of if Condition
			 }catch(Exception e)
			 {
				System.out.println("inside other dietary catch block");
				e.printStackTrace();
			}
		}
	 /**
	 * Test Case for selecting Signup page Allergies none of these
		 * Input: WebDriver
		 * Output: Boolean
		 * @return 
		 * @throws InvalidFormatException 
	*/
	 public  void selectingSignUpPageAllergyNoneOfThese(WebDriver driver,String noneOfThese)
	 {
		 try
		 {
			 if(noneOfThese.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='allergiesNone']")).click();
				log.info("None of these Allergies has been selected");
				 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 }
		 }catch(Exception e)
		 {
			e.printStackTrace();
			
		 }
	 }
	 
	 /**
		 * Test Case for selecting Signup page Allergies none of these
		 * Input: WebDriver
		 * Output: Boolean
		 * @return 
		 * @throws InvalidFormatException 
	*/
	public  void selectingLoginPageAllergyNoneOfThese(WebDriver driver)
	{
		try
		{
			 if(noneOfThese.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='allergies-none']")).click();
				log.info("None of these Allergies has been selected");
				 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 }
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	}
	/**
	* Test Case for Saving Dietary Preferences 
	* Input: WebDriver
	* Output: Boolean
	* @return 
	* @throws InvalidFormatException 
	*/
	 public  void clickingOnSaveButton(WebDriver driver)
	 {
		 try
		 {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("setAllergies")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}catch(Exception e)
		 {
			e.printStackTrace();
			
		 }
	 }
}
