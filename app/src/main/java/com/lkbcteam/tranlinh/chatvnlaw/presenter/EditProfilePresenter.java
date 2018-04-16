package com.lkbcteam.tranlinh.chatvnlaw.presenter;

import com.lkbcteam.tranlinh.chatvnlaw.model.Interator.ProfileInterator;

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

    public interface EditProfileView{
        void displayDefaultValue();
        void displayProfileValue(Object o);
        void onLoadFailure(String error);
    }
}
