/*---------------------------------------------------------------------------*\
|  Subject:    MzTreeView 2.0
|  NameSpace:  System.Web.UI.WebControls.MzTreeView
|  Author:     meizz
|  Created:    2005-12-28
|  Version:    2007-09-02
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/
//Using("System.Data.MzDataProvider");
//Using("System.Web.Forms.MzEffect");

//[interface]
//tree.onrender()
/// modify by wangylin on 2008-03-06
function MzTreeView(){MzDataProvider.call(this);this.index = this.hashCode;}
MzTreeView.Extends(MzDataProvider, "MzTreeView");

System.extend(MzTreeView.prototype,
{
   defaultUrl : "#"
  ,defaultTarget : "_self"
  ,setDefaultUrl : function(v){this.defaultUrl=v}
  ,setDefaultTarget : function(v){this.defaultTarget=v}
  ,iconPath : System.resourcePath +"/MzTreeView/"

  //[configuration]
  ,showPlus : true

  ,showLines : true
  ,showToolTip : true
  ,showNodeIcon : true

  ,autoSort : false
  ,useArrow : true
  ,dynamic  : true
  ,useCheckbox : false
  ,autoFocused : false
  ,useContextMenu : false
  ,convertRootIcon : true
  ,canOperate : false
  ,passChildChecked:true
  //public: entry
  ,render : function()
  {
    function loadImg(C){for(var i in C){if("string"==typeof C[i]){
    var a=new Image(); a.src=me.iconPath + C[i]; C[i]=a;}}} var me=this;
    loadImg(MzTreeView.icons.expand);loadImg(MzTreeView.icons.collapse);
    loadImg(MzTreeView.icons.line); me.firstNode=null;
    System.loadCssFile(System.resourcePath +"/MzTreeView.css", "MzTreeView_css");

    this.dataInit(); var str=" ", i, root=this.rootNode;
    if (root.hasChild){var a = [], c = root.childNodes; me.firstNode=c[0];
    for(i=0;i<c.length;i++)a[i]=c[i].render(i==c.length-1);str=a.join("");}
    setTimeout(function(){me.afterRender();}, 10);

    return ("<div class='MzTreeView' id='MzTree_root_{0}' "+
      "onclick='Instance(\"{0}\").clickHandle(event)' "+
      "oncontextmenu='return  Instance(\"{0}\").contextMenuHandle(event)' "+
      "ondblclick='Instance(\"{0}\").dblClickHandle(event)' "+
      ">"+ str +"</div>").format(this.index);
  }

  ,afterRender : function()
  {
    var me=this;
    if(Mz$("MzTree_root_"+ me.index))
    {
      if(me.firstNode)me.firstNode.focus();
      this.dispatchEvent(new System.Event("onrender"));
      if(me.useArrow) me._attachEventArrow();
    }
    else setTimeout(function(){me.afterRender();}, 100);
  }

  //get treenode by HTMLElement id
  ,getNodeByHtmlId : function(id)
  {
    if(typeof(id)!="string" || id.indexOf(this.index+"_")!=0) return null;
    return this.nodes[id.substr(id.indexOf("_", this.index.length + 2) + 1)];
  }

  //private: attachEvent onTreeKeyDown
  ,_attachEventArrow : function()
  {
    var a = Mz$("MzTree_root_"+ this.index); if(!a) return;
    var me= this;  a.attachEvent("onkeydown", function(e)
    {
      e = window.event || e; var B=false, key = e.keyCode || e.which;
      switch(key)
      {
        case 37 : B=true; me.focusUpperNode();      break;  //Arrow left,  shrink child node
        case 38 : B=true; me.focusPreviousSibling();break;  //Arrow up
        case 39 : B=true; me.focusLowerNode();      break;  //Arrow right, expand child node
        case 40 : B=true; me.focusNextSibling();    break;  //Arrow down
        case 46 : if(me.canOperate){B=true; me.selectedNode.removeNode();} break; //delete
      }
      if(B && me.useArrow){e.cancelBubble=true; e.returnValue=false;}
    });
  }

  //public: loadChildNodes(sourceId)
  ,loadChildNodes : function(id){var n; if(n=this.getNodeById(id)) n.loadChildNodes();}

  //private: build treeline onTreeNodeBuild
  ,buildPrefix : function(prefix)
  {
    var a=prefix.replace(/^,+/,"").split(","); for(var i=0; i<a.length; i++)
    if (a[i]) a[i]="<img src='"+ MzTreeView.icons.line[a[i]].src +"' alt='' />";
    return this.showLines?(prefix ?a.join(""):""):(prefix ?"<div style='width:"+
      (MzTreeView.icons.collapse["pm3"].width*a.length)+"px'>&nbsp;<\/div>":"");
  }

  //public: expand/collapse node
  ,expand : function(id){var n; if(n=this.getNodeById(id)) n.expand();}
  ,collapse : function(id){var n; if(n=this.getNodeById(id)) n.collapse();}

  //private: attachEvent tree onClick
  ,clickHandle : function(e)
  {
    e = window.event || e;
    e = e.srcElement || e.target;
    if(e.id && 0==e.id.indexOf(this.index +"_"))
    {
      var node=this.getNodeByHtmlId(e.id);
      switch(e.tagName)
      {// modify by wangylin, override on jsp , jsp will implements onexpand method.
        case "IMG" :
          if(e.id.indexOf(this.index +"_expand_")==0)
          {	
            if(node.expanded){
            		node.collapse();
            }else{
            	 var e=new System.Event("onexpand");
          		e.target=node; this.dispatchEvent(e);
          		break;
            }
          }
          else if(e.id.indexOf(this.index +"_icon_")==0){
            node.focus();}
          else if(e.id.indexOf(this.index +"_checkbox_")==0){
            node.check(!node.checked); node.upCheck();}
          break;
        case "A":
          node.focus();
          var e=new System.Event("onclick");
          e.target=node; this.dispatchEvent(e);
          break;
      }
    }
  }

  //private: onTreeMouseRightClick
  ,contextMenuHandle : function(e)
  {
    var se = new System.Event("oncontextmenu"), node;
    se.event = e||window.event;
    var obj = se.event.target||se.event.srcElement;
    if(obj.nodeType!=1) obj=obj.parentNode;
    while(!obj.id) obj = obj.parentNode;
    if(obj.id && (node = this.getNodeByHtmlId(obj.id)))
    {
      se.srcElement = se.target = node;
      this.dispatchEvent(se);
    }
    return se.returnValue;
  }

  //private: onTreeDoubleClick
  ,dblClickHandle : function(e)
  {
    e = window.event || e; e = e.srcElement || e.target;
    if((e.tagName=="A" || e.tagName=="IMG")&& e.id)
    {
      e=this.getNodeByHtmlId(e.id);
      e.expanded?e.collapse() : e.expand();
      var t=new System.Event("ondblclick");
      t.target = e;  this.dispatchEvent(t);
    }
  }


  //public: focus a treeNode by node's clientIndex
  ,focus : function(id)
  {
    var n; if(n=this.getNodeById(id)) n.focus();
  }
  ,focusNodeByPath : function(path)
  {
    var me=this, n;
    if ((n=path.indexOf(this.divider))>0)
    {
      var node = this.getNodeById(path.substring(0, n));
      if(node){if(node!=this.rootNode && !node.expanded) node.expand();
      node.focus();} else return;
      path = path.substr(n + this.divider.length);
      setTimeout(function(){me.focusNodeByPath(path)}, 1);
    }
    else this.getNodeById(path).focus();
  }
  ,focusUpperNode : function()
  {
    var e = this.selectedNode, r = this.rootNode; if(e){
    if(e.parentNode==r && !e.expanded) return;
    if(e.expanded) e.collapse(); else e.parentNode.focus();}
  }

  ,focusLowerNode : function()
  {
    var e = this.selectedNode; if(e && e.hasChild){
    if(e.expanded)e.childNodes[0].focus();else e.expand();}
  }

  ,focusPreviousSibling : function()
  {
    var e = this.selectedNode;if(e && e.parentNode){
    var c = e.parentNode.childNodes;for(var i=c.length-1; i>=0; i--){
    if(c[i]==e){if(i==0) return; c[i-1].focus();}}}
  }

  ,focusNextSibling : function()
  {
    var e = this.selectedNode; if(e && e.parentNode){
    var c = e.parentNode.childNodes; for(var i=0; i<c.length; i++){
    if(c[i]==e){ if(i==c.length-1) return; c[i+1].focus();}}}
  }

  ,appendNode : function(parentId, id, dataString)
  {
    if(!this.canOperate || "undefined"==typeof parentId) return;
    var pid=parentId+""; if(pid.length==0) return; id=id||this.getUniqueId();
    var node=this.getNodeById(pid); if(!node) return; node.hasChild=true;
    var d={};d[pid+this.divider+id]=dataString;this.appendData(d);
    if(!node.isLoaded){node.updateNode(); node.expand();} else
    node.appendNode(node.DTO(this.nodePrototype, pid+this.divider+id));
    var e=new System.Event("onappendnode");
    e.target=this.getNodeById(id); this.dispatchEvent(e);
    if(this.useCheckbox && node.checked) node.check(true);
  }

  ,updateNode : function(id)
  {
    if(!this.canOperate) return;
    var node; if(node=this.getNodeById(id)) node.updateNode();
  }
  ,updateNodeIcon : function(id, icon)
  {
    var node = this.getNodeById(id);
    if (node)node.updateNodeIcon(icon);
  }
  ,updateNodeIconSrc : function(id, src)
  {
    var node = this.getNodeById(id), e;
    if (node) node.updateNodeIconSrc(src);
  }

  ,removeNode : function(id)
  {
    if(!this.canOperate) return;
    var node; if(node=this.getNodeById(id)) node.removeNode();
  }
  //************************ can expand a node all childNodes
  ,expandAll : function(id)
  {
    if("undefined"==typeof id) return;
    var node=this.getNodeById(id);
    if(!node||node.hasChlid) return;
    node.expandAll();
  }
  ,collapseAll : function(id)
  {
    if("undefined"==typeof id) return;
    var node=this.getNodeById(id);
    if(!node||node.childNodes.length==0) return;
    node.collapseAll();
  }
  ,expandLevel : function(level)
  {
    if(!/\d+/.test(level) || level==0)return;var r;
    if((r=this.rootNode).hasChild)
    {
      for(var i=0, n=r.childNodes.length; i<n; i++)
        r.childNodes[i].expandLevel(level);
    }
  }
});


System.extend(MzTreeView,
{
  icons : 
  {
    line :
    {
      "l0" :"_line0.gif",
      "l1" :"_line1.gif",
      "l2" :"_line2.gif",
      "l3" :"_line3.gif",
      "l4" :"_line4.gif",
      "ll" :"_line5.gif",
      "c0" :"_checkbox0.gif",
      "c1" :"_checkbox1.gif",
      "c2" :"_checkbox2.gif"
    },
    collapse:
    {
      "pm0":"_plus0.gif",
      "pm1":"_plus1.gif",
      "pm2":"_plus2.gif",
      "pm3":"_plus3.gif",

      "root":"root.gif",
      "file":"file.gif",
      "exit":"exit.gif",
      "folder":"folder.gif"
    },
    expand :
    {
      "pm0":"_minus0.gif",
      "pm1":"_minus1.gif",
      "pm2":"_minus2.gif",
      "pm3":"_minus3.gif",

      "folder":"folderopen.gif"
    }
  }
  ,textLoading : "&#27491;&#22312;&#21152;&#36733;..."
  //static: append new definable icon

  ,setIcon : function(icon, src)
  {
    this._setIcon(icon, src, "collapse");
  }
  ,setExpandedIcon : function(icon, src)
  {
    this._setIcon(icon, src, "expand");
  }
  ,_setIcon : function(iconName, src, con)
  {
    if(/^pm\d$/.test(iconName)){alert("iconName error!"); return;}
    if(typeof(iconName)!="string"||typeof(src)!="string") return;
    var img = new Image(); img.src = src;
    MzTreeView.icons[con][iconName] = img;
  }
});
























MzTreeView.TreeNode = function(){MzDataProvider.DataNode.call(this);};
MzTreeView.TreeNode.Extends(MzDataProvider.DataNode, "MzTreeView.TreeNode");
MzTreeView.prototype.nodePrototype=MzTreeView.TreeNode;
MzTreeView.TreeNode.htmlChildPrefix="";

System.extend(MzTreeView.TreeNode.prototype,
{
   checked : false
  ,expanded : false
  ,childPrefix : ""

  //private: load all node's node and init
  ,loadChildNodes : function()
  {	
    MzDataProvider.DataNode.prototype.loadChildNodes.call(this, MzTreeView.TreeNode);
    if(this.$$caller.useCheckbox && this.$$caller.passChildChecked)
    {
      var data=this.$$caller.dataSource;
      for(var i=0, n=this.childNodes.length; i<n; i++)
      {
        var node=this.childNodes[i];
        node.checked=this.getBoolean("checked");
        node.checked=node.parentNode.checked || node.checked;
      }
      if(n>0) this.childNodes[0].upCheck();
    }
  }

  //private: single node build to HTML
  ,render : function(last)
  {
    var $ = this.$$caller;
    var nodeString = (this.parentNode==$.rootNode && this.text=="") ? "" : this.build(last);

    return "<dl id='"+ $.index +"_tree_"+ this.index +"'>"+ nodeString +"<dd id='"+ $.index +"_container_"+ this.index +"' style='margin-left:0; display:none'></dd></dl>"

    //return f.format(nodeString, $.index, this.index);
  }

  //private: build all node's node
  ,buildChildNodes : function()
  {
    var me = this, mtv = me.$$caller, box;
    if(box = Mz$(mtv.index +"_container_"+ me.index))
    {
      var a = new Array(me.childNodes.length);
      MzTreeView.TreeNode.htmlChildPrefix=mtv.buildPrefix(me.childPrefix);
      if(/(^|\s|;)(JS|XML|UL)Data\s*:/i.test(mtv.dataSource[me.sourceIndex])){
      function cond(a, b){if(a.hasChild!=b.hasChild)
        return (a.hasChild ? -1 : 1); else return(a.index>b.index?1:-1);}
      if(mtv.autoSort) me.childNodes.sort(cond);}
      var d1 = new Date().getTime();
      for(var i=0; i<a.length; i++)a[i]=me.childNodes[i].render(i==a.length-1);
      var d2 = new Date().getTime();
      window.status = a.length +" : "+ (d2-d1) +"ms  ";
      box.innerHTML=a.join(""); a=null; if(me.checked) me.check(me.checked);
      var d1 = new Date().getTime();
      window.status += " -> "+ (d1-d2) +"ms  ";
    }
  }
  //build single treenode
  ,build : function(last)
  {	
    var $=this.$$caller, s=$.dataSource[this.sourceIndex],data;
    var icon=this.get("icon");
    var target  = this.get("target") || $.defaultTarget;
    var hint    = $.showToolTip ? this.get("hint") || this.text : "";
    var url     = this.get("url") || $.defaultUrl;
 
    if(data=this.get("data"))url+=(url.indexOf("?")==-1?"?":"&")+data;
    if(this.getBoolean("nocache"))url+=(url.indexOf("?")==-1?"?":"&")+"_t_="+(new Date().getTime().toString(36));

    var id=this.index;
    var isRoot=this.parentNode==$.rootNode;
    if( isRoot && $.convertRootIcon && !icon) icon = "root";
    if(!isRoot)this.childPrefix=this.parentNode.childPrefix+(last?",ll":",l4");
    if(!icon || typeof(MzTreeView.icons.collapse[icon])=="undefined")
    this.icon = (this.hasChild || !this.leaf)  ? "folder" : "file"; else this.icon = icon;
   
    this.line = (this.hasChild || !this.leaf)? (last ? "pm2" : "pm1") : (last ? "l2" : "l1");
    if(!$.showLines) this.line =(this.hasChild || !this.leaf) ? "pm3" : "ll";

    var a = ["<dt id='{0}_node_{1}' nowrap='nowrap' title='"+ hint +"'><nobr>"];

    if(MzTreeView.TreeNode.htmlChildPrefix) a.push(MzTreeView.TreeNode.htmlChildPrefix);
    if(!isRoot) a.push("<img border='0' id='{0}_expand_{1}' src='"+ ((this.hasChild || !this.leaf)? MzTreeView.icons.collapse[this.line].src : MzTreeView.icons.line[this.line].src)+"'/>");
    if($.showNodeIcon) a.push("<img border='0' id='{0}_icon_{1}' src='"+ MzTreeView.icons.collapse[this.icon].src +"'/>");
    if($.useCheckbox)  a.push("<img border='0' id='{0}_checkbox_{1}' src='"+ MzTreeView.icons.line["c"+ (this.checked?1:0)].src +"'/>");

    a.push("<a href='"+ url +"' target='"+ target +"' id='{0}_link_{1}' class='MzTreeView' ");
    a.push(this.getBoolean("disableLink")?" onclick='event.returnValue=false;return false;'":"");
    a.push(">"+ this.text +"</a>");
    a.push("</nobr></dt>");
   
    return a.join("").format($.index, this.index);
  }
  //rebuild single treenode
  ,rebuild : function()
  {
    var $ = this.$$caller; 
    if(Mz$($.index +"_node_"+ this.index))
    {
      MzTreeView.TreeNode.htmlChildPrefix=$.buildPrefix(this.parentNode.childPrefix);
      var a = this.parentNode.childNodes;
      var s = this.build(a[a.length-1]==this);
      MzElement.remove($.index +"_node_"+ this.index);
      Mz$($.index +"_tree_"+ this.index).insertAdjacentHTML("afterBegin", s);

      if(Mz$($.index +"_container_"+ this.index).style.display!="none")
      {
        var line = Mz$($.index+"_expand_"+ this.index);
        var icon = Mz$($.index+"_icon_"+ this.index);
        var ies=MzTreeView.icons.expand;
        if(line && typeof(ies[this.line])=="object")line.src = ies[this.line].src;
        if(icon && typeof(ies[this.icon])=="object")icon.src = ies[this.icon].src;
      }

      if($.selectedNode==this)
        MzElement.addClassName($.index +"_link_"+ this.index, "selected");
    }
  }

  //private: check checkbox
  ,check : function(checked)
  {
    var me=this, mtv=me.$$caller, B=checked?"true":"false", mc=me.childNodes;
    var chk; if(chk=Mz$(mtv.index+"_checkbox_"+ me.index)){
    chk.src=MzTreeView.icons.line["c"+((me.checked=(checked==true))?1:0)].src;}
    var x=mtv.index; 
	if(this.$$caller.passChildChecked)//add by wangylin on 20080411 
	{
		for(var i=0, chk=mc.length; i<chk; i++)
		 setTimeout("Instance('"+x+"').nodes['"+mc[i].index+"'].check("+B+")",1);
	}
  }
  //private: set checkbox status on childNode has checked
  ,upCheck : function()
  {
	 
	if(this.$$caller.passChildChecked)//add by wangylin on 20080411 
	{
		var node = this, mtv=node.$$caller, chk; if(node.parentNode)
		{
		for(var i=0; i<node.parentNode.childNodes.length; i++)
		{
			if(node.parentNode.childNodes[i].checked != node.checked)
			{
				while(node=node.parentNode){ node.checked = false;
				if (chk = Mz$(mtv.index+"_checkbox_"+node.index))
				chk.src = MzTreeView.icons.line["c2"].src; } return;}
			}
			node = node.parentNode; node.checked = this.checked;
			if (chk = Mz$(mtv.index +"_checkbox_"+ node.index))
			chk.src = MzTreeView.icons.line["c"+(node.checked?1:0)].src;node.upCheck();
		}
	}
  }

  //private: expand node
  ,expand : function()
  {	
  	if(this.leaf){
  		return;
  	}
  	if(!this.hasChild){
  		return;
  	}
    var me=this, $ = me.$$caller;
    var box = Mz$($.index +"_container_"+ this.index);
    if (!box) {System.alert("error in getElementById"); return;}

    this.expanded = box.style.display=="none";
    box.style.display = "block"; if($.dynamic) MzEffect.fadein(box);
    var line = Mz$($.index+"_expand_"+ this.index);
    var icon = Mz$($.index+"_icon_"+ this.index);

    var ies=MzTreeView.icons.expand, ics=MzTreeView.icons.collapse;

    if(line && typeof(ies[this.line])=="object")line.src = ies[this.line].src;
    if(icon && typeof(ies[this.icon])=="object")icon.src = ies[this.icon].src;
		
    if(!this.isLoaded)
    {
      this.loadChildNodes();

      if(this.hasChild && (this.childNodes.length>200 
        || /(^|\s|;)(JS|XML|UL)Data\s*:/i.test($.dataSource[this.sourceIndex])))
      {
        setTimeout(function(){me.buildChildNodes();}, 1);
        box.innerHTML="<table border='0' cellspacing='0' cellpadding='0'><tr><td>"+
          $.buildPrefix(this.childPrefix) +"</td><td><img border='0' src='"+
          ics['pm2'].src +"'>"+ (!$.showNodeIcon ? "" : "<img border='0' src='"+
          ics['folder'].src +"'>") +"<a class='selected' href='#'>"+
          MzTreeView.textLoading +"</a></td></tr></table>";
      }
      else this.buildChildNodes();
    }
    //if($.useCheckbox) this.check(this.checked);
    //var e=new System.Event("onexpand");
  		 
    	//e.target=this; $.dispatchEvent(e);
    //where root node's text is empty
    if(this.parentNode==$.rootNode)
    {
      if(this.text=="" && this.hasChild)
      {
        var node = this.childNodes[0];
        line = Mz$($.index+"_expand_"+node.index);
        if(node.line.indexOf("pm")==0)
        {
          if(node.line=="pm1") node.line="pm0";
          else if(node.line=="pm2") node.line="pm3";
          line.src= ics[node.line].src;
        }
        else
        {
          if(node.line=="l1") node.line="l0";
          else if(node.line=="l2") node.line="l3";
          line.src= MzTreeView.icons.line[node.line].src;
        }
      }
    }
  }
  ,expandAll : function()
  {
    if(this.hasChild && !this.expanded) this.expand();
    for(var x=this.$$caller.index, i=0; i<this.childNodes.length; i++)
    {
      var node = this.childNodes[i]; if (node.hasChild)
      setTimeout("Instance('"+x+"').nodes['"+ node.index +"'].expandAll()", 1);
    }
  }
  ,expandLevel : function(level)
  {
    if(level<=0) return; level--; var me=this;
    if(this.hasChild && !this.expanded) this.expand();
    for(var x=this.$$caller.index, i=0, n=this.childNodes.length; i<n; i++)
    {
      var node=this.childNodes[i], d=node.index; if(node.hasChild)
      setTimeout("Instance('"+x+"').nodes['"+d+"'].expandLevel("+level+")",1);
    }
  }

  ,collapse : function()
  {
    var $ = this.$$caller;
    var box=Mz$($.index +"_container_"+ this.index);
    if (!box) {System.alert("error in getElementByid"); return;}

    var line=Mz$($.index+"_expand_"+ this.index);
    var icon=Mz$($.index+"_icon_"+ this.index);
    if($.dynamic)MzEffect.fadeout(box);else box.style.display="none";
    box=MzTreeView.icons.collapse;
    if(line) line.src= box[this.line].src; this.expanded=false;
    if(icon) icon.src=(box[this.icon]||box["file"]).src;
    if($.selectedNode && 0==$.selectedNode.path.indexOf(this.path)
      && $.selectedNode.path!=this.path) this.focus();
    var e=new System.Event("oncollapse");
    e.target=this; $.dispatchEvent(e);
  }
  ,collapseAll : function()
  {
    if(this.hasChild && this.expanded) this.collapse();
    for(var x=this.$$caller.index, i=0; i<this.childNodes.length; i++)
    {
      var node = this.childNodes[i]; if (node.hasChild && node.isLoaded)
      setTimeout("Instance('"+x+"').nodes['"+ node.index +"'].collapseAll()", 1);
    }
  }

  ,focus : function()
  {
    var $ = this.$$caller, a, o, e;
    if(!$.selectedNode) $.selectedNode=$.rootNode;
    if($.selectedNode==this) return;
    if(a = Mz$($.index +"_link_"+ this.index)){
    if(o = Mz$($.index +"_link_"+ $.selectedNode.index))
    {
      MzElement.removeClassName(o, "selected");
      e=new System.Event("onblur");
      e.target=$.selectedNode;
      $.dispatchEvent(e);
    }
    MzElement.addClassName(a, "selected");
    if($.autoFocused){try{a.focus();}catch(ex){}}
    $.selectedNode=this;
    e=new System.Event("onfocus"); e.target=this; $.dispatchEvent(e);}
  }




  //append update remove  --node method
  //if the node is not loaded then don't use this method!!
  ,appendNode : function(node)
  {
    var $=this.$$caller; if(!$.canOperate) return;
    if(this.hasChild && !this.isLoaded){this.expand(); return;}
    this.childNodes.push(node); this.hasChild=this.isLoaded=true;
    var div=Mz$($.index +"_container_"+ this.index);
    if(div.insertAdjacentHTML) div.insertAdjacentHTML("beforeEnd",node.render(true));
    else{var d=document.createElement("DIV"); d.innerHTML=node.render(true);
    div.appendChild(d); div.insertBefore(d.firstChild, d); div.removeChild(d);}
    if(this.childNodes.length>1)this.childNodes[this.childNodes.length-2].updateNodeLine();
    else {this.updateNodeLine(); this.updateNodeIcon();} this.expand(); this.expanded=true;
  }
  ,updateNode : function()
  {
    var $=this.$$caller; if(!$.canOperate)return;
    var e=new System.Event("onupdatenode"); e.target=this;
    $.dispatchEvent(e); if(!e.returnValue) return;

    this.updateNodeLine();
    this.updateNodeIcon();
    this.updateNodeLink();
  }
  ,updateNodeLine : function()
  {
    var $=this.$$caller, pcs=this.parentNode.childNodes;
    this.hasChild=this.isLoaded?this.childNodes.length>0:this.hasChildNodes();

    var line=Mz$($.index +"_expand_"+ this.index);
    if(line){var i=MzTreeView.icons; if($.showLines)
    {
      var b=pcs.indexOf(this)==(pcs.length-1);
      if(b)this.line=this.hasChild?"pm2":"l2";
      else this.line=this.hasChild?"pm1":"l1";
    }
    else   this.line=this.hasChild?"pm3":"ll";
    i=this.hasChild ? (this.expanded?i.expand:i.collapse) : i.line;
    line.src=i[this.line].src;}

  }
  ,updateNodeIcon : function(icon)
  {
    var $=this.$$caller;
    this.hasChild=this.isLoaded?this.childNodes.length>0:this.hasChildNodes();
    if($.showNodeIcon)
    {
      if(icon && typeof(icon)=="string"
        && typeof(MzTreeView.icons.collapse[icon])=="object") this.icon=icon;

      var icon=Mz$($.index +"_icon_"+ this.index);
      var ico=this.get("icon");
      if(ico!="folder"&&ico!="file"&&(this.icon=="folder"||this.icon=="file"))
        this.icon = this.hasChild ? "folder" : "file";
      var i=MzTreeView.icons; ico="undefined"==typeof(i.expand[this.icon]);
      if(this.expanded) {i=ico?i.collapse:i.expand;icon.src=i[this.icon].src;}
      else icon.src=MzTreeView.icons.collapse[this.icon].src;
    }
  }
  ,updateNodeIconSrc : function(src)
  {
    var e; if(e=Mz$($.index +"_icon_"+ this.index)) e.src=src;
  }
  ,updateNodeText : function()
  {
    Mz$(this.$$caller.index+"_link_"+this.index).innerHTML=this.text;
  }
  ,updateNodeLink : function()
  {
    var $=this.$$caller;
    var link=Mz$($.index +"_link_"+ this.index);
    var s = $.dataSource[this.sourceIndex], target, url;
    var cs= $.selectedNode==this?"selected":"MzTreeView";
    if(!(target=this.get("target")))target=$.defaultTarget;
    if(!(url=this.get("url"))) url = $.defaultUrl; if($.showToolTip)
    MzElement.searchByTagName(link, "TR").title=this.get("hint")||this.text;
    if(data=this.get("data"))url+=(url.indexOf("?")==-1?"?":"&")+data;
    s="<a target='"+ target +"' id='"+$.index +"_link_"+ this.index +
      "' href='"+ url +"' class='"+ cs +"'>"+ this.text +"</a>";
    link.parentNode.innerHTML=s;
  }
  ,removeNode : function()
  {
    var $=this.$$caller; if(!$.canOperate) return;
    var evt=new System.Event("onremovenode");  evt.target=this;
    $.dispatchEvent(evt); if(!evt.returnValue) return;
    if(this.parentNode)
    {
      var div=Mz$($.index +"_container_"+ this.index).parentNode;
      $.indexes=$.indexes.replace($.__ + this.sourceIndex, "");

      var p=this.parentNode, pcs=p.childNodes, n=pcs.indexOf(this), a=[];
      for(var i=0; i<pcs.length; i++){if(i==n) continue; a.push(pcs[i]);}
      p.childNodes=a; pcs=$.dataSource[this.sourceIndex];
      $.dataSource[this.sourceIndex]=pcs.deleteAttribute("index_"+ $.index);
      if(a.length==0){p.collapse();p.updateNode();MzEffect.fadeout(div.parentNode);}
      else if(n==a.length){a[a.length-1].updateNode();} pcs=a=null;

      div.parentNode.removeChild(div); p.focus();
    }
  }
});


/****** MzTreeView Inputer *****/
t=[];
t.push("<table border='0' cellspacing='1' id='MzTreeInputer' widht='100%'>");
t.push("<colgroup><col class='caption'/><col class='content' /></colgroup>");
t.push("<tr><td>&#33410;&#28857;ID</td><td><input class='text' id='mtinputerId' maxlength='16'/></td></tr>");
t.push("<tr><td>&#29238;&#33410;&#28857;ID</td><td><input class='text' id='mtinputerParentId' maxlength='16'/></td></tr>");
t.push("<tr><td>&#33410;&#28857;&#25991;&#23383;</td><td><input class='text' id='mtinputerText' maxlength='64'/></td></tr>");
t.push("<tr><td>&nbsp;</td><td id='mtinputeroption' onclick='MzTreeView.inputerhs(this)'>&#20854;&#23427;&#36873;&#39033; &gt;&gt;&gt;</td></tr>");
t.push("<tr style='display: none'><td>&#33410;&#28857;&#22270;&#26631;</td><td><select id='mtinputerIcon'><option value=''>&#35831;&#36873;&#25321;</option></select></td></tr>");
t.push("<tr style='display: none'><td>&#33410;&#28857;&#38142;&#25509;</td><td><input class='text' maxlength='128' id='mtinputerUrl'/></td></tr>");
t.push("<tr style='display: none'><td>Target</td><td><input class='text' id='mtinputerTarget' maxlength='32'/></td></tr>");
t.push("<tr style='display: none'><td>&#25552;&#31034;&#20449;&#24687;</td><td><input class='text' maxlength='64' id='mtinputerHint'/></td></tr>");
t.push("<tr style='display: none'><td>&#38468;&#21152;&#25968;&#25454;</td><td><input class='text' maxlength='255' id='mtinputerData' title='key=value&key=value&key=value&...'/></td></tr>");
t.push("<tr style='display: none'><td>&#36873;&#20013;&#29366;&#24577;</td><td><input type='checkbox' id='mtinputerCheck'/></td></tr>");
t.push("<tr><td><input class='button' type='button' value='&#30830;&#23450;' /></td><td><input class='button' type='button' value='&#21462;&#28040;' /></td></tr>")
MzTreeView.htmlInputer=t.join("") +"</table>";
MzTreeView.inputerhs=function(td)
{
  var tab = MzElement.searchByTagName(td, "TABLE");
  var b=tab.rows[td.parentNode.rowIndex+1].style.display=="none";
  for(var i=td.parentNode.rowIndex+1; i<tab.rows.length-1; i++)
  b?MzEffect.fadein(tab.rows[i]):MzEffect.fadeout(tab.rows[i]);tab=td.innerHTML;
  td.innerHTML = tab.substring(0, tab.indexOf(" ")) +" "+
  (b ? "&lt;&lt;&lt;" : "&gt;&gt;&gt;");
  tab=Mz$("mtinputerIcon")
  b?MzEffect.fadein(tab):MzEffect.fadeout(tab);
};
MzTreeView.hideInputer=function()
{
  var inputer=Mz$("MzTreeInputer");
  inputer.parentNode.removeChild(inputer);
};

MzTreeView.prototype.showInputer = function()
{
  var container=document.createElement("DIV");
  container.style.width="100%";
  MzEffect.fadein(container);
  container.innerHTML = MzTreeView.htmlInputer;
  document.body.appendChild(container);
  var sel = Mz$("mtinputerIcon");
  sel.options.length=1;
  for(var i in MzTreeView.icons.collapse) if(!/^pm\d$/.test(i))
  sel.options[sel.options.length]=new Option(i, i, true, true);
  sel.selectedIndex=0;
};


