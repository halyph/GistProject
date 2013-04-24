package com.oshmidt;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author oshmidt
 * <p>
 * Class helper. Used for simplification access to resources. 
 */
public class Messages {
	private static final String BUNDLE_NAME = "GistMessages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	/**
	 * Method return string by her bundle from bundle resource file
	 * {@link com.oshmidt.Messages#BUNDLE_NAME}
	 * 
	 * @param key
	 *            - key value
	 * @return string - parsed string
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/**
	 * Method get string by her bundle from bundle resource file
	 * {@link com.oshmidt.Messages#BUNDLE_NAME} and insert inside parameters.
	 * 
	 * @param key
	 *            - key value
	 * @param params
	 *            - parameters for inserting to string
	 * @return string - parsed string
	 */
	public static String getString(String key, Object... params) {
		try {
			return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
