package com.jmeter.batchFile;

import org.testng.annotations.Test;
import com.dinnerLab.util.ExcelLib;

public class Openjmeterbatchfile 
{
	static String isTestPassed="FAIL";
	static boolean exitStatus=false;
	static int i=0;
	static ExcelLib xllib = new ExcelLib();
	
	@Test
	 public void StappLoginLogout() throws Exception
	//public static void main(String[] args) throws IOException, InterruptedException, InvalidFormatException
	
	{
		
		Runtime rt = Runtime.getRuntime();
		rt.exec(new String[]{"cmd.exe","/c","start","D:/jmeter/apache-jmeter-3.3/bin/jmeter.bat"});
		System.out.println("Inside JmeterBatchFile class path");
				
	}

}