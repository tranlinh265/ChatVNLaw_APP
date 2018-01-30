package com.lkbcteam.tranlinh.chatvnlaw.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by tranlinh on 30/01/2018.
 */

@IgnoreExtraProperties
public class TextMessage {
    private String content;
    private String msgTimeStamp;
    private String senderUid;

    public TextMessage(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgTimeStamp() {
        return msgTimeStamp;
    }

    public void setMsgTimeStamp(String msgTimeStamp) {
        this.msgTimeStamp = msgTimeStamp;
    }
    public void setMsgTimeStamp(){
        if(!this.msgTimeStamp.isEmpty()){
            this.msgTimeStamp = (new TimeStamp()).convertTimeStamp(this.msgTimeStamp);
        }
    }
    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }
}
