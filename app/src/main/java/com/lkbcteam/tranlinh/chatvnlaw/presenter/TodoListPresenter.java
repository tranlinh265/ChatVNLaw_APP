package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.TodoListInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.TaskResponse;

import java.util.List;

/**
 * Created by tranlinh on 23/04/2018.
 */

public class TodoListPresenter implements TodoListInterator.onLoadData{
    private TodoListInterator interator;
    private List<TaskResponse.Task> tasks;
    private onLoadData callback;

    public TodoListPresenter(List<TaskResponse.Task> tasks){
        interator = new TodoListInterator(this);
        this.tasks = tasks;
    }

    public void loadTaskFromRail(String lawyerId, String lawyerToken, String lawyerEmail){
        interator.loadTaskFromRails(lawyerId, lawyerToken, lawyerEmail);
    }

    @Override
    public void onLoadSuccess(Object o) {
        TaskResponse taskResponse = (TaskResponse) o;
        tasks.clear();
        for (TaskResponse.Room room : taskResponse.getRooms()){
            TaskResponse.Task task = new TaskResponse.Task();
            task.setRoomId(room.getId());
            task.setTargetUser(room.getTargetUser());

            tasks.add(task);
            for (TaskResponse.Task task1 : room.getTasks()){
                tasks.add(task1);
            }
        }
        callback.notifyDataChanged();
    }

    @Override
    public void onLoadError() {

    }

    public onLoadData getCallback() {
        return callback;
    }

    public void setCallback(onLoadData callback) {
        this.callback = callback;
    }

    public interface onLoadData{
        void notifyDataChanged();
        void onError();
    }
}
