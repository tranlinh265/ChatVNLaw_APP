package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;

/**
 * Created by tranlinh on 26/01/2018.
 */

public class FragmentRegister extends BaseFragment {
    public static Fragment newInstance(){
        return new FragmentRegister();
    }

    private ImageButton ibtnBackToLogin;
    private EditText edtEmail, edtPassword, edtPasswordConfirm, edtDisplayName;
    private FirebaseAuth mAuth;
    private Button btnRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ibtnBackToLogin = view.findViewById(R.id.ibtn_back_to_login);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        edtPasswordConfirm = view.findViewById(R.id.edt_password_confirm);
        edtDisplayName = view.findViewById(R.id.edt_displayname);
        btnRegister = view.findViewById(R.id.btn_register);

        ibtnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackFragment();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        registerUser(view);
    }

    private void registerUser(View view){

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String passwordConfirm = edtPasswordConfirm.getText().toString();
                String displayName = edtDisplayName.getText().toString();

                if(!email.isEmpty() && !password.isEmpty() && !passwordConfirm.isEmpty() && !displayName.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "OK",Toast.LENGTH_LONG);
                            }else{
                                Toast.makeText(getActivity(), "fail",Toast.LENGTH_LONG);
                            }
                        }
                    });
                }
            }
        });

    }
}
