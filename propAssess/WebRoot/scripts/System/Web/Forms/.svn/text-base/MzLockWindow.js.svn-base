/*---------------------------------------------------------------------------*\
|  Subject:    Lock Window class
|  NameSpace:  System.Web.Forms.MzLockWindow
|  Author:     meizz
|  Created:    2006-10-22
|  Version:    2007-08-28
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/

function MzLockWindow(){System.call(this); this.initialize();}
MzLockWindow.Extends(System, "MzLockWindow").initialize=function()
{
  var div = this.element = MzLockWindow.element =document.createElement("DIV");
  div.id  = MzLockWindow.id; this.decontrol();
  
  with(div.style)
  {
    zIndex = 1;
    top = left = "0px";
    width = height = "100%";
    border = display = "none";
    margin = padding = 0;
    position = "absolute";
    backgroundColor = "#666699";
    backgroundImage = "url("+ System.resourcePath +"/blank.gif)";
  }
  if ((MzBrowser.ie    && MzBrowser.version<5.5)
    ||(MzBrowser.opera && MzBrowser.version<8.5))
  {
    div.style.backgroundColor = "";
    div.style.backgroundImage = "url("+ System.resourcePath +"/dotted.gif)";
  }
  MzLockWindow.onResize();
  document.body.insertBefore(div, document.body.firstChild);
};

System.extend(MzLockWindow,
{
  onResize : function()
  {
    MzLockWindow.element.style.width = "100%";
    MzLockWindow.element.style.height= "100%";

    setTimeout(function()
    {
      var body=MzElement.body();
      var W = body.scrollWidth;
      var H = body.scrollHeight;
      MzLockWindow.element.style.width = W +"px";
      MzLockWindow.element.style.height= H +"px";
    }, 10);
  },

  _restore : function(TagName)
  {
    var s = document.getElementsByTagName(TagName);
    for(var i=s.length-1; i>-1; i--)
    {
      s[i].style.visibility=s[i].getAttribute("att_MzLockWindow_v");
      s[i].removeAttribute("att_MzLockWindow_v");
    }
  },

  _safeguard : function(TagName)
  {
    var s = document.getElementsByTagName(TagName);
    for(var i=s.length-1; i>-1; i--)
    {
      s[i].setAttribute("att_MzLockWindow_v", s[i].style.visibility, 0);
      s[i].style.visibility = "hidden";
    }
  },

  id:"MzLockWindow_"+System.getUniqueId()+"_"+new Date().getTime().toString(36),

  lock : function(options) //options{backgroundColor,opacity,zIndex}
  {
    if(!this.instance)this.instance=new MzLockWindow();
    MzElement.show(this.id);
    window.attachEvent("onresize",this.onResize);
    var st=this.element.style;    this.onResize();
    var op=System.extend({zIndex:1, opacity:0.5}, options||{});

    st.zIndex = op.zIndex;
    st.backgroundColor=op.backgroundColor||"#666699";
    if(System.ie){st.filter = "alpha(opacity:"+Math.round(op.opacity*100)+")";}
    else
    {
      st.opacity = op.opacity;
      st.MozOpacity = op.opacity;
      st.KHTMLOpacity = op.opacity;
    }
    "SELECT,OBJECT,EMBED".split(",").forEach(this._safeguard);
  },

  unlock : function()
  {
    if(!this.instance){this.instance=new MzLockWindow(); return;}
    MzElement.hide(this.id);
    window.detachEvent("onresize", this.onResize);
    "SELECT,OBJECT,EMBED".split(",").forEach(this._restore);
  }
});