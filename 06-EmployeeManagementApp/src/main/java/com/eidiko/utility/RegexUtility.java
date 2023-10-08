package com.eidiko.utility;

import java.util.regex.Pattern;

public class RegexUtility {
	// Contact no Validation
	public static boolean isMobileNoValid(Long mobileNo) {
		boolean p = Pattern.matches("[6789][0-9]{9}", String.valueOf(mobileNo));
		return p;
	}
}
