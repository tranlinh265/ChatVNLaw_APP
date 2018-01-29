package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class ChatContentAdapter extends RecyclerView.Adapter<ChatContentAdapter.ViewHolder> {
    private List<Message> mMessageList;
    private Context mContext;
    private View.OnClickListener mOnClickContentItem;

    public ChatContentAdapter(Context context, List<Message> messageList, View.OnClickListener onClickContentItem) {
        mMessageList = messageList;
        mContext = context;
        mOnClickContentItem = onClickContentItem;
    }

    @Override
    public int getItemViewType(int position) {
        if(mMessageList.get(position).getIsCurrentUser()){
            return 0;
        }
        return 2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch (viewType){
            case 0:
                v =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receiver_messages,parent,false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sender_messages,parent,false);
                break;
        }
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mLayoutContainer.setOnClickListener(this.mOnClickContentItem);
        Message message = mMessageList.get(position);
        if(message != null){
            if(message.getmSenderUser() != null){
//                new DownloadImageTask((ImageView) holder.mIvSenderPicture).execute(String.valueOf(message.getmSenderUser().getPhotoURL()));
                Picasso.with(mContext).load(String.valueOf(message.getmSenderUser().getPhotoURL())).into(holder.mIvSenderPicture);
            }
            if(message.getmTextMessage() != null){
                holder.mTextContent.setText(message.getmTextMessage().getContent());
                holder.mTextTimeStamp.setText(message.getmTextMessage().getMsgTimeStamp());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mLayoutContainer;
        private TextView mTextContent, mTextTimeStamp;
        private ImageView mIvSenderPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            mLayoutContainer = itemView.findViewById(R.id.layout_container);
            mTextContent = itemView.findViewById(R.id.tv_text_content);
            mTextTimeStamp = itemView.findViewById(R.id.tv_text_time_stamp);
            mIvSenderPicture = itemView.findViewById(R.id.iv_sender_picture);
        }
    }
}
