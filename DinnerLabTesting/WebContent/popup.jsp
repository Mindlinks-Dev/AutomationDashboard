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
<title>Insert title here</title>
<style type="text/css">
    html,body{ margin:0;padding:0;}
	#container { width: 1000px;
	             margin-left:auto;
	             margin-right:auto;
	             border-style: solid;
                 border-width: 1px;
                 height:auto;
                 min-height:600px;
                 margin-top:-15px;}
                 
    
</style>
<link type="text/css" rel="stylesheet" href="TableCSSCode.css" />
</head>
<body>

  <%
    String Id=request.getParameter("id");
     //int id=Integer.parseInt(request.getParameter("id"));
     System.out.println(" selected Id is : "+Id);
     Connection con=null;
     PreparedStatement ps=null;
     ResultSet rs=null;
        int id=Integer.parseInt(Id);
        System.out.println("after converting to intger "+id);
         
        try
        {
         con=Database.getConnection();

        //String query="select scriptname,seqno from executiondetails where execid=Id";
          ps=con.prepareStatement("select scriptname,seqno from executiondetails where execid=? ");

           ps.setInt(1,id);
       rs=ps.executeQuery();


           %>
<center>
<div class="CSSTableGenerator">
<table border="1">
<tr style="font-size:25px;">

<td>Script Name</td>
<td>Sequence No</td>
<!-- <td>Results</td> -->
<!-- <td>Start Time</td> -->

<!-- <td>End TimeTime</td> -->
<!-- <td>Time Taken</td> -->
<!-- <td>Comments</td> -->
<tr>



<%

  while(rs.next())
              {
                
               
           /*     String Results=null;
if(rs.getString(6)==null)
{
	Results=" ";
}
else
{
	Results=rs.getString(6);
}

String StartTime=null;
if(rs.getString(3)==null)
{
	StartTime=" ";
}
else
{
	StartTime=rs.getString(3);
}

String EndTime=null;
if(rs.getString(4)==null)
{
	EndTime=" ";
}
else
{
	EndTime=rs.getString(4);
}




String TimeTaken=null;
if(rs.getString(5)==null)
{
	TimeTaken=" ";
}

else
{
	TimeTaken=rs.getString(5);	
}



String Comments=null;
if(rs.getString(7)==null)
{
	Comments=" ";
}
else
{
	Comments=rs.getString(7);
}
*/
%>


                
                  <tr>
                  <td><%=rs.getString(1) %></td>
                   <td><%=rs.getString(2) %></td>
                  
                   </tr>

                   <%
                 }
        
        }
        finally
               {
            	  
            	   rs.close();
            	   ps.close();
            	   con.close();
               }
               
               
               %>
              
               

</table>
<br>
<br>

</div>
<br>
<br>

<input type="button" value="Close" onClick="closeFunction()" style="float: right;margin-right:700px; border: solid 2px #1C9CBC;">

</center>
<script>
function closeFunction()
{
//alert("123343545");
	window.close();

}

function close_mywindow()
{   
	alert("Inside close():");
	window.parent.close();
	return true;
}
</script>

</body>
</html>