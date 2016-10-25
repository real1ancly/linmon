package com.ultrapower.assess.web.support.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;

public class EncodeTag extends TagSupport {

	private String name;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



	@Override
	public int doStartTag() throws JspException {
		if (StringUtils.isBlank(name))
			return Tag.EVAL_BODY_INCLUDE;
		JspWriter out = pageContext.getOut();
		try {
			out.print(URLEncoder.encode(name));
			System.out.println("----"+URLEncoder.encode(name));
		} catch (IOException e) {
			return Tag.EVAL_BODY_INCLUDE;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}
}
