package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.TodoItem;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;

/**
 * Created by tranlinh on 05/03/2018.
 */

public class TodoList {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private BaseFragment mBaseFragment;
    private Context mContext;
    private FirebaseUser mCurrentUser;

    public TodoList(BaseFragment baseFragment, Context context, FirebaseUser currentUser){
        this.mBaseFragment = baseFragment;
        this.mContext = context;
        this.mCurrentUser = currentUser;
    }

    public void loadTodoList(final OnDataLoadingFinish callback){

        database.getReference().child(Define.Table.TABLE_TASKS).child(mCurrentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    callback.onDataNotExist();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        database.getReference().child(Define.Table.TABLE_TASKS).child(mCurrentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TodoItem item = snapshot.getValue(TodoItem.class);
                        callback.onSuccess(item);
                    }
                }else{
                    callback.onFail();
                }

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
                Log.e(databaseError.getMessage(), "onCancelled: " );
                callback.onFail();
            }

        });
    }
}
