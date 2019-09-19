<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<script type="text/javascript">

	
function loadTriggerSource()
{           
    $('#triggerId').empty().append('<option value="-1">- Select -</option>');
    var type = $('#triggerType').val();
    console.log("type");
    $.ajax({
            url: 'triggerSources/'+type,
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

    
</script>    
<c:url var="formAction" value = "/configure/add"/>
<c:if test="${configInfo.id != null}">
<c:url var="formAction" value = "/configure/update"/>
</c:if>

<form name="configForm" action="${formAction}" id="configForm" method="post">
<input type="hidden" value="<c:out value="${configInfo.id}" />" id="id" name="id" />
  <table class="container-fluid table-bordered table-striped text-center">
  
      <tr><td>Name</td><td><input type="text" class='form-control' name="name" value="<c:out value="${configInfo.name}" />" /></td></tr>
      <tr class="form-group">
      	<td>Company</td>
      	<td>
     		<select id ="companyId" name="companyId" class="form-control">
      			<option value="-1">-Select-</option>
      			<c:if test="${companyMap != null}">
				 	<c:forEach items= "${companyMap}" var="company">
						<c:choose><c:when test="${company.key == configInfo.companyId }"> 
						<option value="${company.key}" selected>${company.value}</option></c:when> 
						<c:otherwise>
						<option value="${company.key}">${company.value}</option>
					</c:otherwise>
	 				</c:choose>
					</c:forEach>
				</c:if>	      		
			</select>
      	</td>
      </tr>
      <tr class="form-group">
      	<td>Trigger Type</td>
      	<td>
      		<select id ="triggerType" name="triggerType" class="form-control" onchange="loadTriggerSource();">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.triggerType == 1}"> selected </c:if>>Forms</option>
				<option value="2" <c:if test="${configInfo.triggerType == 2}"> selected </c:if>>Works</option>
			</select>
      	</td>
      </tr>
      
      <tr class="form-group">
      	<td>Trigger Source</td>
      	<td>      	
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
      	</td>
      </tr>
      
      <tr class="form-group">
      	<td>Request Content Type</td>
      	<td>
      		<select id ="contentType" name="contentType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.contentType == 1}"> selected </c:if>>JSON</option>
				<option value="2" <c:if test="${configInfo.contentType == 2}"> selected </c:if>>XML</option>
			</select>
      	</td>
      </tr>
     
      <tr class="form-group">
      	<td>Form Data Push Type</td>
      	<td>
      		<select id ="formDataPushType" name="formDataPushType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.formDataPushType == 1}"> selected </c:if>>Detailed</option>
				<option value="2" <c:if test="${configInfo.formDataPushType == 2}"> selected </c:if>>Simplified</option>
			</select>
      	</td>
      </tr>
     
      <tr class="form-group">
      	<td>Push Form Data on Approval</td>
      	<td>
      		<select id ="approveFormData" name="approveFormData" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.approveFormData == 1}"> selected </c:if>>Approved</option>
				<option value="0" <c:if test="${configInfo.approveFormData == 0}"> selected </c:if>>Rejected</option>
			</select>
      	</td>
      </tr> 

      <tr>
      	<td>Invoke Type</td>
      	<td class="form-group">
      		<select id ="invokeType" name="invokeType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.invokeType == 1}"> selected </c:if>>API</option>
				<option value="2" <c:if test="${configInfo.invokeType == 2}"> selected </c:if>>DB</option>
				<option value="3" <c:if test="${configInfo.invokeType == 3}"> selected </c:if>>FILE</option>
				<option value="4" <c:if test="${configInfo.invokeType == 4}"> selected </c:if>>SAP</option>
			</select>
      	</td>
      </tr>
      
      <tr>
      	<td>Response Content Type</td>
      	<td class="form-group">
      		<select id ="acceptType" name="acceptType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.contentType == 1}"> selected </c:if>>JSON</option>
				<option value="2" <c:if test="${configInfo.contentType == 2}"> selected </c:if>>XML</option>
			</select>
      	</td>
      </tr>      

      <tr><td>Submission URL</td><td><input type="text" class='form-control' name="submitUrl" value="<c:out value="${configInfo.submitUrl}" />" /></td></tr>
      <tr><td>Modification URL</td><td><input type="text" class='form-control' name="urlForUpdate" value="<c:out value="${configInfo.urlForUpdate}" />" /></td></tr>


      <tr>
      	<td>Ignore SSL</td>
      	<td>
	      <input type="radio" class="radio-inline" name="ignoreSSL" value="1"
	      <c:if test="${configInfo.ignoreSSL}"> checked</c:if> />True &nbsp;&nbsp;&nbsp;&nbsp; 
	      <input type="radio" class="radio-inline" name="ignoreSSL" value="0" 
	      <c:if test="${!configInfo.ignoreSSL}"> checked</c:if>/>False
		</td>
      </tr>

      <tr>
      	<td>Authentication Type</td>
      	<td class="form-group">
      		<select id ="authenticationType" name="authenticationType" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.authenticationType == 1}"> selected </c:if>>Basic</option>
				<option value="2" <c:if test="${configInfo.authenticationType == 2}"> selected </c:if>>JWT</option>
			</select>
      	</td>
      </tr> 
      
       <tr><td>Username</td><td><input type="text" class='form-control' name="username" value="<c:out value="${configInfo.username}" />" /></td></tr>
      <tr><td>Password</td><td><input type="password" class='form-control' name="password" value="<c:out value="${configInfo.password}" />" /></td></tr>
 
      <tr>
     	<td>Escalation Type</td>
     	<td class="form-group">
     		<select id ="escalationType" name="escalationType" class="form-control">
			<option value="-1">-Select-</option>
			<option value="1" <c:if test="${configInfo.escalationType == 1}"> selected </c:if>>Per Failed</option>
			<option value="2" <c:if test="${configInfo.escalationType == 2}"> selected </c:if>>% Failed</option>
			<option value="3" <c:if test="${configInfo.escalationType == 3}"> selected </c:if>>Duration Failed</option>
		</select>
     	</td>
      </tr>
      
      <tr>
      	<td>Enable</td>
      	<td>
	      <input type="radio" class="radio-inline" name="isEnabled" value="true" 
	      <c:if test="${configInfo.isEnabled}"> checked</c:if>/>True &nbsp;&nbsp;&nbsp;&nbsp; 
	      <input type="radio" class="radio-inline" name="isEnabled" value="false" 
	      <c:if test="${!configInfo.isEnabled}"> checked</c:if>/>False
		</td>
      </tr>

      <tr><td>Retry Count</td><td><input type="text" class='form-control' name="retryCount" value="<c:out value="${configInfo.retryCount}" />" /></td></tr> 

      <tr>
      	<td>Success Key</td>
      	<td class="form-group">
      		<select name="successKey" class="form-control">
				<option value="-1">-Select-</option>
				<option value="1" <c:if test="${configInfo.successKey == 1}"> selected </c:if>>Present</option>
				<option value="2" <c:if test="${configInfo.successKey == 2}"> selected </c:if>>Does Not Present</option>
				<option value="3" <c:if test="${configInfo.successKey == 3}"> selected </c:if>>Match</option>
				<option value="4" <c:if test="${configInfo.successKey == 4}"> selected </c:if>>Does Not Match</option>
			</select>
      	</td>
      </tr>

	  <tr><td>Success Key Value</td><td><input type="text" class='form-control' name="successKeyValue" value="<c:out value="${configInfo.successKeyValue}" />" /></td></tr>

      <tr>
      	<td>Avoid Modification</td>
      	<td>
	      <input type="radio" class="radio-inline" name="restrictUpdateUrl" value="true" 
	      <c:if test="${configInfo.restrictUpdateUrl}"> checked</c:if> />Send &nbsp;&nbsp;&nbsp;&nbsp; 
	      <input type="radio" class="radio-inline" name="restrictUpdateUrl" value="false" 
	      <c:if test="${!configInfo.restrictUpdateUrl}"> checked</c:if> />Don't Send
		</td>
      </tr>
            
      <tr>
      	<td colspan="2" align="center">
      		<input type="submit" name="submitForm" class="btn btn-sm btn-spoors-main" id="submitForm" value="Submit"/>
      	</td>
      </tr>
      
  </table>
</form>