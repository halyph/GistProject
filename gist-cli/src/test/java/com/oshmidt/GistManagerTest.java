package com.oshmidt;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
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
	
	public GistManagerTest() {	
	}

    private GistManager gistManager;

    @Mock
    private User user;

    /*
     * @Mock private GistFetcher gf;
     */

    @Mock
    private GistFetcher fetcherMock;

    @Mock
    private GistRepository repository;

    private List<Gist> arrayList;
    
    private final int COUNT = 5;

    private static final String GIST_ID = "gistID";

    private Gist testGist;

    @BeforeMethod
    public void before() throws IOException {
        gistManager = new GistManager();
        testGist = new Gist();
        fetcherMock = mock(GistFetcher.class);
        gistManager.setGistFetcher(fetcherMock);
        repository = mock(GistRepository.class);
        gistManager.setRepository(repository);
        arrayList = new ArrayList<Gist>();
        for (int i = 0; i < COUNT; i++) {
            Gist g = mock(Gist.class);
            when(g.getDescription()).thenReturn(Integer.toString(i));
            when(g.getId()).thenReturn(Integer.toString(i));
            when(g.getFiles()).thenReturn(null);
            /*
             * Map<String, GistFile> files = new HashMap<>(); for (int j = 0; j
             * < 3; j++){ GistFile gf = new GistFile();
             * files.put(Integer.toString(j), gf); } g.setFiles(files);
             */
            arrayList.add(g);
        }
        user = mock(User.class);
        when(fetcherMock.loadGists(user)).thenReturn(arrayList);
        when(fetcherMock.loadGist(GIST_ID, user)).thenReturn(new Gist());
        when(fetcherMock.updateGist(user, testGist)).thenReturn(testGist);
        when(fetcherMock.deleteGist(user, GIST_ID)).thenReturn(true);
        when(fetcherMock.deleteGists(user, arrayList)).thenReturn(true);
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

    @Test
    public void testReadAndWriteLocalGists() {
        gistManager.showGists();
        when(repository.readGists()).thenReturn(null);
        gistManager.readLocalGists();
        when(repository.readGists()).thenReturn(arrayList);
        gistManager.readLocalGists();
        gistManager.showGists();
    }

    @Test
    public void testLoadGists() {
        gistManager.setUser(user);
        List<Gist> loadGists = null;
        loadGists = gistManager.loadGists();
        gistManager.findGist(GIST_ID);
        gistManager.findGist("2");
        assertEquals(loadGists.size(), COUNT);
    }

    @Test
    public void testLoadGist() throws IOException {
        gistManager.setUser(user);
        assertNotNull(gistManager.loadGist(GIST_ID));
    }

    @Test
    public void testUpdateGists() {
        gistManager.setUser(user);
        assertEquals(testGist, gistManager.updateGist(testGist));
    }

    @Test
    public void testDeleteGist() {
        gistManager.setUser(user);
        assertTrue(gistManager.deleteGist(GIST_ID));
    }

    @Test
    public void testAddNewGist() {
        gistManager.setUser(user);
        assertEquals(testGist, gistManager.addNewGist(testGist));
    }

    @Test
    public void testDownloadGistFiles() {
        gistManager.setUser(user);
        gistManager.loadGists();
        gistManager.downloadGistFiles("all");
        gistManager.downloadGistFiles(GIST_ID);
    }

    @Test
    public void testWriteGistsToRepository() {
        gistManager.setUser(user);
        gistManager.loadGists();
        gistManager.writeGistsToRepository();
    }

    @Test
    public void testLoadAndSaveGists() {
        gistManager.setUser(user);
        gistManager.loadAndSaveRemoteGists();
    }

    @Test
    public void testGistFetcher() {
        gistManager.setGistFetcher(fetcherMock);
        assertEquals(fetcherMock, gistManager.getGistFetcher());
    }

    @Test
    public void testRepository() {
        gistManager.setRepository(repository);
        assertEquals(repository, gistManager.getRepository());
    }

    @Test(expectedExceptions = Exception.class)
    public void testLoadGistsException() throws Exception {
        when(fetcherMock.loadGists(null)).thenThrow(new RuntimeException());
        gistManager.setUser(null);
        gistManager.loadGists();
    }

}
