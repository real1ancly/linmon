<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.ultrapower.assess.util.PageUtils"%>
<%@page import="com.bidlink.core.commons.support.page.Page"%>
<%@page import="com.ultrapower.accredit.util.GetUserUtil" %>
<%@page import="com.ultrapower.accredit.common.value.Privilege" %>
<%@page import="com.ultrapower.accredit.rmiclient.RmiClientApplication" %>
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
		<script src="${rc.contextPath}/js/main.js"></script>
		<script src="${rc.contextPath}/js/common.js"></script>
		<script src="${rc.contextPath}/scripts/date/WdatePicker.js"></script>
		<%
         String message=(String)request.getAttribute("message");
         if(message!=null&&!"".equals(message)){
        	
        %>
		 <script  language="javascript">
		 
		 alert("<%=message%>");
		
         </script>
		<%
			}
		%>
		<script language="javascript">
			window.onresize = function() {
				setCenter(4,27);
			}
			window.onload = function() {
				setCenter(4,27);
				changeRow_color("tab");
			}
		</script>
		<script>
		function toDelete(){
				  var cbx = document.getElementsByName('cbx');
				  
				  var chkIds = "";
				  var ids = "";
				  for(var i=0;i<cbx.length;i++){
				     if(cbx[i].checked==true){
				       if(chkIds.length==0){
				         chkIds = cbx[i].value; 
				       }else{
				         chkIds += ','+cbx[i].value; 
				       }
				      
				     }
				  }
				  if(chkIds.length==0){
				    alert("请选择删除项");
				    return;
				  }else if(!confirm("确定删除选择项")){
				  	return;
				  }
				  document.location.href="assessTemp.do?method=deleteRecores&chkIds="+chkIds;
				}
		</script>
		
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title">
					当前位置：模板下发 >> 下发记录
				</div>
			</div>
			<div style="height: 100%; overflow: hidden; width: 90%; padding: 0px;"
				id="center">
				<div class="contentList">
					<div>
					<form action="assessTemp.do?method=sendRecorder" method="post">
						<table class="tableborder" border="0" cellpadding="5"
							cellspacing="0" width="98%" id="tab1"
							style="margin: 10px 10px 0 10px;">
							<tr>
								<td colspan="4">
									<div style="margin-left: 5px;">
										模板下发记录查询：此处展示查询注意事项
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" align="right">
									考核名称：
								</td>
								<td>
									<input type="text" name="scheduName" value="" maxlength="50"
										onfocus="this.className='focus'"
										onblur="this.className='blur'" class="blur" />
								</td>
							
								<td width="25%" align="right">
									考核对象：
								</td>
								<td>
									<input type="text" name="objectName" value="" maxlength="50"
										onfocus="this.className='focus'"
										onblur="this.className='blur'" class="blur" />
								</td>
							</tr>
							<tr>
								<td width="25%" align="right">
									考核周期：
								</td>
								<td>
									<input type="radio" name="period" value="2" checked />
									周
									<input type="radio" name="period" value="3" />
									月
								</td>
							
								<td align="right">
									下发时间：
								</td>
								<td>
									<input class="blur" id="inputid3" type="text" name="creatTime"
										value="" maxlength="50" />
									<img onclick="WdatePicker({el:$dp.$('inputid3')});"
										src="${rc.contextPath}/images/datePicker.gif" width="16"
										height="22" align="absmiddle" />
								</td>
							</tr>
							<tr>
								<td align="right">
									考核开始时间：
								</td>
								<td>
									<input class="blur" id="inputid" type="text" name="beginTime"
										value="" maxlength="50" />
									<img onclick="WdatePicker({el:$dp.$('inputid')});"
										src="${rc.contextPath}/images/datePicker.gif" width="16"
										height="22" align="absmiddle" />
								</td>
							
								<td align="right">
									考核结束时间：
								</td>
								<td>
									<input class="blur" id="inputid2" type="text" name="endTime"
										value="" maxlength="50" />
									<img onclick="WdatePicker({el:$dp.$('inputid2')});"
										src="${rc.contextPath}/images/datePicker.gif" width="16"
										height="22" align="absmiddle" />
								</td>
							</tr>

							
						</table>
						<table width="99%" class="button_bar">
							<tr>
								<td align="center">
									<input type="submit" value="查询" class="button"
										onmouseover="this.className='buttonhover'"
										onmouseout="this.className='button'" />
									<input type="reset" value="重置" class="button"
										onmouseover="this.className='buttonhover'"
										onmouseout="this.className='button'" />
									<input type="button" value="返回" class="button"
										onmouseover="this.className='buttonhover'"
										onmouseout="this.className='button'" 
										onclick="window.history.go(-1)" />
								</td>
							</tr>
						</table>
					</form>
					</div>
					<form name="form1" action="#" method="post">
				
					
					<table cellpadding="1" cellspacing="0" border="0" width="99%"
						style="margin-left: 5px;">
							<tr>
							<td>
							<c:if test="${candelete==true}">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="border: 1px solid #ccc; border-bottom: none; padding: 5px 0 5px 0;">
									<tr>
										<td >
											<a href="#" onclick="toDelete()">删除记录</a>
											<img
												src="${rc.contextPath}/images/script-edit.png" />
										</td>
										<td width="8"></td>
									</tr>
								</table>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="tableborder" id="tab">
									<tr>
									  <c:if test="${candelete==true}">
									   <th>
										<input type="checkbox" name="chkAll" id="chkAll"
										onclick="checkAll()" />
											</th>
											</c:if>
									    </th>
									    <th>
											考核名称
										</th>
										<th>
											模板名称
										</th>
										<th>
											考核对象
										</th>
										<th>
											考核周期
										</th>
										<th>
											开始考核时间
										</th>
										<th>
											下发人
										</th>
										<th>
											下发时间
										</th>
									</tr>

									<c:forEach items="${rlist}" var="n">
										<tr>
										<c:if test="${candelete==true}">
										  <td>
														<input type="checkbox" name="cbx" id="cbx"
															value="${n.id}" />
													</td>
													</c:if>
										    <td>${n.assessSchedu.name}</td>
										    <td>${n.assessTemplate.templateName}</td>
											<td>
												${n.assessObject.objectName }
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
											<td>
												<fmt:formatDate value="${n.beginTime }" type="both"
													pattern="yyyy.MM.dd" />
													<!-- yyyy.MM.dd HH:mm:ss -->
											</td>
											<td>
												${n.releaseName }
											</td>
											<td>
												<fmt:formatDate value="${n.createTime }" type="both"
													pattern="yyyy.MM.dd" />
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
                             </from>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
