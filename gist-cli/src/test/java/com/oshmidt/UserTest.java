package com.oshmidt;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 * @author oshmidt
 *Tests for User.class
 */
public class UserTest {

    @Test
    public void testUserLogin() {
        User user = new User();
        user.setLogin("UserLogin");
        assertTrue(user.getLogin().equalsIgnoreCase("UserLogin"));
    }

    @Test
    public void testUserPassword() {
        User user = new User();
        user.setPassword("pAsSwOrD");
        assertEquals("pAsSwOrD", user.getPassword());
    }

    @Test
    public void testImportUser() {
        User user = new User();
        user.importUser();
        if (user.importUser()) {
            assertNotNull(user.getLogin());
        }
    }

    @Test
    public void testImportNotExistUserFile() {
        User user = new User();
        String file = "wrong.file";
        assertTrue(!user.importUser(file));
    }


    @Test
    public void testImportWrongUserFile() {
        //need implement !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        User user = new User();
        String file = "wrong.file";
        assertTrue(!user.importUser(file));

    }

}
