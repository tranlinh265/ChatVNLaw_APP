package com.lkbcteam.tranlinh.chatvnlaw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.FragmentEditProfileContainer;

/**
 * Created by tranlinh on 08/03/2018.
 */

public class ActivityEditProfile extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_default);
        super.onCreate(savedInstanceState);
        replaceContainerFragment(R.id.content, FragmentEditProfileContainer.newInstance());
    }
}
