package com.yogi;

import java.nio.file.Path;

public class yogiUtil {
	/**
	 * This method is used to format String 
	 * 
	 * @param stringTobeFormated  String
	 * @param parameters variable argument String array
	 * @return
	 */
	protected static String getFormattedString(final String stringTobeFormated, final String... parameters) {
		String formattedString = stringTobeFormated.trim();
		for (int i = 0; i < parameters.length; i++) {
			formattedString = formattedString.replace(
					"[" + i + "]",String.valueOf(parameters[i].trim()));
		}
		return formattedString;
	}

	/**
	 * This Method is used to create file name out of loc file
	 * 
	 * @param path
	 * @return
	 */
	static String CreateFileName(Path path) {
		String PFileName=path.getFileName().toString().split("[.]")[0];
		return	PFileName.substring(0, 1).toUpperCase()+PFileName.substring(1).toLowerCase();
	}
}
