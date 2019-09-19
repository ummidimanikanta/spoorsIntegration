var map;
var latLongs = [];
var flightPathPonts = [];
var flightPath;

var iconBasePath = null;
var iconVisitComp = null;
var iconVisitInComp = null;
var jobLabel="Job";

var tzo = new Date().getTimezoneOffset();

function setJobLabel(joblabel){
	jobLabel=joblabel;
	
}

function setImagePaths(basePath){
	iconBasePath = basePath;
	iconVisitComp = iconBasePath + 'iconcompleted.png';
	iconVisitInComp = iconBasePath + 'iconincomplete.png';
}

function getMarker(position, icon, title){
	return new google.maps.Marker({
		position: position,
		map: map,
		icon: icon,
		title: title
	});
}

function bindInfoWindow(marker, contentString, infowindow){
	contentString = contentString.replace(':timeZoneOffsetValue', tzo);
	
	google.maps.event.addListener(marker, 'click', function() {
   		infowindow.setContent(contentString);
    	infowindow.open(map, marker);
   	});
}
  
function initialize() {
	var mapOptions = {
  		zoom: 12,
  		center: latLongs[0][1],
  		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);

	var markerBounds = new google.maps.LatLngBounds();
	for (var i = 0; i < latLongs.length; i++) {
		markerBounds.extend(latLongs[i][1]);
		
		if(latLongs[i][2] == 1){
			var marker = null;
			if(latLongs[i][3] == 0){
				marker = getMarker(latLongs[i][1], iconVisitInComp, jobLabel);
			} else if(latLongs[i][3] == 1){
				marker = getMarker(latLongs[i][1], iconVisitComp, jobLabel);
			}
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		}
	}
			
	map.fitBounds(markerBounds);
	
	flightPathPonts = [];
	for (var i = 0; i < latLongs.length; i++) {
		if(latLongs[i][2] == 1){
			flightPathPonts[i]=latLongs[i][1];
		}
	}
	if(flightPath != null){
		flightPath.setMap(null);
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
	flightPath = new google.maps.Polyline({
 	 	path: flightPathPonts,
 	 	strokeColor: '#FF0000',
 	 	strokeOpacity: 1.0,
 	 	strokeWeight: 2,
 	 	icons : [arrow]
	});
	flightPath.setMap(map);
}