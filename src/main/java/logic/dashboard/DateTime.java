package main.java.logic.dashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateTime {
    private LocalDateTime dateTime;

    public DateTime() {
        generateRandomDateTime();
    }

    public void generateRandomDateTime() {
        Random random = new Random();
        int year = random.nextInt(2100 - 1970) + 1970; // Random year between 1970 and 2099
        int month = random.nextInt(12) + 1; // Random month between 1 and 12
        int day = random.nextInt(31) + 1; // Random day between 1 and 31 (ignoring months with fewer days)
        int hour = random.nextInt(24); // Random hour between 0 and 23
        int minute = random.nextInt(60); // Random minute between 0 and 59
        int second = random.nextInt(60); // Random second between 0 and 59
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public void incrementTime(int hours, int minutes, int seconds) {
        this.dateTime = this.dateTime.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    public void incrementSecond() {
        this.dateTime = this.dateTime.plusSeconds(1);
    }    
    
    public void setDate(int year, int month, int day) {
        this.dateTime = this.dateTime.withYear(year).withMonth(month).withDayOfMonth(day);
    }

    public void setTime(int hour, int minute, int second) {
        this.dateTime = this.dateTime.withHour(hour).withMinute(minute).withSecond(second);
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return this.dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return this.dateTime.toString();
    }
}
