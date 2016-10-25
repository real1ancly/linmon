// JavaScript Document


function hospitalMaster(thisObj,Num){
if(thisObj.className == "active")return;
var hospitalMasterObj = thisObj.parentNode.id;
var hospitalMasterList = document.getElementById(hospitalMasterObj).getElementsByTagName("div");
for(i=0; i <hospitalMasterList.length; i++)
{
  if (i == Num)
  {
   thisObj.className = "active"; 
   	  hospitalMasterList[i].className = "tab01"; 
      document.getElementById(hospitalMasterObj+"_Content"+i).style.display = "block";
  }else{
   hospitalMasterList[i].className = "tab02"; 
   document.getElementById(hospitalMasterObj+"_Content"+i).style.display = "none";
  }
} 
}


function tabChangeHos(thisObj,Num){
if(thisObj.className == "active")return;
var tabChangeObj = thisObj.parentNode.id;
var tabChangeList = document.getElementById(tabChangeObj).getElementsByTagName("div");
for(i=0; i <tabChangeList.length; i++)
{
	
  if (i == Num)
  {
  
   thisObj.className = "active"; 
   	  tabChangeList[i].className = "tab01"; 
      document.getElementById(tabChangeObj+"_Content"+i).style.display = "block";
  }else{
    if(tabChangeList[i].className == 'noTab') continue;
   tabChangeList[i].className = "tab02"; 
   document.getElementById(tabChangeObj+"_Content"+i).style.display = "none";
  }
} 
}




function tabChange(thisObj,Num){
if(thisObj.className == "active")return;
var tabChangeObj = thisObj.parentNode.id;
var tabChangeList = document.getElementById(tabChangeObj).getElementsByTagName("div");
for(i=0; i <tabChangeList.length; i++)
{
	
  if (i == Num)
  {
  
   thisObj.className = "active"; 
   	  tabChangeList[i].className = "tab02"; 
      document.getElementById(tabChangeObj+"_Content"+i).style.display = "block";
  }else{
    if(tabChangeList[i].className == 'noTab') continue;
   tabChangeList[i].className = "tab01"; 
   document.getElementById(tabChangeObj+"_Content"+i).style.display = "none";
  }
} 
}




function master_video(thisObj,Num){
if(thisObj.className == "active")return;
var master_videoObj = thisObj.parentNode.id;
var master_videoList = document.getElementById(master_videoObj).getElementsByTagName("div");
for(i=0; i <master_videoList.length; i++)
{
  if (i == Num)
  {
   thisObj.className = "active"; 
   	  master_videoList[i].className = "tab01"; 
      document.getElementById(master_videoObj+"_Content"+i).style.display = "block";
  }else{
   master_videoList[i].className = "tab02"; 
   document.getElementById(master_videoObj+"_Content"+i).style.display = "none";
  }
} 
}







//��̨����

function helpManage(thisObj,Num){
if(thisObj.className == "active")return;
var helpManageObj = thisObj.parentNode.id;
var helpManageList = document.getElementById(helpManageObj).getElementsByTagName("div");
for(i=0; i <helpManageList.length; i++)
{
  if (i == Num)
  {
   thisObj.className = "active"; 
   	   helpManageList[i].className = "act"; 
       document.getElementById(helpManageObj+"_Content"+i).style.display = "block";
  }else{
	   helpManageList[i].className = "wait"; 
	   document.getElementById(helpManageObj+"_Content"+i).style.display = "none";
  }
} 
}