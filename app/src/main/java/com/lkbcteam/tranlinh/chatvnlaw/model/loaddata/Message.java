package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class Message {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private BaseFragment mBaseFragment;
    private Context mContext;
    public Message(BaseFragment baseFragment, Context context){
        mBaseFragment = baseFragment;
        mContext = context;
    }
    public void loadData(){
    }
}
