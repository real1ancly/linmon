package com.ultrapower.assess.manager;

import javax.servlet.http.HttpServletRequest;

import jsx3.lang.Object;

import org.apache.commons.lang.StringUtils;
import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;

import com.bidlink.core.Constants;
import com.bidlink.core.commons.BaseHibernateDao;
import com.bidlink.core.commons.support.page.Page;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.util.PageUtils;

/**
 * 考核模板 操作类
 * @author wangjing
 *
 */
public class AssessPropManager extends BaseHibernateDao<AssessProp> {
	private Long sort = 0l;
	private int countCol = 0;
	private StringBuffer sub = new StringBuffer();
	/*
	 * 得到所有可使用的指标
	 * 
	 */
	@SuppressWarnings("unchecked")
    public List<AssessProp> findUseALL(){
    	return this.getSession().createQuery(" from AssessProp a where a.status=1 order by sort").list();
    }
	
	/**
	 * 根据id得到可使用的子指标
	 * @param assessProp
	 * @return
	 */
	public List<AssessProp> findChildrenById(AssessProp assessProp){
    	return this.getSession().createQuery(" from AssessProp a where a.status=1 and a.parent='"+assessProp.getId()+"' order by sort").list();
    }
	
	public Page getPageObj(HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		int pageNo = PageUtils.populatePageInfoFromRequest(request);
		String hql = "from AssessProp a where a.status=1 and a.parent='"+parentId+"' order by sort";
		Page pageObj = this
		.pagedQuery(hql, pageNo, Constants.DEFAULT_PAGE_SIZE);
		return pageObj;
	}
	
	/**
	 * 返回到manage页面,生成模板预览页面
	 * @param assessTemplate
	 * @return
	 */
	public String htmlToString(int maxColspan,List<String[]> propList) {
		int rowspan = 1;
		Set<String> idSet = new HashSet<String>();
		StringBuffer htmlBuffer = new StringBuffer();
		 for(int i=0;i<propList.size();i++){
			 int countCol = 0;
			 String[] props = propList.get(i);
			 String[] ids = props[0].substring(1).split("/");
			 String[] names = props[1].substring(1).split("/");
			 if(!idSet.contains(ids[0])){
				 rowspan = this.countRowspan(propList, ids[0]);
				 htmlBuffer.append("<tr><td width='16' rowspan='"+rowspan+"'><input type='checkbox'  name='cbx' id='cbx' value='")
		           .append(ids[0])
		           .append("'/></td><td align='center' rowspan='"+rowspan+"'>")
		           .append(props[2].split("/")[1])
		           .append("</td>"); 
			 } 
			 
			 for(int j=0;j<names.length;j++){
				 if(j==names.length-1){
					 htmlBuffer.append("<td colspan='"+(maxColspan-countCol)+"'>")
					           .append(names[j])
				               .append("</td>");
				 }else{
					 if(!idSet.contains(ids[j])){
						 htmlBuffer.append("<td rowspan='"+this.countRowspan(propList, ids[j])+"'>")
						           .append(names[j])
					               .append("</td>");
					 } 
					 countCol++;
				 }
			 }
			 htmlBuffer.append("<td>&nbsp</td>");
			 if(StringUtils.isNotBlank(props[3])){
			    	htmlBuffer.append("<td>").append(props[3]).append("</td>");
			 }else{
			    	htmlBuffer.append("<td>&nbsp</td>");
			 }
			 if(StringUtils.isNotBlank(props[4])){
			    	htmlBuffer.append("<td>").append(props[4]).append("</td>");
			  }else{
			    	htmlBuffer.append("<td>&nbsp</td>");
			   }
			 htmlBuffer.append("<td>&nbsp</td></tr>");
			 for(String id:ids){
				 idSet.add(id); 
			 }
		 }
		 return htmlBuffer.toString();
	}
	
	
	/**
	 * 得到指标列表
	 * @param recordsId
	 * @return
	 */
	public List<String[]> getPropList(String templateId){
		String sql = "with a as (select id,parent_id,prop_name,sort,prop_unit,SCORE_EXPRESSION,prop_type from assess_prop where template_id='"+templateId+"' and status=1)"
        + "select ids,names,sorts,prop_unit,SCORE_EXPRESSION,prop_type from (select sys_connect_by_path(id, '/') ids,sys_connect_by_path(prop_name, '/') names,sys_connect_by_path(sort, '/') sorts,"
        + "prop_unit,SCORE_EXPRESSION,prop_type from a where id not in (select distinct parent_id from a where parent_id is not null)"
	       + " start with id in (select id from a where parent_id is null) connect by prior id=parent_id ) order by sorts";
		List<Object[]> Objects = this.getSession().createSQLQuery(sql).list();
		List<String[]> propList = new ArrayList<String[]>();
		for(Object[] object:Objects){
			String[] toString = new String[6];
			for(int i=0;i<object.length;i++){
				if(object[i]!=null){
					toString[i] = object[i].toString();
				}else{
					toString[i] = "";
				}
				
			}
			propList.add(toString);
			
		}
		return propList;
	}
	
	/**
	 * htmlToString辅助方法
	 * @param rList
	 * @return
	 */
	
	public int getMaxColspan(List<String[]> rList){
		int max = 0;
	       int temp =0;
	       for(int i=0;i<rList.size();i++){
	    	   String[] rs = rList.get(i);
	    	   temp = rs[0].substring(1).split("/").length;
	    	   if(max<temp){
	    		   max = temp;
	    	   }
	    	  
	       }
	       if(max==0){
	    	   return 1;
	       }
		return max;
	}
	
	/**
	 * htmlToString辅助方法
	 * @param cList
	 * @param propId
	 * @return
	 */
	public int countRowspan(List<String[]> propList,String propId) {
		int rowspan = 0;
		for(int i=0;i<propList.size();i++){
			if(propList.get(i)[0].contains(propId)){
				rowspan++;
			}
		}
		return rowspan;
	}
	
  
	/**
	 * 得到当前同一节点最大的序号
	 * @return
	 */
	public Long toGetMaxSort(String parentId,String templateId){
		String hql = "";
		Long  maximum = 0l;
		if(StringUtils.isNotBlank(parentId)){
			hql = "from AssessProp a where a.parent='"+parentId+"' and a.assessTemplate='"+templateId+"' and a.status=1 order by sort desc";
		}else{
			hql = "from AssessProp a where a.parent is null and a.assessTemplate='"+templateId+"' and a.status=1 order by sort desc";
		}
		List<AssessProp> aList = this.getSession().createQuery(hql).list();
		if(!aList.isEmpty()){
			maximum = aList.get(0).getSort();
		}
		
		return maximum;
	}
	
	/**
	 * 处理回显表达式
	 * @return
	 */
	public String dealExpression(String expression){
		String[] expressions =  expression.split(",");
		if(expressions.length>1){
			expression = expressions[0];
			for(int i=1;i<expressions.length;i++){
				expression += "+" + expressions[i];
			}
		}
		expressions =  expression.split(";");
		if(expressions.length>1){
			expression = expressions[0];
			for(int i=1;i<expressions.length;i++){
				expression += "%" + expressions[i];
			}
		}
		
		return expression;
	}
	
	
	public String getprops(String templateId){
		String propIds ="";
		   String sql    = "with a as (select id,parent_id,prop_name,sort,prop_unit,SCORE_EXPRESSION from assess_prop where template_id='"+templateId+"' and status=1)"
						 + "select id from (select id,sys_connect_by_path(sort, '/') sorts,"
						 + "prop_unit,SCORE_EXPRESSION from a where id not in (select distinct parent_id from a where parent_id is not null)"
						 + "start with id in (select id from a where parent_id is null) connect by prior id=parent_id ) order by sorts";
		List<String> list = this.getSession().createSQLQuery(sql).list();
		for(String id:list){
			if(propIds.equals("")){
				propIds += id;
			}else{
				propIds +=","+id;
			}
			
		}
		return propIds;
	}
	
}
