<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.ultrapower.assess.util.PageUtils"%>
<%@page import="com.bidlink.core.commons.support.page.Page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css"
			rel="stylesheet" type="text/css" />
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
					当前位置：历史考核查询结果
				</div>
			</div>
			<div style="height: 100%; overflow: auto; width: 90%; padding: 0px;"
				id="center">
				<div class="contentList">
					<table cellpadding="0" cellspacing="0" border="0" width="99%"
						style="margin-left: 5px;">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="border: 1px solid #ccc; border-bottom: none; padding: 5px 0 5px 0;">
									<tr>
										<td width="60">
											&nbsp;
											<img src="${rc.contextPath}/images/6.png" />
											<a href="checkHistory.do" target="_self">返回查询</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<form name="form1" action="#" method="post">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="tableborder" id="tab">
										<tr>
											<th>
												考核对象名称
											</th>
											<th>
												模板名称
											</th>
											<th>
												创建时间
											</th>
											<th>
												开始时间
											</th>
											<th>
												结束时间
											</th>
											<th>
												周期
											</th>
										</tr>
										<c:forEach items="${listObj}" var="n">
											<tr>
												<td>
													${n.assessObject.objectName }
												</td>
												<td>
													${n.assessTemplate.templateName }
												</td>
												<td>
													<fmt:formatDate value="${n.createTime }" type="both"
													pattern="yyyy.MM.dd HH:mm:ss" />
											
												</td>
												<td>
													<fmt:formatDate value="${n.beginTime }" type="both"
													pattern="yyyy.MM.dd HH:mm:ss" />
												</td>
												<td>
													<fmt:formatDate value="${n.endTime }" type="both"
													pattern="yyyy.MM.dd HH:mm:ss" />
												</td>
												<td>
													<script type="text/javascript">
					
					var a = ${n.period }
					if(a == 1){
					document.write("日");
					}
					if(a == 2){
					document.write("周");
					}
					if(a == 3){
					document.write("月");
					}
					if(a == 4){
					document.write("季");
					}
					if(a == 5){
					document.write("年");
					}
					if(a == 6){
					document.write("自定义");
					}
				</script>
												</td>
											</tr>
										</c:forEach>
										<tr>
											<th colspan="8" align="right"
												style="text-align: right; padding-right: 10px; background: none; height: 22px;">
												${pageToString }
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