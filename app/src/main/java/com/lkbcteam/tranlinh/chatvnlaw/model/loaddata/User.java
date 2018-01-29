package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.*;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class User {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private BaseFragment mBaseFragment;
    private Context mContext;
    private FirebaseUser mCurrentUser;
    public User(BaseFragment baseFragment, Context context,  FirebaseUser currentUser){
        mBaseFragment = baseFragment;
        mContext = context;
        mCurrentUser =currentUser;
    }
    public void loadData() {
        database.getReference().child("users/" + mCurrentUser.getUid()).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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
    public void getUser(String uid, final com.lkbcteam.tranlinh.chatvnlaw.model.Room roominfo){
        database.getReference().child("users/" + uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                com.lkbcteam.tranlinh.chatvnlaw.model.User user = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.User.class);
                user.setUid(dataSnapshot.getKey());
                roominfo.setReceiver(user);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
