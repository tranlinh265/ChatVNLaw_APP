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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.activity.MainActivity;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.DownloadImageTask;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.MessageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.UnreadMessageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;
import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.Room;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class FragmentMessageList extends BaseFragment {
    private List<Message> mMessageList = new ArrayList<Message>();
    private RecyclerView mRvMessageList, mRvUnreadMessageList;
    private TextView mTvNumberOfUnread,mTvWelcomeUser;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mCurrentUser;
    private CircleImageView mCivHomeProfile;

    public static Fragment newInstance(){
        return new FragmentMessageList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages_list, container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mCurrentUser = mAuth.getCurrentUser();
        if (mCurrentUser == null){
            getBaseActivity().startActivity(MainActivity.class,true);
        }
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
        MessageListAdapter adapter = new MessageListAdapter(getContext(),this, mMessageList);
        mRvMessageList.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRvUnreadMessageList.setLayoutManager(layoutManager);
        UnreadMessageListAdapter adapter1 = new UnreadMessageListAdapter(getContext(),this, mMessageList);
        mRvUnreadMessageList.setAdapter(adapter1);
        mTvNumberOfUnread = view.findViewById(R.id.tv_number_of_unread);
        mTvNumberOfUnread.setText(String.valueOf(mMessageList.size()) + " New Messages");
        mTvWelcomeUser = view.findViewById(R.id.tv_welcome_user);
        mCurrentUser = mAuth.getCurrentUser();
        mCivHomeProfile = view.findViewById(R.id.civ_home_profile);

        if(mCurrentUser != null){
            mTvWelcomeUser.setText(mCurrentUser.getDisplayName());
            new DownloadImageTask((ImageView) mCivHomeProfile).execute(String.valueOf(mCurrentUser.getPhotoUrl()));
            Room room = new Room(this,getContext(),mCurrentUser);
            room.loadData();
        }
    }
}
