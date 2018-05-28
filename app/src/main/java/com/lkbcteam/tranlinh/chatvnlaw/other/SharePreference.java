package com.lkbcteam.tranlinh.chatvnlaw.other;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

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
    private static final String ROLE = APP_INFO + "ROLE";
    private static final String EMAIL = APP_INFO + "EMAIL";
    private static final String LAWYER_ID = APP_INFO + "LAWYER_ID";

    public SharePreference(Activity activity){
        this.sp = activity.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static SharePreference getInstance(Activity activity){
        if(sharePreference == null){
            sharePreference = new SharePreference(activity);
        }
        return sharePreference;
    }

    public void resetShareReferenceData(){
        setUsername(null);
        setEmail(null);
        setRole(null);
        setUserToken(null);
        setUserId(null);
    }
    private void pushString(String key, String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }
    private void pushInteger(String key, Integer value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key,value);
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

    public void setRole(String role){
        pushString(ROLE, role);
    }
    public String getRole(){
        return sp.getString(ROLE, "");
    }
    public void setEmail(String email){
        pushString(EMAIL, email);
    }
    public String getEmail(){
        return sp.getString(EMAIL, "");
    }
    public Integer getLawyerId(){
        return sp.getInt(LAWYER_ID, 0);
    }
    public void setLawyerId(Integer lawyerId){
        pushInteger(LAWYER_ID, lawyerId);
    }
}
