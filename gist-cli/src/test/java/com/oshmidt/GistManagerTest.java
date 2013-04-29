package com.oshmidt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GistManagerTest {

    private GistManager gm;
    private User user;

    @BeforeMethod
    public void before() throws IOException {
        System.out.println("before block");
        gm = new GistManager();
        user = new User();
        

      
    }

    @Test()
    public void testInitUser() {
        String username = "user";
        String password = "password";
        gm.initUser(username, password);
        assertEquals(username, gm.getUser().getLogin());
        assertEquals(password, gm.getUser().getPassword());
    }

    @Test
    public void testImportUser() {
        user = mock(User.class);
        when(user.importUser()).thenReturn(true);
        
        gm.importUser();
    }

    @Test
    public void testWriteLocalGists() {
        gm.writeLocalGists();
    }

}
