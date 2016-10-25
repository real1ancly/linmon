package com.ultrapower.assess.web.support.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.xpath.operations.String;

/**
 * 
 * @author Administrator
 *
 */
public class TableTag extends TagSupport {

	/**
	 * 展示的数据的集合
	 */
	private List list;
	/**
	 * 选中的集合
	 */
	private String selectIds;
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(String selectIds) {
		this.selectIds = selectIds;
	}

	@Override
	public int doStartTag() throws JspException {

//		JspWriter out = pageContext.getOut();
//		try {
//			int length = list.size();
//			int yushu = length % 4;
//			int pageLength = length / 4 + yushu==0?0:1;//总计这么多行
//			StringBuffer sb = new StringBuffer();
//			for(int i = 1; i <= pageLength; i++) {
//				//最后一行
//				sb.append("<tr>");
//				int start = (i-1) * 4;
//				if( i == pageLength) {
//					int k = 1;
//					for(k = 1; k <= yushu; k++) {
//						WebDepart webDepart = (WebDepart)list.get((k-1));
//						sb.append("<td width='25%' align='left'><input name='depart' type='checkbox' value='"+webDepart.getId()+"' ");
//						if(StringUtils.contains(selectIds, ";"+webDepart.getId()+";"))
//							sb.append("checked");
//						sb.append("/>"+webDepart.getName()+"</td>");
//					}
//					for(int j = k;j <= 4; j++) {
//						sb.append("<td width='25%'></td>");
//					}
//				} else {
//					for(int j = start; j < start+4; j++) {
//						WebDepart webDepart = (WebDepart)list.get(j);
//						sb.append("<td width='25%'  align='left'><input name='depart' type='checkbox' value='"+webDepart.getId()+"' ");
//						if(StringUtils.contains(selectIds, ";"+webDepart.getId()+";"))
//							sb.append("checked");
//						sb.append("/>"+webDepart.getName()+"</td>");
//					}
//				}
//				sb.append("</tr>");
//			}
//			System.out.println(sb.toString());
//			out.print(sb.toString());
//		} catch (IOException e) {
//			return Tag.EVAL_BODY_INCLUDE;
//		}

		return Tag.EVAL_BODY_INCLUDE;
	}
}
