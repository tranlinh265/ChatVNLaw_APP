package com.lkbcteam.tranlinh.chatvnlaw.model;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class Message {
    private boolean mIsCurrentUser;
    private User mTargetUser,mSenderUser;
    private String mMessageId;
    private Info mMessageInfo;

    public Message(){

    }

    public static class Info{
        private String content;
        private String contentType;
        private String downloadURL;
        private String msgTimeStamp;
        private String name;
        private String senderUid;
        private int height, width;
        public Info(){

        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
        public void setMsgTimeStamp(){
            if(!this.msgTimeStamp.isEmpty()){
                this.msgTimeStamp = TimeStamp.convertTimeStamp(this.msgTimeStamp);
            }
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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public boolean getIsCurrentUser() {
        return mIsCurrentUser;
    }

    public void setIsCurrentUser(boolean mIsCurrentUser) {
        this.mIsCurrentUser = mIsCurrentUser;
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


    public User getmTargetUser() {
        return mTargetUser;
    }

    public String getmMessageId() {
        return mMessageId;
    }

    public void setmMessageId(String mMessageId) {
        this.mMessageId = mMessageId;
    }

    public Info getmMessageInfo() {
        return mMessageInfo;
    }

    public void setmMessageInfo(Info mMessageInfo) {
        this.mMessageInfo = mMessageInfo;
    }

}
