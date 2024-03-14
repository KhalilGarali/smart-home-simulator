package main.java.test;

import main.java.logic.dashboard.DateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeTest {

    @Test
    void testDefaultConstructor() {
        DateTime dateTime = new DateTime();
        assertNotNull(dateTime.getDateTime());
        assertTrue(dateTime.getDateTime().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testCustomConstructor() {
        DateTime dateTime = new DateTime(2021, 5, 15, 10, 30);
        assertEquals(LocalDateTime.of(2021, 5, 15, 10, 30), dateTime.getDateTime());
    }

    @Test
    void testSetDateTime() {
        DateTime dateTime = new DateTime();
        dateTime.setDateTime(2022, 1, 1, 12, 0);
        assertEquals(LocalDateTime.of(2022, 1, 1, 12, 0), dateTime.getDateTime());
    }

    @Test
    void testTick() {
        DateTime dateTime = new DateTime(2021, 5, 15, 10, 30);
        dateTime.tick();
        assertEquals(LocalDateTime.of(2021, 5, 15, 10, 31), dateTime.getDateTime());
    }

    @Test
    void testToDate() {
        DateTime dateTime = new DateTime(2021, 5, 15, 10, 30);
        Date date = dateTime.toDate();
        assertNotNull(date);
        assertEquals(Date.from(LocalDateTime.of(2021, 5, 15, 10, 30)
                .atZone(java.time.ZoneId.systemDefault())
                .toInstant()), date);
    }
}
