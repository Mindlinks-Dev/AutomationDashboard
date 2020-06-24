<%@page import="com.pack.Database"%>
<%@page import="com.pack.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<!-- Html  code--->
<!DOCTYPE html>
<html>
<head>
<style>
th{font-size:14px !important;font-weight:bold !important;}
h2{font-size:20px !important;}
#test{font-weight:bold;}
input{border: 1px solid #dddbda;padding: 0 1rem 0 .75rem;  line-height: 1.875rem !important;
    border-radius: 4px;}
</style>
<link type="text/css" rel="stylesheet" href="Static/salesforce-lightning-design-system.min.css" /> 
<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
<script src="js/jquery.min.js"></script>
<script>
$(function() {	 
	$('*[name=StartDate]').appendDtpicker({
		"current": null,	
		"dateOnly": true,
		 "timeOnly": false
	});
  });
	
	$(function() {		
		$('*[name=EndDate]').appendDtpicker({
			"current": null,
			"dateOnly": true,
			 "timeOnly": false	
	  });
	});
</script>
</head>
<body>
<jsp:include page="header.jsp" />





<!-- code to validate whether the check box is checked -->

<div >
 <section role="dialog" tabindex="-1" aria-labelledby="modal-heading-01" aria-modal="true" aria-describedby="modal-content-id-1" class="slds-modal slds-fade-in-open">
   <div class="slds-modal__container">
     <%
String proId = request.getParameter("id");
System.out.println("proId in clienthome111="+proId);
session.setAttribute("ProjectId", proId);



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
	
%>

<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
        
<form action="UpdateProject" method="post" >
      <header class="slds-modal__header">       
       <h2 id="modal-heading-01" class="slds-text-heading_medium slds-hyphenate">Update Project</h2>
     </header>
     <div class="slds-modal__content slds-p-around_medium" id="modal-content-id-1">
       <div class="slds-form slds-form_horizontal">
	  <div class="slds-form-element">
		<label class="slds-form-element__label" for="input-id-01">Project Name</label>
		<div class="slds-form-element__control">
		  <input type="text" readonly id="ProjectName" name="ProjectName" value="<%=request.getParameter("name") %>"/>
		 
		</div>
	  </div>
	  </div>
	  
	  <div class="slds-form slds-form_horizontal">
	  <div class="slds-form-element">
		<label class="slds-form-element__label" for="input-id-01">Project Description</label>
		<div class="slds-form-element__control">
		  <input type="text" id="Description" name="Description"/>
		</div>
	  </div>
	  </div>
	  
	  <div class="slds-form slds-form_horizontal">
	  <div class="slds-form-element">
		<label class="slds-form-element__label" for="input-id-01">Status</label>
		<div class="slds-form-element__control">
		  <input type="text" id="Status" name="Status"/>
		</div>
	  </div>
	  </div>
	  
	 						
	  
	  <div class="slds-form slds-form_horizontal">
	  <div class="slds-form-element">
		<label class="slds-form-element__label" for="input-id-01">Start Date</label>
		<div class="slds-form-element__control">
		  <input type="text" id="StartDate" name="StartDate"/>
		</div>
	  </div>
	  </div>
	

     </div>
     <!--input type="hidden" id="filename" name="filename" value=""/-->
  
     <footer class="slds-modal__footer">
       <!--  <input class="slds-button slds-button_brand" type="submit" id="submit1" value="Create" onclick="CreateProject()"/>-->
       <button class="slds-button slds-button_brand" onclick="Update()">Update</button>
        <input type="button" onclick="closePopup()" value="close"/>
       <!-- <button class="slds-button slds-button_brand" onclick="SaveUpload()" type="submit">Save</button> -->
        
      </footer>  
      </form>

    </div>
  </section>
  <div class="slds-backdrop slds-backdrop_open"></div>
</div>
 
<jsp:include page="footer.jsp" />

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
 <script>
 
  function Update(){
	  var ProjectName=document.getElementById("ProjectName").value;
		var Description=document.getElementById("Description").value;
		var Status=document.getElementById("Status").value;
		var Type=document.getElementById("Type").value;
		var StartDate=document.getElementById("StartDate").value;
		//var EndDate=document.getElementById("EndDate").value;
  }
  function closePopup()
  {
	  //alert('closePopup');
	  history.back();
	  return false;
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
    function GetFileSizeNameAndType()

    {

      var fi = document.getElementById('file');

      var totalFileSize = 0;

      if (fi.files.length > 0)

      {

        for (var i = 0; i <= fi.files.length - 1; i++)

        { 
          
		  document.getElementById('myInput1').value=document.getElementById('file').innerHTML +     fi.files.item(i).name;
			
        }

      }

    }

  </script>
<script type="text/javascript">

var chklist = [];
$(document).ready(function(){
	
	
	
	$('#test').click(function(){
		chklist = [];
		
		$('#checklist').val(chklist);
		
		$('table [type="checkbox"]').each(function(i, chk) {
			if (chk.checked) 
			{
				//alert("Checked!"+ i+"::"+ JSON.stringify(chk.name));
				chklist.push(i);
			}
			
		}
		);
		
		
		//alert("checklist:"+chklist);
		$('#checklist').val(chklist);
		
		if(chklist==''||chklist==null)
			{
			alert('Please select atleast one Project !');
			return false;
			}
		//alert("Value checklist: " + $('#checklist').val());
		
       });
   });

</script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
 


</body>
</html>