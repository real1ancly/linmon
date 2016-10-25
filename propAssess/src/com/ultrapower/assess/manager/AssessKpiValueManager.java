package com.ultrapower.assess.manager;

import org.apache.xpath.operations.String;

import com.bidlink.core.commons.BaseHibernateDao;
import com.ultrapower.assess.model.AssessKpiValue;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.model.AssessRecords;

public class AssessKpiValueManager extends BaseHibernateDao<AssessKpiValue> {

	@SuppressWarnings("unchecked")
	public AssessKpiValue getKpiValueByRecordAndProp(AssessRecords records,
			AssessProp prop) {
		String beginTime = records.getBeginTime().toString();
		beginTime = beginTime.substring(0, beginTime.length()-2);
		String endTime = records.getEndTime().toString();
		endTime = endTime.substring(0, endTime.length()-2);
		String hql = "from AssessKpiValue a where a.ciId='"
				+ records.getAssessObject().getId() + "' and a.kpiId='"
				+ prop.getKpiId() + "' and a.dataBeginTime between to_date('"+beginTime+"','YYYY-MM-dd hh24:mi:ss') and to_date('"+endTime+"','YYYY-MM-dd hh24:mi:ss') order by writeTime desc";
		List<AssessKpiValue> list = this.getSession().createQuery(hql).list();
		return list.size() > 0 ? list.get(0) : null;
	}
	/**
	 * 得到指标值
	 * @param records
	 * @param prop
	 * @return
	 * wangjing
	 */
	public String getKpiValueByRecordAndProp2(AssessRecords records,
			AssessProp prop) {
		String beginTime = records.getBeginTime().toString();
		beginTime = beginTime.substring(0, beginTime.length()-2);
		String endTime = records.getEndTime().toString();
		endTime = endTime.substring(0, endTime.length()-2);
		String hql = "select kpiValue from AssessKpiValue a where a.ciId='"
			+ records.getAssessObject().getId() + "' and a.kpiId='"
			+ prop.getKpiId() + "' and a.dataBeginTime between to_date('"+beginTime+"','YYYY-MM-dd hh24:mi:ss') and to_date('"+endTime+"','YYYY-MM-dd hh24:mi:ss') order by writeTime desc";
	    List<String> slist = this.getSession().createQuery(hql).list();
		return slist.size() > 0 ? slist.get(0).toString() : "0";
	}
}
