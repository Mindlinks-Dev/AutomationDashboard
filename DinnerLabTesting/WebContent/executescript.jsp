<%@page import="com.sun.jna.platform.win32.Sspi.PSecHandle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="com.pack.*" %>
<%@page import="com.pack.*" %>
<%@page import="com.QuartzSchedulerClasses.*" %>
<%@page import="java.lang.*" %>
<%@page import="com.dinnerLab.util.*" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="java.sql.Statement"%>

<!-- 
		<%@ page import="java.util.Properties" %>               
     	<%@ page import="javax.mail.Message" %>
     	<%@ page import="javax.mail.MessagingException" %>
      	<%@ page import="javax.mail.PasswordAuthentication" %>
      	<%@ page import="javax.mail.Session" %>
       	<%@ page import="javax.mail.Transport" %>
       	<%@ page import="javax.mail.internet.InternetAddress" %>
       	<%@ page import="javax.mail.internet.MimeMessage" %> -->

<html>
<body>

<jsp:include page="header.jsp" />


<div class="nav-margin">
<div class="panel-success">
<div class="panel-head">
<%	
/*
Written by = shridhar 
code = order of execution code
*/
	//String testOrderOfExecution = request.getParameter("OrderList");
	//System.out.println("Inside Executescript JSP testOrderOfExecution:"+testOrderOfExecution);
/* 	System.out.println("Inside Executescript JSP testOrderOfExecution:"+testOrderOfExecution.toString());
	String [] orderExecArray = testOrderOfExecution.split(",");
	System.out.println("orderExecArray is :"+orderExecArray);
	//System.out.println("orderExecArray[0] is :"+orderExecArray[0]);
	//System.out.println("orderExecArray[1] is :"+orderExecArray[1]);
	System.out.println("orderExecArray length is :"+orderExecArray.length);
	OrderEntity orderExecObj = new OrderEntity();
	//OrderEntity.ScriptOrderExecutionMap.put(Integer.parseInt(SciptID), ScriptName);
	for(int k = 0 ; k < orderExecArray.length ; k++ )
	{
		//System.out.println("k :"+k);
		//System.out.println(k+" th array element is :"+orderExecArray[k]);
		String temp = orderExecArray[k];
		System.out.println("temp :"+temp);
		if(temp.contains("="))
		{
			System.out.println("Inside for loop iteration true:");
			String [] tempInnerArray = temp.split("=");
			System.out.println("tempInnerArray[0] :"+tempInnerArray[0]);
			System.out.println("tempInnerArray[1]:"+tempInnerArray[1]);
			OrderEntity.ScriptOrderExecutionMap.put(tempInnerArray[0],tempInnerArray[1]);
		}
		
		//orderExecObj.ScriptId = ; 
	}  code ends here*/
	
	System.out.println("After Final Map Construction:"+OrderEntity.ScriptOrderExecutionMap);
	System.out.println("After Final Map Construction size() is :"+OrderEntity.ScriptOrderExecutionMap.size());
	
	String [] SelectedIds=request.getParameterValues("scriptlist");
	Connection con=null;
	ResultSet rSelectedIds = null;
    int selectedScriptNames=0;
	request.getAttribute("HScenarios");
	ArrayList<ScriptEntity> scriptobjlist = new ArrayList<ScriptEntity>(); 
	ScriptEntity scriptobj =  null;
	  int execid=0;
	  String getSlectedScriptNames=null;
	  
		String scheduleStatus = request.getParameter("rundrop").toString();
		System.out.println("scheduleStatus:::"+scheduleStatus);
	  
	String checkedval=request.getParameter("checkedvalues").toString();
	System.out.println("checkedval:::"+checkedval);
	String ischeckedScheudle = request.getParameter("isSchedule").toString();
    System.out.println("ischeckedScheudle:::"+ischeckedScheudle);
	
	String scheudleDateTime = request.getParameter("scheduledate").toString();
	System.out.println("scheudleDateTime:::"+scheudleDateTime);
	
	String scheudleEndDate = request.getParameter("scheduleenddate").toString();
	System.out.println("scheudleEndDate:::"+scheudleEndDate);
	
	String UserName=null;//defining  UserName globally to enable it to be available throught this jsp page
   
	
	if(ischeckedScheudle.equalsIgnoreCase("scheduled"))
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try{
		connection = Database.getConnection();
		statement=connection.createStatement();

		//String sql  ="insert into schedule(Schedule_id,User_Name,Status,Start_date)values('"+Schedule_id+"','"+User_Name+"','"+Status+"','"+Start_date+"')" ;
		String User_Name = session.getAttribute("name").toString();
		System.out.println("User_Name:"+User_Name);
		System.out.println("LoginAuthenticate.userSignupId:"+LoginAuthenticate.userSignupId);
		String Schedule_Type = scheduleStatus;
		String Status = "Active";
		String sql = null;
		if(scheduleStatus.equalsIgnoreCase("everyday"))
		{
			
			sql  ="INSERT INTO SCHEDULE(User_Name,ScriptStartDateTime,SchedulerEnddate,Status) VALUES('"+User_Name+"','"+scheudleDateTime+"','"+scheudleEndDate+"','"+Status+"')";
		}
		else
		{
			sql  ="INSERT INTO SCHEDULE(User_Name,ScriptStartDateTime,Status) VALUES('"+User_Name+"','"+scheudleDateTime+"','"+Status+"')";
			
		}
		
  
	    
		
			int result  = statement.executeUpdate(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Before starting the execution :"+e.getMessage());
		}
	}
	
	
	//List<String> fetchFlowNames = new ArrayList<String>();//Array list to fetch ScriptNames:for Hybrid flows
    //List<String> ExecutedScripts = new ArrayList<String>();//Array list to fetch ScriptNames: For Normal Scripts
			//System.out.println("logically printing values:inside execute script 1 : "+checkedval);
			//int id=checkedval.split(:);
			//System.out.println("Script Id inside executescript is :"+id);
			ArrayList<String> fetchFlowNames = new ArrayList<String>();
			ArrayList<String> ExecutedScripts = new ArrayList<String>();
			String [] parts =checkedval .split(":");
			String FlowId=null;
			int ScriptId=0;
			try
			{
			ScriptId = Integer.parseInt(parts[0]);
			//String ScriptId = (parts[0]);
			
			FlowId = parts[1]; 
		
			//System.out.println("inside executeScript page ScriptId is 2  :" +ScriptId+ " Flow Id are :"+FlowId);
			//System.out.println("FlowId Length inside executescript is  :" +FlowId.length());
			}
			catch(Exception ex)
			{
				//System.out.println("Exception Occured at executescript.jsp page :"+ex.getMessage());
			}
			
			
			
			
			/*String FlowId[]=checkedval.split(",");
			String temp[]=null;
			
			for(int i = 0 ; i <= FlowId.length ; i++ )
			{
				temp[i]=(FlowId[0]);
			}
			
			
			System.out.println("Flow Id's are :"+temp);
			*/
	//String hidden = request.getParameter("hid"); 
	//System.out.println("SelectedIds::"+SelectedIds);
	if(SelectedIds != null && SelectedIds.length>0)
	{
			%>
			<center>
			<div style="margin-top: 60px;">
			<%

		  	System.out.println("<h1>You have Executed following Scripts </h1>");
	 	String QueryIds =	"(";
	 	for(int count=0; count<SelectedIds.length; count++)
	 			{
	 			
				QueryIds=QueryIds+SelectedIds[count];
				selectedScriptNames=Integer.parseInt(SelectedIds[count]);
				System.out.println("Script ID inside executescript jsp is :"+selectedScriptNames);
				
				//Check for last record
		 			if(count+1 == SelectedIds.length)
		 			{
			 		QueryIds=QueryIds+")";
		 			}
		 			else
		 			{
			 		QueryIds=QueryIds+",";
			 		}
				%>
				<div align="left" style="text-align:center;list-style-type:none;font-size:20px;">
				<%
		 			out.println("<li>"+ScriptEntity.HScenarios.get(selectedScriptNames)); 
		 		%>
		 		</div>
		 		<%	
				getSlectedScriptNames=ScriptEntity.HScenarios.get(selectedScriptNames); 
		 		}
	 			//emailString=getSlectedScriptNames+",";
				%>
				</div>
				</center>
				<% 
	 				
		 		System.out.println("Inside executescript jsp QueryIds"+QueryIds);
	 
	  			for(int i=0;i<SelectedIds.length;i++)
	  			{
	  			//System.out.println("Selected Ids" +SelectedIds[i]);
	  			//out.println("selceted script names inside executescript are :" +ScriptEntity.HScenarios.get(SelectedIds[i]));
	  		
	  			}
	  			try
	  			{

			 	//System.out.println("before calling con in execute script");
		 	 	con= Database.getConnection();
		 	 	//System.out.println("value of con after get connection in execute script::"+con);
	  			String Query= "select * from Script where script_id in "+QueryIds;
		 	 	//System.out.println("after query in execute script ::"+Qyery);
	  		//==================== code to Fetch Hybrid FlowNames from Database based on Flow Id Starts here ==================================		

				//System.out.println("FlowId s are :"+FlowId);
				String FlowId2=null;
				FlowId2=FlowId;
				if(FlowId2.equals("-1"))
				{
					FlowId2=1+","+2+","+3;
				}
				FlowId2 = FlowId2.replace(",", "','");
				//System.out.println("flow id 2 ::"+FlowId2);
				
				//String temp=FlowId2.split("''");
				//System.out.println("After Putting Flow Id Into Different String variable :"+FlowId2);
			 	//System.out.println("before calling resultset in execute script");
				
				
				/* // Commented on October-31
				ResultSet rs2=con.createStatement().executeQuery("Select FlowName from script_flows where FlowId in('"+FlowId2+"')");
			 
				String test="Ticket Purchase(Complete)-->";
				try
				{
			    	while (rs2.next()) 
			    	{
			    		
				fetchFlowNames.add(rs2.getString(1));
				test+=rs2.getString(1)+",";
			  		}//end of while loop
				}
				catch(Exception ex)
				{
					//System.out.println("Exception Occured in Executescript page while Fetching FlowNames From DataBase :"+ex.getMessage());
				}
				finally
				{
					rs2.close();
				}
				*/ //--Commented on October-31
		
			    //System.out.println("After Fetching FlowNames From Database base on FlowId :"+fetchFlowNames);
			    //System.out.println("After Fetching FlowNames From Database test String :"+test);
			   // System.out.println("After removing comma(,) test String :"+test.substring(0,test.length()-1));
			  
//==================== code to Fetch Hybrid FlowNames from Database based on Flow Id Ends here ==================================	
			
				
				
				
				
				rSelectedIds = con.createStatement().executeQuery(Query);
					while(rSelectedIds.next())
				  {
					
					scriptobj =  new ScriptEntity();
					scriptobj.ScriptId = rSelectedIds.getInt("script_id");
					scriptobj.ScriptName= rSelectedIds.getString("script_name");
					scriptobj.ScriptInputFile= rSelectedIds.getString("script_inputfile");
				   	scriptobj.ScriptScenario= rSelectedIds.getString("script_scenario");
				   	scriptobj.ScriptSequenceNo= rSelectedIds.getString("script_sequenceno");
				   	scriptobj.ScriptStatus= rSelectedIds.getString("script_status");
					scriptobj.ScriptCategoryId= rSelectedIds.getInt("script_category_id");
					scriptobj.ScriptCategoryName= rSelectedIds.getString("script_category_Name");
					scriptobj.ScriptPackageName = rSelectedIds.getString("script_package_Name");
					ExecutedScripts.add(rSelectedIds.getString("script_name"));
					//System.out.println("scriptobj.ScriptId:"+scriptobj.ScriptId+"ScriptId:"+ScriptId);  
							
				
					if(scriptobj.ScriptId == ScriptId)
					{
						scriptobj.containsFlow=true;
						scriptobj.FlowID=FlowId;
						
					}
					scriptobjlist.add(scriptobj);
					
					//printing varaibles
				/*System.out.println("Selected  Script Id:"+scriptobj.ScriptId);
				System.out.println("Selected Script Name:"+scriptobj.ScriptName);
			    System.out.println("Selected Script input file:"+scriptobj.ScriptInputFile);
				System.out.println("Selected Script scenario:"+scriptobj.ScriptScenario);
				System.out.println("Selected Script sequence no:"+scriptobj.ScriptSequenceNo);
				System.out.println("Selected Script status:"+scriptobj.ScriptStatus);
				System.out.println("Selected Script categoryId:"+scriptobj.ScriptCategoryId);
				System.out.println("Selected Script categoryName:"+scriptobj.ScriptCategoryName);
				*/
				}//end of while loop 
			
	  	   //2.Write to the execution table
	  	
	  	  	try{
				
	  	 		ScriptResult result=new ScriptResult();
	  		   	 UserName=session.getAttribute("FirstName")+" "+session.getAttribute("LastName") ;
	  		  	execid= result.insertExecutionRequest(scriptobjlist,UserName);
	  		 	 
	  	 		}
	  	  	  catch(Exception ex)
	  	  	  {
	  	  		  
	  	  		 // System.out.println("Exeption occured in executescipt.jsp :"+ex);
	  	  	  }
	  	  	  
	  		  
	  			//writing FlowId's into executiondetails table
	  		/*	if(ScriptId!=0)
	  			{	
	  			String updateQuery="update executiondetails set ContainsFlows='"+FlowId+"' where scriptid='"+ScriptId+"' and execid='"+execid+"' ";
	  			int test= con.createStatement().executeUpdate(updateQuery);
	  	  		}
	  			*/
	  		  //	System.out.println("inside executescript :"+execid);
	  		  	
	  			String emailId="";
	  			
	  			if(null !=session.getAttribute("EmailAddress"))
	  			{
	  				emailId=session.getAttribute("EmailAddress").toString();
	  			}
  
	  	  	//3. Call the automation engine
	  	  	//System.out.println("execid::::"+execid);
	  	 // System.out.println("scriptobjlist::::"+scriptobjlist);
	  	  	ScriptHandler handler = new ScriptHandler();
	  	  	try
	  	  	{
	  	  		System.out.println("execidscheduled::::"+ischeckedScheudle);
	  	  		if(new String(ischeckedScheudle).equals("scheduled"))
	  	  		{
	  	  			System.out.println("inside if::::");
	  	  			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  		    	Date startdateandtime = dateFormatter.parse(scheudleDateTime);
	  		    	Date enddate = dateFormatter.parse(scheudleEndDate);
	  	  			schedule sch = new schedule();
	  	  			
  	  				sch.scheduleThis(scriptobjlist,execid,startdateandtime,enddate,scheduleStatus,scheudleDateTime,scheudleEndDate);
	  	  			
	  	  		}else
	  	  		{
	  	  			System.out.println("inside else::::");
	  	  			handler.execute(scriptobjlist,execid);//calling ScriptHandler's Execute()================	
	  	  		
	  	 	
	  	  /*	try
	  	  	{
	  	  		EditPieChartContents edit = new EditPieChartContents();
				edit.editPieChartContentsActions();
	  	  	}
	  	  	catch(Exception ex)
	  	  	{
	  	  		ex.printStackTrace();
	  	  	}*/
	  	 	
			
	  	//===================calling ScriptHandler's sendEmail(ArrayList<String> ExecutedScriptNames) method====================
	  		 
	  		 
            System.out.println("Before calling syncobject.start:");
 			ReportSync syncobject=new ReportSync();
 			syncobject.start();
 			System.out.println("After calling syncobject.start:");
 			//step5 to call  HTMLToExcel class's copyHTMLToExcelReportActions
 			
 			
 			Thread.sleep(10000);
 			System.out.println("Before calling copyHTMLToExcelReportActions:");
 			HTMLToExcel report = new HTMLToExcel();
 			report.copyHTMLToExcelReportActions();
 			System.out.println("After calling copyHTMLToExcelReportActions:");
 			Thread.sleep(5000);
		 	
 			System.out.println("inside execute script(LoginAuthenticate) before calling send email::"+LoginAuthenticate.email);
 			//System.out.println("inside execute script(UserLoginPage) before calling send email::"+UserLoginPage.email);
				try
				{
	 			handler.sendEmail(ExecutedScripts,emailId,UserName);//calling ScriptHandler's sendEmail(ArrayList<String> ExecutedScriptNames) method=
				}
				catch(Exception ex)
				{
					//System.out.println("Exception occured in executescript jsp while calling ScriptHandler's sendEmail()"+ex.getMessage());
				} 
					  		 		  	 
	//end of try block

	
	  	  		}
  	  		
	  			}	
		  	  	catch(NullPointerException npe)
		  	  	{
		  	  		npe.printStackTrace();
		  	  	}
	  			}
	  			finally
	  			{
	  				//rSelectedIds.close();
	  				//con.close();
	  			}
				//code to send Email
				//String ScriptNames=request.getParameter("mailId").toString();
 				//System.out.println("ExecutedScriptNames inside SendEmail.jsp page:"+ScriptNames);
 	//end of top if condition where we are checking for Selected ID's are not null and its length is  > 0
	}
	else
	{
		out.println("please Select atleast one Script!");
	}
	  

	
	%>
	</div>
	  	 <p >Executed script result will be sent to your registered e-mail.</p>
	  	</div>
</div>	
<!--  <div class="footer" style=""><p style="">Mindlinks Solution &copy 2019</p></div> -->
</body>
</html>
	  	 