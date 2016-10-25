package com.ultrapower.assess.web.support.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;

import com.ultrapower.assess.util.HtmlUtils;

public class HtmlTag extends TagSupport {

	private String src;
	
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
			String result = HtmlUtils.Html2Text(src);
			out.print(result);
		} catch (IOException e) {
			return Tag.EVAL_BODY_INCLUDE;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}
}
