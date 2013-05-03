package com.oshmidt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GistFetcherTest {

    @Mock
    private GistService service;
    @Mock
    private GitHubClient gc;
    
    private static String DESCRIPTION = "test gist";
    
    private User user;

    private GistFetcher gf;
    
    private Gist gist; 
    
  //  private List list;
    
    @BeforeMethod
    public void before() throws IOException {
        user = new User();
        user.setLogin("login");
        user.setPassword("password");
        service = mock(GistService.class);
        gc = mock(GitHubClient.class);
        gist = new Gist();
        gist.setDescription(DESCRIPTION);
       
       // list.add(gist);
        
        when(service.getGist("test")).thenReturn(gist);
        when(service.updateGist(gist)).thenReturn(gist);
     //   when(service.getGists(user.getLogin()).thenReturn(list);
        when(service.createGist(gist)).thenReturn(gist);
        //when(service.deleteGist("test")).thenThrow();
        when(service.getClient()).thenReturn(gc);
        when(gc.setCredentials(user.getLogin(), user.getPassword())).thenReturn(gc);
        gf = new GistFetcher(service);
    }

    @Test
    public void testGetGist() throws IOException { 
        Gist g = gf.loadGist("test", user);
        assertEquals(gist, g); 
    }
    
    
    
    @Test
    public void testAddNewGist() throws IOException{
        Gist g = gf.addNewGist(user, gist);
        assertEquals(gist, g); 
    }
    
    
    @Test
    public void testUpdateGist() throws IOException { 
        Gist g = gf.updateGist(user, gist);
        assertEquals(DESCRIPTION, g.getDescription()); 
    }
    
    @Test
    public void testDeleteGist() throws IOException{ 
        gf.deleteGist(user, "test");
    }
    
  /*  @Test
    public void testLoadGists() throws IOException {
        System.out.println(gf.loadGists(user));
    }*/


}
