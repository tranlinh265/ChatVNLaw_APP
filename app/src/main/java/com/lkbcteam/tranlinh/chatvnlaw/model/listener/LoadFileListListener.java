package com.lkbcteam.tranlinh.chatvnlaw.model.listener;

import com.lkbcteam.tranlinh.chatvnlaw.model.File;

/**
 * Created by tranlinh on 24/03/2018.
 */

public interface LoadFileListListener {
    void onLoadSuccess(File file);
    void onLoadFalure(String error);
}
