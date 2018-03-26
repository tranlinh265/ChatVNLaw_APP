package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import java.util.List;

/**
 * Created by tranlinh on 08/02/2018.
 */

public class CustomImage extends File {
    private ImageListAdapter leftImageListAdapter, rightImageListAdapter;
    private List<com.lkbcteam.tranlinh.chatvnlaw.model.entity.File> leftImageList, rightImageList;

    public CustomImage(Context context, BaseFragment baseFragment, FirebaseUser firebaseUser, Room room) {
        super(context, baseFragment, firebaseUser, room);
    }

    @Override
    public void loadImageData() {
        database.getReference().child(Define.Table.TABLE_ROOMS+"/" + room.getRid()+ "/" + Define.Room.SHARED_IMAGES).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.entity.File file = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.entity.File.class);
                fileList.add(file);
                switch (fileList.size() % 2){
                    case 1:
                        leftImageList.add(file);
                        leftImageListAdapter.notifyDataSetChanged();
                        break;
                    case 0:
                        rightImageList.add(file);
                        rightImageListAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ImageListAdapter getLeftImageListAdapter() {
        return leftImageListAdapter;
    }

    public void setLeftImageListAdapter(ImageListAdapter leftImageListAdapter) {
        this.leftImageListAdapter = leftImageListAdapter;
    }

    public ImageListAdapter getRightImageListAdapter() {
        return rightImageListAdapter;
    }

    public void setRightImageListAdapter(ImageListAdapter rightImageListAdapter) {
        this.rightImageListAdapter = rightImageListAdapter;
    }

    public List<com.lkbcteam.tranlinh.chatvnlaw.model.entity.File> getLeftImageList() {
        return leftImageList;
    }

    public void setLeftImageList(List<com.lkbcteam.tranlinh.chatvnlaw.model.entity.File> leftImageList) {
        this.leftImageList = leftImageList;
    }

    public List<com.lkbcteam.tranlinh.chatvnlaw.model.entity.File> getRightImageList() {
        return rightImageList;
    }

    public void setRightImageList(List<com.lkbcteam.tranlinh.chatvnlaw.model.entity.File> rightImageList) {
        this.rightImageList = rightImageList;
    }
}
