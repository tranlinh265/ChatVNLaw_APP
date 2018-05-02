package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.SearchLawInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;

import java.util.ArrayList;
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

    public void searchLaw(String keyWord){
        interator.searchLaw(keyWord);
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
        callback.notifyDataChanged();
    }

    @Override
    public void onSearchFalure() {

    }

    public SearchLawListener getCallback() {
        return callback;
    }

    public void setCallback(SearchLawListener callback) {
        this.callback = callback;
    }

    public interface SearchLawListener{
        void notifyDataChanged();
        void notifyDataInserted();
    }
}
