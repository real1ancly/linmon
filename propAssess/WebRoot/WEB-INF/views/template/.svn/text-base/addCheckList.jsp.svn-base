<!--propAssess\WebRoot\WEB-INF\views\template\addCheckList.jsp  -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新增考核页</title>
		<link
			href="${rc.contextPath}/style/blue/othercommon/css/other.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
.buttonhover {
	background: url(../images/thbg2.png) repeat-x left top;
	border: solid 1px #a6a6a6;
	padding: 3px 3px;
	height: 22px;
	margin-left: 4px;
	cursor: pointer;
	margin-top: 2px;
	font: "宋体", "幼圆";
	font-size: 12px;
}

.button {
	background: url(images/thbg.png) repeat-x left top;
	border: solid 1px #a6a6a6;
	padding: 3px 3px;
	height: 22px;
	margin-left: 4px;
	cursor: pointer;
	margin-top: 2px;
	font: "宋体", "幼圆";
	font-size: 12px;
}
</style>
		<script src="${rc.contextPath}/js/main.js"></script>
		<script type="text/javascript"
			src="${rc.contextPath}/scripts/jsframework.js"></script>
		<script language="javascript">
Import("System.Web.Forms.MzModalDialog");
		 var form;
window.onresize = function() {
	setCenter(4,27);
}
window.onload = function() {
	setCenter(4,27);
}
function setOption(){
    var propType = document.getElementById("propType");
     propType.setAttribute("value","<c:out value='${assessProp.propType}'></c:out>");
    
}
function beforeAddOrEdit(){
   var kpiId = document.getElementById("kpiId");
   var propName = document.getElementById("propName");
   var sort = document.getElementById("sort");
   var propType = document.getElementById("propType");
   if(propType.value==2){
     if(kpiId.value==null||kpiId.value==""){
      alert("请输指标编码");
      return;
     }
   }
   if(propName.value==null||propName.value==""){
      alert("请输入指标名称");
      return;
   }
   if(sort.value==null||sort.value==""){
        
   }else{
     var re =  /^[1-9]+[0-9]*]*$/;
	    if(!re.test(sort.value)){
	      alert("序号应为正整数，请重新输入");
	      sort.value='';
	      return;
        }
   }
   document.frmForm.submit();
}
 function expressionPane(){
    var scoreExpression =document.getElementById("scoreExpression");
    var value= scoreExpression.value;
    var values = value.split('+');
    if(values.length>1){
      value = values[0]
      for(var i=1;i<values.length;i++){
        value+=','+values[i];
      }
    }
    values = value.split('%');
    if(values.length>1){
      value = values[0]
      for(var i=1;i<values.length;i++){
        value+=';'+values[i];
      }
    }
	form =  MzModalDialog.open('assessProp.do?method=toExpression&expression='+value, 
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

function submitForm(eValue){
    var scoreExpression =document.getElementById("scoreExpression");
	scoreExpression.value=eValue;
	return true;
}
</script>
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<c:if test="${assessProp==null}">
					<div class="content_title">
						当前位置：模板管理 >> 新增考核项
					</div>
				</c:if>
				<c:if test="${assessProp != null}">
					<div class="content_title">
						当前位置：模板管理 >> 修改考核项
					</div>
				</c:if>
			</div>
			<form name="frmForm" id="frmForm" method="post"
				action="assessProp.do?method=save&templateId=${assessTemplate.id}&isF=yes&page=${page}">
				<input type="hidden" name="id" value="${assessProp.id}" />
				<div style="height: 100%; overflow: auto; width: 90%; padding: 0px;"
					id="center">
					<table class="tableborder" border="0" cellpadding="5"
						cellspacing="0" width="98%" id="tab"
						style="margin: 10px 10px 0 10px;">
						<tr>
							<td width="30%" align="right">
								模板：
							</td>
							<td>
								${assessTemplate.templateName}
							</td>
						</tr>
						<tr>
							<td width="30%" align="right">
								父节点：
							</td>
							<td>
								<c:if test="${parentProp==null}">
									<input type="hidden" name="parentId" value="" /> 
					    	       顶级指标项节点
					    	    </c:if>
								<c:if test="${parentProp!=null}">
									<input type="hidden" name="parentId" value="${parentProp.id}" /> 
					    	       ${parentProp.propName}
					    	    </c:if>
							</td>
						</tr>
						<tr>
							<td align="right">
								序号：
							</td>
							<td>
								<input type="text" style="height:22px" name="sort" id="sort"
									value="${assessProp.sort}" maxlength="5" class="blur"
									onFocus="this.className='focus'" onBlur="this.className='blur'">
							</td>
						</tr>
						<tr>
							<td align="right">
								指标名称：
							</td>
							<td>
								<input type="text" style="height:22px" name="propName" id="propName"
									value="${assessProp.propName}" maxlength="200"
									onFocus="this.className='focus'" onBlur="this.className='blur'"
									class="blur">
							</td>
						</tr>
						<tr>
							<td align="right">
								指标类型：
							</td>
							<td>
							   <select name="propType" id="propType">
							     <option selected="selected" value="1">手工录入</option>
							     <option value="2">自动获取</option>
							   </select>
							</td>
						</tr>
						<tr>
							<td align="right">
								指标单位：
							</td>
							<td>
								<input type="text" style="height:22px" name="propUnit" id="propUnit"
									value="${assessProp.propUnit}" maxlength="20"
									onFocus="this.className='focus'" onBlur="this.className='blur'"
									class="blur">
							</td>
						</tr>
						<tr>
							<td align="right">
								得分计算公式：
							</td>

							<td>
								<c:if test="${assessProp==null}">
									<input type="text" style="height:22px" name="scoreExpression" id="scoreExpression"
										value="$var" class="blur" onFocus="this.className='focus'"
										onBlur="this.className='blur'" readonly="readonly"
										onclick="expressionPane()">
								</c:if>
								<c:if test="${assessProp!=null}">
									<input type="text" style="height:22px" name="scoreExpression" id="scoreExpression"
										value="${assessProp.scoreExpression}" class="blur"
										onFocus="this.className='focus'"
										onBlur="this.className='blur'" readonly="readonly"
										onclick="expressionPane()">
								</c:if>($var为指标值)
							</td>
						</tr>
				<!--  		<tr>
							<td align="right">
								周期：
							</td>
							<td>
								<input type="radio" name="period" id="period1" checked="checked"
									value="1">
								周
								<input type="radio" name="period" id="period2" value="2">
								月
							</td>
						</tr>-->
						<tr>
							<td align="right">
								指标编码：
							</td>
							<td>
								<input type="text" style="height:22px" name="kpiId" id="kpiId"
									value="${assessProp.kpiId}" maxlength="100"
									onFocus="this.className='focus'" onBlur="this.className='blur'"
									class="blur">
							</td>
						</tr>
						<c:if test="${assessProp != null}">
							<tr>
							  <td align="right">最后修改人</td>
							  <td>${updateUserName}</td>
							</tr>
							<tr>
							  <td align="right">最后修改时间</td>
							  <td><fmt:formatDate value='${assessProp.updateTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
							</tr>
						</c:if>
						<tr>
							<td align="right">
								指标描述：
							</td>
							<td>
								<textarea name="remarks" id="remarks" maxlength="2000" cols="50"
									rows="5">${assessProp.remarks}</textarea>
							</td>
						</tr>
					</table>
					<table width="99%" class="button_bar">
						<tr>
							<td align="center">
								<input type="button" value="提交" class="button"
									onclick="beforeAddOrEdit()"
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
								<input type="reset" value="重置" class="button"
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
								<input type="button" value="返回" class="button"
									onclick="window.history.go(-1)"
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
<script language="javascript">
<c:if test="${assessProp!=null}">
  setOption();
</c:if>

</script>
