package com.ultrapower.assess.model;

import jsx3.lang.Object;
import net.sf.saxon.exslt.Date;

import org.apache.xpath.operations.String;

/**
 * AssessKpiValue entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssessKpiValue extends BaseModel {

	// Fields

	private String ciId;
	private String kpiId;
	private Date dataBeginTime;
	private String kpiValue;
	private Date writeTime;
	private Long id;

	// Constructors

	/** default constructor */
	public AssessKpiValue() {
	}

	/** minimal constructor */
	public AssessKpiValue(String kpiId, Date dataBeginTime) {
		this.kpiId = kpiId;
		this.dataBeginTime = dataBeginTime;
	}

	/** full constructor */
	public AssessKpiValue(String ciId, String kpiId, Date dataBeginTime,
			String kpiValue, Date writeTime) {
		this.ciId = ciId;
		this.kpiId = kpiId;
		this.dataBeginTime = dataBeginTime;
		this.kpiValue = kpiValue;
		this.writeTime = writeTime;
	}

	// Property accessors


	public String getCiId() {
		return this.ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	public String getKpiId() {
		return this.kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public Date getDataBeginTime() {
		return this.dataBeginTime;
	}

	public void setDataBeginTime(Date dataBeginTime) {
		this.dataBeginTime = dataBeginTime;
	}

	public String getKpiValue() {
		return this.kpiValue;
	}

	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}

	public Date getWriteTime() {
		return this.writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	@Override
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciId == null) ? 0 : ciId.hashCode());
		result = prime * result
				+ ((dataBeginTime == null) ? 0 : dataBeginTime.hashCode());
		result = prime * result + ((kpiId == null) ? 0 : kpiId.hashCode());
		return result;
	}

	@Override
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssessKpiValue other = (AssessKpiValue) obj;
		if (ciId == null) {
			if (other.ciId != null)
				return false;
		} else if (!ciId.equals(other.ciId))
			return false;
		if (dataBeginTime == null) {
			if (other.dataBeginTime != null)
				return false;
		} else if (!dataBeginTime.equals(other.dataBeginTime))
			return false;
		if (kpiId == null) {
			if (other.kpiId != null)
				return false;
		} else if (!kpiId.equals(other.kpiId))
			return false;
		return true;
	}

	@Override
	@Override
	public String toString() {
		return "AssessKpiValue [ciId=" + ciId + ", kpiId=" + kpiId
				+ ", dataBeginTime=" + dataBeginTime + ", kpiValue=" + kpiValue
				+ ", writeTime=" + writeTime + ", id=" + id + "]";
	}
}