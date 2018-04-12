package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;

import com.lkbcteam.tranlinh.chatvnlaw.model.User;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {
    private List<RoomListResponse.Room> mRoomList;
    private Context mContext;
    private onClick callback;

    public RoomListAdapter(Context context, List<RoomListResponse.Room> roomList){
        mRoomList = roomList;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoomListResponse.Room room = mRoomList.get(position);
        RoomListResponse.User user;
        if(room.isCurrentUserIsLawyer()){
            user = room.getUser();
        }else{
            user = room.getLawyer();
        }

        if(user != null){
            holder.tvSenderDisplayName.setText(user.getProfile().getDisplayName());

            Picasso.with(mContext).load(String.valueOf(user.getProfile().getAvatar().getThumbSmall().getUrl())).into(holder.civProfileImage);
            holder.ibtnCall.setOnClickListener(v -> {
                String id = room.isCurrentUserIsLawyer() ? user.getUserId() : user.getId();
                callback.onCallItemClicked(id);
            });
            holder.civProfileImage.setTransitionName(mContext.getResources().getString(R.string.target_user_avatar_transiton) + String.valueOf(position));
            holder.mLayoutContainer.setOnClickListener(v -> {
                callback.onChatMessageItemClicked(room, position, holder.civProfileImage);
            });
        }
    }

    public void setCallback(onClick callback) {
        this.callback = callback;
    }

    public interface onClick {
        void onChatMessageItemClicked(Object o, int position, View view);
        void onCallItemClicked(String uid);
    }

    @Override
    public int getItemCount() {
        return mRoomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView civProfileImage;
        public TextView tvSenderDisplayName, tvMessageContent;
        public View mLayoutContainer;
        public ImageButton ibtnCall;

        public ViewHolder(View itemView) {
            super(itemView);
            civProfileImage = itemView.findViewById(R.id.civ_profile_image);
            tvSenderDisplayName = itemView.findViewById(R.id.tv_sender);
            tvMessageContent = itemView.findViewById(R.id.tv_content);
            ibtnCall = itemView.findViewById(R.id.ibtn_call);
            mLayoutContainer = itemView.findViewById(R.id.message_container);
        }
    }
}
