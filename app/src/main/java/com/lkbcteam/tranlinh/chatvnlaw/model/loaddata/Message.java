package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ChatContentAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.Room;
import com.lkbcteam.tranlinh.chatvnlaw.model.TextMessage;
import com.lkbcteam.tranlinh.chatvnlaw.model.TimeStamp;

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
    public void loadData(){
        database.getReference().child("rooms/"+ mRoom.getRid()+"/messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TextMessage textMessage = dataSnapshot.getValue(TextMessage.class);
                com.lkbcteam.tranlinh.chatvnlaw.model.Message message = new com.lkbcteam.tranlinh.chatvnlaw.model.Message();
                message.setmTextMessage(textMessage);
                if(textMessage.getSenderUid().equals(mCurrentUser.getUid())){
                    message.setmSenderUser(mRoom.getCurrentUser());
                    message.setmTargetUser(mRoom.getTargetUser());
                    message.setIsCurrentUser(true);
                }else{
                    message.setmSenderUser(mRoom.getTargetUser());
                    message.setmTargetUser(mRoom.getCurrentUser());
                    message.setIsCurrentUser(false);
                }
                message.getmTextMessage().setMsgTimeStamp();
                mMessageList.add(message);
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
