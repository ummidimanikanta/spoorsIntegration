(function($) {
	$.fn.latLngPick = function(options) {
		var defaults = {
			latField:'',
			lngField:''
		};
			
		var googleBrowserApiKey = document.getElementById("latLngScript").getAttribute("googleBrowserApiKey");
		
		var settings = $.extend({}, defaults, options);
		
		this.css('cursor','pointer');
		
		var icon = this;
		
		this.click(function() {
			
			var disabled = icon.attr('disabled');
			if(!disabled){
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
			    "  /*background-color: #fff;*/"+
			    "   padding: 2px;"+
			    "  border:0px solid #999;"+
			     " }"+
			     ".form-control { "+
				    "	    width: 100%; "+
				    "	    height: 30px; "+
				    "	    padding: 6px 12px; "+
				    "	    font-size: 13px; "+
				    "	    line-height: 1.42857143; "+
				    "	    color: #555; "+
				    "	    background-color: #fff; "+
				    "	    background-image: none; "+
				    "	    border: 1px solid #ccc; "+
				    "	    border-radius: 4px; "+
				    "	    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075); "+
				    "	    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075); "+
				    "	    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s; "+
				    "	    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; "+
				    "	    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s; "+
				    "	} "+
				"</style> "+
				"<script type='text/javascript' src='https://www.google.com/jsapi'></script>"+
				"<script src='https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false&amp;key="+googleBrowserApiKey+"'></script>"+
				"<script>"+
				"	var latFieldId = '"+settings.latField+"';"+
				"	var lngFieldId = '"+settings.lngField+"';"+
				"	var completeFieldId = '"+settings.completeField+"';"+
				"	var latField = window.opener.document.getElementById(latFieldId);"+
				"	var lngField = window.opener.document.getElementById(lngFieldId);" +
				"	var completeField = window.opener.document.getElementById(completeFieldId);" +
				"	var map;"+
				"		"+
				"	function initialize() {"+
				"		var loc = {};"+
				"		var zoom = 12;"+
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
				"		var input = document.getElementById('location');"+
				"		var searchBox = new google.maps.places.SearchBox(input);"+
				"		google.maps.event.addListener(searchBox, 'places_changed', getLatLngForGivenAddress);"+
				"	}"+
				"	"+
				"	function loadMap(loc, zoom, plotCurrent){" +
				"		var latLng = {};"+
				"		"+
				"		var mapOptions = {"+
				"			zoom: zoom,"+
				"			center: new google.maps.LatLng(loc.lat, loc.lng),"+
				"			mapTypeId: google.maps.MapTypeId.ROADMAP,"+
				"			draggableCursor: 'crosshair'"+
				"		};"+
				"		map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);"+
				"		"+
				"		google.maps.event.addListener(map, 'click', function(event) {"+
				"			var myLatLng = event.latLng;"+
				"			latLng.lat = myLatLng.lat();"+
				"			latLng.lng = myLatLng.lng();"+
				"			var conf = confirm('Click ok to set this ('+latLng.lat+', '+latLng.lng+') Latlong');"+
				"			if(conf == true){"+
				"				"+
				"				try{"+
				"					if(latField && lngField){"+
				"						latField.value = latLng.lat;"+
				"						lngField.value = latLng.lng;"+
				"						try{ "+
				"							 completeField.value= latLng.lat+','+latLng.lng;"+						
				"							 completeField.onchange();}"+
				"						catch(err){}"+		
				"					}"+
				"				} catch (error){"+
				"					alert(error);"+
				"				}"+
				"				window.close();"+
				"			}"+
				"		});"+
				"		"+
				"		if(latField && lngField && plotCurrent){"+
				"			if(latField.value && lngField.value && plotCurrent){"+
				"				var markerBounds = new google.maps.LatLngBounds();"+
				"				markerBounds.extend(new google.maps.LatLng(loc.lat, loc.lng));"+
				"				markerBounds.extend(new google.maps.LatLng(latField.value, lngField.value));"+
				"				map.fitBounds(markerBounds);"+
				"				new google.maps.Marker({"+
				"					position: new google.maps.LatLng(latField.value, lngField.value),"+
				"					map: map"+
				"				});"+
				"			}"+
				"		}"+
				"	}"+
				"	"+
				"function enterPressed(event)"+
				"{   "+
				"if(event.keyCode==13){"+
				"getLatLngForGivenAddress();"+
				"return true;"+
				"}"+
				"return false"+

				"}"+
				"	function getLatLngForGivenAddress() {" +
				"		var loc = {};"+
				"		var zoom = 15;" +
				"		var addressElement = window.document.getElementById('location');"+
				"		var address = addressElement.value;" +
				"		var geocoder = new google.maps.Geocoder();"+
				"		geocoder.geocode({'address' : address}, function(results, status) {" +
				"			if (status == google.maps.GeocoderStatus.OK) {"+
				"				loc.lat = results[0].geometry.location.lat();"+
				"				loc.lng = results[0].geometry.location.lng();"+
				"				addressElement.value = results[0].formatted_address;"+
				"				loadMap(loc, zoom, false);"+
				"			} else {"+
				"				alert('Location not found');"+
				"			}"+
				"		});"+
				"		return false;"+
				"	}"+
				"	"+
				"	google.load('maps', '3.x', {other_params: 'sensor=false&libraries=places&key="+googleBrowserApiKey+"', callback:initialize});"+
				"</script>"+
				"</head>"+
				"<body>"+
				"	<div id='locationDiv'>"+
//				"		<input type='text' id='location' class='form-control' placeholder='Search Address'   onkeypress='enterPressed(event)'>"+
				"		<!--<button  onclick='getLatLngForGivenAddress()'>Search</button>-->"+
				"	</div>"+
				"	<div id='map_canvas' style='width: 100%; height: 100%;'>"+
				"</div>"+
				"</body>"+
				"</html>"+
				"";
				
				var mapWindow = window.open('', '', 'width=600, height=400, location=0, menubar=0');
				mapWindow.document.write(htmlText);
				mapWindow.focus();
			}
		});
	};
})(jQuery);


(function($) {
	$.fn.latLngUpdate = function(customerLabel, contextPath) {
		var defaults = {
			checkInLat: '',
			checkInLong: '',
			custId: null
		};
		var options = {
				checkInLat: this.attr('checkInLat'),
				checkInLong: this.attr('checkInLong'),
				custId: this.attr('custId')
		};
		
		var googleBrowserApiKey = document.getElementById("latLngScript").getAttribute("googleBrowserApiKey");
		
		var settings = $.extend({}, defaults, options);
		
		if(!settings.checkInLat || !settings.checkInLong){
			//alert("Invalid Check In location");
			return;
		}else if(!settings.custId){
			alert("Invalid "+customerLabel);
			return;
		}
		
		this.css('cursor','pointer');
		
		var icon = this;
		

		function getCustomerAddress(customer){
			if(customer.customerAddressCity){
				return customer.customerAddressCity;
			}else if(customer.customerAddressState){
				return customer.customerAddressState;
			}else if(customer.customerAddressCountry){
				return customer.customerAddressCountry;
			}else{
				return null;
			}
		};
		
		this.click(function() {
			var disabled = icon.attr('disabled');
			if(!disabled){
				var mapWindow = window.open('', '', 'width=660, height=400, location=0, menubar=0');
				/*loadingText = "<p id='loadMap'>Loading...</p>";
				mapWindow.document.write(loadingText);*/
				mapWindow.focus();
				$.ajax({
					url: contextPath+"/web/ajax/getCustomer?customerId="+settings.custId,
					method: 'GET',
					contentType: 'application/json',
					success: function(customerData){
						if(!customerData){
							alert("Unable to find "+customerLabel+" location");
							mapWindow.close();
							return;
						}
						
						if(!customerData.customerLat){
							customerData.customerLat = null;
						}
						
						if(!customerData.customerLng){
							customerData.customerLng = null;
						}
						
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
						"<script src='https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false&amp;key="+googleBrowserApiKey+"'></script>"+
						"<script src='"+contextPath+"/resources/jquery/jquery-3.3.1.js'></script>"+
						"<script src='"+contextPath+"/resources/jquery/jquery-migrate-3.0.0.js'></script>"+
						"<script type='text/javascript'>"+
						"	var curLat = '"+customerData.customerLat+"';"+
						"	var curLng = '"+customerData.customerLong+"';"+
						"	var forcedLat = '"+settings.checkInLat+"';"+
						"	var forcedLng = '"+settings.checkInLong+"';"+
						"	var custId = '"+settings.custId+"';"+
						"	var map;"+
						"		"+
						"	function initialize() {"+
						"		var loc = {};"+
						"		var zoom = 12;"+
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
						"	function updateCustomerLocation(){"+
						"		if(confirm('Are you sure you want to update "+customerLabel+" location?')){"+
						//"			var url = '"+contextPath+"/web/from/customer/update/location/'+custId;"+
						"			var url = '"+contextPath+"/web/from/customer/update/location/'+custId;"+
						"			$.ajax({"+
						"				type: 'POST',"+
						"				async: false,"+
						"				url : url,"+
						"				data: {latitude: forcedLat, longitude: forcedLng},"+
						"				success: function(ajaxResponse){"+
						"					if(ajaxResponse.code == null)" +
						"						alert(response.description);" +
						"					else{" +
						"						alert('"+customerLabel+" location updated successfully');"+
						"						window.close();" +
						"					}"+
						"				},"+
						"				error: function(err){"+
						"					alert('Failed to update "+customerLabel+" location. Please try after some time.');"+
						"				}"+
						"			});"+
						"		}"+
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
						"		var markerBounds = new google.maps.LatLngBounds();"+
						"		if(plotCurrent && forcedLat && forcedLng){"+
						"				markerBounds.extend(new google.maps.LatLng(loc.lat, loc.lng));"+
						//"				markerBounds.extend(new google.maps.LatLng(curLat, curLng));"+
						"				markerBounds.extend(new google.maps.LatLng(forcedLat, forcedLng));"+
						"				new google.maps.Marker({"+
						"					position: new google.maps.LatLng(forcedLat, forcedLng),"+
						"					map: map,"+
						"					icon: getMarkerImage('FF0000'),"+
						"					title: 'Check In Location'"+
						"				});"+
						" 			}"+
						"		if(plotCurrent && curLat && curLng && curLat != undefined && curLat !=null && curLng != undefined && curLng !=null){"+
						"				markerBounds.extend(new google.maps.LatLng(curLat, curLng));"+
						"				new google.maps.Marker({"+
						"					position: new google.maps.LatLng(curLat, curLng),"+
						"					map: map,"+
						"					icon: getMarkerImage('298A08'),"+
						"					title: '"+customerLabel+" Location'"+
						"				});"+
						"			}"+
						"			map.fitBounds(markerBounds);"+
						"	}"+
						"	"+
						"	google.load('maps', '3.x', {other_params: 'sensor=false&key="+googleBrowserApiKey+"', callback:initialize});"+
						"</script>"+
						"</head>"+
						"<body>"+
						"	<div id='updateDiv' style='width:100%;'>" +
						"		<font>Check In Location: ("+settings.checkInLat+", "+settings.checkInLong+")&nbsp;&nbsp;</font>";
						
						if(customerData.customerLat != settings.checkInLat || customerData.customerLong != settings.checkInLong){
							htmlText = htmlText +
							"		<button id='test' onclick='updateCustomerLocation()' style='float:right;'>Update "+customerLabel+" Location</button>";
						}
						htmlText = htmlText + "<br/>";
						if(!customerData.customerLat && !customerData.customerLong && getCustomerAddress(customerData)!=null && getCustomerAddress(customerData).length>0){
							htmlText = htmlText +
							"	<font>"+customerLabel+" Location: "+getCustomerAddress(customerData)+"</font>"; 
						}else if(customerData.customerLat && customerData.customerLong && customerData.customerLat.toLowerCase() != 'null' && customerData.customerLong.toLowerCase() != 'null'){
							htmlText = htmlText +"	<font>"+customerLabel+" Location: ("+customerData.customerLat+", "+customerData.customerLong+")</font>";
						};
						htmlText = htmlText +
						"	</div>"+
						"	<div id='map_canvas' style='width: 100%; height: 90%;'>"+
						"</div>"+
						"</body>"+
						"</html>"+
						"";
						mapWindow.document.open();
						mapWindow.document.write(htmlText);
						mapWindow.focus();
					},
					error: function(error){
						alert("Unable to find "+customerLabel+" location");
						mapWindow.close();
					}
				});
				mapWindow.focus();
			}
		});
	};
})(jQuery);