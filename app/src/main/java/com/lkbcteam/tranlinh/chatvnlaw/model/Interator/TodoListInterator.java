package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.CreateTaskResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.TaskResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 23/04/2018.
 */

public class TodoListInterator {

    private onLoadData callback;

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
    }
}
