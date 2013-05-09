package com.oshmidt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamFactory {
	
	public ObjectOutputStreamFactory() {
		
	}
	
	public ObjectOutputStream objectOutputStreamFactory(FileOutputStream fos) throws IOException {
		return new ObjectOutputStream(fos);
	}
	
	public void writeObject(ObjectOutputStream oos, Object obj) throws IOException{
		oos.writeObject(obj);
	}

}
