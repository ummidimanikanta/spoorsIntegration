// Parallel Polylines
// http://matthewschwartz.me/parallel-lines-and-google-maps-v3/
// Original Google Maps V2 awesome code by: Bill Chadwick March 2008
// Released as Free for any use @ http://wtp2.appspot.com/ParallelLines.htm
//
// Modified for use with GMaps V3 by: Matthew Schwartz (schwartz.matthew@schwartzlink.net)
// Also released as free for any use

// Modified again for use by Jereme Causing. Fixed some errors and added parameters -June 2013

/*
* BDCCParallelLines(Array points<lat,lng>, String color, float weight, float opacity, float gap, String _type)
* _type can be either polyline or polygon. polyline is the default
* returns OverlayView();
*/
function BDCCParallelLines(points, color, weight, opacity, gapPx, _type, _editable) {

  this.gapPx = gapPx;
  this.points = points;
  this.color = color;
  this.weight = weight;
  this.opacity = opacity;
  this.prj = null;
  this.line1 = null;
  this.line2 = null;
  this.zoomListener = null;
  this._type = _type;
  this._editable = false;
 


  this.polygon = null;
  if(_editable){

    this._editable = _editable; //boolean
  }
}


BDCCParallelLines.prototype = new google.maps.OverlayView();

// BDCCParallelLines implements the OverlayView interface
// Methods that need to be implemented in GMaps 3 = onAdd(), draw(), and onRemove()




BDCCParallelLines.prototype.onAdd = function() {
  this.setProjection();
  var foo = this;
  var zoomRecalc = function() {
    foo.onRemove();
    foo.setProjection();
  };

  this.zoomListener = google.maps.event.addListener(this.map, 'zoom_changed', zoomRecalc);
}

BDCCParallelLines.prototype.setProjection = function() {
  this.map = this.getMap();
  var overlay = new google.maps.OverlayView();
  overlay.draw = function() {};
  overlay.setMap(this.map);
  this.prj = overlay.getProjection();
}

BDCCParallelLines.prototype.onRemove = function() {
  if(this.line2) {
    this.line2.setMap(null);
    this.line2 = null;
  }
  if(this.line1) {
    this.line1.setMap(null);
    this.line1 = null;
  }
  if (this.prj) {
    this.prj = null;
  }
  if(this.zoomListener != null) {
    google.maps.event.removeListener(this.zoomListener);
  }
  if(this.polygon){
    this.polygon.setMap(null);
  }
}
BDCCParallelLines.prototype.draw = function(map) {
  if(this.line2) {
    this.line2.setMap(null);
    this.line2 = null;
  }
  if(this.line1) {
    this.line1.setMap(null);
    this.line1 = null;
  }
  this.recalc();
  return;
}

BDCCParallelLines.prototype.redraw = function(force) {
    return; //do nothing
}


BDCCParallelLines.prototype.recalc = function() {

 // var zoom = this.map.getZoom();
  var zoom = this.map.getZoom();

  //left and right swapped throughout!

  var pts1 = new Array();//left side of center
  var pts2 = new Array();//right side of center

  //shift the pts array away from the centre-line by half the gap + half the line width
  var o = (this.gapPx + this.weight)/2;

  var p2l,p2r;

  for (var i=1; i<this.points.length; i++){
    var p1lm1;
    var p1rm1;
    var p2lm1;
    var p2rm1;
    var thetam1;

    var p1 = this.prj.fromLatLngToContainerPixel(this.points[i-1]);
    var p2 = this.prj.fromLatLngToContainerPixel(this.points[i]);
    var theta = Math.atan2(p1.x-p2.x,p1.y-p2.y) + (Math.PI/2);
    var dl = Math.sqrt(((p1.x-p2.x)*(p1.x-p2.x))+((p1.y-p2.y)*(p1.y-p2.y)));
      if(theta > Math.PI)
          theta -= Math.PI*2;
    var dx = Math.round(o * Math.sin(theta));
    var dy = Math.round(o * Math.cos(theta));

    var p1l = new google.maps.Point(p1.x+dx,p1.y+dy);
    var p1r = new google.maps.Point(p1.x-dx,p1.y-dy);
    p2l = new google.maps.Point(p2.x+dx,p2.y+dy);
    p2r = new google.maps.Point(p2.x-dx,p2.y-dy);

    if(i==1){   //first point
      pts1.push(this.prj.fromContainerPixelToLatLng(p1l));
      pts2.push(this.prj.fromContainerPixelToLatLng(p1r));
    }

    else{ // mid points

    if(theta == thetam1){
      // adjacent segments in a straight line
      pts1.push(this.prj.fromContainerPixelToLatLng(p1l));
      pts2.push(this.prj.fromContainerPixelToLatLng(p1r));
    }
    else{
      var pli = this.intersect(p1lm1,p2lm1,p1l,p2l);
      var pri = this.intersect(p1rm1,p2rm1,p1r,p2r);

      var dlxi = (pli.x-p1.x);
      var dlyi = (pli.y-p1.y);
      var drxi = (pri.x-p1.x);
      var dryi = (pri.y-p1.y);
      var di = Math.sqrt((drxi*drxi)+(dryi*dryi));
      var s = o / di;

      var dTheta = theta - thetam1;
      if(dTheta < (Math.PI*2))
        dTheta += Math.PI*2;
      if(dTheta > (Math.PI*2))
        dTheta -= Math.PI*2;

      if(dTheta < Math.PI){
        //intersect point on outside bend
        pts1.push(this.prj.fromContainerPixelToLatLng(p2lm1));
        pts1.push(this.prj.fromContainerPixelToLatLng(new google.maps.Point(p1.x+(s*dlxi),p1.y+(s*dlyi)),zoom));
        pts1.push(this.prj.fromContainerPixelToLatLng(p1l));
      }
      else if (di < dl){
        pts1.push(this.prj.fromContainerPixelToLatLng(pli));
      }
      else{
        pts1.push(this.prj.fromContainerPixelToLatLng(p2lm1));
        pts1.push(this.prj.fromContainerPixelToLatLng(p1l));
      }

      var dxi = (pri.x-p1.x)*(pri.x-p1.x);
      var dyi = (pri.y-p1.y)*(pri.y-p1.y);
      if(dTheta > Math.PI){
        //intersect point on outside bend
        pts2.push(this.prj.fromContainerPixelToLatLng(p2rm1));
        pts2.push(this.prj.fromContainerPixelToLatLng(new google.maps.Point(p1.x+(s*drxi),p1.y+(s*dryi)),zoom));
        pts2.push(this.prj.fromContainerPixelToLatLng(p1r));
      }
      else if(di<dl)
  pts2.push(this.prj.fromContainerPixelToLatLng(pri));
      else{
        pts2.push(this.prj.fromContainerPixelToLatLng(p2rm1));
        pts2.push(this.prj.fromContainerPixelToLatLng(p1r));
      }
    }
}

    p1lm1 = p1l;
    p1rm1 = p1r;
    p2lm1 = p2l;
    p2rm1 = p2r;
    thetam1 = theta;
  }

  if(this._type == 'polyline' || this._type == null) //default
  {
   
  pts1.push(this.prj.fromContainerPixelToLatLng(p2l));//final point
  pts2.push(this.prj.fromContainerPixelToLatLng(p2r));

  if(this.line1)
    this.line1.setMap(null);
  this.line1 = new google.maps.Polyline({
    path: pts1,
    strokeColor: this.color,
    strokeOpacity: this.opacity,
    strokeWeight: this.weight,
    editable: this._editable
  });
  this.line1.setMap(this.map);
   if(this.line2)
     this.line1.setMap(null);
   this.line2 = new google.maps.Polyline({
     path: pts2,
     strokeColor: this.color,
     strokeOpacity: this.opacity,
     strokeWeight: this.weight
   });
   this.line2.setMap(this.map);
  }else if(this._type == 'polygon' ){

  pts1.push(this.prj.fromContainerPixelToLatLng(p2l));//final point
  pts2.push(this.prj.fromContainerPixelToLatLng(p2r));


  var newpts = pts1.concat(pts2.reverse());
  if(this.polygon){
      this.polygon.setMap(null);
    }
  this.polygon = new google.maps.Polygon({
    paths: newpts,
    editable: true,
    strokeColor: this.color,
    fillColor: this.color,
    strokeOpacity: this.opacity,
    strokeWeight: this.weight,
    editable: this._editable
  });
  this.polygon.setMap(this.map);

  /* if(this.line2)
     this.line1.setMap(null);
   this.line2 = new google.maps.Polyline({
     path: pts2,
     strokeColor: this.color,
     strokeOpacity: this.opacity,
     strokeWeight: this.weight
   });
   this.line2.setMap(this.map);
*/



  }


}

BDCCParallelLines.prototype.intersect = function(p0,p1,p2,p3)
{
// this function computes the intersection of the sent lines p0-p1 and p2-p3
// and returns the intersection point,

var a1,b1,c1, // constants of linear equations
    a2,b2,c2,
    det_inv,  // the inverse of the determinant of the coefficient matrix
    m1,m2;    // the slopes of each line

var x0 = p0.x;
var y0 = p0.y;
var x1 = p1.x;
var y1 = p1.y;
var x2 = p2.x;
var y2 = p2.y;
var x3 = p3.x;
var y3 = p3.y;

// compute slopes, note the cludge for infinity, however, this will
// be close enough

if ((x1-x0)!==0)
   m1 = (y1-y0)/(x1-x0);
else
   m1 = 1e+10;   // close enough to infinity

if ((x3-x2)!==0)
   m2 = (y3-y2)/(x3-x2);
else
   m2 = 1e+10;   // close enough to infinity

// compute constants

a1 = m1;
a2 = m2;

b1 = -1;
b2 = -1;

c1 = (y0-m1*x0);
c2 = (y2-m2*x2);

// compute the inverse of the determinate

det_inv = 1/(a1*b2 - a2*b1);

// use Kramers rule to compute xi and yi

var xi=((b1*c2 - b2*c1)*det_inv);
var yi=((a2*c1 - a1*c2)*det_inv);

return new google.maps.Point(Math.round(xi),Math.round(yi));

}
