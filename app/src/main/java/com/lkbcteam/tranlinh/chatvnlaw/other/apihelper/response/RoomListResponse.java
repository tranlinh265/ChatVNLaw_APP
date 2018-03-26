package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class RoomListResponse {
    @Nullable
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public class Room {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("lawyer_id")
        @Expose
        private Integer lawyerId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("description")
        @Expose
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getLawyerId() {
            return lawyerId;
        }

        public void setLawyerId(Integer lawyerId) {
            this.lawyerId = lawyerId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}
