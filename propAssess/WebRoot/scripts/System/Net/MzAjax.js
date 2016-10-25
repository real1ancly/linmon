/*---------------------------------------------------------------------------*\
|  Subject:    AJAX (Asynchronous JavaScript + XML)
|  NameSpace:  System.Net.MzAjax
|  Author:     meizz
|  Created:    2006-08-30
|  Version:    2007-07-11
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/

//op{method async data username password onsuccess onfailure on404 onerror}
function MzAjax(op){System.call(this);this.initialize()}
MzAjax.Extends(System, "MzAjax");

MzAjax._states=['Uninitialized','Loading','Loaded','Interactive','Complete'];
MzAjax.prototype.initialize=function(op)
{
  if(!System.supportsXmlHttp()) return;
  op=this.options=System.extend(
  {
    data:null,
    async:true,
    method:"GET",
    contentType: "application/x-www-form-urlencoded"
  },
  op||{});
  op.method = op.method.toUpperCase();

  this.xhr = new XMLHttpRequest();
  this.succeed=false;var me=this,event=new System.Event("onreadystatechange");

  this._onreadystatechange=function()
  {
    me.readyState = me.xhr.readyState;
    me.state = MzAjax._states[me.readyState];
    me.DPEvent("on"+ me.state.toLowerCase());
    me.dispatchEvent(event);
    if (me.xhr.readyState==4)
    {
      var s = me.status = me.xhr.status;
    //alert("onreadystatechange is runing!\n"+ me.state +"\n"+ s);
      if((s>=200 && s<300) || s==0 || typeof(s)=="undefined")
      {
        me.succeed = true;
        me.responseXML    = me.xhr.responseXML;
        me.responseBody   = me.xhr.responseBody;
        me.responseText   = me.xhr.responseText;
        me.responseStream = me.xhr.responseStream;
        me.DPEvent("onsuccess");
      }
      else
      {
        me.DPEvent("onfailure");
        if(s==404) me.DPEvent("on404");
        //else me.DPEvent("onerror");
      }
      me.dispose();
    }
  };
};
MzAjax.prototype.DPEvent=function(eventType)
{
  var e=new System.Event(eventType); this.dispatchEvent(e);
  if(this.options && typeof(this.options[eventType])=="function")
  this.options[eventType](e);
};

//new MzAjax({}).request(url);
MzAjax.prototype.request=function(url)
{
  if(!(this.url=url) || !System.supportsXmlHttp()) return;
  var me=this, op=this.options; if(op.method=="GET"&&(this.data||op.data))
  this.url += (this.url.indexOf("?")>-1?"&":"?")+(this.data||op.data);try{
  if(typeof(op.username)!="undefined"&&typeof(op.password)!="undefined")
  this.xhr.open(op.method, this.url, op.async, op.username, op.password);
  else me.xhr.open(op.method,me.url, op.async); //alert(op.method +", "+ me.url +", "+ op.async)
  me.xhr.onreadystatechange=me._onreadystatechange;
  if(op.method=="POST")me.xhr.setRequestHeader("Content-Type",op.contentType); //?? post data length
  me.xhr.send(me.data||op.data);//alert("mmmm = "+me.xhr.responseText)
    }catch(e){
  me.dispose(); me.message=e.message; me.DPEvent("onerror");} return me;
};

//new MzAjax({}).abort();
MzAjax.prototype.abort=function(){this.xhr.abort();};

//[extended class]
MzAjax.Request=function(url, op){new MzAjax(op).request(url);};

MzAjax.Update=function(){};
MzAjax.AutoComplete=function(){};

//[static method]
MzAjax.submit=function(form, op)
{
  op=System.extend({"method": form.method||"GET"}, op||{});
  function _push(name, value){if(!name) return;
  values[values.length]=name +"="+ encodeURIComponent(value);}

  var values=[];
  for(var i=0, n=form.elements.length; i<n; i++)
  {
    var e=form.elements[i];
    if(!e.name || e.disabled) continue;
    switch(e.tagName.toUpperCase())
    {
      case "INPUT" :
        switch(e.type.toLowerCase())
        {
          case "hidden" : case "image" : case "reset" : case "text" :
          case "submit" : _push(e.name, e.value); break;
          case "checkbox" : case "radio" :
            if(e.checked)_push(e.name, e.value); break;
        }
        break;
      case "SELECT" :
        if(e.multiple)
        {
          for(var j=0; j<e.options.length; j++)
            if(e.options[j].selected) _push(e.name, e.options[j].value);
        }
        else if(e.options.length>0)
        {
          _push(e.name, e.options[(e.selectedIndex>-1?e.selectedIndex:0)].value);
        }
        break;
      case "TEXTAREA" : _push(e.name, e.value); break;
    }
  }
  var ajax = new MzAjax(op);
  ajax.data= values.join("&")||null; values=null; //alert(ajax.data)
  return ajax.request(form.action||location.href);
};

//test url is existent
MzAjax.exist=function(url)
{
  var ajax=new MzAjax({async:false}), re=false;
  ajax.addEventListener("onsuccess",function(e){re=true;});
  ajax.request(url); ajax=null; return re;
};

MzAjax.request=function(url, op){return new MzAjax(op).request(url);};

