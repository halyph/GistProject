package com.oshmidt;

import static org.junit.Assert.*;

import java.io.IOException;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.GitHubResponse;
import org.eclipse.egit.github.core.service.GistService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GistFetcherTest {



    @Mock
    private GistService service;


    @Before
    public void before() throws IOException {
        service = mock(GistService.class);
        Gist g = new Gist();
        g.setDescription("test gist");
       // service.getClient().setCredentials(user.getLogin(), user.getPassword());
    
        when(service.getGist("f")).thenReturn(g);
        
    }

    @Test
    public void getGist() throws IOException {
        System.out.println(service);

        GistFetcher gf = new GistFetcher(service);
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");

        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        Gist g = gf.loadGist("f", user);
        System.out.println(g.getDescription());
    }

    /*
    public final Gist loadGist(final String gistId, final User user) throws IOException {
        setClientCredentials(user);
        return service.getGist(gistId);
   }
*/

}
