package com.lkbcteam.tranlinh.chatvnlaw.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragmentcontainer.FragmentSearchLawyerContainer;

/**
 * Created by tranlinh on 01/03/2018.
 */

public class ActivitySearchLawer extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_lawyers);
        super.onCreate(savedInstanceState);
        replaceContainerFragment(R.id.content, FragmentSearchLawyerContainer.newInstance());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
//        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }
}
