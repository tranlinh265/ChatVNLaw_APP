package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.LoginPresenter;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class FragmentLogin extends BaseFragment implements View.OnClickListener, LoginPresenter.LoginView {
    private static final long MOVE_DEFAULT_TIME = 2000;
    private static final long FADE_DEFAULT_TIME = 1300;
    private TextView tvRegister;
    private ImageButton ibtnLogin;
    private EditText edtEmail, edtPass;
    private LoginPresenter loginPresenter;

    public static FragmentLogin newInstance() {

        Bundle args = new Bundle();

        FragmentLogin fragment = new FragmentLogin();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvRegister = view.findViewById(R.id.tv_register);
        ibtnLogin = view.findViewById(R.id.ibtn_login);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPass = view.findViewById(R.id.edt_password);

        tvRegister.setOnClickListener(this);
        ibtnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setBaseActivity(getBaseActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_login:
                if(loginPresenter != null){
                    if(loginPresenter.servicesOK(getContext())){
                        loginPresenter.loginWithFirebaseAuth(edtEmail.getText().toString(), edtPass.getText().toString());
                    }
                }
                break;
            case R.id.tv_register:
                goNextFragment();
                break;
        }
    }

    private void goNextFragment(){
        goNextFragment(FragmentRegister.newInstance(),true,true);
    }
    @Override
    public void loginSucess() {
        getBaseActivity().startActivity(HomeActivity.class,true);
        getBaseActivity().finish();
    }

    @Override
    public void loginFalure(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(loginPresenter != null){
            if(loginPresenter.checkLogin()){
                loginPresenter.initCloudMessaging();
                loginPresenter.storeCurrentUser();
                getBaseActivity().startActivity(HomeActivity.class,true);
            }
        }
    }
}
