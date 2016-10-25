<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>考核状态页</title>
		<link href="${rc.contextPath}/scripts/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
		<script src="${rc.contextPath}/js/main.js"></script>
		<script src="${rc.contextPath}/scripts/jquery-1.4.2.min.js"></script>
		<script src="${rc.contextPath}/scripts/ztree/jquery-ztree-2.5.min.js"></script>
		<script src="${rc.contextPath}/scripts/ztree/jquery.json.js"></script>
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script type="text/javascript" src="dwr/interface/AssessObjAjax.js"></script>
		<script language="javascript">
			window.onresize = function() {
				setCenter(4,27);
			}
			window.onload = function() {
				setCenter(4,27);
				changeTips();
			}
			
			function changeTips(){
				var stage=$("#stage").val();
				$(".chinfo").each(function(){
					if(stage=="新增"){
						$(this).text("新增");
					}else{
						$(this).text("修改");
					}
				});
				<c:if test="${targetCode=='1'}">
				  $("#targetCodeView").val('组织机构');
				</c:if>
				<c:if test="${targetCode=='2'}">
				   $("#targetCodeView").val('人员');
				 </c:if>
			}
			var zTree = null;
			var setting=null;
			/* 组织机构树（所有） */
			function initTree(){
			   $("#listTree").val('');
			   $("#targetCodeView").val('');
			   $("#targetName1").val('');
				setting = {
				    expandSpeed: "",
					async: true,
					asyncUrl:getUrl,
				    callback :{
				    	beforeExpand: zTreeBeforeExpand,
						asyncSuccess: zTreeAjaxSuccess,
						asyncError: zTreeAjaxError,
						click: zTreeOnClick
				    }
				};
				
				var htmlObj=$.ajax({url:"assessObj.do?method=getListTree", dataType:"json", async:false});
				var treeNodes=eval('('+htmlObj.responseText+')');
				zTree = $("#tree").zTree(setting, $(treeNodes).toArray());
				var listTree=$("#listTree").offset();
				var input_width=$("#listTree").outerWidth();
				var input_height=$("#listTree").outerHeight();
				$("#tree").css({"display":"block","left":listTree.left,"top":listTree.top+input_height-1,"width":"200px"});
			    function zTreeOnClick(event, treeId, treeNode) {
					$("#listTree").val(treeNode.name);
					$("#foreignId").val(treeNode.id);
					if(!treeNode.isParent){
					    $("#targetCode").val('2');
					     $("#targetCodeView").val('人员');
					    $("#targetName1").val(treeNode.name);
						$("#targetName").val(treeNode.hao);
					}else{
					     $("#targetCode").val('1');
					     $("#targetCodeView").val('组织机构');
					}
					$("#tree").css({"display":"none"});
				}
			}
			
			
			function getUrl(treeNode) {
			       return "assessObj.do?method=getListTree&pid="+treeNode.id;
	        }
	        function zTreeBeforeExpand(treeId, treeNode) {
				if (!treeNode.isAjaxing) {
					ajaxGetNodes(treeNode, "refresh");
					return false;
				} else {
					alert("zTree 正在下载数据中，请稍后展开节点…");
					return false;
				}
				return true;
			}
			function zTreeAjaxSuccess(event, treeId, treeNode, msg) {
			    var totalCount = treeNode.count;
				if (treeNode.nodes.length < totalCount) {
					var percent = treeNode.nodes.length*100/totalCount + "%";
					setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
				} else {
					treeNode.icon = "";
					zTree.updateNode(treeNode);
					zTree.selectNode(treeNode.nodes[0]);
				}
			}
			function zTreeAjaxError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
				alert("异步获取数据出现异常!");
				treeNode.icon = "";
				zTree.updateNode(treeNode);
			}
			function ajaxGetNodes(treeNode, reloadType) {
				if (reloadType == "refresh") {
					treeNode.icon = "zTreeStyle/img/loading.gif";
					zTree.updateNode(treeNode);
				}
				zTree.reAsyncChildNodes(treeNode, reloadType);
			}
		         	
			
			
			/* 组织机构下的用户树 */
			function initTree2(){
			$("#usertree").css({"display":"block"});
			var dept_id = $("#foreignId").val();
			var settingu = {
				    callback :{
						click: zTreeOnClick
				    }
			};
			var htmlObj=$.ajax({url:"assessObj.do?method=getListTree&obj=user&pid="+dept_id, dataType:"json", async:false});
			var treeNodes=eval('('+htmlObj.responseText+')');
					uzTree = $("#usertree").zTree(settingu,$(treeNodes));
					var targetName1=$("#targetName1").offset();
					var input_width=$("#targetName1").outerWidth();
					var input_height=$("#targetName1").outerHeight();
					$("#usertree").css({"left":targetName1.left,"top":targetName1.top+input_height+1,"width":"200px"});
					function zTreeOnClick(event, treeId, treeNode) {
						$("#targetName1").val(treeNode.name);
						$("#targetName").val(treeNode.hao);
						$("#usertree").css({"display":"none"});
					}
			}
			
			function InfoSub(){
			    var obj =  document.getElementById("listTree");
				  var targetName1 =  document.getElementById("targetName1");
				  if(obj.value==null||obj.value==""){
				      alert("请选择考核对象名称！")
				      return;
				  }
				  if(targetName1.value==null||targetName1.value==""){
				      alert("请选择负责人！")
				       return;
				  }
				$("#stage").click(function(){
					var stage=$("#stage").val();
					if(stage=="新增"){
						$('#fromFlag').attr("action","assessObj.do?method=addCheckObj&stage=true");
					}else{
						$('#fromFlag').attr("action","assessObj.do?method=addCheckObj&stage=false");
					}
					document.forms.fromFlag.submit();
				});
				
			}
		</script>
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title"> 
					当前位置：考核对象管理 &gt;&gt; <label class="chinfo"></label>考核对象
				</div>
			</div>
			<div style="height: 100%; overflow: auto; width: 90%; padding: 0px;"
				id="center">
				<form id="fromFlag" action="" method="post">
					<table class="tableborder" border="0" cellpadding="5"
						cellspacing="0" width="98%" id="tab"
						style="margin: 10px 10px 0 10px;">
						<c:forEach items="${objMang}" var="m">
							<tr>
								<td colspan="3">
									<div style="margin-left: 5px;">
										<label class="chinfo"></label>考核对象：此处展示<label class="chinfo"></label>考核对象注意事项
									</div>
								</td>
							</tr>
							<tr>
								<td width="30%" align="right">
									考核对象编码：
								</td>
								<td>
									<input type="text" name="tempID" value="${m.objectCode }"
										maxlength="50" onfocus="this.className='focus'"
										onblur="this.className='blur'" class="blur" />
								</td>
							</tr>
							<tr>
								<td width="30%" align="right">
									考核对象名称：
									<input id="idInfo" name="idInfo"  type="hidden" value="${m.id }" />
									<input id="foreignId" name="foreignId" type="hidden" value="${m.foreignId }" />
								</td>
								<td>
									<input type="text" id="listTree" name="fatherNodeID" readonly="readonly"
										value="${m.objectName }" maxlength="50"
										onfocus="this.className='focus'"
										onblur="this.className='blur'" class="blur"  onclick="initTree()"/>

								</td>
							</tr>
							<tr>
								<td width="30%" align="right">
									考核对象类型：
								</td>
								<td>
								    <input type='hidden' id="targetCode" name="targetCode" value="${m.objectType}" />
									<input type='text' id="targetCodeView" name="targetCodeView"
										value=""  maxlength="50" readonly="readonly"
										onFocus="this.className='focus'" 
										onBlur="this.className='blur'" class="blur"
										style="width: 100px;"/>
								</td>
							</tr>
							<!-- 
								<tr>
									<td align="right">
										考核对象ID：
										<input id="idInfo" name="idInfo" type="hidden" value="${m.id }" />
									</td>
									<td>
										<input id="listTrees" name="listTree" type="hidden" />
									</td>
								</tr>
							 -->
							<tr>
								<td align="right">
									责任人：
								</td>
								<td>
								    <input type='hidden' id="targetName" name="targetName"  value="${m.assignee}">
									<input id="targetName1" type="text" name="targetName1" readonly="readonly" onclick="initTree2()"
										value="${userName}" maxlength="50" 
										onFocus="this.className='focus'"
										onBlur="this.className='blur'" class="blur"/>
								</td>
							</tr>
							</c:forEach>
					</table>
					<table width="99%" class="button_bar">
						<tr>
							<td align="center">
								<input id="stage" name="stage" type="button" value="${staflg }"
									class="button" onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" onclick="InfoSub()" />
								<input type="reset" value="重置" class="button"
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
								<input type="button" value="返回" class="button" onclick=window.history.go(-1)
									onmouseover="this.className='buttonhover'"
									onmouseout="this.className='button'" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<ul id="tree" class="tree"
			style="display: none; position: absolute; height: 250px; z-index: 1000; border: 1px solid #617775; background: #E8F2FE; overflow: auto"></ul>
		<ul id="usertree" class="tree"
			style="display: none; position: absolute; height: 250px; z-index: 1000; border: 1px solid #617775; background: #E8F2FE; overflow: auto"></ul>
	</body>
</html>
