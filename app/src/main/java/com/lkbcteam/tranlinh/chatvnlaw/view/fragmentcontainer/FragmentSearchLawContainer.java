package com.lkbcteam.tranlinh.chatvnlaw.view.fragmentcontainer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.FragmentSearchLaw;

/**
 * Created by tranlinh on 02/05/2018.
 */

public class FragmentSearchLawContainer extends BaseFragmentContainer {
    public static FragmentSearchLawContainer newInstance() {

        Bundle args = new Bundle();

        FragmentSearchLawContainer fragment = new FragmentSearchLawContainer();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment(FragmentSearchLaw.newInstance(),false,true);
    }
}
