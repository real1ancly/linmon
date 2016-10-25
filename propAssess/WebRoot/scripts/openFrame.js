// JavaScript Document
var windowclosed = "";
var prox;
var proy;
var proxc;
var proyc;
var isIe=(document.all)?true:false;
function setSelectState(state){
	var objl=document.getElementsByTagName('select');
	for(var i=0;i<objl.length;i++){
		objl[i].style.visibility=state;
	}
}
function mousePosition(ev){
	if(ev.pageX || ev.pageY){
		return {x:ev.pageX, y:ev.pageY};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,y:ev.clientY + document.body.scrollTop - document.body.clientTop
	};
}
//关闭窗口
function closedd(){
	for(j=0; j<7; j++){
		if(document.getElementById("nav_children_"+j) != null){
			document.getElementById("nav_children_"+j).style.display = "none";
		}
	}
	if(isIe){
		setSelectState('');
	}
	clearTimeout(windowclosed);
}
function closeWindow(){
	windowclosed = setTimeout(closedd,2000);
}
function show(id){/*--打开--*/
	if(windowclosed != ""){
		clearTimeout(windowclosed);
	}
	var bWidth=parseInt(document.getElementById(id).getElementsByTagName("TABLE")[0].offsetWidth);
	var bHeight=parseInt(document.getElementById(id).getElementsByTagName("TABLE")[0].offsetHeight);
	
	for(j=0; j<7; j++){
		if(document.getElementById("nav_children_"+j) != null){
			document.getElementById("nav_children_"+j).style.display = "none";
		}
	}
	
	//document.getElementById(id).getElementsByTagName("TABLE")[0].style.display = "none";
	//var bHeight=parseInt(document.documentElement.scrollHeight)<592?592:parseInt(document.documentElement.scrollHeight);
	if(isIe){
		setSelectState('hidden');
	}
	
	clearInterval(prox);
	clearInterval(proy);
	clearInterval(proxc);
	clearInterval(proyc);
	var o = document.getElementById(id);
	o.style.display = "block";	
	o.style.zIndex = "100000";
	o.style.width = bWidth;
	o.style.height = "1px";
	o.style.overflow = "hidden";
	proy = setInterval(function(){openy(o,id,bHeight)},10);
} 
/*
function openx(o,x,id,bHeight){//--打开x--
	var cx = parseInt(o.style.width);
	if(cx < x){
		o.style.width = (cx + Math.ceil((x-cx)/5)) +"px";
	}
	else{
		clearInterval(prox);
		proy = setInterval(function(){openy(o,bHeight,id)},10);
	}
} 
*/
function openy(o,id,y){/*--打开y--*/    
	var cy = parseInt(o.style.height);
	
	if(cy < y){
		o.style.height = (cy + Math.ceil((y-cy)/5)) +"px";
	}
	else{
		
		clearInterval(proy); 
		document.getElementById(id).getElementsByTagName("TABLE")[0].style.display = "block";
	}
}    
function closeed(id){/*--关闭--*/
	closeWindow();
	clearInterval(prox);
	clearInterval(proy);
	clearInterval(proxc);
	clearInterval(proyc);        
	var o = document.getElementById(id);
	if(o.style.display == "block"){
		proyc = setInterval(function(){closey(o)},10);            
	}        
}    
function closey(o){/*--打开y--*/    
	var cy = parseInt(o.style.height);
	if(cy > 0){
		o.style.height = (cy - Math.ceil(cy/5)) +"px";
	}
	else{
		clearInterval(proyc);                
		proxc = setInterval(function(){closex(o)},10);
	}
}    
function closex(o){/*--打开x--*/
	var cx = parseInt(o.style.width);
	if(cx > 0){
		o.style.width = (cx - Math.ceil(cx/5)) +"px";
	}
	else{
		clearInterval(proxc);
		o.style.display = "none";
	}
}    


/*-------------------------鼠标拖动---------------------    
var od = document.getElementById("fd");    
var dx,dy,mx,my,mouseD;
var odrag;
var isIE = document.all ? true : false;
document.onmousedown = function(e){
var e = e ? e : event;
if(e.button == (document.all ? 1 : 0))
{
mouseD = true;            
}
}
document.onmouseup = function(){
mouseD = false;
odrag = "";
if(isIE)
{
od.releaseCapture();
od.filters.alpha.opacity = 100;
}
else
{
window.releaseEvents(od.MOUSEMOVE);
od.style.opacity = 1;
}        
}


//function readyMove(e){    
od.onmousedown = function(e){
	odrag = this;
	var e = e ? e : event;
	if(e.button == (document.all ? 1 : 0))
	{
	mx = e.clientX;
	my = e.clientY;
	od.style.left = od.offsetLeft + "px";
	od.style.top = od.offsetTop + "px";
	if(isIE)
	{
	od.setCapture();                
	od.filters.alpha.opacity = 50;
	}
	else
	{
	window.captureEvents(Event.MOUSEMOVE);
	od.style.opacity = 0.5;
	}
	
	//alert(mx);
	//alert(my);
	
	} 
}
document.onmousemove = function(e){
	var e = e ? e : event;
	
	//alert(mrx);
	//alert(e.button);        
	if(mouseD==true && odrag){        
		var mrx = e.clientX - mx;
		var mry = e.clientY - my;    
		od.style.left = parseInt(od.style.left) +mrx + "px";
		od.style.top = parseInt(od.style.top) + mry + "px";            
		mx = e.clientX;
		my = e.clientY;	
	}
}
*/
function showBackground(obj,endInt){
	obj.filters.alpha.opacity+=1;
	if(obj.filters.alpha.opacity<endInt){
		setTimeout(function(){showBackground(obj,endInt)},8);
	}
}