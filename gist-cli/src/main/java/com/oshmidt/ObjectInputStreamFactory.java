package com.oshmidt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.egit.github.core.Gist;

public class ObjectInputStreamFactory {
	
	public ObjectInputStreamFactory() {
		
	}
	
    public ObjectInputStream objectInputStreamFactory(File gst)
            throws IOException {
        FileInputStream fis = new FileInputStream(gst);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois;
    }
    
    public Object readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException{
    	return ois.readObject();
    }


}
