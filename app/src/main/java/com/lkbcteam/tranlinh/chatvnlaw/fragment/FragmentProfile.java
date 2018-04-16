package com.lkbcteam.tranlinh.chatvnlaw.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.FirebaseLawyer;
import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.FirebaseData;
import com.lkbcteam.tranlinh.chatvnlaw.model.loaddata.User;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.BaseFragment;
import com.lkbcteam.tranlinh.chatvnlaw.view.fragment.FragmentMenu;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 31/01/2018.
 */

public class FragmentProfile extends BaseFragment implements View.OnClickListener{
    private ImageButton mIbtnHomeMenu;
    private FirebaseUser firebaseUser;
    private TextView tvUserName,tvDisplayName;
    private CircleImageView civUserAvatar;
    private EditText edtName,edtDayOfBirth,edtCardNumber,edtCertificate,edtExperience,edtIntro,edtAchievement,edtEducation,edtWorkPlace;
    private Button btnSubmit;

    public static FragmentProfile newInstance() {
        return new FragmentProfile();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mIbtnHomeMenu = view.findViewById(R.id.ibtn_home_menu);
        mIbtnHomeMenu.setOnClickListener(this);
        tvUserName = view.findViewById(R.id.tv_username);
        tvDisplayName = view.findViewById(R.id.tv_displayname);
        civUserAvatar = view.findViewById(R.id.civ_user_avatar);
        edtName = view.findViewById(R.id.edt_name);
        edtDayOfBirth = view.findViewById(R.id.edt_day_of_birth);
        edtDayOfBirth.setOnClickListener(this);
        edtCardNumber = view.findViewById(R.id.edt_card_number);
        edtCertificate = view.findViewById(R.id.edt_certificate);
        edtExperience =view.findViewById(R.id.edt_experience);
        edtIntro = view.findViewById(R.id.edt_intro);
        edtAchievement = view.findViewById(R.id.edt_achievement);
        edtEducation = view.findViewById(R.id.edt_education);
        edtWorkPlace = view.findViewById(R.id.edt_workplace);
        btnSubmit = view.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            User user = new User();
            user.getUser(firebaseUser, new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    com.lkbcteam.tranlinh.chatvnlaw.model.User mUser = dataSnapshot.getValue(com.lkbcteam.tranlinh.chatvnlaw.model.User.class);
                    FragmentProfile.this.tvUserName.setText("@"+mUser.getUsername());
                    Picasso.with(getContext()).load(mUser.getPhotoURL()).into(civUserAvatar);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            FirebaseData.getLawyerInfo(firebaseUser, new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        FirebaseLawyer lawyer = dataSnapshot.getValue(FirebaseLawyer.class);

                        edtName.setText(lawyer.getFullname());
                        edtAchievement.setText(lawyer.getAchievement());
                        edtCardNumber.setText(lawyer.getCardNumber());
                        edtCertificate.setText(lawyer.getCertificate());
                        edtDayOfBirth.setText(lawyer.getBirthday());
                        edtEducation.setText(lawyer.getEducation());
                        edtIntro.setText(lawyer.getIntro());
                        edtExperience.setText(lawyer.getExp());
                        edtWorkPlace.setText(lawyer.getWorkPlace());
                    }else{
                        Toast.makeText(getContext(), getContext().getString(R.string.notice_cant_access),Toast.LENGTH_LONG).show();
                        goNextFragment(FragmentMenu.newInstance(2, false),true,false);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    databaseError.toException().printStackTrace();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(2, true),true,false);
                break;
            case R.id.btn_submit:
                submitInfo();
                break;
            case R.id.edt_day_of_birth:
                showDatePicker();
                break;
        }
    }

    private void submitInfo(){
        FirebaseLawyer lawyer = new FirebaseLawyer();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser == null){
            return;
        }
        lawyer.setFullname(edtName.getText().toString());
        lawyer.setAchievement(edtAchievement.getText().toString());
        lawyer.setBirthday(edtDayOfBirth.getText().toString());
        lawyer.setCardNumber(edtCardNumber.getText().toString());
        lawyer.setCertificate(edtCertificate.getText().toString());
        lawyer.setEducation(edtEducation.getText().toString());
        lawyer.setExp(edtExperience.getText().toString());
        lawyer.setIntro(edtIntro.getText().toString());
        lawyer.setWorkPlace(edtWorkPlace.getText().toString());
        FirebaseData.updateLawyerInfo(firebaseUser,lawyer);
        Toast.makeText(getContext(), getContext().getString(R.string.edit_success),Toast.LENGTH_LONG).show();
    }
    private void showDatePicker(){
        String currentDay = edtDayOfBirth.getText().toString();
        String startDay = "26";
        String startMonth = "5";
        String startYear = "1995";

        if(!currentDay.isEmpty()){
            startDay = currentDay.split("/")[0];
            startMonth = currentDay.split("/")[1];
            startYear = currentDay.split("/")[2];
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtDayOfBirth.setText(String.format("%d/%d/%d",dayOfMonth,month,year));
            }
        }, Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
