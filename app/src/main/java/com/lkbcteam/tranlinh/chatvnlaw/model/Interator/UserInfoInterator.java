package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import android.content.Context;
import android.util.Log;

import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.UserInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 14/04/2018.
 */

public class UserInfoInterator {
    private loadDataCallBack callback;
    public UserInfoInterator(){

    }

    public UserInfoInterator(loadDataCallBack callback){
        this.callback = callback;
    }

    public void loadUserInfoFromRails(String username){
        ApiUtils.getService().getUserInfo(username).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                callback.onLoadSuccess(response.body());
                Log.e("log", "onResponse: " + response.body().toString() );
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });
    }
    public interface loadDataCallBack{
        void onLoadSuccess(UserInfoResponse response);
    }
}
