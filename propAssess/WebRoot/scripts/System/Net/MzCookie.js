/*---------------------------------------------------------------------------*\
|  Subject:    document.cookie
|  NameSpace:  System.Net.MzCookie
|  Author:     meizz
|  Created:    2004-12-07
|  Version:    2007-07-08
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/

//op{path expires domain secure}
function MzCookie(op)
{
  var now=new Date(), path=""; now.setDate(now.getDate()+3); //Save 3 days
  if(/https?:\/\/[^\/]+([^\?]+)/i.test(location.href))path=RegExp.$1;op=op||{};
  this.path = (op.path||path||"/").replace(/(\/)[^\/]*$/,"$1");
  this.expires= op.expires||now;
  this.domain = op.domain||"";
  this.secure = op.secure||"";
}
var t=MzCookie.Extends(System, "MzCookie");

//name: cookie name
//value: cookie value
t.add  = function(name, value)
{
  document.cookie =
    name + "="+ escape (value) 
    + ";expires=" + this.expires.toGMTString()
    + ";path="+ this.path
    + (this.domain == "" ? "" : ("; domain=" + this.domain))
    + (this.secure ? "; secure" : "");
};
t.set  = function(name, value){this.add(name, value)}

//name: cookie name
t.get  = function(name)
{
  var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
  if(arr=document.cookie.match(reg)) return unescape(arr[2]);
  else return null;
};

//name: cookie name
t.remove  = function(name)
{
  var now = new Date();
  now.setTime(now.getTime() - 1);
  var V = this.get(name);
  if(V!=null) document.cookie= name + "="+ V
    +";expires="+ now.toGMTString() + ";path="+ this.path;
};

t.setExpires = function(expires)
{
  if(typeof(expires)=="number") //milliseconds
  {
    var now = new Date();
    now.setTime(now.getTime() + expires);
  }
  else if(typeof(expires)=="object") //Date object
  {
    this.expires = expires;
  }
};

//[static method]
MzCookie.set=function(key,value,op){op=new MzCookie(op);op.add(key, value);op.dispose();}
MzCookie.add=function(key,value,op){MzCookie.set(key,value,op);}
MzCookie.get=function(key,op){op=new MzCookie(op);key=op.get(key);op.dispose();return key;}
MzCookie.remove=function(key,op){op=new MzCookie(op);op.remove(key);op.dispose();}
