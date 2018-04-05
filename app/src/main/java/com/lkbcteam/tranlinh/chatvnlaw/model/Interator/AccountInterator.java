package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.APIService;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.LoginResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SignupResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class AccountInterator {
    private FirebaseAuth firebaseAuth;
    private AccountListener.Login loginCallback;
    private AccountListener.Signup signupCallback;

    private APIService apiService;

    public AccountInterator(){
        apiService = ApiUtils.getService();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }
    public boolean checkLogin(){
        if(firebaseAuth.getCurrentUser() == null)
            return false;
        return true;
    }

    public void loginWithRail(String email, String password){
        apiService.login(email,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    String userToken = response.body().getUserToken();
                    if(userToken != null){
                        loginCallback.onRailLoginSuccess(userToken);

                    }else{
                        loginCallback.onRailLoginFalure();
                    }
                }else{
                    loginCallback.onRailLoginFalure();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void loginWithFirebaseAuth(final String email, final String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loginCallback.onFirebaseLoginSuccess(email,password);
                }else{
                    loginCallback.onFirebaseLoginFalure();
                }
            }
        });
    }

    public void signupWithRail(String email, String password, String passConfirm, String displayName){
        FirebaseUser currentUser = this.getCurrentUser();
        if(currentUser != null){
            final String username = getUsername(displayName);
            apiService.signup(currentUser.getUid(),email,password,passConfirm,displayName, username).enqueue(new Callback<SignupResponse>() {
                @Override
                public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                    if(response.isSuccessful()){
                        String userToken = response.body().getUser().getAuthenticationToken();
                        if(userToken != null){
                            signupCallback.onRailSignupSuccess(userToken);
                        }else{
                            signupCallback.onRailSignupFalure();
                        }
                    }else{
                        signupCallback.onRailSignupFalure();
                    }
                }

                @Override
                public void onFailure(Call<SignupResponse> call, Throwable t) {
                    t.printStackTrace();
                    signupCallback.onRailSignupFalure();
                }
            });
        }
    }

    public void signupWithFirebaseAuth(final String email, final String password, final String passwordConfirm, final String displayName){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    signupCallback.onFirebaseSignupSuccess(email, password, passwordConfirm, displayName);
                }else{
                    signupCallback.onFirebaseSignupFalure();
                }
            }
        });
    }

    public void setLoginCallback(AccountListener.Login loginCallback) {
        this.loginCallback = loginCallback;
    }

    public void setSignupCallback(AccountListener.Signup signupCallback) {
        this.signupCallback = signupCallback;
    }
    private String getUsername(String displayName){
        String result = displayName.toLowerCase();

        result = result.replace("/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g", "a");
        result = result.replace("/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g", "e");
        result = result.replace("/ì|í|ị|ỉ|ĩ/g", "i");
        result = result.replace("/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g", "o");
        result = result.replace("/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g", "u");
        result = result.replace("/ỳ|ý|ỵ|ỷ|ỹ/g", "y");
        result = result.replace("/đ/g", "d");
        result = result.replace("/\\W+/g", " ");
        result = result.replace("/\\s/g", ".");
        result += "." + String.valueOf(System.currentTimeMillis());
        Log.e("123", "getUsername: " +result );
        return result;
    }

    /**
     * Created by tranlinh on 26/03/2018.
     */

    public static interface AccountListener {
        interface Login{
            void onRailLoginSuccess(String userToken);
            void onRailLoginFalure();
            void onFirebaseLoginSuccess(String email, String password);
            void onFirebaseLoginFalure();
        }

        interface Signup{
            void onRailSignupSuccess(String userToken);
            void onRailSignupFalure();
            void onFirebaseSignupSuccess(String email, String password, String passwordConfirm, String displayName);
            void onFirebaseSignupFalure();
        }
    }
}
