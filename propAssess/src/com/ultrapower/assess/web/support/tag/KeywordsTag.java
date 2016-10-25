package com.ultrapower.assess.web.support.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;

/**
 * 页面元素buttonchd
 * 
 * @author fangfang
 */
public class KeywordsTag extends TagSupport {

	private String src;

	private String keywords;
	

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	@Override
	public int doStartTag() throws JspException {
		if (StringUtils.isBlank(src))
			return Tag.EVAL_BODY_INCLUDE;
		JspWriter out = pageContext.getOut();
		try {
			src = src.replaceAll(keywords, "<font color='red'>"+keywords+"</font>");
			out.print(src);
		} catch (IOException e) {
			return Tag.EVAL_BODY_INCLUDE;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}
	
}
