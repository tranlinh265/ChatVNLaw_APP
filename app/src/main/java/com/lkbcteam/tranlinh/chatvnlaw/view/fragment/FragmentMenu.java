package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.view.activity.ActivityEditProfile;
import com.lkbcteam.tranlinh.chatvnlaw.view.activity.ActivitySearchLaw;
import com.lkbcteam.tranlinh.chatvnlaw.view.activity.ActivitySearchLawer;
import com.lkbcteam.tranlinh.chatvnlaw.view.activity.ActivityTodoList;
import com.lkbcteam.tranlinh.chatvnlaw.view.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.view.activity.MainActivity;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.SnackbarHelper;

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
                if (mPosition == 0){
                    goBackFragment();
                }else{
                    getBaseActivity().startActivity(HomeActivity.class, false);
                }
                break;
            case R.id.item_noti_container:

                break;
            case R.id.item_profile_container:
                if(mPosition ==2){
                    if(SharePreference.getInstance(getActivity()).getRole().equals("Lawyer")){
                        goBackFragment();
                    }else{
                        SnackbarHelper.showLongSnackBar(parrentLayout, Define.Notice.LAWYER_ONLY);
                    }
                }else{
                    if(SharePreference.getInstance(getActivity()).getRole().equals("Lawyer")) {
                        getBaseActivity().startActivity(ActivityEditProfile.class,false);
//                        getBaseActivity().finish();
                    }else{
                        SnackbarHelper.showLongSnackBar(parrentLayout, Define.Notice.LAWYER_ONLY);
                    }

                }
                break;
            case R.id.item_search_law_container:
                if( mPosition == 4){
                    goBackFragment();
                }else{
                    getBaseActivity().startActivity(ActivitySearchLaw.class, false);
                }
                break;
            case R.id.item_search_lawyer_container:
                if (mPosition == 5){
                    goBackFragment();
                }else{
                    getBaseActivity().startActivity(ActivitySearchLawer.class,false);
                    getBaseActivity().finish();
                }
                break;
            case R.id.item_todos_container:
                if(mPosition == 3){
                    if(SharePreference.getInstance(getActivity()).getRole().equals("Lawyer")){
                        goBackFragment();
                    }else{
                        SnackbarHelper.showLongSnackBar(parrentLayout, Define.Notice.LAWYER_ONLY);
                    }
                }else{
                    if(SharePreference.getInstance(getActivity()).getRole().equals("Lawyer")) {
                        getBaseActivity().startActivity(ActivityTodoList.class,false);
                    }else{
                        SnackbarHelper.showLongSnackBar(parrentLayout, Define.Notice.LAWYER_ONLY);
                    }

                }
                break;

            case R.id.ibtn_close:
                goBackFragment();
                break;
            case R.id.btn_logout:
                FirebaseAuth.getInstance().signOut();
                SharePreference.getInstance(getActivity()).resetShareReferenceData();
                getBaseActivity().startActivity(MainActivity.class,true);
                getBaseActivity().finish();
                break;
        }
    }
}
