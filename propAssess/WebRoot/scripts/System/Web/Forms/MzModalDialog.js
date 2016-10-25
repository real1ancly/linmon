/*---------------------------------------------------------------------------*\
|  Subject:    Modal Dialog class
|  NameSpace:  System.Web.Forms.MzModalDialog
|  Author:     meizz
|  Created:    2006-10-18
|  Version:    2007-05-22
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/
//Using("System.Web.Forms.MzForm");
//Using("System.Web.Forms.MzLockWindow");

//op{title dialogWidth dialogHeight scroll resizable help drag}
function MzModalDialog(url, op)
{
  System.call(this); this.decontrol();

  this.locked = true;
  this.position="center";
  this.title = "meizz modal dialog";

  System.extend(this, op||{});

  if(this.dialogWidth) this.width = this.dialogWidth;
  if(this.dialogHeight)this.height= this.dialogHeight;
  var me=MzModalDialog.instance=this;

  me.addEventListener("onopen",function(){if(me.locked) MzLockWindow.lock({opacity:0.4})});
  me.addEventListener("onclose",function()
  {
    if(me.locked) MzLockWindow.unlock();
    document.detachEvent("onkeydown", MzModalDialog.keydownHandler);
  });

  this.open(url);
  document.attachEvent("onkeydown", MzModalDialog.keydownHandler);
}
MzModalDialog.Extends(MzForm, "MzModalDialog");

MzModalDialog.keydownHandler=function(e)
{
  e = window.event || e;
  e = e.which || e.keyCode
  if(e==27) MzModalDialog.close();
}

//static method
MzModalDialog.close = function(){MzModalDialog.instance.close();};
MzModalDialog.open = function(url, op){return new MzModalDialog(url, op);};

MzModalDialog.setWidth = function(w){MzModalDialog.instance.setWidth(w);};
MzModalDialog.setHeight = function(h){MzModalDialog.instance.setHeight(h);};
