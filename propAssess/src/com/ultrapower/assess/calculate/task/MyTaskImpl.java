package com.ultrapower.assess.calculate.task;

import org.apache.log4j.Logger;

import com.ultrapower.assess.calculate.Calculate;
import com.ultrapower.assess.model.AssessRecords;

public class MyTaskImpl implements Task {
	private static Logger logger = Logger.getLogger(MyTaskImpl.class);
	private Calculate calculate;

	public void setCalculate(Calculate calculate) {
		this.calculate = calculate;
	}

	public void execute() {
		// 1.查询assess records表 如果有满足运行条件的 开始生成考核记录
		// List<AssessRecords> ars= query from db
		// for(AssessRecords ar:ars)
		// {
		// 2. result =generaterAssessResult(ar );
		// if true{
		// 3.更新assess records表状态 需要加一个字段做标识???
		// }
		// }

		//TODO 这部分暂时不提交
		List<AssessRecords> list = calculate.getNeedCalculateRecordsList();
		if (list == null && "".equals(list)) {
			logger.info("没有需要计算的AssessRecords");
			return;
		} else {
			logger.info("本次需要计算的AssessRecords:" + list.toString());
			logger.info("开始计算");
		}
		for (AssessRecords ar : list) {
			calculate.generaterAssessResult(ar);
		}
		logger.debug("计算结束");
		System.err.println(Thread.currentThread().getName() + "开始执行");
		System.out.println(calculate.getNeedCalculateRecordsList());
		logger.info("MSG");
		System.out.println("11");
		System.err.println(Thread.currentThread().getName() + "执行结束");
	}
}
