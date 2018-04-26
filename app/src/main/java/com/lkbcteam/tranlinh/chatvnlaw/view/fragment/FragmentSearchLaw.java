package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 26/04/2018.
 */

public class FragmentSearchLaw  extends BaseFragment{
    public static FragmentSearchLaw newInstance() {

        Bundle args = new Bundle();

        FragmentSearchLaw fragment = new FragmentSearchLaw();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_law,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebView webView = view.findViewById(R.id.wv_law_content);
        String htmlAsString = getString(R.string.html);
        htmlAsString = htmlAsString.replace("\\r", "");
        webView.loadDataWithBaseURL(null,htmlAsString,"text/html", "utf-8", null);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("javascript:scrollToElement('"+"1_1_1_2_0_0"+"')");

    }
}
