package main.java.test;

import main.java.model.lighting.Light;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LightTest {
    private Light light;

    @Before
    public void setUp() {
        light = new Light();
    }

    @Test
    public void testTurnON() {
        light.turnON();
        assertTrue(light.isON());
    }

    @Test
    public void testTurnOFF() {
        light.turnOFF();
        assertFalse(light.isON());
    }

    @Test
    public void testIsON() {
        assertFalse(light.isON());
        light.turnON();
        assertTrue(light.isON());
    }
}
