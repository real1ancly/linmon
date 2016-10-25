/*---------------------------------------------------------------------------*\
|  Subject:    DataProvider for Meizz WebControl
|  NameSpace:  System.Data.MzDataProvider
|  Author:     meizz
|  Created:    2005-12-28
|  Version:    2007-09-02
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/
//modify by wangyl on 2007-10-23
//去掉了程序112行的System.alert("The node isn't initialized!");
//text,description,icon,url,target,JSData,XMLData,ULData

//added by wangyl on  2008-3-6  property   leaf : true
function MzDataProvider()
{
  System.call(this);
}
MzDataProvider.Extends(System, "MzDataProvider");

System.extend(MzDataProvider.prototype,
{
   __:"\x0f"
  ,rootId:"-1"
  ,dividerEncoding:"_"
  ,divider:"_"
  ,indexes:""
  ,jsDataPath:""
  ,xmlDataPath:""

  ,appendIndexes:function(s)
  {
    this.indexes += this.__+ s +this.__;
  }
  ,getUniqueId:function()
  {
    return "MzDataProvider"+ (MzDataProvider.nodeCounter++).toString(36);
  }

  //public
  ,setDivider:function(d)
  {
    this.divider=d; for(var a="", i=0; i<d.length; i++)
    a+=("\'^{[(\\-|+.*?)]}$\"".indexOf(d.charAt(i))>-1?"\\":"")+d.charAt(i);
    this.dividerEncoding = a;
  }
  ,setAsyncJsDataPath:function(path)
  {
    if(path.length>0) this.jsDataPath = path.replace(/[\\\/]*$/, "/");
  }
  ,setAsyncXmlDataPath:function(path)
  {
    if(path.length>0) this.xmlDataPath = path.replace(/[\\\/]*$/, "/");
  }
  ,render:function(){this.dataInit();}
  //private: initialize data node
  ,dataInit :function()
  {
    if(this._dataInitialized) return;
    if("object"!=typeof(this.nodes)) this.nodes={};
    if("object"!=typeof(this.dataSource)) this.dataSource={};
    if("object"!=typeof(this.indexMapping)) this.indexMapping={};

    var _=this.__, d=this.dividerEncoding, a=[], i;

    for(i in this.dataSource)a[a.length]=i;this.appendIndexes(a.join(_));
    this.dataSource.length=(this.dataSource.length||0)+ a.length;

    a=(MzDataProvider.nodeCounter++).toString(36);
    var node=this.nodes[a]=this.rootNode = new this.nodePrototype; //this.imaginaryNode
    node.$$caller=this;node.index=a;node.virgin=this.rootId=="-1";
	
    if(node.virgin)
    {
      node.id=node.path="-1";
      node.loadChildNodes();
   
      node.hasChildNodes();
    }
    else
    {
      a=new RegExp("([^"+_+d+"]+)"+ d +"("+ this.rootId +")("+_+"|$)");
      if(a.test(this.indexes))
      {
        a=RegExp.$1 + this.divider + this.rootId;
        node.childNodes[0]=node.DTO(this.nodePrototype, a);
        node.isLoaded=true; node.hasChild=true;
      }
    }
    this._dataInitialized=true;
  }
  //public: append data onafterload
  ,appendData :function(data, override) //param data: json
  {
    if(typeof(this.dataSource)!="object") this.dataSource={}; var a=[],i;
    for(i in data)if(!this.dataSource[i]){this.dataSource[i]=data[i];a[a.length]=i}
    if(this._dataInitialized) this.appendIndexes(a.join(this.__));
    this.dataSource.length=(this.dataSource.length||0)+a.length;data=null;a=null;
  }
  //public: getNode (has Builded) by sourceId
  ,getNodeById :function(id)
  {
    if(id==this.rootId&&this.rootNode.virgin) return this.rootNode;
    var _=this.__, d = this.dividerEncoding;
    var reg=new RegExp("([^"+_+ d +"]+"+ d + id +")("+_+"|$)");
    if(reg.test(this.indexes)){var s=this.indexMapping[RegExp.$1];
    //alert(s);
    if("string"==typeof(s)) return this.nodes[s.getAttribute("index_"+this.hashCode)];
    //if(reg.test(this.indexes)){var s=this.dataSource[RegExp.$1];
    //if("string"==typeof(s)) return this.nodes[s.getAttribute("index_"+this.hashCode)];
    //else if("object"==typeof(s)) return this.nodes[s["index_"+this.hashCode]];
   // else{System.alert("The node isn't initialized!"); return null;}}
   else{ return null;}}
    //alert("sourceId="+ id +" is nonexistent!");
	return null;
  }
  //public: asynchronous get childNodes from JS File
  ,loadJsData :function(JsFileUrl)
  {
    var js; try{if(js = System.load("",{path:JsFileUrl})){var d=eval(js);
    if("object"!=d && "object"==typeof(data) && null!=data)d=data;
    this.appendData(d); data=d=null;}}catch(e){}
  }
  //public: asynchronous get childNodes from Json File
  ,loadJsonData :function(JsonFileUrl)
  {
    MzDataProvider.instance=this;
    if("undefined"==typeof(MzJson))Using("System.Net.MzJson");
    var e=new MzJson();  e.cache = /\.js$/i.test(JsonFileUrl);
    e.request(JsonFileUrl); e=null;
  }
  //public: asynchronous get childNodes from XML File
  ,loadXmlData :function(url, parentId, mapping)
  {
    if(System.supportsXmlHttp())
    {
      //Using("System.Xml.MzXmlDocument");
      if("undefined"==typeof parentId) parentId=this.rootId;
      var x=new MzXmlDocument(); x.async=false; x.load(url);
      if(x.readyState==4)
      {
        if(!x.documentElement)
          alert("xmlDoc.documentElement = null, Please update your browser");
        this._loadXmlNodeData(x.documentElement, parentId, mapping);
      }
    }
  }
  //public: asynchronous get childNodes from XML String
  ,loadXmlDataString :function(xmlString, parentId, mapping)
  {
    if(System.supportsXmlHttp())
    {
      //Using("System.Xml.MzXmlDocument");
      if("undefined"==typeof parentId) parentId=this.rootId;
      var x=new MzXmlDocument(); x.loadXML(xmlString);
      this._loadXmlNodeData(x.documentElement, parentId, mapping);
    }
  }
  ,_loadXmlNodeData :function(xmlNode, parentId, mapping)
  {	
    if(!(xmlNode && xmlNode.hasChildNodes())) return;
    for(var k,id,i=0,data={},n=xmlNode.childNodes; i<n.length; i++)
    {
      if(n[i].nodeType==1){id=n[i].getAttribute("id")||this.getUniqueId();
      if(n[i].hasChildNodes()){for(k=0,nic=n[i].childNodes;k<nic.length;k++)
      {
        if(nic[k].nodeType==1){this._loadXmlNodeData(n[i], id, mapping);break;}}
      }
      for(var k=0,s="",a=n[i].attributes; k<a.length; k++)
      {
        if(mapping) s=s.setAttribute(mapping[a[k].name.toLowerCase()]||a[k].name, a[k].value);
        else s=s.setAttribute(a[k].name, a[k].value);
      }
      if(!s.getAttribute("text")) s="text:;"+ s;
      a=parentId + this.divider + id; data[a]=s;}
    }
    this.appendData(data);
  }

  //public
  ,loadUlData:function(HtmlUL, parentId)
  {   
    if("undefined"==typeof parentId) parentId=this.rootId; var ul;
    if("string"==typeof HtmlUL&&(ul=document.getElementById(HtmlUL)));
    else if("object"==typeof HtmlUL&&(ul=HtmlUL.tagName)&&
      "UL OL".indexOf(ul.toUpperCase())>-1) ul=HtmlUL;
    if("object"==typeof ul)
    {	
      var data={}; for(var i=0, n=ul.childNodes; i<n.length; i++)
      {
        if(n[i].nodeType==1 && n[i].tagName=="LI")
        {
          var id=n[i].getAttribute("sourceid")||this.getUniqueId(),txt="",link="";
          for(var k=0; k<n[i].childNodes.length; k++)
          {
            var node=n[i].childNodes[k];
            
            if(node.nodeType==3) txt += node.nodeValue;
            if(node.nodeType==1)
            {
              switch(node.tagName)
              {
                case "UL":
                case "OL": this.loadUlData(node, id); break;
                case "A" : if(!link) link=node; break;
              }
            }
          }
          var str="";
          if(link)
          {
            str=str.setAttribute("target", link.target);
            str=str.setAttribute("url", link.href);
            str=str.setAttribute("text", link.innerHTML);
          }
          else str = str.setAttribute("text", txt);
          var a=n[i].attributes; //<li>.attributes
          for(var k=0; k<a.length; k++)
          {
            if(a[k].specified && a[k].name!="style")
              str = str.setAttribute(a[k].name, a[k].value);
          }
          a=parentId + this.divider + id;
          data[a]=str;
        }
      }
      this.appendData(data);
    }
  }

});
window.MzJsonDataLoad=function(json)
{
  if(MzDataProvider.instance) MzDataProvider.instance.appendData(json);
  MzDataProvider.instance=null;
}

MzDataProvider.nodeCounter=0;
MzDataProvider.prototype.nodePrototype=MzDataProvider.DataNode = function()
{
  this.index = (MzDataProvider.nodeCounter++).toString(36);
  this.childNodes=[];
};
MzDataProvider.DataNode.Extends(System, "MzDataProvider.DataNode");

System.extend(MzDataProvider.DataNode.prototype,
{
   text : ""
  ,path : ""
  ,leaf : true
  ,sourceIndex : ""
  ,isLoaded : false
  ,hasChild : false
  ,parentNode : null
  ,$$caller : null  //instance of System.Data.MzDataProvider


  //public: check node has child
  ,hasChildNodes : function()
  {
    var $=this.$$caller;
    
    this.hasChild=$.indexes.indexOf($.__+ this.id + $.divider)>-1
    ||(this.sourceIndex&&(this.get("JSData")!=null||this.get("XMLData")!=null
    || this.get("ULData")!=null)); return this.hasChild;
  }
  //public: get node attribute
  ,get : function(attribName)
  {
    if("undefined"!=typeof(this[attribName]))return this[attribName]; else
    {
      var s=this.$$caller.dataSource[this.sourceIndex];
      if("string"==typeof(s)) return s.getAttribute(attribName);
      else if("object"==typeof(s)) return s[attribName];
    }
  }
  ,getBoolean : function(attribName)
  {
    var result = this.get(attribName), b;
    if (result == null) return false;
    switch(typeof(result))
    {
      case "string" : b = result!="" && result.toLowerCase()!="false"; break;
      case "number" : b = result!=0; break;
      case "undefined" : b = false; break;
      default : b = !(!result);
    }
    return b;
  }
  //public: set node attribute
  ,set : function(attribName, value)
  {
    if("undefined"!=typeof(this[attribName])) this[attribName]=value; else
    {
      var s=this.$$caller.dataSource[this.sourceIndex];
      if("string"==typeof(s))
        this.$$caller.dataSource[this.sourceIndex]=s.setAttribute(attribName,value);
      else if("object"==typeof(s)) s[attribName]=value;
    }
  }
  //private: load all node's node and init
  ,loadChildNodes : function(DataNodeClass)
  {
    var $=this.$$caller,r=$.dividerEncoding,_=$.__, i, cs;
    var tcn=this.childNodes;tcn.length=0;if(this.sourceIndex){
    if((i=this.get("JSData"))) $.loadJsData((/^\w+\.js(\s|\?|$)/i.test(i)?$.jsDataPath:"")+i);
    if((i=this.get("ULData"))) $.loadUlData(i, this.id);
    if((i=this.get("XMLData")))$.loadXmlData((/^\w+\.xml(\s|\?|$)/i.test(i)?$.xmlDataPath:"")+i,this.id);}
    var reg=new RegExp(_ + this.id + r +"[^"+ _ + r +"]+", "g"); 
    if((cs=$.indexes.match(reg))){for(i=0;i<cs.length;i++){
      tcn[tcn.length]=this.DTO(DataNodeClass, cs[i].substr(_.length));}}
    this.isLoaded = true;
  }
  ,DTO : function(DataNodeClass, sourceIndex)
  {	
    var C=DataNodeClass||MzDataProvider.DataNode,$=this.$$caller,d=$.divider,n=new C,s;
    n.$$caller=this.$$caller; s=$.dataSource[n.sourceIndex=sourceIndex];
   
    n.id=sourceIndex.substr(sourceIndex.indexOf(d)+d.length);
    
    n.hasChildNodes(); n.parentNode=this; $.nodes[n.index]=n;
    n.path=this.path+d+n.id;
    if("string"!=typeof($.indexMapping[n.sourceIndex])) $.indexMapping[n.sourceIndex]="";
    
    $.indexMapping[n.sourceIndex]=$.indexMapping[n.sourceIndex].setAttribute("index_"+ $.hashCode,n.index);
    n.set("index_"+ $.hashCode,n.index);
    var leaf;
    if("string"==typeof(s)) 
    {n.text=s.getAttribute("text");
    	leaf=s.getAttribute("leaf");
    	n.leaf=(leaf==1	? false : true );
    }
  
    else if("object"==typeof(s)) {
    	n.text=s.text; 
    
    }
    	
    	return n;
  }

});


String.prototype.getAttribute = function(attribute)
{
  if(new RegExp("(^|;)\\s*"+attribute+"\\s*:\\s*([^;]*)\\s*(;|$)","i").test(this))
  return RegExp.$2.replace(/%3B/gi,";").replace(/%25/g,"%"); return null;
}

String.prototype.setAttribute = function(attribute, value)
{
  value=(""+value).replace(/%/g,"%25").replace(/;/g,"%3B").replace(/\r|\n/g,"");
  return (attribute +":"+ value +";" + this);
}

String.prototype.deleteAttribute = function(attribute)
{
  return this.replace(new RegExp("\\b\\s*"+attribute+"\\s*:\\s*([^;]*)\\s*(;|$)","gi"),"");
}
