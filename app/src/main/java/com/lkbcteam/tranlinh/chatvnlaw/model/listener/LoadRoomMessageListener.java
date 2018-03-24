package com.lkbcteam.tranlinh.chatvnlaw.model.listener;

import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;

import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public interface LoadRoomMessageListener {
    interface HistoryMessage{
        void onLoadSuccess(List<Message> historyList);
        void onLoadFalure(String error);
    }
    interface IncomingMessage{
        void onLoadSuccess(Message message);
        void onLoadFalure(String error);
    }
}
