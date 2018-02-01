package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;

import java.util.List;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter <ImageListAdapter.ViewHolder>{

    private List<Message.Info> mImageList;
    private Context mContext;
    private BaseFragment mBaseFragment;

    public ImageListAdapter(Context context, BaseFragment baseFragment, List<Message.Info> imageList){
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
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
//        return mImageList.size();
        return 15;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
