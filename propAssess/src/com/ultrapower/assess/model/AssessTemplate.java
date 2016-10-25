package com.ultrapower.assess.model;

import net.sf.saxon.exslt.Date;

import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;

/**
 * AssessTemplate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessTemplate extends BaseModel {

	// Fields

	private String templateName;
	private Long status;
	private String assignee;
	private String updateAccount;
	private Date updateTime;
	private String remarks;
	private Set<AssessRecords> assessRecordses = new HashSet<AssessRecords>(0);
	private Set<AssessProp> assessProps = new HashSet<AssessProp>(0);

	// Constructors

	/** default constructor */
	public AssessTemplate() {
	}

	/** full constructor */
	public AssessTemplate(String templateName, Long status, String assignee,
			String updateAccount, Date updateTime, String remarks,
			Set<AssessRecords> assessRecordses, Set<AssessProp> assessProps) {
		this.templateName = templateName;
		this.status = status;
		this.assignee = assignee;
		this.updateAccount = updateAccount;
		this.updateTime = updateTime;
		this.remarks = remarks;
		this.assessRecordses = assessRecordses;
		this.assessProps = assessProps;
	}

	// Property accessors


	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getUpdateAccount() {
		return this.updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<AssessRecords> getAssessRecordses() {
		return assessRecordses;
	}

	public void setAssessRecordses(Set<AssessRecords> assessRecordses) {
		this.assessRecordses = assessRecordses;
	}

	public Set<AssessProp> getAssessProps() {
		return assessProps;
	}

	public void setAssessProps(Set<AssessProp> assessProps) {
		this.assessProps = assessProps;
	}
	
	

	
}