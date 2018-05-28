package com.lkbcteam.tranlinh.chatvnlaw.model.entity;

/**
 * Created by tranlinh on 05/03/2018.
 */

public class Time {
    private String day,month,year, hour,minute,second;

    public Time(){

    }
    public Time(String day,String month, String year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
