var map;
var mapData = [];

var iconBasePath = null;
var iconVisitComp = null;

function setImagePaths(basePath){
	iconBasePath = basePath;
	iconVisitComp = iconBasePath + 'iconcompleted.png';
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
  		zoom: 16,
  		center: mapData[1],
  		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
	var marker = getMarker(mapData[1], iconVisitComp, mapData[3]);
	bindInfoWindow(marker, mapData[0], new google.maps.InfoWindow);
}