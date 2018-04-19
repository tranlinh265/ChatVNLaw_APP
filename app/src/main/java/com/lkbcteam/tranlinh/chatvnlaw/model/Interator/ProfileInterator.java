package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import android.os.Bundle;

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

    public void updateProfileData(Bundle bundle){
        String username = bundle.getString("username", "");
        String userToken = bundle.getString("userToken","");
        String email = bundle.getString("email", "");
        String birthDay = bundle.getString("birthDay","");
        String achievement = bundle.getString("achievement","");
        String cardNumber = bundle.getString("cardNumber","");
        String certificate = bundle.getString("certificate","");
        String exp = bundle.getString("exp","");
        String intro = bundle.getString("intro","");
        String education = bundle.getString("education","");
        String workPlace = bundle.getString("workPlace","");
        String displayName = bundle.getString("displayName", "");
        ApiUtils.getService().updateLawyerInfo(username,userToken,email,birthDay,
                displayName,achievement,cardNumber,certificate,education,intro,exp,workPlace)
        .enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    callback.onUpdateSuccess();
                }else{
                    callback.onUpdateFalure(response.code());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                callback.onUpdateFalure(999);
            }
        });
    }
    public interface LoadProfileDataListener{
        void onLoadSuccess(Object o);
        void onLoadFailure(String error);
        void onUpdateSuccess();
        void onUpdateFalure(Object error);
    }
}
