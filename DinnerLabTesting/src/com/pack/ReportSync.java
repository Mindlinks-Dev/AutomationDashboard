/**
 * 
 */
package com.pack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * @author win
 *
 */
public class ReportSync extends Thread
{ 
	
	//static String InputFolderLocation="D:\\SOFTWARES\\softwares\\eclipse\\ATU Reports\\";
	//static String OutputFolderLocation="E:\\DinnerLab_Workspace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\Report\\";
	//String OutputFolderLocation="E:\\DinnerLab_WorkSpace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DinnerLabTesting\\Report\\";
	
	static String InputFolderLocation=AutomateLoader.config.getProperty("INPUT_FOLDER_LOCATION");
	static String OutputFolderLocation=AutomateLoader.config.getProperty("OUTPUT_FOLDER_LOCATION");
	
	String FullFolderName;
	File RenameToFolder;

	/*private static void loadProperties()
	{
		InputFolderLocation=AutomateLoader.config.getProperty("INPUT_FOLDER_LOCATION");
		OutputFolderLocation=AutomateLoader.config.getProperty("OUTPUT_FOLDER_LOCATION");
	}
	*/
	   
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
				InputFolderLocation=prop.getProperty("INPUT_FOLDER_LOCATION");
			       OutputFolderLocation=prop.getProperty("OUTPUT_FOLDER_LOCATION");
			       System.out.println("FIle path to Download Inside a servlet "+InputFolderLocation);
			       System.out.println("FIle path to Download Inside a servlet "+OutputFolderLocation);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	     }     */
	
	     
	//String InputFolderLocation=Property.InputFolderLocation;
	//String OutputFolderLocation=Property.OutputFolderLocation;
     
      
	public ReportSync()
	{
		//System.out.println("Inside constructor");
		
	}

	public void writeFolderFiles(File FolderObj)
	{
        //System.out.println(FolderObj);
		//System.out.println("Inside writeFolderFiles():File Object to load:"+ FolderObj.getName());
		HashMap<String,ArrayList<String>> HSubFolders = new HashMap<String,ArrayList<String>>();  
		
		int TotalFilesintheFolder =0;
		File[] FilesinFolder=null;
		//ArrayList<String> FolderNames = new ArrayList<String>();
		
		if(FolderObj.isDirectory())
		{
			FilesinFolder = FolderObj.listFiles();
		}

		//System.out.println("Inside writeFolderFiles():Total Files"+ FilesinFolder.length);
		
		if(FilesinFolder != null)
		{
			for(int count=0; count < FilesinFolder.length; count++)
			{
				//System.out.println("Inside writeFolderFiles():File to Load:"+FilesinFolder[count]);
				
				if(FilesinFolder[count].isDirectory())
				{
				//	String FolderName1 = FilesinFolder[count].getPath();
					//FolderNames.add(FolderName1);
					continue;
				}
				//System.out.println("Inside writeFolderFiles():Copying the file"+ FilesinFolder[count]);

				writeToFile(FilesinFolder[count]);
				TotalFilesintheFolder++;
			}
		}				
		
		//System.out.println("Total Files:"+TotalFilesintheFolder+" in the folder:"+FolderObj.getName());
		
		if(TotalFilesintheFolder == 0)
		{
			//System.out.println("FolderObj::"+FolderObj+"testtest:::"+FolderObj.getName());
			createFolder(FolderObj);
		}
		//String FolderName=FolderObj.getAbsolutePath();
		//HSubFolders.put(FolderName,FolderNames);
		return;
		
	}

	public ArrayList<String> loadSubFolders(File FolderObj, int Level)
	{
        //System.out.println(FolderObj);
		//System.out.println("Inside loadSubFolders():File Object to load:"+ FolderObj.getName());
		//HashMap<String,ArrayList<String>> HSubFolders = new HashMap<String,ArrayList<String>>();  
		//ArrayList<String> HSubFolders = ArrayList<String>();
		
		//int TotalFilesintheFolder =0;
		File[] FilesinFolder=null;
		ArrayList<String> FolderNames = new ArrayList<String>();
		
		if(FolderObj.isDirectory())
		{
			FilesinFolder = FolderObj.listFiles();
		}

		//System.out.println("Inside loadSubFolders():Total Files"+ FilesinFolder.length);
		
		if(FilesinFolder != null)
		{
			for(int count=0; count < FilesinFolder.length; count++)
			{
				//System.out.println("Inside loadSubFolders():File to Load:"+FilesinFolder[count]);
				
				if(FilesinFolder[count].isDirectory())
				{
					String FolderName1 = FilesinFolder[count].getPath();
					FolderNames.add(FolderName1);
					continue;
				}
				

				//writeToFile(FilesinFolder[count]);
				//TotalFilesintheFolder++;
			}
			//System.out.println("Inside writeFolderFiles():Copying the FolderNames file"+ FolderNames);
		}				
		
		//System.out.println("Inside writeFolderFiles():Total Folder:"+FolderNames.size()+": in the folder:"+FolderObj.getName());
		//String FolderName=FolderObj.getAbsolutePath();
		//HSubFolders.put(FolderName,FolderNames);
		//return HSubFolders;
		
		return FolderNames;
	}

	public void run()
	{
		//System.out.println("Inside run method");
		try{
				int Level=1;
				//System.out.println("after destination folder location InputFolderLocation:"+InputFolderLocation);
				File ReportFolder=new File(InputFolderLocation);
				
				//System.out.println("output folder location to copy ATU Reports :"+OutputFolderLocation);
				//Write All Files in that folder
				//Root Directory Folder
				if(ReportFolder.isDirectory())
				{
					//Writing all files in the root directory
					writeFolderFiles(ReportFolder);
					ArrayList<String> SubFolders = loadSubFolders(ReportFolder,Level);
					//System.out.println("Inside Run():Root folder total sub folders:"+SubFolders.size());
					while(null != SubFolders && SubFolders.size() > 0){
					ArrayList<String> TempFolders = new  ArrayList<String>();
					//TempFolders.addAll(SubFolders);
					//Load all files in the subfolders
					for(int count=0; count<SubFolders.size(); count++)
					{
						//System.out.println("==============================================================");
						//System.out.println("Inside Run():Loading files for folder:"+SubFolders.get(count));
						//System.out.println("Inside Run():Total folders in Temp before load:"+TempFolders.size());
						writeFolderFiles(new File(SubFolders.get(count)));
						TempFolders.addAll(loadSubFolders(new File(SubFolders.get(count)),Level));
						//System.out.println("Inside Run():Total folders in Temp after load:"+TempFolders.size());
						//System.out.println("==============================================================");
					}
					SubFolders.clear();
					SubFolders.addAll(TempFolders);
				}//
			//Load sub folders from ROOT Directory
			}
		}catch(Exception ex)
		{
			//System.out.println("Exception occured in run(): Message:"+ex.getMessage());
		}
			
		//Modifying the content
		//System.out.println("Before modifying the consolidated page:");
		modifyConsolidatePageContent();
		//System.out.println("After modifying the consolidated page:");
		try{
			//Calling rename last file name
			renameLastReportFileName();
		}
		catch(Exception ex){
			//System.out.println(":Exception during Report folder rename:"+ex.getMessage());
		}
	}

	public void createFolder(File Inputfileobj)
	{
		//System.out.println("inside createFolder() : Entering"+Inputfileobj.getPath());
		String OutputSubFolderName=getOutputFolder(Inputfileobj.getPath());
		String OutputFolder=OutputFolderLocation+OutputSubFolderName;//destination 
		
		//System.out.println("inside createFolder() : output folder to write:"+OutputFolder);
		File OutputFolderFile = new File(OutputFolder);
		//System.out.println("inside createFolder() : output folderfile is" +OutputFolderFile.getName());
		
		try{	
			if(!OutputFolderFile.exists())
			{
				//System.out.println("inside createFolder() : Creating output folder:"+OutputFolder);
				boolean iscreated = OutputFolderFile.mkdir();
				
				//System.out.println("inside createFolder() : Creating output folder:Folder Exists after creating:"+iscreated);
			}
		}
		catch(Exception ex)
		{
			//System.out.println("Exception:"+ex.getMessage());
		}
		
	}
	public void writeToFile(File Inputfileobj)
	{
		FileInputStream fileinputstreamobj = null;
		
		//System.out.println("inside writeToFile():"+Inputfileobj);
		FileOutputStream fileoutputstreamobj = null;

		
		//File writefile=new File("/Report/index.html");
		//String InputFile=OutputFolderLocation+fileobj.getName();
		String OutputSubFolderName=getOutputSubFolder(Inputfileobj.getPath());
		String OutputFolder=OutputFolderLocation+OutputSubFolderName;//destination 
		
		//System.out.println("output folder to write:"+OutputFolder);
		File OutputFolderFile = new File(OutputFolder);
		//System.out.println("output folderfile is" +OutputFolderFile);
		
		try{	
		if(!OutputFolderFile.exists())
		{
			//System.out.println("Creating output folder:"+OutputFolder);
			boolean iscreated = OutputFolderFile.mkdir();
			
			//System.out.println("Creating output folder:File Exists after creating:"+iscreated);
		}
		
		String OutputFile=OutputFolder+Inputfileobj.getName();
		File writefile=new File(OutputFile);
		//System.out.println("Output FileName to writeToFile():"+OutputFile);

	
		if(!writefile.exists())
		{
			//System.out.println("Creating new file:"+OutputFile);
			boolean iscreated = writefile.createNewFile();
			
			//System.out.println("File Exists after creating:"+iscreated);
			//writefile.
		}
		

		fileoutputstreamobj=new FileOutputStream(writefile);
		}
		catch(Exception ex){
				//System.out.println("Exception when getting the fileoutputstream file:"+ex.getMessage());
		}
		
		//File writefile=new File("C:\Users\win\Desktop\RealProject\pro\WebContent\Report");
	
		 try {
				fileinputstreamobj = new FileInputStream(Inputfileobj);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
		/*	
		 try {
				System.out.println("Total file size to read (in bytes) : "
						+ fileinputstreamobj.available());
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			*/
			int TotalSize=0;
			
		
			try {
					if(null !=fileinputstreamobj)
					{
						TotalSize=fileinputstreamobj.available();
					}
					
					byte[] out = new byte[TotalSize];
					
					fileinputstreamobj.read(out,0, TotalSize);
					fileoutputstreamobj.write(out);
                    //System.out.println("printing out objrct "+out);
					/*
				while ((content = fileinputstreamobj.read()) != -1) 
				{
					// convert to char and display it
					//System.out.print((char) content);
					//records []= (Bytes)fileinputstreamobj.read();
					//records []=(Byte)contents;
					fileoutputstreamobj.write((byte)content);
					//FileOutputStream fileoutputstreamobj1 = new FileOutputStream(writefile,true);
					
                    //String a=fileinputstreamobj.getBytes();
					
				}
				*/
				fileoutputstreamobj.flush();
				fileinputstreamobj.close();
				fileoutputstreamobj.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public String getOutputSubFolder(String FolderName)
	{
		//System.out.println("Inside getOutputSubFolder(): Incoming Folder Name:"+FolderName);
		String SubFolder= "";
		int startindex = InputFolderLocation.length();
		int endIndex=FolderName.lastIndexOf("\\")+1;
		//System.out.println("Inside getOutputSubFolder(): Start Index:"+startindex+":End Index:"+endIndex);		
		//int endIndex = FolderName.in;
		if(startindex>0 && endIndex > 0 && startindex<endIndex)
		{
			SubFolder=FolderName.substring(startindex, endIndex);
			//System.out.println("Inside getOutputSubFolder(): SubFolder:"+SubFolder);
		}
		return SubFolder;
		
	}
	public String getOutputFolder(String FolderName)
	{
		//System.out.println("Inside getOutputFolder(): Incoming Folder Name:"+FolderName);
		String SubFolder= "";
		int startindex = InputFolderLocation.length();
		int endIndex=FolderName.length();
		//System.out.println("Inside getOutputFolder(): Start Index:"+startindex+":End Index:"+endIndex);		
		//int endIndex = FolderName.in;
		if(startindex>0 && endIndex > 0 && startindex<endIndex)
		{
			SubFolder=FolderName.substring(startindex, endIndex);
			//System.out.println("Inside getOutputFolder(): fOLDER:"+SubFolder);
		}
		return SubFolder;
		
	}

	public void modifyConsolidatePageContent()
	{
		 try
         {
			 String FileName=OutputFolderLocation+"Results\\ConsolidatedPage.html";
			 //System.out.println("file name is "+FileName);
			// System.out.println("Consildated page location:"+FileName);
			 File file = new File(FileName);
			 BufferedReader reader = new BufferedReader(new FileReader(file));
			 String line = "", oldtext = "";
			 while((line = reader.readLine()) != null)
             {
				 oldtext += line + "\r\n";
             }
			 reader.close();
			 // replace a word in a file
			 //String newtext = oldtext.replaceAll("drink", "Love");
        
			 //To replace a line in a file
			 String newtext = oldtext.replaceAll("\\\\CurrentRun.html", "/CurrentRun.html");
        
			 FileWriter writer = new FileWriter(FileName);
			 writer.write(newtext);writer.close();
         }catch (IOException ioe)
         {
        	 ioe.printStackTrace();
         }
	}
	
	//Rename the last report folder name-- this is due to ATU reports
	public void renameLastReportFileName()
	{
		try{
        
			String tempRun;
        
	        //To fetch a Run Number from settings.properties file(ATU) 22-07-2015
	        Properties setting = new Properties();
			FileInputStream inputSettingsgStream = new FileInputStream(new File(AutomateLoader.SettingFile));
			setting.load(inputSettingsgStream);
			
	        tempRun = setting.getProperty("run");
	        //int runIncrement=(Integer.parseInt(tempRun)+1);
	        int runIncrement=(Integer.parseInt(tempRun));
	       // System.out.println("Inside renameLastReportFileName():runIncrement125:"+runIncrement);
	        
	        String RenameFolderName = getRenameReportFolder(runIncrement);
	        
	        
	        //System.out.println(":Folder to rename:"+RenameFolderName);

			String ReportFolder=AutomateLoader.config.getProperty("OUTPUT_FOLDER_LOCATION")+"Results\\";
	        
			//String FullFolderName=ReportFolder+RenameFolderName;
	        FullFolderName=RenameFolderName;

			String RenameTo=ReportFolder+"Run_"+runIncrement;
			
	       // System.out.println("Rename To:"+RenameTo+":Full folder name:"+FullFolderName);
			
			File RenameFolder = new File(FullFolderName);
			RenameToFolder = new File(RenameTo);
			
			//System.out.println("RenameToFolder:"+RenameToFolder);

			//Check file exists
			
			if(RenameFolder.exists() && RenameFolder.isDirectory())
			{
				//System.out.println("Folder exists and renaming it");
				RenameFolder.renameTo(RenameToFolder);
				
			}
		}
		catch(Exception ex){
			
		}
	}
	
	private String getRenameReportFolder(int RunNo)
	{
		String ReportFolder=AutomateLoader.config.getProperty("OUTPUT_FOLDER_LOCATION")+"Results\\";
		int Level=1;
		//String RunSeq = RunNo+"";
		String RunSeq = RunNo+"";
		File reportfolder=new File(ReportFolder);
		
		//System.out.println("Inside getRenameReportFolder():Report Folder:"+ReportFolder+":Run Number:"+RunSeq);
		
		//Load the subfolders
		ArrayList<String> foldernames = loadSubFolders(reportfolder,Level);
		
		String RenameFolderName = "";
		
		//Iterate the list
		for(int count=0; count < foldernames.size(); count++)
		{
			
			String ReferenceFolder = foldernames.get(count);
			
			//System.out.println("Inside getRenameReportFolder():Reference Folder Name:"+ReferenceFolder);

			
			if(ReferenceFolder.endsWith(RunSeq))
			{
				//System.out.println("Inside getRenameReportFolder():Find the Match and folder name is:"+ReferenceFolder);
			
				RenameFolderName=ReferenceFolder;
				break;
			}
		}
		
		return RenameFolderName;
	}
	
	/*public static void main(String[] args) 
	{
		ReportSync reportobj = new ReportSync();
		reportobj.start();
	}*/
	
	
}
	