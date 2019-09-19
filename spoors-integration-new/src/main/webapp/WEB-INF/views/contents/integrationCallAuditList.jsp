<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
$(document).ready(function(){
	$("#configurationHelp").data("title","List Integration Call Audit");
	$("#configurationHelp").tooltip({
	track : true
	});
})
</script>

<style type="text/css">
.glyphicon {
	font-size: 20px;
}

.tooltip-inner {
	max-width: 300px;
	width: 300px;
}
</style>

<div class="container-fluid"
	style="padding-left: 0px; padding-right: 0px;">

	<div class="row" style="text-align: right;">

		<div
			style="padding: 5px 5px 3px 0px; border-bottom: 1px solid rgb(201, 194, 194); text-align: left;"
			class="font-header">
			<span class="font-header">List Integration Calls Audit</span> <img
				style="vertical-align: top; padding-top: 0px; padding-left: 4px;"
				class="hoverOnMe" id="configurationHelp"
				src="${pageContext.servletContext.contextPath}/resources/img/question1.png"
				title="" /><img id="favlinkImage" src="" onclick="makeFavourite();"
				style="padding-left: 10px; vertical-align: initial;" />
		</div>
		<div align="right" class="fontSize"
			style="padding-right: 5px; padding-bottom: 1px;padding-top:15px;">
		</div>
	</div>
</div>
<div class="col-xs-12 col-sm-12 col-md-6  col-lg-6 col-xl-6"
	style="padding: 5px 15px;margin-bottom:50px;">
	<div class="row">
		<c:choose>
			<c:when test="${empty integrationCallAuditBeanList}">
				<table id="allApps" cellpadding="8" cellspacing="0"
					class="table grid">
					<thead>
						<tr align="center">
							<th style="text-align: center">Configuration Name</th>
							<th style="text-align: center">Start Time</th>
							<th style="text-align: center">End Time</th>
							<th style="text-align: center">Duration(in Mins:Secs)</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5" style="text-align: center;">No Data
								Available</td>
						</tr>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<table id="allWorkflows" cellpadding="8" cellspacing="0"
					class="table grid">
					<thead>
						<tr align="center">
							<th style="text-align: center">Configuration Name</th>
							<th style="text-align: center">Start Time</th>
							<th style="text-align: center">End Time</th>
							<th style="text-align: center">Duration (in Mins:Secs)</th>
						

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${integrationCallAuditBeanList}"
							var="integrationCallAuditBean">

							<tr>
								<td nowrap="nowrap" align="center">${integrationCallAuditBean.configurationName}</td>
								<td nowrap="nowrap" align="center">${integrationCallAuditBean.startTime}</td>
								<td nowrap="nowrap" align="center">${integrationCallAuditBean.endTime}</td>
								<td nowrap="nowrap" align="center">${integrationCallAuditBean.duration}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page" style="float: right;">
					<table class="table grid xPagination">

					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script>

	/* function deleteTemplate(configId,configName) {
		if (confirm('Are you sure you want to delete '+configName+'?')) {
			 window.location.href = '${pageContext.servletContext.contextPath}/configure/deleteEmpActivityWiseSummaryAdvancedConfiguratorFormData?configId='+configId; 
		} else {
				return false;
		}
	} */
   	$(document).ready(function() { 
   		$('[data-toggle="tooltip"]').tooltip(); 
   	    
   	 var target = '${target}'; 
   	 var tzo = new Date().getTimezoneOffset();
   	 
   	 $('#page').xPagination({
   			page : '${page}',
			total :'${total}',
			orderBy : '${orderBy}',
	   		url : '${pageContext.servletContext.contextPath}/integration/callLogs/audit/list' ,
	   		headers: {
	  	     
	   		}
   		});
   	   $("#dialog-form").dialog({
            autoOpen: false,
            height: 250,
            width: 350,
            modal: true,
            buttons: {
                "Save": function() {
                	$('#leave-state-form').submit();
                	$(this).dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            },
            close: function() {
                
            }
        });
		$('#searchButton').click(function() {
			var state = $(this).attr('state');
			if (state == 'col') {
				$('#searchFrame').show();
				$(this).attr('state', 'exp');
			} else if (state == 'exp') {
				$('#searchFrame').hide();
				$(this).attr('state', 'col');
			}
		});

   	}); 
</script>


