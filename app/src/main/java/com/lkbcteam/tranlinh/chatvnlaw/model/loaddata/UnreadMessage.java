package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class UnreadMessage {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private BaseFragment mBaseFragment;
    private Context mContext;
    public UnreadMessage(BaseFragment baseFragment, Context context){
        mBaseFragment = baseFragment;
        mContext = context;
    }
    public void loadData(){
    }
    public static void getUnreadMessage(String roomid, final OnDataLoadingFinish callback){
        Log.e(roomid, "getUnreadMessage: " );
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child(Define.Table.TABLE_ROOMS).child(roomid).child(Define.Room.UN_READ_MESSAGE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
