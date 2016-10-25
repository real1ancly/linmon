<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet"
			type="text/css" />
		<title>无标题文档</title>
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
					当前位置：模板管理
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
										<td width="8"></td>
										<td width="20">
											<img src="${rc.contextPath}/style/blue/othercommon/images/add.png" />
										</td>
										<td width="60">
											<a href="addTemplate.html" target="contentFrame">新增模板</a>
										</td>
										<td width="20">
											<img src="${rc.contextPath}/style/blue/othercommon/images/delete.png" />
										</td>
										<td width="60">
											<a href="#">删除模板</a>
										</td>
										<td width="20">
											<img src="${rc.contextPath}/style/blue/othercommon/images/modify.png" />
										</td>
										<td>
											<a href="#">修改模板</a>
										</td>
										<td align="right">
											<input type="text" size="15" onblur="this.className='blur'"
												onfocus="this.className='focus'" class="blur" />
											<input type="button" value="搜索" class="button"
												onmouseover="this.className='buttonhover'"
												onmouseout="this.className='button'" />
										</td>
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
												<input type="checkbox" name="chkAll" id="checkbox"
													onclick="checkAll()" />
											</th>
											<th>
												模板ID
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
										<tr>
											<td>
												<input type="checkbox" name="cbx" id="checkbox2" />
											</td>
											<td>
												01
											</td>
											<td>
												模板一
											</td>
											<td>
												正常
											</td>
											<td>
												小孙
											</td>
											<td>
												张三
											</td>
											<td>
												2011-10-12
											</td>
											<td>
												ObjectID
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" name="cbx" id="checkbox3" />
											</td>
											<td>
												02
											</td>
											<td>
												模板二
											</td>
											<td>
												正常
											</td>
											<td>
												小张
											</td>
											<td>
												李四
											</td>
											<td>
												2011-10-11
											</td>
											<td>
												ObjectID2
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" name="cbx" id="checkbox2" />
											</td>
											<td>
												03
											</td>
											<td>
												模板三
											</td>
											<td>
												正常
											</td>
											<td>
												小王
											</td>
											<td>
												王五
											</td>
											<td>
												2011-10-12
											</td>
											<td>
												ObjectID
											</td>
										</tr>
										<tr>
											<td>
												<input type="checkbox" name="cbx" id="checkbox2" />
											</td>
											<td>
												04
											</td>
											<td>
												模板四
											</td>
											<td>
												正常
											</td>
											<td>
												小李
											</td>
											<td>
												孙六
											</td>
											<td>
												2011-10-12
											</td>
											<td>
												ObjectID
											</td>
										</tr>
										<tr>
											<th colspan="8" align="right"
												style="text-align: right; padding-right: 10px; background: none; height: 22px;">
												当前： 第1页 共10页 |
												<img src="${rc.contextPath}/style/blue/othercommon/images/navLeft.gif"
													width="14" height="16" alt="第一页" />
												<img src="${rc.contextPath}/style/blue/othercommon/images/left.gif"
													width="16" height="16" alt="上一页" />
												<img src="${rc.contextPath}/style/blue/othercommon/images/right.gif"
													width="16" height="16" alt="下一页" />
												<img src="${rc.contextPath}/style/blue/othercommon/images/navRight.gif"
													width="14" height="16" alt="最后一页" />
												<input type="text" style="width: 15px;"
													onblur="this.className='blur'"
													onfocus="this.className='focus'" class="blur" />
												<input type="button" class="button" value="GO"
													onmouseover="this.className='buttonhover'"
													onmouseout="this.className='button'" />
												每页行数：
												<select name="" onblur="this.className='blur'"
													onfocus="this.className='focus'">
													<option value="20">
														20
													</option>
													<option value="40">
														40
													</option>
													<option value="60">
														60
													</option>
												</select>
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
