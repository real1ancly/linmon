package com.ultrapower.assess.model;

import org.apache.xpath.operations.String;

/**
 * AssessMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessMenu extends BaseModel{

	// Fields

	private String name;
	private String url;
	private String remark;
	private Long sort;

	// Constructors

	/** default constructor */
	public AssessMenu() {
	}

	/** full constructor */
	public AssessMenu(String name, String url, String remark, Long sort) {
		this.name = name;
		this.url = url;
		this.remark = remark;
		this.sort = sort;
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

}