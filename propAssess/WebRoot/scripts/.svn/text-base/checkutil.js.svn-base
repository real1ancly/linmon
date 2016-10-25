//对input的值非空的校验
function isnotnull(elementId, name){
	if(null == document.getElementById(elementId)){
		alert(" "+name+"的输入元素不存在");
		return false;
	}
	var input_value = document.getElementById(elementId).value;
	if(input_value==""){
					alert("请输入'"+name+"！");
// document.getElementById(elementId).focus();
					return false;
				}
	regexpxs=/^\s*$/;
	if(regexpxs.test(input_value)){
		alert("输入的'"+name+"全是空格，请重新输入！");
// document.getElementById(elementId).focus();
		return;
	}
	return true;
}
// 检查输入的字段不能超过255个
	function checkLength( inputField , name ){
		var inputValue = inputField.value;
		if( inputValue.length >254 ){
				alert( name +"过长，应该在255以内!")
				inputField.value= inputValue.substring( 1, 250 );
				// inputField.value="";
				inputField.focus();
				return false;
		}
		return true;
	}

// 对input中的小数的校验
function checkfloat(elementId, name){
	if(!isnotnull(elementId, name)){
		return false;
	}
	
	var input_value = document.getElementById(elementId).value
	regexpxs=/^\d+\.?\d*$/;
	if(!regexpxs.test(input_value)){
						alert("'"+name+"'必须是小数或整数！");
						document.getElementById(elementId).focus();
						return false;
					}
	return true;
}

// 对input中的正数的校验
function checkinteger(elementId, name){

	if(!isnotnull(elementId, name)){
		return false;
	}
	
	var input_value = document.getElementById(elementId).value
	regexpxs=/^\d+$/;
	if(!regexpxs.test(input_value)){
						alert("'"+name+"'必须是整数！");
						document.getElementById(elementId).focus();
						return false;
					}
	return true;
}

// 对下拉框的校验
function checklistbox(formName, elementName, showName){
						flag=false;
				 		for(i=0;i<document.forms[formName].elements[elementName].options.length;i++){
				 			if(document.forms[formName].elements[elementName].options[i].selected){
				 				flag= true;
				 				break;
				 			}
				 		}
				 		if(!flag){
					 		alert("请选择'"+showName+"'！");
							return false;
						}
	return true;
}
// 对下拉框的校验 首选项是提示信息的情况
function checklistboxIgnoreFirst(formName, elementName, showName){
						flag=false;
				 		for(i=0;i<document.forms[formName].elements[elementName].options.length;i++){
				 			if(document.forms[formName].elements[elementName].options[i].selected){
				 				flag= true;
				 				break;
				 			}
				 		}
				 		if(!flag){
					 		alert("请选择'"+showName+"'！");
							return false;
						}
	return true;
}

// 对于单选复选框的校验
function checkcheckbox(formName, elementName, showName){
						flag=false;
				 		if(document.forms[formName].elements[elementName].checked){
				 			flag=true;
				 		}else{
					 		for(i=0;i<document.forms[formName].elements[elementName].length;i++){
					 			if(document.forms[formName].elements[elementName][i].checked){
					 				flag=true;
					 				break;
					 			}
					 		}
				 		}
				 		if(!flag){
				 			alert("请选择'"+showName+"'！");
							return false;
						}
	return true;
}

function checkradio(elementId,name){	
	if(!document.getElementById(elementId).checked){
					alert("请选择'"+name+"！");
					document.getElementById(elementId).focus();
					return false;
				}
	
	return true;
}

// input输入中的日期的校验
function checktime(elementId, name){

	if(!isnotnull(elementId, name)){
		return false;
	}
	
	// regexpxs =
	// /20\d\d\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])(\s[01][0-9]|2[0-4]\:[0-5][0-9])?$/;
	regexpxs = /[20]\d\d\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])(\s[01][0-9]|2[0-4]\:[0-5][0-9])?/;
	var input_value = document.getElementById(elementId).value
	if(!regexpxs.test(input_value)){
						alert("''"+name+"'必须是日期，格式符合:yyyy-MM-dd hh:mm！");
// document.getElementById(elementId).focus();
						return false;
					}
	return true;
}

// input email的校验
function checkemail(elementId, name){

	if(!isnotnull(elementId, name)){
		return false;
	}
	
	 regexpxs = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	 var input_value = document.getElementById(elementId).value
	 if(!regexpxs.test(input_value)){
	 	alert("'"+name+"' 必须是有效的email格式");
	 	return false;
	 }
	 return true;
}

// 检查手机号码，已13或153，159开头的11位号码, 申思维增加
function checkMobile( elementId, name ){
	if( !isnotnull( elementId, name)){
		return false;
	}
	var input_value = document.getElementById( elementId ).value;
	if(input_value.length>0){
         var reg0 = /^1\d{10}$/;
         var reg1 = /^153\d{4,8}$/;
         var reg2 = /^159\d{4,8}$/;
         var reg3 = /^0\d{10,11}$/;
         var tag = false;
         if (reg0.test(input_value))tag=true;
         if (!tag){
             alert('对不起，您输入的手机号码有错误，必须是11位，并且以13或153,159开头。');
             return false;
         }
         return true;
	}else{
		alert("'"+name+"' 必须是11位，并且首数字为1");
		return false;		
	}
}


// 对input的值长度的校验
function checkStringLength(elementId, name, length){
	if(!isnotnull(elementId, name)){
		return false;
	}
	
	var input_value = document.getElementById(elementId).value
	if(input_value.length > length){
		alert("'"+name+"' 的长度应该限制在"+length+"个字符范围内");
		return false;
	}
	return true;
}

// 对input中百分比的校验
function checkPercent(elementId, name){
	if(!isnotnull(elementId, name)){
		return false;
	}
	
	regexpxs =/^\d{1,2}(\.\d)?$/;
	 var input_value = document.getElementById(elementId).value
	 if(!regexpxs.test(input_value)){
	 	alert("百分比 '"+name+"' 必须在0到100之间，小数点后可以保留一位数字");
	 	return false;
	 }
	 return true;
}

// 对input中数据为小数点前8位，后两位的校验
function checkfloatWith8dot2(elementId, name){
	if(!isnotnull(elementId, name)){
		return false;
	}
	
	regexpxs =/^\d{1,8}(\.\d{1,2})?$/;
	 var input_value = document.getElementById(elementId).value
	 if(!regexpxs.test(input_value)){
	 	alert("'"+name+"'应该小数点前最多8位，小数点后最多2位数字");
	 	return false;
	 }
	 return true;
}

// 对input中的整形数据做校验，只能在个位以内
function checkintegerwithin9(elementId, name){
	if(!isnotnull(elementId, name)){
		return false;
	}
	
	regexpxs =/^[1-9]$/;
	 var input_value = document.getElementById(elementId).value
	 if(!regexpxs.test(input_value)){
	 	alert("'"+name+"'在1到9以内");
	 	return false;
	 }
	 return true;
}

// 检查两个输入的密码是否正确
function confirmPassword(password1Id, password2Id){
	var p1=document.getElementById(password1Id).value;
	var p2=document.getElementById(password2Id).value;

	if(p1.length>0&&p1==p2){
		return true;
	}
	
	else{
		alert("两次输入的密码不匹配，或密码未输入完整");
		return false;
	}
}


// 检查6位邮编 ， 申思维增加 需要用regExp修改
function checkZipCode(elementId){
	var zipCode=document.getElementById(elementId).value;
	if(zipCode.length!=0){
		var regExp=/^\d{6}$/;
		if(regExp.test(zipCode)){
			return true;
		}else{
			alert("邮编必须是6位数字");
			// document.getElementById(elementId).focus();
			return false;
		}
	}
	return true;
}

// 检查6位邮编 ， 申思维增加 需要用regExp修改
function checkZipCodeOfSixNumber(elementId){
	var zipCode=document.getElementById(elementId).value;
	var regExp=/^\d{6}$/;
	if(regExp.test(zipCode)){
		return true;
	}else{
		alert("邮编必须是6位数字");
		// document.getElementById(elementId).focus();
		return false;
	}
}


/*
 * 检查固定电话与传真 格式要求：AAAA-BBBBBBBB-CCCCCC
 * （A部分为3-4个数字，B部分为7-8个数字，C部分为1-6位数字，允许不存在C部分） 申思维增加。
 */
function checkPhone(elementId,name){
	var tag=false;
	var phone=document.getElementById(elementId);
	if(phone.value==null||phone.value.length==0){
		if(elementId!="faxId"){
			alert("请输入"+name);
			phone.focus();
		}
		return tag;
	}else{
		// 检查字符中有几个"-"
		var hyphenExp=new RegExp("-","g");
		var hyphens= phone.value.match(hyphenExp);
		if(hyphens!=null&&hyphens.length==1){
			// 为没有分机号码的电话准备，如：010-88888888
			var regExp1=/^\d{3,4}-\d{7,8}$/;
			if(regExp1.test(phone.value)){
				tag=true;
			}
		}else if(hyphens!=null&&hyphens.length==2){
			// 为带有分机号码的电话号码准备，如 010-88888888-888888
			var regExp333=/^\d{3,4}-\d{7,8}-\d{1,6}$/;
			if(regExp333.test(phone.value)){
				tag=true;
			}
		}
	}
	if(tag==false){
		alert("您输入的"+name+"格式不对。请根据提示，输入正确的格式");
		// phone.focus();
	}
	return tag;	
}

// 检查输入的字段不能超过给定的最大长度
function checkLength( inputId , name ,maxLength){
	var inputValue = document.getElementById(inputId).value;
	if( inputValue.length >maxLength ){
		alert( name +"过长，应该在"+maxLength+"以内!")
		inputField.value= inputValue.substring( 1, maxLength );
		// inputField.focus();
		return false;
	}
	return true;
}

// 检查输入的不能有汉字
function checkNoChinese(inputId, name){
	var inputValue=document.getElementById(inputId).value;
	var regExp=/[\u4E00-\u9FA0]/;
	if(regExp.test(inputValue)){
		alert(name+"中不能有汉字");
		// inputField.focus();
		return false;
	}
	return true;
}

// 检查图片格式
function checkIsImage(inputId, name){
	var inputValue=document.getElementById(inputId).value;
	var regExp=/^.*?\.(jpg|jpeg|bmp|gif)$/;
	if(inputValue!=""&&!regExp.test(inputValue)){
		alert(name+"格式不正确！");
		return false;
	}
	return true;
}

// 检查邮箱 非必填项
function checkemailNo(elementId, name){
	if(document.getElementById(elementId).value.length!=0){
		 regexpxs = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		 var input_value = document.getElementById(elementId).value
		 if(!regexpxs.test(input_value)){
		 	alert("'"+name+"' 必须是有效的email格式");
		 	return false;
		 }
	}
	return true;
}

//检查手机号码，已13或153，159开头的11位号码, 非必填项
function checkMobileNo( elementId, name ){
	if(document.getElementById(elementId).value.length!=0){
		var input_value = document.getElementById( elementId ).value;
	 if(input_value.length>0){
	         var reg0 = /^1\d{10}$/;
	         var reg1 = /^153\d{4,8}$/;
	         var reg2 = /^159\d{4,8}$/;
	         var reg3 = /^0\d{10,11}$/;
	         var tag = false;
	         if (reg0.test(input_value))tag=true;
	         if (!tag){ 
	             alert("对不起,'"+name+"'号码有错误，必须是11位，并且以13或153,159开头。");
	             return false;
	         }
	         return true;
		}else{
			alert("'"+name+"' 必须是11位，并且首数字为1");
			return false;		
		}
	}return true;
}

/*
 * 检查固定电话与传真 格式要求：AAAA-BBBBBBBB-CCCCCC
 * （A部分为3-4个数字，B部分为7-8个数字，C部分为1-6位数字，允许不存在C部分） 申思维增加。
 */
function checkPhoneNo(elementId,name){
	var phone=document.getElementById(elementId);
	if(phone.value.length!=0){
		var tag=false;
		// 检查字符中有几个"-"
		var hyphenExp=new RegExp("-","g");
		var hyphens= phone.value.match(hyphenExp);
		if(hyphens!=null&&hyphens.length==1){
			// 为没有分机号码的电话准备，如：010-88888888
			var regExp1=/^\d{3,4}-\d{7,8}$/;
			if(regExp1.test(phone.value)){
				tag=true;
			}
		}else if(hyphens!=null&&hyphens.length==2){
			// 为带有分机号码的电话号码准备，如 010-88888888-888888
			var regExp333=/^\d{3,4}-\d{7,8}-\d{1,6}$/;
			if(regExp333.test(phone.value)){
				tag=true;
			}
		}
		if(tag==false){
			alert("您输入的"+name+"格式不对。请根据提示，输入正确的格式");
			// phone.focus();
			return false;
		}
	}
	return true;	
}

function ShowIframe(title,url,width,height,back){
    var pop=new Popup({ contentType:1,scrollType:'yes',isReloadOnClose:false,width:width,height:height});
    pop.setContent("contentUrl",url);
    pop.setContent("title",title);
    pop.setContent("callBack",back);
    pop.setContent("parameter",{id:"divCall",str:"",obj:pop});
    pop.build();
    pop.show();
}
function ifameClose(value){
	parent.window.document.getElementById('dialogBoxClose').value=value;
    parent.window.document.getElementById('diaClose').click();
}

