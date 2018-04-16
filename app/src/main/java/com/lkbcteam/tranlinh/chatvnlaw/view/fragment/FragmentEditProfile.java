package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.ProfileInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ProfileResponse;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.EditProfilePresenter;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 16/04/2018.
 */

public class FragmentEditProfile extends BaseFragment implements View.OnClickListener, EditProfilePresenter.EditProfileView{

    private ImageButton ibtnHomeMenu;

    private TextView tvUserName,tvDisplayName;
    private CircleImageView civUserAvatar;
    private EditText edtName,edtDayOfBirth,edtCardNumber,edtCertificate,edtCategory,edtExperience,edtIntro,edtAchievement,edtEducation,edtWorkPlace;
    private Button btnSubmit;

    private EditProfilePresenter presenter;


    public static FragmentEditProfile newInstance() {

        Bundle args = new Bundle();

        FragmentEditProfile fragment = new FragmentEditProfile();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ibtnHomeMenu = view.findViewById(R.id.ibtn_home_menu);
        ibtnHomeMenu.setOnClickListener(this);

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
        presenter = new EditProfilePresenter(this);
        presenter.getProfileData("name1.1510112753493");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_home_menu:
                goNextFragment(FragmentMenu.newInstance(2,true),true,false);
                break;
            case R.id.btn_submit:
                break;
            case R.id.edt_day_of_birth:
                break;
        }
    }

    @Override
    public void displayDefaultValue() {

    }

    @Override
    public void displayProfileValue(Object o) {
        ProfileResponse data = (ProfileResponse) o;
        tvUserName.setText(data.getLawyerInfo().getBaseProfile().getUserName());
        edtName.setText(data.getLawyerInfo().getBaseProfile().getDisplayName());
        Picasso.with(getContext()).load(data.getLawyerInfo().getBaseProfile().getAvatar().getUrl()).into(civUserAvatar);
        edtAchievement.setText(data.getLawyerInfo().getLawyerProfile().getAchievement());
        edtCardNumber.setText(data.getLawyerInfo().getLawyerProfile().getCardNumber());
        edtCertificate.setText(data.getLawyerInfo().getLawyerProfile().getCertificate());
        edtDayOfBirth.setText(data.getLawyerInfo().getBaseProfile().getBirthday());
        edtEducation.setText(data.getLawyerInfo().getLawyerProfile().getEducation());
        edtIntro.setText(data.getLawyerInfo().getLawyerProfile().getIntro());
        edtExperience.setText(data.getLawyerInfo().getLawyerProfile().getExp());
        edtWorkPlace.setText(data.getLawyerInfo().getLawyerProfile().getWorkPlace());
    }

    @Override
    public void onLoadFailure(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
