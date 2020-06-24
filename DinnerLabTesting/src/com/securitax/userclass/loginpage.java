package com.securitax.userclass;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginpage 
{
	
WebDriver driver;
    
    public String URL;
    public String USERNAME;
    public String PASSWORD;
    public int rowCount;
    public boolean Loginstatus;
    int i =0;   
    
    public boolean LoginInitialPage(WebDriver driver) throws InvalidFormatException
    {
        try
        {
            ExcelLib xllib = new ExcelLib();
            int loginRowCount = xllib.getRowCount("Login");
            System.out.println("loginRowCount:"+loginRowCount);
            System.out.println("loginRowCount log initialized ");
            for (i = 1; i <= loginRowCount; i++) 
            {
                URL = xllib.getExcelData("Login", i, 0);
                USERNAME = xllib.getExcelData("Login", i, 1);
                PASSWORD = xllib.getExcelData("Login", i, 2);

                Loginstatus = CreateLogin(driver,URL,USERNAME,PASSWORD);
                System.out.println("Login Count:"+i);
                    
            }
        }
        catch(Exception e)
        {
            System.out.println("login Failed");
            e.printStackTrace();
        }
        return Loginstatus;
    }



	private boolean CreateLogin(WebDriver driver, String URL, String USERNAME, String PASSWORD) throws InterruptedException
	{
		//Calling URL
		Thread.sleep(5000);
		driver.get(URL);
		Thread.sleep(5000);
		//Enter username Filed 
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']")).click();
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']")).sendKeys(USERNAME);
		Thread.sleep(5000);
		//Enter Password filed 
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).click();
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).sendKeys(PASSWORD);
		Thread.sleep(5000);	
		//Clicking Login Button
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		//Thread.sleep(5000);
				
		// TODO Auto-generated method stub
		return false;
	}
   

}
