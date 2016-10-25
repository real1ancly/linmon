<!-- propAssess\WebRoot\WEB-INF\views\left.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Menu</title>
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css"
			rel="stylesheet" type="text/css" />
		<script src="${rc.contextPath}/js/left.js"></script>
		<script src="${rc.contextPath}/js/changeframe.js"></script>
		<script src="${rc.contextPath}/scripts/dtree.js"></script>
	</head>
	<body>
	
		<table class="left" cellpadding="0" style="height: 100%;"
			cellspacing="0" border="0" align="left" id="showtree">
			<tr onClick="showSubmenu(0);">
				<td id="td0" onClick="changeColor(0);" class="tree_title_bg1"
					valign="top">
					<div class="showtree" id="hideleft" onClick="changeFrameleft()"
						title="隐藏左侧菜单"></div>
					模板下发
				</td>
			</tr>
			<tr class=submunuTbody id=submunuTbody>
				<td valign="top" style="margin: 0px; padding: 8px 2px 2px 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="20">
								<img
									src="${rc.contextPath}/style/blue/othercommon/images/page.gif" />
							</td>
							<td>
								<a href="assessTemp.do?method=send"
									target="contentFrame_checkCount">模板下发</a>
							</td>
						</tr>
						<tr>
							<td width="20">
								<img
									src="${rc.contextPath}/style/blue/othercommon/images/page.gif" />
							</td>
							<td>
								<a href="assessTemp.do?method=sendRecorder" target="contentFrame_checkCount">下发记录</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
		</td>
		</tr>
		</table>
		
		<table class="left" cellpadding="0" cellspacing="0" border="0"
			align="center" id="notree" style="display: none; width: 75%;">
			<tr>
				<td class="tree_title_bg2" valign="top">
					<div class="notree" id="openleft" onClick="changeFrameleft()"
						title="显示左侧菜单"></div>
					<textarea rows="" cols="" disabled="disabled" style="display: none"> </textarea>
					<!-- <img align="top" /> -->
				</td>
			</tr>
		</table>
		<!--多个OutLook菜单类型-->
	</body>
</html>