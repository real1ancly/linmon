package com.ultrapower.assess.model;

import net.sf.saxon.exslt.Date;

import org.apache.xpath.operations.String;

/**
 * 后台 用户
 * @author zhongxiaoqi
 *
 */
public class Users extends BaseModel {

	private static final long serialVersionUID = 1L;
    
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 登录密码
	 */
	private String loginPwd;
	
	/**
	 * 真实姓名
	 */
	private String name;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 状态
	 */
	private Integer status;

	public Users() {
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}