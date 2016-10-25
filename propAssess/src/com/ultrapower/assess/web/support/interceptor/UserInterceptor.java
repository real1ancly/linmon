package com.ultrapower.assess.web.support.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsx3.lang.Object;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.operations.String;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bidlink.core.Constants;
import com.bidlink.core.utils.RequestUtil;

/**
 * 用户访问拦截器
 * 
 * @author loveflying
 * 
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

	private Log logger = LogFactory.getLog(getClass());

	private List noCheckUrls;

	public void setNoCheckUrls(List noCheckUrls) {
		this.noCheckUrls = noCheckUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 组装客户端请求地址
		String requestURI = request.getRequestURI();

		String redirectUrl = RequestUtil.getRequestURL(request);
		redirectUrl = redirectUrl.replaceAll("&", "%26");

		requestURI = "/" + StringUtils.substringAfterLast(requestURI, "/");

		String methodName = request.getParameter("method");
		if (StringUtils.isNotBlank(methodName)) {
			requestURI += "?method=" + methodName;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("User-Agent:  " + request.getHeader("User-Agent"));
			logger.debug("Ip:  " + request.getRemoteAddr());
			logger.debug("requestURI:  " + requestURI);
			logger.debug("redirectUrl:  " + redirectUrl);
		}

		if (noCheckUrls.contains(requestURI))
			return true;

		if (request.getSession().getAttribute(Constants.USER_IN_SESSION) == null) {
			// 用户不登陆重定向到login.do
			if (logger.isDebugEnabled()) {
				logger.debug("用户Session属性" + Constants.USER_IN_SESSION
						+ "值为空,禁止访问系统!");
			}
			response.sendRedirect(request.getContextPath()+ "/login~project.do");
			return false;
		}

		return true;
	}

}