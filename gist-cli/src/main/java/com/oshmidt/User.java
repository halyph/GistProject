package com.oshmidt;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class User {
	
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
	
	
	public void importUser(String filename) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filename));
			setLogin(prop.getProperty("login"));
			setPassword(prop.getProperty("password"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void importUser() {
		importUser(CONFIG_FILE_NAME);
	}

}
