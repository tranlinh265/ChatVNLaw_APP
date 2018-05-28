package com.lkbcteam.tranlinh.chatvnlaw.other.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.File;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by tranlinh on 08/02/2018.
 */

public class ImageDetailAdapter extends PagerAdapter {
    private List<File> images;
    private LayoutInflater layoutInflater;
    private Context context;
    public ImageDetailAdapter(Context context, List<File> images){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.images = images;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_image_detail, container, false);
        PhotoView pvImageDetail = view.findViewById(R.id.pv_image_detail);
        File image = images.get(position);
        Picasso.with(context).load(String.valueOf(image.getDownloadURL())).placeholder(R.drawable.spinning_loading_icon).into(pvImageDetail);
        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

}
