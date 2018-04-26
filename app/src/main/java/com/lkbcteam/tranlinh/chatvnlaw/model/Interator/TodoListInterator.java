package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import android.os.Bundle;

import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.CreateTaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.TaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogTaskContent;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 23/04/2018.
 */

public class TodoListInterator {

    private onLoadData callback;

    public static final String BUNDLE_USER_TOKEN = "userToken";
    public static final String BUNDLE_USER_EMAIL = "userEmail";
    public static final String BUNDLE_ROOM_ID = "roomId";
    public static final String BUNDLE_TASK_ID = "taskId";
    public static final String BUNDLE_TASK_CONTENT = "taskContent";
    public static final String BUNDLE_TASK_STATUS = "taskStatus";

    public TodoListInterator(onLoadData callback){
        this.callback = callback;
    }

    public void createNewTask(String userToken, String userEmail, String content, String roomId){
        ApiUtils.getService().createNewTask(roomId,userToken,userEmail,content).enqueue(new Callback<CreateTaskResponse>() {
            @Override
            public void onResponse(Call<CreateTaskResponse> call, Response<CreateTaskResponse> response) {
                if(response.isSuccessful()){
                    callback.onCreateTaskSuccess(response.body().getTask(), roomId);
                }else {
                    callback.onLoadError();
                }
            }

            @Override
            public void onFailure(Call<CreateTaskResponse> call, Throwable t) {
                callback.onLoadError();
            }
        });
    }

    public void deleteTask(Bundle bundle){
        String userToken = bundle.getString(BUNDLE_USER_TOKEN, "");
        String userEmail = bundle.getString(BUNDLE_USER_EMAIL, "");
        String roomId = bundle.getString(BUNDLE_ROOM_ID , "");
        String taskId = bundle.getString(BUNDLE_TASK_ID, "");
        int position = bundle.getInt(DialogTaskContent.DIALOG_TASK_POSITION, -1);

        ApiUtils.getService().deleteTask(roomId,taskId,userToken, userEmail).enqueue(new Callback<CreateTaskResponse>() {
            @Override
            public void onResponse(Call<CreateTaskResponse> call, Response<CreateTaskResponse> response) {
                if (response.isSuccessful()){
                    callback.onDeleteTaskSuccess(position);
                }else{
                    callback.onDeleteTaskError();
                }
            }

            @Override
            public void onFailure(Call<CreateTaskResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onDeleteTaskError();
            }
        });
    }
    public void editTask(Bundle bundle){
        String userToken = bundle.getString(BUNDLE_USER_TOKEN, "");
        String userEmail = bundle.getString(BUNDLE_USER_EMAIL, "");

        String roomId = bundle.getString(BUNDLE_ROOM_ID , "");
        String taskId = bundle.getString(BUNDLE_TASK_ID, "");
        String taskContent = bundle.getString(BUNDLE_TASK_CONTENT, "");
        String taskStatus = bundle.getString(BUNDLE_TASK_STATUS,"");
        int position = bundle.getInt(DialogTaskContent.DIALOG_TASK_POSITION, -1);
        Map<String, String> map = new HashMap<>();
        map.put(Define.RailServer.EditTask.CONTENT , taskContent);
        map.put(Define.RailServer.EditTask.STATUS, taskStatus);

        ApiUtils.getService().editTask(roomId,taskId,userToken,userEmail,map).enqueue(new Callback<CreateTaskResponse>() {
            @Override
            public void onResponse(Call<CreateTaskResponse> call, Response<CreateTaskResponse> response) {
                if (response.isSuccessful()){
                    callback.onEditTaskSuccess(response.body(),position);
                }else{
                    callback.onEditTaskError();
                }
            }

            @Override
            public void onFailure(Call<CreateTaskResponse> call, Throwable t) {
                callback.onEditTaskError();
            }
        });
    }
    public void loadTaskFromRails(String lawyerId, String lawyerToken, String lawyerEmail){
        ApiUtils.getService().getTask(lawyerId, lawyerToken, lawyerEmail).enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                if(response.isSuccessful()){
                    callback.onLoadSuccess(response.body());
                }else{
                    callback.onLoadError();
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                callback.onLoadError();
            }
        });
    }

    public interface onLoadData{
        void onLoadSuccess(Object o);
        void onLoadError();
        void onCreateTaskSuccess(Object o, String roomId);
        void onCreateTaskError();
        void onEditTaskSuccess(Object o, int position);
        void onEditTaskError();
        void onDeleteTaskSuccess( int position);
        void onDeleteTaskError();
    }
}
