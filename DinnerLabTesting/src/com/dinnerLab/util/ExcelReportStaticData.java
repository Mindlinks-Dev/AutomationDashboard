/**
 * @author: Basappa Hunsikatti
 * @Created Date :16/09/2015
 * @Updated Date :
 * @Comments This automation class will write Result into Excel.
 */
package com.dinnerLab.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.pack.AutomateLoader;

public class ExcelReportStaticData 
{
	
	static String InputExcelFilePath = null;
	static String InputExcelFile = null;
	public static int lastRowNumber;
	
	/**
	 	* Test Case for fetching Excel File Path.
	 	* Input: WebDriver
	 	* Output: Void
	 **/
	static{
		ExcelReport.InputExcelFilePath =AutomateLoader.config.getProperty("OUTPUT_REPORT_LOCATION");
		System.out.println("File Path:"+ExcelReport.InputExcelFilePath);
	 }
	
	/**
		 * Test Case for writing Result into Excel.
		 * Input: WebDriver
		 * Output: Void
	**/
	public void excelReportStaticData(String sheetname,int row,int cell,String setvalue)
	{
		try 
	    {
			FileInputStream fis = new FileInputStream(InputExcelFilePath);
	        Workbook wb = WorkbookFactory.create(fis);
	        Sheet s = wb.getSheet(sheetname);
	        Row r=s.getRow(row);
	        Cell c=r.createCell(cell);
	        c.setCellValue(setvalue);
	        fis.close();
	        FileOutputStream fos = new FileOutputStream(InputExcelFilePath);
	        wb.write(fos);
	        lastRowNumber = s.getLastRowNum();
			System.out.println("rowNumber:"+lastRowNumber);
			fos.flush();
			fos.close();
	     }catch (Exception e) 
	     {
	    	 e.printStackTrace();
	     }
	}
}
