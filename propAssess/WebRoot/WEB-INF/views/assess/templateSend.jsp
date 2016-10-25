<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" media="all" type="text/css" href="style/blue/othercommon/css/main.css" />
<link rel="stylesheet" media="all" type="text/css" href="style/blue/othercommon/css/dropdown.css" />
<script language="javascript">
		function getWindowHeight() {
			var windowHeight = 0;
			if (typeof(window.innerHeight) == 'number') {
				windowHeight = window.innerHeight;
				
			}
			else {
				if (document.documentElement && document.documentElement.clientHeight) {
					windowHeight = document.documentElement.clientHeight;
				}
				else {
					if (document.body && document.body.clientHeight) {
						windowHeight = document.body.clientHeight;
					}
				}
			}
			return windowHeight;
		}
		function getWindowWidth() {
			var windowWidth = 0;
			if (typeof(window.innerWidth) == 'number') {
				windowWidth = window.innerWidth;
				
			}
			else {
				if (document.documentElement && document.documentElement.clientWidth) {
					windowWidth = document.documentElement.clientWidth;
				}
				else {
					if (document.body && document.body.clientWidth) {
						windowWidth = document.body.clientWidth;
					}
				}
			}
			return windowWidth;
		}
		function setContent() {
			if (document.getElementById) {
				var windowHeight = getWindowHeight();
				var windowWidth = getWindowWidth();
				if (windowHeight > 0) {
					var contentElement = document.getElementById('content');
					var contentHeight  = contentElement.offsetHeight;
					var contentWidth  = contentElement.offsetWidth;
										
					if (windowHeight - 86 >= 0) {
						contentElement.style.position = 'relative';
						contentElement.style.height = (windowHeight - 86) + 'px';
						contentElement.style.width = (windowWidth ) + 'px';
					
					}
					else {
						contentElement.style.position = 'relative';
						contentElement.style.height = (windowHeight - 86) + 'px';
						contentElement.style.width = (windowWidth) + 'px';
						
					}
					
					
				}
			}
		}
		
		window.onload = function() {
			
			setContent();
			
		}
		window.onresize = function() {
			
			setContent();
		}

</script>
<title>市分公司考核系统</title></head>

<body>

<div class="header">
<div class="banner"><div class="banner_right"><div class="logo"></div></div></div>

<div class="chromestyle" id="chromemenu">
<ul>
<li onmouseover="this.className='menu_onfocus';" onmouseout="this.className='menu_onblur';"><a href="myWork.html">我的待办</a></li>
<li class="menu_line"> </li>
<li onmouseover="this.className='menu_onfocus';" onmouseout="this.className='menu_onblur';"><a href="templateManagement.html">模板管理</a></li>
<li class="menu_line"> </li>
<li class="menu_onfocus"><a href="templateSend.html">模板下发</a></li>
<li class="menu_line"> </li>
<li onmouseover="this.className='menu_onfocus';" onmouseout="this.className='menu_onblur';"><a href="checkManagement.html">考核对象管理</a></li>
<li class="menu_line"> </li>
<li onmouseover="this.className='menu_onfocus';" onmouseout="this.className='menu_onblur';"><a href="assessStat.do?method=checkCount">考核统计</a></li>
<li class="menu_line"> </li>
</ul>
</div>
</div>
<iframe id="content" name="content" src="frame/content_templateSend.html"  frameborder="0" ></iframe>
</body>

</html>