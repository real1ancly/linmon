<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>我的待办</title>
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
		<script src="${rc.contextPath}/js/main.js"></script>
		<script src="${rc.contextPath}/js/common.js"></script>
		<script src="${rc.contextPath}/scripts/jquery-1.4.2.min.js"></script>
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
	<body style="margin: 0; padding: 0">
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title">
					当前位置：我的待办
				</div>
			</div>
			<div style="height: 100%; overflow: auto; width: 100%; padding: 0px;" id="center" >
				<div class="contentList" style="margin-top: 7px;">
					<table cellpadding="0" cellspacing="0" border="0"width="99%" align="center">
						<tr>
							<td>
								<form name="form1" action="#" method="post">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="tableborder" id="tab">
										<tr>
										    <th>
												考核名称
											</th>
											<th>
												模板名称
											</th>
											<th>
												考核周期
											</th>
											<th>
												开始时间
											</th>
											<th>
												结束时间
											</th>
											<th>
												考核对象名称
											</th>
											<th>
												考核对象类型
											</th>
											
											
											<!--<th>
												导出模板
											</th>-->
										</tr>
										<c:forEach items="${recordsList}" var="records">
											<tr>
											    <td>
												  ${records.assessSchedu.name} 
												</td>
												<td>
												   <a href="assessProxy.do?method=view&recordsId=${records.id}" >${records.assessTemplate.templateName}</a>
												</td>
												<td>
												  <c:if test="${records.period==1}">日</c:if>
												  <c:if test="${records.period==2}">周</c:if>
												  <c:if test="${records.period==3}">月</c:if>
												  <c:if test="${records.period==4}">季</c:if>
												  <c:if test="${records.period==5}">年</c:if>
												  <c:if test="${records.period==6}">自定义</c:if>
												</td>
												<td>
												    <fmt:formatDate value='${records.beginTime}' pattern='yyyy-MM-dd' />
												</td>
												<td>
												    <fmt:formatDate value='${records.endTime}' pattern='yyyy-MM-dd' />
												</td>
												<td>
												    ${records.assessObject.objectName}
												</td>
												<td>
												  <c:if test="${records.assessObject.objectType==1}">组织机构</c:if>
												  <c:if test="${records.assessObject.objectType==2}">人员</c:if>
												</td>
												
												<!-- <td>
													<a href="assessProxy.do?method=topage" target="_self">导出填写</a> 
												</td>-->
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
