package com.jmeter.batchFile;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.dinnerLab.util.ExcelLib;
import com.google.common.io.CharStreams;


public class JmeterBatchFile 
{
	static String isTestPassed="FAIL";
	static boolean exitStatus=false;
	static int i=0;
	static ExcelLib xllib = new ExcelLib();
	
	 public static void main(String[] args) throws IOException, InterruptedException, InvalidFormatException
	 
	{
		    Runtime rn=Runtime.getRuntime();
			//rn.exec(new String[]{"cmd.exe","/c","start","D:/jmeter/apache-jmeter-3.3/bin/jmeter.bat"});
		    rn.exec(new String[]{"cmd.exe","/c","start","C:/Program Files/OWASP/Zed Attack Proxy/ZAP.exe"});
			System.out.println("Inside JmeterBatchFile class path"); 
			
			
		//String path="cmd /c start /MAX C://jmeter//apache-jmeter-2.13//bin//jmetertest.bat"; 
		//System.out.println("Inside JmeterBatchFile class path:"+path);
		
		try 
		{
			//Process pr=rn.exec(path);
			//System.out.println("Process:"+pr);
			// get the input stream of the process and print it
			/*InputStream in = pr.getInputStream();
			//String text = CharStreams.toString((Readable)in);
			String result = IOUtils.toString(in, Charsets.UTF_8);
			System.out.println("String format:"+result);
			for (int i = 0; i < in.available(); i++) 
			{
				System.out.println("" + in.read());
				
			}
			
		BufferedReader is = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		System.out.println("Stream line :"+is);
		String line=null;
        while ((line = is.readLine()) != null)
        {
            System.out.println("line is"+line);
        }*/
			
		Thread.sleep(10000);
		//Desktop.getDesktop().open(new File("C:\\jmeter\\apache-jmeter-2.13\\bin\\Test1.csv")); // BASSAPA code --SPOO
		//Desktop.getDesktop().open(new File("E:\\jmeter\\apache-jmeter-3.3\\bin\\Test1.csv"));
		Desktop.getDesktop().open(new File("C:\\Program Files\\OWASP\\Zed Attack Proxy\\ZAP.exe"));
		System.out.println("Inside JmeterBatchFile Before taking Excel path:");
		//Desktop.getDesktop().open(new File("C:\\Jmeter\\apache-jmeter-3.1\\bin\\Test1.csv"));
		

		//closing the command prompt
		String path1="taskkill /f /im cmd.exe";
		Runtime rn1=Runtime.getRuntime();
		Process pr1=rn1.exec(path1);
		System.out.println("Process to exit the command:"+pr1);
		
		
		String str="java.lang.ProcessImpl@7fbe847c";
		if(str!=null)
		{
			exitStatus=true;
			System.out.println("Exit Status:"+exitStatus);
		}
		else
		{
			exitStatus=false;
			System.out.println("Exit Status:"+exitStatus);
		}
		
		resultJmeter();
	}
		
		catch (IOException e) 
		{
		
		e.printStackTrace();
		}
	}
	 
	 
	 
	 public static void resultJmeter() throws InvalidFormatException, InterruptedException
	 {
		 
		 ExcelLib xllib = new ExcelLib();
		 int rowCount= xllib.getRowCount("Test1");
		 System.out.println("rowCount:"+rowCount);
		 for (i = 1; i <= rowCount; i++) 
		 {
			 if(exitStatus)
			 {
				 isTestPassed="PASS";
				 xllib.writeToExcel("Test1", i, 2, isTestPassed);
			
			 }
			 else if(!exitStatus)
			 {
				 isTestPassed="FAIL";
				 xllib.writeToExcel("Test1", i, 2, isTestPassed);
			
			 }
		 }//end of for loop
	 }//end of resultJmeter
	
	public static void runJMeter() throws IOException, InterruptedException, InvalidFormatException
	{
		
		
		//String path="cmd /c start /MAX C://jmeter//apache-jmeter-2.13//bin//jmetertest.bat";BASSAPA CODE --SPOO
		String path="cmd /E:/jmeter/apache-jmeter-3.3/bin/jmeter.bat";
		Runtime rn=Runtime.getRuntime();
		try 
		{
		Process pr=rn.exec(path);
		Thread.sleep(8000);
		//Desktop.getDesktop().open(new File("C:\\jmeter\\apache-jmeter-2.13\\bin\\Test1.csv"));
		
		//closing the command prompt
		String path1="taskkill /f /im cmd.exe";
		Runtime rn1=Runtime.getRuntime();
		Process pr1=rn1.exec(path1);
		System.out.println("Process to exit the command:"+pr1);
		
		
		/*String str="java.lang.ProcessImpl@7fbe847c";
		if(str!=null)
		{
			exitStatus=true;
			System.out.println("Exit Status:"+exitStatus);
		}
		else
		{
			exitStatus=false;
			System.out.println("Exit Status:"+exitStatus);
		}
		*/
		
		//calling resultJmeter class
		resultJmeter();;
		
	}//try end
		
		catch (IOException e) 
		{
		
			e.printStackTrace();
		
		}
		
	}//end of runJmeter
	
	public static void runJMetereLift() throws IOException, InterruptedException, InvalidFormatException
	{
		
		
		String path="cmd /c start /MAX C://jmeter//apache-jmeter-2.13//bin//jmeterelift.bat";
		Runtime rn=Runtime.getRuntime();
		try 
		{
		Process pr=rn.exec(path);
		Thread.sleep(8000);
		//Desktop.getDesktop().open(new File("C:\\jmeter\\apache-jmeter-2.13\\bin\\Test1.csv"));
		
		//closing the command prompt
		String path1="taskkill /f /im cmd.exe";
		Runtime rn1=Runtime.getRuntime();
		Process pr1=rn1.exec(path1);
		System.out.println("Process to exit the command:"+pr1);
		
		
		String str="java.lang.ProcessImpl@7fbe847c";
		if(str!=null)
		{
			exitStatus=true;
			System.out.println("Exit Status:"+exitStatus);
		}
		else
		{
			exitStatus=false;
			System.out.println("Exit Status:"+exitStatus);
		}
		
		//calling resultJmeter class
		resultJmeter();;
		
	}//try end
		
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
		
	}//end of runJMetereLift
	
	
}
