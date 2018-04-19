package com.lkbcteam.tranlinh.chatvnlaw.view.fragmentcontainer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.FragmentEditProfile;

/**
 * Created by tranlinh on 08/03/2018.
 */

public class FragmentEditProfileContainer extends BaseFragmentContainer {
    public static FragmentEditProfileContainer newInstance() {

        Bundle args = new Bundle();

        FragmentEditProfileContainer fragment = new FragmentEditProfileContainer();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment(FragmentEditProfile.newInstance(), false,false);
    }
}
