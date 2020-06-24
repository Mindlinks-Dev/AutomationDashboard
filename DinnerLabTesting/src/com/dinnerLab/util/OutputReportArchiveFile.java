package com.dinnerLab.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pack.AutomateLoader;

public class OutputReportArchiveFile 
{
	//Copying input Excel data to Archive File.
	public void copyExcelReportActions(int execId) throws Exception
	{
		try
		{
			String ExcelReportFilePath=AutomateLoader.config.getProperty("OUTPUT_REPORT_ARCHIVE_LOCATION");
			File file1 = new File(ExcelReportFilePath);
			
			XSSFWorkbook  workbook1 = new XSSFWorkbook(file1);
			
			String OutputReportArchivedFilePath=AutomateLoader.config.getProperty("OUTPUT_REPORT_LOCATION");
		 	FileOutputStream report = new FileOutputStream(OutputReportArchivedFilePath+"ExcelReport_"+execId+"_"+new SimpleDateFormat("yyyyMMdd'.xls'").format(new Date())+"");
  
		 	workbook1.write(report); 	 	
		 	report.close();
		 	workbook1.close();
			
		   // XSSFWorkbook  workbook1 = new XSSFWorkbook(OutputReportArchivedFilePath);
		   // System.out.println("OutputReportArchivedFilePath+'ExcelReport_'+execId+'_'+new SimpleDateFormat('yyyyMMdd'.xls'').format(new Date())+''"+ OutputReportArchivedFilePath+"ExcelReport_"+execId+"_"+new SimpleDateFormat("yyyyMMdd'.xls'").format(new Date())+"" );
		 	System.out.println("report:"+report);
		 	
		 	

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
}
