package main.java.logic.dashboard;

import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.fixtures.HVAC;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DateTime implements Observable{

    // attributes
    private static LocalDateTime dateTime;
    private static int counter = 0;
    private static DateTime instance;
    private int totalSecondsElapsed = 0;
    private int multiplier = 1;

    private ArrayList<Observer> observers = new ArrayList<>();

    static {
        generateRandomDateTime();
    }

    // private constructor for the Singleton pattern
    private DateTime(){}

    public static DateTime getInstance(){
        if(instance == null){
            instance = new DateTime();
        }
        return instance;
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

    // method to  increment time
    public void incrementTime(int hours, int minutes, int seconds) {
        dateTime = dateTime.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
        // totalHoursIncremented += dateTime.getMinute();
        totalSecondsElapsed++;
            this.notifyObservers();
    }

    // method to get total time elapsed
    public int getTotalSecondsElapsed() {
        return totalSecondsElapsed;
    }

    // method to increment time by seconds
    public void incrementSecond() {
        dateTime = dateTime.plusSeconds(1*multiplier);
    }

    // method to set date
    public static void setDate(int year, int month, int day) {
        dateTime = dateTime.withYear(year).withMonth(month).withDayOfMonth(day);
    }

    // method to set time
    public static void setTime(int hour, int minute, int second) {
        dateTime = dateTime.withHour(hour).withMinute(minute).withSecond(second);
    }

    // method to get date
    public static LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    // method to get time
    public static LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public static String toStringDateTime() {
        return dateTime.toString();
    }

    // method to get month
    public static int getMonth() {
        return dateTime.getMonthValue();
    }

    // method to get hour
    public static int getHour() {
        return dateTime.getHour();
    }

    // method to get minute
    public static int getMinute() {
        return dateTime.getMinute();
    }

    // method to get seconds
    public static int getSecond() {
        return dateTime.getSecond();
    }

    // method to set date and time
    public static void setDateTime(LocalDateTime newDateTime) {
        dateTime = newDateTime;
    }

    // method to get seconds
    public static int getSeconds() {
        return counter;
    }

    // return counter
    public static int getCounter(){
        return counter;
    }

    // implementing notifyObservers method 
    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(this);
        }
    }

    // implementing addObserver method
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    // implementing removeObserver method
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // method to set time multiplier
    public void setMultiplier(int multiplier2) {
        this.multiplier = multiplier2;
    }

    // method to get multiplier
    public int getMultiplier() {
        return multiplier;
    }
}