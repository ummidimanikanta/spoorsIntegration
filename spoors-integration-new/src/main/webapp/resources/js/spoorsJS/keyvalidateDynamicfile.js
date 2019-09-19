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
var EXPR_FOR_TYPE_TEXT = /[^<>`%=]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_TEXT_WITH_NEW_LINE = /[^%=<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -/m]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.';\"?: -]/;
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
var EXPR_FOR_TYPE_SSL = /[^%=<>`']|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|\\,.;\"?: -/m]/;; //Single select list
*/


var EXPR_FOR_TYPE_NAME = /[a-zA-Z0-9~@#&$^*()!_+|,.;: -]/;
//var EXPR_FOR_TYPE_ADDRESS = /[^%=/<>`]|[^p{L}][a-zA-Z0-9~@#&$^*()!_+{}|,.;\"?: -]/;
var EXPR_FOR_TYPE_ADDRESS = /[a-zA-Z0-9~@#&$^*()!_+{}|,.;?: -/]/;
var EXPR_FOR_TYPE_EMAIL = /[a-zA-Z0-9.!#$%&*+-/?^_`{|}~]/;
var EXPR_FOR_TYPE_COMPANY_NAME = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;?: -]/;
var EXPR_FOR_TYPE_PHONE = /[0-9\\(\\)+ -]/;
var EXPR_FOR_TYPE_COMPANY_LABEL = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;?: -]/;
var EXPR_FOR_TYPE_TEXT = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.';\/?%: -]/;
var EXPR_FOR_TYPE_TEXT_WITH_NEW_LINE = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;?%: -/m\n]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;?: -]/;
var EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;?: -]/;
var EXPR_FOR_TYPE_OTHER_TITLE = /[a-zA-Z0-9~@#&$^*()!_+{}\[\]|,.;?: -]/;
var EXPR_FOR_TYPE_COMPANY_LEVEL_ID = /[a-zA-Z0-9~@#&$^*()!_+/{}\[\]|,.;?: -]/;
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
var EXPR_FOR_TYPE_SSL = /[a-zA-Z0-9~@#&$^*()!_+{}|,.;\"?: -/m\n]/; //Single select list
var EXPR_FOR_TYPE_URL = /[a-zA-Z0-9@#&_+!,.;?|/: -]/;
var EXPR_FOR_TYPE_ALPHA_NUMERIC = /[a-zA-Z0-9 ]/;
var EXPR_FOR_TYPE_OTHER_LANGUAGES = /[\u0c00-\u0c7f\u0980-\u09ff\u0900-\u097f\u0b80-\u0bff\u0600-\u06ff\u0c80-\u0cff\u00a0-\u00ff\u0100-\u017f\u0180-\u024f\u0a80-\u0aff\u0370-\u03ff\u0400-\u04ff\u0500-\u052f\u0e00-\u0e7f\u1000-\u109f\u10a0-\u10ff\u1200-\u137f\u0b00-\u0b7f\u1e00-\u1eff\u20a0-\u20cf\u0d00-\u0d7f\u1f00-\u1fff\u1780-\u17ff\u19e0-\u19ff\u4e00-\u9fff\u3400-\u4dbf\uf900-\ufaff]/;

function inputType_FormOrEntityTitle_validate(ref){
		
		/* alert("Inside keyvalidate dynamically ");*/
         var str = $(ref).val();
         /*alert("str222 = "+str);*/
		 var newStr = "";
		 var pattern = EXPR_FOR_TYPE_FORM_OR_ENTITY_TITLE;
		 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
		 if(str!=undefined)
		 {
			 for(i=0;i<str.length;i++)
			 {
				 var char = str.charAt(i);
				 if(pattern.test(char) || patternForOtherLanguage.test(char))
				 {
					 newStr += char;
				 }
			 }
			 if(str != newStr)
			 {
				 newStr = newStr.replace(/(\r\n\t|\n|\r)/gm,"");
				 $(ref).val(newStr);
			 }
		 }
	}

 function inputType_FormOrEntityLabel_validate(ref){
		
		/* alert("Inside keyvalidate dynamically ");*/
         var str = $(ref).val();
         /*alert("str222 = "+str);*/
		 var newStr = "";
		 var pattern = EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL;
		 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
		 if(str!=undefined)
		 {
			 for(i=0;i<str.length;i++)
			 {
				 var char = str.charAt(i);
				 if(pattern.test(char) || patternForOtherLanguage.test(char))
				 {
					 newStr += char;
				 }
			 }
			 if(str != newStr)
			 {
				 $(ref).val(newStr);
			 }
		 }
	}

function inputType_Text_validate(ref){
	
	/* alert("Inside keyvalidate dynamically ");*/
     var str = $(ref).val();
     /*alert("str222 = "+str);*/
	 var newStr = "";
	 var pattern = EXPR_FOR_TYPE_TEXT;
	 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
	 if(str!=undefined)
	 {
		 for(i=0;i<str.length;i++)
		 {
			 var char = str.charAt(i);
			 if(pattern.test(char) || patternForOtherLanguage.test(char))
			 {
				 newStr += char;
			 }
		 }
		 if(str != newStr)
		 {
			 $(ref).val(newStr);
		 }
	 }
 }

function inputType_Alpha_numeric(ref){
	
     var str = $(ref).val();
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
			 $(ref).val(newStr);
		 }
	 }
 }

function inputType_Address_validate(ref){
	
	/* alert("Inside keyvalidate dynamically ");*/
     var str = $(ref).val();
     /*alert("str222 = "+str);*/
	 var newStr = "";
	 var pattern = EXPR_FOR_TYPE_ADDRESS;
	 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
	 if(str!=undefined)
	 {
		 for(i=0;i<str.length;i++)
		 {
			 var char = str.charAt(i);
			 if(pattern.test(char) || patternForOtherLanguage.test(char))
			 {
				 newStr += char;
			 }
		 }
		 if(str != newStr)
		 {
			 $(ref).val(newStr);
		 }
	 }
 }

function inputType_Text_with_new_line_validate(ref){
	
	/* alert("Inside keyvalidate dynamically ");*/
     var str = $(ref).val();
     /*alert("str222 = "+str);*/
	 var newStr = "";
	 var pattern = EXPR_FOR_TYPE_TEXT_WITH_NEW_LINE;
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
			 $(ref).val(newStr);
		 }
	 }
 }

function inputType_other_title_validate(ref){
	
	/* alert("Inside keyvalidate dynamically ");*/
     var str = $(ref).val();
     /*alert("str222 = "+str);*/
	 var newStr = "";
	 var pattern = EXPR_FOR_TYPE_OTHER_TITLE;
	 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
	 if(str!=undefined)
	 {
		 for(i=0;i<str.length;i++)
		 {
			 var char = str.charAt(i);
			 if(pattern.test(char) || patternForOtherLanguage.test(char))
			 {
				 newStr += char;
			 }
		 }
		 if(str != newStr)
		 {
			 $(ref).val(newStr);
		 }
	 }
 }


//We have appostropi problem in single select list and aslo in multi select list.
//Eg. lkp'k if we put option like this form spec not opened in edit mode successfully
//so we are doing for both single select list and mutli select list
//Text area we are displaying for single select list and mutli select list only 
//Single select list validation
function inputType_ssl_validate(ref){ 
	
	/* alert("Inside keyvalidate dynamically ");*/
     var str = $(ref).val();
     /*var fieldType = getDataType(ref);
     if(fieldType != 5){
    	 return;
     }*/
     //alert("str222 = "+str);
	 var newStr = "";
	 var pattern = EXPR_FOR_TYPE_SSL;
	 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
	 if(str!=undefined)
	 {
		 for(i=0;i<str.length;i++)
		 {
			 var char = str.charAt(i);
			 if(pattern.test(char) || patternForOtherLanguage.test(char))
			 {
				 newStr += char;
			 }
		 }
		 if(str != newStr)
		 {
			 $(ref).val(newStr);
		 }
	 }
 }

 function getDataType(ref){
	 var str = $(ref).val();
     var fieldType = $('.fieldType', ref).select2('val');
     var index = $(ref).attr('index');
     var sectionIndex = $(ref).attr('sectionIndex');
     var fieldType;
     var isFormField = false;
     var isSectionField = false;
     if(index){
    	 key = "formFieldSpecs"+index+"_fieldLabel";
    	 isFormField = true;
     }
     if(sectionIndex){
    	 key = "formSectionSpecs_"+sectionIndex+"_formSectionFieldSpecs"+index+"_fieldLabel";
    	 isFormField = false;
    	 isSectionField = true;
     }
     for(var k in fieldIdDependencyExpressionMap)
	 {
    	 if(k == key){
    		 if(isFormField)
    			 fieldType = $('#formFieldSpecs'+fieldIdDependencyExpressionMap[k].index+'_fieldType :selected').val();
    		 if(isSectionField)
    			 fieldType = $('#formSectionSpecs_'+fieldIdDependencyExpressionMap[k].sectionIndex+'_formSectionFieldSpecs'+fieldIdDependencyExpressionMap[k].index+'_fieldType :selected').val();
    	 }
	 }
     return fieldType;
 }
	
 
 function inputType_FormOrEntityLabel_validate1(ref, index, type){
		
		/* alert("Inside keyvalidate dynamically ");*/
      var str = $(ref).val();
      /*alert("str222 = "+str);*/
		 var newStr = "";
		 var pattern = EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL;
		 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
		 if(str!=undefined)
		 {
			 for(i=0;i<str.length;i++)
			 {
				 var char = str.charAt(i);
				 if(pattern.test(char) || patternForOtherLanguage.test(char))
				 {
					 newStr += char;
				 }
			 }
			 if(str != newStr)
			 {
				 $(ref).val(newStr);
			 }
			 if(type=="2"){
				 if(str != '')
					 $("#editable_section_head_"+index).html(str);
				 else
					 $("#editable_section_head_"+index).html("Enter the Label");
				 
			 }else if(type=="3"){
				 if(str != '')
					 $("#editable_group_head_"+index).html(str);
				 else
					 $("#editable_group_head_"+index).html("Enter the Label");
				 
			 }else{
				 if(str != '')
					 $("#editable"+index).html(str);
				 else
					 $("#editable"+index).html("Enter the Label");
			 }
		 }
	}
 
 function inputType_FormOrEntityLabel_validate2(ref, index, sectionIndex){
		
		/* alert("Inside keyvalidate dynamically ");*/
   var str = $(ref).val();
   /*alert("str222 = "+str);*/
		 var newStr = "";
		 var pattern = EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL;
		 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
		 if(str!=undefined)
		 {
			 for(i=0;i<str.length;i++)
			 {
				 var char = str.charAt(i);
				 if(pattern.test(char) || patternForOtherLanguage.test(char))
				 {
					 newStr += char;
				 }
			 }
			 if(str != newStr)
			 {
				 $(ref).val(newStr);
				 
			 }
			 if(str != '')
				 $("#section_field_label_"+sectionIndex+"_"+index).html(str);
			 else
				 $("#section_field_label_"+sectionIndex+"_"+index).html("Enter the Label");
		 }
	}

 
 function inputType_FormOrEntityTitle_validate4(ref, pageId){
		
	 var str = $(ref).val();
	   /*alert("str222 = "+str);*/
			 var newStr = "";
			 var pattern = EXPR_FOR_TYPE_FORM_OR_ENTITY_FIELD_LABEL;
			 var patternForOtherLanguage = EXPR_FOR_TYPE_OTHER_LANGUAGES;
			 if(str!=undefined)
			 {
				 for(i=0;i<str.length;i++)
				 {
					 var char = str.charAt(i);
					 if(pattern.test(char) || patternForOtherLanguage.test(char))
					 {
						 newStr += char;
					 }
				 }
				 if(str != newStr)
				 {
					 $(ref).val(newStr);
					 
				 }
				 if(str != '')
					 $("#pageLabel-"+pageId).html(str);
				 else
					 $("#pageLabel-"+pageId).html("Page "+(parseInt(pageId)+1));
				 
				 $('.nav-tabs').scrollingTabs('refresh');
//				 $('.nav-tabs').scrollingTabs();
			 }
	}