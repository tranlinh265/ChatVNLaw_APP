package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import android.os.Bundle;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.SearchLawInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;

import java.util.List;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class SearchLawPresenter implements SearchLawInterator.SearchLawListener{
    private List<SearchLawResponse.Article> articles;
    private SearchLawInterator interator;
    private SearchLawListener callback;

    public SearchLawPresenter(){
        interator = new SearchLawInterator();
        interator.setCallback(this);
    }

    public void searchLaw(Bundle keyWord){
        keyWord.putString("page","1");
        interator.searchLaw(keyWord);
    }

    public void loadMore(Bundle bundle){
        interator.searchLaw(bundle);
    }
    public List<SearchLawResponse.Article> getArticles() {
        return articles;
    }

    public void setArticles(List<SearchLawResponse.Article> articles) {
        this.articles = articles;
    }

    @Override
    public void onSearchSuccess(Object o) {
        articles.clear();
        SearchLawResponse response = (SearchLawResponse) o;
        articles.addAll(response.getArticles());
        String currentPosition = String.format("%d trong tổng số %d kết quả", articles.size(), response.getNumberArticles());
        callback.onSearchFirstPageSuccess(currentPosition);
    }

    @Override
    public void onSearchFalure() {

    }

    @Override
    public void onLoadMoreSuccess(Object o) {
        SearchLawResponse response = (SearchLawResponse) o;
        articles.addAll(response.getArticles());
        String currentPosition = String.format("%d trong tổng số %d kết quả", articles.size(), response.getNumberArticles());
        callback.onSearchMoreSuccess(currentPosition);
    }

    @Override
    public void onNoDataFound() {
        articles.clear();
        callback.onNoDataFound();
    }

    public SearchLawListener getCallback() {
        return callback;
    }

    public void setCallback(SearchLawListener callback) {
        this.callback = callback;
    }

    public interface SearchLawListener{
        void onSearchFirstPageSuccess(String currentPosition);
        void onSearchMoreSuccess(String currentPosition);
        void onNoDataFound();
    }
}
