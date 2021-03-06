package com.ultrapower.assess.model;

import net.sf.saxon.exslt.Date;

import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;

/**
 * AssessSchedu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessSchedu extends BaseModel {

	// Fields

	private Date createtime;
	private Long type;
	private Date begintime;
	private Date endtime;
	private Long perior;
	private String name;
	private Date resultcreatetime;
	private Long  state;
	private Set assessRecordses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssessSchedu() {
	}

	/** minimal constructor */
	public AssessSchedu(Date createtime, Date begintime, Date endtime,
			Long perior, String name, Date resultcreatetime,Long state) {
		this.createtime = createtime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.perior = perior;
		this.name = name;
		this.resultcreatetime = resultcreatetime;
		this.state =state;
	}

	/** full constructor */
	public AssessSchedu(Date createtime, Long type, Date begintime,
			Date endtime, Long perior, String name, Date resultcreatetime,
			Long state,Set assessRecordses) {
		this.createtime = createtime;
		this.type = type;
		this.begintime = begintime;
		this.endtime = endtime;
		this.perior = perior;
		this.name = name;
		this.resultcreatetime = resultcreatetime;
		this.assessRecordses = assessRecordses;
		this.state = state;
	}

	// Property accessors


	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Date getBegintime() {
		return this.begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Long getPerior() {
		return this.perior;
	}

	public void setPerior(Long perior) {
		this.perior = perior;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getResultcreatetime() {
		return this.resultcreatetime;
	}

	public void setResultcreatetime(Date resultcreatetime) {
		this.resultcreatetime = resultcreatetime;
	}

	public Set getAssessRecordses() {
		return this.assessRecordses;
	}

	public void setAssessRecordses(Set assessRecordses) {
		this.assessRecordses = assessRecordses;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

}