package com.ultrapower.assess.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.xpath.operations.String;

import com.bidlink.core.Constants;
import com.bidlink.core.commons.BaseHibernateDao;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.assess.model.AssessObject;
import com.ultrapower.assess.util.PageUtils;

@SuppressWarnings("unchecked")
public class AssessObjManager extends BaseHibernateDao<AssessObject> {

	/**
	 * 添加考核对象
	 * 
	 * @param o
	 */
	public void addObj(AssessObject o) {
		this.getSession().save(o);
	}

	/**
	 * 分页集合
	 * 
	 * @param curPage
	 * @return
	 */
	public List getAobjPage(String curPage, String pageSize) {
		return this.getSession().createSQLQuery("select * from (select rownum rn, t.* from Assess_Object t where t.status=1) where rn > (" + curPage + " - 1) * " + pageSize + "  and rn <= " + curPage + " * " + pageSize).list();
	}

	/**
	 * 查询所有考核对象
	 * 
	 * @return
	 */
	public List<AssessObject> getAobj() {
		return this.find("from AssessObject");
	}
    
	/**
	 * 查询状态为未被删除的考核对象
	 */
	public List<AssessObject> getAllIsUseobj(){
		return this.find(" from AssessObject t where t.status=1");
	}
	/**
	 * 查询考核对象记录数
	 * 
	 * @return
	 */
	
	
	
	public List getCountObj() {
		return this.getSession().createSQLQuery("select count(*) from (select rownum rn, t.* from Assess_Object t where t.status=1)").list();
	}

	/**
	 * 删除考核对象
	 * 
	 * @param id
	 */
	public void delObjManager(String id) {
		//this.getSession().createQuery("delete AssessObject as t where t.id='" + id + "'").executeUpdate();
		this.getSession().createSQLQuery("update assess_object t set t.status=2 where t.id='"+id+"'").executeUpdate();
	}

	/**
	 * 查询考核对象（一）
	 * 
	 * @param id
	 * @return
	 */
	public List<AssessObject> findObjManager(String id) {
		return this.find("from AssessObject as t where t.id='" + id + "'");
	}

	/**
	 * 修改考核对象
	 * 
	 * @param o
	 */
	public void updateObj(AssessObject o) {
		this.getSession().update(o);
	}

	/**
	 * 通过ID查询考核对象
	 * 
	 * @param id
	 * @return
	 */
	public AssessObject getAssessObjectById(String id) {
		List<AssessObject> list = this.getSession().createQuery("from AssessObject a where a.id ='" + id + "'").list();
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<String> getIdsByName(String objectName) {
		if (objectName != null && !"".equals(objectName)) {
			List<String> idList = new ArrayList<String>();
			List<AssessObject> list = this.getSession().createQuery("from AssessObject a where a.objectName = '" + objectName + "'").list();
			for (int i = 0; i < list.size(); i++) {
				idList.add(list.get(i).getId());
			}
			return idList;
		}
		return null;
	}
	/**
	 * 得到翻页对象
	 * @param request
	 * @return
	 * add by wangjing
	 */
	public Page getPageObj(HttpServletRequest request) {
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		String hql = " from AssessObject a where a.status=1";
		Page pageObj = this
				.pagedQuery(hql, pageNo, Constants.DEFAULT_PAGE_SIZE);
		return pageObj;
	}
}
