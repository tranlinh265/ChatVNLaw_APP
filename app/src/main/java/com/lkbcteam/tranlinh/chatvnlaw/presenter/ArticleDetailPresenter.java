package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.ArticleDetailInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ArticleDetailResponse;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class ArticleDetailPresenter implements ArticleDetailInterator.ArticleDetailListener {
    private ArticleDetailInterator interator;
    private ArticleDetailListener callback;

    public ArticleDetailPresenter(){
        interator = new ArticleDetailInterator();
        interator.setCallback(this);
    }

    public void getArticleDetail(String articleId){
        interator.getArticleDetail(articleId);
    }

    @Override
    public void onLoadSuccess(Object o) {
        ArticleDetailResponse response = (ArticleDetailResponse) o;
        callback.displayResult(response);
    }

    @Override
    public void onLoadFalure() {

    }

    public ArticleDetailListener getCallback() {
        return callback;
    }

    public void setCallback(ArticleDetailListener callback) {
        this.callback = callback;
    }

    public interface ArticleDetailListener{
        void displayResult(ArticleDetailResponse response);
    }
}
