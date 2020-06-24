package com.dinnerLab.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import com.opencsv.CSVReader;
//import com.opencsv.CSVWriter;
import com.pack.AutomateLoader;

public class JmeterReport 
{
	public static String csvReport=null;
	//Copying input Excel data to Archive File.
	public void jmeterReportActions(int execId) throws IOException
	{
		try 
		{
			String JmeterReportLocation=AutomateLoader.config.getProperty("JMETER_REPORT_LOCATION");
			String JmeterReportFolderLocation=AutomateLoader.config.getProperty("JMETER_REPORT_FOLDER_LOCATION");
			//System.out.println("JmeterReportLocation123:"+JmeterReportLocation);
			//System.out.println("JmeterReportFolderLocation:"+JmeterReportFolderLocation);
			String csvFilename = JmeterReportLocation;
			//System.out.println("csvFilename:"+csvFilename);
			
			//Reading data from CSV File
			/*CSVReader csvReader = null;
			List<String[]>  content = null;
			try {
				csvReader = new CSVReader(new FileReader(csvFilename));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			String temp = "";
			String[] row = null;
			try 
			{
				content = csvReader.readAll();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			//System.out.println("List<String[]>  content are: "+content);
			
			for (String[] string : content)
			{
				//System.out.println("string value123 ::"+string.length);
				for (String string1 : string) 
				{
					//System.out.println("value of inside string ::"+string1+",");
					string1 = string1 +",";
					temp = temp + string1 ;
				}
			}
			
			*/
			//System.out.println("temp value ::"+temp);
			csvReport = JmeterReportFolderLocation+"JmeterReport_"+execId+"_"+new SimpleDateFormat("yyyyMMdd'.csv'").format(new Date())+"";
			//System.out.println("csv is:"+csvReport);
			
			//Writing data into CSV File
			/*CSVWriter writer = new CSVWriter(new FileWriter(csvReport));
			writer.writeAll(content);
			writer.flush();
			writer.close();
			getfilePath(csvReport);*/
		}
		
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getfilePath(String path)
	{
		System.out.println("Inside getfilePath : "+path);
		
		return path;
	   	
	}
	
	public String getfilePath(int k)
	{
		
		
		return csvReport;
	   	
	}
	
			
}
