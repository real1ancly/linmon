package com.ultrapower.assess.calculate;

import net.sf.saxon.exslt.Date;

import org.apache.log4j.Logger;
import org.apache.xpath.operations.String;
import org.hibernate.mapping.Set;
import org.mozilla.javascript.ObjToIntMap.Iterator;

import com.ultrapower.assess.manager.AssessKpiValueManager;
import com.ultrapower.assess.manager.AssessRecordsManager;
import com.ultrapower.assess.manager.AssessResultManager;
import com.ultrapower.assess.model.AssessKpiValue;
import com.ultrapower.assess.model.AssessProp;
import com.ultrapower.assess.model.AssessRecords;
import com.ultrapower.assess.model.AssessResult;
import com.ultrapower.assess.util.Util;
import com.ultrapower.assess.util.expression.Expressions;

public class CalculateImpl implements Calculate {

	private static Logger logger = Logger.getLogger(Calculate.class);
	private AssessRecordsManager assessRecordsManager;
	private AssessResultManager assessResultManager;
	private AssessKpiValueManager assessKpiValueManager;

	/**
	 * resultSet循环两次，第一次将prop中proptype等于2的指标值填到result表中。// 第二次循环统一计算result的得分。
	 */
	@SuppressWarnings("unchecked")
	public double generaterAssessResult(AssessRecords assessRecords) {
		Set<AssessResult> resultSet = assessRecords.getAssessResults();
		Iterator<AssessResult> iterator = resultSet.iterator();

		List<AssessProp> propList = new ArrayList<AssessProp>();
		while (iterator.hasNext()) { // 如果是手动计算，先将指标值放入assessResult表中
			AssessProp assessProp = iterator.next().getAssessProp();
			if (assessProp.getPropType() == 2L) {
				AssessKpiValue kpiValue = null;
				AssessResult result = null;
				try {
					kpiValue = assessKpiValueManager
							.getKpiValueByRecordAndProp(assessRecords,
									assessProp);
				} catch (Exception e) {
					logger.error("kpivalue未入库 assessPropID"+assessProp.getId());
				}
				try {
					result = assessResultManager.getResultByRecordAndProp(
							assessRecords, assessProp);
				} catch (Exception e) {
					logger.error(e);
				}
				if (kpiValue != null && kpiValue.getKpiValue() != null) { // 如果可以查到值，则将值放入result中。
					result.setKpiValue(kpiValue.getKpiValue());
					propList.add(assessProp);
				} else {
					result.setKpiValue(""); // KPIValue字段无值，不需要进行下一步计算。
					result.setScore(0D);
					logger.debug("kpivalue为入库:assessresultId="+result.getId());
				}
				assessResultManager.save(result);
				logger.debug("保存Assess_Result :"+result.getId());
			}

		}

		double total = 0D;
		for (AssessProp prop : propList) {
			// FISHTODOH<完成待测试> 计算
			// 判断计算结果需要的值是否完整，不完整的话，默认得分为0
			// assessResultManager.updateResultScore(assessRecords, prop,
			// score);
			AssessResult result = assessResultManager.getResultByRecordAndProp(
					assessRecords, prop);
			String kpiValue = result.getKpiValue();
			if (kpiValue != null && !"".equals(kpiValue)) {
				String expr = prop.getScoreExpression();
//				expr.replace(Util.VAR, kpiValue + "D");
				double score = 0D;
				try {
					score = Double.parseDouble(Expressions.eval(expr,kpiValue)
							.toString().trim());
					total += score;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("计算指标得分异常，AssessProp.id=" + prop.getId() + ","
							+ e);
				}
				result.setScore(score);
				assessResultManager.save(result);
			}
		}
		assessRecords.setAssessId(Util.SYSTEM);
		assessRecords.setAssessTimes(assessRecords.getAssessTimes() == null ? 1
				: assessRecords.getAssessTimes() + 1);
		assessRecords.setAssessDate(new Date());
		assessRecords.setAssessTotal(total);
		assessResultManager.save(assessRecords);
		// FISHTODO 保存AssessRecord
		return total;
	}

	public List<AssessRecords> getNeedCalculateRecordsList() {
		return assessRecordsManager.getNeedCalculateRecordsList();
	}

	// ////////////////////////////////////////////////////////////////////
	// /////////以下是Spring需要的set方法。BEGIN
	public void setAssessRecordsManager(
			AssessRecordsManager assessRecordsManager) {
		this.assessRecordsManager = assessRecordsManager;
	}

	public void setAssessResultManager(AssessResultManager assessResultManager) {
		this.assessResultManager = assessResultManager;
	}

	public void setAssessKpiValueManager(
			AssessKpiValueManager assessKpiValueManager) {
		this.assessKpiValueManager = assessKpiValueManager;
	}

	// ////////////////// END ////////////////////////////////////////////

}
