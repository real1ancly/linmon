package com.ultrapower.assess.model;

import net.sf.saxon.exslt.Date;

import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;

/**
 * AssessRecords entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessRecords extends BaseModel {

	// Fields

	private AssessObject assessObject;
	private AssessTemplate assessTemplate;
	private AssessSchedu assessSchedu;
	private Date createTime;
	private Date beginTime;
	private Date endTime;
	private Long period;
	private Date resultcreatTime;
	private String releaseId;
	private String releaseName;
	private Long assessTimes;
	private String assessId;
	private Date assessDate;
	private Double assessTotal;
	private Set assessResults = new HashSet(0);
	private String scheduName;
	private Long state;
	

	// Constructors

	public Long getState() {
		return state;
	}

	public void setState(Long status) {
		this.state = status;
	}

	public AssessSchedu getAssessSchedu() {
		return assessSchedu;
	}

	public void setAssessSchedu(AssessSchedu assessSchedu) {
		this.assessSchedu = assessSchedu;
	}

	public String getScheduName() {
		return scheduName;
	}

	public void setScheduName(String scheduName) {
		this.scheduName = scheduName;
	}

	/** default constructor */
	public AssessRecords() {
	}

	/** minimal constructor */
	public AssessRecords(AssessTemplate assessTemplate) {
		this.assessTemplate = assessTemplate;
	}

	/** full constructor */
	public AssessRecords(AssessObject assessObject,
			AssessTemplate assessTemplate, AssessSchedu assessSchedu,
			Date createTime, Date beginTime, Date endTime, Long period,
			Date resultcreatTime, String releaseId, String releaseName,
			Long assessTimes, String assessId, Date assessDate,
			Double assessTotal, String scheduName, Set assessResults) {
		this.assessObject = assessObject;
		this.assessTemplate = assessTemplate;
		this.assessSchedu = assessSchedu;
		this.createTime = createTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.period = period;
		this.resultcreatTime = resultcreatTime;
		this.releaseId = releaseId;
		this.releaseName = releaseName;
		this.assessTimes = assessTimes;
		this.assessId = assessId;
		this.assessDate = assessDate;
		this.assessTotal = assessTotal;
		this.scheduName = scheduName;
		this.assessResults = assessResults;
	}


	// Property accessors


	public AssessObject getAssessObject() {
		return this.assessObject;
	}

	public void setAssessObject(AssessObject assessObject) {
		this.assessObject = assessObject;
	}


	public AssessTemplate getAssessTemplate() {
		return this.assessTemplate;
	}

	public void setAssessTemplate(AssessTemplate assessTemplate) {
		this.assessTemplate = assessTemplate;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getPeriod() {
		return this.period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public Date getResultcreatTime() {
		return this.resultcreatTime;
	}

	public void setResultcreatTime(Date resultcreatTime) {
		this.resultcreatTime = resultcreatTime;
	}

	public String getReleaseId() {
		return this.releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public String getReleaseName() {
		return this.releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public Long getAssessTimes() {
		return this.assessTimes;
	}

	public void setAssessTimes(Long assessTimes) {
		this.assessTimes = assessTimes;
	}

	public String getAssessId() {
		return this.assessId;
	}

	public void setAssessId(String assessId) {
		this.assessId = assessId;
	}
	public Date getAssessDate() {
		return this.assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}

	public Double getAssessTotal() {
		return this.assessTotal;
	}

	public void setAssessTotal(Double assessTotal) {
		this.assessTotal = assessTotal;
	}
	
	public Set getAssessResults() {
		return this.assessResults;
	}

	public void setAssessResults(Set assessResults) {
		this.assessResults = assessResults;
	}
}