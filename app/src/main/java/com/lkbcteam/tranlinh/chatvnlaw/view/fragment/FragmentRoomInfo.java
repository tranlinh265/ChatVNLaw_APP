package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.FileListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageListAdapter;

import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomFileListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.RoomInfoPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class FragmentRoomInfo extends BaseFragment implements View.OnClickListener, RoomInfoPresenter.RoomInfoView, ImageListAdapter.onItemImageClick{

    private RecyclerView rvFileList,rvImageListLeft,rvImageListRight;
    private List<RoomFileListResponse.File> fileList,leftImageList,rightImageList;
    private FileListAdapter fileListAdapter;
    private ImageButton ibtnBack;
    private ImageListAdapter leftImageListAdapter, rightImageListAdapter;
    private RoomListResponse.Room room;
    private LinearLayoutManager filesLayoutManager;
    private RecyclerView.LayoutManager leftImagesLayoutManager, rightImagesLayoutManager;
    private RoomInfoPresenter presenter;

    public static FragmentRoomInfo newInstance(RoomListResponse.Room room) {
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
        rvFileList = view.findViewById(R.id.rv_file_container);
        rvImageListLeft = view.findViewById(R.id.rv_image_container_1);
        rvImageListRight = view.findViewById(R.id.rv_image_container_2);
        ibtnBack = view.findViewById(R.id.ibtn_back);
        ibtnBack.setOnClickListener(this);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        fileList = new ArrayList<>();
        leftImageList = new ArrayList<>();
        rightImageList = new ArrayList<>();

        filesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFileList. setLayoutManager(filesLayoutManager);
        fileListAdapter = new FileListAdapter(getContext(),this,fileList);

        leftImagesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rightImagesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        rvImageListLeft.setLayoutManager(leftImagesLayoutManager);
        rvImageListRight.setLayoutManager(rightImagesLayoutManager);

        leftImageListAdapter = new ImageListAdapter(getContext(),leftImageList);
        leftImageListAdapter.setCallback(this);
        rightImageListAdapter = new ImageListAdapter(getContext(), rightImageList);
        rightImageListAdapter.setCallback(this);
        rvImageListLeft.setAdapter(leftImageListAdapter);
        rvImageListRight.setAdapter(rightImageListAdapter);

        presenter = new RoomInfoPresenter(this,fileList,leftImageList,rightImageList);
//        presenter.loadFileList(String.valueOf(room.getId()));
//        presenter.loadImageList(String.valueOf(room.getId()));

        presenter.getRoomFileListFromRails(SharePreference.getInstance(getActivity()).getUserToken(),
                "linhtm@gmail.com", String.valueOf(room.getId()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_back:
                goBackFragment();
                break;
        }
    }

    @Override
    public void notifyWhenImageListChange(boolean left,int position) {
        if (left){
            leftImageListAdapter.notifyItemChanged(position);
        }else {
            rightImageListAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void notifyWhenFileListChange(int position) {
        fileListAdapter.notifyItemChanged(position);
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyDataSetChanged() {
        leftImageListAdapter.notifyDataSetChanged();
        rightImageListAdapter.notifyDataSetChanged();
        fileListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemImageClick(Object o, int position) {

    }

    @Override
    public void onItemImageClick(List<RoomFileListResponse.File> list, int position) {
//        goNextFragment(FragmentImageDetail.newInstance(list, position),true);
    }
}
