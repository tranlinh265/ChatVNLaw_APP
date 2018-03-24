package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.model.FirebaseLawyer;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.*;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.TextMessage;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.TimeStamp;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataChange;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;

/**
 * Created by tranlinh on 22/03/2018.
 */

public class FirebaseData {

    public static void getRoomList(final OnDataLoadingFinish callback, final OnDataChange onDataChange){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        database.getReference().child(Define.Table.TABLE_REFERENCE+"/"+ mCurrentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Room room = new Room();
                room.setRid(String.valueOf(dataSnapshot.getValue()));

                FirebaseData.getUser(dataSnapshot.getKey(), new OnDataLoadingFinish() {
                    @Override
                    public void onSuccess(Object o) {
                        com.lkbcteam.tranlinh.chatvnlaw.model.User user = (com.lkbcteam.tranlinh.chatvnlaw.model.User)o;
                        room.setTargetUser(user);
                        onDataChange.onDataChange();
                    }

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onDataNotExist() {

                    }
                });

                FirebaseData.getUser(mCurrentUser.getUid(), new OnDataLoadingFinish() {
                    @Override
                    public void onSuccess(Object o) {
                        com.lkbcteam.tranlinh.chatvnlaw.model.User user = (com.lkbcteam.tranlinh.chatvnlaw.model.User)o;
                        room.setCurrentUser(user);
                        onDataChange.onDataChange();
                    }

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onDataNotExist() {

                    }
                });
                UnreadMessage.getUnreadMessage(String.valueOf(dataSnapshot.getValue()), new OnDataLoadingFinish() {
                    @Override
                    public void onSuccess(Object o) {
                        long count = (long) ((DataSnapshot)o).child(Define.UnreadMessage.COUNT).getValue();
                        if(count > 0){
                            String content =(String) ((DataSnapshot)o).child(Define.UnreadMessage.LASTMESSAGE).child(Define.UnreadMessage.CONTENT).getValue();
                            room.setLastMessContent(content);
                            onDataChange.onDataChange();
                        }
                    }

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onDataNotExist() {

                    }
                });
                callback.onSuccess(room);
                onDataChange.onDataChange();
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
    public static void getUser(String userId, final OnDataLoadingFinish callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child(Define.Table.TABLE_USERS+"/" + userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                com.lkbcteam.tranlinh.chatvnlaw.model.User user = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.User.class);
                user.setUid(dataSnapshot.getKey());
                callback.onSuccess(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onFail();
            }
        });
    }
    public static void getLawyerInfo(FirebaseUser firebaseUser, ValueEventListener callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child(Define.Table.TABLE_LAWYERS).child(firebaseUser.getUid()).addListenerForSingleValueEvent(callback);
    }
    public static void updateLawyerInfo(FirebaseUser firebaseUser, FirebaseLawyer lawyer){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child(Define.Table.TABLE_LAWYERS).child(firebaseUser.getUid()).setValue(lawyer);
    }
    public static void sendMessage(String chatContent, String roomid){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        TextMessage textMessage = new TextMessage();
        textMessage.setContent(chatContent);
        textMessage.setSenderUid(currentUser.getUid());
        textMessage.setMsgTimeStamp((new TimeStamp()).getCurrentTime());

        database.getReference().child(Define.Table.TABLE_ROOMS+"/"+roomid+"/"+ Define.Room.MESSAGES).push().setValue(textMessage);
    }
    public static void getMessageHistory(final Room room, String timeStamp, final OnDataLoadingFinish callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        database.getReference().child(Define.Table.TABLE_ROOMS).child(room.getRid()).child(Define.Room.MESSAGES).orderByChild(Define.Messages.TIMESTAMP).endAt(timeStamp).limitToLast(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message.Info info = dataSnapshot.getValue(Message.Info.class);
                Message message = new Message();
                message.setmMessageInfo(info);
                message.setmMessageId(dataSnapshot.getKey());
                if(info.getSenderUid().equals(currentUser.getUid())){
                    message.setmSenderUser(room.getCurrentUser());
                    message.setmTargetUser(room.getTargetUser());
                    message.setIsCurrentUser(true);
                }else{
                    message.setmSenderUser(room.getTargetUser());
                    message.setmTargetUser(room.getCurrentUser());
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

    public void streamMessage(final Room room, String timeStamp, final OnDataLoadingFinish callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        database.getReference().child(Define.Table.TABLE_ROOMS).child(room.getRid()).child(Define.Room.MESSAGES).orderByChild(Define.Messages.TIMESTAMP).startAt(timeStamp).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message.Info info = dataSnapshot.getValue(Message.Info.class);
                Message message = new Message();
                message.setmMessageInfo(info);
                message.setmMessageId(dataSnapshot.getKey());
                if(info.getSenderUid().equals(currentUser.getUid())){
                    message.setmSenderUser(room.getCurrentUser());
                    message.setmTargetUser(room.getTargetUser());
                    message.setIsCurrentUser(true);
                }else{
                    message.setmSenderUser(room.getTargetUser());
                    message.setmTargetUser(room.getCurrentUser());
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
