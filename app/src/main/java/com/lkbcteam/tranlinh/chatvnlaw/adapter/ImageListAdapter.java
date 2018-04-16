package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomFileListResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter <ImageListAdapter.ViewHolder>{

    private List<RoomFileListResponse.File> mImageList, allImages;
    private Context mContext;
    private int added = 0;
    private onItemImageClick callback;

    public ImageListAdapter(Context context, List<RoomFileListResponse.File> imageList){
        mContext = context;
        mImageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_shared,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RoomFileListResponse.File image = mImageList.get(position);
        Picasso.with(mContext).load(String.valueOf(image.getFile().getThumb().getRealUrl())).into(holder.ivImageShared);
        holder.ivImageShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemImageClick(allImages, position*2+added);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
//        return 15;
    }

    public List<RoomFileListResponse.File> getAllImages() {
        return allImages;
    }

    public void setAllImages(List<RoomFileListResponse.File> allImages) {
        this.allImages = allImages;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public void setCallback(onItemImageClick callback) {
        this.callback = callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivImageShared;
        public ViewHolder(View itemView) {
            super(itemView);
            ivImageShared = itemView.findViewById(R.id.iv_image_shared);
        }
    }

    public interface onItemImageClick{
        void onItemImageClick(Object o, int position);
        void onItemImageClick(List<RoomFileListResponse.File> list, int position);
    }
}
