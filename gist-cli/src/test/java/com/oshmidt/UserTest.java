package com.oshmidt;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author oshmidt
 *Tests for User.class
 */
public class UserTest {

    private User user;

    @BeforeMethod
    public void init() {
        user = new User();
    }

    @Test
    public void testUserLogin() {
        user.setLogin("UserLogin");
        assertTrue(user.getLogin().equalsIgnoreCase("UserLogin"));
    }

    @Test
    public void testUserPassword() {
        user.setPassword("pAsSwOrD");
        assertEquals("pAsSwOrD", user.getPassword());
    }

    @Test
    public void testImportUser() {
        user.importUser();
        if (user.importUser()) {
            assertNotNull(user.getLogin());
        }
    }

    @Test
    public void testImportNotExistUserFile() {
        String file = "wrong.file";
        assertTrue(!user.importUser(file));
    }


    @Test
    public void testImportWrongUserFile() {
        //need implement !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String file = "wrong.file";
        assertTrue(!user.importUser(file));

    }

}
