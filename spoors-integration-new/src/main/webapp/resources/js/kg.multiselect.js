(function($) {
	$.event.special.textchange = {
		setup: function (data, namespaces) {
			$(this).data('lastValue', this.contentEditable === 'true' ? $(this).html() : $(this).val());
			$(this).bind('keyup.textchange', $.event.special.textchange.handler);
			$(this).bind('cut.textchange paste.textchange input.textchange', $.event.special.textchange.delayedHandler);
		},
		teardown: function (namespaces) {
			$(this).unbind('.textchange');
		},
		handler: function (event) {
			$.event.special.textchange.triggerIfChanged($(this));
		},
		delayedHandler: function (event) {
			var element = $(this);
			setTimeout(function () {
				$.event.special.textchange.triggerIfChanged(element);
			}, 25);
		},
		triggerIfChanged: function (element) {
			var current = element[0].contentEditable === 'true' ? element.html() : element.val();
			if (current !== element.data('lastValue')) {
				element.trigger('textchange', [element.data('lastValue')]);
				element.data('lastValue', current);
			}
		}
	};
	
	$.fn.kgMultiselect = function(options) {
		var defaults = {
			sortable: true, 
			searchable: true,
			searchMode: 0,
			searchPlaceholder: 'Enter keywords'
		};
			
		var settings = $.extend({}, defaults, options);
		
		this.each(function() {
			var thisElement = $(this);
			
			thisElement.hide();
			thisElement.attr('multiple','multiple');
			
			var multiselect =$('<div class="ui-kg-multiselect-single ui-corner-all">\
						<div class="ui-kg-multiselect-single-header ui-corner-all">\
							<div class="ui-kg-multiselect-filter">\
								<input id="search" type="search" placeholder="'+settings.searchPlaceholder+'">&nbsp;<span id="search-clear" class="ui-kg-multiselect-single-icon ui-kg-multiselect-single-icon-clear"></span>\
							</div>\
							<ul class="ui-kg-multiselect-helper-reset ui-kg-multiselect-all-none">\
								<li>\
									<a class="ui-kg-multiselect-all" href="#"><span class="ui-kg-multiselect-single-icon ui-kg-multiselect-single-icon-check"></span></a>\
								</li>\
								<li>\
									<a class="ui-kg-multiselect-none" href="#"><span class="ui-kg-multiselect-single-icon ui-kg-multiselect-single-icon-uncheck"></span></a>\
								</li>\
							</ul>\
						</div>\
						<div class="ui-kg-multiselect-single-body">\
							<ul class="ui-kg-multiselect-helper-reset">\
							</ul>\
						</div>\
					</div>');
			
			thisElement.after(multiselect);
			
			$( ".ui-kg-multiselect-helper-reset", multiselect).disableSelection();
			
			if(settings.sortable){
				$( ".ui-kg-multiselect-helper-reset", multiselect).sortable({
					placeholder: "ui-state-highlight",
					axis: 'y', 
					start: function(e, ui) {
				        // creates a temporary attribute on the element with the old index
				        $(this).attr('data-previndex', ui.item.index());
				    },
				    update: function(e, ui) {
				        // gets the new and old index then removes the temporary attribute
				        var newIndex = ui.item.index();
				        var oldIndex = $(this).attr('data-previndex');
				        $(this).removeAttr('data-previndex');
				        
				        var option = thisElement.children('option:eq('+oldIndex+')');
				        var optionPrev = thisElement.children('option:eq('+(newIndex)+')');
				        
				        option.remove();
				        optionPrev.after(option);
				    }
				});
			}
			
			if(settings.searchable){
				$('.ui-kg-multiselect-filter #search', multiselect).bind('textchange', function () {
					var search = $(this).val();
					
					$('.ui-kg-multiselect-single-body ul li', multiselect)
					.hide()
					.filter(function(index) {
						var option = thisElement.children('option:eq('+index+')');
						var text = option.text();
						if(settings.searchMode == 1){
							return text.toLowerCase().indexOf(search.toLowerCase()) >= 0;
						} else {
							return text.toLowerCase().indexOf(search.toLowerCase()) === 0;
						}
					})
					.show();
				});
				
				$('.ui-kg-multiselect-filter #search-clear', multiselect).click(function(){
					$('.ui-kg-multiselect-filter #search', multiselect).val(null);
					$('.ui-kg-multiselect-filter #search', multiselect).trigger('textchange');
				});
			} else {
				multiselect.hide();
			}
			
			$('.ui-kg-multiselect-all', multiselect).click(function(){
				thisElement.children('option').each(function(){
					var value = $(this).val();
					$(this).prop('selected', true);
					$('.ui-kg-multiselect-single-body ul li #'+value, multiselect).prop('checked', true);
				});
				
				thisElement.trigger('change');
				return false;
			});
			
			$('.ui-kg-multiselect-none', multiselect).click(function(){
				thisElement.children('option').each(function(){
					var value = $(this).val();
					$(this).prop('selected', false);
					$('.ui-kg-multiselect-single-body ul li #'+value, multiselect).prop('checked', false);
				});
				
				thisElement.trigger('change');
				return false;
			});
			
			thisElement.children('option').each(function(){
				var text = $(this).text();
				var value = $(this).val();
				var selected = $(this).attr('selected');
				$('.ui-kg-multiselect-single-body ul', multiselect).each(function(){
					var li = $('<li class="ui-state-default ui-kg-multiselect-li"></li>');
					var checkbox = $('<input id="'+value+'" type="checkbox" title="'+text+'">');
					var label = $('<span>'+text+'</span>');
					var sortableIcon = $('<div class="ui-kg-multiselect-li-div"><span class="ui-icon ui-icon-arrowthick-2-n-s ui-kg-multiselect-sortable-icon"></span></div>');
					
					li.hover(
						function(){
							$(this).addClass("ui-state-hover");
						},
						function(){
							$(this).removeClass("ui-state-hover");
						}
					);
					
					checkbox.change(function(){
						var id = $(this).attr('id');
						if($(this).is(':checked')){
							thisElement.find('option[value='+id+']').prop('selected', true);
						} else {
							thisElement.find('option[value='+id+']').prop('selected', false);
						}
						thisElement.trigger('change');
					});
					
					if(selected){
						checkbox.prop('checked', true);
					}
					
					if(settings.sortable){
						li.append(sortableIcon);
					}
					
					li.append(checkbox);
					li.append(label);
					
					$(this).append(li);
				});
			});
			
		});
  	};
})(jQuery);