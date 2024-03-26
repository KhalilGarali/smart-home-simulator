package main.java.logic.dashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateTime {
    private static LocalDateTime dateTime;
    private static int counter = 0;

    static {
        generateRandomDateTime();
    }

    private static void generateRandomDateTime() {
        Random random = new Random();
        int year = random.nextInt(2100 - 1970) + 1970; // Random year between 1970 and 2099
        int month = random.nextInt(12) + 1; // Random month between 1 and 12
        int day = random.nextInt(31) + 1; // Random day between 1 and 31 (ignoring months with fewer days)
        int hour = random.nextInt(24); // Random hour between 0 and 23
        int minute = random.nextInt(60); // Random minute between 0 and 59
        int second = random.nextInt(60); // Random second between 0 and 59
        dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public static void incrementTime(int hours, int minutes, int seconds) {
        dateTime = dateTime.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    public static void incrementSecond() {
        dateTime = dateTime.plusSeconds(1);
        counter++;
        // notifyObervers;
    }

    public static void setDate(int year, int month, int day) {
        dateTime = dateTime.withYear(year).withMonth(month).withDayOfMonth(day);
    }

    public static void setTime(int hour, int minute, int second) {
        dateTime = dateTime.withHour(hour).withMinute(minute).withSecond(second);
    }

    public static LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public static LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public static String toStringDateTime() {
        return dateTime.toString();
    }

    public static int getMonth() {
        return dateTime.getMonthValue();
    }

    public static void setDateTime(LocalDateTime newDateTime) {
        dateTime = newDateTime;
    }

    public static int getSeconds() {
        return counter;
    }

    public static int getCounter(){
        return counter;
    }
}
