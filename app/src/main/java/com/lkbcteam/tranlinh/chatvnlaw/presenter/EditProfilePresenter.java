package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import android.os.Bundle;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.ProfileInterator;
import com.lkbcteam.tranlinh.chatvnlaw.other.Define;

/**
 * Created by tranlinh on 16/04/2018.
 */

public class EditProfilePresenter implements ProfileInterator.LoadProfileDataListener{
    private EditProfileView callback;
    private ProfileInterator profileInterator;

    public EditProfilePresenter(EditProfileView callback){
        this.callback = callback;
        this.profileInterator = new ProfileInterator(this);
    }

    public void getProfileData(String username){
        profileInterator.loadProfileDataFromRails(username);
    }

    @Override
    public void onLoadSuccess(Object o) {
        callback.displayProfileValue(o);
    }

    @Override
    public void onLoadFailure(String error) {
        callback.onLoadFailure(error);
    }

    @Override
    public void onUpdateSuccess() {
        callback.updateSuccess();
    }

    @Override
    public void onUpdateFalure(Object error) {
        int errorCode = (int) error;
        switch (errorCode){
            case 401:
                callback.updateFalure(Define.Notice.ERROR_INVALID_TOKEN);
                break;
            default:
                callback.updateFalure(Define.Notice.UPDATE_FALURE);
                break;
        }
    }

    public void updateProfileAttr(Bundle bundle){
        profileInterator.updateProfileData(bundle);
    }
    public interface EditProfileView{
        void displayDefaultValue();
        void displayProfileValue(Object o);
        void onLoadFailure(String error);
        void updateSuccess();
        void updateFalure(String error);
    }
}
