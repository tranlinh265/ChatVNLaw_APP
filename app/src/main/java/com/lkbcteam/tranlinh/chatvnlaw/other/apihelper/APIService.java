package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper;

import com.lkbcteam.tranlinh.chatvnlaw.model.SearchLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tranlinh on 22/02/2018.
 */

public interface APIService {
    @GET(Define.Api.SEARCH_LAWYER)
    Call<SearchLawyerResponse> searchLawyer(@Query("query") String name);
}
