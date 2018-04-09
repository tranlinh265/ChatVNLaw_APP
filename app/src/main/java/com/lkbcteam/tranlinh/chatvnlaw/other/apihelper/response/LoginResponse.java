package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class LoginResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @Nullable
    @SerializedName("user_token")
    @Expose
    private String userToken;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
