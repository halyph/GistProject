package com.oshmidt;

public class StringUtils {

	public static String convertToString(String[] args) {
		StringBuilder full = new StringBuilder();
		for (String string : args) {
			full.append(string + " ");
		}
		return full.toString();
	}
}
