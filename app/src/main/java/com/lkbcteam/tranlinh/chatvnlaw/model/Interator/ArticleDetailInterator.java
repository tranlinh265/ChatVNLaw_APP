package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ArticleDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class ArticleDetailInterator {
    private ArticleDetailListener callback;
    public ArticleDetailInterator(){

    }

    public void getArticleDetail(String articleId){
        ApiUtils.getService().getArticleDetail(articleId).enqueue(new Callback<ArticleDetailResponse>() {
            @Override
            public void onResponse(Call<ArticleDetailResponse> call, Response<ArticleDetailResponse> response) {
                if (response.isSuccessful()){
                    callback.onLoadSuccess(response.body());
                }else{
                    callback.onLoadFalure();
                }

            }

            @Override
            public void onFailure(Call<ArticleDetailResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadFalure();
            }
        });
    }

    public ArticleDetailListener getCallback() {
        return callback;
    }

    public void setCallback(ArticleDetailListener callback) {
        this.callback = callback;
    }

    public interface ArticleDetailListener{
        void onLoadSuccess(Object o);
        void onLoadFalure();
    }
}
