package sample.Model;



public class Clock {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int milli;


    public Clock(int year, int month, int day, int hour, int minute, int milli) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.milli = milli;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMilli() {
        return milli;
    }

    public void setMilli(int milli) {
        this.milli = milli;
    }
}
