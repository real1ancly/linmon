package com.ultrapower.assess.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.xpath.operations.String;

import com.bidlink.core.Constants;
import com.bidlink.core.commons.BaseHibernateDao;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.assess.model.AssessTemplate;
import com.ultrapower.assess.util.PageUtils;

/**
 * 考核模板 操作类
 * 
 * @author wangjing
 * 
 */
public class AssessTemplateManager extends BaseHibernateDao<AssessTemplate> {
	@SuppressWarnings("unchecked")
	public List<AssessTemplate> findUseALL() {
		return this.getSession()
				.createQuery(" from AssessTemplate a where a.status in (1,2)")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<AssessTemplate> findByStatus(String status) {
		return this
				.getSession()
				.createQuery(
						" from AssessTemplate a where a.status in ( " + status+" )")
				.list();
	}
	@SuppressWarnings("unchecked")
	public List<AssessTemplate> findByStatus(Integer status) {
		return this
				.getSession()
				.createQuery(
						" from AssessTemplate a where a.status = "+status)
				.list();
	}

	public Page getPageObj(HttpServletRequest request) {
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		String hql = " from AssessTemplate a where a.status in (1,2)";
		Page pageObj = this
				.pagedQuery(hql, pageNo, Constants.DEFAULT_PAGE_SIZE);
		return pageObj;
	}

	/*
	 * 模板下发页默认表格数据 biyu
	 */
	public Page getPageSend(HttpServletRequest request) {
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		String hql = "select a.assessTemplate.templateName ,a.assessTemplate.id,max(a.createTime) as createTime,a.releaseName from AssessRecords "
				+ "a group by a.assessTemplate.id,a.releaseName,a.assessTemplate.templateName,a.assessTemplate.id";
	    //	String hqltest = "select s.create_time ,a.template_name,s.release_name from assess_records s,assess_template a where s.create_time in (select max(rs.create_time) create_time from assess_records rs group by rs.template_id) and a.id = s.template_id ";
		// "select t3.assessTemplate.name, t4.assessTemplate.id,t4.creatTime,t4.releaseName from AssessTemplate t3,select t1.creatTime,t1.assessTemplate.id, t2.releaseName from AssessRecords t2,(select t.assessTemplate.id, max(t.createTime) creatTime "
		// +
		// "from AssessRecords t group by t.assessTemplate.id t1 where t1.creatTime = t2.t.assessTemplate.id and t2.assessTemplate.id = t1.assessTemplate.id) t4 where t3.id = t4.assessTemplate.id";

		Page pageObj = this
				.pagedQuery(hql, pageNo, Constants.DEFAULT_PAGE_SIZE);
		return pageObj;
	}

	@SuppressWarnings("unchecked")
	public AssessTemplate getAssessTemplateById(String id) {
		List<AssessTemplate> list = this.getSession()
				.createQuery("from AssessTemplate a where a.id ='" + id + "'")
				.list();
		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<String> getIdsByName(String templateName) {
		List<String> idList = new ArrayList<String>();
		if (templateName != null && !"".equals(templateName)) {
			List<AssessTemplate> list = this.getSession().createQuery("from AssessTemplate a where a.templateName = '"+ templateName + "'").list();
			for (int i = 0; i < list.size(); i++) {
				idList.add(list.get(i).getId());
			}
			System.out.println("查询模板ID的SQL语句:"
					+ "from AssessTemplate a where a.templateName = '"
					+ templateName + "'");
			return idList;
		}

		return null;
	}

}
