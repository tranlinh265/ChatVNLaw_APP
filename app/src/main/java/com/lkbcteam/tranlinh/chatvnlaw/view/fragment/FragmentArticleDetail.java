package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ArticleDetailResponse;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.ArticleDetailPresenter;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class FragmentArticleDetail extends BaseFragment implements View.OnClickListener, ArticleDetailPresenter.ArticleDetailListener {
    private WebView webView;
    private ArticleDetailPresenter presenter;
    private ImageButton ibtnBack;
    private String articleId;

    public static FragmentArticleDetail newInstance() {

        Bundle args = new Bundle();

        FragmentArticleDetail fragment = new FragmentArticleDetail();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_detail, container,false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        webView = view.findViewById(R.id.wv_law_content);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        ibtnBack = view.findViewById(R.id.ibtn_home_menu);
        ibtnBack.setOnClickListener(this);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        articleId = getArguments().getString("articleId", "0");
        presenter = new ArticleDetailPresenter();
        presenter.setCallback(this);
        presenter.getArticleDetail(articleId);
    }

    @Override
    public void displayResult(ArticleDetailResponse response) {
        webView.loadDataWithBaseURL("https://vnlaw.datalab.vn"+"/articles/"+articleId,response.getFullHtml(), "text/html", "utf-8",null);
        webView.setClickable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                goBackFragment();
        }
    }
}
