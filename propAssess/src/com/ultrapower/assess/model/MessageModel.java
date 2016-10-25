package com.ultrapower.assess.model;

import org.apache.xpath.operations.String;

/**
 * control层构造MessageModel,返回给客户端json对象
 * 
 * @author f
 * 
 */
public class MessageModel {

	/**
	 * 是否操作成功STATUS_FIELD
	 */
	private boolean success;

	/**
	 * 操作提示语
	 */
	private String msg = "";

	/**
	 * 重定向url地址
	 */
	private String rewriteUrl = "";

	/**
	 * 
	 * @param success
	 *            是否操作成功
	 * @param msg
	 *            操作提示语
	 * @param rewriteUrl
	 *            重定向url地址
	 */
	public MessageModel(boolean success, String msg, String rewriteUrl) {
		this.success = success;
		this.msg = msg;
		this.rewriteUrl = rewriteUrl;
	}

	/**
	 * 
	 * @param success
	 *            是否操作成功
	 */
	public MessageModel(boolean success) {
		this.success = success;
	}

	/**
	 * 
	 * @param success
	 *            是否操作成功
	 * @param msg
	 *            操作提示语
	 */
	public MessageModel(boolean success, String msg) {
		this.success = success;
		this.msg = msg;

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRewriteUrl() {
		return rewriteUrl;
	}

	public void setRewriteUrl(String rewriteUrl) {
		this.rewriteUrl = rewriteUrl;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
