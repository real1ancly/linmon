<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.ultrapower.assess.util.PageUtils"%>
<%@page import="com.bidlink.core.commons.support.page.Page"%>
<%@page import="com.ultrapower.assess.manager.AssessRecordsManager"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
			
			function toDelete(){
				  var cbx = document.getElementsByName('cbx');
				  var status = document.getElementsByName('status');
				  var chkIds = "";
				   var ids = "";
				  for(var i=0;i<cbx.length;i++){
				     if(cbx[i].checked==true){
				       if(chkIds.length==0){
				         chkIds = cbx[i].value; 
				       }else{
				         chkIds += ','+cbx[i].value; 
				       }
				       if(status[i].value!=1){
				           ids += ",'"+cbx[i].value+"'"
				       }  
				     }
				  }
				  if(chkIds.length==0){
				    alert("请选择删除项");
				    return;
				  }else if(!confirm("确定删除选择项")){
				  	return;
				  }
				  if(ids!=""){
				     document.location.href="template.do?method=beforeDelete&isF=yes&chkIds="+chkIds+"&ids="+ids;
				     return;
				  }
				  document.location.href="template.do?method=delete&isF=yes&chkIds="+chkIds;
				}
				
				function reloadTree(){
					window.parent.leftFrame.location.reload();
				}
				
				function toEdit(){
				  var cbx = document.getElementsByName('cbx');
				  var status = document.getElementsByName('status');
				  var id = "";
				  var sta = "";
				  for(var i=0;i<cbx.length;i++){
				     if(cbx[i].checked==true){
				       if(id.length==0){
				         id = cbx[i].value; 
				         sta = status[i].value;
				       }else{
				         alert("选项过多，请重新选择");
				         return;
				       }
				        
				     }
				  }
				  if(id.length==0){
				    alert("请选择修改项");
				    return;
				  }
				  if(sta!=1){
				    alert("此模板已发布，不可修改")
				     return;
				  }
				  document.location.href='template.do?method=toEdit&id='+id;
			}
			
			function publish(){
			  var cbx = document.getElementsByName('cbx');
			  var status = document.getElementsByName('status');
			  var id = "";
			  var sta = "";
			  for(var i=0;i<cbx.length;i++){
			     if(cbx[i].checked==true){
			       if(id.length==0){
			         id = cbx[i].value; 
			         sta = status[i].value;
			       }else{
			         alert("选项过多，请重新选择");
			         return;
			       }
			        
			     }
			  }
			  if(id.length==0){
			    alert("请选择发布项");
			    return;
			  }
			  if(sta!=1){
			    alert("此模板已发布")
			     return;
			  }
			  if(!confirm("确定发布该模板")){
			  	return;
			  }
			  document.location.href="template.do?method=publish&id="+id;
			}
			function afterdelete(){
			    <c:if test="${using=='using'}">
			       alert("对不起，模板还在使用中，不可删除！")
			    </c:if>
			}
		</script>
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title">
					当前位置：模板管理
				</div>
			</div>
			<div style="height: 100%; overflow: auto; width: 90%; padding: 0px;"
				id="center">
				<div class="contentList" style="margin-top: 7px;">
					<table cellpadding="0" cellspacing="0" border="0" width="99%"
						style="margin-left: 5px;">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									style="border: 1px solid #ccc; border-bottom: none; padding: 5px 0 5px 0;">
									<tr>
										<td width="8"></td>
										<td width="20">
											<img
												src="${rc.contextPath}/images/script-add.png" />
										</td>
										<td width="60">
											<a href="template.do?method=toAdd" target="contentFrame">新增模板</a>
										</td>
										<td width="20">
											<img
												src="${rc.contextPath}/images/script-delete.png" />
										</td>
										<td width="60">
											<a href="#" onclick="toDelete()">删除模板</a>
										</td>
										<td width="20">
											<img
												src="${rc.contextPath}/images/script-edit.png" />
										</td>
										<td width="60">
											<a href="#" onclick="toEdit()">修改模板</a>
										</td>
										<td width="20">
											<img
												src="${rc.contextPath}/images/script-gear.png" />
										</td>
										<td>
											<a href="#"
												onclick="publish()">发布</a>
										</td>
										<!--  <td align="right">
							                    <input type="text" size="15" onblur="this.className='blur'" onfocus="this.className='focus'" class="blur"/>
							                    <input type="button" value="搜索"  class="button"  onmouseover="this.className='buttonhover'" onmouseout="this.className='button'"/>
                							  </td> 
                						-->
										<td width="8"></td>
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
												<input type="checkbox" name="chkAll" id="chkAll"
													onclick="checkAll()" />
											</th>
											<th>
												模板名称
											</th>
											<th>
												模板状态
											</th>
											<th>
												模板负责人
											</th>
											<th>
												最后修改人
											</th>
											<th>
												最后修改时间
											</th>
											<th>
												备注
											</th>
										</tr>
										<c:if test="${templateList!=null}">
											<c:forEach items="${templateList}" var="template">
												<tr>
													<td>
														<input type="checkbox" name="cbx" id="cbx"
															value="${template.id}" />
														<input type="hidden" name="status" id="status"
															value="${template.status}" />
													</td>
													<td>
														${template.templateName}
													</td>
													<td>
														<c:if test="${template.status==1}">草稿</c:if>
														<c:if test="${template.status==2}">已发布</c:if>
													</td>
													<td>
														${template.assignee}
													</td>
													<td>
														${template.updateAccount}
													</td>
													<td>
														<fmt:formatDate value='${template.updateTime}'
															pattern='yyyy-MM-dd HH:mm:ss' />
													</td>
													<td>
														${template.remarks}
													</td>
												</tr>
											</c:forEach>
										</c:if>
										<tr>
											<th colspan="8" align="right"
												style="text-align: right; padding-right: 10px; background: none; height: 22px;">
												${pageToString}
												<!--   每页行数： <select name="" onblur="this.className='blur'" onfocus="this.className='focus'" onchange="changeCount()">
														              <option value="20">20</option>
														              <option value="40">40</option>
														              <option value="60">60</option>
										            			</select> 
										         -->
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
