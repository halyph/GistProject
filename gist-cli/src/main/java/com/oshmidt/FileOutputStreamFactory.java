package com.oshmidt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileOutputStreamFactory {
	
	public FileOutputStreamFactory() {
		
	}
	
	public FileOutputStream fileOutputStreamFactory(String s) throws FileNotFoundException{
		return new FileOutputStream(s);
	}
	
	public FileOutputStream fileOutputStreamFactory(File f) throws FileNotFoundException{
		return new FileOutputStream(f);
	}
	

}
