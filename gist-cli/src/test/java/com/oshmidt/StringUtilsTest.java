package com.oshmidt;

import static org.junit.Assert.assertTrue;
import java.lang.reflect.Constructor;
import org.testng.annotations.Test;

/**
 * @author oshmidt
 *Tests for StringUtils.class
 */
public class StringUtilsTest {

    @Test
    public void testConvertToString() {
        String[] arr = {"a", "b", "c", "d"};
        assertTrue(StringUtils.convertToString(arr, " ").equals("a b c d "));
    }

    @Test
    public void testIsPrivateConstructor() throws Exception {
        Constructor<?>[] cons = StringUtils.class.getDeclaredConstructors();
        cons[0].setAccessible(true);
        cons[0].newInstance((Object[]) null);
    }
}
