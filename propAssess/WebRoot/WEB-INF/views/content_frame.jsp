<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" uri="http://www.chinabidding.com/utils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>index</title>
	</head>
	<frameset cols="251,*" border="2" framespacing="2" name="ContentFrm" id="ContentFrm">
		<frame src="main.do?method=left&id=${leftId}"  name="leftFrame" scrolling="no" frameborder="0"/>
		<frame src="main.do?method=welcome&id=${leftId }" scrolling="no" name="contentFrame" frameborder="0"/>
	</frameset>
	<noframes>
		<body></body>
	</noframes>
</html>