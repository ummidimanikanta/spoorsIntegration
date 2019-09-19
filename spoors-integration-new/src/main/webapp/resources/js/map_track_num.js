var map;
var latLongs = [];
var flightPathPonts = [];
var flightPath;

var iconBasePath = null;
var iconColorBasePath = null;
var iconVisitComp = null;
var iconVisit=null;
var iconVisitInComp = null;
var iconTrackGps = null;
var iconTrackCell = null;
var iconTrackUnknown = null;
var iconComment = null;
var iconVideo = null;
var iconAideo = null;
var iconImage = null;
var iconFile = null;
var iconLastKnown = null;
var iconStartingpoint = null;
var iconHome = null;
var iconEmployee = null;
var iconCustomer = null;
var jobLabel='Job';
var iconWork=null;
var iconWorkComp = null;
var iconWorkInComp = null;
var iconRoutePlan=null;
var workLabel='Work';
var routePlanMap=[[]];
var customerInfoWindowMap = [[]];

var tzo = new Date().getTimezoneOffset();

function setJobLabel(joblabel){
	jobLabel=joblabel;
	
}

function setWorkLabel(worklabel){
	workLabel=worklabel;
	
}

function setImagePaths(basePath){
	iconBasePath = basePath;
	iconVisitComp = iconBasePath + 'iconcompleted.png';
	iconVisitInComp = iconBasePath + 'iconincomplete.png';
	iconVisit=iconBasePath + 'iconjob.png';
	iconTrackGps = iconBasePath + 'icongps.png';
	iconTrackCell = iconBasePath + 'iconcelltower.png';
	iconTrackUnknown = iconBasePath + 'iconunknown.png';
	iconComment = iconBasePath + 'iconcomment.png';
	iconVideo = iconBasePath + 'iconfile.png';
	iconAideo = iconBasePath + 'iconfile.png';
	iconImage = iconBasePath + 'iconfile.png';
	iconFile = iconBasePath + 'iconfile.png';
	iconLastKnown = iconBasePath + 'iconlastknown.png';
	iconStartingpoint = iconBasePath + 'iconStartingpoint.png';
	iconHome = iconBasePath + 'iconhome.png';
	iconEmployee = iconBasePath + 'agent_small.png';
	iconWork=iconBasePath + 'iconwork.png';
	iconWorkComp=iconBasePath + 'iconworkcomplete.png';
	iconWorkInComp=iconBasePath + 'iconworkincomplete.png';
	iconRoutePlan=iconBasePath + 'iconrouteplan.png';
	
}

function setColorImagePaths(iconColorBasePath){
	iconCustomer = iconColorBasePath ;
}

function getMarker(position, icon, title){
	return new google.maps.Marker({
		position: position,
		map: map,
		icon: icon,
		title: title
	});
}

function getMarkerWithHtmlTooltip(position, icon, tooltip){
	var marker = new google.maps.Marker({
		position: position,
		map: map,
		icon: icon,
		tooltip: tooltip
	});
	
	var tooltip = new Tooltip({map: map}, marker);
    tooltip.bindTo("text", marker, "tooltip");
    google.maps.event.addListener(marker, 'mouseover', function() {
        tooltip.addTip();
        tooltip.getPos2(marker.getPosition());
    });
	
    google.maps.event.addListener(marker, 'mouseout', function() {
        tooltip.removeTip();
    });
    
    return marker;
}

function bindInfoWindow(marker, contentString, infowindow){
	//contentString = contentString.replace(':timeZoneOffsetValue', tzo);
	
	google.maps.event.addListener(marker, 'click', function() {
   		infowindow.setContent(contentString.replace(':timeZoneOffsetValue', tzo));
    	infowindow.open(map, marker);
   	});
}

function bindInfoWindowForCustomerRoute(marker, infowindow){
	//contentString = contentString.replace(':timeZoneOffsetValue', tzo);
	
	google.maps.event.addListener(marker, 'click', function() {
    	infowindow.open(map, marker);
   	});
}

//function bindInfoWindowHover(marker, contentString, infowindow){
//	google.maps.event.addListener(marker, 'mouseover', function() {
//		/*marker.setIcon(iconTrackCell);
//	    alert('over');*/
//		
//		infowindow.setContent(contentString);
//    	infowindow.open(map, marker);
//	});
//	google.maps.event.addListener(marker, 'mouseout', function() {
//	    /*marker.setIcon(iconTrackGps);*/
//		infowindow.close();
//	});
//}
  
function initialize1() {
	routePlanMap=[[]];
	customerInfoWindowMap = [[]];
	var mapOptions = {
  		zoom: 12,
  		center: latLongs[0][1],
  		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

	var markerBounds = new google.maps.LatLngBounds();
	for (var i = 0; i < latLongs.length; i++) {
		markerBounds.extend(latLongs[i][1]);
		var marker='';
		var redMarker = '';
		if(i==0){
			marker='https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|00FF00|000000';
		}
		else if(i==latLongs.length-1){
			marker='https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|FF776B|000000';
		}else{
			marker='https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|FF776B|000000';
		}
		
		if(latLongs[i][2] == 0){
			if(i == latLongs.length - 1){
				iconLastKnown = 'https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|FF0000|000000';
				getMarkerWithHtmlTooltip(latLongs[i][1], iconLastKnown, latLongs[i][0]);
			} else if(latLongs[i+1][2] != 0){
				iconLastKnown = 'https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|FF0000|000000';
				getMarkerWithHtmlTooltip(latLongs[i][1], iconLastKnown, latLongs[i][0]);
			} else if(i == 0 && latLongs[i][2] == 0){
				iconStartingpoint = 'https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|008000|000000';
				getMarkerWithHtmlTooltip(latLongs[i][1], iconStartingpoint, latLongs[i][0]);
			} else {
				if(latLongs[i][3] == 0){
					iconTrackUnknown = 'https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|F9AC37|000000';
					getMarkerWithHtmlTooltip(latLongs[i][1], iconTrackUnknown, latLongs[i][0]);
				} else if(latLongs[i][3] == 1){
					iconTrackGps = 'https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|004A9D|000000';
					getMarkerWithHtmlTooltip(latLongs[i][1], iconTrackGps, latLongs[i][0]);
				} else if(latLongs[i][3] == 2){
					iconTrackCell = 'https://chart.googleapis.com/chart?chst=d_map_pin_letter&chld='+ ( i+1) +'|6B4156|000000';
					getMarkerWithHtmlTooltip(latLongs[i][1], iconTrackCell, latLongs[i][0]);
				}
			}
		} else if(latLongs[i][2] == 1||latLongs[i][2] == 7){
			var marker = null;
			if(latLongs[i][3] == 0){
				marker = getMarker(latLongs[i][1], iconVisitInComp, jobLabel);
			} else if(latLongs[i][3] == 1){
				marker = getMarker(latLongs[i][1], iconVisitComp, jobLabel);
				
			}else{
				marker = getMarker(latLongs[i][1], iconVisit, jobLabel);
			}
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		}
		else if(latLongs[i][2] == 11){
			var marker = null;
			if(latLongs[i][3] == 0){
				marker = getMarker(latLongs[i][1], iconWorkInComp, workLabel);
			} else if(latLongs[i][3] == 1){
				marker = getMarker(latLongs[i][1], iconWorkComp, workLabel);
				
			}else{
				marker = getMarker(latLongs[i][1], iconWork, workLabel);
			}
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		}
		
		else if(latLongs[i][2] == 12){
			var infoWindow = new google.maps.InfoWindow({ maxHeight: 400 });
			if(routePlanMap[latLongs[i][4]])
			{
				routePlanMap[latLongs[i][4]].push(latLongs[i]);
			}
			else
			{
				routePlanMap[latLongs[i][4]]=new Array(latLongs[i]);
			}
			if(customerInfoWindowMap[latLongs[i][5]])
			{
				infoWindow = customerInfoWindowMap[latLongs[i][5]];
				var content = infoWindow.content+"<hr> "+latLongs[i][0].replace(':timeZoneOffsetValue', tzo);
				infoWindow.setContent(content);
			}
			else
			{
				infoWindow.setContent(latLongs[i][0].replace(':timeZoneOffsetValue', tzo));
			}
			customerInfoWindowMap[latLongs[i][5]] = infoWindow;
			var marker = null;
				marker = getMarker(latLongs[i][1], iconRoutePlan, "Assigned Route Plan");
				bindInfoWindowForCustomerRoute(marker, infoWindow);
		}
		
		else if(latLongs[i][2] == 2){
			var marker = getMarker(latLongs[i][1], iconComment, 'Comment');
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		} else if(latLongs[i][2] == 3){
			var marker = getMarker(latLongs[i][1], iconFile, 'Attachment');
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		} else if(latLongs[i][2] == 4){
			var marker = null;
			if(latLongs[i][3] == 1){
				marker = getMarker(latLongs[i][1], iconHome, 'Home');
			}
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		}
		else if(latLongs[i][2] == 5){
			marker = getMarker(latLongs[i][1], iconEmployee, latLongs[i][3]);
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		} else if(latLongs[i][2] == 6){
			var infoWindow = new google.maps.InfoWindow({ maxHeight: 400 });
			
			if(customerInfoWindowMap[latLongs[i][5]])
			{
				infoWindow = customerInfoWindowMap[latLongs[i][5]];
				var content = infoWindow.content+"<hr> "+latLongs[i][0].replace(':timeZoneOffsetValue', tzo);
				infoWindow.setContent(content);
			}
			else
			{
				infoWindow.setContent(latLongs[i][0].replace(':timeZoneOffsetValue', tzo));
			}
			customerInfoWindowMap[latLongs[i][5]] = infoWindow;
			marker = getMarker(latLongs[i][1], iconCustomer+""+latLongs[i][4]+".png", latLongs[i][3]);
			bindInfoWindowForCustomerRoute(marker, infoWindow);
		}
	}
			
	map.fitBounds(markerBounds);
	
	flightPathPonts = [];
	flightPathPontsForroute = [];
	var j=0;
	for (var i = 0; i < latLongs.length; i++) {
		if(latLongs[i][2] == 0){
		var latLong=latLongs[i][1];
			if(latLong){
			flightPathPonts[j]=latLong;
			j++;
			}
		
		}
	}
	j=0;
	for (var i = 0; i < latLongs.length; i++) {
		if(latLongs[i][2] == 7){
		var latLong=latLongs[i][1];
			if(latLong){
				flightPathPontsForroute[j]=latLong;
			j++;
			}
		
		}
	}
	
	for(key in routePlanMap)
	{
		if(key == 0)
			continue;
		var routeLatLongs = routePlanMap[key];
		var routeFlightPaths=[];
		
		j=0;
		for (var i = 0; i < routeLatLongs.length; i++) {
			if(routeLatLongs[i][2] == 12){
			var latLong=routeLatLongs[i][1];
				if(latLong){
					routeFlightPaths[j]=latLong;
				j++;
				}
			
			}
		}

		createDottedPolyline(routeFlightPaths);
	 	
	}
	
	var lineSymbol = {
		path: google.maps.SymbolPath.FORWARD_OPEN_ARROW,
		strokeColor: "#0000ff",
		strokeOpacity: 1
	};
	
	
	var arrow = {
			icon: lineSymbol,
			offset: '10%',
			repeat: '20%'
		};
	
	if(flightPath != null){
		flightPath.setMap(null);
	}
	
	flightPath = new google.maps.Polyline({
 	 	path: flightPathPonts,
 	 	strokeColor: '#FF0000',
 	 	strokeOpacity: 1.0,
 	 	strokeWeight: 2,
 	 	icons : [arrow]
	});
	
	flightPath2 = new google.maps.Polyline({
 	 	path: flightPathPontsForroute,
 	 	strokeColor: '#33cc33',
 	 	strokeOpacity: 1.0,
 	 	strokeWeight: 2,
 	 	icons : [arrow]
	});
	
	flightPath2.setMap(map);
	flightPath.setMap(map);
}


function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}


function createDottedPolyline(polyC) {
	/* if(Path1!=null)
	Path1.setMap(null); */
	//alert(polyC);
	var lineSymbol = {
      path: 'M 0,-1 0,1',
      strokeOpacity: 1,
      scale: 4
     };

	var Path1 = new google.maps.Polyline({
		path : polyC,
		strokeOpacity: 0,
		icons: [{
		      icon: lineSymbol,
		      offset: '0',
		      repeat: '20px'

		    }],
		strokeColor : getRandomColor(),
		strokeWeight : 3,
		fillColor : "#FF8800",
		fillOpacity : 0.35

	});
	Path1.setMap(map);
}