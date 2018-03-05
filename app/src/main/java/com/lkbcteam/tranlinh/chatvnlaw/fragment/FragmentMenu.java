package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.ActivitySearchLawer;
import com.lkbcteam.tranlinh.chatvnlaw.activity.ActivityTodoList;
import com.lkbcteam.tranlinh.chatvnlaw.activity.MainActivity;

/**
 * Created by tranlinh on 31/01/2018.
 */

public class FragmentMenu extends BaseFragment implements View.OnClickListener{
    private ImageButton mIbtnCloseMenu;
    private int mPosition;

    private View mHomeContainer, mNotiContainer, mTodosContainer, mProfileContainer, mSearchLawContainer, mSearchLawyerContainer;
    private Button mBtnLogout;

    public static FragmentMenu newInstance(int position) {
        FragmentMenu fragmentMenu = new FragmentMenu();
        fragmentMenu.mPosition = position;
        return fragmentMenu;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIbtnCloseMenu =  view.findViewById(R.id.ibtn_close);
        mHomeContainer = view.findViewById(R.id.item_home_container);
        mNotiContainer = view.findViewById(R.id.item_noti_container);
        mTodosContainer = view.findViewById(R.id.item_todos_container);
        mProfileContainer = view.findViewById(R.id.item_profile_container);
        mSearchLawContainer = view.findViewById(R.id.item_search_law_container);
        mSearchLawyerContainer = view.findViewById(R.id.item_search_lawyer_container);
        mBtnLogout = view.findViewById(R.id.btn_logout);

        switch (mPosition){
            case 0:
                changeBackgroundColor(mHomeContainer);
                break;
            case 1:
                changeBackgroundColor(mNotiContainer);
                break;
            case 2:
                changeBackgroundColor(mProfileContainer);
                break;
            case 3:
                changeBackgroundColor(mTodosContainer);
                break;
            case 4:
                changeBackgroundColor(mSearchLawContainer);
                break;
            case 5:
                changeBackgroundColor(mSearchLawyerContainer);
                break;
        }
        mIbtnCloseMenu.setOnClickListener(this);
        mHomeContainer.setOnClickListener(this);
        mNotiContainer.setOnClickListener(this);
        mTodosContainer.setOnClickListener(this);
        mProfileContainer.setOnClickListener(this);
        mSearchLawContainer.setOnClickListener(this);
        mSearchLawyerContainer.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
    }

    private void changeBackgroundColor(View view){
        view.setBackgroundColor(getResources().getColor(R.color.colorPinkLight));
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_home_container:
                goNextFragment(FragmentHome.newInstance(), true,true);
                break;
            case R.id.item_noti_container:
                goNextFragment(FragmentNotification.newInstance(), true,true);
                break;
            case R.id.item_profile_container:
                goNextFragment(FragmentProfile.newInstance(),true,true);
                break;
            case R.id.item_search_law_container:
                goNextFragment(FragmentSearchLaw.newInstance(),true,true);
                break;
            case R.id.item_search_lawyer_container:
                if (mPosition == 5){
                    goBackFragment();
                }else{
                    getBaseActivity().startActivity(ActivitySearchLawer.class,false);
                }
//                goNextFragment(FragmentSearchLawyer.newInstance(),true,true);
                break;
            case R.id.item_todos_container:
                if( mPosition == 3){
                    goBackFragment();
                }else{
                    getBaseActivity().startActivity(ActivityTodoList.class,false);
                }
//                goNextFragment(FragmentTodos.newInstance(),true,true);
                break;
            case R.id.ibtn_close:
                goBackFragment();
                break;
            case R.id.btn_logout:
                FirebaseAuth.getInstance().signOut();
                getBaseActivity().startActivity(MainActivity.class,true);
                break;
        }
    }
}
