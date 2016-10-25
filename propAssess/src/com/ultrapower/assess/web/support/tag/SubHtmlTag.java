package com.ultrapower.assess.web.support.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;

import com.bidlink.core.utils.StringUtil;
import com.ultrapower.assess.util.HtmlUtils;

public class SubHtmlTag extends TagSupport {

	private String src;
	
	private Integer length;
	
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public int doStartTag() throws JspException {
		if (StringUtils.isBlank(src))
			return Tag.EVAL_BODY_INCLUDE;
		JspWriter out = pageContext.getOut();
		try {
			String result = HtmlUtils.Html2Text(src).trim();
			result = StringUtil.subContent(result, length, "..");
			out.print(result);
		} catch (IOException e) {
			return Tag.EVAL_BODY_INCLUDE;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}
}
