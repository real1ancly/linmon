package com.ultrapower.assess.manager;

import jsx3.lang.Object;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xpath.operations.String;
import org.hibernate.mapping.Map;
import org.hibernate.mapping.Set;

import com.bidlink.core.commons.BaseHibernateDao;
import com.ultrapower.accredit.util.GetUserUtil;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.model.AssessRecords;
import com.ultrapower.assess.model.AssessResult;
import com.ultrapower.assess.util.expression.Expressions;

/**
 * 
 * @author yangsiyi
 * 
 */
public class AssessResultManager extends BaseHibernateDao<AssessResult> {
	private AssessPropManager assessPropManager;
	private AssessKpiValueManager assessKpiValueManager;
	private AssessRecordsManager assessRecordsManager;
	public void setAssessPropManager(AssessPropManager assessPropManager) {
		this.assessPropManager = assessPropManager;
	}
	public void setAssessKpiValueManager(AssessKpiValueManager assessKpiValueManager) {
		this.assessKpiValueManager = assessKpiValueManager;
	}
	
	
	private static Logger logger = Logger.getLogger(AssessRecordsManager.class);

	/**
	 * 根据AssessResult 和 AssessProp 查询一条AssessResult记录
	 * 
	 * @param result
	 * @param prop
	 * @return
	 */ 
	@SuppressWarnings("unchecked")
	public AssessResult getResultByRecordAndProp(AssessRecords records,
			AssessProp prop) {
		List<AssessResult> resultList = this.getSession().createQuery(
				"from AssessResult a where a.assessRecords='" + records.getId()
						+ "' and a.assessProp='" + prop.getId() + "'").list();
		return resultList.size() > 0 ? resultList.get(0) : null;
	}

	/**
	 * 查询KPIValue表，并更新指标值
	 * 
	 * @param records
	 * @param prop
	 *//*
	public void updateResultKPIValue(AssessRecords records, AssessProp prop) {
		AssessResult result = getResultByRecordAndProp(records, prop);
		if (result == null || "".equals(result)) {
			logger.error("AssessResult为空，对应的AssessRecords ID为"
					+ records.getId() + ",对应的AssessProp ID为" + prop.getId());
			return;
		}
		// FISHTODOL<注释掉，暂时不考虑> 查询对应的KPIValue
		result.setKpiValue("");
		this.getSession().update(result);
	}



	*//**
	 * 更新得分
	 * 
	 * @param records
	 * @param prop
	 * @param score
	 *//*
	public void updateResultScore(AssessRecords records, AssessProp prop,
			double score) {
		AssessResult result = getResultByRecordAndProp(records, prop);
		result.setScore(score);
		this.getSession().update(result);
	}*/
	
	/**
	 * 保存结果
	 * add by wangjing
	 */
	public String saveResults(AssessRecords assessRecords,String kpiValues,String resultIds){
		String[] kpiValue = kpiValues.split(",");
		NumberFormat ddf=NumberFormat.getNumberInstance() ;
		ddf.setMaximumFractionDigits(2);
		if(StringUtils.isNotBlank(resultIds)){
			String[] resultId = resultIds.split(",");
			for(int i=0;i<resultId.length;i++){
				AssessResult assessResult = this.get(resultId[i]);
				if(assessResult.getAssessProp().getPropType()==1){
					String kValue=kpiValue[i];
					assessResult.setKpiValue(kValue);
					Double score = this.getScore(assessResult.getAssessProp(), assessResult.getKpiValue());
		            if(score>100){
		            	return "wrongScroe,"+(i+1);
		            }
		            assessResult.setScore(Double.valueOf(ddf.format(score)));
					this.save(assessResult);
				}
			}
		}
		assessRecords.setAssessId(GetUserUtil.getUsername());
		assessRecords.setAssessTimes(System.currentTimeMillis());
		assessRecordsManager.save(assessRecords);
		return "success";
	}
	
	/**
	 * 导入式保存结果
	 * add by wangjing
	 */
	public String saveResults(AssessRecords assessRecords,String kpiValues){
		String propIds = assessPropManager.getprops(assessRecords.getAssessTemplate().getId());
		String[] kpiValue = kpiValues.split(",");
		if(propIds.split(",").length!=kpiValue.length){
			return "wrongprop";
		}
		
		Set<AssessResult> resultSet = assessRecords.getAssessResults();
		String resultIds = "";
		String[] propId = propIds.split(",");
		for(int i=0;i<propId.length;i++){
			for(AssessResult assessResult:resultSet){
				AssessProp assessProp = assessResult.getAssessProp();
				Long state = assessResult.getState();
				if((state==null||state.toString().equals("1"))&&assessProp.getId().equals(propId[i])){
					if(assessProp.getPropType()==1){
							if(kpiValue[i].contains("-")){
								return "wrongNum";
							}
							if(!isNumeric(kpiValue[i])){
								return "wrongvalue";
							}
					}
					
					resultIds += "," + assessResult.getId();
				}
			}
		}
		if(resultIds.length()>0){
			resultIds = resultIds.substring(1);
		}
		
		return this.saveResults(assessRecords, kpiValues,resultIds);
	}
	/**
	 * 判断字符串是否由数字组成
	 * @param str
	 * @return
	 * wangjing
	 */
	public boolean isNumeric(String str)
	{
	  Pattern pattern = Pattern.compile("[0-9.]*");
	  Matcher isNum = pattern.matcher(str);
	  if( !isNum.matches() ){
	    return false;
	  }
	  if(str.split("\\.").length>2){
			 return false;
	 }
	   return true;
	} 

	
	
	/**
	 * 根据表达式计算得分
	 * @param assessProp,kpiValue
	 * @return
	 * wangjing
	 */
	public Double getScore(AssessProp assessProp,String kpiValue){
		try {
			Object object = Expressions.eval(assessProp.getScoreExpression(), kpiValue);
			 return Double.parseDouble(object.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("计算指标得分异常，AssessProp.id=" + assessProp.getId() + ","
					+ e);
		}
		return 0d;
	}
	
	/**
	 * 得到结果Map
	 * @param recordsId
	 * @return
	 * wangjing
	 */
	public Map<String,AssessResult>  getResultMap(String recordsId){
		String hql = "from AssessResult a where a.assessRecords='"+recordsId+"' and (a.state is null or a.state=1) and (a.assessRecords.state is null or a.assessRecords.state=1)";
		List<AssessResult> arList = this.getSession().createQuery(hql).list();
		Map<String,AssessResult> resultMap = new HashMap<String, AssessResult>();
		 for(AssessResult ar:arList){
			 resultMap.put(ar.getAssessProp().getId(), ar);
		 }
		 return resultMap;
	}
	public void setAssessRecordsManager(AssessRecordsManager assessRecordsManager) {
		this.assessRecordsManager = assessRecordsManager;
	}

	

	
}
