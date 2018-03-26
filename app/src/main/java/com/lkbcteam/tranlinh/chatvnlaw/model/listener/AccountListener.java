package com.lkbcteam.tranlinh.chatvnlaw.model.listener;

/**
 * Created by tranlinh on 26/03/2018.
 */

public interface AccountListener {
    interface Login{
        void onRailLoginSuccess(String userToken);
        void onRailLoginFalure();
        void onFirebaseLoginSuccess(String email, String password);
        void onFirebaseLoginFalure();
    }

    interface Signup{
        void onRailSignupSuccess(String userToken);
        void onRailSignupFalure();
        void onFirebaseSignupSuccess(String email, String password,String passwordConfirm, String displayName);
        void onFirebaseSignupFalure();
    }
}
