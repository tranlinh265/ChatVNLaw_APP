package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

/**
 * Created by tranlinh on 24/03/2018.
 */

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.ApiUtils;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.RoomListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Model
 * get room from firebase real time database
 * send room info to presenter through call back LoadRoomListener
 * */
public class RoomListInterator {
    private LoadRoomListListener callback;
    private FirebaseDatabase database;
    private FirebaseUser currentUser;
    private Context context;
    public RoomListInterator(LoadRoomListListener callback){
        this.callback = callback;
        database = FirebaseDatabase.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void getRoomListFromRail(String userToken){

        ApiUtils.getService().getRoomList(userToken, currentUser.getEmail()).enqueue(new Callback<RoomListResponse>() {
            @Override
            public void onResponse(Call<RoomListResponse> call, Response<RoomListResponse> response) {
                if(response.isSuccessful()){
                    RoomListResponse roomListResponse = response.body();
                    for (RoomListResponse.Room room : roomListResponse.getRooms()){
                        room.setCurrentUserIsLawyer(currentUser.getUid().equals(room.getLawyer().getUserId()));
                        room.setDefaultAvatar();
                    }
                    callback.onLoadRoomListFromRailSuccess(roomListResponse.getRooms());
                }else{
                    callback.onLoadRoomListFromRailFalure("response error");
                }
            }

            @Override
            public void onFailure(Call<RoomListResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onLoadRoomListFromRailFalure("server error");
            }
        });
    }
    public void getRoomList(){
        database.getReference().child(Define.Table.TABLE_REFERENCE).child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = new Room();
                room.setRid(String.valueOf(dataSnapshot.getValue()));
                callback.onLoadRoomListSuccess(room);
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
                callback.onLoadRoomListFalure(databaseError.getMessage());
            }
        });
    }

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public interface LoadRoomListListener {
        void onLoadRoomListFromRailSuccess(List<RoomListResponse.Room> roomList);
        void onLoadRoomListFromRailFalure(String error);
        void onLoadRoomListSuccess(Room room);
        void onLoadRoomListFalure(String error);
    }
}
