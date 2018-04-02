package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.*;

/**
 * Created by tranlinh on 29/03/2018.
 */

public class FragmentStartApp1 extends com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment {
    private static final long MOVE_DEFAULT_TIME = 2000;
    private static final long FADE_DEFAULT_TIME = 1300;

    private Handler delayedTransactionHandler = new Handler();
    private Runnable runnable = this::performTransition;

    private ImageView ivLogo;

    public static FragmentStartApp1 newInstance() {
        FragmentStartApp1 fragment = new FragmentStartApp1();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_app_1, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivLogo = view.findViewById(R.id.iv_logo);

        delayedTransactionHandler.postDelayed(runnable, 3000);
    }

    private void performTransition() {
        Fragment nextFragment = FragmentStartApp2.newInstance();
        Fragment previousFragment = getFragmentManager().findFragmentById(R.id.container_framelayout);

        // 1. Exit for Previous Fragment
        Fade exitFade = new Fade();
        exitFade.setDuration(FADE_DEFAULT_TIME);
        previousFragment.setExitTransition(exitFade);

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

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        delayedTransactionHandler.removeCallbacks(runnable);
    }

}
