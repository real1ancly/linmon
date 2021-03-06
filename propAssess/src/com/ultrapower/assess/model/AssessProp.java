package com.ultrapower.assess.model;

import net.sf.saxon.exslt.Date;

import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;

/**
 * AssessProp entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessProp extends BaseModel {

	// Fields

	private AssessTemplate assessTemplate;
	private AssessProp parent;
	private String kpiId;
	private String propName;
	private Long propType;
	private String propExpression;
	private String propUnit;
	private String scoreExpression;
	private String period;
	private Long status;
	private String updateAccount;
	private Date updateTime;
	private Long sort;
	private String remarks;
	private Set<AssessResult> assessResults = new HashSet<AssessResult>(0);
	private Set<AssessProp> assessProps = new HashSet<AssessProp>(0);

	// Constructors

	/** default constructor */
	public AssessProp() {
	}

	/** full constructor */
	public AssessProp(AssessTemplate assessTemplate, AssessProp parent,
			String kpiId, String propName, Long propType,
			String propExpression, String propUnit, String scoreExpression,
			String period, Long status, String updateAccount, Date updateTime,
			Long sort, String remarks, Set<AssessResult> assessResults, Set<AssessProp> assessProps) {
		this.assessTemplate = assessTemplate;
		this.parent = parent;
		this.kpiId = kpiId;
		this.propName = propName;
		this.propType = propType;
		this.propExpression = propExpression;
		this.propUnit = propUnit;
		this.scoreExpression = scoreExpression;
		this.period = period;
		this.status = status;
		this.updateAccount = updateAccount;
		this.updateTime = updateTime;
		this.sort = sort;
		this.remarks = remarks;
		this.assessResults = assessResults;
		this.assessProps = assessProps;
	}

	// Property accessors


	public AssessTemplate getAssessTemplate() {
		return this.assessTemplate;
	}

	public void setAssessTemplate(AssessTemplate assessTemplate) {
		this.assessTemplate = assessTemplate;
	}

	public AssessProp getParent() {
		return parent;
	}

	public void setParent(AssessProp parent) {
		this.parent = parent;
	}

	public String getKpiId() {
		return this.kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getPropName() {
		return this.propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Long getPropType() {
		return this.propType;
	}

	public void setPropType(Long propType) {
		this.propType = propType;
	}

	public String getPropExpression() {
		return this.propExpression;
	}

	public void setPropExpression(String propExpression) {
		this.propExpression = propExpression;
	}

	public String getPropUnit() {
		return this.propUnit;
	}

	public void setPropUnit(String propUnit) {
		this.propUnit = propUnit;
	}

	public String getScoreExpression() {
		return this.scoreExpression;
	}

	public void setScoreExpression(String scoreExpression) {
		this.scoreExpression = scoreExpression;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
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

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<AssessResult> getAssessResults() {
		return assessResults;
	}

	public void setAssessResults(Set<AssessResult> assessResults) {
		this.assessResults = assessResults;
	}

	public Set<AssessProp> getAssessProps() {
		return assessProps;
	}

	public void setAssessProps(Set<AssessProp> assessProps) {
		this.assessProps = assessProps;
	}




}