package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.File;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.FileListInterator;
import com.lkbcteam.tranlinh.chatvnlaw.model.listener.LoadFileListListener;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class RoomInfoPresenter implements LoadFileListListener {

    private FileListInterator fileListInterator;

    public RoomInfoPresenter(FileListInterator fileListInterator){
        this.fileListInterator = fileListInterator;
    }


    @Override
    public void onLoadSuccess(File file) {

    }

    @Override
    public void onLoadFalure(String error) {

    }
}
