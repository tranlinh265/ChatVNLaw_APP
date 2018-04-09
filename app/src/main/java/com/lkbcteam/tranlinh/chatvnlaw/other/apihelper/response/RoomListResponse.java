package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class RoomListResponse {
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public class Lawyer {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("profile")
        @Expose
        private Profile profile;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

    }

    public class Profile {

        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("avatar")
        @Expose
        private String avatar;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    }

    public class Room {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("lawyer")
        @Expose
        private Lawyer lawyer;
        @SerializedName("user")
        @Expose
        private User user;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Lawyer getLawyer() {
            return lawyer;
        }

        public void setLawyer(Lawyer lawyer) {
            this.lawyer = lawyer;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

    public class User {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("profile")
        @Expose
        private Profile profile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

    }

}
