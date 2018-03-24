package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.activity.BaseActivity;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragmentcontainer.BaseFragmentContainer;

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
        initView(view);
        initData(view);
    }
    protected void initView(View view){

    }
    protected void initData(View view){

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
        ((BaseFragmentContainer) getParentFragment()).replaceFragment(fragment, addToBackStack,anim);
    }

    public void goBackFragment(){
        ((BaseFragmentContainer) getParentFragment()).popFragment();
    }


}
