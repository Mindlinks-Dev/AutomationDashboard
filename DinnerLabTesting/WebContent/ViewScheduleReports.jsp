
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
<link type="text/css" rel="stylesheet" href="Main.css" /> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" /> 
<style>
th{font-size:14px !important;font-weight:bold !important;}
#tabs-container
{
  background-color: #fff;
  box-shadow: 0px 3px 4.7px 0.3px rgba(0, 0, 0, 0.24);
  height:51px;
}
.tabs-menu {
    height: 30px;
    float: left;
    clear: both;
}
.tabs-menu li { 
    float: left;
    
    left: 1.4%;
    line-height: 42px;
    list-style-type: none;
    margin-top: -2%;
	position:relative;
}
.tabs-menu li:hover
{
 background:#337ab7;
 color:white;
}
.tabs-menu li.current {
    position: relative;
    background:#337ab7;  
    z-index: 5;    
}
.tabs-menu li a {
    padding: 12px;
    text-transform: uppercase;
	color:black; 
    text-decoration: none; 
    font-size:14px;
}
.tabs-menu li a:hover {
color:white;
}
.tabs-menu .current a {
   color:white; 
}
.tab {
    float: left;
    margin-bottom: 20px;
    width: 92%;
    margin-left:4%;
    margin-top:-2%;
}
.tab-content {
    padding: 20px;
    display: none;
}
#tab-1 {
 display: block;   
}
button.accordion {
  
   width:100%;
	height:40px;
	display:block;
	background:#337ab0;
	color:white;	
	line-height:36px;
	text-decoration:none;
	font-size:20px;
	text-align:left;
}

button.accordion:hover {
 background: #188fff url("Images/arrow2.png") no-repeat;
background-position:90%;
	background-size:9%;
	background-repeat:no-repeat;
}
button#accordion{background:transparent !important;border:1px solid gray;color:black;border-radius:4px !important;border-bottom:2px solid gray;}
#devorg_bg
{
color:#337ab0 !important;
text-decoration:none !important;
background-color:transparent !important;
text-align:left !important;
background-image:none !important;
border:0;
}
#devorg_bg:hover
{
color:#188fff !important;
text-decoration:underline !important;
}
div.panel {
    padding: 0 18px;
    background-color: white;
    max-height: 0;
    overflow: hidden;
    transition: 0.6s ease-in-out;
    opacity: 0;
   
    
}
div.panel.show {
    opacity: 1;
    max-height: 500px;
    background:#f4f4f4;
      
}
a:active, a:focus,input, input:active, input:focus{     outline: 0;     outline-style:none;     outline-width:0; }  
a:active, a:focus,button::-moz-focus-inner, input[type="reset"]::-moz-focus-inner, input[type="button"]::-moz-focus-inner, input[type="submit"]::-moz-focus-inner, input[type="file"] > input[type="button"]::-moz-focus-inner 
{     border: none; } 
caption{font-size:20px;color:#337ab7;padding-bottom:0.4%;text-transform:uppercase;}

th{padding: 0.5%;background:#ccc;color:#337ab7;font-weight:normal;}
.view{background:url("Images/mindlinkslogo.png")}

.accordion:after {
    content: '\002B';
    color: #777;
    font-weight: bold;
    float: right;
    margin-left: 5px;
}

.active:after {
    content: "\2212";
}

/* Modal Content */
.modal-content {
	}
.modal-content label
{
	display:inline-block;
	width:30%;	
}
.modal-content input{
	width:230px;
	padding:4px;
	margin-bottom:12px;
}
.modal-content h2
{
	font-size:20px;
	font-weight:bold;
}
.modal-content .scheduleBtn{margin-left: 169px;}
.modal-content select
{
	width:230px;
	padding:4px;
	margin-bottom:12px;
}
.control{
	text-align: left;
	padding-left: 120px;
}
/* The Close Button */
.close {
    color: #fff;
    text-align: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
</style>

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
<body onLoad="onload()">
<div id="logo">
<a id="logout"  class="slds-button slds-button_neutral backbutton" onclick="back()"  style="">&nbsp;</a>
<a id="logout" class="slds-button slds-button_neutral" href="client.jsp" onclick="return setSessionOff();">Logout</a>
</div>

<div class="header">
<h3 style="">Sigma Automation Dashboard</h3>


</div>


<div>
<div class="slds-card" style="font-size:17px;">
  <div class="slds-card__header slds-grid">
    <header class="slds-media slds-media_center slds-has-flexi-truncate">
	<img src="Images/cloud.png" width="40px"/>
      <div class="slds-media__figure">
        <span class="slds-icon_container slds-icon-standard-account" title="Project Names">
          
          <span class="slds-assistive-text">Schedule Details Page</span>
        </span>
      </div>
      <div class="slds-media__body">
        <h2 class="slds-card__header-title">
          <a href="javascript:void(0);" class="slds-card__header-link slds-truncate" title="Accounts">
            <!--<span>Project Name=<%= request.getAttribute("projectname")%></span>-->
			<span>Project Scripts</span>
          </a>
        </h2>
      </div>
	<input type="hidden" id="field_results"/><br>	
	<input type="hidden" id="OrderList" name="OrderList" value="">	  
     
    </header>
  </div>
  <div class="slds-card__body slds-card__body_inner1">
  
  <table class="slds-table slds-table_cell-buffer slds-table_bordered">
  <thead>
    <tr class="slds-line-height_reset">
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="ScheduleID">ScheduleID</div>
      </th>
	  <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="UserName">UserName</div>
      </th>
	  <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="ScriptStartDateTime">Script_StartDateTime</div>
      </th>
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="SchedulerEnddate">Script_EndDate</div>
      </th>
       <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Status">Script_Status</div>
       </th>
    </tr>
  </thead>
  <tbody>      
	<%


Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
try{
connection = Database.getConnection();
statement=connection.createStatement();
String sql ="select * from schedule";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr >
<td><%=resultSet.getString("Schedule_id") %></td>  
<td><%=resultSet.getString("User_Name") %></td>  
<td><%=resultSet.getString("ScriptStartDateTime") %></td>  
<td><%if(resultSet.getString("SchedulerEnddate")!=null)
	

{
	
	
	out.println(resultSet.getString("SchedulerEnddate") );
	
	}
	else{
	out.println("-");
	}  %></td>



<!--  <td><%=resultSet.getString("Status") %></td>-->
<td>
<div class="slds-form-element__control">
    <div class="slds-select_container">
    <select class="slds-select" id="scriptStatus"  onchange="scriptStatus()">
    <option value="active">Active</option>
        <option value="inactive">Inactive</option>
        
        </select>
        </div>
        </div>
        </td>
        

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
