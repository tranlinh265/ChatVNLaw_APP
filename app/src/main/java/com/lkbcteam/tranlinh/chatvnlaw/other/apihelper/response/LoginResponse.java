package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class LoginResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userToken")
    @Expose
    private String userToken;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("role")
    @Expose
    private String role;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
