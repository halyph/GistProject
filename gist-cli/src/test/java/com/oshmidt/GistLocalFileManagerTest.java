package com.oshmidt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.testng.annotations.Test;

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
    public void testReadFakeGists() {
        GistLocalFileManager gf = new GistLocalFileManager();
        String path = "fake/Path";
        gf.setRepoPath(path);
        assertEquals(null, gf.readGists());   
    }
    
/*
    @Test
    public void testReadGists() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException, IOException {
    	
    	List<GistFile> fileList = new ArrayList<GistFile>();
    	for (int i = 0; i < 5; i++) {
    		fileList.add(new GistFile());
    	}
    	
    	ObjectInputStream oin = mock(ObjectInputStream.class);
    	when(oin.readObject()).thenReturn(new Gist());
    	
    	File gistFolder = mock(File.class);
    	when(gistFolder.exists()).thenReturn(true);
    	when(gistFolder.listFiles()).thenReturn((File[]) fileList.toArray());
    	
    	Class<?> c = GistLocalFileManager.class;
    	Object obj = c.newInstance();
    	GistLocalFileManager test = (GistLocalFileManager) obj;
    	
    	Field f = test.getClass().getDeclaredField("gistFolder");
        f.setAccessible(true);
        f.set(test, gistFolder);
        
        f = test.getClass().getDeclaredField("oin");
        f.setAccessible(true);
        f.set(test, oin);
        
        assertNotNull(test.readGists());
       
    }*/

    
    
    
  /*  
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
 
        gf.writeFiles(gists);
        gf.writeGists(gists);   
    }
    
*/
 

}



