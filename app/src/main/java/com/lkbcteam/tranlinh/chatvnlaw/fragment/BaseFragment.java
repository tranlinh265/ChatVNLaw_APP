package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.activity.BaseActivity;

/**
 * Created by tranlinh on 26/01/2018.
 */

public abstract class BaseFragment  extends Fragment {

    private BaseActivity mBaseActivity;

    public BaseActivity getBaseActivity(){
        return mBaseActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity){
            mBaseActivity = (BaseActivity)context;
        } else {
            throw new RuntimeException("Fragment must be attach to BaseActivity");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void startActivity(Class activity, boolean animation){
        mBaseActivity.startActivity(activity,animation);
    }
    public void goNextFragment(Fragment fragment, boolean addToBackStack){
        goNextFragment(fragment,addToBackStack,true);
    }
    public void goNextFragment(Fragment fragment, boolean addToBackStack, boolean anim){
        ((BaseContainerFragment) getParentFragment()).replaceFragment(fragment, addToBackStack,anim);
    }

    public void goBackFragment(){
        ((BaseContainerFragment) getParentFragment()).popFragment();
    }


}
