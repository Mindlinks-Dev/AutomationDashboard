/**
 * @author: Basappa Hunsikatti
 * @Created Date :22/02/2016
 * @Updated Date :
 * @Comments:This automation class will view Member Statistics
*/
package com.dinnerLab.adminTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.dinnerLab.adminPages.Helpers.MemberStatisticsPage;
import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.pack.ScriptHandler;

public class MemberStatisticsTest 
{
	private static Logger log = Logger.getLogger(MemberStatisticsTest.class);
	public WebDriver driver;
	 
	@Test 
	public void memberStatisticsTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.Browser_Firefox);
		driver.manage().window().maximize();
		
		MemberStatisticsPage statistics = new MemberStatisticsPage();
		statistics.AdminLogin(driver);
		//statistics.memberStatisticsInitialPage(driver);
		
		//Condition to Close Browser
		if(ScriptHandler.scriptIdFlag!=TestConstants.MEMBER_STATISTICS)
		{
			log.info("-------Closing the browser--------");
			//Close opened browser
			driver.close();
			//Quit all the opened browsers
			driver.quit();
		}
	}
}
