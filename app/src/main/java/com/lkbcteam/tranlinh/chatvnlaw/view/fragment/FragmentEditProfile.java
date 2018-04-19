package com.lkbcteam.tranlinh.chatvnlaw.view.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.ProfileInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;
import com.lkbcteam.tranlinh.chatvnlaw.other.SharePreference;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.ProfileResponse;
import com.lkbcteam.tranlinh.chatvnlaw.presenter.EditProfilePresenter;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tranlinh on 16/04/2018.
 */

public class FragmentEditProfile extends BaseFragment implements View.OnClickListener, EditProfilePresenter.EditProfileView{

    private final String DEFAULT_START_DAY = "26";
    private final String DEFAULT_START_MONTH = "5";
    private final String DEFAULT_START_YEAR = "1995";

    private ImageButton ibtnHomeMenu;

    private TextView tvUserName,tvDisplayName;
    private CircleImageView civUserAvatar;
    private EditText edtName,edtDayOfBirth,edtCardNumber,edtCertificate,edtCategory,edtExperience,edtIntro,edtAchievement,edtEducation,edtWorkPlace;
    private Button btnSubmit;
    private CheckBox cbHs, cbShtt, cbHngd, cbNdxd, cbTcnh, cbDs, cbLdbhxh, cbDn;

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
        cbHs = view.findViewById(R.id.cb_hs);
        cbShtt = view.findViewById(R.id.cb_shtt);
        cbHngd = view.findViewById(R.id.cb_hngd);
        cbNdxd = view.findViewById(R.id.cb_ndxd);
        cbTcnh = view.findViewById(R.id.cb_tcnh);
        cbDs = view.findViewById(R.id.cb_ds);
        cbLdbhxh = view.findViewById(R.id.cb_ldbhxh);
        cbDn = view.findViewById(R.id.cb_dn);
        edtDayOfBirth.setOnClickListener( v -> showDatePicker());
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initData(View view) {
        super.initData(view);
        presenter = new EditProfilePresenter(this);
        presenter.getProfileData(SharePreference.getInstance(getActivity()).getUsername());
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
        if(data.getLawyerInfo().getLawyerSpecializes().size() > 0){
            for (ProfileResponse.Specialize specialize : data.getLawyerInfo().getSpecializes()){
                switch (Define.Specializes.convertToIntValue(specialize.getName())){
                    case 1:
                        cbHs.setChecked(true);
                        break;
                    case 2:
                        cbShtt.setChecked(true);
                        break;
                    case 3:
                        cbHngd.setChecked(true);
                        break;
                    case 4:
                        cbNdxd.setChecked(true);
                        break;
                    case 5:
                        cbTcnh.setChecked(true);
                        break;
                    case 6:
                        cbDs.setChecked(true);
                        break;
                    case 7:
                        cbLdbhxh.setChecked(true);
                        break;
                    case 8:
                        cbDn.setChecked(true);
                        break;
                }
            }
        }
    }

    private void updateLawyerInfo(){
        String birthDay = edtDayOfBirth.getText().toString();
        String achievement = edtAchievement.getText().toString();
    }

    private void showDatePicker(){
        String currentDay = edtDayOfBirth.getText().toString();
        String startDay = DEFAULT_START_DAY;
        String startMonth = DEFAULT_START_MONTH;
        String startYear = DEFAULT_START_YEAR;

        if(!TextUtils.isEmpty(currentDay)){
            startYear= currentDay.split("-")[0];
            startMonth = currentDay.split("-")[1];
            startDay = currentDay.split("-")[2];
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) ->
                edtDayOfBirth.setText(String.format("%d-%d-%d", year, month,dayOfMonth))
                ,Integer.parseInt(startYear) , Integer.parseInt(startMonth), Integer.parseInt(startDay));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
    @Override
    public void onLoadFailure(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
