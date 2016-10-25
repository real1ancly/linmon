package com.ultrapower.assess.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.bidlink.core.commons.BaseManageController;

public class CheckHistoryController extends BaseManageController {

	@Override
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("assess/frame/checkHistorySearch.jsp");
		return mav;
	}
	
	public ModelAndView assessResultRang(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("assess/frame/checkResult.jsp");
		return mav;
	}
}
