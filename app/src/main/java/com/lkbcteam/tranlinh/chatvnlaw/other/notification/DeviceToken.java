package com.lkbcteam.tranlinh.chatvnlaw.other.notification;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

/**
 * Created by tranlinh on 05/03/2018.
 */

public class DeviceToken {
    public static DeviceToken getInstance(){
        return new DeviceToken();
    }
    public void sendRegistrationToServer(FirebaseUser currentUser, String token) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child(Define.Table.TABLE_USERS).child(currentUser.getUid()).child(Define.User.TOKEN).setValue(token);
    }

    public static void initFirebaseCloudMessaging(FirebaseUser currentUser){
        String token = FirebaseInstanceId.getInstance().getToken();
        DeviceToken.getInstance().sendRegistrationToServer(currentUser, token);
    }
}
