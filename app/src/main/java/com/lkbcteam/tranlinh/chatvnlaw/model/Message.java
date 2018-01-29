package com.lkbcteam.tranlinh.chatvnlaw.model;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class Message {
    private String mSenderDisplayName;
    private String mMessageContent;
    private String mMessageTime;
    private boolean mIsCurrentUser;
    private TextMessage mTextMessage;
    private User mTargetUser,mSenderUser;
    private String mMessageId;

    public Message(){

    }
    public Message(String senderDisplayName, String messageContent, String messageTime){
        this.mSenderDisplayName = senderDisplayName;
        this.mMessageContent = messageContent;
        this.mMessageTime = messageTime;
        this.mIsCurrentUser = false;
    }

    public Message(String senderDisplayName, String messageContent, String messageTime, boolean isCurrentUser){
        this.mSenderDisplayName = senderDisplayName;
        this.mMessageContent = messageContent;
        this.mMessageTime = messageTime;
        this.mIsCurrentUser = isCurrentUser;
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

    public boolean getIsCurrentUser() {
        return mIsCurrentUser;
    }

    public void setIsCurrentUser(boolean mIsCurrentUser) {
        this.mIsCurrentUser = mIsCurrentUser;
    }


    public void setmTextMessage(TextMessage mTextMessage) {
        this.mTextMessage = mTextMessage;
    }

    public void setmTargetUser(User mTargetUser) {
        this.mTargetUser = mTargetUser;
    }

    public User getmSenderUser() {
        return mSenderUser;
    }

    public void setmSenderUser(User mSenderUser) {
        this.mSenderUser = mSenderUser;
    }

    public TextMessage getmTextMessage() {
        return mTextMessage;
    }

    public User getmTargetUser() {
        return mTargetUser;
    }

    public String getmMessageId() {
        return mMessageId;
    }

    public void setmMessageId(String mMessageId) {
        this.mMessageId = mMessageId;
    }
}
