package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.RoomListInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.UserInfoInterator;
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
        homeView.displayNumberOfListRoom(rooms.size());
    }

    @Override
    public void onLoadRoomListFromRailFalure(String error) {
        homeView.displayError(error);
    }

    @Override
    public void notifyDataChange(RoomListResponse.Room room, int position) {
        if (roomList.get(position).getId().equals(room.getId())){
            roomList.get(position).setLastMessage(room.getLastMessage());
            homeView.notifyDataChanged();
        }
//        homeView.notifyDataChanged();
    }

    @Override
    public void onLoadSuccess(UserInfoResponse response) {
        homeView.displayProfileImage(response.getUserInfo().getProfile().getAvatar().getThumbSmall().getUrl());
        homeView.displayUserDisplayName(response.getUserInfo().getProfile().getDisplayName());
    }

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public interface HomeView {
        void displayProfileImage(String url);
        void displayNumberOfListRoom(int count);
        void notifyDataChanged();
        void displayError(String error);
        void notifyDataInsert(int position);
        void displayUserDisplayName(String displayName);
    }
}
