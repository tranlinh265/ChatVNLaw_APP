package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
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
 * Created by tranlinh on 26/01/2018.
 */

public abstract class BaseFragmentContainer extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_container, container,false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        replaceFragment(fragment,addToBackStack,true);
    }
    public void replaceFragment(Fragment fragment, boolean addToBackStack, boolean anim){
        try{
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            String tag = fragment.getClass().getSimpleName();
            if (addToBackStack){
                fragmentTransaction.addToBackStack(tag);
            }
            if (anim){
                fragmentTransaction.setCustomAnimations( R.animator.trans_left_in,R.animator.trans_left_out,R.animator.trans_right_in, R.animator.trans_right_out);
            }else{
                fragmentTransaction.setCustomAnimations(R.animator.trans_right_in, R.animator.trans_right_out, R.animator.trans_left_in,R.animator.trans_left_out);
            }
            fragmentTransaction.replace(R.id.container_framelayout, fragment,tag);
            fragmentTransaction.commit();
            getChildFragmentManager().executePendingTransactions();

        }catch (Exception ex){
            Log.e("ex", String.valueOf(ex));
        }
    }
    public boolean popFragment(){
        try {
            boolean isPop = false;
            BaseFragment currentFragment = getCurrentFragment();
            if (getChildFragmentManager().getBackStackEntryCount() > 0 &&  currentFragment != null){
                isPop = getChildFragmentManager().popBackStackImmediate( currentFragment.getClass().getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            return isPop;
        }catch (Exception ex){
            Log.e("ex", String.valueOf(ex));
        }
        return false;
    }

    public BaseFragment getCurrentFragment(){
        try {
            return (BaseFragment) getChildFragmentManager().findFragmentById(R.id.container_framelayout);
        } catch (ClassCastException ex){
            throw  new RuntimeException("This project must inherits BaseFragment");
        } catch (Exception ex){
            Log.e("ex", String.valueOf(ex));
        }
        return null;
    }
}