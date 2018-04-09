package com.lkbcteam.tranlinh.chatvnlaw.view.fragmentcontainer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class FragmentHomeContainer extends BaseFragmentContainer {
    public static FragmentHomeContainer newInstance(){
        return new FragmentHomeContainer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment(com.lkbcteam.tranlinh.chatvnlaw.view.fragment.FragmentHome.newInstance(),false, null);
    }
}
