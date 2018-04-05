package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ChatContentAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.*;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.RoomPresenter;
import com.lkbcteam.tranlinh.chatvnlaw.view.RoomView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class FragmentRoom extends BaseFragment implements RoomView,View.OnClickListener  {
    private RecyclerView rvChatContentContainer;
    private List<Message> mMessageList;
    private Room room;
    private EditText mEdtChatInput;
    private Button mBtnSend;
    private RoomPresenter roomPresenter;
    private ChatContentAdapter adapter;
    private RecyclerView.LayoutManager mLayout;
    private ImageButton mIbtnBack,mIbtnInfo;
    private boolean isloading = true;
    private ProgressBar pbLoading;

    private View.OnClickListener mHideSoftKey = view -> {
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    };

    public static FragmentRoom newInstance(Room room) {
        FragmentRoom fragment = new FragmentRoom();
        fragment.setRoom(room);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room,container,false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

//        Slide slide = new Slide();
//        slide.setDuration(1000);
//        getBaseActivity().getWindow().setEnterTransition(slide);

        mMessageList = new ArrayList<>();
        rvChatContentContainer = view.findViewById(R.id.rv_chat_content_container);
        mLayout = new GridLayoutManager(getContext(),1);
        rvChatContentContainer.setLayoutManager(mLayout);
        adapter = new ChatContentAdapter(getContext(),this, mMessageList, mHideSoftKey);
        rvChatContentContainer.setAdapter(adapter);
        mIbtnBack = view.findViewById(R.id.ibtn_back);
        mEdtChatInput = view.findViewById(R.id.edt_chat_input);
        mBtnSend = view.findViewById(R.id.btn_send);
        mIbtnInfo = view.findViewById(R.id.ibtn_info);
        mIbtnInfo.setOnClickListener(this);
        mIbtnBack.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        pbLoading = view.findViewById(R.id.pb_loading);

        rvChatContentContainer.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mMessageList.size() == 0) return;

                int positionView = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if(positionView == 0){
                    if(!isloading){
                        isloading = true;
                        long time = Long.parseLong(mMessageList.get(0).getmMessageInfo().getMsgTimeStamp()) - 1;
                        roomPresenter.loadHistoryMessage(room.getRid(), String.valueOf(time));
                    }
                }
            }
        });
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        roomPresenter = new RoomPresenter(this,mMessageList);
        (new Handler()).postDelayed(() -> {
            pbLoading.setVisibility(View.GONE);
            rvChatContentContainer.setVisibility(View.VISIBLE);
            roomPresenter.loadHistoryMessage(room.getRid());
            roomPresenter.loadIncomingMessage(room.getRid());
        },3000);

    }

    @Override
    public void notifyMessageAdded(int position) {
        adapter.notifyItemInserted(position);
    }

    @Override
    public void notifyListMessage(boolean scrollTolast) {
        adapter.notifyDataSetChanged();
        if(scrollTolast){
            mLayout.scrollToPosition(mMessageList.size() - 1);
        }
        isloading = false;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error, Toast.LENGTH_LONG).show();
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_back:
                goBackFragment();
                break;
            case R.id.btn_send:
                String chatContent = mEdtChatInput.getText().toString();
                if(chatContent != null){
                    roomPresenter.sendMessage(room.getRid(), chatContent);
                    mEdtChatInput.setText(null);
                }
                break;
            case R.id.ibtn_info:
                goNextFragment(FragmentRoomInfo.newInstance(room),true);
                break;
        }
    }
}
