package com.oshmidt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class User {

	private static Logger userManagerLogger = Logger.getLogger("logfile");

	private static final String CONFIG_FILE_NAME = "config.properties";

	private String login;

	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Import user from local property file
	 * 
	 * @param filename
	 *            name for property file with user data
	 */
	public boolean importUser(String filename) {
		Properties prop = new Properties();
		// try {
		try {
			prop.load(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			userManagerLogger.error(Messages
					.getString("com.oshmidt.cli.userFileNotFoutd"));
			return false;
		} catch (IOException e) {
			userManagerLogger.error(Messages
					.getString("com.oshmidt.cli.wrongRserFile"));
			return false;
		}
		setLogin(prop.getProperty("login"));
		setPassword(prop.getProperty("password"));
		return true;

	}

	/**
	 * Import user from local property file
	 * {@link com.oshmidt.User#CONFIG_FILE_NAME}
	 */
	public void importUser() {
		importUser(CONFIG_FILE_NAME);
	}

}
