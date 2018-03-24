package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.FragmentImageDetail;
import com.lkbcteam.tranlinh.chatvnlaw.model.File;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter <ImageListAdapter.ViewHolder>{

    private List<File> mImageList, allImages;
    private Context mContext;
    private BaseFragment mBaseFragment;
    private int added = 0;
    public ImageListAdapter(Context context, BaseFragment baseFragment, List<File> imageList){
        mContext = context;
        mBaseFragment = baseFragment;
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
        final File image = mImageList.get(position);
        Picasso.with(mContext).load(String.valueOf(image.getDownloadURL())).resize(400,(400*image.getHeight()/ image.getWidth())).centerCrop().placeholder(R.drawable.spinning_loading_icon).into(holder.ivImageShared);
        holder.ivImageShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBaseFragment.goNextFragment(FragmentImageDetail.newInstance(allImages, position*2 + added),true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
//        return 15;
    }

    public List<File> getAllImages() {
        return allImages;
    }

    public void setAllImages(List<File> allImages) {
        this.allImages = allImages;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivImageShared;
        public ViewHolder(View itemView) {
            super(itemView);
            ivImageShared = itemView.findViewById(R.id.iv_image_shared);
        }
    }
}
