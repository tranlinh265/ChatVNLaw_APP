package com.lkbcteam.tranlinh.chatvnlaw.view;

/**
 * Created by tranlinh on 26/03/2018.
 */

public interface RoomInfoView {
    void notifyWhenImageListChange(boolean left,int position);
    void notifyWhenFileListChange(int position);
    void showErrorMessage(String error);
}
