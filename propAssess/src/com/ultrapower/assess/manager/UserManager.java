package com.ultrapower.assess.manager;

import jsx3.lang.Object;

import org.apache.xpath.operations.String;

import com.bidlink.core.commons.BaseHibernateDao;
import com.bidlink.core.utils.StringUtil;
import com.ultrapower.assess.model.Users;

/**
 * 用户 操作类
 * @author zhongxiaoqi
 *
 */
public class UserManager extends BaseHibernateDao<Users> {
    
	/**
	 * 根据登录的用户名和密码 获取用户对象
	 * @param name 登录名
	 * @param pwd 密码
	 * @return
	 */
	public Users getUserByLogin(String name,String pwd){
		String hql = "from Users user where user.loginName=? and user.loginPwd=? ";
		List list = this.find(hql, new Object[] { name,StringUtil.encodePassword(pwd, "MD5") });
		return list.isEmpty() ? null : (Users)list.get(0);
	}
	
	/**
	 * 获得目前用户的个数
	 */
	public int getCurrentUserNum(){
		String hql="from Users";
		return this.find(hql).size();
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.encodePassword("123456", "MD5"));
	}
	
}
