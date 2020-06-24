/**
 * @author: Anitha M
 * @Created Date :10/5/2016
 * @Updated Date :
 * @Comments : This automation class will script for Dinner Menu helper page.
 */
package com.dinnerLab.adminPages.Helpers;


import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.dinnerLab.util.ExcelLib;

public class DinnerMenuPageHelper
{
	private static Logger log = Logger.getLogger(DinnerMenuPageHelper.class);
	
	private String dinnername;
	private String coursename;
	private String components;
	private String courseorder;
	private String servingdish;
	public String chefcoursecount;
	
	String isTestPassed="FAIL";
	int i;
	ExcelLib xllib=new ExcelLib();
	
	
	/**
	 * This Method serves as reading the excel data for Adding Menu for Dinner.
	 * Input: WebDriver driver
	 * Output: Void
	 * @throws InvalidFormatException 
	 */
	public void dinnerMenuInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			
			int rowCount=xllib.getRowCount("dmenu");
			for(i=1;i<=rowCount;i++)
			{
				dinnername=xllib.getExcelData("dmenu", i, 0);
				coursename=xllib.getExcelData("dmenu", i, 1);
				components=xllib.getExcelData("dmenu", i, 2);
				courseorder=xllib.getExcelData("dmenu", i, 3);
				servingdish=xllib.getExcelData("dmenu", i, 4);
				chefcoursecount=xllib.getExcelData("dmenu", i, 5);
				DinnerMenuPageHelper dm=new DinnerMenuPageHelper();
				Thread.sleep(2000);
				int res=dm.dinnerMenuActionPage(driver,dinnername,coursename,components,courseorder,servingdish,chefcoursecount);
				//int res1=dm.Chefmenu(driver,chefcoursecount);
				if(res==1)
				{
					log.info("Menu Created successfully");
					isTestPassed="PASS";
    	 			System.out.println("Pass Result :"+isTestPassed);
    			    xllib.writeToExcel("dmenu", i, 6, isTestPassed);
				}
			}
		}//try end
		catch(Exception e)
		{
			log.info("Failed in creating menu");
			isTestPassed="FAIL";
 			System.out.println("Pass Result :"+isTestPassed);
		    xllib.writeToExcel("dmenu", i, 6, isTestPassed);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This Method serves Action for Adding menu for created Dinner.
	 * Input: WebDriver driver,DinnerNAme,CourseName,Components,CourseOrder,ServingDish
	 * Output: int
	 */
	public int dinnerMenuActionPage(WebDriver driver, String Dinnername,String Coursename,
			String Components, String Courseorder, String Servingdish,String Chefcoursecount)
	{
		try
		{
			log.info("Creating the menu");
			driver.findElement(By.id("phSearchInput")).clear();
			driver.findElement(By.id("phSearchInput")).sendKeys(Dinnername); //Search input text
			driver.findElement(By.id("phSearchButton")).click(); // Search button
			
			driver.findElement(By.xpath("//a[contains(text(),'"+Dinnername+"')]")).click(); //DinnerName link
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@id='topButtonRow']/input[@title='MENU']")).click(); //Menu button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='j_id0:j_id2:j_id4:j_id57:j_id58']")).click();//New button
			Thread.sleep(2000);
			driver.findElement(By.id("j_id0:j_id2:j_id4:j_id70:j_id74")).clear();
			driver.findElement(By.id("j_id0:j_id2:j_id4:j_id70:j_id74")).sendKeys(Coursename);//Coursename
			
			driver.findElement(By.id("j_id0:j_id2:j_id4:j_id70:j_id73")).clear();
			driver.findElement(By.id("j_id0:j_id2:j_id4:j_id70:j_id73")).sendKeys(Components);//Components
			
			WebElement ele=driver.findElement(By.id("j_id0:j_id2:j_id4:j_id70:j_id77"));
			Select st=new Select(ele);
			st.selectByVisibleText(Courseorder); //Courseorder
			
			WebElement ele1=driver.findElement(By.id("j_id0:j_id2:j_id4:j_id70:j_id76"));
			Select st1=new Select(ele1);
			st1.selectByVisibleText(Servingdish);//serving dish
			
			driver.findElement(By.id("j_id0:j_id2:j_id4:j_id70:j_id81")).click();//save button
			
			log.info("--------------------------");
			log.info("Adding chef course to menu");
			log.info("--------------------------");
			int n=Integer.parseInt(Chefcoursecount);
			System.out.println("Chefcourse count:"+Chefcoursecount);
			for(int i=0;i<n;i++)
			{
				//WebDriverWait wait = new WebDriverWait(driver, 40);
				//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:j_id2:j_id125:j_id155:"+i+":ckhSelected1")));
				Thread.sleep(3000);
				driver.findElement(By.id("j_id0:j_id2:j_id125:j_id155:"+i+":ckhSelected1")).click(); //Checkbox
				
			}
			driver.findElement(By.id("j_id0:j_id2:j_id125:j_id152:j_id154")).click(); //AddCourse to menu button
			log.info("Added chef courses successfully");
			return 1;
			
		}//try end
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
			
		}
		
		
	}
	/**
	 * This Method serves Action for Adding Chef Course to Dinner Menu.
	 * Input: WebDriver driver,String ChefCourseCount
	 * Output: int
	 */
	/*public int Chefmenu(WebDriver driver,String Chefcoursecount)
	{
		try
		{
			log.info("Adding chef course to menu");
			
			int n=Integer.parseInt(Chefcoursecount);
			System.out.println("Chefcourse count:"+Chefcoursecount);
			for(int i=0;i<n;i++)
			{
				//WebDriverWait wait = new WebDriverWait(driver, 40);
				//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("j_id0:j_id2:j_id125:j_id155:"+i+":ckhSelected1")));
				Thread.sleep(5000);
				driver.findElement(By.id("j_id0:j_id2:j_id125:j_id155:"+i+":ckhSelected1")).click(); //Checkbox
				
			}
			driver.findElement(By.id("j_id0:j_id2:j_id125:j_id152:j_id154")).click(); //AddCourse to menu button
			log.info("Added chef courses successfully");
			return 1;
		}
		
		catch(Exception e)
		{
			log.info("failed in adding Chef courses to dinner");
			e.printStackTrace();
			return 0;
		}
		
	}*/
	
}
