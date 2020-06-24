package com.quicksolar.userclass;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ClientApi;
import com.dinnerLab.util.DriverManager;
import com.quicksolar.userpage.CreateManageUserPage;
import com.quicksolar.userpage.CreateNewDesignPage;
import com.quicksolar.userpage.CreateNewProjectPage;
import com.quicksolar.userpage.UserloginlogoutPage;
import com.zap.exefile.ZapTools;



public class Userloginlogoutclass
{
	private static Logger log = Logger.getLogger(Userloginlogoutclass.class);
	public WebDriver driver;
	static final int ZAP_SESSION_PORT = 8090;
	static final String ZAP_HOSTNAME = "localhost";
	static final String ZAP_SESSION_IP = "127.0.0.1";
	static final String ZAP_URI_PORT = "http://staging.quicksolar.com/";

	
	/**
	 * Test Case for submitting the Login page depending on the test data passed
	 * valid email and password should allow the user to
	 * login in to the application.
	 * Input: No parameter
	 * Output: Void
	 * @throws Exception 
	 * @throws InvalidFormatException 
	 */	 

	 @Test
	 public void loginPageTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
        System.out.println(" open the zap inside Userloginlogoutclass in this loginPageTest method");
		
        ZapTools zaputil = new ZapTools();
		if (zaputil.startZAP() == false) {

			System.out.println("ZAP failed to start. Terminating...");
			System.exit(0);
		}
		Thread.sleep(5000);
		zaputil.startSession(ZAP_HOSTNAME, ZAP_SESSION_PORT);
		System.out.println(" after session");
		Thread.sleep(12000);
		//==============================================================
		String exepath = "D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\chromedriver_win32(2.36)\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",exepath);
		WebDriver driver = new ChromeDriver();
		System.out.println("Chrome browser has been successfully loaded");
		driver.manage().window().maximize();
		System.out.println("-------Loaded the Driver--------");

		
		
		Thread.sleep(6000);
		UserloginlogoutPage login = new UserloginlogoutPage();
		login.loginInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		

	/*	CreateNewProjectPage a = new CreateNewProjectPage();
		a.NewProjectInitialPage(driver);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);*/
		
		
		//CreateNewDesignPage  b = new CreateNewDesignPage();
		//b.NewDesignInitialPage(driver);
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		
		//CreateManageUserPage c= new CreateManageUserPage();
		//c.ManageUserInitialPage(driver);
	
		
		ClientApi capi = new ClientApi(ZAP_HOSTNAME, ZAP_SESSION_PORT);
		
		//Do spider scan
		if (zaputil.spider(capi, ZAP_URI_PORT) == false) 
		{
			System.out.println("Spider Failed - see console for details. Continuing...");
		}
		
		 
	/*	
		  //Do active scan
		Thread.sleep(20000);
		  if( zaputil.ascan(capi, ZAP_URI_PORT ) == false)
		  { 
			  System.out.println("Active Scan Failed - see console for details. Continuing..." );
		  }
		 
*/
		
		 // check for error after the scan
		 
		 zaputil.checkErrors(capi);
		 System.out.println( zaputil.checkErrors( capi ) );

		
		//save the zap session
		 
		//zaputil.saveSession(capi, "QuicksolarLogin");

		
		// stop/close zap
		 
		zaputil.stopZAP(ZAP_SESSION_IP, ZAP_SESSION_PORT);
	 }
}

