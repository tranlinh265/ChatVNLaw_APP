package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lkbcteam.tranlinh.chatvnlaw.R;

/**
 * Created by tranlinh on 26/01/2018.
 */

public class FragmentRegister extends BaseFragment {
    public static Fragment newInstance(){
        return new FragmentRegister();
    }

    private ImageButton ibtnBackToLogin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ibtnBackToLogin = view.findViewById(R.id.ibtn_back_to_login);
        ibtnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackFragment();
            }
        });
    }
}
