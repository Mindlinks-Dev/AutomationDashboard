<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.pack.Database"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.pack.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ProjectScript</title>
<style>
	th{font-size:14px !important;font-weight:bold !important;}
	h2{font-size:20px !important;}
	#test{font-weight:bold;}
</style>
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" /> 
</head>
<body>
<div style="background:white;height:100vh;">
<div class="slds-card slds-m-around_small" style="font-size:17px;">
  <div class="slds-card__header slds-grid">
    <header class="slds-media slds-media_center slds-has-flexi-truncate">
	<img src="Images/cloud.png" width="40px"/>
      <div class="slds-media__figure">
        <span class="slds-icon_container slds-icon-standard-account" title="Project Names">
          
          <span class="slds-assistive-text">Project Names</span>
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
     
    </header>
  </div>
  <div class="slds-card__body slds-card__body_inner1">
  <form   action="executescript.jsp" method="post"  id="form">
<input type="hidden" id="checkedvalues" value="7:-1" name="checkedvalues" />
  <table class="slds-table slds-table_cell-buffer slds-table_bordered">
  <thead>
    <tr class="slds-line-height_reset">
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Project Lists">Project Lists</div>
      </th>
	  <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Category">Category</div>
      </th>
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Select">Select</div>
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
String sql ="select  * from script   WHERE     script_status='active' AND  script_category_name in"+ request.getAttribute("projectname")+" " ;
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr >
<td id="fn"><%=resultSet.getString("script_category_name") %></td>
<td id="fn"><%=resultSet.getString("script_name") %></td> 
<td><input type="checkbox" name="scriptlist"  value=<%=resultSet.getString("script_id") %> value="execute"  > </td> 
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
    <input type="submit" class="slds-button slds-button_brand" id="submit1" value="Execute" onclick="return validateForm();"/>
</footer>
</form>
</div>
</div>
</div>
</body>
</html>