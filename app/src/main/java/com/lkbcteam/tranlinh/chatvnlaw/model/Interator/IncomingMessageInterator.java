package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.TextMessage;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.TimeStamp;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class IncomingMessageInterator {
    private IncomingMessage incomingMessage;
    private FirebaseUser currentUser;
    private FirebaseDatabase database;

    public IncomingMessageInterator(IncomingMessage incomingMessage){
        this.incomingMessage = incomingMessage;
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
    }

    public void addNewMessage(String roomId, String chatContent){
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(chatContent);
        textMessage.setSenderUid(currentUser.getUid());
        textMessage.setMsgTimeStamp((new TimeStamp()).getCurrentTime());
        database.getReference().child(Define.Table.TABLE_ROOMS).child(roomId).child(Define.Room.MESSAGES).push().setValue(textMessage);
    }

    public void loadIncomingMessage(String roomId, String timeStamp){
        database.getReference().child(Define.Table.TABLE_ROOMS).child(roomId).child(Define.Room.MESSAGES).orderByChild(Define.Messages.TIMESTAMP).startAt(timeStamp)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Message.Info info = dataSnapshot.getValue(Message.Info.class);
                        Message message = new Message();
                        message.setmMessageInfo(info);
                        message.setmMessageId(dataSnapshot.getKey());
                        if(message.getmMessageInfo().getSenderUid().equals(currentUser.getUid())){
                            message.setIsCurrentUser(true);
                        }else{
                            message.setIsCurrentUser(false);
                        }
                        incomingMessage.onLoadSuccess(message);
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
                        incomingMessage.onLoadFalure(databaseError.getMessage());
                    }
                });
    }

    public interface IncomingMessage{
        void onLoadSuccess(Message message);
        void onLoadFalure(String error);
    }
}
