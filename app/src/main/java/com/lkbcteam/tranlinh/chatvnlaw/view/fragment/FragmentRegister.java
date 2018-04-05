package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.activity.HomeActivity;
import com.lkbcteam.tranlinh.chatvnlaw.fragment.*;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.RegisterPresenter;
import com.lkbcteam.tranlinh.chatvnlaw.view.RegisterView;

/**
 * Created by tranlinh on 26/03/2018.
 */

public class FragmentRegister extends BaseFragment implements View.OnClickListener, RegisterView{

    private ImageButton ibtnBackToLogin;
    private EditText edtEmail, edtPassword, edtPasswordConfirm, edtDisplayname;
    private Button btnRegister;
    private RegisterPresenter presenter;

    public static FragmentRegister newInstance() {
        FragmentRegister fragment = new FragmentRegister();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ibtnBackToLogin = view.findViewById(R.id.ibtn_back_to_login);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        edtPasswordConfirm = view.findViewById(R.id.edt_password_confirm);
        edtDisplayname = view.findViewById(R.id.edt_displayname);
        btnRegister = view.findViewById(R.id.btn_register);

        ibtnBackToLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        presenter = new RegisterPresenter(this);
        presenter.setBaseActivity(getBaseActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_back_to_login:
//                goNextFragment(FragmentLogin.newInstance(),false,false);
                goBackFragment();
                break;
            case R.id.btn_register:
                if(presenter != null){
                    presenter.signupWithFirebase(edtEmail.getText().toString(),
                            edtPassword.getText().toString(),
                            edtPasswordConfirm.getText().toString(),
                            edtDisplayname.getText().toString());
                }
                break;
        }
    }

    @Override
    public void registerSuccess() {
        getBaseActivity().startActivity(HomeActivity.class,true);
    }

    @Override
    public void registerFalure(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }
}
