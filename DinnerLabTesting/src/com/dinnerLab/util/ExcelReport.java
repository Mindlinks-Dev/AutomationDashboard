/**
 * @author: Basappa Hunsikatti
 * @Created Date :12/08/2015
 * @Updated Date :
 * @Comments This automation class will write Result into Excel.
 */
package com.dinnerLab.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.pack.AutomateLoader;

public class ExcelReport {
	
	static String InputExcelFilePath = null;
	static String InputExcelFile = null;
	
	/**
	 	* Test Case for fetching Excel File Path.
	 	* Input: WebDriver
	 	* Output: Void
	 **/
	static{
		ExcelReport.InputExcelFile =AutomateLoader.config.getProperty("INPUT_EXCEl_FILE_PATH");
	 	//ExcelLib.InputExcelFilePath = InputExcelFile+" Data.xls";
	 	//ExcelLib.InputExcelFilePath = TestConstants.InputExcelFilePath;
		ExcelReport.InputExcelFilePath = ExcelReport.InputExcelFile;
	}
	
	/**
		 * Test Case for writing Result into Excel.
		 * Input: WebDriver
		 * Output: Void
	**/
	public void excelReport(String sheetname,int row,int cell,String setvalue)
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
	        fos.close();
	     }catch (Exception e) 
	     {
	    	 e.printStackTrace();
	     }
	}
}
