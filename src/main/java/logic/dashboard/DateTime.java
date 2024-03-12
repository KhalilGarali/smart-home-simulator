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

    public void setDate(int year, int month, int day) {
        // Set the date
        this.dateTime = this.dateTime.withYear(year)
                                     .withMonth(month)
                                     .withDayOfMonth(day);
    }

    public void setTime(int hour, int minute) {
        // Set the time
        this.dateTime = this.dateTime.withHour(hour)
                                     .withMinute(minute);
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
    
    // New methods to get date and time separately
    public LocalDateTime getDateOnly() {
        return this.dateTime.toLocalDate().atStartOfDay();
    }
    
    public LocalDateTime getTimeOnly() {
        return this.dateTime.toLocalTime().atDate(this.dateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return this.dateTime.toString();
    }
}
