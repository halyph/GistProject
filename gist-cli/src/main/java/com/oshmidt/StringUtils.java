package com.oshmidt;

/**
 * @author oshmidt
 *         <p>
 *         Class for difference operation with strings.
 */
public final class StringUtils {

	/**
	 * Hiding class constructor to prohibit creating class instance.
	 */
	private StringUtils() {
	}

	/**
	 * Concatenate string array to string.
	 * 
	 * @param args
	 *            - String array
	 * @param separator
	 *            - String
	 * @return string - glued array
	 */

	public static String convertToString(final String[] args,
			final String separator) {
		StringBuilder full = new StringBuilder();
		for (String string : args) {
			full.append(string + separator);
		}
		return full.toString();
	}
}
