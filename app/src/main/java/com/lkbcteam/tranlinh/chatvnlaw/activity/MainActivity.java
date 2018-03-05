package com.lkbcteam.tranlinh.chatvnlaw.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.FragmentLoginContainer;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        replaceContainerFragment(R.id.fullscreen_content, FragmentLoginContainer.newInstance());
    }

    public boolean servicesOK(){
        Log.d(TAG, "servicesOK: Checking Google Services.");

        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(isAvailable == ConnectionResult.SUCCESS){
            //everything is ok and the user can make mapping requests
            Log.d(TAG, "servicesOK: Play Services is OK");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(isAvailable)){
            //an error occured, but it's resolvable
            Log.d(TAG, "servicesOK: an error occured, but it's resolvable.");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, isAvailable, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this, "Can't connect to mapping services", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

}
