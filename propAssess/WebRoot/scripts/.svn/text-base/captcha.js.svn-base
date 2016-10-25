function changeImage(){   
    var imgSrc = $("#imgObj");    
    var src = imgSrc.attr("src");    
    imgSrc.attr("src",changeUrl(src));    
}

function changeUrl(url){    
    var timestamp = (new Date()).valueOf();
    //url = url.substring(0,17); 
    if((url.indexOf("&")>=0)){
        url = url + "*tamp=" + timestamp;
    }else{
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}

function isRightCode(){
    var code = $("#captcha").attr("value");
    code = "captchacode=" + code;
$.ajax({
    type:"POST",
    url:"ValidateServlet.do",
    async:false,
    data:code,
    success:callback,
    error:function(){
    }
});
}

function callback(data){
   if(data=='1'){
		$("#message_captcha").html("&nbsp;&nbsp;");
		$("#captcha").removeClass("error_input");  		 		
		$("#frmForm").submit();
		$("#frmForm").attr("disabled",true);
   }else{
   		changeImage();
		$("#message_captcha").html("&nbsp;&nbsp;<font color='red''>验证码输入错误，请重试！</font>");
		$("#captcha").addClass("error_input");
   		
   }  
   
}