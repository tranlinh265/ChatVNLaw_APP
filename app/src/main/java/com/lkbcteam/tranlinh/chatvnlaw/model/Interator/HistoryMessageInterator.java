package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class HistoryMessageInterator {
    private HistoryMessage historyMessageListener;
    private FirebaseDatabase database;
    private FirebaseUser currentUser;

    public HistoryMessageInterator(HistoryMessage historyMessageListener){
        this.historyMessageListener = historyMessageListener;
        database = FirebaseDatabase.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void loadHistory(String roomId, String timeStamp, int limit){
         database.getReference().child(Define.Table.TABLE_ROOMS).child(roomId).child(Define.Room.MESSAGES)
                .orderByChild(Define.Messages.TIMESTAMP).endAt(timeStamp).limitToLast(limit)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Message> historyList = new ArrayList<>();
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            Message.Info info = ds.getValue(Message.Info.class);
                            Message message = new Message();
                            message.setmMessageInfo(info);
                            message.setmMessageId(ds.getKey());
                            if(info.getSenderUid().equals(currentUser.getUid())){
                                message.setIsCurrentUser(true);
                            }else{
                                message.setIsCurrentUser(false);
                            }
                            if(historyList.size() == 0){
                                Message timer = new Message();
                                timer.setmMessageInfo(message.getmMessageInfo());
                                historyList.add(timer);
                            }else{
                                Message lastItem = historyList.get(historyList.size() - 1);
                                if(TimeUnit.MILLISECONDS.toDays(Long.parseLong(lastItem.getmMessageInfo().getMsgTimeStamp())) <
                                        TimeUnit.MILLISECONDS.toDays(Long.parseLong(message.getmMessageInfo().getMsgTimeStamp()))) {
                                    Message timer = new Message();
                                    timer.setmMessageInfo(message.getmMessageInfo());
                                    historyList.add(timer);
                                }
                            }
                            historyList.add(message);
                        }
                        historyMessageListener.onLoadSuccess(historyList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        historyMessageListener.onLoadFalure(databaseError.getMessage());
                    }
                });
    }

    public interface HistoryMessage{
        void onLoadSuccess(List<Message> historyList);
        void onLoadFalure(String error);
    }
}

