package com.pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditPieChartContents {
	
	String reportURL = AutomateLoader.config.getProperty("COMPLETE_REPORT_URL");
	String sourcetemplate = AutomateLoader.config.getProperty("SOURCE_REPORT_PIE_CHART_LOCATION");
	/**
	 * Test Case for Edit Pie Chart Contents
	 * Input: 
	 * Output: Void
	 */
	public void editPieChartContentsActions()
	{
		String currentLine = null;
		//String directory = reportURL+ScriptHandler.runIncrement+"/";
		String directory = reportURL+ScriptHandler.runIncrement;
		System.out.println("DIRECTORY::"+directory);
		System.out.println("ScriptHandler.runIncrement:"+ScriptHandler.runIncrement);
		//String directory = "E:/DinnerLab_Workspace/DinnerLabAutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/DinnerLabTesting/Report/Results/Run_145";
		//String directory = AutomateLoader.config.getProperty("REPORT_PIE_CHART_RUN_NO_LOCATION");
		//String directory = ReportSync
				
		boolean check = new File(directory,"pieChart.js").exists();
		if(check){
			System.out.println("FILE EXIST::"+directory);
		}
		else{
			System.out.println("FILE NOT EXIST::"+directory); 
			//String sourcetemplate="E:/DinnerLab_Workspace/DinnerLabAutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/DinnerLabTesting/Report/Results/pieChart.js";
			//String destinationtemplate="E:/DinnerLab_Workspace/DinnerLabAutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/DinnerLabTesting/Report/Results/Run_145/pieChart.js";
			String destinationtemplate = directory+"/pieChart.js";
			System.out.println("destinationtemplate:"+destinationtemplate);
			FileInputStream fin=null;
			FileOutputStream fout=null;
			
			try {
				fin=new FileInputStream(sourcetemplate);
			} catch (FileNotFoundException e1) {
				//e1.printStackTrace();
			}  
			try {
				fout=new FileOutputStream(destinationtemplate);
			} catch (FileNotFoundException e1) {
				//e1.printStackTrace();
			}
			
			int i=0;  
			try {
				while((i=fin.read())!=-1)
				{  
					fout.write((byte)i);  
				}
			}catch (IOException e1) {
				//e1.printStackTrace();
			}  
			try {
				fin.close();
				fout.close();
			} catch (IOException e1) {
				//e1.printStackTrace();
			} 
			
			File readCurrentRun=new File(directory+"/CurrentRun.html");
			System.out.println("readCurrentRun:"+readCurrentRun);
			
			Scanner scanner = null;
			try {
				scanner = new Scanner(readCurrentRun);
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			}
			
			int lineNumber = 0;
			Boolean passedLine = false;
			 List<String> list=new ArrayList<>();
			while(scanner.hasNextLine())
			 {
			     list.add(scanner.nextLine()); 
			 }
			
			if(list.contains("<td>Passed</td>"))
			{
				System.out.println("String Found ");
			}
			else
			{
				System.out.println("String not  Found "+list);	
			}
			/*iteraing over a list
		 	ListIterator <String> listiterator = list.listIterator();
			String temp="<td>Passed</td>";
			while(listiterator.hasNext())
			{
			 
			}
			 */
			//checking for passed condition
			for( int j = 0; j < list.size(); j++ )
			{
				if(list.get(j).contains("<td>Passed</td>"))
				{
					currentLine=list.get(j+2);
		    	}
		    }
			System.out.println("printing current line"+currentLine);
			//checking for failed condition 
			for( int j = 0; j < list.size(); j++ )
			{
				if(list.get(j).contains("<td>Failed</td>"))
				{
					currentLine=currentLine + list.get(j+2);
		    	}
		    }
			System.out.println("after checking for passsed and failed condtions printing current line"+currentLine);
			//checking for skipped condition 
			for( int j = 0; j < list.size(); j++ )
			{
				if(list.get(j).contains(" <td>Skipped</td>"))
				{
					currentLine=currentLine + list.get(j+2);
		    	}
		    }
			System.out.println("after checking for passsed and failed  and skipped condtions printing current line"+currentLine);
			String newString=currentLine.trim();
			newString=newString.replaceAll("\\s+","");
			newString=newString.replaceAll("<td>+","");
			newString=newString.replaceAll("</td>+"," ");
		 
			System.out.println("printing newString"+newString );
			String [] values=newString.split(" ");
			System.out.println("printinvalues array String :"+values[0] );
			System.out.println("printinvalues array String :"+values[1] );
			System.out.println("printinvalues array String :"+values[2] );
		
			//code to read a js file
		
			String newtext=null;
			try
			{
				//String FileName="E:/DinnerLab_Workspace/DinnerLabAutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/DinnerLabTesting/Report/Results/Run_145/pieChart.js";
				String FileName = reportURL+ScriptHandler.runIncrement+"/pieChart.js";
				File file = new File(FileName);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = "", oldtext = "";
				//String lineToRemove = "//";
				while((line = reader.readLine()) != null)
				{
					oldtext += line+"\n";
					// newtext = oldtext1.replace("var data = [['Passed', 1], ['Failed', 0], ['Skipped', 0]];", "var data = [['Passed', '"+values[0]+"'], ['Failed', '"+values[1]+"'], ['Skipped', '"+values[2]+"']];");
				}
				reader.close();
				System.out.println("printing old text :"+oldtext);
				newtext = oldtext.replace("var data = [['Passed', 1], ['Failed', 0], ['Skipped', 0]];", "var data = [['Passed', "+values[0]+"], ['Failed', "+values[1]+"], ['Skipped', "+values[2]+"]];");
				FileWriter writer = new FileWriter(FileName);
				writer.write(newtext);
				writer.close();
			}catch (IOException ioe)
			{
				//ioe.printStackTrace();
			}
		}
	}
	
	/*public static void main(String args[])
	{
		EditPieChartContents edit = new EditPieChartContents();
		edit.editPieChartContentsActions();
	}*/
}
