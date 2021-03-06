package com.ultrapower.assess.model;

import org.apache.xpath.operations.String;

/**
 * AssessResult entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessResult extends BaseModel {

	// Fields

	private AssessRecords assessRecords;
	private AssessProp assessProp;
	private String kpiValue;
	private Double score;
	private Long state;
	

	// Constructors

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	/** default constructor */
	public AssessResult() {
	}

	/** minimal constructor */
	public AssessResult(AssessRecords assessRecords) {
		this.assessRecords = assessRecords;
	}

	/** full constructor */
	public AssessResult(AssessRecords assessRecords, AssessProp assessProp,
			String kpiValue, Double score) {
		this.assessRecords = assessRecords;
		this.assessProp = assessProp;
		this.kpiValue = kpiValue;
		this.score = score;
	}

	// Property accessors


	public AssessRecords getAssessRecords() {
		return this.assessRecords;
	}

	public void setAssessRecords(AssessRecords assessRecords) {
		this.assessRecords = assessRecords;
	}

	public AssessProp getAssessProp() {
		return this.assessProp;
	}

	public void setAssessProp(AssessProp assessProp) {
		this.assessProp = assessProp;
	}

	public String getKpiValue() {
		return this.kpiValue;
	}

	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}