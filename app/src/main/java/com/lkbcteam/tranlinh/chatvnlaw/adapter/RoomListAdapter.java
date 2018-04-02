package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;

import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {
    private List<Room> mRoomList;
    private Context mContext;
    private BaseFragment mBaseFragment;
    private onChatMessageClick callback;

    public RoomListAdapter(Context context, BaseFragment baseFragment, List<Room> roomList){
        mRoomList = roomList;
        mContext = context;
        mBaseFragment = baseFragment;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Room room = mRoomList.get(position);
        final User user = room.getTargetUser();
        if(user != null){
            holder.tvSenderDisplayName.setText(user.getDisplayName());
            Picasso.with(mContext).load(String.valueOf(user.getPhotoURL())).into(holder.civProfileImage);
            holder.ibtnCall.setOnClickListener(v -> {
                if(mBaseFragment.getBaseActivity() instanceof HomeActivity && !user.getUid().isEmpty()){
                    ((HomeActivity)mBaseFragment.getBaseActivity()).makeCall(user.getUid());
                }
            });
            holder.tvMessageContent.setText(room.getLastMessContent());
        }
        holder.mLayoutContainer.setOnClickListener(v -> {
            callback.onClick(room);
        });


    }

    public void setCallback(onChatMessageClick callback) {
        this.callback = callback;
    }

    public interface onChatMessageClick{
        void onClick(Object o);
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
