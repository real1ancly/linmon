package com.ultrapower.assess.model;

import jsx3.lang.Object;

import org.apache.xpath.operations.String;

import com.bidlink.core.commons.BaseObject;

/**
 * Model基础类,要求每个model必须自己的唯一标识属性id
 * 
 * @author wangjing
 * 
 */
public class BaseModel extends BaseObject implements Serializable {

	protected String id;

	public String getId() {
		if (id != null && id.trim().equals(""))
			return null;
		else
			return this.id;
	}

	public void setId(String id) {
		if (id != null && id.trim().equals("")) {
			this.id = null;
		} else {
			this.id = id;
		}
	}

	@Override
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
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
		final BaseModel other = (BaseModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	@Override
	public String toString() {
		return getClass().getName() + ":" + String.valueOf(this.getId());
	}

}
