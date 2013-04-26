package com.oshmidt;

import org.testng.annotations.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author oshmidt
 *Test for GistLocalFileManager.class
 */
public class GistLocalFileManagerTest {

    @Test
    public void testRepoPath() {
        GistLocalFileManager gf = new GistLocalFileManager();
        String path = "local/Path";
        gf.setRepoPath(path);
        assertEquals(path, gf.getRepoPath());
        gf.loadDefaultRepoPath();
        assertNotNull(gf.getRepoPath());
    }

}
