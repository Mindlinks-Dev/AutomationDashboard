package com.practice;


import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dinnerLab.util.ExcelLib;


public class LoginPage {
	public static String LoginURL;
	public static String Username;
	public static String Password;
	public WebDriver driver;
	@FindBy(id="username")
	private WebElement Usernme;
	
	@FindBy(id="password")
	private WebElement Pass;
	
	@FindBy(id="Login")
	private WebElement login;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void Login() throws Exception
	{
		
		ExcelLib getExcelData = new ExcelLib();
		int RowCount = getExcelData.getRowCount("RedcrossLogin");
		
		for (int i = 1; i <= RowCount; i++) 
		{
	 		 LoginURL = getExcelData.getExcelData("RedcrossLogin", i, 0);
	 		 Username = getExcelData.getExcelData("RedcrossLogin", i, 1);
			 Password = getExcelData.getExcelData("RedcrossLogin", i, 2);
				driver.get(LoginPage.LoginURL);
				 Usernme.sendKeys(Username);
				 Pass.sendKeys(Password);
			
			 
		}
	}
	
}
