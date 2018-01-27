package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.MessageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.UnreadMessageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class FragmentMessageList extends BaseFragment {
    private List<Message> mMessageList = new ArrayList<Message>();
    private RecyclerView mRvMessageList, mRvUnreadMessageList;
    public static Fragment newInstance(){
        return new FragmentMessageList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages_list, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvMessageList = view.findViewById(R.id.rv_messages);
        mRvUnreadMessageList = view.findViewById(R.id.rv_unread_messages);
        Message message = new Message("dwdaw","dawd","daw");
        mMessageList.add(message);
        mMessageList.add(message);
        mMessageList.add(message);
        mMessageList.add(message);
        mMessageList.add(message);
        mMessageList.add(message);
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(getContext(),1);
        mRvMessageList.setLayoutManager(mLayout);
        MessageListAdapter adapter = new MessageListAdapter(getContext(), mMessageList);
        mRvMessageList.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRvUnreadMessageList.setLayoutManager(layoutManager);
        UnreadMessageListAdapter adapter1 = new UnreadMessageListAdapter(getContext(), mMessageList);
        mRvUnreadMessageList.setAdapter(adapter1);
    }
}
