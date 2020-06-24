package com.pack;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.dinnerLab.constants.TestConstants;

/**
 * @author admin
 *
 */
public class AutomateLoader {
	
	public static Properties config = new Properties();
	public static Properties settings = new Properties();
	public static String ConfigFile= TestConstants.CONFIG_FILE_PATH;
	public static String SettingFile= TestConstants.SETTINGS_FILE_PATH;

	//public static String TESTNG=config.getProperty("TESTNG_PATH");		
	public static String TESTNG=(String)config.get("TESTNG_PATH");
	static
	{
		try
		{
			System.out.println("SettingFile :"+SettingFile);
			System.out.println("TESTNG 1 :"+TESTNG);
			System.out.println("Loading the Config File 123");
			System.out.println("ConfigFile:"+ConfigFile);
			FileInputStream inputConfigStream = new FileInputStream(new File(ConfigFile));
			System.out.println("inputConfigStream:"+inputConfigStream);
			config.load(inputConfigStream);

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


}
