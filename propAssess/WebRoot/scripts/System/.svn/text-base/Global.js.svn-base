/*-=< HTMLElement >=-*/
if(typeof(HTMLElement)!="undefined" && !window.opera)
{
  HTMLElement.prototype.contains=function(e){do if(e==this)return true;while(e=e.parentNode);return false;};
  HTMLElement.prototype.__defineGetter__("outerHTML",function()
  {
    var a=this.attributes, str="<"+this.tagName, i=0;for(;i<a.length;i++)
    if(a[i].specified) str+=" "+a[i].name+'="'+a[i].value+'"';
    if(!this.canHaveChildren) return str+" />";
    return str+">"+this.innerHTML+"</"+this.tagName+">";
  });
  HTMLElement.prototype.__defineSetter__("outerHTML",function(s)
  {
    var r = this.ownerDocument.createRange();
    r.setStartBefore(this);
    r = r.createContextualFragment(s);
    this.parentNode.replaceChild(r, this);
    return s;
  });
  HTMLElement.prototype.__defineGetter__("canHaveChildren",function()
  {
    switch(this.tagName.toLowerCase())
    {
      case "area": case "base":  case "basefont":
      case "col":  case "frame": case "hr":
      case "img":  case "br":    case "input":
      case "link": case "meta":  case "isindex":
      case "param":return false;
    } return true;
  });
  HTMLElement.prototype.__defineGetter__("currentStyle", function()
  {
    return this.ownerDocument.defaultView.getComputedStyle(this,null);
  });
  HTMLElement.prototype.__defineGetter__("children",function()
  {
    for(var a=[],j=0,n,i=0; i<this.childNodes.length; i++){
    n=this.childNodes[i];if(n.nodeType==1){a[j++]=n;if(n.name){
    if(!a[n.name])a[n.name]=[]; a[n.name][a[n.name].length]=n;}
    if(n.id) a[n.id]=n;}}return a;
  });
  HTMLElement.prototype.insertAdjacentHTML=function(where, html)
  {
    var e=this.ownerDocument.createRange();
    e.setStartBefore(this);
    e=e.createContextualFragment(html);
    switch (where)
    {
      case 'beforeBegin': this.parentNode.insertBefore(e, this);break;
      case 'afterBegin': this.insertBefore(e, this.firstChild); break;
      case 'beforeEnd': this.appendChild(e); break;
      case 'afterEnd':
        if(!this.nextSibling) this.parentNode.appendChild(e);
        else this.parentNode.insertBefore(e, this.nextSibling); break;
    }
  };
};
if(!window.attachEvent && window.addEventListener)
{
  window.attachEvent = HTMLElement.prototype.attachEvent=
  document.attachEvent = function(en, func, cancelBubble)
  {
    var cb = cancelBubble ? true : false;
    this.addEventListener(en.toLowerCase().substr(2), func, cb);
  };
  window.detachEvent = HTMLElement.prototype.detachEvent=
  document.detachEvent = function(en, func, cancelBubble)
  {
    var cb = cancelBubble ? true : false;
    this.removeEventListener(en.toLowerCase().substr(2), func, cb);
  };
}
if(typeof Event!="undefined" && navigator.userAgent.indexOf("MSIE")==-1 && !window.opera)
{
  Event.prototype.__defineSetter__("returnValue", function(b){if(!b)this.preventDefault();  return b;});
  Event.prototype.__defineSetter__("cancelBubble",function(b){if(b) this.stopPropagation(); return b;});
  Event.prototype.__defineGetter__("offsetX", function(){return this.layerX;});
  Event.prototype.__defineGetter__("offsetY", function(){return this.layerY;});
  Event.prototype.__defineGetter__("srcElement", function(){var n=this.target; while (n.nodeType!=1)n=n.parentNode;return n;}); 
}

/*-=< Function >=-*/
//apply and call
if(typeof(Function.prototype.apply)!="function")
{
  Function.prototype.apply = function(obj, argu)
  {
    if(obj) obj.constructor.prototype.___caller = this;
    for(var a=[], i=0; i<argu.length; i++) a[i] = "argu["+ i +"]";
    var t = eval((obj ? "obj.___caller" : "this") +"("+ a.join(",") +");");
    if(obj) delete obj.constructor.prototype.___caller; return t;};
    Function.prototype.call = function(obj){
    for(var a=[], i=1; i<arguments.length; i++) a[i-1]=arguments[i];
    return this.apply(obj, a);
  }; 
}




/*-=< Array >=-*/
//[extended method] push  insert new item
if(typeof(Array.prototype.push)!="function")
{
  Array.prototype.push = function()
  {
    for (var i=0; i<arguments.length; i++)
      this[this.length] = arguments[i];
    return this.length; 
  };
}
//[extended method] shift  delete the first item
if(typeof(Array.prototype.shift)!="function")
{
  Array.prototype.shift = function()
  {
    var mm = null;
    if(this.length>0)
    {
      mm = this[0]; this.reverse();
      this.length = this.length-1;
      this.reverse();
    }
    return mm;
  };
}
//[extended method] indexOf
if(typeof(Array.prototype.indexOf)!="function")
{
  Array.prototype.indexOf=function(item, start)
  {
    start=start||0; if(start<0)start=Math.max(0,this.length+start);
    for(var i=start;i<this.length;i++){if(this[i]===item)return i;}
    return -1;
  };
}
//[extended method] forEach
Array.prototype.each=function(func)
{
  if(typeof(func)!="function") return this;
  for(var i=0,n=this.length;i<n;i++)this[i]=func(this[i])||this[i];return this;
};
if(typeof(Array.prototype.forEach)!="function")
{
  Array.prototype.forEach=function(func){return this.each(func);}
}
//[extended method] unique  Delete repeated item
Array.prototype.unique = function()
{
  for(var a={}, i=this.length-1; i>-1; i--)
  {
    if(typeof(a[this[i]])=="undefined") a[this[i]] = 1;
  }
  this.length=0;
  for(i in a) this[this.length] = i; return this;
};
Array.prototype.contains=Array.prototype.include=function(e){return this.indexOf(e)>-1};
Array.prototype.remove=function(e)
{
  for(var i=0,n=this.length,a=[]; i<n; i++) if(this[i]!=e) a[a.length]=this[i];
  this.length=0;for(i=0,n=a.length; i<n; i++) this[i]=a[i];a=null; return this;
};

/*-=< Date >=-*/
//datetime format
Date.prototype.format = function(format)
{
  var o = {
    "M+" : this.getMonth()+1, //month
    "d+" : this.getDate(),    //day
    "h+" : this.getHours(),   //hour
    "m+" : this.getMinutes(), //minute
    "s+" : this.getSeconds(), //second
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
    "S"  : this.getMilliseconds() //millisecond
  }
  if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
    (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  for(var k in o)if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,
      RegExp.$1.length==1 ? o[k] : 
        ("00"+ o[k]).substr((""+ o[k]).length));
  return format;
};

/*-=< Number >=-*/
if(typeof(Number.prototype.toFixed)!="function")
{
    Number.prototype.toFixed = function(d)
    {
        var s=this+"";if(!d)d=0;
        if(s.indexOf(".")==-1)s+=".";s+=new Array(d+1).join("0");
        if (new RegExp("^(-|\\+)?(\\d+(\\.\\d{0,"+ (d+1) +"})?)\\d*$").test(s))
        {
            var s="0"+ RegExp.$2, pm=RegExp.$1, a=RegExp.$3.length, b=true;
            if (a==d+2){a=s.match(/\d/g); if (parseInt(a[a.length-1])>4)
            {
                for(var i=a.length-2; i>=0; i--) {a[i] = parseInt(a[i])+1;
                if(a[i]==10){a[i]=0; b=i!=1;} else break;}
            }
            s=a.join("").replace(new RegExp("(\\d+)(\\d{"+d+"})\\d$"),"$1.$2");
        }if(b)s=s.substr(1);return (pm+s).replace(/\.$/, "");} return this+"";
    };
}

/*-=< Global >=-*/
if("undefined"==typeof(encodeURIComponent))encodeURIComponent=function(s){return escape(s);}

/*-=< String >=-*/
String.prototype.trim=function(){return this.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "")};
String.prototype.capitalize=function(){return this.charAt(0).toUpperCase() + this.substr(1);};
String.prototype.getByteLength=function(){return this.replace(/[^\x00-\xff]/g, "mm").length;};

String.prototype.subByte = function(n)
{
  if(this.getByteLength()<=n) return this;
  for(var i=Math.floor((n=n-2)/2), l=this.length; i<l; i++)
  if(this.substr(0,i).getByteLength()>=n)return this.substr(0,i) +"\u2026";
  return this;
}

String.prototype.parseQuery = function(name)
{
  var reg = new RegExp("(^|&|\\?)"+name+"=([^&]*)(&|$)","i"), r;
  if (r=this.match(reg)) return unescape(r[2]); return null;
}

String.prototype.format=function()
{
  if(arguments.length==0) return this;
  for(var s=this, i=0; i<arguments.length; i++)
    s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);
  return s;
}

String.format=function(str)
{
  if("string"!=typeof(str)||arguments.length<=1) return str;
  for(var i=1; i<arguments.length; i++)
    str=str.replace(new RegExp("\\{"+(i-1)+"\\}","g"), arguments[i]);
  return str;
};






/*-=< Meizz Class >=-*/
if(typeof(System)=="undefined")
{
  window.System={};
  System.extend=function(d,s){for(var i in s)d[i]=s[i];return d};
}
System.toHashCode=function(e)
{
  if(typeof(e.hashCode)!="undefined")return e.hashCode;
  return (e.hashCode=System.getUniqueId());
};
System.getType=function(e)
{
  if(typeof(e)!="object")return typeof(e);
  if(e=="[object Object]")return "object";
  if(/\[object\s+([^\s\]]+)\]/.test(e))return RegExp.$1;
  else return "object";
};
System.loadCssFile=function(cssPath, uniqueId)
{
  if(/\w+\.\w+(\?|$)/.test(cssPath))
  {
    if(!(typeof(uniqueId)=="string"&& uniqueId!=""))
    uniqueId = "MzCss_"+ cssPath.replace(/\W/g, "");
    if(document.getElementById(uniqueId)) return;

    var link  = document.createElement("LINK");
    link.href = cssPath; link.id = uniqueId;
    link.type = "text/css";
    link.rel  = "Stylesheet";
    uniqueId  = document.getElementsByTagName("HEAD")[0];
    uniqueId.insertBefore(link, uniqueId.firstChild);
  }
};
System.zIndexBase=
{
   "MzForm": 52000 //foused 56000-60000
  ,"dragLayer": 65000
  ,"popupLayer": 65001
};
System.disabledList={};


if(System.path)
{
  System.Object=System;
  System.resourcePath=System.path+"/System/_resource";


  //[System.prototype] extend
  var t=System.Extends(Object, "System");
  t.addEventListener=function(type, handle)
  {
    if(typeof(handle)!="function")
      throw new Error(this+" addEventListener: "+handle+" is not a function");
    if(!this._listeners) this._listeners={};
    var id=System.toHashCode(handle), t=this._listeners; 
    if(typeof(t[type])!="object") t[type]={}; t[type][id]=handle;
  }

  t.removeEventListener=function(type, handle)
  {
    if(!this._listeners)this._listeners={};var t=this._listeners;
    if(!t[type]) return; var id=System.toHashCode(handle);
    if( t[type][id])delete t[type][id];if(t[type])delete t[type];
  }

  t.dispatchEvent=function(e)
  {
    if(!this._listeners)this._listeners={};var i,t=this._listeners,p=e.type;
    e.target=e.srcElement=e.target||e.srcElement||this;e.currentTarget=this;
    if(this[p])this[p](e);if(typeof(t[p])=="object")
    for(i in t[p])t[p][i].call(null,e);return e.returnValue;
  }

  t.dispose=function()
  {
    if(this.hashCode) delete System._instances[this.hashCode];
    for(var i in this)if(typeof(this[i])!="function")delete this[i];
  }

  t.getHashCode=function()
  {
    if(!this.hashCode)
      System._instances[(this.hashCode=System.getUniqueId())]=this;
    return this.hashCode;
  }

  t.decontrol=function(){delete System._instances[this.hashCode];}
  t.toString=function(){return "[object "+(this._className||"Object")+"]";};

  System.Event=function(type){this.type=type;};
  t=System.Event.Extends(System,"System.Event");
  t.target=t.currentTarget=t.srcElement=null;
  t.returnValue=true;t.cancelBubble=false;t=null;
  System._prototypes["System.Event"]=System.Event;
}


//NameSpace: System.MzBrowser
System.MzBrowser=window["MzBrowser"]={};(function()
{
  if(MzBrowser.platform) return;
  var ua = window.navigator.userAgent;
  MzBrowser.platform = window.navigator.platform;

  MzBrowser.firefox = ua.indexOf("Firefox")>0;
  MzBrowser.opera = typeof(window.opera)=="object";
  MzBrowser.ie = !MzBrowser.opera && ua.indexOf("MSIE")>0;
  MzBrowser.mozilla = window.navigator.product == "Gecko";
  MzBrowser.netscape= window.navigator.vendor=="Netscape";
  MzBrowser.safari  = ua.indexOf("Safari")>-1;

  if(MzBrowser.firefox) var re = /Firefox(\s|\/)(\d+(\.\d+)?)/;
  else if(MzBrowser.ie) var re = /MSIE( )(\d+(\.\d+)?)/;
  else if(MzBrowser.opera) var re = /Opera(\s|\/)(\d+(\.\d+)?)/;
  else if(MzBrowser.netscape) var re = /Netscape(\s|\/)(\d+(\.\d+)?)/;
  else if(MzBrowser.safari) var re = /Version(\/)(\d+(\.\d+)?)/;
  else if(MzBrowser.mozilla) var re = /rv(\:)(\d+(\.\d+)?)/;

  if("undefined"!=typeof(re)&&re.test(ua))
    MzBrowser.version = parseFloat(RegExp.$2);
})();
//alert(MzBrowser.version);



//[MzElement object]
window.MzElement=System.MzElement={};

MzElement.check = function()
{
  for(var a=[], i=arguments.length-1; i>-1; i--)
  {
    var e=arguments[i]; a[i]=null
    if(("object"==typeof(e) && e.tagName) || e==window || e==document) a[i]=e;
    if ("string"==typeof(e) &&(e=document.getElementById(e)))a[i]=e;
  }
  return a.length<2 ? a[0] : a;
}
window["G"]=window["$"]=window["Mz$"]=MzElement.check;

MzElement.hide = function()
{
  for (var e=null, i=arguments.length-1; i>-1; i--)
  if(e=this.check(arguments[i])) e.style.display="none";
}

MzElement.show = function()
{
  for (var e=null, i=arguments.length-1; i>-1; i--)
  if(e=this.check(arguments[i])) e.style.display="";
}

MzElement.remove = function()
{
  for (var e=null, i=arguments.length-1; i>-1; i--)
  if(e=this.check(arguments[i])) e.parentNode.removeChild(e);
}

MzElement.searchByTagName = function(e, TAG)
{
  do if(e.tagName==TAG.toUpperCase())return e;
  while (e=e.parentNode); return null;
}

MzElement.realOffset = function(o)
{
  if(!o) return null; var e=o, x=y=l=t=0, doc=this.body();
  do{l+=e.offsetLeft||0; t+=e.offsetTop||0; e=e.offsetParent;}while(e);
  do{x+=o.scrollLeft||0; y+=o.scrollTop||0; o=o.parentNode;}while(o);
  return {"x":l-x+doc.scrollLeft, "y":t-y+doc.scrollTop};
}

MzElement.body = function()
{
  var W, H, SL, ST, SW, SH;
  var w=window, d=document, dd=d.documentElement;

  if(dd&&dd.clientWidth) W=dd.clientWidth;
  else if(w.innerWidth) W=w.innerWidth;
  else if(d.body) W=d.body.clientWidth;

  if(window.opera||MzBrowser.ie&&MzBrowser.version<5.5)H=d.body.clientHeight;
  else{if(dd&&dd.clientHeight) H=dd.clientHeight;
  else if(w.innerHeight) H=w.innerHeight;
  else if(d.body) H=d.body.clientHeight;}

  if(w.pageXOffset) SL=w.pageXOffset;
  else if(dd&&dd.scrollLeft) SL=dd.scrollLeft;
  else if(d.body) SL=d.body.scrollLeft;

  if(w.pageYOffset) ST=w.pageYOffset;
  else if(dd&&dd.scrollTop) ST=dd.scrollTop;
  else if(d.body) ST=d.body.scrollTop;

  if(dd&&dd.scrollWidth){SW=dd.scrollWidth; SH=dd.scrollHeight;}
  else if(d.body){SW=d.body.scrollWidth; SH=d.body.scrollHeight;}

  return {"scrollTop":ST,"scrollLeft":SL
    ,"scrollWidth":SW,"scrollHeight":SH
    ,"clientWidth":W,"clientHeight":H};
}

MzElement.hasClassName = function(element, className)
{
  if(!(element=this.check(element))) return;
  return element.className.split(" ").contains(className);
}

MzElement.addClassName = function(element, className)
{
  if(!(element=this.check(element))) return; var a=element.className.split(" ");
  if(!a.contains(className)) element.className=a.concat(className).join(" ");
}

MzElement.removeClassName = function(element, className)
{
  if(!(element=this.check(element))) return;
  var r=new RegExp("(^| )"+ className +"( |$)", "g");
  element.className=element.className.replace(r, "$2");
};

(function()
{
  if(System.scriptElement)
  {
    var src=System.scriptElement.src,s;
    if(s=src.parseQuery("include"))s.split(",").forEach(Include);
    if(s=src.parseQuery("using"))  s.split(",").forEach(Using);
  }
})();



//[prototye.js  baidu_jslib_base] 20070830
if (typeof BAIDU == "undefined") var BAIDU = {};

BAIDU.namespace = function()
{
  var a = arguments, o = null, d;
  for (var i=0; i<a.length; ++ i)
  {
    d = a[i].split(".");
    o = BAIDU;

    // BAIDU is implied, so it is ignored if it is included
    for (var j = (d[0] == "BAIDU") ? 1 : 0; j < d.length; ++ j)
    {
      o[d[j]] = o[d[j]] || {};
      o = o[d[j]];
    }
  }
  return o;
};

BAIDU.namespace("util","widget");

Object.extend= function(d,s){for(var i in s)d[i]=s[i];return d};
Object.clone = function(o){return Object.extend({}, o);}

var Class = function()
{
  var _class = function()
  {
    System.call(this);
    this.initialize.apply(this, arguments);
  };
  _class.Extends(System, "Class");

  for(var i=0; i<arguments.length; i++)
    Object.extend(_class.prototype, arguments[i].prototype)

  _class.child = function(){return new Class(this);};
  _class.extend = function(source){Object.extend(_class.prototype, source);};
  return _class;
};

Function.prototype.bind = function()
{
  for(var args=[], i=0, n=arguments.length; i<n; i++) args.push(arguments[i]);
  var me = this, object = args[0];
  return function(){return me.apply(object, args);}
};
              
Function.prototype.bindAsEventListener = function(object)
{
  var me = this;
  return function(event){me.call(object, event||window.event);}
};

var Try = {};
Try.these = function()
{
  for(var result, i=0; i<arguments.length; i++)
  {
    try{result = arguments[i](); break;}catch(ex){}
  }
  return result;
};
