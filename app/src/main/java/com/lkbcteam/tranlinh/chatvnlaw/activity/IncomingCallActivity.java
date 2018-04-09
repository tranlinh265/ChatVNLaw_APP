package com.lkbcteam.tranlinh.chatvnlaw.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;

import org.json.JSONObject;

import me.kevingleason.pnwebrtc.PnPeerConnectionClient;

/**
 * Created by tranlinh on 14/03/2018.
 */

public class IncomingCallActivity extends AppCompatActivity implements View.OnClickListener{

    private String username, calluser;
    private Pubnub pubnub;
    private SharedPreferences sp;
    private ImageButton ibtnReject, ibtnAccept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);
        ibtnAccept = findViewById(R.id.ibtn_phone);
        ibtnReject = findViewById(R.id.ibtn_phone_hangup);
        
        ibtnReject.setOnClickListener(this);
        ibtnAccept.setOnClickListener(this);
        
        this.sp = getSharedPreferences(Define.Pubnub.SHARED_PREFS, MODE_PRIVATE);
        
        this.username = this.sp.getString(Define.Pubnub.USER_NAME, "");
        Bundle extras = getIntent().getExtras();
        if(this.username.isEmpty()){
            this.username = extras.getString(Define.Pubnub.USER_NAME, "");
        }
        this.calluser = extras.getString(Define.Pubnub.CALL_USER,"");

        if(this.username.isEmpty() || this.calluser.isEmpty()){
            Log.e("error", "onCreate: " );
            Log.e(this.username, "onCreate: " + this.calluser );
            finish();
        }

        this.pubnub = new Pubnub(Define.Pubnub.PUB_KEY, Define.Pubnub.SUB_KEY);
        this.pubnub.setUUID(this.username);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(this.pubnub != null){
            this.pubnub.unsubscribeAll();
        }
    }

    private void acceptCall(){
        Intent intent = new Intent(IncomingCallActivity.this,VideoCallActivity.class);
        intent.putExtra(Define.Pubnub.USER_NAME, this.username);
        intent.putExtra(Define.Pubnub.CALL_USER, this.calluser);
        intent.putExtra(Define.Pubnub.DIALED, false);
        startActivity(intent);
    }
    private void rejectCall(){
        JSONObject jsonObject = PnPeerConnectionClient.generateHangupPacket(this.username);
        this.pubnub.publish(this.calluser, jsonObject, new Callback() {
            @Override
            public void successCallback(String channel, Object message) {
                Intent intent = new Intent(IncomingCallActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_phone:
                acceptCall();
                break;
            case R.id.ibtn_phone_hangup:
                rejectCall();
                break;
        }
    }
}
