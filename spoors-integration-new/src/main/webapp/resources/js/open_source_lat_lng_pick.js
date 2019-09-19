(function($) {
	$.fn.openSourceLatLngPick = function(options) {
		var defaults = {
			latField:'',
			lngField:''
		};
			
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
			    " .form-control { "+
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
				" <script src='http://www.openlayers.org/api/OpenLayers.js'></script>"+
			    " <link rel='stylesheet' href='https://openlayers.org/en/v4.2.0/css/ol.css' type='text/css'>"+
			    " <script src='https://openlayers.org/en/v4.2.0/build/ol.js'></script>"+
				"<script>"+
				"	var latFieldId = '"+settings.latField+"';"+
				"	var lngFieldId = '"+settings.lngField+"';"+
				"	var completeFieldId = '"+settings.completeField+"';"+
				"	var latField = window.opener.document.getElementById(latFieldId);"+
				"	var lngField = window.opener.document.getElementById(lngFieldId);" +
				"	var completeField = window.opener.document.getElementById(completeFieldId);" +
							"var map,vectorLayer,selectMarkerControl,selectedFeature;"+
					        "var lat     =   21.7679;"+
					        "var lon     =    78.8718;"+
					        "var zoom    =   5;"+
					        "var curpos = new Array();"+
					        "var position;"+
					        "var fromProjection = new OpenLayers.Projection('EPSG:4326');"+
					        "var toProjection   = new OpenLayers.Projection('EPSG:900913');"+
					        "var cntrposition       = new OpenLayers.LonLat(lon, lat).transform( fromProjection, toProjection);"+
					        "function init()"+
					        	"{"+
					               " map = new OpenLayers.Map('Map',{"+
					                            "controls:"+ 
					                            "["+
							                            "new OpenLayers.Control.PanZoomBar(),"+                        
							                            "new OpenLayers.Control.LayerSwitcher({}),"+
							                            "new OpenLayers.Control.Permalink(),"+
							                            "new OpenLayers.Control.MousePosition({}),"+
							                            "new OpenLayers.Control.ScaleLine(),"+
							                            "new OpenLayers.Control.OverviewMap(),"+
					                             "]"+
					                       "}"+
					                                ");"+
					                "var mapnik      = new OpenLayers.Layer.OSM('MAP');"+ 
					                "var markers     = new OpenLayers.Layer.Markers( 'Markers' );"+
					            "map.addLayers([mapnik,markers]);"+
					            "map.addLayer(mapnik);"+
					            "map.setCenter(cntrposition, zoom);"+
					            "var click = new OpenLayers.Control.Click();"+
					            "map.addControl(click);"+
					            "click.activate();"+
					"};"+
					"OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, { "+              
					 "defaultHandlerOptions: {"+
					 "'single': true,"+
					 "'double': false,"+
					 "'pixelTolerance': 0,"+
					 "'stopSingle': false,"+
					 "'stopDouble': false"+
					 "},"+
					 "initialize: function(options) {"+
					 "this.handlerOptions = OpenLayers.Util.extend("+
					 "{}, this.defaultHandlerOptions"+
					  ");"+
					  "OpenLayers.Control.prototype.initialize.apply("+
					   "this, arguments"+
					  ");"+
					  "this.handler = new OpenLayers.Handler.Click("+
					   "this, {"+
					    "'click': this.trigger"+
					  "}, this.handlerOptions"+
					  ");"+
					 "},"+
					 "trigger: function(e) {"+
					  "var lonlat = map.getLonLatFromPixel(e.xy);"+
					  "lonlat1= new OpenLayers.LonLat(lonlat.lon,lonlat.lat).transform(toProjection,fromProjection);"+
					  "alert('Hello...'+lonlat1.lon + '  ' +lonlat1.lat);"+
					 "}"+
					"});"+
				"	"+
				"</script>"+
				"</head>"+
				"<body onload='init();'>"+
					"<div id='Map' style='height: 650px' ></div>"+
			    "</body>"+
				"</body>"+
				"</html>"+
				"";
				var mapWindowForMap = window.open('', '', 'width=600, height=400, location=0, menubar=0');
				mapWindowForMap.document.write(htmlText);
				mapWindowForMap.focus();
			}
		});
	};
})(jQuery);

