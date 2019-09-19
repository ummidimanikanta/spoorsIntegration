/**
 * 
 */


function adjustWindow(){
	 var windowH = $( window ).height();
	 var  menuH = $("#header_id").height();
	 var footerH = $("#spoorsFooter").height();
	 
	 var containerH = windowH - (menuH+ footerH) ;
	 $("#bodyDiv").css("height", containerH);
	 $(".pageBodyOld").css("margin", "0px");
	 
	 
	}

