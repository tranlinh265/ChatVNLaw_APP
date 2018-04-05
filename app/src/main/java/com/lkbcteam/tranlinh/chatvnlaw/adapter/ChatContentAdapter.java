package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.model.Time;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tranlinh on 29/01/2018.
 */

public class ChatContentAdapter extends RecyclerView.Adapter<ChatContentAdapter.ViewHolder> {
    private List<Message> mMessageList;
    private Context mContext;
    private View.OnClickListener mOnClickContentItem;
    private BaseFragment mBaseFragment;

    public ChatContentAdapter(Context context,BaseFragment baseFragment, List<Message> messageList, View.OnClickListener onClickContentItem) {
        mMessageList = messageList;
        mContext = context;
        mOnClickContentItem = onClickContentItem;
        mBaseFragment = baseFragment;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessageList.get(position).getmMessageId() == null){
            return -1;
        }
        if(mMessageList.get(position).getIsCurrentUser()){
            return 0;
        }
        return 2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch (viewType){
            case -1:
                v =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_time,parent,false);
                break;
            case 0:
                v =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receiver_messages,parent,false);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sender_messages,parent,false);
                break;
        }
        ViewHolder vh = new ViewHolder(v,viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Message message = mMessageList.get(position);
        if(message != null){
            if(message.getmMessageId() == null){
                Time time = message.getmMessageInfo().getTime();
                holder.tvTimeDay.setText(String.format(mContext.getString(R.string.date_format),time.getDay(),time.getMonth(),time.getYear()));
                return;
            }
            holder.mLayoutContainer.setOnClickListener(this.mOnClickContentItem);
            if(message.getmSenderUser() != null){
                Picasso.with(mContext).load(String.valueOf(message.getmSenderUser().getPhotoURL())).resize(50,50).centerCrop().into(holder.mIvSenderPicture);
            }
            if(message.getmMessageInfo() != null){
                if(message.getmMessageInfo().getContentType() != null){
                    holder.mTextContent.setVisibility(View.GONE);
                    if(message.getmMessageInfo().getContentType().contains("image/")){
                        holder.mIvImageContent.setVisibility(View.VISIBLE);
                        holder.mFileUrl.setVisibility(View.GONE);
                        Picasso.with(mContext).load(String.valueOf(message.getmMessageInfo().getDownloadURL())).resize(700,(700*message.getmMessageInfo().getHeight()/ message.getmMessageInfo().getWidth())).centerCrop().placeholder(R.drawable.spinning_loading_icon).into(holder.mIvImageContent);

                    }else{
                        holder.mFileUrl.setVisibility(View.VISIBLE);
                        holder.mIvImageContent.setVisibility(View.GONE);
                        holder.mFileUrl.setPaintFlags(holder.mFileUrl.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                        holder.mFileUrl.setText(message.getmMessageInfo().getName());
                        holder.mFileUrl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(message.getmMessageInfo().getDownloadURL()));
//                            mContext.startActivity(browserIntent);
                                showAlertDialog(message.getmMessageInfo());
//                            mBaseFragment.goNextFragment(FragmentFileContent.newInstance(message.getmMessageInfo().getDownloadURL(), message.getmMessageInfo().getName()),true);
//                            DownLoadFile.DownLoadFileViaUrl(mContext,message.getmMessageInfo().getName(), message.getmMessageInfo().getDownloadURL());
                            }
                        });

                    }
                }else{
                    holder.mTextContent.setVisibility(View.VISIBLE);
                    holder.mTextContent.setText(message.getmMessageInfo().getContent());
                    holder.mFileUrl.setVisibility(View.GONE);
                    holder.mIvImageContent.setVisibility(View.GONE);

                }
                Time time = message.getmMessageInfo().getTime();
                holder.mTextTimeStamp.setText(time.getHour()+":"+time.getMinute());
            }
        }
    }

    public void showAlertDialog(final Message.Info info){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(info.getName());
        builder.setCancelable(true);

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setPositiveButton("Download", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(info.getDownloadURL()));
            mContext.startActivity(browserIntent);
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mLayoutContainer;
        private TextView mTextContent, mTextTimeStamp, mFileUrl;
        private ImageView mIvSenderPicture, mIvImageContent;
        private TextView tvTimeDay;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if(viewType == -1){
                tvTimeDay = itemView.findViewById(R.id.tv_time_day);
            }else{
                mLayoutContainer = itemView.findViewById(R.id.layout_container);
                mTextContent = itemView.findViewById(R.id.tv_text_content);
                mTextTimeStamp = itemView.findViewById(R.id.tv_text_time_stamp);
                mIvSenderPicture = itemView.findViewById(R.id.iv_sender_picture);
                mFileUrl = itemView.findViewById(R.id.tv_file_url);
                mIvImageContent = itemView.findViewById(R.id.iv_image_content);
            }
        }
    }
}
