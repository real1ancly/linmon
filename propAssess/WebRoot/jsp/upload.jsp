<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
function commit(){
  var file = document.getElementById("file");
  if(file.value==''){
    alert("请选择文件")
    return;
  }
    document.form.action="/propAssess/assessProxy.do?method=upload&recordsId=${recordsId}";
     document.form.submit();
  
 
}
</script>
<body>
<div id="minw">
<table width="80%" align="center">
<tr> 
<td>
<form name="form" method="post" action="/propAssess/assessProxy.do?method=upload" enctype="multipart/form-data">
<table class="tableborder">
<tr><td style="font-size:15px;">请选择要导入的文件：</td></tr>
<tr>
<td><input type="file" name="file" id="file" size="50" style="width: 220px;"/></td>
</tr>
<td><input type="button" name="com" onclick="commit()" value="提交"/>
</tr>
</table>
</form>
</td>
</table>
 <br>
</div>
</body>
</html>