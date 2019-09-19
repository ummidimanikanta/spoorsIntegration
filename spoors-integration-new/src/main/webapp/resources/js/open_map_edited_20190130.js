(function($) {
	$.fn.latLngPickForOSM = function(options, contextPath) {
		var defaults = {
			latField:'',
			lngField:''
		};
			
		var settings = $.extend({}, defaults, options);
		
		this.css('cursor','pointer');
		
		var icon = this;
		
		this.click(function() {
				var htmlText = ""+
				"<html xmlns='http://www.w3.org/1999/xhtml'>"+
				"<head>"+
				"<link rel='stylesheet' href='"+contextPath+"/resources/openMapJs/ol.css' type='text/css'>"+
				"<link rel='stylesheet' href='"+contextPath+"/resources/openMapJs/ol3-geocoder.min.css' type='text/css'>"+
				"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
				"<script src='"+contextPath+"/resources/openMapJs/ol.js' type='text/javascript'></script>"+
				"<script src='"+contextPath+"/resources/openMapJs/ol3-geocoder.js' type='text/javascript'></script>"+
				"</head>"+
				"<div id='map' class='map'></div>"+
				"<script>"+
				"	var latFieldId = '"+settings.latField+"';"+
				"	var lngFieldId = '"+settings.lngField+"';"+
				"	var completeFieldId = '"+settings.completeField+"';"+
				"	var latField = window.opener.document.getElementById(latFieldId);"+
				"	var lngField = window.opener.document.getElementById(lngFieldId);" +
				"	var completeField = window.opener.document.getElementById(completeFieldId);" +
				"   var vectorLayer;"+
				"   var fieldLat = latField.value;" +
				"	var fieldLng = latField.value;" +
				"	if(fieldLat!='' && fieldLng!=''){"+
				"   var iconFeature = new ol.Feature({"+
                "       geometry: new ol.geom.Point(ol.proj.transform([parseFloat(lngField.value),parseFloat(latField.value)], 'EPSG:4326', 'EPSG:3857')),"+
                "  });"+
                "      var iconStyle = new ol.style.Style({"+
                "       image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({"+
                "         anchor: [0.5, 46],"+
                "         anchorXUnits: 'fraction',"+
                "          anchorYUnits: 'pixels',"+
                "         src: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=0|FF0000|000000'"+
                "     }))"+
                "  });"+
                "     "+
                "     iconFeature.setStyle(iconStyle);"+
                "      vectorLayer = new ol.layer.Vector({"+
                "       source: new ol.source.Vector({"+
                "       features: [iconFeature]"+
                "        })"+
                "    });"+
                "       }"+
				"		var map = new ol.Map({"+
				"        target: 'map',"+
				"        layers: ["+
				"          new ol.layer.Tile({"+
				"            source: new ol.source.OSM()"+
				"          })"+
				"        ],"+
				"        view: new ol.View({"+
				"          center: ol.proj.fromLonLat([78.390428,17.441149]),"+
				"		  zoom: 12"+
				"        })"+
				"	});"+
				"     if(fieldLat!='' && fieldLng!=''){ " +
				"     	map.addLayer(vectorLayer); " +
				"		map.getView().setCenter(ol.proj.transform([parseFloat(lngField.value),parseFloat(latField.value)], 'EPSG:4326', 'EPSG:3857'));" +
				"	  }"+
				//Instantiate with some options and add the Control
				" var geocoder = new Geocoder('nominatim', {"+
				"  provider: 'photon',"+
				"  lang: 'en',"+
				"  placeholder: 'Search for ...',"+
				"  limit: 5,"+
				"  debug: true,"+
				"  autoComplete: true,"+
				"  keepOpen: true"+
				"});"+
				" map.addControl(geocoder);"+
				  
				//Listen when an address is chosen
				"geocoder.on('addresschosen', function(evt){"+
				  
				"});"+
				"   map.on('click', function(evt) {"+
				"	  var lonlat = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');"+
				"	  var lng = lonlat[0];"+
				"	  var lat = lonlat[1];"+
				"			var conf = confirm('Click ok to set this ('+lat+', '+lng+') Latlong');"+
				"			if(conf == true){"+
				"				"+
				"				try{"+
				"					if(latField && lngField){"+
				"						latField.value = lat;"+
				"						lngField.value = lng;"+
				"						try{ "+
				"							 completeField.value= lat+','+lng;"+						
				"							 completeField.onchange();}"+
				"						catch(err){}"+		
				"					}"+
				"				} catch (error){"+
				"					alert(error);"+
				"				}"+
				"				window.close();"+
				"			}"+

				"	});"+
				"	"+
				"</script>"+
				
				"</html>"+
				"";
				
				var mapWindow = window.open('', '', 'width=600, height=400, location=0, menubar=0');
				mapWindow.document.write(htmlText);
				mapWindow.focus();
			})
	};
})(jQuery);
