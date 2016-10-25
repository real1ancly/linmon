<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>历史考核查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
		<script src="${rc.contextPath}/scripts/date/WdatePicker.js"></script>
		<script src="${rc.contextPath}/js/main.js"></script>
		<script language="javascript">
			window.onresize = function() {
				setCenter(4,27);
			}
			window.onload = function() {
				setCenter(4,27);
			}
		</script>
	</head>
	<body>
		<div class="content">
			<div class="content_title_bg">
				<div class="content_title">
					当前位置：考核统计 >> 历史考核查询
				</div>
			</div>
			<div style="height: 100%; overflow: auto; width: 90%; padding: 0px;"
				id="center">
				<form action="historyResult.do" method="post">
					<table class="tableborder" border="0" cellpadding="5"
						cellspacing="0" width="98%" id="tab"
						style="margin: 10px 10px 0 10px;">
						<tr>
							<td colspan="3">
								<div style="margin-left: 5px;">
									历史考核查询：此处展示查询注意事项
								</div>
							</td>
						</tr>
						<tr>
							<td width="30%" align="right">
								考核模板名称：
							</td>
							<td>
								<input type="text" name="templateName" value="" maxlength="50"
									onfocus="this.className='focus'" onblur="this.className='blur'"
									class="blur" />
							</td>
						</tr>
						
						<tr>
							<td width="30%" align="right">
								考核对象：
							</td>
							<td>
								<input type="text" name="objectName" value="" maxlength="50"
									onfocus="this.className='focus'" onblur="this.className='blur'"
									class="blur" />
							</td>
						</tr>
						<tr>
							<td width="30%" align="right">
								考核周期：
							</td>
							<td>
								<input type="radio" name="checkCyrcle" value="3" checked="checked" />
								月
								<input type="radio" name="checkCyrcle" value="2" />
								周
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
						</tr>
						<tr>
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
									onclick=window.history.go(-1) />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
