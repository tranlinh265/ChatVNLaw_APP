package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.annotation.SuppressLint;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.lkbcteam.tranlinh.chatvnlaw.model.Time;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class FragmentRoom extends BaseFragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView rvChatContentContainer;
    private RelativeLayout mLayoutContainer;
    private List<Message> mMessageList = new ArrayList<>();
    private EditText mEdtChatInput;
    private ImageButton mIbtnBack,mIbtnInfo;
    private Button mBtnSend;
    private Room mRoom;
    private TextView mTvSenderDisplayName;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mCurrentUser;
    private RecyclerView.LayoutManager mLayout;
    private SwipeRefreshLayout srlMessageListContainer;
    private com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.Message message = null;
    private ChatContentAdapter adapter;
    private View.OnClickListener mHideSoftKey = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View currentFocus = getActivity().getCurrentFocus();
            if (currentFocus != null) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        mCurrentUser = mAuth.getCurrentUser();
        if (mCurrentUser == null){
            getBaseActivity().startActivity(MainActivity.class,true);
        }
    }

    public static FragmentRoom newInstance(Room room) {
        FragmentRoom fragmentRoom = new FragmentRoom();
        fragmentRoom.setmRoom(room);
        return fragmentRoom;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room,container,false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurrentUser = mAuth.getCurrentUser();

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                initViewChild(view);
            }
        }, 1000);
    }
    private void initViewChild(View view){
        rvChatContentContainer = view.findViewById(R.id.rv_chat_content_container);
        srlMessageListContainer = view.findViewById(R.id.srl_list_message_container);

        mMessageList = new ArrayList<>();
        mLayout = new GridLayoutManager(getContext(),1);

        rvChatContentContainer.setLayoutManager(mLayout);
        adapter = new ChatContentAdapter(getContext(),this, mMessageList, mHideSoftKey);
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

        mIbtnInfo = view.findViewById(R.id.ibtn_info);
        mIbtnInfo.setOnClickListener(this);
        srlMessageListContainer.setOnRefreshListener(this);
        if(mRoom != null && mCurrentUser != null){

            mTvSenderDisplayName.setText(mRoom.getTargetUser().getDisplayName());
            message = new com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.Message(this,getContext(),
                    mRoom, mCurrentUser, adapter, mMessageList);
            long time = System.currentTimeMillis();
            message.loadData(String.valueOf(time),new OnDataLoadingFinish() {
                @Override
                public void onSuccess(Object o) {
                    if(mMessageList.size() == 0){
                        Message tmp = new Message();
                        tmp.setmMessageInfo(((Message)o).getmMessageInfo());
                        mMessageList.add(tmp);
                    }else{
                        Message lastItem = mMessageList.get(mMessageList.size() -1);
                        if(TimeUnit.MILLISECONDS.toDays(Long.parseLong(lastItem.getmMessageInfo().getMsgTimeStamp())) <
                                TimeUnit.MILLISECONDS.toDays(Long.parseLong(((Message)o).getmMessageInfo().getMsgTimeStamp()))) {
                            Message tmp = new Message();
                            tmp.setmMessageInfo(((Message) o).getmMessageInfo());
                            mMessageList.add(tmp);
                        }
                    }
                    mMessageList.add((Message) o);
                    adapter.notifyDataSetChanged();
                    mLayout.scrollToPosition(mMessageList.size() - 1);
                }

                @Override
                public void onFail() {

                }
            });
            message.streamMessage(String.valueOf(time), new OnDataLoadingFinish() {
                @Override
                public void onSuccess(Object o) {
                    mMessageList.add((Message)o);
                    adapter.notifyItemInserted(mMessageList.size()-1);
                }

                @Override
                public void onFail() {

                }
            });
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

    public void setmRoom(Room mRoom) {
        this.mRoom = mRoom;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_info:
                goNextFragment(FragmentRoomInfo.newInstance(mCurrentUser, mRoom),true);
                break;
        }
    }

    private long toDay(String timeStampInMili){
        return TimeUnit.MILLISECONDS.toDays(Long.parseLong(timeStampInMili));
    }
    private Message timeItem(Message baseItem){
        Message tmp = new Message();
        tmp.setmMessageInfo(baseItem.getmMessageInfo());
        return tmp;
    }
    @Override
    public void onRefresh() {
        if(message != null){
            String time = String.valueOf(System.currentTimeMillis());
            if(mMessageList.size() > 0) {
                time = String.valueOf(Long.parseLong(mMessageList.get(0).getmMessageInfo().getMsgTimeStamp()) - 1);
            }
            message.loadData(time, new OnDataLoadingFinish() {
                @Override
                public void onSuccess(Object o) {
                    Message message = (Message)o;
                    int i;
                    for ( i = 0; i < mMessageList.size() ; i++){
                        if (Long.parseLong(mMessageList.get(i).getmMessageInfo().getMsgTimeStamp()) > Long.parseLong(message.getmMessageInfo().getMsgTimeStamp())){
                            break;
                        }
                    }
                    if (i == 0) {
                        if(toDay(mMessageList.get(i).getmMessageInfo().getMsgTimeStamp()) >
                                toDay(message.getmMessageInfo().getMsgTimeStamp())){
                            mMessageList.add(i,timeItem((Message) o));
                            adapter.notifyItemInserted(i);
                            i++;
                        }
                    }else{

                        i--;
                        if(i == 0){
                            if(toDay(mMessageList.get(i).getmMessageInfo().getMsgTimeStamp()) >
                                    toDay(message.getmMessageInfo().getMsgTimeStamp())){
                                mMessageList.add(i,timeItem((Message) o));
                                adapter.notifyItemInserted(i);
                                i++;
                            }
                        }else {
                            if (i == mMessageList.size() - 1) {
                                if (toDay(mMessageList.get(i).getmMessageInfo().getMsgTimeStamp()) <
                                        toDay(message.getmMessageInfo().getMsgTimeStamp())) {
                                    mMessageList.add(i,timeItem((Message) o));
                                    adapter.notifyItemInserted(i);
                                    i++;
                                }
                            } else {
                                if (toDay(mMessageList.get(i).getmMessageInfo().getMsgTimeStamp()) <
                                        toDay(message.getmMessageInfo().getMsgTimeStamp()) &&
                                        toDay(mMessageList.get(i + 1).getmMessageInfo().getMsgTimeStamp()) >
                                                toDay(message.getmMessageInfo().getMsgTimeStamp())) {
                                    mMessageList.add(i,timeItem((Message) o));
                                    adapter.notifyItemInserted(i);
                                    i++;
                                }
                            }
                        }
                    }
                    mMessageList.add(i,message);
                    adapter.notifyItemInserted(i);
                    srlMessageListContainer.setRefreshing(false);
                }

                @Override
                public void onFail() {

                }
            });
        }
    }
}
