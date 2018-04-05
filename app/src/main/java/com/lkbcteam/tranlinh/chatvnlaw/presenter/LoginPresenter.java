package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseUser;
import com.lkbcteam.tranlinh.chatvnlaw.activity.BaseActivity;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.AccountInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.notification.DeviceToken;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class LoginPresenter implements AccountInterator.AccountListener.Login {
    private LoginView loginView;
    private AccountInterator accountInterator;
    private BaseActivity baseActivity;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
        accountInterator = new AccountInterator();
        accountInterator.setLoginCallback(this);
    }

    public void loginWithFirebaseAuth(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            loginView.loginFalure("null email or pass");
            return;
        }
        accountInterator.loginWithFirebaseAuth(email, password);
    }

    public FirebaseUser getCurrentUser(){
        return accountInterator.getCurrentUser();
    }

    public boolean servicesOK(Context context){
        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);

        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }
        return false;
    }

    public boolean checkLogin(){
        return accountInterator.checkLogin();
    }

    public void initCloudMessaging(){
        FirebaseUser currentUser = this.getCurrentUser();
        DeviceToken.initFirebaseCloudMessaging(currentUser);
    }

    public void storeCurrentUser(){
        FirebaseUser currentUser = this.getCurrentUser();
        SharePreference.getInstance(baseActivity).setUsername(currentUser.getUid());
    }

    public void storeCurrentUser(String userToken){
        FirebaseUser currentUser = this.getCurrentUser();
        SharePreference.getInstance(baseActivity).setUsername(currentUser.getUid());
        SharePreference.getInstance(baseActivity).setUserToken(userToken);
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void onRailLoginSuccess(String userToken) {
        this.initCloudMessaging();
        this.storeCurrentUser(userToken);
        loginView.loginSucess();
    }

    @Override
    public void onRailLoginFalure() {
        loginView.loginFalure("Authentication rails failed");
    }

    @Override
    public void onFirebaseLoginSuccess(String email, String password) {
//        accountInterator.loginWithRail(email,password);
        loginView.loginSucess();
    }

    @Override
    public void onFirebaseLoginFalure() {
        loginView.loginFalure("Authentication firebase failed");
    }

    /**
     * Created by tranlinh on 26/03/2018.
     */

    public static interface LoginView {
        void loginSucess();
        void loginFalure(String error);
    }
}
