package com.lkbcteam.tranlinh.chatvnlaw.model.action;


import android.util.Log;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.FragmentRoom;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;

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
        Log.e("123", "onClick: " );
        mBaseFragment.goNextFragment(FragmentRoom.newInstance(mRoom),true);
    }
}
