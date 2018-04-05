package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.model.FirebaseLawyer;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.*;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.TextMessage;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.TimeStamp;
import com.lkbcteam.tranlinh.chatvnlaw.model.entity.Message;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataChange;
import com.lkbcteam.tranlinh.chatvnlaw.other.OnDataLoadingFinish;

/**
 * Created by tranlinh on 22/03/2018.
 */

public class FirebaseData {


    public static void getLawyerInfo(FirebaseUser firebaseUser, ValueEventListener callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child(Define.Table.TABLE_LAWYERS).child(firebaseUser.getUid()).addListenerForSingleValueEvent(callback);
    }
    public static void updateLawyerInfo(FirebaseUser firebaseUser, FirebaseLawyer lawyer){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child(Define.Table.TABLE_LAWYERS).child(firebaseUser.getUid()).setValue(lawyer);
    }
}
