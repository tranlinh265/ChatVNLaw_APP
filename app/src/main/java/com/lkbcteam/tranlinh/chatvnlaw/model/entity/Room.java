package com.lkbcteam.tranlinh.chatvnlaw.model.entity;

import com.lkbcteam.tranlinh.chatvnlaw.model.User;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class Room {
    private String rid;
    private User currentUser, targetUser;
    private String lastMessContent;

    public Room(){

    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public String getLastMessContent() {
        return lastMessContent;
    }

    public void setLastMessContent(String lastMessContent) {
        this.lastMessContent = lastMessContent;
    }
}
