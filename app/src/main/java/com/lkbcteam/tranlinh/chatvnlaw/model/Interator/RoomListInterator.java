package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

/**
 * Created by tranlinh on 24/03/2018.
 */

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
                    for (int i = 0; i < roomListResponse.getRooms().size(); i++){
                        RoomListResponse.Room room = roomListResponse.getRooms().get(i);
                        getLastMessage(room, i);
                        room.setCurrentUserIsLawyer(currentUser.getUid());
//                        room.setDefaultAvatar();
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

    public void getLastMessage(RoomListResponse.Room room, int position){
        FirebaseDatabase.getInstance().getReference().child("rooms/"+String.valueOf(room.getId())+"/unReadMessage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if (dataSnapshot.child("count").getValue() != null){
                        if (Integer.parseInt(String.valueOf(dataSnapshot.child("count").getValue())) > 0){
                            String lastMess = String.valueOf(dataSnapshot.child("lastMessage").child("content").getValue());
                            room.setLastMessage(lastMess);
                            callback.notifyDataChange(room , position);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    /**
     * Created by tranlinh on 24/03/2018.
     */

    public interface LoadRoomListListener {
        void onLoadRoomListFromRailSuccess(List<RoomListResponse.Room> roomList);
        void onLoadRoomListFromRailFalure(String error);
        void notifyDataChange(RoomListResponse.Room room, int position);
    }
}
