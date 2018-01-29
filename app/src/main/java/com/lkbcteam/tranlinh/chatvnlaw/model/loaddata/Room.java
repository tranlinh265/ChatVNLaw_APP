package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.RoomListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class Room {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private BaseFragment mBaseFragment;
    private Context mContext;
    private FirebaseUser mCurrentUser;
    private List<com.lkbcteam.tranlinh.chatvnlaw.model.Room> mRoomList;
    private RoomListAdapter mAdapter;
    public Room(BaseFragment baseFragment, Context context, FirebaseUser currentUser, RoomListAdapter adapter, List<com.lkbcteam.tranlinh.chatvnlaw.model.Room> roomList){
        mBaseFragment = baseFragment;
        mContext = context;
        mCurrentUser =currentUser;
        mAdapter = adapter;
        mRoomList = roomList;

    }
    public void loadData(){
        database.getReference().child("reference/"+ mCurrentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.Room room = new com.lkbcteam.tranlinh.chatvnlaw.model.Room();
                room.setRid(String.valueOf(dataSnapshot.getValue()));

                User user = new User(mBaseFragment,mContext,mCurrentUser);
                user.getUser(true,dataSnapshot.getKey(),room, mAdapter);
                user.getUser(false,mCurrentUser.getUid(),room, mAdapter);

                mRoomList.add(room);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
