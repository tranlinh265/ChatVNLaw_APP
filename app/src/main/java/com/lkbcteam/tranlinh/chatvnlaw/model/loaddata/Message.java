package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ChatContentAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.FileMessage;
import com.lkbcteam.tranlinh.chatvnlaw.model.ImageMessage;
import com.lkbcteam.tranlinh.chatvnlaw.model.Room;
import com.lkbcteam.tranlinh.chatvnlaw.model.TextMessage;
import com.lkbcteam.tranlinh.chatvnlaw.model.TimeStamp;
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
    private List<com.lkbcteam.tranlinh.chatvnlaw.model.Message> mMessageList;
    private FirebaseUser mCurrentUser;

    public Message(BaseFragment baseFragment, Context context, Room room,FirebaseUser currentUser, ChatContentAdapter adapter, List<com.lkbcteam.tranlinh.chatvnlaw.model.Message> messageList){
        mBaseFragment = baseFragment;
        mContext = context;
        mRoom = room;
        mMessageList = messageList;
        mAdapter = adapter;
        mCurrentUser = currentUser;
    }
    public void sendMessage(String chatContent){
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(chatContent);
        textMessage.setSenderUid(mCurrentUser.getUid());
        textMessage.setMsgTimeStamp((new TimeStamp()).getCurrentTime());
        database.getReference().child("rooms/"+mRoom.getRid()+"/messages").push().setValue(textMessage);
    }

    public void loadData(String timeStamp, final OnDataLoadingFinish callback){
        database.getReference().child(Define.Table.TABLE_ROOMS).child(mRoom.getRid()).child(Define.Room.MESSAGES).orderByChild(Define.Messages.TIMESTAMP).endAt(timeStamp).limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.Message.Info info = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.Message.Info.class);
                com.lkbcteam.tranlinh.chatvnlaw.model.Message message = new com.lkbcteam.tranlinh.chatvnlaw.model.Message();
                message.setmMessageInfo(info);
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

    public void streamMessage(String timeStamp, final OnDataLoadingFinish callback){
        database.getReference().child(Define.Table.TABLE_ROOMS).child(mRoom.getRid()).child(Define.Room.MESSAGES).orderByChild(Define.Messages.TIMESTAMP).startAt(timeStamp).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.Message.Info info = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.Message.Info.class);
                com.lkbcteam.tranlinh.chatvnlaw.model.Message message = new com.lkbcteam.tranlinh.chatvnlaw.model.Message();
                message.setmMessageInfo(info);
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
