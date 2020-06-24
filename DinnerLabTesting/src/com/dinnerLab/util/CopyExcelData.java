package com.dinnerLab.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.pack.AutomateLoader;

public class CopyExcelData {
	
	
	//Copying input Excel data to Archive File.
	public static void copyWorkBookActions(int execId) throws Exception{
		
	    
	    //String InputExcelFilePath=AutomateLoader.config.getProperty("INPUT_EXCEl_FILE_PATH");
		String InputExcelFilePath=AutomateLoader.config.getProperty("OUTPUT_REPORT_ARCHIVE_LOCATION");
		File file = new File(InputExcelFilePath);


		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		//XSSFSheet sheet = workbook.getSheet("PaidMemberSignUp");
		
        String InputArchivedURL=AutomateLoader.config.getProperty("INPUT_ARCHIVED_FILES_PATH"); 
	    FileOutputStream out = new FileOutputStream(InputArchivedURL+"/QAInputOutput_"+execId+"_"+new SimpleDateFormat("yyyyMMdd'.xls'").format(new Date())+"");
         workbook.write(out);
         out.close();
         workbook.close();
 
		}
	
	private static void copyRow(XSSFWorkbook workbook, XSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
	    // Get the source / new row
	    XSSFRow newRow = worksheet.getRow(destinationRowNum);
	    XSSFRow sourceRow = worksheet.getRow(sourceRowNum);

	    // If the row exist in destination, push down all rows by 1 else create a new row
	    if (newRow != null) {
	        worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
	    } else {
	        newRow = worksheet.createRow(destinationRowNum);
	    }
	   
	    // Loop through source columns to add to new row
	    for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
	        // Grab a copy of the old/new cell
	        XSSFCell oldCell = sourceRow.getCell(i);
	        XSSFCell newCell = newRow.createCell(i);

	        // If the old cell is null jump to next cell
	        if (oldCell == null) {
	            newCell = null;
	            continue;
	        }

	        // Copy style from old cell and apply to new cell
	        XSSFCellStyle newCellStyle = workbook.createCellStyle();
	        newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
	        ;
	        newCell.setCellStyle(newCellStyle);

	        // If there is a cell comment, copy
	        if (oldCell.getCellComment() != null) {
	            newCell.setCellComment(oldCell.getCellComment());
	        }

	        // If there is a cell hyperlink, copy
	        if (oldCell.getHyperlink() != null) {
	            newCell.setHyperlink(oldCell.getHyperlink());
	        }

	        // Set the cell data type
	        newCell.setCellType(oldCell.getCellType());
	   
	        // Set the cell data value
	        switch (oldCell.getCellType()) {
	            case Cell.CELL_TYPE_BLANK:
	                newCell.setCellValue(oldCell.getStringCellValue());
	                break;
	            case Cell.CELL_TYPE_BOOLEAN:
	                newCell.setCellValue(oldCell.getBooleanCellValue());
	                break;
	            case Cell.CELL_TYPE_ERROR:
	                newCell.setCellErrorValue(oldCell.getErrorCellValue());
	                break;
	            case Cell.CELL_TYPE_FORMULA:
	                newCell.setCellFormula(oldCell.getCellFormula());
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	                newCell.setCellValue(oldCell.getNumericCellValue());
	                break;
	            case Cell.CELL_TYPE_STRING:
	                newCell.setCellValue(oldCell.getRichStringCellValue());
	                break;
	        }
	     }
	}
}
