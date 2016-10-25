package com.ultrapower.assess.util.expression;

import jsx3.lang.Object;

import org.apache.log4j.Logger;
import org.apache.xpath.operations.String;

import bsh.Interpreter;

import com.ultrapower.assess.util.Util;

public class Expressions {
	private static Logger logger = Logger.getLogger(Expressions.class);
	private static Interpreter interpreter;
	private static Interpreter tester;
	private static int KE = 2;  //默认小数点保留位数
	static {
		interpreter = new Interpreter();
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(Expressions.class
					.getResourceAsStream("Functions.bsh"));
			interpreter.eval(reader);
		} catch (Exception e) {
			logger.error("初始化函数表达式失败" + e);
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	/**
	 * 进行表达式运算
	 * 
	 * @param expr
	 * @return
	 * @throws Exception
	 */
	public synchronized static Object eval(String expr,String val) throws Exception {
		if (expr == null) {
			return null;
		}
		expr = expr.replace(Util.VAR, val+"D");
		
		return interpreter.eval("round("+expr+","+KE+")");
	}

	/**
	 * 表达式进行测试
	 * 
	 * @param expr
	 * @throws Exception
	 */
	public synchronized static void test(String expr,String test) throws Exception {
		if (tester == null) {
			tester = new Interpreter();
			InputStreamReader reader = null;
			try {
				reader = new InputStreamReader(Expressions.class
						.getResourceAsStream("test.bsh"));
				tester.eval(reader);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("初始化表达式函数库失败", e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
					}
				}
			}
		}
		if (expr == null) {
			throw new Exception("表达式为空.");
		}
		expr = expr.replace("$var", test+"D");
		Object obj =  tester.eval(expr);
		System.out.println(obj);
	}

	public static void main(String[] args) {
		/*
		 * String ss = "round($var*6/13,2)"; ss = ss.replace("$var", 10+"D");
		 * System.out.println("ss:"+ss); try { System.out.println(eval(ss)); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		try {
			String ss = "$var%10";
//			ss = ss.replace("$var", "10D");
			String test = "345.02";
			System.out.println(eval(ss,test));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
