<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<%@page import="com.ultrapower.assess.util.PageUtils"%>
<%@page import="com.bidlink.core.commons.support.page.Page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>下发内容</title>
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="${rc.contextPath}/css/jquery.multiselect.css" />
		<link rel="stylesheet" type="text/css"
			href="${rc.contextPath}/css/mutiselectstyle.css" />
		<link rel="stylesheet" type="text/css"
			href="${rc.contextPath}/css/prettify.css" />
		<link rel="stylesheet" type="text/css"
			href="${rc.contextPath}/css/jquery-ui.css" />
		<script type="text/javascript"
			src="${rc.contextPath}/scripts/date/WdatePicker.js"></script>
		<script type="text/javascript" src="${rc.contextPath}/js/main.js"></script>
		<script type="text/javascript" src="${rc.contextPath}/js/common.js"></script>
		<script type="text/javascript"
			src="${rc.contextPath}/scripts/jquery.js"></script>
		<script type="text/javascript"
			src="${rc.contextPath}/scripts/jquery-ui.min.js"></script>
		<script type="text/javascript"
			src="${rc.contextPath}/scripts/jquery.bgiframe.min.js"></script>
		<script type="text/javascript"
			src="${rc.contextPath}/scripts/prettify.js"></script>
		<script type="text/javascript"
			src="${rc.contextPath}/scripts/jquery.multiselect.js"></script>
	    <script src="${rc.contextPath}/js/common.js"></script>
		<%
			String divtype = (String) request.getAttribute("divtype");
			if ("2".equals(divtype)) {
		%>
		<script language="javascript">
		 
		 alert("考核已删除");
		
         </script>
		<%
			} else if ("1".equals(divtype)) {
		%>
		<script language="javascript">
		 
		 alert("删除失败");
		
         </script>
		<%
			}
		%>
           <script language="javascript">
              function topage(){
                 <c:if test="${isPage==2}">
                     var choosetype = document.getElementsByName("choosetype")[1]
                     choosetype.checked=true;
                 </c:if>
                 showDIV();
              }
           </script>
		<script language="javascript">
			window.onresize = function() {
				setCenter(4,27);
			}
			window.onload = function() {
				$("#objectCode1").multiselect();
				$("#objectCode2").multiselect();
				setCenter(4,27);
				changeRow_color("tab5");
			}
			function DateAdd(interval,number,date){
		        switch(interval.toLowerCase()){
	                case "y": return new Date(date.setFullYear(date.getFullYear()+number));
	                case "m": return new Date(date.setMonth(date.getMonth()+number));
	                case "d": return new Date(date.setDate(date.getDate()+number));
	                case "w": return new Date(date.setDate(date.getDate()+7*number));
	                case "h": return new Date(date.setHours(date.getHours()+number));
	                case "n": return new Date(date.setMinutes(date.getMinutes()+number));
	                case "s": return new Date(date.setSeconds(date.getSeconds()+number));
	                case "l": return new Date(date.setMilliseconds(date.getMilliseconds()+number));
		        } 
			}
			
			function showDIV(){
			    if(document.getElementsByName("choosetype")[0].checked){
			       document.getElementById("hasTem").style.display="none";
			       document.getElementById("div1").style.display="";
			    }else{
			       document.getElementById("hasTem").style.display="";
			       document.getElementById("div1").style.display="none";
			    }
			}
			
			function checkOne1(cb){
				var obj= document.getElementsByName('rid');
				for(var i=0;i<obj.length;i++){
					if(obj[i]!=cb){
						obj[i].checked=false;
					}else{ 
						obj[i].checked=true;
					}
				}
			}
			function checkOne(obj){
				var objs = document.getElementsByName('rid');
				for(var j=0;j<objs.length;j++){
					if(objs[j]!=obj){
						objs[j].checked=false;
					}else{
						objs[j].checked=true;
					}
				}
				var tdObj = obj.parentNode.parentNode;
				var tdLen = tdObj.children.length;
				for(var i=0;i<tdLen;i++){
					document.getElementsByName('an')[0].value=tdObj.children[1].innerText.substr(0,32);
				}
			}
            Date.prototype.Format = function(formatStr) 
              {
                var str = formatStr;
                var Week = ['日', '一', '二', '三', '四', '五', '六'];
                str = str.replace(/yyyy|YYYY/, this.getFullYear());
                str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
                str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
                str = str.replace(/M/g, (this.getMonth() + 1));
                str = str.replace(/w|W/g, Week[this.getDay()]);
                str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
                str = str.replace(/d|D/g, this.getDate());
                str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
   			    str = str.replace(/h|H/g, this.getHours());
   				str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
  			    str = str.replace(/m/g, this.getMinutes());
                str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
                str = str.replace(/s|S/g, this.getSeconds());
                return str;
               }
			
			function ChangeTxt(){
				var a = document.getElementById("beginTime").value; 
				var b = document.form1.period;
				var ip = "";
				for(i=0;i<b.length;i++)
				{
					if(b[i].checked){
						ip=b[i].value;
					}
				}
				if(a!="" && i!="" && ip!=""){
					if(ip == "2"){
						var dt = new Date(Date.parse(a.replace(/-/ig,"/")));
						var newDate = DateAdd( "d",7,dt);
						var dd = newDate=newDate.Format("YYYY-MM-DD");
						document.getElementById("endTime").innerText=dd;
						
					}
					if(ip == "3"){
						var dt = new Date(Date.parse(a.replace(/-/ig,"/")));
						var newDate = DateAdd( "m",1,dt);
						var dd = newDate=newDate.Format("YYYY-MM-DD");
						document.getElementById("endTime").innerText=dd;
						document.getElementById("endTime").innerText=dd;
						
					}
				}
			}
			function calibrationfrom2(){
				var a = document.getElementById("objectCode1").value; 
				var b = document.getElementById("scheduName").value;
				var c = document.getElementById("beginTime").value;
				var e =document.getElementById("endTime").value;
				var d = document.getElementById("resultCreatTime").value;
				var nowdate =new Date();
				nowdate =nowdate.Format("YYYY-MM-DD");
				
				if(b == ''){
					alert("请填写考核名称!");
					return false;
				}
				if(a == ''){
					alert("请选择考核对象!");
					return false;
				}
				if(c == ''){
					alert("请选择考核开始时间!");
					return false;
				}
				if(d == ''){
					alert("请选择考核结果生成时间!");
					return false;
				}
				if(d<e){
				    alert("考核结果生成时间需大于考核结束时间!");
				    return false;
				}
				if(nowdate>e){
				    alert("考核结束时间不能小于当前日期!");
				    return false;
				}
				document.form1.submit();
			}
			function calibrationfrom3(){
			 	var a = document.getElementById("objectCode2").value; 
			 	var objs = document.getElementsByName('checkbox2');
			 	var b=0;
				
			 	if(a == ''){
			 		alert("请选择考核对象!");
			 		return false;
			 	}
			 	for(var j=0;j<objs.length;j++){
					if(objs[j].checked==true){
					b=b+1;
					}
				}
				if(b==0){
				   alert("请选择考核项!");
				   return false;
				}
			 	document.form2.submit();
			}
			function toDelete(sid){
			var id=sid
			alert(id);
			   bConfirmed = window.confirm("确定要删除么?");
			   if(bConfirmed) {
					window.open('assessTemp.do?method=deleteSchedu&id='+id);
				}
			}
			
			
		</script>
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title">
					当前位置：模板下发 >> 模板下发
				</div>
			</div>
			<div
				style="height: 100%; overflow: hidden; width: 90%; padding: 0px;"
				id="center">
				<table border="0" cellpadding="5" cellspacing="0" width="98%"
					id="tab1" style="margin: 10px 10px 0 10px;">
					<tr>
						<td colspan="3">
							<div style="margin-left: 5px;">
								选择下发模板类型
								<input type="radio" name="choosetype" checked="checked"
									onclick="showDIV();" />
								新建考核
								<input type="radio" name="choosetype" onclick="showDIV();" />
								选择已有考核
							</div>
						</td>
					</tr>
				</table>
				<div name="div1" id="div1" style="">
					<!-- 新建考核 -->
					<form name="form1" action="assessTemp.do?method=addassessRecords"
						method="post">

						<table class="tableborder" border="0" cellpadding="5"
							cellspacing="0" width="98%" id="tab2"
							style="margin: 10px 10px 0 10px;">
							<tr>
								<td colspan="3">
									<div style="margin-left: 5px;">
										下发内容：此处展示下发内容注意事项
									</div>
								</td>
							</tr>
							<tr>
								<td align="right">
									考核名称：
								</td>
								<td>
									<input class="blur" id="scheduName" type="text"
										name='scheduName' maxlength="50" value="" />
								</td>
							</tr>
							<tr>
								<td width="30%" align="right">
									考核对象选择：
								</td>
								<td>
									<select id="objectCode1" multiple="multiple" name="objectCode" 
										>
										<c:forEach items="${assessObj}" var="assessObj">
											<option value="${assessObj.id}">
												${assessObj.objectName}
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td width="30%" align="right">
									考核模板选择：
								</td>
								<td>
									<select name="assessTempId" size="1">
										<c:forEach items="${templates}" var="template">
											<option value="${template.id}">
												${template.templateName}
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">
									考核周期：
								</td>
								<td>
									<input type="radio" name="period" value="2" checked="checked"
										onclick="ChangeTxt();" onchange="ChangeTxt();" />
									周
									<input type="radio" name="period" onchange="ChangeTxt();"
										onclick="ChangeTxt();" value="3" />
									月
								</td>
							</tr>

							<tr>
								<td align="right">
									考核开始时间：
								</td>
								<td>
									<input class="blur" id="beginTime" type="text" name='beginTime'
										maxlength="50" onchange="ChangeTxt();" value="" />
									<img onclick="WdatePicker({el:$dp.$('beginTime')});"
										src="${rc.contextPath}/images/datePicker.gif" width="16"
										height="22" align="middle" />
								</td>

							</tr>
							<tr>
								<td align="right">
									考核结束时间：
								</td>
								<td>
									<input class="blur" id="endTime" type="text" name="endTime"
										value="" maxlength="50" />
								</td>
							</tr>
							<tr>
								<td align="right">
									考核结果生成时间：
								</td>
								<td>
									<input class="blur" id="resultCreatTime" type="text"
										name="resultcreatTime" value="" maxlength="50" />
									<img onclick="WdatePicker({el:$dp.$('resultCreatTime')});"
										src="${rc.contextPath}/images/datePicker.gif" width="16"
										height="22" align="middle" />
								</td>
							</tr>
						</table>
						<table width="99%" class="button_bar">
							<tr>
								<td align="center">
									<input type="button" value="提交" class="button"
										onmouseover="this.className='buttonhover'"
										onmouseout="this.className='button'"
										onclick="calibrationfrom2();" />
									<input type="reset" value="重置" class="button"
										onmouseover="this.className='buttonhover'"
										onmouseout="this.className='button'" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div id="hasTem" class="content"
					style="display: none; border: none; overFlow: hidden">
					<div style="border: none;"></div>
					<!--  div id="div2" name="div2" style="height: 100%; overflow: auto; width: 100%; padding: 0px; display: none; border:none;"-->
					<div class="contentList">
						<form name="form2" action="assessTemp.do?method=addAssess"
							method="post">
							<input type=hidden name="an" value="" />
							<table cellpadding="0" cellspacing="0" border="0" width="99%"
								id="t1" style="margin-left: 5px;">
								<tr>
									<td style="height: 50px;">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											style="border: 1px solid #ccc; border-bottom: none; padding: 5px 0 5px 0;">
											<tr style="padding: 5px;">
												<td align="right">
													考核对象选择：
												</td>
												<td>
													<select id="objectCode2" style="height: 20px;"
														title="objectCode" multiple="multiple" name="objectCode"
														size="5">
														<c:forEach items="${assessObj}" var="assessObj">
															<option value="${assessObj.id}">
																${assessObj.objectName}
															</option>
														</c:forEach>
													</select>
												</td>
												<td align="left">
													考核模板选择：
												</td>
												<td align="left">
													<select title="assessTempId" name="assessTempId" size="1"
														style="height: 20px;">
														<c:forEach items="${templates}" var="template">
															<option value="${template.id}">
																${template.templateName}
															</option>
														</c:forEach>
													</select>
												</td>
											</tr>

										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="tableborder" id="tab5">
											<tr>
												<th></th>
												<th align="left">
													考核名称
												</th>
												<th align="left">
													考核创建时间
												</th>
												<th align="left">
													考核开始时间
												</th>
												<th align="left">
													考核结束时间
												</th>
												<th align="left">
													考核周期
												</th>
												<th align="left">
													考核结果生成时间
												</th>
												<!--  th align="left">
													删除
												</th-->
											</tr>
											<c:forEach items="${anlist}" var="anlist">
												<tr>

													<td>
														<input type="checkbox" name="rid" id="checkbox2"
															onclick="checkOne(this)" value="${anlist.name}" />
													</td>

													<td style="display: none;">
														${anlist.id}
													</td>
													<td>
														${anlist.name}
													</td>
													<td>
														<fmt:formatDate value="${anlist.createtime }" type="both"
															pattern="yyyy.MM.dd" />
														<!-- pattern="yyyy.MM.dd HH:mm:ss" -->
													</td>
													<td>
														<fmt:formatDate value="${anlist.begintime }" type="both"
															pattern="yyyy.MM.dd" />
													</td>
													<td>
														<fmt:formatDate value="${anlist.endtime }" type="both"
															pattern="yyyy.MM.dd" />
													</td>
													<td>
														<script type="text/javascript">
					
					var a = ${anlist.perior }
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
														<fmt:formatDate value="${anlist.resultcreatetime }"
															type="both" pattern="yyyy.MM.dd" />
													</td>
													<!--  td>
														<a
															href="#"
															onclick="toDelete(${anlist.id});">删除</a>
														<!--  onclick="assessTemp.do?method=deleteSchedu&id=${anlist.id}"-->
													<!-- /td-->
												</tr>
											</c:forEach>

											<tr>
												<th colspan="8" align="right"
													style="text-align: right; padding-right: 10px; background: none; height: 22px;">
													${pageToString}
												</th>
											</tr>
										</table>
										<table width="99%" class="button_bar">
											<tr>
												<td align="center">
													<input type="button" value="提交" class="button"
														onmouseover="this.className='buttonhover'"
														onmouseout="this.className='button'"
														onclick="calibrationfrom3();" />
													<!-- onclick="calibrationfrom2();"  -->
													<input type="reset" value="重置" class="button"
														onmouseover="this.className='buttonhover'"
														onmouseout="this.className='button'" />
												</td>
											</tr>

										</table>
									</td>
								</tr>
							</table>

						</form>
					</div>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>
<script language="javascript">
topage();
</script>
