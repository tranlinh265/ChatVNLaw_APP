package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import android.os.Bundle;

import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.KEYWORD;
import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.RG_MODE;
import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.RG_MODE_VALUE;
import static com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogSearchLaw.RG_SORT_MODE;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class SearchLawInterator {
    private SearchLawListener callback;
    public SearchLawInterator(){

    }
    public void searchLaw(Bundle bundle){
        int rgModeChecked = bundle.getInt(RG_MODE,0);
        int rgSoftModeChecked = bundle.getInt(RG_SORT_MODE, 0);
        int rgSoftValueChecked = bundle.getInt(RG_MODE_VALUE, 0);
        String keyword = bundle.getString(KEYWORD, "");

        String group1 = rgModeChecked == 0 ? "Chính xác cụm từ trên" : "Có tất cả từ trên";
        String group2_1 = rgSoftModeChecked == 0 ? "Ngày phát hành" : "Ngày có hiệu lực";
        String group2_2 = rgSoftValueChecked == 0 ? "Mới tới cũ" : "Cũ tới mới";
        String page = bundle.getString("page", "1");
        ApiUtils.getService().searchLaw(keyword,group1, group2_1,group2_2, page).enqueue(new Callback<SearchLawResponse>() {
            @Override
            public void onResponse(Call<SearchLawResponse> call, Response<SearchLawResponse> response) {
                if(response.isSuccessful()){
                    if (response.body() != null){
                        if(response.body().getCurrentPage() == 1){
                            callback.onSearchSuccess(response.body());
                        }else{
                            if (response.body().getCurrentPage() > 1){
                                callback.onLoadMoreSuccess(response.body());
                            }else{
                                callback.onNoDataFound();
                            }
                        }
                    }
                }
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
        void onLoadMoreSuccess(Object o);
        void onNoDataFound();
    }
}
