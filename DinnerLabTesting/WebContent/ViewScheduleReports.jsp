
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.pack.*" %>
<%@page import="java.sql.Statement"%>

<%@page import="com.pack.Database"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MLS_Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
<script src="js/jquery.min.js"></script>
<script>
function back()
{
	//alert('back');
	history.back();
	}
	function scriptStatus()
	{
		var x = document.getElementById("scriptStatus").value;
		if(x=='active')
		{
			alert('active');
		}	
		else if(x=='inactive')
		{
			alert('inactive');
			
		}			
		
	}
	
	function onload()
	{
		var x = document.getElementById("scriptStatus").value;
		if(x=='active')
		{
			alert('active1');
		}	
		else if(x=='inactive')
		{
			alert('inactive1');
			
		}			
	}
</script>
             
</head>
<jsp:include page="header.jsp" />

<body onLoad="onload()">

<div class="container">
<div class="slds-card1">
  <div class="slds-card__header slds-grid">
    <header class="slds-media slds-media_center slds-has-flexi-truncate">
      <div class="slds-media__figure">
        <span class="slds-icon_container slds-icon-standard-account" title="Project Names">
          
          <span class="slds-assistive-text">Schedule Details Page</span>
        </span>
      </div>
      <div class="slds-media__body">
       <h2 class="slds-card__header-title">
         <a href="javascript:void(0);" class="slds-card__header-link slds-truncate" title="Accounts">
           <span><i class="fa fa-bar-chart" aria-hidden="true"></i> VIEW SCHEDULED REPORTS</span>
         </a>
       </h2>
      </div>
      
	<input type="hidden" id="field_results"/><br>	
	<input type="hidden" id="OrderList" name="OrderList" value="">	  
     
    </header>
  </div>
  <div class="slds-card__body slds-card__body_inner1">
  
  <table class="custom-table slds-table slds-table_cell-buffer slds-table_bordered slds-table_fixed-layout">
  <thead>
    <tr class="slds-line-height_reset">
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="ScheduleID">Schedule ID</div>
      </th>
	  <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="UserName">User Name</div>
      </th>
	  <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="ScriptStartDateTime">Script Start Date</div>
      </th>
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="SchedulerEnddate">Script End Date</div>
      </th>
       <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Status">Script Status</div>
       </th>
    </tr>
  </thead>
  <tbody>      
	<%

	String proId = request.getParameter("id");
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
try{
connection = Database.getConnection();
statement=connection.createStatement();
String sql ="select * from schedule where Schedule_id="+proId;
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr >
<td><%=resultSet.getString("Schedule_id") %></td>  
<td><%=resultSet.getString("Requested_by") %></td>  
<td><%=resultSet.getString("ScriptStartDateTime") %></td>  
<td><%if(resultSet.getString("SchedulerEnddate")!=null)
	

{
	
	
	out.println(resultSet.getString("SchedulerEnddate") );
	
	}
	else{
	out.println("-");
	}  %></td>



  <td><%=resultSet.getString("Status") %></td>
<!--<td>
<div class="slds-form-element__control">
    <div class="slds-select_container">
    <select class="slds-select" id="scriptStatus"  onchange="scriptStatus()">
    <option value="active">Active</option>
        <option value="inactive">Inactive</option>
        
        </select>
        </div>
        </div>
        </td>
       --> 

</tr>


<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
  </tbody>
  
 
</table>

<footer class="slds-card__footer" style="margin-top:0;">
   <!-- <input type="submit" class="slds-button slds-button_brand" id="submit1" value="Save"/> 
    <input type="button" class="slds-button slds-button_brand" id="submit1" value="Cancel"/> -->
</footer>

</div>
</div>
</div>


<div class="footer" style=""><p style="">Mindlinks &copy 2019</p></div>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
</body>
</html>
