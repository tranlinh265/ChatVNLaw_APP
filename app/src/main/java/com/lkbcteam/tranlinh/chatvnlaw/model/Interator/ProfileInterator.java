package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 16/04/2018.
 */

public class ProfileInterator {
    private LoadProfileDataListener callback;

    public ProfileInterator(LoadProfileDataListener callback){
        this.callback = callback;
    }

    public void loadProfileDataFromRails(String username){
        ApiUtils.getService().getLawyerProfileData(username).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    callback.onLoadSuccess(response.body());
                }else {
                    callback.onLoadFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFailure(t.getMessage());
            }
        });
    }
    public interface LoadProfileDataListener{
        void onLoadSuccess(Object o);
        void onLoadFailure(String error);
    }
}
