jQuery.fn.extend({
    country : function(countrySelectId) {
//    	var mode = 1;
//		var countryIds = ["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120","121","122","123","124","125","126","127","128","129","130","131","132","133","134","135","136","137","138","139","140","141","142","143","144","145","146","147","148","149","150","151","152","153","154","155","156","157","158","159","160","161","162","163","164","165","166","167","168","169","170","171","172","173","174","175","176","177","178","179","180","181","182","183","184","185","186","187","188","189","190","191","192","193","194","195","196","197","198","199","200","201","202","203","204","205","206","207","208","209","210","211","212","213","214","215","216","217","218","219","220","221","222","223","224","225","226","227","228","229","230","231","232","233","234","235","236","237","238","239","240","241","242","243","244","245","246","247","248","249","250","251","252","253"];
		var countries = ["Afghanistan","Albania","Algeria","American Samoa","Angola","Anguilla","Antartica","Antigua and Barbuda","Argentina","Armenia","Aruba","Ashmore and Cartier Island","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Brazil","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burma","Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Central African Republic","Chad","Chile","China","Christmas Island","Clipperton Island","Cocos (Keeling) Islands","Colombia","Comoros","Congo, Democratic Republic of the","Congo, Republic of the","Cook Islands","Costa Rica","Cote d'Ivoire","Croatia","Cuba","Cyprus","Czeck Republic","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Europa Island","Falkland Islands (Islas Malvinas)","Faroe Islands","Fiji","Finland","France","French Guiana","French Polynesia","French Southern and Antarctic Lands","Gabon","Gambia, The","Gaza Strip","Georgia","Germany","Ghana","Gibraltar","Glorioso Islands","Greece","Greenland","Grenada","Guadeloupe","Guam","Guatemala","Guernsey","Guinea","Guinea-Bissau","Guyana","Haiti","Heard Island and McDonald Islands","Holy See (Vatican City)","Honduras","Hong Kong","Howland Island","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Ireland, Northern","Israel","Italy","Jamaica","Jan Mayen","Japan","Jarvis Island","Jersey","Johnston Atoll","Jordan","Juan de Nova Island","Kazakhstan","Kenya","Kiribati","Korea, North","Korea, South","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia, Former Yugoslav Republic of","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Man, Isle of","Marshall Islands","Martinique","Mauritania","Mauritius","Mayotte","Mexico","Micronesia, Federated States of","Midway Islands","Moldova","Monaco","Mongolia","Montserrat","Morocco","Mozambique","Namibia","Nauru","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger","Nigeria","Niue","Norfolk Island","Northern Mariana Islands","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Pitcaim Islands","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romainia","Russia","Rwanda","Saint Helena","Saint Kitts and Nevis","Saint Lucia","Saint Pierre and Miquelon","Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Scotland","Senegal","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Georgia and South Sandwich Islands","Spain","Spratly Islands","Sri Lanka","Sudan","Suriname","Svalbard","Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Tobago","Toga","Tokelau","Tonga","Trinidad","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","Uruguay","USA","Uzbekistan","Vanuatu","Venezuela","Vietnam","Virgin Islands","Wales","Wallis and Futuna","West Bank","Western Sahara","Yemen","Yugoslavia","Zambia","Zimbabwe","Myanmar"];
		
		var countrySelect = $(countrySelectId);
		
		var selectedCountry = countrySelect.attr('selectedval');
		
		resetSelect(countrySelect);
		
		$.each(countries, function(index, value) {
//			var countryId = value;
			var country = value;
			
			var option = new Option(country, country);
			$(option).html(country);
			
			if(country == selectedCountry){
				$(option).attr('selected','selected');
			}
			
			countrySelect.append(option);
		});
		
		countrySelect.find("option[value='India']").remove();
		countrySelect.find("option[value='']").remove();
		countrySelect.prepend("<option value='India'>India</option>");
		countrySelect.prepend("<option value=''>  --Select--  </option>");
		
		if(!selectedCountry){
			countrySelect.val('');
		} else {
			countrySelect.val(selectedCountry);
		}
		
		function resetSelect(select){
			select.empty();
			
			var defaultOption = new Option("  --Select--  ", "");
			$(defaultOption).html("  --Select--  ");
			select.append(defaultOption);
		}
    }
});
