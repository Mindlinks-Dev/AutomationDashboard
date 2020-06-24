package com.zap.exefile;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ZapExecutionFile 
{
	// public static String path = "C:/Program Files (x86)/OWASP/Zed Attack Proxy/ZAP.exe";
	
	 public static void main(String[] args) throws IOException
	 {
		 	// Calling the runZap method to open the zap tool
			//ZapExecutionFile zap = new ZapExecutionFile();
		 	//System.out.println("Main Class");
			//ZapExecutionFile.runZap();
		 	
		 /*String path="cmd /c start /MAX C://OWASP//Zed Attack Proxy//zap.bat";
			System.out.println("Path: "+path);
			Runtime rn=Runtime.getRuntime();
			try 
			{
				Process pr=rn.exec(path);
				Thread.sleep(8000);
				//Desktop.getDesktop().open(new File("C:\\OWASP\\Zed Attack Proxy\\zap.bat"));
				
				//closing the command prompt
				/*String path1="taskkill /f /im cmd.exe";
				Runtime rn1=Runtime.getRuntime();
				Process pr1=rn1.exec(path1);
				System.out.println("Process to exit the command:"+pr1);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		*/
		 
		 ZapExecutionFile.runZAp();
	 }
	 public static void runZAp()
	 {
		 BufferedWriter fileOut;
		 String itsFileLocation = "c:\\OWASP\\Zed Attack Proxy\\";
				    /*System.out.println(itsFileLocation);
				    try 
				    {
				     fileOut = new BufferedWriter(new FileWriter("c:\\zap.bat"));
				     fileOut.write("cd\\"+"\n");
				     fileOut.write("cd "+ itsFileLocation +"\n");
				     fileOut.write("ZAP.exe"+"\n");
				     fileOut.write("exit"+"\n");
				     
				     
				     fileOut.close(); // Close the output stream after all output is done.
				    } catch (IOException e1)
				    {
				     // TODO Auto-generated catch block
				     e1.printStackTrace();
				    } */
				    // Create the Buffered Writer object to write to a file called filename.txt
				    Runtime runtime = Runtime.getRuntime();
				    System.out.println(itsFileLocation);
				    try {
				     //Process process =runtime.exec("cmd /c start C://OWASP//ZedAttackProxy//zap.bat");
				    	//Process p=runtime.exec("cmd /c start C://OWASP//ZedAttackProxy//zap.bat");
				    	//Process p = Runtime.getRuntime().exec("cmd /c start C://OWASP//ZedAttackProxy//zap.bat");

				        //Process p = Runtime.getRuntime().exec("cmd.exe /c start dir ");
				    	
				    	Runtime.getRuntime().exec("cmd /c start C:\\OWASP\\ZedAttackProxy\\ZAP.exe");
				    	
				    	
				    	
				    	//Process p = Runtime.getRuntime().exec("cmd /c start C://OWASP//ZedAttackProxy//zap.bat");
				    	
				    	//System.out.println("After process completion"+p);
				    } catch (IOException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				    }
	 }
}
