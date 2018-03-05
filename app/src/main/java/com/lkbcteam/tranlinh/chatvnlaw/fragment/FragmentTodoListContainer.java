package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by tranlinh on 05/03/2018.
 */

public class FragmentTodoListContainer extends BaseFragmentContainer {
    public static FragmentTodoListContainer newInstance() {
        FragmentTodoListContainer fragment = new FragmentTodoListContainer();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment(FragmentTodos.newInstance(),false);
    }
}
