import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dinnerLab.util.ExcelLib;
import com.stos.usersPage.helpers.ForecastTestPage;


public class DatepickerTestPage
{
	public int rowCount;
	int i=0;
	ExcelLib xllib = new ExcelLib();
	
	private static Logger log = Logger.getLogger(ForecastTestPage.class);
	public WebDriver driver;
	
	public void DatepickerTestinititalPage(WebDriver driver)
	{
		try
		{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public boolean DatepickerTestActionPage(WebDriver driver)
	{
		try
		{
			log.info("Date picker flow");
			
			driver.findElement(By.id("datepicker")).click();
			  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			  //Click on next so that we will be in next month
			  driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
			  
			  /*DatePicker is a table.So navigate to each cell 
			   * If a particular cell matches value 13 then select it
			   */
			  WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
			  List<WebElement> rows=dateWidget.findElements(By.tagName("tr"));
			  List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
			  
			  
			for (WebElement cell:columns)
			  {
			   //Select 13th Date 
			   if (cell.getText().equals("13"))
			   {
			   cell.findElement(By.linkText("13")).click();
			   }
			 }
			   
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
}
