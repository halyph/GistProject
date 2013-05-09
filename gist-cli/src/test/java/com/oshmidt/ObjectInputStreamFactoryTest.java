package com.oshmidt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.mockito.Mock;
import org.testng.annotations.Test;


public class ObjectInputStreamFactoryTest {
	
	public ObjectInputStreamFactoryTest() {
		
	}
	
/*	@Test(expectedExceptions = IOException.class)
	public void testObjectInputStreamFactory() throws IOException {
		ObjectInputStreamFactory factory = new ObjectInputStreamFactory();
		File file = mock(File.class);
		assertNotNull(factory.objectInputStreamFactory(null));
	}*/
	
	
	/*@Test
	public void testReadObject() throws IOException, ClassNotFoundException {
		ObjectInputStreamFactory factory = new ObjectInputStreamFactory();
		ObjectInputStream ois = mock(ObjectInputStream.class);
		factory.readObject(null);
	}
*/
}
