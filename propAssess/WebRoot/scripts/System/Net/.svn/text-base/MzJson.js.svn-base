/*---------------------------------------------------------------------------*\
|  Subject:    JSON (JavaScript Object Notation)
|  NameSpace:  System.Net.MzJson
|  Author:     meizz
|  Created:    2006-08-30
|  Version:    2007-02-06
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/

//op{cache parameters callback onload}
function MzJson(op)
{
  System.call(this);
  this.cache=false;
  System.extend(this, op||{});
  this.initialize();
}
MzJson.Extends(System, "MzJson");

//MzJson.script=document.createElement("SCRIPT");
//MzJson.script.type="text/javascript";

MzJson.prototype.initialize=function()
{
  //this.element=MzJson.script;
  this.element=document.createElement("SCRIPT");
  this.element.type="text/javascript";
  this.element.id = "MzJsonElement"+ this.hashCode;
}

MzJson.prototype.request=function(url)
{
  if(!(this.url=url)) return this; var me=this;
  this.element.src=this.assembleURL();
  document.getElementsByTagName("HEAD")[0].appendChild(this.element);
  this.destroyScriptElement();
};

MzJson.prototype._callback=function(json)
{
  if("function"==typeof(this.callback)) this.callback(json);
};

MzJson.prototype.assembleURL=function()
{
  var url=this.url; url += (url.indexOf("?")>0 ? "&" : "?") +"callback="+
    encodeURIComponent("Instance(\""+ this.hashCode +"\")._callback");
  if(!this.cache) url += "&_temp_="+ (new Date().getTime()).toString(36);
  var json = System.extend({}, this.parameters||{}), a=[];
  for(var i in json) a.push(i +"="+ encodeURIComponent(json[i]));
  a = a.join("&");  return url +"&"+ a;
};

MzJson.prototype.destroyScriptElement=function()
{
  var me=this;
  if(System.ie){this.element.onreadystatechange=function()
  {
    if(this.readyState=="loaded")
    {
      me.dispatchEvent(new System.Event("onload")); me.dispose();}
    }
  }
  else{this.element.onload=function()
  {
    me.dispatchEvent(new System.Event("onload")); me.dispose();}
  }
  //opera unsupport script element onload
  if(MzBrowser.opera) setTimeout(function()
  {
    MzElement.remove(me.element); me.dispose();
  }, 10000);
}

MzJson.prototype.dispose=function()
{
  delete this.url;
  //this.element=null;
  delete this.cache;
  delete this.element;
  MzElement.remove("MzJsonElement"+ this.hashCode);
}

//static class
MzJson.request=function(url, op){var j=new MzJson(op); j.request(url); return j;}