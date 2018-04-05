package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.*;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;

/**
 * Created by tranlinh on 29/03/2018.
 */

public class FragmentStartApp2 extends BaseFragment {
    private static final long MOVE_DEFAULT_TIME = 2000;
    private static final long FADE_DEFAULT_TIME = 1300;

    private ImageView ivLogo;
    private Handler delayedTransactionHandler = new Handler();
    private Runnable runnable = () -> performTransition();
    private FirebaseUser firebaseUser;
    private TextView tvWelcome;
    private ProgressBar pbLoading;

    public static FragmentStartApp2 newInstance() {
        FragmentStartApp2 fragment = new FragmentStartApp2();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_app_2, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ivLogo = view.findViewById(R.id.fragment2_logo);
        tvWelcome = view.findViewById(R.id.tv_welcome_user);
        pbLoading = view.findViewById(R.id.pb_loading);

        delayedTransactionHandler.postDelayed(runnable, 5000);
    }

    private void performTransition() {
        String userToken = SharePreference.getInstance(getActivity()).getUserToken();
        if(firebaseUser != null && !TextUtils.isEmpty(userToken)){
            // hide progress bar and show welcome text
            pbLoading.setVisibility(View.GONE);
            tvWelcome.setText(String.format("Chào mừng @dawdwa"));
            tvWelcome.setVisibility(View.VISIBLE);
            (new Handler()).postDelayed((Runnable) () -> {
                getBaseActivity().startActivity(HomeActivity.class, false);
            }, 3000);
        }else{
            // relogin
            Fragment previousFragment = getFragmentManager().findFragmentById(R.id.container_framelayout);
            Fragment nextFragment = FragmentLogin.newInstance();

            pbLoading.setVisibility(View.GONE);
            // 1. Exit for Previous Fragment

            Slide slide = new Slide();
            slide.setDuration(FADE_DEFAULT_TIME);
            previousFragment.setExitTransition(slide);
            // 2. Shared Elements Transition
            TransitionSet enterTransitionSet = new TransitionSet();
            enterTransitionSet.addTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
            enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
            enterTransitionSet.setStartDelay(FADE_DEFAULT_TIME);
            nextFragment.setSharedElementEnterTransition(enterTransitionSet);

            // 3. Enter Transition for New Fragment
            Fade enterFade = new Fade();
            enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
            enterFade.setDuration(FADE_DEFAULT_TIME);
            nextFragment.setEnterTransition(enterFade);

            goNextFragment(nextFragment,false,ivLogo);
        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        delayedTransactionHandler.removeCallbacks(runnable);
    }
}
