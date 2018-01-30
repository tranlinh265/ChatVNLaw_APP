package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.annotation.SuppressLint;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.MainActivity;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ChatContentAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;
import com.lkbcteam.tranlinh.chatvnlaw.model.Room;


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
    private Button mBtnSend;
    private Room mRoom;
    private TextView mTvSenderDisplayName;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mCurrentUser;

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

    @Override
    public void onStart() {
        super.onStart();
        mCurrentUser = mAuth.getCurrentUser();
        if (mCurrentUser == null){
            getBaseActivity().startActivity(MainActivity.class,true);
        }
    }

    @SuppressLint("ValidFragment")
    public FragmentChatContent(Room room) {
        this.mRoom = room;
    }

    public static FragmentChatContent newInstance() {
        return new FragmentChatContent();
    }
    public static FragmentChatContent newInstance(Room room) {
        return new FragmentChatContent(room);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_content,container,false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurrentUser = mAuth.getCurrentUser();

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                initView(view);
            }
        }, 1000);
    }
    private void initView(View view){
        rvChatContentContainer = view.findViewById(R.id.rv_chat_content_container);

        mMessageList = new ArrayList<>();
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(getContext(),1);
        rvChatContentContainer.setLayoutManager(mLayout);
        ChatContentAdapter adapter = new ChatContentAdapter(getContext(), mMessageList, mHideSoftKey);
        rvChatContentContainer.setAdapter(adapter);

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
        mBtnSend = view.findViewById(R.id.btn_send);

        if(mRoom != null && mCurrentUser != null){

            mTvSenderDisplayName.setText(mRoom.getTargetUser().getDisplayName());
            final com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.Message message = new com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.Message(this,getContext(),
                    mRoom, mCurrentUser, adapter, mMessageList);
            message.loadData();
            mBtnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mEdtChatInput.getText().toString() != null){
                        message.sendMessage(mEdtChatInput.getText().toString());
                        mEdtChatInput.setText(null);

                    }
                }
            });
        }
    }
}
