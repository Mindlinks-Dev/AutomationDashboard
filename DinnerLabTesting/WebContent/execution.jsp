<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.pack.*" %>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Execution</title> 
<!-- Datatable pagination CDN libs -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<!-- <script type="text/javascript" src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>-->
<link rel="stylesheet" href="http://cdn.datatables.net/1.10.7/css/jquery.dataTables.css"></link>


<!-- <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="css/jquery.dataTables.css"></link>-->



 
<script type="text/javascript">
$(document).ready(function() {
    $('#example').dataTable( {
    	
        "pagingType": "full_numbers",       
        aaSorting : [[0, 'desc']]
       
    } );
} );

function back()
{
	//alert('back');
	history.back();
	}
</script>

</head>
<body>
<jsp:include page="header.jsp" />


<div class="container">
<div id="clienthome_container">
 <div class="header" style="">
<br/>
<!-- <div style="float: right; margin-top:-40px;margin-left: 10px;margin-right:288px;font-size: 18px"><a href="/DinnerLabAutomationDashboard/Report/index.html" onclick=" show_report(this.href)">View Report</a></div> -->

<%

String proId = request.getParameter("id");
System.out.println("proId in clienthome="+proId);
Connection con=null;
PreparedStatement ps=null;
PreparedStatement ps1=null;
ResultSet rs=null;
ResultSet rs1=null;
String Output_Report_Location=null;


    try
{
 con=Database.getConnection();
	 ps=con.prepareStatement("select * from execution where Project_Id="+proId +" order By Id Desc");
	 ps1=con.prepareStatement("select * from executiondetails where Project_Id="+proId);
	int k=0;
	rs=ps.executeQuery();
	rs1=ps1.executeQuery();
	
	
	
	Output_Report_Location=AutomateLoader.config.getProperty("OUTPUT_REPORT_ARCHIVED_FILES_LOCATION");
	
	
	

%>


<table id="example"  class="custom-table slds-table slds-table_cell-buffer slds-table_bordered slds-table_col-bordered" style="border: 1px solid lightgray;"><!-- class="display" -->


<thead>
            <tr>
                <th id="upload">ID</th>
                <!--  <th id="upload"  style="border-radius:0;">Total Scenarios</th>-->
               <!--  <th id="upload" style="border-radius:0;">Script Name</th> -->
                <th id="upload">Script Name</th>
                <th id="upload">Executed By</th>
                <th id="upload">Start Time</th>
                <th id="upload" >End Time</th>
                <th id="upload">Status</th>
                <th id="upload">Time Taken</th>
                <th id="upload">Action</th>
                <!--<th id="upload"  style="border-radius:0;">Excel Report</th>  -->
            </tr>
        </thead>
        <tbody>
        
        		<% 
           
 			while(rs.next())
 			{
         %>
 
 <tr>
 <td><center><%= rs.getString(1) %></center></td>
 

 
 <%
 String Total_Scenarios = null;
 if(rs.getString(7) == null)
 {
	 Total_Scenarios = "0";
 }
 else
 {
	 Total_Scenarios = rs.getString(7);
	 System.out.println("inside executionjsp to print timetaken::"+rs.getString(7) );
 }
 
 String scriptname = null;
 if(rs1.next())
 {
	 scriptname=rs1.getString(4);
	 System.out.println("scriptname>>>"+scriptname);
	 
 }
 
 
 
 String Time_Taken=null;
 if(rs.getString(8)==null)
 {
	 Time_Taken= " ";
	 
 }
 else
 {
	 Time_Taken= rs.getString(8);
 }
 
 String End_Time=null;
 if(null==rs.getString(6))
 {
	 End_Time=" ";
 }
 else
 {
	 End_Time=rs.getString(6);
 }
 
 
 String Result_File=null;
 if(rs.getString(4)==null)
 {
	 Result_File=" ";
	 
 }
 else
 {
	 Result_File=rs.getString(4);
 }
 
 String OverAllResult=null;
 if(rs.getString(9)==null)
 {
	 OverAllResult= " ";
 }
 else
 {
	 OverAllResult=rs.getString(9); 
 }
 
 
 String Comments=null;
 if(rs.getString(10)==null)
 {
	 Comments= " ";
 }
 else
 {
	 Comments=rs.getString(10);
 }
 
 
 int Report_Link=0;
 /* System.out.println("The data is"+rs.getInt(10)); */
 
/* if(rs.getInt(10)==0)
 {
	 Report_Link=0;
 }
 else
 {
	 Report_Link=rs.getInt(10);
 } */
 Report_Link=rs.getInt(10);
 System.out.println("Inside Execution jsp Page:"+Report_Link);
 
 
 ScriptHandler viewReport = new ScriptHandler();
 

 //int reportNo=Integer.parseInt(Report_Link);
 
 String report = viewReport.viewReportPage(Report_Link);
 
 
 
 %>
 <!-- <td><center><a href="popup.jsp?id=<%=  rs.getInt(1)%>" target="_blank" onclick="return show_details(this.href);"></td>
 <%= Total_Scenarios %></a></center></td> -->
 <td><%=  scriptname %></td>
 <td><%=  rs.getString(3)%></td>
 <td><%=  rs.getString(2)%></td>
 <td><%=  End_Time%></td>
 <td><span class="slds-text-color_success"><%=  rs.getString(5)%></span></td>
 <td><%=  Time_Taken %></td>
 <td><a title="Download" href="${pageContext.request.contextPath}/DownLoadFileServlet?id=<%= rs.getString(4) %>"><i class="fa fa-download fa-green" aria-hidden="true"></i></a>
 <a title="View" href="/SigmaAutomation/Report/Results/Run_<%= Report_Link %>/CurrentRun.html" target="_blank"><i class="fa fa-eye fa-blue" aria-hidden="true"></i></a>
 <a title="Console Report" href="${pageContext.request.contextPath}/ConsoleReport/<%= rs.getString(12) %>"><i class="fa fa-tachometer fa-red" aria-hidden="true"></i></a>
 </td>

</tr>
              
    	 <% 
     		}
}
 
finally
{
	rs.close();
	rs1.close();
	ps.close();
	ps1.close();
	con.close();
}


     	%>
        
        
        </tbody>
        </table>
        




<br>
<br>
<script>
     
  function openNewWindow(link){
	 alert(link);
	 
	 // window.open("http://localhost:8080/DinnerLabAutomationDashboard/Report/Results/Run_31/CurrentRun.html");
	  alert("inside openNewWindow():");
	  window.open(link,"Scenarios", "width=auto,height=auto");
	  return true;
	  
  }
  
  
 	function show_details(link)
	{  
	console.log("Inside show_details");
	window.open(link, "Scenarios", "width=400,height=150,scrollbars=yes,status=no,menubar=no");
	//window.open(link,"Scenarios","width=320,height=210,scrollbars=yes,toolbar=no,screenx=0,screeny=0,location=no,titlebar=no,directories=no,status=no,menubar=no");
	return false;
	}
 	
 	function setSessionOff() 
	{
	alert("You will be Logged Out");
	request.getSession().setAttribute("FirstName",null);
	session.removeAttribute("FirstName");
	session.setAttribute("FirstName",null);
	session.invalidate();
	return false;
	}
 	
 	
 	function show_report(link)
 	{  
 		window.open(link, "Scenarios", "width=auto,height=auto,scrollbars=yes,status=no,menubar=no");
 	}
 	
 	/*
 	function View_Report_Details
 	{
 		window.open(link, "Scenarios", "width=auto,height=auto,scrollbars=yes,status=no,menubar=no");
 	}
 	
 	*/
 	
 	
</script>
</div>
</div>
<!--   <div class="footer" style=""><p style="">CRMIT Solution &copy 2018</p></div>   --> 
</body>
</html>