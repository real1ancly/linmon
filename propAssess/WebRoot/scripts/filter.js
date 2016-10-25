var timer = null;
var ele  = null;
var elecount=0;
var count=0;
function $(id)
{
	return document.getElementById(id);
}
function fade(a){
	ele = $(a);
	if(!ele){return;}
	if(document.all)
	{
		ele.filters.alpha.opacity = 0;
	}else{
		ele.style.opacity = 0;
		count=0;
	}
	//ele.style.zIndex = 1;
	fade_opacity(a);
}


function fade_opacity(a){
	ele = $(a);
	if(!ele){return;}
	var ifstop = false;
	if(document.all){
		if(ele.filters.alpha.opacity < 50){
			ele.filters.alpha.opacity += 5;
		}else{
			ifstop=true;
		}
	}else{
		if(ele.style.opacity <1){
			var x=ele.style.MozOpacity;
			count++;
			//ele.style.opacity =ele.style.opacity +0.1;
			ele.style.MozOpacity=count*0.4;
			//alert(ele.style.opacity);
			//xx.value=ele.style.MozOpacity;
		}else{
			ifstop=true;
		}
	}
	if(ifstop==true){
		window.clearTimeout(timer);	
	}else{
		timer = window.setTimeout("fade_opacity('"+a+"')", 100);
	}
}