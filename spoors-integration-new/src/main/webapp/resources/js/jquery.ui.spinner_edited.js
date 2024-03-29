/*
 * jQuery UI Spinner 1.9m3
 *
 * Copyright (c) 2010 AUTHORS.txt (http://jqueryui.com/about)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI/Spinner
 *
 * Depends:
 *  jquery.ui.core.js
 *  jquery.ui.widget.js
 */
(function($) {

// shortcut constants
var pageModifier = 10;

$.widget('ui.spinner', {
	options: {
		incremental: true,
		max: null,
		min: null,
		numberformat: null,
		step: null,
		value: null
	},
	
	_create: function() {
		this._draw();
		this._markupOptions();
		this._mousewheel();
		this._aria();
	},
	
	_markupOptions: function() {
		var _this = this;
		$.each({
			min: -Number.MAX_VALUE,
			max: Number.MAX_VALUE,
			step: 1
		}, function(attr, defaultValue) {
			if (_this.options[attr] === null) {
				var value = _this.element.attr(attr);
				_this.options[attr] = typeof value == "string" && value.length > 0 ? _this._parse(value) : defaultValue;
			}
		});
		this.value(this.options.value !== null ? this.options.value : this.element.val() || 0);
	},
	
	_draw: function() {
		var self = this,
			options = self.options;

		var uiSpinner = this.uiSpinner = self.element
			.addClass('ui-spinner-input')
			.attr('autocomplete', 'off')
			.wrap(self._uiSpinnerHtml())
			.parent()
				// add buttons
				.append(self._buttonHtml())
				// add behaviours
				.hover(function() {
					if (!options.disabled) {
						$(this).addClass('ui-state-hover');
					}
					self.hovered = true;
				}, function() {
					$(this).removeClass('ui-state-hover');
					self.hovered = false;
				});

		// TODO: move to theme, ask FG how
		// fix inline-block issues for IE. Since IE8 supports inline-block we need to exclude it.
		if (!$.support.opacity && uiSpinner.css('display') == 'inline-block' && $.layout!= undefined && $.layout.browser.version < 8) {
			uiSpinner.css('display', 'inline');
		}

		this.element
			.bind('keydown.spinner', function(event) {
				if (self.options.disabled) {
					return;
				}
				if (self._start(event)) {
					return self._keydown(event);
				}
				return true;
			})
			.bind('keyup.spinner', function(event) {
				if (self.options.disabled) {
					return;
				}
				if (self.spinning) {
					self._stop(event);
					self._change(event);					
				}
			})
			.bind('focus.spinner', function() {
				uiSpinner.addClass('ui-state-active');
				self.focused = true;
			})
			.bind('blur.spinner', function(event) {
				self.value(self.element.val());
				if (!self.hovered) {
					uiSpinner.removeClass('ui-state-active');
				}		
				self.focused = false;
			});

		// button bindings
		this.buttons = uiSpinner.find('.ui-spinner-button')
			.attr("tabIndex", -1)
			.button()
			.removeClass("ui-corner-all")
			.bind('mousedown', function(event) {
				if (self.options.disabled) {
					return;
				}
				if (self._start(event) === false) {
					return false;
				}
				self._repeat(null, $(this).hasClass('ui-spinner-up') ? 1 : -1, event);
			})
			.bind('mouseup', function(event) {
				if (self.options.disabled) {
					return;
				}
				if (self.spinning) {
					self._stop(event);
					self._change(event);					
				}
			})
			.bind("mouseenter", function() {
				if (self.options.disabled) {
					return;
				}
				// button will add ui-state-active if mouse was down while mouseleave and kept down
				if ($(this).hasClass("ui-state-active")) {
					if (self._start(event) === false) {
						return false;
					}
					self._repeat(null, $(this).hasClass('ui-spinner-up') ? 1 : -1, event);
				}
			})
			.bind("mouseleave", function() {
				if (self.spinning) {
					self._stop(event);
					self._change(event);
				}
			});
					
		// disable spinner if element was already disabled
		if (options.disabled) {
			this.disable();
		}
	},
	
	_keydown: function(event) {
		var o = this.options,
			KEYS = $.ui.keyCode;

		switch (event.keyCode) {
		case KEYS.UP:
			this._repeat(null, 1, event);
			return false;
		case KEYS.DOWN:
			this._repeat(null, -1, event);
			return false;
		case KEYS.PAGE_UP:
			this._repeat(null, pageModifier, event);
			return false;
		case KEYS.PAGE_DOWN:
			this._repeat(null, -pageModifier, event);
			return false;
			
		case KEYS.ENTER:
			this.value(this.element.val());
		}
		
		return true;
	},
	
	_mousewheel: function() {
		// need the delta normalization that mousewheel plugin provides
		if (!$.fn.mousewheel) {
			return;
		}
		var self = this;
		this.element.bind("mousewheel.spinner", function(event, delta) {
			if (self.options.disabled) {
				return;
			}
			if (!self.spinning && !self._start(event)) {
				return false;
			}
			self._spin((delta > 0 ? 1 : -1) * self.options.step, event);
			clearTimeout(self.timeout);
			self.timeout = setTimeout(function() {
				if (self.spinning) {
					self._stop(event);
					self._change(event);
				}
			}, 100);
			event.preventDefault();
		});
	},
	
	_uiSpinnerHtml: function() {
		return '<div role="spinbutton" class="ui-spinner ui-state-default ui-widget ui-widget-content ui-corner-all"></div>';
	},
	
	_buttonHtml: function() {
		return '<a class="ui-spinner-button ui-spinner-up ui-corner-tr"><span class="ui-icon ui-icon-triangle-1-n">&#9650;</span></a>' +
				'<a class="ui-spinner-button ui-spinner-down ui-corner-br"><span class="ui-icon ui-icon-triangle-1-s">&#9660;</span></a>';
	},
	
	_start: function(event) {
		if (!this.spinning && this._trigger('start', event) !== false) {
			if (!this.counter) {
				this.counter = 1;
			}
			this.spinning = true;
			return true;
		}
		return false;
	},
	
	_repeat: function(i, steps, event) {
		var self = this;
		i = i || 500;

		clearTimeout(this.timer);
		this.timer = setTimeout(function() {
			self._repeat(40, steps, event);
		}, i);
		
		self._spin(steps * self.options.step, event);
	},
	
	_spin: function(step, event) {
		if (!this.counter) {
			this.counter = 1;
		}
		
		// TODO refactor, maybe figure out some non-linear math
		var newVal = this.value() + step * (this.options.incremental &&
			this.counter > 20
				? this.counter > 100
					? this.counter > 200
						? 100 
						: 10
					: 2
				: 1);
		
		if (this._trigger('spin', event, { value: newVal }) !== false) {
			this.value(newVal);
			this.counter++;			
		}
	},
	
	_stop: function(event) {
		this.counter = 0;
		if (this.timer) {
			window.clearTimeout(this.timer);
		}
		this.element[0].focus();
		this.spinning = false;
		this._trigger('stop', event);
	},
	
	_change: function(event) {
		this._trigger('change', event);
		try
		{
			var a= $(this.element.find("input")).change();
		}
		catch(err)
		{
			console.log(err);
		}
	},
	
	_setOption: function(key, value) {
		if (key == 'value') {
			value = this._parse(value);
			if (value < this.options.min) {
				value = this.options.min;
			}
			if (value > this.options.max) {
				value = this.options.max;
			}
		}
		if (key == 'disabled') {
			if (value) {
				this.element.attr("disabled", true);
				this.buttons.button("disable");
			} else {
				this.element.removeAttr("disabled");
				this.buttons.button("enable");
			}
		}
		$.Widget.prototype._setOption.call( this, key, value );
	},
	
	_setOptions: function( options ) {
		$.Widget.prototype._setOptions.call( this, options );
		if ( "value" in options ) {
			this._format( this.options.value );
		}
		this._aria();
	},
	
	_aria: function() {
		this.uiSpinner
			.attr('aria-valuemin', this.options.min)
			.attr('aria-valuemax', this.options.max)
			.attr('aria-valuenow', this.options.value);
	},
	
	_parse: function(val) {
		var input = val;
		if (typeof val == 'string') {
			// special case for currency formatting until Globalization handles currencies
			if (this.options.numberformat == "C" && window.Globalization) {
				// parseFloat should accept number format, including currency
				var culture = Globalization.culture || Globalization.cultures['default'];
				val = val.replace(culture.numberFormat.currency.symbol, "");
			}
			val = window.Globalization && this.options.numberformat ? Globalization.parseFloat(val) : +val;
		}
		return isNaN(val) ? null : val;
	},
	
	_format: function(num) {
		var num = this.options.value;
		this.element.val( window.Globalization && this.options.numberformat ? Globalization.format(num, this.options.numberformat) : num );
	},
		
	destroy: function() {
		this.element
			.removeClass('ui-spinner-input')
			.removeAttr('disabled')
			.removeAttr('autocomplete');
		$.Widget.prototype.destroy.call( this );
		this.uiSpinner.replaceWith(this.element);
	},
	
	stepUp: function(steps) {
		this._spin((steps || 1) * this.options.step);
	},
	
	stepDown: function(steps) {
		this._spin((steps || 1) * -this.options.step);	
	},
	
	pageUp: function(pages) {
		this.stepUp((pages || 1) * pageModifier);		
	},
	
	pageDown: function(pages) {
		this.stepDown((pages || 1) * pageModifier);		
	},
	
	value: function(newVal) {
		if (!arguments.length) {
			return this._parse(this.element.val());
		}
		this.option('value', newVal);
	},
	
	widget: function() {
		return this.uiSpinner;
	}
});

})(jQuery);
