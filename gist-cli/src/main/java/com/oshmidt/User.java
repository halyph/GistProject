package com.oshmidt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author oshmidt
 *         <p>
 *         Class for saving and keeping user data.
 */
public class User {
	
	public User() {
		
	}

    private static Logger logger = Logger.getLogger("logfile");

    private static final String CONFIG_FILE_NAME = "config.properties";

    private String login;

    private String password;
    
    private Properties prop;

    /**
     *Getter for login field.
     *
     *@return String login
     */
    public final String getLogin() {
        return login;
    }

    /**
     *Setter for login field.
     *
     *@param login
     *           String
     */
    public final void setLogin(final String login) {
        this.login = login;
    }

    /**
     *Getter for password field.
     *
     *@return String password
     */
    public final String getPassword() {
        return password;
    }

    /**
     *Setter for password field.
     *
     *@param password
     *           String
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     *Import user from local property file.
     *
     *@param filename
     *           name for property file with user data
     *@return boolean success status
     */
    public final boolean importUser(final String filename) {
        prop = new Properties();
        try {
            logger.info(Messages
                    .getString("com.oshmidt.gistManager.tryLoadUserData"));
            prop.load(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            logger.error(Messages.getString("com.oshmidt.cli.userFileNotFoutd"));
            return false;
        } catch (IOException e) {
            logger.error(Messages.getString("com.oshmidt.cli.wrongRserFile"));
            return false;
        }
        setLogin(prop.getProperty("login"));
        setPassword(prop.getProperty("password"));
        return true;

    }

    /**
     *Import user from local property file.
     *{@link com.oshmidt.User#CONFIG_FILE_NAME}
     *
     *@return boolean success status
     */
    public final boolean importUser() {
        return importUser(CONFIG_FILE_NAME);
    }

}
