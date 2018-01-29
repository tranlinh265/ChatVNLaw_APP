package com.lkbcteam.tranlinh.chatvnlaw.model.action;


import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.FragmentChatContent;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class RedirectToRoomChat implements View.OnClickListener {
    private Message mMessage;
    private BaseFragment mBaseFragment;
    public RedirectToRoomChat(BaseFragment baseFragment,Message message){
        this.mMessage = message;
        this.mBaseFragment = baseFragment;
    }
    @Override
    public void onClick(View view) {
        mBaseFragment.goNextFragment(FragmentChatContent.newInstance(mMessage),true);
    }
}
