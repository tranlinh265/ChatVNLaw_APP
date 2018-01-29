package com.lkbcteam.tranlinh.chatvnlaw.model.action;


import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.FragmentChatContent;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;
import com.lkbcteam.tranlinh.chatvnlaw.model.Room;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class RedirectToRoomChat implements View.OnClickListener {
    private Room mRoom;
    private BaseFragment mBaseFragment;
    public RedirectToRoomChat(BaseFragment baseFragment,Room room){
        this.mRoom = room;
        this.mBaseFragment = baseFragment;
    }
    @Override
    public void onClick(View view) {
        mBaseFragment.goNextFragment(FragmentChatContent.newInstance(mRoom),true);
    }
}
