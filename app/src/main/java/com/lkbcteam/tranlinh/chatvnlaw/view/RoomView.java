package com.lkbcteam.tranlinh.chatvnlaw.view;

/**
 * Created by tranlinh on 24/03/2018.
 */

public interface RoomView {
    void notifyMessageAdded(int position);
    void notifyListMessage(boolean scrollToLast);
    void showError(String error);
}
