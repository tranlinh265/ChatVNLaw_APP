package com.lkbcteam.tranlinh.chatvnlaw.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 19/03/2018.
 */

public class ActivityWebView extends AppCompatActivity {
    private WebView wvVideoCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        wvVideoCall = findViewById(R.id.wv_video_call);
        wvVideoCall.getSettings().setJavaScriptEnabled(true);
        wvVideoCall.setWebChromeClient(new WebChromeClient());
        wvVideoCall.loadUrl("file:///android_asset/tvn.html");
    }
}
