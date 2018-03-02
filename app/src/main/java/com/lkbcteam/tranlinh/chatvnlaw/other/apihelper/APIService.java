package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper;

import android.support.annotation.Nullable;

import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.LawyerNameResponse;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.SearchLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.TopLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tranlinh on 22/02/2018.
 */

public interface APIService {
    @GET(Define.Api.SEARCH_LAWYER)
    Call<SearchLawyerResponse> searchLawyer( @Query("query") @Nullable String name, @Query("page") int page);
    @GET(Define.Api.LAWYER_NAME)
    Call<LawyerNameResponse> getAllNameOfLawyer();
    @GET(Define.Api.TOP_LAWYERS)
    Call<TopLawyerResponse> getTopLawyer();
}
