package com.lkbcteam.tranlinh.chatvnlaw.model.listener;

import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;

/**
 * Created by tranlinh on 24/03/2018.
 */

public interface LoadRoomListListener {
    void onLoadRoomListSuccess(Room room);
    void onLoadRoomListFalure(String error);
}
