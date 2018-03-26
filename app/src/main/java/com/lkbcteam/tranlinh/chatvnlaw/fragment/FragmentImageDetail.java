package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageDetailAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageDetailViewPager;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.File;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by tranlinh on 08/02/2018.
 */

public class FragmentImageDetail extends BaseFragment {
    private ViewPager vpImageContainer;
    private ImageButton ibtnBack;
    private List<File> images;
    private int position;
    private ImageDetailAdapter adapter;
    private TextView tvCurrentPage;
    private CircleIndicator circleIndicator;
    public static FragmentImageDetail newInstance(List<File> images,int position) {

        FragmentImageDetail fragment = new FragmentImageDetail();

        fragment.setImages(images);
        fragment.setPosition(position);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_detail, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ibtnBack = view.findViewById(R.id.ibtn_back);
        vpImageContainer = (ImageDetailViewPager) view.findViewById(R.id.pager);
        tvCurrentPage =view.findViewById(R.id.tv_current_page);
        circleIndicator = view.findViewById(R.id.indicator);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        adapter = new ImageDetailAdapter(getContext(),images);
        vpImageContainer.setAdapter(adapter);
        vpImageContainer.setCurrentItem(position,true);
        vpImageContainer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvCurrentPage.setText(String.valueOf(position+1) + "/" + String.valueOf(images.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        circleIndicator.setViewPager(vpImageContainer);
        tvCurrentPage.setText(String.valueOf(position+1) + "/" + String.valueOf(images.size()));
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackFragment();
            }
        });
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
