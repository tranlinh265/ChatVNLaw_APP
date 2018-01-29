package com.lkbcteam.tranlinh.chatvnlaw.model;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class Room {
    private String rid;
    private User currentUser, targetUser;
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
}
