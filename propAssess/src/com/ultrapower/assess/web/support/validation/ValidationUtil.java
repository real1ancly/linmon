package com.ultrapower.assess.web.support.validation;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.xpath.operations.String;

public class ValidationUtil {

	// 必须是数字,字母,特殊符号($#@%)两种或两种以上组合
	private static String passValid = "(([0-9]{1,})+[a-zA-Z$#@%]{1,}|([a-zA-Z]{1,})+[0-9$#@%]{1,}|([$#@%]{1,})+[a-zA-Z0-9]{1,})+[0-9a-zA-Z$#@%]*";

	// 只能是字母,数字,中横线，下划线组成
	private static String userIdValid = "^[a-z0-9-_]+$";

	public static boolean validUserId(String userId) {
		if (StringUtils.isBlank(userId))
			return false;
		Pattern pc = Pattern.compile(userIdValid);
		Matcher m = pc.matcher(userId);
		return m.matches();
	}

	public static boolean validPassword(String password) {
		if (StringUtils.isBlank(password))
			return false;
		Pattern pc = Pattern.compile(passValid);
		Matcher m = pc.matcher(password);
		return m.matches();
	}

	public static boolean validMail(String mail) {
		return GenericValidator.isEmail(mail);
	}

	public static void main(String[] args) {
		System.out.println(ValidationUtil.validMail("b_temp@chinabidding.com"));
	}

}
