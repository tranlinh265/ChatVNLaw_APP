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

    public class Room implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("lawyer")
        @Expose
        private User lawyer;
        @SerializedName("user")
        @Expose
        private User user;

        private boolean currentUserIsLawyer = false;

        public Integer getId() {
            return id;
        }

        public void setDefaultAvatar(){
            lawyer.getProfile().getAvatar().setDefaultAvatar();
            user.getProfile().getAvatar().setDefaultAvatar();
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


        public boolean isCurrentUserIsLawyer() {
            return currentUserIsLawyer;
        }

        public void setCurrentUserIsLawyer(boolean currentUserIsLawyer) {
            this.currentUserIsLawyer = currentUserIsLawyer;
        }
    }

    public class Thumb {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class User {

        @SerializedName("user_id")
        @Expose
        private String userId;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public void setDefaultAvatar(){
            if(url.equals("/default-ava/")){
                url = Define.DEFAULT_AVATAR;
                thumb.setUrl(Define.DEFAULT_AVATAR);
                thumbMedium.setUrl(Define.DEFAULT_AVATAR);
                thumbSmall.setUrl(Define.DEFAULT_AVATAR);
            }
        }
        public String getUrl() {
            return url;
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
