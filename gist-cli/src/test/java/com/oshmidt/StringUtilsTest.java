package com.oshmidt;

import static org.junit.Assert.*;
import org.testng.annotations.Test;


public class StringUtilsTest {

	@Test
	public void testConvertToString() {
		String [] arr = {"a","b","c","d"};
		assertTrue(StringUtils.convertToString(arr, " ").equals("a b c d "));
	}
	

}
