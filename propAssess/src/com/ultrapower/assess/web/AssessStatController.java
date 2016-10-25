package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xpath.operations.String;
import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.Constants;
import com.bidlink.core.commons.BaseManageController;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.assess.manager.AssessRecordsManager;
import com.ultrapower.assess.manager.AssessScheduManager;
import com.ultrapower.assess.model.AssessSchedu;
import com.ultrapower.assess.util.PageUtils;

/**
 * 考核统计
 * 
 * @author admin
 * 
 */
@SuppressWarnings("unchecked")
public class AssessStatController extends BaseManageController {
	private AssessRecordsManager assessRecordsManager;
	private AssessScheduManager assessScheduManager;

	public void setAssessScheduManager(AssessScheduManager assessScheduManager) {
		this.assessScheduManager = assessScheduManager;
	}

	public void setAssessRecordsManager(AssessRecordsManager assessRecordsManager) {
		this.assessRecordsManager = assessRecordsManager;
	}

	@Override
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/content_frame_checkCount.jsp");
		return mav;
	}

	public ModelAndView indexleft(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/left_checkCount.jsp");
		return mav;
	}

	public ModelAndView indexcenter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/checkResult.jsp");
		return mav;
	}

	public ModelAndView checkCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("assess/frame/checkResult.jsp");
		String scheduId = request.getParameter("scheduId")== null ? "" : request.getParameter("scheduId").trim();
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		Page pageObj = assessRecordsManager.getReasultByScheduId(request);
		List<AssessSchedu> slist = assessScheduManager.getAllSchedu();
		mav.addObject("rlist", pageObj.getResult());
		/*
		 * 不明白此处分页的url指向assessTemp.do?method=sendRecorder是什么意思
		 */
		// mav.addObject("pageToString", PageUtils.showPage5(pageObj,
		// "assessTemp.do?method=sendRecorder"));
		mav.addObject("slist", slist);
		mav.addObject("selectValue",scheduId);
		mav.addObject("pageNo",pageNo);
		mav.addObject("pageNum",Constants.DEFAULT_PAGE_SIZE);
		mav.addObject("pageToString", PageUtils.showPage5(pageObj, "assessStat.do?method=checkCount"));

		return mav;

	}
}
