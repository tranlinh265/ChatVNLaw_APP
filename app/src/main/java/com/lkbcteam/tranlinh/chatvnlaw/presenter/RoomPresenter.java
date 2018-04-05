package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.HistoryMessageInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.IncomingMessageInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class RoomPresenter implements HistoryMessageInterator.HistoryMessage, IncomingMessageInterator.IncomingMessage {
    private static int LIMIT_MESSAGE = 10;

    private IncomingMessageInterator incomingMessageInterator;
    private HistoryMessageInterator historyMessageInterator;
    private RoomView roomView;
    private List<Message> messageList;

    private boolean scrollToLast = false;

    public RoomPresenter(RoomView roomView, List<Message> messageList){
        this.roomView = roomView;
        this.messageList = messageList;
        historyMessageInterator = new HistoryMessageInterator(this);
        incomingMessageInterator = new IncomingMessageInterator(this);
    }

    public void loadHistoryMessage(String roomId){
        scrollToLast = true;
        long time = System.currentTimeMillis();
        historyMessageInterator.loadHistory(roomId,String.valueOf(time),LIMIT_MESSAGE);
    }
    public void loadHistoryMessage(String roomId,String timeStamp){
        scrollToLast = false;
        historyMessageInterator.loadHistory(roomId,timeStamp,LIMIT_MESSAGE);
    }

    public void loadIncomingMessage(String roomId){
        long time = System.currentTimeMillis();
        incomingMessageInterator.loadIncomingMessage(roomId,String.valueOf(time));
    }

    public void sendMessage(String roomId, String chatContent){
        incomingMessageInterator.addNewMessage(roomId,chatContent);
    }

    private long toDay(String timeStampInMili){
        return TimeUnit.MILLISECONDS.toDays(Long.parseLong(timeStampInMili));
    }

    @Override
    public void onLoadSuccess(List<Message> historyList) {
        if(historyList.size() == 0) {
            roomView.notifyListMessage(scrollToLast);
            return;
        }
        if(messageList.size() == 0){
            messageList.addAll(historyList);
            roomView.notifyListMessage(scrollToLast);
        }else{
            Message lastItem = historyList.get(historyList.size() -1);
            Message fistItem = messageList.get(0);
            if(toDay(lastItem.getmMessageInfo().getMsgTimeStamp()) == toDay(fistItem.getmMessageInfo().getMsgTimeStamp())){
                messageList.remove(0);
            }
            for(int i = 0; i < historyList.size(); i++){
                Message tmp = historyList.get(i);
                messageList.add(i,tmp);
                roomView.notifyMessageAdded(i);
            }
        }
    }

    @Override
    public void onLoadSuccess(Message message) {

        if(messageList.size() == 0){
            Message timer = new Message();
            timer.setmMessageInfo(message.getmMessageInfo());
            messageList.add(timer);
            roomView.notifyMessageAdded(0);
        }else{
            Message lastItem = messageList.get(messageList.size() -1);
            if(toDay(lastItem.getmMessageInfo().getMsgTimeStamp()) <
                    toDay(message.getmMessageInfo().getMsgTimeStamp())){
                Message timer = new Message();
                timer.setmMessageInfo(message.getmMessageInfo());
                messageList.add(timer);
                roomView.notifyMessageAdded(messageList.size() -1);
            }
        }
        messageList.add(message);
        roomView.notifyMessageAdded(messageList.size() -1);
    }

    @Override
    public void onLoadFalure(String error) {
        roomView.showError(error);
    }

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public static interface RoomView {
        void notifyMessageAdded(int position);
        void notifyListMessage(boolean scrollToLast);
        void showError(String error);
    }
}
