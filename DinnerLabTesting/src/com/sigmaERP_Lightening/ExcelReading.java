
package com.sigmaERP_Lightening;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelReading 
{
    
	
	
	
    static String InputExcelFilePath = null;
    static String InputExcelFile = null;
   
            static{
            	//ExcelReading.InputExcelFile =AutomateLoader.config.getProperty("INPUT_EXCEl_FILE_PATH");            	
            	//ExcelReading.InputExcelFilePath = InputExcelFile+" Data.xls";
            	//ExcelReading.InputExcelFilePath = TestConstants.InputExcelFilePath;
            	//ExcelReading.InputExcelFilePath = ExcelReading.InputExcelFile;               
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
                FileInputStream fis = new FileInputStream("D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\sigmaERP_Lightening\\loginTestData.xls");
                		//System.getProperty("user.dir")+"\\src\\com\\sigmaERP_Lightening\\loginTestData.xls");
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
                FileInputStream fis = new FileInputStream("D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\com\\sigmaERP_Lightening\\loginTestData.xls");
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
                FileInputStream fis = new FileInputStream("C:\\Users\\admin\\workspace\\salenium\\Excelfiles\\loginTestData.xls");
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
                FileOutputStream fos = new FileOutputStream("C:\\Users\\admin\\workspace\\salenium\\Excelfiles\\loginTestData.xls");
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
                FileInputStream fis = new FileInputStream("C:\\Users\\admin\\workspace\\salenium\\Excelfiles\\loginTestData.xls");
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

        
}

