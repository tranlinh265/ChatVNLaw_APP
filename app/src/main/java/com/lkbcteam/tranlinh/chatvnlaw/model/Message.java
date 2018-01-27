package com.lkbcteam.tranlinh.chatvnlaw.model;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class Message {
    private String mSenderDisplayName;
    private String mMessageContent;
    private String mMessageTime;

    public Message(String senderDisplayName, String messageContent, String messageTime){
        this.mSenderDisplayName = senderDisplayName;
        this.mMessageContent = messageContent;
        this.mMessageTime = messageTime;
    }

    public String getsenderDisplayName() {
        return mSenderDisplayName;
    }

    public void setsenderDisplayName(String mSenderDisplayName) {
        this.mSenderDisplayName = mSenderDisplayName;
    }

    public String getmessageContent() {
        return mMessageContent;
    }

    public void setmessageContent(String mMessageContent) {
        this.mMessageContent = mMessageContent;
    }

    public String getmessageTime() {
        return mMessageTime;
    }

    public void setmessageTime(String mMessageTime) {
        this.mMessageTime = mMessageTime;
    }
}
