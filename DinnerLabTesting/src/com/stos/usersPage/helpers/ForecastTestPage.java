package com.stos.usersPage.helpers;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dinnerLab.constants.TestConstants;
import com.dinnerLab.util.ExcelLib;


public class ForecastTestPage
{
	private String Timescale;
	private String AnsTimescale;
	private String Needs;
	private String AnsNeeds;
	private String KeyPlayers;
	private String AnsKeyPlayers;
	private String Budget;
	private String AnsBudget;
	private String Barriers;
	private String AnsBarriers;
	private String Presentation;
	private String AnsPresentation;
	private String Proposal;
	private String AnsProposal;
	private String Relationship;
	private String AnsRelationship;
	private String Competition;
	private String AnsCompetition;
	private String Close;
	private String AnsClose;
	private String SearchOpp;
	private String NewResponse;
	private String Modify;
	
	public static boolean forecastStatus; 
	public static boolean forecastConStatus; 
	public static boolean foreIDText; 
	public static boolean foreIDTextModify;
	public int rowCount;
	int i=0;
	ExcelLib xllib = new ExcelLib();
	
	private static Logger log = Logger.getLogger(ForecastTestPage.class);
	public WebDriver driver;

	public void forecastTestInitialPage(WebDriver driver)
	{
		try
		{
			rowCount= xllib.getRowCount("STOSforecast");
		 	for (i = 1; i <= rowCount; i++) 
			{
		 		Timescale = xllib.getExcelData("STOSforecast", i, 0);
		 		AnsTimescale = xllib.getExcelData("STOSforecast", i, 1);
		 		Needs = xllib.getExcelData("STOSforecast", i, 2);
		 		AnsNeeds = xllib.getExcelData("STOSforecast", i, 3);
		 		KeyPlayers = xllib.getExcelData("STOSforecast", i, 4);
		 		AnsKeyPlayers = xllib.getExcelData("STOSforecast", i, 5);
		 		Budget = xllib.getExcelData("STOSforecast", i, 6);
		 		AnsBudget = xllib.getExcelData("STOSforecast", i, 7);
		 		Barriers = xllib.getExcelData("STOSforecast", i, 8);
		 		AnsBarriers = xllib.getExcelData("STOSforecast", i, 9);
		 		Presentation = xllib.getExcelData("STOSforecast", i, 10);
		 		AnsPresentation = xllib.getExcelData("STOSforecast", i, 11);
		 		Proposal = xllib.getExcelData("STOSforecast", i, 12);
		 		AnsProposal = xllib.getExcelData("STOSforecast", i, 13);
		 		Relationship = xllib.getExcelData("STOSforecast", i, 14);
		 		AnsRelationship = xllib.getExcelData("STOSforecast", i, 15);
		 		Competition = xllib.getExcelData("STOSforecast", i, 16);
		 		AnsCompetition = xllib.getExcelData("STOSforecast", i, 17);
		 		Close = xllib.getExcelData("STOSforecast", i, 18);
		 		AnsClose = xllib.getExcelData("STOSforecast", i, 19);
		 		SearchOpp = xllib.getExcelData("STOSforecast", i, 20);
		 		NewResponse = xllib.getExcelData("STOSforecast", i, 21);
		 		Modify = xllib.getExcelData("STOSforecast", i, 22);
		 		
		 		System.out.println("Key players value"+AnsKeyPlayers);
		 		forecastConStatus=forecastTestConditionPage(driver,NewResponse,Modify,SearchOpp);
		 		
		 		
			}
		 		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	private boolean forecastTestConditionPage(WebDriver driver,
			String newResponse, String modify,String searchOpp) 
	{
		try
		{
			log.info("Opportunity searching....");
			log.info("-------------------------");
			Thread.sleep(9000);
			driver.get("https://stosdev-dev-ed.my.salesforce.com/006/o"); //Opportunity URL
			Thread.sleep(9000);
			driver.findElement(By.xpath("//a[contains(text(),'"+searchOpp+"')]")).click();//searchOpp link
			
			Thread.sleep(6000);
			//foreIDText=driver.findElement(By.xpath("//td[@id='00N28000008VAqX_ilecell']/div")).getText();
			//foreIDText=driver.findElement(By.xpath("//div[contains(text(),'ID-')]")).isDisplayed();
			
				for (String handle : driver.getWindowHandles())
				{
			        driver.switchTo().window(handle);
			        // WebElement body = driver.findElement(By.name("body"));
				    driver.switchTo().frame("06628000002lGeG");
				    Thread.sleep(6000);
				    try
					{
				    	foreIDText=driver.findElement(By.xpath("//button[contains(text(),'New Forecast')]")).isDisplayed();
						System.out.println("Forecast ID:"+foreIDText);
												
					}catch(NoSuchElementException nse)
					{
						nse.printStackTrace();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				  
				    try
					{
				    	foreIDTextModify=driver.findElement(By.xpath("//button[contains(text(),'Modify')]")).isDisplayed();
						System.out.println("foreIDTextModify:"+foreIDTextModify);
						
					}catch(NoSuchElementException nse)
					{
						nse.printStackTrace();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				  
					//driver.switchTo().frame(0);//frame 1
					// driver.findElement(By.xpath("html/body/div[1]/span/div/div/span[1]/div/div/div/button[@id='EditResponse']")).click(); //Modify button
				   // System.out.println("Modify button clicked");
				  
					
					//calling forcast action method
					forecastStatus=forecastTestActionPage(driver,Timescale,AnsTimescale,Needs,AnsNeeds,KeyPlayers,AnsKeyPlayers,
			 				Budget,AnsBudget,Barriers,AnsBarriers,Presentation,AnsPresentation,Proposal,AnsProposal,Relationship,
			 				AnsRelationship,Competition,AnsCompetition,Close,AnsClose,SearchOpp);
			 		if(forecastStatus)
			 		{
			 			log.info("Forecast was successfully created");
			 		}
			 		else
			 		{
			 			log.info("Forecast was Unsuccessfully created");
			 		}
			
			    }//end for 
			
			
			if((foreIDText))
			{
				log.info("New Response forecast");
				driver.findElement(By.xpath("//span[@id='selddevelopmentpage:j_id7']/div[1]/div[1]/div[1]/button[@id='NewResponse']")).click(); //Newforecast button
				
				System.out.println("new forecast if condition");
				log.info("----------------------");
				
				Thread.sleep(8000);
				
			}
			else if((foreIDTextModify))
			{
				log.info("Modify Forecast:");
				log.info("------------------");
				
				System.out.println("else condition Modify button");
				Thread.sleep(8000);
				for (String handle1 : driver.getWindowHandles())
				{
			        driver.switchTo().window(handle1);
			        driver.switchTo().frame("06628000002lGeG");
			        Thread.sleep(3000);
			        driver.findElement(By.xpath("html/body/div[1]/span/div/div/span[1]/div/div/div/button[@id='EditResponse']")).click(); //Modify button
			        //driver.findElement(By.xpath("//span[@id='selddevelopmentpage:j_id7']/div[1]/div[1]/div[1]/button[@id='NewResponse']")).click(); //Newforecast button
			        driver.switchTo().defaultContent();
			        forecastStatus=forecastTestActionPage(driver,Timescale,AnsTimescale,Needs,AnsNeeds,KeyPlayers,AnsKeyPlayers,
			 				Budget,AnsBudget,Barriers,AnsBarriers,Presentation,AnsPresentation,Proposal,AnsProposal,Relationship,
			 				AnsRelationship,Competition,AnsCompetition,Close,AnsClose,SearchOpp);
			 		if(forecastStatus)
			 		{
			 			log.info("Forecast was successfully modified");
			 		}
			 		else
			 		{
			 			log.info("Forecast was Unsuccessfully modified");
			 		}
				}//end for
			driver.switchTo().defaultContent();
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	private boolean forecastTestActionPage(WebDriver driver,
			String timescale, String ansTimescale, String needs,
			String ansNeeds, String keyPlayers, String ansKeyPlayers,
			String budget, String ansBudget, String barriers,
			String ansBarriers, String presentation, String ansPresentation,
			String proposal, String ansProposal, String relationship,
			String ansRelationship, String competition,
			String ansCompetition, String close, String ansClose,String searchOpp)
	{
		try
		{
			
			Thread.sleep(2000);
			if(timescale.equalsIgnoreCase(TestConstants.TIMESCALE))
			{
				boolean timessalesiselected=false;
				timessalesiselected=driver.findElement(By.xpath("//a[contains(text(),'Timescale')]")).isSelected();
				if(timessalesiselected)
				{
					Thread.sleep(2000);
					if(ansTimescale.equalsIgnoreCase(TestConstants.ANSTIMESCALE))
						{
							System.out.println("Default value of timescale");
						}
					else
						{
							driver.findElement(By.xpath("//div[@id='Timescale']/div[1]/div[1]/div[1]/div['"+ansTimescale+"']/input[@value='"+ansTimescale+"']")).click();
						}	
					
				}
				else
				{
				driver.findElement(By.xpath("//a[contains(text(),'Timescale')]")).click(); //Timescale tab
				Thread.sleep(2000);
				if(ansTimescale.equalsIgnoreCase(TestConstants.ANSTIMESCALE))
					{
						System.out.println("Default value of timescale");
					}
				else
					{
						driver.findElement(By.xpath("//div[@id='Timescale']/div[1]/div[1]/div[1]/div['"+ansTimescale+"']/input[@value='"+ansTimescale+"']")).click();
					}
				}//end else statement
			}
			
			if(needs.equalsIgnoreCase(TestConstants.NEEDS))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Need')]")).click(); //Needs tab
				Thread.sleep(3000);
				if(ansNeeds.equalsIgnoreCase(TestConstants.ANSNEEDS))
				{
					System.out.println("Default value of Needs");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='Need']/div[1]/div[1]/div[1]/div['"+ansNeeds+"']/input[@value='"+ansNeeds+"']")).click();
				}
			}
			
			if(keyPlayers.equalsIgnoreCase(TestConstants.KEYPLAYERS))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Key Players')]")).click(); // Key Players tab
				Thread.sleep(3000);
				if(ansKeyPlayers.equalsIgnoreCase(TestConstants.ANSKEYPLAYERS))
				{
					System.out.println("Default value of Keyplayers");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='KeyPlayers']/div[1]/div[1]/div[1]/div['"+ansKeyPlayers+"']/input[@value='"+ansKeyPlayers+"']")).click();
				}
			}
			if(budget.equalsIgnoreCase(TestConstants.BUDGET))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Budget')]")).click(); //Budget tab
				Thread.sleep(3000);
				if(ansBudget.equalsIgnoreCase(TestConstants.ANSBUDGET))
				{
					System.out.println("Default value of Budget");
				}
				else
				{
				driver.findElement(By.xpath("//div[@id='Budget']/div[1]/div[1]/div[1]/div['"+ansBudget+"']/input[@value='"+ansBudget+"']")).click();
				}
			}
				
			if(barriers.equalsIgnoreCase(TestConstants.BARRIERS))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Barriers')]")).click(); //Barriers tab
				Thread.sleep(3000);
				if(ansBarriers.equalsIgnoreCase(TestConstants.ANSBARRIERS))
				{
					System.out.println("Default value of Barriers");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='Barriers']/div[1]/div[1]/div[1]/div['"+ansBarriers+"']/input[@value='"+ansBarriers+"']")).click();
				}
			}
			if(presentation.equalsIgnoreCase(TestConstants.PRESENTATION))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Presentation')]")).click(); //Presentation tab
				Thread.sleep(3000);
				if(ansPresentation.equalsIgnoreCase(TestConstants.ANSPRESENTATION))
				{
					System.out.println("Default value of Presentation");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='Presentation']/div[1]/div[1]/div[1]/div['"+ansPresentation+"']/input[@value='"+ansPresentation+"']")).click();
				}
			}
			if(proposal.equalsIgnoreCase(TestConstants.PROPOSAL))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Proposal')]")).click(); //Proposal tab
				Thread.sleep(3000);
				if(ansProposal.equalsIgnoreCase(TestConstants.ANSPROPOSAL))
				{
					System.out.println("Default value of Proposal");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='Proposal']/div[1]/div[1]/div[1]/div['"+ansProposal+"']/input[@value='"+ansProposal+"']")).click();
				}
			}
			if(relationship.equalsIgnoreCase(TestConstants.RELATIONSHIP))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Relationship')]")).click(); //Relationship tab
				Thread.sleep(3000);
				if(ansRelationship.equalsIgnoreCase(TestConstants.ANSRELATIONSHIP))
				{
					System.out.println("Default value of Relationship");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='Relationship']/div[1]/div[1]/div[1]/div['"+ansRelationship+"']/input[@value='"+ansRelationship+"']")).click();
				}
			}
			if(competition.equalsIgnoreCase(TestConstants.COMPETITION))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Competition')]")).click(); //Competition tab
				Thread.sleep(3000);
				if(ansCompetition.equalsIgnoreCase(TestConstants.ANSCOMPETITION))
				{
					System.out.println("Default value of Competition");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='Competition']/div[1]/div[1]/div[1]/div['"+ansCompetition+"']/input[@value='"+ansCompetition+"']")).click();
				}
			}
			if(close.equalsIgnoreCase(TestConstants.CLOSE))
			{
				driver.findElement(By.xpath("//a[contains(text(),'Close')]")).click(); //Close tab
				Thread.sleep(3000);
				if(ansClose.equalsIgnoreCase(TestConstants.ANSCLOSE))
				{
					System.out.println("Default value of Close");
				}
				else
				{
					driver.findElement(By.xpath("//div[@id='Close']/div[1]/div[1]/div[1]/div['"+ansClose+"']/input[@value='"+ansClose+"']")).click();
				}
			}
			
			//Forecast Summary
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[contains(text(),'FORECAST SUMMARY')]")).click(); // FORECAST SUMMARY tab
			
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
}
