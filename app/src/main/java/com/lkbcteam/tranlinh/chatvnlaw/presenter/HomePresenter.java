package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.RoomListInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;

import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class HomePresenter implements RoomListInterator.LoadRoomListListener {

    private HomeView homeView;
    private RoomListInterator roomListInterator;
    private List<Room> roomList;

    public HomePresenter(HomeView homeView, List<Room> roomList){
        this.homeView = homeView;
        this.roomList = roomList;
        roomListInterator = new RoomListInterator(this);
    }

    public void loadRoomList(){
        roomListInterator.getRoomList();
    }

    @Override
    public void onLoadRoomListSuccess(Room room) {
        roomList.add(room);
        homeView.notifyDataInsert(roomList.size() -1);
    }

    @Override
    public void onLoadRoomListFalure(String error) {
        homeView.displayError(error);
    }

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public static interface HomeView {
        void displayListRoom();
        void displayError(String error);
        void notifyDataInsert(int position);
    }
}
