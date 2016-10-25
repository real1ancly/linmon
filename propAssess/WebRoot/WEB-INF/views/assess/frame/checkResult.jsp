<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@page import="com.ultrapower.assess.util.PageUtils"%>
<%@page import="com.bidlink.core.commons.support.page.Page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet"
			type="text/css" />
		<title>无标题文档</title>
		<script src="${rc.contextPath}/js/common.js"></script>
		<script src="${rc.contextPath}/js/main.js"></script>
		<script language="javascript">
			window.onresize = function() {
				setCenter(4,27);
			}
			window.onload = function() {
				setCenter(4,27);
				changeRow_color("tab");
			}
		</script>
		
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title">
					当前位置：考核统计 >> 考核结果排名
				</div>
			</div>
			<div style="height: 100%; overflow: auto; width: 90%; padding: 0px;"
				id="center">
				<div class="contentList">
					<table cellpadding="0" cellspacing="0" border="0" width="99%"
						style="margin-left: 5px;">
						<tr>
							<td>
							<form name="form1" action="assessStat.do?method=checkCount" method="post">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="padding: 0 0 5px 0;">
									<tr>
										<td align="left">&nbsp;选择考核 &nbsp;
											<select name="scheduId" onchange= "this.form.submit(); " size=1 >
											<option value="">---请选择考核名称---</option>
											<!--  <option value="全部">全部</option>-->
											<c:forEach items="${slist}" var="slist">
											<option value="${slist.id}">
											${slist.name}&nbsp;&nbsp;(<fmt:formatDate value="${slist.begintime}" type="both" pattern="yyyy.MM.dd" />
											--&nbsp;<fmt:formatDate value="${slist.endtime}" type="both" pattern="yyyy.MM.dd" />)
											</option>
											</c:forEach>
											</select>
											<script>
		document.getElementById("scheduId").value="${selectValue}";
		</script>
										</td>
									</tr>
								</table>
							</form>
							</td>
						</tr>
						<tr>
							<td>
								<form name="form1" action="#" method="post">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="tableborder" id="tab">
										<tr>
											<th>
												排名
											</th>
											<th>
												考核对象
											</th>
											<th>
												考核模板名称
											</th>
											<th>
												得分
											</th>
										</tr>
										<c:forEach items="${rlist}" var="r" varStatus="status">
										<tr>
											<td>
												<c:out   value= "${status.index+1+(pageNo-1)*pageNum}"/>
											</td>
											<td>
												<a href="assessProxy.do?method=view&recordsId=${r.id}&page=result">${r.assessObject.objectName}</a>
											</td>
											<td>
												<a href="assessProxy.do?method=view&recordsId=${r.id}&page=result">${r.assessTemplate.templateName}</a>
											</td>
											<td>
												<a href="assessProxy.do?method=view&recordsId=${r.id}&page=result">${r.assessTotal}</a>
											</td>
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