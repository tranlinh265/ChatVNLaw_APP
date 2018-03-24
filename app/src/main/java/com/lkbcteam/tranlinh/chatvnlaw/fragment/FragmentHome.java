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
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.activity.MainActivity;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.RoomListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.UnreadMessageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.FirebaseData;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataChange;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.FragmentMenu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class FragmentHome extends BaseFragment {
    private List<Message> mMessageList = new ArrayList<Message>();
    private RecyclerView mRvMessageList, mRvUnreadMessageList;
    private TextView mTvNumberOfUnread,mTvWelcomeUser;
    private List<Room> mRoomList;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mCurrentUser;
    private CircleImageView mCivHomeProfile;
    private ImageButton mIbtnHomeMenu;

    public static Fragment newInstance(){
        return new FragmentHome();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mCurrentUser = mAuth.getCurrentUser();
        if (mCurrentUser == null){
            getBaseActivity().startActivity(MainActivity.class,true);
        }else{
            if(getBaseActivity() instanceof  HomeActivity){
                ((HomeActivity)getBaseActivity()).setUsername(mCurrentUser.getUid());
            }
        }
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        (new Handler()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                initViewChild(view);
//            }
//        }, 500);
        initViewChild(view);
    }

    private void initViewChild(View view){
        mRvMessageList = view.findViewById(R.id.rv_messages);
        mRvUnreadMessageList = view.findViewById(R.id.rv_unread_messages);
        mMessageList = new ArrayList<>();
        mRoomList = new ArrayList<>();
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(getContext(),1);
        mRvMessageList.setLayoutManager(mLayout);
        final RoomListAdapter adapter = new RoomListAdapter(getContext(),this, mRoomList);
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
        mIbtnHomeMenu = view.findViewById(R.id.ibtn_home_menu);
        mIbtnHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextFragment(FragmentMenu.newInstance(0),true,false);
            }
        });
        if(mCurrentUser != null){
            mTvWelcomeUser.setText(mCurrentUser.getDisplayName());
            Picasso.with(getContext()).load(mCurrentUser.getPhotoUrl()).resize(50,50).centerCrop().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar).into(mCivHomeProfile);
            FirebaseData.getRoomList(new OnDataLoadingFinish() {
                @Override
                public void onSuccess(Object o) {
                    Room room = (Room) o;
                    mRoomList.add(room);
                }

                @Override
                public void onFail() {

                }

                @Override
                public void onDataNotExist() {

                }
            }, new OnDataChange() {
                @Override
                public void onDataChange() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
