

function jqPieChart(id,data){

  //Get jqplot object
  var plot1 = jQuery.jqplot (id, [data], 
    { 
         seriesDefaults: {
        // Make this a pie chart.
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          showDataLabels: true,
          height:300,
		  seriesColors: [ "#9033FF", "#33DAFF"]
        }
      }, 
      grid: {borderColor: 'white', shadow: false, drawBorder: true},
      legend: {
		 show:true,		
		 location: 'ne',
		}
    }
  );

	$(window).resize(function() {
          plot1.replot( { resetAxes: true } );
          
          if($(window).width() >= 769) {
              
      		//console.log("1111height="+$(window).height());
       		 
        	 $('#chart1ParentDiv').removeClass('col-xs-12 col-sm-12');
      		 $('#chart2ParentDiv').removeClass('col-xs-12 col-sm-12');

      		 $('#chart1ParentDiv').addClass('col-md-6 col-lg-6');
      		 $('#chart2ParentDiv').addClass('col-md-6 col-lg-6');

           }
           else{
                 $('#chart1ParentDiv').removeClass('col-md-6 col-lg-6');
      		     $('#chart2ParentDiv').removeClass('col-md-6 col-lg-6');

      		     $('#chart1ParentDiv').addClass('col-xs-12 col-sm-12');
      		     $('#chart2ParentDiv').addClass('col-xs-12 col-sm-12'); 
              }
              
    });

   $('#'+id).bind('jqplotDataClick', function(ev, seriesIndex, pointIndex, data) {
        //alert(plot1.series[seriesIndex].seriesColors[pointIndex]);
    	//alert('itemName : '+data[0]+' value : '+data[1]);
	   var itemName = data[0];
	   //alert("itemName = "+itemName);
	   console.log("itemName = "+itemName);
	   
	   if(itemName == 'Employees without day plans')
		   mouse_over_without_dayplans("overdue","withoutDayplans");
	   
	   else if(itemName == 'Employees with day plan')
		   mouse_over_with_dayplans("overdue","withDayplans");
	   
	   else if(itemName == 'Employees With Route Plan')
		   mouse_over_with_routeplans("overdue","withRoutePlans");
	   
	   else if(itemName == 'Employees Without Route Plan')
		  mouse_over_without_routeplans("overdue","withoutRoutePlans");
	   
    });

}//jqPieChart






