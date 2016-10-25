package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.commons.BaseManageController;
import com.ultrapower.accredit.common.value.Resource;
import com.ultrapower.accredit.rmiclient.RmiClientApplication;
import com.ultrapower.accredit.util.GetUserUtil;
import com.ultrapower.assess.manager.AssessMenuManager;
import com.ultrapower.assess.model.AssessMenu;

/**
 * 
 * @author zhongxiaoqi
 * 
 */
@SuppressWarnings("unchecked")
public class IndexController extends BaseManageController {

	private AssessMenuManager assessMenuManager;

	private RmiClientApplication rmi = RmiClientApplication.getInstance();

	public void setAssessMenuManager(AssessMenuManager assessMenuManager) {
		this.assessMenuManager = assessMenuManager;
	}

	/**
	 * 过滤权限资源到首页
	 */
	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("main.jsp");
		List<AssessMenu> list = assessMenuManager.loadUserTopMenus();
		List<Resource> rl = rmi.getSecurityService().getResourcesByLogin(GetUserUtil.getUsername());
		List<AssessMenu> list2 = new ArrayList<AssessMenu>();
		for (int i = 0; i < rl.size(); i++) {
			Resource rp = rl.get(i);
			for (int j = 0; j < list.size(); j++) {
				AssessMenu r = list.get(j);
				if (rp.getName().equals(r.getName()) && (rp.getAppName().indexOf("ASSESS")>-1)) {
					list2.add(list.get(j));
				}
			}
		}
		if(list2.size()>0){
			mav.addObject("url", list2.get(0).getUrl());
		}
		mav.addObject("menuList", list2);
		return mav;
	}
}
