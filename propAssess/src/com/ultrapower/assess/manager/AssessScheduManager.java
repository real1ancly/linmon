package com.ultrapower.assess.manager;

import org.apache.xpath.operations.String;

import com.bidlink.core.commons.BaseHibernateDao;
import com.ultrapower.assess.model.AssessSchedu;

@SuppressWarnings("unchecked")
public class AssessScheduManager extends BaseHibernateDao<AssessSchedu>{
	
	public AssessSchedu getScheduObject(String id){
		AssessSchedu assessedu = new AssessSchedu();
		return assessedu;
	}
	
	public  List<AssessSchedu> getAllSchedu(){
		
		String hql="from AssessSchedu where resultcreatetime<sysdate order by resultcreatetime desc";
		
		return this.getSession().createQuery(hql).list();
		
	}
	
	public List<AssessSchedu> getMaxEndtimeSchedu(){
		String hql ="from AssessSchedu t  where t.resultcreatetime < sysdate and rownum=1 order by t.resultcreatetime desc ";
		return this.getSession().createQuery(hql).list();
		
	}
	
	
}
