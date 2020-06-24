
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.ResultSetMetaData"%>

<%@page import="java.sql.ResultSet"%>

<%@page import="java.sql.PreparedStatement"%>

<%@page import="java.sql.DriverManager"%>

<%@page import="java.sql.Connection"%>

<%@page import="java.util.ArrayList"%>

<%@page import="com.pack.*"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>MLS_Dashboard</title>

<link type="text/css" rel="stylesheet" href="Main.css" />

<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">

<style>
#tabs-container {
    background-color: #fff;
    box-shadow: 0px 3px 4.7px 0.3px rgba(0, 0, 0, 0.24);
    height: 51px;
}

.tabs-menu {
    height: 30px;
    float: left;
    clear: both;
}

.tabs-menu li {
    float: left;
    left: 1.4%;
    line-height: 44.2px;
    list-style-type: none;
    margin-top: -2%;
    position: relative;
}

.tabs-menu li:hover {
    background: #337ab7;
    color: white;
}

.tabs-menu li.current {
    position: relative;
    background: #337ab7;
    z-index: 5;
}

.tabs-menu li a {
    padding: 12px;
    text-transform: uppercase;
    color: black;
    text-decoration: none;
    font-size: 15px;
    font-weight:bold;
}

.tabs-menu li a:hover {
    color: white;
}

.tabs-menu .current a {
    color: white;
}

.tab {
    float: left;
    margin-bottom: 20px;
    width: 92%;
    margin-left: 4%;
    margin-top: -2%;
}

.tab-content {
    padding: 20px;
    display: none;
}

#tab-4 {
    display: block;
}

button.accordion {
    width: 100%;
    height: 40px;
    display: block;
    background: #337ab0;
    color: white;
    line-height: 36px;
    text-decoration: none;
    font-size: 20px;
    text-align: left;
}

button.accordion:hover {
    background: #188fff url("Images/arrow2.png") no-repeat;
    background-position: 90%;
    background-size: 9%;
    background-repeat: no-repeat;
}

button#accordion {
    background: transparent !important;
    border: 1px solid gray;
    color: black;
    border-radius: 4px !important;
    border-bottom: 2px solid gray;
}

#devorg_bg {
    color: #337ab0 !important;
    text-decoration: none !important;
    background-color: transparent !important;
    text-align: left !important;
    background-image: none !important;
    border: 0;
}

#devorg_bg:hover {
    color: #188fff !important;
    text-decoration: underline !important;
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
    max-height: 1000px;
    background: #f4f4f4;
}

a:active, a:focus, input, input:active, input:focus {
    outline: 0;
    outline-style: none;
    outline-width: 0;
}

a:active, a:focus, button::-moz-focus-inner, input[type="reset"]::-moz-focus-inner,
    input[type="button"]::-moz-focus-inner, input[type="submit"]::-moz-focus-inner,
    input[type="file"]>input[type="button"]::-moz-focus-inner {
    border: none;
}

caption {
    font-size: 20px;
    color: #337ab7;
    padding-bottom: 0.4%;
    text-transform: uppercase;
}

#submit1 {
    border: 2px solid #337ab7;
    color: #337ab7;
    background: transparent;
    padding: 0.1% 1%;
    font-size: 18px;
    margin-top: 1.5%;
	cursor:pointer;
}

#submit1:hover {
    background: #337ab7;
    color: white;
}

th {
    padding: 0.5%;
    background: #ccc;
    color: #337ab7;
    font-weight: normal;
}

.view {
    background: url("Images/mindlinkslogo.png")
}

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
    background-color: #fefefe;
	padding: 12px 16px 16px 41px;
	border: 1px solid #888;
}
.modal-content label
{
	display:inline-block;
	width:25%;	
}
.modal-content input{
	width:200px;
	padding:4px;
	margin-bottom:12px;
}
.modal-content select
{
	width:210px;
	padding:4px;
	margin-bottom:12px;
}
/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
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

//<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js"></script>

<script src="js/jquery.min.js"></script>

<script>


  //added by Shridhar to validate order of execution on 12-09-2018
 
$(document).ready(function()
{
	//alert("Inside ready");
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

/* function validateOrder(val1,val2){
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

//Ends here  */
 
 
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

 

</script>

<script language="javascript" type="text/javascript">

$(document).ready(function() {

 

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



</head>

<body>

    <div id="logo">
 		<h3 style="">Sigma Automation Portal</h3>
        <a id="logout" style="" href="client.jsp"
            onclick="return setSessionOff();">Logout</a>
   </div>

    <div id="clienthome_container">

        <!-- <div class="header" style="">
		<h3 style="">Sigma Automation Dashboard</h3>
		</div>-->


	<div id="welcome">
	 <center>  Welcome Mindlinks Solution
                  <p>Choose automation test scenarios from the below list and click
                "Execute" button to start the automation execution</p>
           <%
                String DownloadFileUrl = AutomateLoader.config.getProperty("UPLOADED_INPUTFILE_DOWNLOAD");
            %>
</center>
            <br />
            <div style="text-align:right;">
       <a id="upload" class="upload" href="UpLoadFile.jsp">Upload Input File</a>
    <a id="upload" class="view" href="execution.jsp">View Execution Details</a> 
   <a id="upload" class="download" href="<%=DownloadFileUrl%>" style="">Download Input File</a> 

</div>
           

        </div>



        <div id="tabs-container">

            <ul class="tabs-menu" id="tabmenu">

                 <li class="current"><a href="#tab-4">Salesforce Orgs</a></li>
                <li ><a href="#tab-1">Other Platform</a></li>

                <!-- <li><a href="#tab-2">AlignToday</a></li> -->

                <li><a href="#tab-3" id="j">Security Test</a></li>

               



                <li style="visibility: hidden"><a href="#tab-5">AlignToday</a></li>



            </ul>

            <div class="tab">

                <div id="tab-1" class="tab-content">

                    <div>

                        <form action="executescript.jsp" method="post" id="form">

                            <input type="hidden" id="checkedvalues" value="7:-1"
                                name="checkedvalues" />

                            <!-- /pro/uploads/UpLoadedInPutFiles/Data.xls             My project Url to download file which are uploaded by user-->

                            <%
                                String path = request.getParameter("id");

                                Connection con = null;

                                PreparedStatement ps = null;

                                ResultSet rs = null;

                                String tab1 = "Regression";

                                String tab2 = "AlignToday";

                                String tab3 = "Security";

                                String tab4 = "Others";

                                String tab5 = "Load";

                                String AdditionalWhereClause = "";

                                if (null != path)

                                {

                                    AdditionalWhereClause = " and Test_type='" + path + "'";

                                }

                                //System.out.println("Test Type:"+AdditionalWhereClause);

                                //if(tab1.equals("Regression"))

                                //{

                                try

                                {

                                    con = Database.getConnection();

                                    //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                    String FullQuery = "select * from script where script_status='active' and Test_type='Regression'";

                                    //System.out.println("Full QueryInside Regression Test_Type tab1 :"+FullQuery);

                                    ps = con.prepareStatement(FullQuery);

                                    rs = ps.executeQuery();
                            %>



                            <div>

                                <table border="0" title="Test Scripts">

                                    <caption>Test Scripts</caption>

                                    <tr>

                                        <th class="head1" style="width: 6%;">SL.NO</th>

                                        <th class="head1" style="width: 8%;">SELECT</th>
										
										<!--<td class="head1" style="">ORDER OF EXECUTION</td>-->

                                        <th class="head1">SCRIPT NAME</th>

                                        <th class="head1">SCENARIO
                                        </td>

                                        <th class="head1">CATEGORY NAME</th>

                                    </tr>

                                    <div>

                                        <%
                                            if (tab1.equals("Regression"))

                                                {

                                                    //System.out.println("Inside Regression Test_Type Tab :");

                                                    int count = 0;
													int rowCount = 0;

                                                    String flowlink;

                                                    //ScriptEntity.HScenarios.clear();

                                                    int countreference = 0;

                                                    //String insertQuerytoExceutiondetails=("update executiondetails set SelectedFlows='"+checkedval+"'  where execid='"+execid+"' ");

                                                    //int test=con.createStatement().executeUpdate(insertQuerytoExceutiondetails);

                                                    String CompleteTicketPurchase = null;

                                                    while (rs.next())

                                                    {

                                                        countreference++;

                                                        String SciptID = rs.getString(1);

                                                        String ScriptName = rs.getString(2);

                                                        ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);
                                        %>

                                        <%
                                            if (countreference == 1)

                                                        {

                                                            // System.out.println("Inside  IF OF COUNTREF CONDTION ::"+countreference);
                                        %>

                                        <tr style="">
                                            <th colspan="5" rowspan="1">Salesforce Flows</th>
                                        </tr>

                                        <%
                                            }

                                                        else if (countreference == 2)

                                                        {
                                        %>

                                        <tr style="">
                                            <th colspan="5">Signup Flows</th>
                                        </tr>

                                        <%
                                            }

                                                        else if (countreference == 5)

                                                        {
                                        %>

                                        <tr></tr>

                                        <tr style="">
                                            <th colspan="5">Ticket Purchase Flows</th>
                                        </tr>

                                        <%
                                            }

                                                        else if (countreference == 9)

                                                        {
                                        %>

                                        <tr></tr>

                                        <tr style="">
                                            <th colspan="5">Gift Purchase Flows</th>
                                        </tr>

                                        <%
                                            }

                                                        else if (countreference == 10)

                                                        {
                                        %>

                                        <tr></tr>

                                        <tr style="">
                                            <th colspan="5">Referral Flows</th>
                                        </tr>

                                        <%
                                            }

                                                        else if (countreference == 13)

                                                        {
                                        %>

                                        <tr></tr>

                                        <tr style="">
                                            <th colspan="5">Other Flows</th>
                                        </tr>

                                        <%
                                            }

                                                        else if (countreference == 19)

                                                        {
                                        %>

                                        <tr></tr>

                                        <tr style="">
                                            <th colspan="5">User Profile Flows</th>
                                        </tr>

                                        <%
                                            }
                                        %>

            <tr>

                                            <td><%=rs.getString(5)%></td>

                                            <td><input type="checkbox" id="remember"
                                                name="scriptlist" value="<%=SciptID%>" onclick="validate()"></td>
												
												<!--<td> <input type="number" name="order" id="myOrder" ></br></td>-->

                                            <%
                                                if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                            {

                                                                //flowlink=rs.getString(2);

                                                                //CompleteTicketPurchase=rs.getString(1);
                                            %>

                                            <td><%=rs.getString(2)%>&nbsp;<a
                                                href="FlowNamesDisplay.jsp"
                onclick="return show_details(this.href,<%=SciptID%>)">Select
                                                    Hybrid Scenarios</a></td>

                                            <%
                                                } else

                                                            {
                                            %>

                                            <td><%=rs.getString(2)%></td>
                                            <%
                                                }
                                            %>



                                            <td><%=rs.getString(4)%></td>



                                            <td><%=rs.getString(8)%></td>

                                        </tr>

                                        <%
                                            }

                                                    //System.out.println("script name from hashMap "+ScriptEntity.HScenarios);

                                                    //System.out.println("row id is :"+rowid);

                                                    request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                                    //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                                    //var data={

                                                    //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                                } //End of IF Loop checking for Regression Path

                                                //}

                                                /*catch(Exception ex)
                                                
                                                {
                                                
                                                      ex.printStackTrace();     
                                                
                                                } */
                                        %>

                                    </div>

                                </table>

                            </div>

                            <center>
                                <input type="submit" id="submit1" value="Execute"
                                    onclick="return validateForm();" />
                            </center>

                        </form>

                    </div>

                </div>

                <div id="tab-2" class="tab-content ">

                    <!-- START FOR TAB 2 -->

                    <form action="executescript.jsp" method="post" style="" id="form">

                        <input type="hidden" id="checkedvalues" value="7:-1"
                            name="checkedvalues" />

                        <table border="0" title="Test Scripts" style="">

                            <caption>Test Scripts</caption>

                            <tr style="">

                                <td class="head2" style="">SL.NO</td>

                                <td class="head2" style="">SELECT</td>

                                <td class="head2" style="">SCRIPT NAME</td>

                                <td class="head2" style="">SCENARIO</td>

                                <td class="head2" style="">CATEGORY NAME</td>

                            </tr>

                            <div>

                                <%
                                    if (tab2.equals("AlignToday"))

                                        {

                                            //System.out.println("Inside Load Test_Type Tab :");  

                                            //System.out.println("Inside Load Test_Type Tab :");

                                            int count = 0;

                                            String flowlink;

                                            //ScriptEntity.HScenarios.clear();

                                            int countreference = 0;

                                            int countScriptID = 0;

                                            String CompleteTicketPurchase = null;

                                            //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                            FullQuery = "select * from script where script_status='active' and Test_type='AlignToday'";

                                            //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                            ps = con.prepareStatement(FullQuery);

                                            rs = ps.executeQuery();

                                            while (rs.next())

                                            {

                                                countScriptID++;

                                                countreference++;

                                                String SciptID = rs.getString(1);

                                                String ScriptName = rs.getString(2);

                                                ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);
                                %>

                                <%
                                    
                                %>

                                <tr style="";></tr>

                                <td><%=rs.getString(5)%></td>

                                <%--  <td><%=  countScriptID%></td> --%>

                                <td><input type="checkbox" id="remember" name="scriptlist"
                                    value="<%=SciptID%>" onclick="validate()"></td>

                                <%
                                    if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                {

                                                    //flowlink=rs.getString(2);

                                                    //CompleteTicketPurchase=rs.getString(1);
                                %>

                                <td><%=rs.getString(2)%>&nbsp;<a
                                    href="FlowNamesDisplay.jsp"
                                    onclick="return show_details(this.href,<%=SciptID%>)">Select
                                        Hybrid Scenarios</a></td>

                                <%
                                    } else

                                                {
                                %>

                                <td><%=rs.getString(2)%></td>
                                <%
                                    }
                                %>



                                <td><%=rs.getString(4)%></td>



                                <td><%=rs.getString(8)%></td>

                                </tr>

                                <%
                                    }

                                            //System.out.println("script name from hashMap "+ScriptEntity.HScenarios);

                                            //System.out.println("row id is :"+rowid);

                                            request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                            //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                            //var data={

                                            //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                        }

                                        /* else
                                        
                                        {
                                        
                                              System.out.println("Tab 3 display code to be inserted");
                                        
                                        }
                                        
                                        */
                                %>
                            
                        </table>

                        <center>
                            <input type="submit" id="submit1" value="Execute"
                                onclick="return validateForm();" style="">
                        </center>

                    </form>

                </div>

            </div>

            <!-- END TAB 2 -->



            <!-- Start of TAb3 Mobile Test starts here  -->

            <div id="tab-3" class="tab-content">

                <form action="executescript.jsp" method="post" class="form">

                    <input type="hidden" id="checkedvalues" value="7:-1"
                        name="checkedvalues" />

                    <table border="0" title="Test Scripts" style="">

                        <caption>Test Scripts</caption>

                        <tr style="">

                            <td class="head2" style="">SL.NO</td>

                            <td class="head2" style="">SELECT</td>
							
							<!--<td class="head2" style="">ORDER OF EXECUTION</td>-->

                            <td class="head2" style="">SCRIPT NAME&nbsp;&nbsp;&nbsp;</td>

                            <td class="head2" style="">SCENARIO&nbsp;&nbsp;&nbsp;</td>

                            <td class="head2" style="">CATEGORY NAME</td>

                        </tr>



                        <!--Commented because No scripts are available currently -->

                        <%
                            if (tab3.equals("Security"))

                                {

                                    //System.out.println("Inside Load Test_Type Tab :");  

                                    try

                                    {

                                        //System.out.println("Inside Load Test_Type Tab :");

                                        int count = 0;

                                        String flowlink;

                                        //ScriptEntity.HScenarios.clear();

                                        int countreference = 0;

                                        int countScriptID = 0;

                                        String CompleteTicketPurchase = null;

                                        //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                        FullQuery = "select * from script where script_status='active' and Test_type='Security'";

                                        //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                        ps = con.prepareStatement(FullQuery);

                                        rs = ps.executeQuery();

                                        while (rs.next())

                                        {

                                            countScriptID++;

                                            countreference++;

                                            String SciptID = rs.getString(1);

                                            String ScriptName = rs.getString(2);

                                            ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);
                        %>

                        <%
                            
                        %>





                        <tr style="font-family: Arial; font-size: 14px";>

                            <!--  <td><%=rs.getString(5)%></td>-->

                            <td><%=countScriptID%></td>

                            <td><input type="checkbox" id="remember" name="scriptlist"
                                value="<%=SciptID%>" onclick="validate()"></td>
								
								<!--<td> <input type="number" name="order"></br</td>-->

                            <%
                                if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                {

                                                    //flowlink=rs.getString(2);

                                                    //CompleteTicketPurchase=rs.getString(1);
                            %>

                            <td><%=rs.getString(2)%>&nbsp;<a href="FlowNamesDisplay.jsp"
                                onclick="return show_details(this.href,<%=SciptID%>)">Select
                                    Hybrid Scenarios</a></td>

                            <%
                                } else

                                                {
                            %>

                            <td><%=rs.getString(2)%></td>
                            <%
                                }
                            %>



                            <td><%=rs.getString(4)%></td>



                            <td><%=rs.getString(8)%></td>

                        </tr>

                        <%
                            }

                                        //System.out.println("script name from hashMap "+ScriptEntity.HScenarios);

                                        //System.out.println("row id is :"+rowid);

                                        request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                        //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                        //var data={

                                        //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                    }

                                    catch (NullPointerException ex)

                                    {

                                        ex.printStackTrace();

                                    }

                                }

                                /* else
                                
                                {
                                
                                      System.out.println("Tab 3 display code to be inserted");
                                
                                }
                                
                                */
                        %>

                    </table>



                    <center>
                        <input type="submit" id="submit1" value="Execute"
                            onclick="return validateForm();" style="">
                    </center>

                </form>

            </div>



            <!-- ****************Start of TAb3 Mobile Test Ends   ***************** here  -->

            <!-- Code Starts to display Tab for Loading Salesforce App Exchange  Test Scripts from DataBase! -->

            <div id="tab-4" class="tab-content">

                <div class="form" id="form_salesappexchange">



                    <!-- <div style="width:100"><h4 style="text-align:center">Products</h4>-->

                    <div id="salesappexchange" style="display:none;">

                        <!-- <img src="Images/checklist.jpg"><br> -->

                        <!-- <button class="accordion" onclick="sectioDisplay(this.value);" style="">Salesforce Flows</button>-->







                        <div class="panel">

                            <div style="">

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Preparation</button>

                                <div class="panel">This is div1</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Tech
                                    Review</button>

                                <div class="panel">This is div2</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Implementation</button>

                                <div class="panel">This is div3</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Best
                                    Practices</button>

                                <div class="panel">This is div4</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Code
                                    Scan</button>

                                <div class="panel">This is div5</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Packaging</button>

                                <div class="panel">This is div6</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Environment
                                    Setup</button>

                                <div class="panel">This is div7</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Automation</button>

                                <div class="panel">This is div8</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Sec
                                    Review</button>

                                <div class="panel">This is div9</div>

                                <button class="accordion" id="devorg_bg"
                                    onclick="sectioDisplay(this.value);" style="">Post App
                                    Approval</button>

                                <div class="panel">This is div10</div>

                            </div>

                        </div>

                    </div>



                    <div id="salesappexchange">

                        <!-- <img src="Images/tonixss.jpg"><br> -->

                        <button class="accordion" onmouseover="sectioDisplay(this.value);"
                            style="">
                            <i class="fa fa-folder"></i> LIGHTNING PROJECTS
                        </button>

                        <div class="panel show">

                            <!--   <button class="accordion" id="devorg_bg">Dev Org</button>-->

                            <div class="panel">

                                <form action="executescript.jsp" method="post" id="form"
                                    class="form_devorg">

                                    <input type="hidden" id="checkedvalues" value="7:-1"
                                        name="checkedvalues" />

                                    <table border="0" title="Test Scripts" style="">

                                        </div>



                                        <caption>Test Scripts</caption>

                                        <tr style="">

                                            <td class="head2" style="">SCRIPT ID</td>

                                            <td class="head2" style="">SELECT</td>

                                            <td class="head2" style="">SCRIPT NAME</td>

                                            <td class="head2" style="">SCENARIO</td>

                                            <td class="head2" style="">CATEGORY NAME</td>

                                        </tr>

                                        <div>

                                            <%
                                                if (tab4.equals("Others"))

                                                    {

                                                        //System.out.println("Inside Load Test_Type Tab :");  

                                                        //System.out.println("Inside Load Test_Type Tab :");

                                                        int count = 0;

                                                        String flowlink;

                                                        //     ScriptEntity.HScenarios.clear();

                                                        int countreference = 0;

                                                        int countScriptID = 0;

                                                        String CompleteTicketPurchase = null;

                                                        //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                                        FullQuery = "select * from script where script_status='active' and Test_type='Tronixss' ";

                                                        //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                                        ps = con.prepareStatement(FullQuery);

                                                        rs = ps.executeQuery();

                                                        while (rs.next())

                                                        {

                                                            countScriptID++;

                                                            countreference++;

                                                            String SciptID = rs.getString(1);

                                                            String ScriptName = rs.getString(2);

                                                            ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);
                                            %>

                                            <%
                                                
                                            %>





                                            <tr style="";>

                                                <!--  <td><%=rs.getString(5)%></td>-->

                                                <td><%=countScriptID%></td>

                                                <td><input type="checkbox" id="remember"
                                                    name="scriptlist" value="<%=SciptID%>" onclick="validate()"></td>

                                                <%
                                                    if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                                {

                                                                    //flowlink=rs.getString(2);

                                                                    //CompleteTicketPurchase=rs.getString(1);
                                                %>

                                                <td><%=rs.getString(2)%>&nbsp;<a
                                                    href="FlowNamesDisplay.jsp"
                                                    onclick="return show_details(this.href,<%=SciptID%>)">Select
                                                        Hybrid Scenarios</a></td>

                                                <%
                                                    } else

                                                                {
                                                %>

                                                <td><%=rs.getString(2)%></td>
                                                <%
                                                    }
                                                %>



                                                <td><%=rs.getString(4)%></td>



                                                <td><%=rs.getString(8)%></td>

                                            </tr>

                                            <%
                                                }

                                                        //System.out.println("Inside ClientHome script name from hashMap "+ScriptEntity.HScenarios);

                                                        //System.out.println("row id is :"+rowid);

                                                        request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                                        //System.out.println("Inside ClientHome jsp  scriptName hashMap values HScenarios are  "+ScriptEntity.HScenarios);

                                                        //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                                        //var data={

                                                        //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                                    }
                                            %>
                                        
                                    </table>

                                    <center>
                                        <input type="submit" id="submit1" value="Execute"
                                            onclick="return validateForm();" style="">
                                    </center>



                                </form>

                            </div>

                            <button class="accordion" id="devorg_bg">Coh Baines</button>
							
                            <div class="panel">

                                <form action="executescript.jsp" method="post" id="cohform">

                                    <input type="hidden" id="checkedvalues" value="7:-1"
                                        name="checkedvalues" />

                                    <table border="0" title="Test Scripts" style="">



                                        <br />

                                        <caption>Test Scripts</caption>
									<!--  <input type="text" id="field_results"/><br>	
										<input type="hidden" id="OrderList" name="OrderList" value="">	-->  						
                                        <tr>

                                            <td class="head2" style="">SCRIPT ID</td>

                                            <td class="head2" style="">SELECT</td>
					
										    <!--<td class="head2" style="">ORDER OF EXECUTION</td>  --> 

                                            <td class="head2" style="">SCRIPT NAME</td>

                                            <td class="head2" style="">SCENARIO</td>

                                            <td class="head2" style="">CATEGORY NAME</td>

                                        </tr>

                                        <div>

                                            <%
                                                if (tab4.equals("Others"))

                                                    {

                                                        //System.out.println("Inside Load Test_Type Tab :");  

                                                        //System.out.println("Inside Load Test_Type Tab :");

                                                        int count = 0;

                                                        String flowlink;

                                                        //     ScriptEntity.HScenarios.clear();

                                                        int countreference = 0;

                                                        int countScriptID = 0;

                                                        String CompleteTicketPurchase = null;

                                                        //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                                        FullQuery = "select * from script where script_status='active' and Test_type='Others'and script_category_name='cohbaines'";

                                                        //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                                        ps = con.prepareStatement(FullQuery);

                                                        rs = ps.executeQuery();
														OrderEntity OrderEntityObj = new OrderEntity();
														System.out.println("OrderEntityObj line number1689 :"+OrderEntityObj);
														
                                                        while (rs.next())

                                                        {

                                                            countScriptID++;

                                                            countreference++;

                                                            String SciptID = rs.getString(1);

                                                            String ScriptName = rs.getString(2);

                                                            ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);
                                            %>

                                            <%
                                                
                                            %>





                                            <tr>

                                                <td><%=rs.getString(5)%></td>

                                                <!--  <td><%=countScriptID%></td>-->

                                                <td><input type="checkbox" id="remember"
                                                    name="scriptlist" value="<%=SciptID%>" onchange="validate(this)"></td>
													
													
													
													
												<!-- <td> <input type="number" name="order" value="" id="myOrder<%=rs.getString(5)%>" onchange="validateOrder(this,<%=SciptID%>)"  ></br></td> --> 	

                                                <%
                                                    if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                                {

                                                                    //flowlink=rs.getString(2);

                                                                    //CompleteTicketPurchase=rs.getString(1);
                                                %>

                                                <td><%=rs.getString(2)%>&nbsp;<a
                                                    href="FlowNamesDisplay.jsp"
                                                    onclick="return show_details(this.href,<%=SciptID%>)">Select
                                                        Hybrid Scenarios</a></td>

                                                <%
                                                    } else

                                                                {
                                                %>

                                                <td><%=rs.getString(2)%></td>
                                                <%
                                                    }
                                                %>



                                                <td><%=rs.getString(4)%></td>



                                                <td><%=rs.getString(8)%></td>

                                            </tr>

                                            <%
                                                }

                                                        //System.out.println("Inside ClientHome script name from hashMap "+ScriptEntity.HScenarios);

                                                        //System.out.println("row id is :"+rowid);

                                                        request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                                        //System.out.println("Inside ClientHome jsp  scriptName hashMap values HScenarios are  "+ScriptEntity.HScenarios);

                                                        //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                                        //var data={

                                                        //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                                    }
                                            %>
                                        
                                    </table>
											<center>
											<input type="submit" id="submit1" value="Execute"
                                            onclick="return validateForm();" style="margin-bottom:10px;">
											
											
											<input type="button" id="submit1" value="Schedule" onclick="displayScheduler()"
                                            style="margin-bottom:10px;">
                                            
                                      
											</center>
											
                                    <div class="scheduler-content" id="scheduler-content" style="display:none;">										
										<div class="scheduler-modal">
										  <div class="modal-content">
											<span class="close" onclick="closeModal()">&times;</span>
											<div class="modal-header">
											<h2>Time Scheduler</h2>
											</div>
                                    	<input type="hidden" name="rundrop" value="once" id="rundrop">
										<input type="hidden" name="isSchedule" value="notschduled" id="isSchedule">
                                        
                                            
										<form method="post" action="TimerSchedule1">
										<div class="control" style="margin-bottom:14px;">
										<label for="appt-time">Run:</label>
										<select id="rundropdown" onchange="dropdownchanged();">
											<option value="once">Once</option>
											<option value="everyday">Every Day</option>
										</select><br/>
										<label for="appt-time">DateTime:</label>
										<input type="text" name="scheduledate" value="" id="scheduledate">
											<input type="button" class="scheduleBtn" onclick="ScheduleScript();" value="Submit"/>	  
											  <!--<span class="hours">Office hours: 9AM to 6PM</span>-->

										</div>
											

										</form> 
										                                         
                                        </div>    
                                    </div>
									</div>
                                </form>

                            </div>





                            <button class="accordion" id="devorg_bg">SigmaERP</button>

                            <div class="panel">

                                <form action="executescript.jsp" method="post" id="form">

                                    <input type="hidden" id="checkedvalues" value="7:-1"
                                        name="checkedvalues" />

                                    <table border="0" title="Test Scripts" style="">



                                        <caption>Test Scripts</caption>

                                        <tr style="">

                                            <td class="head2" style="">SCRIPT ID</td>

                                            <td class="head2" style="">SELECT</td>
											
											<!--<td class="head2" style="">ORDER OF EXECUTION</td>-->

                                            <td class="head2" style="">SCRIPT NAME</td>

                                            <td class="head2" style="">SCENARIO</td>

                                            <td class="head2" style="">CATEGORY NAME</td>

                                        </tr>



                                        <%
                                            if (tab4.equals("Others"))

                                                {

                                                    //System.out.println("Inside Load Test_Type Tab :");  

                                                    //System.out.println("Inside Load Test_Type Tab :");

                                                    int count = 0;

                                                    String flowlink;

                                                    //     ScriptEntity.HScenarios.clear();

                                                    int countreference = 0;

                                                    int countScriptID = 0;

                                                    String CompleteTicketPurchase = null;

                                                    //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                                    FullQuery = "select * from script where script_status='active' and Test_type='Others' and script_category_name='SigmaERP' OR 'AlignToday'";

                                                    //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                                    ps = con.prepareStatement(FullQuery);

                                                    rs = ps.executeQuery();

                                                    while (rs.next())

                                                    {

                                                        countScriptID++;

                                                        countreference++;

                                                        String SciptID = rs.getString(1);

                                                        String ScriptName = rs.getString(2);

                                                        ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);

                                                        System.out.println("SciptID inside client home jsp is 1834:" + SciptID);
                                        %>

                                        <%
                                            
                                        %>





                                        <tr>

                                            <td><%=rs.getString(5)%></td>

                                            <!--  <td><%=countScriptID%></td>-->

                                            <td><input type="checkbox" id="remember"
                                                name="scriptlist" value="<%=SciptID%>" onclick="validate()"></td>
												
												<!--<td> <input type="number" name="order"></br</td>-->

                                            <%
                                                if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                            {

                                                                //flowlink=rs.getString(2);

                                                                //CompleteTicketPurchase=rs.getString(1);
                                            %>

                                            <td><%=rs.getString(2)%>&nbsp;<a
                                                href="FlowNamesDisplay.jsp"
                                                onclick="return show_details(this.href,<%=SciptID%>)">Select
                                                    Hybrid Scenarios</a></td>

                                            <%
                                                } else

                                                            {
                                            %>

                                            <td><%=rs.getString(2)%></td>
                                            <%
                                                }
                                            %>



                                            <td><%=rs.getString(4)%></td>



                                            <td><%=rs.getString(8)%></td>

                                        </tr>

                                        <%
                                            }

                                                    //System.out.println("Inside ClientHome script name from hashMap "+ScriptEntity.HScenarios);

                                                    //System.out.println("row id is :"+rowid);

                                                    request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                                    //System.out.println("Inside ClientHome jsp  scriptName hashMap values HScenarios are  "+ScriptEntity.HScenarios);

                                                    //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                                    //var data={

                                                    //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                                }

                                                //End OF MAIN TRY BLOCK
                                        %>

                                    </table>

                                    <center>

                                        <input type="submit" id="submit1" value="Execute"
                                            onclick="return validateForm();" style="">

                                    </center>

                                </form>

                            </div>

                        </div>

                    </div>



                    <div id="salesappexchange" style="display:none;">

                        <!-- <img src="Images/stos1.jpg"><br> -->

                        <!--<button class="accordion" id="accordion1"  onclick="sectioDisplay(this.value);" style="">SToS</button>-->

                        <div class="panel">



                            <button class="accordion" id="devorg_bg">Dev Org</button>

                            <div class="panel">

                                <form action="executescript.jsp" method="post" id="form">

                                    <input type="hidden" id="checkedvalues" value="7:-1"
                                        name="checkedvalues" />

                                    <table border="0" title="Test Scripts" style="">



                                        <caption>Test Scripts</caption>

                                        <tr style="">

                                            <td class="head2" style="">SL.NO</td>

                                            <td class="head2" style="">SELECT</td>
											
											<!--<td class="head2" style="">ORDER OF EXECUTION</td>-->

                                            <td class="head2" style="">SCRIPT NAME</td>

                                            <td class="head2" style="">SCENARIO</td>

                                            <td class="head2" style="">CATEGORY NAME</td>

                                        </tr>



                                        <%
                                            if (tab4.equals("Tronixes"))

                                                {

                                                    //System.out.println("Inside Load Test_Type Tab :");  

                                                    //System.out.println("Inside Load Test_Type Tab :");

                                                    int count = 0;

                                                    String flowlink;

                                                    //     ScriptEntity.HScenarios.clear();

                                                    int countreference = 0;

                                                    int countScriptID = 0;

                                                    String CompleteTicketPurchase = null;

                                                    //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                                    FullQuery = "select * from script where script_status='active' and Test_type='STOS'";

                                                    //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                                    ps = con.prepareStatement(FullQuery);

                                                    rs = ps.executeQuery();

                                                    while (rs.next())

                                                    {

                                                        countScriptID++;

                                                        countreference++;

                                                        String SciptID = rs.getString(1);

                                                        String ScriptName = rs.getString(2);

                                                        ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);

                                                        System.out.println("SciptID inside client home jsp is :" + SciptID);
                                        %>

                                        <%
                                            
                                        %>





                                        <tr>

                                            <!--  <td><%=rs.getString(5)%></td>-->

                                            <td><%=countScriptID%></td>

                                            <td><input type="checkbox" id="remember"
                                                name="scriptlist" value="<%=SciptID%>" onclick="validate()"></td>
												
												<!--<td> <input type="number" name="order"><br</td>-->

                                            <%
                                                if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                            {

                                                                //flowlink=rs.getString(2);

                                                                //CompleteTicketPurchase=rs.getString(1);
                                            %>

                                            <td><%=rs.getString(2)%>&nbsp;<a
                                                href="FlowNamesDisplay.jsp"
                                                onclick="return show_details(this.href,<%=SciptID%>)">Select
                                                    Hybrid Scenarios</a></td>

                                            <%
                                                } else

                                                            {
                                            %>

                                            <td><%=rs.getString(2)%></td>
                                            <%
                                                }
                                            %>



                                            <td><%=rs.getString(4)%></td>



                                            <td><%=rs.getString(8)%></td>

                                        </tr>

                                        <%
                                            }

                                                    //System.out.println("Inside ClientHome script name from hashMap "+ScriptEntity.HScenarios);

                                                    //System.out.println("row id is :"+rowid);

                                                    request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                                    //System.out.println("Inside ClientHome jsp  scriptName hashMap values HScenarios are  "+ScriptEntity.HScenarios);

                                                    //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                                    //var data={

                                                    //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                                }
                                        %>

                                    </table>

                                    <center>

                                        <input type="submit" id="submit1" value="Execute"
                                            onclick="return validateForm();" style="">

                                    </center>

                                </form>

                            </div>





                            <button class="accordion" id="devorg_bg">New Dev Org</button>

                            <div class="panel">



                                <form action="executescript.jsp" method="post" id="form">

                                    <input type="hidden" id="checkedvalues" value="7:-1"
                                        name="checkedvalues" />

                                    <table border="0" title="Test Scripts" style="">



                                        <caption>Test Scripts</caption>

                                        <tr style="">

                                            <td class="head2" style="">SL.NO</td>

                                            <td class="head2" style="">SELECT</td>
											
											<!--<td class="head2" style="">ORDER OF EXECUTION</td>-->

                                            <td class="head2" style="">SCRIPT NAME</td>

                                            <td class="head2" style="">SCENARIO</td>

                                            <td class="head2" style="">CATEGORY NAME</td>

                                        </tr>



                                        <%
                                            if (tab4.equals("Tronixes"))

                                                {

                                                    //System.out.println("Inside Load Test_Type Tab :");  

                                                    //System.out.println("Inside Load Test_Type Tab :");

                                                    int count = 0;

                                                    String flowlink;

                                                    //     ScriptEntity.HScenarios.clear();

                                                    int countreference = 0;

                                                    int countScriptID = 0;

                                                    String CompleteTicketPurchase = null;

                                                    //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                                    FullQuery = "select * from script where script_status='active' and Test_type='STOS'";

                                                    //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                                    ps = con.prepareStatement(FullQuery);

                                                    rs = ps.executeQuery();

                                                    while (rs.next())

                                                    {

                                                        countScriptID++;

                                                        countreference++;

                                                        String SciptID = rs.getString(1);

                                                        String ScriptName = rs.getString(2);

                                                        ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);

                                                        System.out.println("SciptID inside client home jsp is :" + SciptID);
                                        %>

                                        <%
                                            
                                        %>





                                        <tr>

                                            <!--  <td><%=rs.getString(5)%></td>-->

                                            <td><%=countScriptID%></td>

                                            <td><input type="checkbox" id="remember"
                                                name="scriptlist" value="<%=SciptID%>" onclick="validate()"></td>
												
												<!--<td> <input type="number" name="order"><br</td>-->

                                            <%
                                                if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                            {

                                                                //flowlink=rs.getString(2);

                                                                //CompleteTicketPurchase=rs.getString(1);
                                            %>

                                            <td><%=rs.getString(2)%>&nbsp;<a
                                                href="FlowNamesDisplay.jsp"
                                                onclick="return show_details(this.href,<%=SciptID%>)">Select
                                                    Hybrid Scenarios</a></td>

                                            <%
                                                } else

                                                            {
                                            %>

                                            <td><%=rs.getString(2)%></td>
                                            <%
                                                }
                                            %>



                                            <td><%=rs.getString(4)%></td>



                                            <td><%=rs.getString(8)%></td>

                                        </tr>

                                        <%
                                            }

                                                    //System.out.println("Inside ClientHome script name from hashMap "+ScriptEntity.HScenarios);

                                                    //System.out.println("row id is :"+rowid);

                                                    request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                                    //System.out.println("Inside ClientHome jsp  scriptName hashMap values HScenarios are  "+ScriptEntity.HScenarios);

                                                    //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                                    //var data={

                                                    //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                                }
                                        %>

                                    </table>

                                    <center>

                                        <input type="submit" id="submit1" value="Execute"
                                            onclick="return validateForm();" style="">

                                    </center>

                                </form>



                            </div>





                            <button class="accordion" id="devorg_bg">Demo Org</button>

                            <div class="panel">



                                <form action="executescript.jsp" method="post" id="form">

                                    <input type="hidden" id="checkedvalues" value="7:-1"
                                        name="checkedvalues" />

                                    <table border="0" title="Test Scripts" style="">



                                        <caption>Test Scripts</caption>

                                        <tr style="">

                                            <td class="head2" style="">SL.NO</td>

                                            <td class="head2" style="">SELECT</td>
											
											<!--<td class="head2" style="">ORDER OF EXECUTION</td>-->

                                            <td class="head2" style="">SCRIPT NAME</td>

                                            <td class="head2" style="">SCENARIO</td>

                                            <td class="head2" style="">CATEGORY NAME</td>

                                        </tr>



                                        <%
                                            if (tab4.equals("Tronixes"))

                                                {

                                                    //System.out.println("Inside Load Test_Type Tab :");  

                                                    //System.out.println("Inside Load Test_Type Tab :");

                                                    int count = 0;

                                                    String flowlink;

                                                    //     ScriptEntity.HScenarios.clear();

                                                    int countreference = 0;

                                                    int countScriptID = 0;

                                                    String CompleteTicketPurchase = null;

                                                    //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                                    FullQuery = "select * from script where script_status='active' and Test_type='STOS'";

                                                    //System.out.println("Full Query Inside Load Test_Type tab2:"+FullQuery);

                                                    ps = con.prepareStatement(FullQuery);

                                                    rs = ps.executeQuery();

                                                    while (rs.next())

                                                    {

                                                        countScriptID++;

                                                        countreference++;

                                                        String SciptID = rs.getString(1);

                                                        String ScriptName = rs.getString(2);

                                                        ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);

                                                        System.out.println("SciptID inside client home jsp is :" + SciptID);
                                        %>

                                        <%
                                            
                                        %>





                                        <tr>

                                            <!--  <td><%=rs.getString(5)%></td>-->

                                            <td><%=countScriptID%></td>

                                            <td><input type="checkbox" id="remember"
                                                name="scriptlist" value="<%=SciptID%>" onclick="validate()"></td>
												
												<!--<td> <input type="number" name="order"><br</td>-->

                                            <%
                                                if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                                            {

                                                                //flowlink=rs.getString(2);

                                                                //CompleteTicketPurchase=rs.getString(1);
                                            %>

                                            <td><%=rs.getString(2)%>&nbsp;<a
                                                href="FlowNamesDisplay.jsp"
                                                onclick="return show_details(this.href,<%=SciptID%>)">Select
                                                    Hybrid Scenarios</a></td>

                                            <%
                                                } else

                                                            {
                                            %>

                                            <td><%=rs.getString(2)%></td>
                                            <%
                                                }
                                            %>



                                            <td><%=rs.getString(4)%></td>



                                            <td><%=rs.getString(8)%></td>

                                        </tr>

                                        <%
                                            }

                                                    //System.out.println("Inside ClientHome script name from hashMap "+ScriptEntity.HScenarios);

                                                    //System.out.println("row id is :"+rowid);

                                                    request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                                    //System.out.println("Inside ClientHome jsp  scriptName hashMap values HScenarios are  "+ScriptEntity.HScenarios);

                                                    //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                                    //var data={

                                                    //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                                                }

                                            } //End OF MAIN TRY BLOCK

                                            catch (NullPointerException ex)

                                            {

                                                ex.printStackTrace();

                                            }

                                            /*  finally
                                            
                                                   {
                                            
                                                     rs.close();
                                            
                                                     ps.close();
                                            
                                                     Database.closeConnection(con);
                                            
                                                   }
                                            
                                            */
                                        %>

                                    </table>

                                    <center>

                                        <input type="submit" id="submit1" value="Execute"
                                            onclick="return validateForm();" style="">

                                    </center>

                                </form>





                            </div>

                        </div>





















                    </div>





                    <!-- Tab for Loading Salesforce App Exchange  Test Scripts from DataBase! Ends Here!  -->

                </div>

            </div>





        </div>















        <div id="tab-5" class="tab-content ">

            <!-- START FOR TAB 2 -->

            <form action="executescript.jsp" method="post" style="" id="form">

                <input type="hidden" id="checkedvalues" value="7:-1"
                    name="checkedvalues" />

                <table border="0" title="Test Scripts" style="">

                    <caption>Test Scripts</caption>

                    <tr style="">

                        <td class="head2" style="">SL.NO</td>

                        <td class="head2" style="">SELECT</td>
						
						<!--<td class="head2" style="">ORDER OF EXECUTION</td>-->

                        <td class="head2" style="">SCRIPT NAME</td>

                        <td class="head2" style="">SCENARIO</td>

                        <td class="head2" style="">CATEGORY NAME</td>

                    </tr>

                    <div>

                        <%
                            if (tab5.equals("Load"))

                            {

                                //System.out.println("Inside AlignToday Test_Type Tab :");   

                                //System.out.println("Inside AlignToday Test_Type Tab :");

                                int count = 0;

                                String flowlink;

                                //ScriptEntity.HScenarios.clear();

                                int countreference = 0;

                                int countScriptID = 0;

                                String CompleteTicketPurchase = null;

                                //String FullQuery="select * from script where script_status='active'"+AdditionalWhereClause;

                                String FullQuery = "select * from script where script_status='active' and Test_type='Load'";

                                //System.out.println("Full Query Inside AlignToday Test_Type tab2:"+FullQuery);

                                ps = con.prepareStatement(FullQuery);

                                rs = ps.executeQuery();

                                while (rs.next())

                                {

                                    countScriptID++;

                                    countreference++;

                                    String SciptID = rs.getString(1);

                                    String ScriptName = rs.getString(2);

                                    ScriptEntity.HScenarios.put(Integer.parseInt(SciptID), ScriptName);
                        %>

                        <%
                            
                        %>

                        <tr style="";></tr>

                        <!--  <td><%=rs.getString(5)%></td>-->

                        <td><%=countScriptID%></td>

                        <td><input type="checkbox" id="remember" name="scriptlist"
                            value="<%=SciptID%>" onclick="validate()"></td>
							
							<!--<td> <input type="number" name="order"><br</td>-->

                        <%
                            if (rs.getString(2).equalsIgnoreCase("Complete Ticket Purchase(Hybrid Flow)"))

                                    {

                                        //flowlink=rs.getString(2);

                                        //CompleteTicketPurchase=rs.getString(1);
                        %>

                        <td> <%=rs.getString(2)%>&nbsp;<a href="FlowNamesDisplay.jsp"
                            onclick="return show_details(this.href,<%=SciptID%>)">Select
                                Hybrid Scenarios</a></td>

                        <%
                            } else

                                    {
                        %>

                        <td><%=rs.getString(2)%></td>
                        <%
                            }
                        %>



                        <td><%=rs.getString(4)%></td>



                        <td><%=rs.getString(8)%></td>

                        </tr>

                        <%
                            }

                                //System.out.println("script name from hashMap "+ScriptEntity.HScenarios);

                                //System.out.println("row id is :"+rowid);

                                request.setAttribute("HScenarios", ScriptEntity.HScenarios);

                                //System.out.println("Script Id of Complete Ticket Purchase :"+CompleteTicketPurchase);

                                //var data={

                                //     '"+CompleteTicketPurchase+"' : '"+checkedval+"'};

                            }

                            /* else
                            
                            {
                            
                                  System.out.println("Tab 3 display code to be inserted");
                            
                            }
                            
                            */
                        %>
                    
                </table>

                <center>
                    <input type="submit" id="submit1" value="Execute"
                        onclick="return validateForm();" style="">
                </center>

            </form>

        </div>

    </div>



    <div id="tab-6" class="tab-content">

        <div class="form" style="margin-top: 0%;">

            <p>This is Security test content in Progress</p>

        </div>

    </div>



   <!-- <div class="footer" style="">
        <p style="">Mindlinks Solution &copy 2018</p>    
    </div> -->

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
	document.getElementById("isSchedule").value = 'scheduled';
	document.getElementById("cohform").submit();
}

function dropdownchanged()
{
	var e = document.getElementById("rundropdown");
	var strUser = e.options[e.selectedIndex].value;
	document.getElementById("rundrop").value = strUser;
}
$(function() {
	
	$('*[name=scheduledate]').appendDtpicker();
	
	
  });
</script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
</body>

</html>