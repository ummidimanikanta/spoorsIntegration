<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 --%><%@ page trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/areyousure/areyousure.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/areyousure/ays-beforeunload-shim.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/spoorsJS/keyvalidateDynamicfile.js"></script>
<script type="text/javascript">
//<![CDATA[
 	
 		var itemCount = 0;	
 		var existingEmployeeFields = [];
 		var existingCustomerFields = [];
 		//store only fields other than customer and employee
 		var existingFields = [];
 		var empExistingIndex = [];
 		var custExistingIndex = [];
 		var firstFieldSpecType = '${firstFormFieldSpecObj}';
 		
 		var formFieldSpecs = "<option value=''>select field</option>";
 		formFieldSpecs += '<c:forEach items="${formFieldSpecs}" var="formFieldSpec">\
							<option value="${formFieldSpec.fieldLabel}" fieldType="${formFieldSpec.fieldType}">${formFieldSpec.fieldLabel}</option>\
						</c:forEach>';
				
		var employeeFields = "<option value=''>select field</option>";
		employeeFields += '<c:forEach items="${employeeFields}" var="employeeField">\
			<option value="${employeeField.objectFieldName}" fieldType="${employeeField.fieldType}">${employeeField.objectFieldName}</option>\
		</c:forEach>';
		
		var customerFields = "<option value=''>select field</option>";
		customerFields += '<c:forEach items="${customerFields}" var="customerField">\
			<option value="${customerField.fieldLabel}" fieldType="${customerField.fieldType}">${customerField.fieldLabel}</option>\
		</c:forEach>';

	 	$(document).ready(function() {
	 	  	
	 		$('#title-content').html("Add Field Mapping");
	 		
	 		//Existing objects
	 		 var formAndWorkFieldMappingBeans = JSON.parse('${formAndWorkFieldMappingBeansJson}');
	 	    
	 	    if(formAndWorkFieldMappingBeans != undefined && formAndWorkFieldMappingBeans != null){
	 	    	 for(var i=0; i<formAndWorkFieldMappingBeans.length; i++){
	 	 	    	var sourceKey = formAndWorkFieldMappingBeans[i].sourceKey;
	 	 	    	//alert("sourceKey "+sourceKey);
	 	 	    	var sourceSubKey = formAndWorkFieldMappingBeans[i].sourceSubKey;
	 	 	    	var destinationKey = formAndWorkFieldMappingBeans[i].destinationKey;
	 	 	    	var ignoreObj = formAndWorkFieldMappingBeans[i].ignoreField;
	 	 	    	var sourceKeyFieldType = formAndWorkFieldMappingBeans[i].fieldType;
	 	 	    	var existingObjects = true;
	 	 	    	addItemPopulated(itemCount,sourceKey,destinationKey,ignoreObj,sourceKeyFieldType,existingObjects);
	 	 	    	if(sourceKeyFieldType == 7 || sourceKeyFieldType == 15){
	 	 	    		showSourceKeySubField("",itemCount,"subFieldTd_"+itemCount,sourceKey,sourceKeyFieldType,existingObjects);
	 	 	    		
	 	 	    	}
	 	 	    	  
	 	 	    	itemCount++;
	 	    	 }
	 	    	
	 	    }
	 	});
	 	
	 	function removeItem(elem){
	   	 	var tr = $(elem).parent().parent();
	   	 	//tr.remove();
	   	 	tr.html("");
		}
	 	
	 	function addItemPopulated(index,sourceKey,destinationKey,ignoreObj,fieldType,existingObjects){
	 		var tr = $("<tr class='item' id='itemRow"+itemCount+"' index="+itemCount+"></tr>");
	 		
	 		var fieldTypeHiddenTd = $("<input type='hidden' name='formAndWorkFieldMappingBeans["+index+"].fieldType' id='fieldType_"+index+"' style='width: 200px;'/>");
	 		var sourceKeyTd = $("<td style='width: 200px; vertical-align: top;'></td>")
	 		.append("<select class='field type fieldType' index='"+index+"' onchange='showSourceKeySubField(this)' id='sourceKey_"+index+"' name='formAndWorkFieldMappingBeans["+index+"].sourceKey' style='width: 200px;'>"+formFieldSpecs+"</select>")
	 		
	 		var soruceSubKeyTd = $("<td style='width: 200px; vertical-align: top;' id='subFieldTd_"+index+"'></td>");
	 		
	 		var destinationKeyTd = $("<td style='width: 200px; vertical-align: top;'></td>")
	 		.append("<input type='text' name='formAndWorkFieldMappingBeans["+index+"].destinationKey' id='destinationKey_"+index+"' class='form-control' style='width: 200px;/'>")
	 		
	 		var ignoreTd = $("<td style='width: 200px; vertical-align: top;'></td>")
	 		.append("<input type='checkbox' name='formAndWorkFieldMappingBeans["+index+"].ignoreField' id='ignoreObj_"+index+"' style='width: 200px;'/>")
	 		
	 		var removeItemTd = $("<td style='width: 25px; vertical-align: top;'></td>")
			.append("<span title='remove this field' index ="+index+" class='glyphicon  glyphicon-remove-circle mode glyphicon-deleted-color' onclick='removeItem(this)' aria-hidden='true' style=' font-size: 20px;'></span>");
	 		
	 		tr.append(sourceKeyTd)
	 		.append(fieldTypeHiddenTd)
	 		.append(soruceSubKeyTd)
	 		.append(destinationKeyTd)
	 		.append(ignoreTd)
	 		.append(removeItemTd);
	 		
	   		$('#formFields > tbody:last').append(tr);
	   		
	   		 var fieldTypeHidden = $("#sourceKey_"+index+" option:selected").attr("fieldType");
	   		// alert("fieldTypeHidden "+fieldTypeHidden);
		   	$("#fieldType_"+index).val(fieldTypeHidden);
		 	$("#sourceKey_"+index).select2();
	   		
	 		
	 		if(existingObjects && (fieldType == 7 || fieldType == 15)){
	 			$("#sourceSubKey_"+index).val(sourceKey);
	 			var sourceKey = $("#sourceKey_"+index).find('option').filter(function() { return  $(this).attr('fieldType')  == fieldType; }).text();
	 			$("#sourceKey_"+index).val(sourceKey);
	 			$("#destinationKey_"+index).val(destinationKey);
	 			if(ignoreObj){
	 				$("#ignoreObj_"+index).prop("checked",true);
	 			}
	 		}else if(existingObjects){
	 			$("#sourceKey_"+index).val(sourceKey)
	 			$("#destinationKey_"+index).val(destinationKey)
	 			if(ignoreObj){
	 				$("#ignoreObj_"+index).prop("checked",true);
	 			}
	 		}
	 		
	 		$("#sourceKey_"+index).select2();
	 		
	 		//To open object property select box automatically if first field is object in formfieldSpecs
	 		if(existingObjects == undefined ){
	 			var obj = document.createElement("div");
	 			obj.setAttribute("index",index);
	 			obj.setAttribute("id","sourceKey_"+index);
	 			
	 			if(firstFieldSpecType == 7 || firstFieldSpecType == 15){
	 				showSourceKeySubField(obj);
	 			}else{
	 				//validateSelectedSourceKeyValues(obj);
	 			}
	 			
	 		}
	 		
		}
	 	
	 	
	 	function addItem(){
	 		addItemPopulated(itemCount);
	 		//checkAndStoreExistingCustEmpFieldsInArray();
	 		itemCount++;
	 	 }
	 	
	 
	 	
	 	function showSourceKeySubField(obj,indexNo,tdId,sourceSubKey,sourceKeyFieldType,existingObjects){
	 		
	 		var index,id;
	 		if(obj != ""){
	 			index = obj.getAttribute('index');
		 		id = obj.getAttribute("id");
	 		}
	 		
	 		if(id == undefined || id == null){
	 			id = tdId;
	 		}
	 		
	 		var fieldType = $("#"+id+" option:selected").attr("fieldType");
	 		
	 		if(existingObjects){
	 			index = indexNo;
	 			fieldType = sourceKeyFieldType;
	 		}
	 		
	 		$("#subFieldTd_"+index).html("");
	 		if(fieldType == 15){
	 			var employeeFieldOptions = "<select name='formAndWorkFieldMappingBeans["+index+"].sourceSubKey' id='sourceSubKey_"+index+"' style='width:200px;' index="+index+" onchange='validateSelectedOptionFromEmpOrCust(this)'>"+employeeFields+"</select>";
		 		$("#subFieldTd_"+index).append(employeeFieldOptions);
		 		
		 		empExistingIndex.push(index);
		 	
	 		} else if (fieldType == 7){
	 			var customerFieldsOptions = "<select name='formAndWorkFieldMappingBeans["+index+"].sourceSubKey' id='sourceSubKey_"+index+"' style='width:200px;' index="+index+" onchange='validateSelectedOptionFromEmpOrCust(this)'>"+customerFields+"</select>";
		 		$("#subFieldTd_"+index).append(customerFieldsOptions);
		 		
		 		custExistingIndex.push(index);
	 		}
	 		//setting fieldType to hidden variable
	 		$("#fieldType_"+index).val(fieldType);
	 		
	 		//Below function calling is to validate different field is selected in source key 
	 		if(obj != ""){   
	 	    	validateSelectedSourceKeyValues(obj);
	 	    }
	 		if(existingObjects){
	 			$("#sourceSubKey_"+index).val(sourceSubKey);
	 		}
	 		$("#sourceSubKey_"+index).select2();
	 		
	 		if(fieldType == 15 || fieldType == 7){
 				//var sourceSubKeyObj = $("#sourceSubKey_"+index);
 				var sourceSubKeyObj = document.getElementById("sourceSubKey_"+index);
 				sourceSubKeyObj.setAttribute("index",index);
 				//removeSelectedOptionFromEmpOrCust(sourceSubKeyObj);
 			}
	 		
	 		
	 		}
	 	
	 	//Below function is to validate customer or employee
	 	//Will not allow to pick same field from customer or employee
	 	function validateSelectedOptionFromEmpOrCust(obj){
	 		
	 		var selectedOption = $("#"+obj.getAttribute('id')+" option:selected").val();
	 		
	 		var index = obj.getAttribute('index');
	 		var fieldType = $("#fieldType_"+index).val();
	 		
	 		if(fieldType == 15 && existingEmployeeFields.includes(selectedOption)){
 				alert("Employee Field Already Selected");
 				$("#"+obj.getAttribute('id')).select2("val", "");
	 		
	 		} else if(fieldType == 7 && existingCustomerFields.includes(selectedOption)){  
	 			alert("Cutomer Field Already Selected");
 				$("#"+obj.getAttribute('id')).select2("val", "");
	 		}
	 			 
	 		checkAndStoreExistingCustEmpFieldsInArray();
	 	}
	 	
		//below function store existing customer and employee fields,
	 	//will not be store same field name field again
	 	function checkAndStoreExistingCustEmpFieldsInArray(){
	 		
	 		existingEmployeeFields = [];
	 		existingCustomerFields = [];
	 		existingFields = [];
	 		$(".item").each(function(){
	 			var index = $(this).attr("index");
	 			var fieldType =  $("#fieldType_"+index).val();
	 			
	 			//alert("fieldType "+fieldType)
	 			//alert("existingFields "+existingFields);
	 			//alert("fieldType "+fieldType);
	 			//below code store sourceSubKeyValue
	 			var sourceSubKeyValue = $("#sourceSubKey_"+index).val();
	 			//alert("sourceSubKeyValue "+sourceSubKeyValue);
	 			if(fieldType == 15 && sourceSubKeyValue!='' && !existingEmployeeFields.includes(sourceSubKeyValue)){
	 				existingEmployeeFields.push(sourceSubKeyValue);
	 				
	 			} else if(fieldType == 7 && sourceSubKeyValue!='' && !existingCustomerFields.includes(sourceSubKeyValue)){
	 				existingCustomerFields.push(sourceSubKeyValue);
	 			}
	 			
	 			//below code store sourceKey value other than customer and employee
	 			var sourceKeyValue = $("#sourceKey_"+index).val();
	 			//alert("existingFields "+existingFields);
	 			
	 			if(fieldType != 7 && fieldType != 15 && sourceKeyValue!='' && !existingFields.includes(sourceKeyValue)){
	 				existingFields.push(sourceKeyValue);
	 			}
	 			
	 		})
	 	}
		   //Below function is to check different source key is selected 
			function validateSelectedSourceKeyValues(obj){
	 		
	 		var selectedOption = $("#"+obj.getAttribute('id')+" option:selected").val();
	 		
	 		var index = obj.getAttribute('index');
	 		var fieldType = $("#fieldType_"+index).val();
	 		
	 		if(fieldType != 15 && fieldType != 7 && selectedOption != '' && existingFields.includes(selectedOption)){
 				alert("Field Already Selected");
 				$("#"+obj.getAttribute('id')).select2("val", "");
	 		
	 		}
	 		checkAndStoreExistingCustEmpFieldsInArray();
	 	}
	 	
	 	
 	</script>

<div class="container-fluid" style="height: 570px; overflow: auto;">
<form:form  method="post" action="${pageContext.servletContext.contextPath}/configure/save/field/mapping"
commandName="formAndWorkFieldMappingListBean" modelAttribute="formAndWorkFieldMappingListBean">

       <input type="hidden" name="configId" value="${configId}"/>
		<div class="row"
			style="border-bottom: 1px solid rgb(201, 194, 194); padding-bottom: 3px;">
			<div class="font-header" style="float: left;" class="title"
				id="title-content">Add Field Mapping</div>
		</div>

		<br>
	<div style="border:1px solid rgb(201, 194, 194);min-height:200px;height:auto;padding:11px;">
	<table class=" noGrid mode" cellpadding="8" cellspacing="0" style="width: 100%; clear: both;">
		<tr id='itemAdd'><td>
			<div align="right" style="width: 100%;padding-bottom: 5px;">
				<input type="Button" class="btn btn-sm btn-spoors-action" value="Add Field" onclick="addItem();"/>
			</div>
		</td></tr>
	</table>
	<br>
		<table id="formFields" class="grid table noGrid" cellpadding="8" cellspacing="0" style="width: 100%; clear: both; /* border-collapse: collapse; */">
			<thead>
				<tr>
					<th>Source Key</th>
					<th></th>
					<th>Destination Key</th>
					<th>Ignore </th>
					<th>Actions</th>
					</tr>
			</thead>
			<tbody>
				 
			</tbody>
		</table>
	</div>
	<table class=" noGrid mode" cellpadding="8" cellspacing="0" style="width: 100%; clear: both;">
		<tr id='itemAdd'>
		
		<td>
			<div align="right" style="width: 100%;padding-bottom: 5px;margin-top:10px">
				<input type="Button" class="btn btn-sm btn-spoors-action" value="Add Field" onclick="addItem();"/>
			</div>
		</td></tr>
	</table>
	
	<br>
	
	<div class="row"  style="border-top:1px solid rgb(201, 194, 194);padding-top: 3px;">
				<div style="float: right;" class="mode">
					<input type="submit" class="btn btn-sm btn-spoors-main" id="save" name="save" value="Save"/>&nbsp;
				</div>
	</div>
	
	<br>
	<br>
	
</form:form>
			
</div>
