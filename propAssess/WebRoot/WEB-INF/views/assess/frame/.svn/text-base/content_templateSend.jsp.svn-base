<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
<title>无标题文档</title>
		<script src="${rc.contextPath}/js/main.js"></script>
		<script type="text/javascript" src="${rc.contextPath}/script/jquery-1.3.2.min.js"></script>
		<script language="javascript">
		window.onresize = function() {
			setCenter(4,27);
		}
		window.onload = function() {
			setCenter(4,27);
			changeRow_color("tab");
		}
	</script>
	<script language="javascript" >

function toSend(){
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
    alert("请选择模板项");
    return;
  }
  document.location.href="assessTemp.do?method=send&id="+id;
}
	function checkOne(cb){
	   var obj= document.getElementsByName('cbx');
	   for(var i=0;i<obj.length;i++){
	   if(obj[i]!=cb) obj[i].checked=false;
	   else obj[i].chedked=true;
	   }
	   }
	</script>
</head>
<body> 
<div class="content">
<div class="content_title_bg">
<div class="content_title">当前位置：模板下发</div>
</div>
<div style="height:100%;overflow:auto;width:90%;padding:0px;" id="center">
  <div class="contentList">
    <table cellpadding="0" cellspacing="0" border="0" width="99%" style="margin-left:5px;">
      <tr>
        <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #ccc; border-bottom:none; padding:5px 0 5px 0;">
              <tr>
              	<td width="8"></td>
              	<td width="20"><img src="${rc.contextPath}/style/blue/othercommon/images/send.png" /></td>
                <td width="35"><a href="#" target="_self"  onclick ="toSend();">下发</a></td>
              	<td width="20"><img src="${rc.contextPath}/style/blue/othercommon/images/sendRecorder.png" /></td>
                <td width="70"><a href="assessTemp.do?method=sendRecorder" target="_self">下发记录</a></td>
                <td align="right"></td>
                <td width="8"></td>
              </tr>
            </table>
        </td>
      </tr>
      <tr>
        <td>
        <form name="form1" action="#" method="post">
	       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableborder"  id="tab">
            <tr>
            <th><!--  input type="checkbox" name="chkAll" id="checkbox" onclick="checkAll()"  /--></th>
            <th>模板名称</th>
            <th>最后下发人</th>
            <th>最后下发时间</th>
            <th>id</th>
            </tr>
       
         <c:forEach items="${templist}" var="templist">
		<tr>
            <td><input type="checkbox" name="cbx" id="checkbox2" onclick ="checkOne(this)" value="${templist[1]}"/></td>
            <td>${templist[0]}</td>          
            <td>${templist[3]}</td>
            <td>${templist[2]}</td>
	        <td>${templist[1]}</td>
          </tr>
	   </c:forEach>
          <tr>
			<th colspan="8" align="right"
				style="text-align: right; padding-right: 10px; background: none; height: 22px;">
				${pageToString}
			</th>
		</tr>
          
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
