package com.lkbcteam.tranlinh.chatvnlaw.model.entity;


import java.util.Calendar;

import android.text.format.DateFormat;

import java.util.Locale;

/**
 * Created by tranlinh on 30/01/2018.
 */

public class TimeStamp {
    public static String convertTimeStamp(String input){
        Calendar cal = null;
        cal = Calendar.getInstance(Locale.CHINESE);

        cal.setTimeInMillis(Long.parseLong(input));

        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
        return date;
    }

    public static Time getTimeFromMiliSecond(String input){
        try{
            String date = convertTimeStamp(input);
            Time time = new Time();
            String left = date.split(" ")[0];
            String right = date.split(" ")[1];
            time.setDay(left.split("-")[0]);
            time.setMonth(left.split("-")[1]);
            time.setYear(left.split("-")[2]);
            time.setHour(right.split(":")[0]);
            time.setMinute(right.split(":")[1]);
            time.setSecond(right.split(":")[2]);
            return time;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static String getCurrentTime(){
        Calendar cal = null;
        cal = Calendar.getInstance(Locale.CHINESE);

//        cal.getTimeInMillis();
        return String.valueOf(cal.getTimeInMillis());
    }
}
