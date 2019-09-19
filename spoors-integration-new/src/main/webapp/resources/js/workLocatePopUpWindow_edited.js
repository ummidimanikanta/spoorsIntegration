
function showMapInWindow(workId,contextPath){
	//alert("Inside showMapInWindow of workLocate popup");
	//console.log("contextPath = "+contextPath);
	var mapWindow = window.open('', '', 'width=660, height=400, location=0, menubar=0');
	mapWindow.focus(); 
	mapWindow.document.write("<html><body><div id='loadingId'>Loading ...</body></html></div>");
	$("#loadingId").html("Loading ...");
	//var url = "http://nd.spoors.io:9080/effort5-2016/resources/demo_test.txt";
	//var url = "http://localhost:8080/effort/resources/demo_emp_work.txt";
	//var url = '${pageContext.servletContext.contextPath}/web/ext/work/related/locate/details/'+workId+'?mode=pop';
	//var url = contextPath+'/web/ext/work/related/locate/details/'+workId+'?mode=pop';
	//var url = 'http://localhost:8080/effort//web/ext/work/related/locate/details/html/'+workId+'?mode=pop';
	//var url = 'http://nd.spoors.io:9080/effort-map/web/ext/work/related/locate/details/html/'+workId+'?mode=pop';
	var url = contextPath+'/web/ext/work/related/locate/details/html/'+workId+'?mode=pop';
	$.ajax({
		url: url,
		async:false,
	success: function(result)
		{
			   			
			//$("#div1").html(result);
			        //var htmlText = getHtmlText();
					var response = JSON.parse(result);
					//console.log("response = "+response);
					//var points = [['<div id="content"><div id="siteNotice"></div><div id="bodyContent"><table border="0"><tr><td rowspan="6" style="padding-right:10px; "><img src="/effort5-2016/resources/img/agent.png" /></td></tr><tr><td style="text-align: left;"><img alt="Battery: 77.0" src="/effort5-2016/resources/img/b_4.png" /></td><td></td></tr><tr><td colspan="2">sreenaath k</td></tr><tr><td colspan="2">2016-10-27 11:21 AM</td></tr><tr><td colspan="2">2-52/1/3, Hitech City Rd, Megha Hills, Sri Sai Nagar, Madhapur, Hyderabad, Telangana 500081, India</td></tr><tr><td><a style ="display:block;text-align: left;" href="/effort5-2016/web/job/create?empId=31741" target="_blank">Assign :job </a></td><td><a id="empDetails" target="_blank" onclick="empTodayActivity(31741)" href="#">:employee Activities</a></td> <td style="text-align: right;"><a onclick="openAddWork(31741)" href="#"> Assign :work </a></td>    </tr></table></div></div>', new google.maps.LatLng('18.4414985', '78.3925325'), 5, 'sreenaath k', 0]];
					//Emp points data
					var points = response.pointsData;
					var latLongs = eval(points);
					//console.log("latLongs.length = "+latLongs.length);
					
					//var workLocationPoints = [['<div id="content"><div id="siteNotice"></div><div id="bodyContent"><table border="0"><tr><td rowspan="6" style="padding-right:10px; "><img src="/effort5-2016/resources/img/iconwork.png" /></td></tr><tr></tr><tr><td colspan="2"><a style ="display:block;text-align: left;" href="/effort5-2016/web/work/details/view/46239" target="_blank">invitation issue</a></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="2"></td></tr><tr><td></td><td></td>    </tr></table></div></div>', new google.maps.LatLng(78.3927926, 78.392888), 5, 'invitation issue']];
					//Work Location points data
					var workLocationPoints = response.workLocationPointsData;
					var workLatLongs = eval(workLocationPoints);
					console.log("workLatLongs.length = "+workLatLongs.length);
					
					var htmlText = getHtmlText(latLongs,workLatLongs,contextPath);
					console.log("htmlText = "+htmlText);
					mapWindow.document.open();
					mapWindow.document.write(htmlText);
					$("#loadingId").html("");
					mapWindow.focus();		
	        }});
}


function getHtmlText(latLongs,workLatLongs,contextPath){
	
	//alert("Inside getHtmlText of workLocate popup ");
	//var contextPath = 'http://nd.spoors.io:9080/effort5-2016/';
	var empIcon = contextPath+'/resources/img/agent_small.png;';
	var workIcon = contextPath+'/resources/img/iconwork.png;';
	//var iconBasePath = '<%=request.getContextPath()%>/resources/img/';
	//var empIcon = iconBasePath+agent_small.png;
	var htmlText = ""+
	"<html xmlns='http://www.w3.org/1999/xhtml'>"+
	"<head>"+
	"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
	"<style>"+
	"	html, body, #map_canvas {"+
	"		margin: 0;"+
	"		padding: 0;"+
	"  	}"+
	" #locationDiv {"+
	"   position:absolute;"+
    "   left:5px;"+
    "   top: 2px;"+
    " width:425px;"+
    "   z-index: 5;"+
    "  background-color: #fff;"+
    "   padding: 2px;"+
    "  border:0px solid #999;"+
     " }"+
	"</style> "+
	"<script type='text/javascript' src='https://www.google.com/jsapi'></script>"+
	"<script src='https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false'></script>"+
	"<script src='"+contextPath+"/resources/jquery/jquery-3.3.1.js'></script>"+
	"<script src='"+contextPath+"/resources/jquery/jquery-migrate-3.0.0.js'></script>"+
	"<script type='text/javascript'>"+
	"var curLat = '24.2068896223';"+
	"var curLng = '83.3203125';"+
	"var forcedLat = '17.4412464';"+
	"var forcedLng = '78.392888';"+
	"var custId = '223898';"+
	"var map;"+
	"		"+
	"	function initialize() {"+
	"var loc = {};"+
	"var zoom = 12;"+
	"		"+
	"		if(google.loader.ClientLocation) {"+
	"			loc.lat = google.loader.ClientLocation.latitude;"+
	"			loc.lng = google.loader.ClientLocation.longitude;"+
	"		} else {"+
	"          loc.lat = 21.7679;"+
	"			loc.lng = 78.8718;"+
	"			zoom = 5;"+
	"		}"+
	"		"+
	"		loadMap(loc, zoom, true);"+
	"	}"+
	"	"+
	" 	function getMarkerImage(color){"+
	"		return new google.maps.MarkerImage(" +
	"			'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|'+color,"+
	"			new google.maps.Size(21, 34),"+
	"			new google.maps.Point(0,0),"+
	"			new google.maps.Point(10, 34));"+
	"	}"+
	"	"+
	"	function loadMap(loc, zoom, plotCurrent){" +
	"		var latLng = {};"+
	
	"		var mapOptions = {"+
	"			zoom: zoom,"+
	"			center: new google.maps.LatLng(loc.lat, loc.lng),"+
	"			mapTypeId: google.maps.MapTypeId.ROADMAP,"+
	"			draggableCursor: 'crosshair',"+
	"			zoomControlOptions: {"+
    "    			position: google.maps.ControlPosition.LEFT_CENTER"+
    "			},"+
    "			streetViewControlOptions: {"+
    "   			position: google.maps.ControlPosition.LEFT_CENTER"+
    "			}"+
	"		};"+
	"		map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);"+
	"		"+
	"		var markerBounds = new google.maps.LatLngBounds();";
			for (var i = 0; i < latLongs.length; i++) {
		htmlText += 
	"               var position = new google.maps.LatLng("+latLongs[i][1].lat()+", "+latLongs[i][1].lng()+");"+
	"				markerBounds.extend(position);"+
	"				var icon = '"+empIcon+"';"+
	"				var title = '"+latLongs[i][3]+"';"+
	"				var contentVal = '"+latLongs[i][0]+"';"+
	"				var marker = getMarker(position, icon,title);"+
	"				var infoWindow = new google.maps.InfoWindow();"+
"				 google.maps.event.addListener(marker, 'click', (function(marker1,contentVal)"+
	" 				  {"+ 
	"                  return function()"+
    "                   {"+
	"						infoWindow.setContent(contentVal);"+
	"						infoWindow.open(map,marker1);"+
	"                   }"+
	"				  })(marker,'"+latLongs[i][0]+"'));"
		}
		
	for (var k = 0; k < workLatLongs.length; k++) {
				htmlText += 
			"               var position = new google.maps.LatLng("+workLatLongs[k][1].lat()+", "+workLatLongs[k][1].lng()+");"+
			"				markerBounds.extend(position);"+
			"				var icon = '"+workIcon+"';"+
			"				var title = '"+workLatLongs[k][3]+"';"+
			"				var contentVal = '"+workLatLongs[k][0]+"';"+
			"				var marker = getMarker(position, icon,title);"+
			"				var infoWindow = new google.maps.InfoWindow();"+
			"				 google.maps.event.addListener(marker, 'click', (function(marker1, contentVal)"+
			"				{"+
			"                  return function()"+
		    "                   {"+
			"						infoWindow.setContent(contentVal);"+
			"						infoWindow.open(map,marker1);"+
			"                   }"+
			"				  })(marker,'"+workLatLongs[k][0]+"'));"
				}
	htmlText += "	map.fitBounds(markerBounds); "+
	" }"+
"function getContent(arr,index1,index2)"+
	"{"+
	"	var arr1 = eval(arr);return arr1[index1][index2];"+
	"}"+
	"function getMarker(position, icon, title){"+	
		"return new google.maps.Marker({"+
					"position: position,"+
					"map: map,"+
					"icon: icon,"+	
					"title: title"+	
				"});"+
		"}"+	
	"	google.load('maps', '3.x', {other_params: 'sensor=false', callback:initialize});"+
	"</script>"+
	
	"<body>"+
	"	<div id='map_canvas' style='width: 100%; height: 100%;'>"+
	"</div>"+
	"</body>"+
	"</html>"+
	"";
	return htmlText;
}

function pop(div) {
	document.getElementById(div).style.display = 'block';
}
function hide(div) {
	document.getElementById(div).style.display = 'none';
}
            