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
<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
<title>无标题文档</title>
<script src="${rc.contextPath}/js/main.js"></script>
<script src="${rc.contextPath}/js/common.js"></script>
<script language="javascript">
window.onresize = function() {
	setCenter(4,27);
}
window.onload = function() {
	setCenter(4,27);
	changeRow_color("tab");
	 <c:if test="${isF=='yes'}">
		reloadTree();
	</c:if>
	afterdelete();
}
function afterdelete(){
	 <c:if test="${using=='using'}">
		 alert("对不起，指标项还在使用中，不可删除！")
	</c:if>
}


</script>
</head>
<body>
<div class="content">
<div class="content_title_bg">
<div class="content_title">当前位置：模板管理</div>
</div>
<div style="height:100%;overflow:auto;width:90%;padding:0px;" id="center">
  <div class="contentList">
    <table cellpadding="0" cellspacing="0" border="0" width="99%" style="margin-left:5px;">
      <tr>
        <td colspan="17">
          <table width="100%"  border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #ccc; border-bottom:none; padding:5px 0 5px 0;" >
              	<td width="8"></td>
              	<td width="20"><img src="${rc.contextPath}/images/script-add.png" /></td>
                <td width="70"><a href="#" onclick="toAdd('assessProp.do?method=toAdd&templateId=${assessTemplate.id}&page=page','${assessTemplate.status}')">新增考核项</a></td>
              	<td width="20"><img src="${rc.contextPath}/images/script-delete.png" /></td>
                <td width="70"><a href="#" onclick="toDelete('assessProp.do?method=delete&isF=yes&templateId=${assessTemplate.id}&page=page','${assessTemplate.status}','${assessTemplate.id}')">删除考核项</a></td>
             	<td width="20"><img src="${rc.contextPath}/images/script-edit.png" /></td>
                <td><a href="#" onclick="toEdit('assessProp.do?method=toEdit&templateId=${assessTemplate.id}&page=page','${assessTemplate.status}')">修改考核项</a></td>
               <!--  <td align="right">
                    <input type="text" size="15" onblur="this.className='blur'" onfocus="this.className='focus'" class="blur"/>
                    <input type="button" value="搜索"  class="button"  onmouseover="this.className='buttonhover'" onmouseout="this.className='button'"/>
                </td> -->
                <td width="8"></td>
              </tr>
            </table>
        </td>
      </tr>
      <tr>
        <td>
        <form name="form1" action="#" method="post">
	       <table width="100%" border="1" cellpadding="0" cellspacing="0" class="tableborder1" id="tab">
	         <tr><th align="center" style="font-weight:bold;text-align:center; font-size:15px;height:30px;background:url(${rc.contextPath}/style/blue/othercommon/images/thbg_title.png) repeat-x;" colspan="${colspan+6}">${assessTemplate.templateName}</th></tr>
	                <tr><th><input type="checkbox" name="chkAll" id="chkAll" onclick="checkAll()"/></th> 
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