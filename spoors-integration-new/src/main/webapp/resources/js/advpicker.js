(function($) {
	$.fn.advPicker = function(options) {
		var key;
		var val;
		
		var defaults = {
			width: 750,
			height: 500,
			baseUrl: '',
			url: '',
			title: 'Select',
			disposeOnPicked: true,
			icon : '/resources/img/picker.png',
			clicked : function (settings){},
			picked: function(key, val){
				alert(key + " ," + val);
			}
		};
			
		var settings = $.extend({}, defaults, options);
		
		return this.each(function() {
			$(this).html('<img style="vertical-align: middle; text-decoration: underline;" alt="picker" src="' + settings.baseUrl + settings.icon + '">')
			.click(function(){
				settings.clicked(settings);
				
				var pickFor = $(this).attr('pickFor');
				var url = settings.baseUrl + settings.url;
   				var popDiv = $(
	   				'<div id="progressDiv">\
	   					<div id="progress" style="margin-top: 40px; font-weight: bold; font-size: medium;">\
	   						<img src="'+settings.baseUrl+'/resources/img/busy.gif" /> Loading...\
	   					</div>\
	   					<div id="pop">\
	   						<iframe id="pop-iframe" width="' + settings.width + '" height="' + settings.height + '" style="border: 0;"></iframe>\
	   					</div>\
	   				</div>'
	   			);
   				
   				popDiv.dialog({
   					title: settings.title,
   				 	width: settings.width + 30,
   				 	height: "auto",
   		         	position: { my: "center", at: "top", of: window },
   		            open: function () {
   		            	var progress = $(this).find('#progress');
   		            	progress.show();
   		            	
   		            	var popIframe = $(this).find('#pop-iframe');
   		            	popIframe.hide();
   		            	popIframe.attr('src',url);
   		            	
   		            	$(this).find('#pop-iframe').load(function(){
   		            		progress.hide();
   		            		popIframe.show();
   		            		
   		            		$('.advPick', popIframe.contents()).click(function (){
	   		 	   				key = $(this).attr('advPickKey');
	   			   				val = $(this).attr('advPickVal');

	   			   				settings.picked(key, val, pickFor);
	   			   				
	   			   				if(settings.disposeOnPicked){
	   			   					$("#progressDiv").dialog("close");
	   			   				}
	   			   			})
	   			   			.css('cursor','pointer');
   		              	});
   		            },
   		            close: function(event, ui) {
   		            	$(this).remove();
   		            }
   		        });
			})
			.css('cursor','pointer');
		});
		
  	};
})(jQuery);