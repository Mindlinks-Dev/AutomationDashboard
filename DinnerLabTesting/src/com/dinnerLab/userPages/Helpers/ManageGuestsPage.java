/**
 * @author: Basappa Hunsikatti
 * @Created Date :30/11/2015
 * @Updated Date :
 * @Comments This automation class will serve adding Guests details and their dietary restrictions or allergies.
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

public class ManageGuestsPage 
{
	private String loginURL;
	private String loginEmail;
	private String loginPassword;
	public String peanuts;
	public String shellfish;
	public String treeNuts;
	public String lactose;
	public String gluten;
	public String noneOfThese;
	public String dietaryPreferences;
	public String dietaryRestrictions;
	public String otherDietaryRestrictOrAllergies;
	public String OtherAllergiesTextArea;
	private String guestNameStatus;
	private String guestName;
	private String guestNameUpdate;
	private String guestEmail;
	private String guestEmailStatus;
	private String addGuest;
	private String editGuest;
	private String deleteGuest;
	public boolean updatedGuestStatus;
	public boolean addedGuestStatus;
	boolean present;
	public int rowCount;
	String isTestPassed="FAIL";
	public static boolean selectedCitiesStatus;
	private static Logger log = Logger.getLogger(ManageGuestsPage.class);
	 
	 /**
	 * Test Case for Reading the excel data and login into the Login Page
	 * portal on successful login to the application.
	 * Input: WebDriver
	 * Output: Void
	 */
	 public void manageGuestsInitialPage(WebDriver driver)
	 {
		try
		{
			ExcelLib xllib = new ExcelLib();
			 
			int rowCount = xllib.getRowCount("ManageGuests");
			log.info("********************* Manage Guests Logger Initialized******************************* ");
			for(int i=1;i<= rowCount;i++)
			{
				loginURL = xllib.getExcelData("ManageGuests", i, 0);
				loginEmail = xllib.getExcelData("ManageGuests", i, 1);
				loginPassword = xllib.getExcelData("ManageGuests", i, 2);

				addGuest = xllib.getExcelData("ManageGuests", i,3);
				editGuest = xllib.getExcelData("ManageGuests", i,4);
				deleteGuest = xllib.getExcelData("ManageGuests", i,5);
				guestNameStatus = xllib.getExcelData("ManageGuests", i,6);
				guestName = xllib.getExcelData("ManageGuests", i,7);
				guestNameUpdate = xllib.getExcelData("ManageGuests", i,8);
				guestEmailStatus = xllib.getExcelData("ManageGuests", i,9);
		 		guestEmail = xllib.getExcelData("ManageGuests", i,10);
		 		dietaryPreferences = xllib.getExcelData("ManageGuests", i, 11);
				dietaryRestrictions = xllib.getExcelData("ManageGuests", i, 12);
				peanuts = xllib.getExcelData("ManageGuests", i,13);
				shellfish = xllib.getExcelData("ManageGuests", i,14);
				treeNuts = xllib.getExcelData("ManageGuests", i, 15);
				lactose = xllib.getExcelData("ManageGuests", i, 16);
				gluten = xllib.getExcelData("ManageGuests", i,17);
				noneOfThese = xllib.getExcelData("ManageGuests", i,18);
		 		otherDietaryRestrictOrAllergies = xllib.getExcelData("ManageGuests", i,19);
		 		OtherAllergiesTextArea = xllib.getExcelData("ManageGuests", i,20);
		 		
				MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
				
				//Calling Login URL method
				loginLogout.loginURLActions(driver,loginURL);
				
				//Calling Member Login Method
				loginLogout.loginPageActions(driver,loginEmail,loginPassword);
						
				//Checking whether login credentials valid or not
				if(MemberLoginLogoutPage.loggedInStatus)
				{
					isTestPassed="PASS";
					xllib.writeToExcel("ManageGuests", i, 21, isTestPassed);
					
					 //Clicking on Manage Guest link
					 clickingOnManageGuestLink(driver);
					 
					 if(addGuest.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					 {
						 //Calling add Guests actions method
						 addGuestsActions(driver);
						 
						 //Calling verify added Guest method
						 verifyAddedGuest(driver);
						 
						 if(addedGuestStatus)
						 {
							 isTestPassed="PASS";
							 xllib.writeToExcel("ManageGuests", i, 22, isTestPassed);
							 log.info("Guest has been added");
						 }else if(!addedGuestStatus)
						 {
							 isTestPassed = "FAIL";
							 xllib.writeToExcel("ManageGuests", i, 22, isTestPassed);
						 }
					 }else if(editGuest.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					 {
						//Calling edit Guests actions method
						 editGuestsActions(driver);
						 
						 //Calling verify updated Guest method
						 verifyUpdatedGuest(driver);
					
						 if(updatedGuestStatus)
						 {
							 isTestPassed="PASS";
							 xllib.writeToExcel("ManageGuests", i, 23, isTestPassed);
							 log.info("Guest has been updated");
						 }else if(!updatedGuestStatus)
						 {
							 isTestPassed = "FAIL";
							 xllib.writeToExcel("ManageGuests", i, 23, isTestPassed);
						 }
					 }else if(deleteGuest.equalsIgnoreCase(TestConstants.STATUS_TRUE))
					 {
						//Calling delete Guest action method
						 deleteGuestsActions(driver);
						 
						 //Calling verify updated Guest method
						 verifyAddedGuest(driver);
					
						 if(!addedGuestStatus)
						 {
							 isTestPassed="PASS";
							 xllib.writeToExcel("ManageGuests", i, 24, isTestPassed);
							 log.info("Guest has been deleted");
						 }else if(addedGuestStatus)
						 {
							 isTestPassed = "FAIL";
							 xllib.writeToExcel("ManageGuests", i, 24, isTestPassed);
						 }
					 }
					 if(rowCount !=1)
					 {
						//Calling Logout method
						loginLogout.logoutActions(driver);
					 }
				}else if(!MemberLoginLogoutPage.loggedInStatus)
			    {
			    	isTestPassed = "FAIL";
					xllib.writeToExcel("ManageGuests", i, 21, isTestPassed);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	/**
		* Test Case for adding Guests details.
		* Input: WebDriver
		* Output: Void
	*/
	 public void addGuestsActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//div[@class='add-guest info']")).click();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 if(guestNameStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.id("guest-add-name")).clear();
				 driver.findElement(By.id("guest-add-name")).sendKeys(guestName);
			 }
			 if(guestEmailStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.id("guest-add-email")).clear();
				 driver.findElement(By.id("guest-add-email")).sendKeys(guestEmail);
			 }
			 
			 if(dietaryPreferences.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 //Calling Dietary Restrictions method
				 addGuestDietaryRestrictionsActions(driver,dietaryRestrictions);
		            
		         //Calling Allergies method
				 addGuestAllergiesActions(driver,peanuts,shellfish,treeNuts,lactose,gluten,noneOfThese);
		            
		         //Calling other Dietary Restrict Or Allergies method
		         addGuestOtherDietaryRestrictOrAllergiesActions(driver,otherDietaryRestrictOrAllergies,OtherAllergiesTextArea);
			 }
			 //Clicking on Add Button
			 driver.findElement(By.xpath("//input[@value='Add']")).click();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	 /**
		* Test Case for editing Guests details.
		* Input: WebDriver
		* Output: Void
	*/
	 public void editGuestsActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 driver.findElement(By.xpath("//div[div[contains(text(),'"+guestName+"')]]/span[contains(text(),'Edit')]")).click();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 if(guestNameStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.xpath("//input[@value='"+guestName+"']")).clear();
				 driver.findElement(By.xpath("//input[@value='"+guestName+"']")).sendKeys(guestNameUpdate);
			 }
			 		
			 if(guestEmailStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[3]/div/input[@type='email']")).clear();
				 driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[3]/div/input[@type='email']")).sendKeys(guestEmail);
			 }
			 
			 if(dietaryPreferences.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			 {
				 //Calling edit Dietary Restrictions method
				 editGuestDietaryRestrictionsActions(driver,dietaryRestrictions);
		            
		         //Calling Allergies method
				 editGuestAllergiesActions(driver,peanuts,shellfish,treeNuts,lactose,gluten,noneOfThese);
		            
		         //Calling other Dietary Restrict Or Allergies method
		         editGuestOtherDietaryRestrictOrAllergiesActions(driver,otherDietaryRestrictOrAllergies,OtherAllergiesTextArea);
			 }
			 //Clicking on Save Button
			 driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/input[3]")).click();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}	
	/**
	 * Test Case for selecting Dietary
	 * Input: WebDriver
	 * Output: Boolean
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public Boolean addGuestDietaryRestrictionsActions(WebDriver driver,String dietaryRestrictions) 
	 {
		 //Condition to restrict Dietaries.
		 if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Pescetarian))
		 {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//label[@for='pescetarian-guest-add']")).click();
			log.info("Pescetarian has been selected");	
			return true;
		}
		else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Vegetarian))
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//label[@for='vegetarian-guest-add']")).click();
			log.info("Vegetarian has been selected");	
			return true;
		}
		else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Vegan))
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//label[@for='vegan-guest-add']")).click();
			//driver.findElement(By.xpath("//label[@class='icon-vegan']")).click();
			log.info("Vegan has been selected");	
			return true;
		}
		else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_NoneOfThese))
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//label[@for='preferences-none-guest-add']")).click();
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
	public Boolean addGuestAllergiesActions(WebDriver driver,String peanuts,String shellfish,String treeNuts,String lactose,String gluten,String noneOfThese)
	{
		try
		{
			//Condition to restricting allergies.
			if(peanuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='peanut-guest-add']")).click();
				log.info("Peanuts has been selected");
			}
			if(shellfish.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='shellfish-guest-add']")).click();
				log.info("Shellfish has been selected");
			}
			if(treeNuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='treenuts-guest-add']")).click();
				log.info("Tree Nuts has been selected");
			}
			if(lactose.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='lactose-guest-add']")).click();
				log.info("Lactose has been selected");
			}
			if(gluten.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='gluten-guest-add']")).click();
				log.info("Gluten has been selected");
			}
			if(noneOfThese.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//label[@for='allergies-none-guest-add']")).click();
				log.info("None of these selected");
			}
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Test Case for selecting other Dietary
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void addGuestOtherDietaryRestrictOrAllergiesActions(WebDriver driver,String otherDietaryRestrictOrAllergies,String OtherAllergiesTextArea) 
	{
		//Condition for other dietary restrictions or allergies. 
		if(otherDietaryRestrictOrAllergies.equalsIgnoreCase(TestConstants.STATUS_TRUE))
		{
			driver.findElement(By.id("otherAllergiesGuestCheck")).click();
			driver.findElement(By.id("otherAllergiesGuestAdd")).sendKeys(OtherAllergiesTextArea);
			log.info("Entering other Dietary Restrictions and/or Allergies");
		}//End of if Condition
	}
	/**
	 * Test Case for updating Dietary
	 * Input: WebDriver
	 * Output: Boolean
	 * @return 
	 * @throws InvalidFormatException 
	*/
	 public Boolean editGuestDietaryRestrictionsActions(WebDriver driver,String dietaryRestrictions) 
	 {
		 //Condition to restrict Dietaries.
		 if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Pescetarian))
		 {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[4]/div/div[1]/label[1]")).click();
			log.info("Pescetarian has been selected");	
			return true;
		}
		else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Vegetarian))
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[4]/div/div[2]/label[1]")).click();
			log.info("Vegetarian has been selected");	
			return true;
		}
		else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_Vegan))
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[4]/div/div[3]/label[1]")).click();
			//driver.findElement(By.xpath("//label[@class='icon-vegan']")).click();
			log.info("Vegan has been selected");	
			return true;
		}
		else if(dietaryRestrictions.equalsIgnoreCase(TestConstants.Dietary_NoneOfThese))
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[4]/div/div[4]/label[1]")).click();
			log.info("None of these Dietary has been selected");	
			return true;
		}
		else
		{
			return false;
		}
	}
	 /**
	 * Test Case for editing Allergies
	 * Input: WebDriver
	 * Output: Boolean
	 * @return 
	 * @throws InvalidFormatException 
	*/	
	public Boolean editGuestAllergiesActions(WebDriver driver,String peanuts,String shellfish,String treeNuts,String lactose,String gluten,String noneOfThese)
	{
		try
		{
			//Condition to restricting allergies.
			if(peanuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[1]/label")).click();
				log.info("Peanuts has been selected");
			}
			if(shellfish.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[2]/label")).click();
				log.info("Shellfish has been selected");
			}
			if(treeNuts.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[3]/label")).click();
				log.info("Tree Nuts has been selected");
			}
			if(lactose.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[4]/label")).click();
				log.info("Lactose has been selected");
			}
			if(gluten.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[5]/label")).click();
				log.info("Gluten has been selected");
			}
			if(noneOfThese.equalsIgnoreCase(TestConstants.STATUS_TRUE))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[6]/label")).click();
				log.info("None of these selected");
			}
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}		
	/**
	 * Test Case for editing other Dietary
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
	*/
	public void editGuestOtherDietaryRestrictOrAllergiesActions(WebDriver driver,String otherDietaryRestrictOrAllergies,String OtherAllergiesTextArea) 
	{
		//Condition for other dietary restrictions or allergies. 
		if(otherDietaryRestrictOrAllergies.equalsIgnoreCase(TestConstants.STATUS_TRUE))
		{
			driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[7]/label/input")).click();
			driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/div[5]/div/div[8]/textarea")).sendKeys(OtherAllergiesTextArea);
			log.info("Entering other Dietary Restrictions and/or Allergies");
		}//End of if Condition
	}
	/**
	 * Test Case for verifying added guest
	 * Input: WebDriver
	 * Output: Boolean
	 * @return 
	 * @throws InvalidFormatException 
	 */
	public  void verifyAddedGuest(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			//Thread.sleep(5000);
			addedGuestStatus = driver.findElement(By.xpath("//div[contains(text(),'"+guestName+"')]")).isDisplayed();
			System.out.println("addedGuestStatus:"+addedGuestStatus);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
		 * Test Case for verifying updated/edited guest
		 * Input: WebDriver
		 * Output: Boolean
		 * @return 
		 * @throws InvalidFormatException 
	*/
	public  void verifyUpdatedGuest(WebDriver driver)
	{
		try
		{
			 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			 //Thread.sleep(5000);
			 updatedGuestStatus = driver.findElement(By.xpath("//span[contains(text(),'Guest successfully updated.')]")).isDisplayed();
			 //updatedGuestStatus = driver.findElement(By.xpath("//form[div[div[input[@value='"+guestNameUpdate+"']]]]/div[5]/div/div[2]/label")).isDisplayed();
			 System.out.println("updatedGuestStatus:"+updatedGuestStatus);
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 }catch(Exception e)
		 {
			e.printStackTrace();
		 }
	}
	/**
	 * Test Case for deleting Guest.
	 * Input: WebDriver
	 * Output: Void
	*/
	public void deleteGuestsActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[div[contains(text(),'"+guestName+"')]]/span[contains(text(),'Edit')]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 			 
			//Clicking on Delete Button
			driver.findElement(By.xpath("//form[div[div[input[@value='"+guestName+"']]]]/span[contains(text(),'delete')]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
			//Clicking on Delete Pop up
			driver.findElement(By.id("deleteGuestButton")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}	
	/**
	 * Test Case for clicking on Manage Guest link
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 */
	public void clickingOnManageGuestLink(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			
			WebElement parent = driver.findElement(By.xpath("//li[@class='parent']")); // member drop down
			actions.moveToElement(parent).build().perform();

			driver.findElement(By.xpath("//a[contains(text(),'Manage Guests')]")).click(); 
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
	}
}