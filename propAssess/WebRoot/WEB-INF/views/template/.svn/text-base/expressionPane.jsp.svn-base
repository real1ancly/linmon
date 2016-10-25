<!--propAssess\WebRoot\WEB-INF\views\template\content.jsp  -->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="menu" uri="http://struts-menu.sf.net/tag"%>
<%@ taglib prefix="menu-el" uri="http://struts-menu.sf.net/tag-el"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${rc.contextPath}/style/blue/othercommon/css/main.css" rel="stylesheet" type="text/css" />
<title>无标题文档</title>
<script src="${rc.contextPath}/js/main.js"></script>
<script src="${rc.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/jsframework.js"></script>
<script type="text/javascript">
function add(value){
   var expression = document.getElementById("expression");
     var p = getPosition(expression);
     var aa = expression.value;
     var sc = aa.substring(p-1,p);
	 var sd = aa.substring(p,p+1);
 if(p==aa.length){
    if(value=='c'){
       if(sc!='$'&&sc!='v'&&sc!='a'&&sc!='r'){
	     expression.value = aa.substring(0,aa.length-1);
       }else{
             alert('此字符不可删除');
       }
    }else{
	  if(value=='-/+'){
	    var num = '';
	    var sb = '';
	    if(sc!='r'&&!(sc==')'&&aa.substring(p-2,p-1)=='r')){
		    for(var i=aa.length;i>0;i--){
		      sb = aa.substring(i-1,i)
		      if(sb!='+'&&sb!='-'&&sb!='*'&&sb!='/'&&sb!='%'&&sb!='('&&sb!='r'){
		          num = sb+num;
		      }else{
		          if(sb=='-'&&aa.substring(i-2,i-1)=='('){
		              num = "(-"+num;
		          }else{
		               break;
		          }
		      }
		    }
		    
		       if(num.substring(1,2)=='-'){
		         expression.value = aa.substring(0,aa.length-num.length)+num.substring(2,num.length-1);
		       }else{
		         expression.value = aa.substring(0,aa.length-num.length)+"(-"+num+")";
		       }  
		       
	    }else{
	        var isM = 0;
	        for(var i=aa.length;i>0;i--){
		      sb = aa.substring(i-1,i)
		      if(sb=='-'&&aa.substring(i-2,i-1)=='('){
		          isM = 1;
		          num = i;
		          break;
		      }else{
		          if(sb=='$'){
		            num = i;
		          }
		      }
		  }
		  if(isM==1){
		     expression.value = aa.substring(0,num-2)+'$var'+aa.substring(num+5,aa.length);
		  }else{
		     expression.value = aa.substring(0,num-1)+'(-$var)'+aa.substring(num+3,aa.length);
		  }
		  
	    
	    }      
	  }else{
	    expression.value += value;
	  }
   }
 
 }else{
   if(value=='c'){
       if(p>0){
          if(sc!='$'&&sc!='v'&&sc!='a'&&sc!='r'){
             expression.value = aa.substring(0,p-1)+aa.substring(p,aa.length);
             p = p-1;
          }else{
             alert('此字符不可删除');
          }
        }
    }else{
	  if(value=='-/+'){
	    var n = '';
	    var m = '';
	    var num = '';
	    var sb = '';
	    var se = aa.substring(p-2,p-1);
	    if(sc!='$'&&sc!='v'&&sc!='a'&&sc!='r'&&sd!='$'&&sd!='v'&&sd!='a'&&sd!='r'&&(sc==')'&&se!='r')){
	      for(var i=p;i>=0;i--){
		      sb = aa.substring(i-1,i);
		      if(sb!='+'&&sb!='-'&&sb!='*'&&sb!='/'&&sb!='%'&&sb!='('&&sb!='r'&&sb!=''){
		          num = sb+num;
		      }else{
		          if(sb=='-'&&aa.substring(i-2,i-1)=='('){
		              num = "(-"+num;
		          }else{
		               n = i
		               break;
		          }
		      }
		    }
		    for(var i=p;i>=0;i++){
		      sb = aa.substring(i,i+1)
		      if(sb!='+'&&sb!='-'&&sb!='*'&&sb!='/'&&sb!='%'&&sb!=')'&&sb!='$'&&sb!=''){
		          num = num+sb;
		      }else{
		         if(sb=='-'&&aa.substring(i-2,i-1)!='('){
		             num = '('+sb;
		             continue;
		         }
		         if(!(sb==''&&aa.substring(i-2,i-1)!='')){
		            m = i;
			         break;
		         }
		      }
		    }
		       if(num.substring(1,2)=='-'){
		         if(num.substring(num.length-1,num.length)==')'){
		            expression.value = aa.substring(0,n-1)+num.substring(2,num.length-1)+aa.substring(m,aa.length);
		            p = n+1;
		         }else{
		            expression.value = aa.substring(0,n-1)+num.substring(2,num.length)+aa.substring(m+1,aa.length);
		             p = n+1;
		         }
		         
		       }else{
		         expression.value = aa.substring(0,n)+"(-"+num+")"+aa.substring(m,aa.length);
		           p = n+5;
		       }     
		      
	   }else{
	       var isM = 0;
	      for(var i=p;i>=0;i--){
		      sb = aa.substring(i-1,i)
		      if(sb=='-'&&aa.substring(i-2,i-1)=='('){
		          isM = 1;
		          n = i;
		          break;
		      }else{
		          if(sb=='$'){
		            n = i;
		          }
		          
		      }
		  }
		  if(n==''){
		    for(var i=p;i>aa.length;i++){
		      sb = aa.substring(i-1,i)
		      if(sb=='('&&aa.substring(i,i+1)=='-'){
		          isM = 1;
		          n = i;
		          break;
		      }else{
		          if(sb=='$'){
		            n = i;
		          }
		      }
		    }
		  }
		  if(n==''){
		     n=p+1;
		  }
		   alert(n)
		  if(isM==1){
		     expression.value = aa.substring(0,n-2)+'$var'+aa.substring(n+5,aa.length);
		     p = n+2;
		  }else{
		     expression.value = aa.substring(0,n-1)+'(-$var)'+aa.substring(n+3,aa.length);
		     p = n+6;
		  }
		  
		  
	   
	   } 
	  }else{
	    if(sc!='$'&&sc!='v'&&sc!='a'&&sd!='v'&&sd!='a'&&sd!='r'){
	       expression.value = aa.substring(0,p)+value+aa.substring(p,aa.length);
	        p = p+1;
	    }else{
	       alert('此处不可输入');
	    }
	    
	  }
   }
   setPosition(expression,p);
 }
   
}
function init(){
   var expression = document.getElementById("expression");
   setPosition(expression,expression.value.length);
}
function setPosition(expression,p){
  var r =expression.createTextRange(); 
   r.moveStart('character',p); 
   r.collapse(true); 
   r.select(); 
}
function getPosition(expression){
   var result = 0;
   expression.focus();   
   var s=document.selection.createRange();      
   s.setEndPoint("StartToStart",expression.createTextRange())  
   result = s.text.length ;
   return result;
}

function submitE(){
  var expression = document.getElementById("expression");
  var reg = new RegExp("[1-9.]");
  var es = expression.value.split("$var");
  var before = es[0].substring(es[0].length-1);
  var after =  es[1].substring(0,1);
  alert(before+":"+reg.test(before))
  alert(after+":"+reg.test(after))
  if(reg.test(before)||reg.test(after)||before==')'||after=='('){
     alert("表达式输入有误，请重新输入");
     return ;
  }
  var et = expression.value.split("(");
  var em = expression.value.split(")");
  if(et.length!=em.length){
     alert("表达式缺少半边括号，请重新输入");
      return ;
  }
  if(window.parent.submitForm(expression.value)){
      window.parent.form.close(); 
  }
}

function resetE(){
   var expression = document.getElementById("expression");
   expression.value = "$var";
}


</script>
</head>
<body>
<div class="content" style="text-align:center;BORDER:0px;" >
<div style="height:100%;width:90%;padding:0px;">
  <div class="contentList">
    <table cellpadding="0" cellspacing="0" border="0" width="99%" style="background-color:#f9f9f9;">
      <tr>
        <td>
        <form name="form1" action="#" method="post">
	       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableborder1"  id="tab">
               <tr><td colspan="5" style="font-weight:bold;height:40px;font-size:12px;">得分计算公式:<input type="text"  name="expression" id="expression" value="${expression}" readonly="readonly"></td></tr>
               <tr>
                  <td>
                    <input style="width:40px" type="button" value="1" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="2" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="3" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="+" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="c" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
               </tr>
                <tr>
                  <td>
                    <input style="width:40px" type="button" value="4" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="5" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="6" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="-" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                   <td>
                    <input style="width:40px" type="button" value="(" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  
               </tr>
                <tr>
                  <td>
                    <input style="width:40px" type="button" value="7" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="8" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="9" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="*" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                   <td>
                    <input style="width:40px" type="button" value=")" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
               </tr>
                <tr>
                  <td>
                    <input style="width:40px" type="button" value="0" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="." class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="-/+" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                  <td>
                    <input style="width:40px" type="button" value="/" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
                   <td>
                    <input style="width:40px" type="button" value="%" class="button" onclick="add(this.value)" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                  </td>
               </tr>
           </table>
           <table colspan="5" align="right" width="100%" border="0" cellpadding="0" cellspacing="0"  id="tab">
              <tr>
                <td colspan="4" align="right"  style="height:30px;">
                  <input type="button" value="确定" class="button" onclick="submitE()" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                </td>
                <td>
                   <input type="button" value="重置" class="button" onclick="resetE()" onmouseover="this.value.className='buttonhover'"   onmouseout="this.value.className='button'"/>
                </td>
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
<script type="text/javascript">
init();
</script>