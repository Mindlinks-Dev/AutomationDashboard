package com.dinnerLab.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLToExcel 
{
	 static int execId;
	 public static int report_Link=1; 
	 public static String htmlPath = null; 

	public void copyHTMLToExcelReportActions() 
	 //public static void main(String[] args)
	  {

	     BufferedReader br = null;

	     try {
	    	 
		    String htmlPath ="D:\\AutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\Report\\Results\\Run_"+report_Link+"\\CurrentRun.html";
		    System.out.println("htmlPath:"+ htmlPath);
		    File file = new File(htmlPath);
		    //br = new BufferedReader(new FileReader(new File("D:/AlignWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/AlignToday/Report/Results/Run_9/CurrentRun.html")));
		    br = new BufferedReader(new FileReader((file)));
		    String htmldata = org.apache.commons.io.IOUtils.toString(br);
		    System.out.println("brPath:htmldata"+ htmldata);
		    Document doc = Jsoup.parse(htmldata);
		    System.out.println("doc"+ doc);

			
				// Create Work book
				XSSFWorkbook xwork = new XSSFWorkbook();

				// Create Spread Sheet
				XSSFSheet xsheet = xwork.createSheet("Output Report of Automation script");

				//Create Row (Row is inside spread sheet)
				XSSFRow xrow  = null;
				
				Cell cell;
				int rowCount = 0;
				for (Element table : doc.select("table[id=tableStyle]")) 
				{
					rowCount++;
					// loop through all tr of table
					for (Element row : table.select("tr")) 
					{
						// create row for each tag
						xrow = xsheet.createRow(rowCount);
						// loop through all
						Elements ths = row.select("th");
						int count = 0;
						for (Element element : ths) 
						{
							// set header style
							cell = xrow.createCell(count);
							System.out.println("element.text"+ element.text());
							cell.setCellValue(element.text());
							//cell.setCellStyle(headerStyle);
							count++;
						}
						// now loop through all td tag
						Elements tds = row.select("td:not([rowspan])");
						System.out.println("tdstds"+ tds);
						count = 0;
						for (Element element : tds) 
						{
							
							System.out.println("tdstdscount"+ count);
							// create cell for each
							cell = xrow.createCell(count);
							if(count == 7)
							{
								String imgurl = tds.select("img").first().attr("src");
								if(imgurl.contains("pass"))
								{
									cell.setCellValue("passed");
								}
								else if(imgurl.contains("fail"))
								{
									cell.setCellValue("failed");
								}else
								{
									cell.setCellValue("skipped");
								}
								System.out.println("tdstdscountimgurl"+ imgurl);
								
							}else
							{
								cell.setCellValue(element.text());
							}
							
							count++;
						}
						rowCount++;
						// set auto size column for excel sheet
						xsheet = xwork.getSheetAt(0);
						for (int j = 0; j < row.select("th").size(); j++) 
						{
							xsheet.autoSizeColumn(j);
						}
					}
				
				}
				
				Date d1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
				String todaysDate = sdf.format(d1);
				System.out.println(sdf.format(d1));
				//Create file system using specific name
				//FileOutputStream fout = new FileOutputStream(new File("D:\\AlignWorkspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\AlignToday\\OutputReportArchiveFiles\\Outputreport_"+report_Link+"_"+todaysDate+".xlsx"));
				FileOutputStream fout = new FileOutputStream(new File("D:\\AutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\OutputReportArchiveFiles\\Outputreport_"+report_Link+"_"+todaysDate+".xlsx"));	
				xwork.write(fout);
				fout.close();
				System.out.println("redaingfromHTMLFile_"+todaysDate+".xlsx written successfully" );
      
	       }
	       catch (Exception e) {
	          e.printStackTrace();
	      }
	    }

}
