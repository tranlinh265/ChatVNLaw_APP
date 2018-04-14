package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.RoomListInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.UserInfoInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.UserInfoResponse;

import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class HomePresenter implements RoomListInterator.LoadRoomListListener, UserInfoInterator.loadDataCallBack {

    private HomeView homeView;
    private RoomListInterator roomListInterator;
    private List<RoomListResponse.Room> roomList;
    private UserInfoInterator userInfoInterator;

    public HomePresenter(HomeView homeView, List<RoomListResponse.Room> roomList){
        this.homeView = homeView;
        this.roomList = roomList;
        roomListInterator = new RoomListInterator(this);
        userInfoInterator = new UserInfoInterator(this);
    }

    public void loadRoomListFromFirebase(){
        roomListInterator.getRoomList();
    }

    public void loadRoomListFromRail(String userToken){
        roomListInterator.getRoomListFromRail(userToken);
    }
    public void loadUserInfo(String username){
        userInfoInterator.loadUserInfoFromRails(username);
    }
    @Override
    public void onLoadRoomListFromRailSuccess(List<RoomListResponse.Room> rooms) {

        this.roomList.clear();
        this.roomList.addAll(rooms);
        homeView.notifyDataChanged();
    }

    @Override
    public void onLoadRoomListFromRailFalure(String error) {
        homeView.displayError(error);
    }

    @Override
    public void onLoadRoomListSuccess(Room room) {
//        roomList.add(room);
//        homeView.notifyDataInsert(roomList.size() -1);
    }

    @Override
    public void onLoadRoomListFalure(String error) {
        homeView.displayError(error);
    }

    @Override
    public void onLoadSuccess(UserInfoResponse response) {
        homeView.displayProfileImage(response.getUserInfo().getProfile().getAvatar().getThumbSmall().getRealUrl());
        homeView.displayUserDisplayName(response.getUserInfo().getProfile().getDisplayName());
    }

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public interface HomeView {
        void displayProfileImage(String url);
        void displayListRoom();
        void notifyDataChanged();
        void displayError(String error);
        void notifyDataInsert(int position);
        void displayUserDisplayName(String displayName);
    }
}
