package com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tranlinh on 09/04/2018.
 */

public class ProfileResponse {
    @SerializedName("lawyer_info")
    @Expose
    private LawyerInfo lawyerInfo;

    public LawyerInfo getLawyerInfo() {
        return lawyerInfo;
    }

    public void setLawyerInfo(LawyerInfo lawyerInfo) {
        this.lawyerInfo = lawyerInfo;
    }
}
