package com.oshmidt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author oshmidt Test for GistLocalFileManager.class
 */
public class GistLocalFileManagerTest {
	
    private final int COUNT = 5;
    
    private File[] files;
    
    private List <Gist> listGists;
      
	public GistLocalFileManagerTest() {
		
	}
    
    private GistLocalFileManager repository;
    
    @BeforeMethod
    public void before() throws IOException, ClassNotFoundException {
        repository = new GistLocalFileManager();
        files = new File[COUNT];
        for (int i = 0; i < COUNT; i++) { 
            File f = mock(File.class);
            when(f.isFile()).thenReturn(true);
            when(f.getName()).thenReturn(Integer.toString(i) + ".gist");
            files[i] = f;
        }
        
        listGists = new ArrayList<Gist>();
    	for (int i = 0; i < COUNT; i++) {
    		Gist gist = new Gist();
    		gist.setDescription(Integer.toString(i));
    		Map<String, GistFile> map = new TreeMap<String, GistFile>();//new HashMap<String, GistFile>();
    		for (int j = 0; j < COUNT; j++) {
    			GistFile gistFile = new GistFile();
    			gistFile.setFilename(Integer.toString(j));
    			gistFile.setRawUrl("127.0.0.1");
    			map.put(Integer.toString(j), gistFile);
    		}
    		gist.setFiles(map);
    		listGists.add(gist);
    	}
        

        Gist gist = mock(Gist.class);
        when(gist.getDescription()).thenReturn("gist");
        
        ObjectInputStream objectInputStream = mock(ObjectInputStream.class);
        
        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        
        ObjectOutputStream objectOutputStream = mock(ObjectOutputStream.class);
    //    when(objectOutputStream.equals(anyObject())).thenReturn(true);
        
        ObjectInputStreamFactory oisf = mock(ObjectInputStreamFactory.class);
        when(oisf.objectInputStreamFactory(any(File.class))).thenReturn(objectInputStream);
        when(oisf.readObject(any(ObjectInputStream.class))).thenReturn(gist);
        
        FileOutputStreamFactory fosf = mock(FileOutputStreamFactory.class);
        when(fosf.fileOutputStreamFactory(anyString())).thenReturn(fileOutputStream);
        
        ObjectOutputStreamFactory oosf = mock(ObjectOutputStreamFactory.class);
        when(oosf.objectOutputStreamFactory(any(FileOutputStream.class))).thenReturn(objectOutputStream);
      //  when(oosf.writeObject(any(ObjectOutputStream.class), anyObject()));
        
  //      FileInputStream fileInputStreamMock = mock(FileInputStream.class);
        
        repository = new GistLocalFileManager(oisf, fosf, oosf);
        
    }

    @Test
    public void testRepoPath() {
        
        String path = "local/Path";
        repository.setRepoPath(path);
        assertEquals(path, repository.getRepoPath());
        repository.loadDefaultRepoPath();
        assertNotNull(repository.getRepoPath());
    }

    @Test
    public void testReadFakeGists() {
        String path = "fake/Path";
        repository.setRepoPath(path);
        assertEquals(null, repository.readGists());
    }
    
    
    @Test
    public void testReadGists() {
        File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        when(file.getName()).thenReturn("filename.gist");
        when(file.listFiles((FileFilter) any())).thenReturn(files);
        
        GistLocalFileManager spy = spy(repository);

        when(spy.gistFileFactory(anyString())).thenReturn(file);
      //  when(spy.fileInputStreamFactory(any(File.class))).thenReturn(fileInputStreamMock);
       // when(spy.objectInputStreamFactory((FileInputStream) Matchers.anyObject())).thenReturn(objectInputStreamMock);
        assertEquals(COUNT, spy.readGists().size());
    }
    
    
    @Test
    public void testCreateGistFilter() {
    	assertNotNull(repository.createGistFilter());
    }
    
    
    @Test
    public void testWriteGists() {
    	repository.writeGists(listGists);
    }
    
    @Test
    public void testWriteFiles() {
        /*List<Gist> list = new ArrayList<Gist>();
        for (int i = 0; i < 5; i++) {
            list.add(new Gist());
        }*/
        
    	repository.writeFiles(listGists);
    }
    
    
   /* @Test
    public void testWriteGists() {
        GistLocalFileManager gf = new GistLocalFileManager();
        FileOutputStream fileOutputStream = mock(FileOutputStream.class);
        gf.setFileOutputStream(fileOutputStream);
        List<Gist> list = new ArrayList<Gist>();
        for (int i = 0; i < 5; i++) {
            list.add(new Gist());
        }
             
        gf.writeGists(list);
    }*/

    /*
     * @Test public void testReadGists() throws InstantiationException,
     * IllegalAccessException, NoSuchFieldException, SecurityException,
     * ClassNotFoundException, IOException {
     * 
     * List<GistFile> fileList = new ArrayList<GistFile>(); for (int i = 0; i <
     * 5; i++) { fileList.add(new GistFile()); }
     * 
     * ObjectInputStream oin = mock(ObjectInputStream.class);
     * when(oin.readObject()).thenReturn(new Gist());
     * 
     * File gistFolder = mock(File.class);
     * when(gistFolder.exists()).thenReturn(true);
     * when(gistFolder.listFiles()).thenReturn((File[]) fileList.toArray());
     * 
     * Class<?> c = GistLocalFileManager.class; Object obj = c.newInstance();
     * GistLocalFileManager test = (GistLocalFileManager) obj;
     * 
     * Field f = test.getClass().getDeclaredField("gistFolder");
     * f.setAccessible(true); f.set(test, gistFolder);
     * 
     * f = test.getClass().getDeclaredField("oin"); f.setAccessible(true);
     * f.set(test, oin);
     * 
     * assertNotNull(test.readGists());
     * 
     * }
     */

    /*
     * @Test public void testReadFiles() { GistLocalFileManager gf = new
     * GistLocalFileManager(); String path = "fake/Path"; gf.setRepoPath(path);
     * assertEquals(null, gf.readFiles()); }
     * 
     * 
     * @Test public void testWriteGists() { GistLocalFileManager gf = new
     * GistLocalFileManager(); List<Gist> gists = new ArrayList<Gist>(); for
     * (int i = 0; i < 5; i++){ Gist g = new Gist();
     * 
     * g.setId(String.valueOf(i)); gists.add(g);
     * 
     * gf.writeFiles(gists); gf.writeGists(gists); }
     */

}
