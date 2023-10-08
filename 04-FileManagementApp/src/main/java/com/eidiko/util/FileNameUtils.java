package com.eidiko.util;

public class FileNameUtils {

	public static boolean isSupportedExtension(String extension) {

		System.out.println(extension);
		return extension != null && (extension.equals(".png"));
		// || extension.equals("jpg") || extension.equals("jpeg")
		// || extension.equals("pdf"));

	}

	public static String getFileExtension(String originalFileName) {
		String name = originalFileName;
		int lastIndexOf = name.lastIndexOf(".");

		if (lastIndexOf == -1) {
			return ""; // empty extension
		}
		return name.substring(lastIndexOf);
	}

}
