package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import android.util.Log;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.RoomListInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;

import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class HomePresenter implements RoomListInterator.LoadRoomListListener {

    private HomeView homeView;
    private RoomListInterator roomListInterator;
    private List<RoomListResponse.Room> roomList;

    public HomePresenter(HomeView homeView, List<RoomListResponse.Room> roomList){
        this.homeView = homeView;
        this.roomList = roomList;
        roomListInterator = new RoomListInterator(this);
    }

    public void loadRoomListFromFirebase(){
        roomListInterator.getRoomList();
    }

    public void loadRoomListFromRail(String userToken){
        roomListInterator.getRoomListFromRail(userToken);
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

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public interface HomeView {
        void displayListRoom();
        void notifyDataChanged();
        void displayError(String error);
        void notifyDataInsert(int position);
    }
}
