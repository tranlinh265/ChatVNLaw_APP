package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranlinh on 14/04/2018.
 */

public class RoomFileListResponse {
    @SerializedName("list_files")
    @Expose
    private List<File> files = null;

    @SerializedName("list_files_names")
    @Expose
    private List<String> listFilesNames = null;

    public List<File> getFiles() {
        return files;
    }


    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<String> getListFilesNames() {
        return listFilesNames;
    }

    public void setListFilesNames(List<String> listFilesNames) {
        this.listFilesNames = listFilesNames;
    }

    public class File {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("room_id")
        @Expose
        private Integer roomId;
        @SerializedName("content_type_id")
        @Expose
        private Integer contentTypeId;
        @SerializedName("file")
        @Expose
        private RoomListResponse.Avatar file;
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

        public Integer getRoomId() {
            return roomId;
        }

        public void setRoomId(Integer roomId) {
            this.roomId = roomId;
        }

        public Integer getContentTypeId() {
            return contentTypeId;
        }

        public void setContentTypeId(Integer contentTypeId) {
            this.contentTypeId = contentTypeId;
        }

        public RoomListResponse.Avatar getFile() {
            return file;
        }

        public void setFile(RoomListResponse.Avatar file) {
            this.file = file;
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
