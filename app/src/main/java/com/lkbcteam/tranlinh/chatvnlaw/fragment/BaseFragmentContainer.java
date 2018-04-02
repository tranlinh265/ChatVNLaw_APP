package com.lkbcteam.tranlinh.chatvnlaw.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 02/04/2018.
 */

public abstract class BaseFragmentContainer extends BaseFragment {
    private static final long MOVE_DEFAULT_TIME = 2000;
    private static final long FADE_DEFAULT_TIME = 1300;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_container, container,false);
    }

    public void replaceFragment(Fragment nextFragment, boolean addToBackStack,@Nullable View sharedView) {
        try {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            String tag = nextFragment.getClass().getSimpleName();
            if (addToBackStack) {
                transaction.addToBackStack(tag);
            }
            if (sharedView != null) {
                transaction.addSharedElement(sharedView, sharedView.getTransitionName());
            }
            transaction.replace(R.id.container_framelayout, nextFragment,tag);
            transaction.commitAllowingStateLoss();
            getChildFragmentManager().executePendingTransactions();
        } catch ( Exception ex){
            ex.printStackTrace();
        }
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, boolean enterTransition){
        try{
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            String tag = fragment.getClass().getSimpleName();
            if (addToBackStack){
                fragmentTransaction.addToBackStack(tag);
            }
            if (enterTransition){
                fragmentTransaction.setCustomAnimations( R.animator.trans_left_in,R.animator.trans_left_out,R.animator.trans_right_in, R.animator.trans_right_out);
            }else{
                fragmentTransaction.setCustomAnimations(R.animator.trans_right_in, R.animator.trans_right_out, R.animator.trans_left_in,R.animator.trans_left_out);
            }

            fragmentTransaction.replace(R.id.container_framelayout, fragment,tag);
            fragmentTransaction.commit();
            getChildFragmentManager().executePendingTransactions();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public boolean popFragment(){
        try{
            boolean isPop = false;
            BaseFragment currentFragment = getCurrentFragment();
            Log.e("123", "popFragment: " );
            if(getChildFragmentManager().getBackStackEntryCount() > 0 && currentFragment != null){
                Log.e("456", "popFragment: " );
                isPop = getChildFragmentManager().popBackStackImmediate(currentFragment.getClass().getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            return isPop;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public BaseFragment getCurrentFragment(){
        try{
            return (BaseFragment) getChildFragmentManager().findFragmentById(R.id.container_framelayout);
        }catch (ClassCastException ex){
            throw new RuntimeException("This project must inherits BaseFragment");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
