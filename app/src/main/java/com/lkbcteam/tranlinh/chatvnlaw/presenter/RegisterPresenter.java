package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.google.firebase.auth.FirebaseUser;
import com.lkbcteam.tranlinh.chatvnlaw.activity.BaseActivity;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.AccountInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.notification.DeviceToken;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class RegisterPresenter implements AccountInterator.AccountListener.Signup {

    private RegisterView registerView;
    private AccountInterator accountInterator;
    private BaseActivity baseActivity;

    public  RegisterPresenter (RegisterView registerView){
        this.registerView = registerView;
        this.accountInterator = new AccountInterator();
        accountInterator.setSignupCallback(this);
    }

    public void signupWithFirebase(String email, String password, String passwordConfirm, String displayName){
        if( !email.isEmpty() && !password.isEmpty() && !passwordConfirm.isEmpty() && !displayName.isEmpty()){
            if(password.equals(passwordConfirm)){
                accountInterator.signupWithFirebaseAuth(email,password,passwordConfirm,displayName);
            }else{
                registerView.registerFalure("pass != pass cofirm");
            }
        }else{
            registerView.registerFalure("empty field");
        }
    }

    public FirebaseUser getCurrentUser(){
        return accountInterator.getCurrentUser();
    }

    public void initCloudMessaging(){
        FirebaseUser currentUser = this.getCurrentUser();
        DeviceToken.initFirebaseCloudMessaging(currentUser);
    }

    public void storeCurrentUser(String userToken){
        FirebaseUser currentUser = this.getCurrentUser();
        SharePreference.getInstance(baseActivity).setUsername(currentUser.getUid());
        SharePreference.getInstance(baseActivity).setUserToken(userToken);
    }

    @Override
    public void onRailSignupSuccess(String userToken) {
        this.initCloudMessaging();
        this.storeCurrentUser(userToken);
        registerView.registerSuccess();
    }

    @Override
    public void onRailSignupFalure() {
        registerView.registerFalure("error with rail");
    }

    @Override
    public void onFirebaseSignupSuccess(String email, String password, String passwordConfirm, String displayName) {
        accountInterator.signupWithRail(email, password, passwordConfirm, displayName);
    }


    @Override
    public void onFirebaseSignupFalure() {
        registerView.registerFalure("error with firebase");
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    /**
     * Created by tranlinh on 26/03/2018.
     */

    public static interface RegisterView {
        void registerSuccess();
        void registerFalure(String error);
    }
}
