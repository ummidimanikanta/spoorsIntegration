<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page trimDirectiveWhitespaces="true"%>

<style>
.tmp_workspec {
	background: rgba(204, 204, 204, .5);
	width: 99%;
	height: 110%;
	background-size: 100% 100%;
	z-index: 1000;
	position: absolute;
	top: 0;
}

.tmp_workspec .workspec {
	background: white;
	position: absolute;
	width: 80%;
	margin: 0 auto;
	left: 10%;
	top: 0;
	line-height: 25px;
	padding: 10px;
	height: 85%;
	overflow-y: scroll;

}

</style>
<script type="text/javascript">
//Plug-in to fetch page data 
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};
</script>
<style>
.ontop {
	z-index: 999;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	display: none;
	position: absolute;
	border: solid 1px black;
}

#popup {
	width: 300px;
	height: 150px;
	position: absolute;
	color: #000000;
	background-color: #ffffff;
	/* To align popup window at the center of screen*/
	top: 50%;
	left: 50%;
	margin-top: -100px;
	margin-left: -150px;
	border: solid 1px black;
	padding: 10px;
}

.autocomplete-suggestions {
	z-index: 999;
	background: white;
	padding-left: 5px;
	height: 150px;
	overflow: auto;
	cursor: pointer;
}

@media ( min-width : 1550px) {
	.dataTables_wrapper {
		height: 94.5%;
	}
	.dataTables_scroll {
		height: 94%;
	}
	.dataTables_scrollBody {
		height: 93%;
	}
}

@media ( max-width : 1290px) and (max-height: 900px) {
	.dataTables_wrapper {
		height: 97%;
	}
	.dataTables_scroll {
		height: 93%;
	}
	.dataTables_scrollBody {
		height: 90%;
	}
}

@media ( max-width : 1290px) and (min-height:901px) {
	.dataTables_wrapper {
		height: 94.5%;
	}
	.dataTables_scroll {
		height: 94%;
	}
	.dataTables_scrollBody {
		height: 93%;
	}
}

@media ( min-width : 1291px) and (max-width: 1549px) {
	.dataTables_wrapper {
		height: 97%;
	}
	.dataTables_scroll {
		height: 93%;
	}
	.dataTables_scrollBody {
		height: 90%;
	}
}
</style>

<script>
	var headers = {};
	var table;
	var pxls;
	var tzo = new Date().getTimezoneOffset();

	   	$(document).ready(function() { 
		    var searchDataJson = {};
		    
		    var ht = $('#pageNew').height();
	   		ht = ht - 75;
	   		pxls = ht + "px";

	   		table =$("#example").DataTable( {
		    	"language":{"paginate":{"first":"<<","last":">>","next":">","previous":"<"}, "zeroRecords": "No records found for the searched data"},
				"pagingType":"full_numbers",
				"scrollX": true,
		    	"scrollY":pxls,
			    "scrollCollapse": true,
			    "fixedColumns":   {leftColumns: 3},
	   	        "bProcessing": true,
	   	        "bServerSide": true,
	   	        "sort": "position",
	   	        //bStateSave variable you can use to save state on client cookies: set value "true" 
	   	        "bStateSave": false,
	   	        //Default: Page display length
	   	        "iDisplayLength":10,
	   	        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
	   	        "iDisplayStart": 0,
	   	        "fnDrawCallback": function () {
	   	            //Get page numer on client. Please note: number start from 0 So
	   	            //for the first page you will see 0 second page 1 third page 2...
	   	            //Un-comment below alert to see page number
	   	        	$("#example_paginate span").empty();				       
				    $("#example_paginate span").html("<a class='paginate_button current'>"+((this.fnPagingInfo().iPage)+1)+"</a>");
				    $('.dataTables_scrollHeadInner').css({
	   	        		'padding':'0px'
	   	        	});
				    $('.dataTables_scrollBody').css({
	   	        		'max-height':''
	   	        	});
	   	        },
	   	     "fnInitComplete": function (){
	   	        	$('.dataTables_scrollHeadInner').css({
	   	        		'padding':'0px'
	   	        	});
	   	        },
		   	     'rowCallback': function(row, data, dataIndex){
		   	         // Get row ID
		   	         var rowId = data[0];
		   	     },
	   	        "sAjaxSource": "list/dataJson",
	   	     	"sServerMethod": "POST",
	   	     	"fnServerParams": function ( aoData ) {
					aoData.push( { "name": "searchJsonData", "value":JSON.stringify(searchDataJson) } );
				},
	   	        "aoColumns": [
	   	        	
				   	        	{
				   	        		"mData" : "enable or disable",
									render: function ( data, type, row ) {	
									return '<input type="checkbox" class="material-icons" value="'+row.id+'"></span>';
									},	
									className: "dt-body-center",
									orderable: false
								},
								 { "mData":   "id",
											render: function ( data, type, row ) {												
												   var str='<div >';
												   str+='<a href="${pageContext.servletContext.contextPath}/configure/navAdd?pushId='+row.id+'"><img src="${pageContext.servletContext.contextPath}/resources/imagebuttons/edit.png" title="Edit" alt="Edit" /></a>';
												   str+='<span class="dropdown" style="text-align: center;"><button style="background:none;" class="btn dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><i class="glyphicon glyphicon-option-vertical"></i></button><ul class="dropdown-menu" style="right:-80px;overflow:auto;height:83px;" aria-labelledby="dropdownMenu1">';
												   str+='<li><a href="${pageContext.servletContext.contextPath}/configure/field/mappings/'+row.id+'?triggerType='+row.triggerType+'">Mapping</a></li>';
												   str+='<li><a href="${pageContext.servletContext.contextPath}/escalations/addPage/'+row.id+'">Escalation</a></li>';
												   str+='<li><a href="#" onClick="return deleteConfiguration('+row.id+');">Delete</a></li>';
												   str+='</ul></span></div>';
												    return str;
												
											
										},
										className: "dt-body-center",
										orderable: false
									},						
									{
										"mData" : "name",
										render: function ( data, type, row ) {	
											var str='<a href="${pageContext.servletContext.contextPath}/configure/navAdd?pushId='+row.id+'&disableAllInputs=true">'+row.name+'</a>';
											
											  return str;
									},
										className: "dt-body-center",
										orderable: false												
									},
									
									{
										"mData" : "triggerType",
										render: function ( data, type, row ) {	
											var str='<div class="btn-group" role="group" aria-label="...">';
											if(row.triggerType == 1){
												  str+='FORMS';
											}else if(row.triggerType == 2){
												str+='WORKS';
											}
										      str+='</div>';
											  return str;
									},
										className: "dt-body-center",
										orderable: false
									},
									{
										"mData" : "triggerSource",
										className: "dt-body-center",
										orderable: false
									},
									{
										"mData" : "retryCount",
										className: "dt-body-center",
										orderable: false
									}
								 
	   	        ]
	   	    } );
		    
		    $('#example_length').appendTo('.dataTables_wrapper');
		    $('[name="example_length"]').css({
		        'float': 'left',
		    });
		    $("#example_length label").css({
		    	 'margin-left':'5px',
			        'margin-top':'8px'
		    });
	   	 	
	   	    if(('${error}'.length) > 0){
	   			$("#error").dialog();
	   		}
	 	   	
		/* exportFiltered only ends */
			filterListByText('txt', 'selectedForms', 'elem');
			filterListByText('routefilt', 'routefiltSrch', 'elemFilt');
			
	   	});
   	
   	
	$("*").scroll(function(){
   	    var top =   $(this).scrollTop();
   	    $("#tmp_workspec").css({"top":top+"px"});
   	    var left = $(this).scrollLeft();
   	    $("#tmp_workspec").css({"left":left+"px"});
   	});
    
	
	function enabledisableconfig(configId,evnt){
		var enableVal = false;
		if(evnt.checked){
			enableVal = true;
		}
		$.ajax({
	    	url: configId+"/"+enableVal,
	    	async: true,
	     	type: "GET",
	   		success : function(response){
	   				
	   			$('#example').DataTable().ajax.reload();
			}
			});
	} 

	function resetAllSearchFilters(){
		$("#name").val("");
		$("[name='triggerType']").val("");
		$("[name='filterRadio']").prop("checked",false);
		}
	
	function deleteConfiguration(configId){
		$.ajax({
	    	url: "delete/"+configId,
	    	async: true,
	     	type: "POST",
	     	beforeSend:function(){
	            return confirm("Please confirm to delete?");
	         },
	   		success : function(response){
	   				
	   			$('#example').DataTable().ajax.reload();
			}
			});
	} 

	function enablelOrDisableConfiguration(enable){
		
		var ids = "";
		var checkCount = $("#example tbody").find("[type='checkbox']:checked").length;
		if(checkCount == 0){
			alert("Please check atleast one checkbox");
			return false;
		}
		
		$("#example tbody").find("[type='checkbox']:checked").each(function(){
			if(ids == ""){
				ids = $(this).val();
			}else{
				ids += ","+$(this).val();
			}
		})
		var url = '${pageContext.servletContext.contextPath}/configure/list/'+enable;
		var form = "<form action='"+url+"' id='enableOrDisable' method='POST'>";
		    form += "<input type='hidden' name='ids' value='"+ids+"'>";
		    form += "</form>"
		    
		    $(form).appendTo('body').submit();
		
	}
	
	
	function getAjaxResponce(){
		
		if ( $.fn.dataTable.isDataTable( '#example' ) ) {
			console.log("asdasd");
		    table = $('#example').DataTable();
		    table.destroy();
		}	
		var searchDataJson = {};
		
		
		var name = $("#name").val();
		if($.trim(name) != ""){
			searchDataJson["name"] = name;
		}
		
		var triggerType = $("[name='triggerType'] option:selected").val();
		
		if(triggerType != ""){
			searchDataJson["triggerType"] = triggerType;
		}
		
		//below code is for enable,disable,deleted
		var filterRadio = $("[name='filterRadio']:checked").data("name");
		
		var filterRadioValue = $("[name='filterRadio']:checked").val();
		
		if(filterRadio != "" || filterRadio != undefined){
			searchDataJson[filterRadio] = filterRadioValue;
		}
	    
	    var ht = $('#pageNew').height();
   		ht = ht - 75;
   		pxls = ht + "px";

   		table =$("#example").DataTable( {
	    	"language":{"paginate":{"first":"<<","last":">>","next":">","previous":"<"}, "zeroRecords": "No records found for the searched data"},
			"pagingType":"full_numbers",
			"scrollX": true,
	    	"scrollY":pxls,
		    "scrollCollapse": true,
		    "fixedColumns":   {leftColumns: 3},
   	        "bProcessing": true,
   	        "bServerSide": true,
   	        "sort": "position",
   	        //bStateSave variable you can use to save state on client cookies: set value "true" 
   	        "bStateSave": false,
   	        //Default: Page display length
   	        "iDisplayLength":10,
   	        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
   	        "iDisplayStart": 0,
   	        "fnDrawCallback": function () {
   	            //Get page numer on client. Please note: number start from 0 So
   	            //for the first page you will see 0 second page 1 third page 2...
   	            //Un-comment below alert to see page number
   	        	console.log("Current page number: "+this.fnPagingInfo().iPage);    
   	        	$("#example_paginate span").empty();				       
			    $("#example_paginate span").html("<a class='paginate_button current'>"+((this.fnPagingInfo().iPage)+1)+"</a>");
			    $('.dataTables_scrollHeadInner').css({
   	        		'padding':'0px'
   	        	});
			    $('.dataTables_scrollBody').css({
   	        		'max-height':''
   	        	});
   	        },
   	     "fnInitComplete": function (){
   	        	$('.dataTables_scrollHeadInner').css({
   	        		'padding':'0px'
   	        	});
   	        },
	   	     'rowCallback': function(row, data, dataIndex){
	   	         // Get row ID
	   	         var rowId = data[0];
	   	     },
   	        "sAjaxSource": "list/dataJson",
   	     	"sServerMethod": "POST",
   	     	"fnServerParams": function ( aoData ) {
				aoData.push( { "name": "searchJsonData", "value":JSON.stringify(searchDataJson) } );
			},
   	        "aoColumns": [
			   	        	{
			   	        		"mData" : "enable or disable",
								render: function ( data, type, row ) {	
									return '<input type="checkbox" class="material-icons" value="'+row.id+'"></span>';
								},
								className: "dt-body-center",
								orderable: false
							},
							 { "mData":   "id",
										render: function ( data, type, row ) {												
											   var str='<div >';
											   str+='<a href="${pageContext.servletContext.contextPath}/configure/navAdd?pushId='+row.id+'"><img src="${pageContext.servletContext.contextPath}/resources/imagebuttons/edit.png" title="Edit" alt="Edit" /></a>';
											   str+='<span class="dropdown" style="text-align: center;"><button style="background:none;" class="btn dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><i class="glyphicon glyphicon-option-vertical"></i></button><ul class="dropdown-menu" style="right:-80px;overflow:auto;height:83px;" aria-labelledby="dropdownMenu1">';
											   str+='<li><a href="${pageContext.servletContext.contextPath}/configure/field/mappings/'+row.id+'?triggerType='+row.triggerType+'">Mapping</a></li>';
											   str+='<li><a href="${pageContext.servletContext.contextPath}/escalations/addPage/'+row.id+'">Escalation</a></li>';
											   str+='<li><a href="#" onClick="return deleteConfiguration('+row.id+');">Delete</a></li>';
											   str+='</ul></span></div>';
											    return str;
									},
									className: "dt-body-center",
									orderable: false
								},						
								{
									"mData" : "name",
									render: function ( data, type, row ) {	
										var str='<a href="${pageContext.servletContext.contextPath}/configure/navAdd?pushId='+row.id+'&disableAllInputs=true">'+row.name+'</a>';
										  return str;	
									}
								},
								
								{
									"mData" : "triggerType",
									render: function ( data, type, row ) {	
										var str='<div class="btn-group" role="group" aria-label="...">';
										if(row.triggerType == '1'){
											  str+='<span class="material-icons">FORMS</span>';
										}else{
											str+='<span class="material-icons">WORKS</span>';
										}
									      str+='</div>';
										  return str;
								},
									className: "dt-body-center",
									orderable: false
								},
								{
									"mData" : "triggerSource",
									className: "dt-body-center",
									orderable: false
								},
								{
									"mData" : "retryCount",
									className: "dt-body-center",
									orderable: false
								}
								
							 
   	        ]
   	    } );
	    
	    $('#example_length').appendTo('.dataTables_wrapper');
	    $('[name="example_length"]').css({
	        'float': 'left',
	    });
	    $("#example_length label").css({
	    	 'margin-left':'5px',
		        'margin-top':'8px'
	    });
   	 	
   	    if(('${error}'.length) > 0){
   			$("#error").dialog();
   		}
 	   	
	/* exportFiltered only ends */
		filterListByText('txt', 'selectedForms', 'elem');
		filterListByText('routefilt', 'routefiltSrch', 'elemFilt');
	}
	
	
   	function block() {
   		unblock();
   		$('body').append(
   						'\
   				<div class="kg-blockDiv1" style="margin-top: 12px; font-weight: bold; font-size:100px  color:white;">\
   				<img src="${pageContext.servletContext.contextPath}/resources/img/busy.gif" /> Loading...\
   				</div>');
   		
   		$('.kg-blockDiv1').dialog({
   			height : 110,
   			modal : true,
   			draggable : false,
   			resizable : false,
   			closeOnEscape : false,
   			open : function(event, ui) {
   				$(this).parent().find('.ui-dialog-titlebar').hide();
   			}
   		});
   	}

   	function unblock() {
   		$('.kg-blockDiv1').dialog('close');
   		$('.kg-blockDiv1').remove();
   		
   		
   	}		
</script>

<!-- New Route Plan UI Starts Here  -->
<div Class="error" id="error" title="Error">${error}</div>
<div id="AllRoutesMain" class="row mainDiv">
	<div id="leavesLeftPane"
		class="col-md-2 col-lg-2 col-xl-2 col-sm-2 leftPane">
		<div>
			<div
				style="padding: 0px 5px 3px 0px; border-bottom: 1px solid rgb(201, 194, 194);"
				class="font-header">Filters</div>
			<div style="padding: 5px;" class="row" align="center">
				<div class="col-sm-6 col-md-6 col-lg-6"
					style="display: inline; padding-top: 8px;">
					<a href="#" id="reset" class="reset pull-right"
						onClick="resetAllSearchFilters()">RESET</a>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6"
					style="display: inline; padding-left: 5px;">
					<input type="submit" class="btn btn-sm btn-spoors-action pull-left"
						id="search" name="search" value="Apply"
						onClick="getAjaxResponce()" />
				</div>
			</div>
		</div>
		<div class="leftPaneMainDiv">
			<div class="row scroll-over leftPaneSubDiv">
				<div style="padding: 5px;" class="font-sub-header"><br>
					
					<input class="form-control" type="text" name="name" id="name" placeholder=" Name">
						
						<br><br>  
						<select class="form-control" name="triggerType">
							<option value="">--select--</option>
							<option value="1">FORMS</option>
							<option value="2">WORKS</option>
						</select>
				</div>

				<div style="" class="font-sub-header">
					
					<div class="">
						<label><input type="radio"	id="enable" name="filterRadio" data-name="enable" value="true"/>&nbsp;Enable</label><br>
						<label><input type="radio"	id="disable" name="filterRadio" data-name="enable" value="false" />&nbsp;Disable</label><br>
						<label><input type="radio" name="filterRadio"  data-name="deleted" value="true"/>&nbsp;Deleted</label><br>
					</div>
				</div>
  
			</div>
			<div style="padding: 5px;" class="row" align="center">
				<div class="col-sm-6 col-md-6 col-lg-6"
					style="display: inline; padding-top: 8px;">
					<a href="#" id="reset" class="reset pull-right"
						onClick="resetAllSearchFilters()">RESET</a>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6"
					style="display: inline; padding-left: 5px;">
					<input type="submit" class="btn btn-sm btn-spoors-action pull-left"
						id="search" name="search" value="Apply"
						onClick="getAjaxResponce()" />
				</div>
			</div>
		</div>
	</div>
	
	
		<div id="routesMiddlePane"
		class="col-md-10 col-lg-10 col-xl-10 col-sm-10 middlePane"
		align="center">
		<!--  <div style="height:20%">  	 -->
		<div>
			<div
				style="padding: 5px 5px 32px 0px; border-bottom: 1px solid rgb(201, 194, 194); text-align: left;"
				class="font-header">
				<div class="font-header" style="text-align: left; float: left;">
					<span class="form-head"
						style="font-weight: bolder; font-size: medium;">List
						Integration Configuration</span>
				</div>
				<div style="float: right; padding-left: 10px; margin-top: -5px;">
					<a
						href="${pageContext.servletContext.contextPath}/configure/navAdd">
						<input type="button" class="btn btn-sm btn-spoors-main"
						value="Create" />
					</a>
					<a href="javascript:void(0)" onclick="enablelOrDisableConfiguration(true)">
						<input type="button" class="btn btn-sm btn-spoors-main"
						value="enable" />
					</a>
					
					<a href="javascript:void(0)" onclick="enablelOrDisableConfiguration(false)">
						<input type="button" class="btn btn-sm btn-spoors-main"
						value="disable" />
					</a>
				</div>
			</div>
		</div>
		<div class="row" style="padding-top: 3px; height: 98%">
			<div id="pageNew" style="height: 94%">
				<table id="example" class="display nowrap"
					style="cellspacing: 0; width: 100%;">
					<thead>
						<tr class="dataTable-header">
							<th></th>
							<th>Edit</th>
							<th>Name</th>
							<th>Trigger Type</th>
							<th>Trigger Source</th>
							<th>Max. Retry Count</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

</div>
<input id="refreshed" type="hidden" value="no" />