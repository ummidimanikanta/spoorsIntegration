/**
 * This file is for validation of regex on sign up page
 */

/*var EXPR_FOR_TYPE_NAME = /[^%=/{}<>?'`"]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+|\\,.;: -]/;
//var EXPR_FOR_TYPE_ADDRESS = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_ADDRESS = /[^%=<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -/]/;
var EXPR_FOR_TYPE_EMAIL = /[a-zA-Z0-9.!#$%&'*+-/?^_`{|}~]/;
var EXPR_FOR_TYPE_COMPANY_NAME = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_PHONE = /[0-9\\(\\)+ -]/;
var EXPR_FOR_TYPE_COMPANY_LABEL = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[^p{L}]//[a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[^p{L}<>]|[a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /^[<>`%=]&[^p{L}]|[a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[^p{L}]|^[%=/<>`]|[a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[^p{L}]|^[<>`%=]&[a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[^<>`%=]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+/{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_TEXT = /[^<>`%=]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[^<>`%=]|[^p{L}]&[a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[\u0C00-\u0C7Fa-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
//var EXPR_FOR_TYPE_TEXT = /[\u0C00-\u0C7F\u0900-\u097Fa-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE = /[^%=/<>`/m]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_OTHER_TITLE = /[^%=/<>`"]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_COMPANY_LEVEL_ID = /[^%=<>'`"]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+/{}|\\,.;?: -]/;
var EXPR_FOR_TYPE_CAPTCHA = /[a-zA-Z0-9]/;
var EXPR_FOR_TYPE_NUMBER = /[0-9.-]/;
var EXPR_FOR_TYPE_NUMBER_CSV = /[0-9.,-]/;
var EXPR_FOR_TYPE_ALPHABET = /[a-zA-Z ]/;
var EXPR_FOR_TYPE_ALPAHBET_WITH_UNDERSCORE = /[a-zA-Z _]/;
var EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN = /[a-zA-Z -]/;
var EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE = /[a-zA-Z_ -]/;
var EXPR_FOR_TYPE_DATE_TIME = /[a-zA-Z0-9/: -]/;
var EXPR_FOR_TYPE_JSON = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}\\[\\]|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_BOOLEAN = /(y|e|s|Y|E|S|n|o|N|O|t|r|u|T|R|U|F|A|L|f|a|l|1|0)/;
var EXPR_FOR_TYPE_LOCATION = /[0-9., ]/;
var EXPR_FOR_TYPE_ONLY_POSITIVE_NUMBER = /[0-9]/;*/


var EXPR_FOR_TYPE_NAME = /[a-zA-Z0-9~@#&$^*()!_+|,.;: -]/;
var EXPR_FOR_TYPE_ADDRESS = /[a-zA-Z0-9~@#&$^*(){}\?/!_+|,.;: -]/;
//var EXPR_FOR_TYPE_ADDRESS = /[a-zA-Z0-9~@#&$^*()!_+{}|,.;\?: -/]/;
var EXPR_FOR_TYPE_EMAIL = /[a-zA-Z0-9.!#$%&*+-/?^_`{|}~]/;
var EXPR_FOR_TYPE_COMPANY_NAME = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;\?: -]/;
var EXPR_FOR_TYPE_PHONE = /[0-9\\(\\)+ -]/;
var EXPR_FOR_TYPE_COMPANY_LABEL = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;\?: -]/;
var EXPR_FOR_TYPE_TEXT = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.';\/?%: -]/;
var EXPR_FOR_TYPE_TEXT_WITH_NEW_LINE = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;?%: -/m\n]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;\?: -]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;\?: -]/;
var EXPR_FOR_TYPE_OTHER_TITLE = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;\?: -]/;
var EXPR_FOR_TYPE_COMPANY_LEVEL_ID = /[a-zA-Z0-9~@#&$^*()!_+/{}|,.;?: -]/;
var EXPR_FOR_TYPE_CAPTCHA = /[a-zA-Z0-9]/;
var EXPR_FOR_TYPE_NUMBER = /[0-9.-]/;
var EXPR_FOR_TYPE_NUMBER_CSV = /[0-9.,-]/;
var EXPR_FOR_TYPE_ALPHABET = /[a-zA-Z ]/;
var EXPR_FOR_TYPE_ALPAHBET_WITH_UNDERSCORE = /[a-zA-Z _]/;
var EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN = /[a-zA-Z -]/;
var EXPR_FOR_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE = /[a-zA-Z_ -]/;
var EXPR_FOR_TYPE_DATE_TIME = /[a-zA-Z0-9/: -]/;
var EXPR_FOR_TYPE_JSON = /[a-zA-Z0-9~@#&$^*()!_+{}\\[\\]|\\,.;\"?: -]/;
var EXPR_FOR_TYPE_BOOLEAN = /(y|e|s|Y|E|S|n|o|N|O|t|r|u|T|R|U|F|A|L|f|a|l|1|0)/;
var EXPR_FOR_TYPE_LOCATION = /[0-9., ]/;
var EXPR_FOR_TYPE_ONLY_POSITIVE_NUMBER = /[0-9]/;
var EXPR_FOR_TYPE_URL = /[a-zA-Z0-9@#&_+!/,.;?|: -]/;
var EXPR_FOR_TYPE_ALPHA_NUMERIC = /[a-zA-Z0-9 ]/;
var EXPR_FOR_TYPE_OTHER_LANGUAGES = /[\u0c00-\u0c7f\u0980-\u09ff\u0900-\u097f\u0b80-\u0bff\u0600-\u06ff\u0c80-\u0cff\u00a0-\u00ff\u0100-\u017f\u0180-\u024f\u0a80-\u0aff\u0370-\u03ff\u0400-\u04ff\u0500-\u052f\u0e00-\u0e7f\u1000-\u109f\u10a0-\u10ff\u1200-\u137f\u0b00-\u0b7f\u1e00-\u1eff\u20a0-\u20cf\u0d00-\u0d7f\u1f00-\u1fff\u1780-\u17ff\u19e0-\u19ff\u4e00-\u9fff\u3400-\u4dbf\uf900-\ufaff]/;

	$(document).ready(function()
	{    
		$(".inputTypeName").on("keyup",function(e)
		{
			 var str = $(this).val();
			 var newStr = "";
			 var pattern = EXPR_FOR_TYPE_NAME;
			 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
			 if(str!=undefined)
			 {
				 for(i=0;i<str.length;i++)
				 {
					 var char = str.charAt(i);
					 if(pattern.test(char) || patternForOtherLanguage.exec(char))
					 {
						 newStr += char;
					 }
				 }
				 if(str != newStr)
				 {
					 $(this).val(newStr);
				 }
			 }
		});
		
		$(".inputTypeCompanyName").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_COMPANY_NAME;
					 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char) || patternForOtherLanguage.exec(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypePhone").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_PHONE;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeUrl").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_URL;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeOnlyPositiveNumber").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_ONLY_POSITIVE_NUMBER;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeCompanyLabel").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_COMPANY_LABEL;
					 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char) || patternForOtherLanguage.exec(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeOtherTitle").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_OTHER_TITLE;
					 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char) || patternForOtherLanguage.exec(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeText").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_TEXT;
					 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char) || patternForOtherLanguage.exec(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeTextWithNewLine").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_TEXT_WITH_NEW_LINE;
					 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char) || patternForOtherLanguage.exec(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		
		$(".inputTypeCompanyLevelId").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_COMPANY_LEVEL_ID;
					 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char) || patternForOtherLanguage.exec(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeAddress").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_ADDRESS;
					 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char) || patternForOtherLanguage.exec(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeNumber").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_NUMBER;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
		$(".inputTypeAlphaNumeric").on("keyup",function(e)
				{
					 var str = $(this).val();
					 var newStr = "";
					 var pattern = EXPR_FOR_TYPE_ALPHA_NUMERIC;
					 if(str!=undefined)
					 {
						 for(i=0;i<str.length;i++)
						 {
							 var char = str.charAt(i);
							 if(pattern.test(char))
							 {
								 newStr += char;
							 }
						 }
						 if(str != newStr)
						 {
							 $(this).val(newStr);
						 }
					 }
				});
		
	});
	

	