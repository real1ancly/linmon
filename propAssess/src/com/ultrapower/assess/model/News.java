package com.ultrapower.assess.model;

import net.sf.saxon.exslt.Date;

import org.apache.xpath.operations.String;

/**
 * 后台 新闻信息
 * 
 * @author zhongxiaoqi
 */
public class News extends BaseModel {

	/**
	 * 更新人
	 */
	private Users updateUser;

	/**
	 * 创建人
	 */
	private Users createUser;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 来源
	 */
	private String docSource;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 类型 1项目动态 2行业资讯 3政策法规 4申报动态 5专家视点 6项目展示
	 */

	private Long type;

	/**
	 * 点击量
	 */
	private Integer count;

	private Long isYZ;

	private String imageUrl;
	
	private Integer seq;

	public News() {
	}


	public Users getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Users updateUser) {
		this.updateUser = updateUser;
	}

	public Users getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Users createUser) {
		this.createUser = createUser;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDocSource() {
		return docSource;
	}

	public void setDocSource(String docSource) {
		this.docSource = docSource;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getIsYZ() {
		return isYZ;
	}

	public void setIsYZ(Long isYZ) {
		this.isYZ = isYZ;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}