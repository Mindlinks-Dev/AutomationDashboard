package com.dinnerLab.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.dinnerLab.constants.TestConstants;
import com.pack.AutomateLoader;
import com.pack.ScriptHandler;

/**This class is used to copy the console output from the eclipse (testoutput-suite-abc.html) 
 * and will create new  file to the mentioned locationin config.property.
 */
public class Console 

{

	public static Properties config = new Properties();
	public static Properties settings = new Properties();
	public static String ConfigFile = TestConstants.CONFIG_FILE_PATH;
	public static String SettingFile = TestConstants.SETTINGS_FILE_PATH;
	public static FileInputStream fis;

	// @Test

	public void main1(int excelid) throws IOException {

		/**
		 * exceid is a parameter fetching from the reportvalidate() method within the script handler class.
		 */
		
		String excelReportPath = "ConsoleReport_" + excelid + new SimpleDateFormat("'.html'").format(new Date()) + "";
		System.out.println(excelReportPath);
		
		 /**excelReportPath is a variable which create new file with the mention
		 name above.*/
		
		
		/**Create new file*/
		//File file= new File(excelReportPath);
		//System.out.println("console file name:: "+ file);
		
		
		 /**Below line of code will copy the .html file from eclipse and copy to
		 the console report folder.*/
		
		copyFileUsingApacheCommonsIO(new File(AutomateLoader.config.getProperty("CONSOLE_FILE_LOCATION")),
				new File(AutomateLoader.config.getProperty("CONSOLE_OUTPUTFILE_LOCATION")

						+ excelReportPath));

	}

	public void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
		FileUtils.copyFile(source, dest);
	}

}
