/**
* Purpose: blink a page element
* Preconditions: the element you want to apply the blink to, 
    the number of times to blink the element (or -1 for infinite times),
    the speed of the blink
**/
function blink(elem, times, speed)
{
    if (times > 0 || times < 0) { 
      if ($(elem).hasClass("blink"))
         $(elem).removeClass("blink");
      else
         $(elem).addClass("blink");
     }

     clearTimeout(function() { blink(elem, times, speed); });

     if (times > 0 || times < 0) {
       setTimeout(function() { blink(elem, times, speed); }, speed);
       times-= .5;
     }
}