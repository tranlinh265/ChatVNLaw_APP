package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
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
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.UserInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void relogin(){
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

    private void loginSuccess(String displayName){
        pbLoading.setVisibility(View.GONE);
        tvWelcome.setText(String.format(Define.Notice.WELCOME) + SharePreference.getInstance(getActivity()).getUsername());
        tvWelcome.setVisibility(View.VISIBLE);
        (new Handler()).postDelayed((Runnable) () -> {
            getBaseActivity().startActivity(HomeActivity.class, false);
        }, 3000);
    }
    private void performTransition() {
        String userToken = SharePreference.getInstance(getActivity()).getUserToken();
        String userName = SharePreference.getInstance(getActivity()).getUsername();
        if(firebaseUser != null && !TextUtils.isEmpty(userToken) && !TextUtils.isEmpty(userName)){
            // hide progress bar and show welcome text
            ApiUtils.getService().getUserInfo(userName).enqueue(new Callback<UserInfoResponse>() {
                @Override
                public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                    if(response.isSuccessful()){
                        loginSuccess(response.body().getUserInfo().getProfile().getDisplayName());
                    }else {
                        relogin();
                    }
                }

                @Override
                public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                    relogin();
                }
            });
        }else{
            // relogin
            relogin();
        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        delayedTransactionHandler.removeCallbacks(runnable);
    }
}
