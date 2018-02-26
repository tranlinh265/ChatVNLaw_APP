package com.lkbcteam.tranlinh.chatvnlaw.activity;

import android.os.Bundle;
import android.view.Menu;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.FragmentLoginContainer;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        replaceContainerFragment(R.id.fullscreen_content, FragmentLoginContainer.newInstance());
    }

}
