//package com.lkbcteam.tranlinh.chatvnlaw.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//
//import com.google.firebase.auth.FirebaseUser;
//import com.lkbcteam.tranlinh.chatvnlaw.R;
//import com.lkbcteam.tranlinh.chatvnlaw.adapter.FileListAdapter;
//import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageListAdapter;
//import com.lkbcteam.tranlinh.chatvnlaw.model.entity.File;
//import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
//import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.CustomImage;
//import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by tranlinh on 01/02/2018.
// */
//
//public class FragmentRoomInfo extends BaseFragment {
//
//    private RecyclerView mRvFileList,mRvImageListLeft,mRvImageListRight;
//    private List<File> mFiles,mImages,mLeftImages,mRightImages;
//    private FileListAdapter mFileListAdapter;
//    private ImageButton mIbtnBack;
//    private FirebaseUser firebaseUser;
//    private Room room;
//    private ImageListAdapter mLeftImageListAdapter, mRightImageListAdapter;
//    private com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.File loadFile;
//    private CustomImage loadImage;
//
//    public static FragmentRoomInfo newInstance(Room room) {
//        FragmentRoomInfo fragment = new FragmentRoomInfo();
//        fragment.setRoom(room);
//        return fragment;
//    }
//    public static FragmentRoomInfo newInstance(FirebaseUser firebaseUser, Room room) {
//        FragmentRoomInfo fragmentRoomInfo = new FragmentRoomInfo();
//        fragmentRoomInfo.setFirebaseUser(firebaseUser);
//        fragmentRoomInfo.setRoom(room);
//        return fragmentRoomInfo;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_room_info, container,false);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    @Override
//    protected void initView(View view) {
//        super.initView(view);
//        mRvFileList = view.findViewById(R.id.rv_file_container);
//        mRvImageListLeft = view.findViewById(R.id.rv_image_container_1);
//        mRvImageListRight = view.findViewById(R.id.rv_image_container_2);
//        mIbtnBack = view.findViewById(R.id.ibtn_back);
//    }
//
//    @Override
//    protected void initData(View view) {
//        super.initData(view);
//        mFiles = new ArrayList<>();
//        mLeftImages = new ArrayList<>();
//        mRightImages = new ArrayList<>();
//        mImages = new ArrayList<>();
//
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        mRvFileList.setLayoutManager(layoutManager);
//        mFileListAdapter = new FileListAdapter(getContext(),this, mFiles);
//        mRvFileList.setAdapter(mFileListAdapter);
//        RecyclerView.LayoutManager mLayout1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
//        mRvImageListLeft.setLayoutManager(mLayout1);
//        RecyclerView.LayoutManager mLayout2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
//
//        mRvImageListRight.setLayoutManager(mLayout2);
//        mLeftImageListAdapter = new ImageListAdapter(getContext(),this, mLeftImages);
//        mLeftImageListAdapter.setAllImages(mImages);
//        mRightImageListAdapter = new ImageListAdapter(getContext(),this, mRightImages);
//        mRightImageListAdapter.setAllImages(mImages);
//        mRightImageListAdapter.setAdded(1);
//        mRvImageListRight.setAdapter(mRightImageListAdapter);
//        mRvImageListLeft.setAdapter(mLeftImageListAdapter);
//        mIbtnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goBackFragment();
//            }
//        });
//
//        loadFile = new com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.File(getContext(),this, firebaseUser, room);
//        loadFile.setFileList(mFiles);
//        loadFile.setFileAdapter(mFileListAdapter);
//        loadFile.loadFileData();
//
//        loadImage = new CustomImage(getContext(),this,firebaseUser,room);
//        loadImage.setLeftImageList(mLeftImages);
//        loadImage.setRightImageList(mRightImages);
//        loadImage.setLeftImageListAdapter(mLeftImageListAdapter);
//        loadImage.setRightImageListAdapter(mRightImageListAdapter);
//        loadImage.setFileList(mImages);
//        loadImage.loadImageData();
//    }
//
//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//
//    public FirebaseUser getFirebaseUser() {
//        return firebaseUser;
//    }
//
//    public void setFirebaseUser(FirebaseUser firebaseUser) {
//        this.firebaseUser = firebaseUser;
//    }
//}
