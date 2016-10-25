<!-- propAssess\WebRoot\WEB-INF\views\template\contentItem.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
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
          <table width="100%" border="0" cellspacing="0" cellpadding="0" style=" border:1px solid #ccc; border-bottom:none; padding:5px 0 5px 0;">
              <tr>
              	<td width="8"></td>
              	<td width="20"><img src="${rc.contextPath}/images/script-add.png" /></td>
                <td width="70"><a href="#" onclick="toAdd('assessProp.do?method=toAdd&templateId=${assessTemplate.id}&parentId=${currentProp.id}&page=item','${assessTemplate.status}')">新增考核项</a></td>
              	<td width="20"><img src="${rc.contextPath}/images/script-delete.png" /></td>
                <td width="70"><a href="#" onclick="toDelete('assessProp.do?method=delete&isF=yes&templateId=${assessTemplate.id}&parentId=${currentProp.id}&page=item','${assessTemplate.status}')">删除考核项</a></td>
             	<td width="20"><img src="${rc.contextPath}/images/script-edit.png" /></td>
                <td><a href="#" onclick="toEdit('assessProp.do?method=toEdit&parentId=${currentProp.id}&page=item','${assessTemplate.status}')">修改考核项</a></td>
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
	       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableborder"  id="tab">
	       <input type="hidden" name="parentId" id="parentId" value="${currentProp.id}"/> 
             <tr>
	            <th><input type="checkbox" name="chkAll" id="chkAll" onclick="checkAll()"  /></th>
	            <th>序号</th>
	            <th>评价指标</th>	           
	            <th>单位</th>
	            <th>得分计算公式</th>
	            <th>指标类型</th>
	            <th>指标编码</th>
	            <th>最后修改人</th>
	            <th>最后修改时间</th>
	        </tr>
          <c:if test="${assessProps!=null}">
            <c:forEach items="${assessProps}" var="assessProp">
              <tr>
              <input type="hidden" name="status" id="status" value="${assessProp.assessTemplate.status}"/>
                <td><input type="checkbox" name="cbx" id="cbx" value="${assessProp.id}"/></td>
                <td>${assessProp.sort}</td>
	            <td>
	               ${assessProp.propName}
	            </td>
	           <!--  <td></td> -->
	            <td>${assessProp.propUnit}</td>
	            <td>${assessProp.scoreExpression}</td>	 
	            <td>
	              <c:if test="${assessProp.propType==1}">手工录入</c:if>
	               <c:if test="${assessProp.propType==2}">自动获取</c:if>
				</td>           
	            <td>${assessProp.kpiId}</td>
	            <td>${assessProp.updateAccount}</td>
	            <td><fmt:formatDate value='${assessProp.updateTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
	          </tr>
            </c:forEach>
         </c:if>
          </tr>
	      <tr>
            <th colspan="9" align="right" style="text-align:right; padding-right:10px; background:none; height:22px;">
            ${pageToString}
            <!--  每页行数： <select name="" onblur="this.className='blur'" onfocus="this.className='focus'">
              <option value="20">20</option>
              <option value="40">40</option>
              <option value="60">60</option>
            </select> --> </th>
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