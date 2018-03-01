package com.lkbcteam.tranlinh.chatvnlaw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import com.google.firebase.auth.FirebaseAuth;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.FragmentHomeContainer;

/**
 * Created by tranlinh on 26/01/2018.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        replaceContainerFragment(R.id.content, FragmentHomeContainer.newInstance());
    }

}
