// common.js
function tongleSelect(checkname, tongleboxname) {
    var checkAll = document.getElementsByName(checkname);
    var tonglebox = document.getElementsByName(tongleboxname)[0];
    if (tonglebox.checked) {
        for (var i = 0; i < checkAll.length; i++) {
            checkAll[i].checked = true;
        }
    } else {
        for (var i = 0; i < checkAll.length; i++) {
            checkAll[i].checked = false;
        }
    }
}
function resetTongle(checkname, tongleboxname) {
    var flag = false;
    var checkAll = document.getElementsByName(checkname);
    for (var i = 0; i < checkAll.length; i++) {
        if (!checkAll[i].checked) {
            flag = true;
            break;
        }
    }
    if (flag) {
        document.getElementsByName(tongleboxname)[0].checked = false;
    } else {
        document.getElementsByName(tongleboxname)[0].checked = true;
    }
}
// newFunction
function validateInteger(field) {
    var bValid = true;
                	// var field = form[oInteger[x][0]];
    if (field.type == "text" || field.type == "textarea" || field.type == "select-one" || field.type == "radio") {
        var value = "";
						// get field's value
        if (field.type == "select-one") {
            var si = field.selectedIndex;
            if (si >= 0) {
                value = field.options[si].value;
            }
        } else {
            value = field.value;
        }
        if (value.length > 0) {
            if (!isAllDigits(value)) {
                bValid = false;
                field.focus();
            } else {
                var iValue = parseInt(value);
                if (isNaN(iValue) || !(iValue >= -2147483648 && iValue <= 2147483647)) {
                    if (i == 0) {
                        focusField = field;
                    }
                    bValid = false;
                }
            }
        }
    }
    return bValid;
}
function isAllDigits(argvalue) {
    argvalue = argvalue.toString();
    var validChars = "0123456789";
    var startFrom = 0;
    if (argvalue.substring(0, 2) == "0x") {
        validChars = "0123456789abcdefABCDEF";
        startFrom = 2;
    } else {
        if (argvalue.charAt(0) == "0") {
            validChars = "01234567";
            startFrom = 1;
        } else {
            if (argvalue.charAt(0) == "-") {
                startFrom = 1;
            }
        }
    }
    for (var n = startFrom; n < argvalue.length; n++) {
        if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
            return false;
        }
    }
    return true;
}
function showadvs(checkname, childname, advancename) {
    temp = eval("document.forms[0]." + checkname);
    if (temp.checked == true) {
        document.getElementById(childname).style.display = "";
        document.getElementById(advancename).innerText = "关闭详细注册信息";
    } else {
        document.getElementById(childname).style.display = "none";
        document.getElementById(advancename).innerText = "显示详细注册信息";
    }
}

function showetype(type){
   if (type == 0){
     document.getElementById('0').style.display = "";
     document.getElementById('1').style.display = "none";
   } else{
     document.getElementById('1').style.display = "";
     document.getElementById('0').style.display = "none";
   }
}
function check(e)
{
	var k = window.event.keyCode;
	if (k < 46 || k > 57 ){
		alert("你输入的不是数字！")
		window.event.keyCode = 0 ;}
}
function login_tool(){
 if(location!=top.location ){
  top.location.href="login.jsp";
 }
}

var submitFlag=true;

// 添加目标人函数()
function Append()
{   
    
    submitFlag=false;
	
    MoveOption(document.all("allElement"), document.all("selectElement"));
   
    submitFlag=true;
}



// / 删除目标人函数()
function Delete()
{   submitFlag=false;
    MoveOption(document.all("selectElement"), document.all("allElement"));
    submitFlag=true;
}


// / 移动选项函数(源Select对象, 目的Select对象)
function MoveOption(oSrcSelect, oDestSelect)
{   
    var oOption, oNewOption;

	if(oSrcSelect==null || oDestSelect==null)
	{
		return;
	}
	for(var i=oSrcSelect.options.length-1; i>=0; i--)
	{
		oOption = oSrcSelect.options[i];
		if(oOption.selected)
		{
			if(contains(oDestSelect, oOption.text, oOption.value)) {
				
			} else {
				oNewOption = new Option(oOption.text, oOption.value, false, false);
				oNewOption.__handset = oOption.__handset;
				oDestSelect.options[oDestSelect.options.length] = oNewOption;
				oSrcSelect.options[i] = null;
			}
		}
	}
}

function contains(oDestSelect, text, value) {
	var oOption;
	for(var i=oDestSelect.options.length-1; i>=0; i--) {
		oOption = oDestSelect.options[i];
		if(text == oOption.text && value == oOption.value) {
			return true;
		}
	}
	return false;
}

function allSelect() // 这是当用户按下提交按钮时，对列出选择的select对象执行全选工作，让递交至的后台程序能取得相关数据
{
  List = document.forms[0].selectElement;
  for (i=0;i<List.length;i++)
  {
     List.options[i].selected = true;
  }
}

function IsMonthEquals(date1,date2)
{
 return date1.getMonth()==date2.getMonth()&&date1.getFullYear()==date2.getFullYear();
}
function IsDateEquals(date1,date2)
{
 return date1.getDate()==date2.getDate()&&IsMonthEquals(date1,date2);
}
function IsToday(date)
{
 return IsDateEquals(date,new Date());
}
function getToday(){
 var s = new Date();
 return s.getFullYear()+"-"+(s.getMonth()+1)+"-"+s.getDate();
}

function showSearch() {
    document.getElementById("serchtable").style.display = "";
}
function closeSearch() {
    document.getElementById("serchtable").style.display = "none";
}
function toCancel() {
    form1.reset();
}
function toClose() {
    window.close();
}
function retrieveURL(url) {
    req = new ActiveXObject("Microsoft.XMLHTTP");
    if (req) {
        req.open("POST", url, false);
        req.send(null);
        req.onreadystatechange = processStateChange;
    }
}

function processStateChange() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            return req.responseText;
        }
    }
}

function regInput(obj, reg, inputStr)
{
	var docSel	= document.selection.createRange()
	if (docSel.parentElement().tagName != "INPUT")	return false
	oSel = docSel.duplicate()
	oSel.text = ""
	var srcRange	= obj.createTextRange()
	oSel.setEndPoint("StartToStart", srcRange)
	var str = oSel.text + inputStr + srcRange.text.substr(oSel.text.length)
	if(!reg.test(str)) 
	  return false
    addreg=/;;/
    return (!addreg.test(str))
}

function isNumber(value) {
	var s = value;
	var gz = /^[+-]?[0-9.,，;]*$/;
	return gz.test(s);
}
function checkMoney(e)
{
	var value = e.value;
	if(!isNumber(value)){
		alert("你输入的不是数字！")
		e.focus();
	} else {
		return true;
	}
}

// 去左空格;
function ltrim(s){ 
 return s.replace( /^\s*/, ""); 
} 
// 去右空格;
function rtrim(s){ 
 return s.replace( /\s*$/, ""); 
} 
// 去左右空格;
function trim(s){ 
 return rtrim(ltrim(s)); 
}

/** 组合CHECKBOX的ID */
function joinCheckIds(tagName, strAction, actionName, params, isNewWin) {
	var ids = document.getElementsByName(tagName);
	var str = new Array();
	var len = 0;
	for (var i = 0; i < ids.length; i++) {           
       if(ids[i].checked == true) {
       		str[len] = ids[i].value;
       		len++;
       }
     }
     if(len <= 0) {
     	alert("操作中必须至少选择一项！");
     	return false;
     }
     if(strAction == 'modify' && len > 1) {
     	alert("修改操作只能选择一项！");
     	return false;
     }
	var url = actionName +".jhtml?method="+strAction+"&ids="+str.join(",")+params;
	if(isNewWin) {
		window.open(url,'_blank');
	} else {
		window.location.href=url;
	}
	return true;
}

var  highlightcolor='#eafcd5';
// 此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
// alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
// source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
// alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
function checkOneBox(ele) {
	var eles=document.getElementsByName(ele);
	var j=0; 
	for(var i=0;i<eles.length;i++){
		if(eles[i].checked) j++; 
	}
	if (j > 1 || j == 0){ 
		alert('只能选择一个进行修改'); 
		return false 
	}
	return true;
} 
function checkMoreBox(ele) {
	var eles=document.getElementsByName(ele);
	var j=0; 
	for(var i=0;i<eles.length;i++){
		if(eles[i].checked) j++; 
	}
	if (j <= 0){ 
		alert('至少要选择一个删除'); 
		return false 
	}
	return true;
}

function keyForSubmit() {
	if(window.event.keyCode == 13) {
		return true
	} else {
		return false;
	}
}
// 打开一个新窗口
function openBrWindow(theURL,dialogWidth,dialogHeight) { // v2.0
	 var returnValue=window.showModalDialog(theURL,window,'dialogHeight:'+dialogHeight+'px;dialogWidth:'+dialogWidth+'px;center:yes;resizable:on;');
	 if(returnValue!=null&& returnValue!=undefined){
		window.location=returnValue;
	 }
}

/**
 * myForm 为jquery对象，比如$('#myForm')
 * 
 * validateForm为回调函数
 */
function submitForm(myForm,validateForm,resultFlag){
     var id = document.getElementsByName('id');
     var idValue = '';
     if (id != null && id.length >0){
        idValue = document.getElementsByName('id')[0].value;
     }
     var options = {
		 beforeSubmit: function(formData, jqForm) {
		     var form = jqForm[0]; 
			  if (validateForm != null)
				 if (!validateForm(form)){
				    return false;
				 }
			 $.blockUI({ message: "<b><font color=red size=2 ><img src='images/loading.gif' style='vertical-align:middle;'/>正在提交,请稍后...</font></b>" }); 
			
		  },
		  dataType: "json",
		  timeout: 50000,
		  error: function(status,responseText){
		      alert("提交超时或网络传输、程序异常!\r\n"+responseText);
		  },
		  success: function(responseText, statusText) {
			  var flag = responseText.success;
			  if (flag){
			     if (responseText.msg != '')
			      alert("操作成功！\r\n\r\n返回信息:\r\n" + responseText.msg);
			     if (resultFlag != null){
				     if (resultFlag == 1){
				       // 保存&关闭
				       // 奇怪的问题，赋值一遍之后可以刷新，不然刷新不了
				       window.dialogArguments.location = window.dialogArguments.location;
				       window.dialogArguments.location.reload();
				       window.close();
				       
				     }else if (resultFlag == 0 ){
				       // 保存
				       if (idValue == '')
				        myForm.resetForm();
				     }else if (resultFlag == 3){
				       location.reload();
				     }
				 }
			  }else{
			     alert("操作失败！\r\n\r\n错误信息:\r\n" + responseText.msg);
			  }
		  },
		  complete :function(){$.unblockUI();}
		  // clearForm: true,
		  // resetForm: true
	 };
	 myForm.ajaxSubmit(options);
} 
/**
 * 点击form操作
 */
function batch_do(myForm,doName,entityName, action)
{
        if (!atleaseOneCheck())
        {
            alert('请至少选择一个' + entityName + '！');
            return;
        }
    if (confirm("确定要" + doName + entityName + "?"))
    {
        myForm[0].action = action;
        submitForm(myForm,null,3);
    }
}

function batch_update(myForm,doName,entityName, action, validateForm)
{
        if (!atleaseOneCheck())
        {
            alert('请至少选择一个' + entityName + '！');
            return;
        }
    if (confirm("确定要" + doName + entityName + "?"))
    {
        myForm[0].action = action;
        submitForm(myForm,validateForm,3);
    }
}
function batch_nocheck(myForm,doName,entityName, action, validateForm)
{
    if (confirm("确定要" + doName + entityName + "?"))
    {
        myForm[0].action = action;
        submitForm(myForm,validateForm,3);
    }
}

// checkbox中至少有一项被选中
function atleaseOneCheck()
{
    var items = document.getElementsByName('chkIds');
    if (items.length > 0) {
        for (var i = 0; i < items.length; i++)
        {
            if (items[i].checked == true)
            {
                return true;
            }
        }
    } else {
        if (items.checked == true) {
            return true;
        }
    }
    return false;
}

/*
 * 全选
 */
function checkAll(objAll)
{
	var isok=objAll.checked;
	
	if(document.all("chkIds")!='[object]')
	{
		return ;
	}
	
	var len=document.all("chkIds").length;
	// alert(len);
	if (len == undefined){
	  if (!document.all("chkIds").disabled)
	   document.all("chkIds").checked=isok;	
	  return; 
	}
	var objCheck=document.all("chkIds");
	for(var i=0;i<len;i++)
	{
	   if (!objCheck[i].disabled)
		 objCheck[i].checked=isok;
	}
}
//查询搜索
function doSearch() {
	var searchTxt = $("#searchTxt").val();
	if (searchTxt == "") {
		return;
	} else {
		searchTxt = encodeURI(searchTxt);
		location.href = "ask.do?method=search&searchTxt=" + searchTxt;
	}
}

function SetWinHeight(obj) { 
	var win=obj; 
	if (document.getElementById) { 
	if (win && !window.opera) {
		if (win.contentDocument && win.contentDocument.body.offsetHeight) 
		win.height = win.contentDocument.body.offsetHeight; 
		else if(win.Document && win.Document.body.scrollHeight) 
		win.height = win.Document.body.scrollHeight; } } 
	} 
	
function openWindowReturn(theURL,dialogWidth,dialogHeight) { // v2.0
	 var returnValue=window.showModalDialog(theURL,window,'dialogHeight:'+dialogHeight+'px;dialogWidth:'+dialogWidth+'px;center:yes;resizable:on;');
	 if(returnValue!=null&& returnValue!=undefined){
		return returnValue;
	 }
}
