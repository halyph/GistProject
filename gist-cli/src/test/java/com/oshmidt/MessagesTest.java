package com.oshmidt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.testng.annotations.Test;

public class MessagesTest {

    @Test
    public void testGetString() {
        String key = "com.oshmidt.cli.long.help";
        ResourceBundle rb = ResourceBundle.getBundle("GistMessages");
        String value1 = rb.getString(key);
        String value2 = Messages.getString(key);
        assertEquals(value1, value2);
    }

    @Test
    public void testGetStringInvalidKey() {
        String key = "invalidKey";
        assertNotNull(Messages.getString(key));
    }

    @Test
    // (expectedExceptions = NullPointerException.class)
    public void testGetStringWithParameters() {
        String key1 = "com.oshmidt.cli.aplicationStartOption";
        int key2 = 2;
        ResourceBundle rb = ResourceBundle.getBundle("GistMessages");
        String value1 = MessageFormat.format(rb.getString(key1), key2);
        String value2 = Messages.getString(key1, key2);
        assertEquals(value1, value2);
    }

}
