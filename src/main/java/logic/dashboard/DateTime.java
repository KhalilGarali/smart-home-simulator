package main.java.logic.dashboard;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTime {
    private LocalDateTime dateTime;

    public DateTime() {
        // Initialize with current local date and time
        this.dateTime = LocalDateTime.now();
    }

    public DateTime(int year, int month, int day, int hour, int minute) {
        // Initialize with provided date and time
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
    }

    public void setDateTime(int year, int month, int day, int hour, int minute) {
        // Set the date and time
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
    }

    public void tick() {
        // Increment the time by one second
        this.dateTime = this.dateTime.plusMinutes(1);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public Date toDate() {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return this.dateTime.toString();
    }
}
