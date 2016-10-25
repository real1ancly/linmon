package com.ultrapower.assess.model;

import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;

/**
 * AssessObject entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessObject extends BaseModel{

	// Fields

	private String foreignId;
	private String objectCode;
	private String objectName;
	private Long objectType;
	private String assignee;
	private Long status;
	private Set assessRecordses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssessObject() {
	}

	/** full constructor */
	public AssessObject(String foreignId, String objectCode, String objectName,
			Long objectType, String assignee, Long status, Set assessRecordses) {
		this.foreignId = foreignId;
		this.objectCode = objectCode;
		this.objectName = objectName;
		this.objectType = objectType;
		this.assignee = assignee;
		this.status = status;
		this.assessRecordses = assessRecordses;
	}

	// Property accessors


	public String getForeignId() {
		return this.foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Long getObjectType() {
		return this.objectType;
	}

	public void setObjectType(Long objectType) {
		this.objectType = objectType;
	}

	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Set getAssessRecordses() {
		return this.assessRecordses;
	}

	public void setAssessRecordses(Set assessRecordses) {
		this.assessRecordses = assessRecordses;
	}

}