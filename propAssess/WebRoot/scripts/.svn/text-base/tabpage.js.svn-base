
var imgSet = "";
var setI = 1;
/*--------�Ӳ˵������л�Ч��js-----*/
function tablist(num,oEvent,imgSrc){
	if(window.event){
		oEvent = window.event;
	}
	var oTarget;
	if(oEvent.srcElement){
		oTarget = oEvent.srcElement;
	}else{
		oTarget = oEvent.target;
	}

	var liObjes = (oTarget.parentNode).getElementsByTagName("LI");
	for(i=0; i<liObjes.length; i++){
		if(i == num){
			liObjes[num].className = "mouse_over";
			document.getElementById("case_detail_"+num).style.display = "block";
		}else{
			liObjes[i].className = "";
			document.getElementById("case_detail_"+i).style.display = "none";
		}		
	}
	if(imgSrc != undefined){
		document.getElementById("moduleImg").src = "images/index/"+imgSrc;	
	}	
}

/*----------�����Ӳ˵�css-------*/
function navlink(num){
	document.getElementById("nav_children_"+num).style.display = "block";
	document.getElementById("nav_children_"+num).style.left = (num*116-1)+"px";
	show("nav_children_"+num);
}

/*-----------��ҳbanner�л�---------*/
function producttop(self){
	if((self.style.top).substring(0,(self.style.top).length-2) > 0){
		self.style.top = (self.style.top).substring(0,(self.style.top).length-2)-setI*0.8 + "px";
		setI++;
	}else{
		self.style.top = "0px";
		setI = 1;
	}
	
	imgSet = setTimeout(function(){producttop(self);},10);
}

function bannerChange(num){
	for(i=0; i<4; i++){
		if(i == num){
			if(imgSet != ""){
				clearTimeout(imgSet);
			}
			producttop(document.getElementById("product_img_"+i));
			document.getElementById("product_img_"+i).className = "product_img";
			document.getElementById("bannerBg_home").className = "bannerBg_home_"+i;
			fade("bannerBg_home");
		}else{
			
			document.getElementById("product_img_"+i).style.top = "18px";
			document.getElementById("product_img_"+i).className = "product_img_over";
			
		}
	}
}