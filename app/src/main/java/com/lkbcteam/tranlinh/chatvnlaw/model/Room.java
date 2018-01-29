package com.lkbcteam.tranlinh.chatvnlaw.model;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class Room {
    private String rid;
    private User sender, receiver;
    public Room(){

    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
