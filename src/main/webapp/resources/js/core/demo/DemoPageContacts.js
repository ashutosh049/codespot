(function(namespace, $) {
	"use strict";

	var DemoPageContacts = function() {
		// Create reference to this instance
		var o = this;
		// Initialize app when document is ready
		$(document).ready(function() {
			o.initialize();
		});

	};
	var p = DemoPageContacts.prototype;

	// =========================================================================
	// MEMBERS
	// =========================================================================

	p.map = null;

	// =========================================================================
	// INIT
	// =========================================================================

	p.initialize = function() {
//		this._initDuplicateSource();
		this._initSummernote();
//		this._initMultiselect();
//		this._initTypeahead();
//		this._initAutocomplete();
		this._initSelect2();
//		this._initMultiSelect();
	};

	// =========================================================================
	// DUPLICATE
	// =========================================================================

	p._initDuplicateSource = function(e) {
		var o = this;

		// Add event lsitener for duplication
		$('[data-duplicate]').on('click', function(e) {
			var item = $(this);
			var templateId = item.data('duplicate');
			var target = $(item.data('target'));
			o._duplicateTemplate(templateId, target);
		});

		// Init dulicate function
		$('[data-duplicate]').each(function() {
			var item = $(this);
			var templateId = item.data('duplicate');
			var target = $(item.data('target'));
			o._duplicateTemplate(templateId, target);
		});
	};

	p._duplicateTemplate = function(templateId, target) {
		if (typeof tmpl === 'undefined') {
			return;
		}
		var o = this;

		var index = (target.data('index') > 0) ? target.data('index') : target.children().length + 1;
		target.data('index', index + 1);
		var clonedContent = tmpl(templateId, {index: index});

		// Add cloned source to parent
		var newContent = $(clonedContent).appendTo(target).hide().slideDown('fast');

		// Init date component
		this._initDatetime(newContent, index);

		// Add delete event
		newContent.on('click', '.btn-delete', function(e) {
			newContent.slideUp('fast', function() {
				newContent.remove();
			});
		});
	};
	
	// =========================================================================
	// TYPEAHEAD
	// =========================================================================

	p._initTypeahead = function () {
		if (!$.isFunction($.fn.typeahead)) {
			return;
		}
		var countries = new Bloodhound({
			datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
			queryTokenizer: Bloodhound.tokenizers.whitespace,
			limit: 10,
			prefetch: {
				// url points to a json file that contains an array of country names, see
				// https://github.com/twitter/typeahead.js/blob/gh-pages/data/countries.json
				url: $('#autocomplete1').data('source'),
				// the json file contains an array of strings, but the Bloodhound
				// suggestion engine expects JavaScript objects so this converts all of
				// those strings
				filter: function (list) {
					return $.map(list, function (country) {
						return {name: country};
					});
				}
			}
		});
		countries.initialize();
		$('#autocomplete1').typeahead(null, {
			name: 'countries',
			displayKey: 'name',
			// `ttAdapter` wraps the suggestion engine in an adapter that
			// is compatible with the typeahead jQuery plugin
			source: countries.ttAdapter()
		});
	};


	// =========================================================================
	// SUMMERNOTE EDITOR
	// =========================================================================

	/*p._initSummernote = function() {
		if (!$.isFunction($.fn.summernote)) {
			return;
		}
		if ($('#summernote').length === 0) {
			return;
		}
		
		$('#summernote').summernote({
			height: $('#summernote').height(),
			toolbar: [
				['style', ['bold', 'italic', 'underline', 'clear']],
				['fontsize', ['fontsize']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['height', ['height']]
			]
		});
	};*/
	
	p._initSummernote = function () {
		if (!$.isFunction($.fn.summernote)) {
			return;
		}

		// Full toolbar
//		$('#summernote').summernote();
		
		// Simple toolbar
		$('#description').summernote({
			height: $('#description').height()
		});
		
		/*$('#description').summernote({
			height: $('#description').height(),
		    tabsize: 2,
		    prettifyHtml:false,
		    toolbar:[
		        ['highlight', ['highlight']],
		        ['style', ['bold', 'italic', 'underline', 'clear']],
				['fontsize', ['fontsize']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['height', ['height']]
		    ],
		    lang:'tr-TR'
		});*/
		/*$('#description').summernote({
		    prettifyHtml:true,
		    toolbar:[
		        ['highlight', ['highlight']]
		    ],
		    lang:'tr-TR'
		});*/
	};
	

	
	
	// =========================================================================
	// AUTOCOMPLETE
	// =========================================================================

	p._initAutocomplete = function () {
		if (!$.isFunction($.fn.autocomplete)) {
			return;
		}

		$.ajax({
			url: $('#autocomplete2').data('source'),
			dataType: "json",
			success: function (countries) {
				$("#autocomplete2").autocomplete({
					source: function (request, response) {
						var results = $.ui.autocomplete.filter(countries, request.term);
						response(results.slice(0, 10));
					}
				});
			}
		});
	};
	// =========================================================================
	// SELECT2
	// =========================================================================

	p._initSelect2 = function () {
		if (!$.isFunction($.fn.select2)) {
			return;
		}
		$(".select2-list").select2({
			allowClear: true
		});
	};

	// =========================================================================
	// MULTISELECT
	// =========================================================================

	p._initMultiselect = function() {
		if (!$.isFunction($.fn.multiselect)) {
			return;
		}

		$('select[name="category"]').multiselect({
			buttonClass: 'form-control',
			buttonContainer: '<div class="btn-group btn-group-justified" />'
		});
		$('#optgroup').multiSelect({selectableOptgroup: true});
	};

	// =========================================================================
	// DATETIME
	// =========================================================================

	p._initDatetime = function() {
		if (!$.isFunction($.fn.datepicker)) {
			return;
		}

		$('.input-daterange').datepicker({todayHighlight: true});
	};

	// =========================================================================
	// InputMask
	// =========================================================================

	p._initInputMask = function() {
		if (!$.isFunction($.fn.inputmask)) {
			return;
		}
		$(":input").inputmask();
	};

	// =========================================================================
	namespace.DemoPageContacts = new DemoPageContacts;
}(this.materialadmin, jQuery)); // pass in (namespace, jQuery):
