package com.pack;


import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.pack.Database;
import com.pack.ScriptEntity;

public class LoadClass{
	  
	public static int executionId;
	public static int ScriptID;
	String startDateTime=null;
	String executedDateTime=null;
	String scriptstarttime=null;
	String archiveInputLocation=null;
	String excelReportArchiveLocation=null;
	int printscriptStartTime=0;
	public static int scriptIdFlag =0;
	String endTime=null;
    String startTime=null;
    public static String result;
    String Script_Name;
    String Script_Packagename;
     String packageclass;
     
     public LoadClass(){
    	 /*try {
			classLoading();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	 
     }
    
    public String getPackageclass() {
        return packageclass;
    }
 
    public void setPackageclass(String name) {
        this.packageclass = packageclass;
    }
	public void classLoading() throws SQLException{
		
		
		//connecting to database
		Connection con = null;
	    Statement stm = null;
	    ResultSet rs = null;
		con = Database.getConnection();
		stm = con.createStatement();
		//executionId=execId;
		ArrayList<String> temp = new ArrayList<String>();
		
		//Iterator<ScriptEntity> itr = scriptlist.iterator();
		/*for(int i=0;i<scriptlist.size();i++)
		{
			ScriptID = scriptlist.get(i).ScriptId;
			int ScriptID=scriptlist.get(i).ScriptId;
			System.out.println("after assigning to integer inside script handler ScriptID:"+ScriptID);
			scriptstarttime = "UPDATE executiondetails SET starttime=now()  WHERE scriptid="+ScriptID+" and execid="+execId;
			System.out.println("scriptstarttime:"+scriptstarttime);
			printscriptStartTime=stm.executeUpdate(scriptstarttime);
			System.out.println("Script start time :"+printscriptStartTime);
			
			//Retrieving the starttime value from executiondetails table
			rs=stm.executeQuery("SELECT starttime from executiondetails WHERE scriptid="+ScriptID+" and execid="+execId);
			while(rs.next())
			{			
			startTime=rs.getString(1);
            }
			
			result = null;
			
			
			scriptIdFlag = scriptlist.get(i).ScriptId;
			Script_Name = scriptlist.get(i).ScriptName;
			Script_Packagename = scriptlist.get(i).ScriptPackageName;
			
			
			System.out.println("scriptIdFlag:"+scriptIdFlag);
			System.out.println("Class Name inside ScrptHandler is:"+Script_Name);
			System.out.println("Package Name inside ScrptHandler is:"+Script_Packagename);
			
			
		}*/
		
		
		
		
		
		
		
		Class noparams[] = {};
	
	// Create a File object on the root of the directory containing the class file
	//File f = new File("C:/Users/Soundarya/Desktop/classes/");
	File f = new File("D:/AutomationDashboard/SigmaAutomation/WebContent/WEB-INF/classes/");
	

	try 
	{
		System.out.println("In Try");
	    // Convert File to a URL
	    URL url = f.toURL();      // file:/c:/myclasses/
	    URL[] urls = new URL[]{url};
	    System.out.println("In Try1>>"+urls);

	    // Create a new class loader with the directory
	    //ClassLoader cl = new URLClassLoader(urls);
	    //System.out.println("classloaderlist:"+cl);

	    // Load in the class; MyClass.class should be located in
	    // the directory file:/c:/myclasses/com/mycompany
	    
	    //packageclass = Script_Packagename+"."+Script_Name;
	   // System.out.println("packageclass>>"+packageclass);
	    //Class cls = cl.loadClass("com.dynamicload.sample");
	   //Class cls = Class.forName("com.dynamic.sample");
	    //System.out.println("cls>>"+cls);
	   // Object obj = cls.newInstance();
	   // System.out.println("obj:::"+obj);
	    
	    LoadClass o = new LoadClass();
        
	    
	    // Get package name and print it
	          Package pack = o.getClass().getPackage();
	           
	          String packageName = pack.getName();
	           
	          System.out.println("Package = " + packageName);
	    
	    Class c = Class.forName("com.pack.sample3");
	    System.out.println("c:::"+c);
		Object obj = c.newInstance();
		System.out.println("obj::"+obj);
		Method method = c.getDeclaredMethod("loginPageTest", noparams);
		method.invoke(obj, null);
		System.out.println("method:"+method);
		System.out.println("ALl Methods:::"+c.getDeclaredMethods().toString());
		c.getDeclaredMethods();
		System.out.println("obj::"+obj.toString());
		

	    
	} 
	
	catch (Exception e) {
		System.out.println("exc message12345>>"+e.getStackTrace());
		//e.printStackTrace();
	}
}
	

public static void main(String[] args) {
	
	LoadClass l = new LoadClass();
	try {
		l.classLoading();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		
	}



}
	
	

