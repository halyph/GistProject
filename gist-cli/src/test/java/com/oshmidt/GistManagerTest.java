package com.oshmidt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.eclipse.egit.github.core.Gist;
import org.junit.Before;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GistManagerTest {

    private GistManager gm;
    
    private User user;
    
    @Mock
    private GistFetcher gf;
    
    private Gist gist;
    

    @BeforeMethod
    public void before() throws IOException {
        System.out.println("before block");
        gm = new GistManager();
        user = new User();
        user.setLogin("login");
        user.setPassword("password");
        gf = mock(GistFetcher.class);
        gist = new Gist();
     //   gist.setId("test");
        when(gf.loadGist("test", user)).thenReturn(gist);
    }

    @Test()
    public void testInitUser() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String username = "user";
        String password = "password";
        gm.initUser(username, password);
        
        Class c = gm.getClass();
         
        Field field = c.getDeclaredField("user");
        field.setAccessible(true);
        User us = (User) field.get(gm);

        assertEquals(username, us.getLogin());
        assertEquals(password, us.getPassword());
    }

 /*   @Test//(expectedExceptions=IOException)
    @Deprecated
    public void testImportUser() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
  //      user = mock(User.class);
  //      when(user.importUser()).thenReturn(true);
        
        GistManager gm = new GistManager();
        gm.importUser();
        
        Class c = gm.getClass();
         
        Field field = c.getDeclaredField("user");
        field.setAccessible(true);
        User us = (User) field.get(gm);
        System.out.println("login" + us.getLogin());
        assertNotNull(user);
        
    }*/

    @Test
    public void testLoadGists() {
       Gist g = gm.loadGist("test");
       assertEquals(gist, g);
       System.out.println("gist id: " + gist.getId());
    }

}
