package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xpath.operations.String;
import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.commons.BaseManageController;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.assess.manager.AssessMenuManager;
import com.ultrapower.assess.manager.AssessPropManager;
import com.ultrapower.assess.manager.AssessTemplateManager;
import com.ultrapower.assess.model.AssessMenu;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.model.AssessTemplate;
import com.ultrapower.assess.util.PageUtils;

/**
 * 系统
 * 
 * @author zhongxiaoqi
 * 
 */
@SuppressWarnings("unchecked")
public class MainController extends BaseManageController {

	private AssessMenuManager assessMenuManager;
	private AssessTemplateManager assessTemplateManager;
	private AssessPropManager assessPropManager;

	public void setAssessMenuManager(AssessMenuManager assessMenuManager) {
		this.assessMenuManager = assessMenuManager;
	}

	public void setAssessTemplateManager(
			AssessTemplateManager assessTemplateManager) {
		this.assessTemplateManager = assessTemplateManager;
	}

	public void setAssessPropManager(AssessPropManager assessPropManager) {
		this.assessPropManager = assessPropManager;
	}

	/**
	 * 默认进入整体框架页
	 */
	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("content_frame.jsp");
		mav.addObject("leftId", request.getParameter("leftId"));
		return mav;
	}

	/**
	 * 左侧操作页面
	 */
	public ModelAndView left(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("left.jsp");
		try {
			String idStr = request.getParameter("id");
			AssessMenu cmenu = assessMenuManager.get(idStr);
			if(cmenu.getName().equals("模板管理")){
				List<AssessTemplate> templateList = assessTemplateManager.findUseALL();
				List<AssessProp> propList = assessPropManager.findUseALL();
				mav.addObject("templateList", templateList);
				mav.addObject("propList", propList);
				mav.addObject("cmenu", cmenu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 欢迎页
	 */
	public ModelAndView welcome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/content.jsp");
		Page pageObj = assessTemplateManager.getPageObj(request);
		mav.addObject("templateList", pageObj.getResult());
		mav.addObject("pageToString",PageUtils.showPage5(pageObj, "template.do?method=index"));
		return mav;
	}

}
