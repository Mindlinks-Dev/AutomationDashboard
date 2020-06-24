package com.QuartzSchedulerClasses;



import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.src.test.java.com.cohtestcases.Create_Account;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class FirstJob  implements Job {

	public static String [] selectedScript;
	
   
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
	     System.out.println("This is my first quartz job!!");
	     System.out.println("Open Chrome Page");
	     System.out.println("--------passing parameters----------- ");
	     
	    /* ProductTestClass schedulertest = new ProductTestClass();
	     try {
			schedulertest.ProductTestClass();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    MailAttachmentTester EmailAttachment = new MailAttachmentTester();
	    EmailAttachment.EmailAttachment();
	*/
	 
	}

}