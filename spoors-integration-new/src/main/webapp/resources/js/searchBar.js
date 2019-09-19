var att='';
$.expr[":"].containsTitleNoCase = function(el, i, m) {
	var search = m[3];
	if (!search) return false;
	search = getNewSearchStringWithEscapingSpecialCharacters(search);
	return new RegExp(search, "i").test($(el).attr(att));
};

function getNewSearchStringWithEscapingSpecialCharacters(search)
{
	var newSearch = '';
	for(i=0;i<search.length;i++)
	 {
		 var char = search.charAt(i);
		 if(char == '$' || char == '*' || char == '^' || char == '(' || char == ')' || char == '+' || char == '.' || char == '?' || char == '|')
		 {
			 newSearch += '\\'+char;
		 }
		 else
		{
			 newSearch += char;
		}
	 }
	return newSearch;
}

function filterListByText(txtBoxId, optionDiv, attribute){
	var ele=document.getElementById(txtBoxId);
	if(ele==undefined || ele==null)
		return;
	
	att=attribute;
	var existingValue = $("#"+txtBoxId).val(),$rowsdefault = $("#"+optionDiv).children("div");
	if (existingValue.length > 0) {
		$rowsdefault.stop().hide(); 
		$("#"+optionDiv).find("div:containsTitleNoCase('" + existingValue + "')").stop().show();
	} else {
		$rowsdefault.stop().show();
	} 
	
	ele.addEventListener('keyup', function() {
		att=attribute;
		var srchTerm = $(this).val(),$rows = $("#"+optionDiv).children("div");
		//alert(srchTerm);
		if (srchTerm.length > 0) {
			$rows.stop().hide(); 
			$("#"+optionDiv).find("div:containsTitleNoCase('" + srchTerm + "')").stop().show();
		} else {
			$rows.stop().show();
		} 
	});
}

/*Date: 2016-04-06
*Function Purpose: filtering list with Free text search
                                   we are using .find() to find all its nested children
                                                .children() will give only immediate child level
*Resource: Deva*/

function filterListByTextWithInnerElements(txtBoxId, optionDiv, attribute){
	
	/*alert("txtBoxId = "+txtBoxId+"attribute = "+attribute);*/
	var ele=document.getElementById(txtBoxId);
	if(ele==undefined || ele==null)
		return;
	ele.addEventListener('keyup', function() {
		/*alert("inside keyup "+" optionDiv = "+optionDiv+" att = "+att);*/
		att=attribute;
		var srchTerm = $(this).val(),$rows = $("#"+optionDiv).find("div");
		//alert(srchTerm);
		if (srchTerm.length > 0) {
			$rows.stop().hide(); 
			$("#"+optionDiv).find("div:containsTitleNoCase('" + srchTerm + "')").stop().show();
		} else {
			$rows.stop().show();
		} 
	});
}


$.expr[":"].containsTitleNoCaseforli = function(el, i, m) {
	var search = m[3];
	if (!search) return false;
	search = getNewSearchStringWithEscapingSpecialCharacters(search);
	return new RegExp(search, "i").test($(el).text());
};
function filterListByTextbyli(txtBoxId,mainDiv, optionDiv, attribute){
	var ele=document.getElementById(txtBoxId);
	if(ele==undefined || ele==null)
		return;
	ele.addEventListener('keyup', function() {
		att=attribute;
		var srchTerm = $(this).val().toLowerCase();
		var $rows = $("."+optionDiv).children("li");
		var containText = $("#"+mainDiv).attr('containText');
		if(containText!=undefined && containText!='undefined'){
			var tt = (containText.toLowerCase());
			if(tt!=undefined && tt!='undefined'){
				if(tt.indexOf(srchTerm)==-1){
					$("#"+mainDiv).hide();
				}else{
					$("#"+mainDiv).show();
				}
			}
		}
		//alert(srchTerm);
		if (srchTerm.length > 0) {
			$rows.stop().hide(); 
			$("."+optionDiv).find("li:containsTitleNoCaseforli('" + srchTerm + "')").stop().show();
		} else {
			$rows.stop().show();
		} 
	});
}

function filterListByTextbyliAndParagraphTag(txtBoxId,mainDiv, optionDiv, attribute){
	var ele=document.getElementById(txtBoxId);
	if(ele==undefined || ele==null)
		return;
	ele.addEventListener('keyup', function() {
		att=attribute;
		var srchTerm = $(this).val().toLowerCase();
		var $rows1 = $("."+optionDiv).find("li").each(function(){
			
			$rows = $(this).children('p');
		
			var containText = $("#"+mainDiv).attr('containText');
		if(containText!=undefined && containText!='undefined'){
			var tt = (containText.toLowerCase());
			if(tt!=undefined && tt!='undefined'){
				if(tt.indexOf(srchTerm)==-1){
					$("#"+mainDiv).hide();
				}else{
					$("#"+mainDiv).show();
				}
			}
		}
		//alert(srchTerm);
		if (srchTerm.length > 0) {
			//$rows.stop().hide(); 
			var ele = $(this).stop().hide(); 
			//$("."+optionDiv).find("p:containsTitleNoCaseforli('" + srchTerm + "')").stop().show();
			 ele = $($(this).find("p:containsTitleNoCaseforli('" + srchTerm + "')").stop());
			 //alert($(ele).prop('nodeName'));
			 if($(ele).prop('nodeName') == 'P')
				 $(this).show();
			 
		} else {
			//$rows.stop().show();
			$(this).stop().show();
		}
		
		});
		 
	});
}