package com.lkbcteam.tranlinh.chatvnlaw.model;


import java.util.Calendar;
import android.os.Build;
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
    public static String getCurrentTime(){
        Calendar cal = null;
        cal = Calendar.getInstance(Locale.CHINESE);

//        cal.getTimeInMillis();
        return String.valueOf(cal.getTimeInMillis());
    }
}
