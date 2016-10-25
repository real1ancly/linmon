package com.ultrapower.assess.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsx3.lang.Object;
import net.sf.saxon.exslt.Date;
import net.sf.saxon.exslt.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bidlink.core.Constants;
import com.bidlink.core.commons.SessionContext;
import com.bidlink.core.utils.DateUtil;
import com.ultrapower.assess.model.Users;

/**
 * 处理request和response的一些相关方法工具类
 * 
 * @author fangfang
 * 
 */
public class WebUtil {
	
	public static final String WEBUSER_IN_SESSION = "webLoginUser";

	/**
	 * 拿到提示信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getMessages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String result = "";
		try {
			List messages = (List) request.getSession()
					.getAttribute("messages");
			if (messages != null) {
				for (int i = 0; i < messages.size(); i++) {
					if (i > 0)
						result += "\r\n" + messages.get(i);
					else
						result += messages.get(i);
				}
				session.removeAttribute("messages");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据request获取指定名称的bean对象
	 * 
	 * @param beanName
	 * @param req
	 * @return
	 */
	public static Object getBeanByNameFromReq(String beanName,
			HttpServletRequest req) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(req.getSession()
						.getServletContext());
		return ctx.getBean(beanName);
	}

	/**
	 * 该方法只有在运行时才有效
	 */
	public static Object getBeanByNameFromReq(String beanName) {
		SessionContext sessionContext = SessionContext.getCurrentContext();
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(sessionContext
						.getServletContext());
		return ctx.getBean(beanName);
	}

	/**
	 * 从参数里取年份，如果指为空，则返回当前值
	 * 
	 * @param request
	 * @param keyYear
	 * @return
	 */
	public static int getYear(HttpServletRequest request, String keyYear) {
		int result;
		String strYear = request.getParameter(keyYear);
		if (StringUtils.isBlank(strYear))
			result = DateUtil.getYear(new Date());
		else
			result = new Integer(strYear).intValue();
		return result;
	}

	public static String getPara(HttpServletRequest request, String paraName,
			String defaultValue) {
		String result = request.getParameter(paraName);
		if (StringUtils.isBlank(result)) {
			result = defaultValue;
		}
		return result;
	}

	/**
	 * 从参数里取月份，如果指为空，则返回当前值
	 * 
	 * @param request
	 * @param keyMonth
	 * @return
	 */
	public static int getMonth(HttpServletRequest request, String keyMonth) {
		int result;
		String strMonth = request.getParameter(keyMonth);
		if (StringUtils.isBlank(strMonth))
			result = DateUtil.getMonth(new Date());
		else
			result = new Integer(strMonth).intValue();
		return result;
	}

	// 取得系统当前日期，格式yyyy-MM-dd
	public static String getNowDate() {
		return "";
	}
	
	/**
	 * 获取登陆后的用户的公用方法，不用this.getLoginUser
	 * 是因为没有继承iuser
	 * @param request
	 * @return
	 */
//	public static WebUser getLoginWebUser(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		
//		WebUser webUser = (WebUser)session.getAttribute(Constants.USER_IN_SESSION);
//		return webUser;
//	}
	
	/**
	 * 获取登陆后的用户的公用方法，不用this.getLoginUser
	 * 是因为没有继承iuser
	 * @param request
	 * @return
	 */
//	public static WebUser getLoginWebUser(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		
//		WebUser webUser = (WebUser)session.getAttribute(WEBUSER_IN_SESSION);
//		return webUser;
//	}
	
	public static Users getAdminUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Users adminUser = (Users)session.getAttribute(Constants.USER_IN_SESSION);
		return adminUser;
	}
	
	/**
     * 生成一个随机密码
     * @return
     */
    public static String generatorPassword() {
		String[] seeds = new String[]{"A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q",
				"S","T","U","V","W","X","Y","a","b","c","d","e","f","g","h","i","j","k","m","n","p","q",
				"q","r","s","t","u","v","w","x","y","z","2","3","4","5","6","7","8","9"};
		
		int length = seeds.length;
		Random   random= new   Random();
		String result = "";
		for(int i = 0; i < 6; i++){
			result += seeds[random.nextInt(length)];
		}
    	return result;
	}

    /**
     * UTF-8转化字符创
     * @param str
     * @return
     */
	public static String encode( String str ) {
		if( str == null ){
			return str;
		}
		try {
			return URLEncoder.encode( str, "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
	  java.util.regex.Pattern p_script;
	  java.util.regex.Matcher m_script;
	  java.util.regex.Pattern p_style;
	  java.util.regex.Matcher m_style;
	  java.util.regex.Pattern p_html;
	  java.util.regex.Matcher m_html;
	  try {
	   String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
	   // }
	   String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
	   // }
	   String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

	   p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	   m_script = p_script.matcher(htmlStr);
	   htmlStr = m_script.replaceAll(""); // 过滤script标签

	   p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	   m_style = p_style.matcher(htmlStr);
	   htmlStr = m_style.replaceAll(""); // 过滤style标签

	   p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	   m_html = p_html.matcher(htmlStr);
	   htmlStr = m_html.replaceAll(""); // 过滤html标签

	   textStr = htmlStr;

	  } catch (Exception e) {
	   System.err.println("Html2Text: " + e.getMessage());
	  }

	  return textStr;
	}
	
    
    public static void main(String[] args) {  
    	System.out.println(WebUtil.generatorPassword());
	}
	

}
