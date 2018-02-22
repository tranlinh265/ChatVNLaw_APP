package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper;

import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import retrofit2.Retrofit;

/**
 * Created by tranlinh on 22/02/2018.
 */

public class ApiUtils {
    public static APIService getService(){
        return RetrofitClient.getClient(Define.Api.BASE_URL).create(APIService.class);
    }
}
