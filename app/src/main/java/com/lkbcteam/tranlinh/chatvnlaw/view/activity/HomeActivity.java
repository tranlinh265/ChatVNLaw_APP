package com.lkbcteam.tranlinh.chatvnlaw.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragmentcontainer.FragmentHomeContainer;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tranlinh on 26/01/2018.
 */

public class HomeActivity extends BaseActivity {

    private Pubnub pubnub;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        initView();
        replaceContainerFragment(R.id.content, FragmentHomeContainer.newInstance());
    }

    private void initView(){
        this.username = SharePreference.getInstance(this).getUserId();
        initPubnub();
    }
    private void initPubnub(){
        if(this.username.isEmpty()) return;
        
        String stdbyChannel = this.username + Define.Pubnub.STDBY_SUFFIX;
        this.pubnub = new Pubnub(Define.Pubnub.PUB_KEY, Define.Pubnub.SUB_KEY);
        this.pubnub.setUUID(this.username);
        try {
            this.pubnub.subscribe(stdbyChannel, new Callback() {
                @Override
                public void successCallback(String channel, Object message) {
                    if (!(message instanceof JSONObject)) return; // Ignore if not JSONObject
                    JSONObject jsonMsg = (JSONObject) message;
                    try {
                        if (!jsonMsg.has(Define.Pubnub.JSON_CALL_USER)) return;
                        String user = jsonMsg.getString(Define.Pubnub.JSON_CALL_USER);
                        // Consider Accept/Reject call here
                        Intent intent = new Intent(HomeActivity.this, IncomingCallActivity.class);
                        intent.putExtra(Define.Pubnub.USER_NAME, username);
                        intent.putExtra(Define.Pubnub.JSON_CALL_USER, user);
                        intent.putExtra(Define.Pubnub.CALL_USER,user);
                        intent.putExtra(Define.Pubnub.CAMERA_MODE, Define.Pubnub.CAMERA_MODE_FRONT);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }
    
    public void setUsername(String username){
        if(!this.username.isEmpty()) return;
        this.username = username;
        initPubnub();
    }
    
    public void makeCall(String callId){
        if (callId.isEmpty() || callId.equals(this.username)) {
            Toast.makeText(this, "Enter a valid number.", Toast.LENGTH_SHORT).show();
        } else {
            dispatchCall(callId);
        }
    }

    private void dispatchCall(final String callNum) {
        final String callNumStdBy = callNum + Define.Pubnub.STDBY_SUFFIX;
        JSONObject jsonCall = new JSONObject();
        try {
            jsonCall.put(Define.Pubnub.JSON_CALL_USER, this.username);
            pubnub.publish(callNumStdBy, jsonCall, new Callback() {
                @Override
                public void successCallback(String channel, Object message, String timetoken) {
                    super.successCallback(channel, message, timetoken);
                }

                @Override
                public void successCallback(String channel, Object message) {
                    Log.d(Define.Pubnub.LOG_TAG, "SUCCESS: " + message.toString());
                    Intent intent = new Intent(HomeActivity.this, VideoCallActivity.class);
                    intent.putExtra(Define.Pubnub.USER_NAME, username);
                    intent.putExtra(Define.Pubnub.CALL_USER, callNum);
                    intent.putExtra(Define.Pubnub.CAMERA_MODE, Define.Pubnub.CAMERA_MODE_FRONT);
                    intent.putExtra(Define.Pubnub.DIALED, true);
                    startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
}
