<%@page import="com.pack.Database"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.pack.*" %>


<!DOCTYPE html>
<html>
<head>

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

<div class="container">
<article class="slds-card1">
 <div class="slds-card__header slds-grid">
   <header class="slds-media slds-media_center slds-has-flexi-truncate">

     <div class="slds-media__body">
       <h2 class="slds-card__header-title">
         <a href="javascript:void(0);" class="slds-card__header-link slds-truncate" title="Accounts">
           <span><i class="fa fa-file-text" aria-hidden="true"></i> PROJECT NAMES</span>
         </a>
       </h2>
     </div>
     <div class="slds-no-flex" style="display:inline-flex;">
   <form action="ActionCreateProject.jsp" >
   <button class="slds-button slds-button_neutral slds-m-right_x-small" id="Upload" type="submit"><b>New</b></button>
   </form>
		<!-- <button class="slds-button slds-button_brand slds-m-right_x-small" id="Upload" onclick="uploadPopup();">Upload Script</button> -->
<!--</form>
  <form action="ListOfScripts" >
   <input  type="hidden" id="checklist" type="text" name="checklist" > 
   <input  type="hidden" id="pnameList" type="text" name="pnameList" >

<button class="slds-button slds-button_brand" id="test" onclick="Validate()" type="submit" >Ok</button>

</form>-->
    </div>
  </header>
</div>
<div class="slds-card__body slds-card__body_inner1">
<table class="custom-table slds-table slds-table_cell-buffer slds-table_bordered slds-table_fixed-layout">
<thead>
  <tr class="slds-line-height_reset">
  <!-- <th class="slds-text-title_caps" scope="col">
     <div class="slds-truncate" title="Select">Select</div>
   </th> -->
    <th class="slds-text-title_caps" scope="col">
      <div class="slds-truncate" title="Project Names">Project Lists</div>
    </th>
    
    <th class="slds-text-title_caps" scope="col">
      <div class="slds-truncate" title="Project Names">Project Description</div>
    </th>
    <th class="slds-text-title_caps" scope="col">
      <div class="slds-truncate" title="Project Names">Action</div>
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
//String sql ="select  * from script GROUP BY script_category_name";
String sql ="select  * from project GROUP BY ProjectName";
resultSet = statement.executeQuery(sql);

while(resultSet.next()){
%>
<tr >
<!--  <td> <input type="checkbox" name=<%=resultSet.getString("ProjectId") %> id=<%=resultSet.getString("ProjectName") %> value="execute"  > </td>-->

<td id="fn"><a href="clienthome.jsp?id=<%=resultSet.getString("ProjectId")%>&name=<%=resultSet.getString("ProjectName") %>" value="execute"/><%=resultSet.getString("ProjectName") %></td>  
<td><%=resultSet.getString("Description") %></td>
<%-- <td><%=resultSet.getString("script_scenario") %></td>
<td> <%=resultSet.getString("script_status") %></td> --%>
<th> 
   <form action="ActionUpdateProject.jsp" >
   <a  class="slds-text-title_caps update-btn" href="ActionUpdateProject.jsp?id=<%=resultSet.getString("ProjectId")%>&name=<%=resultSet.getString("ProjectName") %>" value="execute">Update</a>
    
    </form>
     </th>

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
  
  </div>

</article>
</div>



<!-- code to validate whether the check box is checked -->

<div id="uploadPopup" style="display:none;">
<section role="dialog" tabindex="-1" aria-labelledby="modal-heading-01" aria-modal="true" aria-describedby="modal-content-id-1" class="slds-modal slds-fade-in-open">
  <div class="slds-modal__container">
   
   <form action="ActionCreateProject.jsp" method="post"
                      enctype="multipart/form-data">
    <header class="slds-modal__header">       
      <h2 id="modal-heading-01" class="slds-text-heading_medium slds-hyphenate">New Project</h2>
    </header>
    <div class="slds-modal__content slds-p-around_medium" id="modal-content-id-1">
      <div class="slds-form slds-form_horizontal">
  <div class="slds-form-element">
	<label class="slds-form-element__label" for="input-id-01">Project Name</label>
	<div class="slds-form-element__control">
	  <input type="text" id="ProjectName" name="ProjectName"/>
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
  </div>
  
 						
  
  <div class="slds-form slds-form_horizontal">
  <div class="slds-form-element">
	<label class="slds-form-element__label" for="input-id-01">Start Date</label>
	<div class="slds-form-element__control">
	  <input type="text" id="StartDate" name="StartDate"/>
	</div>
  </div>
  </div>
  
  
  <div class="slds-form slds-form_horizontal">
  <div class="slds-form-element">
	<label class="slds-form-element__label" for="input-id-01">End Date</label>
	<div class="slds-form-element__control">
	  <input type="text" id="EndDate" name="EndDate"/>
	</div>
  </div>
  </div>


    </div>
    <input type="hidden" id="filename" name="filename" value="">
 
    <footer class="slds-modal__footer">
      <input class="slds-button slds-button_brand" type="submit" id="submit1" value="Create"/>
      <button class="slds-button slds-button_neutral" onclick="closePopup()">Cancel</button>
     
     <!-- <button class="slds-button slds-button_brand" onclick="SaveUpload()" type="submit">Save</button> -->
        
      </footer> 
      </form>
    </div>
  </section>
  <div class="slds-backdrop slds-backdrop_open"></div>
</div>
 

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
 <script>
 function New()
 {
	 //alert('new ');
	 window.location.href = "ActionUpdateProject.jsp";
 }
  
  function closePopup()
  {
	  //alert('closePopup');
	  location.reload();
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
<!--<script type="text/javascript">

var chklist = [];
$(document).ready(function(){
	
	
	
	$('#test').click(function(){
		chklist = [];
		pnameList = [];
		$('#checklist').val(chklist);
		
		$('table [type="checkbox"]').each(function(i, chk) {
			if (chk.checked) 
			{
				alert("Checked!"+ i+"::"+ JSON.stringify(chk.name));
				chklist.push(chk.name);
				pnameList.push(chk.id);
			}
			
		}
		);
		
		
		//alert("checklist:"+chklist);
		$('#checklist').val(chklist);
		$('#pnameList').val(pnameList);
		if(chklist==''||chklist==null)
			{
			alert('Please select atleast one Project !');
			return false;
			}
		//alert("Value checklist: " + $('#checklist').val());
		
       });
   });

</script>-->

<div id="uploadPopup" style="display:none;">
<section role="dialog" tabindex="-1" aria-labelledby="modal-heading-01" aria-modal="true" aria-describedby="modal-content-id-1" class="slds-modal slds-fade-in-open">
  <div class="slds-modal__container">
   
   <form action="ActionUpdateProject.jsp" method="post"
                      enctype="multipart/form-data">
    <header class="slds-modal__header">       
      <h2 id="modal-heading-01" class="slds-text-heading_medium slds-hyphenate">Update Project</h2>
    </header>
    <div class="slds-modal__content slds-p-around_medium" id="modal-content-id-1">
      <div class="slds-form slds-form_horizontal">
  <div class="slds-form-element">
	<label class="slds-form-element__label" for="input-id-01">Project Name</label>
	<div class="slds-form-element__control">
	  <input type="text" id="ProjectName" name="ProjectName"/>
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
    <input type="hidden" id="filename" name="filename" value="">
 
    <footer class="slds-modal__footer">
      <input class="slds-button slds-button_brand" type="submit" id="submit1" value="Update"/>
      <button class="slds-button slds-button_neutral" onclick="closePopup()">Cancel</button>
     
     <!-- <button class="slds-button slds-button_brand" onclick="SaveUpload()" type="submit">Save</button> -->
        
      </footer> 
      </form>
    </div>
  </section>
  <div class="slds-backdrop slds-backdrop_open"></div>
</div>


<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
 <script>
  
  function closePopup()
  {
	  //alert('closePopup');
	  location.reload();
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



<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
 


</body>
</html>
