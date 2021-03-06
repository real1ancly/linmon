package com.ultrapower.assess.manager;

import com.bidlink.core.commons.BaseHibernateDao;
import com.ultrapower.assess.model.News;

/**
 * 后台 新闻操作类
 * 
 * @author zhongxiaoqi
 * 
 */
public class NewsManager extends BaseHibernateDao<News> {

	@SuppressWarnings("unchecked")
	public List<News> getNewsList() {
		return this.find("from News n where n.status=0 order by type,updateDate desc");
	}

	/**
	 * 获取头几条新闻
	 * 
	 * @param num
	 *            取头几条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> getNewsByTypeAndTop(int type, int num) {
		return getSession().createQuery(
				"from News n where n.status=0 and n.type=" + new Long(type)
						+ " order by n.seq desc").setFirstResult(0)
				.setMaxResults(num).list();
	}

	/**
	 * 重置院长视点焦点
	 */
	public void updateNewsYZ() {
		getSession().createQuery("update News n set n.isYZ=0").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public News getYZNews() {
		List lst = this
				.find("from News n where n.status=0 and n.type=5 and n.isYZ=1");
		return lst.isEmpty() ? null : (News) lst.get(0);
	}

	/**
	 * 去集合的前几条
	 * @param id
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public News getUpNews(Integer id,Long type) {
		List lst = getSession().createQuery(
				"from News n where n.type="+type+" and n.seq < " + id + " order by n.seq desc")
				.setFirstResult(0).setMaxResults(1).list(); 
		return lst.isEmpty() ? null : (News) lst.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public News getDownNews(Integer id,Long type) {
		List lst = getSession().createQuery(
				"from News n where n.type="+type+" and n.seq > " + id + " order by n.seq")
				.setFirstResult(0).setMaxResults(1).list();
		return lst.isEmpty() ? null : (News) lst.get(0);
	}
}
