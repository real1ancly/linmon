package com.ultrapower.assess.manager;

import org.apache.xpath.operations.String;

import com.bidlink.core.commons.BaseHibernateDao;
import com.ultrapower.assess.model.AssessMenu;

/**
 * 后台菜单 操作类
 * @author zhongxiaoqi
 *
 */
public class AssessMenuManager extends BaseHibernateDao<AssessMenu> {

	/**
	 * 获取第一级别的
	 * @return
	 */
//	public List getTopList() {
//		String hql = "from AdminMenu ad where ad.pid = 0 order by sort asc";
//		List<AssessMenu> list = this.find(hql);
//		return list;
//	}
	/**
	 * 获取子类以及 子类的子类
	 * @param pid
	 * @return
	 */
	@SuppressWarnings("unchecked")
//	public List<AssessMenu> getListByParent(Long pid) { 
//		
//		String sql = "select id, name,pid,grade,url from admin_menu a start with a.pid = "+pid+" " +
//				"connect by prior a.id = a.pid order by id";
//		List<Object[]> list=this.getSession().createSQLQuery(sql).list();
//		List<AssessMenu> alist=new ArrayList<AssessMenu>();
//		for (Object[] o:list) {
//			AssessMenu menu=new AssessMenu();
//			menu.setId(new Long(o[0].toString()));
//			menu.setName(o[1].toString());
//			menu.setUrl(o[4]==null ?"javascript:void(0);" : o[4].toString());
//			alist.add(menu);
//		}
//		return alist;
//	}
	
//	public List getListByParent(Long pid, Long userId) {
//		String hql = "select distinct menu from AssessMenu menu inner join menu.adminRoles role inner join role.adminUsers user where user.id=? and menu.pid=? order by menu.id";
//		List<AssessMenu> list = this.find(hql, new Object[]{userId, pid});
//		for(AssessMenu am : list) {
//			List<AssessMenu> subList = this.find(hql, new Object[]{userId, am.getId()});
////			am.setSubList(subList);
//		}
//		return list;
//	}
	
	
//	public List loadUserSystemMenus(Long userId) {
//		String hql = "select distinct menu from AssessMenu menu inner join menu.adminRoles role inner join role.adminUsers user where user.id=? order by menu.id";
//		return this.find(hql, new Object[] { userId });
//	}
	
	public List<AssessMenu> loadUserTopMenus() {
		String hql = "select distinct menu from AssessMenu menu order by menu.id";
		return this.find(hql);
	}

//	public List loadChildRenById(Long parentId) {
//		String hql = "from AssessMenu menu where menu.pid = ?";
//		return this.find(hql, new Object[] { parentId });
//	}
}
