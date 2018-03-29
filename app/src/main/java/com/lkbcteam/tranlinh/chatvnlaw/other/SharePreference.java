package com.lkbcteam.tranlinh.chatvnlaw.other;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.lkbcteam.tranlinh.chatvnlaw.activity.BaseActivity;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class SharePreference {
    public static SharePreference sharePreference;
    public SharedPreferences sp;

    private static final String SHARED_PREFERENCE = "com.lkbcteam.tranlinh.chatvnlaw.SHARED_PREFERENCE";
    private static final String USERNAME = "com.lkbcteam.tranlinh.chatvnlaw.USERNAME";
    private static final String USER_TOKEN  = "com.lkbcteam.tranlinh.chatvnlaw.USER_TOKEN";

    public SharePreference(Activity activity){
        this.sp = activity.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static SharePreference getInstance(Activity activity){
        if(sharePreference == null){
            sharePreference = new SharePreference(activity);
        }
        return sharePreference;
    }

    private void pushString(String key, String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public void setUsername(String username){
        pushString(USERNAME, username);
    }

    public void setUserToken(String userToken){
        pushString(USER_TOKEN, userToken);
    }

    public String getUserToken(){
        return sp.getString(USER_TOKEN, "");
    }
}
