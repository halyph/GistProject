package com.oshmidt;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;
import org.junit.Before;
import org.junit.Test;

public class GistFetcherTest {

    // @Mock
    private GistService service;

    @Before
    public void before() throws IOException {
        service = mock(GistService.class);
        Gist g = new Gist();
        g.setDescription("test gist");
        // service.getClient().setCredentials(user.getLogin(),
        // user.getPassword());
        when(service.getGist("f")).thenReturn(g);
        // GitHubClient gc = new GitHubClient();
        // when(service.getClient().thenReturn(gc));
        // service.getClient()g.th;
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
        gf.loadGist("f", user);
        Gist g = gf.loadGist("f", user);
        System.out.println(g.getDescription());
    }

    /*
     * public final Gist loadGist(final String gistId, final User user) throws
     * IOException { setClientCredentials(user); return service.getGist(gistId);
     * }
     */

}
