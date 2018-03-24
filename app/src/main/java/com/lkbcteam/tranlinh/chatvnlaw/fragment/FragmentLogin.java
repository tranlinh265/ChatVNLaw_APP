package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.notification.DeviceToken;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;

/**
 * Created by tranlinh on 26/01/2018.
 */

public class FragmentLogin extends BaseFragment {
    private TextView tvRegister;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ImageButton ibtnLogin, ibtnFacebook;
    private EditText edtEmail, edtPass;
    private static final String TAG = "FragmentLogin";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    public static Fragment newInstance(){
        return new FragmentLogin();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvRegister = view.findViewById(R.id.tv_register);
        ibtnLogin = view.findViewById(R.id.ibtn_login);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPass = view.findViewById(R.id.edt_password);
        ibtnFacebook = view.findViewById(R.id.ibtn_facebook_login);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextFragment(FragmentRegister.newInstance(),true);
            }
        });
        if(servicesOK()){
            basicLogin();
        }
    }
    public boolean servicesOK(){
        Log.d(TAG, "servicesOK: Checking Google Services.");

        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());

        if(isAvailable == ConnectionResult.SUCCESS){
            //everything is ok and the user can make mapping requests
            Log.d(TAG, "servicesOK: Play Services is OK");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(isAvailable)){
            //an error occured, but it's resolvable
            Log.d(TAG, "servicesOK: an error occured, but it's resolvable.");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), isAvailable, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(getContext(), "Can't connect to mapping services", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    private void basicLogin() {
        ibtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty()){
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DeviceToken.initFirebaseCloudMessaging(mAuth.getCurrentUser());
                                getBaseActivity().startActivity(HomeActivity.class,true);
                            } else {
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            DeviceToken.initFirebaseCloudMessaging(currentUser);
            SharedPreferences sp = getBaseActivity().getSharedPreferences(Define.Pubnub.SHARED_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(Define.Pubnub.USER_NAME, currentUser.getUid());
            editor.apply();

            getBaseActivity().startActivity(HomeActivity.class,true);
        }
    }
}
