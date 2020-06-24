/**
 * @author: Anitha M
 * @Created Date :07/07/2015
 * @Updated Date :
 * @Comments:This automation class will server Admin Logout Test case
 */
package com.dinnerLab.adminPages.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SFAdminLogoutPageHelper 
{
	private static Logger log = Logger.getLogger(SFAdminLogoutPageHelper.class);
	
	/**
	 * This Method serves the Actions for the SFDC Admin to Logout from an Application
	 * depending on login condition
	 * Input: WebDriver driver
	 * Output: Void
	 * 
	 */
	public void sfAdminLogoutInitialPage(WebDriver driver) 
	{
		
		try
		{
			if(driver.findElement(By.id("userNavLabel")).isDisplayed())
			{
				// User Menu drop down
				log.info("***************************");
				log.info("Logout Processing");
				driver.findElement(By.id("userNavLabel")).click();
				
				// logout menu click action
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[@title='Logout']")).click();
			}
			
		}
		catch(Exception e)
		{
			log.info("Logout from SFDC was Unsucessfull");
			e.printStackTrace();
		}
	}

}
