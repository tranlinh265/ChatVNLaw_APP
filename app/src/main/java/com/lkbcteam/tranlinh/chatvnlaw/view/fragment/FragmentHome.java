package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.RoomListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
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
    private List<Room> roomList;
    private RecyclerView.LayoutManager layoutManager;
    private RoomListAdapter adapter;
    private ImageButton ibtnHomeMenu;
    private HomePresenter homePresenter;

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
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

        ibtnHomeMenu = view.findViewById(R.id.ibtn_home_menu);
        ibtnHomeMenu.setOnClickListener(this);

    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        homePresenter = new HomePresenter(this,roomList);
        homePresenter.loadRoomList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(0),true,false);
                break;
        }
    }

    @Override
    public void displayListRoom() {

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
    public void onChatMessageItemClicked(Object o, int position, View view) {
        Fragment nextFragment = FragmentRoom.newInstance((Room)o, position);
        Fragment previousFragment = getFragmentManager().findFragmentById(R.id.container_framelayout);

        // 1. Exit for Previous Fragment
        Slide exitFade = new Slide(Gravity.LEFT);
        exitFade.setDuration(FADE_DEFAULT_TIME);
        previousFragment.setExitTransition(exitFade);

        Transition sharedElementEnterTransition = TransitionInflater.from(getContext()).inflateTransition(R.transition.default_transition);
        sharedElementEnterTransition.setDuration(FADE_DEFAULT_TIME);

        previousFragment.setSharedElementReturnTransition(sharedElementEnterTransition);
        nextFragment.setSharedElementEnterTransition(sharedElementEnterTransition);

        // 3. Enter Transition for New Fragment
        Slide slideTransition = new Slide(Gravity.RIGHT);

        slideTransition.setDuration(FADE_DEFAULT_TIME);
        nextFragment.setEnterTransition(slideTransition);

        goNextFragment(nextFragment,true,view);
    }

    @Override
    public void onCallItemClicked(String uid) {
        ((HomeActivity)getBaseActivity()).makeCall(uid);
    }
}
