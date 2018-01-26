package com.lkbcteam.tranlinh.chatvnlaw.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseContainerFragment;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;


/**
 * Created by tranlinh on 26/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected int mContainerViewId;

    public void startActivity(Class activity, boolean animation) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        if (animation){
            overridePendingTransition(R.animator.trans_right_in,R.animator.trans_left_out);
        }
    }

    public void startActivity(Class activity, boolean animation, String action){
        Intent intent = new Intent(this,activity);
        if (!TextUtils.isEmpty(action)){
            intent.setAction(action);
        }
        startActivity(activity,animation);
    }

    protected void replaceContainerFragment(@IdRes int containerViewId, @NonNull Fragment fragment){
        replaceContainerFragment(containerViewId,fragment,false);
    }
    protected void replaceContainerFragment(@IdRes int containerViewId, @NonNull Fragment fragment, boolean addToBackStack){
        mContainerViewId = containerViewId;
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public BaseContainerFragment getCurrentFragmentContainer(){
        try{
            return (BaseContainerFragment)
                    getSupportFragmentManager().findFragmentById(mContainerViewId);
        } catch (ClassCastException e){
            throw  new RuntimeException("This project must inherits BaseContainer Fragment");
        } catch (Exception ex){
            Log.e("Ex", String.valueOf(ex));
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void replaceFragment(Fragment fragment, boolean addToBackStack){
        BaseContainerFragment baseContainerFragment = getCurrentFragmentContainer();
        if (baseContainerFragment != null){
            BaseFragment current = baseContainerFragment.getCurrentFragment();

            if (current != null && fragment != null && current.getClass().getSimpleName().contentEquals(fragment.getClass().getSimpleName())){
                return;
            }
            baseContainerFragment.replaceFragment(fragment,addToBackStack);
        }
    }

    protected void hideStatusBar(){
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hideStatusBar();
    }
}
