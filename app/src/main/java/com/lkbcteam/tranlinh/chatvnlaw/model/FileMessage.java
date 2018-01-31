package com.lkbcteam.tranlinh.chatvnlaw.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by tranlinh on 31/01/2018.
 */
@IgnoreExtraProperties
public class FileMessage {
    private String contentType;
    private String downloadURL;
    private String msgTimeStamp;
    private String name;
    private String senderUid;

    public FileMessage(){

    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getMsgTimeStamp() {
        return msgTimeStamp;
    }

    public void setMsgTimeStamp(String msgTimeStamp) {
        this.msgTimeStamp = msgTimeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }
}
