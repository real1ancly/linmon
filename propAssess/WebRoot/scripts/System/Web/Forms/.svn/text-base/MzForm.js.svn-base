/*---------------------------------------------------------------------------*\
|  Subject:    MzForm class
|  NameSpace:  System.Web.Forms.MzForm
|  Author:     meizz
|  Created:    2007-01-04
|  Version:    2007-05-23
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/
//Using("System.Web.Forms.MzBehavior");

//op{contentType title width height scroll resizable help}
function MzForm()
{
  System.call(this);
  this.drag = true;
  this.help = false;
  this.icon = System.resourcePath +"/MzForm/icon.gif";
  this.width = "";
  this.title = "MzForm";
  this.height = "";
  this.autofit = false;
  this.content = "&nbsp;";
  this.overflow = "visible";
  this.position = "center"; //length|percentage|alignment
  this.titlebar = true;
  this.scrolling = "auto";
  this.buttonbar = false;
  this.statusbar = false;
  this.resizable = false;
  this.controlbar = true;
  this.buttonClose = true;
  this.contentType = "HTMLString";
  this.buttonAccept = true;
  this.buttonCancel = true;
  this.buttonMinimize = false;
  this.buttonMaximize = false;
  this.id = "MzForm";
}
var t=MzForm.Extends(System, "MzForm");

t.open=function(content, op)
{
  if("undefined"!=typeof(content)) this.content=content;
  if("object"==typeof(op)) System.extend(this, op);
  //if(!isNaN(parseInt(this.width))) this.width = Math.max(parseInt(this.width), 150);
  if("number"==typeof(this.width)) this.width += "px";
  if("number"==typeof(this.height)) this.height+="px";
  var me=this, w=this.mzform=MzFormWindow.provider();

  w.onaccept=function(){me.accept();};
  w.oncancel=function(){me.cancel()};
  w.onclose=function(){me.close()};
  w.onmaximize=function(){me.maximize()};
  w.onminimize=function(){me.minimize()};
  w.onhelp=function(){me.help();};

  w.show(this); w.setActive();
  this.dispatchEvent(new System.Event("onopen"));
  return this;
};
t.setContent=function(content){this.mzform.setContent(content)};
t.setCaption=function(caption){this.mzform.setCaption(caption)};
t.setWidth=function(w){this.mzform.setWidth(w)};
t.setHeight=function(h){this.mzform.setHeight(h)};

t.close=function()
{
  var me=this, e=new System.Event("onclose");
  me.dispatchEvent(e); if(!e.returnValue)return;
  this.mzform.hide(function(){me.dispose();});
}
t.accept=function()
{
  var e=new System.Event("onaccept"); this.dispatchEvent(e);
  if(!e.returnValue) return; this.close();
};
t.cancel=function()
{
  var e=new System.Event("oncancel"); this.dispatchEvent(e);
  if(!e.returnValue) return; this.close();
};
t.maximize=function(){this.dispatchEvent(new System.Event("onmaximize"));};
t.minimize=function(){this.dispatchEvent(new System.Event("onminimize"));};
t.help=function(){this.dispatchEvent(new System.Event("onhelp"));};


MzForm.open=function(content, op){return new MzForm().open(content, op);};












//20070514
function MzFormWindow()
{
  System.call(this);
  this.active=false;
  this.minimal=false;
  this.maximal=false;
  this.resizable=true;
  MzFormWindow.windows.push(this);
}
var t=MzFormWindow.Extends(System, "MzFormWindow");
function $(id){return Mz$(id);}
t.setActive=function()
{
  var w=MzFormWindow.currentWindow; if(w==this)return; if(w){
  with($("MzForm_"+ w.hashCode).style)zIndex=parseInt(zIndex)-4000;
  MzElement.removeClassName("MzForm_"+w.hashCode, "focused");}
  MzElement.addClassName("MzForm_"+this.hashCode, "focused");
  MzFormWindow.currentWindow=this;
  with($("MzForm_"+ this.hashCode).style)zIndex=parseInt(zIndex)+4000;
};

System.loadCssFile(System.resourcePath +"/MzForm.css");
MzFormWindow.windows=[];
MzFormWindow.currentWindow=null;

MzFormWindow.png=function()
{
  //return false;
  if(System.ie && MzBrowser.version>=7) return true;
  else if(!System.ie) return true; return false;
}

t.create=function()
{
  var s = [];
  s.push('<div id="MzForm_{0}" class="MzForm" style="position:absolute; z-index:'+ (System.zIndexBase.MzForm+MzFormWindow.windows.length) +';display:none" ');
  s.push(' onclick="Instance(\'{0}\').click(event)"');
  s.push(' onmousedown="Instance(\'{0}\').mousedown(event)"');
  s.push(' onmousemove="Instance(\'{0}\').mousemove(event)"');
  s.push(' onmouseup="">');
  s.push('<div class="InnerMzForm'+ (MzFormWindow.png()?" png":"") +'">');
  s.push('  <div id="MzFormLayer_{0}" class="MzFormLayer">');
  s.push('    <table id="MzFormLayerTable_{0}" class="MzFormLayerTable" border="0" cellpadding="0" cellspacing="0">');
  s.push('      <tr>');
  s.push('        <td id="MzFormCaption_{0}" class="MzFormCaption" onselectstart="return false">');
  s.push('          <div id="MzFormControlBar_{0}" class="MzFormControlBar">');
  s.push('            <a id="MzFormButtonMinimize_{0}" class="Minimize" href="#" onclick="Instance(\'{0}\').onminimize(); return false;"><img alt="minimize" src="{1}/blank.gif" /></a><a');
  s.push('             id="MzFormButtonMaximize_{0}" class="Maximize" href="#" onclick="Instance(\'{0}\').onmaximize(); return false;"><img alt="maximize" src="{1}/blank.gif" /></a><a');
  s.push('             id="MzFormButtonClose_{0}" class="Close" href="#" onclick="Instance(\'{0}\').onclose(); return false;"><img alt="close" src="{1}/blank.gif" /></a>');
  s.push('          </div>');
  s.push('          <div id="MzFormCaptionText_{0}" onmousedown="Instance(\'{0}\').setActive()" class="MzFormCaptionText">MzForm</div>');
  s.push('        </td>');
  s.push('      </tr>');
  s.push('      <tr>');
  s.push('        <td>');
  s.push('          <div id="MzFormContent_{0}" class="MzFormContent">&nbsp;</div>');
  s.push('        </td>');
  s.push('      </tr>');
  s.push('      <tr id="MzFormButtonBar_{0}" class="MzFormButtonBar">');
  s.push('        <td>');
  s.push('          <input id="MzFormButtonAccept_{0}" type="button" value="\u786e \u5b9a" onclick="Instance(\'{0}\').onaccept()" class="accept" />');
  s.push('          <input id="MzFormButtonCancel_{0}" type="button" value="\u53d6 \u6d88" onclick="Instance(\'{0}\').oncancel()" class="cancel" />');
  s.push('        </td>');
  s.push('      </tr>');
  s.push('      <tr id="MzFormStatusBar_{0}" class="MzFormStatusBar">');
  s.push('        <td>');
  s.push('          <div id="MzFormStatusText_{0}">StatusBar</div>');
  s.push('        </td>');
  s.push('      </tr>');
  s.push('    </table>');
  s.push('  </div>');

  s.push('  <table id="MzFormBgLayer_{0}" class="BgLayer" border="0" cellspacing="0" cellpadding="0" onselectstart="return false">');
  s.push('    <tr class="top">');
  s.push('      <td class="corner left">&nbsp;</td>');
  s.push('      <td class="vertical center">&nbsp;</td>');
  s.push('      <td class="corner right">&nbsp;</td>');
  s.push('    </tr>');
  s.push('    <tr class="middle">');
  s.push('      <td class="horizontal left">&nbsp;</td>');
  s.push('      <td class="midland center"><div class="normal">&nbsp;</div><div class="focused">&nbsp;</div></td>');
  s.push('      <td class="horizontal right">&nbsp;</td>');
  s.push('    </tr>');
  s.push('    <tr class="bottom">');
  s.push('      <td class="corner left">&nbsp;</td>');
  s.push('      <td class="vertical center">&nbsp;</td>');
  s.push('      <td class="corner right">&nbsp;</td>');
  s.push('    </tr>');
  s.push('  </table>');
  s.push('</div></div>');
  s = s.join("").format(this.hashCode, System.resourcePath);

  if(document.body) document.body.insertAdjacentHTML("afterBegin", s); else document.write(s);
  this.dragger=MzBehavior.drag("MzForm_"+ this.hashCode,{"trigger": "MzFormCaptionText_"+ this.hashCode});
};new MzFormWindow().create(); var t=MzFormWindow.prototype;
var s='<div id="MzFormWindowResizeLayer" style="display:none; z-index:'+ System.zIndexBase.dragLayer
     +'; position:absolute; border:2px dotted #999999">&nbsp;</div>';
if(document.body) document.body.insertAdjacentHTML("afterBegin", s); else document.write(s);


t.show=function(op)
{
  var me=this; function _(id){return $(id +"_"+ me.hashCode)}
  //set property
  _("MzFormCaption").style.backgroundImage="url("+ op.icon +")";
  _("MzFormCaptionText").innerHTML=op.title;
  _("MzFormCaption").style.display = op.titlebar ? "" : "none";
  _("MzFormStatusBar").style.display = op.statusbar ? "" : "none";
  _("MzFormControlBar").style.display = op.controlbar ? "" : "none";
  _("MzFormButtonBar").style.display = op.buttonbar ? "" : "none";
  _("MzFormButtonAccept").style.display = op.buttonAccept ? "" : "none";
  _("MzFormButtonClose").style.display = op.buttonClose ? "" : "none";
  _("MzFormButtonCancel").style.display = op.buttonCancel ? "" : "none";
  _("MzFormButtonMaximize").style.display = op.buttonMaximize ? "" : "none";
  _("MzFormButtonMinimize").style.display = op.buttonMinimize ? "" : "none";
  with(_("MzFormContent").style){width=op.width; height=op.height; overflow=op.overflow;}
  System.disabledList[this.dragger.hashCode]=!op.drag;

  if(op.contentType.toLowerCase()=="htmlelement" && !$(op.content)) op.contentType="HTMLString";
  switch(op.contentType.toLowerCase())
  {
    case "htmlstring" : _("MzFormContent").innerHTML=op.content; break;
    case "htmlelement":
      var e=$(op.content); this.opContentDisplay=e.style.display;
      e.insertAdjacentHTML("beforeBegin", "<input type='button' id='MzFormWindowInset_"+
        this.hashCode +"' style='width:"+ e.offsetWidth +"px; height:"+ e.offsetHeight
        +"px; padding:0; margin:0; border:none; visibility:hidden' />");
      _("MzFormContent").innerHTML=""; e.style.display="";
      _("MzFormContent").appendChild(e); break;
    default :
	
      _("MzFormContent").innerHTML="<iframe frameborder='0' allowTransparency='true' scrolling='"+
       op.scrolling +"' name='"+op.id+"'  style='width:100%; height:100%' src='"+ op.content +"'></iframe>";
      break;
  }

  //position 20070613
  var a = op.position.trim().toLowerCase().split(/\s/);
  var body=MzElement.body(); MzElement.show("MzForm_"+ this.hashCode);
  if(System.ie && _("MzFormContent").offsetWidth<136) _("MzFormContent").style.width = "130px";
  if(System.ie && _("MzFormContent").offsetHeight<50) _("MzFormContent").style.height = "50px";
  var top =(Math.max(parseInt((body.clientHeight-_("MzForm").offsetHeight)/2), 0)+body.scrollTop) +"px";
  var left=(Math.max(parseInt((body.clientWidth-_("MzForm").offsetWidth)/2), 0) +body.scrollLeft) +"px";
  if(a.length==1)
  {
    if(a[0]==""||a[0]=="center"){}
    else if(a[0]=="top") top = body.scrollTop +"px";
    else if(a[0]=="bottom") top = (body.scrollTop + body.clientHeight - _("MzForm").offsetHeight) +"px";
    else if(a[0]=="left") left = body.scrollLeft +"px";
    else if(a[0]=="right") left = (body.scrollLeft + body.clientWidth - _("MzForm").offsetWidth) +"px";
    else if(/\d+%/.test(a[0])) top=a[0];
    else if(/(\d+)(cm|mm|in|pt|pc|px|em|ex)?/.test(a[0])) top = parseInt(RegExp.$1) + RegExp.$2||"px";
  }
  else if(a.length>1)
  {
    if(/\d+%/.test(a[0])) top=a[0];
    else if(/(\d+)(cm|mm|in|pt|pc|px|em|ex)?/.test(a[0])) top = parseInt(RegExp.$1) + RegExp.$2||"px";

    if(/\d+%/.test(a[1])) left=a[1];
    else if(/(\d+)(cm|mm|in|pt|pc|px|em|ex)?/.test(a[1])) left= parseInt(RegExp.$1) + RegExp.$2||"px";

    if(a[0]=="top"||a[1]=="top") top = body.scrollTop +"px";
    if(a[0]=="bottom"||a[1]=="bottom")top = (body.scrollTop + body.clientHeight - _("MzForm").offsetHeight)+"px";
    if(a[0]=="left" ||a[1]=="left") left = body.scrollLeft +"px";
    if(a[0]=="right"||a[1]=="right") left = (body.scrollLeft + body.clientWidth - _("MzForm").offsetWidth) +"px";
  }
  _("MzForm").style.top = top;  _("MzForm").style.left= left;

  var op = {"duration":500};
  if(!System.ie)
  {
    MzEffect.mask("MzForm_"+ this.hashCode,
    {
       trend:true
      ,opacity:true
      ,duration:360
      ,direction:"box"
    });
  }
  else MzEffect.expand("MzForm_"+ this.hashCode, {"duration":500});
  this.resizeBy(); this.active=true;
  //alert(_("MzForm").outerHTML);
};

t.hide=function(callback)
{
  var me=this, op=
  {
     "duration":500
    ,"onafterfinish":function()
    {
      me.active=false;
      if($("MzFormWindowInset_"+ me.hashCode))
      {
        var e=$("MzFormWindowInset_"+ me.hashCode);
        var t=$("MzFormContent_"+me.hashCode).childNodes[0];
        e.parentNode.insertBefore(t,e);
        e.parentNode.removeChild(e);
        t.style.display=me.opContentDisplay;
      }
      if("function"==typeof(callback)) callback(me);
      setTimeout(function()
      {
        me.setContent("&nbsp;");
        var e=$("MzFormContent_"+ me.hashCode);
        if(e){e.style.width=e.style.height=e.style.overflow="";}
      }, 50);
    }
  };clearTimeout(this.timer);
  if(!System.ie)
  {
    op.trend=false;
    op.opacity=true;
    op.direction="box";
    MzEffect.mask("MzForm_"+ this.hashCode, op);
  }
  else MzEffect.collapse("MzForm_"+ this.hashCode, op);
};

t.dispose=function()
{
  MzFormWindow.windows=MzFormWindow.windows.remove(this);
  this.dragger.restore();
  System.prototype.dispose.call(this);
};

t.setWidth=function(w){var e; if(e=$("MzFormContent_"+this.hashCode)){e.style.width =w;}};
t.setHeight=function(h){var e;if(e=$("MzFormContent_"+this.hashCode)){e.style.height=h;}};
t.setCaption=function(s){$("MzFormCaptionText_"+ this.hashCode).value=s;}
t.setContent=function(s){$("MzFormContent_"+ this.hashCode).innerHTML=s;}
t.setStatus =function(s){$("MzFormStatusText_"+this.hashCode).innerHTML=s;}
t.resizeBy=function()
{
  var me=this, layer="MzFormLayer_"+ this.hashCode;
  $("MzFormBgLayer_"+ this.hashCode).style.width = $(layer).offsetWidth +"px";
  $("MzFormBgLayer_"+ this.hashCode).style.height= $(layer).offsetHeight+"px";
  if(System.ie && $("MzFormBgLayer_"+ this.hashCode))
  {
    if(MzBrowser.version<5.5)
    {
      $("MzFormLayer_"+ this.hashCode).style.width="130px";
      $("MzForm_"+ this.hashCode).style.width=$("MzFormLayer_"+ this.hashCode).offsetWidth +"px";
    }
    var table=$("MzFormBgLayer_"+ this.hashCode);
    if(table.rows[0].cells[0].currentStyle)
    {
      var h1=parseInt(table.rows[0].cells[0].currentStyle.height);
      var h2=parseInt(table.rows[2].cells[0].currentStyle.height);
      if(!isNaN(h1) && !isNaN(h2))
      {
        table.rows[1].cells[1].style.height=(Math.max($(layer).offsetHeight-h1-h2, 12))+"px";
      }
    }
  }
  if(window.opera && $("MzFormLayerTable_"+ this.hashCode).offsetWidth<130)
    $("MzFormLayerTable_"+ this.hashCode).style.width="130px";
  this.timer=setTimeout(function(){me.resizeBy();}, 50);
};
MzFormWindow.provider=function()
{
  for(var i=0, n=MzFormWindow.windows.length; i<n; i++)
  {
    if(!MzFormWindow.windows[i].active)
    {
      if(i==(n-1)) setTimeout(function(){new MzFormWindow().create();}, 20);
      return MzFormWindow.windows[i];
    }
  }
  return null;
};

t.click=function(e){(window.event||e).cancelBubble=true;this.setActive();};
t.mousedown=function(e)
{
  e=window.event||e;
  if(this.resizable && this.readyResize)
  {
    var obj=$("MzForm_"+ this.hashCode); if(!obj) return;
    var body=MzElement.body();
    MzFormWindow.pageX = (e.pageX || e.clientX) + body.scrollLeft;
    MzFormWindow.pageY = (e.pageY || e.clientY) + body.scrollTop;
    var layer = Mz$("MzFormWindowResizeLayer");
    layer.style.top =MzFormWindow.originalTop +"px";
    layer.style.left=MzFormWindow.originalLeft+"px";
    layer.style.width =MzFormWindow.offsetWidth +"px";
    layer.style.height=MzFormWindow.offsetHeight+"px";
    layer.style.cursor=obj.style.cursor;
    MzElement.show(layer);
  }
};
t.mousemove=function(e)
{
  if(!this.resizable) return; e=window.event||e;
  var obj=$("MzForm_"+ this.hashCode); if(!obj) return;
  var xy =MzElement.realOffset(obj), body=MzElement.body();
  var x = (e.pageX || e.clientX) + body.scrollLeft;
  var y = (e.pageY || e.clientY) + body.scrollTop;
  var offset=4

  if(y>=xy.y && y<=xy.y+offset)
  {
    if(x>=xy.x && x<=xy.x+offset)
    {
      obj.style.cursor = "nw-resize";
    }
    else if(x>=xy.x+obj.offsetWidth-offset && x<=xy.x+obj.offsetWidth)
    {
      obj.style.cursor = "ne-resize";
    }
    else
    {
      obj.style.cursor = "n-resize";
    }
  }
  else if(y>=xy.y+obj.offsetHeight-offset && y<=xy.y+obj.offsetHeight)
  {
    if(x>=xy.x && x<=xy.x+offset)
    {
      obj.style.cursor = "sw-resize";
    }
    else if(x>=xy.x+obj.offsetWidth-offset && x<=xy.x+obj.offsetWidth)
    {
      obj.style.cursor = "se-resize";
    }
    else
    {
      obj.style.cursor = "s-resize";
    }
  }
  else
  {
    if(x>=xy.x && x<=xy.x+offset)
    {
      obj.style.cursor = "w-resize";
    }
    else if(x>=xy.x+obj.offsetWidth-offset && x<=xy.x+obj.offsetWidth)
    {
      obj.style.cursor = "e-resize";
    }
    else
    {
      obj.style.cursor = "default";
    }
  }
  MzFormWindow.offsetWidth = obj.offsetWidth;
  MzFormWindow.offsetHeight= obj.offsetHeight;
  MzFormWindow.originalTop = parseInt(obj.style.top ||0);
  MzFormWindow.originalLeft= parseInt(obj.style.left||0);
  this.readyResize=obj.style.cursor!="default";
};
document.attachEvent("onmousemove", function(e)
{
  var w=MzFormWindow.currentWindow;
  if(w && w.resizable && w.readyResize)
  {
    var obj=$("MzForm_"+ w.hashCode);
    var layer=Mz$("MzFormWindowResizeLayer");
    e=window.event||e; if(!obj || layer.style.display=="none") return;
    var xy =MzElement.realOffset(obj), body=MzElement.body();
    var x = (e.pageX || e.clientX) + body.scrollLeft;
    var y = (e.pageY || e.clientY) + body.scrollTop;
    switch(obj.style.cursor.toLowerCase())
    {
      case "" :
        break;
      case "n-resize" : //top
        var n = Math.abs(y - MzFormWindow.pageY);
        layer.style.height = (MzFormWindow.offsetHeight + n) +"px";
        layer.style.top  = (MzFormWindow.originalTop - n) +"px";
        break;
      case "" :
        break;
      case "w-resize" : //left
        var n = Math.abs(x - MzFormWindow.pageX);
        layer.style.width = (MzFormWindow.offsetWidth + n) +"px";
        layer.style.left  = (MzFormWindow.originalLeft - n) +"px";
        break;
      case "e-resize" : //right
        var n = Math.abs(x - MzFormWindow.pageX);
        layer.style.width = (MzFormWindow.offsetWidth + n) +"px";
        break;
      case "" :
        break;
      case "s-resize" : //bottom
        var n = Math.abs(y - MzFormWindow.pageY);
        window.status = n
        layer.style.height = (MzFormWindow.offsetHeight + n) +"px";
        break;
      case "" :
        break;
    }
  }
});
document.attachEvent("onmouseup", function(e)
{
  var w=MzFormWindow.currentWindow;
  if(w && w.resizable && w.readyResize)
  {
    MzElement.hide("MzFormWindowResizeLayer");
    w.readyResize=false;
  }
});
