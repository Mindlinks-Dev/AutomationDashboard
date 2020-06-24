/**
 * @author: Basappa Hunsikatti
 * @Created Date :28/04/2015
 * @Updated Date :
 * @Comments This util class is used to read data from excel file,
 * write into excel file and count number of rows in excel file. 
 */
package com.dinnerLab.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dinnerLab.constants.TestConstants;
import com.pack.AutomateLoader;

public class ExcelLib 
{
	
	static String InputExcelFilePath = null;
	static String InputExcelFile = null;
	static String JemeterExcelFile = null;
	static String JemeterExcelFilePath = null;
	private HSSFRow row   =null;
	private HSSFSheet sheet = null;
	private HSSFWorkbook workbook = null;
	private HSSFCell cell = null;
	/*static
    {
  
   	 Properties prop = new Properties();
   	 InputStream readfile = null;
	
		try {
			readfile = new FileInputStream("E:\\DinnerLab_WorkSpace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\WEB-INF\\lib\\config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			prop.load(readfile);
			ExcelLib. =prop.getProperty("INPUT_EXCEl_FILE_PATH"); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
   	 	static{
   	 		ExcelLib.InputExcelFile =AutomateLoader.config.getProperty("INPUT_EXCEl_FILE_PATH"); 
   	 		ExcelLib.JemeterExcelFile =AutomateLoader.config.getProperty("JMETER_EXCEl_FILE_PATH");
   	 		ExcelLib.InputExcelFilePath = InputExcelFile+" Data.xls";
   	 		ExcelLib.InputExcelFilePath = TestConstants.InputExcelFilePath;
   	 		ExcelLib.InputExcelFilePath = ExcelLib.InputExcelFile;
   	 		ExcelLib.JemeterExcelFilePath = ExcelLib.JemeterExcelFile;
   	 		//System.out.println("JemeterExcelFile:"+ExcelLib.JemeterExcelFile);
   	 		//System.out.println("JemeterExcelFilePath:"+ExcelLib.JemeterExcelFilePath);
   	 	}
   	 			
       	/**
		 * Method is used to read data from excel file
		 * Input: Sheet Name, Row Number, Cell Number
		 * Output: String
		*/
		public String getExcelData(String sheetName,int rowNum,int cellNum) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
		{
			   
			String retVal=null;
			try 
			{
				//FileInputStream fis = new FileInputStream(InputExcelFilePath);
				FileInputStream fis = new FileInputStream("D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xlsx");
				Workbook wb = WorkbookFactory.create(fis);
				Sheet s = wb.getSheet(sheetName);
				Row r = s.getRow(rowNum);	
				Cell c = r.getCell(cellNum);
				fis.close();
				//Check whether excel cell contains null value.
				if(c == null)
				{
					retVal = "";
				} 
				else if (c.equals(""))
				{
					retVal = "";
				}
				else if (c.equals(" "))
				{
					retVal = "";
				}
				else
				{
					//Check whether excel cell contains numeric value.
					if(c.getCellType() == Cell.CELL_TYPE_NUMERIC)
					{
						retVal = String.valueOf((long)c.getNumericCellValue());
					}
					//Check whether excel cell contains string value.
					else if(c.getCellType() == Cell.CELL_TYPE_STRING)
					{		
						retVal = c.getStringCellValue();
					}
					else if(c.getCellType() == Cell.CELL_TYPE_BOOLEAN)
					{
						Boolean temp = c.getBooleanCellValue();
						retVal = String.valueOf(temp);
					}
					//Check whether excel cell is blank.
					else if(c.getCellType() == Cell.CELL_TYPE_BLANK)
					{
						retVal = "";
					}
				}
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
			return retVal;
		}
		
		/**
		 * Method is used to count number of rows in excel file.
		 * Input: Sheet Name
		 * Output: Integer
		 * @throws InterruptedException 
		*/
		public int getRowCount(String sheetName) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, InterruptedException
		{
			int rowCount=0;
			try 
			{
				//FileInputStream fis = new FileInputStream(InputExcelFilePath);
				FileInputStream fis = new FileInputStream("D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xlsx");
				//FileInputStream fis = new FileInputStream(JemeterExcelFilePath);
				System.out.println("Inside a getRowCount of  ExcelLib sheetName is :"+sheetName);
				//FileInputStream fis = new FileInputStream("C:/jmeter/apache-jmeter-2.13/bin/loginjmeter.xls");
				 //InputStream is = new FileInputStream("C:/jmeter/apache-jmeter-2.13/bin/Data.xls");
			     Workbook wb = WorkbookFactory.create(fis);
			     Sheet sheet = wb.getSheet(sheetName);
			     Thread.sleep(2000);
			     rowCount = sheet.getLastRowNum();
			     System.out.println("rowCount is :"+rowCount);
			}
			catch (IllegalArgumentException e) 
			{
				e.printStackTrace();
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
		/**
		 * Method is used to write data into excel file
		 * Input: Sheet Name, Row Number, Cell Number,Data
		 * Output: Void
		 * @return 
		*/
		public String writeToExcel(String sheetName, int rowNum, int cellNum, String isTestPassed) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
		{
			
			try 
			{
				//FileInputStream fis = new FileInputStream(InputExcelFilePath);
				FileInputStream fis = new FileInputStream("D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xlsx");
				//FileInputStream fis = new FileInputStream("C:/jmeter/apache-jmeter-2.13/bin/Data.xls");
				//FileInputStream fis = new FileInputStream("C:\\Jmeter\\apache-jmeter-3.1\\bin\\Test1.csv");
				
				//Workbook wb  = new HSSFWorkbook();
				//wb = WorkbookFactory.create(fis);
				Workbook wb = WorkbookFactory.create(fis);
				Sheet s = wb.getSheet(sheetName);
				Row r = s.getRow(rowNum);
				Cell c = r.createCell(cellNum);
				c.setCellValue(isTestPassed);
				fis.close();
				//FileOutputStream fos = new FileOutputStream(InputExcelFilePath);
				FileOutputStream fos = new FileOutputStream("D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xlsx");
				//FileInputStream fos = new FileInputStream("C:/jmeter/apache-jmeter-2.13/bin/Data.xls");
				wb.write(fos);
				fos.close();
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
			return isTestPassed;
		}	
		
		/**
		 * Method is used to create new row in excel file
		 * Input: Sheet Name, Row Number, Cell Number,Data
		 * Output: Void
		 * @return 
		*/
		public String createNewRow(String sheetName, int rowNum) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException
		{
			
			try 
			{
				//FileInputStream fis = new FileInputStream(InputExcelFilePath);
				FileInputStream fis = new FileInputStream("D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xlsx");
				//FileInputStream fis = new FileInputStream(JemeterExcelFilePath);
				Workbook wb = WorkbookFactory.create(fis);
				Sheet s = wb.getSheet(sheetName);
				Row r = s.createRow(rowNum);
				fis.close();
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
			return sheetName;
		}

		
		// find whether sheets exists	
		public boolean isSheetExist(String sheetName){
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1){
				index=workbook.getSheetIndex(sheetName.toUpperCase());
					if(index==-1)
						return false;
					else
						return true;
			}
			else
				return true;
		}
		
		
		public int getColumnCount(String sheetname)
		{
			// check if sheet exists
			if(!isSheetExist(sheetname))
			 return -1;
			
			sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(0);
			
			if(row==null)
				return -1;
			
			return row.getLastCellNum();
					
		}

		public String getCellData(String sheetName,int colNum,int rowNum) {
			try{
				if(rowNum <=0)
					return "";
			
			int index = workbook.getSheetIndex(sheetName);

			if(index==-1)
				return "";
			
		
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";
			cell = row.getCell(colNum);
			if(cell==null)
				return "";
			
		  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.MONTH)+1 + "/" +
		                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cellText;
		           
		         

		         }

			  
			  
			  return cellText;
		  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return "";
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
			}
		}
}
