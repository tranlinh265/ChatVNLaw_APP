package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tranlinh on 23/04/2018.
 */

public class CreateTaskResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("task")
    @Expose
    private TaskResponse.Task task;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TaskResponse.Task getTask() {
        return task;
    }

    public void setTask(TaskResponse.Task task) {
        this.task = task;
    }

}
