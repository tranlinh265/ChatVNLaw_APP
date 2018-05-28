package com.lkbcteam.tranlinh.chatvnlaw.view.activity;

import android.Manifest;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RendererCommon;
import org.webrtc.VideoCapturerAndroid;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRendererGui;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.util.List;

import me.kevingleason.pnwebrtc.PnPeer;
import me.kevingleason.pnwebrtc.PnRTCClient;
import me.kevingleason.pnwebrtc.PnRTCListener;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by tranlinh on 14/03/2018.
 */

public class VideoCallActivity extends AppCompatActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks{

    private static final  String VIDEO_TRACK_ID = "videoPn";
    private static final String LOCAL_MEDIA_STREAM_ID = "localStreamPM";
    private static final int RC_CAMERA_AND_RECORD_AUDIO = 0x1;

    private PnRTCClient pnRTCClient;
    private VideoSource localVideoSource;
    private VideoRenderer.Callbacks localRender;
    private VideoRenderer.Callbacks remoteRender;
    private GLSurfaceView videoView;

    private String username;
    private boolean webRtcInitialized;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        Bundle extras = getIntent().getExtras();
        if(extras == null || !extras.containsKey(Define.Pubnub.USER_NAME)){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        this.username = extras.getString(Define.Pubnub.USER_NAME,"");
        this.videoView = findViewById(R.id.gl_surface);

        VideoRendererGui.setView(videoView, null);
        webRtcInitialized = false;
        preInitWebRTCResource();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.webRtcInitialized) {
            this.videoView.onPause();
            this.localVideoSource.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.webRtcInitialized) {
            this.videoView.onResume();
            this.localVideoSource.restart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.webRtcInitialized) {
            this.localVideoSource.stop();
            this.pnRTCClient.onDestroy();
        }
    }

    @AfterPermissionGranted(RC_CAMERA_AND_RECORD_AUDIO)
    private void preInitWebRTCResource() {
        String[] perms = {Manifest.permission.CAMERA};
        if(EasyPermissions.hasPermissions(this,perms)){
            Bundle extras = getIntent().getExtras();
            initPeerConnectionFactory(extras);
        }else{
            EasyPermissions.requestPermissions(this,"",RC_CAMERA_AND_RECORD_AUDIO, perms);
        }
    }

    private void initPeerConnectionFactory(Bundle extras) {
        PeerConnectionFactory.initializeAndroidGlobals(
                this, // Context
                true, // Audio Enabled
                true, // Video Enabled
                true // Hardware Acceleration Enabled
        );
        PeerConnectionFactory pcFactory = new PeerConnectionFactory();
        this.pnRTCClient = new PnRTCClient(Define.Pubnub.PUB_KEY, Define.Pubnub.SUB_KEY, this.username);

        String frontFacingCam = CameraEnumerationAndroid.getNameOfFrontFacingDevice();
        String backFacingCam = CameraEnumerationAndroid.getNameOfBackFacingDevice();
        boolean frontCamera = true;

        if(Define.Pubnub.CAMERA_MODE_BACK.equals(extras.getString(Define.Pubnub.CAMERA_MODE,""))){
            frontCamera = false;
        }

        VideoCapturerAndroid capturer = (VideoCapturerAndroid)VideoCapturerAndroid.create(
                frontCamera ? frontFacingCam : backFacingCam);

        localVideoSource = pcFactory.createVideoSource(capturer,this.pnRTCClient.videoConstraints());

        VideoTrack localVideoTrack = pcFactory.createVideoTrack(VIDEO_TRACK_ID, localVideoSource);

        MediaStream mediaStream = pcFactory.createLocalMediaStream(LOCAL_MEDIA_STREAM_ID);

        mediaStream.addTrack(localVideoTrack);
        remoteRender = VideoRendererGui.create(0, 0, 100, 100,
                RendererCommon.ScalingType.SCALE_ASPECT_FILL, false);
        localRender = VideoRendererGui.create(0, 0, 100, 100,
                RendererCommon.ScalingType.SCALE_ASPECT_FILL, true);
        this.pnRTCClient.attachRTCListener(new MyRTCListener());
        this.pnRTCClient.attachLocalMediaStream(mediaStream);

        this.pnRTCClient.listenOn(this.username);
        this.pnRTCClient.setMaxConnections(1);

        webRtcInitialized = true;

        // If Define.Pubnub.CALL_USER is in the intent extras, auto-connect them.
        boolean dialed = extras.getBoolean(Define.Pubnub.DIALED, false);

        if (extras.containsKey(Define.Pubnub.CALL_USER) && !dialed) {
            Log.e(String.valueOf(!dialed), "initPeerConnectionFactory: " );
            String callUser = extras.getString(Define.Pubnub.CALL_USER, "");
            connectToUser(callUser);
        }

    }

    private void connectToUser(String callUser) {
        this.pnRTCClient.connect(callUser);
    }

    public void hangup(View v){
        this.pnRTCClient.closeAllConnections();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        preInitWebRTCResource();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onClick(View v) {

    }

    private class MyRTCListener extends PnRTCListener{
        @Override
        public void onLocalStream(final MediaStream localStream) {
            Log.i(Define.Pubnub.LOG_TAG, "onLocalStream ");
            VideoCallActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(localStream.videoTracks.size() == 0) return;
                    localStream.videoTracks.get(0).addRenderer(new VideoRenderer(localRender));
                }
            });
        }

        @Override
        public void onAddRemoteStream(final MediaStream remoteStream, PnPeer peer) {
            Log.i(Define.Pubnub.LOG_TAG, "onAddRemoteStream: ");
            VideoCallActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        if(remoteStream.videoTracks.size() == 0 && remoteStream.audioTracks.size() == 0) return;

                        remoteStream.videoTracks.get(0).addRenderer(new VideoRenderer(remoteRender));
                        VideoRendererGui.update(remoteRender, 0, 0, 100, 100, RendererCommon.ScalingType.SCALE_ASPECT_FILL, false);
                        VideoRendererGui.update(localRender, 72, 72, 25, 25, RendererCommon.ScalingType.SCALE_ASPECT_FIT, true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onCallReady(String callId) {
            super.onCallReady(callId);
            Log.i(Define.Pubnub.LOG_TAG, "onCallReady: callId" + callId);
        }

        @Override
        public void onConnected(String userId) {
            super.onConnected(userId);
            Log.i(Define.Pubnub.LOG_TAG, "onConnected: userId" + userId);
        }

        @Override
        public void onPeerConnectionClosed(PnPeer peer) {
            Log.i(Define.Pubnub.LOG_TAG, "onPeerConnectionClosed: ");
            Intent intent = new Intent(VideoCallActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
