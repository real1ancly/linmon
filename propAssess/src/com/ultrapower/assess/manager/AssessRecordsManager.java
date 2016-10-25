package com.ultrapower.assess.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.xpath.operations.String;

import com.bidlink.core.Constants;
import com.bidlink.core.commons.BaseHibernateDao;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.assess.model.AssessRecords;
import com.ultrapower.assess.util.PageUtils;

/**
 * 
 * @author yangsiyi
 * 
 */
public class AssessRecordsManager extends BaseHibernateDao<AssessRecords> {

	@SuppressWarnings("unchecked")
	public List<AssessRecords> getNeedCalculateRecordsList() {
		// FISHTODOH <完成待测试>查询需要计算的Records
		// return this.getSession().createQuery("查询条件").list();
		String hql = "from AssessRecords a where a.assessTimes=0 and a.assessId is null and a.resultcreatTime<sysdate";
		return this.getSession().createQuery(hql).list();
	}

	/*
	 * 获得已有考核信息
	 */

	public Page getScheduName(HttpServletRequest request) {
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		String hql = " from AssessSchedu a where a.state=0 order by a.createtime desc";
		Page pageObj = this
				.pagedQuery(hql, pageNo, 10);
		
		return pageObj;
	}

	/*
	 * 判断考核对象是否已存在于考核记录
	 */
	public boolean getHadRedordByObj(String scheduid, String objid) {

		String hql = "from AssessRecords ar where ar.assessObject.id='" + objid
				+ "' and ar.assessSchedu.id='" + scheduid+"'";
		int i = this.getSession().createQuery(hql).list().size();
		if (i == 0) {
			return false; // 不存在
		}

		return true; // 存在
	}

	/*
	 * 获得下发记录
	 */
	public Page getSendRecordObj(HttpServletRequest request) {

		// String scheduName = request.getParameter("assessName");
		String objectName = request.getParameter("objectName");
		String period = request.getParameter("period");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String scheduid = request.getParameter("an");
		String tempId = request.getParameter("assessTempId");
		String scheduName = request.getParameter("scheduName");
		int pageNo = PageUtils.populatePageInfoFromRequest(request);

		StringBuffer sb = new StringBuffer();
		sb.append("from AssessRecords ar where state<>2 or state is null ");
		if (objectName != null && !"".equals(objectName)) {
			sb.append(" and ar.assessObject.objectName like '%" + objectName
					+ "%'");
		}

		if (period != null && !"".equals(period)) {

			sb.append(" and ar.period=" + period);
		}

		if (beginTime != null && !"".equals(beginTime)) {
			sb.append(" and to_char(ar.beginTime,'yyyy-mm-dd')>='" + beginTime
					+ "'");
		}

		if (endTime != null && !"".equals(endTime)) {
			sb.append(" and to_char(ar.endTime,'yyyy-mm-dd')<='" + endTime
					+ "'");
		}

		if (tempId != null && !"".equals(tempId)) {
			sb.append(" and ar.assessTemplate.id ='" + tempId + "'");
		}

		if (scheduid != null && !"".equals(scheduid)) {

			sb.append(" and ar.assessSchedu.id ='" + scheduid + "'");
		}

		if (scheduName != null && !"".equals(scheduName)) {
			sb.append(" and ar.assessSchedu.name like '%" + scheduName + "%'");
		}

		sb.append(" order by ar.createTime desc");
		String hql = sb.toString();
		Page pageObj = this
				.pagedQuery(hql, pageNo, 10);

		return pageObj;

	}

	public Page getPageObj(HttpServletRequest request,
			List<String> templateList, List<String> objectList) {

		int pageNo = PageUtils.populatePageInfoFromRequest(request);

		// String templateName = request.getParameter("templateName") == null ?
		// ""
		// : request.getParameter("templateName");
		// String objectName = request.getParameter("objectName") == null ? ""
		// : request.getParameter("objectName");
		String checkCyrcle = request.getParameter("checkCyrcle") == null ? ""
				: request.getParameter("checkCyrcle");
		String beginTime = request.getParameter("beginTime") == null ? ""
				: request.getParameter("beginTime");
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime");

		// AssessObjManager assessObjManager = new AssessObjManager();
		// AssessTemplateManager assessTemplateManager = new
		// AssessTemplateManager();
		// List<String> templateList = assessTemplateManager
		// .getIdsByName(templateName);
		// List<String> objectList = assessObjManager.getIdsByName(objectName);
		StringBuffer sb = new StringBuffer();
		sb.append("from AssessRecords a where state<>2 or state is null ");
		if (objectList != null) {
			String objIds = "";
			if (objectList.size() == 0) {
				objIds = "''";
			}
			for (int i = 0; i < objectList.size(); i++) {
				if (i < objectList.size() - 1) {
					objIds += "'" + objectList.get(i).toString() + "',";
				} else {
					objIds += "'" + objectList.get(i).toString() + "'";
				}
			}
			System.out.println("ObjectID为：" + objIds);
			sb.append("and a.assessObject in (" + objIds + ") ");
		}
		if (templateList != null) {
			String tempIds = "";
			if (templateList.size() == 0) {
				tempIds = "''";
			}
			for (int j = 0; j < templateList.size(); j++) {
				if (j < templateList.size() - 1) {
					tempIds += "'" + templateList.get(j).toString() + "',";
				} else {
					tempIds += "'" + templateList.get(j).toString() + "'";
				}
			}
			System.out.println("templateId为:" + tempIds);
			sb.append("and a.assessTemplate in (" + tempIds + ") ");
		}
		if (checkCyrcle != null && !"".equals(checkCyrcle)) {
			sb.append("and a.period =" + checkCyrcle + " ");
		}
		if (beginTime != null && !"".equals(beginTime)) {
			sb.append(" and to_char(a.beginTime,'yyyy-MM-dd HH24:mi:ss')>='"
					+ beginTime + "'");
		}
		if (endTime != null && !"".equals(endTime)) {
			sb.append(" and to_char(a.endTime,'yyyy-MM-dd HH24:mi:ss')<='"
					+ endTime + "'");
		}

		String hql = sb.toString();
		Page pageObj = this
				.pagedQuery(hql, pageNo, Constants.DEFAULT_PAGE_SIZE);
		return pageObj;
	}

	@SuppressWarnings("unchecked")
	public List<AssessRecords> getHisResult(List<String> objectList,
			List<String> templateList, String checkCyrcle, String beginTime,
			String endTime) {

		StringBuffer sb = new StringBuffer();
		sb.append("from AssessRecords a where 1=1 ");
		if (objectList != null) {
			String objIds = "";
			if (objectList.size() == 0) {
				objIds = "''";
			}
			for (int i = 0; i < objectList.size(); i++) {
				if (i < objectList.size() - 1) {
					objIds += "'" + objectList.get(i).toString() + "',";
				} else {
					objIds += "'" + objectList.get(i).toString() + "'";
				}
			}
			System.out.println("ObjectID为：" + objIds);
			sb.append("and a.assessObject in (" + objIds + ") ");
		}
		if (templateList != null) {
			String tempIds = "";
			if (templateList.size() == 0) {
				tempIds = "''";
			}
			for (int j = 0; j < templateList.size(); j++) {
				if (j < templateList.size() - 1) {
					tempIds += "'" + templateList.get(j).toString() + "',";
				} else {
					tempIds += "'" + templateList.get(j).toString() + "'";
				}
			}
			System.out.println("templateId为:" + tempIds);
			sb.append("and a.assessTemplate in (" + tempIds + ") ");
		}
		if (checkCyrcle != null && !"".equals(checkCyrcle)) {
			sb.append("and a.period =" + checkCyrcle + " ");
		}
		if (beginTime != null && !"".equals(beginTime)) {
			sb.append(" and to_char(a.beginTime,'yyyy-MM-dd HH24:mi:ss')>='"
					+ beginTime + "'");
		}
		if (endTime != null && !"".equals(endTime)) {
			sb.append(" and to_char(a.endTime,'yyyy-MM-dd HH24:mi:ss')<='"
					+ endTime + "'");
		}

		sb.append(" order by a.createTime desc");
		String hql = sb.toString();
		System.out.println("历史查询的SQL:" + hql);
		return this.getSession().createQuery(hql).list();
	}

	/**
	 * 根据负责人得到考核记录List add by wangjing
	 * 
	 * @param assignee
	 * @return
	 */
	public Page getRecordPageByAssignee(String assignee,
			HttpServletRequest request) {
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		String hql = "from AssessRecords r where  r.assessObject.assignee='"
				+ assignee
				+ "' and r.assessObject.status=1 and (r.state is null or r.state=1) order by r.assessObject";
		Page pageObj = this
				.pagedQuery(hql, pageNo, Constants.DEFAULT_PAGE_SIZE);
		return pageObj;
	}

	/*
	 * 根据考核名称得到考核结果
	 */
	public Page getReasultByScheduId(HttpServletRequest request) {
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		StringBuffer sb = new StringBuffer();
		AssessScheduManager assessScheduManager =new AssessScheduManager();
		sb.append("from AssessRecords a where state<>2 or state is null and a.assessTotal is not null ");
		String scheduId = request.getParameter("scheduId");
		if (scheduId != null && !"".equals(scheduId)) {
			if (!"全部".equals(scheduId)) {
				sb.append(" and a.assessSchedu.id='" + scheduId + "' ");
			}
		}else{
			sb.append(" and a.assessSchedu.id='' ");
		}
		sb.append(" order by a.assessTotal desc");
		// String hql = "from AssessRecords a where a.scheduName='" + scheduName
		// + "' order by a.assessTotal desc";
		String hql = sb.toString();
		Page pageObj = this
				.pagedQuery(hql, pageNo, Constants.DEFAULT_PAGE_SIZE);
		return pageObj;

	}
	/**
	 * 判断是否过时
	 * @param ids
	 * @return
	 * wangjing
	 */
	public boolean isOverTime(String ids){
		boolean isOverTime = false;
			String hql = " select max(endTime) from AssessRecords r where assessTemplate in ("+ids.substring(1)+") and (r.state is null or r.state=1)";
		List<Timestamp> list = this.getSession().createQuery(hql).list();
		if(!list.isEmpty()&&list.get(0)!=null){
			long maxtime = list.get(0).getTime();
			if(System.currentTimeMillis()>maxtime){
				isOverTime = true;
			}
		}
		return isOverTime;
	}
	/**
	 * 
	 * @param recordsId
	 * @return
	 */
	public boolean CanModifyResult(String recordsId){
		String hql ="select a.assessSchedu.resultcreatetime from AssessRecords a where id='"+recordsId+"' and (a.state is null or a.state=1)";
		List<Timestamp> list = this.getSession().createQuery(hql).list();
		if(!list.isEmpty()&&list.get(0)!=null){
			long resultcreatetime = list.get(0).getTime()+24*60*60*1000-1;
			if(System.currentTimeMillis()>resultcreatetime){
				return false;
			}
		}
		return true;
		
	}
	public static void main(String[] args) {
		Timestamp out = new Timestamp(System.currentTimeMillis());
		System.out.println(out);
		System.out.println(new Timestamp(out.getTime()+24*60*60*1000));
	}
}
