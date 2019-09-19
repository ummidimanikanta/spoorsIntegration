var map;
var latLongs = [];
var flightPathPonts = [];
var flightPath;

var iconBasePath = null;
var iconEmployee = null;
var iconCustomer = null;

function setImagePaths(basePath){
	iconBasePath = basePath;
	iconEmployee = iconBasePath + 'agent_small.png';
	iconCustomer = iconBasePath + 'company_ico_small.png';
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
		
		var marker = null;
		if(latLongs[i][2] == 1){
			marker = getMarker(latLongs[i][1], iconEmployee, latLongs[i][3]);
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		} else if(latLongs[i][2] == 2){
			marker = getMarker(latLongs[i][1], iconCustomer, latLongs[i][3]);
			bindInfoWindow(marker, latLongs[i][0], new google.maps.InfoWindow);
		}
		
		var mapLabel = new MapLabel({
	        text: latLongs[i][3],
	        position: latLongs[i][1],
	        map: map,
	        fontSize: 15,
	        align: 'center'
        });
		
		marker.bindTo('map', mapLabel);
	    marker.bindTo('position', mapLabel);
			
	}
			
	map.fitBounds(markerBounds);
}

