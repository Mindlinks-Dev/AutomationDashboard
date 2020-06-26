
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

  //added by Shridhar to validate order of execution on 12-09-2018
 
$(document).ready(function()
{
	//alert("Inside ready");	
	//document.getElementById("scheduledate").innerHTML = "";
	
    $checks = $(":checkbox");
    $checks.on('change', function() {
        var string = $checks.filter(":checked").map(function(i,v)
		{
			
            return this.value;
        }).get().join(",");
		//alert("string:"+string);
        $('#field_results').val(string); 
    });
});

/* //======28-09-2018====
    $(function () {
    	alert('1');
        $("#remember").click(function () {
            if ($(this).is(":checked")) {
                $("#myOrder").removeAttr("disabled");
                $("#myOrder").focus();
            } else {
                $("#myOrder").attr("disabled", "disabled");
            }
        });
    });
//ends here */ 


 //var orderofexecution=[];
 //var checker = document.getElementById('remember');
 //var sendbtn = document.getElementById('myOrder');
 function validate()
 {
	// sendbtn.true = !!this.checked;
	 
 }

function validateOrder(val1,val2){
//alert('script id is:'+val2);
var mapKey = document.getElementById("field_results").value;
//alert("mapKey:"+mapKey);
//alert("mapKey.length:"+mapKey.length);
 var arr = mapKey.split(",");
 //alert("arr:"+arr);
 //alert("arr.length"+arr.length);
 //alert("arr[0]:"+arr[0]);
 //alert("arr[1]:"+arr[1]);
 var oder=document.getElementById(val1.id).value;
 
 //alert('oder is :'+oder);   	
if(oder <= 0)
{

alert('Please enter the Valid Number');
document.getElementById(val1.id).value='';
//document.getElementById('myOrder').value = "";
return false;
}
else
{
	//alert('After if');
	 var oderList = document.getElementById('OrderList').value; 
	 //alert('Before for loop oderList is :'+oderList);
	//alert("After ifarr:"+arr);
	//alert('After if arr.length :'+arr.length);
	 for(var i =0; i < arr.length; i++)
	 {
	 
	   //alert("Inside for loop arr:"+[i]+':'+arr[i]);
	  
	   //alert('Inside for loop Existing oderList is :'+oderList);
		if ((arr[i]) == val2)
		{
			//alert('for scriptId : '+arr[i] +' Order is :'+oder);
			if(oderList =="")
			{
				//alert('Inside if Orderlist is empty true:');
				var temp = arr[i]+'='+oder; 
				//alert('temp :'+temp);
				//document.getElementById('OrderList').value = arr[i]+'='+oder; 
				document.getElementById('OrderList').value = arr[i]+'='+oder+','; 
				//alert('Before Final OrderList is:'+document.getElementById('OrderList').value);
				
			}
			else
			{
				//alert('Inside if Orderlist is not empty:');
				//document.getElementById('OrderList').value = oderList+","+arr[i]+'='+oder+','; 
				document.getElementById('OrderList').value = oderList+arr[i]+'='+oder+','; 
			}
			
			alert('Final OrderList is:'+document.getElementById('OrderList').value);
			
		}
		else
		{	
			//alert("Inside Else :");
			//alert('for scriptId'+arr[i] +'Order is'+oder);
			
		}
		
	 }//For loop ends	
	
  }//end of else	

} //Function ends 

//Ends here


function sectioDisplay()
{
//alert("Inside section function");	
var acc = document.getElementsByClassName("accordion");
//alert("The acc"+acc.length);
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].onclick = function(){
        this.classList.toggle("active");
        //alert("Inside the for loop:::::");
        this.nextElementSibling.classList.toggle("show");
  }
}
}


function displayScheduler()
{		  
	var x = document.getElementById("scheduler-content");
	if (x.style.display === "none") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}
function closeModal()
{	
document.getElementById("scheduler-content").style.display = "none";
}

</script>


<script language="javascript" type="text/javascript">
$(document).ready(function() {

	$('#select-01').val('Select');
	var sPageURL = decodeURIComponent(window.location.search.substring(1)),
    sURLVariables = sPageURL.split('&'),
    sParameterName,
    i;

for (i = 0; i < sURLVariables.length; i++) {
    sParameterName = sURLVariables[i].split('=');

    if (sParameterName[0] === 'id') {
        //alert(sParameterName[1]);
        var currid = sParameterName[1];
 
        
      //  return sParameterName[1] === undefined ? true : sParameterName[1];
    }
}

//alert("id::"+currid);

if(currid=='Mobile')
	{
	$('#tab-1').hide();
	$('#tab-2').hide();
	//var tab='#tab-3';
	$('#tab-3').show();
	$('#tab-4').hide();
   $('#j').focus();
	//$('#tab-3').addClass("current");
	// $(".tab-content").not(tab).css("display", "none");
   //  $(tab).fadeIn();
	
	 //event.preventDefault();
	// $(this).parent().addClass("current");
   //  $(this).parent().siblings().removeClass("current");
	//$(".tab-content").not(tab).css("display", "none");
   // $(tab).fadeIn();


	/*$(".tabs-menu a").parent().addClass("current");
    $(".tabs-menu a").parent().siblings().removeClass("current");
    var tab ='#tab-3';
   // $(".tabs-menu a #tab-3").css("background", "#337ab7 none repeat scroll 0 0");
   
    alert("tab is::"+tab);
    $(".tab-content").not(tab).css("display", "none");
    $(tab).fadeIn();*/

	}else{
	
    $(".tabs-menu a").click(function(event) {
        //alert(event);
        event.preventDefault();
        $(this).parent().addClass("current");
        $(this).parent().siblings().removeClass("current");
        var tab = $(this).attr("href");
        //alert("tab is::"+tab);
        $(".tab-content").not(tab).css("display", "none");
        $(tab).fadeIn();
    });
	}
	
});


                function validateForm()
                {
                	
                	
                	var jsn={
                			
                			}
                	
                	
                	
	                var check = new Array();
	                var i,v=document.getElementsByName("scriptlist");
	                System.out.println("scriptlist=="+scriptlist);
	                
	                
	                //alert('V :::'+v);
	                check  =  Array.prototype.slice.call(v);
	                var returntype = false;
	                //alert(check.length);
	                for (i = 0; i < check.length; i++) 
	                {
	                	//alert(check[i].checked);
	                	if(check[i].checked)
	                	{
	                		return true;
	                	}
	               	 //return false;
	                 }
	                
	               alert('Select atleast one scenario to execute');	
	               return false;
                 }
             
                
                
             
          function setSessionOff() 
          {
          	alert("You will be logged out!");
          	request.getSession().setAttribute("FirstName",null);
          	session.removeAttribute("FirstName");
          	session.setAttribute("FirstName",null);
          	session.invalidate();
          return false;
          }
</script>
             
</head>
<body>
<jsp:include page="header.jsp" />
<div class="slds-grid nav-margin panel-grid">
	<!-- <div class="slds-col">
	<div class="panel panel-default purple">
					<p>Upload Input File<i class="fa fa-upload" aria-hidden="true"></i></p>
					
					<a class="btn-purple" href="UpLoadFile.jsp">Click Here</a>					
								
				</div>
	</div>
	 -->
	<div class="slds-col">
	<div class="panel panel-default green">
					<p>View Execution Details<i class="fa fa-file-text-o" aria-hidden="true"></i></p>
					 
					<a class="btn-green" href="execution.jsp?id=<%=request.getParameter("id")%>">Click Here</a>					
								
				</div>
	</div>
	<div class="slds-col">
	<div class="panel panel-default blue">
					<p>View Scheduled Reports<i class="fa fa-file-text-o" aria-hidden="true"></i></p>
					
					<a class="btn-blue" href="ViewScheduleReports.jsp?id=<%=request.getParameter("id")%>">Click Here</a>					
								
				</div>
	</div>
	<div class="slds-col">
	<div class="panel panel-default pink">
					<p>Upload Script<i class="fa fa-cloud-upload" aria-hidden="true"></i></p>
					
					<a class="btn-pink" id="Upload" onclick="uploadPopup();">Click Here</a>					
								
				</div>
	</div>
</div>
<div class="container">
<div id="welcome">


<%
String DownloadFileUrl=AutomateLoader.config.getProperty("UPLOADED_INPUTFILE_DOWNLOAD");
%>




<div>
<div class="slds-card1">
 <div class="slds-card__header slds-grid">
   <header class="slds-media slds-media_center slds-has-flexi-truncate">
     <div class="slds-media__figure">
       <span class="slds-icon_container slds-icon-standard-account" title="Project Names">
         
         <span class="slds-assistive-text">Project Names</span>
       </span>
     </div>
    <div class="slds-media__body">
       <h2 class="slds-card__header-title">
         <a href="javascript:void(0);" class="slds-card__header-link slds-truncate" title="Accounts">
           <span><i class="fa fa-file-text" aria-hidden="true"></i> PROJECT SCRIPTS</span>
         </a>
       </h2>
      </div>
	<input type="hidden" id="field_results"/><br>	
	<input type="hidden" id="OrderList" name="OrderList" value="">	  
     
    </header>
  </div>
  <div class="slds-card__body slds-card__body_inner1">
  <form action="executescript.jsp" method="post" id="form1">
<input type="hidden" id="checkedvalues" value="7:-1" name="checkedvalues" />
  <table class="custom-table slds-table slds-table_cell-buffer slds-table_bordered slds-table_fixed-layout">
  <thead>
    <tr class="slds-line-height_reset">
    <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Select">Select</div>
      </th> 
      <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Project Lists">Project Lists</div>
      </th>
	  <th class="slds-text-title_caps" scope="col">
        <div class="slds-truncate" title="Scripts">Scripts</div>
      </th>
      
      <!-- <th class="slds-text-title_caps" scope="col">
       <div class="slds-truncate" title="Order">ORDER OF EXECUTION</div>
     </th>	 -->  
   </tr>
 </thead>
 <tbody>      
<%
String proId = request.getParameter("id");
System.out.println("proId in clienthometoaction="+proId);
String proname = request.getParameter("name");
System.out.println("proId in clienthometoaction="+proname);
session.setAttribute("ProjectId", proId);
session.setAttribute("Projectname", proname);

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
try{
connection = Database.getConnection();
statement=connection.createStatement();
//String sql ="select  * from script   WHERE     script_status='active' AND  script_category_name in"+ request.getAttribute("projectname")+" " ;
String sql ="select  * from script s   WHERE  script_status='active' AND  s.ProjectID= "+proId;
System.out.println("sql ===="+sql);
resultSet = statement.executeQuery(sql);
System.out.println("sql resultSet===="+resultSet);
while(resultSet.next()){
	String SciptID=resultSet.getString("script_id") ;
	String ScriptName=resultSet.getString("script_name");
    ScriptEntity.HScenarios.put(Integer.parseInt(SciptID),ScriptName);
	String SciptIDNew=resultSet.getString("script_id") ;
    //out.println("     SciptID Meghana  ::::" + SciptID+"  ");
	
%>


<tr >
<td><input type="checkbox" name="scriptlist" value=<%=resultSet.getString("script_id") %> value="execute"  > </td>
<td id="fn1"><%=request.getParameter("name") %></td> 

<td id="fn"><%=resultSet.getString("script_name") %></td> 

 
<!--  <td> <input type="number" name="order" value="" id="myOrder<%=resultSet.getString(5)%>" onchange="validateOrder(this,<%=SciptIDNew%>)"></td>  -->
<!-- <td> <input type="number" name="order" value="" id="myOrder<%=resultSet.getString(5)%>" onchange="validateOrder(this,<%=SciptIDNew%>)"  ></br></td> -->



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
<footer class="slds-card__footer" style="margin-top:0;padding-top: 30px;">
   <input type="submit" class="slds-button slds-button_brand" id="submit1" value="Execute" onclick="return validateForm();"/>
   <input type="button" class="slds-button slds-button_brand" id="submit1" value="Schedule" onclick="displayScheduler()"/>
                                          
                                          <div class="scheduler-content" id="scheduler-content" style="display:none;">

<div class="demo-only">
  <section role="dialog" tabindex="-1" aria-labelledby="modal-heading-01" aria-modal="true" aria-describedby="modal-content-id-1" class="slds-modal slds-fade-in-open">
    <div class="slds-modal__container">
	<input type="hidden" name="rundrop" value="once" id="rundrop">
										<input type="hidden" name="isSchedule" value="notschduled" id="isSchedule">
                                        
                                            
										<form method="post" action="TimerSchedule1">
	<span class="close" onclick="closeModal()">&times;</span>
     <header class="slds-modal__header">
       
       <h2 id="modal-heading-01" class="slds-text-heading_medium slds-hyphenate">Time Schedule</h2>
     </header>
     <div class="slds-modal__content slds-p-around_medium" id="modal-content-id-1">
       <div class="scheduler-modal">
									  <div class="modal-content">
										
                                   	
									<div class="control" style="margin-bottom:14px;text-align:left;">
									<label class="slds-form-element__label">Run:</label>
									<select id="rundropdown" class="slds-select" onchange="runtype();">
										<option value="once">Once</option>
										<option value="everyday">Every Day</option> 
									</select><br/>
									
									<label class="slds-form-element__label">Run Date&Time:</label>
										<input type="text" class="slds-input" name="scheduledate" id="scheduledate">
									<br/>
									<div id="enddateSection" style="display:none;">
										<label class="slds-form-element__label" id="endDateLabel">End DateTime:</label>
										<input type="text" class="slds-input" name="scheduleenddate" id="scheduleenddate">  
									</div>
									<br/>
									
										
										  <!--<span class="hours">Office hours: 9AM to 6PM</span>-->

									</div>
										
									
									                                         
                                       </div>     
                                   </div>
     </div>
     <footer class="slds-modal__footer">
  <input type="button" class="slds-button slds-button_brand" onclick="ScheduleScript();" value="Save"/>	  
      <input type="button" class="slds-button slds-button_brand" onclick="closeModal();" value="Cancel"/>	 
      </footer>
	  </form> 
    </div>
  </section>
  <div class="slds-backdrop slds-backdrop_open"></div>
</div>										   
										
</div>
</footer>
</form>
</div>
</div>
</div>


<!-- code to validate whether the check box is checked -->

<div id="uploadPopup" style="display:none;">
  <section role="dialog" tabindex="-1" aria-labelledby="modal-heading-01" aria-modal="true" aria-describedby="modal-content-id-1" class="slds-modal slds-fade-in-open">
    <div class="slds-modal__container">
     
     <form action="Action_file_upload.jsp" method="post"
                        enctype="multipart/form-data">
      <header class="slds-modal__header">       
        <h2 id="modal-heading-01" class="slds-text-heading_medium slds-hyphenate">Upload File</h2>
      </header>
      <div class="slds-modal__content slds-p-around_medium" id="modal-content-id-1">
        <input type="file" name="file"/>    
	
      </div>
      <input type="hidden" id="filename" name="filename" value="">
      <input type="hidden" id="ProjectId" name="ProjectId" value="">
	  
      <footer class="slds-modal__footer">
        <input class="slds-button slds-button_brand" type="submit" id="submit1" value="Upload"/>
        <button class="slds-button slds-button_neutral" onclick="cancelUploadPopup()">Cancel</button>
        
        <!-- <button class="slds-button slds-button_brand" onclick="SaveUpload()" type="submit">Save</button> -->
        
      </footer> 
      </form>
    </div>
  </section>
  <div class="slds-backdrop slds-backdrop_open"></div>
</div>

</div>

<script>

function show_details(link,scriptid)

{

      

       alert("inside show details");

       var formids=document.getElementById("checkedvalues").value;
	   
	   alert("formids"+formids);

       var Reference=link+'?scriptid='+scriptid+'&formid='+formids;

       //alert(":Reference:"+Reference);

window.open(Reference,"Flows","width=750,height=450,top=100,left=270,right=400,scrollbars=no,status=no,menubar=no");

this.focus();

return false;

}

function ScheduleScript()
{
	var status=document.getElementById("rundropdown").value;

	var startdate=document.getElementById("scheduledate").value;
	var enddate=document.getElementById("scheduleenddate").value;
	//alert('startdate'+startdate+enddate);
	var date1=startdate.split(" ");
	var date2=enddate.split(" ");
	
	if(date1[0]==date2[0] && status=='everyday')
		{
	//	alert('StartDate and EndDate should not be same');
		return false;
		}
	//alert('startdate'+startdate+enddate);
	document.getElementById("isSchedule").value = 'scheduled';
	document.getElementById("form1").submit();
	System.out.println ("Test Submit Button");
	
}

function dropdownchanged()
{
	
}
$(function() {
	
	$('*[name=scheduledate]').appendDtpicker({
		//"futureOnly": true
	});
	
	$('*[name=scheduleenddate]').appendDtpicker();
	
	
	 var x = document.getElementById("rundropdown").value;
	   var e = document.getElementById("rundropdown");
		var strUser = e.options[e.selectedIndex].value;
		document.getElementById("rundrop").value = strUser;
		
	   if(x=='once')
	   {
	   //alert('once');
	   document.getElementById("enddateSection").style.display = "none";
	   document.getElementById("startDateLabel").innerHTML="Run Date&Time:";
	   
	   }
	   else if(x=='everyday')
	   {
	   //alert('everyday');
	   document.getElementById("enddateSection").style.display = "block";
	   document.getElementById("startDateLabel").innerHTML = "Start Date & Time:";
	   document.getElementById("endDateLabel").innerHTML = "End Date:";
	   }
  });
  
function runtype() {
	
	   var x = document.getElementById("rundropdown").value;
	   var e = document.getElementById("rundropdown");
		var strUser = e.options[e.selectedIndex].value;
		document.getElementById("rundrop").value = strUser;
		
	   if(x=='once')
	   {
	   //alert('once');
	   document.getElementById("enddateSection").style.display = "none";
	   document.getElementById("startDateLabel").innerHTML="Run Date&Time:";
	   
	   }
	   else if(x=='everyday')
	   {
	   //alert('everyday');
	   document.getElementById("enddateSection").style.display = "block";
	   document.getElementById("startDateLabel").innerHTML = "Start Date & Time:";
	   document.getElementById("endDateLabel").innerHTML = "End Date:";
	   }
	   //document.getElementById("demo").innerHTML = "You selected: " + x;
	}
	function testflight()
	{
		alert('hi');
	}
	
	function uploadPopup()
	{
	//alert('inside');
		 $("#uploadPopup").css("display", "block");
	}
	function cancelUploadPopup()
	{
		//alert("Before cancel popup:");
		$("#uploadPopup").css("display", "none");
		//alert("After cancel popup:");
	}
	function SaveUpload()
	{
		$("#uploadPopup").css("display", "none");
		//alert('before');
		document.getElementById("file").value = "";
		//document.getElementById("myInput").value = "";
		 
		 //alert('after');
	}
</script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
</body>
</html>
