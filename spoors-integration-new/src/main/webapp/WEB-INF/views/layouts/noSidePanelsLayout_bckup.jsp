<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="padding: 0px;margin: 0px;height: 100%;">
<head>
<title id="header-title-content">${sessionData.productConstants.productName} | <tiles:insertAttribute
		name="header-title-content" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<tiles:insertAttribute name="css-content" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/print.css"
	media="print">

<link rel="icon" href="${pageContext.servletContext.contextPath}/resources/img/favicon.ico">

<style type="text/css">
	.ui-layout-pane{
		padding:0;
		overflow:auto;
		background: none;
	}	
	/* .ui-layout-resizer{
		position:absolute;
		padding:0;
		margim:0;
	} */
	.button-close-west{
		left: 5px;
		position: absolute;
		top: 6px;
		width: 96px;
		height: 20px;
		z-index: 2;
		display: block;
		cursor: pointer;
		background: url(${pageContext.servletContext.contextPath}/resources/css/img/closeleft.png) no-repeat;
	}
	.button-close-east{
		right: 5px;
		position: absolute;
		top: 6px;
		width: 48px;
		height: 20px;
		z-index: 2;
		display: block;
		cursor: pointer;
		background: url(${pageContext.servletContext.contextPath}/resources/css/img/closeright.png) no-repeat;
	} 
	.ui-layout-toggler-west{
		position: absolute;
		display: block;
		padding: 0px;
		margin: 0px;
		overflow: hidden;
		text-align: center;
		font-size: 1px;
		cursor: pointer;
		z-index: 1;
		background: url(${pageContext.servletContext.contextPath}/resources/css/img/openleft.png) no-repeat scroll 0% 0% !important;
		visibility: visible;
		height: 50px;
		width: 0px!important;
		top: 0px;
		left: 0px;
	}
	.ui-layout-toggler-west-closed{
		position: absolute;
		display: block;
		padding: 0px;
		margin: 0px;
		overflow: hidden;
		text-align: center;
		font-size: 1px;
		cursor: pointer;
		z-index: 1;
		background: url(${pageContext.servletContext.contextPath}/resources/css/img/openleft.png) no-repeat scroll 0% 0% !important;
		visibility: visible;
		height: 128px!important;
		width: 20px!important;
		top: 5px!important;
		left: 5px!important;					
	}
	.ui-layout-toggler-east{
		position: absolute;
		display: block;
		padding: 0px;
		margin: 0px;
		overflow: hidden;
		text-align: center;
		font-size: 1px;
		cursor: pointer;
		z-index: 1;
		background: url(${pageContext.servletContext.contextPath}/resources/css/img/openright.png) no-repeat scroll 0% 0% !important;
		visibility: visible;
		height: 50px;
		width: 0px!important;
		top: 104px;
		left: 0px;
	}
	.ui-layout-toggler-east-closed{
		position: absolute;
		display: none;
		padding: 0px;
		margin: 0px;
		overflow: hidden;
		text-align: center;
		font-size: 1px;
		cursor: pointer;
		z-index: 1;
		background: url(${pageContext.servletContext.contextPath}/resources/css/img/openright.png) no-repeat scroll 0% 0% !important;
		visibility: visible;
		height: 128px!important;
		width: 20px!important;
		top: 5px!important;
		left: 0px;
				
	}	
	.ui-layout-toggler{
		position: absolute;
		display: block;
		padding: 0px;
		margin: 0px;
		overflow: hidden;
		text-align: center;
		font-size: 1px;
		cursor: pointer;
		z-index: 1;
		background: none repeat scroll 0% 0% rgb(142, 236, 255);
		visibility: visible;
		height: 50px;
		width: 6px;
		top: 104px;
		left: 0px;
				
	}
	
</style>

<tiles:insertAttribute name="js-content" />
<script	src="${pageContext.servletContext.contextPath}/resources/js/jquery.layout-latest.js"></script>
<script type="text/javascript">
	
</script>
<script>

	$(document).ready(function() {
		
// 			$("#hideFooter").attr("hide", "true");
// 			$("#spoorsFooter").hide();
			
		//	hideSpoorsFooter();
			
			$("#hideFooter").click(function(){
				var isHide = $("#hideFooter").attr("hide");
				hideSpoorsFooter();
				if(isHide == "true"){
					$("#hideFooter").attr("hide", "false");
				}else{
					$("#hideFooter").attr("hide", "true");
				}
			});
		
		/* outerLayout = 
		$('body').layout({ 
            closable:    true,    
            resizable:    true,    
            slidable:    true,
            livePaneResizing:true,
            east__minSize:    220,    
            east__maxSize:    .5,
            north__minSize: 85,
            east__initClosed:    true,
            west__resizable:    true, 
            west__initClosed:    true, 
            south__initClosed:    true,
            south__resizable:    false,
            south__togglerTip_open: "hide",
            south__togglerTip_closed: "show",
            west__togglerTip_open: "hide",
            west__togglerTip_closed: "show",
            east__togglerTip_open: "hide",
            east__togglerTip_closed: "show",
            east__resizable:    false,//new
            west__spacing_closed: 20,//new
            east__spacing_closed: 20,//new
            north: {
                resizable: false,
                closable: false,
                spacing_open: 0 ,
                spacing_closed: 0 ,
                enableCursorHotkey: false,
                padding:0,
                showOverflowOnHover: true
            },
            
           
        }); */
		/* var westSelector = "body > .ui-layout-west"; // outer-west pane
		<c:if test="${sessionData.productConstants.uiLayoutEast}">
		var eastSelector = "body > .ui-layout-east"; // outer-east pane
		</c:if>

		 // CREATE SPANs for close-buttons - using unique IDs as identifiers
	 	$("<span class='button-close-west'></span>").attr("id", "west-closer" ).prependTo( westSelector );
	 	<c:if test="${sessionData.productConstants.uiLayoutEast}">
		$("<span class='button-close-east'></span>").attr("id", "east-closer").prependTo( eastSelector );
		</c:if>
		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#west-closer", "west");
		<c:if test="${sessionData.productConstants.uiLayoutEast}">
		outerLayout.addCloseBtn("#east-closer", "east");
		</c:if>
 */		
		$(document).ajaxSuccess(function(evt, request, settings){
			   if (request.responseText.indexOf('layout="loginAjaxRefreshPreventer"') != -1 &&
					   request.responseText.indexOf('loginAjaxRefreshPreventerPreventer') == -1) 
					      location.href="${pageContext.servletContext.contextPath}/web";
			});
        
      /*   $('.ui-layout-pane')
        	.css('background', 'none'); */
        	
       	 $("#jobs-create,#employees-create,#jobs-assign,#assign-routes,#bulkListUpload,#add_notification,#apply_leave,#add_form,#my_approvals").css({"display":"none"});
         	 $("#customers-create").css({"display":"none"});
         	$('#quicklinks #jobs-create').css({"display":"none"});
         	 $("#show_jobs,#employee_leaves, #employee_leaves1,#mobile_updates,#location_updates,#tabs-5,#show_map").css({"display":"none"}); 
       		
         	 /*organize*/
         	 $("#menuHover #knowledge_base,#menuHover #jobs12,#menuHover #forms12,#menuHover #customers,#menuHover #leaves,#menuHover #claims,#menuHover #asssigned_route_plan").css({"display":"none"});
         	 $("#organise_sidemenu #kb, #organise_sidemenu #jobs ,#organise_sidemenu #forms,#organise_sidemenu #customers_organise,#organise_sidemenu #leaves_organise,#organise_sidemenu #claims_organise,#organise_sidemenu #assign_route").css({"display":"none"});
         	
         	 
         	/* monitor */
         	$("#monitorMenuHover #job_reports ,#monitorMenuHover #audits ,#monitorMenuHover #form_custom_reports ,#monitorMenuHover #schedule_reports").css({"display":"none"});;
         	$("#monitorMenuHover #location_reports ,#monitorMenuHover #misc ,#monitorMenuHover #claims ,#monitorMenuHover #routes_report,#monitorMenuHover #insights,#monitorMenuHover #my_reports").css({"display":"none"});
         	$("#monitorMenuHover #employee_reports,#monitorMenuHover #reports-leavefrequency1,#monitorMenuHover #routeAndActivityReport1,#monitorMenuHover #reports-employeeleave1").css({"display":"none"});
        
         	$("#monitor_sidemenu #assign_route_monitor ,#monitor_sidemenu #claims_monitor ,#monitor_sidemenu #misc").css({"display":"none"});
           $("#monitor_sidemenu #formReport ,#monitor_sidemenu #scheduleReport ,#monitor_sidemenu #locationReports ,#monitor_sidemenu #reports-leavefrequency").css({"display":"none"});
           $("#monitor_sidemenu #reports-employeeleave ,#monitor_sidemenu #routeAndActivityReport").css({"display":"none"});
           $("#monitor_sidemenu #monitor,#monitor_sidemenu #myReports,#monitor_sidemenu #jobReport,#monitor_sidemenu #audits").css({"display":"none"});
        	
         	
         	/* config */
         	 $("#config_employees_title,#config_employees_body,#employees").css({"display":"none"});
            $("#config_workflow_title,#config_workflow_body,#workflowConfig").css({"display":"none"});
            $("#config_calendars_title,#config_calendars_body,#caleders").css({"display":"none"});
            $("#config_forms_title,#config_forms_body,#formsConfig").css({"display":"none"});
            $("#configure_nls_title,#configure_nls_body,#namedLocation").css({"display":"none"});
            $("#configure_job_title,#configure_job_body,#jobConfig").css({"display":"none"});
            $("#configure_customer_title,#configure_customer_body,#customer").css({"display":"none"});
            $("#configure_company_title,#configure_company_body,#Company").css({"display":"none"});
            $("#configure_my_account_title,#configure_my_account_body,#myAccount").css({"display":"none"});
            $("#config_notifications").css({"visibility":"hidden"});
            $("#configure_invoice_title,#configure_invoice_body,#invoices").css({"display":"none"});
            $("#configure_claims_title,#configure_claims_body,#claims").css({"display":"none"});
            $("#configure_routes_title,#configure_routes_body,#routes-settings").css({"display":"none"});
         	  /* $("#uiLayoutEast").css({"display":"none"}); */
         	 $("#notificatons").css({"display":"none"});
			show_links();
          
	});
	
	
	function hideSpoorsFooter(){
			var isHide = $("#hideFooter").attr("hide");
			if(isHide == "true"){
				$("#bodyDiv").css("height","76%");	
			}else{
				$("#bodyDiv").css("height","70%");
				$("#spoorsFooter").css("height","6%");
			}
	}
	
	function show_links(){		 
		var response =  $.ajax({
	        url: "${pageContext.servletContext.contextPath}/web/ajax/product/constants",
	        type: "GET",
	        async: false
	      }).responseText;  
	   
		
	var productConstants = JSON.parse(response);
	
	if(productConstants.isTracking){
		$("#mon #mon_href_monitor").prop("href", "${pageContext.servletContext.contextPath}/web/report/mngr/timeSpentAtGivenLocation");
		$("#org #org_href_job").prop("href", "${pageContext.servletContext.contextPath}/web/customer/search/page?fill=true");
	}
	
	if(productConstants.showJobs){
		 $("#show_jobs,#menuHover #jobs12").css({"display":"inline-block"});
		 $("#show_jobs,#organise_sidemenu #jobs").css({"display":"block"});
	}
		
	if(productConstants.showEmployeeLeaves)
		$("#employee_leaves, #employee_leaves1").css({"display":"compact"});
			
	
	if(productConstants.showMobileUpdates)
		$("#mobile_updates").css({"display":"block"});
			
	
	if(productConstants.showLocationUpdates)
		$("#location_updates").css({"display":"block"});
	   
	if(productConstants.showKnownLocations)
		$("#tabs-5").css({"display":"block"});
		
	if(productConstants.showMap)
		$("#show_map").css({"display":"block"});
	
	
	if(productConstants.createJob){
		$("#quicklinks #jobs-create").css({"display":"block"});
		$("#jobs #jobs-create").css({"display":"block"});
	}
	
	if(productConstants.addEmployee)
		$("#employees-create").css({"display":"block"});

	if(productConstants.addForm)
		$("#add_form").css({"display":"block"});

	if(productConstants.addNotification)
		$("#add_notification").css({"display":"block"});

	if(productConstants.addCustomer)
		$("#customers-create").css({"display":"block"});
	
	
	if(productConstants.applyLeave)
		$("#apply_leave").css({"display":"block"});

	if(productConstants.dispatch)
		$("#jobs-assign").css({"display":"block"});

	if(productConstants.assignRoutePlan)
		$("#assign-routes").css({"display":"block"});

	if(productConstants.myApprovals)
		$("#my_approvals").css({"display":"block"});

	if(productConstants.bulkListUpload)
		$("#bulkListUpload").css({"display":"block"});
		
	
	/* organize */
	if(productConstants.showKnowledgeBase){
		$("#menuHover #knowledge_base").css({"display":"inline-block"});
		$("#organise_sidemenu #kb").css({"display":"block"});
	}
	
	/* if(productConstants.showJobsOrganize)
		$("#menuHover #jobs").css({"display":"block"});
	 */
	 
	if(productConstants.showForms){
		 $("#menuHover #forms12").css({"display":"inline-block"});
		 $("#organise_sidemenu #forms").css({"display":"block"});
		 $("#config_forms_title,#config_forms_body").css({"display":"block"});
	}
	if(productConstants.showCustomers){
		$("#menuHover #customers").css({"display":"inline-block"});
		$("#organise_sidemenu #customers_organise").css({"display":"block"});
	}
	
	if(productConstants.showLeaves){
		$("#organise_sidemenu #leaves_organise").css({"display":"block"});
		$("#menuHover #leaves").css({"display":"inline-block"});
	}
	
	
	
	if(productConstants.showAssignedRoutePlans){
		$("#menuHover #asssigned_route_plan").css({"display":"inline-block"});
		$("#organise_sidemenu #assign_route").css({"display":"block"});	
	}
	
	/* if(productConstants.uiLayoutEast){
		$("#uiLayoutEast").css({"display":"block"});
	} */
	
	
	/* configure */
	if(productConstants.showEmployee)
           $("#config_employees_title,#config_employees_body,#employees").css({"display":"block"});
   
       if(productConstants.showWorkflow)
           $("#config_workflow_title,#config_workflow_body,#workflowConfig").css({"display":"block"});
   
       if(productConstants.showCalendars)
           $("#config_calendars_title,#config_calendars_body,#caleders").css({"display":"block"});
       
       if(productConstants.showForms){
           $("#config_forms_title,#config_forms_body,#formsConfig").css({"display":"block"});
       }
       
       if(productConstants.showNamedLocations)
           $("#configure_nls_title,#configure_nls_body,#namedLocation").css({"display":"block"});
      if(productConstants.showJob)
           $("#configure_job_title,#configure_job_body,#jobConfig").css({"display":"block"});
      if(productConstants.showCustomer)
           $("#configure_customer_title,#configure_customer_body,#customer").css({"display":"block"});
      if(productConstants.showCompany)
           $("#configure_company_title,#configure_company_body,#Company").css({"display":"block"});
      if(productConstants.showNotificationsInMyAccount){
      		$("#notificatons").css({"display":"block"});
      		$('.freqMessages').css({"display":"block"});
      }
      
      if(productConstants.showMyAccount)
           $("#configure_my_account_title,#configure_my_account_body,#myAccount").css({"display":"block"});
      if(productConstants.showInvoice)
           $("#configure_invoice_title,#configure_invoice_body,#invoices").css({"display":"block"});
     
      if(productConstants.showClaims){
            $("#configure_claims_title,#configure_claims_body").css({"display":"block"});
            $("#claims").css({"display":"inline-block"});    
            $("#menuHover #claims").css({"display":"inline-block"});
			$("#organise_sidemenu #claims_organise").css({"display":"block"});
			$("#configure_sidemenu #claims,#monitor_sidemenu #claims_monitor").css({"display":"block"});
			$("#monitorMenuHover #claims").css({"display":"inline-block"});
			
			
      }
      if(productConstants.showRoutes){
    	  $("#configure_routes_title,#configure_routes_body,#routes-settings").css({"display":"block"});
    	  $('.freqRoute').css({"display":"block"});
      }
      
      
  	/* monitor */
  	/* monitor */
    	
      if(productConstants.showJobReports){
			$("#monitor_sidemenu #jobReport").css({"display":"block"});
			$("#monitorMenuHover #job_reports").css({"display":"inline-block"});
   }
		
 	if(productConstants.showAudits){
			$("#monitor_sidemenu #audits").css({"display":"block"});
			$("#monitorMenuHover #audits").css({"display":"inline-block"});
 	}
		
 	if(productConstants.showFormReports){
			$("#monitor_sidemenu #formReport").css({"display":"block"});
			$("#monitorMenuHover #form_custom_reports").css({"display":"inline-block"});		
 	  }
		
 	if(productConstants.showLocationReports){
			$("#monitor_sidemenu #locationReports").css({"display":"block"});
			$("#monitorMenuHover #location_reports").css({"display":"inline-block"});
 	  }
		
 	if(productConstants.showMisc){
			$("#monitor_sidemenu #misc").css({"display":"block"});
			$("#monitorMenuHover #misc").css({"display":"inline-block"});	
 	}
 	/* if(productConstants.showClaims)
			$("#monitorMenuHover #claims").css({"display":"block"}); */
 	
 	if(productConstants.showRoutesReport){
			$("#monitorMenuHover #routes_report,#monitor_sidemenu #assign_route_monitor").css({"display":"block"});
			$("#monitorMenuHover #routes_report").css({"display":"inline-block"});
 	}
		
 	if(productConstants.showInsights){
			$("#monitor_sidemenu #monitor").css({"display":"block"});
			$("#monitorMenuHover #insights").css({"display":"inline-block"});
 	}
		
 	if(productConstants.showMyReports){
			$("#monitor_sidemenu #myReports").css({"display":"block"});
			$("#monitorMenuHover #my_reports").css({"display":"inline-block"});
 	}
 	
 	if(productConstants.showScheduleReports){
		$("#monitor_sidemenu #scheduleReport").css({"display":"block"});
		$("#monitorMenuHover #schedule_reports").css({"display":"inline-block"});
 	}
	
 	
 	if(productConstants.showEmployeeReports)
			$("#monitorMenuHover #employee_reports").css({"display":"inline-block"});
		
 	if(productConstants.showEmployeeLeaveFrequencyReport){
			$("#monitor_sidemenu #reports-leavefrequency").css({"display":"block"});
			$("#monitorMenuHover #reports-leavefrequency1").css({"display":"inline-block"});
 	}
		
 	if(productConstants.showEmployeeLeaveReport){
			$("#monitor_sidemenu #reports-employeeleave").css({"display":"block"});
			$("#monitorMenuHover #reports-employeeleave1").css({"display":"inline-block"});
 	}
		
 	if(productConstants.showEmployeeActivityReport){
			$("#monitor_sidemenu #routeAndActivityReport").css({"display":"block"}); 
			$("#monitorMenuHover #routeAndActivityReport1").css({"display":"inline-block"});
 	}

	}
	
	
</script>



</head>
<body style="padding: 0px;margin: 0px;height: 100%;">
		<!-- <div style="height:10%"> -->
		<div style="height: 64px">
			<tiles:insertAttribute name="nav-menu-content" />
		</div>
		<div id="bodyDiv" style="height:calc( 100% - ( 64px + 60px  ) )">
		  <%--  <h1 style="font-size:15px;font-weight:normal;padding-left: 10px;font-weight: bold;" class="title" id="title-content">
					<tiles:insertAttribute name="title-content" />
			</h1> --%>
			 <div class="pageBodyOld">
				<tiles:insertAttribute name="body-content" />
				
				<!-- some js interupting when following script code is placed in  head tag of this jsp-->
				<!--Date: 2016-03-29
										function Purpose: Enter key for filters  
										Resource: Deva -->
					<script>
						
						$('#formsLeftPane,#leavesLeftPane,#notificationLeftPane,#jobsLeftPane,#routesLeftPane,#eventsLeftPane').
							bind('keydown',"*", function(e) {
							var key = e.which;
							if (key == 13) // the enter key code
							{
								$('input[name = search]').click();
								return false;
							}
							
						});
		
					</script>
			</div> 
<!-- 			<div><i class="fa fa-xs fa-ellipsis-h" id="hideFooter"></i></div> -->
		</div>

		<div id="spoorsFooter" style="height:41px; width: 100%;background-color:#495456;position: fixed;bottom: 0px; " >
			 <tiles:insertAttribute name="footer-content" /> 
		</div>
</body>
