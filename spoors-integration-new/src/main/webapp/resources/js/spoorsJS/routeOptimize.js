

var custMap = [];



function drawOptimizeRoute(sourceId,customerLocationJson){
	
	var directionsDisplay = new google.maps.DirectionsRenderer({
	    suppressMarkers: true,
	    polylineOptions: {
	        strokeColor: "#254f65",
	        strokeWeight: 5,
	        strokeOpacity: 0.8
	      }
	});
	
     var customerLocationObjects = JSON.parse(customerLocationJson);
     var latLngs=[]; var latlng='';
     var allPoints = [];  var point = '';
     var custMap = [];
     $.each(customerLocationObjects, function( index, value ) {
    	 
    	 latlng={lat : value.customerLat, lng : value.customerLong};
    	 latLngs[value.customerId]=latlng;
    	 
    	 point = value.customerId;
    	 allPoints.push(value.customerId.toString());
    	 
    	 custMap[value.customerId.toString()] = value.customerName;
    	 //allPoints.push(point);
     });
     
     console.log("latLngs size : "+latLngs.length);
     //displayArray(latLngs,"latLngs");
     
     console.log("allPoints size : "+allPoints.length);
     //displayArray(allPoints,"allPoints");
     
     drawRoute(sourceId,latLngs,allPoints,custMap);
   			//latLngs["3779398"] =myLatlng;
}

function drawRoute(sourceId, latLngs, allPoints, custMap)
{
	    var sourceLatLong = latLngs[sourceId];
		var distanceMap = [];
		for(var i=0;i<allPoints.length; i++)
		{
			if(allPoints[i] != sourceId)
			{
				var latLng = latLngs[allPoints[i]];
				distanceMap.push({id:allPoints[i], distance : getDistanceFromLatLngInMeter(sourceLatLong.lat, sourceLatLong.lng, latLng.lat, latLng.lng)});
			}
		}
		
		var distance = 0;
		distanceMap.sort(function(a,b){
			return a.distance-b.distance;
		});
		
		var destinationId = sourceId;
		var destinationIdLatLong = sourceLatLong;
		var wayPointsCount = 0;
		var wayPoints = "";
		waypts = [];
		var wayPtCustIds = [];
		for(var i=0;i<distanceMap.length; i++)
		{
			var latLng = latLngs[distanceMap[i].id];
			if(i == distanceMap.length-1 || i == 23)
			{
				destinationId = distanceMap[i].id;
				destinationIdLatLong = latLng;
				break;
			}
			else
			{
				wayPointsCount++;
				waypts.push({location: new google.maps.LatLng(latLng.lat, latLng.lng), stopover: true});
				wayPtCustIds.push(distanceMap[i].id);
			}
		}
		
	    var directionsService = new google.maps.DirectionsService();
		var request = {
	            origin: new google.maps.LatLng(sourceLatLong.lat,sourceLatLong.lng),
	            destination: new google.maps.LatLng(destinationIdLatLong.lat,destinationIdLatLong.lng),
	            waypoints:waypts,
	            optimizeWaypoints: true,
	            travelMode: google.maps.DirectionsTravelMode.DRIVING
	        };
		    
		 var optimiziedCustomerIds=[];
         var optimiziedCustomerNames=[];
         optimiziedCustomerIds.push(sourceId);
         optimiziedCustomerNames.push(custMap[sourceId]);
         
	     directionsService.route(request, function(response, status) {
	          
	        	  console.log("directionsService status = "+status);
	        	  console.log("directionsService response = "+response);
	        	  var wayPointsOrder = response.routes[0].waypoint_order; 
	                 
                  for(i=0; i < wayPointsOrder.length; i++){
   		            	var newOrder = wayPointsOrder[i];
   		            	var custId = wayPtCustIds[newOrder];
   		            	var custLocation = waypts[newOrder].location;
   		            	var custName = custMap[custId];
                        optimiziedCustomerIds.push(custId);
                        optimiziedCustomerNames.push(custName);
                  }
                 
                 optimiziedCustomerIds.push(destinationId);
                 optimiziedCustomerNames.push(custMap[destinationId]);
                 
                 $.each(optimiziedCustomerNames, function( index, value ) {
                	  console.log( index + "===> " + value );
                 });
                 
                 var otherCustomerIds=[];
 		         var otherCustomerNames=[];
 		           
	        	//routeMarkers.push(destinationMarker);
	        	//$("#"+destinationId+"_custId").insertAfter($("#"+previousCustId+"_custId"));
	        	//$("#"+destinationId+"_custId").attr("order",count+1);
	        	var sortForOthers = false;
	        	for(var i=0;i<distanceMap.length; i++)
	   			{
	        		var otherCustId = distanceMap[i].id;
	        		if(sortForOthers)
	        		{
	        			//$("#"+otherCustId+"_custId").insertAfter($("#"+previousCustId+"_custId"));
	        			previousCustId = otherCustId;
	        			otherCustomerIds.push(otherCustId);
	        			otherCustomerNames.push(custMap[otherCustId]);
	        		}
	        		
	        		if(otherCustId == destinationId)
	        		{
	        			sortForOthers = true;
	        		}
	        		
	   			}
		        	
		         $.each(otherCustomerNames, function( index, value ) {
            	    console.log("others : "+ index + "===> " + value );
                 });
                 
	      });
	     
	       
}

function displayArray(array,arrayName){
	console.log("displaying content of array = "+arrayName);
	$.each(array, function( index, value ) {
		  console.log( index + ": " + JSON.stringify(value, null, 4));
	});
}

function getDistanceFromLatLngInMeter(lat1,lon1,lat2,lon2) {
	  var R = 6371; // Radius of the earth in km
	  var dLat = deg2rad(lat2-lat1);  // deg2rad below
	  var dLon = deg2rad(lon2-lon1); 
	  var a = 
	    Math.sin(dLat/2) * Math.sin(dLat/2) +
	    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
	    Math.sin(dLon/2) * Math.sin(dLon/2)
	    ; 
	  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	  var d = R * c * 1000; // Distance in meter
	  return d;
}

function deg2rad(deg) {
  return deg * (Math.PI/180)
}

	
	