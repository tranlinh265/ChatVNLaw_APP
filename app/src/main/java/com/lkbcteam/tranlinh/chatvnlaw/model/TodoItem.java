package com.lkbcteam.tranlinh.chatvnlaw.model;

/**
 * Created by tranlinh on 05/03/2018.
 */

public class TodoItem {
//    private long TimeCreate;
    private int status;
    private String target_uid;
    private String targetuserdisplayname;
    private String text;
    private long timecreate;
    private Time time;
    private int type = 1;

    public TodoItem(){

    }

//    public long getTimeCreate() {
//        return TimeCreate;
//    }
//
//    public void setTimeCreate(long timeCreate) {
//        TimeCreate = timeCreate;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTarget_uid() {
        return target_uid;
    }

    public void setTarget_uid(String target_uid) {
        this.target_uid = target_uid;
    }

    public String getTargetuserdisplayname() {
        return targetuserdisplayname;
    }

    public void setTargetuserdisplayname(String targetuserdisplayname) {
        this.targetuserdisplayname = targetuserdisplayname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimecreate() {
        return timecreate;
    }

    public void setTimecreate(long timecreate) {
        this.timecreate = timecreate;
        this.time = TimeStamp.getTimeFromMiliSecond(String.valueOf(timecreate));
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}


