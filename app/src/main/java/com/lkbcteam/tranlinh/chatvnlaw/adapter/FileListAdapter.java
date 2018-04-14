package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomFileListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;

import java.util.List;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {
    private List<RoomFileListResponse.File> mFileList;
    private Context mContext;
    private BaseFragment mBaseFragment;

    public FileListAdapter(Context context, BaseFragment baseFragment, List<RoomFileListResponse.File> fileList){
        mContext = context;
        mBaseFragment = baseFragment;
        mFileList = fileList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file_shared,parent,false);
        ViewHolder vh = new ViewHolder(v);
        v.setTag(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        final RoomFileListResponse.File file = mFileList.get(position);
//        holder.tvFileName.setText(file.getName());
//        holder.tvFileSize.setText(String.valueOf(file.getSize()) + "KB");
//        holder.tvFileContentType.setText(file.getContentType());
//        holder.ibtnDownload.setOnClickListener(view -> {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(file.getDownloadURL()));
//            mContext.startActivity(browserIntent);
//        });
//        holder.ibtnShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shareIntent = new Intent();
//                shareIntent.setAction(Intent.ACTION_SEND);
//                shareIntent.putExtra(Intent.EXTRA_TEXT,
//                        "Hey check out my file: "+ file.getDownloadURL());
//                shareIntent.setType("text/plain");
//                mContext.startActivity(shareIntent);
//            }
//        });
//        if(file.getContentType().contains("text")){
//            if(file.getContentType().contains("HTML")){
//                changeColorBaseOnFileType(holder,R.drawable.html,R.color.colorHtml,R.color.html);
////                holder.ivFileType.setImageResource(R.drawable.html);
//            }else{
//                changeColorBaseOnFileType(holder,R.drawable.txt,R.color.colorTxt,R.color.txt);
//
////                holder.ivFileType.setImageResource(R.drawable.txt);
//            }
//        }else {
//            if(file.getContentType().contains("application")){
//                if (file.getContentType().contains("pdf")){
//                    changeColorBaseOnFileType(holder,R.drawable.pdf,R.color.colorPdf,R.color.pdf);
//
////                    holder.ivFileType.setImageResource(R.drawable.pdf);
//                }else{
//                    if (file.getContentType().contains("zip")){
//                        changeColorBaseOnFileType(holder,R.drawable.zip,R.color.colorZip,R.color.zip);
//
////                        holder.ivFileType.setImageResource(R.drawable.zip);
//                    }
//                }
//            }
//        }
    }

    private void changeColorBaseOnFileType(ViewHolder holder, int fileImage, int color, int colorXmlId){
        holder.ivFileType.setImageResource(fileImage);
        holder.rlImageContainer.setBackgroundColor(mContext.getResources().getColor(color));
        holder.ibtnDownload.setBackgroundTintList(mContext.getResources().getColorStateList(colorXmlId));
        holder.ibtnShare.setBackgroundTintList(mContext.getResources().getColorStateList(colorXmlId));
    }
    @Override
    public int getItemCount() {
        return mFileList.size();
//        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout rlImageContainer;
        public ImageView ivFileType;
        public TextView tvFileName, tvFileSize, tvFileContentType;
        public ImageButton ibtnDownload,ibtnShare;
        public ViewHolder(View itemView) {
            super(itemView);
            rlImageContainer = itemView.findViewById(R.id.image_container);
            ivFileType = itemView.findViewById(R.id.iv_file_type);
            tvFileName = itemView.findViewById(R.id.tv_file_name);
            tvFileSize = itemView.findViewById(R.id.tv_file_size);
            tvFileContentType = itemView.findViewById(R.id.tv_file_content_type);
            ibtnDownload = itemView.findViewById(R.id.ibtn_download);
            ibtnShare = itemView.findViewById(R.id.ibtn_share);
        }
    }
}
