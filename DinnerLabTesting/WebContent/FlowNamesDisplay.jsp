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
<link href="TableCSSCode.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
    html,body{ margin:0;padding:0;}
    #container { width: 800px;
	             margin-left:auto;
	             margin-right:auto;
	             border-style: solid;
                 border-width: 1px;
                 height:auto;
                 min-height:600px;
                 margin-top:-0px;}
</style>           
	

<%
	String ScriptID=request.getParameter("scriptid");

	String FormIds=request.getParameter("formid").toString();
	System.out.println("Inside FlowNamesDisplay page: Form IDs:"+FormIds);
	int checkboxcount=0;


%>

</head>
<body>
<div id="container">
<div class="header1" style="margin-top: -20px"><center style="float: left;margin-left: 280px"><h2>Automation Dashboard</h2></center></div>
<br>
<div id="bodydiv" style="margin-top:-20px;">

<%
System.out.println("Checked value:"+request.getParameter("checkedvalues"));
Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;
String[] formIdList= null;
formIdList=FormIds.split(":");
System.out.println("formIdList[0] :"+formIdList[0]);
System.out.println("formIdList[1] :"+formIdList[1]);
String mystring[];
String CheckBoxValue="";
String s1[];
//int count=0;
//String s1[];
// s1[];
if(formIdList[1].equals("-1"))
{
	CheckBoxValue="checked";	
}

else
{
	s1=formIdList[1].split(",");
	for(int k=0; k < s1.length ; k++)
	{
		System.out.println("s1 :"+s1[k]);
	}
}







try
{
		con = Database.getConnection();
	 	ps=con.prepareStatement("select FlowId,FlowName from script_flows where status='Active'");
		rs=ps.executeQuery();
		

		
		
		
	%>	
	<center>
		<form action="clienthome.jsp" method="post">
		<input type="hidden" id="scriptid" value="<%=ScriptID%>" name="scriptid">
		
	<div class="CSSTableGenerator" style="width: 400px; margin-top: 60px;">
<br>

	<table>
		<tr>
			<td style="font-size: 16px;font-style:normal;">Flow Name</td>
		</tr>
	<% 	
		
		while(rs.next())
		{
			checkboxcount=checkboxcount+1;
			
			//String CheckBoxValue="";
			//CheckBoxValue="";
			/*if(CheckBoxValue.equals("-1"))
			{
				CheckBoxValue="checked";
			}
			else 
			{
				for(int count=0; count < formIdList[1].length() - 1; count++ )
				{
					count++;
					//if(formIdList[count].equals(rs.getString(1)));
					if((rs.getString(1).contains(formIdList[count])))
					{
						CheckBoxValue="checked";
						break;
					}
				}
			}
			*/
			if(formIdList[1].equals("-1"))
			{
				CheckBoxValue="checked";
				
			}
			else
			{
			String []temp=formIdList[1].split(",");
			for(int k=0; k < temp.length ; k++)
			{
				System.out.println("inside While loop for testing :");
				System.out.println("s1 :"+temp[k]);
				if(temp[k].equals(rs.getString(1)))
				{
					CheckBoxValue="checked";
					break;
				}
				else
				{
					CheckBoxValue="";
				}
			}
			}
		%>
		<center><tr><td style="margin-left: 10px"><input type="checkbox" name="checklist" id="chk" onclick="CheckboxValidation()" <%=CheckBoxValue%> value="<%= rs.getString(1)%>"><%= rs.getString(2) %></td></tr></center>
		<% 
		
		}
	
	//System.out.println("Total no of checkboxes(FLow Names Inside) Database are:"+checkboxcount);
	%>
	</table>
	</center>
	<% 
	}
catch(Exception ex)
{
	System.out.println("Exception occured :"+ex.getMessage());
}
   finally
   {
	rs.close();
	ps.close();
	con.close();
   }

%>
<center>
<br>
<br>
<ul>
<input type="button" value="Save" id="show" onclick="return validateForm();">&nbsp;&nbsp;
<input type="button" value="Close" onClick="window.close()">
<input type="hidden" id="totalcheckbox" value="<%=checkboxcount %>" name="totalcheckbox">
</form>
</center>
</ul>
</div><!-- closing of bodydiv -->
<div class="footer1" style="margin-top: 111px"><br><center><h3>Mindlinks Solution 2016</h3></center></div>
</div><!-- closing of container div -->
<script>

// Checkbox validating whether he or she checked the box based on the Decending order
function CheckboxValidation()
{
	// Getting array values
	var check = document.getElementsByName("checklist");
	//alert('check '+check[0]);
	var arra=[];
	var j;
	var i=0;
	//alert('before the for loop :');
	//alert('check box length is '+check.length);
	// Looping through 
	for(j= check.length-1; j >=0 ; j-- )
		{	
		//alert(" The value of j is"+j);
		
		//alert("Value of the check box is"+check[j].value);
		//return false;
		//alert('inside the 1st for loop :');
		// First memberregistration checkbox cheked or not
		if(check[j].checked==false && j==0)
		{
		//alert('inside if condtition*************:');
		//alert("j value before is "+j);
		j=j+1;
		if(check[j].checked==true)
			{
			alert('Please Uncheck the Dietary Restrictions Checkbox');
			//j=-1;
			return false;
			}
		j=j+1;
		 if(check[j].checked==true)
		 {
		alert('Please Uncheck the Ticket Purchase Checkbox');
		//j=-2;
		return false;
		}
		 return false;
		
	}
		
		// Dietory restrictions checked or not
		if(check[j].checked==false && j==1)
			{
			//alert('Second Value check box');
			//alert("j value before is "+j);
			j=j+1;
			if(check[j].checked==true)
				{
				alert('Please Uncheck ticket Purchase and Dietary Restrictions Checkbox');
				return false;
				}
			
			}
		
		
		// Ticket Purchase Checkbox checked; We can't allowed only this checkbox to check
		if(check[j].checked==true && j==2)
			{
			//alert("Third check box");
			j=j-1;
			if(check[j].checked==false)
				{
				alert('Please Uncheck the Ticket Purchase Checkbox');
				return false;
				}
			j=j-1;
			
			if(check[j].checked==false)
			{
				alert('Please Uncheck the Ticket Purchase Checkbox');
			   return false;
			}
			//return false;
			}
		
		
		// Ticket Purchase Checkbox checked or not
		if(check[j].checked==false && j==2)
		{
		//alert("Third check box");
		j=j-1;
		// Dietory Restriction checked or not
		if(check[j].checked==false)
			{
			//alert("plz Uncheck  ticket Purchase checkbox  : ");
							return false;
			}
		else
			{
			//alert("Please  Uncheck  Dietary Restrictions checkbox  : ");
			//check[j].checked=false;
			return false;
			}
		j=j-1;
		
		// Member Registration checked or not
		if(check[j].checked==false)
		{//alert("plz Uncheck  ticket Purchase checkbox  : ");
		return false;
		}
		//return false;
		}
		
	}
	
}	

// Save flow ids ******** ValidateForm()  Function to Save FlowId's *****************

function validateForm()
{
	var checkbox= document.getElementsByName("checklist");
	var i,j;
	var k=0;
	for(i= 0; i <= checkbox.length - 1 ; i++)
	{
		//alert('inside  outer for loop : ');
		//{
		if(checkbox[i].checked==false && i==0)
		{
			alert('Please Select Top Most Hybrid Scenario');
			return false;
		}

		if(i==1 && checkbox[i].checked==false && checkbox[i+1].checked==true)
		{
	        alert('Please select Dietary Restriction or uncheck Upgrade Account');
	        //alert('Inside Test condition');
	        return false;
	    }
	    
	    if(i==2 && checkbox[i].checked==false && checkbox[i+1].checked==true)
	    {
  	      alert('Please select Upgrade Account or uncheck Ticket Purchase');
		  //alert('Inside Test condition');
	      return false;
	     }
	}//end of for loop
	
	//alert('check box length is '+checkbox.length);
	var checkArray=[];
	for(i= 0; i <= checkbox.length - 1 ; i++)
		{
		//alert('inside  outer for loop : ');
		//{
		if(checkbox[i].checked)
		{
			checkArray[k]=checkbox[i].value;
			
			//alert('checkArray is :'+checkArray[k]);
			k++;
		}
	
		else
			{
			continue;
			}
	}
		    //alert("checked checkbox length is :"+checkArray.length);
		    //alert('testing :'+document.getElementById("totalcheckbox").value);
		    
		   if(checkArray.length == document.getElementById("totalcheckbox").value)
		   //if(checkArray.length == 4)
		   {
			//alert('inside if condition to indicate all available checkboxes are checked :');
			window.opener.document.getElementById("checkedvalues").value=document.getElementById("scriptid").value+":"+"-1";
			value=document.getElementById("scriptid").value+":"+"-1";
			}
		    else if(checkArray.length != 0)
			{
				//alert('user has not selected all checkboxes so I am setting values myself:'+checkArray);
				//window.opener.document.getElementById("checkedvalues").value=document.getElementById("scriptid")+":"+checkArray;
				window.opener.document.getElementById("checkedvalues").value=document.getElementById("scriptid").value+":"+checkArray;
				//alert("Hidden value :"+window.opener.document.getElementById("checkedvalues").value);
			}
			else
				{
				window.opener.document.getElementById("checkedvalues").value=document.getElementById("scriptid").value+":"+"0";
				}
			//alert('cheked values are :'+checkArray);
			
			window.close();
			//return true;
}
</script>                 
</body>
</html>