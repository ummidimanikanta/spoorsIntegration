<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<script type="text/javascript">

var disableAllInputs = ${disableAllInputs};
$(document).ready(function(){
	if(disableAllInputs){
		$(".disableAllInputs :input").prop("disabled", true);
		$("[name='save']").hide();
		$("[name='savenew']").hide();
		$("#viewOrAdd").text("View");
		}	
});	
	
function loadTriggerSource()
{           
    $('#triggerId').empty().append('<option>select</option>');
    var type = $('#triggerType').val();
    var companyId = $('#companyId').val();
    console.log("type");
    $.ajax({
            url: 'triggerSources/'+companyId+'/'+type,
            dataType: 'json',
            type: 'POST',
            success: function(response) {              
              $.each(response, function(key,obj) {
                  $("#triggerId").append("<option value='"+key+"'>"+obj+"</option>");
               });
            },
            error: function(x, e) {
				console.log("Error in returning the data"+e+"::"+x);
            }

        });
}

function formSubmit(saveThenNewAgain)
{
	var id = $("#id").val();
	console.log(id);
	formUrl = "add?isSaveThenNewAgain="+saveThenNewAgain;
	if(id != null && id != 'undefined' && id != ''){
		formUrl = "update?isSaveThenNewAgain="+saveThenNewAgain;
	}

	$("#configForm").attr("action", formUrl);
	$("#configForm").submit(); // Submit the form
	
}


</script>  
<style>

.form-horizontal .control-label{
		text-align: left !important;
	}
</style>  
<div class="container-fluid" style="height: 100%; overflow: auto;">
<form name="configForm" id="configForm" method="post">
<input type="hidden" name="id" id="id" value="${configInfo.id}" />
<c:choose>
  <c:when test="${empty configInfo.companyId}">
    <input type="hidden" name="companyId" id="companyId" value="1374" />
  </c:when>
  <c:otherwise>
    <input type="hidden" name="companyId" id="companyId" value="${configInfo.companyId}" />
  </c:otherwise>
</c:choose>
<div class="row"  style="border-bottom:1px solid rgb(201, 194, 194);padding-bottom: 2px;">
	<div class="font-header" style="float: left;"  class="title" id="title-content">
					<span id="viewOrAdd">Add</span> Integration Configuration				
	</div>
	<div style="float: right;">
			<input type="button" class="btn btn-sm btn-spoors-main" onclick="formSubmit(false)" id="save" name="save" value="Save"/>&nbsp;&nbsp;
			<input type="button" class="btn btn-sm btn-spoors-main" onclick="formSubmit(true)" id="savenew" name="savenew" value="Save & New"/>&nbsp;&nbsp;
	</div>
</div>

<div class="container form-horizontal disableAllInputs" style="margin-bottom: 50px;margin-top: 36px;background-color:white;">
	<br/>
  <div class="form-group" style="
    background-color:  #254f65;
    color: rgb(202, 236, 255);margin-left:1px;margin-right:1px;font-weight:bold;">
  	<label for="inputEmail3" class="col-sm-2 control-label"></label>
    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align: center !important;">Field</label>
    <div class="col-sm-4" style="text-align: center;">
      <label for="inputEmail3" class="control-label" >Data</label>
    </div>
     <div class="col-sm-4">
      <label for="inputEmail3" class="control-label"></label>
    </div>
  </div>

  <div class="form-group">
  	<label for="name" class="col-sm-2 control-label"></label>
    <label for="name" class="col-sm-2 control-label">Name</label>
    <div class="col-sm-4">
      <input type="text" class="form-control" id="name" name="name" value="<c:out value="${configInfo.name}" />" placeholder="Name">
    </div>
  </div>
  
  <%-- <div class="form-group">
  	<label for="companyId" class="col-sm-2 control-label"></label>
    <label for="companyId" class="col-sm-2 control-label">Company</label>
    <div class="col-sm-4">
		<select id ="companyId" name="companyId" class="form-control">
  			<option value="-1">-Select-</option>
  			<c:if test="${companyMap != null}">
			 	<c:forEach items= "${companyMap}" var="company">
					<c:choose>
						<c:when test="${company.key == configInfo.companyId }"> 
							<option value="${company.key}" selected>${company.value}</option></c:when> 
						<c:otherwise>
							<option value="${company.key}">${company.value}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>	      		
		</select>
    </div>
  </div> --%>

   <div class="form-group">
   	<label for="triggerType" class="col-sm-2 control-label"></label>
    <label for="triggerType" class="col-sm-2 control-label">Trigger Type</label>
    <div class="col-sm-4">
    <select id ="triggerType" name="triggerType" class="form-control" onchange="loadTriggerSource();">
		<option value="-1">-Select-</option>
		<option value="1" <c:if test="${configInfo.triggerType == 1}"> selected </c:if>>Forms</option>
		<option value="2" <c:if test="${configInfo.triggerType == 2}"> selected </c:if>>Works</option>
	</select>
    </div>
  </div>
 

  <div class="form-group">
  	<label for="triggerId" class="col-sm-2 control-label"></label>
    <label for="triggerId" class="col-sm-2 control-label">Trigger Source</label>
    <div class="col-sm-4">
    	<select id ="triggerId" name="triggerId" class="form-control">
     		<option value="-1">-Select-</option>
     		<c:if test="${triggerSourceMap != null}">
		 	<c:forEach items= "${triggerSourceMap}" var="triggerSource">
				<c:choose><c:when test="${triggerSource.key == configInfo.triggerId }"> 
				<option value="${triggerSource.key}" selected>${triggerSource.value}</option></c:when> 
				<c:otherwise>
				<option value="${triggerSource.key}">${triggerSource.value}</option>
			</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>	      		
	</select>
    </div>
  </div>
 

		 <div class="form-group">
		 	<label for="contentType" class="col-sm-2 control-label"></label>
		   <label for="contentType" class="col-sm-2 control-label">Request Content Type</label>
		   <div class="col-sm-4">
		   		 <select id ="contentType" name="contentType" class="form-control">
					<option value="-1">-Select-</option>
					<option value="1" <c:if test="${configInfo.contentType == 1}"> selected </c:if>>JSON</option>
					<option value="2" <c:if test="${configInfo.contentType == 2}"> selected </c:if>>XML</option>
				</select>
		   </div>
		 </div>
 

	  <div class="form-group">
	  	<label for="formDataPushType" class="col-sm-2 control-label"></label>
	    <label for="formDataPushType" class="col-sm-2 control-label">Form Data Push Type</label>
	    <div class="col-sm-4">
	     <select id ="formDataPushType" name="formDataPushType" class="form-control">
			<option value="-1">-Select-</option>
			<option value="1" <c:if test="${configInfo.formDataPushType == 1}"> selected </c:if>>Detailed</option>
			<option value="2" <c:if test="${configInfo.formDataPushType == 2}"> selected </c:if>>Simplified</option>
		</select>
	    </div>
	  </div>
 

		<div class="form-group">
			<label for="approveFormData" class="col-sm-2 control-label"></label>
		  <label for="approveFormData" class="col-sm-2 control-label">Push Form Data on Approval</label>
		  <div class="col-sm-4">
		   <select id ="approveFormData" name="approveFormData" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.approveFormData == 1}"> selected </c:if>>Approved</option>
				<option value="0" <c:if test="${configInfo.approveFormData == 0}"> selected </c:if>>Rejected</option>
				</select>
		   </div>
		 </div>

	  <div class="form-group">
	  	<label for="invokeType" class="col-sm-2 control-label"></label>
	    <label for="invokeType" class="col-sm-2 control-label">Invoke Type</label>
	    <div class="col-sm-4">
	    	<select id ="invokeType" name="invokeType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.invokeType == 1}"> selected </c:if>>API</option>
				<option value="2" <c:if test="${configInfo.invokeType == 2}"> selected </c:if>>DB</option>
				<option value="3" <c:if test="${configInfo.invokeType == 3}"> selected </c:if>>FILE</option>
				<option value="4" <c:if test="${configInfo.invokeType == 4}"> selected </c:if>>SAP</option>
			</select>
	    </div>
	  </div>
	 

	  <div class="form-group">
	  	<label for="acceptType" class="col-sm-2 control-label"></label>
	    <label for="acceptType" class="col-sm-2 control-label">Response Content Type</label>
	    <div class="col-sm-4">
	     <select id ="acceptType" name="acceptType" class="form-control">
			<option value="-1">-Select-</option>
			<option value="1" <c:if test="${configInfo.contentType == 1}"> selected </c:if>>JSON</option>
			<option value="2" <c:if test="${configInfo.contentType == 2}"> selected </c:if>>XML</option>
		</select>
	    </div>
	  </div>

	   <div class="form-group">
	   <label for="submitUrl" class="col-sm-2 control-label"></label>
	    <label for="submitUrl" class="col-sm-2 control-label">Submission URL</label>
	    <div class="col-sm-4">
	      <input type="text" class='form-control' name="submitUrl" value="<c:out value="${configInfo.submitUrl}" />" />
	    </div>
	  </div>
	 

	  <div class="form-group">
	  	<label for="urlForUpdate" class="col-sm-2 control-label"></label>
	    <label for="urlForUpdate" class="col-sm-2 control-label">Modification URL</label>
	    <div class="col-sm-4">
	      <input type="text" class='form-control' name="urlForUpdate" value="<c:out value="${configInfo.urlForUpdate}" />" />
	    </div>
	  </div>


	  <div class="form-group">
	  	<label for="ignoreSSL" class="col-sm-2 control-label"></label>
	    <label for="ignoreSSL" class="col-sm-2 control-label">Ignore SSL	</label>
	    <div class="col-sm-4">
	     <label class="radio-inline">
		  <input type="radio" class="radio-inline" name="ignoreSSL" value="1"
		  <c:if test="${configInfo.ignoreSSL}"> checked</c:if> />True &nbsp;&nbsp;&nbsp;&nbsp; 
		     
		</label>
		<label class="radio-inline">
		  <input type="radio" class="radio-inline" name="ignoreSSL" value="0" 
		      <c:if test="${!configInfo.ignoreSSL}"> checked</c:if> />False
		</label>
	    </div>
	  </div>
  
	  <div class="form-group">
	  	<label for="authenticationType" class="col-sm-2 control-label"></label>
	    <label for="authenticationType" class="col-sm-2 control-label">Authentication Type</label>
	    <div class="col-sm-4">
	   		<select id ="authenticationType" name="authenticationType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.authenticationType == 1}"> selected </c:if>>Basic</option>
				<option value="2" <c:if test="${configInfo.authenticationType == 2}"> selected </c:if>>JWT</option>
			</select>
	    </div>
	  </div>

	   <div class="form-group">
	   	<label for="username" class="col-sm-2 control-label"></label>
	    <label for="username" class="col-sm-2 control-label">Username</label>
	    <div class="col-sm-4">
	      <input type="text" class='form-control' name="username" value="<c:out value="${configInfo.username}" />" />
	    </div>
	  </div>
 

	  <div class="form-group">
	  	<label for="password" class="col-sm-2 control-label"></label>
	    <label for="password" class="col-sm-2 control-label">Password</label>
	    <div class="col-sm-4">
	     <input type="password" class='form-control' name="password" value="<c:out value="${configInfo.password}" />" />
	    </div>
	  </div>

	   <div class="form-group">
	   	<label for="escalationType" class="col-sm-2 control-label"></label>
	    <label for="escalationType" class="col-sm-2 control-label">Escalation Type</label>
	    <div class="col-sm-4">
	    	<select id ="escalationType" name="escalationType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.escalationType == 1}"> selected </c:if>>Per Failed</option>
				<option value="2" <c:if test="${configInfo.escalationType == 2}"> selected </c:if>>% Failed</option>
				<option value="3" <c:if test="${configInfo.escalationType == 3}"> selected </c:if>>Duration Failed</option>
			</select>
	    </div>
	  </div>

	  <div class="form-group">
	  	<label for="isEnabled" class="col-sm-2 control-label"></label>
	    <label for="isEnabled" class="col-sm-2 control-label">Enable</label>
	    <div class="col-sm-4">
	     <label class="radio-inline">
		     <input type="radio" class="radio-inline" name="isEnabled" value="true" 
		      <c:if test="${configInfo.isEnabled}"> checked</c:if>/>True 
		   
		</label>
		<label class="radio-inline">
		  <input type="radio" class="radio-inline" name="isEnabled" value="false" 
		      <c:if test="${!configInfo.isEnabled}"> checked</c:if>/>False
		</label>
	    </div>
	  </div>


	  <div class="form-group">
	  	<label for="retryCount" class="col-sm-2 control-label"></label>
	    <label for="retryCount" class="col-sm-2 control-label">Retry Count	</label>
	    <div class="col-sm-4">
	      <input type="text" class='form-control' name="retryCount" value="<c:out value="${configInfo.retryCount}" />" />
	    </div>
	  </div>

	 <div class="form-group">
	 	<label for="successKey" class="col-sm-2 control-label"></label>
	    <label for="successKey" class="col-sm-2 control-label">Success Key</label>
	    <div class="col-sm-4">
	    <select id ="successKey" name="successKey" class="form-control">
			<option value="-1">-Select-</option>
			<option value="1" <c:if test="${configInfo.successKey == 1}"> selected </c:if>>Present</option>
			<option value="2" <c:if test="${configInfo.successKey == 2}"> selected </c:if>>Does Not Present</option>
			<option value="3" <c:if test="${configInfo.successKey == 3}"> selected </c:if>>Match</option>
			<option value="4" <c:if test="${configInfo.successKey == 4}"> selected </c:if>>Does Not Match</option>
		</select>
	    </div>
	  </div>

	  <div class="form-group">
	  	<label for="successKeyValue" class="col-sm-2 control-label"></label>
	    <label for="successKeyValue" class="col-sm-2 control-label">Success Key Value</label>
	    <div class="col-sm-4">
	      <input type="text" class='form-control' name="successKeyValue" value="<c:out value="${configInfo.successKeyValue}" />" />
	    </div>
	  </div>

	  <div class="form-group">
		<label for="restrictUpdateUrl" class="col-sm-2 control-label"></label>
	    <label for="restrictUpdateUrl" class="col-sm-2 control-label">Avoid Modification</label>
	    <div class="col-sm-4">
	     <label class="radio-inline">
		   <input type="radio" class="radio-inline" name="restrictUpdateUrl" value="true" 
	      <c:if test="${configInfo.restrictUpdateUrl}"> checked</c:if> />Send 
		</label>
		<label class="radio-inline">
		  <input type="radio" class="radio-inline" name="restrictUpdateUrl" value="false" 
	      <c:if test="${!configInfo.restrictUpdateUrl}"> checked</c:if> />Don't Send
		</label>
	    </div>
     </div>
	<br/>
</div>
<div class="row"  style="border-top:1px solid rgb(201, 194, 194);padding-top: 2px;">
	<div style="float: right;">	
			<input type="button" class="btn btn-sm btn-spoors-main" onclick="formSubmit(false)" id="save" name="save" value="Save"/>&nbsp;&nbsp;
			<input type="button" class="btn btn-sm btn-spoors-main" onclick="formSubmit(true)" id="savenew" name="savenew" value="Save & New"/>&nbsp;&nbsp;
	</div>		
</div>
</form>
</div>