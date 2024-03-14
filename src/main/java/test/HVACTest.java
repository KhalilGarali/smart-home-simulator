package main.java.test;

import main.java.model.fixtures.HVAC;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HVACTest {
    private HVAC hvac;

    @Before
    public void setUp() {
        hvac = new HVAC();
    }

    @Test
    public void testHeating() {
        assertFalse(hvac.isHeating());
        hvac.setHeating(true);
        assertTrue(hvac.isHeating());
        hvac.setHeating(false);
        assertFalse(hvac.isHeating());
    }

    @Test
    public void testCooling() {
        assertFalse(hvac.isCooling());
        hvac.setCooling(true);
        assertTrue(hvac.isCooling());
        hvac.setCooling(false);
        assertFalse(hvac.isCooling());
    }
}
