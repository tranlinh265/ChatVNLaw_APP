package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.FileListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.model.File;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;

import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class FragmentRoomInfo extends BaseFragment implements View.OnClickListener{

    private RecyclerView rvFileList,rvImageListLeft,rvImageListRight;
    private List<File> fileList,imageList,leftImageList,rightImageList;
    private FileListAdapter fileListAdapter;
    private ImageButton ibtnBack;
    private ImageListAdapter leftImageListAdapter, rightImageListAdapter;
    private Room room;

    public static FragmentRoomInfo newInstance(Room room) {
        FragmentRoomInfo fragment = new FragmentRoomInfo();
        fragment.room = room;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room_info, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
    }

    @Override
    public void onClick(View v) {

    }
}
