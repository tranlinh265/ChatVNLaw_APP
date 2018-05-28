package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.view.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.other.adapter.RoomListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class FragmentHome extends BaseFragment implements HomePresenter.HomeView, View.OnClickListener,RoomListAdapter.onClick {
    private static final long MOVE_DEFAULT_TIME = 2000;
    private static final long FADE_DEFAULT_TIME = 1300;
    private RecyclerView rvRoomList;
    private List<RoomListResponse.Room> roomList;
    private RecyclerView.LayoutManager layoutManager;
    private RoomListAdapter adapter;
    private ImageButton ibtnHomeMenu;
    private HomePresenter homePresenter;
    private TextView tvCountAllMess;
    private ImageView ivTop;

//    private CircleImageView civProfileImage;
//    private TextView tvUserDisplayName;

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_new_design, container,false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        // init recycler view
        roomList = new ArrayList<>();
        rvRoomList = view.findViewById(R.id.rv_messages);
        layoutManager = new GridLayoutManager(getContext(), 1);
        rvRoomList.setLayoutManager(layoutManager);
        adapter = new RoomListAdapter(getContext(),roomList);
        adapter.setCallback(this);
        rvRoomList.setAdapter(adapter);
//        civProfileImage = view.findViewById(R.id.civ_home_profile);
//        tvUserDisplayName = view.findViewById(R.id.tv_welcome_user);
        ibtnHomeMenu = view.findViewById(R.id.ibtn_home_menu);
        ibtnHomeMenu.setOnClickListener(this);
        tvCountAllMess = view.findViewById(R.id.tv_count_all_chat);
        ivTop = view.findViewById(R.id.iv_home_top_image);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        homePresenter = new HomePresenter(this,roomList);
//        homePresenter.loadRoomListFromFirebase();
        homePresenter.loadRoomListFromRail(SharePreference.getInstance(getActivity()).getUserToken());
        homePresenter.loadUserInfo(SharePreference.getInstance(getActivity()).getUsername());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                Fragment nextFragment = FragmentMenu.newInstance(0);
                Fragment previousFragment = getFragmentManager().findFragmentById(R.id.container_framelayout);
                Transition sharedElementEnterTransition = TransitionInflater.from(getContext()).inflateTransition(R.transition.default_transition);
                sharedElementEnterTransition.setDuration(FADE_DEFAULT_TIME);
                previousFragment.setSharedElementReturnTransition(sharedElementEnterTransition);
                nextFragment.setSharedElementEnterTransition(sharedElementEnterTransition);
                goNextFragment(nextFragment,true,false,ivTop);
                break;
        }
    }

    @Override
    public void displayProfileImage(String url) {
//        Picasso.with(getActivity()).load(url).into(civProfileImage);
    }


    @Override
    public void displayListRoom() {

    }

    @Override
    public void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyDataInsert(int position) {
        adapter.notifyItemInserted(position);
    }

    @Override
    public void displayUserDisplayName(String displayName) {
//        tvUserDisplayName.setText(displayName);
    }

    @Override
    public void onChatMessageItemClicked(Object o, int position, View view) {
        Fragment nextFragment = FragmentRoom.newInstance((RoomListResponse.Room)o, position);
        Fragment previousFragment = getFragmentManager().findFragmentById(R.id.container_framelayout);

        Transition sharedElementEnterTransition = TransitionInflater.from(getContext()).inflateTransition(R.transition.default_transition);
        sharedElementEnterTransition.setDuration(FADE_DEFAULT_TIME);

        previousFragment.setSharedElementReturnTransition(sharedElementEnterTransition);
        nextFragment.setSharedElementEnterTransition(sharedElementEnterTransition);

        ArrayList<View> views  = new ArrayList<>();
        views.add(view);
        views.add(ivTop);

        goNextFragment(nextFragment,true,true,views);
    }

    @Override
    public void onCallItemClicked(String uid) {
        ((HomeActivity)getBaseActivity()).makeCall(uid);
    }
}
