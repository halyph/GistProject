package com.oshmidt;

/**
 * @author oshmidt
 *         <p>
 *         Class for difference operation with strings.
 */
public class StringUtils {

	private StringUtils() {
	}

	/**
	 * Convert string array to string
	 * 
	 * @param args
	 *            - string array
	 * @return string - glued array
	 */
	public static String convertToString(String[] args) {
		StringBuilder full = new StringBuilder();
		for (String string : args) {
			full.append(string + " ");
		}
		return full.toString();
	}
}
