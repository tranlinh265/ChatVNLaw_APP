package com.lkbcteam.tranlinh.chatvnlaw.other.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 27/01/2018.
 */

public class UnreadMessageListAdapter extends RecyclerView.Adapter<UnreadMessageListAdapter.ViewHolder> {
    private List<Message> mMessageList;
    private Context mContext;
    private BaseFragment mBaseFragment;
    public UnreadMessageListAdapter(Context context,BaseFragment baseFragment, List<Message> messageList){
        mMessageList = messageList;
        mContext = context;
        mBaseFragment = baseFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unread_messages_2,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Message message = mMessageList.get(position);
//        if(message.getmMessageInfo() != null){
//            holder.tvSenderDisplayName.setText(message.);
//            holder.tvMessageContent.setText(message.getmessageContent());
//            holder.tvMessageTime.setText(message.getmessageTime());
//        }

//        holder.container.setOnClickListener(new RedirectToRoomChat(mBaseFragment, message));

    }

    @Override
    public int getItemCount() {
//        return mMessageList.size();
        return 5;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView civProfileImage;
        public TextView tvSenderDisplayName, tvMessageContent, tvMessageTime;
        public View container;

        public ViewHolder(View itemView) {
            super(itemView);
            civProfileImage = itemView.findViewById(R.id.civ_profile_image);
            tvSenderDisplayName = itemView.findViewById(R.id.tv_sender);
            tvMessageContent = itemView.findViewById(R.id.tv_content);
            tvMessageTime = itemView.findViewById(R.id.tv_time);
            container = itemView.findViewById(R.id.layout_container);
        }
    }
}
