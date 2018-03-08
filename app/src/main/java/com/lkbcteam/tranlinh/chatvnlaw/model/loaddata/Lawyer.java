package com.lkbcteam.tranlinh.chatvnlaw.model.loaddata;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.model.FirebaseLawyer;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

/**
 * Created by tranlinh on 08/03/2018.
 */

public class Lawyer {
    public static void getLawyerInfo(FirebaseUser firebaseUser, ValueEventListener callback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child(Define.Table.TABLE_LAWYERS).child(firebaseUser.getUid()).addListenerForSingleValueEvent(callback);
    }
    public static void updateLawyerInfo(FirebaseUser firebaseUser, FirebaseLawyer lawyer){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child(Define.Table.TABLE_LAWYERS).child(firebaseUser.getUid()).setValue(lawyer);
    }
}
