package com.lkbcteam.tranlinh.chatvnlaw.model.Interator;

/**
 * Created by tranlinh on 24/03/2018.
 */

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Room;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

/**
 * Model
 * get room from firebase real time database
 * send room info to presenter through call back LoadRoomListener
 * */
public class RoomListInterator {
    private LoadRoomListListener loadRoomListListener;
    private FirebaseDatabase database;
    private FirebaseUser currentUser;

    public RoomListInterator(LoadRoomListListener loadRoomListListener){
        this.loadRoomListListener = loadRoomListListener;
        database = FirebaseDatabase.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void getRoomList(){
        database.getReference().child(Define.Table.TABLE_REFERENCE).child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room room = new Room();
                room.setRid(String.valueOf(dataSnapshot.getValue()));
                loadRoomListListener.onLoadRoomListSuccess(room);
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
                loadRoomListListener.onLoadRoomListFalure(databaseError.getMessage());
            }
        });
    }

    /**
     * Created by tranlinh on 24/03/2018.
     */

    public static interface LoadRoomListListener {
        void onLoadRoomListSuccess(Room room);
        void onLoadRoomListFalure(String error);
    }
}
