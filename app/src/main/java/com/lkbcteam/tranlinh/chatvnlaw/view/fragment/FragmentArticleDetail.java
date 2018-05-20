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
import android.widget.Button;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ArticleDetailResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.custom.DialogArticleInfo;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.ArticleDetailPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class FragmentArticleDetail extends BaseFragment implements View.OnClickListener, ArticleDetailPresenter.ArticleDetailListener {
    private WebView webView;
    private ArticleDetailPresenter presenter;
    private ImageButton ibtnBack;
    private String articleId;
    private Button btnSearch;
    private ArticleDetailResponse articleDetailResponse;
    private ImageButton ibtnInfo;
    private Bundle bundle;

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
                view.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        ibtnBack = view.findViewById(R.id.ibtn_home_menu);
        ibtnBack.setOnClickListener(this);
        btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
        btnSearch.setVisibility(View.GONE);
        ibtnInfo = view.findViewById(R.id.ibtn_info);
        ibtnInfo.setOnClickListener(this);
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
        articleDetailResponse = response;
        webView.loadDataWithBaseURL(null,response.getFullHtml(), "text/html", "utf-8",null);
        webView.setClickable(false);
        if (bundle == null){
            bundle = new Bundle();
        }
        bundle.putString("numerical_symbol", response.getDetail().getNumericalSymbol());
        bundle.putString("article_type", response.getDetail().getArticleType());
        bundle.putString("source", response.getDetail().getSource());
        bundle.putString("agency_issued", response.getDetail().getAgencyIssued());
        bundle.putString("the_signer", response.getDetail().getTheSigner());
        bundle.putString("signer_title", response.getDetail().getSignerTitle());
        bundle.putString("scope", response.getDetail().getScope());
        bundle.putString("public_day", response.getDetail().getPublicDay());
        bundle.putString("effect_day", response.getDetail().getEffectDay());
        bundle.putString("day_report", response.getDetail().getDayReport());
        bundle.putString("effect_status", response.getDetail().getEffectDay());
    }

    @Override
    public void onLoadFalure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                goBackFragment();
                break;
            case R.id.btn_search:
                Document doc = Jsoup.parseBodyFragment(articleDetailResponse.getFullHtml());
                Element element = doc.getElementsByAttributeValue("name","1_1_1_1_0_0").first();
//                webView.loadDataWithBaseURL(null,"<script> document.getElementsByName(\"1_1_1_1_0_0\").first().scrollIntoView();  </script>", "text/html", "utf-8",null);
                webView.loadDataWithBaseURL(null,"<script> document.getElementById(\"toanvancontent\").scrollIntoView();  </script>", "text/html", "utf-8",null);

                break;
            case R.id.ibtn_info:
                DialogArticleInfo dialogArticleInfo = new DialogArticleInfo();
                dialogArticleInfo.setArguments(bundle);
                dialogArticleInfo.show(getFragmentManager(), "articleInfo");
                break;

        }
    }

}
