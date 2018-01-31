package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Pattern;

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
                Picasso.with(mContext).load(String.valueOf(message.getmSenderUser().getPhotoURL())).resize(50,50).centerCrop().into(holder.mIvSenderPicture);
            }
            if(message.getmMessageInfo() != null){
                if(message.getmMessageInfo().getContentType() != null){
                    holder.mTextContent.setVisibility(View.GONE);
                    if(message.getmMessageInfo().getContentType().contains("image/")){
                        holder.mIvImageContent.setVisibility(View.VISIBLE);
                        holder.mFileUrl.setVisibility(View.GONE);
                        Picasso.with(mContext).load(String.valueOf(message.getmMessageInfo().getDownloadURL())).resize(300,(300* (int)(message.getmMessageInfo().getHeight() / message.getmMessageInfo().getWidth()))).centerCrop().into(holder.mIvImageContent);

                    }else{
                        holder.mFileUrl.setVisibility(View.VISIBLE);
                        holder.mIvImageContent.setVisibility(View.GONE);
                        holder.mFileUrl.setClickable(true);
                        String text = "<a href='http://www.google.com'> google </a>";
                        holder.mFileUrl.setText(Html.fromHtml(text));
                        holder.mFileUrl.setMovementMethod(LinkMovementMethod.getInstance());

//                        String fileUrl = String.format("<a href=\"%s\">%s</a>",message.getmMessageInfo().getDownloadURL(), message.getmMessageInfo().getName());
//                        Log.d(fileUrl, "onBindViewHolder: ");
//                        holder.mFileUrl.setText(Html.fromHtml(fileUrl));

                    }
                }else{
                    holder.mTextContent.setVisibility(View.VISIBLE);
                    holder.mTextContent.setText(message.getmMessageInfo().getContent());
                    holder.mFileUrl.setVisibility(View.GONE);
                    holder.mIvImageContent.setVisibility(View.GONE);

                }
                holder.mTextTimeStamp.setText(message.getmMessageInfo().getMsgTimeStamp());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mLayoutContainer;
        private TextView mTextContent, mTextTimeStamp, mFileUrl;
        private ImageView mIvSenderPicture, mIvImageContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mLayoutContainer = itemView.findViewById(R.id.layout_container);
            mTextContent = itemView.findViewById(R.id.tv_text_content);
            mTextTimeStamp = itemView.findViewById(R.id.tv_text_time_stamp);
            mIvSenderPicture = itemView.findViewById(R.id.iv_sender_picture);
            mFileUrl = itemView.findViewById(R.id.tv_file_url);
            mIvImageContent = itemView.findViewById(R.id.iv_image_content);
        }
    }
}
