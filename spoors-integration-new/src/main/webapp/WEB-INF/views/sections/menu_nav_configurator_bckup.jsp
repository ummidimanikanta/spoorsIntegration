<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib prefix="sec" uri="http://com.spoors.effort/custom/jsp/taglib/security"%> --%>
<%@ page session="true"%>
<%@ page trimDirectiveWhitespaces="true"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/jquery.sticky.min.css" />
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/animate.css">
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery.sticky.min.js"></script>

<style>
.list_item {
	display: inline;
	padding-left: 3%;
	padding-right: 3%;
}
.list_item .title {
	display: inline;
    font-family: Lato,sans-serif;
    font-style: italic;
    font-size: 13px;
}
.ui-dialog-titlebar {
  background-color: #2a3234;
  background-image: none;
  color: white;
  font-size: 13px;
  font-family: "Lucida Sans Unicode"
}
.ui-widget-header {
font-weight:100;
}
.ui-widget-header .ui-icon {
background-color:white;
}
.menu_top {
	height: 20px;
	background: url('${pageContext.servletContext.contextPath}/resources/img/Sign_in_bar.png') repeat-x scroll 0% 0% / 100% auto transparent;
	width: 100%;
	font-size: 15px;
	text-transform: capitalize;
	/* margin-left: 2px; */
}

.popover, .popover-content{
	background: #183442;
	padding:2px;
}

.popover{	
	width: 500px;
	padding: 8px 3px;
}

.popover.bottom .arrow:after {
  border-bottom-color: #183442;/* #E6E6E6; */
}


.noti_bubble {
    position:absolute;
    top: -6px;
    right:0px;
    padding:1px 2px 1px 2px;
    background-color:red;
    color:white;
    font-weight:bold;
    font-size:0.75em;
    border-radius:15px;
    box-shadow:1px 1px 1px gray;
    display: none;
}

.navbar{
  position:relative;min-height:17px;
  margin-bottom:0px;
  border:1px solid transparent; 
  /* height: 31px; */
  border-radius:0px;
  }
  .nav>li {
   border-right: 1px solid #606161;
  }
  .nav>li>a {
  position: relative;
  display: block;
  height: 30px;
  padding: 5px 10px;
}

.navbar-inverse .navbar-nav>li>a {
  color: #e7ffff;
  font-style: normal;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 12px;
 text-transform: uppercase;
 
}
.dropdown-menu>li>a{
  font-style: normal;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 12px;
 text-transform: uppercase;
 
}

.navbar-inverse .navbar-nav>.active>a{
background: none;
border-bottom: 3px solid #fdc12b;
color: #fdc12b;
}

.navbar-inverse{
background: #183442;
}
.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:focus, .navbar-inverse .navbar-nav>.open>a:hover{
background: none;
}
.navbar-inverse .navbar-nav>.active>a, .navbar-inverse .navbar-nav>.active>a:focus, .navbar-inverse .navbar-nav>.active>a:hover{
background: none;
}

.navbar-inverse .navbar-toggle:focus, .navbar-inverse .navbar-toggle:hover {
  background: none;
}

.glyphicon-spin {
			    -webkit-animation: spin 500ms infinite linear;
			    animation: spin 500ms infinite linear;
			}
			

  </style>
 
 
<script>



 
var homeMenuConfiguration = JSON.parse('${sessionData.homeMenuConfigJson}');

$(document).on('click','#feedback',function(){
	var text=$(this).val();
	var textAreaId=this.id;
	
		var formulaTextArea = $(this);
		var formula = formulaTextArea.val();
		$('#ta').remove();
		formula='';
		var divMain = $('<div style="display: inline-block;color:#1f2228;"></div');
		var ul = $('<ol style=" float:left;"></ol>');
		var fEdit = $('<textarea class="form-control" id="ta" rows="6" cols="10" style="width:550px;font-size:15px;">'+text+'</textarea>');
		$('.formFields').each(function(){
			$('.sectionItem, .item', $(this)).each(function(){
				var exp = $('.expression', this).val();
				var label = $('.label', this).val();
				var fieldType = $('.fieldType', this).val();
				var computedField = $('.computedField', this).val();
				
				if(fieldType == 2 && computedField == 0){
					var li = $('<li class="badge">'+label+'&nbsp;('+exp+')</li>');
					li.click(function(){
						var f = fEdit.val()+exp;
						fEdit.val(f);
					});
					
					ul.append(li);
				}
				
			});
		});
		
		var opts="";
		var ok = $('<div style="padding-left:390px"><button class="btn btn-sm btn-spoors-action" id="doneButton" style="font-size:15px;clear: both;">Submit feedback</button></div>');
		ok.click(function(){
			
		if(fEdit.val()!='')	{
		var t=fEdit.val();
		var extraText="EFFORT_NEW_UI ";
		var finalText = extraText+t;
		var response=submitFeedBack(finalText);
		dialog.dialog("close");
		alert("Thanks for your feedback");
		
		}
			dialog.dialog("close");
		});
		
		divMain.append(fEdit);
		divMain.append(ok);
		
		var dialog = $('<div title="Please give your feedback" class="heading" style="overflow: auto;"></div>').dialog(
				{
					width: 600,
					height: 250,
					modal: true
				}
		).append(divMain);
		return false;
	});
	
	
	function displayMenuItemsInOrder(homeMenuConfiguration){
		var menuItems = homeMenuConfiguration.menuItems;
		var enableItemsCount =0;
		
		var extraItemsList = "";
		
		for(var i=0; i< menuItems.length; i++){
			var displayOrder = menuItems[i].displayOrder;
			var moduleId = menuItems[i].moduleId;
			var visible = menuItems[i].visible;
			var customEntitySpecId = menuItems[i].customEntitySpecId;
			var moduleName = menuItems[i].moduleName;
			var formSpecId = menuItems[i].formSpecId;
			var quickLinkUrl = menuItems[i].quickLinkUrl;
			var menuHtml = "";
			
			if(!visible){
				continue;
			}
			
			switch(moduleId){
			
				
				/* case 1:  menuHtml = '<sec:authorize access="hasPermission(0,\'foremp\',\'EMP_JOB_READ\') or hasPermission(0,\'foremp\',\'COMP_EMP_JOB_INVITATION_READ\')">'+
										'<li id="org"><a href="${pageContext.servletContext.contextPath}/web/job/all/search/page">'+
											'<c:choose><c:when test="${sessionData.productConstants.isService}"><span>${sessionData.companyLabel.jobsLabelPlural}</span></c:when><c:otherwise><span>${sessionData.companyLabel.jobsLabelPlural}</span></c:otherwise></c:choose>'+
										'</a></li>'+
									'</sec:authorize>';
									enableItemsCount++;
									break; */
				
				case 2:  menuHtml = '<li id="work"><a href="${pageContext.servletContext.contextPath}/configure/workspec/service/work/search/page">'+
										'${sessionData.companyLabel.workLabel} Processes</a></li>';
										enableItemsCount++;
									break;
				
				case 3:  menuHtml = '<li id="employees"><a href="${pageContext.servletContext.contextPath}/configure/employee/configurations">'+
										'${sessionData.companyLabel.employeesLabelPlural}</a></li>';
									enableItemsCount++;
									break;
				
				case 4:  /* menuHtml = '<li id="forms"><a href="${pageContext.servletContext.contextPath}/configure/formSpec/cards/view/all/page?fill=true">'+
											'${sessionData.companyLabel.formsLabelPlural}</a></li>'; */
							menuHtml = '<li id="forms"><a href="${pageContext.servletContext.contextPath}/configure/formSpec/cards/view/all/page?fill=true">'+
											'${sessionData.companyLabel.formsLabelPlural}</a></li>';
										enableItemsCount++;
										break;
				
				/* case 6:  menuHtml = '<li id="routes"><a class="tzoUrl" href="${pageContext.servletContext.contextPath}/web/route/assign/search/page/new/-330">'+
												'Routes</a></li>';
									enableItemsCount++;
									break; */
				
				/* case 7:  menuHtml = '<li id="userSettings">'+					
										'<a href="${pageContext.servletContext.contextPath}/map/secure/plot/lastKnown/-330/?showLastKnown=true">Locate</a>'+
									'</li>';
									enableItemsCount++;
									break; */
				
			/* 	case 8:  menuHtml = '<li id="dispatch">	<a 	href="${pageContext.servletContext.contextPath}/web/job/assign?tzo=-330">'+
											'Dispatch</a></li>';
									enableItemsCount++;
									break; */
				
				case 9:  menuHtml = '<sec:authorize access="hasPermission(0,\'foremp\',\'COMP_EMP_CUSTOMER_READ\')">'+
									'<li id="customers"><a href="${pageContext.servletContext.contextPath}/configure/customer/configurations">'+
									'${sessionData.companyLabel.customersLabelPlural}</a></li></sec:authorize>';
									enableItemsCount++;
									break;
									
				/* case 10:  menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/workFlow/workFlowApprovalStatus?fill=true">My&nbsp;Approvals</a></li>';
									enableItemsCount++;
									break; */
				
				/* case 11:  menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/job/auto/assign?tzo=-330">Auto&nbsp;Dispatch</a></li>';
									enableItemsCount++;
									break; */
				
			/* 	case 12:  menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/knowledgebase/manage">${sessionData.companyLabel.knowledgeBaseLabelPlural}</a></li>';
									enableItemsCount++;
									break; */
				
			/* 	case 13:  menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/notifications/specs/all">Notifications</a></li>';
										enableItemsCount++;
										break; */
										
				/* case 14:  menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/messages/all">Send&nbsp;Message</a></li>';
										enableItemsCount++;
										break; */
										
				/* case 15:  menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/leave/mngr/search/allLeaves/page/-330">Leaves</a></li>';
										enableItemsCount++;
										break; */
				
				/* case 16:  menuHtml = '<li><a class="tzoLink2" href="${pageContext.servletContext.contextPath}/web/claim/my/multi/approve">Claims</a></li>';
										enableItemsCount++;
										break; */
				
				/* case 17: var dailyPlanType = ${sessionData.dailyPlanType};
					     if(dailyPlanType == 1){
					      menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/dayPlanners">Day Plans</a></li>';
										enableItemsCount++;
										break;
					     }else{
					    	    //menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/ext/monthPlanners">Month Plans</a></li>';
					    	    menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/ext/monthPlanApproval">Month Plans</a></li>';
								enableItemsCount++;
								break;
					     } */
					     
				/* case 34: menuHtml = '<sec:authorize access="hasPermission(0,\'foremp\',\'EMP_CUSTOM_ENTITY_VIEW\') ">'+
										'<li><a class="tzoLink2" href="${pageContext.servletContext.contextPath}/web/ext/custom/entities/data/all/'+customEntitySpecId+'">'+moduleName+'</a></li>'+
									'</sec:authorize>';
									enableItemsCount++;
									break; */
				
				 case 24:  menuHtml = '<li id="listMenu"><a href="${pageContext.servletContext.contextPath}/configure/entityspec/cards/all">${sessionData.companyLabel.listsLabelPlural}</a></li>';
										enableItemsCount++;
										break; 
										
				/* case 36: menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/work/search/page/new/'+customEntitySpecId+'">'+moduleName+'</a></li>';
										enableItemsCount++;
										break;
				case 37: menuHtml = '<li><a href="${pageContext.servletContext.contextPath}/web/form/data/all/'+formSpecId+'">'+moduleName+'</a></li>';
										enableItemsCount++;
										break;
				case 38: menuHtml = '<li><a href="'+quickLinkUrl+'" target="_blank">'+moduleName+'</a></li>';
										enableItemsCount++;
										break; */
			
			}
			
			
			if(enableItemsCount <=8){
				$("#orderedUl").append(menuHtml);
			}else{
				extraItemsList += 	menuHtml;
			}
			
		}
			
		/* var isRoot = ${sessionData.rootEmployee};
		if(isRoot)
		{
			var reportAndAdminHtml = '<li id="reports"><a class="tzoLink" href="${pageContext.servletContext.contextPath}/web/reports">Reports</a></li>'+
				'<li id="config"><a href="${pageContext.servletContext.contextPath}/web/configure">Admin</a></li>';
			$("#orderedUl").append(reportAndAdminHtml);
		}else{
			
			var reportAndAdminHtml = '<li id="reports"><a class="tzoLink" href="${pageContext.servletContext.contextPath}/web/reports">Reports</a></li>'+
			 '<sec:authorize access="hasPermission(0,\'foremp\',\'EMP_VIEW_ADMIN\')">'+
				'<li id="config"><a href="${pageContext.servletContext.contextPath}/web/configure">Admin</a></li></sec:authorize>';
			$("#orderedUl").append(reportAndAdminHtml);
		} */
			
								 
		
		
		if(enableItemsCount > 8){
			var extraItems = '<li class="dropdown" id="dots"><a class="dropdown-toggle" data-toggle="dropdown" href="#">&#9679;&#9679;&#9679;</a>'+
							 '<ul class="dropdown-menu">'+extraItemsList+'</ul></li>';
			$("#orderedUl").append(extraItems);
		}
		

		$('.tzoLink2').each(function() {
   	    	try{
   	    		var href = $(this).attr('href');
   	    		if(href.indexOf(new Date().getTimezoneOffset())==-1){
	   	    		href = href + '/' + new Date().getTimezoneOffset();
	   	    		
	   	    		if(href.indexOf("claim/mngr/search/page") != -1){
	   	    			href=href+'?fill=true';
	   	    		}else if(href.indexOf("/route/assign/search/page/") != -1){
	   	    			href=href+'?fill=true';
	   	    		}
	   	    		
	   	    		$(this).attr('href', href);
   	    		}
   	    	} catch (error){
   	    		
   	    	}
   	    });
		
		$('.tzoLink').each(function() {
   	    	try{
   	    		
   	    		var href = $(this).attr('href');
   	    		if(href.indexOf("?tzo")==-1&&href.indexOf("&tzo")==-1){
   	    		if(href.indexOf("?") !== -1){
   	    			href = href + '&tzo=' + new Date().getTimezoneOffset();
   	    		} else {
   	    			href = href + '?tzo=' + new Date().getTimezoneOffset();
   	    		}
   	    		$(this).attr('href', href);
   	    		}
   	    	} catch (error){
   	    		
   	    	}
   	    });
	}
	
	
	
	function submitFeedBack(c){
		item={};
		item['feedback']=c;
		var response=$.ajax({
		        url: "${pageContext.servletContext.contextPath}/web/saveFeedBack",
		        type: "POST",
		        data:"json="+encodeURIComponent(JSON.stringify(item)),
		        async: false                                
		}).responseText;
		
		return response;
		
	}
	
	
	function adjustMenuBar(){
		hWidth = $("#header_menu").width();
		liWidth = $("#orderedUl").width();
		
		console.log("Window Width: "+hWidth);
		console.log("Menus Width: "+liWidth);
		
		liPer = (liWidth/hWidth)*100;
		
		console.log("Menus Percentage: "+liPer);
		var switchPath = '';
		if('${sessionData.webUser.hasWebAndConfiguratorAppAccess}' == 'true'){
			if('${sessionData.webUser.oppUser}' == 'true')
			{
				switchPath = '<li><a href="${pageContext.servletContext.contextPath}/web?isOpp=true">Switch to Web Panel</a></li>';
			}else
			{
				switchPath = '<li><a href="${pageContext.servletContext.contextPath}/web?fromLogin=true">Switch to Web Panel</a></li>';
			}
			
		} 
		
		if(liPer > 75){
			
		var	accountInfoHtml = '<li class="dropdown" title="${sessionData.webUser.empName}" style="border-right:0px;" id="logout_id"><a class="dropdown-toggle" data-toggle="dropdown" href="#">'+
			'<span  class="glyphicon glyphicon-user " id="logoutGear" style="color:#E7FFFF;font-size: 1.2em;padding-top: 2px;"></span></a>'+
'<ul class="dropdown-menu">'+
'<li sytle="background: #183442; border-bottom: 1px solid #183442;"><a><span class="glyphicon glyphicon-user" style="color:#183442;font-size: 1.2em;padding-top: 2px;"></span>&nbsp;${sessionData.webUser.empName}</a></li>'+
'<li><a href="${pageContext.servletContext.contextPath}/web/setting/webuser/update">Change Password</a></li>'+
'<li><a href="${pageContext.servletContext.contextPath}/configure/employee/mycalendar/-330">My Calendar</a></li>'+
'<li><a href="#" id="feedback">Feedback</a></li>'+
 switchPath +
'<li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>'+
'</ul></li>';

$("#accountInfo").append(accountInfoHtml);
			
		}else{
			
			var	accountInfoHtml = '<li class="dropdown"  style="border-right:0px;" id="logout_id"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> <span class="glyphicon glyphicon-user" style="color:#FFF;font-size: 1.2em;padding-top: 2px;"></span>${sessionData.webUser.empName}'+
							'<span class="caret"></span></a>'+
			'<ul class="dropdown-menu">'+
				'<li><a href="${pageContext.servletContext.contextPath}/web/setting/webuser/update">Change Password</a></li>'+
				'<li><a href="${pageContext.servletContext.contextPath}/configure/employee/mycalendar/-330">My Calendar</a></li>'+
				'<li><a href="#" id="feedback">Feedback</a></li>'+
				 switchPath +
				'<li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>'+
			'</ul></li>';
			
			$("#accountInfo").append(accountInfoHtml);
			
			logoutDiv_Width = $("#logout_id").width();
			console.log("logoutDiv_Width: "+logoutDiv_Width);
			logoutDiv_Per = (logoutDiv_Width/hWidth)*100;
			console.log("logoutDiv_Per: "+logoutDiv_Per);
			
			if(logoutDiv_Per > 20){
				
				
				
				var accountInfoHtml = '<a class="dropdown-toggle" data-toggle="dropdown" href="#">'+
				'<span class="glyphicon glyphicon-user " id="logoutGear" style="color:#E7FFFF;font-size: 1.2em;padding-top: 2px;"></span></a>'+
	'<ul class="dropdown-menu">'+
	'<li sytle="background: #183442; border-bottom: 1px solid #183442;"><a ><span class="glyphicon glyphicon-user" style="color:#183442;font-size: 1.2em;padding-top: 2px;"></span>&nbsp;${sessionData.webUser.empName}</a></li>'+
	'<li><a href="${pageContext.servletContext.contextPath}/web/setting/webuser/update">Change Password</a></li>'+
	'<li><a href="${pageContext.servletContext.contextPath}/configure/employee/mycalendar/-330">My Calendar</a></li>'+
	'<li><a href="#" id="feedback">Feedback</a></li>'+
	 switchPath +
	'<li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>'+
	'</ul>';
	
				$("#logout_id").html(accountInfoHtml);
				$("#logout_id").attr("title","${sessionData.webUser.empName}");
				
			}
			
		}
	}
	$(document).ready(function() {
		//popNotifications();
		var IDLE_TIMEOUT = 120; //seconds
        var _idleSecondsCounter = 0;
        document.onclick = function() {
            _idleSecondsCounter = 0;
        };
        document.onmousemove = function() {
            _idleSecondsCounter = 0;
        };
        document.onkeypress = function() {
            _idleSecondsCounter = 0;
        };
        window.setInterval(CheckIdleTime, 1000);
       
        function CheckIdleTime() {
            _idleSecondsCounter++;
        }
				setInterval(function() {
				if (_idleSecondsCounter < IDLE_TIMEOUT) {
					popNotifications();
				}
		}, 60000);
		
		//displayMenuItemsInOrder(homeMenuConfiguration);
		adjustMenuBar();
		/* $("#logoutGear").click(function(){
			$("#logoutGear").addClass("glyphicon-spin");
			 window.setTimeout( function() {
				 $("#logoutGear").removeClass("glyphicon-spin");
		        }, 100 );
		}) */
		$('.menuHover li').css({"padding-top":"3px","padding-bottom":"3px"});
		$.ajax({
			type: 'GET',
			async :true,
			url:"${pageContext.servletContext.contextPath}/web/ajax/comapny/subscription/" + new Date().getTimezoneOffset(), 
			success: function(data){
		    	var result = eval(data);
		    	var gracePeriod = 30;
		    	if(result.length > 0){
		    		if(!result[3]){
			    		if(result[2] == 1){
				    		if(result[1].split(' ')[0]<=7){ //checking whether the value is less than 7 days
				    			if(result[1].split(' ')[0] == 2){
				    				$("#expiry").html('Your Account expires in '+result[1].split(' ')[0]+' days');
				    			}else{
				    				$("#expiry").html('Your Account expires in '+result[1]);	
				    			}
				    		}
			    		} else {
			    			var differenceBetweenExpiryDateAndGracePeriod = gracePeriod - result[1].split(' ')[0];
			    			if(differenceBetweenExpiryDateAndGracePeriod > 0){
			    				if(differenceBetweenExpiryDateAndGracePeriod == 1){
			    					$("#expiry").html('Clear dues in 1 day to enjoy uninterrupted service!');
			    				}else{
			    					$("#expiry").html('Clear dues in '+differenceBetweenExpiryDateAndGracePeriod+' days to enjoy uninterrupted service!');
			    				}
			    			}else{
			    				$("#expiry").html('Clear dues in 0 days to enjoy uninterrupted service!');
			    			}
			    		}
		    		}else{
			    			if(result[2] == 1){
					    		if(result[1].split(' ')[0]<=7){ //checking whether the value is less than 7 days
					    			if(result[1].split(' ')[0] == 2){
					    				$("#expiry").html('Your Account expires in '+result[1].split(' ')[0]+' days');
					    			}else{
					    				$("#expiry").html('Your Account expires in '+result[1]);	
					    			}
					    		}
				    		}else {
			    			   if(result[1].split(' ')[0]<=7){
				    				if(result[1].split(' ')[0] == 2){
					    				$("#expiry").html('Your Account expired '+result[1].split(' ')[0]+' days ago.');
					    			}else{
					    				$("#expiry").html('Your Account expired '+result[1]+' ago.');	
					    			}
				    		}else{
				    			$("#expiry").html('Thanks for completing the free trial. Please contact sales for next steps.');
				    		}
			    		}
		    		}
		    		$('#expiry').css("color","#ffc600");
	    		}
    	},
		  error: function (err) {
		        console.log("AJAX error in comapnySubscription" + JSON.stringify(err, null, 2));
		   }
	});
		var length=$('.myReportsOnHover >li').length;
		if(length<2){
			$('.myReportsLabel').hide();
		}
		
		 $('a.forms-data-all1').each(function() {
		        var href= $(this).attr('href');
		        $(this).attr('href','javascript:void(0);');
		        $(this).attr('jshref',href);
		    });
		    $('a.forms-data-all1').bind('click', function(e)
		    {
		        e.stopImmediatePropagation();          
		        e.preventDefault();
		        e.stopPropagation();
		        var href= $(this).attr('jshref');
		        if ( !e.metaKey && e.ctrlKey )
		            e.metaKey = e.ctrlKey;
		        if(!e.metaKey)
		        {
		            location.href= href;
		        }
		        return false;
		});
		
		  
		    var url = window.location.href;
		    var host = window.location.host;
		    if(url.indexOf('${pageContext.servletContext.contextPath}/configure/admin') != -1){
		    	selectclickedMenu('config');
		    }else if( url.indexOf('${pageContext.servletContext.contextPath}/configure/workspec') != -1 ){
			    	selectclickedMenu('work');
		    }else if(url.indexOf('isCustomer=true') !=-1 && (url.indexOf('${pageContext.servletContext.contextPath}/configure/formSpec') != -1 )){
		    	selectclickedMenu('customers');
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/formSpec') != -1){
		    	selectclickedMenu('forms');		    	
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/customer') != -1){
		    	selectclickedMenu('customers');
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/employee') != -1 &&
		    		url.indexOf("/mycalendar/-330") == -1){
		    	selectclickedMenu('employees');
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/customentityspec') != -1){
		    	selectclickedMenu('customEntity');
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/entityspec/masters') != -1){
		    	selectclickedMenu('entitySpecMasters');
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/territory/') != -1){
		    	selectclickedMenu('territory');
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/settings/') != -1){
		    	selectclickedMenu('settings');
		    }else if(url.indexOf('${pageContext.servletContext.contextPath}/configure/entityspec/') != -1 ||
		    		url.indexOf('${pageContext.servletContext.contextPath}/configure/entity/') != -1){
		    	selectclickedMenu('listMenu');
		    }
		    else{
		    	var hmUrl="http://"+host+"${pageContext.servletContext.contextPath}/configure/home";
		    	var hmUrl1="https://"+host+"${pageContext.servletContext.contextPath}/web";
		    	if((url == hmUrl || url == hmUrl1 || url.indexOf('${pageContext.servletContext.contextPath}/opp/x') != -1 
		    			|| url.indexOf('${pageContext.servletContext.contextPath}/configure') != -1 || 
		    			url.indexOf('${pageContext.servletContext.contextPath}/web?ifd=0') != -1) && url.indexOf("/mycalendar/-330") == -1){
		    		  selectclickedMenu('home');
		    	}
		    } 
		   
		    function selectclickedMenu(id) {
				$("#header_menu").children("li").removeClass("active");
				$("#"+ id).addClass("active");
			}
		    
		    $('#header_id').hover(
		            function () {
		            
		            }, 
		            function () {
		              $(".dropdown").removeClass("open");
		            }
		         );
	});

	
	function popNotifications()
	{
	   try{	
		var responseList =null;
		$.ajax({
			  type: "GET",
			  async :false,
			  url: "${pageContext.servletContext.contextPath}/web/ext/ajax/get/activity/stream",
			  success: function(response){
				  if(response.length >0){
					  responseList = response;
					}
			  }
	   	 });
		
		if (Notification.permission === "granted") {
		    // If it's okay let's create a notification
			    if(responseList!=null)
			    {
			    	if(responseList.length <=3)
			    	{
					    for(var i=0; i<responseList.length; i++){
					    	var htmltext=responseList[i].message;
					    	var $html = $("<div/>").html(htmltext);
					    	$html.find("a").each(function() {
					    		$(this).replaceWith($(this).text());
					    	});
					    	responseList[i].message= $html.text();
					    	var notification = new Notification("Spoors Notification",{ icon: '${pageContext.servletContext.contextPath}/resources/img/spoors-logo1.png',body:responseList[i].message});  		 
		    	 		}
			    	}else
			    	{
			    		var notification = new Notification("Spoors Notification",{ icon: '${pageContext.servletContext.contextPath}/resources/img/spoors-logo1.png',body:'You have '+responseList.length+' new Notifications'});
			    	}
		    	 	responseList =null;
			    }
		    }
		
		  // Otherwise, we need to ask the user for permission
		  else if (Notification.permission !== "denied") {
		    Notification.requestPermission(function (permission) {
		      // If the user accepts, let's create a notification
		      if (permission === "granted") {
		    	  if(responseList.length <=3)
			    	{
					    for(var i=0; i<responseList.length; i++){
					    	var htmltext=responseList[i].message;
					    	var $html = $("<div/>").html(htmltext);
					    	$html.find("a").each(function() {
					    		$(this).replaceWith($(this).text());
					    	}); 
					    	responseList[i].message= $html.text();
					    	var notification = new Notification("Spoors Notification",{ icon: '${pageContext.servletContext.contextPath}/resources/img/spoors-logo1.png',body:responseList[i].message});  		 
		    	 		}
			    	}else
			    	{
			    		var notification = new Notification("Spoors Notification",{ icon: '${pageContext.servletContext.contextPath}/resources/img/spoors-logo1.png',body:'You have '+responseList.length+' new Notifications'});
			    	}
		      }
		    });
		  }
	   }catch(e){
		   
	   }
	}
	
	function logout_menu() {

		var str = "<div id='log_pop' class='log_pop_up_show'><div class='arrow-up' style='margin-left:50px;'></div>"
				+ "<div class='log_started_pop_up'><div class='titles'><div class='head1'></div><div class='logout'>"
				+ "<table><tr><td><a href='#'>My Account</a></td></tr><tr><td><a href='${pageContext.servletContext.contextPath}/logout'>Log Out</a></td></tr><tr><td><a href='#'>Feedback</a></td></tr>"
				+ "</table></div></div></div></div>";
		$("body").append(str);
		console.log("logout");
	}

	
</script>

<script>
//,#log_pop
$( "#logout_table").hover(
		function() {
			    $( "#logout_menu").css( "display","block");
			    
			  }, function() {
			        $( "#logout_menu" ).css( "display","none");  
			 }
		);

/* Notifications Popup */

$(document).ready(function() {	
	var IDLE_TIMEOUT1 = 120; //seconds
    var _idleSecondsCounter1 = 0;
     $(window).click(function() {
        _idleSecondsCounter1 = 0;
    });
    $(window).mousemove(function() {
        _idleSecondsCounter1 = 0;
    });
    $(window).keypress(function() {
        _idleSecondsCounter1 = 0;
    }); 
    window.setInterval(CheckIdleTime1, 1000);
   
    function CheckIdleTime1() {
        _idleSecondsCounter1++;
       // console.log(_idleSecondsCounter1);
    }
    
    setInterval(function() {
		//console.log(_idleSecondsCounter1 < IDLE_TIMEOUT1);
		if (_idleSecondsCounter1 < IDLE_TIMEOUT1) {
				testAjaxWebService();
				calWorkInvitationAjax();
			}
	}, 60000);
});

$(document).ready(function() {	
	var notificationsCount=0;
	var notificationId=null;
	var url;
	var isOpen=false;
	(function worker() {
		
		testAjaxWebService();
		
		$.ajax({
			url: '${pageContext.servletContext.contextPath}/web/ajax/notifications/count', 
		    success: function(data) {
		    			data=JSON.parse(data);
		    			notificationsCount=data.count;
		    			notificationId=data.notificationId;
		    			$('.noti_bubble').html(notificationsCount);
		    			alignNotificationsBubble(notificationsCount);
		    		 },
		    complete: function() {
		      			// Schedule the next request when the current one's complete
		      			setTimeout(worker, 30*1000);
		    		  }
		});
	})();
	var popover=$("#notifications").popover({
		html: 'true',
		content: '<div id="popoverContent" style="background:#183442"><div id="progress" style="margin-top: 20px; margin-bottom: 20px; font-weight: bold; font-size: small;background:#183442;color:#fff">\
			   <img src="${pageContext.servletContext.contextPath}/resources/img/busy.gif" /> Loading...\
				</div></div>'
		}).on('show.bs.popover', function() {
			isOpen=true;
			$(document).click(bindPopupClose);
			$.ajax({
		     	url : '${pageContext.servletContext.contextPath}/web/ajax/notifications/1', 
		      	success : function(response) {
		    	  			$("#popoverContent").html(response);
		    	  			if(isOpen){
		    	  				$('.noti_bubble').css({'display': 'none'});
		    	  				if(notificationsCount > 0)
		    	  					updateNotifications(notificationId);
		    	  			}
		    	  				
		      			  },
		      	error: function (xhr, ajaxOptions, thrownError) {
		    	  			$("#popoverContent").html("<p style='color:#fff'>Unable to load notifications</p>");
		        	   }
		   });
		});
	  	
		popover.on('hide.bs.popover', function() {
		  	$(document).unbind('click', bindPopupClose);
		  	isOpen=false;
	  	});
		
		$('#notifications').click(function (e) {
			e.stopPropagation();
		});
		
		
		
        calWorkInvitationAjax();
		
		
		
});


function calWorkInvitationAjax(){
	
	$.ajax({
		url: '${pageContext.servletContext.contextPath}/web/ajax/work/invitations/count/for/me', 
	    success: function(data) {
	    		
	    	
	    	
	    			if(data !='' && data !=null && typeof data != 'undefined'){
	    				showWorkInvitationsPopUp(data);
	    			}
	    				
	    		 }
	});
	
}


function showWorkInvitationsPopUp(data){
	
	workInvitationsCount = data['workInvitation'];
	var elaspedTime = data['invitationElaspedTime'];
	
	$.sticky.dequeue();
	var invitationPopUpBody = '';
	var elaspedTimeHtml = '';
	if(workInvitationsCount > 0 && workInvitationsCount > 1){
		invitationPopUpBody = "You have "+ workInvitationsCount + " ${sessionData.companyLabel.workLabel} Invitations<br>";
		elaspedTimeHtml = "from "+elaspedTime+" ago.<br>";
	}else if(workInvitationsCount > 0){
		invitationPopUpBody = "You have "+ workInvitationsCount + " ${sessionData.companyLabel.workLabel} Invitation<br>";
		elaspedTimeHtml = "from "+elaspedTime+" ago.<br>";
	}
	
	invitationPopUpBody = "<div style='margin-bottom:20px;'>"+invitationPopUpBody+" "+elaspedTimeHtml+"<div style='float:right;'><a class='btn btn-sm btn-spoors-action' onClick='removeWorkInvitationPopUp()' >Ignore</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.servletContext.contextPath}/web/ext/view/myWork/invitations' target='_blank' class='btn btn-sm btn-spoors-main'>View</button></div></div>"
	
		
		if(workInvitationsCount > 0){
		  $.sticky({
		        //icon         : '${pageContext.servletContext.contextPath}/resources/imagebuttons/otherProvider.png',
		        title        : '${sessionData.companyLabel.workLabel} Invitation',
		        body         : invitationPopUpBody,
		        position     : 'bottom-right',
		        width        : '300px',
		        useAnimateCss: true,
		        onShown      : function(id){
		          console.log('shown', id);
		        },
		        onHidden     : function(id){
		          console.log('hidden', id);
		        }
		      });
		}
	
}

function removeWorkInvitationPopUp(){
	$.sticky.dequeue();
}

function bindPopupClose(e) {
	if (($('.popover').has(e.target).length == 0) || $(e.target).is('.close')) {
		$('#notifications').click();
	}
}

function alignNotificationsBubble(count){
	if(count > 9999){
		$('.noti_bubble').css({'right': '-12px', 'display':'block'}); 
	}else if(count > 999){
		$('.noti_bubble').css({'right': '-6px', 'display':'block'}); 
	}else if(count > 99){
		$('.noti_bubble').css({'padding': '0px 2px 0px 2px', 'display':'block'}); 
	}else if(count > 9){
		$('.noti_bubble').css({'padding': '0px 5px 0px 5px', 'display':'block'}); 
	}else if(count > 0){
		$('.noti_bubble').css({'padding': '0px 7px 0px 7px', 'display':'block'}); 
	}else{
		$('.noti_bubble').css({'display':'none'}); 
	}
};

function updateNotifications(id){
	if(id==undefined || id==null){
		return;
	}
	$.ajax({
     	url : '${pageContext.servletContext.contextPath}/web/ajax/notificationsViews/update?notificationId='+id+'&sessionWebActionToken=<%= session.getAttribute("sessionWebActionToken") %>', 
      	success : function(response) {
      		
      			  },
      	error: function (xhr, ajaxOptions, thrownError) {
    	  			
        	   }
   	});
}

//testAjaxWebService() used to check session is active
function testAjaxWebService()
{
	$.ajax({
			url: '${pageContext.servletContext.contextPath}/ajaxWeb/testAjaxService', 
  		  statusCode: {
 			    401: function() {
 			    	var currentWindowUrl = window.location.href;
 			    	
 			    	if(currentWindowUrl && currentWindowUrl.indexOf("${pageContext.servletContext.contextPath}/opp/x") != -1)
 			    	{
 			    		window.location = "${pageContext.servletContext.contextPath}/web";
 			    	}
 			    	else
 			    	{
 			   	 		location.reload(true);
 			    	}
 			    }
 		  	}
		});
}

/* Notifications Popup */
</script>
<div id="header_id">
<nav class="navbar navbar-inverse" style="background: #254f65;">
  <div class="container-fluid">
     <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar1">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar1">
    
      <ul class="nav navbar-nav">
        <li style="border: 0;"><a href="${pageContext.servletContext.contextPath}/web?isOpp=true" style="padding: 0;"><img alt="" src="${pageContext.servletContext.contextPath}/${sessionData.webLogoUrl}" height="25px" width="100px" style="
    vertical-align: middle;padding-top: 2px;"/></a></li>
      </ul>
      
       <ul class="nav navbar-nav navbar-right">
       	<%-- <sec:authorize access="isAuthenticated()"> --%>
       	<li class="li_head_menu_top_right" style="border: 0;padding-right: 35px;padding-top: 4px;">
       		<%-- <sec:authorize access="hasPermission(0,'foremp','COMP_EMP_INVOICE_PAY')"> --%>
       			<span id='expiry' style='' style=""></span>
       		<%-- </sec:authorize> --%>
       	</li>
        <li class="li_head_menu_top_right" style="border: 0;padding-right: 15px;">
           <a href="#"  style="padding-top: 5px;">${sessionData.companyInfo.companyName}</a>
        </li>
        <%-- </sec:authorize> --%>
        <li class="li_head_menu_top_right" style="border: 0;"><a href="#"  style="padding-top: 5px;">Customer Support: ${sessionData.productConstants.customerSupport}</a></li>
      </ul>
      
    </div>
  </div>
</nav>


  <nav class="navbar navbar-inverse">
  <div class="container-fluid" id="header_menu">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav" id="orderedUl">
						<li id="home">
							<a href="${pageContext.servletContext.contextPath}/configure/home">Home</a>
						</li>
						<li id="employees">
							<a href="${pageContext.servletContext.contextPath}/configure/employee/configurations">
								${sessionData.companyLabel.employeesLabelPlural}
							</a>
						</li>
						<%-- <sec:authorize access="hasPermission(0,\'foremp\',\'COMP_EMP_CUSTOMER_READ\')"> --%>
							<li id="customers">
								<a href="${pageContext.servletContext.contextPath}/configure/customer/configurations">
									${sessionData.companyLabel.customersLabelPlural}
								</a>
							</li>
						<%-- </sec:authorize> --%>
						<li id="customEntity">
							<a href="${pageContext.servletContext.contextPath}/configure/customentityspec/configurations">
								Custom Entities
							</a>
						</li>
						<c:choose>
							<c:when test="${sessionData.rootEmployee eq true}">
							  	<li id="entitySpecMasters">
							  		<a href="${pageContext.servletContext.contextPath}/configure/entityspec/masters">
							  			Masters
							  		</a>
							  	</li>
							</c:when>
							<c:otherwise>
							 <%--  <sec:authorize access="hasPermission(0,\'foremp\',\'EMP_VIEW_ADMIN\')"> --%>
							  	<li id="entitySpecMasters">
							  		<a href="${pageContext.servletContext.contextPath}/configure/entityspec/masters">
							  			Masters
							  		</a>
							  	</li>
							 <%--  </sec:authorize> --%>
							</c:otherwise>
						</c:choose>
						<li id="listMenu">
							<a href="${pageContext.servletContext.contextPath}/configure/entityspec/cards/all">
								${sessionData.companyLabel.listsLabelPlural}
							</a>
						</li>
						<li id="forms">
							<a href="${pageContext.servletContext.contextPath}/configure/formSpec/cards/view/all/page?fill=true">
								${sessionData.companyLabel.formsLabelPlural}</a>
						</li>
						<li id="work">
							<a href="${pageContext.servletContext.contextPath}/configure/workspec/service/work/search/page">
								${sessionData.companyLabel.workLabel} Processes
							</a>
						</li>
						<li id="territory">
							<a href="${pageContext.servletContext.contextPath}/configure/territory/configurations">
								Territories
							</a>
						</li>
						<li id="settings">
							<a href="${pageContext.servletContext.contextPath}/configure/settings/configurations">
								Settings
							</a>
						</li>
						<li id="ConfigurationList">
							<a href="${pageContext.servletContext.contextPath}/configure/list">
								Configurations
							</a>
						</li>
						<li id="CallLogList">
							<a href="${pageContext.servletContext.contextPath}/integration/callLogs/list">
								Integration Call Logs
							</a>
						</li>
						<li id="CallAuditList">
							<a href="${pageContext.servletContext.contextPath}/integration/callLogs/audit/list">
								Integration Call Audit
							</a>
						</li>
						<li id="ConfigurationTransaction">
							<a href="${pageContext.servletContext.contextPath}/integration/txnHit/1/1">
								Configuration Transaction
							</a>
						</li>
						<%-- <c:choose>
							<c:when test="${sessionData.rootEmployee eq true}">
							  	<li id="config">
							  		<a href="${pageContext.servletContext.contextPath}/configure/admin">
							  			Admin
							  		</a>
							  	</li>
							</c:when>
							<c:otherwise>
							  <sec:authorize access="hasPermission(0,\'foremp\',\'EMP_VIEW_ADMIN\')">
							  	<li id="config">
							  		<a href="${pageContext.servletContext.contextPath}/configure/admin">
							  			Admin
							  		</a>
							  	</li>
							  </sec:authorize>
							</c:otherwise>
						</c:choose> --%>
			</ul>
			<ul class="nav navbar-nav navbar-right" id="accountInfo">
				<li><a id="notifications" data-placement="bottom" style="cursor:pointer; padding:5px 3px;"></a></li>
			</ul>
		</div>
  </div>
</nav>
</div>


