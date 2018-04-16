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

    private static final String APP_INFO = "com.lkbcteam.tranlinh.chatvnlaw.";
    private static final String SHARED_PREFERENCE = APP_INFO + "SHARED_PREFERENCE";
    private static final String USERNAME = APP_INFO + "USERNAME";
    private static final String USER_TOKEN  = APP_INFO + "USER_TOKEN";
    private static final String USER_ID = APP_INFO + "USER_ID";

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

    public void setUserId(String userId){
        pushString(USER_ID, userId);
    }
    public String getUserId(){
        return sp.getString(USER_ID, "");
    }
    public String getUsername(){
        return sp.getString(USERNAME, "");
    }
    public void setUserToken(String userToken){
        pushString(USER_TOKEN, userToken);
    }

    public String getUserToken(){
        return sp.getString(USER_TOKEN, "");
    }
}
