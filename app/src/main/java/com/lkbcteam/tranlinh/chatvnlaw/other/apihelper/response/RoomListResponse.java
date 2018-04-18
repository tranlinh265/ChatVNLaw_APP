package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import java.io.Serializable;
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

    public class Profile {

        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("avatar")
        @Expose
        private Avatar avatar;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Avatar getAvatar() {
            return avatar;
        }

        public void setAvatar(Avatar avatar) {
            this.avatar = avatar;
        }

    }

    public class Room {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("lawyer")
        @Expose
        private User lawyer;
        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("description")
        @Expose
        private String description;

        public boolean currentUserIsLawyer = false;

        public void setCurrentUserIsLawyer(String uid){
            this.currentUserIsLawyer = uid.equals(lawyer.getUid());
        }

        public boolean isCurrentUserIsLawyer(){
            return this.currentUserIsLawyer;
        }
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public User getLawyer() {
            return lawyer;
        }

        public void setLawyer(User lawyer) {
            this.lawyer = lawyer;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    public class Thumb {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return Define.RailServer.BASE_API_URL + url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("avatar")
        @Expose
        private Avatar avatar;
        @SerializedName("userName")
        @Expose
        private String userName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Avatar getAvatar() {
            return avatar;
        }

        public void setAvatar(Avatar avatar) {
            this.avatar = avatar;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

    }
    public class Avatar {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("thumb")
        @Expose
        private Thumb thumb;
        @SerializedName("thumb_medium")
        @Expose
        private Thumb thumbMedium;
        @SerializedName("thumb_small")
        @Expose
        private Thumb thumbSmall;

        public String getUrl() {
            return Define.RailServer.BASE_API_URL + url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Thumb getThumb() {
            return thumb;
        }

        public void setThumb(Thumb thumb) {
            this.thumb = thumb;
        }

        public Thumb getThumbMedium() {
            return thumbMedium;
        }

        public void setThumbMedium(Thumb thumbMedium) {
            this.thumbMedium = thumbMedium;
        }

        public Thumb getThumbSmall() {
            return thumbSmall;
        }

        public void setThumbSmall(Thumb thumbSmall) {
            this.thumbSmall = thumbSmall;
        }

    }
}
