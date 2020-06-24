package com.dinnerLab.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.pack.AutomateLoader;

public class OutputReport {
	
	public static String outputReportFilePath = AutomateLoader.config.getProperty("OUTPUT_REPORT_LOCATION"); 
	//outputReportFilePath = AutomateLoader.config.getProperty("OUTPUT_REPORT_LOCATION");
	public static int rowNmber;
	public static int lastRowNumber;

	public static List<Integer> RowNumbers = new ArrayList<>();
	
	
	public static Map<String, Map<String, String>> outerMap =  new HashMap<String, Map<String, String>>();
	//String rowNumber= Integer.toString(rowNum);
	
	/*outerMap.put(rowNumber,new HashMap(){{put("0","Sign up");}});
	outerMap.put(rowNumber,new HashMap(){{put("1","User type");}});
	outerMap.put(rowNumber,new HashMap(){{put("2","");}});
	outerMap.put(rowNumber,new HashMap(){{put("3","Status(Pass/Fail)");}});*/
	
	/*static{
		//Row 1 - Columns
		Map<String,String> innerMap1 = new HashMap<String,String>();
		innerMap1.put("0","Sign up");
		innerMap1.put("1","User type");
		innerMap1.put("2","");
		innerMap1.put("3","Status(Pass/Fail)");
		outerMap.put("1", innerMap1);

		//Row 2 - Columns
		Map<String,String> innerMap2 = new HashMap<String,String>();
		innerMap2.put("0","");
		innerMap2.put("1","City");
		innerMap2.put("2","");
		innerMap2.put("3","");
		outerMap.put("2", innerMap2);
		System.out.println("innerMap2:"+innerMap2);
		
		//Row 3 - Columns
		Map<String,String> innerMap3 = new HashMap<String,String>();
		innerMap3.put("0","Purchase");
		innerMap3.put("1","Stripe Customer");
		innerMap3.put("2","Customer Details");
		innerMap3.put("3","");
		outerMap.put("3", innerMap3);
		System.out.println("innerMap3:"+innerMap3);
		
		//Row 3 - Columns
		Map<String,String> innerMap4 = new HashMap<String,String>();
		innerMap4.put("0","Stripe");
		innerMap4.put("1","Customer Details");
		innerMap4.put("2","");
		innerMap4.put("3","");
		outerMap.put("9", innerMap4);
		System.out.println("innerMap4:"+innerMap4);

	}*/
	
	public  void outputReport(String sheetname,int row,int cell,String setvalue)throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
			System.out.println("Sheet name ::"+sheetname);
			System.out.println("row parameters:"+row);
			System.out.println("cell value ::"+cell);
			System.out.println("set value ::"+setvalue);
		

			RowNumbers.add(10000000);
		
			Row r = null;
			FileInputStream fis = new FileInputStream(outputReportFilePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetname);
			

			if(!RowNumbers.contains(row))
			{
				System.out.println("working inside create row");
				r=s.createRow(row);				
			}
			else
			{
				System.out.println("working inside get row");
				r = s.getRow(row);
				//c1 = r.getCell(cell);				
			}
			
			RowNumbers.add(row);

			Cell c1 = r.createCell(cell);
			c1.setCellValue(setvalue);
			
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(outputReportFilePath);
			wb.write(fos);
			//System.out.println("successfully copied");
			lastRowNumber = s.getLastRowNum();
			System.out.println("rowNumber:"+lastRowNumber);
			fos.flush();
			fos.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
   }//end of method

	/**
	 * Method is used to count number of rows in excel file.
	 * Input: Sheet Name
	 * Output: Integer
	 */
	public int getRowCount(String sheetName) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		//System.out.println("Calling row count");
		int rowCount=0;
		try 
		{
			//System.out.println("before file");
			FileInputStream fis = new FileInputStream(outputReportFilePath);
			//System.out.println("after file");
			//System.out.println("File:"+fis);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);		
			rowCount = s.getLastRowNum();
			System.out.println("Output Report rowCount:"+rowCount);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (InvalidFormatException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return rowCount;
	}
	
	//public static void main(String[] args)
	public int deleteRowsData(String sheetName) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		try
		{
		    Workbook wb = WorkbookFactory.create(new FileInputStream(outputReportFilePath));
		    Sheet sheet = wb.getSheet(sheetName);
		    Workbook wb2 = new HSSFWorkbook();
		    wb2 = wb;

		    Row row;
		    row = sheet.getRow(0);
		    int getLastCell=row.getLastCellNum()-1;
		   // System.out.println("get last cell value ::"+getLastCell);
		    int lastIndex = sheet.getLastRowNum();
		    System.out.println("last index value ::"+lastIndex);
		    for (int i=0; i<=lastIndex; i++)
		    {
		    	//System.out.println("value of i"+i);
		    	row=sheet.getRow(i);
		      	sheet.removeRow(row);
		    }
		    FileOutputStream fileOut = new FileOutputStream(outputReportFilePath);
		    wb2.write(fileOut);
		    fileOut.close();
		}
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		 return 0;
	}
}
