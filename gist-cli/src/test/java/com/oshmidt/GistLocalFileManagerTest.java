package com.oshmidt;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
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
    
    @Test
    public void testReadGists() {
        GistLocalFileManager gf = new GistLocalFileManager();
        String path = "fake/Path";
        gf.setRepoPath(path);
        assertEquals(null, gf.readGists());   
    }
    
    
    @Test
    public void testReadFiles() {
        GistLocalFileManager gf = new GistLocalFileManager();
        String path = "fake/Path";
        gf.setRepoPath(path);
        assertEquals(null, gf.readFiles());   
    }
    
    
    @Test
    public void testWriteGists() {
        GistLocalFileManager gf = new GistLocalFileManager();
        List<Gist> gists = new ArrayList<Gist>();
        for (int i = 0; i < 5; i++){
            Gist g = new Gist();
            
            g.setId(String.valueOf(i));
            gists.add(g);
            
        /*    Map<String, GistFile> gfiles = new HashMap<String, GistFile>();
            for (int j = 0; i < 4; j++){
                GistFile gfile = new GistFile();
             //   gfile.setFilename(String.valueOf(j));  
                gfiles.put(gfile.getFilename(), gfile);      
            }
            g.setFiles(gfiles);*/
        }
        gf.writeFiles(gists);
        gf.writeGists(gists);   
    }
    

 

}



