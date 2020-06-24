/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/09/2015
 * @Updated Date :
 * @Comments:This automation class will serve the Chef Sign Up.
 */

package com.dinnerLab.userPages.Helpers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;

public class ChefSignUpPage 
{
	//private static String signUpUrl = TestConstants.SignUp_Url;
	private static String firstName;
	private static String lastName;
	private static String email;
	private static String password;
	private static String useOtherCity;
	private static String city;
	private static String otherCity;
	private static String doYouHavePromoCode;
	private static String promoCode;
	private static String signUpURL;
	public static boolean signUpSuccess;
	private static String currentEmployment;
	private static String workHistory;
	private static String education;
	private static String homeTown;
	private static String favouriteRestaurants;
	private static String favouriteBars;
	
	private static String street1;
	private static String street2;
	private static String cityName;
	private static String state;
	private static String zipCode;
	private static String phoneNumber;
	private static String birthMonth;
	private static String birthDay;
	private static String birthYear;
	private static String willingToTravel;
	private static String referrer;
	
	private String menuTitle;
	private String menuConcept;
	private String courseTitle;
	private String courseType;
	private static String courseNotes;
	private String component1;
	private String component2;
	private String component3;
	private String saveOrSubmitMenu;	
	
	public static String allergies;
	public boolean homeCity;
	public static String dietaryRestrictions;
	public static String memberType;
	
	public static String eventCity;
	public static String eventName;
	public static String seatingDate;
	public static String seatingTime;
	public static String purchaseQuantity;
	public static String courseNotesStatus;
	public static int rowCount;
	public boolean eventTicketPurchaseSuccess;
	public static Boolean ticketPurchaseStatus;
	public static String memberCityName;
	 
	public static boolean upgradeAccountStatus;
	String isTestPassed="FAIL";
	int i=0;
	private static Logger log = Logger.getLogger(ChefSignUpPage.class);
			
	/**
	 * Test Case for Reading the excel data for signing up as a Free Member
	 * portal on successful registration to the application.
	 * Input: WebDriver
	 * Output: Void
	 * @throws InvalidFormatException 
	*/
	public void chefSignUpInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
			int rowCount = xllib.getRowCount("ChefSignUp");
			//For loop:Fetch data from excel input configuration file(excel sheet)
			for(i=1;i<= rowCount;i++)
			{
			 	signUpURL = xllib.getExcelData("ChefSignUp", i, 0);
			 	firstName = xllib.getExcelData("ChefSignUp", i, 1);
			 	lastName = xllib.getExcelData("ChefSignUp", i, 2);
				email = xllib.getExcelData("ChefSignUp", i, 3);
				password = xllib.getExcelData("ChefSignUp", i, 4);
				city = xllib.getExcelData("ChefSignUp", i, 5);
				useOtherCity = xllib.getExcelData("ChefSignUp", i, 6);
				otherCity = xllib.getExcelData("ChefSignUp", i, 7);
				doYouHavePromoCode = xllib.getExcelData("ChefSignUp", i, 8);
				promoCode = xllib.getExcelData("ChefSignUp", i, 9);
				
				currentEmployment = xllib.getExcelData("ChefSignUp", i, 10);
				workHistory = xllib.getExcelData("ChefSignUp", i, 11);
				education = xllib.getExcelData("ChefSignUp", i, 12);
				homeTown	= xllib.getExcelData("ChefSignUp", i, 13);	
				favouriteRestaurants = xllib.getExcelData("ChefSignUp", i, 14);	
				favouriteBars = xllib.getExcelData("ChefSignUp", i, 15);	
				
				street1 = xllib.getExcelData("ChefSignUp", i, 16);
				street2 = xllib.getExcelData("ChefSignUp", i, 17);
			 	cityName = xllib.getExcelData("ChefSignUp", i, 18);
			 	state = xllib.getExcelData("ChefSignUp", i, 19);
			 	zipCode = xllib.getExcelData("ChefSignUp", i, 20);
				phoneNumber = xllib.getExcelData("ChefSignUp", i, 21);
				birthMonth = xllib.getExcelData("ChefSignUp", i, 22);
				birthDay = xllib.getExcelData("ChefSignUp", i, 23);
				birthYear = xllib.getExcelData("ChefSignUp", i, 24);
				willingToTravel = xllib.getExcelData("ChefSignUp", i, 25);
				referrer = xllib.getExcelData("ChefSignUp", i, 26);
				
				menuTitle = xllib.getExcelData("ChefSignUp", i,27);
				menuConcept = xllib.getExcelData("ChefSignUp", i,28);
				courseTitle = xllib.getExcelData("ChefSignUp", i,29);
				//courseType = xllib.getExcelData("ChefSignUp", i,30);
				courseNotesStatus = xllib.getExcelData("ChefSignUp", i,30);
				courseNotes = xllib.getExcelData("ChefSignUp", i,31);
				component1 = xllib.getExcelData("ChefSignUp", i,32);
				component2 = xllib.getExcelData("ChefSignUp", i,33);
				component3 = xllib.getExcelData("ChefSignUp", i,34);
				saveOrSubmitMenu = xllib.getExcelData("ChefSignUp", i,35);
				
				FreeMemberSignUpPage freeMember = new FreeMemberSignUpPage();
				
				//Calling subscriber Signup URL Method.
			 	freeMember.subscriberSignUpURL(driver,signUpURL);
		 		
		 		//Selecting User Type as Member
			 	selectUserTypeChef(driver);
		 		
		 		//Calling Sign Up Method.
			 	signUpSuccess = freeMember.signUpPageActions(driver,doYouHavePromoCode,promoCode,firstName,lastName,email,password,city,useOtherCity,otherCity);
		 		System.out.println("signUpSuccess:"+signUpSuccess);
		 		
		 		if(signUpSuccess)
		 		{
		 			isTestPassed="PASS";
					xllib.writeToExcel("ChefSignUp", i, 36, isTestPassed);
					//ScriptHandler.comment.add("Script Chef, Test Data "+i+": Success in Paid Member Sign up page.");
					
		 			//Calling Chef Information Method
			 		//chefInformation(driver,tellUsAboutYourSelf,currentEmployment,workHistory,education,homeTown,favouriteRestaurants,favouriteBars);
			 		chefInformation(driver);
			 		
			 		//Calling Add Menu by Chef Method
			 		addMenuByChefActions(driver);
			 		
			 		//Calling Log Out Method
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        	driver.navigate().refresh();
		        	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
		        	if(rowCount !=1)
					{
			           	MemberLoginLogoutPage loginLogout = new MemberLoginLogoutPage();
			            	
			           	//Calling Logout method
						loginLogout.logoutActions(driver);
					}
			 		
			 	}else
		 		{
		 			isTestPassed = "FAIL";
					xllib.writeToExcel("ChefSignUp", i, 36, isTestPassed);
					//ScriptHandler.comment.add("Script Chef, Test Data "+i+": Failed in Paid Member Sign up Page.");
		 		}
		 		
		 	}//End of for loop
		}
		catch(Exception e)
		{
			log.info("Chef Account creation unsucessfull");
		}
	}
	
	/**
	 * Test Case for selecting User Type as Member
	 * Input: WebDriver
	 * Output: Void
	 * @return 
	 * @throws InvalidFormatException 
    */
	public void selectUserTypeChef(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement member = driver.findElement(By.id("usertypeID"));
			Select st = new Select(member);
			st.selectByIndex(2);	
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	/**
	 * Test Case for adding Chef Information
	 * Input: driver and parameters
	 * Output: Void
     * @throws InterruptedException 
     * @throws InvalidFormatException 
	 * 
	 */	 
	
	public void chefInformation(WebDriver driver) 
	{
		try
		{
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.id("currentemployment")).clear();
			driver.findElement(By.id("currentemployment")).sendKeys(currentEmployment);
			driver.findElement(By.id("laststaged")).clear();
			driver.findElement(By.id("laststaged")).sendKeys(workHistory);
			driver.findElement(By.id("education")).clear();
			driver.findElement(By.id("education")).sendKeys(education);
			driver.findElement(By.id("hometown")).clear();
			driver.findElement(By.id("hometown")).sendKeys(homeTown);
			driver.findElement(By.id("favoriterestaurant")).clear();
			driver.findElement(By.id("favoriterestaurant")).sendKeys(favouriteRestaurants);
			driver.findElement(By.id("favoritebar")).clear();
			driver.findElement(By.id("favoritebar")).sendKeys(favouriteBars);
			
			driver.findElement(By.xpath("//span[@class='next ctrl']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.findElement(By.id("address")).clear();
			driver.findElement(By.id("address")).sendKeys(street1);
			driver.findElement(By.id("address2")).clear();
			driver.findElement(By.id("address2")).sendKeys(street2);
			driver.findElement(By.id("city")).clear();
			driver.findElement(By.id("city")).sendKeys(cityName);
			//driver.findElement(By.id("state")).sendKeys(state);
			
			WebElement stateText = driver.findElement(By.id("state"));
			Select st = new Select(stateText);
			st.selectByVisibleText(state);
			
			driver.findElement(By.id("zip")).clear();
			driver.findElement(By.id("zip")).sendKeys(zipCode);
			driver.findElement(By.id("telephone")).clear();
			driver.findElement(By.id("telephone")).sendKeys(phoneNumber); 
			
			//driver.findElement(By.id("bdm")).sendKeys(birthMonth);
			WebElement monthText = driver.findElement(By.id("bdm"));
			Select st1 = new Select(monthText);
			st1.selectByVisibleText(birthMonth);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.findElement(By.id("bdd")).sendKeys(birthDay);
			WebElement dayText = driver.findElement(By.id("bdd"));
			Select st2 = new Select(dayText);
			st2.selectByVisibleText(birthDay);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.findElement(By.id("bdy")).sendKeys(birthYear);
			WebElement yearText = driver.findElement(By.id("bdy"));
			Select st3 = new Select(yearText);
			st3.selectByVisibleText(birthYear);
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(willingToTravel.equalsIgnoreCase(TestConstants.STATUS_FALSE))
			{
				driver.findElement(By.id("travelno")).click();
			}
			driver.findElement(By.id("referrer")).clear();
			driver.findElement(By.id("referrer")).sendKeys(referrer);
			driver.findElement(By.id("chef-info-check")).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	/**
	 * Test Case for adding Menu by Chef
	 * Input: WebDriver
	 * Output: Void
     */
	 public void addMenuByChefActions(WebDriver driver) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	 {
		 try
		 {
		 	log.info("Clicking on Add a Menu");	
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			//driver.findElement(By.xpath("//a[@href='/chef/menus/add']")).click();
			driver.findElement(By.id("menu-title")).click();
			driver.findElement(By.id("menu-title")).clear();
			driver.findElement(By.id("menu-title")).sendKeys(menuTitle);
			driver.findElement(By.id("menuConcept")).click();
			driver.findElement(By.id("menuConcept")).clear();
			driver.findElement(By.id("menuConcept")).sendKeys(menuConcept);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.name("course-title")).clear();
			driver.findElement(By.name("course-title")).sendKeys(courseTitle);
			//driver.findElement(By.id("courseType")).sendKeys(courseType);
			
			/*WebElement courseTypeText = driver.findElement(By.id("courseType"));
			Select st4 = new Select(courseTypeText);
			st4.selectByVisibleText(courseType);*/
			
			try
			{
				if(courseNotesStatus.equalsIgnoreCase(TestConstants.STATUS_TRUE))
				{
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//div[@class='notes-btn btn-white']")).click();
									
					//WebElement notes = driver.findElement(By.id("courseNotes"));
					//action.click(notes).sendKeys(courseNotes).perform();
					System.out.println("before courseNotes");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//textarea[@name='courseNotes']")).click();
					driver.findElement(By.xpath("//textarea[@name='courseNotes']")).clear();
					driver.findElement(By.xpath("//textarea[@name='courseNotes']")).sendKeys(courseNotes);
					System.out.println("after courseNotes");
					driver.findElement(By.xpath("//div[contains(text(),'Save Notes')]")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				log.info("Notes is not added");
			}	
						
			System.out.println("courseNotes:"+courseNotes);
			System.out.println("component1:"+component1);
			System.out.println("component2:"+component2);
			System.out.println("component3:"+component3);
			
			//Actions action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("before component1");
			driver.findElement(By.xpath("//ul[@class='components']/li[1]/input")).click();
			System.out.println("after clicking on component1");
			//driver.findElement(By.xpath("//ul[@class='components']/li[1]/input")).clear();
			driver.findElement(By.xpath("//ul[@class='components']/li[1]/input")).sendKeys(component1);
			System.out.println("after component1");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//ul[@class='components']/li[2]/input")).click();
			//driver.findElement(By.xpath("//ul[@class='components']/li[2]/input")).clear();
			driver.findElement(By.xpath("//ul[@class='components']/li[2]/input")).sendKeys(component2);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//ul[@class='components']/li[3]/input")).click();
			//driver.findElement(By.xpath("//ul[@class='components']/li[3]/input")).clear();
			driver.findElement(By.xpath("//ul[@class='components']/li[3]/input")).sendKeys(component3);
			
			/*System.out.println("before component1");
			WebElement compText1 = driver.findElement(By.xpath("//ul[@class='components']/li[1]"));
			//action.click(compText1).sendKeys(component1).perform();
			action.click(compText1).perform();
			System.out.println("after clicking on component1");
			action.sendKeys(component1).perform();
			System.out.println("after component1");
			WebElement compText2 = driver.findElement(By.xpath("//ul[@class='components']/li[2]"));
			//action.click(compText2).sendKeys(component1).perform();
			action.click(compText2).perform();
			action.sendKeys(component2).perform();
			WebElement compText3 = driver.findElement(By.xpath("//ul[@class='components']/li[3]"));
			//action.click(compText3).sendKeys(component1).perform();
			action.click(compText3).perform();
			action.sendKeys(component3).perform();*/
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//Condition to Menu can Save/Submit.
			if(saveOrSubmitMenu.equalsIgnoreCase(TestConstants.SaveButton))
			{
				//driver.findElement(By.xpath("//input[@value='Save']")).click();
				driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				log.info("Menu successfully saved.");	
			}
			else 
			{
				//driver.findElement(By.xpath("//span[text()='Submit']")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
				driver.findElement(By.id("submitAddMenu")).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				log.info("Menu successfully submitted.");	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Test case failed to execute");
		}		
	}
}//End of Class
		

