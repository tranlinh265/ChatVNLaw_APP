package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ChatContentAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class FragmentChatContent extends BaseFragment {
    private RecyclerView rvChatContentContainer;
    private RelativeLayout mLayoutContainer;
    private List<Message> mMessageList = new ArrayList<>();
    private EditText mEdtChatInput;
    private ImageButton mIbtnBack;
    private Message mMessage;
    private TextView mTvSenderDisplayName;

    private View.OnClickListener mHideSoftKey = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View currentFocus = getActivity().getCurrentFocus();
            if (currentFocus != null) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
            getBaseActivity().hide();
        }
    };

    public FragmentChatContent(){

    }
    @SuppressLint("ValidFragment")
    public FragmentChatContent(Message message) {
        this.mMessage = message;
    }

    public static FragmentChatContent newInstance() {
        return new FragmentChatContent();
    }
    public static FragmentChatContent newInstance(Message message) {
        return new FragmentChatContent(message);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_content,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvChatContentContainer = view.findViewById(R.id.rv_chat_content_container);
        Message message = new Message("dwdaw","dawd","daw");
        Message message1 = new Message("dwdaw","dawd","daw",true);

        mMessageList.add(message1);
        mMessageList.add(message);
        mMessageList.add(message);
        mMessageList.add(message1);
        mMessageList.add(message);
        mMessageList.add(message1);
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(getContext(),1);
        rvChatContentContainer.setLayoutManager(mLayout);

        ChatContentAdapter adapter = new ChatContentAdapter(getContext(), mMessageList, mHideSoftKey);
        rvChatContentContainer.setAdapter(adapter);

        rvChatContentContainer.scrollToPosition(adapter.getItemCount() - 1);

        mEdtChatInput = view.findViewById(R.id.edt_chat_input);
        mLayoutContainer = view.findViewById(R.id.layout_container);
        mIbtnBack = view.findViewById(R.id.ibtn_back);
        mIbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackFragment();
            }
        });
        mTvSenderDisplayName = view.findViewById(R.id.tv_sender_displayname);
        if(mMessage != null){
            mTvSenderDisplayName.setText(mMessage.getsenderDisplayName());
        }
    }

}
