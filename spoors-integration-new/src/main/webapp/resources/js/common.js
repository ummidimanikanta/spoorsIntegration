function loadDateTime(hiddenFiled, dateField, timeFiled){
	try{
	var dateTimeRaw = document.getElementById(hiddenFiled).value;
	if(dateTimeRaw.length > 0){
		var dateTime = dateTimeRaw.trim().split(" ");
		var time = null;
		
		if(dateTime.length > 1){
			time = dateTime[1].trim().split(":");
		}
		
		var dateText = dateTime[0].trim();
		var timeText = null;
		
		if(time != null){
			timeText = time[0].trim() + ":" + time[1].trim();
		}
		
		if(dateField != null){
			document.getElementById(dateField).value = dateText;
		}
		if(timeFiled != null && time != null){
			document.getElementById(timeFiled).value = timeText;
		}
	}
	}catch(e){
		
	}
}

function intialLoadDateTime(hiddenField, dateTimeField){
	var dateTime = null;
	dateTime = document.getElementById(dateTimeField).value;
	if(dateTime.length >= 16 ){
		document.getElementById(hiddenField).value = dateTime + ":00";
	}
}

function compileDateTime(hiddenFiled, dateField, timeFiled){
	var date = null;
	var time = null;
	
	date = document.getElementById(dateField).value;

	if(timeFiled != null){
		time = document.getElementById(timeFiled).value;
	}

	if(date.length > 0){
		if(timeFiled != null){
//			if(time.trim().length==5){
			document.getElementById(hiddenFiled).value = date + " " + time + ":00";
//			}else{
//				document.getElementById(hiddenFiled).value = date + " " + time;
//			}
		} else {
			document.getElementById(hiddenFiled).value = date + " 00:00:00";
		}
	} else {
		document.getElementById(hiddenFiled).value = "";
	}
}



function compileDateTime1(hiddenFiled, dateField, timeFiled){
	var date = null;
	var time = null;
	
	date = document.getElementById(dateField).value;

	if(timeFiled != null){
		time = document.getElementById(timeFiled).value;
	}

	if(date.length > 0){
		if(timeFiled != null){
			if(time.trim().length==5){
			document.getElementById(hiddenFiled).value = date + " " + time + ":00";
			}else{
				document.getElementById(hiddenFiled).value = date + " " + time;
			}
		} else {
			document.getElementById(hiddenFiled).value = date + " 00:00:00";
		}
	} else {
		document.getElementById(hiddenFiled).value = "";
	}
}

function  populateDateTime(hiddenFiled, dateTimeField){
	var dateTime = null; 
	dateTime = document.getElementById(dateTimeField).value;
	
	if(dateTime == 'undefined' || dateTime != "" ){
		if(dateTime.length >= 16){
			document.getElementById(hiddenFiled).value = dateTime + ":00";
		}
	}else{
		document.getElementById(hiddenFiled).value = "";
	}
}

function encodeNewLinesAndQuotes(string){
	string = replaceAll(string, "\n", "&-nl-n;");
	string = replaceAll(string, "\r", "&-nl-r;");
	string = replaceAll(string, "'", "&-sq;");
	string = replaceAll(string, "\"", "&-dq;");
	return string;
}

function decodeNewLinesAndQuotes(string){
	string = replaceAll(string, "&-nl-n;", "\n");
	string = replaceAll(string, "&-nl-r;", "\r");
	string = replaceAll(string, "&-sq;", "'");
	string = replaceAll(string, "&-dq;", "\"");
	return string;
}

function dcodeHtml(string){
	string = replaceAll(string, '&lt;', '<');
	string = replaceAll(string, '&gt;', '>');
	string = replaceAll(string, '&amp;', '&');
	string = replaceAll(string, '&quot;', '"');
	string = replaceAll(string, '&#x27;', '\'');
	string = replaceAll(string, '&#x2F;', '/');
	string = replaceAll(string, '&#nln;', '\n');
	string = replaceAll(string, '&#slN;', '\r');
	return string;
}


function replaceAll(source, stringToFind, stringToReplace){
	var temp = source;
	var index = temp.indexOf(stringToFind);
	while(index != -1){
		temp = temp.replace(stringToFind,stringToReplace);
		index = temp.indexOf(stringToFind);
	}
	return temp;
}

function sortDropDownListByText(selectId) {
    var foption = $('#'+ selectId + ' option:first');
    var soptions = $('#'+ selectId + ' option:not(:first)').sort(function(a, b) {
       return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
    });
    $('#' + selectId).html(soptions).prepend(foption);              

};

function insertAtCaret(areaId,text) {
    var txtarea = document.getElementById(areaId);
    var scrollPos = txtarea.scrollTop;
    var strPos = 0;
    var br = ((txtarea.selectionStart || txtarea.selectionStart == '0') ? 
    	"ff" : (document.selection ? "ie" : false ) );
    if (br == "ie") { 
    	txtarea.focus();
    	var range = document.selection.createRange();
    	range.moveStart ('character', -txtarea.value.length);
    	strPos = range.text.length;
    }
    else if (br == "ff") strPos = txtarea.selectionStart;

    var front = (txtarea.value).substring(0,strPos);  
    var back = (txtarea.value).substring(strPos,txtarea.value.length); 
    txtarea.value=front+text+back;
    strPos = strPos + text.length;
    if (br == "ie") { 
    	txtarea.focus();
    	var range = document.selection.createRange();
    	range.moveStart ('character', -txtarea.value.length);
    	range.moveStart ('character', strPos);
    	range.moveEnd ('character', 0);
    	range.select();
    }
    else if (br == "ff") {
    	txtarea.selectionStart = strPos;
    	txtarea.selectionEnd = strPos;
    	txtarea.focus();
    }
    txtarea.scrollTop = scrollPos;
}

function checkDateDiffCondition(startDate,endDate,numOfDays){
	// The number of milliseconds in one day
    var ONE_DAY = 1000 * 60 * 60 * 24;
    
	var from =dateutil.parse(startDate).getTime();
	var to =dateutil.parse(endDate).getTime();
	
   var difference_ms = Math.abs(from - to);
    
    // Convert back to days and return
     var calculatedNumDays= Math.round(difference_ms/ONE_DAY);
     
     if(calculatedNumDays>numOfDays){
    	 return false;
     }
     
     return true;
	
	
}


$(document).ready(function(){
	$(".Multiselect").each(function(index){
		applyMultiSelect($( this ).attr('id'));
	});
});

function applyMultiSelect(id){
	$("#"+id).multiselect();
	
}


function toCsv(array){
	var csvString="";
	if(array!=undefined && array!=null && array instanceof Array ){
		for(var i=0;i<array.length;i++){
			if(!isEmptyString(csvString)){
				csvString=csvString+",";
			}
			csvString=csvString+array[i];
		}
	}
	return  csvString;
}

function isEmptyString(data){
	if(data==undefined ||data==""||data==null){
		return true;
	}
}




/*$(document).ready(function(){
	$(".numeric").numeric({ negative: true }, function() { alert("No negative values"); this.value = ""; this.focus(); });
	
});

function applyDate(id){
	$("#"+id).datepicker({ 
         minDate: new Date, 
        dateFormat: 'yy-mm-dd',
        showOn: 'button',
        buttonText: 'Pick Date',
    buttonImageOnly: true, 
    buttonImage: '${pageContext.servletContext.contextPath}/resources/img/calendar_picker.png'
});
	
}*/

