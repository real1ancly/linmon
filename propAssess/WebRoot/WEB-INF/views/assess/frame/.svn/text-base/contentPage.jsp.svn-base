<!-- propAssess\WebRoot\WEB-INF\views\template\contentPage.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.ultrapower.assess.model.AssessTemplate"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${rc.contextPath}/style/blue/othercommon/css/other.css" rel="stylesheet" type="text/css" />
<title>无标题文档</title>
<script src="${rc.contextPath}/js/main.js"></script>
<script src="${rc.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/jsframework.js"></script>
<script language="javascript">
window.onresize = function() {
	setCenter(4,27);
}
window.onload = function() {
	setCenter(4,27);
	changeRow_color("tab");
}
function tosave(page){
  var kpiValue = document.getElementsByName('kpiValue');
  var kpiValues = "";
  var url = "assessProxy.do?method=save&recordsId=${recordsId}&kpiValues=";
  if(page=='add'){
   var propId = document.getElementsByName('propId');
   var propIds = '';
   for(var i=0;i<propId.length;i++){
       if(kpiValue[i].type=='text'&&kpiValue[i].value==''){
            alert('对不起，请输入指标值！');
             return;
       }
       if(kpiValue[i].type=='text'&&isNaN(kpiValue[i].value)){
            alert('对不起，请输入数字！');
             return;
       }
    if(i==0){
       kpiValues = kpiValue[i].value;
       propIds = propId[i].value;
    }else{
       kpiValues += ',' + kpiValue[i].value;
       propIds += ',' + propId[i].value;
    }
  }
   url += kpiValues+"&propIds="+propIds;
}else{
   var resultId = document.getElementsByName('resultId');
   var resultIds = '';
     for(var i=0;i<resultId.length;i++){
       if(kpiValue[i].type=='text'&&kpiValue[i].value==''){
           alert('对不起，请输入指标值！')
          return;
        }
        if(kpiValue[i].type=='text'&&isNaN(kpiValue[i].value)){
            alert('对不起，请输入数字！');
             return;
       }
       if(kpiValue[i].value<0){
            alert('对不起，指标值不能为负数！');
             return;
       }
        if(i==0){
	        resultIds = resultId[i].value;
	        kpiValues = kpiValue[i].value;
	    }else{
	        resultIds += ',' + resultId[i].value;
	        kpiValues += ',' + kpiValue[i].value;
	    }
     }
  url += kpiValues+"&resultIds="+resultIds;
}
sendRequest(url);
  
}

Import("System.Web.Forms.MzModalDialog");
var form;
		 
function select(){
	form =  MzModalDialog.open('assessProxy.do?method=toupload&recordsId=${recordsId}', 
	     {
   		  title:'请定义得分公式'
  		  ,buttonbar:false
 		  ,contentType:'page'
 		  ,width:'300px'
 		  ,height:'200px'
 		  ,position:'center middle'
 		  ,overflow:'hidden'
 		  ,buttonCancel:false
		 });
	 return form;
}
function sendRequest(url){
    var xmlhttp;
   if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
     xmlhttp=new XMLHttpRequest();
   }else{// code for IE6, IE5
     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
   }
        //调用open方法
        xmlhttp.open("get",url,true); 
        //发送  
        xmlhttp.send();             
        //设置服务器要回调的方法
        xmlhttp.onreadystatechange = function(){
			  if (xmlhttp.readyState==4 && xmlhttp.status==200){
				   var msg=xmlhttp.responseText;
				   if(msg=='success'){
				     document.location.href="assessProxy.do?method=view&recordsId=${recordsId}";
				   }else{
				     var msgs = msg.split(",");
					 if(msgs[0]=='wrongScroe'){
					   alert('保存失败！第'+msgs[1]+'行得分大于100，请检查指标值与公式是否有误！')
					 }
				   }
		      }
	   }; 
                     
    }

</script>
</head>
<body>
<div class="content">
<div class="content_title_bg">
<div class="content_title">当前位置：
<c:if test="${page=='add'}">
  填写考核记录表
</c:if>  
<c:if test="${page=='toedit'}">
  修改考核记录表
</c:if> 
<c:if test="${page=='edit'}">
  查看考核记录表
</c:if> 
</div>
</div>
<div style="height:100%;overflow:auto;width:90%;padding:0px;" id="center">
  <div class="contentList">
    <table cellpadding="0" cellspacing="0" border="0" width="99%" style="margin-left:5px;">
    <c:if test="${CanModifyResult}">
      <tr>
        <td colspan="17">
          <table width="100%"  border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #ccc; border-bottom:none; padding:5px 0 5px 0;" >
              	<td width="8"></td>
              	<td width="20"><img src="${rc.contextPath}/images/export.png" /></td>
                <td width="70"><a href="assessProxy.do?method=topage&recordsId=${recordsId}"  >导出Execl</a></td>
                <td width="20"><img src="${rc.contextPath}/images/import.png" /></td>
                <td width="70"><a href="#" onclick="select()" >导入Execl</a></td>
                <c:if test="${page=='toedit'}">
                <td width="20"><img src="${rc.contextPath}/images/save.png" /></td>
                <td>
                   <a href="#" onclick="tosave('${page}')">保存</a>
                </td>
                </c:if>
                <c:if test="${page!='toedit'}">
                <td width="20"><img src="${rc.contextPath}/images/script-edit.png" /></td>
                <td>
                   <a href="assessProxy.do?method=view&recordsId=${recordsId}&page=toedit">修改</a>
                </td>
                </c:if>
              </tr>
            </table>
        </td>
      </tr>
       </c:if>
      <tr>
        <td>
        <form name="form1" action="#" method="post">
	       <table width="100%" border="1" cellpadding="0" cellspacing="0" class="tableborder1" id="tab">
	         <tr><th align="center" style="font-weight:bold;text-align:center; font-size:15px;height:30px;background:url(${rc.contextPath}/style/blue/othercommon/images/thbg_title.png) repeat-x;" colspan="${colspan+5}">${assessTemplate.templateName}</th></tr>
	                <tr>
	                    <th>序号</th>
			            <th colspan="${colspan}">评价指标</th>
			            <th>指标值</th>
			            <th>单位</th>
			            <th>得分计算公式</th>
			            <th>得分</th>
			        </tr>
	            ${htmlToString}
	      <!--<tr>
            <th colspan="17" align="right" style="text-align:right; padding-right:10px; background:none; height:22px;">当前： 第1页  共10页 | <img src="${rc.contextPath}/style/blue/othercommon/images/navLeft.gif" width="14" height="16" alt="第一页"/><img src="${rc.contextPath}/style/blue/othercommon/images/left.gif" width="16" height="16" alt="上一页" /><img src="${rc.contextPath}/style/blue/othercommon/images/right.gif" width="16" height="16"  alt="下一页"/><img src="${rc.contextPath}/style/blue/othercommon/images/navRight.gif" width="14" height="16"  alt="最后一页" />  <input type="text" style="width:15px;" onblur="this.className='blur'" onfocus="this.className='focus'" class="blur"/><input type="button" class="button" value="GO" onmouseover="this.className='buttonhover'" onmouseout="this.className='button'"/>
              每页行数： <select name="" onblur="this.className='blur'" onfocus="this.className='focus'">
              <option value="20">20</option>
              <option value="40">40</option>
              <option value="60">60</option>
            </select>  </th>
          </tr>-->
        </table>
          </form>
            </td>
         </tr>
       </table>
  </div>
</div>
</div>
</body>
</html>