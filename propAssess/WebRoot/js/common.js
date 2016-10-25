// JavaScript Document
function toAdd(url,status){
  if(status!=1){
     alert("此模板已发布，不可新增!")
     return;
  }
  document.location.href=url;
}
function toDelete(url,status){
  var cbx = document.getElementsByName('cbx');
  var chkIds = "";
   var ids = "";
  for(var i=0;i<cbx.length;i++){
     if(cbx[i].checked==true){
       if(chkIds.length==0){
         chkIds = cbx[i].value; 
       }else{
         chkIds += ','+cbx[i].value; 
       }
        
     }
  }
  if(chkIds.length==0){
    alert("请选择删除项");
    return;
  }else if(!confirm("确定删除选择项")){
  	return;
  }
  if(status!=1){
     var urls = url.split('delete');
    document.location.href=urls[0]+"beforeDelete"+urls[1]+"&chkIds="+chkIds;
	return;
  }
  document.location.href=url+"&chkIds="+chkIds;
}


function reloadTree(){
	window.parent.leftFrame.location.reload();
}

function toEdit(url,status){
  var cbx = document.getElementsByName('cbx');
  var id = "";
  for(var i=0;i<cbx.length;i++){
     if(cbx[i].checked==true){
       if(id.length==0){
         id = cbx[i].value; 
       }else{
         alert("选项过多，请重新选择");
         return;
       }
        
     }
  }
  if(id.length==0){
    alert("请选择修改项");
    return;
  }
  if(status!=1){
    alert("此模板已发布，不可修改")
     return;
  }
  document.location.href=url+"&id="+id;
}


function changePage(pageno,sourceURL){
 document.location.href=sourceURL+"&pageno="+pageno;
}
function changePage2(pageno,totalpage,sourceURL){
  if(pageno>totalpage||pageno<1){
    if(totalpage<pageno){
       alert("已是最后一页")
    }else{
        alert("已是第一页")
    }
    return;
  }else{
    document.location.href=sourceURL+"&pageno="+pageno;
  }
 
}
function changePage3(totalpage,sourceURL){
  var changepage = document.getElementsByName("changepage");
  if(totalpage<changepage[0].value||changepage[0].value<1){
  alert("输入页数不对，请重新输入")
    return;
  }else{
    document.location.href=sourceURL+"&pageno="+changepage[0].value;
  }
}

  