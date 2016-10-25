/*---------------------------------------------------------------------------*\
|  Subject:    popup menu  cross browser(IE5.0+ Firefox NS Moz Opera)
|  NameSpace:  System.Web.Forms.MzPopupMenu
|  Author:     meizz
|  Created:    2007-03-02
|  Version:    2007-03-02
|-------------------------------------------------------------
|  MSN: huangfr@msn.com   QQ: 112889082   http://www.meizz.com
|  Blog: http://blog.csdn.net/meizz/      Copyright (c)  meizz
\*---------------------------------------------------------------------------*/
//Using("System.Data.MzDataProvider");
//Using("System.Web.Forms.MzPopupLayer");

//datanode{space: true, text: "xx", hotkey: "A"}
function MzPopupMenu(trigger, op)
{
  System.call(this);

  this.trigger=trigger;
  this.autoFit=true;
  this.isOpen=false;
  this.layer =false;

  this.initialize();
}
MzPopupMenu.Extends(MzDataProvider, "MzPopupMenu");


function mm(e)
{
  var s = "";
  for(var i in e) s += i +" = "+ e[i] +"\n";
 
}

MzPopupMenu.icons = {};
MzPopupMenu.defaultIconPath = System.resourcePath +"/MzPopupMenu/item.gif";
MzPopupMenu.setIcon = function(icon, path)
{
  var img = new Image();
  img.src = path;
  MzPopupMenu.icons[icon] = img;
};

System.extend(MzPopupMenu.prototype,
{
  initialize : function()
  {
    var me=this;
    //attach page event
    this.attachClick=function(){me.hide();}
    this.attachScroll=function(){me.hide();}
    this.attachBlur=function(){me.hide();}

    this.attachPopupKeyDown=function(evt)
    {
      evt=window.event||evt;  evt=evt.keyCode||evt.which;
    }
  }

  ,render : function(evt)
  {
    this.dataInit();
    this.layer = MzPopupLayer.provider();

    var str = this.nodeToString("-1");
    this.layer.loadCssFile(System.resourcePath+"/MzPopupMenu.css", "CSS_MzPopupMenu")
    this.layer.write(str);

    var body = MzElement.body();

    var cY=evt.clientY;
    var cX=evt.clientX;
    
    var cH=body.clientHeight;
    var cW=body.clientWidth;
    var count = this.getNodeById("-1").childNodes.length;
   	var temp=count*20;
    if(cH-cY<temp){
    	cY=cY-temp;
    }
   	if(cX>120)cX=70;
    var top  = cY+body.scrollTop;
    var left = cX+body.scrollLeft;
  	
    if(System.ie){top-=2; left-=2;}
    this.layer.show(left, top);

    document.attachEvent("onmousedown", this.attachClick);
  }

  ,nodeToString : function(nodeId)
  {
    var node = this.getNodeById(nodeId);
    if(!node.isLoaded) node.loadChildNodes();
    var a = this.getNodeById(nodeId).childNodes;
    if(a.length==0) return "";
    var s = [];
    s.push("<table  border='0' cellspacing='0' cellpadding='0' style='border:solid 1px #666666;'>");
    for(var i=0; i<a.length; i++)
    {
      var node = a[i];
      if(node.getBoolean("space"))
      {
        s.push("<tr class='line'><td class='icon'>&nbsp;</td>");
        s.push("<td colspan='2' class='line'><div>&nbsp;</div></td></tr>");
      }
      else
      {
        s.push("<tr"); if(node.getBoolean("disabled"))
        s.push(" class='disabled'");
        s.push(" onclick=\"parent.Instance('"+ this.hashCode +"').callbackClick(event, this, '"+ node.id +"')\"");
        s.push(" onmouseover=\"parent.Instance('"+ this.hashCode +"').callbackMouseOver(event, this, '"+ node.id +"')\"");
        s.push(" onmouseout =\"parent.Instance('"+ this.hashCode +"').callbackMouseOut(event, this, '"+ node.id +"')\"");
        s.push("><td class='icon'><div style='width:25px;text-align:center'>"); var icon = node.get("icon");
        if(icon)
        {
          if(MzPopupMenu.icons[icon]) var src=MzPopupMenu.icons[icon].src; else var src=MzPopupMenu.defaultIconPath
          s.push("<img alt='' src='"+ src  +"' style='width:16px;height:16px' />");
        }
        else s.push("&nbsp;");
        s.push("</div></td>");
        s.push("<td class='text' nowrap='nowrap' style='line-height:20px;'><nobr>"+ node.get("text") +"</nobr></td>");
        if(!node.hasChildNodes()) s.push("<td class='hasChild'><div style='width:16px'>&nbsp;</div></td>"); else
        s.push("<td class='hasChild'><div style='width:16px'><img alt='' src='"+ System.resourcePath +"/blank.gif' /></div></td>");
        s.push("</tr>");
      }
    }
    s.push("</table>")
    s = s.join("");
    return s;
  }

  ,renderSubMenu : function(tr, nodeId)
  {
    this.subLayer = MzPopupLayer.provider(this.layer);

    var str = this.nodeToString(nodeId);
    this.subLayer.loadCssFile(System.resourcePath+"/MzPopupMenu.css", "CSS_MzPopupMenu")
    this.subLayer.write(str);

    var pos = MzElement.realOffset(tr);

    this.subLayer.show(this.layer.left + pos.x + tr.offsetWidth - 4, this.layer.top + pos.y - 2);
  }


  //popupmenu item event callback
  ,drive : function(node, eventType)
  {
    var func = node.get(eventType);
    if("string"==typeof(func) && "function"==typeof(eval(func))) eval(func)(this.trigger);
    else if("function"==typeof(func)) func(this.trigger);
  }
  ,callbackClick : function(evt, tr, id)
  {
    var node = this.getNodeById(id);
    if (node.getBoolean("disabled")) return;
    if (node.hasChildNodes())
    {
      this.renderSubMenu(tr, id);
    }
    else{this.drive(node, "onclick"); this.hide();}
  }
  ,callbackMouseOver : function(evt, tr, id)
  {
    MzElement.addClassName(tr, "MouseOver");
    this.drive(this.getNodeById(id), "onmouseover");
  }
  ,callbackMouseOut : function(evt, tr, id)
  {
    MzElement.removeClassName(tr, "MouseOver");
    this.drive(this.getNodeById(id), "onmouseover");
  }

  ,show : function()
  {
  }
  ,hide : function()
  {
    this.layer.hide();
    this.layer = false;
    document.detachEvent("onmousedown", this.attachClick);
  }
});