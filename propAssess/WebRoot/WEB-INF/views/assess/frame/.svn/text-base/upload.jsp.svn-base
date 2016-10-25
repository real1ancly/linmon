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
  var value = file.value;
  var sub = value.substring(value.length-4,value.length);
  if(value==''){
    alert("请选择文件");
    return;
  }
  if(sub=='xlsx'||sub=='.xls'){
     document.form.action="assessProxy.do?method=upload&recordsId=${recordsId}";
     document.form.submit();
  }else{
     alert("请选择excel文件");
     return;
  }
   
  
 
}

function msg(){
  if("${msg}"=='success'){
     alert('导入成功！')
     window.parent.location='/propAssess/assessProxy.do?method=view&recordsId=${recordsId}';
     return;
  }
  if("${msg}"=='noprop'){
     alert("导入失败！找不到名为'指标值'的列！")
     return;
  }
  if("${msg}"=='isnull'){
     alert('导入失败！指标值不能为空！')
     return;
  }
  if("${msg}"=='wrongprop'){
     alert('导入失败！指标数目不对！')
     return;
  }
  if("${msg}"=='wrongvalue'){
     alert('导入失败！指标值必须是数字！')
     return;
  }
  if("${msg}"=='wrongNum'){
     alert('导入失败！指标值不能为负数！')
     return;
  }
  if("${msg}"=='unknow'){
     alert("导入失败！未知错误！")
     return;
  }
  var msg = "${msg}";
  var msgs = msg.split(",");
  if(msgs[0]=='wrongScroe'){
     alert('导入失败！第'+msgs[1]+'行得分大于100，请检查指标值与公式是否有误！')
     return;
  }
  
}
</script>
<body>
<div id="minw" >
<table width="90%" align="center">
<tr> 
<td>
<form name="form" method="post" action="/propAssess/assessProxy.do?method=upload" enctype="multipart/form-data">
<table class="tableborder" width="99%">
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
<script>
msg();
</script>