package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import com.lkbcteam.tranlinh.chatvnlaw.model.apiresponse.SearchLawyerResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class SearchLawInterator {
    private SearchLawListener callback;
    public SearchLawInterator(){

    }
    public void searchLaw(String keyword){
        ApiUtils.getService().searchLaw(keyword).enqueue(new Callback<SearchLawResponse>() {
            @Override
            public void onResponse(Call<SearchLawResponse> call, Response<SearchLawResponse> response) {
                callback.onSearchSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SearchLawResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onSearchFalure();
            }
        });
    }

    public SearchLawListener getCallback() {
        return callback;
    }

    public void setCallback(SearchLawListener callback) {
        this.callback = callback;
    }

    public interface SearchLawListener{
        void onSearchSuccess(Object o);
        void onSearchFalure();
    }
}
