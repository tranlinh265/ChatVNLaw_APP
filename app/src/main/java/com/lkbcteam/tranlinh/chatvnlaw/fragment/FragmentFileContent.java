package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class FragmentFileContent extends BaseFragment {
    private String mUrl,mFileName;
    private WebView mWvFileContent;
    private ImageButton mIbtnClose;

    public FragmentFileContent(){

    }
    @SuppressLint("ValidFragment")
    public FragmentFileContent(String url, String fileName){
        mUrl = url;
        mFileName = fileName;
    }
    public static FragmentFileContent newInstance() {
        return new FragmentFileContent();
    }
    public static FragmentFileContent newInstance(String url, String fileName) {
        return new FragmentFileContent(url,fileName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file_content, container,true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWvFileContent = (WebView)view.findViewById(R.id.wv_file_content);
        mWvFileContent.setWebViewClient(new WebViewClient());
        mWvFileContent.getSettings().setLoadsImagesAutomatically(true);
        mWvFileContent.getSettings().setJavaScriptEnabled(true);
        mWvFileContent.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWvFileContent.loadUrl(mUrl);
        mIbtnClose = (ImageButton)view.findViewById(R.id.ibtn_close);
        mIbtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackFragment();
            }
        });
    }
}
