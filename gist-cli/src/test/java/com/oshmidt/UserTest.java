package com.oshmidt;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author oshmidt
 *Tests for User.class
 */
public class UserTest {

    private User user;
    
    private Properties prop;
    
    public UserTest() {
    	
    }

    @BeforeMethod
    public void init() {
        user = new User();
        prop = mock(Properties.class);
       
        when(prop.getProperty("login")).thenReturn("login");
        when(prop.getProperty("password")).thenReturn("password");
        
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


  /*  @Test
    public void testImportWrongUserFile() {
        //need implement !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String file = "wrong.file";
        assertTrue(!user.importUser(file));
    }*/
   /* 
    @Test
    public void testImportUserFile() throws ClassNotFoundException, 
    			InstantiationException, IllegalAccessException, 
    			NoSuchFieldException {
    	Class<?> c = Class.forName("User");
    	Object obj = c.newInstance();
    	User test = (User) obj;
    	
    	Field f = test.getClass().getDeclaredField("prop");
        f.setAccessible(true);
        f.set(test, prop);
        
        String file = "properties.file";
        assertTrue(user.importUser(file));
    }*/

}
