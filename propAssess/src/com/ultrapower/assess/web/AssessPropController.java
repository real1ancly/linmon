package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.saxon.exslt.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.commons.BaseManageController;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.accredit.rmiclient.RmiClientApplication;
import com.ultrapower.accredit.util.GetUserUtil;
import com.ultrapower.assess.manager.AssessPropManager;
import com.ultrapower.assess.manager.AssessRecordsManager;
import com.ultrapower.assess.manager.AssessTemplateManager;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.model.AssessTemplate;
import com.ultrapower.assess.model.MessageModel;
import com.ultrapower.assess.util.PageUtils;
import com.ultrapower.assess.util.WebUtil;

/**
 * 考核项的控制类
 * 
 * @author wangjing
 * 
 */
public class AssessPropController extends BaseManageController {

	private AssessPropManager assessPropManager;
	private AssessTemplateManager assessTemplateManager;
	private AssessRecordsManager assessRecordsManager;
	private RmiClientApplication rmi = RmiClientApplication.getInstance();

	
	public void setAssessPropManager(AssessPropManager assessPropManager) {
		this.assessPropManager = assessPropManager;
	}
	public void setAssessTemplateManager(
			AssessTemplateManager assessTemplateManager) {
		this.assessTemplateManager = assessTemplateManager;
	}
	public void setAssessRecordsManager(AssessRecordsManager assessRecordsManager) {
		this.assessRecordsManager = assessRecordsManager;
	}

	/**
	 * 去考核项列表
	 * 
	 */
	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/content.jsp");
//		Page pageObj = assessTemplateManager.getPageObj(request);
//		mav.addObject("pageObj",pageObj );
//		mav.addObject("templateList", pageObj.getResult());
		return mav;
	}
	
	
	/**
	 * 增加考核项
	 *  
	 */
	public ModelAndView toAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/addCheckList.jsp");
		String templateId = request.getParameter("templateId");
		String parentId = request.getParameter("parentId");
		String page = request.getParameter("page");
		AssessTemplate assessTemplate = null;
		AssessProp parentProp = null;
		if(StringUtils.isNotBlank(templateId)){
			assessTemplate = assessTemplateManager.get(templateId);
		}
		if(StringUtils.isNotBlank(parentId)){
			parentProp = assessPropManager.get(parentId);
			assessTemplate = parentProp.getAssessTemplate();
		}
		mav.addObject("assessTemplate", assessTemplate);
		mav.addObject("parentProp", parentProp);
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 去修改页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/addCheckList.jsp");
		AssessProp assessProp = assessPropManager.get(request.getParameter("id"));
		String userName = rmi.getSecurityService().getUserByAccount(assessProp.getUpdateAccount()).getUserName();
		String page = request.getParameter("page");
		mav.addObject("assessProp", assessProp);
		mav.addObject("updateUserName", userName);
		mav.addObject("assessTemplate", assessProp.getAssessTemplate());
		mav.addObject("parentProp", assessProp.getParent());
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 保存考核项
	 * 
	 */
	@Override
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg = "";
		String id = request.getParameter("id");
		String templateId = request.getParameter("templateId");
		String parentId = request.getParameter("parentId");
		String page = request.getParameter("page");
		// 新增的情况
		if (StringUtils.isBlank(id)) {
			AssessProp assessProp = new AssessProp();
			BindingResult result = bindObject(request, assessProp);
			if (result.hasErrors()) {
				msg = result.getAllErrors().toString();
			} else {
				if (assessProp.getUpdateTime() == null) {
					assessProp.setUpdateTime(new Date());
				}
				assessProp.setStatus(1l);
				assessProp.setUpdateAccount(GetUserUtil.getUsername());
				if(StringUtils.isNotBlank(templateId)){
				   assessProp.setAssessTemplate(assessTemplateManager.get(templateId));
				}
				if(StringUtils.isNotBlank(parentId)){
					assessProp.setParent(assessPropManager.get(parentId));
				}
				if(assessProp.getSort()==null){
					assessProp.setSort(assessPropManager.toGetMaxSort(parentId,templateId)+1);
				}
				assessPropManager.save(assessProp);
				
				msg = "数据操作成功!";
			}
		} else {// 修改的情况
			AssessProp assessProp = assessPropManager.get(id);
			assessProp.setKpiId(request.getParameter("kpiId"));
			assessProp.setPropName(request.getParameter("propName"));
			assessProp.setPropType(Long.valueOf(request.getParameter("propType")));
			assessProp.setPropUnit(request.getParameter("propUnit"));
			assessProp.setScoreExpression(request.getParameter("scoreExpression"));
//			assessProp.setPeriod(request.getParameter("period"));
			assessProp.setRemarks(request.getParameter("remarks"));
			if(!request.getParameter("sort").trim().equals("")){
				assessProp.setSort(Long.valueOf(request.getParameter("sort")));
			}
			assessPropManager.save(assessProp);
			msg = "数据操作成功!";
		}
		if(page.equals("page")){
			return this.manage(request, response);
		}else{
			return this.item(request, response);
		}
		
	}
	
	/**
	 * 去模板管理
	 * 
	 * @author wangjing
	 */
	public ModelAndView manage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/contentPage.jsp");
		String isF = request.getParameter("isF");
		String templateId = request.getParameter("templateId");
		AssessTemplate assessTemplate = new AssessTemplate();
		int maxColspan = 1;
		String htmlToString = "";
		if(StringUtils.isNotBlank(templateId)){
			assessTemplate = assessTemplateManager.get(templateId);
			List<String[]> propList = assessPropManager.getPropList(templateId);
			maxColspan = assessPropManager.getMaxColspan(propList);
			htmlToString = assessPropManager.htmlToString(maxColspan,propList);
			
		}
		mav.addObject("assessTemplate", assessTemplate);
		mav.addObject("htmlToString", htmlToString);
		mav.addObject("colspan", maxColspan);
		mav.addObject("isF", isF);
		return mav;
	}

	/**
	 * 去考核项管理
	 */
	public ModelAndView item(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/contentItem.jsp");
		String isF = request.getParameter("isF");
		String parentId = request.getParameter("parentId");
		AssessProp currentProp = assessPropManager.get(parentId);
		Page pageObj = assessPropManager.getPageObj(request);
		mav.addObject("assessTemplate", currentProp.getAssessTemplate());
		mav.addObject("currentProp", currentProp);
		mav.addObject("assessProps", pageObj.getResult());
		mav.addObject("pageToString",PageUtils.showPage5(pageObj, "assessProp.do?method=item&parentId="+parentId));
		mav.addObject("isF", isF);
		return mav;
	}
	/**
	 * 删除模板 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String chkIds = request.getParameter("chkIds");
		String page = request.getParameter("page");
		String[] chkIdes =  chkIds.split(",");
		int success = 0;
		String msg = WebUtil.getMessages(request, response);
		if (StringUtils.isNotBlank(chkIds)) {
			for (String id : chkIdes) {
				AssessProp assessProp = assessPropManager.get(id);
				assessProp.setStatus(2l);// 设置为删除
				assessPropManager.save(assessProp);
				success++;
			}
		}
		if (StringUtils.isBlank(msg)) {
			msg = "成功更新" + success + "条纪录!";
		}
		this.renderJson(response, JSONObject.fromObject(
				new MessageModel(true, msg, null)).toString());
		if(page.equals("page")){
			return this.manage(request, response);
		}else{
			return this.item(request, response);
		}
    }
	
	/**
	 * 去公式编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView toExpression(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/expressionPane.jsp");
		String expression = request.getParameter("expression");
		mav.addObject("expression", assessPropManager.dealExpression(expression));
		return mav;
	}
	
	
	public ModelAndView beforeDelete(HttpServletRequest request,
			HttpServletResponse response)  throws Exception{
		String templateId = request.getParameter("templateId");
		
		if(!assessRecordsManager.isOverTime(",'"+templateId+"'")){
			String page = request.getParameter("page");
			ModelAndView mav = new ModelAndView();
			if(page.equals("page")){
				mav.setViewName("template/contentPage.jsp");
				AssessTemplate assessTemplate = new AssessTemplate();
				int maxColspan = 1;
				String htmlToString = "";
				if(StringUtils.isNotBlank(templateId)){
					assessTemplate = assessTemplateManager.get(templateId);
					List<String[]> propList = assessPropManager.getPropList(templateId);
					maxColspan = assessPropManager.getMaxColspan(propList);
					htmlToString = assessPropManager.htmlToString(maxColspan,propList);
					
				}
				mav.addObject("assessTemplate", assessTemplate);
				mav.addObject("htmlToString", htmlToString);
				mav.addObject("colspan", maxColspan);
				mav.addObject("using", "using");
			}else{
				mav.setViewName("template/contentItem.jsp");
				String parentId = request.getParameter("parentId");
				AssessProp currentProp = assessPropManager.get(parentId);
				Page pageObj = assessPropManager.getPageObj(request);
				mav.addObject("assessTemplate", currentProp.getAssessTemplate());
				mav.addObject("currentProp", currentProp);
				mav.addObject("assessProps", pageObj.getResult());
				mav.addObject("pageToString",PageUtils.showPage5(pageObj, "assessProp.do?method=item&parentId="+parentId));
				mav.addObject("using", "using");
			}
			return mav;
		}else{
			return this.delete(request, response);
			
		}
		
	}
		
		
	
}
