package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.File;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomFileListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tranlinh on 24/03/2018.
 */

public class FileListInterator {
    private LoadFileListListener loadFileListListener;
    private FirebaseDatabase database;
    private FirebaseUser currentUser;

    public FileListInterator(LoadFileListListener loadFileListListener){
        this.loadFileListListener = loadFileListListener;
        database = FirebaseDatabase.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void loadRoomFileFromRails(String userToken, String userEmail, String roomId){
        ApiUtils.getService().getRoomFileList(userToken, userEmail, roomId).enqueue(new Callback<RoomFileListResponse>() {
            @Override
            public void onResponse(Call<RoomFileListResponse> call, Response<RoomFileListResponse> response) {
                if (response.isSuccessful()){
                    loadFileListListener.onLoadFromRailsSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<RoomFileListResponse> call, Throwable t) {

            }
        });
    }

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public interface LoadFileListListener {
        void onLoadFalure(String error);
        void onLoadFromRailsSuccess(RoomFileListResponse response);
    }
}
