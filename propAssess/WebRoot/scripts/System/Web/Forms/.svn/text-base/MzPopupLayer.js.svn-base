/*---------------------------------------------------------------------------*\
|  Subject:    popup layer  cross browser(IE5.0+ Firefox NS Moz Opera)
|  NameSpace:  System.Web.Forms.MzPopupLayer
|  Author:     meizz
|  Created:    2006-03-19
|  Version:    2007-09-09
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/


MzPopupLayer=function()
{
  System.call(this);
  this.busy = false;
  this.exclusive=false;
  this.autoFit=true;
  this.initialize();
}
MzPopupLayer.Extends(System, "MzPopupLayer");
MzPopupLayer.supportsPopup = System.ie && MzBrowser.version>=5.5;
MzPopupLayer._layers=new Array();

System.extend(MzPopupLayer.prototype,
{
  initialize : function()
  {
    MzPopupLayer._layers[MzPopupLayer._layers.length] = this;
    var me=this; me.id=me.index="MzPopupLayer_"+ me.hashCode;

    this.onBlurHandler=function(){me.hide()};
    this.onClickHandler=function(){me.hide()};
    this.onScrollHandler=function(){me.hide()};

    var hh ="", s = " src='about:blank'", str = "";
    if(MzBrowser.netscape && MzBrowser.netscape.version<7.2)
      hh = "visibility: hidden;"; else hh = "display:none;";

    var str = "<iframe frameborder='0' name='"+ me.index +"' style='"+
      "position:absolute; z-index:"+ System.zIndexBase.popupLayer +
      ";"+ hh +"' scrolling='no' id='"+ me.index +"'></iframe>"

    if(!document.body) document.write(str); else
    document.body.insertAdjacentHTML("afterBegin", str);

    var fs = document.getElementsByTagName("IFRAME");
    for(var i=0; i<fs.length; i++)
    {
      if(fs[i].name==this.index) this.layer = fs[i];
    }

    if(!(MzBrowser.ie && MzBrowser.version<5.5))
    {
      frames[this.index].document.write("<html><head><style type='text/css'>"+
        "html *{-moz-user-select:none;}"+
        "body,td,th{font:12px Geneva, Arial, sans-serif; cursor:default;}"+
        "</style></head><body style='margin:0;background-color:#FFF;' "+
        " onselectstart='return false' oncontextmenu='alert(document.body.innerHTML);return false'>"+
        "<div id='Content'></div></body></html>");
      frames[this.index].document.close();
    }
  }

  ,loadCssFile : function(path, uniqueId)
  {
    uniqueId = uniqueId || path.replace(/\W/g, "_");
    if(!this.getElementById(uniqueId))
    {
      var doc   = frames[this.index].document;
      var link  = doc.createElement("LINK");
      link.href = path; link.id = uniqueId;
      link.type = "text/css";
      link.rel  = "Stylesheet";
      doc.body.insertBefore(link, doc.body.firstChild);
    }
  }

  //write HTMLString to popuplayer
  ,write : function(str){this.getElementById("Content").innerHTML = str;}

  //show popuplayer
  ,show : function(left, top, width, height)
  {
    if(MzBrowser.netscape && MzBrowser.netscape.version<7.2)
      this.layer.style.visibility=""; else MzElement.show(this.layer);

    if(this.autoFit)
    {
      this.layer.style.width = "2px";
      this.layer.style.height= "2px";

      var d = frames[this.index].document, dsw = dsh = 0;
      var bsw = d.body.scrollWidth;
      var bsh = d.body.scrollHeight;

      if(d.documentElement)
      {
        dsw = d.documentElement.scrollWidth;
        dsh = d.documentElement.scrollHeight;
      }

      width = Math.max(dsw, bsw);
      height= Math.max(dsh, bsh);
    }
    this.top    = top    || 0;
    this.left   = left   || 0;
    this.width  = width  || 2;
    this.height = height || 2;

    this.layer.style.top   = this.top    +"px";
    this.layer.style.left  = this.left   +"px";
    this.layer.style.width = this.width  +"px";
    this.layer.style.height= this.height +"px";
    frames[this.index].focus();
    //alert(this.layer.outerHTML);

    if(!this.exclusive)
    {
      window.attachEvent("onblur", this.onBlurHandler);
      window.attachEvent("onscroll", this.onScrollHandler);
      document.attachEvent("onmousedown", this.onClickHandler);
    }
  }

  ,showBy : function(element, width, height)
  {
    element = Mz$(element); if(!element) return;
    var body = MzElement.body();
    var pos  = MzElement.realOffset(element);
    this.show(pos.x, pos.y, width, height);

    var LOW = this.layer.offsetWidth;
    var LOH = this.layer.offsetHeight;
    var EOH = element.offsetHeight;

    if(body.scrollLeft + body.clientWidth < pos.x + LOW)
    {
      if(pos.x - LOW > body.scrollLeft) this.left = pos.x - LOW;
      else this.left = body.scrollLeft + body.clientWidth - LOW;
    }
    this.setLeft(this.left);

    if(body.scrollTop + body.clientHeight <pos.y + LOH + EOH)
    {
      if(pos.y - LOH >= body.scrollTop) this.top = pos.y - LOH;
      else this.top = body.scrollTop + body.clientHeight - LOH;
    }
    else this.top = pos.y + EOH; this.setTop(this.top);
  }

  //hide popuplayer
  ,hide : function()
  {
    this.layer.style.zIndex = System.zIndexBase.popupLayer;
    if(MzBrowser.netscape && MzBrowser.netscape.version<7.2)
      this.layer.style.visibility = "hidden"; else MzElement.hide(this.layer);

    if(!this.exclusive)
    {
      frames[this.index].document.body.innerHTML="<div id='Content'></div>";
      window.detachEvent("onblur", this.onBlurHandler);
      window.detachEvent("onscroll", this.onScrollHandler);
      document.detachEvent("onmousedown", this.onClickHandler);

      this.width = this.height = 0;
      this.style = this.busy = false;
    }
  }

  //get HTMLElement by id from popuplayer
  ,getElementById : function(id)
  {
    return frames[this.index].document.getElementById(id);
  }

  ,setTop   : function(top)   {this.layer.style.top   = top +"px";}
  ,setLeft  : function(left)  {this.layer.style.left  = left +"px";}
  ,setWidth : function(width) {this.layer.style.width = width +"px";}
  ,setHeight: function(height){this.layer.style.height= height +"px";}
});
window.attachEvent("onload", function(){new MzPopupLayer();});


//[static method]
MzPopupLayer.provider=function(parentWindow)
{
  for(var layer, i=0; i<this._layers.length; i++)
  if(!this._layers[i].busy){layer=this._layers[i]; break;}
  if(i==this._layers.length-1) new MzPopupLayer();
  if(i==this._layers.length) layer=new MzPopupLayer(parentWindow);

  layer.busy = true; layer.style = layer.getElementById("Content").style;
  if(parentWindow && System.getType(parentWindow)=="MzPopupLayer")
  layer.layer.style.zIndex = parentWindow.layer.style.zIndex + 1;

  return layer;
}