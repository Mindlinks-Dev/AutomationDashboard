import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;


public class UploadFilesTest 
{
	private static Logger log = Logger.getLogger(UploadFilesTest.class);
	public WebDriver driver;
	 
	public void UploadFilesTest() throws Exception
	{
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		driver.manage().window().maximize();
		
		UploadFilesTestPage ufp=new UploadFilesTestPage();
		//ufp.UploadFilesTestInitialPage(driver);
		
	}
	
}
