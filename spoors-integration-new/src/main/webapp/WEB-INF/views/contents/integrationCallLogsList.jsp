<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script>
$(document).ready(function(){
	$("#configurationHelp").data("title","List Integration Call Logs");
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
.ui-widget {
    font-family: Verdana,Arial,sans-serif;
    font-size: .8em;
}

.ui-widget-content {
    background: #F9F9F9;
    border: 1px solid #90d93f;
    color: #222222;
}

.ui-dialog {
    left: 0;
    outline: 0 none;
    padding: 0 !important;
    position: absolute;
    top: 0;
}

.divDialog {
    padding: 0;
    margin: 0; 
}

.ui-dialog .ui-dialog-content {
    background: none repeat scroll 0 0 transparent;
    border: 0 none;
    overflow: auto;
    position: relative;
    padding: 0 !important;
}

.ui-widget-header {
    background: #b0de78;
    border: 0;
    color: #fff;
    font-weight: normal;
}

.ui-dialog .ui-dialog-titlebar {
    padding: 0.1em .5em;
    position: relative;
        font-size: 1em;
}
</style>

<div class="container-fluid"
	style="padding-left: 0px; padding-right: 0px;">

	<div class="row" style="text-align: right;">

		<div
			style="padding: 5px 5px 3px 0px; border-bottom: 1px solid rgb(201, 194, 194); text-align: left;"
			class="font-header">
			<span class="font-header">List Integration Call Logs</span> <img
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
			<c:when test="${empty integrationCallLogsBeanList}">
				<%-- 	<div style="float: right ">&raquo;<a href="${pageContext.servletContext.contextPath}/web/create/modify/appConfiguration?defaultApp=true"><b>Default App</b></a></div><br><br> --%>
				<table id="allApps" cellpadding="8" cellspacing="0"
					class="table grid">
					<thead>
						<tr align="center">
							<th style="text-align: center">Configuration Name</th>
							<th style="text-align: center">Status</th>
							<th style="text-align: center">Retried Count</th>
							<th style="text-align: center">Response</th>
							<th style="text-align: center">External System Id</th>
							<th style="text-align: center">Delete</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="6" style="text-align: center;">No Data Available</td>
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
							<th style="text-align: center">Status</th>
							<th style="text-align: center">Retried Count</th>
							<th style="text-align: center">Response</th>
							<th style="text-align: center">External System Id</th>
							<th style="text-align: center">Delete</th>
						

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${integrationCallLogsBeanList}"
							var="integrationCallLogsBean" varStatus="items">

							<tr>
								<td nowrap="nowrap" align="center">${integrationCallLogsBean.configurationName}</td>
								<td nowrap="nowrap" align="center">
								<c:choose>
									<c:when test="${integrationCallLogsBean.status == 0 }"> 
										Not-Processed
									</c:when> 
									<c:when test="${integrationCallLogsBean.status == 1 }"> 
										Success
									</c:when> 
									<c:when test="${integrationCallLogsBean.status == 2 }"> 
										ResponseTimeout
									</c:when> 
									<c:when test="${integrationCallLogsBean.status == 3 }"> 
										Hold
									</c:when> 
									<c:otherwise>
										Failed
									</c:otherwise>
			 				</c:choose>
								</td>
								<td nowrap="nowrap" align="center">${integrationCallLogsBean.retryCount}</td>
								<td nowrap="nowrap" align="center">
									<a href='javascript:openDialog(${items.index})'>Response</a>
									<div class="divDialog" id="dialog${items.index}" title="Response" style="display:none">
										<textarea style="width:100%; font-family: Monospace; font-size:12px; 
										border:0;" rows="20" disabled>
										${integrationCallLogsBean.responseData}
										</textarea>
									</div>
								</td>
								<td nowrap="nowrap" align="center">${integrationCallLogsBean.externalSystemId}</td>
								<td nowrap="nowrap" align="center"><a href="../txnRetry/${integrationCallLogsBean.id}"><button >RePush</button></a></td>
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


function openDialog(index){
	$('#dialog'+index).dialog({
	    modal: true,
	    resizable: true,
	    dialogClass: 'divDialog'
	});
    return false;
}


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
	   		url : '${pageContext.servletContext.contextPath}/integration/callLogs/list' ,
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


