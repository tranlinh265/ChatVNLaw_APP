package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class LoginResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userToken")
    @Expose
    private String userToken;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("avatar")
    @Expose
    private RoomListResponse.Avatar avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RoomListResponse.Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(RoomListResponse.Avatar avatar) {
        this.avatar = avatar;
    }
}
