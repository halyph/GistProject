package com.oshmidt;

import static org.junit.Assert.*;
import org.testng.annotations.Test;

//import org.junit.Test;

public class UserTest {

	@Test
	public void testUserLogin() {
		User user = new User();
		user.setLogin("UserLogin");
		assertTrue(user.getLogin().equalsIgnoreCase("UserLogin"));
	}
	
	@Test
	public void testUserPassword() {
		User user = new User();
		user.setPassword("pAsSwOrD");
		assertEquals("pAsSwOrD", user.getPassword());
	}
	

}
