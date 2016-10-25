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
import com.ultrapower.assess.manager.AssessRecordsManager;
import com.ultrapower.assess.manager.AssessTemplateManager;
import com.ultrapower.assess.model.AssessTemplate;
import com.ultrapower.assess.model.MessageModel;
import com.ultrapower.assess.util.PageUtils;
import com.ultrapower.assess.util.WebUtil;

/**
 * 考核项的控制类
 * 
 * @author zhongxiaoqi
 * 
 */
public class TemplateController extends BaseManageController {

	private AssessTemplateManager assessTemplateManager;
	private AssessRecordsManager assessRecordsManager;
	private RmiClientApplication rmi = RmiClientApplication.getInstance();
	
	public void setAssessTemplateManager(
			AssessTemplateManager assessTemplateManager) {
		this.assessTemplateManager = assessTemplateManager;
	}
	
	public void setAssessRecordsManager(AssessRecordsManager assessRecordsManager) {
		this.assessRecordsManager = assessRecordsManager;
	}




	/**
	 * 去模板列表
	 * 
	 * @author wangjing
	 */
	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/content.jsp");
		String isF = request.getParameter("isF");
		Page pageObj = assessTemplateManager.getPageObj(request);
		List<AssessTemplate> templateList = (List)pageObj.getResult();
		for(AssessTemplate assessTemplate:templateList){
			  String userName = rmi.getSecurityService().getUserByAccount(assessTemplate.getAssignee()).getUserName();
			  assessTemplate.setAssignee(userName);
			  userName = rmi.getSecurityService().getUserByAccount(assessTemplate.getUpdateAccount()).getUserName();
			  assessTemplate.setUpdateAccount(userName);
		}
		mav.addObject("templateList", templateList);
		mav.addObject("pageToString",PageUtils.showPage5(pageObj, "template.do?method=index"));
		mav.addObject("isF", isF);
		return mav;
	}
	
	
	/**
	 * 增加模板
	 *  
	 * @author wangjing
	 */
	public ModelAndView toAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/addTemplate.jsp");
		return mav;
	}
	
	/**
	 * 去修改页面
	 * @author wangjing
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("template/addTemplate.jsp");
		AssessTemplate assessTemplate = assessTemplateManager.get(request.getParameter("id"));
		mav.addObject("template", assessTemplate);
		return mav;
	}
	
	/**
	 * 保存模板
	 * 
	 * @author wangjing
	 */
	@Override
	public ModelAndView save(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		String msg = "";
		String id = request.getParameter("id");
		// 新增的情况
		if (StringUtils.isBlank(id)) {  
			AssessTemplate assessTemplate = new AssessTemplate();
			BindingResult result = bindObject(request, assessTemplate);
			if (result.hasErrors()) {
				msg = result.getAllErrors().toString();
			} else {
				if (assessTemplate.getUpdateTime() == null) {
					assessTemplate.setUpdateTime(new Date());
				}
				assessTemplate.setStatus(1l);
				assessTemplate.setAssignee(GetUserUtil.getUsername());
				assessTemplate.setUpdateAccount(GetUserUtil.getUsername());
				assessTemplateManager.save(assessTemplate);
				msg = "数据操作成功!";
			}
		} else {// 修改的情况
			AssessTemplate assessTemplate = assessTemplateManager.get(id);
			assessTemplate.setTemplateName(request.getParameter("templateName"));
			assessTemplate.setRemarks(request.getParameter("remarks"));
			assessTemplate.setUpdateTime(new Date());
			assessTemplate.setUpdateAccount(GetUserUtil.getUsername());
			assessTemplateManager.save(assessTemplate);
			msg = "数据操作成功!";
		}
		return this.index(request, response);
	}

	/**
	 * 删除模板 
	 * @author wangjing
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String chkIds = request.getParameter("chkIds");
		String[] chkIdes =  chkIds.split(",");
		int success = 0;
		String msg = WebUtil.getMessages(request, response);
		if (StringUtils.isNotBlank(chkIds)) {
			for (String id : chkIdes) {
				AssessTemplate assessTemplate = assessTemplateManager.get(id);
				assessTemplate.setStatus(3l);// 设置为删除
				assessTemplateManager.save(assessTemplate);
				success++;
			}
		}
		if (StringUtils.isBlank(msg)) {
			msg = "成功更新" + success + "条纪录!";
		}
		this.renderJson(response, JSONObject.fromObject(
				new MessageModel(true, msg, null)).toString());
		return this.index(request, response);	
		
    }
	/**
	 * 模板发布
	 * @author wangjing
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView publish(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			 AssessTemplate assessTemplate = assessTemplateManager.get(id);
			 assessTemplate.setStatus(2l);// 设置为发布
			 assessTemplateManager.save(assessTemplate);
		}
		return this.index(request, response);
	}
	
	public ModelAndView beforeDelete(HttpServletRequest request,
			HttpServletResponse response)  throws Exception{
		String ids = request.getParameter("ids");
		if(!assessRecordsManager.isOverTime(ids)){
		ModelAndView mav = new ModelAndView("template/content.jsp");
			Page pageObj = assessTemplateManager.getPageObj(request);
			mav.addObject("templateList", pageObj.getResult());
			mav.addObject("pageToString",PageUtils.showPage5(pageObj, "template.do?method=index"));
			mav.addObject("using", "using");
			return mav;
		}else{
			return this.delete(request, response);
			
		}
		
	}
	
}
