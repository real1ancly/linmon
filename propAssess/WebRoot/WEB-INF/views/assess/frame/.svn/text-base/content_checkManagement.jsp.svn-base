<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考核对象管理</title>
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css"
			rel="stylesheet" type="text/css" />
		<script src="${rc.contextPath}/js/main.js"></script>
		<script src="${rc.contextPath}/scripts/jquery-1.4.2.min.js"></script>
		<script src="${rc.contextPath}/js/common.js"></script>
		<script language="javascript">
			window.onresize = function() {
				setCenter(4,27);
			}
			window.onload = function() {
				setCenter(4,27);
				changeRow_color("tab");
				convtype();
			}
			function delObj(obj){
				var idList="";
				$("input:checked").each(function(i){
					idList+=$(this).val()+",";
				});
				if(idList.length == 0){
					alert("请选择要删除的数据");
					return;
				}else{
					var f=window.confirm("你确定要删除这条数据吗？");
					if(f==true){
						$(obj).attr("href","assessObj.do?method=delObj&idList="+idList);
					}
				}
			}
			function upObj(obj){
				var idList="";
				var l=$("input:checked").length;
				if(l>1){
					alert("只能选择一个进行修改!");
				}else if(l==0){
					alert("请选择一个进行修改!");
				}else{
					$("input:checked").each(function(i){
						idList=$(this).val();
					});
					$(obj).attr("href","assessObj.do?method=forAddPage&stage=false&id="+idList);
				}
			}
			
			function convtype(){
				$(".convType").each(function(){
					var ovalue=$.trim($(this).text());
					if(ovalue=="1"){
						$(this).text("组织机构");
					}else{
						$(this).text("人员");
					}
				});
			}
			
		</script>
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title">
					当前位置：考核对象管理
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
										<td width="85">
											<a href="assessObj.do?method=forAddPage&id=&stage=true"
												target="_self">新增考核对象</a>
										</td>
										<td width="20">
											<img
												src="${rc.contextPath}/images/script-delete.png" />
										</td>
										<td width="85">
											<a href="#" onclick="delObj(this)">删除考核对象</a>
										</td>
										<td width="20">
											<img
												src="${rc.contextPath}/images/script-edit.png" />
										</td>
										<td>
											<a href="#" onclick="upObj(this)">修改考核对象</a>
										</td>
										<td width="9"></td>
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
												<input type="checkbox" name="chkAll" id="checkbox"
													onclick="checkAll()" />
											</th>
											<!-- 
												<th>
													考核对象ID
												</th>
											 -->
											<th>
												考核对象编码
											</th>
											<th>
												考核对象名称
											</th>
											<th>
												考核对象类型
											</th>
											<th>
												责任人
											</th>
										</tr>
										<c:forEach items="${listObj}" var="n">
											<tr>
												<td>
													<input type="checkbox" value="${n.id}" name="cbx"
														id="checkbox2" />
													<input type="hidden" name="objId" id="objId"
														value="${n.id}" />
												</td>
												<!-- 
													<td>
														${ n.foreignId }
													</td>
												 -->
												<td>
													${n.objectCode }
												</td>
												<td>
													${n.objectName}
												</td>
												<td class="convType">
													${n.objectType}
												</td>
												<td>
													${n.assignee }
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
