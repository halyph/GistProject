package com.oshmidt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.testng.annotations.Test;

/**
 * @author oshmidt
 *Test for Messages.class
 */
public class MessagesTest {
	
	public MessagesTest() {
		
	}

    @Test
    public void testGetString() {
        String key = "Commands.long.help";
        ResourceBundle rb = ResourceBundle.getBundle("commandLine");
        String value1 = rb.getString(key);
        String value2 = Messages.getCommand(key);
        assertEquals(value1, value2);
    }

    @Test
    public void testGetStringInvalidKey() {
        String key = "invalidKey";
        assertNotNull(Messages.getString(key));
    }
    
    @Test
    public void testGetCommandInvalidKey() {
        String key = "invalidKey";
        assertNotNull(Messages.getCommand(key));
    }

    @Test
    public void testGetStringWithParameters() {
        String key1 = "com.oshmidt.cli.aplicationStartOption";
        int key2 = 2;
        ResourceBundle rb = ResourceBundle.getBundle("GistMessages");
        String value1 = MessageFormat.format(rb.getString(key1), key2);
        String value2 = Messages.getString(key1, key2);
        assertEquals(value1, value2);
    }

    @Test
    public void testGetStringWithParametersInvalidKey() {
        String key = "invalidKey";
        int key2 = 2;
        assertNotNull(Messages.getString(key), key2);
    }


    @Test
    public void testIsPrivateConstructor() throws Exception {
        Constructor<?>[] cons = Messages.class.getDeclaredConstructors();
        cons[0].setAccessible(true);
        cons[0].newInstance((Object[]) null);
    }


}
