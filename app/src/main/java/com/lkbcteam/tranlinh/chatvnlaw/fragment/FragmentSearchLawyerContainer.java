package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by tranlinh on 01/03/2018.
 */

public class FragmentSearchLawyerContainer extends BaseFragmentContainer {
    public static FragmentSearchLawyerContainer newInstance() {

        Bundle args = new Bundle();

        FragmentSearchLawyerContainer fragment = new FragmentSearchLawyerContainer();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment(FragmentSearchLawyer.newInstance(),false);
    }
}
