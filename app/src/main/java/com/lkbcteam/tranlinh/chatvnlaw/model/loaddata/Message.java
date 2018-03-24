package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ChatContentAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;

import java.util.List;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class Message {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private BaseFragment mBaseFragment;
    private Context mContext;
    private Room mRoom;
    private ChatContentAdapter mAdapter;
    private List<com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message> mMessageList;
    private FirebaseUser mCurrentUser;

    public Message(BaseFragment baseFragment, Context context, Room room,FirebaseUser currentUser, ChatContentAdapter adapter, List<com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message> messageList){
        mBaseFragment = baseFragment;
        mContext = context;
        mRoom = room;
        mMessageList = messageList;
        mAdapter = adapter;
        mCurrentUser = currentUser;
    }


    public void streamMessage(String timeStamp, final OnDataLoadingFinish callback){
        database.getReference().child(Define.Table.TABLE_ROOMS).child(mRoom.getRid()).child(Define.Room.MESSAGES).orderByChild(Define.Messages.TIMESTAMP).startAt(timeStamp).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message.Info info = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message.Info.class);
                com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message message = new com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message();
                message.setmMessageInfo(info);
                message.setmMessageId(dataSnapshot.getKey());
                if(info.getSenderUid().equals(mCurrentUser.getUid())){
                    message.setmSenderUser(mRoom.getCurrentUser());
                    message.setmTargetUser(mRoom.getTargetUser());
                    message.setIsCurrentUser(true);
                }else{
                    message.setmSenderUser(mRoom.getTargetUser());
                    message.setmTargetUser(mRoom.getCurrentUser());
                    message.setIsCurrentUser(false);
                }
                callback.onSuccess(message);
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
