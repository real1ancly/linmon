<!--propAssess\WebRoot\WEB-INF\views\template\addTemplate.jsp  -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
		<title>新增模板</title>
		<script src="${rc.contextPath}/js/main.js"></script>
		<script language="javascript">
			window.onresize = function() {
				setCenter(4,27);
			}
			window.onload = function() {
				setCenter(4,27);
			}
			function beforeAddOrEdit(){
			   var name = document.getElementById("templateName");
			   if(name==null||name.value==""){
			      alert("请输入模板名称");
			      return;
			   }
			   document.frmForm.submit();
			}
		</script>
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<c:if test="${template == null}">
					<div class="content_title">
						当前位置：模板管理 &gt;&gt; 新增模板
					</div>
				</c:if>
				<c:if test="${template != null}">
					<div class="content_title">
						当前位置：模板管理 &gt;&gt; 修改模板
					</div>
				</c:if>
			</div>
			<div style="height: 100%; overflow: auto; width: 90%; padding: 0px;"
				id="center">
				<form name="frmForm" id="frmForm" method="post"
					action="template.do?method=save&isF=yes">
					<input type="hidden" name="id" value="${template.id}" />
					<table class="tableborder" border="0" cellpadding="5"
						cellspacing="0" width="98%" id="tab"
						style="margin: 10px 10px 0 10px;">
						<tr>
							<td align="right">
								模板名称:
								<font color=#FF0000>*</font>
							</td>
							<td>
								<input type="text" name="templateName" id="templateName"
									value="${template.templateName}" maxLength="100"
									onFocus="this.className='focus'" onBlur="this.className='blur'"
									class="blur">
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								模板说明:&nbsp&nbsp
							</td>
							<td colspan="3">
								<textarea name="remarks" maxLength="500" cols="50" rows="5">${template.remarks}</textarea>
							</td>
						</tr>
					</table>
					<table width="99%" class="button_bar">
						<tr>
							<td align="center">
								<input type="button" value="提交" class="button"
									onclick="beforeAddOrEdit();"
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
								<input type="reset" value="重置" class="button"
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
								<input type="button" value="返回" class="button"
									onclick=window.history.go(-1)
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
