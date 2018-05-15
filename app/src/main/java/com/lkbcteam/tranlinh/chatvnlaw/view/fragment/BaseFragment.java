package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.BaseActivity;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragmentcontainer.BaseFragmentContainer;

import java.util.ArrayList;

/**
 * Created by tranlinh on 26/01/2018.
 */

public abstract class BaseFragment  extends Fragment {
    protected static final long FADE_DEFAULT_TIME = 1300;

    private BaseActivity baseActivity;
    protected View parrentLayout;

    public BaseActivity getBaseActivity(){
        return baseActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if( context instanceof BaseActivity){
            baseActivity = (BaseActivity) context;
        }else {
            throw new RuntimeException("Fragment must be attact to BaseActivity");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData(view);
    }

    protected void initView(View view){
        parrentLayout = view.findViewById(R.id.view_parrent);
    }

    protected void initData(View view){}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void startActivity(Class activity, boolean animation){
        baseActivity.startActivity(activity,animation);
    }

    public void goNextFragment(Fragment fragment, boolean addToBackStack){
        ((BaseFragmentContainer)getParentFragment()).replaceFragment(fragment,addToBackStack,null);
    }

    public void goNextFragment(Fragment fragment, boolean addToBackStack, View sharedView){
        ((BaseFragmentContainer)getParentFragment()).replaceFragment(fragment,addToBackStack,sharedView);
    }

    public void goNextFragment(Fragment fragment, boolean addToBackStack, boolean enterTransition){
        ((BaseFragmentContainer)getParentFragment()).replaceFragment(fragment,addToBackStack,enterTransition);
    }

    public void goNextFragment(Fragment fragment, boolean addToBackStack, boolean enterTransition, View sharedView){
        ((BaseFragmentContainer)getParentFragment()).replaceFragment(fragment,addToBackStack,enterTransition,sharedView);
    }

    public void goNextFragment(Fragment fragment, boolean addToBackStack, boolean enterTransition, ArrayList<View> sharedViews){
        ((BaseFragmentContainer)getParentFragment()).replaceFragment(fragment,addToBackStack,enterTransition,sharedViews);
    }
    public void goBackFragment(){
        ((BaseFragmentContainer)getParentFragment()).popFragment();
    }
}
