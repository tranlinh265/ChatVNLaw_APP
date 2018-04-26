package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import android.os.Bundle;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.TodoListInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.CreateTaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.TaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogTaskContent;

import java.util.List;
import java.util.Map;

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
                task1.setRoomId(room.getId());
                tasks.add(task1);
            }
        }
        callback.notifyDataChanged();
    }

    public void createNewTask(String userToken, String userEmail, String roomId ,String content){
        interator.createNewTask(userToken,userEmail,content,roomId);
    }

    public void editTask(String userToken, String userEmail, String roomId, String taskId, String content, String status, int position){
        Bundle bundle = new Bundle();
        bundle.putString(TodoListInterator.BUNDLE_USER_TOKEN , userToken);
        bundle.putString(TodoListInterator.BUNDLE_USER_EMAIL , userEmail);
        bundle.putString(TodoListInterator.BUNDLE_ROOM_ID, roomId);
        bundle.putString(TodoListInterator.BUNDLE_TASK_CONTENT, content);
        bundle.putString(TodoListInterator.BUNDLE_TASK_STATUS, status);
        bundle.putString(TodoListInterator.BUNDLE_TASK_ID, taskId);
        bundle.putInt(DialogTaskContent.DIALOG_TASK_POSITION, position);

        interator.editTask(bundle);
    }

    public void deleteTask(String userToken, String userEmail, String roomId, String taskId, int position){
        Bundle bundle = new Bundle();
        bundle.putString(TodoListInterator.BUNDLE_USER_TOKEN , userToken);
        bundle.putString(TodoListInterator.BUNDLE_USER_EMAIL , userEmail);
        bundle.putString(TodoListInterator.BUNDLE_ROOM_ID, roomId);
        bundle.putString(TodoListInterator.BUNDLE_TASK_ID, taskId);
        bundle.putInt(DialogTaskContent.DIALOG_TASK_POSITION, position);

        interator.deleteTask(bundle);
    }
    @Override
    public void onLoadError() {

    }

    @Override
    public void onCreateTaskSuccess(Object o, String roomId) {
        TaskResponse.Task task = (TaskResponse.Task)o;
        task.setRoomId(Integer.valueOf(roomId));
        int i;
        for (i = tasks.size() - 1; i >= 0; i--){
           TaskResponse.Task task1 = tasks.get(i);
           if(task1.getRoomId().equals(task.getRoomId())){
               tasks.add(i+1, task);
               break;
           }
        }
        callback.notifyDataChanged();
    }

    @Override
    public void onCreateTaskError() {

    }

    @Override
    public void onEditTaskSuccess(Object o,int position) {
        TaskResponse.Task task = tasks.get(position);
        task.setContent(((CreateTaskResponse) o).getTask().getContent());
        task.setStatus(((CreateTaskResponse)o).getTask().getStatus());
        callback.notifyDataChanged();
    }

    @Override
    public void onEditTaskError() {

    }

    @Override
    public void onDeleteTaskSuccess(int position) {
        tasks.remove(position);
        callback.notifyDataChanged();
    }

    @Override
    public void onDeleteTaskError() {

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
