
package com.eidiko.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtility {

	public static boolean isValidPassword(String password) {

		// Regex to check valid password.
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);

		// If the password is empty // return false
		if (password == null) {
			return false;
		}

// Pattern class contains matcher() method to find matching between given password and regular expression.
		Matcher m = p.matcher(password);

// Return if the password matched the ReGex 
		return m.matches();
	}

	// mobile no Validation
	public static boolean isMobileNoValid(Long mobileNo) {
		boolean p = Pattern.matches("[6789][0-9]{9}", String.valueOf(mobileNo));
		return p;
	}
}
