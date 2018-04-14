package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tranlinh on 14/04/2018.
 */

public class UserInfoResponse {

    @SerializedName("user_info")
    @Expose
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public class UserInfo {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("profile")
        @Expose
        private Profile profile;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("mn_acc")
        @Expose
        private Object mnAcc;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getMnAcc() {
            return mnAcc;
        }

        public void setMnAcc(Object mnAcc) {
            this.mnAcc = mnAcc;
        }

    }

    public class Profile {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("avatar")
        @Expose
        private RoomListResponse.Avatar avatar;
        @SerializedName("birthday")
        @Expose
        private Object birthday;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public RoomListResponse.Avatar getAvatar() {
            return avatar;
        }

        public void setAvatar(RoomListResponse.Avatar avatar) {
            this.avatar = avatar;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
