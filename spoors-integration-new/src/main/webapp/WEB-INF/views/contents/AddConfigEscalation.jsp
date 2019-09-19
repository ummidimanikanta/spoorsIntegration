<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>

<script type="text/javascript">


$(document).ready(function(){
	<c:if test="${not empty configurationInfo.configEscalationList}">
	<c:forEach items="${configurationInfo.configEscalationList}" var="configEscalation">
	addEscalation("${configEscalation.id}","${configEscalation.configId}","${configEscalation.status}","${configEscalation.mailIds}",
			"${configEscalation.subjectTemplate}","${configEscalation.bodyTemplate}","${configEscalation.bodyType}" ,"${configEscalation.mailType}",
			"${configEscalation.addRequestInMail}","${configEscalation.addResponseInMail}");
	</c:forEach>
	</c:if>
});


function addEscalation(escalationId, configId, statusValue, mailIdValues, subjectValue, bodyValue, bodyTypeValue, mailTypeValue,
		requestInMail, responseInMail)
{
	var placeholder=$('#escalationContainer');
	var index = $('#escalationContainer tr').length-1;
	var tdata=$("<tr class='tdata'></tr>");

	var statusType=getStatusTypeOptions("configEscalationList["+index+"].status","configEscalationList_"+index+"_status");
	statusType.attr('index',index);
	statusType=$("<td><input  type='hidden' class='form-control' name='configEscalationList["+index+"].id'/></td>").append(statusType);
	if(!isEmpty(statusValue)){
		statusType.find("select").val(statusValue);
		statusType.find("input").val(escalationId);
	}
	tdata.append(statusType);
	
	var mailIdTag=$("<td><input  type='text' class='form-control' name='configEscalationList["+index+"].mailIds'/></td>");
  	if(!isEmpty(mailIdValues)){
  		mailIdTag.find("input").val(mailIdValues);
	}
	tdata.append(mailIdTag);
	
	var subjectTemplateTag=$("<td><input  type='text' class='form-control' name='configEscalationList["+index+"].subjectTemplate'/></td>");
  	if(!isEmpty(subjectValue)){
  		subjectTemplateTag.find("input").val(subjectValue);
  	}
	tdata.append(subjectTemplateTag);	

	var bodyTemplateTag=$("<td><input  type='text' class='form-control' name='configEscalationList["+index+"].bodyTemplate'/></td>");
  	if(!isEmpty(bodyValue)){
  		bodyTemplateTag.find("input").val(bodyValue);
  	}
	tdata.append(bodyTemplateTag);
	
	var bodyType=getBodyTypeOptions("configEscalationList["+index+"].bodyType","configEscalationList_"+index+"_bodyType");
	bodyType.attr('index',index);
	bodyType=$('<td></td>').append(bodyType);
	if(!isEmpty(bodyTypeValue)){
		bodyType.find("select").val(bodyTypeValue);
	}else{
		bodyType.find("select").val(2);
	}
	tdata.append(bodyType);
	
	var mailType=getMailTypeOptions("configEscalationList["+index+"].mailType","configEscalationList_"+index+"_mailType");
	mailType.attr('index',index);
	mailType=$('<td></td>').append(mailType);
	if(!isEmpty(mailTypeValue)){
		mailType.find("select").val(mailTypeValue);
	}
	tdata.append(mailType);

	var requestInMailTag=$("<td><input  type='checkbox' name='configEscalationList["+index+"].addRequestInMail' /></td>");
	requestInMail=='true' ? requestInMailTag.find("input[type='checkbox']").prop('checked', true): requestInMailTag.find("input[type='checkbox']").prop('checked',false);
	tdata.append(requestInMailTag);

	var responseInMailTag=$("<td><input  type='checkbox' name='configEscalationList["+index+"].addResponseInMail' /></td>");
	responseInMail=='true' ? responseInMailTag.find("input[type='checkbox']").prop('checked', true): responseInMailTag.find("input[type='checkbox']").prop('checked',false);
	tdata.append(responseInMailTag);
	
	var deleteTag = $("<td><button  class='deleteStage btn btn-sm btn-spoors-action' onclick='deleteRow(this);return false;' name='delete"+index+"' >Delete</button></td>");
	tdata.append(deleteTag);
	
	placeholder.append(tdata);
	
}

function getStatusTypeOptions(name,id){
	var selectOptions=$("<select name='name' id='id'></select>");
	selectOptions.attr("name",name);
	selectOptions.attr("id",id);
	selectOptions.addClass("stageType");
	selectOptions.append("<option value='0'>Not Processed</option>");
	selectOptions.append("<option value='1'>Failed</option>");
	selectOptions.append("<option value='2'>Success</option>");
	selectOptions.append("<option value='3'>ResponseTimeout</option>");
	selectOptions.append("<option value='4'>Hold</option>");
	return selectOptions;
}

function getMailTypeOptions(name,id){
	var selectOptions=$("<select name='name' id='id'></select>");
	selectOptions.attr("name",name);
	selectOptions.attr("id",id);
	selectOptions.addClass("stageType");
	selectOptions.append("<option value='0'>None</option>");
	selectOptions.append("<option value='1'>First Time Only</option>");
	selectOptions.append("<option value='2'>Retry Only</option>");
	selectOptions.append("<option value='3'>Every Time</option>");
	selectOptions.append("<option value='4'>After Last Retry</option>");
	return selectOptions;
}

function isEmpty(value){
	if(value!=undefined && value!=''){
		return false;
	}
	return true;
}

function getBodyTypeOptions(name,id){
	var selectOptions=$("<select name='name' id='id'></select>");
	selectOptions.attr("name",name);
	selectOptions.attr("id",id);
	selectOptions.addClass("stageType");
	selectOptions.append("<option value='1'>HTML</option>");
	selectOptions.append("<option value='2'>Text</option>");
	return selectOptions;
}


function deleteRow(currentRow){
	$("table").find(currentRow).each(function(){
        $(this).parents("tr").remove();
    });
}

</script>

<c:url var="formAction" value = "/escalations/add/${configId}"/>
<%-- <c:if test="${configInfo.id != null}">
<c:url var="formAction" value = "/escalations/update"/>
</c:if> --%>
<div  class="row">
<form:form method="post" action="${formAction}"  commandName="configurationInfo"  modelAttribute="configurationInfo">
	<input type="hidden" id="configId" name="configId" value="${configId}"/>
	<div class="row"  style="border-bottom:1px solid rgb(201, 194, 194);padding-bottom: 3px;">
					<div class="font-header" style="float: left;"  class="title" id="title-content">
									Add Integration Configuration Escalation				
					</div>
					<div style="float: right;">	
							<input type="submit" style="" class="btn btn-sm btn-spoors-main"  id="save" name="save" value="Save"/>&nbsp;&nbsp;
					</div>
				</div>
	<br/>
	<span class="error"></span>
	<div class="row">
		<span style="float:right;font-size:15px;margin-right:2%;">
	          <button class="btn btn-sm btn-spoors-action" onclick="addEscalation();return false;">Add Escalation level</button>
		</span>
	</div>
	<br/>
	<hr/>
	<br/>
	<div  style="" id="escalationsContainer">
		<table id="escalationContainer" class ='table grid'>
			<tr class='theader'>
				<th style='text-align:center;'>Status</th>
				<th style='text-align:center;'>Mail ID's</th>
				<th style='text-align:center;'>Subject Template</th>
				<th style='text-align:center;'>Body Template</th>
				<th style='text-align:center;'>Body Type</th>
				<th style='text-align:center;'>Mail Type</th>
				<th style='text-align:center;'>Add Request</th>
				<th style='text-align:center;'>Add Response</th>
				<th style='text-align:center;'>Action</th>
			</tr>
		</table>
	</div>
	<br/>
	<div class="row">
		<span style="float:right;font-size:15px;margin-right:2%;">
	          <button class="btn btn-sm btn-spoors-action" onclick="addEscalation();return false;">Add Escalation level</button>
		</span>
	</div>
	<hr/>
	<div class="row"  style="border-top:1px solid rgb(201, 194, 194);padding-top: 3px;">
				<div style="float: right;">	
						<input type="submit" style="" class="btn btn-sm btn-spoors-main"  id="save" name="save" value="Save"/>&nbsp;&nbsp;
				</div>
				
			</div>
			<br/>
			<br/>
	
</form:form>
</div>
<br>