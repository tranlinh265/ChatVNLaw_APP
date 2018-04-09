package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.entity.File;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.FileListInterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class RoomInfoPresenter implements FileListInterator.LoadFileListListener {

    private FileListInterator fileListInterator;
    private List<File> fileList, imageList,leftImageList, rightImageList;
    private RoomInfoView roomInfoView;

    public RoomInfoPresenter(RoomInfoView roomInfoView, List<File> fileList, List<File> leftImageList, List<File> rightImageList){
        this.roomInfoView = roomInfoView;
        this.imageList = new ArrayList<>();
        this.leftImageList = leftImageList;
        this.rightImageList = rightImageList;
        this.fileList = fileList;
        fileListInterator = new FileListInterator(this);
    }
    public RoomInfoPresenter(FileListInterator fileListInterator){
        this.fileListInterator = fileListInterator;
    }

    public void loadFileList(String roomId){
        fileListInterator.loadFileItem(roomId);
    }

    public void loadImageList(String roomId){
        fileListInterator.loadImageItem(roomId);
    }

    @Override
    public void onLoadSuccess(File file) {
        if(file.getContentType().contains("image")){
            imageList.add(file);
            if(imageList.size() % 2 == 1){
                leftImageList.add(file);
                roomInfoView.notifyWhenImageListChange(true,leftImageList.size() -1);
            }else{
                rightImageList.add(file);
                roomInfoView.notifyWhenImageListChange(false,rightImageList.size() -1);
            }
        }else{
            fileList.add(file);
            roomInfoView.notifyWhenFileListChange(fileList.size() - 1);
        }
    }

    @Override
    public void onLoadFalure(String error) {
        roomInfoView.showErrorMessage(error);
    }

    /**
     * Created by tranlinh on 26/03/2018.
     */

    public static interface RoomInfoView {
        void notifyWhenImageListChange(boolean left, int position);
        void notifyWhenFileListChange(int position);
        void showErrorMessage(String error);
    }
}
