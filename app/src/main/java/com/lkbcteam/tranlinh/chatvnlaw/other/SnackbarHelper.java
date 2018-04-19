package com.lkbcteam.tranlinh.chatvnlaw.other;

import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by tranlinh on 19/04/2018.
 */

public class SnackbarHelper {
    public static void showShortSnackBar(View viewParrent, String text){
        showSnackBar(viewParrent,text,Snackbar.LENGTH_SHORT);
    }

    public static void showLongSnackBar(View viewParrent, String text) {
        showSnackBar(viewParrent,text,Snackbar.LENGTH_LONG);
    }

    public static void showInfiniteSnackBar(View view, String text){
        showSnackBar(view, text,Snackbar.LENGTH_INDEFINITE);
    }

    public static void showSnackBar(View viewParrent,String text, int duration){
        Snackbar.make(viewParrent,text, duration).show();
    }
}
