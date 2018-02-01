package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.FileListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class FragmentRoomInfo extends BaseFragment {

    private RecyclerView mRvFileList,mRvImageListLeft,mRvImageListRight;
    private List<Message.Info> mFiles,mLeftImages,mRightImages;
    private FileListAdapter mFileListAdapter;
    private ImageButton mIbtnBack;
    private ImageListAdapter mLeftImageListAdapter, mRightImageListAdapter;

    public static FragmentRoomInfo newInstance() {
        return new FragmentRoomInfo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room_info, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFiles = new ArrayList<>();
        mRvFileList = view.findViewById(R.id.rv_file_container);
        mRvImageListLeft = view.findViewById(R.id.rv_image_container_1);
        mRvImageListRight = view.findViewById(R.id.rv_image_container_2);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRvFileList.setLayoutManager(layoutManager);
        mFileListAdapter = new FileListAdapter(getContext(),this, mFiles);
        mRvFileList.setAdapter(mFileListAdapter);

        mLeftImages = new ArrayList<>();
        mRightImages = new ArrayList<>();

        RecyclerView.LayoutManager mLayout1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRvImageListLeft.setLayoutManager(mLayout1);
        RecyclerView.LayoutManager mLayout2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRvImageListRight.setLayoutManager(mLayout2);

        mLeftImageListAdapter = new ImageListAdapter(getContext(),this, mLeftImages);
        mRightImageListAdapter = new ImageListAdapter(getContext(),this, mRightImages);

        mRvImageListRight.setAdapter(mRightImageListAdapter);
        mRvImageListLeft.setAdapter(mLeftImageListAdapter);

        mIbtnBack = view.findViewById(R.id.ibtn_back);
        mIbtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackFragment();
            }
        });


    }
}
