package com.jandk.userpage;

import java.beans.Statement;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import com.dinnerLab.util.ExcelLib;

public class CreateTransactionPage 
{
WebDriver driver;
	
	public String Transactiontype;
	public String Company;
	public String Account;
	boolean present;
	Statement stm=null;
	String isTestPassed="FAIL";
	public static boolean newProductstatus;
    int i =0;
    
    private static Logger log = Logger.getLogger (CreateTransactionPage.class);
    
    //Test Case for Reading the excel Quotation data.
    public boolean transactionInitialPage(WebDriver driver) throws InvalidFormatException
	{
		try
		{
			ExcelLib xllib = new ExcelLib();
			
		}
		catch(Exception ex)
		
		{
		
		}
		
		return false;
}
}
