package com.oshmidt;


import org.testng.annotations.Test;
import java.lang.reflect.Constructor;


/**
 * @author oshmidt
 *Test for App.class
 */
public class AppTest {

    @Test
    public void testIsPrivateConstructor() throws Exception {
        Constructor<?>[] cons = App.class.getDeclaredConstructors();
        cons[0].setAccessible(true);
        cons[0].newInstance((Object[]) null);
    }

    @Test
    public void testMain() throws Exception {
        App.main(new String[] {"-h"});
        App.main(new String[] {"-l", "-p"});
    }
}


