package com.ultrapower.assess.manager;

import org.apache.xpath.operations.String;

import com.bidlink.core.commons.BaseHibernateDao;

public class AssessSendManager extends BaseHibernateDao{
	public List getAssessSend (){
		String sql = "";
		List list = this.getSession().createSQLQuery(sql).list();
		return list;
		
	}
	
	

}
