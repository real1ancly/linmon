/*---------------------------------------------------------------------------*\
|  Subject:    UserData Manager (IE only)
|  NameSpace:  System.Net.MzUserData
|  Author:     meizz
|  Created:    2006-09-11
|  Version:    2007-01-18
|-----------------------------------
|  MSN: huangfr@msn.com QQ:112889082 Copyright (c) meizz
|  http://www.meizz.com/jsframework/ MIT-style license
|  The above copyright notice and this permission notice shall be
|  included in all copies or substantial portions of the Software
\*---------------------------------------------------------------------------*/

function MzUserData()
{
  var now = new Date();
  now.setDate(now.getDate()+3); //Save 3 days
  this.expires = now;
}
t=MzUserData.Extends(System, "MzUserData");

MzUserData._encode=function(key)
{
  return key.replace(/\W/g, "_");
};

//key: UserData key
//value: UserData value
t.add  = function(key, value)
{
  try
  {
    var t=System.scriptElement;
    t.load(MzUserData._encode(key));
    t.setAttribute("code", value);
    t.setAttribute("version", System.currentVersion);
    t.expires=this.expires.toUTCString();
    t.save(MzUserData._encode(key));
    return  t.getAttribute("code");
  }
  catch (ex){}
};

//key: UserData key
t.get  = function(key)
{
  try
  {
    var t=System.scriptElement;
    t.load(MzUserData._encode(key));
    if(System.currentVersion!=t.getAttribute("version")){
    if(t.getAttribute("code"))System.deleteUserData(key);
      return null;} return t.getAttribute("code");
  }
  catch (ex){return null;}
};

//name: UserData name
t.remove  = function(name)
{
  try
  {
    var t=System.scriptElement;
  	t.load(MzUserData._encode(key));
    t.expires = new Date(315532799000).toUTCString();
    t.save(MzUserData._encode(key));
  }
  catch (ex){}
};

t.setExpires = function(milliseconds)
{
  var now = new Date();
  now.setTime(now.getTime() + milliseconds);
  this.expires = now;
};