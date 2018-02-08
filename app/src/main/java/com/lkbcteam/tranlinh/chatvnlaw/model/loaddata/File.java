package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.FileListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.adapter.ImageListAdapter;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.model.*;
import com.lkbcteam.tranlinh.chatvnlaw.model.Room;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import java.util.List;

/**
 * Created by tranlinh on 01/02/2018.
 */

public class File {
    protected FirebaseDatabase database = FirebaseDatabase.getInstance();
    protected BaseFragment mBaseFragment;
    protected Context mContext;
    protected FirebaseUser mCurrentUser;
    protected FileListAdapter fileAdapter;
    protected ImageListAdapter imageAdapter;
    protected Room room;
    protected List<com.lkbcteam.tranlinh.chatvnlaw.model.File> fileList;
    public File(Context context, BaseFragment baseFragment, FirebaseUser firebaseUser, Room room){
        this.mBaseFragment = baseFragment;
        this.mContext = context;
        this.mCurrentUser = firebaseUser;
        this.room = room;
    }
    public void loadFileData(){
        database.getReference().child(Define.Table.TABLE_ROOMS+"/" + room.getRid() + "/" +Define.Room.SHARED_FILES).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.File file = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.File.class);
                fileList.add(file);
                fileAdapter.notifyDataSetChanged();
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
    public void loadImageData(){
        database.getReference().child(Define.Table.TABLE_ROOMS+"/" + room.getRid()+ "/" + Define.Room.SHARED_IMAGES).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                com.lkbcteam.tranlinh.chatvnlaw.model.File file = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.File.class);
                fileList.add(file);
                imageAdapter.notifyDataSetChanged();
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
    public FileListAdapter getFileAdapter() {
        return fileAdapter;
    }

    public void setFileAdapter(FileListAdapter fileAdapter) {
        this.fileAdapter = fileAdapter;
    }

    public ImageListAdapter getImageAdapter() {
        return imageAdapter;
    }

    public void setImageAdapter(ImageListAdapter imageAdapter) {
        this.imageAdapter = imageAdapter;
    }

    public List<com.lkbcteam.tranlinh.chatvnlaw.model.File> getFileList() {
        return fileList;
    }

    public void setFileList(List<com.lkbcteam.tranlinh.chatvnlaw.model.File> fileList) {
        this.fileList = fileList;
    }
}
