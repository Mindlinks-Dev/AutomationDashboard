import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.DriverManager;
import com.stos.usersPage.ForecastTest;
import com.stos.usersPage.helpers.ForecastTestPage;
import com.stos.usersPage.helpers.LoginTestPage;


public class DatepickerTest
{
	private static Logger log = Logger.getLogger(DatepickerTest.class);
	public WebDriver driver;
	 
	@Test 
	public void DatepickerTest() throws Exception
	 {
		log.info("-------Opening the browser--------");
		DriverManager loadBrowser = new DriverManager();
	 	driver = loadBrowser.getDriverForBrowser(TestConstants.BROWSER_CHROME);
		driver.manage().window().maximize();
		
		DatepickerTestPage date = new DatepickerTestPage();
		date.DatepickerTestinititalPage(driver);
		
	}
}
