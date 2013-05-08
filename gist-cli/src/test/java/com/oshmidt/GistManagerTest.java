package com.oshmidt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.Gist;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class GistManagerTest {

    private GistManager gistManager;

    @Mock
    private User user;

    @Mock
    private GistFetcher gf;

    @BeforeMethod
    public void before() throws IOException {
        System.out.println("before block");

        gistManager = new GistManager();
     /*   user = new User();
        user.setLogin("login");
        user.setPassword("password");*/
        
         user = mock(User.class);
     //    when(user.getLogin()).thenReturn(s); System.out.println("4");
   //      when(user.getPassword()).thenReturn(null); System.out.println("5");
    //     when(user.importUser()).thenReturn(null); System.out.println("6");
         
    }

    @Test
    public void testInitUser() {
        gistManager.initUser(user.getLogin(), user.getPassword());
        assertEquals(user.getLogin(), gistManager.getUser().getLogin());
        assertEquals(user.getPassword(), gistManager.getUser().getPassword());
    }

    @Test
    public void testImportUser() {
        gistManager.importUser();
    }

    /*
     * @Test public void testReadAndWriteLocalGists() { GistRepository glfm =
     * mock(GistRepository.class); when(glfm.readGists()).thenReturn(null);
     * 
     * gm.setRepository(glfm); gm.readLocalGists(); ArrayList<Gist> list = new
     * ArrayList<Gist>(); for (int i = 0; i < 5; i++) { list.add(new Gist()); }
     * when(glfm.readGists()).thenReturn(list); gm.readLocalGists();
     * gm.showGists(); }
     */

    @Test
    public void testLoadGists() throws IOException {
        System.out.println("testLoadGists");
        GistFetcher fetcherMock = mock(GistFetcher.class);
        ArrayList<Gist> list = new ArrayList<Gist>();
        for (int i = 0; i < 5; i++) {
            list.add(new Gist());
        }

        try {
            when(fetcherMock.loadGists(user)).thenReturn(list);
        } catch (Exception e) {
            System.out.println("init mock behavior");
            e.printStackTrace();
        }

        gistManager.setGistFetcher(fetcherMock);
        gistManager.setUser(user);
        List<Gist> loadGists = null;
        loadGists = gistManager.loadGists();
        
        assertEquals(loadGists.size(), 5);

    }

}
